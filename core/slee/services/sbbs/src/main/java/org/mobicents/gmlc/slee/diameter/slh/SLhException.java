package org.mobicents.gmlc.slee.diameter.slh;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLhException extends Exception {

    private String rirError;
    private String riaError;

    public SLhException(String message) {
        super(message);
    }

    public SLhException(String message, String rirError, String riaError) {
        super(message);
        this.rirError = rirError;
        this.riaError = riaError;
    }

    public SLhException(String message, Throwable cause, String rirError, String riaError) {
        super(message, cause);
        this.rirError = rirError;
        this.riaError = riaError;
    }

    @Override
    public String toString() {
        return "SLh exception.\nRIR error : "+rirError+"\nRIA error : "+riaError;
    }

}
