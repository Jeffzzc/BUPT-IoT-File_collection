/**
 * Title      : GPAView.java
 * Description: This class represents the GPA calculation in the GUI.
 * It allows users to check their GPA in grade page.
 * Copyright  : Copyright (c) 2023/5/20
 * @author Tangxun Wu
 * @version 1.2
 */
package main.GUI;

import main.Entity.GPA;
import main.Entity.Grade;
import main.Read.GradeReader;
import main.Read.ModuleReader;

import java.util.ArrayList;
import java.util.Collections;

// GPA calculation: Read all student's data for calculation and ranking.
// Each person's grade point average is calculated as the sum of course credits already taken plus the grades earned divided by the total grade points.
// Another 4-point gpa conversion can be performed after the calculation.
// Sort: Read the current number of students in the same grade and the average scores of students in the same grade, sort by the average scores, and finally read the order of the students according to the average scores.

public class GPAView {
    public static ArrayList<GPA> studentGPAView;
    public ArrayList<String> stuID;
    public double totalGrade1 = 0.0;
    public double totalCredit = 0.0;
    public double totalGrade2 = 0.0;
    public static int studentNum;
    public static String major;

    public GPAView(){
        studentGPAView = new ArrayList<GPA>();
        stuID = new ArrayList<String>();

        // Read the data
        GradeReader.initialize();
        ModuleReader.initialize();

        // Get all student IDs and remove duplicate IDs
        for(Grade grade : GradeReader.grades){
            stuID.add(grade.studentID);
        }
        for (int i = 0; i < stuID.size() - 1; i++) {
            for (int j = i + 1; j < stuID.size(); j++) {
                if (stuID.get(i).equals(stuID.get(j))) {
                    stuID.remove(j);
                }
            }
        }

        // Data matching, GPA calculated according to student ID traversal
        for (int i = 0; i < stuID.size() - 1; i++) {
            for (Grade grade : GradeReader.grades){
                if(stuID.get(i).equals(grade.studentID)){
                    for(main.Entity.Module module : ModuleReader.modules){
                        if(module.moduleID.equals(grade.moduleID)){
                            totalGrade1 += Integer.parseInt(grade.grade) * Integer.parseInt(module.credit);
                            totalGrade2 += (Integer.parseInt(grade.grade)/20-1) * Integer.parseInt(module.credit);
                            totalCredit += Integer.parseInt(module.credit);
                            major = module.major;
                        }
                    }
                }
            }
            studentGPAView.add(new GPA(stuID.get(i),totalGrade1/totalCredit,totalGrade2/totalCredit, major));
            totalGrade1 = 0;
            totalGrade2 = 0;
            totalCredit = 0;
            major = "";
        }

        // Do ranking
        for(int i = 0; i < studentGPAView.size() - 1; i++){
            if(!studentGPAView.get(i).major.equals("")){
                studentNum++;
            }
        }
        Collections.sort(studentGPAView);
    }
}
