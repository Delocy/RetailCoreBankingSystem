/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import javax.ejb.Remote;

/**
 *
 * @author zares
 */
@Remote
public interface BankingServiceSessionBeanRemote {

   public void createCustomer(Long customerId, String firstName, String lastName, String identificationnumber, String contactNumber, String addressLine1, String addressLine2, String postalCode);

   void openDepositAccount(Customer customer);
    
}
