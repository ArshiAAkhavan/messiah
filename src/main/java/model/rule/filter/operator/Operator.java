package model.rule.filter.operator;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.rule.Script;
import model.rule.filter.Filter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = In.class, name = "filter"),
        @JsonSubTypes.Type(value = Script.class, name = "script")})
public interface Operator {
    boolean validate(String source , String key);

    String getName();
}
