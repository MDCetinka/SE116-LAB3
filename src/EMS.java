import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

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
            System.out.println("Successfully registered " + employee.getName() + " (ID: "+ employee.getId() +")." );

        }// else { System.out.println(employee.getName() + " ("+ employee.getId() +") already exists."); }
    }

    public void removeEmployee(Employee employee) {
        if(employees.remove(employee)){ //üstteki methodla aynı mantık
            salaryMap.remove(employee);
            System.out.println("Employee " + employee.getName() +  " ( ID: "+ employee.getId() +") removed.");
        }else{ System.out.println("Employee not found.");  }
    }

    public void calculateSalaries(String fileName){
        //I NEED TO READ FROM IT, only way I know is by using Scanner, FileReader, Paths.get()
        try {
            Scanner sc = new Scanner( Paths.get(fileName) );

            System.out.println("FILE FOUND SUCCESSFULLY!");//buraya geldiyse bulmuştur

            while (sc.hasNextLine()){

            String line = sc.nextLine().trim();

            if(line.isEmpty()){continue;}

            String[] nameNmoney = line.split(",");

            if(nameNmoney.length != 2){
                System.out.println(line + "formatted wrong.");
                continue;
            }

            String name = nameNmoney[0].trim();
            double dailySalary = 0; //initialize etmiş olmak için

                try{
                    dailySalary = Double.parseDouble(nameNmoney[1].trim());
                }catch(NumberFormatException e){

                    if(line.equals("name,dailySalary")) {continue;}

                        System.out.println("Wrong format for salary. Fix the number on line: " + line);
                        continue;

                }

//SO FAR, THIS IS WHAT WE DID IN SE115 except the map, set thingies.

//we read the names from the file so now we have to check if those names are registered users
                Employee theEmployeeInFileLine = null;

                for(Employee person : employees){
                    if(person !=null) {
                        if (person.getName().trim().equalsIgnoreCase(name)){
                            theEmployeeInFileLine = person; break;
                        }
                    }
                }
//so we checked if the employee already exists, now we have to create new if doesnt exist


                if(theEmployeeInFileLine == null){ //hala null ise

                    theEmployeeInFileLine = new Employee( String.valueOf( employees.size() + 1 )
                            , name );

                    registerEmployee(theEmployeeInFileLine);
                }

//OKAY NOW WE HAVE THE EMPLOYEE OBJECT SET. NOW DEALING WITH MONEY,

                if(dailySalary<0){dailySalary *= -1;}

                double monthlySalary = dailySalary * 30;

                //şimdi salaryMap'a key-value pair olarak koyacağım

                salaryMap.put(theEmployeeInFileLine, monthlySalary);
              //  System.out.printf("%s has monthly salary of %s dollars.\n", name, monthlySalary);


            }//close bracket for while has next Line
//this means we are done reading the file
        }catch(IOException e){
            System.out.println("Problem finding/reading the .csv file. Make sure you wrote the path correctly.");


        }

    }//END OF CALCULATE SALARIES METHOD


    public void askForSalaryLimit(){
        if(manager.getSalaryLimit() == 0){
            System.out.println("no salary limit set. set a limit and try again.");
             return;
        }

        double limit = manager.getSalaryLimit();
        System.out.println("Salary limit is: "+ limit);

        //now this is the tricky part, I need to loop through people and remove the ones that exceed the limit.
        //In our slides it says:
        // !!!Do not modify a collection directly while iterating; use iterator methods instead
//SO, I had to figure out how the hell do I work with iterators!! AND I HATE THE NEW SLIDES!!! SE115 slides were explaining better. this is a prison.

        Iterator<Map.Entry<Employee, Double>> iterator = salaryMap.entrySet().iterator(); //the crap I stole from stackoverflow because why the fuck did no one teach me this??? Did I somehow missed this in class??? Is this school even good for anything?

        while (iterator.hasNext()){ //okay this makes sense, we are going through the map collection elements.
            Map.Entry<Employee, Double> entry = iterator.next(); //does this mean it's the current element that it is iterating through?
        Employee employee = entry.getKey();
        double salary = entry.getValue();

            if(salary> limit){
                System.out.println("REMOVING: " + employee.getName() + "\n REASON: SALARY EXCEEDS LIMIT");

                iterator.remove(); //salaryMap'tan remove
                //iterator ile removelamak safe diye yaptık.
                employees.remove(employee); // setten remove
            }
        }//while iterate edecek obje oldukça

    }




}//EMS CLASS parenthesis