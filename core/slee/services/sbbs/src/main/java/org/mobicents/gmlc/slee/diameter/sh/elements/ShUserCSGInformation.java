package org.mobicents.gmlc.slee.diameter.sh.elements;

import net.java.slee.resource.diameter.sh.events.avp.userdata.UserCSGInformation;
import java.util.Base64;

import java.nio.charset.StandardCharsets;


/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ShUserCSGInformation {

  private static final String CSG_ID = "CSGId";

  private String userCSGInformationStr;
  private UserCSGInformation userCSGInformation;

  public ShUserCSGInformation(String userCGId, UserCSGInformation userCSGInformation) {
    this.userCSGInformation = userCSGInformation;
  }


  public ShUserCSGInformation(UserCSGInformation userCSGInformation) {
    this.userCSGInformation = userCSGInformation;
  }

  public ShUserCSGInformation() {
  }

  public UserCSGInformation getUserCSGInformation() {
    return userCSGInformation;
  }

  public void setUserCSGInformation(UserCSGInformation userCSGInformation) {
    this.userCSGInformation = userCSGInformation;
  }

  public String getUserCSGInformationStr() {
    return userCSGInformationStr;
  }

  public void setUserCSGInformationStr(String userCSGInformationStr) {
    if (userCSGInformationStr != null) {
      byte[] bytes = Base64.getDecoder().decode(userCSGInformationStr);
      String userCSGInfoStr = new String(bytes, StandardCharsets.ISO_8859_1);
      this.userCSGInformationStr = userCSGInfoStr;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("userCSGInformation");
    sb.append(" [ ");

    if (userCSGInformationStr != null) {

      try {
        sb.append(CSG_ID+"=");
        sb.append(this.userCSGInformationStr);

      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    sb.append("]");

    return sb.toString();
  }

}
