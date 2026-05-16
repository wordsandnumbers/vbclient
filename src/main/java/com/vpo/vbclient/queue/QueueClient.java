package com.vpo.vbclient.queue;

import org.springframework.web.client.RestTemplate;

import com.vpo.vbclient.model.Play;
import com.vpo.vbclient.model.Queue;
import com.vpo.vbclient.model.Session;

public class QueueClient {

private RestTemplate template;
	
	private static final String BASE_API_URL = "/api/v1/queue";
	private static final String DEFAULT_ROOT_URL = "https://voiceboxpdx.com";

	private String baseURL;
	private String organization = null;
	
	public QueueClient() {
		this((String) null);
	}

	public QueueClient(String root) {
		super();
		if(root != null) {
			this.baseURL = root + BASE_API_URL;
		} else {
			this.baseURL = DEFAULT_ROOT_URL + BASE_API_URL;
		}
		this.template = new RestTemplate();
	}
	
	public QueueClient(String root, String organization) {
		super();
		if(root != null) {
			this.baseURL = root + BASE_API_URL;
		} else {
			this.baseURL = DEFAULT_ROOT_URL + BASE_API_URL;
		}
		this.organization = organization;
		this.template = new RestTemplate();
	}
	
	public Queue getQueue(String roomCode) {
		return template.getForObject(baseURL + "?room_code={1}&organization={2}", Queue.class, roomCode, organization);
	}
	
	public Play addSong(PlayRequest request, Session session) {
		String sessionId = session != null ? session.getSession() : null;
		return template.postForObject(baseURL + "?organization={1}&session={2}", request, Play.class, organization, sessionId);
	}
	
	public Play addSong(PlayRequest request) {
		return template.postForObject(baseURL + "?organization={1}", request, Play.class, organization);
	}
	
	public void deletePlay(String roomCode, Play play) {
		template.delete(baseURL + "?organization={1}&from={2}&room_code={3}", organization, play.getPlayId(), roomCode);
	}
	
	public void deleteAll(String roomCode) {
		template.delete(baseURL + "?organization={1}&room_code={2}", organization, roomCode);
	}
	
	public void reorder(String roomCode, String from, String to) {
		Object result = template.postForObject(baseURL + "/reorder?organization={1}&room_code={2}&from={3}&to={4}", null, Object.class, organization, roomCode, from, to);
		System.out.println(result);
	}
	
	public Play replace(String roomCode, Play current, PlayRequest replacement, Session session) {
		replacement.setTo(current.getPlayId());
		Play play = null;
		if(session != null) {
			play = addSong(replacement, session);
		} else {
			play = addSong(replacement);
		}
		deletePlay(roomCode, current);
		return play;
	}
	
	
}
