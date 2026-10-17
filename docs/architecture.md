# Architecture

## Model

A modular monolith running one Spring application per environment. An MCP client (such as Codex or Claude Code) connects to the exposed server. Native tool beans live under `connectors/`. Future downstream MCP connections belong under `federation/` and must pass an allowlist before any tool is re-exposed.

## Modules by responsibility

- `config`: HTTP security and deployment-wide configuration.
- `connectors/<provider>`: configuration properties, bean activation, domain/API adapter, and safe tools.
- `federation` (future): downstream client configuration, discovery, filtering, and controlled re-exposure.

## Lifecycle

1. The process selects `local` or `remote` at startup.
2. Configuration is resolved from environment and YAML.
3. A connector is registered only if its `enabled` property is explicitly `true`.
4. Connector-specific properties are validated during startup.
5. Spring AI discovers annotated tool methods on registered beans.
6. MCP clients can list and call only exposed tools (subject to HTTP authorization).

## Profiles

`local` is the default and binds to loopback. `remote` disables MCP exposure until remote authentication and authorization are implemented. Activating both profiles simultaneously is unsupported; security must fail closed.

## Direction of dependencies

The gateway and individual connectors must not depend on one another's implementation internals. Protocol/transport concerns stay at the boundary. External services are accessed through constrained adapters, not general-purpose proxy endpoints.
