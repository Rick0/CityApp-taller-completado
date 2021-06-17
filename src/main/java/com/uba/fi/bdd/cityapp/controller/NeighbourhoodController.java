package com.uba.fi.bdd.cityapp.controller;

import com.uba.fi.bdd.cityapp.model.Neighbourhood;
import com.uba.fi.bdd.cityapp.repository.NeighbourhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NeighbourhoodController {

	private final NeighbourhoodRepository neighbourhoodRepository;

	@Autowired
	public NeighbourhoodController(NeighbourhoodRepository neighbourhoodRepository) {
		this.neighbourhoodRepository = neighbourhoodRepository;
	}

	@GetMapping({"/neighbourhood/list"})
	public String getNeighbourhoodsList(Model model) {
		return getNeighbourhoodsListData(model);
	}

	@GetMapping({"/neighbourhood/crud"})
	public String getCrudNeighbourhood(@RequestParam(required = false) String neighbourhoodName, Model model) {
		final Neighbourhood neighbourhood;
		if (neighbourhoodName != null && !neighbourhoodName.isBlank()) {
			neighbourhood = neighbourhoodRepository.get(neighbourhoodName);
		} else {
			neighbourhood = new Neighbourhood();
		}
		model.addAttribute("neighbourhood", neighbourhood);
		return "neighbourhood/crud";
	}

	@PostMapping({"/neighbourhood/add"})
	public String addNeighbourhood(@ModelAttribute Neighbourhood neighbourhood, Model model) {
		neighbourhoodRepository.add(neighbourhood);
		model.addAttribute("neighbourhood", neighbourhood);
		return getNeighbourhoodsListData(model);
	}

	@PostMapping({"/neighbourhood/put"})
	public String putNeighbourhood(@ModelAttribute Neighbourhood neighbourhood, Model model) {
		neighbourhoodRepository.update(neighbourhood);
		return getNeighbourhoodsListData(model);
	}

	@PostMapping({"/neighbourhood/delete"})
	public String deleteNeighbourhood(@RequestParam String neighbourhoodName, Model model) {
		neighbourhoodRepository.delete(neighbourhoodName);
		return getNeighbourhoodsListData(model);
	}

	private String getNeighbourhoodsListData(Model model) {
		model.addAttribute("neighbourhoods", neighbourhoodRepository.getAll());
		return "neighbourhood/list";
	}

}
