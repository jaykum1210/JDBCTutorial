package HotelManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class reservation {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_management_tutorial";
    private static final String name = "root";
    private static final String password = "jaykum1210";

    private static boolean reservationexist(Connection con, int id){
        try (Statement stmt = con.createStatement()){
            String sql = "SELECT id FROM reservation WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    private static void reserveroom(Connection con, Scanner sc){
        try {
            System.out.println("Enter Name : ");
            String guest_name = sc.nextLine();
            System.out.println("Enter Room Number : ");
            int room_number = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Contact Number : ");
            String contact_number = sc.nextLine();

            String sql = "INSERT INTO reservation(guest_name,room_number,contact_number) " +
                    "VALUES('" + guest_name + "', " + room_number + ", '" + contact_number + "')";

            try (Statement stmt = con.createStatement()){
                int affected_rows = stmt.executeUpdate(sql);
                if (affected_rows>0){
                    System.out.println("Reservation Successful");
                }
                else{
                    System.out.println("Reservation Failed!");
                }
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void viewreservation(Connection con) throws SQLException{
        String sql = "select * from reservation";

        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String guest_name = rs.getString("guest_name");
                int room_number = rs.getInt("room_number");
                String contact_number = rs.getString("contact_number");
                String reservation_date = rs.getTimestamp("reservation_date").toString();
                System.out.println("Id : " + id + " Guest Name : " + guest_name + " Room Number : " + room_number + " Contact Number : " + contact_number + " Reservation Date : " + reservation_date);
                System.out.println("-----------------------------------------------------------------------");
            }
        }
    }

    private static void getroomnumber(Connection con , Scanner sc) throws SQLException{
        try (Statement stmt = con.createStatement()){
            System.out.println("Enter Id : ");
            int id = sc.nextInt();
            String sql = "SELECT room_number FROM reservation WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Room Number : " + rs.getInt("room_number"));
            } else {
                System.out.println("Reservation not found!");
            }
        }
    }

    private static void updatereservation(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Id: ");
            int id = sc.nextInt();
            if (!reservationexist(con, id)) {
                System.out.println("Reservation Not Found for given Id");
                return;
            }
            sc.nextLine(); // consume newline
            System.out.print("Enter Guest Name: ");
            String guest_name = sc.nextLine();
            System.out.print("Enter Room Number: ");
            int room_number = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter Contact Number: ");
            String contact_number = sc.nextLine();
            String sql = "UPDATE reservation SET guest_name = '" + guest_name + "', " +
                    "room_number = " + room_number + ", " +
                    "contact_number = '" + contact_number + "' " +
                    "WHERE id = " + id;
            try (Statement stmt = con.createStatement()) {
                int rowsAffected = stmt.executeUpdate(sql);
                if (rowsAffected > 0) {
                    System.out.println("Reservation Updated");
                } else {
                    System.out.println("Reservation Not Updated!");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deletereservation(Connection con, Scanner sc){
        try {
            System.out.println("Enter Id : ");
            int id = sc.nextInt();
            if (!reservationexist(con,id)){
                System.out.println("Reservation Not Found for given Id");
                return;
            }
            String sql = "DELETE FROM reservation WHERE id = " + id;
            try (Statement stmt = con.createStatement()){
                int affect_rows = stmt.executeUpdate(sql);
                if (affect_rows>0){
                    System.out.println("Reservation Deleted");
                }
                else{
                    System.out.println("Failed!");
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void exit() throws InterruptedException{
        System.out.print("Exiting");
        for (int i = 0;i<5;i++){
            System.out.print(".");
            Thread.sleep(450);
        }
        System.out.println();
    }

    public static void main(String[] args) throws ClassNotFoundException{
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,name,password);
            while(true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");

                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                System.out.println();
                System.out.println("Choose an Option : ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1 :
                        reserveroom(con, sc);
                        break;
                    case 2:
                        viewreservation(con);
                        break;
                    case 3:
                        getroomnumber(con, sc);
                        break;
                    case 4:
                        updatereservation(con, sc);
                        break;
                    case 5:
                        deletereservation(con, sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice! Try Again.");
                }
            }
        } catch (SQLException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
