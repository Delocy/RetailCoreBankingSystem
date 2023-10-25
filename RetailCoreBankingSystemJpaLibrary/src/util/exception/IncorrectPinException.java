/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class IncorrectPinException extends Exception {

    /**
     * Creates a new instance of <code>IncorrectPinException</code> without
     * detail message.
     */
    public IncorrectPinException() {
    }

    /**
     * Constructs an instance of <code>IncorrectPinException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IncorrectPinException(String msg) {
        super(msg);
    }
}
