package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class PslResponseValues implements Serializable {

  private static final long serialVersionUID = 1L;

  /*
    3GPP TS 29.002 MAP Specification v15.0.0 (2017-03-15)

    13A.2   MAP-PROVIDE-SUBSCRIBER-LOCATION Service

      13A.2.1 Definition
      This service is used by a GMLC to request the location of a target MS from the visited MSC or SGSN at any time.
      This is a confirmed service using the primitives from table 13A.2/1.

      13A.2.2 Service Primitives

      Table 13A.2/1: Provide_Subscriber_Location
      Parameter name                              Request         Indication        Response          Confirm
      Invoke id                                       M               M(=)            M(=)               M(=)
      Location Type                                   M               M(=)
      MLC Number                                      M               M(=)
      LCS Client ID                                   M               M(=)
      Privacy Override                                U               C(=)
      IMSI                                            C               C(=)
      MSISDN                                          C               C(=)
      LMSI                                            C               C(=)
      LCS Priority                                    C               C(=)
      LCS QoS                                         C               C(=)
      IMEI                                            U               C(=)
      Supported GAD Shapes                            C               C(=)
      LCS-Reference Number                            C               C(=)
      LCS Codeword                                    C               C(=)
      LCS Service Type Id                             C               C(=)
      LCS Privacy Check                               C               C(=)
      Area Event Info                                 C               C(=)
      H-GMLC Address                                  C               C(=)
      Reporting PLMN List                             C               C(=)
      PeriodicLDRInfo                                 C               C(=)
      MO-LR Short Circuit Indicator                   C               C(=)            C                   C(=)
      Location Estimate                                                               M                   M(=)
      GERAN Positioning Data                                                          C                   C(=)
      UTRAN Positioning Data                                                          C                   C(=)
      GERAN GANSS Positioning Data                                                    C                   C(=)
      UTRAN GANSS Positioning Data                                                    C                   C(=)
      UTRAN Additional Positioning Data                                               C                   C(=)
      UTRAN Barometric Pressure Measurement                                           C                   C(=)
      UTRAN Civic Address                                                             C                   C(=)
      Age of Location Estimate                                                        C                   C(=)
      Additional Location Estimate                                                    C                   C(=)
      Deferred MT-LR Response Indicator                                               C                   C(=)
      Cell Id Or SAI                                                                  C                   C(=)
      Accuracy Fulfilment Indicator                                                   C                   C(=)
      Target Serving Node for Handover                                                C                   C(=)
      User error                                                                      C                   C(=)
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
  private Boolean moLrShortCircuitIndicator;
  private ExtGeographicalInformation locationEstimate;
  private PositioningDataInformation geranPositioningDataInformation;
  private UtranPositioningDataInfo utranPositioningDataInfo;
  private GeranGANSSpositioningData geranGANSSpositioningData;
  private UtranGANSSpositioningData utranGANSSpositioningData;
  private Integer ageOfLocationEstimate;
  private AddGeographicalInformation additionalLocationEstimate;
  private Boolean deferredMTLRResponseIndicator;
  private CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
  private Boolean saiPresent;
  private AccuracyFulfilmentIndicator accuracyFulfilmentIndicator;
  private VelocityEstimate velocityEstimate;
  private ServingNodeAddress servingNodeAddress;

  public PslResponseValues() {
    super();
  }

  public PslResponseValues(ExtGeographicalInformation locationEstimate, int ageOfLocationEstimate) {
    super();
    this.locationEstimate = locationEstimate;
    this.ageOfLocationEstimate = ageOfLocationEstimate;
  }

  public PslResponseValues(Boolean moLrShortCircuitIndicator, ExtGeographicalInformation locationEstimate,
                           PositioningDataInformation geranPositioningDataInformation,
                           UtranPositioningDataInfo utranPositioningDataInfo, GeranGANSSpositioningData geranGANSSpositioningData,
                           UtranGANSSpositioningData utranGANSSpositioningData, int ageOfLocationEstimate,
                           AddGeographicalInformation additionalLocationEstimate, Boolean deferredMTLRResponseIndicator,
                           CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI,
                           AccuracyFulfilmentIndicator accuracyFulfilmentIndicator) {
    this.moLrShortCircuitIndicator = moLrShortCircuitIndicator;
    this.locationEstimate = locationEstimate;
    this.geranPositioningDataInformation = geranPositioningDataInformation;
    this.utranPositioningDataInfo = utranPositioningDataInfo;
    this.geranGANSSpositioningData = geranGANSSpositioningData;
    this.utranGANSSpositioningData = utranGANSSpositioningData;
    this.ageOfLocationEstimate = ageOfLocationEstimate;
    this.additionalLocationEstimate = additionalLocationEstimate;
    this.deferredMTLRResponseIndicator = deferredMTLRResponseIndicator;
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Boolean isMoLrShortCircuitIndicator() {
    return moLrShortCircuitIndicator;
  }

  public void setMoLrShortCircuitIndicator(Boolean moLrShortCircuitIndicator) {
    this.moLrShortCircuitIndicator = moLrShortCircuitIndicator;
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

  public int getAgeOfLocationEstimate() {
    return ageOfLocationEstimate;
  }

  public void setAgeOfLocationEstimate(int ageOfLocationEstimate) {
    this.ageOfLocationEstimate = ageOfLocationEstimate;
  }

  public AddGeographicalInformation getAdditionalLocationEstimate() {
    return additionalLocationEstimate;
  }

  public void setAdditionalLocationEstimate(AddGeographicalInformation additionalLocationEstimate) {
    this.additionalLocationEstimate = additionalLocationEstimate;
  }

  public Boolean isDeferredMTLRResponseIndicator() {
    return deferredMTLRResponseIndicator;
  }

  public void setDeferredMTLRResponseIndicator(Boolean deferredMTLRResponseIndicator) {
    this.deferredMTLRResponseIndicator = deferredMTLRResponseIndicator;
  }

  public CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI() {
    return cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public void setCellGlobalIdOrServiceAreaIdOrLAI(CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI) {
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public Boolean getSaiPresent() {
    return saiPresent;
  }

  public void setSaiPresent(Boolean saiPresent) {
    this.saiPresent = saiPresent;
  }

  public AccuracyFulfilmentIndicator getAccuracyFulfilmentIndicator() {
    return accuracyFulfilmentIndicator;
  }

  public void setAccuracyFulfilmentIndicator(AccuracyFulfilmentIndicator accuracyFulfilmentIndicator) {
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
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

  @Override
  public String toString() {
    return "PslResponseValues{" +
        "moLrShortCircuitIndicator=" + moLrShortCircuitIndicator +
        ", locationEstimate=" + locationEstimate +
        ", geranPositioningDataInformation=" + geranPositioningDataInformation +
        ", utranPositioningDataInfo=" + utranPositioningDataInfo +
        ", geranGANSSpositioningData=" + geranGANSSpositioningData +
        ", utranGANSSpositioningData=" + utranGANSSpositioningData +
        ", ageOfLocationEstimate=" + ageOfLocationEstimate +
        ", additionalLocationEstimate=" + additionalLocationEstimate +
        ", deferredMTLRResponseIndicator=" + deferredMTLRResponseIndicator +
        ", cellGlobalIdOrServiceAreaIdOrLAI=" + cellGlobalIdOrServiceAreaIdOrLAI +
        ", saiPresent=" + saiPresent +
        ", accuracyFulfilmentIndicator=" + accuracyFulfilmentIndicator +
        ", velocityEstimate=" + velocityEstimate +
        ", servingNodeAddress=" + servingNodeAddress +
        '}';
  }
}

