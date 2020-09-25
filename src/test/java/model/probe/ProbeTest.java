package model.probe;

import model.rule.filter.Filter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProbeTest {
    static int temp=0;

    @BeforeEach
    void set(){
        temp=0;
    }

    Probe generateTestProbe(){
        Probe p=new Probe();
        p.addRule(new Filter("test-technology","in"));
        p.addRule(new Filter("hardware-problem","not-in"));
        p.setAction(i -> ++temp);
        return p;
    }

    @Test
    void handle(){
        Probe p=generateTestProbe();
        p.handle("test-technology is one of the newest brand in programming these days");
        assertEquals(temp,1);
        p.handle("test-technology is one of the mains reasons that we have hardware-problems");
        assertEquals(temp,1);
    }

}
