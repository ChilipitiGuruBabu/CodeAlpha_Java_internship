import java.util.*;
import java.io.*;

public class HotelReservationSystem {

    // Room Class
    static class Room {
        int roomId;
        String category;
        double price;
        boolean isAvailable;

        Room(int roomId, String category, double price) {
            this.roomId = roomId;
            this.category = category;
            this.price = price;
            this.isAvailable = true;
        }

        void displayRoom() {
            System.out.println("Room ID: " + roomId +
                    " | Category: " + category +
                    " | Price: ₹" + price +
                    " | Available: " + isAvailable);
        }
    }

    // Customer Class
    static class Customer {
        int customerId;
        String name;
        String phone;

        Customer(int customerId, String name, String phone) {
            this.customerId = customerId;
            this.name = name;
            this.phone = phone;
        }
    }

    // Reservation Class
    static class Reservation {
        int reservationId;
        Customer customer;
        Room room;
        String checkIn;
        String checkOut;
        boolean paymentStatus;

        Reservation(int reservationId, Customer customer, Room room, String checkIn, String checkOut) {
            this.reservationId = reservationId;
            this.customer = customer;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.paymentStatus = false;
        }

        void displayReservation() {
            System.out.println("Reservation ID: " + reservationId);
            System.out.println("Customer Name: " + customer.name);
            System.out.println("Room ID: " + room.roomId);
            System.out.println("Category: " + room.category);
            System.out.println("Check-In: " + checkIn);
            System.out.println("Check-Out: " + checkOut);
            System.out.println("Payment Status: " + (paymentStatus ? "Paid" : "Pending"));
            System.out.println("--------------------------------");
        }
    }

    // Payment Class
    static class Payment {
        static boolean processPayment(double amount) {
            System.out.println("Processing payment of ₹" + amount);
            System.out.println("Payment Successful!");
            return true;
        }
    }

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add Rooms
    static void addRooms() {
        rooms.add(new Room(101, "Standard", 2000));
        rooms.add(new Room(102, "Standard", 2000));
        rooms.add(new Room(201, "Deluxe", 3500));
        rooms.add(new Room(202, "Deluxe", 3500));
        rooms.add(new Room(301, "Suite", 6000));
    }

    // Show Available Rooms
    static void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (r.isAvailable)
                r.displayRoom();
        }
    }

    // Search by Category
    static void searchByCategory() {
        System.out.print("Enter category (Standard/Deluxe/Suite): ");
        String cat = sc.next();

        for (Room r : rooms) {
            if (r.category.equalsIgnoreCase(cat) && r.isAvailable)
                r.displayRoom();
        }
    }

    // Book Room
    static void bookRoom() {

        System.out.print("Enter your name: ");
        String name = sc.next();

        System.out.print("Enter phone number: ");
        String phone = sc.next();

        showAvailableRooms();

        System.out.print("Enter Room ID to book: ");
        int roomId = sc.nextInt();

        for (Room r : rooms) {

            if (r.roomId == roomId && r.isAvailable) {

                Customer c = new Customer(reservations.size()+1, name, phone);

                System.out.print("Enter Check-in Date: ");
                String checkIn = sc.next();

                System.out.print("Enter Check-out Date: ");
                String checkOut = sc.next();

                if (Payment.processPayment(r.price)) {

                    Reservation res = new Reservation(
                            reservations.size()+1, c, r, checkIn, checkOut);

                    res.paymentStatus = true;

                    reservations.add(res);

                    r.isAvailable = false;

                    saveReservation(res);

                    System.out.println("Room booked successfully!");
                    return;
                }
            }
        }

        System.out.println("Room not available.");
    }

    // Cancel Reservation
    static void cancelReservation() {

        System.out.print("Enter Reservation ID: ");
        int id = sc.nextInt();

        for (Reservation r : reservations) {

            if (r.reservationId == id) {

                r.room.isAvailable = true;

                reservations.remove(r);

                System.out.println("Reservation cancelled.");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }

    // View Reservations
    static void viewReservations() {

        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : reservations) {
            r.displayReservation();
        }
    }

    // Save Reservation to File
    static void saveReservation(Reservation r) {

        try {

            FileWriter fw = new FileWriter("reservations.txt", true);

            fw.write(
                    r.reservationId + "," +
                            r.customer.name + "," +
                            r.customer.phone + "," +
                            r.room.roomId + "," +
                            r.room.category + "," +
                            r.checkIn + "," +
                            r.checkOut + "," +
                            r.paymentStatus + "\n"
            );

            fw.close();

        } catch (Exception e) {
            System.out.println("File error.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        addRooms();

        while (true) {

            System.out.println("\n--- HOTEL RESERVATION SYSTEM ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Rooms by Category");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. View Bookings");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showAvailableRooms();
                    break;

                case 2:
                    searchByCategory();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    cancelReservation();
                    break;

                case 5:
                    viewReservations();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}