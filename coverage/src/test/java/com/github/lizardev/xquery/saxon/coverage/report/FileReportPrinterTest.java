package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import com.github.lizardev.xquery.saxon.coverage.ModuleUri;
import org.junit.Test;

import static com.github.lizardev.xquery.saxon.coverage.util.UriUtils.uriToFilename;
import static org.junit.Assert.assertEquals;

public abstract class FileReportPrinterTest {

	@Test
	public void testDefaultGenerateFilename() throws Exception {
		FileReportPrinter printer = new FileReportPrinter();
		
		URI uri = new URI("file://c/myfile.xq");
		ModuleUri moduleUri = new ModuleUri(uri);
		ModuleReport mr = new ModuleReport(moduleUri, new ArrayList<LineReport>());
		assertEquals(uriToFilename(uri), printer.generateFilename(mr));
	}

	
	@Test
	public void testDefaultReportDir() throws Exception {
		FileReportPrinter printer = new FileReportPrinter();
		
		assertEquals(new File(FileReportPrinter.DEFAULT_REPORT_DIR), printer.getReportDir());
	}
	
}
