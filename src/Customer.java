import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Customer {
    //Initialize variables for customer attributes
    private String name;
    private Date dateOfBirth;
    private long income;

    //Sets input tag for method to be "CUSTOMER"
    static final String inputTag = "CUSTOMER";

    //Associates attribute values for the customer using the hashmap values
    Customer(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        //The tag object are extracted from the hashmap using the string index, and their values are stored
        //using the tag value getter function
        name = tags.get("NAME").get(0).getValue();
        //Specialized methods are used to parse the date and long for D.O.B. and income to maintain formatting
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").get(0).getValue());
        income = Long.parseLong(tags.get("INCOME").get(0).getValue());
    }

    //Getter functions for the various customer attributes
    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public long getIncome() {
        return income;
    }

    public static String getInputTag() {
        return inputTag;
    }
}
