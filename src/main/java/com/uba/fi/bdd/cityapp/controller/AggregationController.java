package com.uba.fi.bdd.cityapp.controller;

import com.uba.fi.bdd.cityapp.repository.AggregationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AggregationController {

	private final AggregationRepository aggregationRepository;

	@Autowired
	public AggregationController(AggregationRepository aggregationRepository) {
		this.aggregationRepository = aggregationRepository;
	}

	@GetMapping({"/aggregation"})
	public String getAllNeighbourhoods(Model model) {
		model.addAttribute("aggregationQueryResult1", aggregationRepository.getAggregationQuery1());
		model.addAttribute("aggregationQueryResult2", aggregationRepository.getAggregationQuery2());
		return "info/aggregation";
	}

}
