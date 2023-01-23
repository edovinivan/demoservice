package ru.otr.demoservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otr.demoservice.entity.Users;
import ru.otr.demoservice.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public Optional<Users> get(long id){
        return usersRepository.findById(id);
    }

    public Optional<Users> findByIdkeycloakEquals(String idKeycloak){
        return usersRepository.findByIdkeycloakEquals(idKeycloak);
    }

    public Users save(Users users){
        return usersRepository.save(users);
    }

    public Users findOrSave(Users users){
        Optional<Users> byIdkeycloakEquals = findByIdkeycloakEquals(users.getIdkeycloak());
        if(!byIdkeycloakEquals.isPresent()){
            return save(users);
        } else {
            return byIdkeycloakEquals.get();
        }
    }

}
