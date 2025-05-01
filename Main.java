import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Connection details
        String url = "jdbc:mysql://localhost:3306/inventory_db";
        String username = "root";
        String password = "Equinox47@26";  // Your MySQL password

        // Uncomment the operation you want to run

        // 1. Insert Product (Uncomment to run)
//         insertProduct(url, username, password);

        // 2. Retrieve Products (Uncomment to run)
//         retrieveProducts(url, username, password);

        // 3. Update Product (Uncomment to run)
//         updateProduct(url, username, password);

        // 4. Delete Product (Uncomment to run)
//         deleteProduct(url, username, password);
    }

    // Insert product method
    private static void insertProduct(String url, String username, String password) {
        String sql = "INSERT INTO products (name, quantity, price, supplier_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set values for the query parameters
            statement.setString(1, "Laptop");
            statement.setInt(2, 10);
            statement.setBigDecimal(3, new java.math.BigDecimal("799.99"));
            statement.setInt(4, 1);

//            statement.setString(1, "Bag");
//            statement.setInt(2, 50);
//            statement.setBigDecimal(3, new java.math.BigDecimal("850.99"));
//            statement.setInt(4, 1);

            // Execute the update
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product inserted successfully!");
            } else {
                System.out.println("Failed to insert product.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve products method
    private static void retrieveProducts(String url, String username, String password) {
        String sql = "SELECT * FROM products";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                int supplierId = resultSet.getInt("supplier_id");
                System.out.println("ID: " + id + ", Name: " + name + ", Quantity: " + quantity + ", Price: " + price + ", Supplier ID: " + supplierId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update product method
    private static void updateProduct(String url, String username, String password) {
        String sql = "UPDATE products SET price = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set values for the query parameters
            statement.setBigDecimal(1, new java.math.BigDecimal("899.99"));
            statement.setInt(2, 3);  // Update the product with id = 1

            // Execute the update
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Failed to update product.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete product method
    private static void deleteProduct(String url, String username, String password) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set the id of the product to delete
            statement.setInt(1, 4);  // Delete the product with id = 1

            // Execute the delete
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Failed to delete product.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
