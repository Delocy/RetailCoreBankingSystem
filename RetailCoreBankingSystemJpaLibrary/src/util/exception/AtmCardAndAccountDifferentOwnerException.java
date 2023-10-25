/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package util.exception;

/**
 *
 * @author zares
 */
public class AtmCardAndAccountDifferentOwnerException extends Exception {

    /**
     * Creates a new instance of
     * <code>AtmCardAndAccountDifferentOwnerException</code> without detail
     * message.
     */
    public AtmCardAndAccountDifferentOwnerException() {
    }

    /**
     * Constructs an instance of
     * <code>AtmCardAndAccountDifferentOwnerException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AtmCardAndAccountDifferentOwnerException(String msg) {
        super(msg);
    }
}
