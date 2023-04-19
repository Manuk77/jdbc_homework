package org.example.hibernate_homework.jdbc_homework.airport_management_system;

import org.example.hibernate_homework.jdbc_homework.airport_management_system.create_db_tables.CreateAndInsert;

import java.util.Scanner;
import static org.example.hibernate_homework.jdbc_homework.menu.Menu.*;

public class Main {
    public static void main(String[] args) {

        CreateAndInsert cri = new CreateAndInsert();
        Scanner scInt = new Scanner(System.in);
        int choice = 0;


        do {
            menu();
            System.out.print("ENTER YOUR CHOICE -> ");
            choice = scInt.nextInt();
            choiceForMenu(choice, scInt, cri);
        }while(choice != 5);

        cri.closeConnection();


    }






}
