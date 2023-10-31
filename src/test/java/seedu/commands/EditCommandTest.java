package seedu.commands;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;


import static seedu.commands.EditCommand.RESOURCE_NOT_FOUND;
import static seedu.commands.EditCommand.EDIT_SUCCESS;
import static seedu.common.Messages.formatLastLineDivider;
import static seedu.ui.UI.LINESEPARATOR;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandTest {
    private static List<Resource> testResourceList = new ArrayList<>();
    private Parser parser = new Parser();
    private List<Resource> emptyResourceList = new ArrayList<>();
    private TestUtil testUtil = new TestUtil();
    private Command editCommand = new EditCommand();

    @BeforeAll
    public static void setup() throws SysLibException {
        testResourceList = TestUtil.fillTestList();

    }

    @Test
    public void testEditResourceNotFound() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, "/i 123 /t NEWTITLE", emptyResourceList);
        String expectedMessage =  RESOURCE_NOT_FOUND;
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    public void testNoArgumentGiven() throws SysLibException {
        assertThrows(SysLibException.class, ()->editCommand.execute("/i 123", parser));

    }
    @Test
    public void testNotBookBehavior() throws SysLibException {
        parser.resourceList = testResourceList;
        assertThrows(SysLibException.class, ()->editCommand.execute("/i 1 /g Horror", parser));
    }
    @Test
    public void testEditTitleBehavior() throws SysLibException {
        executeEditSuccessBehavior("/i 2 /t NEWTITLE");
    }

    @Test
    public void testEditAuthorBehavior() throws SysLibException {
        executeEditSuccessBehavior("/i 2 /a NEWAUTHOR");
    }

    @Test
    public void testEditGenreBehavior() throws SysLibException {
        executeEditSuccessBehavior("/i 2 /g Horror, Action, Fantasy");
    }

    private void executeEditSuccessBehavior(String arguments) throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, arguments, testResourceList);
        String expectedMessage = EDIT_SUCCESS + formatLastLineDivider((testResourceList.get(1)).toString());
        assertEquals(expectedMessage, outputMessage);
    }
}
