package seedu.syslib;

import seedu.data.ResourceList;
import seedu.parser.Parser;
import seedu.ui.UI;

public class Syslib {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static UI ui;
    private static Parser parser;
    private static ResourceList resourcelist;

    public Syslib(){
        resourcelist = new ResourceList();
        ui = new UI();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Syslib().run();
    }

    public void run(){
        ui.showWelcomeMessage();

        while (true) {
            String response = ui.readCommand();
            parser.processInput(response, resourcelist);
            if (response.equalsIgnoreCase("exit")){
                break;
            }
        }
    }

}
