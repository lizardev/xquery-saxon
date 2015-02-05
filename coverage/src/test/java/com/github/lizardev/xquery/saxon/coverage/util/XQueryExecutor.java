package com.github.lizardev.xquery.saxon.coverage.util;

import net.sf.saxon.Configuration;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmValue;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtension;
import com.github.lizardev.xquery.saxon.coverage.ModuleUri;

import java.io.File;

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
        configuration.setModuleURIResolver(new ClasspathModuleUriResolver());
        Processor processor = new Processor(configuration);
        xQueryCompiler = processor.newXQueryCompiler();
        StaticQueryContext staticQueryContext = xQueryCompiler.getUnderlyingStaticContext();
        staticQueryContext.setCodeInjector(traceExtension.getTraceCodeInjector().get());
    }

    public <T> T execute(String query) {
        return execute(query, new ExecutionContext());
    }

    public <T> T execute(String query, ExecutionContext context) {
        try {
            return execute(xQueryCompiler.compile(query), context);
        } catch (SaxonApiException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T execute(ModuleUri query) {
        return execute(query, new ExecutionContext());
    }

    public <T> T execute(ModuleUri query, ExecutionContext context) {
        try {
            return execute(xQueryCompiler.compile(new File(query.getUri())), context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T execute(XQueryExecutable executable, ExecutionContext context) {
        try {
            XQueryEvaluator evaluator = executable.load();
            for (ExternalVariable externalVariable : context.getExternalVariables()) {
                evaluator.setExternalVariable(externalVariable.getName(), new XdmAtomicValue(externalVariable.getValue()));
            }
            evaluator.setTraceListener(traceExtension.getTraceListener().get());
            XdmValue result = evaluator.evaluate();
            return xdmValueConverter.convert(result);
        } catch (SaxonApiException e) {
            throw new RuntimeException(e);
        }
    }
}
