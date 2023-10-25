/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import util.exception.ATMCardNoExistException;
import util.exception.ATMCardNotFoundException;
import util.exception.AtmCardAndAccountDifferentOwnerException;
import util.exception.CustomerNotFoundException;
import util.exception.DepositAccountNotFoundException;
import util.exception.IncorrectPinException;
import util.exception.InvalidAtmCardException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zares
 */
@Stateless
public class ATMCardSessionBean implements ATMCardSessionBeanRemote, ATMCardSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingSystemJpa-ejbPU")
    private EntityManager em;

    @EJB
    private DepositAccountSessionBeanLocal depositAccountSessionBeanLocal;
    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public AtmCard issueNewATMCard(AtmCard card, Long customerID, List<Long> depositAccountIDs) throws ATMCardNoExistException, UnknownPersistenceException, CustomerNotFoundException, Exception {
        try {
            Customer currentCustomer = customerSessionBeanLocal.retrieveCustomerByID(customerID);
           
            
            card.setCustomer(currentCustomer);
            currentCustomer.setAtmCard(card);
            for (Long ID : depositAccountIDs) {
                DepositAccount account = depositAccountSessionBeanLocal.retrieveDepositAccountByID(ID);
                account.setAtmCard(card);
                card.getDepositAccounts().add(account);
            }
            em.persist(card);
            em.flush();
            return card;
           
        } catch (CustomerNotFoundException ex) {
            throw new CustomerNotFoundException("Unable to create new ATM card as the customer record does not exist");
        }
        catch (PersistenceException ex) {
            if (ex.getCause() != null && ex.getCause().getCause() != null && ex.getCause().getCause().getClass()
                    .getSimpleName().equals("SQLIntegrityConstraintViolationException")) {
                throw new ATMCardNoExistException("Atm card with same card number already exist");
            } else {
                throw new Exception("An unexpected error has occurred: " + ex.getMessage());
            }
        }
        
    }
    
    @Override
    public void removeATMCard (Long cardID) throws CustomerNotFoundException, ATMCardNotFoundException {
        AtmCard card = retrieveATMCardByID(cardID);
        card.getCustomer().setAtmCard(null);
        for (DepositAccount depositAccount : card.getDepositAccounts()) {
            depositAccount.setAtmCard(null);
        }
        card.setDepositAccounts(new ArrayList<>());
        em.remove(card);
    }
    
    @Override
    public AtmCard retrieveATMCardByID (Long cardID) throws ATMCardNotFoundException {
        AtmCard card = em.find(AtmCard.class, cardID);

        if (card != null) {
            return card;
        } else {
            throw new ATMCardNotFoundException("Card " + cardID + "does not exist!");
        }
    }
    
    @Override
    public void changePin (Long cardID, String currPin, String newPin) throws IncorrectPinException,ATMCardNotFoundException  {
        AtmCard currentCard = retrieveATMCardByID(cardID);
        String existingPin = currentCard.getPin();
        if (existingPin.equals(currPin)) {
            currentCard.setPin(newPin);
        } else {
            throw new IncorrectPinException("The current pin is incorrect, please try again.");
        }
        em.persist(currentCard);
    }
    
    public List<DepositAccount> retrieveDepositAccounts(Long cardID) throws ATMCardNotFoundException {
        AtmCard currentCard = retrieveATMCardByID(cardID);
        return currentCard.getDepositAccounts();
    }
    
}

