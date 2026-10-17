# Configuration

Configuration is resolved only at application startup. There is no live enable/disable API.

| Property | Environment variable | Default | Meaning |
| --- | --- | --- | --- |
| `spring.profiles.active` | `SPRING_PROFILES_ACTIVE` | `local` (default profile) | Execution environment |
| `server.port` | `SERVER_PORT` | `8080` | HTTP port |
| `all.connectors.github.enabled` | `GITHUB_MCP_ENABLED` | `false` | Register GitHub MCP tools |
| `all.connectors.github.url` | `GITHUB_API_URL` | `https://api.github.com` | HTTPS GitHub API base URL |
| `all.connectors.github.api-key` | `GITHUB_API_KEY` | empty | Token required when enabled |
| `all.connectors.github.allowed-hosts` | `GITHUB_ALLOWED_HOSTS` | `api.github.com` | Comma-separated exact HTTPS hosts allowed for the API base URL |

Use `.env.example` as a reference. Spring Boot does **not** automatically import a `.env` file; export variables or configure them in your deployment secret manager. Never commit `.env` or tokens.

## Security behavior

- `local`: MCP enabled, bind to `127.0.0.1`, restricted HTTP routes, `/mcp` CSRF exception only.
- `remote`: MCP disabled, HTTP requests denied until proper authentication and authorization exist.
- Federated MCP client auto-start disabled for the MVP.
- Downstream tool re-exposure disabled for the MVP.

A connector's activation flag only decides whether a bean exists. It does not authenticate a client or authorize a particular tool invocation.
