/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class ATMCardNoExistException extends Exception {

    /**
     * Creates a new instance of <code>ATMCardExistException</code> without
     * detail message.
     */
    public ATMCardNoExistException() {
    }

    /**
     * Constructs an instance of <code>ATMCardExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ATMCardNoExistException(String msg) {
        super(msg);
    }
}
