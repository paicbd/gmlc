package org.mobicents.gmlc.slee.supl;

/**
 * @author <a href="mailto:enmanuelcalero61@gmail.com"> Enmanuel Calero </a>
 */
public enum SuplSessionType {
    SUPL_PERIODIC(0), SUPL_SINGLE(1);

    private final int suplSessionType;

    SuplSessionType(int suplSessionType) {
        this.suplSessionType = suplSessionType;
    }

    public int getSuplSessionType() {
        return suplSessionType;
    }
}
