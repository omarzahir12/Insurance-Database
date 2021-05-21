import java.util.ArrayList;
import java.util.Scanner;

class InputReader {
    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;
    //method used to initialize keyboard
    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    //Return the inputReader through instance and create new instance if initially set to null
    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    ArrayList<Command> getCommands() {
        //Create arraylist to store commands
        ArrayList<Command> commands = new ArrayList<>();
        //Initialize variables to store line as string and and act as a line counter
        String line = "";
        lineNumber = 0;

        try {
            while (keyboard.hasNext()) {
                lineNumber++;   //Increment line counter
                line = keyboard.nextLine();
                //Determine the command type of the input adding each to the arraylist and
                //sending to appropriate method to handle each
                if (line.startsWith("PRINT ")) {
                    commands.add(makePrintCommand(line));
                } else if (line.startsWith("BEGIN_")) {     //Indicates the beginning of a block of commands
                    commands.add(makeBlockCommand(line));
                } else if (line.equals("FINISH")) {  //break the loop when "FINISH", indicating end of input
                    break;
                } //If line is not empty string or above scenarios then invalid command, throw exception
                else if (!line.equals("")) {
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");
                }
            }
        } catch (BadCommandException e) {   //catch a thrown exception and output message
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        return commands;
    }

    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));
        //For every line in the block
        while (keyboard.hasNext()) {
            //Add one to the counter
            lineNumber ++;
            line = keyboard.nextLine();
            //Indicates the end of the command block, return the command
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {   //Ignore empty lines
            }
            else {  //Every block command should be able to split into exactly three tokens if valid
                String [] tokens = line.split(" ", 3);
                //If there are not three tokens or the second token is not one character long,
                //as it should be a '=' character, throw an exception
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                //If no issues were found add the generated tokens to the command
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    private Command makePrintCommand(String line) {
        //Every print command should split into 4 tokens, including the keyword 'PRINT' itself
        String[] tokens = line.split(" ", 5);
        //If the tokens are more or less then 4 then throw exception with appropriate message
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        } else if (tokens.length < 4) {
                throw new BadCommandException("Invalid print command; too few tokens.");
        }
        //If 4 tokens then return the PrintCommand with tokens
        return new PrintCommand(tokens);
    }
}