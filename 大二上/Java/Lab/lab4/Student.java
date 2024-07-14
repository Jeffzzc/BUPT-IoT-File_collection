/**
 *  A class that represents a student.
 *  @author  Ling Ma
 *  @created 2009
 *  @version 1.0
 *  @author  Paula Fonseca
 *  @version 1.1
 */
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private int year; // Year of registration on the course.

    /**
     *  Constructor
     *  @param  first name, last name, email and year of registration
     */
    public Student(String firstName, String lastName, String email, int year) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.year = year;
    }

    /**
     *  Get the first name.
     *  @return  The student's first name.
     */
    public String getFirstName() {
      return firstName;
    }

    /**
     *  Get the last name.
     *  @return  The student's last name.
     */
    public String getLastName() {
      return lastName; 
    }

    /**
     *  A toString() method to give a String representation of a Student.
     *  @return  The String representation of a Student.
     */
    public String toString() {
      String fullName = firstName + " " + lastName;
      return "Name: " + fullName + " Email: " + email + " Year: " + year;
    }	
}