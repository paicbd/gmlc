package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "PublicIdentifiers")
public class PublicIds implements Serializable {

  private static final long serialVersionUID = 1L;
  private String msisdn;
  private String imsPublicIdentity;

  public PublicIds() {
    super();
  }

  public String getMsisdn() {
    return msisdn;
  }
  @XmlElement(name = "MSISDN")
  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getImsPublicIdentity() {
    return imsPublicIdentity;
  }
  @XmlElement(name = "IMSPublicIdentity")
  public void setImsPublicIdentity(String imsPublicIdentity) {
    this.imsPublicIdentity = imsPublicIdentity;
  }


  @Override
  public String toString() {
    return "PublicIdentifiers{" +
        "msisdn='" + msisdn + '\'' +
        ", imsPublicIdentity='" + imsPublicIdentity + '\'' +
        '}';
  }
}
