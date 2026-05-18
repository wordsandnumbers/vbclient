package com.vpo.vbclient.session;

import org.springframework.web.client.RestTemplate;

import com.vpo.vbclient.model.Session;

public class SessionClient {



	private RestTemplate template;
	
	private static final String BASE_PROFILE_URL = "/api/v1/profile";
	private static final String BASE_LOGIN_URL = "/api/v1/login";
	private static final String BASE_POPUP_URL = "/api/v1/popups";
	private static final String BASE_LIGHTS_URL = "/api/v1/lights";


	private static final String DEFAULT_ROOT_URL = "https://voiceboxpdx.com";

	
	private String profileURL;
	private String loginURL;
	private String popupURL;
	private String lightsURL;
	private String organization = "";
	
	public SessionClient() {
		super();
		this.profileURL = DEFAULT_ROOT_URL + BASE_PROFILE_URL;
		this.loginURL = DEFAULT_ROOT_URL + BASE_LOGIN_URL;
		this.popupURL = DEFAULT_ROOT_URL + BASE_POPUP_URL;
		this.lightsURL = DEFAULT_ROOT_URL + BASE_LIGHTS_URL;

		this.template = new RestTemplate();
	}
	
	public SessionClient(String root, String organization) {
		super();
		if(root != null) {
			this.profileURL = root + BASE_PROFILE_URL;
			this.loginURL = root + BASE_LOGIN_URL;
			this.popupURL = root +  BASE_POPUP_URL;
			this.lightsURL = root + BASE_LIGHTS_URL;

		} else {
			this.profileURL = DEFAULT_ROOT_URL + BASE_PROFILE_URL;
			this.loginURL = DEFAULT_ROOT_URL + BASE_LOGIN_URL;		
			this.popupURL = DEFAULT_ROOT_URL + BASE_POPUP_URL;
			this.lightsURL = DEFAULT_ROOT_URL + BASE_LIGHTS_URL;
			}
		this.organization = "&organization=" + organization;
		this.template = new RestTemplate();
	}
	
	public Session getSessionById(final String id) {
		return template.getForObject(profileURL + "?session={1}" + organization, Session.class, id);
	}
	
	public Session createSession(final Session session) {
		return template.postForObject(loginURL + "?" + organization, session, Session.class);
	}

	public Session createSession(final Session session, final String existingSessionUuid) {
		return template.postForObject(loginURL + "?session={1}" + organization, session, Session.class, existingSessionUuid);
	}
	
	public Session updateSession(final Session session) {
		template.put(profileURL + "?" + organization, session, Session.class);
		return getSessionById(session.getSession());
	}
	
	public void postPopup(final Session session, String roomCode, String message) {
		template.postForLocation(popupURL + "?session={1}&text={2}&room_code={3}" + organization , null, session.getSession(), message, roomCode);
	}
	
	public void controlLights(String roomCode, Integer mode) {
		controlLights(roomCode, mode, 1);
	}
	
	public void controlLights(String roomCode, Integer mode, Integer effects) {
		template.put(lightsURL + "?room_code={1}&mode={2}&effects={3}" + organization , null, roomCode, mode, effects);
	}
	
	
}
