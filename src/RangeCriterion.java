class RangeCriterion {
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    //Creates a maximum and minimum range for the created criterion objects
    void addCriterion(Tag tag) {
        //Checks if relation for the tag is the LARGER or '>' relation which would mean that the tag is
        //specifying a minimum value for a criteria, the minValue is then set to be the value given in
        //the tag or the lowest possible value a long can be if given value is too small
        if (tag.getRelation() == Tag.Relation.LARGER) {
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        //Checks if relation for the tag is the SMALLER or '<' relation which would mean that the tag is
        //specifying a maximum value for a criteria, the maxValue is then set to be the value given in
        //the tag or the largest possible value a long can be if given value is too large
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
    }

    //Checks whether attributes meet the criteria by ensuring value is between minimum and maximum values
    //Returns true when it is, false otherwise
    boolean isInRange(long value) {
        if (value < maxValue && value > minValue)
            return true;
        return false;
    }
}
