/**
 * This class represents the GPA entity class.
 * It demonstrate the attricutes for GPA objects.
 * @author Tangxun Wu
 * @since 20 May 2023
 * @version 1.0
 */
package main.Entity;

public class GPA implements Comparable<GPA>{
    public String studentID;
    public int studentNum;
    public String position;
    public String year;
    public String major;
    public int ranking;
    public double gpa1;
    public double gpa2;

    public GPA(){

    }

    public GPA(int studentNum, String position, String studentID){
        this.studentNum = studentNum;
        this.position = position;
        this.studentID = studentID;
    }

    public GPA(String studentID, double gpa1, double gpa2, String major){
        this.studentID = studentID;
        this.gpa1 = gpa1;
        this.gpa2 = gpa2;
        this.major = major;
    }

    public void setRanking(int ranking){
        this.ranking =ranking;
    }

    public void setPosition(String position){
        this.position =position;
    }

    /**
     * Description: Ranked from high GPA to low GPA.
     */
    @Override
    public int compareTo(GPA o) {
        return (int) ((o.gpa1) - (this.gpa1));
    }
}
