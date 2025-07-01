package Test.Read;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.Entity.Grade;
import main.Read.GradeReader;

public class GradeReaderTest {
    @Test
    void testInitialize() {
        GradeReader.initialize();
        int i = 0;
        for (Grade grade : GradeReader.grades) {
            assertEquals(grade, GradeReader.grades.get(i));
            i++;
        }
    }
}
