package com.demo.travelcompanion.controller;

import com.demo.travelcompanion.exceptions.DuplicateEmailException;
import com.demo.travelcompanion.model.Passenger;
import com.demo.travelcompanion.service.IEmailService;
import com.demo.travelcompanion.service.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/travel")
public class TravelCampaignController {

    @Autowired
    private IEmailService emailService;
    @Autowired
    private IPassengerService passengerSerice;
    private final String SUCCESS_RESGISTRATION_MESSAGE = "REGISTERED SUCCESSFULLY with Travel Companion";


    @GetMapping("/all-passenger-details")
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        List<Passenger> passengerDetails = passengerSerice.getAllPassengerDetails();
        return new ResponseEntity<List<Passenger>>(passengerDetails, HttpStatus.OK);
    }

    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<Passenger> getPassengerDetails(@PathVariable String passengerId){
        Passenger passenger = passengerSerice.getPassengerDetails(passengerId);
        try {
            if(passenger != null)
                return new ResponseEntity<Passenger>(passenger,HttpStatus.OK);
            else
                return new ResponseEntity<Passenger>(HttpStatus.NOT_FOUND);
        }catch(Exception ex) {
            return new ResponseEntity<Passenger>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/passenger")
    public ResponseEntity<?> registerPassenger(@RequestBody Passenger passenger){

        try {
            Passenger newPassenger = passengerSerice.registerPassenger(passenger);
            emailService.sendEmail(passenger.getEmailId(),SUCCESS_RESGISTRATION_MESSAGE,"Thanks for registering with Travel-Companion. Please go to the app to find matches for destinations.");
            return new ResponseEntity<Passenger>(newPassenger, HttpStatus.OK);
        }catch(DuplicateEmailException ex) {
            String errorMessage = "Email address " + passenger.getEmailId() + " is already registered.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @GetMapping("/find-matchings/{passengerId}")
    public ResponseEntity<List<Passenger>> getPassengerMatchings(@PathVariable String passengerId){
        try {
            List<Passenger> passengers = passengerSerice.getPassengerMatchings(passengerId);
            return new ResponseEntity<List<Passenger>>(passengers,HttpStatus.OK);
        }catch(Exception ex) {
            return new ResponseEntity<List<Passenger>>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/passenger/{passengerId}")
    public ResponseEntity<HttpStatus> deletePassenger(@PathVariable String passengerId){
        if(passengerSerice.getPassengerDetails(passengerId)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            passengerSerice.deleteById(passengerId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}