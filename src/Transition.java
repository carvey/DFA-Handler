public class Transition
{
    Character transition_char;
    State transitioning_state;

    Transition()
    {

    }

    Transition(Character character, State transitioning_state)
    {
        this.transition_char = character;
        this.transitioning_state = transitioning_state;
    }

}