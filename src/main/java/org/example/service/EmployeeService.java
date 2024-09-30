package org.example.service;

import org.example.dao.EmployeeDAO;
import org.example.dao.model.Employee;
import org.example.dao.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    static EmployeeDAO employeeDAO = new EmployeeDAO();

    public  List<Employee> selectAllEmployee() {
        List<Employee> listEmployee = employeeDAO.selectAllEmployee();
        return listEmployee;
    }

    public Employee selectEmployee(int id) {
        Employee existingEmployee = EmployeeDAO.selectEmployee(id);
        return existingEmployee;
    }

    public void insertEmployee(Employee newEmployee)
            throws SQLException {
        employeeDAO.insertEmployee(newEmployee);
    }

    public void updateEmployee(Employee employee)
            throws SQLException, IOException {
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(int id)
            throws SQLException, IOException {
        employeeDAO.deleteEmployee(id);
    }

    public void insertEmployee(HttpServletRequest request, HttpServletResponse response) {
    }
}
