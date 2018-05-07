package org.pg4200.sol09;

import org.pg4200.ex09.PatternExamples;
import org.pg4200.ex09.PatternExamplesTestTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-May-18.
 */
public class PatternExamplesImpTest extends PatternExamplesTestTemplate {

    @Override
    protected PatternExamples getNewInstance() {
        return new PatternExamplesImp();
    }
}