package ModelLayer;

import java.io.Serializable;

/**
 * Represents an Admin user in the system, extending the base User class.
 * An Admin has additional fields for salary, access level, and department.
 */


public class Admin extends User{


    private int salary;
    private int access_level;
    private String department;

    /**
     * Constructs an Admin object with specified details.
     *
     * @param user_id      the unique ID of the user
     * @param user_name    the name of the user
     * @param password     the password of the user
     * @param email        the email of the user
     * @param salary       the salary of the admin
     * @param access_level the access level of the admin
     * @param department   the department of the admin
     */

    public Admin(int user_id, String user_name, String password, String email, int salary, int access_level, String department) {
        super(user_id, user_name, password, email);
        this.salary = salary;
        this.access_level = access_level;
        this.department = department;
    }

    public Admin() {
        super(0, "", "", "");
        this.salary = 0;
        this.access_level = 0;
        this.department = "";
    }

    /**
     * Gets the salary of the admin.
     *
     * @return the salary of the admin
     */
    public int getSalary() {
        return salary;
    }


    /**
     * Sets the salary of the admin.
     *
     * @param salary the salary to be set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Gets the access level of the admin.
     *
     * @return the access level of the admin
     */
    public int getAccess_level() {
        return access_level;
    }

    /**
     * Sets the access level of the admin.
     *
     * @param access_level the access level to be set
     */
    public void setAccess_level(int access_level) {
        this.access_level = access_level;
    }

    /**
     * Gets the department of the admin.
     *
     * @return the department of the admin
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the admin.
     *
     * @param department the department to be set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Returns a string representation of the Admin object, including salary, access level, and department.
     *
     * @return a string representation of the Admin object
     */
    @Override
    public String toString() {
        return super.toString() + "Admin{" +
                "salary=" + salary +
                ", access_level='" + access_level + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

//    @Override
//    public String getType() {
//        return "Admin";
//    }
//
//    @Override
//    public String toCSV() {
//        return String.join(";",
//                getType(), String.valueOf(getUser_id()), getUser_name(), getPassword(), getEmail(),
//                String.valueOf(salary), String.valueOf(access_level), department
//        );
//    }
//
//    public static Admin fromCSV(String csvLine) {
//        String[] parts = csvLine.split(";", 8);
//        return new Admin(
//                Integer.parseInt(parts[1]), parts[2], parts[3], parts[4],
//                Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]
//        );
//    }
}


