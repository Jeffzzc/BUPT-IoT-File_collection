/**
 * Title      : ModuleReader.java
 * Description: This class is used to read data from curriculum.csv.
 * Copyright  : Copyright (c) 2023/5/13
 * @author      Wu Wei
 * @version     2.0
 */
package main.Read;

import main.Entity.Description;
import main.Entity.Module;
import main.Entity.Schedule;
import main.Entity.Student;
import main.Control.ButtonControl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModuleReader {
    public static ArrayList<Module> modules;

    /**
     * function: initialize the function, read data from csv file
     */
    public static void initialize() {
        String csvFile = "./data/curriculum.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        modules = new ArrayList<Module>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String module[] = line.split(csvSplitBy);
                modules.add(new Module(module[0], module[1], module[2], module[3], module[4], module[5], module[6],
                        new Schedule(module[7], module[0], module[1],module[2],module[3],module[6]), module[8], module[9],
                        new Description(module[0], module[1], module[11], module[12], module[13], module[10])));
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

    /**
     * function: get Modules
     * @return modules
     */
    public static ArrayList<Module> getModules() {
        return modules;
    }

    /**
     * funciton: generate the schedule
     * @param array1 schedule
     * @param array2 add new course to the schedule
     * @return updated schedule
     */
    public static String[][] addArrays(String[][] array1, String[][] array2) {
        String[][] result = new String[7][14];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                // 进行相加操作
                if (array1[i][j] == null && array2[i][j] != null) {
                    result[i][j] = array2[i][j];
                } else if (array1[i][j] != null && array2[i][j] == null) {
                    result[i][j] = array1[i][j];
                } else {
                    result[i][j] = null;
                }
            }
        }

        return result;
    }

    /**
     * function: transfer the schedule into object format to show on the GUI
     * @return the schedule in object format
     */
    public static Object[][] object(String year, String semester) {
        DataReader.initialize();
        ModuleReader.initialize();
        Student student = DataReader.select(ButtonControl.currentStudent);
        String[][] scheduleAll = new String[7][14];

        for (Module module : modules) {
            if (student.major.equals(module.major) && year.equals(module.year)
                    && semester.equals(module.semester)) {
                Schedule schedule = module.schedule;
                scheduleAll = addArrays(scheduleAll, schedule.regulation());
            }
        }

        Object[][] data = new Object[14][8];

        for (int i = 0; i < scheduleAll.length; i++) {
            for (int j = 0; j < scheduleAll[i].length; j++) {
                String schedule = scheduleAll[i][j];
                Object value = null;

                if (schedule != null) {
                    value = (Object) schedule;
                }
                data[j][i + 1] = value;
            }
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                Object element = data[i][j];
            }
        }
        return data;
    }


//    public static void main(String args[]) {
//        ModuleReader.initialize();
//        String[][] scheduleAll = new String[7][14];
//        for (Module module : modules) {
//            Schedule schedule = module.schedule;
//            System.out.println(schedule.regulation());
//            scheduleAll = addArrays(scheduleAll, schedule.regulation());
//        }
//
//        System.out.println(scheduleAll);
//        for (String[] row : scheduleAll) {
//            for (String value : row) {
//                System.out.print(value + " ");
//            }
//            System.out.println();
//        }
//    }
}
