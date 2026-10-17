package com.jbqneto.allconnectormcp.connectors.github;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "all.connectors.github")
public record GitHubConnectorProperties(
        @NotNull URI url,
        @NotBlank String apiKey,
        @NotNull List<String> allowedHosts
) {
    public GitHubConnectorProperties {
        if (url != null) {
            if (!"https".equalsIgnoreCase(url.getScheme())
                    || url.getHost() == null
                    || url.getUserInfo() != null
                    || url.getQuery() != null
                    || url.getFragment() != null) {
                throw new IllegalArgumentException("GitHub API URL must be a valid HTTPS URL without credentials, query or fragment");
            }
            if (allowedHosts != null && allowedHosts.stream()
                    .noneMatch(host -> host.trim().equalsIgnoreCase(url.getHost()))) {
                throw new IllegalArgumentException("GitHub API URL host must be explicitly allowed");
            }
        }
    }
}
