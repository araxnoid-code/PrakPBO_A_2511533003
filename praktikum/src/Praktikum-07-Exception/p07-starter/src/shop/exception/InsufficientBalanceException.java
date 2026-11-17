package shop.exception;

public class InsufficientBalanceException extends PaymentFailedException {
    private static final long serialVersionUID = 1L;
    long required;
    long available;
    
    public InsufficientBalanceException(long required, long available){
        super("Saldo tidak cukup: perlu Rp" + required +", tersedia Rp" + available);
        this.required = required;
        this.available = available;
    }

    public long getRequired(){
        return this.required;
    }

    public long getAvailable(){
        return this.available;
    }

    public long getShortfall(){
        return this.required - this.available;
    }
}
