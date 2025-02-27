import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class EMS {

    private String companyName;
    private Manager manager;
    private Set<Employee> employees;
    private Map<Employee, Double> salaryMap;

    public EMS(String companyName, Manager manager) {
        this.companyName = companyName;
        this.manager = manager;
        employees = new HashSet<>();
        salaryMap = new HashMap<>();
    }

    public void registerEmployee(Employee employee){
    //employees setine eklemem lazım parametredeki employee'yi

        //I learned that sets dont allow duplicates, so I can add it like this. On the java set method documentation,
        //it is said that: "Adds the specified element to this set if it is not already present" so I can use it for if statement
        //"If this set already contains the element, the call leaves the set unchanged and returns false." direkt Oracle'dan alıntı
        if(employees.add(employee)){ //tek satırda var mı diye baktık, varsa boş geçtik yoksa ekledik ve if'i sağladık
        salaryMap.put(employee, 0.0 );
        //map'i boşuna yapmadık herhalde diyip ekledim key value pair olarak
            System.out.println("Successfully registered" + employee.getName() + " ("+ employee.getId() +")." );

        }else { System.out.println(employee.getName() + " ("+ employee.getId() +") already exists."); }
    }

    public void removeEmployee(Employee employee) {
        if(employees.remove(employee)){ //üstteki methodla aynı mantık
            salaryMap.remove(employee);
            System.out.println("Employee " + employee.getName() +  " ("+ employee.getId() +") removed.");
        }else{ System.out.println("Employee not found.");  }
    }

    public void calculateSalaries(String fileName){
        //I NEED TO READ FROM IT, only way I know is by using Scanner, FileReader, Paths.get()
        try {
            Scanner reader = new Scanner(  Paths.get(fileName)  );

            while (reader.hasNextLine()){

            String line = reader.nextLine();
            String[] nameNmoney = line.split("r");


            }

        } catch (IOException e) {
            System.out.println("Problem finding the .csv file.");
        }





    }







}
