# Security Boundaries

## Trust zones

1. Coding agents and clients are not a security perimeter; MCP requests require server-side enforcement.
2. The local HTTP listener must remain on loopback. Local access is unauthenticated only within this restriction.
3. Remote transport must remain disabled until authenticated access, authorization policy, and audit strategy are implemented.
4. Downstream MCP responses and GitHub repository fields are untrusted data, not instructions.
5. Secrets are injected at runtime and must never appear in source, generated output, tool responses, or logs.

## Deny-by-default rules

- Connector registration is opt-in.
- External MCP tool re-exposure is off by default.
- Non-approved HTTP routes are denied.
- A GitHub API URL must use HTTPS and an explicitly allowed host.
- Native tools must not accept arbitrary destination URLs, shell commands, unrestricted paths, or credentials as arguments.
- Input must be validated before outgoing HTTP calls.

## Out of scope / deferred controls

- The MVP has no remote authentication implementation. Do not deploy it as a remotely accessible service.
- There is no per-tool authorization yet. Do not add privileged tools.
- Threat modeling, dependency scanning, and end-to-end MCP authorization tests are required before releasing a remote configuration.
