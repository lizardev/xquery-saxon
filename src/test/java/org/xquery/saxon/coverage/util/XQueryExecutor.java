package org.xquery.saxon.coverage.util;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.s9api.*;
import org.xquery.saxon.coverage.trace.CoverageInstructionInjector;
import org.xquery.saxon.coverage.trace.CoverageInstructionListener;

import static net.sf.saxon.expr.parser.Optimizer.NO_OPTIMIZATION;
import static net.sf.saxon.lib.FeatureKeys.OPTIMIZATION_LEVEL;

public class XQueryExecutor {

    private final XQueryCompiler xQueryCompiler;
    private final CoverageInstructionListener coverageInstructionListener;

    public XQueryExecutor(CoverageInstructionInjector coverageInstructionInjector,
            CoverageInstructionListener coverageInstructionListener) {
        this.coverageInstructionListener = coverageInstructionListener;
        Configuration configuration = Configuration.newConfiguration();
        configuration.setConfigurationProperty(OPTIMIZATION_LEVEL, String.valueOf(NO_OPTIMIZATION));
        Processor processor = new Processor(configuration);
        xQueryCompiler = processor.newXQueryCompiler();
        StaticQueryContext staticQueryContext = xQueryCompiler.getUnderlyingStaticContext();
        staticQueryContext.setCodeInjector(coverageInstructionInjector);
    }

    public XdmValue execute(String mainModuleResource) {
        try {
            XQueryExecutable executable = xQueryCompiler.compile(Utils.resourceAsFile(mainModuleResource));
            XQueryEvaluator evaluator = executable.load();
            evaluator.setTraceListener(coverageInstructionListener);
            return evaluator.evaluate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
