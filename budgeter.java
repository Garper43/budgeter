/*
Taras Sozanskyi
period 2
11/24/2020

this is a budgeter, it explains what it does when you start it, so i'm not gonna bother

you did not specify what we could use here so I decided to experiment
*/

import java.util.*;

public class budgeter {
    final static int MONTH = 31; //# of days in a month
    public static void main(String arg[]) {
        Scanner scan = new Scanner(System.in);
        Double income , expense;
        int expenseType;   
        int incomeCategories = get_income_categories( scan );    
        income = get_income( scan , incomeCategories ); 
        expenseType = get_expense_type( scan );
        int expenseCategories = get_expense_categories( scan );
        expense = get_expense( scan , expenseCategories );
        calculate( income , expense , expenseType );
        scan.close();
    }

    public static int get_income_categories( Scanner scan ) { //returns an empty Double array
        System.out.println("This program asks for your monthly income and expenses, then tells you your net monthly income. ");
        System.out.println("How many categories of income? ");
        System.out.println("");
        int incomeCategories = Integer.parseInt(scan.nextLine());
        return incomeCategories; 
    } 

    public static Double get_income( Scanner scan , int incomeCategories ) { //returns an income array  
        Double income = 0.0;                                  
        for( int i = 0 ; i < incomeCategories ; i++ ) {
            System.out.println("Next income amount? ");    
            Double input = Double.parseDouble( scan.nextLine() ); 
            income += input;     
        }
        return income;
    }

    public static int get_expense_type( Scanner scan ) { //gets expense type that will later be use by calculate()
        System.out.println("");
        System.out.println("Enter 1) monthly or 2) daily expenses? ");
        return Integer.parseInt(scan.nextLine());
    }

    public static int get_expense_categories( Scanner scan ) { //returns an empty Double array
        System.out.println("How many categories of expense? ");
        int expenseCategories = Integer.parseInt(scan.nextLine());
        return expenseCategories;
    }

    public static Double get_expense( Scanner scan , int expenseCategories ) { //returns an expense array
        Double expense = 0.0;
        Double input;
        for( int i = 0 ; i < expenseCategories ; i++ ) {
            System.out.println("Next expense amount? ");    
            input = Double.parseDouble( scan.nextLine() ); 
            expense += input;      
        }
        return expense;
    }

    public static void calculate( Double income , Double expense , int expenseType ) {
        Double total = 0.0;
        String string;
        System.out.println("");
        //round numbers
        if( expenseType == 2 ) { expense = expense * MONTH; }
        income = Math.round(income * 100.0) / 100.0;
        expense = Math.round(expense * 100.0) / 100.0;
        //find the total earnings/spendings
        total = income - expense;
        total = Math.round(total * 100.0) / 100.0;;
        //output
        System.out.println("Total income = $"+income+" ($"+Math.round(income/MONTH * 100.0) / 100.0+"/day)");
        System.out.println("Total expense = $"+expense+" ($"+Math.round(expense/MONTH * 100.0) / 100.0+"/day)");
        System.out.println("");
        if( total > 250f )                       { string = "Big Saver"; 
        } else if( total <= 250.0 && total > 0 )  {  string = "Saver";
        } else if( total > -250.0 && total <= 0 ) {  string = "Spender";
        } else                                   {  string = "Big Spender"; } 
        if( total <= 0 ) { System.out.println("You spent $" + total*-1 + " more than you earned this month."); 
        } else           { System.out.println("You earned $" + total + " more than you spent this month."); }
        System.out.println("");
        System.out.println("You're a " + string );
    }
}