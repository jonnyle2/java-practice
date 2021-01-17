public class Department {
    private static final int CAPACITY = 10;
    private String name;
    private Employee[] employees;
    private int size;

    public Department(String name){
        this.name = name;
        this.employees = new Employee[Department.CAPACITY];
        this.size = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public void addEmployee(Employee e) {
        if(this.size == Department.CAPACITY) {
            System.out.println("Department is at max capacity.");
        } else {
            if(e == null) {
                System.out.println("Cannot add null to department.");
            } else {
                this.employees[this.size++] = e;
            }
        }
    }

    public Employee[] getEmployees() {
        Employee[] eList = new Employee[this.size];
        for(int i = 0; i < this.size; i++) {
            eList[i] = this.employees[i];
        }
        return eList;
    }

    public Employee getById(int id) {
        for(Employee e : this.employees) {
            if(e != null) {
                if(e.getId() == id)
                    return e;
            }
        }
        return null;
    }

    public double totalSalary() {
        double total = 0;
        for(Employee e : this.employees) {
            if(e != null)
                total += e.getSalary();
        }
        return total;
    }

    public double averageSalary() {
        double total = this.totalSalary();
        return total/this.size;
    }

    public String toString() {
        return String.format("Department: %s", this.name);
    }
}