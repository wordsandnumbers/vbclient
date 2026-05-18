package com.vpo.vbclient.song;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vpo.vbclient.model.Song;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SongStats {

	public SongStats() {
		super();
	}

	@JsonProperty("top_songs")
	private List<Song> topSongs;
	@JsonProperty("top_artists")
	private List<TopArtist> topArtists;
	@JsonProperty("plays_per_day")
	private Map<String, Integer> playsPerDay;
	@JsonProperty("plays_per_week")
	private Map<String, Integer> playsPerWeek;
	@JsonProperty("total_plays")
	private Long totalPlays;

	public List<Song> getTopSongs() {
		return topSongs;
	}
	public void setTopSongs(List<Song> topSongs) {
		this.topSongs = topSongs;
	}
	public List<TopArtist> getTopArtists() {
		return topArtists;
	}
	public void setTopArtists(List<TopArtist> topArtists) {
		this.topArtists = topArtists;
	}
	public Map<String, Integer> getPlaysPerDay() {
		return playsPerDay;
	}
	public void setPlaysPerDay(Map<String, Integer> playsPerDay) {
		this.playsPerDay = playsPerDay;
	}
	public Map<String, Integer> getPlaysPerWeek() {
		return playsPerWeek;
	}
	public void setPlaysPerWeek(Map<String, Integer> playsPerWeek) {
		this.playsPerWeek = playsPerWeek;
	}
	public Long getTotalPlays() {
		return totalPlays;
	}
	public void setTotalPlays(Long totalPlays) {
		this.totalPlays = totalPlays;
	}

	@JsonIgnoreProperties(ignoreUnknown=true)
	public static class TopArtist {
		private String artist;
		@JsonProperty("play_count")
		private Integer playCount;

		public TopArtist() {
			super();
		}
		public String getArtist() {
			return artist;
		}
		public void setArtist(String artist) {
			this.artist = artist;
		}
		public Integer getPlayCount() {
			return playCount;
		}
		public void setPlayCount(Integer playCount) {
			this.playCount = playCount;
		}
	}
}
