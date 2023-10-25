/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tellerterminalclient;

import ejb.session.stateless.ATMCardSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.DepositAccountSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import entity.Employee;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import util.enumeration.DepositAccountType;
import util.exception.CustomerIDExistException;
import util.exception.CustomerNotFoundException;
import util.exception.DepositAccountExistException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author zares
 */
public class MainApp {
    private EmployeeSessionBeanRemote employeeSessionBeanRemote;
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private DepositAccountSessionBeanRemote depositAccountSessionBeanRemote;
    private ATMCardSessionBeanRemote atmCardSessionBeanRemote;
    
    private Employee currentEmployee;
    
    public MainApp() 
    {        
    }

    public MainApp(EmployeeSessionBeanRemote employeeSessionBeanRemote, CustomerSessionBeanRemote customerSessionBeanRemote, DepositAccountSessionBeanRemote depositAccountSessionBeanRemote, ATMCardSessionBeanRemote atmCardSessionBeanRemote) {
        this.employeeSessionBeanRemote = employeeSessionBeanRemote;
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.depositAccountSessionBeanRemote = depositAccountSessionBeanRemote;
        this.atmCardSessionBeanRemote = atmCardSessionBeanRemote;
    }
    
    public void runApp() {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;

        while (true) {
            System.out.println("*** Welcome to Retail Core Banking System - Teller Terminal ***\n");
            System.out.println("1: Login");
            System.out.println("2: Exit\n");
            response = 0;
            while (response < 1 || response > 2) {
                System.out.print("> ");
                response = scanner.nextInt();
                if (response == 1) {
                    try {
                        doLogin();
                        System.out.println("Login successful!\n");
                        menuMain();
                    } catch (InvalidLoginCredentialException ex) {
                        System.out.println("Invalid login credentials " + ex.getMessage());
                    }
                } else if (response == 2) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            if (response == 2) {
                break;
            }
        }
    }

    private void doLogin() throws InvalidLoginCredentialException
    {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";
        
        System.out.println("*** Teller System :: Login ***\n");
        System.out.print("Enter username> ");
        username = scanner.nextLine().trim();
        System.out.print("Enter password> ");
        password = scanner.nextLine().trim();
        
        if(username.length() > 0 && password.length() > 0)
        {
            currentEmployee = employeeSessionBeanRemote.employeeLogin(username, password);      
        }
        else
        {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    private void menuMain() 
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while (true)
        {
            System.out.println("*** Retail-Core-Banking-System (RCBS) ***\n");
            System.out.println("You are login as " + currentEmployee.getFirstName() + " " + currentEmployee.getLastName() + " with " + currentEmployee.getAccessRight().toString() + " rights\n");
            System.out.println("1: Create Customer");
            System.out.println("2: Open Deposit Account");
            System.out.println("3: Issue/Replace ATM Card");
            System.out.println("4: Logout\n");
            response = 0;

            while (response < 1 || response > 4) {
                System.out.print("> ");

                response = scanner.nextInt();

                if (response == 1) {
                    doCreateCustomer();
                } else if (response == 2) {
                    doOpenDepositAccount();
                } else if (response == 3) {
                    doIssueAtmCard();
                } else if (response == 4) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }

            if (response == 4) {
                break;
            }
        }
    }
    
    private void doCreateCustomer() {
        Scanner scanner = new Scanner(System.in);
        Customer newCustomer = new Customer();
        System.out.println("*** RCBS - Create Customer ***\n");
        System.out.print("Enter first name> ");
        newCustomer.setFirstName(scanner.nextLine().trim());
        System.out.print("Enter last name> ");
        newCustomer.setLastName(scanner.nextLine().trim());
        System.out.print("Enter identification number> ");
        newCustomer.setIdentificationnumber(scanner.nextLine().trim());
        System.out.print("Enter contact number> ");
        newCustomer.setContactNumber(scanner.nextLine().trim());
        System.out.print("Enter address line 1> ");
        newCustomer.setAddressLine1(scanner.nextLine().trim());
        System.out.print("Enter address line 2> ");
        newCustomer.setAddressLine2(scanner.nextLine().trim());
        System.out.print("Enter postal code> ");
        newCustomer.setPostalCode(scanner.nextLine().trim());
        try {
            Long CustomerID = customerSessionBeanRemote.createNewCustomer(newCustomer);
            System.out.println("** Customer " + newCustomer.getFirstName() + newCustomer.getLastName() + " has been successfully created **");
        } catch (CustomerIDExistException | UnknownPersistenceException ex) {
            System.out.println("Error occured when creating the new customer " + ex.getMessage());
        }
    }
    
    private void doOpenDepositAccount() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*** RCBS - Open new deposit account ***\n");
            System.out.println("Enter Customer ID> ");
            Long customerId = scanner.nextLong();
            Customer customer = customerSessionBeanRemote.retrieveCustomerByID(customerId);
            System.out.println("Opening deposit account for " + customer.getFirstName() + customer.getLastName() + "\n");
            DepositAccount newDepositAccount = new DepositAccount();
            newDepositAccount.setEnabled(true);
            scanner.nextLine();
            System.out.println("Enter account number> ");
            newDepositAccount.setAccountNumber(scanner.nextLine().trim());
            System.out.println("Select account type (1: Savings, 2: Current)> ");
            Integer accountTypeInt = scanner.nextInt();
            while (true) {
                if (accountTypeInt == 1) {
                    newDepositAccount.setAccountType(DepositAccountType.values()[accountTypeInt - 1]);
                    break;
                } else {
                    System.out.println("Not valid now , please try again!\n");
                    return;
                }
            }
            
            System.out.println("Enter initial deposit amount> $");
            BigDecimal initialAmount = scanner.nextBigDecimal();
            newDepositAccount.setAvailableBalance(initialAmount);
            System.out.println("Initial Deposit: " + initialAmount);
            System.out.println("Enter holding amount> $");
            BigDecimal holdingAmount = scanner.nextBigDecimal();
            newDepositAccount.setHoldBalance(holdingAmount);
            BigDecimal ledgerAmount = initialAmount.add(holdingAmount);
            newDepositAccount.setLedgerBalance(ledgerAmount);

            // to push into em
            newDepositAccount = depositAccountSessionBeanRemote.openNewDepositAccount(newDepositAccount, customerId);
            System.out.println("Successfully created new deposit account " + newDepositAccount.getAccountNumber() + " for  " + customer.getFirstName() + " " + customer.getLastName() + "!\n");
        } catch (CustomerNotFoundException | UnknownPersistenceException ex) {
            System.out.println(ex.getMessage() + "!\n");
        } catch (DepositAccountExistException ex) {
            System.out.println(ex.getMessage() + "!\n");
        }
    }
    
    private void doIssueAtmCard() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("*** RCBS - Issue new ATM Card ***\n");
            System.out.println("Enter Customer Id> ");
            Long customerId = scanner.nextLong();
            scanner.nextLine();
            Customer customer = customerSessionBeanRemote.retrieveCustomerByID(customerId);
            
//            System.out.println(customer.getFirstName());
//            System.out.println(customer.getAtmCard());
            AtmCard atmCard = customer.getAtmCard();
            if (atmCard != null) {
                System.out.println("Do you want to replace the existing ATM Card? ('Y' to remove, 'N' to go back)> ");
                String input = scanner.nextLine().trim();
                if (input.equals("Y")) {
                    atmCardSessionBeanRemote.removeATMCard(atmCard.getAtmCardId());
                    System.out.println("Current ATM Card removed");
                } else {
                    System.out.println("Current ATM Card not removed");
                    return;
                }
            }

            AtmCard newAtmCard = new AtmCard();
            System.out.println(customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Enter card number> ");
            newAtmCard.setCardNumber(scanner.nextLine().trim());
            System.out.println("Enter name on ATM Card> ");
            newAtmCard.setNameOnCard(scanner.nextLine().trim());
            System.out.println("Enter PIN> ");
            newAtmCard.setPin(scanner.nextLine().trim());
            List<Long> depositAccountIDs = new ArrayList<>();
            System.out.println("Tag existing deposit accounts to the new ATM card");
            for (DepositAccount depositAccount : customer.getDepositAccounts()) {
                System.out.println("Tag account " + depositAccount.getAccountNumber() + "? (Enter 'Y' to link)> ");
                String input = scanner.nextLine().trim();
                if (input.equals("Y")) {
                    depositAccountIDs.add(depositAccount.getDepositAccountId());
                }
            }
    
            if (!depositAccountIDs.isEmpty()) {
                newAtmCard.setEnabled(true);
//                List<DepositAccount> emptyDepositAccounts = new ArrayList<>();
//                newAtmCard.setDepositAccounts(emptyDepositAccounts);
                newAtmCard = atmCardSessionBeanRemote.issueNewATMCard(newAtmCard, customerId, depositAccountIDs);
                System.out.println("New ATM card created successfully!: " + newAtmCard.getAtmCardId() + "\n");
            } else {
                System.out.println("One deposit account has to be chosen, unable to create new ATM card!\n");
            }
        } catch (Exception ex) {
            System.out.println("An error occured : " + ex.getMessage() + "!\n");
        }
    }
}
