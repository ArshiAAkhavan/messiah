package model.rule;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.rule.filter.Filter;

import java.io.IOException;

@JsonDeserialize(using = RuleDeserializer.class)
public interface Rule {
    boolean passes(String source);
}

class RuleDeserializer extends StdDeserializer<Rule> {

    public RuleDeserializer() {
        this(null);
    }

    public RuleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Rule deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{

        JsonNode node = jp.getCodec().readTree(jp);
        if (node.has("key")){                   //filter
            return new Filter(node.get("key").asText(),node.get("operator").asText());
        }else if(node.has("script")){           //script
            return new Script(node.get("command").asText());
        }
        return null;
    }
}
