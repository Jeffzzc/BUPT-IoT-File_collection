package Test.GUI;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.Entity.Student;
import main.Read.DataReader;

public class LoginPageViewTest {
    @Test
    void testRun() {
        DataReader.initialize();
        // Verify read success
        for (Student student : DataReader.students.values()) {
            assertEquals(student, DataReader.students.get(student.studentID));
        }
    }
}
