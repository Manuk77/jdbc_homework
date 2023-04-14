package org.example.hibernate_homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReading {
    public static List<String[]> read(String filePath){
        if (validString(filePath)){
            List<String[]> passengers = new ArrayList<>();
            try{
                BufferedReader bf = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = bf.readLine()) != null){
                    passengers.add(line.split(","));
                }
                return passengers;
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
       throw new NullPointerException("the given string must not be null or empty");

    }

    public static List<Integer> parserToInt(List<String[]> passenger, int col) {
        List<Integer> passId = new ArrayList<>();
        for (int i = 0; i < passenger.size(); ++i) {
            if (isDigit(passenger.get(i)[col])){
                passId.add(parser(passenger.get(i)[col]));
            }
        }
        return passId;
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
