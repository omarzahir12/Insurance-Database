import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Home extends Insurable {  //Implies Home is a type of insurable object
    private String postalCode;
    private Date buildDate;

    static final String inputTag = "HOME";

    //Gives values to the attributes from the hashmap by obtaining the tag object using the string index
    //The value of the tag object is then given to the attributes using the getter function
    Home(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        super(tags);    //Inherits all existing attribute initialization from parent class
        postalCode = tags.get("POSTAL_CODE").get(0).getValue();
        //Use method to convert to correct date format
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").get(0).getValue());
    }

    //Getter functions for the various attributes
    public String getPostalCode() {
        return postalCode;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public static String getInputTag() {
        return inputTag;
    }
}
