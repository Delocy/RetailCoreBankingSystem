/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zares
 */
@Stateless
public class BankingServiceSessionBean implements BankingServiceSessionBeanRemote, BankingServiceSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingSystemJpa-ejbPU")
    private EntityManager em;

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void createCustomer(Long customerId, String firstName, String lastName, String identificationnumber, String contactNumber, String addressLine1, String addressLine2, String postalCode) {
        try {
            // Check if the customer already exists based on their identification number
            Customer existingCustomer = em.createNamedQuery("findCustomerByIdentificationNumber", Customer.class)
                .setParameter("identificationNumber", identificationnumber)
                .getSingleResult();
            
            // Customer with the same identification number already exists, handle accordingly
            // what if i just create the the customer then em.find()
        } catch (NoResultException e) {
            // Customer doesn't exist, proceed to create a new customer
            Customer newCustomer = new Customer(customerId, firstName, lastName, identificationnumber, contactNumber, addressLine1, addressLine2, postalCode);
            em.persist(newCustomer);
        }
    }

    @Override
    public void openDepositAccount(Customer customer) {
        
    }
    
    
    
    
}
