package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.ResourceList;
import seedu.data.SysLibException;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand(){
        args = new String[]{"id"};
        required = new boolean[]{true};
    }
    @Override
    public void execute(String statement, ResourceList resourceList) throws SysLibException {
        int id = parseInt(parseArgument(statement)[0]);
        assert id > 0;
        ArrayList<Resource> removals = new ArrayList<>();
        System.out.println("Looking for ID: " + id + "...");
        for (Resource r: resourceList.getResourceList()){
            Book b = (Book) r;
            if (b.getId() == id){
                System.out.println("This resource is removed: ");
                System.out.println(b);
                System.out.println("____________________________________________________________");
                removals.add(r);
            }
        }
        if(removals.isEmpty()){
            System.out.println("No resources with id matching " + id + System.lineSeparator() +
                    "____________________________________________________________");
        } else {
            resourceList.getResourceList().removeAll(removals);
        }
    }

}
