/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatedtellermachineclientjpa;

import ejb.session.stateless.ATMCardSessionBeanRemote;
import entity.AtmCard;
import entity.DepositAccount;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;
import util.exception.ATMCardNotFoundException;
import util.exception.DepositAccountNotFoundException;
import util.exception.IncorrectPinException;
import util.exception.InvalidAtmCardException;

/**
 *
 * @author zares
 */
public class MainApp {
    private ATMCardSessionBeanRemote atmCardSessionBeanRemote;
    private Long atmCardID;
    private AtmCard atmCard;

    public MainApp(ATMCardSessionBeanRemote atmCardSessionBeanRemote) {
        this.atmCardSessionBeanRemote = atmCardSessionBeanRemote;
    }

    
    public void runApp() throws IncorrectPinException, ATMCardNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        while (true) {
            System.out.println("*** Welcome to RCBS - ATM ***\n");
            System.out.println("1: Insert ATM Card");
            System.out.println("2: Exit\n");
            response = 0;
            while (response < 1 || response > 2) {
                System.out.print("> ");
                response = scanner.nextInt();
                if (response == 1) {
                    try {
                        doInsertAtmCard();
                        menuMain();
                    } catch (InvalidAtmCardException ex) {
                        System.out.println("ATM Card is invalid! " + ex.getMessage() + "\n");
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
    
    private void doInsertAtmCard() throws InvalidAtmCardException, ATMCardNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ATM Card ID> ");
        atmCardID = scanner.nextLong();
        atmCard = atmCardSessionBeanRemote.retrieveATMCardByID(atmCardID);
    }
    
    private void menuMain() throws IncorrectPinException, ATMCardNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;

        while (true) {
            System.out.println("*** Welcome " + atmCard.getNameOnCard() + " to RCBS - ATM ***\n");
            System.out.println("1: Change PIN");
            System.out.println("2: Enquire available balance");
            System.out.println("3: Logout\n");
            response = 0;
            while (response < 1 || response > 4) {
                System.out.print("> ");

                response = scanner.nextInt();

                if (response == 1) {
                    doChangePin();
                } else if (response == 2) {
                    doBalanceEnquiry();
                } else if (response == 3) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            if (response == 3) {
                break;
            }
        }
    }
        
    private void doChangePin() throws IncorrectPinException, ATMCardNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String currPin = "";
        String newPin = "";
        System.out.println("*** RCBS - ATM : Change PIN ***\n");
        System.out.print("Enter current PIN> ");
        currPin = scanner.nextLine().trim();
        System.out.print("Enter new PIN> ");
        newPin = scanner.nextLine().trim();
        atmCardSessionBeanRemote.changePin(atmCardID, currPin, newPin);
        System.out.println("ATM card PIN changed!");
    }

    private void doBalanceEnquiry() throws ATMCardNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** RCBS - ATM : Enquire Available Balance ***\n");
//        AtmCard currentATMCard = atmCardSessionBeanRemote.retrieveATMCardByID(atmCardID);
        List<DepositAccount> depositAccounts = atmCardSessionBeanRemote.retrieveDepositAccounts(atmCardID);
                //currentATMCard.getDepositAccounts();
        System.out.printf("%4s%16s%24s\n", "Index", "Account Type", "Account Number");
        int index = 0;
        for (DepositAccount depositAccount : depositAccounts) {
            ++index;
            System.out.printf("%4s%16s%24s\n", index, depositAccount.getAccountType().toString(), depositAccount.getAccountNumber());
        }
        System.out.println("----------------------------------------------");
        System.out.print("Account to Enquire > ");
        int response = scanner.nextInt();
        if (response >= 1 && response <= index) {
            System.out.println("Available balance is $" + depositAccounts.get(response - 1).getAvailableBalance());
        } else {
            System.out.println("Invalid option!\n");
        }
    }
}
