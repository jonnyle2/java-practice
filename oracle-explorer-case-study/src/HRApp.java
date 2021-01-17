public class HRApp {
    public static void main(String[] args) {
        Employee e1 = new Employee("Jonny", 50645.31);
        Employee e2 = new Employee("Hanh", 65456.45);
        Department d = new Department("Admin");

        System.out.println(e1.toString());
        System.out.println(e2.toString());
        System.out.println(d.toString());
    }
}
