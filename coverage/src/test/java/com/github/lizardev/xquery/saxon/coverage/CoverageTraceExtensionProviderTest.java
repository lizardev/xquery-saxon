package com.github.lizardev.xquery.saxon.coverage;

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
    public void shouldProvideExtensionWhenCoverageIsEnabled() {
        given(systemProperties.isCoverageEnabled()).willReturn(true);

        assertThat(provider.getTraceExtension().isPresent()).isTrue();
    }

    @Test
    public void shouldNotProvideExtensionWhenCoverageIsDisabled() {
        given(systemProperties.isCoverageEnabled()).willReturn(false);

        assertThat(provider.getTraceExtension().isPresent()).isFalse();
    }
}