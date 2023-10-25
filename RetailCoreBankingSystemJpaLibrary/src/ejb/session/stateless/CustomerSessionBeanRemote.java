/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import javax.ejb.Remote;
import util.exception.CustomerIDExistException;
import util.exception.CustomerNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zares
 */
@Remote
public interface CustomerSessionBeanRemote {
    
    public Long createNewCustomer(Customer newCustomer) throws CustomerIDExistException, UnknownPersistenceException;
    
    public Customer retrieveCustomerByID(Long customerId) throws CustomerNotFoundException;
}
