package org.mobicents.gmlc.slee.diameter.slg;

import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.slg.SLgAVPFactory;
import net.java.slee.resource.diameter.slg.events.avp.AccuracyFulfilmentIndicator;
import net.java.slee.resource.diameter.slg.events.avp.ELPAVPCodes;
import net.java.slee.resource.diameter.slg.events.avp.ESMLCCellInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.GERANPositioningInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.ServingNodeAvp;
import net.java.slee.resource.diameter.slg.events.avp.UTRANPositioningInfoAvp;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLgPlaAvpValues implements Serializable {

  private static final long serialVersionUID = 70989240932624124L;

  private static final int DIAMETER_SLg_VENDOR_ID = 10415;

  /*
    3GPP TS 29.172 v15.0.0 reference
      7.3.2	Provide-Location-Answer (PLA) Command
      The Provide-Location-Answer (PLA) command, indicated by the Command-Code field set to 8388620 and the 'R' bit cleared in the Command Flags field,
      is sent by the MME or SGSN to the GMLC in response to the Provide-Location-Request command.

      Message Format:

      < Provide-Location-Answer > ::=	< Diameter Header: 8388620, PXY, 16777255 >
			                        < Session-Id >
	                                [ Vendor-Specific-Application-Id ]
                                    [ Result-Code ]
                                    [ Experimental-Result ]
                                    { Auth-Session-State }
                                    { Origin-Host }
                                    { Origin-Realm }
                                    [ Location-Estimate ]
                                    [ Accuracy-Fulfilment-Indicator ]
                                    [ Age-Of-Location-Estimate]
                                    [ Velocity-Estimate ]
                                    [ EUTRAN-Positioning-Data]
                                    [ ECGI ]
                                    [ GERAN-Positioning-Info ]
                                    [ Cell-Global-Identity ]
                                    [ UTRAN-Positioning-Info ]
                                    [ Service-Area-Identity ]
                                    [ Serving-Node ]
                                    [ PLA-Flags ]
                                    [ ESMLC-Cell-Info ]
                                    [ Civic-Address ]
                                    [ Barometric-Pressure ]
                                    *[ Supported-Features ]
                                    *[ AVP ]
                                    [ Failed-AVP ]
                                    *[ Proxy-Info ]
                                    *[ Route-Record ]
  */

  private Long resultCode;
  private ExperimentalResultAvp experimentalResultAvp;
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
  private Long plaFlags;
  private ESMLCCellInfoAvp esmlcCellInfoAvp;
  private Long cellPortionId;
  private byte[] civicAddress;
  private Long barometricPressure;

  public SLgPlaAvpValues() {
    super();
  }

  public SLgPlaAvpValues(byte[] locationEstimate, AccuracyFulfilmentIndicator accuracyFulfilmentIndicator, Long ageOfLocationEstimate, byte[] velocityEstimate,
                         byte[] eUtranPositioningData, byte[] ecgi, GERANPositioningInfoAvp geranPositioningInfoAvp, byte[] geranPositioningData,
                         byte[] geranGANSSPositioningData, byte[] cellGlobalIdentity, UTRANPositioningInfoAvp utranPositioningInfoAvp,
                         byte[] utranPositioningData, byte[] utranGANSSPositioningData, byte[] utranAdditionalPositioningData,
                         byte[] serviceAreaIdentity, ServingNodeAvp servingNodeAvp, byte[] sgsnNumber, DiameterIdentity sgsnName,
                         DiameterIdentity sgsnRealm, DiameterIdentity mmeName, DiameterIdentity mmeRealm, byte[] mscNumber, DiameterIdentity tgppAAAServerName,
                         Long lcsCapabilitiesSets, Address gmlcAddress, Long plaFlags, ESMLCCellInfoAvp esmlcCellInfoAvp, Long cellPortionId,
                         byte[] civicAddress, Long barometricPressure, Long resultCode, ExperimentalResultAvp experimentalResultAvp) {
    super();
    this.resultCode = resultCode;
    this.experimentalResultAvp = experimentalResultAvp;
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
    this.plaFlags = plaFlags;
    this.esmlcCellInfoAvp = esmlcCellInfoAvp;
    this.cellPortionId = cellPortionId;
    this.civicAddress = civicAddress;
    this.barometricPressure = barometricPressure;
  }

  public Long getResultCode() {
    return resultCode;
  }

  public void setResultCode(Long resultCode) {
    this.resultCode = resultCode;
  }

  public ExperimentalResultAvp getExperimentalResultAvp() {
    return experimentalResultAvp;
  }

  public void setExperimentalResultAvp(ExperimentalResultAvp experimentalResultAvp) {
    this.experimentalResultAvp = experimentalResultAvp;
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

  public Long getPlaFlags() {
    return plaFlags;
  }

  public void setPlaFlags(Long plaFlags) {
    this.plaFlags = plaFlags;
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

  /** PLA AVPs building method **/
  public void createPLAAvps(SLgAVPFactory slgAVPFactory) throws Exception {

    try {

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

      // AVP name="PLA-Flags" code="2546" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp plaFlagsAvp = slgAVPFactory.createAvp(DIAMETER_SLg_VENDOR_ID, ELPAVPCodes.PLA_FLAGS, this.plaFlags);
      setPlaFlags(plaFlagsAvp.longValue());

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
