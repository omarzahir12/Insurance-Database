import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class HomePlan extends Plan {
    static final String inputTag = "HOME_PLAN";
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    HomePlan(HashMap<String, ArrayList<Tag>> tags) {
        super(tags);    //Inherits all initialization attributes from Plan

        if (tags.get("HOME.VALUE") != null) {
            //If tags with HOME.VALUE are found, add those cost values to the criteria for the specific plan
            //This will create a maximum or minimum home value amount for the plan
            for (Tag tag : tags.get("HOME.VALUE")) {
                homeValueCriterion.addCriterion(tag);
            }
        }
        if (tags.get("HOME.AGE") != null) {
            //If tags with HOME.AGE are found, add those age values to the criteria for the specific plan
            //This will create a maximum or minimum age limit for a house in the plan
            for (Tag tag : tags.get("HOME.AGE")) {
                homeAgeCriterion.addCriterion(tag);
            }
        }
    }

    @Override   //Overrides the parent class method
    boolean isEligible(Insurable insurable, Date date) {
        //Checks to make sure the object trying to be insured (the insurable) is a Home object
        if (!(insurable instanceof Home))
            //return false if insurable object is not a Home object
            return false;
        Home home = (Home) insurable; //Cast the insurable object as a Home object so useful methods can be applied
        //Makes sure the value of the home is in the required range for insurance eligibility
        //Returns false if not
        if (!homeValueCriterion.isInRange(home.getValue()))
            return false;

        // Extracting the approximate age of the home (calendar years)
        LocalDate localCurrentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate = home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();;
        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    @Override
    //Returns the home object associated for the given customer
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getHome(customer.getName());
    }

}
