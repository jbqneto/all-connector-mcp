package com.jbqneto.allconnectormcp.connectors.github;

import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.web.client.RestClient;

public final class GitHubMcpTools {

    private static final Pattern REPOSITORY_SEGMENT = Pattern.compile("[A-Za-z0-9_.-]{1,100}");
    private final RestClient restClient;

    public GitHubMcpTools(RestClient restClient) {
        this.restClient = restClient;
    }

    @McpTool(name = "github_get_repository", description = "Read GitHub repository metadata without making changes")
    public RepositorySummary getRepository(
            @McpToolParam(description = "GitHub repository owner or organization") String owner,
            @McpToolParam(description = "GitHub repository name") String repository) {
        validateSegment(owner);
        validateSegment(repository);

        Map<?, ?> response = restClient.get()
                .uri(builder -> builder.pathSegment("repos", owner, repository).build())
                .retrieve()
                .body(Map.class);
        if (response == null) {
            throw new IllegalStateException("GitHub returned an empty repository response");
        }
        return new RepositorySummary(
                text(response.get("full_name")),
                text(response.get("description")),
                text(response.get("html_url")),
                Boolean.TRUE.equals(response.get("private")),
                Boolean.TRUE.equals(response.get("archived")));
    }

    private static void validateSegment(String value) {
        if (value == null || !REPOSITORY_SEGMENT.matcher(value).matches()
                || value.equals(".") || value.equals("..")) {
            throw new IllegalArgumentException("Invalid GitHub repository owner or name");
        }
    }

    private static String text(Object value) {
        return value == null ? "" : value.toString();
    }

    public record RepositorySummary(
            String fullName,
            String description,
            String htmlUrl,
            boolean privateRepository,
            boolean archived) {
    }
}
