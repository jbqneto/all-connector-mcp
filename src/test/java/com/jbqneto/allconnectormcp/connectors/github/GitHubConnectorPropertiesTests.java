package com.jbqneto.allconnectormcp.connectors.github;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jakarta.validation.Validation;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.Test;

class GitHubConnectorPropertiesTests {

    @Test
    void blankApiKeyIsInvalidWhenConnectorPropertiesAreValidated() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            GitHubConnectorProperties properties = new GitHubConnectorProperties(
                    URI.create("https://api.github.com"), "", List.of("api.github.com"));
            assertThat(factory.getValidator().validate(properties)).isNotEmpty();
        }
    }

    @Test
    void rejectsHttpAndUnapprovedHosts() {
        assertThatThrownBy(() -> new GitHubConnectorProperties(
                URI.create("http://api.github.com"), "dummy", List.of("api.github.com")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new GitHubConnectorProperties(
                URI.create("https://untrusted.example"), "dummy", List.of("api.github.com")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
