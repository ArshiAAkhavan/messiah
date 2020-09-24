package model.rule.filter.operator;

public class Equal implements Operator {
    @Override
    public boolean validate(String source, String key) {
        return source.equals(key);
    }

    @Override
    public String getName() {
        return "equal";
    }
}
