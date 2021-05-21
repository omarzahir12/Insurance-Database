import java.text.ParseException;

class PrintCommand extends Command {    //Inherits attributes and methods from Command class
    private String entityType;
    private String queryType;
    private String queryValue;

    PrintCommand(String[] tokens) {
        super();    //Inherit methods, properties of the Command class

        //Stores key values in separate variables from the command tokens array
        entityType = tokens[1];
        queryType = tokens[2];
        queryValue = tokens[3];
    }

    @Override   //Indicates that the methods will be overriding the methods from the parent class
    void run(Database database) {
        //Determines the entity type, customer or plan, and handles each case with the appropriate call
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        //Throws error on incorrect entity type
        else {
            throw new BadCommandException("Bad print command.");
        }
    }

    private void runPrintCustomer(Database database) {
        //Determines type of customer query and handles each case appropriately
        //For either query, the output structure is formed, with methods to obtain the name and answer
        //using the queryValue
        if (queryType.equals("TOTAL_CLAIMED")) {
            System.out.println("Total amount claimed by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        } else if (queryType.equals("TOTAL_RECEIVED")) {
            System.out.println("Total amount received by " + database.getCustomer(queryValue).getName() +
                            " is " + database.totalReceivedAmountByCustomer(queryValue));
        } else {    //throws error on incorrect query type
            throw new BadCommandException("Invalid PRINT CUSTOMER command.");
        }
    }

    private void runPrintPlan(Database database) {
        //Determines the type of plan query
        //Output uses the custom made methods created in database.java to return
        //the number of customers and amount-payed under plan
        //the query value is just the customer name so we do not need to add the extra methods to get it
        if (queryType.equals("NUM_CUSTOMERS")) {
            System.out.println("Number of customers under " + queryValue +
                    " is " + database.totalCustomersUnderPlan(queryValue));
        } else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS")) {
            System.out.println("Total amount payed under " + queryValue +
                    " is " + database.totalPayedUnderPlan(queryValue));
        }
        else {  //Throws error on incorrect query type
            throw new BadCommandException("Invalid PRINT PLAN command.");
        }
    }
}
