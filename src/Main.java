import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        try {
            //Using the input reader, an arraylist of commands is created and given the user commands
            //using the get command method
            InputReader inputReader = InputReader.getInstance();
            ArrayList<Command> commands = inputReader.getCommands();
            //The commands are iterated over using the iterator
            Iterator<Command> currentCommand = commands.iterator();

            CommandHandler commandHandler = new CommandHandler(new Database());

            //For all the commands in the arraylist, commandHandler runs each through command using the run method
            while (currentCommand.hasNext()) {
                commandHandler.run(currentCommand.next());
            }
        }
        //Parse and BadCommand exceptions are caught and messages are given appropriately
        catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (BadCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
