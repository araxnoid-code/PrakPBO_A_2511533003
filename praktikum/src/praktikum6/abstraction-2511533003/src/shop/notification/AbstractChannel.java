package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {

    private int sendCount = 0;
    private String last = "";

    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (message.isEmpty()){
            return false;
        }

        if (!validation(contact)){
            return false;
        }


        sendCount += 1;
        last = msg(message);
        System.out.println(target(contact) + " " + last);

        return true;
    }

    public abstract boolean validation(CustomerContact contact);
    public abstract String msg(String msg);
    public abstract String target(CustomerContact contact);

    public int getSentCount(){
        return this.sendCount;
    }

    public String getLastDelivered(){
        return this.last;
    }
    
}
