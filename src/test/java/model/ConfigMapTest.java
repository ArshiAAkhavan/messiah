package model;

import model.action.Script;
import model.probe.Probe;
import model.probe.ProbeManager;
import model.probe.Receiver;
import model.rule.filter.Filter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ConfigMapTest {

    ConfigMap configMap=new ConfigMap();
    String configPathPrefix="src/main/resources/";

    Probe generateTestProbe(String seed1,String seed2){
        return new Probe()
                .addRule(new Filter(seed1,"in"))
                .addRule(new Filter(seed2,"not-in"))
                .setAction(new Script("echo"));

    }
    Receiver generateReceiver(String path,String seed1,String seed2) {
        return new Receiver(path)
                .addProbe(generateTestProbe(seed1,"cpu"))
                .addProbe(generateTestProbe(seed2,"disk-usage"));
    }

    ProbeManager generateProbeManager(){
        return new ProbeManager(4545)
                .addReceiver(generateReceiver("/cassandra","cassandra","scylla"))
                .addReceiver(generateReceiver("/k8s","k8s","docker"))
                .addReceiver(generateReceiver("/monitoring","grafana","datadog"));

    }

    @Test
    void load() {
        ProbeManager probeManager =configMap.load(configPathPrefix+"correct_test_config.yaml");
        configMap.store(probeManager,configPathPrefix+"test_config.yaml");
        assertTrue(isSame(configPathPrefix+"test_config.yaml",configPathPrefix+"correct_test_config.yaml"));
    }

    private boolean isSame(String firstPath, String secondPath) {
        try {
            String first = new String(Files.readAllBytes(Paths.get(firstPath)), StandardCharsets.UTF_8);
            String second = new String(Files.readAllBytes(Paths.get(secondPath)), StandardCharsets.UTF_8);
            return first.equals(second);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}