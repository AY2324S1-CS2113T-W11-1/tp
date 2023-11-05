package seedu.ui;
import seedu.data.resources.Resource;
import seedu.data.resources.Book;
import seedu.data.resources.EBook;
import seedu.data.resources.Magazine;
import seedu.data.resources.EMagazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.CD;
import seedu.exception.SysLibException;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import static seedu.common.FormatMessages.formatADivider;
import static seedu.common.FormatMessages.formatLastLineDivider;

public class UI {
    public static final String LINESEPARATOR = System.lineSeparator();
    public static final String LINEDIVIDER = "____________________________________________________________";
    public static final String SEPARATOR_LINEDIVIDER = LINESEPARATOR + LINEDIVIDER;

    public static final String ZERO_RESOURCES_MESSAGE =  formatLastLineDivider("There are currently 0 resources.");
    protected static String logo =
            " ____            _     _ _        ____ _     ___ \n" +
                    "/ ___| _   _ ___| |   (_) |__    / ___| |   |_ _|\n" +
                    "\\___ \\| | | / __| |   | | '_ \\  | |   | |    | | \n" +
                    " ___) | |_| \\__ \\ |___| | |_) | | |___| |___ | | \n" +
                    "|____/ \\__, |___/_____|_|_.__/   \\____|_____|___|\n" +
                    "       |___/                                     ";

    protected Scanner myScanner;

    public UI(){
        this.myScanner = new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        showLine();
        System.out.println(logo);
        System.out.println("What would you like to do?");
        showLine();
    }

    public void showExitMessage(){
        System.out.println("Bye, hope to see you again soon!");
        showLine();
    }

    public void showHelpMessage(){
        System.out.println("Commands available:");
        System.out.println("add: adds a new resource to the library inventory." +
                "(e.g. add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE /s STATUS])");
        System.out.println("delete: deletes the resource with the specified ID from the library inventory. " +
                "(e.g. delete /id 123456789)");
        System.out.println("list: list all resources OR filter by certain tags or genre." +
                "(e.g. list /tag B /g Fiction");
        System.out.println("find: find a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188)");
        System.out.println("edit: Edit a listing by entering its isbn to update its details. " +
                "(e.g. edit /i 123 /t NEW_TITLE /a NEW_AUTHOR)");
        System.out.println("eventadd: Add an event to the event list " +
                "(e.g. eventadd /t TITLE /date DATE [/desc DESCRIPTION])");
        System.out.println("eventlist: List out all the event list (e.g. eventlist)");
        System.out.println("eventdelete: Delete an event in the event list based on the index " +
                "(e.g. eventdelete /i INDEX)");
        System.out.println("exit: displays a farewell message and exits the program (e.g. exit)");
        System.out.println("For more information, please refer to our user guide at:" +
                "https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html");
        showLine();
    }

    public void showError(Exception e){
        System.out.println(e);
    }

    public String readCommand(){
        System.out.print("> ");
        return myScanner.nextLine();
    }

    public void showLoadMessage(String filepath, List<Resource> resourcelist){
        showLine();
        System.out.println("Storage file found @ " + filepath);
        System.out.printf("Loaded %d listings!%n", resourcelist.size());
    }

    public void showLine(){
        System.out.println(LINEDIVIDER);
    }

    public static String showResourcesDetails(List<Resource> resourcesList) throws SysLibException {

        String messageToDisplay = "";

        if (resourcesList.isEmpty()){
            messageToDisplay += ZERO_RESOURCES_MESSAGE;

        } else {

            String displayFormat = "%-15s %-5s %-25s %-13s %-25s %-40s %-10s %-15s %-15s" + LINESEPARATOR;

            Formatter bookDisplayFormatter = buildBookFormatter(displayFormat);
            Formatter magazineDisplayFormatter = buildMagazineFormatter(displayFormat);
            Formatter cdDisplayFormatter = buildCDFormatter(displayFormat);
            Formatter newspaperDisplayFormatter = buildNewspaperFormatter(displayFormat);


            for (int i = 0; i < resourcesList.size(); i += 1) {
                Resource resource = resourcesList.get(i);

                if (resource instanceof Book || resource instanceof EBook){
                    bookDisplayFormatter = resource.toTableFormat(displayFormat, bookDisplayFormatter);
                } else if (resource instanceof Magazine || resource instanceof EMagazine){
                    magazineDisplayFormatter = resource.toTableFormat(displayFormat, magazineDisplayFormatter);
                } else if(resource instanceof CD ) {
                    cdDisplayFormatter = resource.toTableFormat(displayFormat, cdDisplayFormatter);
                } else if(resource instanceof Newspaper || resource instanceof ENewspaper){
                    newspaperDisplayFormatter = resource.toTableFormat(displayFormat, newspaperDisplayFormatter);
                }

            }

            messageToDisplay += bookDisplayFormatter.toString() + LINESEPARATOR;
            messageToDisplay += magazineDisplayFormatter.toString()  + LINESEPARATOR;
            messageToDisplay += cdDisplayFormatter.toString()  + LINESEPARATOR;
            messageToDisplay += newspaperDisplayFormatter.toString() + LINESEPARATOR;

            messageToDisplay += formatLastLineDivider("There are currently " + resourcesList.size() +
                    " resource(s).");

        }


        return messageToDisplay;
    }

    public static Formatter buildBookFormatter(String displayFormat){
        Object[] bookArgs = {"ID", "Tag", "Title", "ISBN", "Author", "Genre", "Link", "Status", "Received Date"};
        String bookHeader = String.format("%89s"+ LINESEPARATOR, "[BOOKS]");
        Formatter bookDisplayFormatter = buildDisplayFormatter(displayFormat, bookArgs, bookHeader, "%-170s");

        return bookDisplayFormatter;
    }
    public static Formatter buildMagazineFormatter(String displayFormat){
        Object[] magazineArgs = {"ID", "Tag", "Title", "ISBN", "Brand", "Issue", "Link", "Status", "Received Date"};
        String magazineHeader = String.format("%91s"+ LINESEPARATOR, "[MAGAZINES]");
        Formatter magazineDisplayFormatter = buildDisplayFormatter(displayFormat, magazineArgs, magazineHeader,
                "%-170s");
        return magazineDisplayFormatter;
    }

    public static Formatter buildCDFormatter(String displayFormat){
        Object[] cdArgs = { "ID", "Tag", "Title", "ISBN", "Creator", "Type", "Link", "Status", "Received Date"};
        String cdHeader = String.format("%86s"+ LINESEPARATOR, "[CDS]");
        Formatter cdDisplayFormatter = buildDisplayFormatter(displayFormat, cdArgs, cdHeader, "%-170s");
        return cdDisplayFormatter;
    }

    public static Formatter buildNewspaperFormatter(String displayFormat){
        Object[] newspaperArgs = {"ID", "Tag", "Title", "ISBN", "Publisher", "Edition", "Link",
            "Status", "Received Date"};
        String newspaperHeader = String.format("%91s"+ LINESEPARATOR, "[NEWSPAPERS]");
        Formatter newspaperFormatter = buildDisplayFormatter(displayFormat, newspaperArgs, newspaperHeader, "%-170s");
        return newspaperFormatter;
    }
    public static Formatter buildDisplayFormatter(String displayFormat, Object[] displayArgs, String header,
                                                  String padding){
        String customDivider = formatADivider(padding);
        Formatter displayFormatter = new Formatter();
        displayFormatter.format(header);
        displayFormatter.format(customDivider);
        displayFormatter.format(displayFormat, displayArgs);
        displayFormatter.format(customDivider);
        return displayFormatter;

    }
}
