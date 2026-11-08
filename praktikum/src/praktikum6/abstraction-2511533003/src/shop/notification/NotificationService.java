package shop.notification;

import java.util.ArrayList;

public class NotificationService {
    ArrayList<NotificationChannel> list = new ArrayList<>();

    public void register(NotificationChannel channel){
        list.add(channel);
    }

    public int broadcast(CustomerContact contact, String message) {
        int sukses = 0;
        for (int i = 0; i < this.list.size(); i++) {
            if (list.get(i).send(contact, message)){
                sukses += 1;
            }
        }
        return sukses;
    }
}
