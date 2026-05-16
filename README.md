# vbclient

Java client for the [Voice Box Karaoke](http://voiceboxpdx.com) API.

## Overview

Voice Box Karaoke is a private-room karaoke platform (voiceboxpdx.com). `vbclient` is the Java SDK that wraps its HTTP API — searching the song catalog, managing per-room sessions and queues, posting popups, and controlling room lights. It's used by the [DJ VoxBox] Spring Boot backend.

## Requirements

- Java 17
- Maven
- Spring Boot 3.2 (the library uses Spring's `RestTemplate` for HTTP)

## Installation

```xml
<dependency>
  <groupId>com.vpo</groupId>
  <artifactId>vbclient</artifactId>
  <version>0.2.0-RELEASE</version>
</dependency>
```

## Usage

All three clients share the same constructor pattern. `root` overrides the API base URL (defaults to `http://voiceboxpdx.com`); `organization` is the 32-character org identifier required for every call.

```java
String org = "00000000000000000000000000000000"; // your 32-char org ID
```

### SongClient — catalog, favorites, history

```java
SongClient songs = new SongClient(null, org);

Search results = songs.findSongs(new Search("love"));
Song song = songs.getSongById(68733);
TagList tags = songs.tags();
LanguageList languages = songs.languages();
```

### SessionClient — sessions, popups, lights

```java
SessionClient sessions = new SessionClient(null, org);

Session s = new Session();
s.setHandle("Alice");
s.setSession(UUID.randomUUID().toString());
Session created = sessions.createSession(s);

sessions.postPopup(created, "CQFW", "Hello room!");
sessions.controlLights("CQFW", 2);
```

### QueueClient — room queue

```java
QueueClient queue = new QueueClient(null, org);

Queue current = queue.getQueue("CQFW");
queue.addSong(new PlayRequest(/* ... */));
queue.reorder("CQFW", "0", "1");
```

Room codes (e.g. `"CQFW"`) identify a karaoke room within the organization.

## Build & test

```bash
mvn clean verify
```

The same command runs on every push and pull request via [GitHub Actions](.github/workflows/build.yml).

## Versioning

`0.2.0-RELEASE` migrated the library to Spring Boot 3.2, Java 17, and JUnit 5. Prior `0.1.x` releases targeted Spring Boot 1.x / Java 7 / JUnit 4.
