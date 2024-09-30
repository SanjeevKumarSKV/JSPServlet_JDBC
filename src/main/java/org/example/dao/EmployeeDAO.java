package org.example.dao;

import org.example.dao.model.Employee;
import org.example.dao.model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 *
 * @author Ramesh Fadatare
 *
 */
public class EmployeeDAO {
    private static final String  jdbcURL = "jdbc:mysql://localhost:3306/employee_test";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "password";

    private static final String INSERT_USERS_SQL = "INSERT INTO employee" + "  (name, password) VALUES "
            + " (?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,password from  employee where id =?";
    private static final String SELECT_ALL_USERS = "select * from employee";
    private static final String DELETE_USERS_SQL = "delete from employee where id = ?;";
    private static final String UPDATE_USERS_SQL = "update employee set name = ?,password= ?, where id = ?;";
    private List<org.example.dao.model.Employee> Employee;

    public EmployeeDAO() {
    }

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            System.out.println("Before Connecting to the database!");
            Class.forName(
                    "com.mysql.jdbc.Driver");
            System.out.println("Connected to the database!");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void createTable(Statement stmt){
        String tableSql = "CREATE TABLE IF NOT EXISTS Employee"
                + "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
                + " password varchar(30))";
        try {
            stmt.execute(tableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertEmployee(@NotNull Employee employee) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        Connection connection = getConnection();
        createTable(connection.createStatement());

        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPassword());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static Employee selectEmployee(int id) {
        Employee employee = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                employee = new Employee(id, name, password);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boilerplate code)
        List<Employee> employee = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                employee.add(new Employee(id, name, password));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getPassword());
            statement.setInt(4, employee.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<Employee> selectAllEmployee() {
        return Employee;
    }
}