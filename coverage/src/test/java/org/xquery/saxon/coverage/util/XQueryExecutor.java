package org.xquery.saxon.coverage.util;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.s9api.*;
import org.xquery.saxon.adapter.trace.TraceExtension;

import static net.sf.saxon.expr.parser.Optimizer.NO_OPTIMIZATION;
import static net.sf.saxon.lib.FeatureKeys.OPTIMIZATION_LEVEL;

public class XQueryExecutor {

    private final XQueryCompiler xQueryCompiler;
    private final XdmValueConverter xdmValueConverter = new XdmValueConverter();
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

    public <T> T execute(String mainModuleResource) {
        return execute(mainModuleResource, new ExecutionContext());
    }

    public <T> T execute(String mainModuleResource, ExecutionContext context) {
        try {
            XQueryExecutable executable = xQueryCompiler.compile(Utils.resourceAsFile(mainModuleResource));
            XQueryEvaluator evaluator = executable.load();
            for (ExternalVariable externalVariable : context.getExternalVariables()) {
                evaluator.setExternalVariable(externalVariable.getName(), new XdmAtomicValue(externalVariable.getValue()));
            }
            evaluator.setTraceListener(traceExtension.getTraceListener());
            XdmValue result = evaluator.evaluate();
            return xdmValueConverter.convert(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
