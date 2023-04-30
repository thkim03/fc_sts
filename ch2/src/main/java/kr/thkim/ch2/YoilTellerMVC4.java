package kr.thkim.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC4 {

	@RequestMapping("/getYoilMVC4")
	//public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	public String main(Mydate date, Model model) throws IOException {

		
		
		if (!isValid(date)) {
			return "yoilError";
		}
		
		char yoil = getYoil(date);
		
		model.addAttribute("myDate", date);
		model.addAttribute("yoil", yoil);
		return "yoil";

	}

	private boolean isValid(Mydate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(Mydate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private boolean isValid(int year, int month, int day) {
		if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31);
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		  cal.set(year, month - 1, day);

		  int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		  return " 일월화수목금토".charAt(dayOfWeek);   // 일요일:1, 월요일:2, ... 
	}
}