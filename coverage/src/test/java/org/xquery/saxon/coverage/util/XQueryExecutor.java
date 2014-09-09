package org.xquery.saxon.coverage.util;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.s9api.*;
import org.xquery.saxon.adapter.trace.TraceExtension;

import static net.sf.saxon.expr.parser.Optimizer.NO_OPTIMIZATION;
import static net.sf.saxon.lib.FeatureKeys.OPTIMIZATION_LEVEL;

public class XQueryExecutor {

    private final XQueryCompiler xQueryCompiler;
    private final TraceExtension traceExtension;

    public XQueryExecutor(TraceExtension traceExtension) {
        this.traceExtension = traceExtension;
        Configuration configuration = Configuration.newConfiguration();
        if (!traceExtension.allowsOptimization()) {
            configuration.setConfigurationProperty(OPTIMIZATION_LEVEL, String.valueOf(NO_OPTIMIZATION));
        }
        Processor processor = new Processor(configuration);
        xQueryCompiler = processor.newXQueryCompiler();
        StaticQueryContext staticQueryContext = xQueryCompiler.getUnderlyingStaticContext();
        staticQueryContext.setCodeInjector(traceExtension.getTraceCodeInjector());
    }

    public XdmValue execute(String mainModuleResource) {
        try {
            XQueryExecutable executable = xQueryCompiler.compile(Utils.resourceAsFile(mainModuleResource));
            XQueryEvaluator evaluator = executable.load();
            evaluator.setTraceListener(traceExtension.getTraceListener());
            return evaluator.evaluate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
