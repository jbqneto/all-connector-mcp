# Project Scope

## Goal

Provide one configurable MCP gateway codebase with native Java MCP tools and carefully selected downstream MCP integrations. Deployments select which connectors exist at startup through Spring Profiles and environment properties.

## MVP scope

- Java 25 / Spring Boot 4.1.1 / Spring AI 2.0.1; Maven Wrapper.
- Streamable HTTP MCP server for loopback-only development.
- Connector beans are absent unless enabled explicitly.
- GitHub repository metadata lookup as a read-only example, configured by URL, API key, enabled flag, and trusted hosts.
- Documentation, integration tests, and a CI verification workflow.

## Planned, not implemented

- Downstream MCP federation with explicit tool allowlists.
- Authenticated remote access, with per-tool authorization and audit logging.
- Additional native connectors and connectors specific to individual deployments.

## Non-goals for MVP

- An LLM, inference service, prompt orchestration, admin dashboard, database, or runtime configuration UI.
- Hot reloading connectors after startup.
- Arbitrary URL fetching, general-purpose shell commands, arbitrary filesystem access, or privileged GitHub writes.
