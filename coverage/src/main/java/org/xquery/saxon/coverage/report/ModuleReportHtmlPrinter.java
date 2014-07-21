package org.xquery.saxon.coverage.report;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.xquery.saxon.coverage.report.Freemarker.renderTemplate;

public class ModuleReportHtmlPrinter {

	public String print(ModuleReport moduleReport) {
		List<String> lines = readLines(moduleReport);
		List<LineReport> lineReports = new ArrayList<LineReport>();

		for (int i = 1; i <= lines.size(); i++) {
			LineReport lineReport = moduleReport.getLineReport(i);
			lineReports.add(lineReport);
		}

		Map<String, ?> params = ImmutableMap.of("lineReports", lineReports, "lines", lines);
		return renderTemplate("/org/xquery/saxon/coverage/report/ModuleReport.ftl", params);
	}

	private List<String> readLines(ModuleReport moduleReport) {
		try {
			return FileUtils.readLines(getFile(moduleReport.getModule()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private File getFile(String module) {
		try {
			return new File(new URL(module).getFile());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
