package ModelLayer;

public class Admin extends User{

    private int salary;
    private String access_level;
    private String department;

    public Admin(int user_id, String user_name, String password, String email, int salary, String access_level, String department) {
        super(user_id, user_name, password, email);
        this.salary = salary;
        this.access_level = access_level;
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
