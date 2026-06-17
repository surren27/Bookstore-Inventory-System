package bookstore.model;

public class UserFactory {

    // Factory Method
    public static User createUser(String role,
                                  String name,
                                  String email) {

        if(role == null) {

            return null;
        }

        switch(role.toLowerCase().trim()) {

            case "admin":

                return new Admin(name, email);

            case "staff":

                return new Staff(name, email);

            default:

                System.out.println(
                        "Unknown role: " + role);

                return null;
        }
    }
}