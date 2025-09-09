import java.util.Scanner;

public class Bank {
    private double credit = 0.0;
    public double getCredit(){return this.credit;}
    private boolean depositCredit(double value){
            if(value > 0) {
                this.credit = this.credit + value;
                System.out.println("Successfully added " + value + " to the account!");
                System.out.println("New credit: " + this.getCredit());
                return true;
            }
            else {
                System.out.println("Amount to be added is invalid -- Try again!");
                return false;
            }

    }
    private boolean withdrawCredit(double value){
            if(value > 0 && credit >= value) {
                this.credit = this.credit - value;
                System.out.println("Successfully withdrew " + value + " from the account!");
                System.out.println("New credit: " + this.getCredit());
                return true;
            }
            else {
                System.out.println("Amount to be added is invalid -- Try again!");
                return false;
            }
        

    }

    public boolean  transaction(Scanner input){
        Helpers helper = new Helpers();
        while(true){
            String type = helper.askLine(input,"What would you like to do, a 'Withdrawal' or a 'Deposit'? " );

            if(!correctTransactionType(type)){
                System.err.println("Invalid transaction type -- We only support 'Withdraw', or 'Deposit' ");
                continue;
            }

            double value = helper.askDobule(input, "What amount? ");

            switch (type) {
                case "Withdraw":
                case "withdraw":
                case "w":
                    return withdrawCredit(value);
                case "Deposit":
                case "deposit":
                case "d":
                    return depositCredit(value);
                default:
                    return false;
            }
        }
        
    }

   private boolean correctTransactionType(String type){
        return
            type.equals("Withdraw") ||
            type.equals("withdraw") ||
            type.equals("w") ||
            type.equals("Deposit") ||
            type.equals("deposit") ||
            type.equals("d");
    }
}
