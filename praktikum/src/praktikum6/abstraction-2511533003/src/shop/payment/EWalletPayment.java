package shop.payment;

public class EWalletPayment implements PaymentMethod, RefundablePayment {
    private String provider;
    private Long balance;

    public EWalletPayment(String provider, long balance){
        if (provider.isEmpty()){
            throw new IllegalArgumentException();
        }

        if (balance < 0){
            throw new IllegalArgumentException();
        }

        this.balance = balance;
        this.provider = provider;
    }

    @Override
    public String getName() {
        return "E-Wallet " + this.provider;
    }

    public long getBalance(){
        return this.balance;
    }

    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || amount > this.balance){
            return false;
        }

        this.balance -= amount;
        return true;
    }

    @Override
    public boolean refund(long amount) {
        if (amount <=  0){
            return false;
        }

        this.balance += amount;
        return true;
        
    }

    
}
