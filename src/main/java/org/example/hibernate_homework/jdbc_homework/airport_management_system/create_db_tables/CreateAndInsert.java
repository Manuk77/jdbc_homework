package org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.Company;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Passengers;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.Trip;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.CompanyService;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.PassengerService;
import org.example.hibernate_homework.jdbc_homework.airport_management_system.service.TripService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class CreateAndInsert {
    private static String url = "jdbc:postgresql://localhost:5432/JDBC";
    private static String userName = "postgres";
    private static String passwd = "manukpoloz2000";
    private static CreateAndInsert createAndInsert;
    static Connection con;
    Statement st;
    Scanner sc;
    BufferedReader bufferedReader;
    CompanyService compService;
    TripService tripService;
    PassengerService passService;

    public static Connection connectToUrl() {
        try {
            if (con == null)
                return con =  DriverManager.getConnection(url, userName,passwd);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    private void bufferReader(String path) {
            try {
                bufferedReader = new BufferedReader(new FileReader(path));
            }catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
    }
    public static CreateAndInsert createAndInsertSingle() {
        if (createAndInsert == null)
            return new CreateAndInsert();
        return createAndInsert;
    }

    public void closeConnection() {
        con = null;
    }


    private void insertCompany(String filePath){
        if (validString(filePath)) {
            con = connectToUrl();
            createStatement();
            bufferReader(filePath);
            String[] comp;
            try {
                int i = 1;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    StringBuilder sb = new StringBuilder("insert into Company(company_id, company_name, found_date) values (");
                    comp = line.split(",");
                    sb.append(i).append(",'").append(comp[0] + "','").append(comp[1] + "');");
                    st.executeUpdate(sb.toString());
                    sb.setLength(0);
                    ++i;
                }

            }catch (SQLException  | IOException e){
                System.out.println(e.getMessage());
            }finally {
                if (st != null)
                    try {
                        st.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                        try {
                            bufferedReader.close();
                        }catch (IOException x) {
                            System.out.println(x.getMessage());
                        }
                    }
            }
            return;
        }
        throw new IllegalArgumentException("filePath must not be null or empty");

    }

    private void insertPassengers(String filePath){
        if (validString(filePath)) {
            createStatement();
            bufferReader(filePath);
            String[] passenger;
            String line;
            try {
                long i = 1;
                while ((line = bufferedReader.readLine()) != null){
                    StringBuilder sb = new StringBuilder("insert into Passengers(passenger_id, namee, phone, country, city) values (");
                    passenger = line.split(",");
                    sb.append((i) + ",'").append(passenger[0] +"','")
                            .append(passenger[1]+"','" + passenger[2] +"'," + "'" + passenger[3] + "');");
                    st.executeUpdate(sb.toString());
                    sb.setLength(0);
                    ++i;
                }
            }catch (SQLException | IOException e){
                System.out.println(e.getMessage());
            }finally {
                if (st != null)
                    try {
                        st.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                        try {
                            bufferedReader.close();
                        }catch (IOException x) {
                            System.out.println(x.getMessage());
                        }
                    }
            }
            return;
        }
        throw new IllegalArgumentException("filepath must not be null or empty");
    }

    private void insertTrip(String filePath){
        if (validString(filePath)) {
            createStatement();
            bufferReader(filePath);
            createStatement();
            StringBuilder sb = new StringBuilder("insert into Trip(trip_id, passenger_id," +
                    " company, town_from, town_to, time_out, time_in) values(");
            String[] trip;
            String line;
            try{
                while ((line = bufferedReader.readLine()) != null){
                    int i = 1;
                    trip = line.split(",");
                    sb.append(parserToInt(trip[0])).append( ",").append(parserToInt(trip[1])).append( ",'").append(trip[2])
                            .append("','").append(trip[3]).append("','").append(trip[4]).append(",'").append(trip[5])
                            .append("','").append(trip[6]).append("');");
                    st.executeUpdate(sb.toString());
                    System.out.println("trip-----------");
                    System.out.println(sb.toString());
                    sb.setLength(0);

                }
            }catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }finally {
                if (st != null)
                    try {
                        st.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                        try {
                            bufferedReader.close();
                        }catch (IOException x) {
                            System.out.println(x.getMessage());
                        }
                    }
            }
            return;
        }
        throw new IllegalArgumentException("filepath must not be null or empty");
    }

    private void insertPassInTrip(String filePath) {
        if (validString(filePath)) {
            createStatement();
            bufferReader(filePath);
            String[] passInT;
            StringBuilder sb = new StringBuilder("insert into Pass_in_trip(trip_id, passenger_id, datee, place) values(");
            String line;
            try {

                while ((line = bufferedReader.readLine()) != null) {
                    passInT = line.split(",");
                    sb.append(parserToInt(passInT[0]) + "," + parserToInt(passInT[1] + ",'" + passInT[2] + "','" +
                            passInT[3] + "');"));
                    st.executeUpdate(sb.toString());
                    sb.setLength(0);


                }
            }catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }finally {
                if (st != null)
                    try {
                        st.close();
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                        try {
                            bufferedReader.close();
                        }catch (IOException x) {
                            System.out.println(x.getMessage());
                        }
                    }
            }
            return;

        }
        throw new IllegalArgumentException("filepath must not be null or empty");
    }






    private void createTableCompany() {
        final String comName =
                "create table Company(company_id integer primary key , company_name varchar(50) not null, found_date date not null);";

        try{
            con = connectToUrl();
            Statement st = con.createStatement();
            st.execute(comName);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if (st != null)
                try {
                    st.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
        }


    }

    private void createTablePassengers() throws SQLException {
        final String passName =
                "create table Passengers(passenger_id integer primary key, namee varchar(50) not null," +
                        " phone varchar(50) not null, country varchar(50) not null, city varchar(50) not null);";
        Statement st = con.createStatement();
        st.execute(passName);

        st.close();

    }

    private void createTableTrip() throws SQLException {
        final String tripName = "create table Trip(trip_id integer primary key, passenger_id integer references Passengers(passenger_id)," +
                " company varchar(50) not null, town_from varchar(50) not null ," +
                " town_to varchar(50)not null, time_out date not null, time_in date not null );";

        Statement st = con.createStatement();
        st.execute(tripName);
        st.close();

    }

    private void createTablePassInTrip() throws SQLException {
        final String passInTrip = "create table Pass_in_trip(trip_id integer  references Trip(trip_id), " +
                "passenger_id integer references Passengers(passenger_id), datee date not null, place varchar(50) not null )";
        Statement st = con.createStatement();
        st.execute(passInTrip);

        st.close();
    }

    public void createAllTables(){
        try{
            createTableCompany();
            createTablePassengers();
            createTableTrip();
            createTablePassInTrip();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void insertAllTables(String[] paths) {
        if (paths.length == 4) {
            insertCompany(paths[0]);
            insertPassengers(paths[1]);
            insertTrip(paths[2]);
            insertPassInTrip(paths[3]);
            return;
        }
        throw new IllegalArgumentException("specified array of String must not be null or less or more than 4 elements");

    }

    private Set<Company> readFromCompany(int choice) {
        compService = new CompanyService();
        Set<Company> comp = new HashSet<>();
        sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        if (choice == 1){
            System.out.print("enter id for searching -> ");
            int id = sc.nextInt();
            comp.add(compService.getById(id));

        } else if (choice == 2) {
            comp = compService.getAll();

        } else if (choice == 3) {
            System.out.print("enter offset -> ");
            int offset = sc.nextInt();
            System.out.print("enter perPage -> ");
            int perPage = sc.nextInt();
            System.out.print("enter sort -> ");
            String sort = scStr.nextLine();
            comp = compService.get(offset, perPage, sort);
        }
        return comp;
    }
    private Set<Passengers> readFromPassenger(int choice) {
        passService = new PassengerService();
        Set<Passengers> pass = new HashSet<>();
        sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        if (choice == 1){
            System.out.print("enter id for searching -> ");
            int id = sc.nextInt();
            pass.add(passService.getById(id));

        } else if (choice == 2) {
            pass = passService.getAll();

        } else if (choice == 3) {
            System.out.print("enter offset -> ");
            int offset = sc.nextInt();
            System.out.print("enter perPage -> ");
            int perPage = sc.nextInt();
            System.out.print("enter sort -> ");
            String sort = scStr.nextLine();
            pass = passService.get(offset, perPage, sort);
        }
        return pass;
    }

    private Set<Trip> readFromTrip(int choice) {
        tripService = new TripService();
        Set<Trip> trip = new HashSet<>();
        sc = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);
        if (choice == 1){
            System.out.print("enter id for searching -> ");
            int id = sc.nextInt();
            trip.add(tripService.getById(id));
        } else if (choice == 2) {
            trip = tripService.getAll();
        } else if (choice == 3) {
            System.out.print("enter offset -> ");
            int offset = sc.nextInt();
            System.out.print("enter perPage -> ");
            int perPage = sc.nextInt();
            System.out.print("enter sort -> ");
            String sort = scStr.nextLine();
            trip = tripService.get(offset, perPage, sort);
        }
        return trip;
    }

    public void read(int choice) {
        if (choice == 1) {
            sc = new Scanner(System.in);
            menuComp();
            int choiceCompany = sc.nextInt();
            sc = null;
            for(Company com: readFromCompany(choiceCompany)) {
                System.out.println(com);
            }
        }else if (choice == 2) {
            sc = new Scanner(System.in);
            menuPass();
            int choicePassenger = sc.nextInt();
            sc = null;
            for(Passengers pas :readFromPassenger(choicePassenger)) {
                System.out.println(pas);
            }
        }else {
            sc = new Scanner(System.in);
            menuTrip();
            int choiceTrip = sc.nextInt();
            sc = null;
            for(Trip trip :readFromTrip(choiceTrip)) {
                System.out.println(trip);
            }
        }
    }

    public void deleteFrom(int choice) {
        sc = new Scanner(System.in);
        if (choice == 1) {
            System.out.print("Enter id for deleting -> ");
            long id = sc.nextLong();
            compService = new CompanyService();
            compService.delete(id);
        }else if (choice == 2) {
            System.out.print("Enter id for deleting -> ");
            long id = sc.nextLong();
            passService = new PassengerService();
            passService.delete(id);
        }else {
            System.out.print("Enter id for deleting -> ");
            long id = sc.nextLong();
            tripService = new TripService();
            tripService.delete(id);
        }
    }



    private void menuComp() {
        System.out.println("1. Company getById(long id)");
        System.out.println("2. Set<Company> getAll()");
        System.out.println("3. Set<Company> get(int offset, int perPage, String sort)");
        System.out.print("Enter your choice -> ");
    }

    private void menuPass() {
        System.out.println("1. Passenger getById(long id)");
        System.out.println("2. Set<Passenger> getAll()");
        System.out.println("3. Set<Passenger> get(int offset, int perPage, String sort)");
        System.out.print("Enter your choice -> ");
    }

    private void menuTrip() {
        System.out.println("1. Trip getById(long id)");
        System.out.println("2.  Set<Trip> getAll()");
        System.out.println("3.  Set<Trip> get(int offset, int perPage, String sort)");
        System.out.print("Enter your choice -> ");
    }





//    private void connection(){
//        try {
//           con = DriverManager.getConnection(url, userName, passwd);
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }

//    public Connection getCon() {
//        return con;
//    }

    private void createStatement(){
        try {
            st = con.createStatement();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static int parserToInt(String trip_id) {
        int id = -1;
        if (isDigit(trip_id))
           id = parser(trip_id);
        return id;
    }
    private static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); ++i){
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }

    private static int parser(String str) {
        int number = Integer.parseInt(str);
        return number;
    }



    private static boolean validString(String str){
        return str != null && !str.isEmpty();
    }
}
