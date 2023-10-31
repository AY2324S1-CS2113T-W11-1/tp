package seedu.util;

import seedu.commands.Command;
import seedu.data.Resource;
import seedu.data.ResourceList;
import seedu.data.Status;
import seedu.data.SysLibException;
import seedu.data.Book;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtil {

    public String getOutputMessage(Command c, String m, ResourceList resourceList) throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        c.execute(m, resourceList);
        return outputStream.toString();
    }

    public static String getCurrentDate(){
        LocalDateTime dateReceived = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dateReceived.format(formatter);
    }

    public static ResourceList fillTestList() {
        ResourceList testResourceList = new ResourceList();
        String[] genres = {"Horror", "Fiction"};
        String[] genresAdventure = {"Adventure"};
        String[] genresNull = {null};

        Resource test1 = new Resource("title1", "1", Status.AVAILABLE);
        Book testBook = new Book("title2", "2", "author", genres, 123123, Status.AVAILABLE);
        Book testBook2 = new Book("title3", "3", "author", genresAdventure, 123123,
                Status.AVAILABLE);
        Book testBook3 = new Book("title3", "4", "author", genresNull, 123123, Status.AVAILABLE);

        testResourceList.getResourceList().add(test1);
        testResourceList.getResourceList().add(testBook);
        testResourceList.getResourceList().add(testBook2);
        testResourceList.getResourceList().add(testBook3);
        return testResourceList;
    }
}
