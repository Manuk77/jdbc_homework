package org.example.hibernate_homework.jdbc_homework.menu;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.Company;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Passengers;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.CompanyService;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.PassengerService;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.TripService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Scanner;

public class Menu {
    private static String[] paths = new String[]{"/home/manuk/IdeaProjects/jdbc_project/src/main/resources/companies.txt",
            "/home/manuk/IdeaProjects/jdbc_project/src/main/resources/passengers.txt",
            "/home/manuk/IdeaProjects/jdbc_project/src/main/resources/trip.txt",
            "/home/manuk/IdeaProjects/jdbc_project/src/main/resources/pass_in_trip.txt"};
    public static void menu(){
        System.out.println("1. CREAT");
        System.out.println("2. READ");
        System.out.println("3. UPDATE");
        System.out.println("4. DELETE");
        System.out.println("5. EXIT");

    }

    public static void read(){
        System.out.println("1. read from table company");
        System.out.println("2. read from table passengers");
        System.out.println("3. read from table trip");
    }

    public static void update() {
        System.out.println("1. update in table company");
        System.out.println("2. update in table passengers");
        System.out.println("3. update in table trip");
    }

    public static void delete(){
        System.out.println("1. delete from table company");
        System.out.println("2. delete from table passengers");
        System.out.println("3. delete from table trip");
    }


    public static void choiceForMenu(int choice, Scanner scInt, CreateAndInsert cri) {
        switch (choice){
            case 1:
                cri.createAllTables();
                cri.insertAllTables(paths);
                break;

            case 2:
                read();
                System.out.print("ENTER YOUR CHOICE -> ");
                int read = scInt.nextInt();
                cri.read(read);
                break;

            case 3:
                update();
                System.out.print("ENTER YOUR CHOICE -> ");
                int update = scInt.nextInt();
                Scanner scStr = new Scanner(System.in);
                if (update == 1){
                    System.out.print("Enter The date m/d/yyyy -> ");
                    Date date = new Date(scInt.nextLong());
                    System.out.println("Enter the airplane name");
                    String airplane = scStr.nextLine();
                    Company comp = new Company(0,airplane,date);
                    CompanyService companyService = new CompanyService();
                    System.out.print("Enter id for searching -> ");
                    companyService.update(comp, scInt.nextLong());
                }
                else if (update == 2) {
                    System.out.print("Enter name -> ");
                    String name = scStr.nextLine();
                    System.out.print("Enter phone -> ");
                    String phone = scStr.nextLine();
                    System.out.print("Enter country -> ");
                    String country = scStr.nextLine();
                    System.out.print("Enter city -> ");
                    String city = scStr.nextLine();
                    Passengers pass = new Passengers(0, name, phone, country, city);
                    PassengerService passengerService = new PassengerService();
                    System.out.print("Enter id for searching -> ");
                    passengerService.update(pass, scInt.nextLong());
                }
                else{
                    System.out.print("Enter airplane -> ");
                    String airplane = scStr.nextLine();
                    System.out.print("Enter town from -> ");
                    String townFrom = scStr.nextLine();
                    System.out.print("Enter town to -> ");
                    String townTo = scStr.nextLine();
                    System.out.print("Enter time out yyyy-mm-dd ->");
                    Timestamp timeOut = new Timestamp(scInt.nextLong());
                    System.out.print("Enter time in yyyy-mm-dd ->");
                    Timestamp timeIn = new Timestamp(scInt.nextLong());
                    Trip trip = new Trip(0, 0, airplane, townFrom, townTo, timeOut, timeIn);
                    TripService tripService = new TripService();
                    System.out.print("Enter id for searching -> ");
                    tripService.update(trip, scInt.nextLong());
                }
                break;

            case 4:
                delete();
                System.out.print("ENTER YOUR CHOICE -> ");
                int delete = scInt.nextInt();
                cri.deleteFrom(delete);
                break;

            case 5:
                break;

            default:
                System.out.println("THERE IS NOT MATCH CASE :)");
        }
    }
}
