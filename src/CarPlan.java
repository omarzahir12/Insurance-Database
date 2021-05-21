import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class CarPlan extends Plan {
    static final String inputTag = "CAR_PLAN";
    RangeCriterion mileageCriterion = new RangeCriterion();

    CarPlan(HashMap<String, ArrayList<Tag>> tags) {
        super(tags);    //Inherits all initialization attributes from Plan

        if (tags.get("CAR.MILEAGE") != null) {
            //If tags with CAR.MILEAGE are found, add those mileage values to the criteria for the specific plan
            //This will create a maximum or minimum mileage limit for the car plan
            for (Tag tag : tags.get("CAR.MILEAGE")) {
                mileageCriterion.addCriterion(tag);
            }
        }
    }

    @Override   //Used to override the parent class method
    boolean isEligible(Insurable insurable, Date date) {
        //Checks to make sure the object trying to be insured (the insurable) is a Car object
        if (!(insurable instanceof Car))
            //return false if insurable object is not a Car object
            return false;
        Car car = (Car) insurable;  //Cast the insurable object as a Car object so useful methods can be applied
        //Makes sure the mileage of the car is in the required range for insurance eligibility
        //Return true if it is, and false if not
        return mileageCriterion.isInRange(car.getMileage());
    }


    @Override
    //Returns the car object associated for the given customer
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getCar(customer.getName());
    }
}
