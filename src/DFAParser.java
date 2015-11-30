import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by charlesarvey on 11/19/15.
 */
public class DFAParser {

    String[] content;
    Machine machine;

    /**
     * Constructor takes in a file location and turns the contents into a string. It also initializes a new machine.
     * @param file_input
     * @throws IOException
     */
    DFAParser(String file_input) throws IOException
    {
        this.content = new String(Files.readAllBytes(Paths.get(file_input))).split("\\r?\\n");
        this.machine = new Machine();
    }

    /**
     * Parses the given text file and builds a Machine instance as the file gets parsed
     * @return the Machine instance that has been built
     */
    public Machine parse_dfa()
    {

        for (int i = 0; i < content.length; i++)
        {
            if (i == 0)
                extract_alphabet(content[i]);
            if (i == 1)
                extract_states(content[i]);
            if (i == 2)
                extract_start(content[i]);
            if (i == 3)
                extract_accept(content[i]);
            if (i > 3)
                extract_transition(content[i]);
        }

        return this.machine;
    }

    /**
     * Strips any uneccessary character off of the line being evaluated
     * @param token the string to be stripped
     * @return the stripped string
     */
    private String strip(String token)
    {
        token = token.replaceAll("[,{}()]", "");
        token = token.replace("-", "");
        token = token.replace(">", "");
        return token;
    }

    /**
     * The logic to extract the alphabet from the file
     * @param token the alphabet string
     */
    private void extract_alphabet(String token)
    {
        token = this.strip(token);
        ArrayList<Character> alphabet = new ArrayList<>();

        for (Character character : token.toCharArray())
        {
            alphabet.add(character);
        }

        machine.alphabet = alphabet;
    }

    /**
     * The logic to extract the states from the file
     * @param token the alphabet string
     */
    private void extract_states(String token)
    {
        token = this.strip(token);
        ArrayList<State> states = new ArrayList<>();

        for (Character character : token.toCharArray())
        {
            State state = new State(character);
            states.add(state);
        }

        machine.states = states;
    }

    /**
     * The logic to handle setting the start state from the file
     * @param token the start state character as a string
     */
    private void extract_start(String token)
    {
        Character token_char = this.strip(token).toCharArray()[0];
        machine.start_state = State.getInstance(this.machine, token_char);
        machine.current_state = machine.start_state;
    }

    /**
     * The logic to handle setting the accept states from the file
     * @param token the accept states string
     */
    private void extract_accept(String token)
    {
        token = this.strip(token);

        for (Character accept_state : token.toCharArray())
        {
            State.getInstance(this.machine, accept_state).accept = true;
        }
    }

    /**
     * The logic to handle parsing and setting the transitions from the file
     * @param token the transition string
     */
    private void extract_transition(String token)
    {

        token = this.strip(token);
        char[] token_array = token.toCharArray();

        Transition transition = new Transition();
        State state = State.getInstance(this.machine, token_array[0]);
        transition.transition_char = token_array[1];
        transition.transitioning_state = State.getInstance(this.machine, token_array[2]);

        state.add_transition(transition);
    }

}
