package shop.notification;

final public class CustomerContact {
    String name;
    String email;
    String phone;

    public CustomerContact (String name, String email, String phone){
        if (name.isEmpty()){
            throw new IllegalArgumentException("Name tidak boleh kosong");
        }
        this.name = name;

        if (email == null){
            this.email = "";
        } else {
            this.email = email;
        }

        if (phone == null){
            this.phone = "";
        } else {
            this.phone = phone;

        }
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }
}
