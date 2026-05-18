package com.vpo.vbclient.room;

import org.springframework.web.client.RestTemplate;

import com.vpo.vbclient.model.Room;
import com.vpo.vbclient.model.ServiceCall;
import com.vpo.vbclient.model.Session;

public class RoomClient {

	private static final String BASE_ROOM_URL = "/api/v1/room";
	private static final String BASE_SERVICE_CALL_URL = "/api/v1/service_call";
	private static final String DEFAULT_ROOT_URL = "https://voiceboxpdx.com";

	private RestTemplate template;
	private String roomURL;
	private String serviceCallURL;
	private String organization = null;

	public RoomClient() {
		this(null, null);
	}

	public RoomClient(String root, String organization) {
		super();
		String base = (root != null) ? root : DEFAULT_ROOT_URL;
		this.roomURL = base + BASE_ROOM_URL;
		this.serviceCallURL = base + BASE_SERVICE_CALL_URL;
		this.organization = organization;
		this.template = new RestTemplate();
	}

	public Room getRoom(String roomCode) {
		return template.getForObject(roomURL + "?room_code={1}&organization={2}", Room.class, roomCode, organization);
	}

	public ServiceCall getServiceCall(String roomCode) {
		return template.getForObject(serviceCallURL + "?room_code={1}&organization={2}", ServiceCall.class, roomCode, organization);
	}

	public void setServiceCall(Session session, String roomCode, String state) {
		String sessionId = (session != null) ? session.getSession() : null;
		template.put(serviceCallURL + "?session={1}&room_code={2}&state={3}&organization={4}",
				null, sessionId, roomCode, state, organization);
	}
}
