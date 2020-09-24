package model.Responce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Script implements Action {

    String command;

    public Script(String command) {
        this.command = command;
    }

    @Override
    public void act(String input) {
        try {
            String s;
            Process p=Runtime.getRuntime().exec(command+" "+input);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}
