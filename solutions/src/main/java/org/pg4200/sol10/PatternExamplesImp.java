package org.pg4200.sol10;

import org.pg4200.ex10.PatternExamples;

/**
 * Created by arcuri82 on 07-May-18.
 */
public class PatternExamplesImp implements PatternExamples {


    @Override
    public String dnaRegex() {
        return "[CGAT]+";
    }

    @Override
    public String telephoneNumberRegex() {
        return "((\\+|00)[0-9]{2})?[0-9]{8}";
    }

    @Override
    public String emailRegex() {
        return "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
    }

    @Override
    public String isItAJoke() {
        return "[iI][sS] +[tT][hH][iI][sS] +[aA][nN] +[oO][uU][tT] +[oO][fF] +[sS][eE][aA][sS][oO][nN] +[aA][pP][rR][iI][lL] +[fF][oO][oO][lL][sS] +[jJ][oO][kK][eE]\\?";
    }
}
