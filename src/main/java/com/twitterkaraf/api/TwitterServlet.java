package com.twitterkaraf.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import com.twitterkaraf.api.helper.TwitterClient;

/**
 * A sample web app to call the twitter apis.
 * @author prathna hemanth
 *
 */
@WebServlet("/twitterapi")
public class TwitterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String twitterSt = null;
	private TwitterClient twitterClient = null;
	
    public TwitterServlet() {
        super();
        twitterClient = new TwitterClient();
    }

    public TwitterServlet(TwitterClient twitterClient) {
        this.twitterClient = twitterClient;
    }

    /**
     * To handle all the twitter Get Calls such as Login, Search tweets and Get tweets
     * @param request HttpServletRequest 
     * @param response HttpServletResponse
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Twitter goGet api is called RequestParameters ,  parameterMap = " + request.getParameterMap());
		// Login API call
		if (request.getParameter("login") != null) {
			login(response);
			
		// Search Tweet API call
		} else if (request.getParameter("searchTweetsSF") != null) {
			searchTweets(request, response);
		}
		// Get Tweet API call
		else if (request.getParameter("getMyTweets") != null) {
			getTweets(request, response);
		}
		else {
			System.out.println("Else condition");
		}
	}

	/**
     * To handle all the twitter Post Calls such as Create Tweet and Delete tweet
     * @param request HttpServletRequest 
     * @param response HttpServletResponse
     */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// To handle default tweet page
		if (request.getParameter("tweet") != null) {
			System.out.println("tweet: " + request.getParameter("tweet"));
			twitterClient.setAccToken(request);
			request.getRequestDispatcher("/createTweet.jsp").forward(request, response);
		// To call Create Tweet API
		} else if (request.getParameter("createTweet") != null && request.getParameter("status") != null) {
			createTweet(request, response);
		}
		// To call Delete Tweet API
		else if (request.getParameter("delete") != null)
		{
			deleteTweet(request, response);
		}
	}
	

	/**
	 * Delete the Tweet by providing the authentic token and tweetId
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteTweet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		twitterClient.setAccToken(request);
		Long tweetID = Long.valueOf(request.getParameter("tweetId"));
		twitterClient.deleteMyTweet(tweetID);
		request.setAttribute("tweets", twitterClient.getMyTweets());
		request.getRequestDispatcher("/getMyTweets.jsp").forward(request, response);
	}


	/**
	 * Post a tweet by providing the authentic token and message 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createTweet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		twitterClient.setAccToken(request);
		twitterSt = request.getParameter("status");
		twitterClient.createTweet(twitterSt);
		request.setAttribute("status", twitterSt);
		System.out.println("Status: " + request.getParameter("status"));
		request.getRequestDispatcher("/createTweet.jsp").forward(request, response);
	}
	
	
	/**
	 * Get my tweets by authentic token 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getTweets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		twitterClient.setAccToken(request);
		request.setAttribute("tweets", twitterClient.getMyTweets());
		request.getRequestDispatcher("/getMyTweets.jsp").forward(request, response);
	}


	/**
	 * Search the tweet
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchTweets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		twitterClient.setAccToken(request);
		request.setAttribute("searchTweetsSF", twitterClient.getSearchTweetsSF());
		request.getRequestDispatcher("/searchTweetsSF.jsp").forward(request, response);
	}
	
	/**
	 * Login via Oath
	 * @param response
	 * @throws 	IOException
	 */
	private void  login(HttpServletResponse response) throws IOException {
		OAuthService oath = twitterClient.getOAuth();
		Token reqTokenVl = twitterClient.getOAuthRequestToken(oath);
		String authorizationUrl = oath.getAuthorizationUrl(reqTokenVl);
		System.out.println("authorizationUrl:" + authorizationUrl);
		response.sendRedirect(response.encodeRedirectURL(authorizationUrl));
	}
	
	
}
