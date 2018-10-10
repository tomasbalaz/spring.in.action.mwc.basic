package spring.in.action.mwc.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.in.action.mwc.basic.model.Spittle;
import spring.in.action.mwc.basic.service.SpittleRepository;

@Controller
@RequestMapping("spittles")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	// http://localhost:8080/mwc.basic/spittles
	@RequestMapping(value = "/v1", method = RequestMethod.GET)
	public String spittles(Model model) {

		// 1.
		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		// 2.
		// model.addAttribute("spittleList",
		// spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		// 3.
		// model.put("spittleList",spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		// 4.
		// return spittleRepository.findSpittles(Long.MAX_VALUE, 20));

		return "spittles";
	}

	@RequestMapping(value = "/v2", method = RequestMethod.GET)
	public List<Spittle> spittles(
			// 1.
			// @RequestParam("max") long max,
			// @RequestParam("count") int count) {

			// 2.
			// @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "max", defaultValue = "20") long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {

		return spittleRepository.findSpittles(max, count);
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showSpittle(@RequestParam("spittle_id") long spittleId, Model model) {

		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

	@RequestMapping(value = "/v3/{spittleId}", method = RequestMethod.GET)
	public String spittleV3(@PathVariable("spittleId") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

	@RequestMapping(value = "/v4/{spittleId}", method = RequestMethod.GET)
	public String spittleV4(@PathVariable long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spittle spitter, Errors errors) {
		
		if (errors.hasErrors()) {
			return "registerForm";
		}
		spittleRepository.save(spitter);
		return "redirect:/spitter/" + spitter.getMessage();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		Spittle spitter = spittleRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}
}
