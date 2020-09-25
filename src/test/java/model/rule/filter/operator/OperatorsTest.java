package model.rule.filter.operator;

import model.rule.filter.Filter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class OperatorsTest {

    private Map<String, Operator>operators=Filter.getOperators();

    @Test
    public void nameMatchTest(){
        for (Map.Entry<String, Operator> entry : operators.entrySet()) {
            Assertions.assertEquals(entry.getKey(),entry.getValue().getName());
        }
    }

    @Test
    public void inOperatorTest(){
        Operator in=operators.get("in");
        Assertions.assertTrue(in.validate("i am testing","test"));
        Assertions.assertTrue(in.validate("i am testing","testing"));
        Assertions.assertTrue(in.validate("i am testing"," testing"));
        Assertions.assertTrue(in.validate("i am testing","i am testing"));

        Assertions.assertFalse(in.validate("i am testing"," testingo"));
        Assertions.assertFalse(in.validate("i am testing","i am testing i am testing"));
    }

    @Test
    public void notInOperatorTest(){
        Operator notIn=operators.get("not-in");
        Assertions.assertFalse(notIn.validate("i am testing","test"));
        Assertions.assertFalse(notIn.validate("i am testing","testing"));
        Assertions.assertFalse(notIn.validate("i am testing"," testing"));
        Assertions.assertFalse(notIn.validate("i am testing","i am testing"));

        Assertions.assertTrue(notIn.validate("i am testing"," testingo"));
        Assertions.assertTrue(notIn.validate("i am testing","i am testing i am testing"));

    }
    @Test
    public void equalsOperatorTest(){
        Operator equal=operators.get("equal");
        Assertions.assertFalse(equal.validate("i am testing","test"));
        Assertions.assertFalse(equal.validate("i am testing","testing"));
        Assertions.assertFalse(equal.validate("i am testing"," testing"));
        Assertions.assertFalse(equal.validate("i am testing"," testingo"));
        Assertions.assertFalse(equal.validate("i am testing","i am testing i am testing"));

        Assertions.assertTrue(equal.validate("i am testing","i am testing"));

    }




}
