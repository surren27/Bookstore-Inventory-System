package bookstore.model;

public abstract class User {

    protected String name;
    protected String email;
    protected String role;

    public User(String name,
                String email,
                String role) {

        this.name = name;
        this.email = email;
        this.role = role;
    }

    public abstract void login();

    public void logout() {

        System.out.println(name + " logged out.");
    }

    public String getName() {

        return name;
    }

    public String getRole() {

        return role;
    }
}
