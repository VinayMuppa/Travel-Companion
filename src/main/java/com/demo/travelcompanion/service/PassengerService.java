package com.demo.travelcompanion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.travelcompanion.exceptions.DuplicateEmailException;
import com.demo.travelcompanion.model.Passenger;
import com.demo.travelcompanion.repository.IPassengerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PassengerService implements IPassengerService{

    @Autowired
    private IPassengerDetailsRepository passengerRepository;

    @Override
    public List<Passenger> getAllPassengerDetails(){
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassengerDetails(String passengerId){
        return passengerRepository.findById(passengerId).get();

    }

    @Override
    public Passenger registerPassenger(Passenger passenger) throws DuplicateEmailException {
        try {
            if (isEmailUnique(passenger.getEmailId())) {
                System.out.println("Saving Data");
                System.out.println(passenger.toString());
                passengerRepository.save(passenger);
            } else {
                throw new DuplicateEmailException("Email Registered ALready !! ");
            }
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return passenger;
    }

    @Override
    public List<Passenger> getPassengerMatchings(String passengerId) {

        Passenger passengerLookingForMatches = passengerRepository.findById(passengerId).orElseThrow();;
        List<String> passengerInterestsLookingForMatches = passengerLookingForMatches.getIntrests();

        List<Passenger> matchingPassengers = passengerRepository.findAll().stream()
                .filter(passenger -> !passenger.equals(passengerLookingForMatches)) // Exclude the passengerLookingForMatches
                .filter(passenger -> hasCommonInterest(passenger.getIntrests(), passengerInterestsLookingForMatches))
                .collect(Collectors.toList());

        List<Passenger> finalMatches = new ArrayList<Passenger>();
        for(int i=0; i< matchingPassengers.size();i++) {
            Passenger pass = matchingPassengers.get(i);
            if(!pass.getEmailId().equals(passengerLookingForMatches.getEmailId())) {
                finalMatches.add(pass);
            }

        }
        return finalMatches;

    }

    private boolean isEmailUnique(String email) {
        System.out.println(email);
        Passenger existingUser = passengerRepository.findByEmailId(email);
        return existingUser == null;
    }

    private boolean hasCommonInterest(List<String> interests1, List<String> interests2) {
        return interests1.stream().anyMatch(interests2::contains);
    }



}

