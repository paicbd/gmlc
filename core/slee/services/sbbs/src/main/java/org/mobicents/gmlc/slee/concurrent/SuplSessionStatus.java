package org.mobicents.gmlc.slee.concurrent;

public enum SuplSessionStatus {

    SUPL_SESSION_ACTIVE(1), SUPL_SESSION_END(0);

    private final int suplSessionStatus;

    SuplSessionStatus(int suplSessionStatus) {
        this.suplSessionStatus = suplSessionStatus;
    }

    public int getSuplSessionStatus() {
        return suplSessionStatus;
    }
}
