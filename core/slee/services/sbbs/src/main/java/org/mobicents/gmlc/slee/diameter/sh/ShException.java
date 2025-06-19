package org.mobicents.gmlc.slee.diameter.sh;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShException extends Exception {

    private String udrError;
    private String udaError;

    public ShException(String message) {
        super(message);
    }

    public ShException(String message, Throwable cause, String udrError, String udaError) {
        super(message, cause);
        this.udrError = udrError;
        this.udaError = udaError;
    }

    @Override
    public String toString() {
        return "SLh exception.\nRIR error : "+udrError+"\nRIA error : "+udaError;
    }

}