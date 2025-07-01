package Test.Read;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.Entity.Student;
import main.Read.DataReader;

public class DataReaderTest {
    @Test
    void testInitialize() {
        DataReader.initialize();
        // Verify read success
        for (Student student : DataReader.students.values()) {
            assertEquals(student, DataReader.students.get(student.studentID));
        }
    }

    @Test
    void testSelect() {
        DataReader.initialize();
        for (Student student : DataReader.students.values()) {
            assertEquals(DataReader.select(student.studentID),student); 
        }
    }
}
