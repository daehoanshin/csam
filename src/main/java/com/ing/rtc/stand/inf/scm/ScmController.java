package com.ing.rtc.stand.inf.scm;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScmController {
	
	@RequestMapping("/harweb/servlet/custermg.ItsmToScmInterface")
	public ModelAndView scmResult() {
		ModelAndView mv = new ModelAndView("result");
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		String actionId = "";
		String chgId2 = "";
		String tasId = "";
		String pakId = "";
		String returnV = "Success";
		String relId2 = "";
		String userId = "";
		String returnMsg = "Success";
		
		modelMap.put("actionId", actionId);
		modelMap.put("chgId2", chgId2);
		modelMap.put("tasId", tasId);
		modelMap.put("pakId", pakId);
		modelMap.put("returnV", returnV);
		modelMap.put("relId2", relId2);
		modelMap.put("userId", userId);
		modelMap.put("returnMsg", returnMsg);
		
		mv.addAllObjects(modelMap);
		return mv;
	}
}
