package com.vpo.vbclient.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Play {
	
	public Play() {
			super();
		}
//  from: play/history
//    "song_id": 63530,
//    "play_id": "G-Test-fefdadffdcfa-1439598265971-1",
//    "title": "Just Dropped In (To See What Condition My Condition Was In)",
//    "artist": "Rogers, Kenny & The First Edition",
//    "location": "Test",
//    "business_date": "2015-08-14",
//    "enqueue_time": "2015-08-15T00:24:25.971Z",
//    "start_time": "2015-08-15T00:26:39.035Z",
//    "end_time": "2015-08-15T00:30:14.117Z",
//    "duration": 212331,
//    "position": 212331,
//    "favorite": false
	
	
//    "song_id": 67519,
//    "play_id": "G-Test-f23c9167b37b-1458100596049-4",
//    "title": "Start It Up",
//    "artist": "Banks, Lloyd ft. Kanye West, Swizz Beatz, Ryan Leslie & Fabolous",
//    "duration": 302680,
//    "position": 30000,
//    "paused": false,
//    "message": "a3c71usdda9jjw9",
//    "message_color": "#00BCB5"

	@JsonProperty("song_id")
	private Integer songId;
	@JsonProperty("play_id")
	private String playId;
	private Integer index;
	private String title;
	private String artist;
	private Integer duration;
	private Integer position;
	private Long estimatedPlayTime;
	private boolean paused;
	private String message;
	@JsonProperty("message_color")
	private String messageColor;
	@JsonAlias({"location"})
	private String locaton;
	private Boolean favorite;
	@JsonProperty("business_date")
	private Date businessDate;
	@JsonProperty("enqueue_date")
	private Date enqueueDate;
	@JsonProperty("start_date")
	private Date startDate;
	@JsonProperty("end_date")
	private Date endDate;
	
	public Integer getId() {
		return songId;
	}
	public void setId(Integer id) {
		this.songId = id;
	}
	public String getPlayId() {
		return playId;
	}
	public void setPlayId(String playId) {
		this.playId = playId;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Long getEstimatedPlayTime() {
		return estimatedPlayTime;
	}
	public void setEstimatedPlayTime(Long estimatedPlayTime) {
		this.estimatedPlayTime = estimatedPlayTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageColor() {
		return messageColor;
	}
	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	public Integer getSongId() {
		return songId;
	}
	public void setSongId(Integer songId) {
		this.songId = songId;
	}
	public String getLocaton() {
		return locaton;
	}
	public void setLocaton(String locaton) {
		this.locaton = locaton;
	}
	public Boolean getFavorite() {
		return favorite;
	}
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}
	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	public Date getEnqueueDate() {
		return enqueueDate;
	}
	public void setEnqueueDate(Date enqueueDate) {
		this.enqueueDate = enqueueDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Play [songId=" + songId + ", playId=" + playId + ", index="
				+ index + ", title=" + title + ", artist=" + artist + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playId == null) ? 0 : playId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Play other = (Play) obj;
		if (playId == null) {
			if (other.playId != null)
				return false;
		} else if (!playId.equals(other.playId))
			return false;
		return true;
	}
	
	
	
}
