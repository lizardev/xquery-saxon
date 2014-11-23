package org.xquery.saxon.coverage.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class FileUtils {

    public static void deleteDir(File dir) {
        try {
            org.apache.commons.io.FileUtils.deleteDirectory(dir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(File file, String content) {
        try {
            org.apache.commons.io.FileUtils.write(file, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readLines(URI uri) {
        try {
            return org.apache.commons.io.FileUtils.readLines(new File(uri));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
