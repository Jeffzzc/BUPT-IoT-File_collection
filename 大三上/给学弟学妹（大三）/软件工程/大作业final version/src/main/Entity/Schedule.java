/**
 * Title      : Schedule.java
 * Description: This class represents the Schedule entity class.
 * Copyright  : Copyright (c) 2023/5/13
 * @author      Zhendong Cui
 * @version     4.0
 */
package main.Entity;

public class Schedule {
    public String data;
    public String moduleID;
    public String moduleName;
    public String Year;
    public String Semester;
    public String major;

    String schedule[][] = new String[7][14];

    public Schedule(String data, String moduleID, String moduleName, String Year, String Semester, String major) {
        this.data = data;
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.Year = Year;
        this.Semester = Semester;
        this.major = major;
    }

    /**
     * Description: Process schedule reading.
     */
    public String[][] regulation() {
        String schedule[][] = new String[7][14];
        String splityBy_1 = ";";
        String splityBy_2 = ".";
        String temp[] = this.data.split(splityBy_1);
        int a;
        int b;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].length() == 4) {
                String stringa = String.valueOf(temp[i].charAt(0));
                a = Integer.parseInt(stringa);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(temp[i].charAt(2));
                stringBuffer.append(temp[i].charAt(3));
                String stringb = stringBuffer.toString();
                b = Integer.parseInt(stringb);
            } else {
                String stringa = String.valueOf(temp[i].charAt(0));
                a = Integer.parseInt(stringa);
                String stringb = String.valueOf(temp[i].charAt(2));
                b = Integer.parseInt(stringb);
            }
            schedule[a - 1][b - 1] = this.moduleName;
        }
        return schedule;
    }

    @Override
    public String toString() {
        return "data:" + data;
    }

}
