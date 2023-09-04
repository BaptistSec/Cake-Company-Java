import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Employee {

    //the below attributes first initialise the employee name, cakesCovered, additionalCakesCovered etc.
    //qcStatus is called from the QC class
    private String name;
    private int cakesCovered;
    private int additionalCakesCovered;
    private int unsuitableCakes;
    private QC qcStatus;

    //when an employee array is first created the totalCakesCovered and totalWagesPaid are reset to 0
    private static int totalCakesCovered = 0;
    private static double totalWagesPaid = 0.0;

    public Employee(String name, int cakesCovered, int additionalCakesCovered, int unsuitableCakes, QC qcStatus) {
        //ensures employee name is not null or empty
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        //ensure that all cake counts are non-negative
        if (cakesCovered < 0 || additionalCakesCovered < 0 || unsuitableCakes < 0) {
            throw new IllegalArgumentException("Cake counts must be non-negative");
        }
        //this is where if an employee object is created, the value used when creating it are put here
        this.name = name;
        this.cakesCovered = cakesCovered;
        this.additionalCakesCovered = additionalCakesCovered;
        this.unsuitableCakes = unsuitableCakes;
        this.qcStatus = qcStatus;
        coverCakes(cakesCovered, additionalCakesCovered, unsuitableCakes);
    }

    //the QCStatus is returned when called
    public QC getQCStatus() {
        return qcStatus;
    }
    //the below methods simply return whatever value was assigned to the object
    public String getName() {
        return name;
    }

    public int getCakesCovered() {
        return cakesCovered;
    }

    public int getAdditionalCakesCovered() {
        return additionalCakesCovered;
    }

    public int getUnsuitableCakes() {
        return unsuitableCakes;
    }

    //coverCakes keeps a running total of all cakes covered and any additional cakes that have been created by any object
    public void coverCakes(int cakesCovered, int additionalCakes, int notSuitableCakes) {
        //coverCakes also removes any notSuitableCakes as they don't count towards the total number of cakes
        totalCakesCovered += (cakesCovered + additionalCakes) - notSuitableCakes;
    }
// while similar to coverCakes, the getTotalCakes method returns only the total cakes for the object calling it and not the running total
    // this method is exclusively used for the salary in this iteration of the program but could be used for a total of cakes created in the employee table
    public int getTotalCakes() {
        return cakesCovered + additionalCakesCovered - unsuitableCakes;
    }

    //the getSalary method is used for each employee to figure out the total salary but also keeps a running total of totalWagesPaid
    public double getSalary() {
        int totalCakes = getTotalCakes();
        //because of the wage criteria, the regularCakes are separate to the extraCakes payment.
        //in this method regularCakes is from the minimum of totalCakes until 50 at which it pays 0.1 or 10p whereas extraCakes is counted until the max
        //of the totalCakes after 50 while paying 0.15 or 15p
        int regularCakes = Math.min(totalCakes, 50);
        int extraCakes = Math.max(totalCakes - 50, 0);
        double salary = Math.round((regularCakes * 0.1) + (extraCakes * 0.15));
        //after each employee has had their wage calculated there will be an increase of 12% depending on if they are listed as a QC using the QcStatus method or NOT_QC
        if (qcStatus == QC.QC) {
            salary *= 1.12;
        }
        //this is where the running total is accounted for
        totalWagesPaid += Math.round(salary);

        return salary;
    }

// the total wages for every employee returns in this method
    public static double getTotalWagesPaid() {
        return totalWagesPaid;
    }
    // the toString method returns the formatting when printing below the table for the employee currently being added
    @Override
    public String toString() {
        return String.format("%-10s | Cakes Covered: %-3d | Additional Cakes: %-3d | Unsuitable Cakes: %-3d | Salary: £%.2f",
                name, cakesCovered, additionalCakesCovered, unsuitableCakes, getSalary());
    }
    public static int getTotalCakesCovered() {
        return totalCakesCovered;
    }
}
