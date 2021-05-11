import incometax.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
class income_cal
{
    String name,address,pannum;
    int age;
    double income,income1;
    double tax=0.0;
    Scanner sc=new Scanner(System.in);
    public void input_info()
    {
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
    }
    public void input_income()
    {
        System.out.print("Enter the total income/salary: ");
        income=sc.nextDouble();
        income1=income;
        System.out.println("Your total income/salary: "+(income/100000)+" lacs");
    }
    public void cal_income_tax()
    {
        System.out.println("Total taxable income :"+income);
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
    System.out.println("Your Tax is: " + tax);
    tax=tax+(0.04*tax);
    if(income<=500000)
    {
        System.out.println("Your total Tax(after adding cess) is: " + tax);
        tax=0;
        System.out.println("\nAppling Rebate because income is <=5lac\n");
        System.out.println("Your total Tax(after adding cess) is: " + tax);
    }
    else {
        System.out.println("Your total Tax(after adding cess) is: " + tax);
    }
    }
}
class hra_cal extends income_cal
{
    double hra,monthly_rent,rent_paid,half_income,low;
    Scanner sa=new Scanner(System.in);
    public void input_hra()
    {
        System.out.print("Enter the total HRA(House Rental Allowance) Recieved: ");
        hra = sa.nextDouble();
    }
    public void cal_hra()
    {
        if(hra==0)
        {
            low=0;
        }
        else {
            System.out.print("Enter the monthly rent you paid: ");
            monthly_rent=sa.nextDouble();
            rent_paid=(monthly_rent*12)-(0.1*income);
            half_income=0.5*income;
            if (hra < rent_paid) {
                if (hra < half_income) {
                    low = hra;
                } else {
                    low = half_income;
                }
            } else {
                if (rent_paid < half_income) {
                    low = rent_paid;
                } else {
                    low = half_income;
                }
            }
        }
        System.out.println("Total excempted amount from hra is: "+low);
        income=income+(hra-low);
    }
}
class special_allowance extends hra_cal
{
    double sp_all;
    public void input_special_allowance()
    {
        System.out.print("Enter the special allowance : ");
        sp_all=sc.nextDouble();
        income=income+sp_all;
    }
}
class Lta extends special_allowance{
    double lta,bills;
    public void lta_cal()
    {
        System.out.print("Enter the Lta:");
        lta= sc.nextDouble();
        System.out.print("Enter the total sum of bills that are submitted:");
        bills= sc.nextDouble();
        income=income+(lta-bills);
    }
}
class std_deduction extends Lta{
    double stdde=50000.0;
    public void stddec()
    {
        if (income <= 50000)
        {
            income=0;
        }
        else
        {
            income=income-stdde;
        }
        System.out.println("Total taxable amount after standard deduction is: "+income);
    }
}
class sv_interest extends std_deduction
{
    double sv_amount;
    public void sv_input()
    {
        System.out.print("Enter the amount you earned on saving interest:");
        sv_amount=sc.nextDouble();
        if(sv_amount>10000.0) {
            income = income+(sv_amount-10000.0);
        }
    }
}
class health_ins_cal extends sv_interest
{
    double ins_amount;
    public void ins_cal()
    {
        System.out.print("Enter the amount that you have paid for health insurance:");
        ins_amount=sc.nextDouble();
        if(ins_amount<=25000.0)
        {
            income=income-ins_amount;
        }
        else
        {
            income=income-(ins_amount-25000);
        }
    }
}
class investment extends health_ins_cal
{
    double inv_amount;
    public void invested()
    {
        System.out.println("\tUnder Section 80c PPF, ELSS Funds, Life insurance & EPF are included");
        System.out.print("Enter the total amount that you have invested:");
        inv_amount=sc.nextDouble();
        if(inv_amount<=150000)
        {
            income=income-inv_amount;
        }
        else
        {
            income=income-(inv_amount-150000);
        }
    }
}
class making_file extends investment
{
    public void make_file()
    {
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\Acer\\Desktop\\Itr_report.txt");
            
            myWriter.write("-------------------------------------------------\n\n");
            myWriter.write("Name : "+name+"\n");
            myWriter.write("Age : "+age+"\n");
            myWriter.write("Address : "+address+"\n");
            myWriter.write("Pan Number : "+pannum+"\n");
            myWriter.write("\n-------------------------------------------------\n");
            myWriter.write("\n\t** Basic Component**\n\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Gross Income                         | "+(income1/100000)+" lacs ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("HRA                                  | "+(hra/100000)+" lacs ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Special Allowance                    | "+(sp_all/100000)+" lacs ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Leave-travel Allowance               | "+lta+" ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("\n\t** Deduction **\n\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Standard Deduction                   | "+stdde+" ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Saving Account Interest              | "+sv_amount+" ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Health insurance                     | "+ins_amount+" ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Investment-Section 80c               | "+(inv_amount/100000)+" lacs ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("\n\t** Excemptions **\n\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("HRA Excempted                        | "+(low/100000)+" lacs ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("LTA Excempted                        | "+bills+" ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("\n\t** Net Taxable Income **\n\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Total Taxable Income                 | "+(income/100000)+" lacs ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("\n\t** Net Tax Payable **\n\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.write("Total Tax you have to pay(inc. cess) | "+tax+" ₹\n");
            myWriter.write("--------------------------------------------------------------\n");
            myWriter.close();
            System.out.println("Completed File Processing \nLocation: C:\\Users\\Acer\\Desktop");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
public class project {
    public static void main(String[] args)
    {
        int inp;
        Scanner ic=new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("\tWELCOME TO INCOME TAX CALCULATOR");
        System.out.println("--------------------------------------------------");
        System.out.println("\n--------------------------------------------------\n");
        System.out.println("1 -> press 1. to opt for new regim");
        System.out.println("2 -> press 2. to opt for old regim");
        System.out.println("\n--------------------------------------------------\n");
        inp=ic.nextInt();
        if(inp==1)
        {
            newregim obj1=new newregim();
            obj1.cal_newregim();
        }
        else
        {
            System.out.println("----------------------------------------");
            System.out.println("\t \tOLD REGIM\t");
            System.out.println("----------------------------------------");
            making_file obj=new making_file();
            obj.input_info();
            System.out.println("\n------------------------------------------");
            System.out.println("Basic salary");
            System.out.println("------------------------------------------\n");
            obj.input_income();
            System.out.println("\n------------------------------------------");
            System.out.println("HRA(House Rental Allowance)");
            System.out.println("------------------------------------------\n");
            obj.input_hra();
            obj.cal_hra();
            System.out.println("\n------------------------------------------");
            System.out.println("Special Allowance");
            System.out.println("------------------------------------------\n");
            obj.input_special_allowance();
            System.out.println("\n------------------------------------------");
            System.out.println("Leave Travel Allowance");
            System.out.println("------------------------------------------\n");
            obj.lta_cal();
            System.out.println("\n------------------------------------------");
            System.out.println("Standard Deduction");
            System.out.println("------------------------------------------\n");
            obj.stddec();
            System.out.println("\n------------------------------------------");
            System.out.println("Saving Account Interest");
            System.out.println("------------------------------------------\n");
            obj.sv_input();
            System.out.println("\n------------------------------------------");
            System.out.println("Health insurance");
            System.out.println("------------------------------------------\n");
            obj.ins_cal();
            System.out.println("\n------------------------------------------");
            System.out.println("Investment under section 80c");
            System.out.println("------------------------------------------\n");
            obj.invested();
            System.out.println("\n------------------------------------------");
            System.out.println("Final Tax You have to paid");
            System.out.println("------------------------------------------\n");
            obj.cal_income_tax();
            System.out.println("------------------------------------------\n");
            obj.make_file();
            System.out.println("\n------------------------------------------\n");
        }
    }
}
