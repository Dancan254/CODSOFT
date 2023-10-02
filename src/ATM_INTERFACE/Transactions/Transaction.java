package ATM_INTERFACE.Transactions;

import ATM_INTERFACE.Bank.Account;
import ATM_INTERFACE.User.User;
import java.util.Date;
public class Transaction {

    /**
     * The amount of transaction
     */
    private double amount;
    /**
     * The time and date for this transaction
     */
    private Date timeStamp;
    /**
     * A meme for the transaction
     */
    private String memo;

    /**
     * The account in which the transaction was performed
     */
    private Account account;

    /**
     * Create a new transaction
     * @param amount amount to be transacted
     * @param inAccount the account the transaction belong to
     */public Transaction(double amount, Account inAccount){

        this.amount = amount;
        this.account = inAccount;
        this.timeStamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transaction
      * @param amount amount to be transacted
     * @param memo the memo for the transaction
     * @param account the account the transaction belong to
     */
    public Transaction(double amount, String memo, Account account) {
        //call the two-arg constructor first
        this(amount, account);

        //set the memo
        this.memo = memo;
    }

    /**
     * Get the balance of this account by adding the amounts of transactions
     * @return      the balance value
     */
    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine(){
        if (this.amount >= 0){
            return String.format("%s : $%.02f : %s",
                    this.timeStamp.toString(),
                    this.amount, this.memo);
        }else{
            return String.format("%s : $(%.02f) : %s",
                    this.timeStamp.toString(),
                    this.amount, this.memo);
        }

    }
}
