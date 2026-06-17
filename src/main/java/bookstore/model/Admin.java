package bookstore.model;

public class Admin extends User {

    public Admin(String name,
                 String email) {

        super(name, email, "Admin");
    }

    @Override
    public void login() {

        System.out.println("Admin login successful.");
    }

    public void manageProduct(Product product) {

        System.out.println(
                "Admin managing product: "
                        + product.getTitle());
    }
}
