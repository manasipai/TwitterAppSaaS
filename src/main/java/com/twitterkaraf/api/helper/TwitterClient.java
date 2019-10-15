package com.twitterkaraf.api.helper;

import javax.servlet.http.HttpServletRequest;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Client class to call twitter apis
 * 
 * @author akanksha jaiswal
 */
public class TwitterClient {

	private Token reqTokenVl = null;
	private Token accTokenVl = null;
	private OAuthService oauths = null;

	// Needs to added by the developer
	private String APIKey = "---Yor API Key---";
	private String APISecret = "---Your API Secret---";

	public TwitterClient() {
	}

	public TwitterClient(OAuthService oauths) {
		this.oauths = oauths;
	}

	/**
	 * Invoke Search API
	 * 
	 * @return
	 */
	public String getSearchTweetsSF() {
		return twitterAPIInvoke(Verb.GET, TwitterURLS.SEARCH_TWEET_URL);

	}

	/**
	 * Invoke Create API
	 * 
	 * @return
	 */
	public String createTweet(String status) {
		System.out.println("tweet posted!");
		return twitterAPIInvoke(Verb.POST, TwitterURLS.POST_TWEET_URL + status);
	}

	/**
	 * Invoke Get Tweets
	 * 
	 * @return
	 */
	public String getMyTweets() {
		System.out.println("my tweets posted");
		return twitterAPIInvoke(Verb.GET, TwitterURLS.GET_TWEET_URL);
	}

	/**
	 * Invoke Delete Tweets
	 * 
	 * @return
	 */
	public String deleteMyTweet(Long deleteTwt) {
		System.out.println("delete tweet = " + deleteTwt);
		String deleteTweetAPI = TwitterURLS.DELETE_TWEET_URL + deleteTwt + ".json?id=" + deleteTwt;
		return twitterAPIInvoke(Verb.POST, deleteTweetAPI);
	}

	//// *************** Logic Related to Login  **************///
	public Token getOAuthRequestToken(OAuthService oauths) {
		reqTokenVl = oauths.getRequestToken();
		return reqTokenVl;
	}

	public OAuthService getOAuth() {
		oauths = new ServiceBuilder().provider(TwitterApi.class).apiKey(APIKey).apiSecret(APISecret)
				.callback(TwitterURLS.OATH_CALLBACK_URL).build();
		System.out.println("Response from oAuth: " + oauths);
		return oauths;
	}

	public void setAccToken(HttpServletRequest request) {
		Verifier authverifier = null;
		System.out.println("Request:" + request);
		if (request.getParameter("oauth_verifier") != null) {
			if (authverifier == null) {
				authverifier = new Verifier(request.getParameter("oauth_verifier"));
			}
			if (accTokenVl == null) {
				accTokenVl = oauths.getAccessToken(reqTokenVl, authverifier);
				;
			}
		} else {
			System.out.println("No oauth_verifier is present ");
		}
	}

	/**
	 * Utility method to call all the twitter apis
	 * 
	 * @param httpMethod
	 * @param url
	 * @return
	 */
	private String twitterAPIInvoke(Verb httpMethod, String url) {
		String responseBody = "";
		try {
			OAuthRequest request = new OAuthRequest(httpMethod, url);
			System.out.println("Response: " + request.getBodyContents().toString());
			System.out.println("Calling twitter API ..accTokenVl=" + accTokenVl + " url = " + url);
			oauths.signRequest(accTokenVl, request);
			Response response = request.send();
			System.out.println("Response: " + response.getBody());
			responseBody = response.getBody();

		} catch (Exception e) {
			System.out.println("Exception while serving twitter request error=" + e.getStackTrace().toString());
		}
		return responseBody;

	}
}
