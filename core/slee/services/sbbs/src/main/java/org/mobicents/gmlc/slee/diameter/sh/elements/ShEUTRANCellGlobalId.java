package org.mobicents.gmlc.slee.diameter.sh.elements;

import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;

import java.util.Base64;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShEUTRANCellGlobalId {

  private static final String MCC = "mcc";
  private static final String MNC = "mnc";
  private static final String ENB = "eNBId";
  private static final String CI = "ci";

  private String eUtranCgiStr;
  private EUTRANCGI eUtranCgi;

  public ShEUTRANCellGlobalId(String eUtranCgiStr, EUTRANCGI eUtranCgi) {
    this.eUtranCgiStr = eUtranCgiStr;
    this.eUtranCgi = eUtranCgi;
  }

  public ShEUTRANCellGlobalId(String eUtranCgiStr) {
    this.eUtranCgiStr = eUtranCgiStr;
  }

  public ShEUTRANCellGlobalId(EUTRANCGI eUtranCgi) {
    this.eUtranCgi = eUtranCgi;
  }

  public ShEUTRANCellGlobalId() {
  }

  public String getEutranCgiStr() {
    return eUtranCgiStr;
  }

  public void seteUtranCgiStr(String eUtranCgiStr) {
    this.eUtranCgiStr = eUtranCgiStr;
  }

  public EUTRANCGI getEutranCgi() {
    return eUtranCgi;
  }

  public void setEutranCgi(EUTRANCGI eUtranCgi) {
    this.eUtranCgi = eUtranCgi;
  }

  public void setECGIStr(String ecgiStr) {
    if (ecgiStr != null) {
      byte[] cgiBytes = getECGIBytes(ecgiStr);
      this.eUtranCgi = decodeECGIBytes(cgiBytes);
      this.eUtranCgiStr = this.eUtranCgi.toString();
    }
  }

  public byte[] getECGIBytes(String ecgiInfo) {
    byte[] bytes = Base64.getDecoder().decode(ecgiInfo);
    return bytes;
  }

  public EUTRANCGI decodeECGIBytes(byte[] cgiBytes) {
    EUTRANCGI ecgi = new EUTRANCGIImpl(cgiBytes);
    return ecgi;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ECGI");
    sb.append(" [");

    if (eUtranCgi != null) {

      try {
        try {
          sb.append(MCC+"=");
          sb.append(this.eUtranCgi.getMCC());
          sb.append(", "+MNC+"=");
          sb.append(this.eUtranCgi.getMNC());
        } catch (Exception e) {
          e.printStackTrace();
        }

        sb.append(", "+ENB+"=");
        sb.append(this.eUtranCgi.getENodeBId());

        sb.append(", "+CI+"=");
        sb.append(this.eUtranCgi.getCi());

      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    sb.append("]");

    return sb.toString();
  }

}
