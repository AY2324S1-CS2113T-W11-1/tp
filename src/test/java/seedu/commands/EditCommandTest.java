package seedu.commands;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.ResourceList;
import seedu.data.SysLibException;
import seedu.util.TestUtil;


import static seedu.commands.EditCommand.RESOURCE_NOT_FOUND;
import static seedu.commands.EditCommand.EDIT_SUCCESS;
import static seedu.common.Messages.formatLastLineDivider;
import static seedu.ui.UI.LINESEPARATOR;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandTest {
    private static ResourceList testResourceList = new ResourceList();
    private static ResourceList resourcelist = new ResourceList();
    private final ResourceList emptyResourceList = new ResourceList();
    private final TestUtil testUtil = new TestUtil();
    private final Command editCommand = new EditCommand();

    @BeforeAll
    public static void setup() throws SysLibException {
        testResourceList = TestUtil.fillTestList();
    }

    @Test
    public void testEditResourceNotFound() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, "/i 123 /t NEWTITLE",
                emptyResourceList.getResourceList());
        String expectedMessage =  RESOURCE_NOT_FOUND;
        expectedMessage += LINESEPARATOR;
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    public void testNoArgumentGiven() throws SysLibException {
        assertThrows(SysLibException.class, ()->editCommand.execute("/i 123", resourcelist));

    }
    @Test
    public void testNotBookBehavior() throws SysLibException {
        resourcelist = testResourceList;
        assertThrows(SysLibException.class, ()->editCommand.execute("/i 1 /g Horror", resourcelist));
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
        String outputMessage = testUtil.getOutputMessage(editCommand, arguments, testResourceList.getResourceList());
        String expectedMessage = EDIT_SUCCESS + formatLastLineDivider((testResourceList.
                getResourceList().get(1)).toString());
        expectedMessage += LINESEPARATOR;
        assertEquals(expectedMessage, outputMessage);
    }
}
