package com.uba.fi.bdd.cityapp.controller;

import com.uba.fi.bdd.cityapp.model.Bank;
import com.uba.fi.bdd.cityapp.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

	private final BankRepository bankRepository;

	@Autowired
	public BankController(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	@GetMapping({"/bank/list"})
	public String getAllNeighbourhoods(Model model) {
		return getBanksListData(model);
	}

	@GetMapping({"/bank/crud"})
	public String getCrudBank(@RequestParam(required = false) Integer bankId, Model model) {
		final Bank bank;
		if (bankId != null) {
			bank = bankRepository.get(bankId);
		} else {
			bank = new Bank();
		}
		model.addAttribute("bank", bank);
		return "bank/crud";
	}

	@PostMapping({"/bank/add"})
	public String addBank(@ModelAttribute Bank bank, Model model) {
		bankRepository.add(bank);
		model.addAttribute("bank", bank);
		return getBanksListData(model);
	}

	@PostMapping({"/bank/put"})
	public String putBank(@ModelAttribute Bank bank, Model model) {
		bankRepository.update(bank);
		return getBanksListData(model);
	}

	@PostMapping({"/bank/delete"})
	public String deleteBank(@RequestParam Integer bankId, Model model) {
		bankRepository.delete(bankId);
		return getBanksListData(model);
	}

	private String getBanksListData(Model model) {
		model.addAttribute("banks", bankRepository.getAll());
		return "bank/list";
	}
	
}
