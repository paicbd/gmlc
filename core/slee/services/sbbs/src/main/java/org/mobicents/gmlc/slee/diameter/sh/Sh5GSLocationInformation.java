package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 	@author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Sh-5GSLocationInformation")
public class Sh5GSLocationInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nrCellGlobalId;
    private String eUTRANCellGlobalId;
    private String trackingAreaId;
    private String geographicalInformation;
    private String amfAddress;
    private String smsfAddress;
    private String currentLocationRetrieved;
    private String ageOfLocationInformation;
    private String visitedPLMNId;
    private LocalTimeZone localTimeZone;
    private String ratType;

    public Sh5GSLocationInformation() {
    }

    public String getNRCellGlobalId() {
        return nrCellGlobalId;
    }
    @XmlElement(name = "NRCellGlobalId")
    public void setNRCellGlobalId(String nrCellGlobalId) {
        this.nrCellGlobalId = nrCellGlobalId;
    }

    public String getEUTRANCellGlobalId() {
        return eUTRANCellGlobalId;
    }
    @XmlElement(name = "E-UTRANCellGlobalId")
    public void setEUTRANCellGlobalId(String eUTRANCellGlobalId) {
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

    public String getAMFAddress() {
        return amfAddress;
    }
    @XmlElement (name = "AMFAddress")
    public void setAMFAddress(String amfAddress) {
        this.amfAddress = amfAddress;
    }

    public String getSMSFAddress() {
        return smsfAddress;
    }
    @XmlElement (name = "SMSFAddress")
    public void setSMSFAddress(String smsfAddress) {
        this.smsfAddress = smsfAddress;
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

    public String getRatType() {
        return ratType;
    }
    @XmlElement (name = "RATtype")
    public void setRatType(String ratType) {
        this.ratType = ratType;
    }

    @Override
    public String toString() {
        return "Sh5GSLocationInformation{" +
            "nrCellGlobalId='" + nrCellGlobalId + '\'' +
            ", eUTRANCellGlobalId='" + eUTRANCellGlobalId + '\'' +
            ", trackingAreaId='" + trackingAreaId + '\'' +
            ", geographicalInformation='" + geographicalInformation + '\'' +
            ", amfAddress='" + amfAddress + '\'' +
            ", smsfAddress='" + smsfAddress + '\'' +
            ", currentLocationRetrieved='" + currentLocationRetrieved + '\'' +
            ", ageOfLocationInformation='" + ageOfLocationInformation + '\'' +
            ", visitedPLMNId='" + visitedPLMNId + '\'' +
            ", localTimeZone=" + localTimeZone +
            ", ratType='" + ratType + '\'' +
            '}';
    }
}
