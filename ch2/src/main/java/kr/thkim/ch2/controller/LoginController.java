package kr.thkim.ch2.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/login")
	public String LoginForm() {
		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String Login(String id, String pwd, String toURL, boolean rememberId, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. id/pwd 확인
		if (!loginCheck(id, pwd)) {
			// 2-1. 틀리면 로그인폼으로
			String msg = URLEncoder.encode("id 또는 pwd를 확인하세요.", "UTF-8");
			return "redirect:/login/login?msg="+msg;
		}
		// 2-2. 맞으면 홈으로
		HttpSession session = request.getSession();	// 세션 가져오기
		session.setAttribute("id", id);				// 세션ID 저장
		if (rememberId) {
			Cookie cookie = new Cookie("id", id);	// 1. 쿠키 생성
			response.addCookie(cookie);				// 2. 쿠키 저장
		}
		else {
			// 쿠키 삭제
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
			// 3. 홈으로
		toURL = toURL==null || toURL.equals("") ? "/" : toURL;
		
		return "redirect:"+toURL;
	}

	private boolean loginCheck(String id, String pwd) {
		return "thkim".equals(id) && "1234".equals(pwd);
	}
}
