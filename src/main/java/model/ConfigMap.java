package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.probe.Probe;
import model.probe.ProbeManager;

import java.io.File;
import java.io.IOException;

public class ConfigMap {
    private final String path = "src/main/resources/messiah.yaml";
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
            .findAndRegisterModules()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    public ProbeManager load() {
        return this.load(path);
    }
    public void store(ProbeManager probeManager) {
        this.store(probeManager,path);
    }

    public ProbeManager load(String path){
        try {
            ProbeManager probeManager = mapper.readValue(new File(path), ProbeManager.class);
            return probeManager;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ProbeManager(8000);

    }

    public void store(ProbeManager probeManager, String path){
        try {
            mapper.writeValue(new File(path), probeManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
