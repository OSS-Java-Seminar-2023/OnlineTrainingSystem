package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.ClientDto;
import com.training.OnlineTraining.model.Client;
import com.training.OnlineTraining.model.Role;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.model.UserRole;
import com.training.OnlineTraining.repository.ClientRepository;
import com.training.OnlineTraining.repository.RoleRepository;
import com.training.OnlineTraining.repository.UserRoleRepository;
import com.training.OnlineTraining.service.ClientService;
import com.training.OnlineTraining.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final UserService userService;
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void registerClient(ClientDto clientDto, UUID userId) {
        Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(userId));

        optionalUser.ifPresentOrElse(
                user -> {
                    Client client = new Client();
                    client.setUser(user);
                    client.setMedicalCondition(clientDto.getMedicalCondition());
                    client.setInjuries(clientDto.getInjuries());
                    clientRepository.save(client);
                    Role roleClient = roleRepository.findByName("CLIENT")
                            .orElseThrow(() -> new RuntimeException("Role not found: CLIENT"));
                    userRoleRepository.save(new UserRole(user, roleClient));
                },
                () -> {
                    throw new RuntimeException("User not found");
                }
        );
    }
    @Override
    public boolean isClient(User user) {
        return clientRepository.existsByUser(user);
    }

    @Override
    public Client getClientByUserId(UUID userId) {
        return clientRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
