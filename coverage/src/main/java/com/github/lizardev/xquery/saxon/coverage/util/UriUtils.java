package com.github.lizardev.xquery.saxon.coverage.util;

import com.google.common.hash.Hashing;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

public final class UriUtils {
    private final static int MAX_FILENAME_LENGTH = 250;

    private UriUtils() {
    }

    public static URI urlToUri(URL url) {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String uriToFilename(URI uri) {
        String filename = uri.getPath().replace("/", "_").replace(":", "");
        if (filename.length() > MAX_FILENAME_LENGTH) {
            String prefix = Hashing.sha256().newHasher().
                putString(filename, Charset.defaultCharset()).hash().toString();
            int suffixIndex = filename.length() - MAX_FILENAME_LENGTH + prefix.length() + 1;
            filename = prefix + "-" + filename.substring(suffixIndex);
        }
        return filename;
    }
}
