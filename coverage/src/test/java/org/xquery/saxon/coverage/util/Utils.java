package org.xquery.saxon.coverage.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Utils {

    public static File resourceAsFile(String resource) {
        try {
            return new File(Utils.class.getResource(resource).toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String fileNameFromUri(String uri) {
        try {
            return new File(new URI(uri)).getName();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeStringToFile(File file, String data) {
        try {
            FileUtils.write(file, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

