package org.pg4200.sol07;

import org.pg4200.ex07.ComputationExample;
import org.pg4200.ex07.ComputationExampleTestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class ComputationExampleStreamTest extends ComputationExampleTestTemplate {

    @Override
    protected ComputationExample getNewInstance() {
        return new ComputationExampleStream();
    }
}