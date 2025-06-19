package org.mobicents.gmlc.slee.diameter.slg;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLgException extends Exception {

    private String plrError;
    private String plaError;
    private String lrrError;
    private String lraError;

    public SLgException(String message) {
        super(message);
    }

    public SLgException(String message, String plrError, String plaError, String lrrError, String lraError) {
        super(message);
        this.plrError = plrError;
        this.plaError = plaError;
        this.lrrError = lrrError;
        this.lraError = lraError;
    }

    public SLgException(String message, Throwable cause, String plrError, String plaError, String lrrError, String lraError) {
        super(message, cause);
        this.plrError = plrError;
        this.plaError = plaError;
        this.lrrError = lrrError;
        this.lraError = lraError;
    }

    @Override
    public String toString() {
        return "SLg exception.\nPLR error : "+plrError+"\nPLA error : "+plaError+"\nLRR error : "+lrrError+"\nLRA error : "+lraError;
    }

}
