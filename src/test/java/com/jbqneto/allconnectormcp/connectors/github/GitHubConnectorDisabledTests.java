package com.jbqneto.allconnectormcp.connectors.github;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "all.connectors.github.enabled=false")
@ActiveProfiles("local")
class GitHubConnectorDisabledTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void disabledConnectorDoesNotRegisterToolsOrCredentials() {
        assertThat(context.getBeansOfType(GitHubMcpTools.class)).isEmpty();
        assertThat(context.getBeansOfType(GitHubConnectorProperties.class)).isEmpty();
    }
}
