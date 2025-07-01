/**
 * Title      : Grade.java
 * Description: This class represents the Grade entity class.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Tangxun Wu & Zhendong Cui
 * @version     3.0
 */
package main.Entity;

public class Grade implements Comparable<Grade>{
    public String studentID;
    public String moduleID;
    public String grade;

    public Grade(String moduleID, String studentID, String grade) {
        this.studentID = studentID;
        this.moduleID = moduleID;
        this.grade = grade;
    }

    /**
     * Description: Ranked from high grade to low grade.
     */
    @Override
    public int compareTo(Grade o) {
        return Integer.parseInt(o.grade) - Integer.parseInt(this.grade);
    }
}