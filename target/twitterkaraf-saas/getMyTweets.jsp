<%@ page language="java" import="org.json.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- author - rimzim thube -->
<style>
button {
    background-color: #ccc;
    border: 1px solid #eee;
    border-radius: 10px;
    color: #fefefe;
    font-size: 18px;
  }
  button:hover {
    background-color: #333;
  }
  button.active {
    background-color: #008cff;
    cursor: pointer;
  }
</style>
<title>My Tweets Posted</title>
</head>
<body>

<h3>Here are your last 5 tweets</h3>
	<table>
		<thead>
   			<tr>
   				<th>TimeStamp</th>
   				<th>Tweet</th>
   			</tr>
   		</thead>
   		<tbody>
   
	<%
	String tweetsStr = request.getAttribute("tweets").toString();
	JSONArray tweets = new JSONArray(tweetsStr);
	JSONObject tweet;
		for (int i = 0; i < tweets.length(); i++)
		{
			tweet = tweets.getJSONObject(i);
			%>
				<tr>	
					<td>
					<%=tweet.getString("created_at")%>
					</td>
					<td>
					<%=tweets.getJSONObject(i).getString("text")%>
					</td>
					<td>
					<form action="TwitterServlet" method="POST">
						<input type="submit" class="active" name="delete" value="delete" />
						<input type="hidden" name="tweetId" id="tweetId" value="<%=tweets.getJSONObject(i).getLong("id")%>" />
						<input type="hidden" name="oauth_verifier" id="oauth_verifier" value="<%=request.getParameter("oauth_verifier")%>" />
						<input type="hidden" name="oauth_token" id="oauth_token" value="<%=request.getParameter("oauth_token")%>" />
					</form>
					</td>
				</tr>
		<%}
	%>
	</tbody>
	<tfoot>
	<tr>
	<td>
	<%
   String homePage = "twitterAPICalls.jsp";
	%>
	<button class="active" value="HomePage" onClick="JavaScript:window.location='<%= homePage %>';">HomePage</button>
	 </td>
	</tr>
	</tfoot>
	</table>

</body>
</html>