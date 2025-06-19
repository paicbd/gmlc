package org.mobicents.gmlc.slee.diameter.slh;

import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.slh.SLhAVPFactory;
import net.java.slee.resource.diameter.slh.events.avp.AdditionalServingNodeAvp;
import net.java.slee.resource.diameter.slh.events.avp.LCSRoutingInfoAVPCodes;
import net.java.slee.resource.diameter.slh.events.avp.ServingNodeAvp;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SLhRiaAvpValues implements Serializable {

  private static final long serialVersionUID = 4175527953889836017L;

  private static final int DIAMETER_SLh_VENDOR_ID = 10415;

  /*
    3GPP TS 29.173 v15.0.0 reference
      6.2.4	LCS-Routing-Info-Answer (RIA) Command
      The LCS-Routing-Info-Answer (RIA) command, indicated by the Command-Code field set to 8388622
      and the 'R' bit cleared in the Command Flags field, is sent from HSS to GMLC.

      Message Format:

      < LCS-Routing-Info-Answer> ::=	< Diameter Header: 8388622, PXY, 16777291 >

      < Session-Id >
	  [ Vendor-Specific-Application-Id ]
	  [ Result-Code ]
      [ Experimental-Result ]
      { Auth-Session-State }
      { Origin-Host }
      { Origin-Realm }
      *[ Supported-Features ]
      [ User-Name ]
      [ MSISDN ]
      [ LMSI ]
      [ Serving-Node ]
      *[ Additional-Serving-Node ]
      [ GMLC-Address ]
      [ PPR-Address ]
      [ RIA-Flags ]
      *[ AVP ]
      [ Failed-AVP ]
      *[ Proxy-Info ]
      *[ Route-Record ]

  */

  private Long resultCode;
  private ExperimentalResultAvp experimentalResultAvp;
  private String userName;
  private byte[] msisdn;
  private byte[] lmsi;
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
  private AdditionalServingNodeAvp additionalServingNodeAvp;
  private byte[] additionalSGSNNumber;
  private DiameterIdentity additionalSGSNName;
  private DiameterIdentity additionalSGSNRealm;
  private DiameterIdentity additionalMMEName;
  private DiameterIdentity additionalMMERealm;
  private byte[] additionalMSCNumber;
  private DiameterIdentity additional3GPPAAAServerName;
  private Long  additionalLCSCapabilitiesSets;
  private Address additionalGMLCAddress;
  private Address pprAddress;
  private Long riaFLags;

  public Integer lteLcsReferenceNumber;

  public SLhRiaAvpValues() {
    super();
  }

  public SLhRiaAvpValues(String userName, byte[] msisdn, byte[] lmsi, byte[] sgsnNumber, DiameterIdentity sgsnName, DiameterIdentity sgsnRealm, DiameterIdentity mmeName,
                         DiameterIdentity mmeRealm, byte[] mscNumber, DiameterIdentity tgppAAAServerName, Long  lcsCapabilitiesSets, Address gmlcAddress,
                         byte[] additionalSGSNNumber, DiameterIdentity additionalSGSNName, DiameterIdentity additionalSGSNRealm, DiameterIdentity additionalMMEName,
                         DiameterIdentity additionalMMERealm, byte[] additionalMSCNumber, DiameterIdentity additional3GPPAAAServerName, Long  additionalLCSCapabilitiesSets,
                         Address additionalGMLCAddress, Address pprAddress, Long  riaFLags, Long resultCode, ExperimentalResultAvp experimentalResultAvp) {
    super();
    this.resultCode = resultCode;
    this.experimentalResultAvp = experimentalResultAvp;
    this.userName = userName;
    this.msisdn = msisdn;
    this.lmsi = lmsi;
    this.sgsnNumber = sgsnNumber;
    this.sgsnName = sgsnName;
    this.sgsnRealm = sgsnRealm;
    this.mmeName = mmeName;
    this.mmeRealm = mmeRealm;
    this.mscNumber = mscNumber;
    this.tgppAAAServerName = tgppAAAServerName;
    this.lcsCapabilitiesSets = lcsCapabilitiesSets;
    this.gmlcAddress = gmlcAddress;
    this.additionalSGSNNumber = additionalSGSNNumber;
    this.additionalSGSNName = additionalSGSNName;
    this.additionalSGSNRealm = additionalSGSNRealm;
    this.additionalMMEName = additionalMMEName;
    this.additionalMMERealm = additionalMMERealm;
    this.additionalMSCNumber = additionalMSCNumber;
    this.additional3GPPAAAServerName = additional3GPPAAAServerName;
    this.additionalLCSCapabilitiesSets = additionalLCSCapabilitiesSets;
    this.additionalGMLCAddress = additionalGMLCAddress;
    this.pprAddress = pprAddress;
    this.riaFLags = riaFLags;
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

  public byte[] getLmsi() {
    return lmsi;
  }

  public void setLmsi(byte[] lmsi) {
    this.lmsi = lmsi;
  }

  public ServingNodeAvp getServingNodeAvp() {
    return servingNodeAvp;
  }

  public void setServingNodeAvp(ServingNodeAvp servingNodeAvp) {
    this.servingNodeAvp = servingNodeAvp;
    this.sgsnNumber = servingNodeAvp.getSGSNNumber();
    this.sgsnName = servingNodeAvp.getSGSNName();
    this.sgsnRealm = servingNodeAvp.getSGSNRealm();
    this.mmeName = servingNodeAvp.getMMEName();
    this.mmeRealm = servingNodeAvp.getMMERealm();
    this.mscNumber = servingNodeAvp.getMSCNumber();
    this.lcsCapabilitiesSets = servingNodeAvp.getLcsCapabilitiesSets();
    this.tgppAAAServerName = servingNodeAvp.get3GPPAAAServerName();
    this.gmlcAddress = servingNodeAvp.getGMLCAddress();
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

  public Long  getLcsCapabilitiesSets() {
    return lcsCapabilitiesSets;
  }

  public void setLcsCapabilitiesSets(Long  lcsCapabilitiesSets) {
    this.lcsCapabilitiesSets = lcsCapabilitiesSets;
  }

  public Address getGmlcAddress() {
    return gmlcAddress;
  }

  public void setGmlcAddress(Address gmlcAddress) {
    this.gmlcAddress = gmlcAddress;
  }

  public AdditionalServingNodeAvp getAdditionalServingNodeAvp() {
    return additionalServingNodeAvp;
  }

  public void setAdditionalServingNodeAvp(AdditionalServingNodeAvp additionalServingNodeAvp) {
    this.additionalServingNodeAvp = additionalServingNodeAvp;
    this.additionalSGSNNumber = additionalServingNodeAvp.getSGSNNumber();
    this.additionalSGSNName = additionalServingNodeAvp.getSGSNName();
    this.additionalSGSNRealm = additionalServingNodeAvp.getSGSNRealm();
    this.additionalMMEName = additionalServingNodeAvp.getMMEName();
    this.additionalMMERealm = additionalServingNodeAvp.getMMERealm();
    this.additionalMSCNumber = additionalServingNodeAvp.getMSCNumber();
    this.additionalLCSCapabilitiesSets = additionalServingNodeAvp.getLcsCapabilitiesSets();
    this.additional3GPPAAAServerName = additionalServingNodeAvp.get3GPPAAAServerName();
    this.additionalGMLCAddress = additionalServingNodeAvp.getGMLCAddress();
  }

  public byte[] getAdditionalSGSNNumber() {
    return additionalSGSNNumber;
  }

  public void setAdditionalSGSNNumber(byte[] additionalSGSNNumber) {
    this.additionalSGSNNumber = additionalSGSNNumber;
  }

  public DiameterIdentity getAdditionalSGSNName() {
    return additionalSGSNName;
  }

  public void setAdditionalSGSNName(DiameterIdentity additionalSGSNName) {
    this.additionalSGSNName = additionalSGSNName;
  }

  public DiameterIdentity getAdditionalSGSNRealm() {
    return additionalSGSNRealm;
  }

  public void setAdditionalSGSNRealm(DiameterIdentity additionalSGSNRealm) {
    this.additionalSGSNRealm = additionalSGSNRealm;
  }

  public DiameterIdentity getAdditionalMMEName() {
    return additionalMMEName;
  }

  public void setAdditionalMMEName(DiameterIdentity additionalMMEName) {
    this.additionalMMEName = additionalMMEName;
  }

  public DiameterIdentity getAdditionalMMERealm() {
    return additionalMMERealm;
  }

  public void setAdditionalMMERealm(DiameterIdentity additionalMMERealm) {
    this.additionalMMERealm = additionalMMERealm;
  }

  public byte[] getAdditionalMSCNumber() {
    return additionalMSCNumber;
  }

  public void setAdditionalMSCNumber(byte[] additionalMSCNumber) {
    this.additionalMSCNumber = additionalMSCNumber;
  }

  public DiameterIdentity getAdditional3GPPAAAServerName() {
    return additional3GPPAAAServerName;
  }

  public void setAdditional3GPPAAAServerName(DiameterIdentity additional3GPPAAAServerName) {
    this.additional3GPPAAAServerName = additional3GPPAAAServerName;
  }

  public Long  getAdditionalLCSCapabilitiesSets() {
    return additionalLCSCapabilitiesSets;
  }

  public void setAdditionalLCSCapabilitiesSets(Long  additionalLCSCapabilitiesSets) {
    this.additionalLCSCapabilitiesSets = additionalLCSCapabilitiesSets;
  }

  public Address getAdditionalGMLCAddress() {
    return additionalGMLCAddress;
  }

  public void setAdditionalGMLCAddress(Address additionalGMLCAddress) {
    this.additionalGMLCAddress = additionalGMLCAddress;
  }

  public Address getPprAddress() {
    return pprAddress;
  }

  public void setPprAddress(Address pprAddress) {
    this.pprAddress = pprAddress;
  }

  public Long  getRiaFLags() {
    return riaFLags;
  }

  public void setRiaFLags(Long  riaFLags) {
    this.riaFLags = riaFLags;
  }

  /** RIA AVPs building method **/
  public void createRIAAvps(SLhAVPFactory slhAVPFactory) throws Exception {

    try {

      // AVP name="User-Name" code="1" mandatory="must" protected="may" may-encrypt="yes" vendor-bit="mustnot" type-name="UTF8String"
      DiameterAvp userNameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.USER_NAME, this.userName);
      setUserName(userNameAvp.stringValue());

      // AVP name="MSISDN" code="701" vendor-id="TGPP" mandatory="must" protected="may" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp msisdnAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.MSISDN, this.msisdn);
      setMsisdn(msisdnAvp.byteArrayValue());

      // AVP name="LMSI" code="2400" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp lmsiAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.LMSI, this.lmsi);
      setLmsi(lmsiAvp.byteArrayValue());

      // AVP name="SGSN-Number" code="1489" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp sgsnNumberAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.SGSN_NUMBER, this.sgsnNumber);
      setSgsnNumber(sgsnNumberAvp.byteArrayValue());
      // AVP name="SGSN-Name" code="2409" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp sgsnNameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.SGSN_NAME);
      setSgsnName((DiameterIdentity) sgsnNameAvp);
      // AVP name="SGSN-Realm" code="2410" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp sgsnRealmAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.SGSN_REALM);
      setSgsnRealm((DiameterIdentity) sgsnRealmAvp);
      // AVP name="MME-Name" code="2402" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp mmeNameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.MME_NAME);
      setMmeName((DiameterIdentity) mmeNameAvp);
      // AVP name="MME-Realm" code="2408" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp mmeRealmAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.MME_REALM);
      setMmeRealm((DiameterIdentity) mmeRealmAvp);
      // AVP name="MSC-Number" code="2403" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp mscNumberAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.MSC_NUMBER, this.mscNumber);
      setMscNumber(mscNumberAvp.byteArrayValue());
      // AVP name="3GPP-AAA-Server-Name" code="318" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp tgppAAAServerNameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.TGPP_AAA_SERVER_NAME);
      setTgppAAAServerName((DiameterIdentity) tgppAAAServerNameAvp);
      // AVP name="LCS-Capabilities-Sets" code="2404" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp lcsCapabilitiesSetsAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.LCS_CAPABILITIES_SETS, this.lcsCapabilitiesSets);
      setLcsCapabilitiesSets(lcsCapabilitiesSetsAvp.longValue());
      // AVP name="GMLC-Address" code="2405" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Address"
      DiameterAvp gmlcAddressAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.GMLC_ADDRESS);
      setGmlcAddress((Address) gmlcAddressAvp);
      // name="Serving-Node" code="2401" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" >
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
      servingNodeAvp = slhAVPFactory.createServingNode();
      servingNodeAvp.setSGSNNumber(getSgsnNumber());
      servingNodeAvp.setSGSNName(getSgsnName());
      servingNodeAvp.setSGSNRealm(getSgsnRealm());
      servingNodeAvp.setMMEName(getMmeName());
      servingNodeAvp.setMMERealm(getMmeRealm());
      servingNodeAvp.setMSCNumber(getMscNumber());
      servingNodeAvp.setLcsCapabilitiesSets(getLcsCapabilitiesSets());
      servingNodeAvp.setGMLCAddress(getGmlcAddress());

      // AVP name="SGSN-Number" code="1489" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp additionalSGSNNumberAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.SGSN_NUMBER, this.additionalSGSNNumber);
      setAdditionalSGSNNumber(additionalSGSNNumberAvp.byteArrayValue());
      // AVP name="SGSN-Name" code="2409" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp additionalSGSNNameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.SGSN_NAME);
      setAdditionalSGSNName((DiameterIdentity) additionalSGSNNameAvp);
      // AVP name="SGSN-Realm" code="2410" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp additionalSGSNRealmAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.SGSN_REALM);
      setAdditionalSGSNRealm((DiameterIdentity) additionalSGSNRealmAvp);
      // AVP name="MME-Name" code="2402" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp additionalMMENameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.MME_NAME);
      setAdditionalMMEName((DiameterIdentity) additionalMMENameAvp);
      // AVP name="MME-Realm" code="2408" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp additionalMMERealmAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.MME_REALM);
      setAdditionalMMERealm((DiameterIdentity) additionalMMERealmAvp);
      // AVP name="MSC-Number" code="2403" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="OctetString"
      DiameterAvp additionalMSCNumberAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.MSC_NUMBER, this.additionalMSCNumber);
      setAdditionalMSCNumber(additionalMSCNumberAvp.byteArrayValue());
      // AVP name="3GPP-AAA-Server-Name" code="318" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="DiameterIdentity"
      DiameterAvp additional3gppAAAServerNameAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.TGPP_AAA_SERVER_NAME);
      setAdditional3GPPAAAServerName((DiameterIdentity) additional3gppAAAServerNameAvp);
      // AVP name="LCS-Capabilities-Sets" code="2404" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp additionalLcsCapabilitiesSetsAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.LCS_CAPABILITIES_SETS, this.additionalLCSCapabilitiesSets);
      setAdditionalLCSCapabilitiesSets(additionalLcsCapabilitiesSetsAvp.longValue());
      // AVP name="GMLC-Address" code="2405" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Address"
      DiameterAvp additionalGmlcAddressAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.GMLC_ADDRESS);
      setAdditionalGMLCAddress((Address) additionalGmlcAddressAvp);
      // name="Additional-Serving-Node" code="2406" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" >
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
      additionalServingNodeAvp = slhAVPFactory.createAdditionalServingNode();
      additionalServingNodeAvp.setSGSNNumber(getAdditionalSGSNNumber());
      additionalServingNodeAvp.setSGSNName(getAdditionalSGSNName());
      additionalServingNodeAvp.setSGSNRealm(getAdditionalSGSNRealm());
      additionalServingNodeAvp.setMMEName(getAdditionalMMEName());
      additionalServingNodeAvp.setMMERealm(getAdditionalMMERealm());
      additionalServingNodeAvp.setMSCNumber(getAdditionalMSCNumber());
      additionalServingNodeAvp.set3GPPAAAServerName(getAdditional3GPPAAAServerName());
      additionalServingNodeAvp.setLcsCapabilitiesSets(getAdditionalLCSCapabilitiesSets());
      additionalServingNodeAvp.setGMLCAddress(getAdditionalGMLCAddress());

      // AVP name="PPR-Address" code="2407" vendor-id="TGPP" mandatory="must" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Address"
      DiameterAvp pprAddressAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.PPR_ADDRESS);
      setPprAddress((Address) pprAddressAvp);

      // AVP name="RIA-Flags" code="2411" vendor-id="TGPP" mandatory="mustnot" protected="mustnot" may-encrypt="no" vendor-bit="must" type-name="Unsigned32"
      DiameterAvp riaFlagsAvp = slhAVPFactory.createAvp(DIAMETER_SLh_VENDOR_ID, LCSRoutingInfoAVPCodes.RIA_FLAGS, this.riaFLags);
      setLcsCapabilitiesSets(riaFlagsAvp.longValue());

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}