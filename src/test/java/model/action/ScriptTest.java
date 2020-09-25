package model.action;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScriptTest {


    @Test
    void commandActTest() {
        Script failCommand = new Script("ls -lah");
        assertNotEquals(failCommand.act("some input"),0);

        Script successCommand = new Script("echo");
        assertEquals(successCommand.act("some input"),0);
    }

    @Test
    void scriptActTest() {

        Script successScript = new Script("sh src/main/resources/test_success_script.sh");
        assertEquals(successScript.act("some input"),0);

        Script failsScript = new Script("sh src/main/resources/test_fail_script.sh");
        assertNotEquals(failsScript.act("some input"),0);
    }
}