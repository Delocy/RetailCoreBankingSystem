/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class InvalidAtmCardException extends Exception {

    /**
     * Creates a new instance of <code>InvalidAtmCardException</code> without
     * detail message.
     */
    public InvalidAtmCardException() {
    }

    /**
     * Constructs an instance of <code>InvalidAtmCardException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidAtmCardException(String msg) {
        super(msg);
    }
}
