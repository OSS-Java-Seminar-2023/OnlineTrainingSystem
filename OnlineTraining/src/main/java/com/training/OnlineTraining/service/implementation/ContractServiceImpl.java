package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.repository.ContractRepository;
import com.training.OnlineTraining.service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

}
