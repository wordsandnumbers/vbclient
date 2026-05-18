package com.vpo.vbclient.currentsong;

import org.springframework.web.client.RestTemplate;

import com.vpo.vbclient.model.Audio;

public class CurrentSongClient {

	private static final String BASE_CURRENT_URL = "/api/v1/current_song";
	private static final String BASE_AUDIO_URL = "/api/v1/audio";
	private static final String DEFAULT_ROOT_URL = "https://voiceboxpdx.com";

	private RestTemplate template;
	private String currentURL;
	private String audioURL;
	private String organization = null;

	public CurrentSongClient() {
		this(null, null);
	}

	public CurrentSongClient(String root, String organization) {
		super();
		String base = (root != null) ? root : DEFAULT_ROOT_URL;
		this.currentURL = base + BASE_CURRENT_URL;
		this.audioURL = base + BASE_AUDIO_URL;
		this.organization = organization;
		this.template = new RestTemplate();
	}

	public void skip(String roomCode) {
		template.postForObject(currentURL + "/skip?room_code={1}&organization={2}",
				null, Void.class, roomCode, organization);
	}

	public void restart(String roomCode) {
		template.postForObject(currentURL + "/restart?room_code={1}&organization={2}",
				null, Void.class, roomCode, organization);
	}

	public void pause(String roomCode) {
		template.postForObject(currentURL + "/pause?room_code={1}&organization={2}",
				null, Void.class, roomCode, organization);
	}

	public void resume(String roomCode) {
		template.postForObject(currentURL + "/resume?room_code={1}&organization={2}",
				null, Void.class, roomCode, organization);
	}

	public void setAudio(Audio audio) {
		template.put(audioURL + "?organization={1}", audio, organization);
	}

	public void setAudio(String roomCode, Integer volume, Integer pitchShift, String channels) {
		Audio audio = new Audio(roomCode);
		audio.setVolume(volume);
		audio.setPitchShift(pitchShift);
		audio.setChannels(channels);
		setAudio(audio);
	}
}
