package org.mobicents.gmlc.slee.supl;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public enum SuplAreaId {

    GSM(1), WCDMA(2), CDMA(3), HRDP(4), UMB(5), LTE(6), WLAN(7), WIMAX(8), NR5G(9), EXTELEM1(10);

    private final int suplAreaId;

    SuplAreaId(int suplAreaId) {
        this.suplAreaId = suplAreaId;
    }

    public int getSuplAreaId() {
        return suplAreaId;
    }

    @Override
    public String toString() {
        return "SuplAreaId{" +
            "suplAreaId=" + suplAreaId +
            '}';
    }
}
