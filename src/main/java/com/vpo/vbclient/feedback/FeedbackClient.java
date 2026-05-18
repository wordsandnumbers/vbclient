package com.vpo.vbclient.feedback;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vpo.vbclient.model.Session;

public class FeedbackClient {

	private static final String BASE_FEEDBACK_URL = "/api/v1/feedback";
	private static final String BASE_PROMPTS_URL = "/api/v1/feedback/prompts";
	private static final String DEFAULT_ROOT_URL = "https://voiceboxpdx.com";

	private RestTemplate template;
	private String feedbackURL;
	private String promptsURL;
	private String organization = null;

	public FeedbackClient() {
		this(null, null);
	}

	public FeedbackClient(String root, String organization) {
		super();
		String base = (root != null) ? root : DEFAULT_ROOT_URL;
		this.feedbackURL = base + BASE_FEEDBACK_URL;
		this.promptsURL = base + BASE_PROMPTS_URL;
		this.organization = organization;
		this.template = new RestTemplate();
	}

	public List<FeedbackPrompt> getPrompts(Session session, String roomCode) {
		String sessionId = (session != null) ? session.getSession() : null;
		FeedbackPromptList result = template.getForObject(
				promptsURL + "?session={1}&room_code={2}&organization={3}",
				FeedbackPromptList.class, sessionId, roomCode, organization);
		if (result == null) {
			return null;
		}
		return result.getPrompts();
	}

	public void submit(Session session, String roomCode, List<FeedbackResponse> responses) {
		String sessionId = (session != null) ? session.getSession() : null;
		FeedbackSubmission body = new FeedbackSubmission(responses);
		template.postForObject(
				feedbackURL + "?session={1}&room_code={2}&organization={3}",
				body, Void.class, sessionId, roomCode, organization);
	}
}
