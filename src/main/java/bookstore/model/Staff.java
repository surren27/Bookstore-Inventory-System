package bookstore.model;

public class Staff extends User {

    public Staff(String name,
                 String email) {

        super(name, email, "Staff");
    }

    @Override
    public void login() {

        System.out.println("Staff login successful.");
    }

    public void monitorInventory() {

        System.out.println(
                "Monitoring inventory...");
    }
}