package com.twitterkaraf.api;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import com.twitterkaraf.api.helper.TwitterClient;

/**
 * Class for Junit testing for servlet
 * @author manasi sadanand pai
 */

@RunWith(JUnit4.class)
public class TwitterServletTest extends Mockito {

	@InjectMocks
	private TwitterServlet twitterServlet;
	
	private TwitterClient twitterClient;
	
	@Before
	public void init() {
		 twitterClient = Mockito.mock(TwitterClient.class);
		twitterServlet = new TwitterServlet(twitterClient);
	}
	
	@Test
	public void testDoGet_login() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(request.getParameter("login")).thenReturn("login");
		OAuthService oauth = Mockito.mock(OAuthService.class);
		Mockito.when(twitterClient.getOAuth()).thenReturn(oauth);
		Token token = Mockito.mock(Token.class);
		Mockito.when(twitterClient.getOAuthRequestToken(oauth)).thenReturn(token);
		String url = "http://localhost:8080/test";
		Mockito.when(oauth.getAuthorizationUrl(token)).thenReturn(url);
		response.sendRedirect(response.encodeRedirectURL(url));
		twitterServlet.doGet(request, response);
		verify(request, atLeast(1)).getParameter("login"); 
	}

	@Test
	public void testDoGet_search() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(request.getParameter("searchTweetsSF")).thenReturn("searchTweetsSF");
		Mockito.when(twitterClient.getSearchTweetsSF()).thenReturn("test, test1");
		RequestDispatcher value  = Mockito.mock(RequestDispatcher.class);
		Mockito.when(request.getRequestDispatcher("/searchTweetsSF.jsp")).thenReturn(value);
		value.forward(request, response);
		twitterServlet.doGet(request, response);
		verify(request, atLeast(1)).getParameter("searchTweetsSF"); 
	}
	
	@Test
	public void testDoGet_getmytweets() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(request.getParameter("getMyTweets")).thenReturn("getMyTweets");
		Mockito.when(twitterClient.getMyTweets()).thenReturn("test, test1");
		RequestDispatcher value  = Mockito.mock(RequestDispatcher.class);
		Mockito.when(request.getRequestDispatcher("/getMyTweets.jsp")).thenReturn(value);
		value.forward(request, response);
		twitterServlet.doGet(request, response);
		verify(request, atLeast(1)).getParameter("getMyTweets"); 
	}
	
	@Test
	public void testDoPost_create() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(request.getParameter("tweet")).thenReturn("tweet");
		twitterClient.setAccToken(request);
		RequestDispatcher value  = Mockito.mock(RequestDispatcher.class);
		Mockito.when(request.getRequestDispatcher("/createTweet.jsp")).thenReturn(value);
		value.forward(request, response);
		twitterServlet.doPost(request, response);
		verify(request, atLeast(1)).getParameter("tweet"); 
	}
	
	@Test
	public void testDoPost_delete() throws IOException, ServletException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Long tweetId = 12321l;
		Mockito.when(request.getParameter("delete")).thenReturn(String.valueOf("delete"));
		Mockito.when(request.getParameter("tweetId")).thenReturn(String.valueOf(tweetId));
		twitterClient.setAccToken(request);
		twitterClient.deleteMyTweet(tweetId);
		Mockito.when(request.getParameter("getMyTweets")).thenReturn("getMyTweets");
		RequestDispatcher value  = Mockito.mock(RequestDispatcher.class);
		Mockito.when(request.getRequestDispatcher("/getMyTweets.jsp")).thenReturn(value);
		value.forward(request, response);
		twitterServlet.doPost(request, response);
		verify(request, atLeast(1)).getParameter("tweetId"); 
	}
}
