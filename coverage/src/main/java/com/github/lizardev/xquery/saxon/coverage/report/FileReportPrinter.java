package com.github.lizardev.xquery.saxon.coverage.report;

import com.github.lizardev.xquery.saxon.coverage.SystemProperties;

import java.io.File;
import java.util.List;

import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.deleteDir;
import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.write;
import static com.github.lizardev.xquery.saxon.coverage.util.UriUtils.uriToFilename;
import static com.google.common.collect.Lists.newArrayList;

public class FileReportPrinter implements ReportPrinter {

    public static final String REPORT_INDEX = "index.html";
    public static final String DEFAULT_REPORT_DIR = "target/xquery-saxon-coverage";

    private File baseDir = new File(new SystemProperties().getCoverageReportDirectory().or(DEFAULT_REPORT_DIR));
    private HtmlModuleReportGenerator moduleReportGenerator = new HtmlModuleReportGenerator();
    private HtmlModuleReportIndexGenerator moduleReportIndexGenerator = new HtmlModuleReportIndexGenerator();
    private StaticResourceTransferor resourceTransferor = new StaticResourceTransferor();
	
    @Override public void print(Report report) {
        deleteDir(baseDir);
        List<ModuleReportReference> moduleReportReferences = newArrayList();
        for (ModuleReport moduleReport : report.getModuleReports()) {
            File file = getHtmlModuleReportFile(moduleReport);
            moduleReportReferences.add(new ModuleReportReference(
                    moduleReport.getModuleUri().toString(), file.getName(), moduleReport.getLineCoverage()));
            write(file, moduleReportGenerator.generate(moduleReport));
        }
        write(new File(baseDir, REPORT_INDEX), moduleReportIndexGenerator.generate(moduleReportReferences));
        resourceTransferor.copyResources(baseDir);
    }

	/**
	  * Returns the directory into which the report will be written.
	  */
	public File getReportDir() {
		return baseDir;
	}
	
	/**
	  * The coverage report consists of many files that are stored in a directory.
	  * This method can be called to override the default report directory.
	  *
	  * <b>Be aware that the directory will be deleted prior to report generation!</b>
	  *
	  * @see DEFAULT_REPORT_DIR
	  */
	public void setReportDir(File reportDir) {
		baseDir = reportDir;
	}
	
    private File getHtmlModuleReportFile(ModuleReport moduleReport) {
        return new File(baseDir, generateFilename(moduleReport));
    }
	
	/**
	  * This is a template method for generating a report file name for a given 
	  * XQuery module.
	  * The default implementation calls uriToFilename and appends an .html suffix
	  *
	  * @param moduleReport the description of the XQuery module
	  * @return non-null, valid file name ending in .html or .htm
	  * @see com.github.lizardev.xquery.saxon.coverage.util.UriUtils#uriToFilename
	  */
	protected String generateFilename(ModuleReport moduleReport) {
		return uriToFilename(moduleReport.getModuleUri().getUri()) + ".html";
	}
}