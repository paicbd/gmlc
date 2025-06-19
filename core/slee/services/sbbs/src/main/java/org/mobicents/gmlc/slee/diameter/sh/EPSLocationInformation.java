package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "EPSLocationInformation")
public class EPSLocationInformation implements Serializable {

  private static final long serialVersionUID = 1L;
  private String eUTRANCellGlobalId;
  private String trackingAreaId;
  private String geographicalInformation;
  private String geodeticInformation;
  private String mmeName;
  private String currentLocationRetrieved;
  private String ageOfLocationInformation;
  private UserCSGInformation userCSGInformation;
  private EPSLocationInformationExtension epsLocationInformationExtension;

  public EPSLocationInformation() {
    super();
  }

  public String geteUTRANCellGlobalId() {
    return eUTRANCellGlobalId;
  }
  @XmlElement (name = "E-UTRANCellGlobalId")
  public void seteUTRANCellGlobalId(String eUTRANCellGlobalId) {
    this.eUTRANCellGlobalId = eUTRANCellGlobalId;
  }

  public String getTrackingAreaId() {
    return trackingAreaId;
  }
  @XmlElement (name = "TrackingAreaId")
  public void setTrackingAreaId(String trackingAreaId) {
    this.trackingAreaId = trackingAreaId;
  }

  public String getGeographicalInformation() {
    return geographicalInformation;
  }
  @XmlElement (name = "GeographicalInformation")
  public void setGeographicalInformation(String geographicalInformation) {
    this.geographicalInformation = geographicalInformation;
  }

  public String getGeodeticInformation() {
    return geodeticInformation;
  }
  @XmlElement (name = "GeodeticInformation")
  public void setGeodeticInformation(String geodeticInformation) {
    this.geodeticInformation = geodeticInformation;
  }

  public String getMmeName() {
    return mmeName;
  }
  @XmlElement (name = "MMEName")
  public void setMmeName(String mmeName) {
    this.mmeName = mmeName;
  }

  public String getCurrentLocationRetrieved() {
    return currentLocationRetrieved;
  }
  @XmlElement (name = "CurrentLocationRetrieved")
  public void setCurrentLocationRetrieved(String currentLocationRetrieved) {
    this.currentLocationRetrieved = currentLocationRetrieved;
  }

  public String getAgeOfLocationInformation() {
    return ageOfLocationInformation;
  }
  @XmlElement (name = "AgeOfLocationInformation")
  public void setAgeOfLocationInformation(String ageOfLocationInformation) {
    this.ageOfLocationInformation = ageOfLocationInformation;
  }

  public UserCSGInformation getUserCSGInformation() {
    return userCSGInformation;
  }
  @XmlElement (name = "UserCSGInformation")
  public void setUserCSGInformation(UserCSGInformation userCSGInformation) {
    this.userCSGInformation = userCSGInformation;
  }

  public EPSLocationInformationExtension getEpsLocationInformationExtension() {
    return epsLocationInformationExtension;
  }
  @XmlElement (name = "Extension")
  public void setEpsLocationInformationExtension(EPSLocationInformationExtension epsLocationInformationExtension) {
    this.epsLocationInformationExtension = epsLocationInformationExtension;
  }

  @Override
  public String toString() {
    return "EPSLocationInformation{" +
        "eUTRANCellGlobalId='" + eUTRANCellGlobalId + '\'' +
        ", trackingAreaId='" + trackingAreaId + '\'' +
        ", geographicalInformation='" + geographicalInformation + '\'' +
        ", geodeticInformation='" + geodeticInformation + '\'' +
        ", mmeName='" + mmeName + '\'' +
        ", currentLocationRetrieved='" + currentLocationRetrieved + '\'' +
        ", ageOfLocationInformation='" + ageOfLocationInformation + '\'' +
        ", userCSGInformation=" + userCSGInformation +
        ", epsLocationInformationExtension=" + epsLocationInformationExtension +
        '}';
  }
}

