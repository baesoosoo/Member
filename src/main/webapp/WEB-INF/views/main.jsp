<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>

		<div align="center">
			<hr width="30%" color="gray">
				<h3>MEMBER 테이블 메인 페이지</h3>
			<hr width="30%" color="gray">
			<br><br>
			
			<a href="<%=request.getContextPath()%>/member_list.go">[회원전체목록]</a>
			
		</div>
</body>
</html>
