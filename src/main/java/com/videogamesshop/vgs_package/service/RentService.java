package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.RentNotFoundException;
import com.videogamesshop.vgs_package.model.Enums.RentStatus;
import com.videogamesshop.vgs_package.model.Enums.VideoGameCopyStatus;
import com.videogamesshop.vgs_package.model.entities.*;
import com.videogamesshop.vgs_package.model.records.CreateRentRecord;
import com.videogamesshop.vgs_package.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class RentService {
    RentRepository rentRepository;
    VideoGameCopyRepository videoGameCopyRepository;
    CustomerRepository customerRepository;
    ShopRepository shopRepository;
    EmployeeRepository employeeRepository;
    VideoGameRepository videoGameRepository;
    CustomerService customerService;
    VideoGameService videoGameService;
    @Autowired
    public RentService(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }

    public Rent createRent(CreateRentRecord createRentRecord){
        Rent rent = new Rent();
        Optional<Customer> theCustomer = customerRepository.findById(createRentRecord.customerId());
        theCustomer.ifPresent(customer -> {
            customerService.decreaseBalance(createRentRecord.customerId(),createRentRecord.numberOfDays()*((double) 1 /7));
        });
        Optional<Shop> theShop = shopRepository.findById(createRentRecord.shopId());
        theShop.ifPresent(rent::setShop);
        Optional<Employee> theEmployee = employeeRepository.findById(createRentRecord.employeeId());
        theEmployee.ifPresent(rent::setEmployee);
        rent.setEndDate(LocalDate.now().plusDays(createRentRecord.numberOfDays()));
        rent.setStartDate(LocalDate.now());
        rent.setCost(createRentRecord.numberOfDays()*((double) 1 /7)); // 1 Dollar par semaine par jeu, quel que soit le jeu
        return rentRepository.save(rent);
    }

    public synchronized void addGameToRent(Long rentId, Long videoGameId, Long gameConsoleId) {
        Optional<VideoGame> theVideoGame = videoGameRepository.findById(videoGameId);
        theVideoGame.ifPresent(videoGame -> {
            List<VideoGameCopy> copies = videoGame.getCopies();
            if(!copies.isEmpty()){
                VideoGameCopy videoGameCopy = copies.get(0);
                copies.remove(videoGameCopy); // D'abord là retirer
                videoGameCopy.setStatus(VideoGameCopyStatus.RENTED); // Ensuite là manipuler
                videoGame.setCopies(copies);
                videoGameRepository.save(videoGame);
                rentRepository.findById(rentId).map((rent) -> {
                    var copiesInRent = rent.getVideoGameCopies();
                    copiesInRent.add(videoGameCopy);
                    rent.setVideoGameCopies(copiesInRent);
                    return rentRepository.save(rent);
                });
            }
        });
    }
    public synchronized void deleteGameFromRent(Long rentId, Long videoGameCopyId) {
        Optional<VideoGameCopy> theVideoGameCopy = videoGameCopyRepository.findById(videoGameCopyId);
        theVideoGameCopy.ifPresent(videoGameCopy -> {
            rentRepository.findById(rentId).map((rent) -> {
                var copiesInRent = rent.getVideoGameCopies();
                copiesInRent.remove(videoGameCopy);
                videoGameCopy.setStatus(VideoGameCopyStatus.INSTORE);
                Optional<VideoGame> theVideoGame = videoGameRepository.findById(videoGameCopy.getVideoGame().getId());
                theVideoGame.ifPresent(videoGame -> {
                    List<VideoGameCopy> copies = videoGame.getCopies();
                    copies.add(videoGameCopy);
                    videoGame.setCopies(copies);
                    videoGameRepository.save(videoGame);
                });
                rent.setVideoGameCopies(copiesInRent);
                return rentRepository.save(rent);
            });
        });
    }
    public Optional<Rent> returnRent(Long rentId){
        return rentRepository.findById(rentId).map((rent)->{
            for(VideoGameCopy videoGameCopy : rent.getVideoGameCopies()){
                Optional<VideoGame> theVideoGame = videoGameRepository.findById(videoGameCopy.getId());
                theVideoGame.ifPresent(videoGame -> {
                    var copies = videoGame.getCopies();
                    copies.add(videoGameCopy);
                    videoGame.setCopies(copies);
                    videoGameRepository.save(videoGame);
                });
            }
            rent.setStatus(RentStatus.FINISHED);
            return rentRepository.save(rent);
        });
    }
    public Rent findRentById(Long rentId){
        return rentRepository.findById(rentId).orElseThrow(()->new RentNotFoundException(rentId));
    }
    public List<Rent> findRentsByCustomerId(Long customerId){
        return rentRepository.findAllByCustomer_IdOrderByEndDate(customerId);
    }
    public List<Rent> findRentsByEmployeeId(Long employeeId){
        return rentRepository.findAllByEmployee_Id(employeeId);
    }
}
