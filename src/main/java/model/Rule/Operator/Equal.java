package model.Rule.Operator;

public class Equal implements Operator {
    @Override
    public boolean validate(String source, String key) {
        return source.equals(key);
    }
}
