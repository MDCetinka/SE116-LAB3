import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager("0", "Michael Scott");
        EMS ems = new EMS("Dunder Mifflin, Scranton", manager);

        Scanner scanner = new Scanner(System.in);

        String fileName;

        try {
            if(args[0] != null){
                fileName = args[0];
            }else{
                fileName = scanner.nextLine().trim();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Attention! This program can also run on command line.");
            System.out.println("Enter the CSV filepath in this format: ");
            System.out.println("C:\\Users\\hp\\IdeaProjects\\SE116_LAB3_MelissaDC\\src\\LAB3(2).csv");
            fileName = scanner.nextLine().trim();
        }



        ems.calculateSalaries(fileName);

        System.out.print("Enter salary limit (or 0 for no limit): ");
        double limit = scanner.nextDouble();
        manager.setSalaryLimit(limit);
//added another version of setSalaryLimit that has no parameters. This is fun!
        ems.askForSalaryLimit();

    }
}