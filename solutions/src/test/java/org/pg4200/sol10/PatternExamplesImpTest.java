package org.pg4200.sol10;

import org.pg4200.ex10.PatternExamples;
import org.pg4200.ex10.PatternExamplesTestTemplate;

/**
 * Created by arcuri82 on 07-May-18.
 */
public class PatternExamplesImpTest extends PatternExamplesTestTemplate {

    @Override
    protected PatternExamples getNewInstance() {
        return new PatternExamplesImp();
    }
}