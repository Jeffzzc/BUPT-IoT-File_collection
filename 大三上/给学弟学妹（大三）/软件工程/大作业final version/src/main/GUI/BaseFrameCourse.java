/**
 * Title      : BaseFrameCourse.java
 * Description: This class is used to generate a course detail page.
 * Copyright  : Copyright (c) 2023/5/25
 * @author    : Zixuan Wang
 * @version   : 3.0
 */
package main.GUI;

import javax.swing.*;
import java.awt.*;

import main.Control.ButtonControl;
import main.Read.ModuleReader;
import main.Entity.Description;
import main.Entity.Module;
/**
 * The `BaseFrameCourse` class represents a frame for displaying course details.
 * It extends the `BaseFrame2` class.
 */
public class BaseFrameCourse extends BaseFrame2{
    public  JFrame frameBaseCourse;
    private JLabel course,lecturer,
            credits,exam,summary,aims,outcome,lecturerInfo,
            lecturerInfo1,creditsInfo,examInfo,summaryInfo,aimsInfo,outcomeInfo;
    /**
     * Constructs a `BaseFrameCourse` object with the specified module index.
     *
     * @param a The index of the module.
     */
    public BaseFrameCourse(int a) throws AWTException {
        super();

        frameBaseCourse=new JFrame("Learning Journey");
        frameBaseCourse.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameBaseCourse.setBounds(0,0,1368,864);
        frameBaseCourse.setVisible(false);

        ModuleReader.initialize();
        Module mo= ModuleReader.modules.get(a);
        Description des = mo.description;
        //super();

        title.setText(mo.moduleID);
        // Course label
        course = new JLabel(mo.moduleName);
        course.setFont(new Font("Times New Roman", Font.BOLD, 70));
        course.setBounds(0, 100, 1368, 70);
        course.setHorizontalAlignment(JLabel.CENTER);
        // Lecturer labels
        lecturer = new JLabel("Lecturer: ");
        lecturer.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lecturer.setBounds(150, 200, 200, 50);
        lecturerInfo = new JLabel(mo.teacherName);
        lecturerInfo.setFont(new Font("Times New Roman",Font.PLAIN,30));
        lecturerInfo.setBounds(300, 200, 400, 50);

        lecturerInfo1 = new JLabel(mo.teacherEmil);
        lecturerInfo1.setFont(new Font("Times New Roman",Font.PLAIN,30));
        lecturerInfo1.setBounds(300, 250, 400, 50);
        // Credits labels
        credits = new JLabel("Credits: ");
        credits.setFont(new Font("Times New Roman", Font.BOLD, 30));
        credits.setBounds(850, 200, 200, 50);

        creditsInfo = new JLabel(mo.credit);
        creditsInfo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        creditsInfo.setBounds(1000, 200, 200, 50);
        // Exam labels
        exam = new JLabel("Exam: ");
        exam.setFont(new Font("Times New Roman", Font.BOLD, 30));
        exam.setBounds(850, 250, 200, 50);

        examInfo = new JLabel(des.exam);
        examInfo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        examInfo.setBounds(950, 250, 400, 50);
         // Summary labels
        summary = new JLabel("Summary: ");
        summary.setFont(new Font("Times New Roman", Font.BOLD, 30));
        summary.setBounds(150, 300, 200, 50);
        summaryInfo = new JLabel("<html>"+des.summary+"</html>");
        summaryInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        summaryInfo.setBounds(150, 350, 1000, 70);

         // Aims labels
        aims = new JLabel("Aims: ");
        aims.setFont(new Font("Times New Roman", Font.BOLD, 30));
        aims.setBounds(150, 425, 200, 50);

        aimsInfo = new JLabel("<html>"+des.aim+"</html>");
        aimsInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        aimsInfo.setBounds(150, 475, 1000, 70);
         // Learning outcomes labels
        outcome = new JLabel("Learning Outcomes in Subject Specific Skills: ");
        outcome.setFont(new Font("Times New Roman", Font.BOLD, 30));
        outcome.setBounds(150, 550, 600, 50);
  
        outcomeInfo = new JLabel("<html>"+des.outcome+"<html>");
        outcomeInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        outcomeInfo.setBounds(150, 550, 1000, 200);


        contentPane.add(course);
        contentPane.add(lecturer);
        contentPane.add(credits);
        contentPane.add(exam);
        contentPane.add(summary);
        contentPane.add(aims);
        contentPane.add(outcome);


        contentPane.add(lecturerInfo);
        contentPane.add(lecturerInfo1);
        contentPane.add(creditsInfo);
        contentPane.add(examInfo);
        contentPane.add(summaryInfo);
        contentPane.add(aimsInfo);
        contentPane.add(outcomeInfo);

        line.setBounds(0,180,1368,3);
        back.setIcon(new ImageIcon("img/back2.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));

        background.setIcon(new ImageIcon("img/background2.jpg"));
        contentPane.add(background);
        frameBaseCourse.add(contentPane);

    }


}
