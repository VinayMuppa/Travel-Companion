package com.demo.travelcompanion.service;

import com.demo.travelcompanion.exceptions.DuplicateEmailException;
import com.demo.travelcompanion.model.Passenger;

import java.util.List;

public interface IPassengerService {
    public List<Passenger> getAllPassengerDetails();
    public Passenger getPassengerDetails(String passengerId);
    public Passenger registerPassenger(Passenger passenger) throws DuplicateEmailException;
    public List<Passenger> getPassengerMatchings(String passengerId);

    public void deleteById(String passengerId);
}
