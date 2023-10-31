package seedu.data;

import java.util.ArrayList;

public class ResourceList {
    protected ArrayList<Resource> resources;

    public ResourceList() {
        this.resources = new ArrayList<>();
    }

    public ResourceList(ArrayList<Resource> arraylist) {
        this.resources = arraylist;
    }

    public ArrayList<Resource> getResourceList(){
        return this.resources;
    }

    public void printList(){
        if (!resources.isEmpty()){
            for (int i = 0; i < resources.size(); i++) {
                Resource resource = resources.get(i);
                System.out.println(i+1 + "." + resource.toString());
            }
        }else{
            System.out.println("No resources in inventory.");
        }
    }
}
