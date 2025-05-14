# Slack GrokBot
## Overview
Slack GrokBot is a Kotlin-based Spring Boot application that integrates with the Slack AI Apps API to provide a conversational bot in Slack channels. The bot processes user messages, queries the xAI Grok API for user details, and responds in-thread. Future enhancements will include integration with the SeaLights API for retrieving code coverage and TGA reports.
## Features

- Listens for Slack events (assistant_thread_started, message.im) using the Slack Bolt framework.
- Queries the xAI Grok API to fetch user details based on user input.
- Built with Kotlin, Spring Boot, and Gradle Kotlin DSL for robust development.
Planned: SeaLights API integration for coverage dashboard queries.

## Architecture

See ARCHITECTURE.md for details on the tech stack, APIs, and application architecture, including a PlantUML diagram.

## Prerequisites

JDK: 17 or higher
Gradle: 8.x (or use ./gradlew)
Docker: For building and running the Docker image
Slack Workspace: Admin access for app creation
xAI Grok API Key: Obtain from xAI Docs
Slack Tokens: Bot User OAuth Token (xoxb-) and App Token (xapp-)

## Setup

Clone the repository:git clone <repository-url>
cd slack-grok-bot


Configure environment variables:
Create .env in the project root:SLACK_BOT_TOKEN=xoxb-<your-bot-token>
SLACK_APP_TOKEN=xapp-<your-app-token>
GROK_API_KEY=<your-grok-api-key>




Configure Slack app:
Create an app at Slack API with AI Apps features enabled.
Subscribe to events: assistant_thread_started, assistant_thread_context_changed, message.im.
Install the app to your workspace to obtain tokens.



Build
Build the project using Gradle:
./gradlew build

This compiles the Kotlin code, runs ktlint checks (if configured), and generates a JAR in build/libs.
Test
Run unit and integration tests with JUnit 5 and MockK:
./gradlew test


Tests are located in src/test/kotlin/com/example.
Example test (GrokServiceTest.kt) verifies Grok API interactions.
View test reports in build/reports/tests/test.

Build and Run Docker Image

Create a Dockerfile in the project root:FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/slack-grok-bot-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env
ENV SPRING_CONFIG_IMPORT=optional:file:.env[.properties]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


Build the Docker image:./gradlew bootJar
docker build -t slack-grok-bot:latest .


Run the Docker container:docker run -p 8080:8080 --env-file .env slack-grok-bot:latest


The app runs on port 8080.
Ensure .env contains valid SLACK_BOT_TOKEN, SLACK_APP_TOKEN, and GROK_API_KEY.



Project Structure
slack-grok-bot/
├── src/
│   ├── main/
│   │   ├── kotlin/com/example/  # Application code
│   │   ├── resources/           # Configuration files
│   ├── test/
│   │   ├── kotlin/com/example/  # Test code
├── .env                         # Environment variables
├── build.gradle.kts             # Gradle build file
├── settings.gradle.kts          # Gradle settings
├── Dockerfile                   # Docker configuration
├── .junie/
│   ├── guidelines.md            # Development guidelines
├── README.md                    # This file

Next Steps

Implement Slack bot logic in src/main/kotlin/com/example.
Add SeaLights API integration for coverage queries.
Enhance error handling and logging.

