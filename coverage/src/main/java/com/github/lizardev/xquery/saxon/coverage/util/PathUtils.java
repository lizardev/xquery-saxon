package com.github.lizardev.xquery.saxon.coverage.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {

    public static Path transformToRelativePath(Path path) {
        Path root = path.getRoot();
        if (root == null) {
            return path;
        } else {
            Path rootAsRelativeDir = Paths.get(root.toString()
                    .replace("/", "")
                    .replace("\\", "")
                    .replace(":", ""));
            Path withoutRoot = root.relativize(path);
            return rootAsRelativeDir.resolve(withoutRoot);
        }
    }
}
