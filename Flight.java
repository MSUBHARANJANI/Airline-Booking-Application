package com.airlinereservation;

public class Flight {
    private String flightNumber;
    private String destination;
    private String timing;
    private String timeFormat; // AM or PM
    private int normalCapacity;
    private int businessCapacity;
    private double normalRate;
    private double businessRate;
    private boolean[] normalSeats;
    private boolean[] businessSeats;

    public Flight(String flightNumber, String destination, String timing, String timeFormat, int normalCapacity, int businessCapacity, double normalRate, double businessRate) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.timing = timing;
        this.timeFormat = timeFormat;
        this.normalCapacity = normalCapacity;
        this.businessCapacity = businessCapacity;
        this.normalRate = normalRate;
        this.businessRate = businessRate;
        this.normalSeats = new boolean[normalCapacity];
        this.businessSeats = new boolean[businessCapacity];
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public String getTiming() {
        return timing;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public double getNormalRate() {
        return normalRate;
    }

    public double getBusinessRate() {
        return businessRate;
    }

    public boolean bookSeat(int seatNumber, String seatClass) {
        if (seatClass.equalsIgnoreCase("normal")) {
            if (seatNumber <= normalCapacity && !normalSeats[seatNumber - 1]) {
                normalSeats[seatNumber - 1] = true;
                return true;
            }
        } else if (seatClass.equalsIgnoreCase("business")) {
            if (seatNumber <= businessCapacity && !businessSeats[seatNumber - 1]) {
                businessSeats[seatNumber - 1] = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber + ", Destination: " + destination + ", Timing: " + timing + " " + timeFormat +
               ", Normal Capacity: " + normalCapacity + ", Business Capacity: " + businessCapacity +
               ", Normal Rate: " + normalRate + ", Business Rate: " + businessRate;
    }
}
