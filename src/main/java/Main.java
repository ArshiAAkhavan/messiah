import model.ConfigMap;
import model.probe.ProbeManager;

public class Main {
    public static void main(String[] args) {
        new ConfigMap().load().init();
    }
}