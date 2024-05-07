<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<c:set var="dto" value="${Content }"/>
		<hr width="30%" color="red">
			<h3>${dto.memname }회원 상세 정보 페이지</h3>
		<hr width="30%" color="red">
		<br><br>
		
		<table border="1" width="450">
		 <tr>
		 	<th>회원 No.</th>
		 	<td>${dto.memno }</td>
		 </tr>
		 
		  <tr>
		 	<th>회원 아이디</th>
		 	<td>${dto.memid }</td>
		 </tr>
		 
		  <tr>
		 	<th>회원 이름</th>
		 	<td>${dto.memname }</td>
		 </tr>
		 
		  <tr>
		 	<th>회원 비밀번호</th>
		 	<td>
		 		<c:if test="${!empty dto.mempwd }">
		 			<c:forEach begin="1" end="${dto.mempwd.length() }">
		 				*
		 			</c:forEach>
		 		</c:if>
		 	</td>
		 </tr>
		
		 <tr>
		 	<th>회원 나이</th>
		 	<td>${dto.age }</td>
		 </tr>
		 
		  <tr>
		 	<th>회원 마일리지</th>
		 	<td>
		 		<fmt:formatNumber value="${dto.mileage }"/>마일리지
		 	</td>
		 </tr>
		 
		  <tr>
		 	<th>회원 직업</th>
		 	<td>${dto.job }</td>
		 </tr>
		 
		  <tr>
		 	<th>회원 주소</th>
		 	<td>${dto.addr }</td>
		 </tr>
		 
		  <tr>
		 	<th>회원 가입일</th>
		 	<td>${dto.regdate }</td>
		 </tr>
		
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>회원에 대한 정보가 없습니다</h3>
					</td>
				</tr>
			</c:if>
		</table>
		<br><br>
		<input type="button" value="회원수정" onclick="location.href='member_modify.go?num=${dto.memno}'">
		<input type="button" value="회원삭제" onclick="if(confirm('정말로 회원을 삭제하시겠습니까?')){location.href='member_delete.go.go?num=${dto.memno}'
		}else{return;}">
		<input type="button" value="회원목록" onclick="location.href='member_list.go'">
		
		
	</div>

</body>
</html>