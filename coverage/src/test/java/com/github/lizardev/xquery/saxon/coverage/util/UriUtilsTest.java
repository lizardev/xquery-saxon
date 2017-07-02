package com.github.lizardev.xquery.saxon.coverage.util;

import static com.github.lizardev.xquery.saxon.coverage.util.UriUtils.uriToFilename;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.junit.Test;

public class UriUtilsTest {

    @Test
    public void verifyLongUriTransformation() throws Exception {
        String xQueryModuleFileName = "module.xq";
        URI uri = new URI("file:///very/long/directory/structure/with/length/exceeding/250/character/long/aaaaaaaaa/bbbbbbbbbbbbb/cccccccccc/dddddddddddd/eeeeeeeeee/fffffffffff/ggggggggggggggggg/hhhhhhhhhhhhhhhhhhh/iiiiiiiiiiiiiiiiiiiiiiiiii/jjjjjjjjjjjjjjjjjjjjjjjjjjj/xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx/jjjjjjjjjjjjjjjjjjjjjjjjj/com/example/" + xQueryModuleFileName);
        String filename = uriToFilename(uri);
        assertThat(filename.length()).as("Check if file name is not too long").isLessThanOrEqualTo((250));
        assertThat(filename).as("Check if file name have correct suffix").endsWith(xQueryModuleFileName);
        assertThat(filename).isEqualTo("fb3a2286f683c9bd21507f40e72a5cd406c8b83efff411ff1b1a680ed49b251a-fffffffffff_ggggggggggggggggg_hhhhhhhhhhhhhhhhhhh_iiiiiiiiiiiiiiiiiiiiiiiiii_jjjjjjjjjjjjjjjjjjjjjjjjjjj_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx_jjjjjjjjjjjjjjjjjjjjjjjjj_com_example_module.xq");
    }
  
    @Test
    public void verifyShortUriTransformation() throws Exception {
        URI uri = new URI("file:///workspace/com/example/module.xq");
        String filename = uriToFilename(uri);
        assertThat(filename).isEqualTo("_workspace_com_example_module.xq");
    }
}