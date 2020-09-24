package model.Rule.Operator;

public class In implements Operator{
    @Override
    public boolean validate(String source, String key) {
        return source.contains(key);
    }
}
