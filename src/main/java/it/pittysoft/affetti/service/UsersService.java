package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.model.UserRequest;
import it.pittysoft.affetti.model.UserResponse;
import it.pittysoft.affetti.repository.UsersRepository;


@Component
public class UsersService {
	
	private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }
    
    public UserResponse getUsers(UserRequest user) {
    	UserResponse response = new UserResponse();
    	response.setUtenti(usersRepository.findAll());
        return response;
    }
    
    public Users saveUser(Users users) {
    	return usersRepository.save(users);
    }

}
