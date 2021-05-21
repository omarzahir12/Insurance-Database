import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

abstract class Plan {
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();

    Plan(HashMap<String, ArrayList<Tag>> tags) {
        //Gives values to the attributes from the hashmap by obtaining the tag object using the string index
        //The value of the obtained tag object is then assigned to the attributes using the getter function
        name = tags.get("NAME").get(0).getValue();
        //parseInt is used to ensure the assigned value is stored as an integer
        premium = Integer.parseInt(tags.get("PREMIUM").get(0).getValue());
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").get(0).getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").get(0).getValue());

        if (tags.get("CUSTOMER.AGE") != null) {
            //If tags with CUSTOMER.AGE are found, add those cost values to the criteria for the specific plan
            //This will create a maximum or minimum age value for the customer of the plan
            for (Tag tag: tags.get("CUSTOMER.AGE")) {
                customerAgeCriterion.addCriterion(tag);
            }
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            //If tags with CUSTOMER.INCOME are found, add those cost values to the criteria for the specific plan
            //This will create a maximum or minimum income value for the customer of the plan
            for (Tag tag: tags.get("CUSTOMER.INCOME")) {
                customerIncomeCriterion.addCriterion(tag);
            }
        }

    }

    //Declares methods in the parent class which will then be overridden by the subclasses
    //Forces subclass to contain these methods
    abstract boolean isEligible(Insurable insurable, Date date);

    abstract Insurable getInsuredItem(Customer customer, Database database);

    boolean isEligible(Customer customer, Date currentDate) {
        // Extracting the approximate age of the customer (just based on the calendar years)
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBirthDate.getYear();
        // Checking if the age is in the range.
        if (!customerAgeCriterion.isInRange(age))
            return false;
        // Checking if the income is in the range.
        return customerIncomeCriterion.isInRange(customer.getIncome());
    }

    //Getter functions for the various attributes
    String getName() {
        return name;
    }

    long getPremium() {
        return premium;
    }

    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    long getDeductible() {
        return deductible;
    }
}
