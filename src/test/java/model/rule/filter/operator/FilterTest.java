package model.rule.filter.operator;

import model.rule.filter.Filter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTest {

    @Test
    void passTest(){
        Filter f=new Filter("technology","in");
        assertTrue(f.passes("21'st century is the center of technology"));
        assertFalse(f.passes("tech+no+logy is what makes today's world possible"));
    }
}
