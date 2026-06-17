package model;
    //Inheritance
public class Staff extends User {
    private String role;
    public Staff() {
    }

    public Staff(String userID, String name, String password, String role) {
        //Constructor
        super(userID, name, password);
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    //Polymorphism
    @Override
    public void login() {
        System.out.println("Staff login successful.");
    }
}
