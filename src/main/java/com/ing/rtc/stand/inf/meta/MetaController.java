package com.ing.rtc.stand.inf.meta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MetaController {
	
	@RequestMapping("/harweb")
	public ModelAndView respose() {
		ModelAndView mv = new ModelAndView("meta");
		return mv;
	}
}