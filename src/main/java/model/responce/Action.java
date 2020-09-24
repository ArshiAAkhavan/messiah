package model.responce;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import model.rule.filter.Filter;

import java.io.IOException;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = Script.class, name = "script")})
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
            jGen.writeStringField("type","script");
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
