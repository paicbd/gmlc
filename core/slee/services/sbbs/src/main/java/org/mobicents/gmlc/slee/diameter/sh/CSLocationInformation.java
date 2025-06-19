package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "CSLocationInformation")
public class CSLocationInformation implements Serializable {

  private static final long serialVersionUID = 1L;
  private String locationNumber;
  private String cellGlobalId;
  private String serviceAreaId;
  private String locationAreaId;
  private String geographicalInformation;
  private String geodeticInformation;
  private VLRNumber vlrNumber;
  private MSCNumber mscNumber;
  private String currentLocationRetrieved;
  private String ageOfLocationInformation;
  private CSLocationInformationExtension csLocationInformationExtension;

  public CSLocationInformation() {
    super();
  }

  public String getLocationNumber() {
    return locationNumber;
  }
  @XmlElement (name = "LocationNumber")
  public void setLocationNumber(String locationNumber) {
    this.locationNumber = locationNumber;
  }

  public String getCellGlobalId() {
    return cellGlobalId;
  }
  @XmlElement (name = "CellGlobalId")
  public void setCellGlobalId(String cellGlobalId) {
    this.cellGlobalId = cellGlobalId;
  }

  public String getServiceAreaId() {
    return serviceAreaId;
  }
  @XmlElement (name = "ServiceAreaId")
  public void setServiceAreaId(String serviceAreaId) {
    this.serviceAreaId = serviceAreaId;
  }

  public String getLocationAreaId() {
    return locationAreaId;
  }
  @XmlElement (name = "LocationAreaId")
  public void setLocationAreaId(String locationAreaId) {
    this.locationAreaId = locationAreaId;
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

  public VLRNumber getVlrNumber() {
    return vlrNumber;
  }
  @XmlElement (name = "VLRNumber")
  public void setVlrNumber(VLRNumber vlrNumber) {
    this.vlrNumber = vlrNumber;
  }

  public MSCNumber getMscNumber() {
    return mscNumber;
  }
  @XmlElement (name = "MSCNumber")
  public void setMscNumber(MSCNumber mscNumber) {
    this.mscNumber = mscNumber;
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

  public CSLocationInformationExtension getCsLocationInformationExtension() {
    return csLocationInformationExtension;
  }
  @XmlElement (name = "Extension")
  public void setCsLocationInformationExtension(CSLocationInformationExtension csLocationInformationExtension) {
    this.csLocationInformationExtension = csLocationInformationExtension;
  }

  @Override
  public String toString() {
    return "CSLocationInformation{" +
        "locationNumber='" + locationNumber + '\'' +
        ", cellGlobalId='" + cellGlobalId + '\'' +
        ", serviceAreaId='" + serviceAreaId + '\'' +
        ", locationAreaId='" + locationAreaId + '\'' +
        ", geographicalInformation='" + geographicalInformation + '\'' +
        ", geodeticInformation='" + geodeticInformation + '\'' +
        ", vlrNumber=" + vlrNumber +
        ", mscNumber=" + mscNumber +
        ", currentLocationRetrieved='" + currentLocationRetrieved + '\'' +
        ", ageOfLocationInformation='" + ageOfLocationInformation + '\'' +
        '}';
  }
}

