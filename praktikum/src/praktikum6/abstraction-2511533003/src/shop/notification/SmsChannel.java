package shop.notification;

public class SmsChannel extends AbstractChannel {
    private int maxLength;
    public SmsChannel(int maxLength){
        this.maxLength = maxLength;
    }

    @Override
    public String channelName() {
        return "SMS";
    }

    @Override
    public String target(CustomerContact contact) {
        return "[" + channelName() + "] to " + contact.getPhone();
    }

    @Override
    public String msg(String msg) {
        if (msg.length() <= maxLength){
            return msg;
        }

        String slice = msg.substring(0, this.maxLength - 3);
        slice += "...";
        return slice;
    }

    @Override
    public boolean validation(CustomerContact contact) {
        String slice = contact.phone.substring(0, 2);
        return slice.equals("08");
    }

    

    
}
