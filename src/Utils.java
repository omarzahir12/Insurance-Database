import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

class Utils {

    //Creates a date format
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    //Converts a string with date into the above format by parsing, throwing a parseException if unsuccessful
    static Date convertDate(String input) throws ParseException {
        return formatter.parse(input);
    }

    //Formats a date object into a string object with the given date pattern mentioned below
    static String formattedDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
