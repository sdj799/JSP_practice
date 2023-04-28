<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<th>앨범커버</th>
				<th>가수</th>
				<th>앨범 제목</th>
				<th>발매일</th>
			</tr>
			<tr>
				<td><img alt="안유진" src="./안유진.png" style= "width: 100px; height: 100px;" ></td>
				<td>안유진</td>
				<td>
					<a href="req_album_result.jsp?choice=안유진"> I AM </a>
				</td>
				<td>2023.04.10</td>
			</tr>
			<tr>
				<td><img alt="장원영" src="./장원영.jpg" style= "width: 100px; height: 100px;"></td>
				<td>장원영</td>
				<td>
					<a href="req_album_result.jsp?choice=장원영"> After Like </a>
				</td>
				<td>2022.08.22</td>
			</tr>
		</table>
	</div>
</body>
</html>