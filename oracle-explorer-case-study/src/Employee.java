public class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.id = Employee.nextId++;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return String.format("Employee: %d %s %.2f", this.id, this.name, this.salary);
    }
}