package incometax;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
public class newregim
{
    public void cal_newregim()
    {
        String name,address,pannum;
        int age;
        double income;
        double tax=0.0;
        Scanner sc=new Scanner(System.in);
            System.out.println("----------------------------------------");
            System.out.println("\t NEW REGIM\t");
            System.out.println("----------------------------------------");
            System.out.println("\n----------------------------------------\n");
            System.out.print("Enter your name:");
            name=sc.nextLine();
            System.out.print("\nEnter your age:");
            age=sc.nextInt();
            System.out.print("\nEnter your address:");
            sc.nextLine();
            address=sc.nextLine();
            System.out.print("\nEnter your Pan card number:");
            pannum=sc.nextLine();
            System.out.println("\n----------------------------------------\n");
            System.out.print("Enter the total income/salary: ");
            income=sc.nextDouble();
            System.out.println("\nYour total income/salary: "+(income/100000)+" lacs");
            if (income <= 250000)
                tax = 0;
            else if (income <= 500000)
                tax = (income - 250000) * 0.05;
            else if (income <= 750000)
                tax = 12500 + (income - 500000) * 0.1;
            else if (income <= 1000000)
                tax = 37500 + (income - 750000) * 0.15;
            else if (income <= 1250000)
                tax = 75000 + (income - 1000000) * 0.2;
        System.out.println("\nYour Tax is: " + tax);
        try
        {
            FileWriter myWriter = new FileWriter("C:\\Users\\Acer\\Desktop\\Itr_report.txt");
            myWriter.write("-------------------------------------------------\n\n");
            myWriter.write("Name : "+name+"\n");
            myWriter.write("Age : "+age+"\n");
            myWriter.write("Address : "+address+"\n");
            myWriter.write("Pan Number : "+pannum+"\n");
            myWriter.write("\n-------------------------------------------------");
            myWriter.write("\n\n\t** Net Taxable Income **\n\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Total Taxable Income                 | "+(income/100000)+" lacs ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("\n\t** Net Tax Payable **\n\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Total Tax you have to pay(inc. cess) | "+tax+" ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
           
            myWriter.close();
            System.out.println("\nCompleted File Processing \nLocation: C:\\Users\\Acer\\Desktop");
        }
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}