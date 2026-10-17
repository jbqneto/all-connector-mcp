package com.jbqneto.allconnectormcp.connectors.github;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

class GitHubMcpToolsTests {

    private MockRestServiceServer server;
    private GitHubMcpTools tools;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader("Authorization", "Bearer test-token");
        server = MockRestServiceServer.bindTo(builder).build();
        tools = new GitHubMcpTools(builder.build());
    }

    @Test
    void returnsOnlySelectedMetadata() {
        server.expect(requestTo("https://api.github.com/repos/octocat/Hello-World"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("Authorization", "Bearer test-token"))
                .andRespond(withSuccess("""
                        {"full_name":"octocat/Hello-World","description":"Example",
                         "html_url":"https://github.com/octocat/Hello-World",
                         "private":false,"archived":false,"secret":"not-exposed"}
                        """, MediaType.APPLICATION_JSON));

        GitHubMcpTools.RepositorySummary summary = tools.getRepository("octocat", "Hello-World");
        assertThat(summary.fullName()).isEqualTo("octocat/Hello-World");
        assertThat(summary.description()).isEqualTo("Example");
        assertThat(summary.htmlUrl()).isEqualTo("https://github.com/octocat/Hello-World");
        server.verify();
    }

    @Test
    void rejectsUntrustedPathSegmentsBeforeSendingRequests() {
        assertThatThrownBy(() -> tools.getRepository("../admin", "repo"))
                .isInstanceOf(IllegalArgumentException.class);
        server.verify();
    }
}
