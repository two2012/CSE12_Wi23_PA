/*
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: JDK 17 Doc
 *
 * this is a student class which has info. of student such as 
 * fist name, last name and PID number
 */

 import java.util.Objects;

 
 /**
  * this is a student class which has info. of student such as 
  * fist name, last name and PID number
  */
public class Student implements Comparable<Student> {

    // instance variables
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Initialize the student’s information. Throw an
     * IllegalArgumentException if any of the arguments are null.
     * 
     * @param firstName first name of student
     * @param lastName  last name of student
     * @param PID       PID number of student
     */
    public Student(String firstName, String lastName, String PID) {
        // check if any argument is null
        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException();
        } else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.PID = PID;
        }
    }

    /**
     * Return the student’s first name
     * 
     * @return student’s first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Return the student’s last name
     * 
     * @return student’s last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Return the student’s PID number
     * 
     * @return student’s PID number
     */
    public String getPID() {
        return this.PID;
    }

    /**
     * Return the hash value generated
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, PID);

    }

    /**
     * Returns true if o is
     * 1) not null,
     * 2) a Student object,
     * 3) all the instance variables of o equal the corresponding
     * instance variables of the current Student object. Otherwise,
     * return false.
     * 
     * @return equal result
     */
    @Override
    public boolean equals(Object o) {
        // check if o is null or not Student type
        if (o != null && o instanceof Student) {
            Student s = (Student) o;
            // check if first name, last name and PID are eqauls
            if (this.firstName.equals(s.getFirstName()) &&
                    this.lastName.equals(s.getLastName()) &&
                    this.PID.equals(s.getPID())) {

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Compare this with another Student in the order of 
     * lastName, firstName, and PID, using String::compareTo. 
     * @param o Student needs to compare
     * @return result of comparesion
     */
    @Override
    public int compareTo(Student o) {
        // check if o is null
        if (o == null) {
            throw new NullPointerException();
        }

        // compare last name
        int compl = 
            this.lastName.compareTo(o.getLastName());
        if (compl != 0) {
            return compl;
        }

        // compare first name
        int compf = 
            this.firstName.compareTo(o.getFirstName());
        if (compf != 0) {
            return compf;
        }

        // compare PID
        return this.PID.compareTo(o.getPID());
    }

    
}
