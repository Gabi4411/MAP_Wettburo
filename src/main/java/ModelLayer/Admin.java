package ModelLayer;

import java.util.Objects;

/**
 * Represents an Admin user in the system, extending the base User class.
 * An Admin has additional fields for salary, access level, and department.
 */
public class Admin extends User {

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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAccess_level() {
        return access_level;
    }

    public void setAccess_level(int access_level) {
        this.access_level = access_level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "salary=" + salary +
                ", access_level=" + access_level +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return salary == admin.salary &&
                access_level == admin.access_level &&
                Objects.equals(department, admin.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, access_level, department);
    }
}
