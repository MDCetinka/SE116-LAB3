import java.util.Scanner;

public class Manager {

    private String id;
    private String name;
    private double salaryLimit;

    public Manager(String id, String name) {
        this.id = id;
        this.name = name;
        salaryLimit = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalaryLimit() {
        return salaryLimit;
    }

    public void setSalaryLimit(double salaryLimit) {
        this.salaryLimit = salaryLimit;

    }

}
