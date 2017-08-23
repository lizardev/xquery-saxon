package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class ReportRepository {
    private final File repositoryDir;

    public ReportRepository(File repositoryDir) {
        this.repositoryDir = repositoryDir;
        this.repositoryDir.mkdirs();
    }

    public void save(Report report) {
        try (FileOutputStream fos = new FileOutputStream(new File(repositoryDir, report.getId() + ".report.bin"))) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(report);
            }
        } catch (Exception e) {
            throw new RuntimeException("cannot save report because " + e.getMessage(), e);
        }
    }

    public Set<Report> readAll() {
        Set<Report> reports = new HashSet<>();
        File[] reportFiles = repositoryDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return repositoryDir.equals(dir) && name.endsWith(".report.bin");
            }
        });
        if (reportFiles != null) {
            for (File reportFile : reportFiles) {
                reports.add(readReport(reportFile));
            }
        }
        return reports;
    }

    private Report readReport(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (Report) ois.readObject();
            }
        } catch (Exception e) {
            throw new RuntimeException("cannot read report because " + e.getMessage(), e);
        }
    }
}
