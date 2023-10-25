/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package automatedtellermachineclientjpa;

import ejb.session.stateless.ATMCardSessionBeanRemote;
import javax.ejb.EJB;
import util.exception.ATMCardNotFoundException;
import util.exception.IncorrectPinException;

/**
 *
 * @author zares
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    @EJB
    private static ATMCardSessionBeanRemote atmCardSessionBeanRemote;
    
    public static void main(String[] args) throws IncorrectPinException, ATMCardNotFoundException {
        // TODO code application logic here
    
    
        MainApp mainApp = new MainApp(atmCardSessionBeanRemote);
        mainApp.runApp();
    }
}
