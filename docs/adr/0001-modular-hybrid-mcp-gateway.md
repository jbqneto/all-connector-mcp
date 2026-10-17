# ADR 0001 — Modular hybrid MCP gateway

- Status: Accepted for MVP
- Date: 2026-10-17

## Context

We need one Java application consumable by Codex, Claude Code, and other MCP clients, with configurable native connectors and future third-party MCP federation. Some connectors must be local only, remote only, or permitted in both deployments.

## Decision

Use a modular Spring Boot monolith with Spring AI MCP server and client starters. Each deployment chooses a Spring Profile at startup, and each connector uses `@ConditionalOnProperty` for opt-in bean creation. Connector configuration uses typed `@ConfigurationProperties` objects and environment variables. No runtime toggles or distributed persistence are needed.

The initial local deployment uses Streamable HTTP on loopback. Remote MCP transport stays disabled until authentication and authorization are implemented. Downstream tool re-exposure stays disabled until tool allowlisting is reviewed.

## Consequences

- A single repository and deployable artifact simplify maintenance.
- Tools never exist in contexts where their connector is disabled.
- Adding federation or authenticated remote access requires additional ADRs, tests, and security review.
- Separate deployments have independently resolved configuration and secrets.
