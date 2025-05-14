# Slack GrokBot Architecture
## Overview
Slack GrokBot is a Kotlin-based Spring Boot application designed to operate as a conversational bot within Slack channels. It leverages the Slack AI Apps API to handle user interactions and the xAI Grok API to fetch user details. The application is built for scalability and maintainability, with plans to integrate the SeaLights API for code coverage and TGA reports.
This document outlines the tech stack, key APIs, and application architecture, including a PlantUML sequence diagram illustrating the bot’s interaction flow.
## Tech Stack

Kotlin: Primary programming language, chosen for its conciseness, null safety, and interoperability with Java. Version: 1.9.24.
Spring Boot: Framework for building the RESTful application, handling dependency injection, and managing API integrations. Version: 3.3.2.
Gradle Kotlin DSL: Build tool for dependency management and project configuration. Version: 8.x.
Slack Bolt for Java: Library for interacting with Slack’s API, handling events like assistant_thread_started and message.im. Version: 1.40.1.
WebClient (Spring WebFlux): Non-blocking HTTP client for calling the xAI Grok API. Part of Spring Boot.
JUnit 5: Testing framework for unit and integration tests. Version: 5.11.0.
MockK: Mocking library for Kotlin, used in tests. Version: 1.13.12.
ktlint: Linter for enforcing Kotlin coding conventions. Version: 12.1.1.
Docker: Containerization for deployment, using openjdk:17-jdk-slim base image.

## APIs
### Slack AI Apps API

Purpose: Enables the bot to receive and respond to Slack events in channels or direct messages.
Endpoints/Events:
assistant_thread_started: Triggered when a user opens the AI app container.
assistant_thread_context_changed: Triggered when the channel context changes.
message.im: Captures direct messages to the bot.


Authentication: Uses SLACK_BOT_TOKEN (OAuth) and SLACK_APP_TOKEN (WebSocket).
Reference: Slack AI Apps API Docs

### xAI Grok API

Purpose: Provides user details based on the bot’s queries, processed from Slack messages.
Endpoint: POST https://api.x.ai/v1/grok
Request: JSON payload with a prompt field (e.g., {"prompt": "Get user details for John Doe"}).
Authentication: Bearer token via GROK_API_KEY.
Response: JSON with a response field containing the result.
Reference: xAI Grok API Docs

### SeaLights API (Planned)

Purpose: Will enable querying code coverage data and TGA reports from the SeaLights dashboard.
Endpoints (TBD):
/sl-api/v2/coverage: For coverage metrics.
/sl-api/v2/tga: For TGA reports.


## Authentication: API token (to be stored in .env).
Status: Planned for post-MVP integration.
Reference: SeaLights API Docs

## Application Architecture
The application follows a layered architecture, with Spring Boot handling HTTP and WebSocket interactions, Slack Bolt processing events, and WebClient making external API calls. The bot runs in a Docker container, with environment variables loaded from .env.
Components

Slack Event Handler: Processes Slack events (assistant_thread_started, message.im) using Slack Bolt, triggering responses.
Grok Service: Encapsulates logic for calling the xAI Grok API via WebClient, parsing responses, and formatting replies.
Spring Boot Application: Manages dependency injection, configuration, and application lifecycle.
Tests: Unit and integration tests in src/test/kotlin/com/example, using JUnit 5 and MockK.

## Sequence Diagram
Below is a PlantUML sequence diagram showing the flow when a user sends a message to the bot in Slack:

```plantuml

actor User
participant "Slack" as Slack
participant "GrokBot\n(Spring Boot)" as Bot
participant "Grok API\n(xAI)" as GrokAPI

User -> Slack: Sends message\n"Get user details for John Doe"
Slack -> Bot: Event: message.im
Bot -> GrokAPI: POST /v1/grok\n{"prompt": "Get user details..."}
GrokAPI -> Bot: {"response": "User details..."}
Bot -> Slack: Reply in thread\n"User details..."
Slack -> User: Displays reply

```