package com.github.lizardev.xquery.saxon.coverage;

import java.net.URI;
import java.util.Objects;

import static com.github.lizardev.xquery.saxon.coverage.util.UriUtils.urlToUri;

public class ModuleUri {
    private final URI uri;

    public ModuleUri(URI uri) {
        this.uri = uri;
    }

    public static ModuleUri fromResourceName(String resourceName) {
        return new ModuleUri(urlToUri(ModuleUri.class.getResource(resourceName)));
    }

    public static ModuleUri fromUri(URI uri) {
        return new ModuleUri(uri);
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ModuleUri other = (ModuleUri) obj;
        return Objects.equals(this.uri, other.uri);
    }

    @Override
    public String toString() {
        return uri.toString();
    }
}
