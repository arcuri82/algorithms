package org.pg4200.ex10;

public class PatternExamplesImp implements PatternExamples{
    @Override
    public String dnaRegex() {


        return "[C,G,A,T]+";
    }

    @Override
    public String telephoneNumberRegex() {
        return "((00|\\+)[0-9]{2})?[0-9]{8}";
    }

    @Override
    public String emailRegex() {
        /**
         * An email is composed of, in order:
         * - a non-empty word (letters from a to z, upper and lower case, plus digits)
         * - followed by zero or more non-empty words, each one separated by a "."
         * - symbol @
         * - a non-empty word
         * - followed by zero or more non-empty words, each one separated by a "."
         * - a final domain code, which is at least 2 letters (upper or lower case), and no digits
         */

        String mail = "([a-zA-Z0-9]+)(.[a-zA-Z0-9]+)*@([a-zA-Z0-9]+)(.[a-zA-Z0-9]+)*(.[a-zA-Z][a-zA-Z]+)";
        return mail;
    }

    @Override
    public String isItAJoke() {

        String joke = "(I|i)(S|s)( )+" +
                "(T|t)(H|h)[I|i][S|s]( )+" +
                "[A|a][N|n]( )+" +
                "[O|o][U|u][T|t]( )+" +
                "[O|o][F|f]( )+" +
                "[S|s][E|e][A|a][S|s][O|o][N|n]( )+" +
                "[A|a][P|p][R|r][I|i][L|l]( )+" +
                "[F|f][O|o]{2}[L|l][S|s](')?( )+" +
                "[J|j][O|o][K|k][E|e]\\?";
        return joke;

    }
}
