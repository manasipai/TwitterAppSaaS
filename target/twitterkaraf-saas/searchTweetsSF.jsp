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
<title>Search Tweets SF</title>
</head>
<body>
<h3>Tweets related to San Francisco </h3>
	<table>
		<thead>
	 		<tr>
	 			<th>UserName</th>
	 			<th>Tweet</th>
			</tr>
	   	</thead>
	   	<tbody>
	<%
	String tweetsSF = request.getAttribute("searchTweetsSF").toString();
	System.out.println(" Tweets recieved response=" + tweetsSF);
	JSONArray tweets = new JSONObject(tweetsSF).getJSONArray("statuses");
	JSONObject tweet;
		for (int i = 0; i < tweets.length(); i++)
		{
			tweet = tweets.getJSONObject(i);
			%><tr>
				<td>
					<%=tweet.getJSONObject("user").getString("name")%>
				</td>
				<td>
					<%=tweet.getString("text")%>
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