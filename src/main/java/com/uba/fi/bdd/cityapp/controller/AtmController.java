package com.uba.fi.bdd.cityapp.controller;

import com.uba.fi.bdd.cityapp.repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AtmController {

	private final AtmRepository atmRepository;

	@Autowired
	public AtmController(AtmRepository atmRepository) {
		this.atmRepository = atmRepository;
	}

	@GetMapping({"/atm/list"})
	public String getAllNeighbourhoods(Model model, @RequestParam("page") Optional<Integer> maybePage) {
		final int page = Math.max(maybePage.orElse(0), 0);
		model.addAttribute("atms", atmRepository.get(20, page));
		model.addAttribute("page", page);
		return "atm/list";
	}

}
