package org.pg4200.sol08;

import org.pg4200.ex08.ComputationExample;
import org.pg4200.ex08.ComputationExampleTestTemplate;

public class ComputationExampleStreamTest extends ComputationExampleTestTemplate {

    @Override
    protected ComputationExample getNewInstance() {
        return new ComputationExampleStream();
    }
}