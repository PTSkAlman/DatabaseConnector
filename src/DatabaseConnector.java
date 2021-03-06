import javax.swing.*;
import java.sql.*;

public class DatabaseConnector {

    Connection connection;

    public DatabaseConnector() {
/*
        JPasswordField pf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String password = new String(pf.getPassword());
*/
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/te19? " +
                            "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database error!");
            System.exit(0);
        }
    }

    public String getDatabaseContent() {
        String result = "";
        try {
            // Setup statement
            Statement stmt = connection.createStatement();

            // Create query and execute
            String SQLQuery = "select * from meeps";
            ResultSet rset = stmt.executeQuery(SQLQuery);

            // Loop through the result set and convert to String
            // Need to know the table-structure
            while (rset.next()) {
                result += rset.getInt("id") + ", " +
                        rset.getString("body") + ", " +
                        rset.getString("created_at") + ", " +
                        rset.getString("updated_at") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Something went wrong, check your tablestructure...");
            return "Error reading result from SQL-query";
        }
        return result;
    }

    public String getTableInfo() {
        String result = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM author");

            // Get resultset metadata
            ResultSetMetaData metadata = results.getMetaData();
            int columnCount = metadata.getColumnCount();

            // Get the column names; column indices start from 1
            for (int i=1; i<=columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                result += columnName + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
