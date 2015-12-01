# DFA Handler

This is a class project for CSCI 3236, Fall 2015. The purpose of this program is to:

  - Parse text files that completely define a DFA in a pre-defined format (complete format and example below). This information should include
     - Alphabet
     - List of States
     - Start State
     - Accept States
     - Transitions
  - Test whether or not certain strings are valid for that DFA.

### Technical Notes
The parser for this program accepts text files containing the following format. File location should be specified as the first command line param for the program

```
 {character1, character2, ...}
 {state1, state2, ...}
 start_state
 {accept_state1, accept_state2, ...}
 (current_state, character_input)->new_state
 (current_state, character_input)->new_state
 ...
 ```
Note that there can be an unlimited number of transitions defined, as long as the transitions continue to define a DFA instead of an NFA.

Full Example:
```
{0,1,2}
{a,b,c,d,e,f}
a
{e,f}
(a,0)->b
(a,1)->a
(a,2)->c
(b,0)->c
(b,1)->a
(b,2)->b
(c,0)->c
(c,1)->d
(c,2)->e
(d,0)->d
(d,1)->e
(d,2)->f
(e,0)->e
(e,1)->e
(e,2)->f
(f,0)->f
(f,1)->f
(f,2)->e
```
Note that any attempt at loading in a transition that would turn the DFA into an NFA will be detected and ignored. So if state-1 should move to state-2 on character input 0, and another transition gets read that says state-1 should move to state-3 on character input 0, then this latter transition will be completely ignored.

---
Project by: Charles Arvey, Hunter Smith, Garret Edwards, Andrew Perry