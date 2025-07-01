/**
 * Title      : DataReader.java
 * Description: This class is used to read data from data.csv.
 * Copyright  : Copyright (c) 2023/5/13
 * @author      Wu Wei
 * @version     2.0
 */
package main.Read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import main.Entity.*;

public class DataReader {
    public static HashMap<String, Student> students;

    /**
     * function: initialize, read data from csv file.
     */
    public static void initialize() {
        String csvFile = "./data/data.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        students = new HashMap<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                students.put(data[1], new Student(data[0], data[1], data[2], data[3], data[4], data[5]));
            }
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * function: select student accounding to student ID
     * @param id of student for searching
     * @return student in object format
     */
    public static Student select(String id) {
        Student student = students.get(id);
        if (student == null) {
            System.out.println("Wrong student ID: " + id );
        }
        return student;
    }


/*    public static void main(String args[]) {
        CSVReader csv = new CSVReader();
        csv.initialize();
        csv.select("2021213000");
    }*/
}


