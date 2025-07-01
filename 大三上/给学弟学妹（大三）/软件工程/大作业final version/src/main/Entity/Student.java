/**
 * Title      : Student.java
 * Description: This class represents the Student entity class.
 * Copyright  : Copyright (c) 2023/4/23
 * @author      Zhendong Cui
 * @version     2.0
 */
package main.Entity;
import java.util.ArrayList;

public class Student {

    public String studentName;
    public String studentID;
    public String password;
    public void SetPassword(String newPassword){
        this.password=newPassword;
    }

    public String year;
    public String semester;
    public String major;
    ArrayList<Award> awards = new ArrayList<Award>();

    public Student(String studentName,String studentID,String password,String year,String semester,String major){
        this.studentName=studentName;
        this.password=password;
        this.studentID=studentID;
        this.year=year;
        this.semester=semester;
        this.major=major;
    }

}