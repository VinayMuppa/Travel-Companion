package com.demo.travelcompanion.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PassengerDetails")
public class Passenger {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Double phoneNumber;

    @Indexed(unique = true)
    private String emailId;

    private List<String> intrests;
    private List<String> disLikes;
    private List<String> destinationPlaces;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private String estimatedTripStartDate;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private String estimatedTripEndDate;


    public String getPassengerId() {
        return id;
    }
    public void setPassengerId(String passengerId) {
        this.id = passengerId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Double getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public List<String> getIntrests() {
        return intrests;
    }
    public void setIntrests(List<String> intrests) {
        this.intrests = intrests;
    }
    public List<String> getDisLikes() {
        return disLikes;
    }
    public void setDisLikes(List<String> disLikes) {
        this.disLikes = disLikes;
    }
    public List<String> getDestinationPlaces() {
        return destinationPlaces;
    }
    public void setDestinationPlaces(List<String> destinationPlaces) {
        this.destinationPlaces = destinationPlaces;
    }
    public String getEstimatedTripStartDate() {
        return estimatedTripStartDate;
    }
    public void setEstimatedTripStartDate(String estimatedTripStartDate) {
        this.estimatedTripStartDate = estimatedTripStartDate;
    }
    public String getEstimatedTripEndDate() {
        return estimatedTripEndDate;
    }
    public void setEstimatedTripEndDate(String estimatedTripEndDate) {
        this.estimatedTripEndDate = estimatedTripEndDate;
    }


}

