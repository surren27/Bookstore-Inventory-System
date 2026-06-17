package model;
    //Abstraction
public abstract class User {
    //Encapsulation : private attributes
    private String userID;
    private String name;
    private String password;

    public User() {
    }
    public User(String userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
    }
    //Getter Setter 
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //Polymorphism Preparation : Staff Override
    public abstract void login();
}