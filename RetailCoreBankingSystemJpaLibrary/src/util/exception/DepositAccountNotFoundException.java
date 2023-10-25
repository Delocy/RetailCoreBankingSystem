/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class DepositAccountNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>DepositAccountNotFoundException</code>
     * without detail message.
     */
    public DepositAccountNotFoundException() {
    }

    /**
     * Constructs an instance of <code>DepositAccountNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DepositAccountNotFoundException(String msg) {
        super(msg);
    }
}
