package com.twitterkaraf.api.helper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * Class for Junit testing for authorization
 * @author manasi sadanand pai
 */

@RunWith(JUnit4.class)
public class TwitterClientTest extends Mockito  {

	@InjectMocks
	private TwitterClient twitterClient;
	
	private OAuthService oAuthService;
	
	@Before
	public void init() {
		oAuthService = Mockito.mock(OAuthService.class);
		twitterClient = new TwitterClient(oAuthService);
	}
	
	
	@Test
	public void testGetSearchTweetsSF() {
		oAuthService.signRequest(Mockito.any(Token.class), Mockito.any(OAuthRequest.class));
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getBody()).thenReturn("test");
		String tweets = twitterClient.getSearchTweetsSF();
		Assert.assertNotNull(tweets);
	}

	@Test
	public void testCreateTweet() {
		oAuthService.signRequest(Mockito.any(Token.class), Mockito.any(OAuthRequest.class));
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getBody()).thenReturn("test");
		String tweets = twitterClient.createTweet("Test Message");
		Assert.assertNotNull(tweets);
	}

	@Test
	public void testGetMyTweets() {
		oAuthService.signRequest(Mockito.any(Token.class), Mockito.any(OAuthRequest.class));
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getBody()).thenReturn("test");
		String tweets = twitterClient.getMyTweets();
		Assert.assertNotNull(tweets);
	}

	@Test
	public void testDeleteMyTweet() {
		oAuthService.signRequest(Mockito.any(Token.class), Mockito.any(OAuthRequest.class));
		Response response = Mockito.mock(Response.class);
		Mockito.when(response.getBody()).thenReturn("test");
		String tweets = twitterClient.deleteMyTweet(1212l);
		Assert.assertNotNull(tweets);
	}

}
