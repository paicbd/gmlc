package org.mobicents.gmlc.slee.supl;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public enum SuplAreaEventType {

    ENTERING_AREA(0), INSIDE_AREA(1), OUTSIDE_AREA(2), LEAVING_AREA(3), UNDEFINED(-999);

    private final int suplAreaEventType;

    SuplAreaEventType(int suplAreaEventType) {
        this.suplAreaEventType = suplAreaEventType;
    }

    public int getSuplAreaEventType() {
        return suplAreaEventType;
    }

    @Override
    public String toString() {
        return "SuplAreaEventType{" +
            "suplAreaEventType=" + suplAreaEventType +
            '}';
    }
}
