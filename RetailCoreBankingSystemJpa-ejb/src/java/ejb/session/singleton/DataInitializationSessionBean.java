/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejb.session.singleton;

//import ejb.session.stateless.ProductEntitySessionBeanLocal;
//import ejb.session.stateless.StaffEntitySessionBeanLocal;
import ejb.session.stateless.EmployeeSessionBeanLocal;
import entity.Customer;
import entity.Employee;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import util.enumeration.EmployeeAccessRightEnum;
import util.exception.EmployeeIDExistException;
import util.exception.EmployeeNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zares
 */

@Singleton
@LocalBean
@Startup

public class DataInitializationSessionBean {
    
    @EJB
    private EmployeeSessionBeanLocal employeeSessionBean;
    
    public DataInitializationSessionBean()
    {
    }
    
    
    @PostConstruct
    public void postConstruct()
    {
        try
        {
            employeeSessionBean.retrieveEmployeeByUsername("teller") ;
        }
        catch(EmployeeNotFoundException ex)
        {
            initializeData();
        }
    }
    
    private void initializeData()
    {
        employeeSessionBean.createNewEmployee(new Employee("Default", "Zares", "teller", "password", EmployeeAccessRightEnum.TELLER));
    }
}
