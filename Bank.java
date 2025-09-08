public class Bank {
    private double credit;
    public double getCredit(){return this.credit;}
    private void depositCredit(double value){
            if(value > 0) {
                this.credit = this.credit + value;
                System.out.println("Successfully added " + value + " to the account!");
                return;
            }
            else {
                System.out.println("Amount to be added is invalid -- Try again!");
                return;
            }

    }
    private void withdrawCredit(double value){
            if(value > 0 && credit >= value) {
                this.credit = this.credit - value;
                System.out.println("Successfully withdrew " + value + " from the account!");
                return;
            }
            else {
                System.out.println("Amount to be added is invalid -- Try again!");
                return;
            }
        

    }

    public void transaction(String type, double value){
        switch (type) {
            case "Withdraw":
            case "withdraw":
            case "w":
                withdrawCredit(value);
                System.out.println("New credit: " + this.getCredit());
                break;
            case "Deposit":
            case "deposit":
            case "d":
                depositCredit(value);
                System.out.println("New credit: " + this.getCredit());
                break;
            default:
                System.err.println("Invalid transaction type -- We only support 'Withdraw', or 'Deposit' ");
                break;
        }
        
    }
}
