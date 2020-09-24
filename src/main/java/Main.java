import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.probe.Probe;
import model.probe.ProbeManager;
import model.responce.Script;
import model.rule.filter.Filter;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

//        Probe test = new Probe("/test");
//        test.addRule(new Filter("cassandra", "in"));
//        test.addRule(new Filter("disk", "not-in"));
//        test.setAction(new Script("ls -lah"));
//
//        ProbeManager probeManager = new ProbeManager(7000);
//        probeManager.addProbe(test);


        ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
                .findAndRegisterModules()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        try {
            ProbeManager probeManager = mapper.readValue(new File("src/main/resources/probe.yaml"), ProbeManager.class);
            probeManager.init();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            mapper.writeValue(new File("src/main/resources/probe.yaml"), probeManager);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}