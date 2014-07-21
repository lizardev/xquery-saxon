package org.xquery.saxon.coverage.util;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.s9api.*;
import org.xquery.saxon.adapter.trace.TraceProvider;

import static net.sf.saxon.expr.parser.Optimizer.NO_OPTIMIZATION;
import static net.sf.saxon.lib.FeatureKeys.OPTIMIZATION_LEVEL;

public class XQueryExecutor {

    private final XQueryCompiler xQueryCompiler;
    private final TraceProvider traceProvider;

    public XQueryExecutor(TraceProvider traceProvider) {
        this.traceProvider = traceProvider;
        Configuration configuration = Configuration.newConfiguration();
        if (!traceProvider.supportsOptimization()) {
            configuration.setConfigurationProperty(OPTIMIZATION_LEVEL, String.valueOf(NO_OPTIMIZATION));
        }
        Processor processor = new Processor(configuration);
        xQueryCompiler = processor.newXQueryCompiler();
        StaticQueryContext staticQueryContext = xQueryCompiler.getUnderlyingStaticContext();
        staticQueryContext.setCodeInjector(traceProvider.getTraceCodeInjector());
    }

    public XdmValue execute(String mainModuleResource) {
        try {
            XQueryExecutable executable = xQueryCompiler.compile(Utils.resourceAsFile(mainModuleResource));
            XQueryEvaluator evaluator = executable.load();
            evaluator.setTraceListener(traceProvider.getTraceListener());
            return evaluator.evaluate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
