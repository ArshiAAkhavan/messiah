package model.rule.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import model.rule.Rule;
import model.rule.filter.operator.Equal;
import model.rule.filter.operator.In;
import model.rule.filter.operator.NotIn;
import model.rule.filter.operator.Operator;

import java.io.IOException;
import java.util.Map;

@JsonSerialize(using = FilterSerializer.class)
public class Filter implements Rule {
    private static final Map<String, Operator> operators = Map.of(
            "in", new In(),
            "not-in", new NotIn(),
            "equal", new Equal()
    );

    String key;
    Operator operator;

    public Filter(String key, String operator) {
        this.key = key;
        this.operator = operators.get(operator);
    }

    @Override
    public boolean passes(String source) {
        return this.operator.validate(source, key);
    }
}
class FilterSerializer extends JsonSerializer<Filter> {
    @Override
    public void serialize(Filter filter,
                          JsonGenerator jGen,
                          SerializerProvider serializerProvider) {
        try {
            jGen.writeStartObject();
//            jGen.writeStringField("type","filter");
            jGen.writeStringField("key",filter.key);
            jGen.writeStringField("operator", filter.operator.getName());
            jGen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serializeWithType(Filter value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, serializers);
    }
}

