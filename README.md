# java-project-gaming-presentation
🎮 Game API: Security & Observability Framework
This project features a robust, enterprise-grade architecture for a Game Management API. It focuses on Stateless Security using JWT and Cross-Cutting Concerns using Aspect-Oriented Programming (AOP).

🚀 System Architecture
The application is built on three main pillars:

Stateless Authentication: Security is handled via JSON Web Tokens (JWT), ensuring the server doesn't need to store session data.

Role-Based Access Control (RBAC): Users are assigned roles (User, Moderator, Admin) that strictly define what API actions they can perform.

Automated Observability: Method logging, performance timing, and exception tracking are handled by "Aspects," keeping the business logic clean.

🔒 Security Implementation
1. JWT Lifecycle (JwtUtils)
Handles the heavy lifting of security tokens:

Encoding: Signs user identity and roles into a cryptographic string.

Validation: Verifies token integrity and checks for expiration.

Secret Management: Uses HMAC-SHA signing with a configurable secret key.

2. The Gatekeeper (JwtAuthenticationFilter)
A custom security filter that runs once per request:

Intercepts the Authorization: Bearer <token> header.

Authenticates the user against the database.

Injects the user's roles into the Spring Security Context for access control.

3. Password Safety (PasswordEncoderConfig)
Uses BCrypt hashing.

Ensures passwords are never stored in plain text.

Automatically handles "salting" to prevent rainbow table attacks.

4. API Documentation (OpenApiConfig)
Integrated Swagger UI for testing.

Global Security Definition: Adds the "Authorize" button to the UI so you can paste your JWT once and test all protected endpoints.

📊 Monitoring & Logging (AOP)
We utilize Spring AOP to separate technical concerns from game logic. This keeps our service classes readable and focused.

Logging Aspect
Entry/Exit: Logs every method call in the controller and service layers.

Performance: Measures execution time in milliseconds for every request to identify bottlenecks.

Data Audit: Logs input arguments and return results automatically.

Exception Aspect
Centralized Errors: Automatically catches and logs exceptions in any service or controller.

Stack Traces: Ensures developers have full visibility into crashes without manual try-catch blocks everywhere.

🛠️ Security Rules Table
Endpoint,Method,Required Role,Description
/api/v1/auth/**,ANY,Public,"Login, Registration, Token Refresh"
/v3/api-docs/**,GET,Public,Swagger Documentation
/api/games/**,GET,Public,Browsing the game library
/api/users/**,ANY,ADMIN / MODERATOR,Managing user accounts
/api/**,DELETE,ADMIN,Only Admins can delete data
All other endpoints,POST/PUT,AUTHENTICATED,Requires a valid JWT

⚙️ Configuration
Add these properties to your application.properties to initialize the security system:

# JWT Configuration
app.jwt.secret=your_super_secret_high_entropy_key_here
app.jwt.expiration=86400000

# Logging Levels
logging.level.com.game.hyf=INFO

📦 Dependencies
Spring Boot Starter Security: Core security framework.

JJWT (io.jsonwebtoken): For JWT creation and parsing.

Spring Boot Starter AOP: For cross-cutting logging logic.

Lombok: For @Slf4j and boilerplate reduction.

SpringDoc OpenAPI: For interactive Swagger documentation.