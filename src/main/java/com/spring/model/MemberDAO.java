package com.spring.model;

import java.util.List;

public interface MemberDAO {
	
	// 회원 전체 리스트를 호출하는 추상 메서드
	List<Member> getMemberList();
	
	// 회원을 추가하는 추상 메서드
	int insertMember(Member dto);
	
	// 한 회원의 정보를 호출하는 추상 메서드
	Member getMember(int memno);
	
	// 한 회원의 정보를 수정하는 추상 메서드
	int updateMember(Member dto);
	
	// 한 회원을 삭제할 추상 메서드
	int deleteMember(int memno);
	
	// 회원번호를 재작업하는 추상 메서드
	void updateSequence(int memno);

}
