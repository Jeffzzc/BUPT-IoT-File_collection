/**
 * Title      : Description.java
 * Description: This class represents the Module Description entity class.
 * Copyright  : Copyright (c) 2023/4/5
 * @author      Zhendong Cui
 * @version     1.0
 */
package main.Entity;

public class Description {
    public String moduleID;
    public String moduleName;
    public String summary;
    public String aim;
    public String outcome;
    public String exam;

    public Description(String moduleID, String moduleName, String summary, String aim, String outcome, String exam) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.summary = summary;
        this.aim = aim;
        this.outcome = outcome;
        this.exam = exam;
    }
}