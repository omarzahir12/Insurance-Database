import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Contract {
    private String contractName;
    private String customerName;
    private String planName;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;

    static final String inputTag = "CONTRACT";

    //Gives values to the attributes from the hashmap by obtaining the tag object using the string index
    //The value of the tag object is then given to the attributes using the getter function
    Contract(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").get(0).getValue();
        customerName = tags.get("CUSTOMER_NAME").get(0).getValue();
        planName = tags.get("PLAN_NAME").get(0).getValue();
        startDate = Utils.convertDate(tags.get("START_DATE").get(0).getValue());
        endDate = Utils.convertDate(tags.get("END_DATE").get(0).getValue());
        discountPercentage = Integer.parseInt(tags.get("DISCOUNT_PERCENTAGE").get(0).getValue());
    }

    //Getter functions
    public String getCustomerName() {
        return customerName;
    }

    public String getPlanName() {
        return planName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public static String getInputTag() {
        return inputTag;
    }

    public String getContractName() {
        return contractName;
    }
}
