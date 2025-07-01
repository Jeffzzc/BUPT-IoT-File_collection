/**
 * Title      : GradeReader.java
 * Description: This class is used to read data from grade.csv.
 * Copyright  : Copyright (c) 2023/5/13
 * @author      Wu Wei
 * @version     2.0
 */
package main.Read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import main.Entity.*;

public class GradeReader {
    public static ArrayList<Grade> grades;

    /**
     * function: initialize the function, read data from csv file
     */
    public static void initialize() {
        String csvFile = "./data/grade.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        grades = new ArrayList<Grade>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] grade = line.split(csvSplitBy);
                grades.add(new Grade(grade[0], grade[1], grade[2]));
            }
        } catch (FileNotFoundException e) {
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

//    public static void main(String args[]) {
//        GradeReader.initialize();
//        System.out.println(grades.size());
//    }
}
