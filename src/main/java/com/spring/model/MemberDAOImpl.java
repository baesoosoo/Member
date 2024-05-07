package com.spring.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class MemberDAOImpl implements MemberDAO{

	private JdbcTemplate template;
	
	public MemberDAOImpl(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource); //root-context에서 주입 받음
	}
	
	String sql = null;
	
	
	
	@Override
	public List<Member> getMemberList() {
	
		List<Member> list = null;
		
		sql = "select * from member order by memno desc";
		
		return list = this.template.query(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				//mapRow는 리스트 갯수(rowNum) 만큼 반복해서 리턴을 해줌. 리턴값은 list에 저장
				Member dto = new Member();
				
				dto.setMemno(rs.getInt("memno"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setMempwd(rs.getString("mempwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;
			}
		});
	}

	@Override
	public int insertMember(final Member dto) {
		
		sql="select max(memno) from member";
		
	   final int count = this.template.queryForInt(sql); //int 값의 값이 넘어옴
		
		sql="insert into member values(?,?,?,?,?,?,?,?,sysdate)";
		
		return this.template.update(sql, new PreparedStatementSetter() {
			//return은 리턴된 결과 값이 저장이 됨
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
			 ps.setInt(1, count+1); //중첩클래스,내부가 외부 접근 가능, 값 변동안되게 final붙여줘야함
			 ps.setString(2, dto.getMemid());
			 ps.setString(3, dto.getMemname());
			 ps.setString(4, dto.getMempwd());
			 ps.setInt(5, dto.getAge());
			 ps.setInt(6, dto.getMileage());
			 ps.setString(7, dto.getJob());
			 ps.setString(8, dto.getAddr());
				
			}
		});
	  
	}

	@Override
	public Member getMember(int memno) {
		
		sql="select * from member where memno=?";
		
		return this.template.queryForObject(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member dto = new Member();
				
				dto.setMemno(rs.getInt("memno"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setMempwd(rs.getString("mempwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;
			}
		}, memno);
	}

	@Override
	   public int updateMember(final Member dto) {
	      
	      sql = "select mempwd from member where memno = ?";
	      String db_pwd = this.template.queryForObject(sql, String.class, dto.getMemno());  // 특정 컬럼만 필요할 경우
	      
	      if(db_pwd.equals(dto.getMempwd())) {
	         sql = "update member set age = ?, mileage = ?, job = ?, addr = ? where memno = ?";
	         return this.template.update(sql, new PreparedStatementSetter() {
	            
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	               
	               ps.setInt(1, dto.getAge());
	               ps.setInt(2, dto.getMileage());
	               ps.setString(3, dto.getJob());
	               ps.setString(4, dto.getAddr());
	               ps.setInt(5, dto.getMemno());
	            }
	         });
	      }else {
	         // 비밀번호가 다른 경우
	         return -1;
	      }
	      
	   }

	@Override
	public int deleteMember(final int memno) {
		
		sql="delete from member where memno=?";
		
		return this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, memno);
				
			}
		});
	}

	@Override
	public void updateSequence(final int memno) {
		
		sql="update member set memno=memno-1 where memno > ?";
		
			this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, memno);
				
			}
		});
		
	}
	

}
