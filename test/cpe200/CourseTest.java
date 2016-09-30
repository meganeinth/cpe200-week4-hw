package cpe200;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CourseTest {

    private Course c;


    @Test
    public void defaultConstructor() throws Exception {
        c = new Course();
        assertEquals("TBA", c.getCourse_name());
        assertEquals("000000", c.getCourse_id());
        assertEquals("TBA", c.getLecturer());
        assertEquals(3, c.getMax_students() );
        assertEquals(0, c.getNo_students());
    }

    @Test
    public void parameterizedConstructor() throws Exception {
        c = new Course("Web Programming", "269103", "Kenneth Cosh", 40);
        assertEquals("Web Programming", c.getCourse_name());
        assertEquals("269103", c.getCourse_id());
        assertEquals("Kenneth Cosh", c.getLecturer());
        assertEquals(40, c.getMax_students() );
        assertEquals(0, c.getNo_students());
    }

    @Before
    public void setUp() throws Exception {
        c = new Course("CPE200-OOP", "261344", "Pruet Boonma", 60);
    }

    @Test
    public void validAttributes() throws Exception {
        // continue from setUp() method
        c.setCourse_id("261200");
        c.setLecturer(c.getLecturer() + " and Dome Potikanond");
        c.setMax_students(60);

        assertEquals("261200", c.getCourse_id());
        assertEquals("Pruet Boonma and Dome Potikanond", c.getLecturer());
        assertEquals(60, c.getMax_students());
    }

    @Test
    public void invalidAttributes() throws Exception {
        c.setCourse_name("");
        c.setCourse_id("CPE200");
        c.setLecturer("");
        c.setMax_students(-10);

        assertEquals("CPE200-OOP", c.getCourse_name());
        assertEquals("261344", c.getCourse_id());
        assertEquals("Pruet Boonma", c.getLecturer());
        assertEquals(60, c.getMax_students() );

        c.setCourse_id("2612003");
        assertEquals("261344", c.getCourse_id());
        c.setNo_students(80);
        assertEquals(0, c.getNo_students());
        c.setNo_students(-3);
        assertEquals(0, c.getNo_students());

    }

    @Test
    public void checkToStringNoStudent() throws Exception {
        c.setLecturer(c.getLecturer() + " and Dome Potikanond");
        c.setCourse_id("261200");
        assertEquals("CPE200-OOP (261200), Teacher: Pruet Boonma and Dome Potikanond"
                + ", has NO student, [maximum: 60]", c.toString());
    }

    @Test
    public void checkToStringOneStudent() throws Exception {
        c.setNo_students(1);
        c.setCourse_id("261200");
        c.setLecturer(c.getLecturer() + " and Dome Potikanond");
        assertEquals("CPE200-OOP (261200), Teacher: Pruet Boonma and Dome Potikanond"
                + ", has ONE student, [maximum: 60]", c.toString());
    }

    @Test
    public void checkToStringManyStudent() throws Exception {
        c.setNo_students(45);
        c.setCourse_id("261200");
        c.setLecturer(c.getLecturer() + " and Dome Potikanond");
        assertEquals("CPE200-OOP (261200), Teacher: Pruet Boonma and Dome Potikanond"
                + ", has 45 students, [maximum: 60]", c.toString());
    }

    @Test
    public void testEnrollRemoveStudent() throws Exception {
        Course c1 = new Course("C1","111111","L1");
        Student s1 = new Student("S1","590610701",1991);
        Student s2 = new Student("S2","590610702",1992);
        Student s3 = new Student("S3","590610703",1993);
        Student s4 = new Student("S4","590610704",1994);

            assertTrue(c1.enrollStudent(s1));
        assertFalse(c1.enrollStudent(s1));
        assertTrue(c1.enrollStudent(s2));
        assertTrue(c1.enrollStudent(s3));
        assertFalse(c1.enrollStudent(s4));

        assertTrue(c1.removeStudent(s2));
        assertFalse(c1.removeStudent(s2));
        assertTrue(c1.removeStudent(s3));
        assertFalse(c1.removeStudent(s4));
        assertTrue(c1.removeStudent(s1));
    }
}