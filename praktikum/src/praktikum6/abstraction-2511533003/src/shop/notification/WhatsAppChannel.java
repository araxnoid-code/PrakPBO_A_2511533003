package shop.notification;

public class WhatsAppChannel extends AbstractChannel {

    @Override
    public String channelName() {
        return "WA";
    }

    @Override
    public boolean validation(CustomerContact contact) {

        String slice_2 = contact.phone.substring(0, 2);
        String slice_3 = contact.phone.substring(0, 3);
        return (slice_2.equals("08") || slice_3.equals("+62")) && contact.phone.length() >= 10;
    }

    @Override
    public String target(CustomerContact contact) {
        return "[" + channelName() + "] to " + contact.getPhone();
    }

    @Override
    public String msg(String msg) {
        return "[Toko UMKM] " + msg;
    }
}
