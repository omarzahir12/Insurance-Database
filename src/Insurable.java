import java.util.ArrayList;
import java.util.HashMap;

class  Insurable {
    //Ensures that the following attribute variables can only be accessed by current class and subclasses
    protected String ownerName;
    protected long value;

    //Gives values to the attributes from the hashmap by obtaining the tag object using the string index
    //The value of the obtained tag object is then assigned to the attributes using the getter function
    Insurable(HashMap<String, ArrayList<Tag>> tags) {
        ownerName = tags.get("OWNER_NAME").get(0).getValue();
        value = Long.parseLong(tags.get("VALUE").get(0).getValue());
    }

    //Getter functions for the two attributes
    public String getOwnerName() {
        return ownerName;
    }

    public long getValue() {
        return value;
    }
}
