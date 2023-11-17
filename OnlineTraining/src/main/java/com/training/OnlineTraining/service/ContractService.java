package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

}
