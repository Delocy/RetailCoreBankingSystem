/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class CustomerIDExistException extends Exception {

    /**
     * Creates a new instance of <code>CustomerIDExistException</code> without
     * detail message.
     */
    public CustomerIDExistException() {
    }

    /**
     * Constructs an instance of <code>CustomerIDExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerIDExistException(String msg) {
        super(msg);
    }
}
