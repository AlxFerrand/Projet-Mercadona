package com.example.mercadona23.daoService;

import com.example.mercadona23.model.Users;
import com.example.mercadona23.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersDao {
    @Autowired
    UsersRepository usersRepository;

    public List<Users> getUsers(){return usersRepository.findAll();}
    public List<Users> getUsersByUserRole(String userRole){return usersRepository.findAllByUserRole(userRole);}
    public Users getUser(Long id){return usersRepository.findById(id).get();}
    public Users getUserByUserName(String userName){return usersRepository.findUsersByUserName(userName);}
    public void addUser(Users newEntry){usersRepository.save(newEntry);}
    public void updateUsers(Users update,Long id){}
    public void deleteUsers(Long id){}
}
