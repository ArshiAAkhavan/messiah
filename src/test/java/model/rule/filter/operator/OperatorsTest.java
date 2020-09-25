package model.rule.filter.operator;

import model.rule.filter.Filter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OperatorsTest {

    private Map<String, Operator>operators=Filter.getOperators();

    @Test
    void nameMatchTest(){
        for (Map.Entry<String, Operator> entry : operators.entrySet()) {
            assertEquals(entry.getKey(),entry.getValue().getName());
        }
    }

    @Test
    void inOperatorTest(){
        Operator in=operators.get("in");
        assertTrue(in.validate("i am testing","test"));
        assertTrue(in.validate("i am testing","testing"));
        assertTrue(in.validate("i am testing"," testing"));
        assertTrue(in.validate("i am testing","i am testing"));

        assertFalse(in.validate("i am testing"," testingo"));
        assertFalse(in.validate("i am testing","i am testing i am testing"));
    }

    @Test
    void notInOperatorTest(){
        Operator notIn=operators.get("not-in");
        assertFalse(notIn.validate("i am testing","test"));
        assertFalse(notIn.validate("i am testing","testing"));
        assertFalse(notIn.validate("i am testing"," testing"));
        assertFalse(notIn.validate("i am testing","i am testing"));

        assertTrue(notIn.validate("i am testing"," testingo"));
        assertTrue(notIn.validate("i am testing","i am testing i am testing"));

    }
    @Test
    void equalsOperatorTest(){
        Operator equal=operators.get("equal");
        assertFalse(equal.validate("i am testing","test"));
        assertFalse(equal.validate("i am testing","testing"));
        assertFalse(equal.validate("i am testing"," testing"));
        assertFalse(equal.validate("i am testing"," testingo"));
        assertFalse(equal.validate("i am testing","i am testing i am testing"));

        assertTrue(equal.validate("i am testing","i am testing"));

    }




}
