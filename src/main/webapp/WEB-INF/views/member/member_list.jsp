<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div align="center">
			<hr width="30%" color="red">
				<h3>MEMBER 테이블 회원 전체 리스트 페이지</h3>
			<hr width="30%" color="red">
			<br><br>
			
			<table border="1" width="450">
			<tr>
				<th>회원 No.</th> <th>회원 이름</th>
				<th>회원 직업</th> <th>회원 가입일</th>
			</tr>
			
			<c:set var="list" value="${MemberList}"/>
			<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
			 <tr>
				<td>${dto.getMemno() }</td>
				<td>
					<a href="<%=request.getContextPath() %>/member_content.go?num=${dto.getMemno()}">
					${dto.getMemname() }</a></td>
				<td>${dto.getJob() }</td>
				<td>${dto.getRegdate().substring(0,10) }</td>
			 </tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty list }">
			<tr>
				<td colspan="4" align="center">
					<h3>전체 회원 리스트가 없습니다.</h3>
				</td>
			</tr>
		</c:if>
		</table>
		<br><br>
		
		<input type="button" value="회원등록" onclick="location.href='member_insert.go'">
	</div>

</body>
</html>