package com.vpo.vbclient;

import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.vpo.vbclient.model.Play;
import com.vpo.vbclient.model.Queue;
import com.vpo.vbclient.model.Session;
import com.vpo.vbclient.model.Song;
import com.vpo.vbclient.queue.PlayRequest;
import com.vpo.vbclient.queue.QueueClient;
import com.vpo.vbclient.session.SessionClient;
import com.vpo.vbclient.song.LanguageList;
import com.vpo.vbclient.song.Search;
import com.vpo.vbclient.song.SongClient;
import com.vpo.vbclient.song.TagList;

import vbclient.Application;

@SpringBootTest(classes = Application.class)
public class ApplicationTests {

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();
	static final String TEST_ORG_ID = "00000000000000000000000000000000";
	static final String TEST_ROOM_CODE = "CQFW";

	String randomString( int len )
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ )
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void restTemplateTest() {
		RestTemplate template = new RestTemplate();
		String test = template.getForObject("http://www.google.com", String.class);
	}

	@Test
	public void testSongsClient() {
		SongClient client = new SongClient(null, TEST_ORG_ID);
		Song song = client.getSongById(68733);
		Assertions.assertTrue(song.getTitle().equals("Baby, One More Time"));
	}

	@Test
	public void testSearchClient() {
		SongClient client = new SongClient(null, TEST_ORG_ID);
		Search search = new Search("love");
		Search result = client.findSongs(search);
		Assertions.assertTrue(result.getTotalEntries() > 0);
	}

	@Test
	public void testSearchClientBrowse() {
		SongClient client = new SongClient(null, TEST_ORG_ID);
		Search search = new Search("Dep");
		search.setBrowse(true);
		search.setBy("artist");
		Search result = client.findSongs(search);
		Assertions.assertTrue(result.getTotalEntries() > 0);
	}

	@Test
	public void testPagingation() {
		SongClient client = new SongClient(null, TEST_ORG_ID);
		Search search = new Search();
		search.setQuery("gaga");
		search.setPerPage(1);
		Search result = client.findSongs(search);
		Assertions.assertTrue(result.hasMore());
		Search newResult = client.next(result);
		Assertions.assertTrue(newResult.getTotalEntries() > 0);
		Search reverse = client.prev(newResult);
		Assertions.assertTrue(reverse.getTotalEntries() > 0);
		Assertions.assertTrue(client.prev(reverse) == null);
	}

	@Test
	public void testWithTestOrg() {
		SongClient client = new SongClient(null, TEST_ORG_ID);
		Search search = new Search("start");
		Search result = client.findSongs(search);
		Assertions.assertTrue(result.getTotalEntries() > 0);
	}

	@Test
	public void testLights() {
		SessionClient client = new SessionClient(null, TEST_ORG_ID);
		client.controlLights(TEST_ROOM_CODE, 2);
	}

	@Test
	public void testSession() {
		SessionClient client = new SessionClient(null, TEST_ORG_ID);
		Session s = new Session();
		s.setEmail(randomString(15) + "@mailinator.com");
		s.setSession(UUID.randomUUID().toString());
		Session r = client.createSession(s);
		Session result = client.getSessionById(s.getSession());
		Assertions.assertEquals(r.getSession(), result.getSession());
		r.setHandle("Changed");
		Session c = client.updateSession(r);
		Assertions.assertEquals("Changed", c.getHandle());
		Assertions.assertEquals(r.getSession(), c.getSession());
		client.postPopup(c, TEST_ROOM_CODE, "Yo! I am a test!");
	}

	@Test
	public void testFavoritesAndHistory() {
		SessionClient client = new SessionClient(null, TEST_ORG_ID);
		Session s = new Session();
		s.setEmail(randomString(15) + "@mailinator.com");
		s.setSession(UUID.randomUUID().toString());
		Session r = client.createSession(s);
		SongClient searchClient = new SongClient(null, TEST_ORG_ID);
		Search search = new Search("start");
		search.setSession(s);
		Search result = searchClient.findSongs(search);
		searchClient.addFavorite(result.getSongs().get(0), s);
		Search favorites = searchClient.findSongs(new Search(s,true, false));
		Assertions.assertEquals(result.getSongs().get(0).getId(), favorites.getSongs().get(0).getId());
		searchClient.deleteFavorite(result.getSongs().get(0), s);
		favorites = searchClient.findSongs(new Search(s,true, false));
		Assertions.assertTrue(favorites.getSongs().size() == 0);
		Search history = searchClient.playHistory(new Search(s, false, true));
		Assertions.assertTrue(history.getSongs().size() == 0);
	}

	@Test
	public void testQueue() {
		QueueClient client = new QueueClient(null, TEST_ORG_ID);
		Queue queue = client.getQueue(TEST_ROOM_CODE);
		Assertions.assertNotNull(queue);
		SongClient searchClient = new SongClient(null, TEST_ORG_ID);
		Search search = new Search("start");
		Search result = searchClient.findSongs(search);
		PlayRequest request = new PlayRequest(TEST_ROOM_CODE,result.getSongs().get(0).getId());
		request.setAllowDuplicate(true);
		SessionClient sessionClient = new SessionClient(null, TEST_ORG_ID);
		Session s = new Session();
		s.setEmail(randomString(15) + "@mailinator.com");
		s.setSession(UUID.randomUUID().toString());
		Session sr = sessionClient.createSession(s);
		Play play = client.addSong(request);
		Assertions.assertNotNull(play);
		Play play2 = client.addSong(request, sr);
		System.out.println(play2);
		Assertions.assertNotNull(play2);
		Play play3 = client.addSong(request, sr);
		System.out.println(play3);
		Assertions.assertNotNull(play3);
		client.reorder(TEST_ROOM_CODE, "0", "1");

	}

	@Test
	public void testTags() {
		SongClient client = new SongClient(null, TEST_ORG_ID);
		TagList tags = client.tags();
		Assertions.assertTrue(tags.getCategories().size() > 0);
	}

	@Test
	public void testLanguages() {
		SongClient client = new SongClient(null, TEST_ORG_ID);
		LanguageList languages = client.languages();
		Assertions.assertTrue(languages.getLanguages().size() > 0);
	}

}
