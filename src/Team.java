import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Team {
    public static void main(String[] args) {
        try {
        //Creates a new list of employees in an array
        List<Employee> employees = new ArrayList<>();

        //adds a new object representing each employee on the team to the array with each value being defined and calls to the employee class
        employees.add(new Employee("Andrew", 162, 32, 16, QC.NOT_QC));
        employees.add(new Employee("Hafsa", 34, 44, 21, QC.NOT_QC));
        employees.add(new Employee("Ayub", 193, 102, 12, QC.NOT_QC));
        employees.add(new Employee("Amaan", 309, 56, 1, QC.NOT_QC));
        employees.add(new Employee("Gary", 49, 62, 11, QC.NOT_QC));
        employees.add(new Employee("Diane", 217, 10, 2, QC.NOT_QC));
        employees.add(new Employee("Rabia", 385, 0, 0, QC.QC));

        //efficiently sorts the employees based on the numbers of cakes covered and in alphabetical order depending
        //on if they have the same number of cakes covered
        Collections.sort(employees, Comparator.comparing(Employee::getCakesCovered).reversed()
                .thenComparing(Employee::getName));

        //prints out the visual of each row so each variable is represented
        System.out.println("Team Statistics:");
        System.out.println("================\n");
        System.out.println("Name       | Cakes Covered      | Additional Cakes      | Unsuitable Cakes      | Salary");
        System.out.println("--------------------------------------------------------------------------");

        //prints each employee from the array in the correct format
        for (Employee e : employees) {
            System.out.println(e.toString());

        }
        //when the table has been completed, the total cakes and total wages are printed using an additional method in the Employee class
        System.out.println("Total cakes covered: " + Employee.getTotalCakesCovered());
        System.out.println("Total wages paid: £" + Employee.getTotalWagesPaid());
        //if any errors occur in this class it will be caught and an error will be displayed to the screen
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}