package model.rule.filter;

import model.rule.Rule;
import model.rule.filter.operator.Equal;
import model.rule.filter.operator.In;
import model.rule.filter.operator.NotIn;
import model.rule.filter.operator.Operator;

import java.util.Map;

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
