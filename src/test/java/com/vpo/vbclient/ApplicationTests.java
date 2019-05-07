package com.vpo.vbclient;

import java.util.Random;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

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
		SongClient client = new SongClient(null, "00000000000000000000000000000000");
		Song song = client.getSongById(68733);
		Assert.assertTrue(song.getTitle().equals("Baby, One More Time"));
	}
	
	@Test
	public void testSearchClient() {
		SongClient client = new SongClient(null, "00000000000000000000000000000000");
		Search search = new Search("love");
		Search result = client.findSongs(search);
		Assert.assertTrue(result.getTotalEntries() > 0);
	}
	
	@Test
	public void testSearchClientBrowse() {
		SongClient client = new SongClient(null, "00000000000000000000000000000000");
		Search search = new Search("Dep");
		search.setBrowse(true);
		search.setBy("artist");
		Search result = client.findSongs(search);
		Assert.assertTrue(result.getTotalEntries() > 0);
	}
	
	@Test
	public void testPagingation() {
		SongClient client = new SongClient(null, "00000000000000000000000000000000");
		Search search = new Search();
		search.setQuery("gaga");
		search.setPerPage(1);
		Search result = client.findSongs(search);
		Assert.assertTrue(result.hasMore());
		Search newResult = client.next(result);
		Assert.assertTrue(newResult.getTotalEntries() > 0);
		Search reverse = client.prev(newResult);
		Assert.assertTrue(reverse.getTotalEntries() > 0);
		Assert.assertTrue(client.prev(reverse) == null);
	}
	
	@Test
	public void testWithTestOrg() {
		SongClient client = new SongClient(null, "00000000000000000000000000000000");
		Search search = new Search("start");
		Search result = client.findSongs(search);
		Assert.assertTrue(result.getTotalEntries() > 0);
	}
	
	@Test
	public void testLights() {
		SessionClient client = new SessionClient(null, "00000000000000000000000000000000");
		client.controlLights("HSCL", 2);
	}
	
	@Test
	public void testSession() {
		SessionClient client = new SessionClient(null, "00000000000000000000000000000000");
		Session s = new Session();
		s.setEmail(randomString(15) + "@mailinator.com");
		s.setSession(UUID.randomUUID().toString());
		Session r = client.createSession(s);
		Session result = client.getSessionById(s.getSession());
		Assert.assertEquals(r.getSession(), result.getSession());
		r.setHandle("Changed");
		Session c = client.updateSession(r);
		Assert.assertEquals("Changed", c.getHandle());
		Assert.assertEquals(r.getSession(), c.getSession());
		client.postPopup(c, "HSCL", "Yo! I am a test!");
	}
	
	@Test
	public void testFavoritesAndHistory() {
		SessionClient client = new SessionClient(null, "00000000000000000000000000000000");
		Session s = new Session();
		s.setEmail(randomString(15) + "@mailinator.com");
		s.setSession(UUID.randomUUID().toString());
		Session r = client.createSession(s);
		SongClient searchClient = new SongClient(null, "00000000000000000000000000000000");
		Search search = new Search("start");
		search.setSession(s);
		Search result = searchClient.findSongs(search);
		searchClient.addFavorite(result.getSongs().get(0), s);
		Search favorites = searchClient.findSongs(new Search(s,true, false));
		Assert.assertEquals(result.getSongs().get(0).getId(), favorites.getSongs().get(0).getId());
		searchClient.deleteFavorite(result.getSongs().get(0), s);
		favorites = searchClient.findSongs(new Search(s,true, false));
		Assert.assertTrue(favorites.getSongs().size() == 0);
		Search history = searchClient.playHistory(new Search(s, false, true));
		Assert.assertTrue(history.getSongs().size() == 0);
	}
	
	@Test
	public void testQueue() {
		QueueClient client = new QueueClient(null, "00000000000000000000000000000000");
		Queue queue = client.getQueue("HSCL");
		Assert.assertNotNull(queue);
		SongClient searchClient = new SongClient(null, "00000000000000000000000000000000");
		Search search = new Search("start");
		Search result = searchClient.findSongs(search);
		PlayRequest request = new PlayRequest("HSCL",result.getSongs().get(0).getId());
		request.setAllowDuplicate(true);
		SessionClient sessionClient = new SessionClient(null, "00000000000000000000000000000000");
		Session s = new Session();
		s.setEmail(randomString(15) + "@mailinator.com");
		s.setSession(UUID.randomUUID().toString());
		Session sr = sessionClient.createSession(s);
		Play play = client.addSong(request);
		Assert.assertNotNull(play != null);
		Play play2 = client.addSong(request, sr);
		System.out.println(play2);
		Assert.assertNotNull(play2 != null);
		Play play3 = client.addSong(request, sr);
		System.out.println(play3);
		Assert.assertNotNull(play3 != null);
		client.reorder("HSCL", "0", "1");

	}
	
	@Test
	public void testTags() {
		SongClient client = new SongClient(null, "00000000000000000000000000000000");
		TagList tags = client.tags();
		Assert.assertTrue(tags.getCategories().size() > 0);
	}
	
	@Test
	public void testLanguages() {
		SongClient client = new SongClient(null, "00000000000000000000000000000000");
		LanguageList languages = client.languages();
		Assert.assertTrue(languages.getLanguages().size() > 0);
	}
	
}
