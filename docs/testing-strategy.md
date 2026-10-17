# Testing Strategy

## Unit tests

- Validate tool inputs before network access.
- Verify a GitHub response is reduced to an approved DTO.
- Verify authentication headers are sent only to the configured, approved GitHub API base URL.

## Context tests

- A disabled connector does not instantiate properties or tools.
- An enabled connector instantiates its tool bean with valid configuration.
- A missing token causes startup validation failure (add a dedicated failure-case context test if possible).
- `remote` profile disables the MCP server.

## Integration / protocol tests (next iteration)

- Use a real MCP client against the local HTTP server to verify `tools/list` and `tools/call`.
- Verify tool invisibility for disabled connectors, authorization failures, and invalid upstream configuration.
- Keep outbound HTTP mocked; do not use live GitHub credentials in tests.

## CI

GitHub Actions runs `./mvnw verify` using Java 25. CI must not require secrets or an external GitHub API.
