import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Car extends Insurable {   //Implies Car is a type of insurable object
    //Initialize attribute variables for car class
    private String make;
    private String model;
    private Date purchaseDate;
    private long mileage;

    //Identifies the final input tag for object to be "CAR"
    static final String inputTag = "CAR";

    //Gives values to the attributes from the hashmap by obtaining the tag object using the string index
    //The value of the tag object is then given to the attributes using the getter function
    Car(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        super(tags);    //Inherits all existing attribute initialization from parent class
        make = tags.get("MAKE").get(0).getValue();
        model = tags.get("MODEL").get(0).getValue();
        //Use method to convert to correct date format and ensure long type
        purchaseDate = Utils.convertDate(tags.get("PURCHASE_DATE").get(0).getValue());
        mileage = Long.parseLong(tags.get("MILEAGE").get(0).getValue());
    }

    //Getter functions for the various attributes
    public String getOwnerName() {
        return ownerName;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public long getMileage() {
        return mileage;
    }

    public static String getInputTag() {
        return inputTag;
    }
}

