package com.training.OnlineTraining.service;


import com.training.OnlineTraining.dto.ClientDto;
import com.training.OnlineTraining.model.User;

import java.util.UUID;

public interface ClientService {
    void registerClient(ClientDto clientDto, UUID userId);
    boolean isClient(User user);
}
