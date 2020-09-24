package model.Rule;

import model.Rule.Operator.Equal;
import model.Rule.Operator.In;
import model.Rule.Operator.NotIn;
import model.Rule.Operator.Operator;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

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
