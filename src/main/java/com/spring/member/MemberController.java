package com.spring.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.Member;
import com.spring.model.MemberService;

@Controller
public class MemberController {

	@Inject
	private MemberService service;
	
	
	@RequestMapping("member_list.go")
	public String list(Model model) {
		
		List<Member> memberList = this.service.list();
		
		model.addAttribute("MemberList", memberList);
		
		return "member/member_list";
	}
	
	@RequestMapping("member_insert.go")
	public String insert() {
		
		return "member/member_insert";
	}
	
	@RequestMapping("member_insert_ok.go")
	public void insertOk(Member dto,HttpServletResponse response) throws IOException {
		
		int check = this.service.insert(dto);
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원 등록 성공!!')");
			out.println("location.href='member_list.go'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("member_content.go")
	public String cont(@RequestParam("num")int no,Model model) {
		
		//회원의 상세 정보를 조회하는 메서드 호출
		Member cont = this.service.member(no);
		
		model.addAttribute("Content", cont);
		
		return "member/member_content";
	}
	
	@RequestMapping("member_modify.go")
	public String modify(@RequestParam("num")int no,Model model) {
		
		Member modify = this.service.member(no);
		
		model.addAttribute("Modify", modify);
		
		return "member/member_modify";
	}
	
	@RequestMapping("member_modify_ok.go")
	public void modifyOk(Member dto,HttpServletResponse response) throws IOException {
		
		int check = this.service.update(dto);
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
	         out.println("<script>");
	         out.println("alert('회원 수정 성공!!')");
	         out.println("location.href = 'member_content.go?num=" + dto.getMemno() + "'");
	         out.println("</script>");
	         
	      } else if(check == -1){
	         out.println("<script>");
	         out.println("alert('비밀번호가 틀립니다. 확인해주세요!!')");
	         out.println("history.back()");
	         out.println("</script>");
	      } else {
	         out.println("<script>");
	         out.println("alert('회원 수정 실패..')");
	         out.println("history.back()");
	         out.println("</script>");
	      }
	
    }
	
	@RequestMapping("member_delete.go")
	public String delete(@RequestParam("num") int no,Model model) {
		
		model.addAttribute("No", no);
		
		return "member/member_delete";
		
	}
	
	@RequestMapping("member_delete_ok.go")
	public void ok(@RequestParam("num") int no,@RequestParam("num") String pwd,HttpServletResponse response ) throws IOException {
		
		Member cont = this.service.member(no);
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(cont.getMempwd().equals(pwd)) {
			
			int check = this.service.delete(no);
			
			if(check > 0) {
				//삭제 시 회원번호 재작업 메서드 호출
				this.service.sequence(no);
				
				out.println("<script>");
				out.println("alert('회원 삭제 성공')");
				out.println("location.href='member_list'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('회원 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		}else {
			// 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.확인 요망')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}
}
