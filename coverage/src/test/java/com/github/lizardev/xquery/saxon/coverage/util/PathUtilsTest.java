package com.github.lizardev.xquery.saxon.coverage.util;

import org.junit.Test;

import java.nio.file.Path;

import static com.github.lizardev.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;
import static com.github.lizardev.xquery.saxon.coverage.util.PathUtils.transformToRelativePath;
import static java.nio.file.Paths.get;

public class PathUtilsTest {

    @Test
    public void doesNotTransformAlreadyRelativePath() {
        Path relativePath = get("some/relative/path");

        assertThat(transformToRelativePath(relativePath)).isEqualTo(relativePath);
    }

    @Test
    public void transformsToRelativeWhenPathIsAbsoluteWithDriveLetter() {
        Path absolutePathWithDriveLetter = get("c:/some/absolute/path");

        assertThat(transformToRelativePath(absolutePathWithDriveLetter)).isEqualTo(get("c/some/absolute/path"));
    }

    @Test
    public void transformsToRelativeWhenPathIsAbsolute() {
        Path absolutePath = get("/some/absolute/path");

        assertThat(transformToRelativePath(absolutePath)).isEqualTo(get("some/absolute/path"));
    }
}