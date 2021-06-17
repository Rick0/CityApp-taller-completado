package com.uba.fi.bdd.cityapp.controller;

import com.uba.fi.bdd.cityapp.model.Commune;
import com.uba.fi.bdd.cityapp.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommuneController {

	private final CommuneRepository communeRepository;

	@Autowired
	public CommuneController(CommuneRepository communeRepository) {
		this.communeRepository = communeRepository;
	}

	@GetMapping({"/commune/list"})
	public String getCommunesList(Model model) {
		return getCommunesListData(model);
	}

	@GetMapping({"/commune/crud"})
	public String getCrudCommune(@RequestParam(required = false) Integer communeId, Model model) {
		final Commune commune;
		if (communeId != null) {
			commune = communeRepository.get(communeId);
		} else {
			commune = new Commune();
		}
		model.addAttribute("commune", commune);
		return "commune/crud";
	}

	@PostMapping({"/commune/add"})
	public String addCommune(@ModelAttribute Commune commune, Model model) {
		communeRepository.add(commune);
		model.addAttribute("commune", commune);
		return getCommunesListData(model);
	}

	@PostMapping({"/commune/put"})
	public String putCommune(@ModelAttribute Commune commune, Model model) {
		communeRepository.update(commune);
		return getCommunesListData(model);
	}

	@PostMapping({"/commune/delete"})
	public String deleteCommune(@RequestParam Integer communeId, Model model) {
		communeRepository.delete(communeId);
		return getCommunesListData(model);
	}

	private String getCommunesListData(Model model) {
		model.addAttribute("communes", communeRepository.getAll());
		return "commune/list";
	}

}
