/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class EmployeeIDExistException extends Exception{

    /**
     * Creates a new instance of <code>EmployeeIDExistException</code> without
     * detail message.
     */
    public EmployeeIDExistException() {
    }

    /**
     * Constructs an instance of <code>EmployeeIDExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmployeeIDExistException(String msg) {
        super(msg);
    }
}
