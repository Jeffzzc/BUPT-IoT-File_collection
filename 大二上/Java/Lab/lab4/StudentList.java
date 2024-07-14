import java.util.ArrayList;

/**
 *  A class that holds a list of students.
 *
 *  @author     
 *  @date
 */
public class StudentList {
    private ArrayList<Student> list; // instance variable

    /**
     *  Constructor
     */
    public StudentList() { list = new ArrayList<Student>(); }
	
    /**
     *  A method to print off all ArrayList elements.
     */
    public void printList() {
        System.out.println("--Begin--");
	// WRITE YOUR CODE HERE
	System.out.println("--End--");
    }
	
    /**
     *  A method to add a student to the list.
     *  @param  The student.
     */
    public void addToList(Student stu) {
        // WRITE YOUR CODE HERE
    }

    /**
     *  A method to remove a student from the list.
     *  @param  The student.
     */
    public void removeFromList(Student stu) {
        // WRITE YOUR CODE HERE
    }
	
    /**
     *  A main() method to test.
     */
    public static void main(String[] args) {
        // Create an instance of the class.
	StudentList studentList = new StudentList();

	// Create 3 student objects.
	Student stu1 = new Student("John", "Smith", "js@qmul.ac.uk", 2021);
	Student stu2 = new Student("Mary", "Davis", "md@qmul.ac.uk", 2022);
	Student stu3 =  // WRITE YOUR OWN DETAILS HERE
		
	// Add the 3 students to the list.
	studentList.addToList(stu1);
	studentList.addToList(stu2);
	studentList.addToList(stu3);

	// Print the list.
	studentList.printList();
			
	// Remove the student "Mary Davis"
	studentList.removeFromList(stu2);
		
	// Print the list again
	studentList.printList();
    }
}