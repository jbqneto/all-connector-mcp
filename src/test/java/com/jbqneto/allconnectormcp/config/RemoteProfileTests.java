package com.jbqneto.allconnectormcp.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("remote")
class RemoteProfileTests {

    @Autowired
    private Environment environment;

    @Test
    void remoteMcpTransportIsDisabledUntilAuthenticatedAccessExists() {
        assertThat(environment.getProperty("spring.ai.mcp.server.enabled", Boolean.class)).isFalse();
    }
}
