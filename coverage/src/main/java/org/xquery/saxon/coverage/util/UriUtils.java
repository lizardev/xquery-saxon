package org.xquery.saxon.coverage.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class UriUtils {

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
        return uri.getPath().replace("/", "_").replace(":", "");
    }
}
