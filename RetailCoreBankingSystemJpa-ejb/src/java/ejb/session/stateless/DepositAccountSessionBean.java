/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.DepositAccount;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import util.exception.CustomerNotFoundException;
import util.exception.DepositAccountExistException;
import util.exception.DepositAccountNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zares
 */
@Stateless
public class DepositAccountSessionBean implements DepositAccountSessionBeanRemote, DepositAccountSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingSystemJpa-ejbPU")
    private EntityManager em;
    
    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public DepositAccount openNewDepositAccount(DepositAccount newDepositAccount, Long customerId) throws DepositAccountExistException, UnknownPersistenceException, CustomerNotFoundException {
        try {
            Customer customer = customerSessionBeanLocal.retrieveCustomerByID(customerId);
            newDepositAccount.setCustomer(customer);
            customer.addDepositAccount(newDepositAccount);
            em.persist(newDepositAccount);
            em.flush();
            
        } catch (PersistenceException ex) {
            if (ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException")) {
                if (ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) {
                    throw new DepositAccountExistException("Deposit Account with the same account number already exists!");
                } else {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            }
        } catch (CustomerNotFoundException ex) {
            throw new CustomerNotFoundException("Customer record not found in the database!");
        }
        return newDepositAccount;
    }
    
    @Override
    public DepositAccount retrieveDepositAccountByID(Long depositAccountId) throws DepositAccountNotFoundException {
        DepositAccount depositAccount = em.find(DepositAccount.class, depositAccountId);

        if (depositAccount != null) {
            return depositAccount;
        } else {
            throw new DepositAccountNotFoundException("Deposit Account ID " + depositAccountId + "does not exist!");
        }
    }

    
}
