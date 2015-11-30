import java.util.ArrayList;

/**
 * Wrapper class that handles top level verification of any given strings.
 */
class Machine
{
    ArrayList<State> states;
    ArrayList<Character> alphabet;

    State start_state;
    State current_state;

    /**
     * This constructor acts like the formal math definition of a DFA, except the transitions are built into each state
     * @param alphabet the alphabet the machine accepts
     * @param states the states of the machine
     * @param start_state the starting state of the DFA
     * @param accept_states the accept states of the DFA
     */
    Machine(ArrayList<Character> alphabet, ArrayList<State> states, State start_state, ArrayList<State> accept_states)
    {
        this.alphabet = alphabet;
        this.states = states;
        this.start_state = start_state;
        this.current_state = start_state;

        for (State state : accept_states)
        {
            state.accept = true;
        }
    }

    /**
     * Empty constructor to allow the DFAParser class to build an instance as it extracts info from the given text file
     */
    Machine()
    {

    }

    /***
     * Evaluates a string to see if it is accepted by the DFA
     * @param string the string to validate
     * @return a boolean telling whether the string is a valid member of the DFA defined by the machine
     */
    public boolean validate(String string)
    {
        for (Character character : string.toCharArray())
        {
            //
            if (this.current_state == null)
                return false;

            //
            if (this.validate_string_in_alphabet(character))
            {
                current_state = current_state.validate(character);

            }
            else return false;

        }

        //Have to store the ending state before the current state gets set back to the start state
        State ending_state = this.current_state;

        //Set the current state back to the start state for the validation to run again
        this.current_state = start_state;

        /*
        If the the code has gotten this far, then the last barrier is to check if the current state is one that counts
        as an accept state. If it is, then the string is accepted. Otherwise, the string is not accepted
         */
        return ending_state.accept;
    }

    /**
     * Formats and prints out whether or not the string is accepted
     * @param string
     */
    public void print_validation(String string)
    {
        String base_string = String.format("The string %s is", string);
        boolean validation = this.validate(string);
        if (!validation)
            base_string += " not";
        System.out.println(base_string += " valid.");
    }

    /**
     * Validates that the character being evaluated is in the DFA's alphabet
     * @param character the character to evaluate
     * @return whether or not the character is in the alphabet
     */
    private boolean validate_string_in_alphabet(Character character)
    {
        for (Character alphabet_character : this.alphabet)
        {
            if (character.equals(alphabet_character))
                return true;
        }

        return false;
    }
}