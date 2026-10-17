# Connector Contract

## Native connector requirements

A connector owns its configuration properties, conditional bean registration, external service adapter, and tests. Follow the existing `connectors/github` example.

- `enabled` defaults to `false`.
- `url` is explicit, validated, and constrained to approved destinations.
- `api-key` is loaded from environment or a secret manager, never committed or logged.
- Use `@ConditionalOnProperty` to conditionally instantiate the tool bean; `@Profile` only when its permitted environment is restricted.
- Enable `@ConfigurationProperties` validation only when the connector exists so that disabled integrations require no credentials.
- Use `@McpTool` and narrowly described schemas for exposed operations.
- Prefer read-only operations and return a bounded, structured, sanitized response.
- Validate all parameters before outbound calls.

## Remote federation (planned)

Downstream MCP tools must be allowlisted by upstream server identity and tool name, and conflicts must be handled explicitly. Do not enable `spring.ai.mcp.server.expose-mcp-client-tools` without an approved and tested filtering strategy.

## Examples of forbidden tool behavior

No arbitrary shell execution, broad filesystem traversal, arbitrary outbound requests, credential disclosure, or write operations without explicit feature approval and authorization.
