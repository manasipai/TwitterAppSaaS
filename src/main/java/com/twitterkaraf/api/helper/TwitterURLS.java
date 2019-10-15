package com.twitterkaraf.api.helper;

/**
 * Class to maintain all the Twitter API urls
 * @author rimzim thube
 */
public class TwitterURLS {

	public static String SEARCH_TWEET_URL = "https://api.twitter.com/1.1/search/tweets.json?q=San%20Francisco"; 
	public static String POST_TWEET_URL = "https://api.twitter.com/1.1/statuses/update.json?status=";
	public static String GET_TWEET_URL = "https://api.twitter.com/1.1/statuses/user_timeline.json";
	public static String DELETE_TWEET_URL= "https://api.twitter.com/1.1/statuses/destroy/";
	public static String OATH_CALLBACK_URL = "http://localhost:8080/twitterkaraf-saas/twitterAPICalls.jsp";
	
}
