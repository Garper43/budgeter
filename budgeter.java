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
        title();
        int incomeCategories = get_categories( scan , "income" );    
        income = get_value( scan , incomeCategories , "income" ); 
        expenseType = get_expense_type( scan );
        int expenseCategories = get_categories( scan , "expense" );
        expense = get_value( scan , expenseCategories , "expense" );
        calculate( income , expense , expenseType );
        scan.close();
    }

    public static void title() {
        System.out.println("This program asks for your monthly income and expenses, then tells you your net monthly income. ");
        System.out.println("");
    }

    public static int get_categories( Scanner scan , String type ) { //returns # of categories for either income or expense
        System.out.println("How many categories of " + type + "? ");
        int categories = Integer.parseInt(scan.nextLine());
        return categories; 
    } 

    public static int get_expense_type( Scanner scan ) { //gets expense type that will later be used by calculate()
        System.out.println("");
        System.out.println("Enter 1) monthly or 2) daily expenses? ");
        return Integer.parseInt(scan.nextLine());
    }

    public static Double get_value( Scanner scan , int categories , String type ) { //returns a value for either income or expense
        Double value = 0.0;
        Double input;
        for( int i = 0 ; i < categories ; i++ ) {
            System.out.println("Next " + type + " amount? ");    
            input = Double.parseDouble( scan.nextLine() ); 
            value += input;      
        }
        return value;
    }

    public static void calculate( Double income , Double expense , int expenseType ) { //makes all the final calculations
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