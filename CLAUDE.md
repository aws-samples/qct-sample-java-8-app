# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a sample Java 8 Spring Boot application designed for testing Amazon Q Code Transformation. It implements a simple file download server with HTTP caching support (ETags, Last-Modified headers) and bandwidth throttling.

**Key Technologies:**
- Java 8 (source/target 1.8)
- Spring Boot 1.2.3
- Maven 3 for build management
- Spock Framework (Groovy) for testing

## Build and Test Commands

### Building the project
```bash
./mvnw clean package
```

### Running tests
```bash
./mvnw test
```

### Running the application
```bash
./mvnw spring-boot:run
```

### Running a single test
```bash
# For Spock tests (Groovy)
./mvnw test -Dtest=DownloadControllerSpec
```

## Architecture

### Core Components

The application follows a layered architecture with clear separation of concerns:

**1. Controller Layer** (`DownloadController`)
- Exposes two endpoints: `GET /download/{uuid}` (redirect) and `GET /download/{uuid}/{filename}` (direct download)
- Both endpoints support GET and HEAD methods
- Handles HTTP caching headers (If-None-Match, If-Modified-Since)
- Returns 301 redirects or 200/304 responses based on caching logic

**2. File Abstraction Layer** (`FilePointer` interface)
- Abstracts file metadata and access regardless of storage backend
- Provides: size, original name, ETag, media type, last modified timestamp
- Key implementation: `FileSystemPointer` for local filesystem access

**3. Storage Layer** (`FileStorage` interface)
- Responsible for locating files by UUID
- Two implementations controlled by Spring profiles:
  - `FileSystemStorage` (active when NOT in test profile): stub that returns logback.xml for all UUIDs
  - `FileStorageStub` (active in test profile): returns predefined test fixtures

**4. Response Handling** (`ExistingFile`)
- Encapsulates HTTP response logic for file downloads
- Implements caching strategy (ETag matching, modification date comparison)
- Applies bandwidth throttling at 1 MB/s using Guava's RateLimiter
- Handles both redirect and direct download scenarios

### Key Design Patterns

- **Strategy Pattern**: FileStorage interface allows swapping storage implementations
- **Profile-based Configuration**: Spring profiles control which FileStorage implementation is active
- **Optional Pattern**: Heavy use of Java 8 Optional for null safety (e.g., headers, file lookup)
- **Functional Programming**: Lambda expressions and method references throughout

### Testing Strategy

Tests are written in Spock (Groovy BDD framework):
- Use Spring's MockMvc for controller integration testing
- Test profile activates FileStorageStub for predictable test data
- Tests cover: basic download, 404 handling, ETag caching, Last-Modified caching, HEAD requests, content negotiation

## Important Implementation Details

- **Bandwidth Throttling**: Downloads are limited to 1 MB/s via `ThrottlingInputStream` wrapping
- **HTTP Caching**: Full support for conditional requests using ETags and Last-Modified timestamps
- **Redirect Pattern**: UUID-only endpoint returns 301 to UUID+filename endpoint for SEO/browser-friendly URLs
- **HEAD Request Support**: Properly implements HEAD by returning headers without body content

## Known Limitations

`FileSystemStorage` is a stub implementation that always returns logback.xml regardless of UUID. A production implementation would need proper UUID-to-file mapping logic.
