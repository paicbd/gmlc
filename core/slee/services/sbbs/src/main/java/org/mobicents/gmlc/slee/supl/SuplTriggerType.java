package org.mobicents.gmlc.slee.supl;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public enum SuplTriggerType {

    Periodic(0), AreaEvent(1), Undefined(-999);

    private final int suplTriggerType;

    SuplTriggerType(int suplTriggerType) {
        this.suplTriggerType = suplTriggerType;
    }

    public int getSuplTriggerType() {
        return suplTriggerType;
    }

    @Override
    public String toString() {
        return "SuplTriggerType{" +
            "suplTriggerType=" + suplTriggerType +
            '}';
    }
}
