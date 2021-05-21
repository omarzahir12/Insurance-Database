import java.text.ParseException;

abstract class Command {
    //Creates method that will be overwritten by their subclasses
    //Forces subclass to contain these methods
    abstract void run(Database database) throws ParseException;
}
