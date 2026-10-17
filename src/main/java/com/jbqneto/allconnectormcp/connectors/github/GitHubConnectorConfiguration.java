package com.jbqneto.allconnectormcp.connectors.github;

import java.net.http.HttpClient;
import java.time.Duration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "all.connectors.github", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(GitHubConnectorProperties.class)
public class GitHubConnectorConfiguration {

    @Bean
    GitHubMcpTools gitHubMcpTools(RestClient.Builder builder, GitHubConnectorProperties properties) {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(Duration.ofSeconds(10));
        RestClient client = builder
                .requestFactory(requestFactory)
                .baseUrl(properties.url().toString())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.apiKey())
                .build();
        return new GitHubMcpTools(client);
    }
}
