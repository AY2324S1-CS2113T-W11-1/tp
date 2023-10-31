package seedu.commands;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.ResourceList;
import seedu.data.SysLibException;
import seedu.util.TestUtil;


import static seedu.commands.ListCommand.GENERIC_MESSAGE;
import static seedu.commands.ListCommand.FILTER_MESSAGE;
import static seedu.commands.ListCommand.ZERO_RESOURCES_MESSAGE;

import static seedu.ui.UI.LINESEPARATOR;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandTest {

    private static ResourceList testResourceList = new ResourceList();
    private static ResourceList resourcelist = new ResourceList();
    private final ResourceList emptyResourceList = new ResourceList();
    private final TestUtil testUtil = new TestUtil();

    private final Command listCommand = new ListCommand();


    @BeforeAll
    public static void setup() throws SysLibException {
        testResourceList = TestUtil.fillTestList();

    }

    @Test
    public void testEmptyListMessage() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "", emptyResourceList.getResourceList());
        String expectedMessage = GENERIC_MESSAGE;
        expectedMessage +=  ZERO_RESOURCES_MESSAGE + LINESEPARATOR;
        assertEquals(expectedMessage, outputMessage);

    }

    @Test
    public void testListByTagBehavior() {
        resourcelist = testResourceList;
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/tag", resourcelist));

    }
    @Test
    public void testListByGenreBehavior()  {
        resourcelist = testResourceList;
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/g", resourcelist));

    }

    @Test
    public void testNoFilteredListDisplay() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "/g Thriller",
                testResourceList.getResourceList());
        String expectedMessage = FILTER_MESSAGE;
        expectedMessage += ZERO_RESOURCES_MESSAGE + LINESEPARATOR;
        assertEquals(expectedMessage, outputMessage);

    }
}
