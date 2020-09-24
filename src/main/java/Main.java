import model.ConfigMap;
import model.probe.ProbeManager;

public class Main {
    public static void main(String[] args) {
        ProbeManager probeManager = new ConfigMap().load();//.init();
        probeManager.init();
        ProbeManager ne =new ProbeManager(7000);
        ne.init();
    }
}