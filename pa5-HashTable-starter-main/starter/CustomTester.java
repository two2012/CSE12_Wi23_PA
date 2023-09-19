/*
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: JDK 17 Doc
 *
 * This is the custom tester for CSE 12 PA5 in Winter 2023.
 * It contains sanity checks on all 3 classes.
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

/**
 * This is the custom tester for CSE 12 PA5 in Winter 2023.
 * It contains sanity checks on all 3 classes.
 */
public class CustomTester {

    /*----------------Student----------------*/

    /**
     * Call equals when the argument is a
     * 1. non-Student object
     * 2. different Student object
     * 3. null
     */
    @Test
    public void testStudentEquals() {

        Student s1 = new Student("Tim", "Smith", "123456789");
        Student s3 = null;
        Student s4 = new Student("Tim", "Smith", "123456781");
        Student s5 = new Student("Tim", "Tmith", "123456789");
        Student s6 = new Student("Pim", "Smith", "123456789");

        assertFalse(s1.equals(new String("Smith")));
        assertFalse(s1.equals(s4));
        assertFalse(s1.equals(s5));
        assertFalse(s1.equals(s6));
        assertFalse(s1.equals(s3));

    }

    /**
     * Call compareTo to compare two Students that
     * 1. have the same last name and same first name. this
     * Student should have a PID that is lexigraphically
     * smaller than the other Student.
     * 2. have the same PID and same first name. this
     * Student should have a last name that is lexigraphically
     * smaller than the other Student.
     * 3. have the same last name and same PID. this
     * Student should have a first name that is lexigraphically
     * smaller than the other Student.
     * 4. with null
     * 5. two same Student
     */
    @Test
    public void testStudentCompareTo() {

        Student s1 = new Student("Tim", "Smith", "123456789");
        Student s2 = new Student("Tim", "Smith", "123456789");
        Student s3 = null;
        Student s4 = new Student("Tim", "Smith", "123456799");
        Student s5 = new Student("Tim", "Zmith", "123456789");
        Student s6 = new Student("Vim", "Smith", "123456789");

        int result1 = s1.compareTo(s4);
        int result2 = s1.compareTo(s5);
        int result3 = s1.compareTo(s6);
        int result4 = s1.compareTo(s2);

        try {
            s1.compareTo(s3);
            fail();
        } catch (NullPointerException e) {

        }

        assertTrue("PID different s1 < s4", result1 < 0);
        assertTrue("last name different s1 < s5", result2 < 0);
        assertTrue("first name different s1 < s6", result3 < 0);
        assertTrue("same two student", result4 == 0);
    }

    /*----------------Course----------------*/

    /**
     * Attempt to drop
     * 1. a non-existing student with a non-empty course roster.
     * This means the course should have at least 1 student enrolled already.
     * 2. if student is null
     * 3. drop multiple times
     */
    @Test
    public void testCourseDrop() {
        Student s1 = new Student("Aim", "Smith", "123456789");
        Student s2 = new Student("Bim", "Tmith", "223456799");
        Student s4 = new Student("Cim", "Tmith", "323456799");
        Student s5 = new Student("Dim", "Tmith", "423456799");
        Student s6 = new Student("Eim", "Tmith", "523456799");
        Student s7 = new Student("Gim", "Tmith", "623456799");
        Student s8 = new Student("Gim", "Tmith", "723456799");
        Student s3 = null;

        Course cse11 = new Course("CSE", "11", "Java Intro", 100);
        cse11.enroll(s1);
        cse11.enroll(s2);
        cse11.enroll(s4);
        cse11.enroll(s5);
        cse11.enroll(s6);
        cse11.enroll(s7);
        cse11.enroll(s8);

        Course cse12 = new Course("CSE", "12", "Data Structure", 100);
        cse12.enroll(s1);

        assertFalse("non-existing student with a non-empty course", cse12.drop(s2));
        try {
            cse12.drop(s3);
            fail();
        } catch (IllegalArgumentException e) {
        }

        assertTrue("existing student with a non-empty course", cse11.drop(s1));
        assertTrue("existing student with a non-empty course", cse11.drop(s2));
        assertTrue("existing student with a non-empty course", cse11.drop(s4));
        assertTrue("existing student with a non-empty course", cse11.drop(s5));
        assertEquals(3, cse11.enrolled.size());
        assertFalse(cse11.enrolled.contains(s1));
        assertFalse(cse11.enrolled.contains(s2));
        assertFalse(cse11.enrolled.contains(s4));
        assertFalse(cse11.enrolled.contains(s5));
    }

    /**
     * Attempt to enroll
     * 1. a valid student into a course that is already at
     * maximum capacity(false)
     * 2. null(exception)
     * 3. exsisting student to non-full course(false)
     * 
     */
    @Test
    public void testCourseEnroll() {
        Student s1 = new Student("Aim", "Smith", "123456789");
        Student s2 = new Student("Bim", "Tmith", "223456799");
        Student s4 = new Student("Cim", "Tmith", "323456799");
        Student s5 = new Student("Dim", "Tmith", "423456799");
        Student s6 = new Student("Eim", "Tmith", "523456799");
        Student s7 = new Student("Gim", "Tmith", "623456799");
        Student s8 = new Student("Him", "Tmith", "723456799");
        Student s9 = new Student("Iim", "Tmith", "823456799");
        Student s3 = null;

        Course cse11 = new Course("CSE", "11", "Java Intro", 7);
        cse11.enroll(s1);
        cse11.enroll(s2);
        cse11.enroll(s4);
        cse11.enroll(s5);
        cse11.enroll(s6);
        cse11.enroll(s7);

        try {
            cse11.enroll(s3);
            fail();
        } catch (IllegalArgumentException e) {
        }

        assertFalse("exsting student to non-full course", cse11.enroll(s7));
        assertTrue(cse11.enrolled.contains(s7));
        cse11.enroll(s8);

        assertFalse("exsting student to non-full course", cse11.enroll(s9));
        assertFalse("exsting student to non-full course", cse11.enrolled.contains(s9));

    }

    /**
     * Call getRoster on a course with a large number of
     * enrolled students. (We suggest testing on a course
     * that has 100 validly enrolled students.)
     */
    @Test
    public void testCourseGetRoster() {

        Course cse11 = new Course("CSE", "11", "Java Intro", 100);
        ArrayList<Student> students1 = new ArrayList<Student>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student("firstName" + i, "lastName" + i, "PID" + i);
            students1.add(student);
            cse11.enroll(student);
        }

        Collections.sort(students1);

        assertTrue(students1.equals(cse11.getRoster()));
        assertEquals(100, cse11.enrolled.size());
    }

    /**
     * test method cancel 
     * 1. non-empty course (size = 0)
     * 2. empty course (size = 0)
     */
    @Test
    public void testCourseCancel(){
        Course cse11 = new Course("CSE", "11", "Java Intro", 100);
        for (int i = 0; i < 100; i++) {
            Student student = new Student("firstName" + i, "lastName" + i, "PID" + i);
            cse11.enroll(student);
        }

        cse11.cancel();
        assertEquals(0, cse11.enrolled.size());

        cse11.cancel();
        assertEquals(0, cse11.enrolled.size());
    }

    /**
     * test method getEnrollCount 
     * 1. non-empty course 
     * 2. empty course 
     */
    @Test
    public void testCourseEnrollCount(){
        Course cse11 = new Course("CSE", "11", "Java Intro", 100);
        Course cse12 = new Course("CSE", "12", "Data Structure", 100);
        for (int i = 0; i < 50; i++) {
            Student student = new Student("firstName" + i, "lastName" + i, "PID" + i);
            cse11.enroll(student);
        }

        
        assertEquals(50, cse11.getEnrolledCount());
        assertEquals(0, cse12.getEnrolledCount());
    }

    /**
     * test method getAvailableSeats
     * 1. non-empty course 
     * 2. empty course 
     */
    @Test
    public void testCourseAvailableSeats(){
        Course cse11 = new Course("CSE", "11", "Java Intro", 100);
        Course cse12 = new Course("CSE", "12", "Data Structure", 100);
        for (int i = 0; i < 50; i++) {
            Student student = new Student("firstName" + i, "lastName" + i, "PID" + i);
            cse11.enroll(student);
        }

        
        assertEquals(50, cse11.getAvailableSeats());
        assertEquals(100, cse12.getAvailableSeats());
    }
    /**
     * test method getStudent 
     */
    @Test
    public void testCourseGetStudent(){
        Course cse11 = new Course("CSE", "11", "Java Intro", 100);
        for (int i = 0; i < 50; i++) {
            Student student = new Student("firstName" + i, "lastName" + i, "PID" + i);
            cse11.enroll(student);
        }

        HashSet copy = cse11.getStudents();
        
        assertFalse(copy == cse11.enrolled);
        
    }

    /*----------------Sanctuary----------------*/

    /**
     * Call the Sanctuary constructor with a 
     * 1. negative argument for maxAnimals
     * 2. negative argument for maxSpecies
     * 3. maxAnimals < maxSpecies
     */
    @Test
    public void testSanctConstructor(){

        try {
            Sanctuary s1 = new Sanctuary(-1, 50);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
        try {
            Sanctuary s2 = new Sanctuary(100, -1);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
        try {
            Sanctuary s3 = new Sanctuary(10, 50);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
    }

    /**
     * rescue animals from an existing species, 
     * where rescuing num animals will cause the number of animals to exceed 
     * the sanctuary's max capacity. 
     * This means only some of the animals should be rescued successfully.
     */
    @Test
    public void testSanctRescuePartial(){

        Sanctuary s = new Sanctuary(100, 2);
        s.sanctuary.put("species1", 90);
        

        int n = s.rescue("species1", 20);

        assertEquals(10, n);
    }

    /**
     * rescue a new non-null species when the 
     * sanctuary is already at the max capacity for species.
     */
    @Test
    public void testSanctRescueMaxSpecies(){

        Sanctuary s = new Sanctuary(100, 2);
        s.sanctuary.put("species1", 50);
        s.sanctuary.put("species2", 40);

        int n = s.rescue("species3", 20);
        assertEquals(20, n);
        assertEquals(2, s.getTotalSpecies());
        assertFalse(s.sanctuary.containsKey("species3"));

    }

    /**
     * test method rescue with
     * 1. null species
     * 2. num < 0
     */
    @Test
    public void testSanctRescue(){

        Sanctuary s = new Sanctuary(100, 2);
        s.sanctuary.put("species1", 50);
        s.sanctuary.put("species2", 40);

        try {
            s.rescue(null, 20);
            fail();
        } catch (IllegalArgumentException e) {
            
        }

        try {
            s.rescue("species3", -5);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
    }

    /**
     * release some (not all) of the animals of an 
     * existing species in the sanctuary.
     */
    @Test
    public void testSanctReleasePartial(){
        Sanctuary s = new Sanctuary(100, 5);
        s.sanctuary.put("species1", 50);
        s.sanctuary.put("species2", 40);

        s.release("species1", 30);

        assertEquals(2, s.getTotalSpecies());
        assertEquals(60, s.getTotalAnimals());
        assertEquals(new Integer(20), (Integer)s.sanctuary.get("species1"));
    }

    /**
     * Attempt to release more animals than exists for 
     * a specific animal species in the sanctuary. 
     * The existing number of animals for this species should be non-zero.
     */
    @Test
    public void testSanctReleaseTooMany(){

        Sanctuary s = new Sanctuary(1000, 2);
        s.sanctuary.put("species1", 50);
        s.sanctuary.put("species2", 40);

        try {
            s.release("species1", 60);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
        

        

    }

     /**
     * tset method release 
     * 1. null species
     * 2. species not exist
     * 3. num <= 0
     * 4. num > maxAnimals
     * 
     */
    @Test
    public void testSanctRelease(){

        Sanctuary s = new Sanctuary(1000, 2);
        s.sanctuary.put("species1", 50);
        s.sanctuary.put("species2", 40);

        try {
            s.release(null, 12);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
        try {
            s.release("species3", 12);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
        try {
            s.release("species1", -10);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
        try {
            s.release("species1", 1002);
            fail();
        } catch (IllegalArgumentException e) {
            
        }

    }

   
}
