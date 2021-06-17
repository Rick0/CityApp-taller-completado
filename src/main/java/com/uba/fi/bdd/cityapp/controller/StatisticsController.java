package com.uba.fi.bdd.cityapp.controller;

import com.uba.fi.bdd.cityapp.service.TablesCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {

	private final TablesCountService tablesCountService;

	@Autowired
	public StatisticsController(TablesCountService tablesCountService) {
		this.tablesCountService = tablesCountService;
	}

	@GetMapping({"/statistics"})
	public String getAllNeighbourhoods(Model model) {
		model.addAttribute("tablesCountMap", tablesCountService.get());
		return "info/statistics";
	}

}
