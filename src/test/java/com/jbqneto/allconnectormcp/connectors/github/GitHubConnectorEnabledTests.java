package com.jbqneto.allconnectormcp.connectors.github;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
        "all.connectors.github.enabled=true",
        "all.connectors.github.api-key=test-token-not-a-real-secret"
})
@ActiveProfiles("local")
class GitHubConnectorEnabledTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void enabledConnectorRegistersOneToolBean() {
        assertThat(context.getBeansOfType(GitHubMcpTools.class)).hasSize(1);
    }
}
