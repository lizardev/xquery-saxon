package com.github.lizardev.xquery.saxon.coverage.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

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
            org.apache.commons.io.FileUtils.write(file, content, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readLines(URI uri) {
        try {
            return org.apache.commons.io.FileUtils.readLines(new File(uri), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyResourceToFile(String sourceResource, File destinationFile) {
        InputStream sourceStream = FileUtils.class.getClassLoader().getResourceAsStream(sourceResource);
        checkNotNull(sourceStream, "Cannot find resource: " + sourceResource);
        try {
            copyInputStreamToFile(sourceStream, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
