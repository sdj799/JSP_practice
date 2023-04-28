<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    	request.setCharacterEncoding("utf-8");
    	String choice = request.getParameter("choice");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<h2>선택하신 앨범 정보</h2>
	
	<hr>
	
	<p>당신이 선택하신 앨범은 <%=choice %>의 
	<%if(choice.equals("안유진")) {%>
		I AM
	<% } else {%>
		After Like
	<%} %> 입니다.
	 </p>
	
	<hr>
	
	<h3>타이틀 곡 뮤직 비디오</h3>
	<%if(choice.equals("안유진")) {%>
		<iframe width="800" height="600" src="https://www.youtube.com/embed/6ZUIwj3FgUY?autoplay=1" title="IVE 아이브 &#39;I AM&#39; MV" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
	<% } else {%>
		<iframe width="800" height="600" src="https://www.youtube.com/embed/F0B7HDiY-10?autoplay=1" title="IVE 아이브 &#39;After LIKE&#39; MV" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
	<%} %>
	</div>
</body>
</html>