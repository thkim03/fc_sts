package kr.thkim.ch2.controller;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@RequestMapping(value="/register/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/save")
	public String save(User user, Model m) throws Exception  {
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 확인하세요.", "utf-8");
			m.addAttribute("msg", msg);
//			return "redirect:/register/add";
			return "forward:/register/add";
		}
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}
