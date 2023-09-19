
/*
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: JDK 17 Doc
 *
 * this is a course class which has info. of course such as 
 * student enrolled, capacity, department, course number, and
 * description
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * this is a course class which has info. of course such as
 * student enrolled, capacity, department, course number, and
 * description
 */
public class Course {

    // instance variables
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * Initialize the course’s information with an initial
     * enrollment of 0 students.
     * 
     * @param department  department of course
     * @param number      course number
     * @param description course description
     * @param capacity    course capacity
     */
    public Course(String department, String number, String description, int capacity) {
        // check if any of the arguments are null
        // or capacity is 0 or negative
        if (department == null ||
                number == null ||
                description == null ||
                capacity <= 0) {

            throw new IllegalArgumentException();
        } else {
            this.department = department;
            this.number = number;
            this.description = description;
            this.capacity = capacity;
            this.enrolled = new HashSet<Student>();
        }
    }

    /**
     * Return the capacity of course
     * 
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * return the department of course
     * 
     * @return course department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * return the course number
     * 
     * @return course number
     */
    public String getNumber() {
        return number;
    }

    /**
     * return the course description
     * 
     * @return course description
     */
    public String getDescription() {
        return description;
    }

    /**
     * If there is room in this course and the student is
     * not currently enrolled, add the student to the course.
     * Return true for successful enrollment and false otherwise
     * 
     * @param student to be add the course
     * @return added result
     */
    public boolean enroll(Student student) {
        // check if student is null
        if (student == null) {
            throw new IllegalArgumentException();
        } else {
            // check if course has room and student not enrolled
            if (this.capacity - this.enrolled.size() > 0 &&
                    !this.enrolled.contains(student)) {

                this.enrolled.add(student);
                return true;
            }
        }
        return false;
    }

    /**
     * If the student is enrolled in the course, 
     * drop them from the course. Return true for 
     * successfully dropping student and false otherwise 
     * @param student to be drop the course
     * @return drop result
     */
    public boolean drop(Student student){

        // check if student is null
        if (student == null){
            throw new IllegalArgumentException();
        } else {
            // remove student if student enrolled the course
            if (this.enrolled.contains(student)){
                this.enrolled.remove(student);
                return true;
            }
        }
        return false;
    }

    /**
     * If the course is canceled, all of the students are 
     * dropped from the course. Remove all the students 
     * from the course.
     */
    public void cancel(){
        this.enrolled.clear();

    }

    /**
     * If the course is at its capacity, return true. 
     * Otherwise, return false
     * @return if the course is full 
     */
    public boolean isFull(){
         if (this.enrolled.size() == this.capacity){
            return true;
         } else {
            return false;
         }
    }

    /**
     * Return the current number of enrolled students.
     * @return number of enrolled students
     */
    public int getEnrolledCount(){
        return this.enrolled.size();
    }

    /**
     * Return the number of students that can 
     * still enroll in the course
     * @return Available Seats in course
     */
    public int getAvailableSeats(){

        return this.capacity - this.enrolled.size(); 

    }

    /**
     * Return a shallow copy of all the students enrolled in this course. 
     * @return list of enrolled student
     */
    public HashSet<Student> getStudents(){
        // create a new hashset
        HashSet<Student> copyEnrolled = new HashSet<>();
        // copy the original enrolled to new hashset
        copyEnrolled = (HashSet<Student>)this.enrolled.clone();

        return copyEnrolled;

    }

    /**
     * Turn the collection of enrolled students into an 
     * ArrayList<Student> collection by iterating through 
     * the set using the iterator or a for-each loop. 
     * @return ArrayList of Student
     */
    public ArrayList<Student> getRoster(){

        ArrayList<Student> roster = new ArrayList<>();

        // copy all element from enrolled in to roster
        for (Student student : this.enrolled) {
            roster.add(student);
        }
        // sort roster
        Collections.sort(roster);

        return roster;

    }

    @Override
    public String toString() {
        return department + " " + number + " [" + capacity + "] " + description;
    }
    
}
