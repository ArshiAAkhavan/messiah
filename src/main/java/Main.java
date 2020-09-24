import model.Probe;
import model.ProbeManager;
import model.Responce.Script;
import model.Rule.Filter;

public class Main {
    public static void main(String[] args) {

        Probe test=new Probe("/test");
        test.addRule(new Filter("cassandra","in"));
        test.addRule(new Filter("disk","not-in"));
        test.setAction(new Script("ls -lah"));

        ProbeManager probeManager=new ProbeManager();
        probeManager.addProbe(test);
        probeManager.init();
    }
}