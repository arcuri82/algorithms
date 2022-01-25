package org.pg4200.ex11;

public class GradeCompressionImplTest extends GradeCompressorTestTemplate{
    @Override
    protected GradeCompressor getNewInstance() {
        return new GradeCompressionImpl();
    }
}
