import java.util.ArrayList;

/**
 * This class represents the states of a DFA
 */
public class State
{
    Character state_name;
    ArrayList<Transition> transitions;
    boolean accept;

    State(Character state_name)
    {
        this.state_name = state_name;
        transitions = new ArrayList<>();
    }

    /**
     * Validates that a given character has a valid transition out of the current state.
     * @param character the character that is getting validated.
     * @return the state that the valid transition links to.
     */
    public State validate(char character)
    {
        for (Transition transition : transitions)
        {
            if (transition.transition_char.equals(character))
            {
                return transition.transitioning_state;
            }
        }
        return null;
    }


    public void add_transition(Character character, State transitioning_state)
    {
        Transition transition = new Transition(character, transitioning_state);
        if (!this.check_nfa(transition))
            transitions.add(transition);
    }

    /**
     * Adds a transition to the current state. It first checks to make sure the transition won't make the DFA an NFA
     * @param transition the transition to be added
     */
    public void add_transition(Transition transition)
    {
        if (!this.check_nfa(transition))
            transitions.add(transition);
    }

    /**
     * This method will be ran before a transition gets added and will ensure that the transition being added does
     * not turn the DFA into an NFA
     */
    private boolean check_nfa(Transition new_transition)
    {
        for (Transition transition: this.transitions)
        {
            if (transition.transition_char.equals(new_transition.transition_char))
                return true;
        }
        return false;
    }

    /**
     * Gets a particular instance of the state class
     * @param machine the machine to look for the state in
     * @param character the character identifier (state_name) of the state being looked for
     * @return the state being looked for
     */
    public static State getInstance(Machine machine, Character character) {
        for (State state : machine.states)
        {
            if (state.state_name.equals(character))
                return state;
        }
        return null;
    }

}