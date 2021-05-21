import java.util.ArrayList;

class Database {
    //Create various arraylists to store groups of objects
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();

    //Various methods to add objects to the arraylists
    void insertHome(Home home) {
        homes.add(home);
    }

    void insertCar(Car car) {
        cars.add(car);
    }

    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    //Various getter functions that return the object type given the string name
    //Implemented through a for loop through the arraylist and comparing the name for each with the one given
    //Returns the object associated with the name on a match, null otherwise
    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /**
     * There is at most one home owned by each person.
     */
    Home getHome(String ownnerName) {
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownnerName))
                return home;
        }
        return null;
    }

    /**
     * There is at most one car owned by each person.
     */
    Car getCar(String ownnerName) {
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownnerName))
                return car;
        }
        return null;
    }

    //Calculates the total claim amount for a customer given customer name
    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        //Loops through all the claims and checks whether the customer name matches for each claim
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();  //Adds the claim value for all claims with matching names-
        }
        return totalClaimed;
    }


    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        //Loops through the claims to obtain the contract object for the claim
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            //Checks whether the contract is for the given customer and the claim was successful
            if (contract.getCustomerName().equals(customerName)) {
                if (claim.wasSuccessful()) {
                    //obtains deductible for the plan of the contract
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    //Uses deductible to calculate received amount by subtracting it from the claim amount
                    //Adds to difference to total received, adds 0 if the difference is less than 0
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }

    long totalCustomersUnderPlan(String planName) {
        long numCustomers = 0;
        //Loops through all the existing contracts and adds 1 to
        //the counter for every plan found with the same name, indicating a customer for each plan found
        for (Contract contract : contracts) {
            if (planName.equals(contract.getPlanName())){
                numCustomers ++;
            }
        }
        return numCustomers;
    }

    //Behaves very similar to totalReceivedAmountByCustomer, but uses plan name instead of customer name
    long totalPayedUnderPlan(String planName) {
        long totalPayed = 0;
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            //Checks for each contract of every claim if the plan name matches and the claim was successful
            if (contract.getPlanName().equals(planName) && claim.wasSuccessful()) {
                    //Obtains deductible from the planName and uses it for the same calculation as before
                    long deductible = getPlan(planName).getDeductible();
                    totalPayed += Math.max(0, claim.getAmount() - deductible);
            }
        }
        return totalPayed;
    }

}
