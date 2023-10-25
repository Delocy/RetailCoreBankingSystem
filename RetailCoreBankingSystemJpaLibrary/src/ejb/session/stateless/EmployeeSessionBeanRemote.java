/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
import javax.ejb.Remote;
import util.exception.EmployeeNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author zares
 */
@Remote
public interface EmployeeSessionBeanRemote {
    
    Long createNewEmployee(Employee newEmployee);

    Employee retrieveEmployeeByUsername(String username) throws EmployeeNotFoundException;
    
    Employee employeeLogin(String username, String password) throws InvalidLoginCredentialException;
}
