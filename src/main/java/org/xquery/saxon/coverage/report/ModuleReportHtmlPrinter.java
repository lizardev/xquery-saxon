package org.xquery.saxon.coverage.report;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ModuleReportHtmlPrinter {

    public String print(ModuleReport moduleReport) {
        List<String> lines = readLines(moduleReport);
        StringBuilder report = new StringBuilder();
        for (int i = 1; i <= lines.size(); i++) {
            LineReport lineReport = moduleReport.getLineReport(i);
            //report.append(String.format("<tr class='%s'><td>%d</td><td><pre>%s</pre></td></tr>%n",
            report.append(String.format("<tr class='%s'><td>%d</td><td><pre>%s</pre></td></tr>%n",
                    getCssClass(lineReport), i, StringEscapeUtils.escapeHtml4(lines.get(i - 1))));
        }
        report.insert(0, "<html>\n" +
                "<head>\n" +
                "    <style type=\"text/css\">\n" +
                "        .covered {\n" +
                "            background: lightgreen;\n" +
                "            font-family: monospace;\n" +
                "        }\n" +
                "\n" +
                "        .not-covered {\n" +
                "            background: lightpink;\n" +
                "            font-family: monospace;\n" +
                "        }\n" +
                "\n" +
                "        .not-instrumented {\n" +
                "            font-family: monospace;\n" +
                "        }\n" +
                "        pre {\n" +
                "            margin: 0;\n" +
                "        }" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>");
        report.append("</table>\n" +
                "</body>\n" +
                "</html>");
        return report.toString();
    }

    private String getCssClass(LineReport lineReport) {
        if (lineReport == null) return "not-instrumented";
        return lineReport.isFullyCovered() ? "covered" : "not-covered";
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
