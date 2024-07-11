package com.airlinereservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationSystem {
    private static List<Flight> flights = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nAirline Reservation System");
            System.out.println("1. Add Flight");
            System.out.println("2. List Flights");
            System.out.println("3. Book Ticket");
            System.out.println("4. List Tickets");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    listFlights();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    listTickets();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addFlight() {
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter timing (hh:mm): ");
        String timing = scanner.nextLine();
        System.out.print("Enter time format (AM/PM): ");
        String timeFormat = scanner.nextLine().toUpperCase();
        System.out.print("Enter normal capacity: ");
        int normalCapacity = scanner.nextInt();
        System.out.print("Enter business capacity: ");
        int businessCapacity = scanner.nextInt();
        System.out.print("Enter normal rate: ");
        double normalRate = scanner.nextDouble();
        System.out.print("Enter business rate: ");
        double businessRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        flights.add(new Flight(flightNumber, destination, timing, timeFormat, normalCapacity, businessCapacity, normalRate, businessRate));
        System.out.println("Flight added successfully!");
    }

    private static void listFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            System.out.println("Available Flights:");
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        }
    }

    private static void bookTicket() {
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        
        // List available flights
        listFlights();
        
        System.out.print("Enter flight number to book: ");
        String flightNumber = scanner.nextLine();
        Flight flight = findFlightByNumber(flightNumber);
        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        System.out.print("Enter seat class (normal/business): ");
        String seatClass = scanner.nextLine();
        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String foodPreference = "";
        double amount = 0.0;

        if (seatClass.equalsIgnoreCase("normal")) {
            amount = flight.getNormalRate();
            System.out.printf("Booking amount: $%.2f. Confirm booking by entering the amount: ", amount);
            double enteredAmount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (enteredAmount != amount) {
                System.out.println("Amount mismatch. Booking failed.");
                return;
            }
            System.out.print("Enter food preference (veg/non-veg): ");
            foodPreference = scanner.nextLine();
            if (!foodPreference.equalsIgnoreCase("veg") && !foodPreference.equalsIgnoreCase("non-veg")) {
                System.out.println("Invalid food preference. Booking failed.");
                return;
            }
        } else if (seatClass.equalsIgnoreCase("business")) {
            amount = flight.getBusinessRate();
            System.out.printf("Booking amount: $%.2f. Confirm booking by entering the amount: ", amount);
            double enteredAmount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (enteredAmount != amount) {
                System.out.println("Amount mismatch. Booking failed.");
                return;
            }
        }

        if (flight.bookSeat(seatNumber, seatClass)) {
            tickets.add(new Ticket(passengerName, flightNumber, seatClass, seatNumber, foodPreference, amount));
            System.out.println("Ticket booked successfully!");
        } else {
            System.out.println("No available seats in the chosen class or seat already booked.");
        }
    }

    private static void listTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets booked.");
        } else {
            System.out.println("Booked Tickets:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }
    }

    private static Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}
