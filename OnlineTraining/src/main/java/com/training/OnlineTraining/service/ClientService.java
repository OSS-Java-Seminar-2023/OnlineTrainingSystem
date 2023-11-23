package com.training.OnlineTraining.service;

import com.training.OnlineTraining.model.Client;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ClientService {

    private final UserService userService;
    private final ClientRepository clientRepository;

    public void registerClient(Client client) {
        User user = userService.getUserById(client.getUser().getId());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        client.setUser(user);
        clientRepository.save(client);
    }
    public boolean isClient(User user) {
        return clientRepository.existsByUser(user);
    }
}
