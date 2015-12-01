import java.io.*;
import java.util.Scanner;

/**
 * DFA Group Project
 *
 * Charles Arvey
 * Andrew Perry
 * Garret Edwards
 * Hunter Smith
 *
 * The parser for this program accepts text files containing the following format. File location should be specified
 * as the first command line param for the program
 *
 * {character1, character2, ...}
 * {state1, state2, ...}
 * start_state
 * {accept_state1, accept_state2, ...}
 * (current_state, character_input)->new_state
 * (current_state, character_input)->new_state
 * ...
 *
 * Note that any attempt at loading in a transition that would turn the DFA into an NFA will be detected and ignored.
 * So if state1 should move to state2 on character input 0, and another transition gets read that says state1 should
 * move to state3 on character input 0, then this latter transition will be completely ignored.
 */

public class Main
{

    /**
     * Main method will set up the DFAParser and DFA machine. It also runs the loop to get and test input strings
     * @param args command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {

        if (args.length == 0) {
            throw new IOException("A file path must be specified.");
        }

        Scanner scanner = new Scanner(System.in);
        DFAParser parser = new DFAParser(args[0]);
        Machine machine = parser.parse_dfa();

        System.out.println("\nEnter Strings to Test the DFA. Enter an empty string exit.");
        String test_string;

        do
        {
            System.out.print("\nEnter a string to be tested: ");
            test_string = scanner.nextLine();
            if (!test_string.equals(""))
                machine.print_validation(test_string);
        } while (!test_string.equals(""));
    }



}
