package org.xquery.saxon.coverage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CoverageTraceExtensionProviderTest {

    @Mock private SystemProperties systemProperties;
    @InjectMocks private CoverageTraceExtensionProvider provider;

    @Test
    public void shouldProvideExtensionWhenEnabled() {
        given(systemProperties.isCoverageTraceExtensionEnabled()).willReturn(true);

        assertThat(provider.getTraceExtension()).isNotNull();
    }

    @Test
    public void shouldNotProvideExtensionWhenDisabled() {
        given(systemProperties.isCoverageTraceExtensionEnabled()).willReturn(false);

        assertThat(provider.getTraceExtension()).isNull();
    }
}