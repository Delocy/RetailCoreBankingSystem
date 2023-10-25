/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.DepositAccount;
import javax.ejb.Remote;
import util.exception.CustomerNotFoundException;
import util.exception.DepositAccountExistException;
import util.exception.DepositAccountNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zares
 */
@Remote
public interface DepositAccountSessionBeanRemote {
    public DepositAccount openNewDepositAccount(DepositAccount newDepositAccount, Long customerId) throws DepositAccountExistException, UnknownPersistenceException, CustomerNotFoundException;
    
    public DepositAccount retrieveDepositAccountByID(Long depositAccountId) throws DepositAccountNotFoundException;
}
