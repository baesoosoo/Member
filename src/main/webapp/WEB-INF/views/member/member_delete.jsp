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
		<hr width="300%" color="gray">
			<h3>MEMBER 테이블 회원 삭제 폼 페이지</h3>
		<hr width="300%" color="gray">
		<br><br>
		
		<form method="post" action="<%=request.getContextPath()%>/member_delete_ok.go">
			<input type="hidden" name="num" value="${No }">
			<table border="1" width="400">
				<tr>
					<th>삭제할 회원 비밀번호</th>
					<td>
						<input type="password" name="pwd">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원 삭제">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시 작성">
					</td>
				</tr>
			
			</table>
		
		</form>
	
	</div>

</body>
</html>