/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tellerterminalclient;

import ejb.session.stateless.ATMCardSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.DepositAccountSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.Employee;
import java.util.Scanner;
import javax.ejb.EJB;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author zares
 */
public class Main {

    /**
     * @param args the command line argument
     */
    @EJB
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;
    @EJB
    private static CustomerSessionBeanRemote customerSessionBeanRemote;
    @EJB
    private static DepositAccountSessionBeanRemote depositAccountSessionBeanRemote;
    @EJB
    private static ATMCardSessionBeanRemote atmCardSessionBeanRemote;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        MainApp mainApp = new MainApp(employeeSessionBeanRemote, customerSessionBeanRemote, depositAccountSessionBeanRemote, atmCardSessionBeanRemote);
        mainApp.runApp();
    }
}
