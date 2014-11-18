package org.xquery.saxon.coverage.util;

import net.sf.saxon.lib.ModuleURIResolver;

import javax.xml.transform.stream.StreamSource;
import java.net.URL;

public class ClasspathModuleUriResolver implements ModuleURIResolver {
    @Override
    public StreamSource[] resolve(String moduleUri, String baseUri, String[] locations) {
        if (moduleUri != null) {
            try {
                URL url = getClass().getResource(moduleUri);
                return new StreamSource[]{new StreamSource(url.openStream(), url.toString())};
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("module uri cannot be null");
        }
    }
}
