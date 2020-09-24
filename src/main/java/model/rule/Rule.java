package model.rule;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.rule.filter.Filter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = Filter.class, name = "filter"),
                @JsonSubTypes.Type(value = Script.class, name = "script")})
public interface Rule {
    boolean passes(String source);
}
