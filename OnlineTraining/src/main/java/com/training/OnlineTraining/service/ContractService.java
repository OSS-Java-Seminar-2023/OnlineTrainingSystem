package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    @Autowired
    public ContractService(ContractRepository contractRepository){
        this.contractRepository = contractRepository;
    }
}
