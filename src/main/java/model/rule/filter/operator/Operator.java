package model.rule.filter.operator;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.rule.Rule;
import model.rule.Script;
import model.rule.filter.Filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@JsonDeserialize(using = OperatorDeserializer.class)
public interface Operator {
    boolean validate(String source , String key);

    String getName();
}
class OperatorDeserializer extends StdDeserializer<Operator> {

    public OperatorDeserializer() {
        this(null);
    }

    public OperatorDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Operator deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
                .findAndRegisterModules()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        JsonNode node = jp.getCodec().readTree(jp);
        Rule r = null;
        if (node.has("key")){                   //filter
            r = mapper.readValue(new ByteArrayInputStream(node.toString().getBytes()), Filter.class);
        }else if(node.has("script")){           //script
            r = mapper.readValue(new ByteArrayInputStream(node.toString().getBytes()), Script.class);
        }
        return null;
    }
}