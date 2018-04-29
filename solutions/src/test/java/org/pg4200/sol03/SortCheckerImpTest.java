package org.pg4200.sol03;

import org.junit.jupiter.api.Test;
import org.pg4200.ex03.SortChecker;
import org.pg4200.ex03.SortCheckerTestTemplate;

import java.util.Arrays;

public class SortCheckerImpTest extends SortCheckerTestTemplate {

    @Override
    protected SortChecker getNewInstance() {
        return new SortCheckerImp();
    }


}
