package com.demo.travelcompanion.repository;

import com.demo.travelcompanion.model.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPassengerDetailsRepository extends MongoRepository<Passenger, String> {

    public Passenger findByEmailId(String emailId);
}
