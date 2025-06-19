package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;

import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SlrRequestValues  implements Serializable {

  private static final long serialVersionUID = 1L;

  private static Charset isoCharset = Charset.forName("ISO-8859-1");

  /*
  3GPP TS 29.002 MAP Specification v15.0.0 (2017-03-15)

    13A.3   MAP-SUBSCRIBER-LOCATION-REPORT Service

    13A.3.1 Definition
    This service is used by a VMSC or SGSN to provide the location of a target MS to a GMLC
    when a request for location is either implicitly administered or made at some   earlier time.
    This is a confirmed service using the primitives from table 13A.3/1.

    Table 13A.3/1: Subscriber_Location_Report
    Parameter name                                      Request         Indication       Response     Confirm
    Invoke id                                               M               M(=)            M(=)        M(=)
    LCS Event                                               M               M(=)
    LCS Client ID                                           M               M(=)
    Network Node Number                                     M               M(=)
    IMSI                                                    C               C(=)
    MSISDN                                                  C               C(=)
    NA-ESRD                                                 C               C(=)            C           C(=)
    NA-ESRK                                                 C               C(=)            C           C(=)
    IMEI                                                    U               C(=)
    Location Estimate                                       C               C(=)
    GERAN Positioning Data                                  C               C(=)
    UTRAN Positioning Data                                  C               C(=)
    GERAN GANSS Positioning Data                            C               C(=)
    UTRAN GANSS Positioning Data                            C               C(=)
    UTRAN Additional Positioning Data                       C               C(=)
    UTRAN Barometric Pressure Measurement                   C               C(=)
    UTRAN Civic Address                                     C               C(=)
    Age of Location Estimate                                C               C(=)
    LMSI                                                    U               C(=)
    GPRS Node Indicator                                     C               C(=)
    Additional Location Estimate                            C               C(=)
    Deferred MT-LR Data                                     C               C(=)
    LCS-Reference Number                                    C               C(=)            C           C(=)
    NA-ESRK Request                                         C               C(=)
    Cell Id Or SAI                                          C               C(=)
    H-GMLC Address                                          C               C(=)            C           C(=)
    LCS Service Type Id                                     C               C(=)
    Pseudonym Indicator                                     C               C(=)
    Accuracy Fulfilment Indicator                           C               C(=)
    Sequence Number                                         C               C(=)
    Periodic LDR Info                                       C               C(=)
    MO-LR Short Circuit Indicator                           C               C(=)            C           C(=)
    Target Serving Node for Handover                        C               C(=)
    Reporting PLMN List                                                                     C           C(=)
    User error                                                                              C           C(=)
    Provider error                                                                                      O

    ? (M): mandatory parameter.
    ? (O): provider option.
    ? (C): conditional parameter (i.e. it will always be present in the indication type primitive
         if it was present in the corresponding request type primitive).
    ? (U): TC-user optional parameter.
    ? (=): the parameter must have the same value in the indication primitive as provided in the
         corresponding request primitive.
    ? A blank Indicates that the parameter is not applicable.

  */
  private LCSEvent lcsEvent;
  private LCSClientID lcsClientID;
  private ISDNAddressString networkNodeNumber;
  private IMSI imsi;
  private ISDNAddressString msisdn;
  private IMEI imei;
  private ExtGeographicalInformation locationEstimate;
  private PositioningDataInformation geranPositioningDataInformation;
  private UtranPositioningDataInfo utranPositioningDataInfo;
  private GeranGANSSpositioningData geranGANSSpositioningData;
  private UtranGANSSpositioningData utranGANSSpositioningData;
  private Integer ageOfLocationEstimate;
  private LMSI lmsi;
  private Boolean gprsNodeIndicator;
  private AddGeographicalInformation additionalLocationEstimate;
  private DeferredmtlrData deferredmtlrData;
  private Integer lcsReferenceNumber, originatorLcsReferenceNumber;
  private CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
  private Boolean saiPresent;
  private GSNAddress hGmlcAddress;
  private Integer lcsServiceTypeID;
  private Boolean pseudonymIndicator;
  private AccuracyFulfilmentIndicator accuracyFulfilmentIndicator;
  private Integer sequenceNumber;
  private PeriodicLDRInfo periodicLDRInfo;
  private Boolean moLrShortCircuitIndicator;
  private ReportingPLMNList reportingPLMNList;
  private VelocityEstimate velocityEstimate;
  private ServingNodeAddress servingNodeAddress;
  private ISDNAddressString naESRD, naESRK;

  public SlrRequestValues() {
    super();
  }

  public SlrRequestValues(IMSI imsi, ExtGeographicalInformation locationEstimate, int lcsReferenceNumber,
                          CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI) {
    super();
    this.imsi = imsi;
    this.locationEstimate = locationEstimate;
    this.lcsReferenceNumber = lcsReferenceNumber;
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public SlrRequestValues(LCSEvent lcsEvent, LCSClientID lcsClientID, ISDNAddressString networkNodeNumber,
                          IMSI imsi, ISDNAddressString msisdn, IMEI imei, ExtGeographicalInformation locationEstimate,
                          PositioningDataInformation geranPositioningDataInformation, UtranPositioningDataInfo utranPositioningDataInfo,
                          GeranGANSSpositioningData geranGANSSpositioningData, UtranGANSSpositioningData utranGANSSpositioningData,
                          Integer ageOfLocationEstimate, LMSI lmsi, Boolean gprsNodeIndicator, Boolean saiPresent,
                          AddGeographicalInformation additionalLocationEstimate, DeferredmtlrData deferredmtlrData,
                          Integer lcsReferenceNumber, Integer originatorLcsReferenceNumber, CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI,
                          GSNAddress hGmlcAddress, Integer lcsServiceTypeID, Boolean pseudonymIndicator,
                          AccuracyFulfilmentIndicator accuracyFulfilmentIndicator, Integer sequenceNumber,
                          PeriodicLDRInfo periodicLDRInfo, Boolean moLrShortCircuitIndicator, ReportingPLMNList reportingPLMNList,
                          ISDNAddressString naESRD, ISDNAddressString naESRK) {
    super();
    this.lcsEvent = lcsEvent;
    this.lcsClientID = lcsClientID;
    this.networkNodeNumber = networkNodeNumber;
    this.imsi = imsi;
    this.msisdn = msisdn;
    this.imei = imei;
    this.locationEstimate = locationEstimate;
    this.geranPositioningDataInformation = geranPositioningDataInformation;
    this.utranPositioningDataInfo = utranPositioningDataInfo;
    this.geranGANSSpositioningData = geranGANSSpositioningData;
    this.utranGANSSpositioningData = utranGANSSpositioningData;
    this.ageOfLocationEstimate = ageOfLocationEstimate;
    this.lmsi = lmsi;
    this.gprsNodeIndicator = gprsNodeIndicator;
    this.saiPresent = saiPresent;
    this.additionalLocationEstimate = additionalLocationEstimate;
    this.deferredmtlrData = deferredmtlrData;
    this.lcsReferenceNumber = lcsReferenceNumber;
    this.originatorLcsReferenceNumber = originatorLcsReferenceNumber;
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
    this.hGmlcAddress = hGmlcAddress;
    this.lcsServiceTypeID = lcsServiceTypeID;
    this.pseudonymIndicator = pseudonymIndicator;
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
    this.sequenceNumber = sequenceNumber;
    this.periodicLDRInfo = periodicLDRInfo;
    this.moLrShortCircuitIndicator = moLrShortCircuitIndicator;
    this.reportingPLMNList = reportingPLMNList;
    this.naESRD = naESRD;
    this.naESRK = naESRK;
  }

  public LCSEvent getLcsEvent() {
    return lcsEvent;
  }

  public void setLcsEvent(LCSEvent lcsEvent) {
    this.lcsEvent = lcsEvent;
  }

  public LCSClientID getLcsClientID() {
    return lcsClientID;
  }

  public void setLcsClientID(LCSClientID lcsClientID) {
    this.lcsClientID = lcsClientID;
  }

  public ISDNAddressString getNetworkNodeNumber() {
    return networkNodeNumber;
  }

  public void setNetworkNodeNumber(ISDNAddressString networkNodeNumber) {
    this.networkNodeNumber = networkNodeNumber;
  }

  public IMSI getImsi() {
    return imsi;
  }

  public void setImsi(IMSI imsi) {
    this.imsi = imsi;
  }

  public ISDNAddressString getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(ISDNAddressString msisdn) {
    this.msisdn = msisdn;
  }

  public IMEI getImei() {
    return imei;
  }

  public void setImei(IMEI imei) {
    this.imei = imei;
  }

  public ExtGeographicalInformation getLocationEstimate() {
    return locationEstimate;
  }

  public void setLocationEstimate(ExtGeographicalInformation locationEstimate) {
    this.locationEstimate = locationEstimate;
  }

  public PositioningDataInformation getGeranPositioningDataInformation() {
    return geranPositioningDataInformation;
  }

  public void setGeranPositioningDataInformation(PositioningDataInformation geranPositioningDataInformation) {
    this.geranPositioningDataInformation = geranPositioningDataInformation;
  }

  public UtranPositioningDataInfo getUtranPositioningDataInfo() {
    return utranPositioningDataInfo;
  }

  public void setUtranPositioningDataInfo(UtranPositioningDataInfo utranPositioningDataInfo) {
    this.utranPositioningDataInfo = utranPositioningDataInfo;
  }

  public GeranGANSSpositioningData getGeranGANSSpositioningData() {
    return geranGANSSpositioningData;
  }

  public void setGeranGANSSpositioningData(GeranGANSSpositioningData geranGANSSpositioningData) {
    this.geranGANSSpositioningData = geranGANSSpositioningData;
  }

  public UtranGANSSpositioningData getUtranGANSSpositioningData() {
    return utranGANSSpositioningData;
  }

  public void setUtranGANSSpositioningData(UtranGANSSpositioningData utranGANSSpositioningData) {
    this.utranGANSSpositioningData = utranGANSSpositioningData;
  }

  public Integer getAgeOfLocationEstimate() {
    return ageOfLocationEstimate;
  }

  public void setAgeOfLocationEstimate(Integer ageOfLocationEstimate) {
    this.ageOfLocationEstimate = ageOfLocationEstimate;
  }

  public LMSI getLmsi() {
    return lmsi;
  }

  public void setLmsi(LMSI lmsi) {
    this.lmsi = lmsi;
  }

  public Boolean isGprsNodeIndicator() {
    return gprsNodeIndicator;
  }

  public void setGprsNodeIndicator(Boolean gprsNodeIndicator) {
    this.gprsNodeIndicator = gprsNodeIndicator;
  }

  public Boolean isSaiPresent() {
    return saiPresent;
  }

  public void setSaiPresent(Boolean saiPresent) {
    this.saiPresent = saiPresent;
  }

  public AddGeographicalInformation getAdditionalLocationEstimate() {
    return additionalLocationEstimate;
  }

  public void setAdditionalLocationEstimate(AddGeographicalInformation additionalLocationEstimate) {
    this.additionalLocationEstimate = additionalLocationEstimate;
  }

  public DeferredmtlrData getDeferredmtlrData() {
    return deferredmtlrData;
  }

  public void setDeferredmtlrData(DeferredmtlrData deferredmtlrData) {
    this.deferredmtlrData = deferredmtlrData;
  }

  public Integer getLcsReferenceNumber() {
    return lcsReferenceNumber;
  }

  public void setLcsReferenceNumber(Integer lcsReferenceNumber) {
    this.lcsReferenceNumber = lcsReferenceNumber;
  }

  public Integer getOriginatorLcsReferenceNumber() {
    return originatorLcsReferenceNumber;
  }

  public void setOriginatorLcsReferenceNumber(Integer originatorLcsReferenceNumber) {
    this.originatorLcsReferenceNumber = originatorLcsReferenceNumber;
  }

  public CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI() {
    return cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public void setCellGlobalIdOrServiceAreaIdOrLAI(CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI) {
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public Boolean getGprsNodeIndicator() {
    return gprsNodeIndicator;
  }

  public Boolean getSaiPresent() {
    return saiPresent;
  }

  public Boolean getMoLrShortCircuitIndicator() {
    return moLrShortCircuitIndicator;
  }

  public GSNAddress gethGmlcAddress() {
    return hGmlcAddress;
  }

  public void sethGmlcAddress(GSNAddress hGmlcAddress) {
    this.hGmlcAddress = hGmlcAddress;
  }

  public Integer getLcsServiceTypeID() {
    return lcsServiceTypeID;
  }

  public void setLcsServiceTypeID(Integer lcsServiceTypeID) {
    this.lcsServiceTypeID = lcsServiceTypeID;
  }

  public Boolean getPseudonymIndicator() {
    return pseudonymIndicator;
  }

  public void setPseudonymIndicator(Boolean pseudonymIndicator) {
    this.pseudonymIndicator = pseudonymIndicator;
  }

  public AccuracyFulfilmentIndicator getAccuracyFulfilmentIndicator() {
    return accuracyFulfilmentIndicator;
  }

  public void setAccuracyFulfilmentIndicator(AccuracyFulfilmentIndicator accuracyFulfilmentIndicator) {
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
  }

  public Integer getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(Integer sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public PeriodicLDRInfo getPeriodicLDRInfo() {
    return periodicLDRInfo;
  }

  public void setPeriodicLDRInfo(PeriodicLDRInfo periodicLDRInfo) {
    this.periodicLDRInfo = periodicLDRInfo;
  }

  public Boolean isMoLrShortCircuitIndicator() {
    return moLrShortCircuitIndicator;
  }

  public void setMoLrShortCircuitIndicator(Boolean moLrShortCircuitIndicator) {
    this.moLrShortCircuitIndicator = moLrShortCircuitIndicator;
  }

  public ReportingPLMNList getReportingPLMNList() {
    return reportingPLMNList;
  }

  public void setReportingPLMNList(ReportingPLMNList reportingPLMNList) {
    this.reportingPLMNList = reportingPLMNList;
  }

  public VelocityEstimate getVelocityEstimate() {
    return velocityEstimate;
  }

  public void setVelocityEstimate(VelocityEstimate velocityEstimate) {
    this.velocityEstimate = velocityEstimate;
  }

  public ServingNodeAddress getServingNodeAddress() {
    return servingNodeAddress;
  }

  public void setServingNodeAddress(ServingNodeAddress servingNodeAddress) {
    this.servingNodeAddress = servingNodeAddress;
  }

  public ISDNAddressString getNaESRD() {
    return naESRD;
  }

  public void setNaESRD(ISDNAddressString naESRD) {
    this.naESRD = naESRD;
  }

  public ISDNAddressString getNaESRK() {
    return naESRK;
  }

  public void setNaESRK(ISDNAddressString naESRK) {
    this.naESRK = naESRK;
  }

  @Override
  public String toString() {
    return "SlrRequestValues{" +
        "lcsEvent=" + lcsEvent +
        ", lcsClientID=" + lcsClientID +
        ", networkNodeNumber=" + networkNodeNumber +
        ", imsi=" + imsi +
        ", msisdn=" + msisdn +
        ", imei=" + imei +
        ", locationEstimate=" + locationEstimate +
        ", geranPositioningDataInformation=" + geranPositioningDataInformation +
        ", utranPositioningDataInfo=" + utranPositioningDataInfo +
        ", geranGANSSpositioningData=" + geranGANSSpositioningData +
        ", utranGANSSpositioningData=" + utranGANSSpositioningData +
        ", ageOfLocationEstimate=" + ageOfLocationEstimate +
        ", lmsi=" + lmsi +
        ", gprsNodeIndicator=" + gprsNodeIndicator +
        ", additionalLocationEstimate=" + additionalLocationEstimate +
        ", deferredmtlrData=" + deferredmtlrData +
        ", lcsReferenceNumber=" + lcsReferenceNumber +
        ", originatorLcsReferenceNumber=" + originatorLcsReferenceNumber +
        ", cellGlobalIdOrServiceAreaIdOrLAI=" + cellGlobalIdOrServiceAreaIdOrLAI +
        ", saiPresent=" + saiPresent +
        ", hGmlcAddress=" + hGmlcAddress +
        ", lcsServiceTypeID=" + lcsServiceTypeID +
        ", pseudonymIndicator=" + pseudonymIndicator +
        ", accuracyFulfilmentIndicator=" + accuracyFulfilmentIndicator +
        ", sequenceNumber=" + sequenceNumber +
        ", periodicLDRInfo=" + periodicLDRInfo +
        ", moLrShortCircuitIndicator=" + moLrShortCircuitIndicator +
        ", reportingPLMNList=" + reportingPLMNList +
        ", velocityEstimate=" + velocityEstimate +
        ", servingNodeAddress=" + servingNodeAddress +
        ", naESRD=" + naESRD +
        ", naESRK=" + naESRK +
        '}';
  }
}
