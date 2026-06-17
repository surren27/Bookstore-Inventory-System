package bookstore.controller;

import bookstore.database.DatabaseManager;
import bookstore.model.Product;
import java.util.ArrayList;
import bookstore.model.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class ProductController {

    public boolean addProduct(Product product) {

        String sql =
"INSERT INTO Product(title, author, isbn, category, price, stockQty)"
+ " VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection conn = DatabaseManager.connectDB();

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, product.getTitle());
            pst.setString(2, product.getAuthor());
            pst.setString(3, product.getIsbn());
            pst.setString(4, product.getCategory());
            pst.setDouble(5, product.getPrice());
            pst.setInt(6, product.getInventory().getStockQuantity());

            pst.executeUpdate();

            conn.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public void loadProductTable(DefaultTableModel model) {

    String sql = "SELECT * FROM Product";

    try {

        Connection conn = DatabaseManager.connectDB();

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        model.setRowCount(0);

        // COLLECTION
        ArrayList<Product> productList =
                new ArrayList<>();

        while (rs.next()) {

            // CREATE INVENTORY OBJECT
            Inventory inventory =
                    new Inventory(
                            rs.getInt("stockQty"),
                            5
                    );

            // CREATE PRODUCT OBJECT
            Product product =
                    new Product(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("isbn"),
                            rs.getString("category"),
                            rs.getDouble("price"),
                            inventory
                    );

            // STORE OBJECT INTO ARRAYLIST
            productList.add(product);

            int stockQty =
                    inventory.getStockQuantity();

            String stockStatus;

            if(inventory.isLowStock()) {

                stockStatus = "LOW STOCK";
            }
            else {

                stockStatus = "AVAILABLE";
            }

            model.addRow(new Object[]{

                rs.getInt("productID"),
                product.getTitle(),
                product.getAuthor(),
                product.getIsbn(),
                product.getCategory(),
                product.getPrice(),
                stockQty,
                stockStatus
            });
        }

        conn.close();

    } catch (Exception e) {

        e.printStackTrace();
    }
}
    public void searchProduct(DefaultTableModel model, String keyword) {

        String sql = "SELECT * FROM Product WHERE title LIKE ?";

        try {

            Connection conn = DatabaseManager.connectDB();

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {

               int stockQty =
        rs.getInt("stockQty");

String stockStatus;

if(stockQty <= 5) {

    stockStatus = "LOW STOCK";
}
else {

    stockStatus = "AVAILABLE";
}

model.addRow(new Object[]{

    rs.getInt("productID"),
    rs.getString("title"),
    rs.getString("author"),
    rs.getString("isbn"),
    rs.getString("category"),
    rs.getDouble("price"),
    stockQty,
    stockStatus
});
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
  
public boolean updateProduct(Product product) {

    boolean success = false;

    try {

        Connection conn =
                DatabaseManager.connectDB();

        String sql =
"UPDATE Product SET title=?, author=?, isbn=?, category=?, price=?, stockQty=? WHERE productID=?";
       
        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1, product.getTitle());

pst.setString(2, product.getAuthor());

pst.setString(3, product.getIsbn());

pst.setString(4, product.getCategory());

pst.setDouble(5, product.getPrice());

pst.setInt(6,product.getInventory().getStockQuantity());

pst.setInt(7, product.getProductID());
       
int rowsUpdated =
                pst.executeUpdate();

        success = rowsUpdated > 0;

        conn.close();

    } catch (Exception e) {

        System.out.println(e);
    }

    return success;
}
 
public boolean deleteProduct(int productID) {

    String sql = "DELETE FROM Product WHERE productID=?";

    try {

        Connection conn = DatabaseManager.connectDB();

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, productID);

        pst.executeUpdate();

        conn.close();

        return true;

    } catch (Exception e) {

        e.printStackTrace();

        return false;
    }
}

}
