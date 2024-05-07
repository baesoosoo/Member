package com.spring.model;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Inject
	private MemberDAO dao; //MemberDAOImpl이 주입되는 거임
	
	public List<Member> list(){
		
		return dao.getMemberList();
	}
	
	public int insert(Member dto) { 
		
		return dao.insertMember(dto);
	}
	
	public Member member(int no) {
		
		return dao.getMember(no);
	}
	
	public int update(Member dto) {
		
		return dao.updateMember(dto);
	}
	
	public int delete(int no) {
		
		return dao.deleteMember(no);
	}
	
	public void sequence(int no) {
		
		dao.updateSequence(no);
	}
	
	
}
