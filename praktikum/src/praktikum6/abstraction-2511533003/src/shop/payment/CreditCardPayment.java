package shop.payment;

public class CreditCardPayment implements RefundablePayment, InstallmentPayment {
    private String cardHolder;
    private long usedCredit;
    private long creditLimit;

    public CreditCardPayment(String cardHolder, long creditLimit){
        this.cardHolder = cardHolder;
        this.creditLimit = creditLimit;
    }

    @Override 
    public String getName(){
        return "Kartu Kredit (" + cardHolder + ")";
    }

    @Override 
    public boolean refund(long amount) {
        if (amount <= 0 || amount > this.usedCredit) 
            return false;

        this.usedCredit -= amount;

        return true;
    }

    @Override 
    public boolean pay(long amount) {
        if (amount <= 0 || (this.usedCredit + amount) > creditLimit) 
            return false;

        this.usedCredit += amount;

        return true;
    }

    @Override 
    public int maxInstallmentMonths() {
        return 12; 
    }

    public long getUsedCredit() { 
        return this.usedCredit; 
    }

    
}
