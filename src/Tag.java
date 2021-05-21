class Tag {
    //Creates predefined constants for variables to me associated with
    public enum Relation {
        SMALLER, LARGER, EQUAL
    }
    private Relation relation;
    private String name;
    private String value;

    Tag(String[] tokens) {
        //Assigns name to be the first element of the tokens array
        name = tokens[0];
        //Assigns a type of relation for each type of symbol so it can be handled appropriately by other methods
        switch (tokens[1].charAt(0)) {
            case '<':
                relation = Relation.SMALLER;
                break;
            case '>':
                relation = Relation.LARGER;
                break;
            case '=':
                relation = Relation.EQUAL;
                break;
            default:    //If case not one of the above then throw bad command exception
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
        }
        value = tokens[2];  //Assigns value to be the third element of the tokens array
    }

    //Getter functions
    public Relation getRelation() {
        return relation;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}