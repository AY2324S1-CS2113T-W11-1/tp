package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.ResourceList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitCommandTest {
    @Test
    void execute() {
        ResourceList resourcelist = new ResourceList();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute("", resourcelist);

        String output = outputStream.toString();
        String expectedOutput = "Bye, hope to see you again soon!" + System.lineSeparator()+
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

}
