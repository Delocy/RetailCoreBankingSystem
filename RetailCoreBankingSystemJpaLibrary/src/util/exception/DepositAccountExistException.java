/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class DepositAccountExistException extends Exception {

    /**
     * Creates a new instance of <code>DepositAccountExistException</code>
     * without detail message.
     */
    public DepositAccountExistException() {
    }

    /**
     * Constructs an instance of <code>DepositAccountExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DepositAccountExistException(String msg) {
        super(msg);
    }
}
