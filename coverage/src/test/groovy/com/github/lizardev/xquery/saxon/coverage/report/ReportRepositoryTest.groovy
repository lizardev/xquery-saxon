package com.github.lizardev.xquery.saxon.coverage.report

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static com.github.lizardev.xquery.saxon.coverage.TestObjects.id
import static com.github.lizardev.xquery.saxon.coverage.TestObjects.someReport

class ReportRepositoryTest extends Specification {

    private static final REPORT_1 = someReport(), REPORT_2 = someReport()
    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder()
    private File repositoryDir

    void setup() {
        repositoryDir = temporaryFolder.newFolder()
    }

    def 'saves report'() {
        given:
        ReportRepository repository = new ReportRepository(repositoryDir)

        when:
        repository.save(REPORT_1)

        then:
        repositoryDir.listFiles().toList() == [new File(repositoryDir, "${REPORT_1.id}.report.bin")]
    }

    def 'reads all reports'() {
        given:
        ReportRepository repository = new ReportRepository(repositoryDir)
        repository.save(REPORT_1)
        repository.save(REPORT_2)

        expect:
        repository.readAll() == [REPORT_1, REPORT_2] as Set
    }

    def 'ignores no report files when reading'() {
        given:
        new File(repositoryDir, 'some-file.bin') << id()
        new File(repositoryDir, 'some-file.report') << id()
        def subdir = new File(repositoryDir, 'subdir')
        subdir.mkdirs()
        new File(subdir, 'some-report-in-subdir.report.bin') << id()
        ReportRepository repository = new ReportRepository(repositoryDir)
        repository.save(REPORT_1)

        expect:
        repository.readAll() == [REPORT_1] as Set
    }
}
