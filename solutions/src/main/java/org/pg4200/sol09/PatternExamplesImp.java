package org.pg4200.sol09;

import org.pg4200.ex09.PatternExamples;

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
}
