package model.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptTest {

    @Test
    public void commandPassesTest(){
        Script failCommand=new Script("ls -lah");
        assertFalse(failCommand.passes("some input"));

        Script successCommand=new Script("echo");
        assertTrue(successCommand.passes("some input"));
    }

    @Test
    public void scriptPassesTest() {

        Script successScript = new Script("sh src/main/resources/test_success_script.sh");
        assertTrue(successScript.passes("some input"));

        Script failsScript = new Script("sh src/main/resources/test_fail_script.sh");
        assertFalse(failsScript.passes("some input"));
    }
}