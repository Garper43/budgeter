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
        Double[] income , expense;
        int expenseType;   
        income = get_income_categories( scan );    
        income = get_income( scan , income ); 
        expenseType = get_expense_type( scan );
        expense = get_expense_categories( scan );
        expense = get_expense( scan , expense );
        calculate( income , expense , expenseType );
        scan.close();
    }

    public static Double[] get_income_categories( Scanner scan ) { //returns an empty Double array
        boolean complete = false;
        int incomeCategories = 0;
        System.out.println("This program asks for your monthly income and expenses, then tells you your net monthly income. ");
        System.out.println("How many categories of income? ");
        System.out.println("");
        while( !complete ) {
            try {
                incomeCategories = Integer.parseInt(scan.nextLine());
                complete = true;
            } catch( Exception e ) {
                System.out.println("Please try again ");
                scan.nextLine();
            }
        }
        return new Double[incomeCategories]; 
    } 

    public static Double[] get_income( Scanner scan , Double[] income ) { //returns an income array 
        System.out.println("");                                         //( I used arrays because they'd be useful if I needed to add functionality and because i felt like it )
        for( int i = 0 ; i < income.length ; i++ ) {
            try { //takes income input
                System.out.println("Next income amount? ");    
                Double input = Double.parseDouble( scan.nextLine() ); 
                income[ i ] = input;  
            } catch( Exception e ) {
                System.out.println("Please try again ");
                scan.nextLine();
                i -= 1; //prevents waste of loops on failed attmets
            }     
        }
        return income;
    }

    public static int get_expense_type( Scanner scan ) { //gets expense type that will later be use by calculate()
        int expenseType = 0;
        boolean complete = false;
        System.out.println("");
        while( !complete ) {
            try {
                System.out.println("Enter 1) monthly or 2) daily expenses? ");
                expenseType = Integer.parseInt(scan.nextLine());
                if( expenseType != 1 && expenseType != 2 ) { throw new Exception("invalid expenseType"); }
                complete = true;
            } catch( Exception e ) {
                System.out.println("Please try again ");
                scan.nextLine();
            }
        }
        return expenseType;
    }

    public static Double[] get_expense_categories( Scanner scan ) { //returns an empty Double array
        boolean complete = false;
        int expenseCategories = 0;
        System.out.println("");
        while( !complete ) {
            try {
                System.out.println("How many categories of expense? ");
                expenseCategories = Integer.parseInt(scan.nextLine());
                complete = true;
            } catch( Exception e ) {
                System.out.println("Please try again ");
                scan.nextLine();
            }
        }
        return new Double[expenseCategories];
    }

    public static Double[] get_expense( Scanner scan , Double[] expense ) { //returns an expense array
        Double input;
        System.out.println("");
        for( int i = 0 ; i < expense.length ; i++ ) {
            try {
                System.out.println("Next expense amount? ");    
                input = Double.parseDouble( scan.nextLine() ); 
                expense[ i ] = input;  
            } catch( Exception e ) {
                scan.nextLine();
                i -= 1; //prevents waste of loops on failed attmets
            }     
        }
        return expense;
    }

    public static void calculate( Double[] income , Double[] expense , int expenseType ) {
        Double totalIncome = 0.0;
        Double totalExpense = 0.0;
        Double total = 0.0;
        String string;
        System.out.println("");
        //add numbers from the arrays
        for( int i = 0 ; i < income.length ; i++ ) { totalIncome += income[i]; }
        for( int i = 0 ; i < expense.length ; i++ ) { totalExpense += expense[i]; }
        //round numbers
        if( expenseType == 2 ) { totalExpense = totalExpense * MONTH; }
        totalIncome = Math.round(totalIncome * 100.0) / 100.0;
        totalExpense = Math.round(totalExpense * 100.0) / 100.0;
        //find the total earnings/spendings
        total = totalIncome - totalExpense;
        //output
        System.out.println("Total income = $"+totalIncome+" ($"+Math.round(totalIncome/MONTH * 100.0) / 100.0+"/day)");
        System.out.println("Total expense = $"+totalExpense+" ($"+Math.round(totalExpense/MONTH * 100.0) / 100.0+"/day)");
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