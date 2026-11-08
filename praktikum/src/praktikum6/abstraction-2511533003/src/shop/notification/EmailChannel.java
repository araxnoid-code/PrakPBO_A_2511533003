package shop.notification;

public class EmailChannel extends AbstractChannel {
    @Override
    public String channelName() {
        return "EMAIL";
    }

    @Override
    public String target(CustomerContact contact) {
        return "[" + channelName() + "] to " + contact.getEmail();
    }

    @Override
    public String msg(String msg) {
        return msg;
    }

    @Override
    public boolean validation(CustomerContact contact) {
        return contact.email.contains("@");
    }
}
