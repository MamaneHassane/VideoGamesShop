package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.AdminNotFoundException;
import com.videogamesshop.vgs_package.model.Admin;
import com.videogamesshop.vgs_package.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository){ this.adminRepository = adminRepository; }

    public void addAdmin(Admin admin){
        admin.setAdminCode(UUID.randomUUID().toString());
        adminRepository.save(admin);
    }
    public List<Admin> findAllAdmins(){
        return adminRepository.findAll();
    }

    public Admin findAdminById(Long Id){
        return adminRepository.findAdminById(Id)
                .orElseThrow(()->new AdminNotFoundException(Id));
    }

    public Admin updateAdminById(Admin updatedAdmin, Long Id){
        return adminRepository.findById(Id).map(
                admin -> {
                    admin.setAdminName(updatedAdmin.getAdminName());
                    admin.setAdminPassword(updatedAdmin.getAdminPassword());
                    admin.setRole(updatedAdmin.getRole());
                    return adminRepository.save(admin);
                }
        ).orElseThrow(()->new AdminNotFoundException(Id));
    }

    public void deleteAdminById(Long Id){
        adminRepository.deleteAdminById(Id);
    }
}


