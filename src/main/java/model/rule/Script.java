package model.rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Script implements Rule {
    private String command;
    public Script(String command) {
        this.command=command;
    }

    @Override
    public boolean passes(String input) {
        try {
            String s;
            Process p=Runtime.getRuntime().exec(command+" "+input);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
            return p.exitValue()==0;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
