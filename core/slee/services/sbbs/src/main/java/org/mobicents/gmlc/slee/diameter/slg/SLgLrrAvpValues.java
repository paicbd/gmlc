package org.mobicents.gmlc.slee.diameter.slg;

import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.SLgAVPFactory;
import net.java.slee.resource.diameter.slg.events.avp.AccuracyFulfilmentIndicator;
import net.java.slee.resource.diameter.slg.events.avp.DeferredMTLRDataAvp;
import net.java.slee.resource.diameter.slg.events.avp.DelayedLocationReportingDataAvp;
import net.java.slee.resource.diameter.slg.events.avp.ELPAVPCodes;
import net.java.slee.resource.diameter.slg.events.avp.ESMLCCellInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.GERANPositioningInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.LCSEPSClientNameAvp;
import net.java.slee.resource.diameter.slg.events.avp.LCSFormatIndicator;
import net.java.slee.resource.diameter.slg.events.avp.LCSQoSClass;
import net.java.slee.resource.diameter.slg.events.avp.LocationEvent;
import net.java.slee.resource.diameter.slg.events.avp.PeriodicLDRInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.PseudonymIndicator;
import net.java.slee.resource.diameter.slg.events.avp.ServingNodeAvp;
import net.java.slee.resource.diameter.slg.events.avp.UTRANPositioningInfoAvp;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLgLrrAvpValues implements Serializable {

  private static final long serialVersionUID = -5949494938824386134L;

  private static final int DIAMETER_SLg_VENDOR_ID = 10415;

  /*
    3GPP TS 29.172 v15.0.0 reference
      7.3.3	Location-Report-Request (LRR) Command
      The Location-Report-Request (LRR) command, indicated by the Command-Code field set to 8388621 and the 'R' bit set in the Command Flags field,
      is sent by the MME or SGSN in order to provide subscriber location data to the GMLC.

      Message Format

        < Location-Report-Request> ::=	< Diameter Header: 8388621, REQ, PXY, 16777255 >
                                        < Session-Id >
	                                    [ Vendor-Specific-Application-Id ]
	                                    { Auth-Session-State }
	                                    { Origin-Host }
	                                    { Origin-Realm }
	                                    { Destination-Host }
	                                    { Destination-Realm }
	                                    { Location-Event }
	                                    [ LCS-EPS-Client-Name ]
	                                    [ User-Name ]
	                                    [ MSISDN ]
	                                    [ IMEI ]
	                                    [ Location-Estimate ]
	                                    [ Accuracy-Fulfilment-Indicator ]
	                                    [ Age-Of-Location-Estimate ]
	                                    [ Velocity-Estimate ]
	                                    [ EUTRAN-Positioning-Data ]
	                                    [ ECGI ]
	                                    [ GERAN-Positioning-Info ]
	                                    [ Cell-Global-Identity ]
	                                    [ UTRAN-Positioning-Info ]
	                                    [ Service-Area-Identity ]
	                                    [ LCS-Service-Type-ID ]
	                                    [ Pseudonym-Indicator ]
	                                    [ LCS-QoS-Class ]
	                                    [ Serving-Node ]
	                                    [ LRR-Flags ]
	                                    [ LCS-Reference-Number ]
	                                    [ Deferred-MT-LR-Data ]
	                                    [ GMLC-Address ]
	                                    [ Reporting-Amount ]
	                                    [ Periodic-LDR-Information ]
	                                    [ ESMLC-Cell-Info ]
	                                    [ 1xRTT-RCID ]
	                                    [ Delayed-Location-Reporting-Data ]
	                                    [ Civic-Address ]
	                                    [ Barometric-Pressure ]
	                                    *[ Supported-Features ]
	                                    *[ AVP ]
	                                    *[ Proxy-Info ]
	                                    *[ Route-Record ]

  */

  private LocationEvent locationEvent;
  private LCSEPSClientNameAvp lcsEPSClientName;
  private String lcsNameString;
  private LCSFormatIndicator lcsFormatIndicator;
  private String userName; // i.e. IMSI
  private byte[] msisdn;
  private String imei;
  private byte[] locationEstimate;
  private AccuracyFulfilmentIndicator accuracyFulfilmentIndicator;
  private Long ageOfLocationEstimate;
  private byte[] velocityEstimate;
  private byte[] eUtranPositioningData;
  private byte[] ecgi;
  private GERANPositioningInfoAvp geranPositioningInfoAvp;
  private byte[] geranPositioningData;
  private byte[] geranGANSSPositioningData;
  private byte[] cellGlobalIdentity;
  private UTRANPositioningInfoAvp utranPositioningInfoAvp;
  private byte[] utranPositioningData;
  private byte[] utranGANSSPositioningData;
  private byte[] utranAdditionalPositioningData;
  private byte[] serviceAreaIdentity;
  private Long lcsServiceTypeId;
  private PseudonymIndicator pseudonymIndicator;
  private LCSQoSClass lcsQoSClass;
  private ServingNodeAvp servingNodeAvp;
  private byte[] sgsnNumber;
  private DiameterIdentity sgsnName;
  private DiameterIdentity sgsnRealm;
  private DiameterIdentity mmeName;
  private DiameterIdentity mmeRealm;
  private byte[] mscNumber;
  private DiameterIdentity tgppAAAServerName;
  private Long lcsCapabilitiesSets;
  private Address gmlcAddress;
  private Long lrrFlags;
  private byte[] lcsReferenceNumber;
  private DeferredMTLRDataAvp deferredMTLRDataAvp;
  private Long deferredLocationType;
  private Long terminationCause;
  private PeriodicLDRInfoAvp periodicLDRInformation;
  private Long reportingAmount;
  private Long reportingInterval;
  private ESMLCCellInfoAvp esmlcCellInfoAvp;
  private Long cellPortionId;
  private byte[] oneXRttRcid;
  private DelayedLocationReportingDataAvp delayedLocationReportingDataAvp;
  private byte[] civicAddress;
  private Long barometricPressure;

  private Integer originatorLcsReferenceNumber;

  public SLgLrrAvpValues() {
    super();
  }

  public SLgLrrAvpValues(LocationEvent locationEvent, LCSEPSClientNameAvp lcsEPSClientName, String lcsNameString, LCSFormatIndicator lcsFormatIndicator,
                         String userName, byte[] msisdn, String imei, byte[] locationEstimate, AccuracyFulfilmentIndicator accuracyFulfilmentIndicator,
                         Long ageOfLocationEstimate, byte[] velocityEstimate, byte[] eUtranPositioningData, byte[] ecgi, GERANPositioningInfoAvp geranPositioningInfoAvp,
                         byte[] geranPositioningData, byte[] geranGANSSPositioningData, byte[] cellGlobalIdentity, UTRANPositioningInfoAvp utranPositioningInfoAvp,
                         byte[] utranPositioningData, byte[] utranGANSSPositioningData, byte[] utranAdditionalPositioningData, byte[] serviceAreaIdentity,
                         Long lcsServiceTypeId, PseudonymIndicator pseudonymIndicator, LCSQoSClass lcsQoSClass, ServingNodeAvp servingNodeAvp, byte[] sgsnNumber,
                         DiameterIdentity sgsnName, DiameterIdentity sgsnRealm, DiameterIdentity mmeName, DiameterIdentity mmeRealm, byte[] mscNumber,
                         DiameterIdentity tgppAAAServerName, Long lcsCapabilitiesSets, Address gmlcAddress, Long lrrFlags, byte[] lcsReferenceNumber,
                         DeferredMTLRDataAvp deferredMTLRDataAvp, Long deferredLocationType, Long terminationCause, PeriodicLDRInfoAvp periodicLDRInformation,
                         Long reportingAmount, Long reportingInterval, ESMLCCellInfoAvp esmlcCellInfoAvp, Long cellPortionId, byte[] oneXRttRcid,
                         DelayedLocationReportingDataAvp delayedLocationReportingDataAvp, byte[] civicAddress, Long barometricPressure) {
    super();
    this.locationEvent = locationEvent;
    this.lcsEPSClientName = lcsEPSClientName;
    this.lcsNameString = lcsNameString;
    this.lcsFormatIndicator = lcsFormatIndicator;
    this.userName = userName;
    this.msisdn = msisdn;
    this.imei = imei;
    this.locationEstimate = locationEstimate;
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
    this.ageOfLocationEstimate = ageOfLocationEstimate;
    this.velocityEstimate = velocityEstimate;
    this.eUtranPositioningData = eUtranPositioningData;
    this.ecgi = ecgi;
    this.geranPositioningInfoAvp = geranPositioningInfoAvp;
    this.geranPositioningData = geranPositioningData;
    this.geranGANSSPositioningData = geranGANSSPositioningData;
    this.cellGlobalIdentity = cellGlobalIdentity;
    this.utranPositioningInfoAvp = utranPositioningInfoAvp;
    this.utranPositioningData = utranPositioningData;
    this.utranGANSSPositioningData = utranGANSSPositioningData;
    this.utranAdditionalPositioningData = utranAdditionalPositioningData;
    this.serviceAreaIdentity = serviceAreaIdentity;
    this.lcsServiceTypeId = lcsServiceTypeId;
    this.pseudonymIndicator = pseudonymIndicator;
    this.lcsQoSClass = lcsQoSClass;
    this.servingNodeAvp = servingNodeAvp;
    this.sgsnNumber = sgsnNumber;
    this.sgsnName = sgsnName;
    this.sgsnRealm = sgsnRealm;
    this.mmeName = mmeName;
    this.mmeRealm = mmeRealm;
    this.mscNumber = mscNumber;
    this.tgppAAAServerName = tgppAAAServerName;
    this.lcsCapabilitiesSets = lcsCapabilitiesSets;
    this.gmlcAddress = gmlcAddress;
    this.lrrFlags = lrrFlags;
    this.lcsReferenceNumber = lcsReferenceNumber;
    this.deferredMTLRDataAvp = deferredMTLRDataAvp;
    this.deferredLocationType = deferredLocationType;
    this.terminationCause = terminationCause;
    this.periodicLDRInformation = periodicLDRInformation;
    this.reportingAmount = reportingAmount;
    this.reportingInterval = reportingInterval;
    this.esmlcCellInfoAvp = esmlcCellInfoAvp;
    this.cellPortionId = cellPortionId;
    this.oneXRttRcid = oneXRttRcid;
    this.delayedLocationReportingDataAvp = delayedLocationReportingDataAvp;
    this.civicAddress = civicAddress;
    this.barometricPressure = barometricPressure;
  }

  public LocationEvent getLocationEvent() {
    return locationEvent;
  }

  public void setLocationEvent(LocationEvent locationEvent) {
    this.locationEvent = locationEvent;
  }

  public LCSEPSClientNameAvp getLcsEPSClientName() {
    return lcsEPSClientName;
  }

  public void setLcsEPSClientName(LCSEPSClientNameAvp lcsEPSClientName) {
    this.lcsEPSClientName = lcsEPSClientName;
  }

  public String getLcsNameString() {
    return lcsNameString;
  }

  public void setLcsNameString(String lcsNameString) {
    this.lcsNameString = lcsNameString;
  }

  public LCSFormatIndicator getLcsFormatIndicator() {
    return lcsFormatIndicator;
  }

  public void setLcsFormatIndicator(LCSFormatIndicator lcsFormatIndicator) {
    this.lcsFormatIndicator = lcsFormatIndicator;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public byte[] getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(byte[] msisdn) {
    this.msisdn = msisdn;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public byte[] getLocationEstimate() {
    return locationEstimate;
  }

  public void setLocationEstimate(byte[] locationEstimate) {
    this.locationEstimate = locationEstimate;
  }

  public AccuracyFulfilmentIndicator getAccuracyFulfilmentIndicator() {
    return accuracyFulfilmentIndicator;
  }

  public void setAccuracyFulfilmentIndicator(AccuracyFulfilmentIndicator accuracyFulfilmentIndicator) {
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
  }

  public Long getAgeOfLocationEstimate() {
    return ageOfLocationEstimate;
  }

  public void setAgeOfLocationEstimate(Long ageOfLocationEstimate) {
    this.ageOfLocationEstimate = ageOfLocationEstimate;
  }

  public byte[] getVelocityEstimate() {
    return velocityEstimate;
  }

  public void setVelocityEstimate(byte[] velocityEstimate) {
    this.velocityEstimate = velocityEstimate;
  }

  public byte[] geteUtranPositioningData() {
    return eUtranPositioningData;
  }

  public void seteUtranPositioningData(byte[] eUtranPositioningData) {
    this.eUtranPositioningData = eUtranPositioningData;
  }

  public byte[] getEcgi() {
    return ecgi;
  }

  public void setEcgi(byte[] ecgi) {
    this.ecgi = ecgi;
  }

  public GERANPositioningInfoAvp getGeranPositioningInfoAvp() {
    return geranPositioningInfoAvp;
  }

  public void setGeranPositioningInfoAvp(GERANPositioningInfoAvp geranPositioningInfoAvp) {
    this.geranPositioningInfoAvp = geranPositioningInfoAvp;
  }

  public byte[] getGeranPositioningData() {
    return geranPositioningData;
  }

  public void setGeranPositioningData(byte[] geranPositioningData) {
    this.geranPositioningData = geranPositioningData;
  }

  public byte[] getGeranGANSSPositioningData() {
    return geranGANSSPositioningData;
  }

  public void setGeranGANSSPositioningData(byte[] geranGANSSPositioningData) {
    this.geranGANSSPositioningData = geranGANSSPositioningData;
  }

  public byte[] getCellGlobalIdentity() {
    return cellGlobalIdentity;
  }

  public void setCellGlobalIdentity(byte[] cellGlobalIdentity) {
    this.cellGlobalIdentity = cellGlobalIdentity;
  }

  public UTRANPositioningInfoAvp getUtranPositioningInfoAvp() {
    return utranPositioningInfoAvp;
  }

  public void setUtranPositioningInfoAvp(UTRANPositioningInfoAvp utranPositioningInfoAvp) {
    this.utranPositioningInfoAvp = utranPositioningInfoAvp;
  }

  public byte[] getUtranPositioningData() {
    return utranPositioningData;
  }

  public void setUtranPositioningData(byte[] utranPositioningData) {
    this.utranPositioningData = utranPositioningData;
  }

  public byte[] getUtranGANSSPositioningData() {
    return utranGANSSPositioningData;
  }

  public void setUtranGANSSPositioningData(byte[] utranGANSSPositioningData) {
    this.utranGANSSPositioningData = utranGANSSPositioningData;
  }

  public byte[] getUtranAdditionalPositioningData() {
    return utranAdditionalPositioningData;
  }

  public void setUtranAdditionalPositioningData(byte[] utranAdditionalPositioningData) {
    this.utranAdditionalPositioningData = utranAdditionalPositioningData;
  }

  public byte[] getServiceAreaIdentity() {
    return serviceAreaIdentity;
  }

  public void setServiceAreaIdentity(byte[] serviceAreaIdentity) {
    this.serviceAreaIdentity = serviceAreaIdentity;
  }

  public Long getLcsServiceTypeId() {
    return lcsServiceTypeId;
  }

  public void setLcsServiceTypeId(Long lcsServiceTypeId) {
    this.lcsServiceTypeId = lcsServiceTypeId;
  }

  public PseudonymIndicator getPseudonymIndicator() {
    return pseudonymIndicator;
  }

  public void setPseudonymIndicator(PseudonymIndicator pseudonymIndicator) {
    this.pseudonymIndicator = pseudonymIndicator;
  }

  public LCSQoSClass getLcsQoSClass() {
    return lcsQoSClass;
  }

  public void setLcsQoSClass(LCSQoSClass lcsQoSClass) {
    this.lcsQoSClass = lcsQoSClass;
  }

  public ServingNodeAvp getServingNodeAvp() {
    return servingNodeAvp;
  }

  public void setServingNodeAvp(ServingNodeAvp servingNodeAvp) {
    this.servingNodeAvp = servingNodeAvp;
  }

  public byte[] getSgsnNumber() {
    return sgsnNumber;
  }

  public void setSgsnNumber(byte[] sgsnNumber) {
    this.sgsnNumber = sgsnNumber;
  }

  public DiameterIdentity getSgsnName() {
    return sgsnName;
  }

  public void setSgsnName(DiameterIdentity sgsnName) {
    this.sgsnName = sgsnName;
  }

  public DiameterIdentity getSgsnRealm() {
    return sgsnRealm;
  }

  public void setSgsnRealm(DiameterIdentity sgsnRealm) {
    this.sgsnRealm = sgsnRealm;
  }

  public DiameterIdentity getMmeName() {
    return mmeName;
  }

  public void setMmeName(DiameterIdentity mmeName) {
    this.mmeName = mmeName;
  }

  public DiameterIdentity getMmeRealm() {
    return mmeRealm;
  }

  public void setMmeRealm(DiameterIdentity mmeRealm) {
    this.mmeRealm = mmeRealm;
  }

  public byte[] getMscNumber() {
    return mscNumber;
  }

  public void setMscNumber(byte[] mscNumber) {
    this.mscNumber = mscNumber;
  }

  public DiameterIdentity getTgppAAAServerName() {
    return tgppAAAServerName;
  }

  public void setTgppAAAServerName(DiameterIdentity tgppAAAServerName) {
    this.tgppAAAServerName = tgppAAAServerName;
  }

  public Long getLcsCapabilitiesSets() {
    return lcsCapabilitiesSets;
  }

  public void setLcsCapabilitiesSets(Long lcsCapabilitiesSets) {
    this.lcsCapabilitiesSets = lcsCapabilitiesSets;
  }

  public Address getGmlcAddress() {
    return gmlcAddress;
  }

  public void setGmlcAddress(Address gmlcAddress) {
    this.gmlcAddress = gmlcAddress;
  }

  public Long getLrrFlags() {
    return lrrFlags;
  }

  public void setLrrFlags(Long lrrFlags) {
    this.lrrFlags = lrrFlags;
  }

  public byte[] getLcsReferenceNumber() {
    return lcsReferenceNumber;
  }

  public void setLcsReferenceNumber(byte[] lcsReferenceNumber) {
    this.lcsReferenceNumber = lcsReferenceNumber;
  }

  public DeferredMTLRDataAvp getDeferredMTLRDataAvp() {
    return deferredMTLRDataAvp;
  }

  public void setDeferredMTLRDataAvp(DeferredMTLRDataAvp deferredMTLRDataAvp) {
    this.deferredMTLRDataAvp = deferredMTLRDataAvp;
  }

  public Long getDeferredLocationType() {
    return deferredLocationType;
  }

  public void setDeferredLocationType(Long deferredLocationType) {
    this.deferredLocationType = deferredLocationType;
  }

  public Long getTerminationCause() {
    return terminationCause;
  }

  public void setTerminationCause(Long terminationCause) {
    this.terminationCause = terminationCause;
  }

  public PeriodicLDRInfoAvp getPeriodicLDRInformation() {
    return periodicLDRInformation;
  }

  public void setPeriodicLDRInformation(PeriodicLDRInfoAvp periodicLDRInformation) {
    this.periodicLDRInformation = periodicLDRInformation;
  }

  public Long getReportingAmount() {
    return reportingAmount;
  }

  public void setReportingAmount(Long reportingAmount) {
    this.reportingAmount = reportingAmount;
  }

  public Long getReportingInterval() {
    return reportingInterval;
  }

  public void setReportingInterval(Long reportingInterval) {
    this.reportingInterval = reportingInterval;
  }

  public ESMLCCellInfoAvp getEsmlcCellInfoAvp() {
    return esmlcCellInfoAvp;
  }

  public void setEsmlcCellInfoAvp(ESMLCCellInfoAvp esmlcCellInfoAvp) {
    this.esmlcCellInfoAvp = esmlcCellInfoAvp;
  }

  public Long getCellPortionId() {
    return cellPortionId;
  }

  public void setCellPortionId(Long cellPortionId) {
    this.cellPortionId = cellPortionId;
  }

  public byte[] getOneXRttRcid() {
    return oneXRttRcid;
  }

  public void setOneXRttRcid(byte[] oneXRttRcid) {
    this.oneXRttRcid = oneXRttRcid;
  }

  public DelayedLocationReportingDataAvp getDelayedLocationReportingDataAvp() {
    return delayedLocationReportingDataAvp;
  }

  public void setDelayedLocationReportingDataAvp(DelayedLocationReportingDataAvp delayedLocationReportingDataAvp) {
    this.delayedLocationReportingDataAvp = delayedLocationReportingDataAvp;
  }

  public byte[] getCivicAddress() {
    return civicAddress;
  }

  public void setCivicAddress(byte[] civicAddress) {
    this.civicAddress = civicAddress;
  }

  public Long getBarometricPressure() {
    return barometricPressure;
  }

  public void setBarometricPressure(Long barometricPressure) {
    this.barometricPressure = barometricPressure;
  }

  public Integer getOriginatorLcsReferenceNumber() {
    return originatorLcsReferenceNumber;
  }

  public void setOriginatorLcsReferenceNumber(Integer originatorLcsReferenceNumber) {
    this.originatorLcsReferenceNumber = originatorLcsReferenceNumber;
  }

  /** LRR AVPs building method**/
  public void createLRRAvps(SLgAVPFactory slgAVPFactory) throws Exception {

    try {

      // AVP name="Location-Event" code="2518" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Enumerated"
      DiameterAvp locationEventAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LOCATION_EVENT, this.locationEvent);
      LocationEvent locationEventCode = LocationEvent.fromInt(locationEventAvp.getCode());
      setLocationEvent(locationEventCode);

      // AVP name="LCS-Name-String" code="1238" vendor-id="TGPP" mandatory="must" protected="may" may-encrypt="no" vendor-bit="must" type-name="UTF8String"
      DiameterAvp lcsNameStringAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LCS_NAME_STRING, this.lcsNameString);
      setLcsNameString(lcsNameStringAvp.stringValue());
      // AVP name="LCS-Format-Indicator" code="1237" vendor-id="TGPP" mandatory="may" protected="may" may-encrypt="yes" vendor-bit="must" type-name="Enumerated"
      DiameterAvp lcsFormatIndicatorAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LCS_FORMAT_INDICATOR, this.lcsFormatIndicator);
      LCSFormatIndicator lcsFormatIndicatorCode = LCSFormatIndicator.fromInt(lcsFormatIndicatorAvp.getCode());
      setLcsFormatIndicator(lcsFormatIndicatorCode);
      // AVP name="LCS-EPS-Client-Name" code="2501" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must"
      //		<grouped>
      //			<avp name="LCS-Name-String" multiplicity="0-1" />
      //			<avp name="LCS-Format-Indicator" multiplicity="0-1" />
      //		</grouped>
      lcsEPSClientName = slgAVPFactory.createLCSEPSClientName();
      lcsEPSClientName.setLCSNameString(getLcsNameString());
      lcsEPSClientName.setLCSFormatIndicator(lcsFormatIndicator);

      // AVP name="User-Name" code="1" mandatory="must" protected="may" may-encrypt="yes" vendor-bit="mustnot" type-name="UTF8String"
      DiameterAvp userNAmeAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.USER_NAME, this.userName);
      setUserName(userNAmeAvp.stringValue());

      // AVP name="MSISDN" code="701" vendor-id="TGPP" mandatory="must" protected="may" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp msisdnAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.MSISDN, this.msisdn);
      setMsisdn(msisdnAvp.byteArrayValue());

      // AVP name="IMEI" code="1402" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="UTF8String"
      DiameterAvp imeiAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.IMEI, this.imei);
      setUserName(imeiAvp.stringValue());

      // AVP name="Location-Estimate" code="1242" vendor-id="TGPP" mandatory="must" protected="may" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp locationEstimateAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LOCATION_ESTIMATE, this.locationEstimate);
      setLocationEstimate(locationEstimateAvp.byteArrayValue());

      // AVP name="Accuracy-Fulfilment-Indicator" code="2513" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Enumerated"
      DiameterAvp accuracyFulfilmentIndicatorAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.ACCURACY_FULFILMENT_INDICATOR, this.accuracyFulfilmentIndicator);
      AccuracyFulfilmentIndicator accuracyFulfilmentIndicatorCode = AccuracyFulfilmentIndicator.fromInt(accuracyFulfilmentIndicatorAvp.getCode());
      setAccuracyFulfilmentIndicator(accuracyFulfilmentIndicatorCode);

      // AVP name="Age-Of-Location-Estimate" code="2514" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp ageOfLocationEstimateAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.AGE_OF_LOCATION_ESTIMATE, this.ageOfLocationEstimate);
      setAgeOfLocationEstimate(ageOfLocationEstimateAvp.longValue());

      // AVP name="Velocity-Estimate" code="2515" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp velocityEstimateAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.VELOCITY_ESTIMATE, this.velocityEstimate);
      setVelocityEstimate(velocityEstimateAvp.byteArrayValue());

      // AVP name="EUTRAN-Positioning-Data" code="2516" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp eUtranPositioningDataAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.EUTRAN_POSITIONING_DATA, this.eUtranPositioningData);
      seteUtranPositioningData(eUtranPositioningDataAvp.byteArrayValue());

      // AVP ame="ECGI" code="2517" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp ecgiAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.ECGI, this.ecgi);
      setEcgi(ecgiAvp.byteArrayValue());

      // AVP name="GERAN-Positioning-Data" code="2525" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp geranPositioningDataAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.GERAN_POSITIONING_DATA, this.geranPositioningData);
      setGeranPositioningData(geranPositioningDataAvp.byteArrayValue());
      // AVP name="GERAN-GANSS-Positioning-Data" code="2526" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp geranGanssPositioningDataAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.GERAN_GANSS_POSITIONING_DATA, this.geranGANSSPositioningData);
      setGeranGANSSPositioningData(geranGanssPositioningDataAvp.byteArrayValue());
      // AVP name="GERAN-Positioning-Info" code="2524" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" >
      //		<grouped>
      //			<avp name="GERAN-Positioning-Data" multiplicity="0-1" />
      //			<avp name="GERAN-GANSS-Positioning-Data" multiplicity="0-1" />
      //		</grouped>
      geranPositioningInfoAvp = slgAVPFactory.createGERANPositioningInfo();
      geranPositioningInfoAvp.setGERANPositioningData(getGeranPositioningData());
      geranPositioningInfoAvp.setGERANGANSSPositioningData(getGeranGANSSPositioningData());

      // AVP name="Cell-Global-Identity" code="1604" vendor-id="TGPP" mandatory="mustnot" protected="may" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp cellGlobalIdAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.CELL_GLOBAL_IDENTITY, this.cellGlobalIdentity);
      setCellGlobalIdentity(cellGlobalIdAvp.byteArrayValue());

      // AVP name="UTRAN-Positioning-Data" code="2528" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp utranPositioningDataAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.UTRAN_POSITIONING_DATA, this.utranPositioningData);
      setUtranPositioningData(utranPositioningDataAvp.byteArrayValue());
      // AVP name="UTRAN-GANSS-Positioning-Data" code="2529" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp utranGANSSPositioningDataAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.UTRAN_GANSS_POSITIONING_DATA, this.utranGANSSPositioningData);
      setUtranGANSSPositioningData(utranGANSSPositioningDataAvp.byteArrayValue());
      // AVP name="UTRAN-Additional-Positioning-Data" code="2558" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp utranAdditionalPositioningDataAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.UTRAN_ADDITIONAL_POSITIONING_DATA, this.utranAdditionalPositioningData);
      setUtranAdditionalPositioningData(utranAdditionalPositioningDataAvp.byteArrayValue());
      // AVP name="UTRAN-Positioning-Info" code="2527" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" >
      //		<grouped>
      //			<avp name="UTRAN-Positioning-Data" multiplicity="0-1" />
      //			<avp name="UTRAN-GANSS-Positioning-Data" multiplicity="0-1" />
      //			<avp name="UTRAN-Additional-Positioning-Data" multiplicity="0-1" />
      //		</grouped>
      utranPositioningInfoAvp = slgAVPFactory.createUTRANPositioningInfo();
      utranPositioningInfoAvp.setUTRANPositioningData(getUtranPositioningData());
      utranPositioningInfoAvp.setUTRANGANSSPositioningData(getUtranGANSSPositioningData());
      utranPositioningInfoAvp.setUTRANAdditionalPositioningData(getUtranAdditionalPositioningData());

      // AVP name="Service-Area-Identity" code="1607" vendor-id="TGPP" mandatory="mustnot" protected="may" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp serviceAreaIdentityAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.SERVICE_AREA_IDENTITY, this.serviceAreaIdentity);
      setServiceAreaIdentity(serviceAreaIdentityAvp.byteArrayValue());

      // AVP name="LCS-Service-Type-ID" code="2520" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp lcsServiceTypeIdAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LCS_SERVICE_TYPE_ID, this.lcsServiceTypeId);
      setLcsServiceTypeId(lcsServiceTypeIdAvp.longValue());

      // AVP name="Pseudonym-Indicator" code="2519" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Enumerated"
      DiameterAvp pseudonymIndicatorAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.PSEUDONYM_INDICATOR, this.locationEvent);
      PseudonymIndicator pseudonymIndicatorCode = PseudonymIndicator.fromInt(pseudonymIndicatorAvp.getCode());
      setPseudonymIndicator(pseudonymIndicatorCode);

      // AVP name="LCS-QoS-Class" code="2523" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Enumerated"
      DiameterAvp lcsQosClassAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LCS_QoS_CLASS, this.lcsQoSClass);
      LCSQoSClass lcsQoSClassCode = LCSQoSClass.fromInt(lcsQosClassAvp.getCode());
      setLcsQoSClass(lcsQoSClassCode);

      // AVP name="SGSN-Number" code="1489" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp sgsnNumberAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.SGSN_NUMBER, this.sgsnNumber);
      setSgsnNumber(sgsnNumberAvp.byteArrayValue());
      // AVP name="SGSN-Name" code="2409" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp sgsnNameAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.SGSN_NAME);
      setSgsnName((DiameterIdentity) sgsnNameAvp);
      // AVP name="SGSN-Realm" code="2410" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp sgsnRealmAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.SGSN_REALM);
      setSgsnRealm((DiameterIdentity) sgsnRealmAvp);
      // AVP name="MME-Name" code="2402" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp mmeNameAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.MME_NAME);
      setMmeName((DiameterIdentity) mmeNameAvp);
      // AVP name="MME-Realm" code="2408" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp mmeRealmAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.MME_REALM);
      setMmeRealm((DiameterIdentity) mmeRealmAvp);
      // AVP name="MSC-Number" code="2403" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp mscNumberAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.MSC_NUMBER, this.mscNumber);
      setMscNumber(mscNumberAvp.byteArrayValue());
      // AVP name="3GPP-AAA-Server-Name" code="318" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp tgppAAAServerNameAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.TGPP_AAA_SERVER_NAME);
      setTgppAAAServerName((DiameterIdentity) tgppAAAServerNameAvp);
      // AVP name="LCS-Capabilities-Sets" code="2404" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp lcsCapabilitiesSetsAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LCS_CAPABILITIES_SETS, this.lcsCapabilitiesSets);
      setLcsCapabilitiesSets(lcsCapabilitiesSetsAvp.longValue());
      // AVP name="GMLC-Address" code="2405" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Address"
      DiameterAvp gmlcAddressAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.GMLC_ADDRESS);
      setGmlcAddress((Address) gmlcAddressAvp);
      // AVP name="Serving-Node" code="2401" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" >
      //		<grouped>
      //			<avp name="SGSN-Number" multiplicity="0-1" />
      //			<avp name="SGSN-Name" multiplicity="0-1" />
      //			<avp name="SGSN-Realm" multiplicity="0-1" />
      //			<avp name="MME-Name" multiplicity="0-1" />
      //			<avp name="MME-Realm" multiplicity="0-1" />
      //			<avp name="MSC-Number" multiplicity="0-1" />
      //			<avp name="3GPP-AAA-Server-Name" multiplicity="0-1" />
      //			<avp name="LCS-Capabilities-Sets" multiplicity="0-1" />
      //			<avp name="GMLC-Address" multiplicity="0-1" />
      //		</grouped>
      servingNodeAvp = slgAVPFactory.createServingNode();
      servingNodeAvp.setSGSNNumber(getSgsnNumber());
      servingNodeAvp.setSGSNName(getSgsnName());
      servingNodeAvp.setSGSNRealm(getSgsnRealm());
      servingNodeAvp.setMMEName(getMmeName());
      servingNodeAvp.setMMERealm(getMmeRealm());
      servingNodeAvp.setMSCNumber(getMscNumber());
      servingNodeAvp.setLcsCapabilitiesSets(getLcsCapabilitiesSets());
      servingNodeAvp.setGMLCAddress(getGmlcAddress());

      // AVP name="LRR-Flags" code="2530" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp lrrFlagsAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LRR_FLAGS, this.lrrFlags);
      setLrrFlags(lrrFlagsAvp.longValue());

      // AVP ame="LCS-Reference-Number" code="2531" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp lcsReferenceNumberAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.LCS_REFERENCE_NUMBER, this.lcsReferenceNumber);
      setLcsReferenceNumber(lcsReferenceNumberAvp.byteArrayValue());

      // AVP ame="Deferred-Location-Type" code="2532" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp deferredLocationTypeAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.DEFERRED_LOCATION_TYPE, this.deferredLocationType);
      setDeferredLocationType(deferredLocationTypeAvp.longValue());
      // AVP name="Termination-Cause" code="2548" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp terminationCauseAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.TERMINATION_CAUSE, this.terminationCause);
      setTerminationCause(terminationCauseAvp.longValue());
      // AVP name="Deferred-MT-LR-Data" code="2547" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" >
      //		<grouped>
      //			<avp name="Deferred-Location-Type" multiplicity="1" />
      //			<avp name="Termination-Cause" multiplicity="0-1" />
      //			<avp name="Serving-Node" multiplicity="0-1" />
      //		</grouped>
      deferredMTLRDataAvp = slgAVPFactory.createDeferredMTLRData();
      deferredMTLRDataAvp.setDeferredLocationType(getDeferredLocationType());
      deferredMTLRDataAvp.setTerminationCause(getTerminationCause());
      deferredMTLRDataAvp.setServingNode(getServingNodeAvp());

      // AVP name="Reporting-Amount" code="2541" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp reportingAmountAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.REPORTING_AMOUNT, this.reportingAmount);
      setReportingAmount(reportingAmountAvp.longValue());

      // AVP name="Reporting-Interval" code="2542" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp reportingIntervalAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.REPORTING_INTERVAL, this.reportingInterval);
      setReportingInterval(reportingIntervalAvp.longValue());
      // AVP name="Periodic-LDR-Info" code="2540" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must"
      //		<grouped>
      //			<avp name="Reporting-Amount" multiplicity="1" />
      //			<avp name="Reporting-Interval" multiplicity="1" />
      //		</grouped>
      periodicLDRInformation = slgAVPFactory.createPeriodicLDRInformation();
      periodicLDRInformation.setReportingAmount(getReportingAmount());
      periodicLDRInformation.setReportingInterval(getReportingInterval());

      // APN name="Cell-Portion-ID" code="2553" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp cellPortionIdAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.CELL_PORTION_ID, this.cellPortionId);
      setCellPortionId(cellPortionIdAvp.longValue());
      // AVP name="ESMLC-Cell-Info" code="2552" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" >
      //		<grouped>
      //			<avp name="ECGI" multiplicity="0-1" />
      //			<avp name="Cell-Portion-ID" multiplicity="0-1" />
      //		</grouped>
      esmlcCellInfoAvp = slgAVPFactory.createESMLCCellInfo();
      esmlcCellInfoAvp.setECGI(getEcgi());
      esmlcCellInfoAvp.setCellPortionID(getCellPortionId());

      // AVP name="1xRTT-RCID" code="2554" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp onexRTTRCIDAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.ONExRTT_RCID, this.oneXRttRcid);
      setOneXRttRcid(onexRTTRCIDAvp.byteArrayValue());

      // AVP name="Delayed-Location-Reporting-Data" code="2555" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="mustnot" >
      //		<grouped>
      //			<avp name="Termination-Cause" multiplicity="0-1" />
      //			<avp name="Serving-Node" multiplicity="0-1" />
      //		</grouped>
      delayedLocationReportingDataAvp = slgAVPFactory.createDelayedLocationReportingData();
      delayedLocationReportingDataAvp.setTerminationCause(getTerminationCause());
      delayedLocationReportingDataAvp.setServingNode(getServingNodeAvp());

      // AVP name="Civic-Address" code="2556" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="UTF8String"
      DiameterAvp civicAddressAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.CIVIC_ADDRESS, this.civicAddress);
      setCivicAddress(civicAddressAvp.byteArrayValue());

      // name="Barometric-Pressure" code="2557" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" <type type-name="Unsigned32"
      DiameterAvp barometricPressureAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.BAROMETRIC_PRESSURE, this.barometricPressure);
      setBarometricPressure(barometricPressureAvp.longValue());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
