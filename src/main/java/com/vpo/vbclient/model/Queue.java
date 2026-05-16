package com.vpo.vbclient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Queue {
//	{
//    "room_code": "CQFW",
//    "current_song": {
//        "song_id": 68250,
//        "play_id": "G-Test-6c4008b3e6c4-1435717763903-2",
//        "title": "Beautiful, Dirty, Rich",
//        "artist": "Lady Gaga",
//        "duration": 170000,
//        "position": 30000,
//        "paused": false
//    },
//    "songs_queued": 1,
//    "queue": [
//        {
//            "index": 0,
//            "song_id": 69001,
//            "play_id": "G-Test-6c4008b3e6c4-1435717770940-3",
//            "title": "Summerboy",
//            "artist": "Lady Gaga",
//            "duration": 242865
//        }
//    ]
//}

	public Queue() {
		super();
	}
	
	@JsonProperty("room_code")
	private String roomCode;
	@JsonProperty("current_song")
	private Play currentSong;
	@JsonProperty("songs_queued")
	private Integer songsQueued;
	private List<Play> queue;
	
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public Play getCurrentSong() {
		return currentSong;
	}
	public void setCurrentSong(Play currentSong) {
		this.currentSong = currentSong;
	}
	public Integer getSongsQueued() {
		return songsQueued;
	}
	public void setSongsQueued(Integer songsQueued) {
		this.songsQueued = songsQueued;
	}
	public List<Play> getQueue() {
		return queue;
	}
	public void setQueue(List<Play> queue) {
		this.queue = queue;
	}
	
}
