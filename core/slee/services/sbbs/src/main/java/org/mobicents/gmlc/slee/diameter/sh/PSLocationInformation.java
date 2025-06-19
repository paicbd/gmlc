package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "PSLocationInformation")
public class PSLocationInformation implements Serializable {

  private static final long serialVersionUID = 1L;
  private String cellGlobalId;
  private String serviceAreaId;
  private String locationAreaId;
  private String routingAreaId;
  private String geographicalInformation;
  private String geodeticInformation;
  private SGSNNumber sgsnNumber;
  private String currentLocationRetrieved;
  private String ageOfLocationInformation;
  private PSLocationInformationExtension psLocationInformationExtension;

  public PSLocationInformation() {
    super();
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

  public String getRoutingAreaId() {
    return routingAreaId;
  }
  @XmlElement (name = "RoutingAreaId")
  public void setRoutingAreaId(String routingAreaId) {
    this.routingAreaId = routingAreaId;
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

  public SGSNNumber getSgsnNumber() {
    return sgsnNumber;
  }
  @XmlElement (name = "SGSNNumber")
  public void setSgsnNumber(SGSNNumber sgsnNumber) {
    this.sgsnNumber = sgsnNumber;
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

  public PSLocationInformationExtension getPsLocationInformationExtension() {
    return psLocationInformationExtension;
  }
  @XmlElement (name = "Extension")
  public void setPsLocationInformationExtension(PSLocationInformationExtension psLocationInformationExtension) {
    this.psLocationInformationExtension = psLocationInformationExtension;
  }

  @Override
  public String toString() {
    return "PSLocationInformation{" +
        "cellGlobalId='" + cellGlobalId + '\'' +
        ", serviceAreaId='" + serviceAreaId + '\'' +
        ", locationAreaId='" + locationAreaId + '\'' +
        ", routingAreaId='" + routingAreaId + '\'' +
        ", geographicalInformation='" + geographicalInformation + '\'' +
        ", geodeticInformation='" + geodeticInformation + '\'' +
        ", sgsnNumber=" + sgsnNumber +
        ", currentLocationRetrieved='" + currentLocationRetrieved + '\'' +
        ", ageOfLocationInformation='" + ageOfLocationInformation + '\'' +
        ", psLocationInformationExtension=" + psLocationInformationExtension +
        '}';
  }
}

