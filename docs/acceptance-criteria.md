# MVP Acceptance Criteria

1. The project uses Maven Wrapper and Java 25; no Gradle files are present.
2. `./mvnw verify` passes in CI on Java 25.
3. Default startup uses the `local` profile and binds only to `127.0.0.1`.
4. Streamable HTTP is configured at `/mcp` for local development.
5. With `GITHUB_MCP_ENABLED=false`, no GitHub MCP tool bean is registered and no GitHub token is required.
6. With `GITHUB_MCP_ENABLED=true` and a valid token, exactly one GitHub tool bean is registered.
7. With the connector enabled and a missing token, startup fails validation rather than exposing a broken tool.
8. GitHub API URLs must be HTTPS and use a host explicitly on the allowlist.
9. The GitHub example performs only repository metadata GET calls; invalid owner/repository arguments are rejected before requests.
10. Tool responses do not contain API tokens or unselected API response fields.
11. The `remote` profile does not expose an unauthenticated MCP transport.
12. The gateway does not automatically re-expose third-party MCP tools.
13. README, AGENTS, CLAUDE, architecture, security boundaries, and contribution guidelines exist and are in English.
14. No secrets, local paths, or private configuration appear in the Git repository.

Passing CI does not by itself establish production security. End-to-end MCP protocol and access-control verification remain required before remote deployment.
