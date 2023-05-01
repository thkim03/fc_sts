package kr.thkim.ch2.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@InitBinder
	public void toDate(WebDataBinder binder) {
//		SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dt, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
	}
	
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/save")
	public String save(User user, BindingResult result, Model m) throws Exception  {
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 확인하세요.", "utf-8");
			m.addAttribute("msg", msg);
//			return "redirect:/register/add";
			return "forward:/add";
		}
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
