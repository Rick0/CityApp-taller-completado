package com.uba.fi.bdd.cityapp.service;

import com.uba.fi.bdd.cityapp.repository.AtmRepository;
import com.uba.fi.bdd.cityapp.repository.BankRepository;
import com.uba.fi.bdd.cityapp.repository.CommuneRepository;
import com.uba.fi.bdd.cityapp.repository.NeighbourhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TablesCountService {

	private final NeighbourhoodRepository neighbourhoodRepository;
	private final CommuneRepository communeRepository;
	private final BankRepository bankRepository;
	private final AtmRepository atmRepository;

	@Autowired
	public TablesCountService(NeighbourhoodRepository neighbourhoodRepository, CommuneRepository communeRepository,
							  BankRepository bankRepository, AtmRepository atmRepository) {
		this.neighbourhoodRepository = neighbourhoodRepository;
		this.communeRepository = communeRepository;
		this.bankRepository = bankRepository;
		this.atmRepository = atmRepository;
	}

	public Map<String, Integer> get() {
		Map<String, Integer> tablesCountMap = new LinkedHashMap<>();
		tablesCountMap.put(neighbourhoodRepository.getTableName(), neighbourhoodRepository.getCount());
		tablesCountMap.put(communeRepository.getTableName(), communeRepository.getCount());
		tablesCountMap.put(bankRepository.getTableName(), bankRepository.getCount());
		tablesCountMap.put(atmRepository.getTableName(), atmRepository.getCount());

		return tablesCountMap;
	}

}
