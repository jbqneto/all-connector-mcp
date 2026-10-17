# All Connector MCP

A modular, hybrid Model Context Protocol (MCP) gateway built with Java 25, Spring Boot 4.1.1, and Spring AI 2.0.1. The project is designed to expose native Java tools and, in future iterations, selected tools from third-party MCP servers.

> **Status:** early MVP. Native GitHub read-only metadata lookup is the only example connector. Third-party MCP federation and authenticated remote access are **not implemented yet**.

## Requirements

- Java 25
- Maven Wrapper (`./mvnw`), included in this repository

## Run locally

```bash
./mvnw spring-boot:run
```

By default, the `local` profile binds HTTP to `127.0.0.1:8080`, enables Streamable HTTP at `http://127.0.0.1:8080/mcp`, and disables every optional connector. Unauthenticated local MCP calls are allowed only because the listener is bound to loopback.

Connect an MCP-aware client:

```bash
codex mcp add all-connector-mcp --url http://127.0.0.1:8080/mcp
claude mcp add --transport http --scope user all-connector-mcp http://127.0.0.1:8080/mcp
```

## Enable the GitHub connector

Provide a read-only, least-privileged GitHub token. Do not commit it or paste it into application YAML.

```bash
export SPRING_PROFILES_ACTIVE=local
export GITHUB_MCP_ENABLED=true
export GITHUB_API_KEY='replace-with-a-token-from-your-secret-manager'
./mvnw spring-boot:run
```

The connector registers `github_get_repository`, which returns only selected repository metadata (`fullName`, `description`, `htmlUrl`, `privateRepository`, and `archived`). It performs no writes. A token is required when enabled; startup validation fails if it is blank. The `GITHUB_API_URL` setting defaults to `https://api.github.com`, and `GITHUB_ALLOWED_HOSTS` defaults to `api.github.com`. Additional hosts must be explicitly approved via environment configuration.

See [configuration](docs/configuration.md) and [connector contract](docs/connector-contract.md).

## Remote deployment

The `remote` profile deliberately disables the MCP HTTP server and denies HTTP access until authenticated remote access is implemented and reviewed. **Do not expose the local profile on a public interface, reverse proxy, tunnel, or VPS.** Remote deployment is not usable yet; this is an intentional security boundary.

## Project documentation

- [Scope](docs/project-scope.md)
- [Architecture](docs/architecture.md)
- [Connector contract](docs/connector-contract.md)
- [Configuration](docs/configuration.md)
- [Security boundaries](docs/security-boundaries.md)
- [Agent constraints](docs/agent-constraints.md)
- [Coding standards](docs/coding-standards.md)
- [Acceptance criteria](docs/acceptance-criteria.md)
- [Testing strategy](docs/testing-strategy.md)
- [Architecture decisions](docs/adr/0001-modular-hybrid-mcp-gateway.md)

## Build and test

```bash
./mvnw --batch-mode verify
```

## License

Apache License 2.0. See [`LICENSE`](LICENSE).
