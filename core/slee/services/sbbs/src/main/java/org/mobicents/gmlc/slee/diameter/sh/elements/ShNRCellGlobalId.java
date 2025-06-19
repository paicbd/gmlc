package org.mobicents.gmlc.slee.diameter.sh.elements;

import org.mobicents.gmlc.slee.primitives.NRCellGlobalId;
import org.mobicents.gmlc.slee.primitives.NRCellGlobalIdImpl;
import static org.mobicents.gmlc.slee.utils.ByteUtils.decodeHexString;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShNRCellGlobalId {

    private static final String MCC = "mcc";
    private static final String MNC = "mnc";
    private static final String NCI = "nci";

    private String nrCellGlobalIdStr;
    private NRCellGlobalId nrCellGlobalId;

    public ShNRCellGlobalId(String nrCellGlobalIdStr, NRCellGlobalId nrCellGlobalId) {
        this.nrCellGlobalIdStr = nrCellGlobalIdStr;
        this.nrCellGlobalId = nrCellGlobalId;
    }

    public ShNRCellGlobalId(String nrCellGlobalIdStr) {
        this.nrCellGlobalIdStr = nrCellGlobalIdStr;
    }

    public ShNRCellGlobalId(NRCellGlobalId nrCellGlobalId) {
        this.nrCellGlobalId = nrCellGlobalId;
    }

    public ShNRCellGlobalId() {
    }

    public NRCellGlobalId getNRCellGlobalId() {
        return nrCellGlobalId;
    }

    public void setNRCellGlobalId(NRCellGlobalId nrCellGlobalId) {
        this.nrCellGlobalId = nrCellGlobalId;
    }

    public String getNRCellGlobalIdStr() {
        return nrCellGlobalIdStr;
    }

    public void setNRCellGlobalIdStr(String nrCellGlobalIdStr) {
        if (nrCellGlobalIdStr != null) {
            StringBuilder sb = new StringBuilder();
            if (nrCellGlobalIdStr.length() == 15)
                sb.append(nrCellGlobalIdStr).append("0");
            else if (nrCellGlobalIdStr.length() == 14)
                sb.append(nrCellGlobalIdStr).append("00");
            byte[] nrCgiBytes = getNRCGIBytes(sb.toString());
            this.nrCellGlobalId = decodeNRCGIBytes(nrCgiBytes);
            this.nrCellGlobalIdStr = this.nrCellGlobalId.toString();
        }
    }

    public byte[] getNRCGIBytes(String nrCGIStr) {
        byte[] bytes = null;
        if (nrCGIStr != null)
            bytes = decodeHexString(nrCGIStr);
        return bytes;
    }

    public NRCellGlobalId decodeNRCGIBytes(byte[] nrCGIBytes) {
        NRCellGlobalId nrCellGlobalId = new NRCellGlobalIdImpl(nrCGIBytes);
        return nrCellGlobalId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NCGI");
        sb.append(" [");

        if (nrCellGlobalId != null) {

            try {
                try {
                    sb.append(MCC+"=");
                    sb.append(this.nrCellGlobalId.getMCC());
                    sb.append(", "+MNC+"=");
                    sb.append(this.nrCellGlobalId.getMNC());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                sb.append(", "+NCI+"=");
                sb.append(this.nrCellGlobalId.getNCI());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
