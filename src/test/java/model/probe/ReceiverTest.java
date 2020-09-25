package model.probe;

import model.rule.filter.Filter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiverTest {


    static int temp=0;
    @BeforeEach
    void set(){
        temp=0;
    }

    Probe generateTestProbe(String seed1,String seed2){
        Probe p=new Probe();
        p.addRule(new Filter(seed1,"in"));
        p.addRule(new Filter(seed2,"not-in"));
        p.setAction(i -> ++temp);
        return p;
    }
    private Receiver generateReceiver() {
        Probe probe1=generateTestProbe("database","cpu");
        Probe probe2=generateTestProbe("k8s","disk-usage");
        Receiver r=new Receiver("/test");
        r.addProbe(probe1).addProbe(probe2);
        return r;
    }

    @Test
    void singleHandleTest() {
        Receiver r = generateReceiver();

        r.handle("database no2 has high cpu usage");
        assertEquals(temp,0);

        r.handle("database no23 has high disk usage");
        assertEquals(temp,1);

        r.handle("k8s instance no23 has high disk usage");
        assertEquals(temp,2);

        r.handle("k8s instance no23 has high disk-usage");
        assertEquals(temp,2);

    }
    @Test
    void multipleHandleTest() {
        Receiver r = generateReceiver();

        r.handle("database no2 has high cpu usage");
        assertEquals(temp,0);

        r.handle("database no23 has eaten up the reserved memory of the k8s instance no3");
        assertEquals(temp,2);
    }
}