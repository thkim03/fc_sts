package kr.thkim.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC6 {

	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result = " + result);
		ex.printStackTrace();
		return "yoilError";
	}

	@RequestMapping("/getYoilMVC6")
	public String main(Mydate date, BindingResult result) throws IOException {
		
		System.out.println("result = " + result);
		
		if (!isValid(date)) {
			return "yoilError";
		}
		
		return "yoil";

	}

	private boolean isValid(Mydate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(Mydate date) {
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