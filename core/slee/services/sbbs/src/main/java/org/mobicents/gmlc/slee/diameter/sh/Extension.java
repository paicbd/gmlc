package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Sh-Data")
public class Extension implements Serializable {

  private static final long serialVersionUID = 1L;
  private Extension extension;
  private EPSLocationInformation epsLocationInformation;
  private Sh5GSLocationInformation sh5GSLocationInformation;
  private UserCSGInformation userCSGInformation;
  private String visitedPLMNId;
  private LocalTimeZone localTimeZone;
  private Integer ratType;

  public Extension() {
    super();
  }

  public Extension getExtension() {
    return extension;
  }
  @XmlElement (name = "Extension")
  public void setExtension(Extension extension) {
    this.extension = extension;
  }

  public EPSLocationInformation getEpsLocationInformation() {
    return epsLocationInformation;
  }
  @XmlElement (name = "EPSLocationInformation")
  public void setEpsLocationInformation(EPSLocationInformation epsLocationInformation) {
    this.epsLocationInformation = epsLocationInformation;
  }

  public Sh5GSLocationInformation getSh5GSLocationInformation() {
    return sh5GSLocationInformation;
  }
  @XmlElement (name = "Sh-5GSLocationInformation")
  public void setSh5GSLocationInformation(Sh5GSLocationInformation sh5GSLocationInformation) {
    this.sh5GSLocationInformation = sh5GSLocationInformation;
  }

  public UserCSGInformation getUserCSGInformation() {
    return userCSGInformation;
  }
  @XmlElement (name = "UserCSGInformation")
  public void setUserCSGInformation(UserCSGInformation userCSGInformation) {
    this.userCSGInformation = userCSGInformation;
  }

  public String getVisitedPLMNId() {
    return visitedPLMNId;
  }
  @XmlElement (name = "VisitedPLMNID")
  public void setVisitedPLMNId(String visitedPLMNId) {
    this.visitedPLMNId = visitedPLMNId;
  }

  public LocalTimeZone getLocalTimeZone() {
    return localTimeZone;
  }
  @XmlElement (name = "LocalTimeZone")
  public void setLocalTimeZone(LocalTimeZone localTimeZone) {
    this.localTimeZone = localTimeZone;
  }

  public Integer getRatType() {
    return ratType;
  }
  @XmlElement (name = "RATtype")
  public void setRatType(Integer ratType) {
    this.ratType = ratType;
  }

  @Override
  public String toString() {
    return "Extension{" +
        "extension=" + extension +
        ", epsLocationInformation=" + epsLocationInformation +
        ", sh5GSLocationInformation=" + sh5GSLocationInformation +
        ", localTimeZone=" + localTimeZone +
        '}';
  }
}

