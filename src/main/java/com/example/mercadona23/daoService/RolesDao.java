package com.example.mercadona23.daoService;

import com.example.mercadona23.model.Roles;
import com.example.mercadona23.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesDao {
    @Autowired
    RolesRepository rolesRepository;

    public List<Roles> getRoles(){return rolesRepository.findAll();}
    public Roles getRole(String roleName){return rolesRepository.findById(roleName).get();}
    public void addRole(Roles newEntry){rolesRepository.save(newEntry);}
    public void updateRoles(Roles update,String catName){}
    public void deleteRoles(String catName){}
}
