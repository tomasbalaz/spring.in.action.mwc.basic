package spring.in.action.mwc.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

	@RequestMapping(method=RequestMethod.GET)
	public String home() {
		return "home";
	}
}
