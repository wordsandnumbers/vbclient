# vbclient

Java client for the [Voice Box Karaoke](https://voiceboxpdx.com) API.

## Overview

Voice Box Karaoke is a private-room karaoke platform (voiceboxpdx.com). `vbclient` is the Java SDK that wraps its HTTP API — searching the song catalog, managing per-room sessions and queues, posting popups, controlling room lights and audio, sending current-song commands, validating room codes, handling service calls, and collecting feedback. As of `0.3.0` it covers the full [karaoke API surface](https://vbsongs.com/api/v1/documentation/karaoke.html). It's used by the [DJ VoxBox] Spring Boot backend.

## Requirements

- Java 17
- Maven
- Spring Boot 3.2 (the library uses Spring's `RestTemplate` for HTTP)

## Installation

```xml
<dependency>
  <groupId>com.vpo</groupId>
  <artifactId>vbclient</artifactId>
  <version>0.3.0</version>
</dependency>
```

## Usage

All clients share the same constructor pattern. `root` overrides the API base URL (defaults to `https://voiceboxpdx.com`); `organization` is the 32-character org identifier required for every call.

```java
String org = "00000000000000000000000000000000"; // your 32-char org ID
```

### SongClient — catalog, favorites, history, stats

```java
SongClient songs = new SongClient(null, org);

Search results = songs.findSongs(new Search("love"));
Song song = songs.getSongById(68733);
TagList tags = songs.tags();
LanguageList languages = songs.languages();

List<AutocompleteSuggestion> suggestions = songs.autocomplete("lov");
List<Song> random = songs.roulette(new RouletteRequest());
SongStats stats = songs.stats();
songs.requestSong(new SongRequest("Beatles", "Let It Be"), session);
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

### CurrentSongClient — playback and audio controls

```java
CurrentSongClient current = new CurrentSongClient(null, org);

current.pause("CQFW");
current.resume("CQFW");
current.skip("CQFW");
current.restart("CQFW");
current.setAudio("CQFW", 75, 0, null); // volume, pitch_shift, channels
```

### RoomClient — room validation and service calls

```java
RoomClient rooms = new RoomClient(null, org);

Room room = rooms.getRoom("CQFW");
ServiceCall call = rooms.getServiceCall("CQFW");
rooms.setServiceCall(session, "CQFW", ServiceCall.STATE_REQUESTED);
```

### FeedbackClient — prompts and submissions

```java
FeedbackClient feedback = new FeedbackClient(null, org);

List<FeedbackPrompt> prompts = feedback.getPrompts(session, "CQFW");
feedback.submit(session, "CQFW", List.of(
    new FeedbackResponse(prompts.get(0).getGuid(), 5, "great room")));
```

Room codes (e.g. `"CQFW"`) identify a karaoke room within the organization.

## Build & test

```bash
mvn clean verify
```

The same command runs on every push and pull request via [GitHub Actions](.github/workflows/build.yml).

## Versioning

- `0.3.0` brings the library to full parity with the karaoke API: adds `RoomClient`, `CurrentSongClient`, `FeedbackClient`, and new `SongClient` methods (`autocomplete`, `roulette`, `stats`, `requestSong`), plus profile/queue/search fields that the prior models didn't expose.
- `0.2.0` migrated the library to Spring Boot 3.2, Java 17, and JUnit 5.
- Prior `0.1.x` releases targeted Spring Boot 1.x / Java 7 / JUnit 4.
