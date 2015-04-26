package com.github.lizardev.xquery.saxon.coverage;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static com.github.lizardev.xquery.saxon.coverage.TestConstants.FUNCTX_MAIN_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.TestConstants.FUNCTX_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.TestConstants.TOUR_MODULE;
import static com.github.lizardev.xquery.saxon.coverage.report.FileReportPrinter.DEFAULT_REPORT_DIR;
import static com.github.lizardev.xquery.saxon.coverage.report.FileReportPrinter.REPORT_INDEX;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportGenerationTest extends AbstractCoverageTest {

    private WebDriver driver = new HtmlUnitDriver();

    @Test
    public void shouldProduceHtmlReport() {
        runXQueryModules();

        goToReportPage();

        verifyPageStructure();
    }

    private void runXQueryModules() {
        xqueryExecutor.execute(TOUR_MODULE);
        xqueryExecutor.execute(FUNCTX_MAIN_MODULE);

        coverageService.generateReport();
    }

    private void goToReportPage() {
        String userDir = System.getProperties().getProperty("user.dir");
        String reportDir = new SystemProperties().getCoverageReportDirectory().or(DEFAULT_REPORT_DIR);
        driver.get("file:///" + userDir + "/" + reportDir + "/" + REPORT_INDEX);
    }

    private void verifyPageStructure() {
        assertThat(driver.findElement(By.id("moduleColumn")).getText()).isEqualTo("Module");
        assertThat(driver.findElement(By.id("coverageColumn")).getText()).isEqualTo("Line coverage");
        driver.findElement(By.partialLinkText(TOUR_MODULE.toString()));
        driver.findElement(By.partialLinkText(FUNCTX_MAIN_MODULE.toString()));
        driver.findElement(By.partialLinkText(FUNCTX_MODULE.toString()));
    }
}
