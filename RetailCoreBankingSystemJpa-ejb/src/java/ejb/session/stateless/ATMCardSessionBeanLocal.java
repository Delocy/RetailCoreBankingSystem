/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Local;
import util.exception.ATMCardNoExistException;
import util.exception.ATMCardNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.DepositAccountNotFoundException;
import util.exception.IncorrectPinException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zares
 */
@Local
public interface ATMCardSessionBeanLocal {

    public AtmCard issueNewATMCard(AtmCard card, Long customerID, List<Long> depositAccountIDs) throws ATMCardNoExistException, UnknownPersistenceException, CustomerNotFoundException, Exception;

    public void removeATMCard(Long cardID) throws CustomerNotFoundException, ATMCardNotFoundException;

    public AtmCard retrieveATMCardByID(Long cardID) throws ATMCardNotFoundException;

    public void changePin(Long cardID, String currPin, String newPin) throws IncorrectPinException, ATMCardNotFoundException;

    public List<DepositAccount> retrieveDepositAccounts(Long cardID) throws ATMCardNotFoundException;
    
}
