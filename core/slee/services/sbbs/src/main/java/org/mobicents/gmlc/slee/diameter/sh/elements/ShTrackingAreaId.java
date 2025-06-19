package org.mobicents.gmlc.slee.diameter.sh.elements;

import org.mobicents.gmlc.slee.primitives.TrackingAreaId;
import org.mobicents.gmlc.slee.primitives.TrackingAreaIdImpl;

import java.util.Base64;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShTrackingAreaId {

  private static final String MCC = "mcc";
  private static final String MNC = "mnc";
  private static final String TAC = "tac";

  private String trackingAreaIdStr;
  private TrackingAreaId trackingAreaId;

  public ShTrackingAreaId(String trackingAreaIdStr, TrackingAreaId trackingAreaId) {
    this.trackingAreaIdStr = trackingAreaIdStr;
    this.trackingAreaId = trackingAreaId;
  }

  public ShTrackingAreaId(String trackingAreaIdStr) {
    this.trackingAreaIdStr = trackingAreaIdStr;
  }

  public ShTrackingAreaId(TrackingAreaId trackingAreaId) {
    this.trackingAreaId = trackingAreaId;
  }

  public ShTrackingAreaId() {
  }

  public TrackingAreaId getTrackingAreaId() {
    return trackingAreaId;
  }

  public void setTrackingAreaId(TrackingAreaId trackingAreaId) {
    this.trackingAreaId = trackingAreaId;
  }

  public String getTrackingAreaIdStr() {
    return trackingAreaIdStr;
  }

  public void setTrackingAreaIdStr(String trackingAreaIdentityStr) {
    if (trackingAreaIdentityStr != null) {
      byte[] raIdBytes = getTrackingAreaIdentityBytes(trackingAreaIdentityStr);
      this.trackingAreaId = decodeTrackingAreaIdentity(raIdBytes);
      this.trackingAreaIdStr = this.trackingAreaId.toString();
    }
  }

  public byte[] getTrackingAreaIdentityBytes(String trackingAreaIdentity) {
    byte[] bytes = Base64.getDecoder().decode(trackingAreaIdentity);
    return bytes;
  }

  public TrackingAreaId decodeTrackingAreaIdentity(byte[] raIdBytes) {
    TrackingAreaId trackingAreaIdentity = new TrackingAreaIdImpl(raIdBytes);
    return trackingAreaIdentity;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("TAI");
    sb.append(" [");

    if (trackingAreaId != null) {

      try {
        sb.append(MCC+"=");
        sb.append(this.trackingAreaId.getMCC());

        sb.append(", "+MNC+"=");
        sb.append(this.trackingAreaId.getMNC());

        sb.append(", "+TAC+"=");
        sb.append(this.trackingAreaId.getTAC());

      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    sb.append("]");

    return sb.toString();
  }

}
