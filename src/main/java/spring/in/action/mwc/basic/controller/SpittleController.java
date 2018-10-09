package spring.in.action.mwc.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.in.action.mwc.basic.service.SpittleRepository;

@Controller
@RequestMapping("spittles")
public class SpittleController {

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {

		// 1.
		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		// 2.
		// model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		// 3.
		// model.put("spittleList",spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		
		// 4.
		// return spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		return "spittles";
	}

}
