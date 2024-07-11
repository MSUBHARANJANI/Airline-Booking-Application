package com.airlinereservation;

public class Ticket {
    private String passengerName;
    private String flightNumber;
    private String seatClass;
    private int seatNumber;
    private String foodPreference;
    private double amount;

    public Ticket(String passengerName, String flightNumber, String seatClass, int seatNumber, String foodPreference, double amount) {
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.foodPreference = foodPreference;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Passenger Name: " + passengerName + ", Flight Number: " + flightNumber + 
               ", Seat Class: " + seatClass + ", Seat Number: " + seatNumber + ", Food Preference: " + foodPreference +
               ", Amount: " + amount;
    }
}
