# Coding Standards

- Java 25, Spring Boot 4, Spring AI 2; Maven is the only build system.
- Use English names, Javadocs, messages, and documentation.
- Prefer constructor injection, immutable data records, and cohesive classes.
- Keep responsibilities separated by connector; avoid premature generalized abstractions.
- Keep tool names stable, descriptive, and namespaced by connector.
- Never put secrets in strings, examples, exception messages, or logs.
- Use configuration properties rather than scattered `@Value` fields.
- Make outbound calls bounded and predictable; add explicit timeouts before production deployment.
- Do not introduce Lombok, JPA, WebFlux, or a database without a documented need.
- Run Maven verification and document every unverified assumption.
