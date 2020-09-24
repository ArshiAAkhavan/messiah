package model.responce;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import model.rule.Rule;
import model.rule.filter.Filter;

import java.io.IOException;

@JsonDeserialize(using = ActionDeserializer.class)
public interface Action {
    void act(String input);
}
class ScriptSerializer extends JsonSerializer<Script> {
    @Override
    public void serialize(Script script,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) {
        try {
            jGen.writeStartObject();
            jGen.writeStringField("command", script.command);
            jGen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serializeWithType(Script value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, serializers);
    }
}

class ActionDeserializer extends StdDeserializer<Action> {

    public ActionDeserializer() {
        this(null);
    }

    public ActionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Action deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{

        JsonNode node = jp.getCodec().readTree(jp);
        if (node.has("command")) {      //script
            return new Script(node.get("command").asText());
        }
        return null;
    }
}

