package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.EmployeeNotFoundException;
import com.videogamesshop.vgs_package.exceptions.ShopNotFoundException;
import com.videogamesshop.vgs_package.model.Enums.Role;
import com.videogamesshop.vgs_package.model.entities.Employee;
import com.videogamesshop.vgs_package.model.entities.RoleInCareer;
import com.videogamesshop.vgs_package.model.entities.Shop;
import com.videogamesshop.vgs_package.model.records.AddEmployeeToShopRecord;
import com.videogamesshop.vgs_package.model.records.MutateEmployeeRecord;
import com.videogamesshop.vgs_package.repository.EmployeeRepository;
import com.videogamesshop.vgs_package.repository.RoleInCareerRepository;
import com.videogamesshop.vgs_package.repository.ShopRepository;
import com.videogamesshop.vgs_package.security.entities.UserInfo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.videogamesshop.vgs_package.service.helpers.ShopServiceHelper.getMutationMessage;

@Service
@Transactional
public class ShopService {
    private final ShopRepository shopRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleInCareerRepository roleInCareerRepository;
    @Autowired
    public ShopService(ShopRepository shopRepository, EmployeeRepository employeeRepository, RoleInCareerRepository roleInCareerRepository){
        this.shopRepository = shopRepository;
        this.employeeRepository = employeeRepository;
        this.roleInCareerRepository = roleInCareerRepository;
    }
    public void addShop(@NotNull Shop shop){
        shop.setRentsOfShop(new ArrayList<>());
        shopRepository.save(shop);
    }
    public List<Shop> findAllShops(){
        return shopRepository.findAll();
    }
    public Shop findShopById(Long Id){
        return shopRepository.findById(Id)
                             .orElseThrow(()-> new ShopNotFoundException(Id));
    }
    public List<RoleInCareer> findCurrentEmployeesOfShop(Long shopId){
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()-> new ShopNotFoundException(shopId));
        List<RoleInCareer> employeesWhoWorkedHere = shop.getEmployeesWhoWorkedHere();
        List<RoleInCareer> employeesWhoCurrentlyWorkHere = new ArrayList<>();
        for(RoleInCareer roleInCareer : employeesWhoWorkedHere) {
            if(roleInCareer.getUntil() == null)
                employeesWhoCurrentlyWorkHere.add(roleInCareer);
        }
        return employeesWhoCurrentlyWorkHere;
    }
    public List<RoleInCareer> findOldEmployeesOfShop(Long shopId){
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()-> new ShopNotFoundException(shopId));
        List<RoleInCareer> employeesWhoWorkedHere = shop.getEmployeesWhoWorkedHere();
        List<RoleInCareer> employeesWhoFormerlyWorkHere = new ArrayList<>();
        for(RoleInCareer roleInCareer : employeesWhoWorkedHere) {
            if(roleInCareer.getUntil() != null)
                employeesWhoFormerlyWorkHere.add(roleInCareer);
        }
        return employeesWhoFormerlyWorkHere;
    }
    public Employee MutateEmployee(@NotNull MutateEmployeeRecord mutateEmployeeRecord){
        // Retrouver l'employé en question
        Employee employee = employeeRepository.findById(mutateEmployeeRecord.employeeId())
                .orElseThrow(()->new EmployeeNotFoundException(mutateEmployeeRecord.employeeId()));
        // Retrouver la boutique de départ
        Shop startShop = shopRepository.findById(mutateEmployeeRecord.startShopId())
                .orElseThrow(()->new ShopNotFoundException(mutateEmployeeRecord.startShopId()));
        // Retrouver la boutique de destination
        Shop destinationShop = shopRepository.findById(mutateEmployeeRecord.destinationShopId())
                .orElseThrow(()->new ShopNotFoundException(mutateEmployeeRecord.startShopId()));

        // Retrouver l'ancien pas de carrière
        RoleInCareer oldRoleInCareer = employee.getRolesOccupied().get(employee.getRolesOccupied().size()-1);
        // Modifier la date d'arrêt de travail
        oldRoleInCareer.setUntil(LocalDate.now()); // Arrête le travail aujourd'hui, le jour de la mutation
        // Le mettre à jour dans la base de données
        roleInCareerRepository.save(oldRoleInCareer);

        // Créer le nouveau pas de carrière avec toutes les informations nécéssaires
        RoleInCareer newRoleInCareer = RoleInCareer.builder()
                .employee(employee)
                .salary(oldRoleInCareer.getSalary())
                .shop(destinationShop) // La boutique de destination
                .since(mutateEmployeeRecord.since()) // La date de départ depuis le corps de la reqûete
                .until(null) // Il n'y a pas de date de fin pour l'instant
                .role(oldRoleInCareer.getRole()) // Muté avec le même rôle
                .build();
        // Le sauvegarder dans la base de données
        roleInCareerRepository.save(newRoleInCareer);
        // L'ajouter à l'employé
        List<RoleInCareer> allRolesInCareer = employee.getRolesOccupied();
        allRolesInCareer.add(newRoleInCareer);
        employee.setRolesOccupied(allRolesInCareer);
        // Mettre à jour la base de données
        employeeRepository.save(employee);
        // Retourner un message
        System.out.println(getMutationMessage(employee.getName(),startShop.getShopName(),destinationShop.getShopName(),newRoleInCareer.getSince()));
        return employee;
    }
    public Employee addEmployeeToShop(@NotNull AddEmployeeToShopRecord addEmployeeToShopRecord){
        // L'employé
        Employee employee = addEmployeeToShopRecord.employee();
        employeeRepository.save(employee);
        // Retrouver la boutique en question
        Shop startShop = shopRepository.findById(addEmployeeToShopRecord.shopId())
                .orElseThrow(()->new ShopNotFoundException(addEmployeeToShopRecord.shopId()));
        // Créer le nouveau pas de carrière avec toutes les informations nécéssaires
        RoleInCareer newRoleInCareer = RoleInCareer.builder()
                .employee(employee)
                .salary(addEmployeeToShopRecord.salary())
                .shop(startShop) // La boutique de destination
                .since(addEmployeeToShopRecord.startDate()) // La date de départ depuis le corps de la reqûete
                .until(null) // Il n'y a pas de date de fin pour l'instant
                .role(addEmployeeToShopRecord.role()) // Muté avec le même rôle
                .build();
        // Le sauvegarder dans la base de données
        roleInCareerRepository.save(newRoleInCareer);
        // Les roles occupés par l'employé
        List<RoleInCareer> rolesInCareer = new ArrayList<>();
        rolesInCareer.add(newRoleInCareer);
        // L'ajouter à l'employé
        employee.setRolesOccupied(rolesInCareer);
        // Mettre à jour la base de données
        employeeRepository.save(employee);
        return employee;
    }
    public Employee removeEmployeeFromShop(Long employeeId, Long shopId){
        // L'employé en question
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new EmployeeNotFoundException(employeeId));
        // Retrouver la boutique en question
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()->new ShopNotFoundException(shopId));
        // Retrouver l'ancien pas de carrière
        RoleInCareer oldRoleInCareer = employee.getRolesOccupied().get(employee.getRolesOccupied().size()-1);
        // Modifier la date d'arrêt de travail
        oldRoleInCareer.setUntil(LocalDate.now()); // Arrête le travail aujourd'hui, le jour de la mutation
        // Le mettre à jour dans la base de données
        roleInCareerRepository.save(oldRoleInCareer);
        return employee;
    }
    public Employee findAdminOfShop(Long shopId){
        // Trouver le pas de carrière de l'employé qui est présentement le chef de la boutique
        RoleInCareer roleInCareer = roleInCareerRepository.findByShop_IdAndRoleAndUntilIsNull(shopId, Role.ShopManager);
        // Puis trouver l'employé
        return employeeRepository.findById(roleInCareer.getEmployee().getId())
                .orElseThrow(()->new RuntimeException("N'arrive pas à trouver le manager de la boutique ."));
    }
    public Shop updateShopById(Shop updatedShop, Long Id){
        return shopRepository.findById(Id).map(
                shop->{
                    shop.setShopName(updatedShop.getShopName());
                    shop.setRentsOfShop(updatedShop.getRentsOfShop());
                    shop.setEmployeesWhoWorkedHere(updatedShop.getEmployeesWhoWorkedHere());
                    return shopRepository.save(shop);
                }
        ).orElseThrow(()->new ShopNotFoundException(Id));
    }
    public void deleteShopById(Long id){
        shopRepository.deleteById(id);
    }
}
