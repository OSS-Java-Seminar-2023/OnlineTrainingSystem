package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.ContractDto;
import com.training.OnlineTraining.model.Contract;
import com.training.OnlineTraining.repository.ContractRepository;
import com.training.OnlineTraining.service.ContractService;
import com.training.OnlineTraining.mapper.ContractMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;


    public Contract createContract(ContractDto contractDto) {
        Contract contract = contractMapper.mapDto(contractDto);
        contractRepository.save(contract);
        return contract;
    }

}
