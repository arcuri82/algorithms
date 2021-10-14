package org.pg4200.sol12;

import org.pg4200.ex11.GradeCompressor;
import org.pg4200.ex11.GradeCompressorTestTemplate;

public class GradeCompressorTest extends GradeCompressorTestTemplate {

    @Override
    protected GradeCompressor getNewInstance() {
        return new GradeCompressorImp();
    }
}
