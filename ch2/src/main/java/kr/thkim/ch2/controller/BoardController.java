package kr.thkim.ch2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if (!loginCheck(request)) {
			return "redirect:/login/login?toURL="+request.getRequestURL();
		}
		
		return "boardList";
	}

	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션 확인
		HttpSession session = request.getSession();
		
//		if (session.getAttribute("id")!=null) {
//			return true;
//		}
//		else {
//			return false;
//		}
		
		return session.getAttribute("id")!=null;
	}
}
