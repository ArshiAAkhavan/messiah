package model.Rule;

import model.Rule.Operator.Operator;

import java.util.HashMap;
import java.util.Map;

public class Filter implements Rule{
    private Map<String,Operator> operators=new HashMap<>();
    String key;
    Operator operator;

    public Filter(String key, String operator) {
        this.key = key;
        this.operator = operators.get(operator);
    }

    @Override
    public boolean passes(String source) {
        return this.operator.validate(source,key);
    }
}
