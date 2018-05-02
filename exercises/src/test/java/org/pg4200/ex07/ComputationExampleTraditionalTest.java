package org.pg4200.ex07;

import static org.junit.jupiter.api.Assertions.*;

public class ComputationExampleTraditionalTest extends ComputationExampleTestTemplate{

    @Override
    protected ComputationExample getNewInstance() {
        return new ComputationExampleTraditional();
    }
}