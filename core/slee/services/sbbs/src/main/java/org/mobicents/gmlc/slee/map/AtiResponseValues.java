package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationEPS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationNumberMap;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RouteingNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberState;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MNPInfoRes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class AtiResponseValues implements Serializable {

  private static final long serialVersionUID = 1L;

  /*
   3GPP TS 29.002 MAP Specification v15.0.0 (2017-03-15)

    8.11	Subscriber Information services

    8.11.1	MAP-ANY-TIME-INTERROGATION service

    8.11.1.1	Definition
    This service is used by the gsmSCF, to request information (e.g. subscriber state and location) from the HLR or the GMLC at any time.
    This service may also be used by the gsmSCF to request the Mobile Number Portability (MNP) information from the NPLR.
    This service is also used by the Presence Network Agent to request information, (e.g. subscriber state and location)
    about the subscriber (associated with a presentity) from the HLR at any time (see 3GPP TS 23.141).
    When this service is used to the HLR, the subscriber state, location, Time Zone, or T-ADS data may be requested.
    When this service is used to the GMLC, only the location may be requested.
    When this service is used to the NPLR, only the MNP information may be requested.

    8.11.1.2	Service primitives
    Parameter name                              Request     Indication  Response    Confirm
    Invoke id                                      M           M(=)        M(=)       M(=)
    Requested Info                                 M           M(=)
    Requested domain                               C           C(=)
    MNP Requested Info                             C           C(=)
    gsmSCF-Address                                 M           M(=)
    IMSI                                           C           C(=)
    MSISDN                                         C           C(=)
    Location Information                                                    C         C(=)
    Location Information for GPRS                                           C         C(=)
    Location Information for EPS                                            C         C(=)
    Subscriber State                                                        C         C(=)
    PS Subscriber State                                                     C         C(=)
    EPS Subscriber State                                                    C         C(=)
    IMEI                                                                    C         C(=)
    MS Classmark 2                                                          C         C(=)
    GPRS MS Class                                                           C         C(=)
    MNP info Result                                                         C         C(=)
    IMS Voice Over PS Sessions Support Indicator                            C         C(=)
    Last UE Activity Time                                                   C         C(=)
    Last RAT Type                                                           C         C(=)
    Time Zone                                                               C         C(=)
    Daylight Saving Time                                                    C         C(=)
    User error                                                              C         C(=)
    Provider error                                                                    O

  ? (M): mandatory parameter.
  ? (O): provider option.
  ? (C): conditional parameter (i.e. it will always be present in the indication type primitive
         if it was present in the corresponding request type primitive).
  ? (U): TC-user optional parameter.
  ? (=): the parameter must have the same value in the indication primitive as provided in the
         corresponding request primitive.
  ? A blank Indicates that the parameter is not applicable.

  */
  private LocationInformation locationInformation; // The VLR indicates in this parameter the location of the served subscriber as defined in 3GPP TS 23.018.
  // LocationInformation/GPRS/EPS includes Geographical Information
  // The VLR indicates in this parameter the location of the served subscriber as defined in 3GPP TS 23.018 and 3GPP TS 23.032.
  // Shall be present if the VLR can derive it from the stored service area identity, cell global identity or location area identity; otherwise shall be absent.
  private Double  geographicalLatitude;
  private Double  geographicalLongitude;
  private Double  geographicalUncertainty;
  private TypeOfShape typeOfShape;
  // LocationInformation/GPRS/EPS includes Geodetic Information
  // This information element corresponds to the Calling Geodetic Location defined in ITU-T Recommendation Q.763.
  // Shall be present if the VLR can derive it from the stored service area identity, cell global identity or location area identity; otherwise shall be absent.
  private Double  geodeticLatitude;
  private Double  geodeticLongitude;
  private Double  geodeticUncertainty;
  private Integer geodeticConfidence;
  private Integer screeningAndPresentationIndicators;

  private CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
  private Integer mcc; // MCC of CGI or SAI or LAI
  private Integer mnc; // MNC of CGI or SAI or LAI
  private Integer lac; // LAC of CGI or SAI or LAI
  private Integer ci; // CI of CGI or SAI
  private Integer ageOfLocationInfo; // Age of location information of CGI or LAI
  ISDNAddressString vlrNumber; // VLR Global Title at which the MS is attached to
  ISDNAddressString mscNumber; // MSC Global Title at which the MS is attached to
  private LocationInformationGPRS locationInformationGPRS; // The SGSN indicates here the location of the served subscriber.
  // LocationInformationGPRS includes SGSN number, LSAIdentity, RAIdentity
  private ISDNAddressString sgsnNumber;
  private LSAIdentity lsaIdentity;
  private Boolean isPLMNSignificantLSA;
  private RAIdentity raIdentity;
  // The MME (via an IWF) indicates in LocationInformationEPS the location of the served subscriber.
  private LocationInformationEPS locationInformationEPS;
  private DiameterIdentity mmeName; // The MME indicates here the location of the served subscriber.

  private SubscriberState subscriberState; // State of the MS as defined in 3GPP TS 23.018.
  // Possible values are assumedIdle, camelBusy, networkDeterminedNotReachable, notProvidedFromVlr
  // LocationInformation/GPRS/EPS includes if SAI is present
  private Boolean saiPresent;
  // LocationInformation/GPRS/EPS includes if current location information is retrieved.
  // Shall be present when location information was obtained after a successful paging procedure for Active Location Retrieval.
  private Boolean currentLocationRetrieved;
  private PSSubscriberState psSubscriberState; // Packet-Switched state of the MS.
  private IMEI imei; // International Mobile Equipment Identity.
  private MSClassmark2 msClassmark2; // Defined in 3GPP TS 24.008.
  private GPRSMSClass gprsmsClass;
  // MNP Info Result refers to the Mobile Number Portability (MNP) information result (3GPP TS 23.078 and 3GPP TS 23.066).
  // This parameter may contain the following information: Routeing Number, IMSI, MSISDN, Number Portability Status.
  private MNPInfoRes mnpInfoRes;
  private Integer numberPortabilityStatus; // Number portability status of subscriber (3GPP TS 23.066).
  private ISDNAddressString msisdn;
  private String msisdnAddress;
  private IMSI imsi;
  private String imsiData;
  private RouteingNumber routeingNumber; // RouteingNumber refers to a number used for routeing purpose and identifying a network operator (3GPP TS 23.066).
  private String routeingNumberStr;
  // LocationInformation includes Location Number (defined at ITU T Recommendation Q.763)
  // Shall be present if the VLR can derive it from the stored service area identity (for UMTS) or cell global identity (for GSM) or location area identity;
  // otherwise shall be absent. The mapping from service area identity or cell ID and location area to location number is network-specific
  // and outside the scope of the UMTS and GSM standards.
  private LocationNumberMap locationNumberMap;

  // LocationInformation/EPS includes E-UTRAN CGI
  // E-UTRAN cell global identity of the cell in which the MS is currently in radio contact or in which the MS was last in radio contact.
  // Shall be present if the MS uses E-UTRAN radio access and the subscriber record is marked as confirmed by radio contact; otherwise shall be absent.
  private EUtranCgi eUtranCgi;

  // LocationInformation/EPS includes Tracking Area Id
  // Tracking area identity of the cell in which the MS is currently in radio contact or in which the MS was last in radio contact.
  // Shall be present if the MS uses E-UTRAN radio access; otherwise shall be absent.
  TAId taId;


  public AtiResponseValues() {
    super();
  }

  public AtiResponseValues(LocationInformation locationInformation, SubscriberState subscriberState) {
    super();
    this.locationInformation = locationInformation;
    this.subscriberState = subscriberState;
  }

  public LocationInformation getLocationInformation() {
    return locationInformation;
  }

  public void setLocationInformation(LocationInformation locationInformation) {
    this.locationInformation = locationInformation;
    this.ageOfLocationInfo = locationInformation.getAgeOfLocationInformation();
    this.vlrNumber = locationInformation.getVlrNumber();
  }

  public CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI() {
    return cellGlobalIdOrServiceAreaIdOrLAI;
  }

  public void setCellGlobalIdOrServiceAreaIdOrLAI(CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI) {
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
    if (cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
      try {
        this.mcc = cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMCC();
        this.mnc = cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMNC();
        this.lac = cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getLac();
      } catch (MAPException e) {
        e.printStackTrace();
      }
    }
    if (cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
      try {
        this.mcc = cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
        this.mnc = cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
        this.lac = cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
        this.ci = cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
      } catch (MAPException e) {
        e.printStackTrace();
      }
    }
  }

  public Integer getMcc() {
    return mcc;
  }

  public void setMcc(Integer mcc) {
    this.mcc = mcc;
  }

  public Integer getMnc() {
    return mnc;
  }

  public void setMnc(Integer mnc) {
    this.mnc = mnc;
  }

  public Integer getLac() {
    return lac;
  }

  public void setLac(Integer lac) {
    this.lac = lac;
  }

  public Integer getCi() {
    return ci;
  }

  public void setCi(Integer ci) {
    this.ci = ci;
  }

  public ISDNAddressString getVlrNumber() {
    return vlrNumber;
  }

  public void setVlrNumber(ISDNAddressString vlrNumber) {
    this.vlrNumber = vlrNumber;
  }

  public ISDNAddressString getMscNumber() {
    return mscNumber;
  }

  public void setMscNumber(ISDNAddressString mscNumber) {
    this.mscNumber = mscNumber;
  }

  public Integer getAgeOfLocationInfo() {
    return ageOfLocationInfo;
  }

  public TypeOfShape getTypeOfShape() {
    return typeOfShape;
  }

  public void setTypeOfShape(TypeOfShape typeOfShape) {
    this.typeOfShape = typeOfShape;
  }

  public Double  getGeographicalLatitude() {
    return geographicalLatitude;
  }

  public void setGeographicalLatitude(Double  geographicalLatitude) {
    this.geographicalLatitude = geographicalLatitude;
  }

  public Double  getGeographicalLongitude() {
    return geographicalLongitude;
  }

  public void setGeographicalLongitude(Double  geographicalLongitude) {
    this.geographicalLongitude = geographicalLongitude;
  }

  public Double  getGeographicalUncertainty() {
    return geographicalUncertainty;
  }

  public void setGeographicalUncertainty(Double  geographicalUncertainty) {
    this.geographicalUncertainty = geographicalUncertainty;
  }

  public Double  getGeodeticLatitude() {
    return geodeticLatitude;
  }

  public void setGeodeticLatitude(Double  geodeticLatitude) {
    this.geodeticLatitude = geodeticLatitude;
  }

  public Double  getGeodeticLongitude() {
    return geodeticLongitude;
  }

  public void setGeodeticLongitude(Double  geodeticLongitude) {
    this.geodeticLongitude = geodeticLongitude;
  }

  public Double  getGeodeticUncertainty() {
    return geodeticUncertainty;
  }

  public void setGeodeticUncertainty(Double  geodeticUncertainty) {
    this.geodeticUncertainty = geodeticUncertainty;
  }

  public Integer getGeodeticConfidence() {
    return geodeticConfidence;
  }

  public void setGeodeticConfidence(Integer geodeticConfidence) {
    this.geodeticConfidence = geodeticConfidence;
  }

  public Integer getScreeningAndPresentationIndicators() {
    return screeningAndPresentationIndicators;
  }

  public void setScreeningAndPresentationIndicators(Integer screeningAndPresentationIndicators) {
    this.screeningAndPresentationIndicators = screeningAndPresentationIndicators;
  }

  public void setAgeOfLocationInfo(Integer ageOfLocationInfo) {
    this.ageOfLocationInfo = ageOfLocationInfo;
  }

  public SubscriberState getSubscriberState() {
    return subscriberState;
  }

  public void setSubscriberState(SubscriberState subscriberState) {
    this.subscriberState = subscriberState;
  }

  public Boolean isSaiPresent() {
    return saiPresent;
  }

  public void setSaiPresent(Boolean saiPresent) {
    this.saiPresent = saiPresent;
  }

  public Boolean isCurrentLocationRetrieved() {
    return currentLocationRetrieved;
  }

  public void setCurrentLocationRetrieved(Boolean currentLocationRetrieved) {
    this.currentLocationRetrieved = currentLocationRetrieved;
  }

  public LocationInformationGPRS getLocationInformationGPRS() {
    return locationInformationGPRS;
  }

  public void setLocationInformationGPRS(LocationInformationGPRS locationInformationGPRS) {
    this.locationInformationGPRS = locationInformationGPRS;
  }

  public ISDNAddressString getSgsnNumber() {
    return sgsnNumber;
  }

  public void setSgsnNumber(ISDNAddressString sgsnNumber) {
    this.sgsnNumber = sgsnNumber;
  }

  public PSSubscriberState getPsSubscriberState() {
    return psSubscriberState;
  }

  public void setPsSubscriberState(PSSubscriberState psSubscriberState) {
    this.psSubscriberState = psSubscriberState;
  }

  public LSAIdentity getLsaIdentity() {
    return lsaIdentity;
  }

  public void setLsaIdentity(LSAIdentity lsaIdentity) {
    this.lsaIdentity = lsaIdentity;
  }

  public Boolean isPLMNSignificantLSA() {
    return isPLMNSignificantLSA;
  }

  public void setPLMNSignificantLSA(Boolean PLMNSignificantLSA) {
    isPLMNSignificantLSA = PLMNSignificantLSA;
  }

  public RAIdentity getRaIdentity() {
    return raIdentity;
  }

  public void setRaIdentity(RAIdentity raIdentity) {
    this.raIdentity = raIdentity;
  }

  public RouteingNumber getRouteingNumber() {
    return routeingNumber;
  }

  public void setRouteingNumber(RouteingNumber routeingNumber) {
    this.routeingNumber = routeingNumber;
  }

  public String getRouteingNumberStr() {
    return routeingNumberStr;
  }

  public void setRouteingNumberStr(String routeingNumberStr) {
    this.routeingNumberStr = routeingNumberStr;
  }

  public LocationNumberMap getLocationNumberMap() {
    return locationNumberMap;
  }

  public void setLocationNumberMap(LocationNumberMap locationNumberMap) {
    this.locationNumberMap = locationNumberMap;
  }

  public LocationInformationEPS getLocationInformationEPS() {
    return locationInformationEPS;
  }

  public void setLocationInformationEPS(LocationInformationEPS locationInformationEPS) {
    this.locationInformationEPS = locationInformationEPS;
  }

  public EUtranCgi geteUtranCgi() {
    return eUtranCgi;
  }

  public void seteUtranCgi(EUtranCgi eUtranCgi) {
    this.eUtranCgi = eUtranCgi;
  }

  public TAId getTaId() {
    return taId;
  }

  public void setTaId(TAId taId) {
    this.taId = taId;
  }

  public DiameterIdentity getMmeName() {
    return mmeName;
  }

  public void setMmeName(DiameterIdentity mmeName) {
    this.mmeName = mmeName;
  }

  public IMEI getImei() {
    return imei;
  }

  public void setImei(IMEI imei) {
    this.imei = imei;
  }

  public MSClassmark2 getMsClassmark2() {
    return msClassmark2;
  }

  public void setMsClassmark2(MSClassmark2 msClassmark2) {
    this.msClassmark2 = msClassmark2;
  }

  public GPRSMSClass getGprsmsClass() {
    return gprsmsClass;
  }

  public void setGprsmsClass(GPRSMSClass gprsmsClass) {
    this.gprsmsClass = gprsmsClass;
  }

  public MNPInfoRes getMnpInfoRes() {
    return mnpInfoRes;
  }

  public void setMnpInfoRes(MNPInfoRes mnpInfoRes) {
    this.mnpInfoRes = mnpInfoRes;
  }

  public Integer getNumberPortabilityStatus() {
    return numberPortabilityStatus;
  }

  public void setNumberPortabilityStatus(Integer numberPortabilityStatus) {
    this.numberPortabilityStatus = numberPortabilityStatus;
  }

  public ISDNAddressString getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(ISDNAddressString msisdn) {
    this.msisdn = msisdn;
  }

  public String getMsisdnAddress() {
    return msisdnAddress;
  }

  public void setMsisdnAddress(String msisdnAddress) {
    this.msisdnAddress = msisdnAddress;
  }

  public IMSI getImsi() {
    return imsi;
  }

  public void setImsi(IMSI imsi) {
    this.imsi = imsi;
  }

  public String getImsiData() {
    return imsiData;
  }

  public void setImsiData(String imsiData) {
    this.imsiData = imsiData;
  }

  @Override
  public String toString() {
    return "AtiResponseValues{" +
        "locationInformation=" + locationInformation +
        ", geographicalLatitude=" + geographicalLatitude +
        ", geographicalLongitude=" + geographicalLongitude +
        ", geographicalUncertainty=" + geographicalUncertainty +
        ", typeOfShape=" + typeOfShape +
        ", geodeticLatitude=" + geodeticLatitude +
        ", geodeticLongitude=" + geodeticLongitude +
        ", geodeticUncertainty=" + geodeticUncertainty +
        ", geodeticConfidence=" + geodeticConfidence +
        ", screeningAndPresentationIndicators=" + screeningAndPresentationIndicators +
        ", cellGlobalIdOrServiceAreaIdOrLAI=" + cellGlobalIdOrServiceAreaIdOrLAI +
        ", mcc=" + mcc +
        ", mnc=" + mnc +
        ", lac=" + lac +
        ", ci=" + ci +
        ", ageOfLocationInfo=" + ageOfLocationInfo +
        ", vlrNumber=" + vlrNumber +
        ", mscNumber=" + mscNumber +
        ", locationInformationGPRS=" + locationInformationGPRS +
        ", sgsnNumber=" + sgsnNumber +
        ", lsaIdentity=" + lsaIdentity +
        ", isPLMNSignificantLSA=" + isPLMNSignificantLSA +
        ", raIdentity=" + raIdentity +
        ", locationInformationEPS=" + locationInformationEPS +
        ", mmeName=" + mmeName +
        ", subscriberState=" + subscriberState +
        ", saiPresent=" + saiPresent +
        ", currentLocationRetrieved=" + currentLocationRetrieved +
        ", psSubscriberState=" + psSubscriberState +
        ", imei=" + imei +
        ", msClassmark2=" + msClassmark2 +
        ", gprsmsClass=" + gprsmsClass +
        ", mnpInfoRes=" + mnpInfoRes +
        ", numberPortabilityStatus=" + numberPortabilityStatus +
        ", msisdn=" + msisdn +
        ", msisdnAddress='" + msisdnAddress + '\'' +
        ", imsi=" + imsi +
        ", imsiData='" + imsiData + '\'' +
        ", routeingNumber=" + routeingNumber +
        ", routeingNumberStr='" + routeingNumberStr + '\'' +
        ", locationNumberMap=" + locationNumberMap +
        ", eUtranCgi=" + eUtranCgi +
        ", taId=" + taId +
        '}';
  }
}
