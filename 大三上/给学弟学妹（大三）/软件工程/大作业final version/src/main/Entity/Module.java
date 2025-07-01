/**
 * Title      : Module.java
 * Description: This class represents the Module entity class.
 * Copyright  : Copyright (c) 2023/5/20
 * @author      Zhendong Cui & Tangxun Wu
 * @version     4.0
 */
package main.Entity;

public class Module implements Comparable<Module>{
    public String moduleID;
    public String moduleName;
    public String year;
    public String semester;
    public String type;
    public String credit;
    public String major;
    public Schedule schedule;
    public String teacherName;
    public String teacherEmil;
    public Description description;
    public String imgAdd;
    public String imgPath;

    public Module(String moduleID, String moduleName, String year, String semester, String type, 
    String credit, String major, Schedule schedule, String teacherName, String teacherEmail, Description description ) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.year = year;
        this.semester = semester;
        this.type = type;
        this.credit = credit;
        this.major = major;
        this.schedule = schedule;
        this.teacherName = teacherName;
        this.teacherEmil = teacherEmail;
        this.description = description;
    }

    @Override
    public String toString() {
        return moduleID;
    }

    /**
     * Description: Ranked from high credit to low credit.
     */
    @Override
    public int compareTo(Module a) {
        return Integer.parseInt(a.credit) - Integer.parseInt(this.credit);
    }

} 



