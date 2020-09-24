import chert.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class Main2 {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
//            Item item = mapper.readValue(new File("src/main/resources/probe.yaml"), Item.class);
            Test test = mapper.readValue(new File("src/main/resources/probe.yaml"), Test.class);
//            User user = mapper.readValue(new File("src/main/resources/probe.yaml"), User.class);
            System.out.println("al");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Item item=new ItemA(1, "itemName", new User(2, "null"));
        Test test=new Test();
        User user=new User(1,"adf");
        test.a=new ItemA(1,"a",new User(3,"hasan"));
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        try {
//            mapper.writeValue(new File("src/main/resources/probe.yaml"), item);
            mapper.writeValue(new File("src/main/resources/probe.yaml"), test);
//            mapper.writeValue(new File("src/main/resources/probe.yaml"), user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Test{
    public Item a;
//    public Int a=new Impl2();
}













