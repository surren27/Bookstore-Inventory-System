package database;
import model.Supplier;
import model.SupplierHistory;
import model.PurchaseOrder;
import model.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class SupplierDB {
    //Encapsulation
    private String connectionString;
    
    public SupplierDB() {
    }
    public SupplierDB(String connectionString) {
        this.connectionString = connectionString;
    }
    public Connection connectDB() {

    try {

        String url =
"jdbc:mysql://localhost:3306/bookstore_db";

        String user = "root";

        String password = "";

        Connection conn =
                DriverManager.getConnection(
                        url,
                        user,
                        password
                );

        return conn;

    } catch(Exception e) {

        e.printStackTrace();

        return null;
    }
}
    //Object Passing
    public void saveSupplier(Supplier supplier) {

    try {

        Connection conn =
                connectDB();

        String sql =
"INSERT INTO suppliers(supplier_name, phone, email, address) VALUES (?, ?, ?, ?)";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1,
                supplier.getCompanyName());

       pst.setString(2,
        supplier.getContactNo()) ;

        pst.setString(3,
                supplier.getEmail());

        pst.setString(4,
                supplier.getAddress());

        pst.executeUpdate();

String historySQL =
"INSERT INTO supplier_history(supplier_name, action_description, action_date) VALUES (?, ?, NOW())";

PreparedStatement historyPst =
        conn.prepareStatement(historySQL);

historyPst.setString(1,
        supplier.getCompanyName());

historyPst.setString(2,
        "Supplier Added");

historyPst.executeUpdate();

conn.close();

System.out.println("Supplier Saved");

    } catch(Exception e) {

        e.printStackTrace();
    }
}
    public SupplierHistory getSupplierHistory() {
        System.out.println("Retrieving Supplier History...");
        return new SupplierHistory();
    }
    //Object Passing
    public void saveOrder(PurchaseOrder order) {
        System.out.println("Purchase Order Saved");
    }
    public Report getReportData() {
        System.out.println("Retrieving Report Data...");
        return new Report();
    }
    public void closeConnection() {
        System.out.println("Database Connection Closed");
    }
    //Getter & Setter
    public String getConnectionString() {
        return connectionString;
    }
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}