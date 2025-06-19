package org.mobicents.gmlc.slee.cdr;

import net.java.slee.resource.diameter.slg.events.avp.LCSQoSClass;
import net.java.slee.resource.diameter.slg.events.avp.LocationEvent;
import org.joda.time.DateTime;
import org.mobicents.gmlc.slee.diameter.sh.LocalTimeZone;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.EllipsoidPoint;
import org.mobicents.gmlc.slee.primitives.LocationInformation5GS;
import org.mobicents.gmlc.slee.primitives.NRCellGlobalId;
import org.mobicents.gmlc.slee.primitives.Polygon;
import org.mobicents.gmlc.slee.primitives.PolygonImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;

import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSQoS;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationEPS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MNPInfoRes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationNumberMap;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.UUID;

/**
 * Represents state associated with ongoing dialog required for proper CDR generation.
 * Data which should be used for CDR is spread across many objects.
 * So, we need an object which can be used to store them in one convenient place.
 *
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 * @author <a href="mailto:bbaranow@redhat.com"> Bartosz Baranowski </a>
 */
public class GMLCCDRState implements Serializable {

  private static final long serialVersionUID = -1L;

  //public static final String GMLC_STRING_SEPARATOR = "|";

  protected boolean initiated, generated;

  protected String id;

  protected String curlUser;

  protected RecordStatus recordStatus;

  protected Long statusCode;

  // Dialog params
  protected Long localDialogId, remoteDialogId;
  //NB: once we fully update to JDK8, we should revert to using standard java.time package
  protected DateTime dialogStartTime, dialogEndTime;
  protected Long dialogDuration;

  // Circuit-Switched Core Network / SS7 params
  protected AddressString origReference, destReference;

  protected IMSI imsi;
  protected AddressString vlrAddress;

  protected ISDNAddressString isdnAddressString;
  protected SccpAddress localAddress, remoteAddress;

  // Evolved Packet-Switched Core Network / LTE Diameter params
  protected net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginHost;
  protected net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginRealm;
  protected int diameterOriginPort;
  protected net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestHost;
  protected net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestRealm;
  protected int diameterDestPort;
  protected String diameterSessionId;

  // SUPL TLS Socket parameters
  protected InetAddress slpSocketAddress;
  protected int slpSocketPort;
  protected InetAddress setSocketAddress;
  protected int setSocketPort;

  // MAP ATI and PSI response parameters
  protected SubscriberInfo subscriberInfo;
  protected LocationInformation locationInformation;
  protected LocationInformationEPS locationInformationEPS;
  protected LocationInformationGPRS locationInformationGPRS;
  protected Boolean saiPresent;
  protected Boolean currentLocationRetrieved;
  protected MSClassmark2 msClassmark2;
  protected GPRSMSClass gprsmsClass;
  protected TAId taId;
  protected EUTRANCGI eUtranCgi;
  protected LocationNumberMap locationNumberMap;
  protected MNPInfoRes mnpInfoRes;

  // MAP LSM operations exclusive parameters.
  protected LCSClientID lcsClientID;
  protected LMSI lmsi;
  protected ISDNAddressString networkNodeNumber;
  protected Boolean gprsNodeIndicator;
  protected AdditionalNumber additionalNumber;
  protected ISDNAddressString mscNumber, sgsnNumber;
  protected DiameterIdentity mmeName, mmeRealm, sgsnName, sgsnRealm, aaaServerName;
  protected GSNAddress hGmlcAddress, vGmlcAddress, pprAddress;
  protected ExtGeographicalInformation locationEstimate;
  protected TypeOfShape typeOfShape;
  protected Polygon polygon;
  protected Integer numberOfPoints;
  EllipsoidPoint[] polygonEllipsoidPoints;
  protected Polygon additionalPolygon;
  protected Integer additionalNumberOfPoints;
  EllipsoidPoint[] additionalPolygonEllipsoidPoints;
  protected Boolean moLrShortCircuitIndicator;
  protected PositioningDataInformation geranPositioningDataInformation;
  protected UtranPositioningDataInfo utranPositioningDataInfo;
  protected GeranGANSSpositioningData geranGANSSpositioningData;
  protected UtranGANSSpositioningData utranGANSSpositioningData;
  protected Integer ageOfLocationEstimate;
  protected AddGeographicalInformation additionalLocationEstimate;
  protected Boolean deferredMTLRResponseIndicator;
  protected CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
  protected AccuracyFulfilmentIndicator accuracyFulfilmentIndicator;
  protected VelocityEstimate velocityEstimate;
  protected ServingNodeAddress servingNodeAddress;
  protected LCSQoS lcsQoS;
  protected Integer lcsReferenceNumber;
  protected Integer clientReferenceNumber;
  protected Long barometricPressureMeasurement;
  protected String civicAddress;
  protected LCSEvent lcsEvent;
  protected ISDNAddressString msisdn;
  protected IMEI imei;
  protected DeferredmtlrData deferredmtlrData;
  protected Integer lcsServiceTypeID;
  protected Boolean pseudonymIndicator;
  protected Integer sequenceNumber;
  protected PeriodicLDRInfo periodicLDRInfo;
  protected ReportingPLMNList reportingPLMNList;

  // EPC Network / LTE params
  // LTE LCS operations parameters (not analogue to SS7 location services parameters)
  protected String eUTRANPositioningData;
  protected String cellGlobalIdentity;
  protected String utranAdditionalPositioningData;
  protected String serviceAreaIdentity;
  protected Long cellPortionId;
  protected LocationEvent locationEvent;
  protected LCSQoSClass lteLcsQoSClass;
  protected String oneXRTTRCID;

  // 5GS params
  LocationInformation5GS locationInformation5GS;
  protected NRCellGlobalId nrCellGlobalId;
  protected Integer ageOfLocationInformation;
  protected String amfAddress;
  protected String smsfAddress;
  protected PlmnId visitedPlmnId;
  protected LocalTimeZone localTimeZone;
  protected Integer ratType;

  /****************/
  /*** GETTERS ***/
  /**************/

  /**
   * @return the curl user name
   */
  public String getCurlUser() {
    return curlUser;
  }

  /**
   * @return the MAP or Diameter status code
   */
  public Long getStatusCode() {
    return statusCode;
  }

  /**
   * @return the id for MAP
   */
  public String getId() {
    return id;
  }

  /**
   * @return the initiated
   */
  public boolean isInitiated() {
    return initiated;
  }

  /**
   * @return the initiated
   */
  public boolean isInitialized() {
    return this.initiated;
  }

  /**
   * @return the generated
   */
  public boolean isGenerated() {
    return generated;
  }

  /**
   * @return the origReference
   */
  public AddressString getOrigReference() {
    return origReference;
  }

  /**
   * @return the destReference
   */
  public AddressString getDestReference() {
    return destReference;
  }

  /**
   * @return the imsi
   */
  public IMSI getImsi() {
    return imsi;
  }

  /**
   * @return the vlrAddress
   */
  public AddressString getVlrAddress() {
    return vlrAddress;
  }

  /**
   * @return the ISDNAddressString
   */
  public ISDNAddressString getISDNAddressString() {
    return isdnAddressString;
  }

  /**
   * @return the ISDNAddressString
   */
  public ISDNAddressString getIsdnAddressString() {
    return isdnAddressString;
  }

  /**
   * @return the localAddress (GMLC)
   */
  public SccpAddress getLocalAddress() {
    return localAddress;
  }

  /**
   * @return the remoteAddress (Core Network Entity responding address)
   */
  public SccpAddress getRemoteAddress() {
    return remoteAddress;
  }

  /**
   * @return the Diameter session id
   */
  public String getDiameterSessionId() {
    return diameterSessionId;
  }

  /**
   * @return the Diameter Origin Host (EPC Diameter origin host)
   */
  public net.java.slee.resource.diameter.base.events.avp.DiameterIdentity getDiameterOriginHost() {
    return diameterOriginHost;
  }

  /**
   * @return the Diameter Origin Host (EPC Diameter origin realm)
   */
  public net.java.slee.resource.diameter.base.events.avp.DiameterIdentity getDiameterOriginRealm() {
    return diameterOriginRealm;
  }

  /**
   * @return the Diameter Origin Port (EPC Diameter origin port)
   */
  public int getDiameterOriginPort() {
    return diameterOriginPort;
  }

  /**
   * @return the Diameter Destination Host (EPC Diameter destination host)
   */
  public net.java.slee.resource.diameter.base.events.avp.DiameterIdentity getDiameterDestHost() {
    return diameterDestHost;
  }

  /**
   * @return the Diameter Destination Realm (EPC Diameter destination realm)
   */
  public net.java.slee.resource.diameter.base.events.avp.DiameterIdentity getDiameterDestRealm() {
    return diameterDestRealm;
  }

  /**
   * @return the Diameter Destination Port (EPC Diameter destination port)
   */
  public Integer getDiameterDestPort() {
    return diameterDestPort;
  }

  /**
   * @return the SLP socket InetAddress (GMLC)
   */
  public InetAddress getSlpSocketAddress() {
    return slpSocketAddress;
  }

  /**
   * @return the SLP socket port (GMLC)
   */
  public int getSlpSocketPort() {
    return slpSocketPort;
  }

  /**
   * @return the SET socket InetAddress
   */
  public InetAddress getSetSocketAddress() {
    return setSocketAddress;
  }

  /**
   * @return the SET socket port
   */
  public int getSetSocketPort() {
    return setSocketPort;
  }

  /**
   * @return the localDialogId
   */
  public Long getLocalDialogId() {
    return localDialogId;
  }

  /**
   * @return the remoteDialogId
   */
  public Long getRemoteDialogId() {
    return this.remoteDialogId;
  }

  /**
   * @return the dialogStartTime
   */
  public DateTime getDialogStartTime() {
    return this.dialogStartTime;
  }

  /**
   * @return dialogEndTime
   */
  public DateTime getDialogEndTime() {
    return this.dialogEndTime;
  }

  /**
   * @return dialogDuration to set
   */
  public Long getDialogDuration() {
    return this.dialogDuration;
  }

  /**
   * @return the recordStatus
   */
  public RecordStatus getRecordStatus() {
    return recordStatus;
  }

  /**
   * @return the target subscriber MS Classmark from MAP ATI
   */
  public MSClassmark2 getMsClassmark2() {
    return msClassmark2;
  }

  /**
   * @return the target subscriber GPRS MS Class from MAP ATI
   */
  public GPRSMSClass getGprsmsClass() {
    return gprsmsClass;
  }

  /**
   * @return the LCS Client ID
   */
  public LCSClientID getLcsClientID() {
    return lcsClientID;
  }

  /**
   * @return the Cell Global Identity
   */
  public String getCellGlobalIdentity() {
    return cellGlobalIdentity;
  }

  /**
   * @return the Service Area Identity
   */
  public String getServiceAreaIdentity() {
    return serviceAreaIdentity;
  }

  /**
   * @return the LMSI
   */
  public LMSI getLmsi() {
    return lmsi;
  }

  /**
   * @return the Network Node Number
   */
  public ISDNAddressString getNetworkNodeNumber() {
    return networkNodeNumber;
  }

  /**
   * @return GPRS Node Indicator
   */
  public Boolean isGprsNodeIndicator() {
    return gprsNodeIndicator;
  }

  /**
   * @return the Additional Number
   */
  public AdditionalNumber getAdditionalNumber() {
    return additionalNumber;
  }

  /**
   * @return the Additional MSC Number
   */
  public ISDNAddressString getMscNumber() {
    return mscNumber;
  }

  /**
   * @return the Additional MSC Number
   */
  public ISDNAddressString getSgsnNumber() {
    return sgsnNumber;
  }

  /**
   * @return the MME Name
   */
  public DiameterIdentity getMmeRealm() {
    return mmeRealm;
  }

  /**
   * @return the MME Name
   */
  public DiameterIdentity getMmeName() {
    return mmeName;
  }

  /**
   * @return the SGSN Name
   */
  public DiameterIdentity getSgsnName() {
    return sgsnName;
  }

  /**
   * @return the SGSN Realm
   */
  public DiameterIdentity getSgsnRealm() {
    return sgsnRealm;
  }

  /**
   * @return the AAA Server Name
   */
  public DiameterIdentity getAaaServerName() {
    return aaaServerName;
  }

  /**
   * @return the Home GMLC Address
   */
  public GSNAddress gethGmlcAddress() {
    return hGmlcAddress;
  }

  /**
   * @return the Visited GMLC Address
   */
  public GSNAddress getvGmlcAddress() {
    return vGmlcAddress;
  }

  /**
   * @return the PPR Address
   */
  public GSNAddress getPprAddress() {
    return pprAddress;
  }

  /**
   * @return the location estimate
   */
  public ExtGeographicalInformation getLocationEstimate() {
    return locationEstimate;
  }

  /**
   * @return the Polygon of the Location Estimate
   */
  public Polygon getPolygon() {
    return polygon;
  }

  /**
   * @return the number of points of the Polygon
   */
  public Integer getNumberOfPoints() {
    return numberOfPoints;
  }

  /**
   * @return the EllipsoidPoint array of the Polygon
   */
  public EllipsoidPoint[] getPolygonEllipsoidPoints() {
    return polygonEllipsoidPoints;
  }

  /**
   * @return the Polygon of the Additional Location Estimate
   */
  public Polygon getAdditionalPolygon() {
    return additionalPolygon;
  }

  /**
   * @return the number of points of the Polygon of the Additional Location Estimate
   */
  public Integer getAdditionalNumberOfPoints() {
    return additionalNumberOfPoints;
  }

  /**
   * @return the EllipsoidPoint array of the Polygon of the Additional Location Estimate
   */
  public EllipsoidPoint[] getAdditionalPolygonEllipsoidPoints() {
    return additionalPolygonEllipsoidPoints;
  }

  /**
   * @return the MO-LR Short Circuit indicator
   */
  public Boolean isMoLrShortCircuitIndicator() {
    return moLrShortCircuitIndicator;
  }

  /**
   * @return the GERAN positioning Data info
   */
  public PositioningDataInformation getGeranPositioningDataInformation() {
    return geranPositioningDataInformation;
  }

  /**
   * @return the UTRAN positioning data info
   */
  public UtranPositioningDataInfo getUtranPositioningDataInfo() {
    return utranPositioningDataInfo;
  }

  /**
   * @return the GERAN GANSS positioning data info
   */
  public GeranGANSSpositioningData getGeranGANSSpositioningData() {
    return geranGANSSpositioningData;
  }

  /**
   * @return the UTRAN GANSS positioning data info
   */
  public UtranGANSSpositioningData getUtranGANSSpositioningData() {
    return utranGANSSpositioningData;
  }

  /**
   * @return the age of location estimate
   */
  public Integer getAgeOfLocationEstimate() {
    return ageOfLocationEstimate;
  }

  /**
   * @return the additional location estimate
   */
  public AddGeographicalInformation getAdditionalLocationEstimate() {
    return additionalLocationEstimate;
  }

  /**
   * @return the deferred MT LR response indicator
   */
  public Boolean isDeferredMTLRResponseIndicator() {
    return deferredMTLRResponseIndicator;
  }

  /**
   * @return the CGI or SAI or LAI
   */
  public CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI() {
    return cellGlobalIdOrServiceAreaIdOrLAI;
  }

  /**
   * @return the accuracy fulfillment indicator
   */
  public AccuracyFulfilmentIndicator getAccuracyFulfilmentIndicator() {
    return accuracyFulfilmentIndicator;
  }

  /**
   * @return the velocity estimate
   */
  public VelocityEstimate getVelocityEstimate() {
    return velocityEstimate;
  }

  /**
   * @return the serving node address
   */
  public ServingNodeAddress getServingNodeAddress() {
    return servingNodeAddress;
  }

  /**
   * @return the LCS QoS
   */
  public LCSQoS getLcsQoS() {
    return lcsQoS;
  }

  /**
   * @return the LCS reference number
   */
  public Integer getLcsReferenceNumber() {
    return lcsReferenceNumber;
  }

  /**
   * @return the client LCS reference number
   */
  public Integer getClientReferenceNumber() {
    return clientReferenceNumber;
  }

  /**
   * @return the barometric pressure
   */
  public Long getBarometricPressureMeasurement() {
    return barometricPressureMeasurement;
  }

  /**
   * @return the civic address
   */
  public String getCivicAddress() {
    return civicAddress;
  }

  /**
   * @return the LCS event (MAP)
   */
  public LCSEvent getLcsEvent() {
    return lcsEvent;
  }

  /**
   * @return the LCS event (LTE)
   */
  public LocationEvent getLocationEvent() {
    return locationEvent;
  }

  /**
   * @return the MSISDN
   */
  public ISDNAddressString getMsisdn() {
    return msisdn;
  }

  /**
   * @return the IMEI
   */
  public IMEI getImei() {
    return imei;
  }

  /**
   * @return the deferred MT LR data
   */
  public DeferredmtlrData getDeferredmtlrData() {
    return deferredmtlrData;
  }

  /**
   * @return the LCS Service type ID
   */
  public Integer getLcsServiceTypeID() {
    return lcsServiceTypeID;
  }

  /**
   * @return the pseudonym indicator
   */
  public Boolean isPseudonymIndicator() {
    return pseudonymIndicator;
  }

  /**
   * @return the location report sequence number
   */
  public Integer getSequenceNumber() {
    return sequenceNumber;
  }

  /**
   * @return the periodic LDR info
   */
  public PeriodicLDRInfo getPeriodicLDRInfo() {
    return periodicLDRInfo;
  }

  /**
   * @return the reporting PLMN list
   */
  public ReportingPLMNList getReportingPLMNList() {
    return reportingPLMNList;
  }

  /**
   * @return the subscriber info
   */
  public SubscriberInfo getSubscriberInfo() {
    return subscriberInfo;
  }

  /**
   * @return the subscriber's location info
   */
  public LocationInformation getLocationInformation() {
    return locationInformation;
  }

  /**
   * @return the subscriber's EPS location info
   */
  public LocationInformationEPS getLocationInformationEPS() {
    return locationInformationEPS;
  }

  /**
   * @return the subscriber's location info GPRS
   */
  public LocationInformationGPRS getLocationInformationGPRS() {
    return locationInformationGPRS;
  }

  /**
   * @return the subscriber's geodetic or geographical type of shape
   */
  public TypeOfShape getTypeOfShape() {
    return typeOfShape;
  }

  /**
   * @return the subscriber's MNP info result
   */
  public MNPInfoRes getMnpInfoRes() {
    return mnpInfoRes;
  }

  /**
   * @return if SAI is present
   */
  public Boolean isPsiSaiPresent() {
    return saiPresent;
  }

  /**
   * @return if current location is retrieved
   */
  public Boolean isCurrentLocationRetrieved() {
    return currentLocationRetrieved;
  }

  /**
   * @return the subscriber's location Tracking Area Id
   */
  public TAId getTaId() {
    return taId;
  }

  /**
   * @return the subscriber's E-UTRAN Cell Id
   */
  public EUTRANCGI getEUtranCgi() {
    return eUtranCgi;
  }

  /**
   * @return the subscriber's E-UTRAN Cell Portion Id
   */
  public Long getCellPortionId() {
    return cellPortionId;
  }

  /**
   * @return the subscriber's information Location Number
   */
  public LocationNumberMap getLocationNumberMap() {
    return locationNumberMap;
  }

  /**
   * @return the subscriber's EUTRAN positioning data
   */
  public String geteUTRANPositioningData() {
    return eUTRANPositioningData;
  }

  /**
   * @return the subscriber's UTRAN Additional positioning data
   */
  public String getUtranAdditionalPositioningData() {
    return utranAdditionalPositioningData;
  }

  /**
   * @return the LCS QoS Class
   */
  public LCSQoSClass getLteLcsQoSClass() {
    return lteLcsQoSClass;
  }

  /**
   * @return the oneXRTTRCID
   */
  public String getOneXRTTRCID() {
    return oneXRTTRCID;
  }

  /**
   * @return the LocationInformation5GS
   */
  public LocationInformation5GS getLocationInformation5GS() {
    return locationInformation5GS;
  }

  /**
   * @return the 5G NR CellGlobalId
   */
  public NRCellGlobalId getNrCellGlobalId() {
    return nrCellGlobalId;
  }

  /**
   * @return the age of Location Information
   */
  public Integer getAgeOfLocationInformation() {
    return ageOfLocationInformation;
  }

  /**
   * @return the 5G AMF Address
   */
  public String getAmfAddress() {
    return amfAddress;
  }

  /**
   * @return the 5G SMSF Address
   */
  public String getSmsfAddress() {
    return smsfAddress;
  }

  /**
   * @return the visited PLMN Id
   */
  public PlmnId getVisitedPlmnId() {
    return visitedPlmnId;
  }

  /**
   * @return the Local Time Zone
   */
  public LocalTimeZone getLocalTimeZone() {
    return localTimeZone;
  }

  /**
   * @return the RAT Type
   */
  public Integer getRatType() {
    return ratType;
  }

  /****************/
  /*** SETTERS ***/
  /**************/

  /**
   * @param curlUser the id to set
   */
  public void setCurlUser(String curlUser) {
    this.curlUser = curlUser;
  }

  /**
   * @param statusCode the MAP or Diameter status code to set
   */
  public void setStatusCode(Long statusCode) {
    this.statusCode = statusCode;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @param initiated the initiated to set
   */
  public void setInitiated(boolean initiated) {
    this.initiated = initiated;
  }

  /**
   * @param generated to set
   */
  public void setGenerated(boolean generated) {
    this.generated = generated;
  }

  /**
   * @param origReference the origReference to set
   */
  public void setOrigReference(AddressString origReference) {
    this.origReference = origReference;
  }

  /**
   * @param destReference the destReference to set
   */
  public void setDestReference(AddressString destReference) {
    this.destReference = destReference;
  }

  /**
   * @param imsi the IMSI to set
   */
  public void setImsi(IMSI imsi) {
    this.imsi = imsi;
  }

  /**
   * @param vlrAddress the VLR Address to set
   */
  public void setVlrAddress(AddressString vlrAddress) {
    this.vlrAddress = vlrAddress;
  }

  /**
   * @param iSDNString the ISDNString to set
   */
  public void setISDNAddressString(ISDNAddressString iSDNString) {
    isdnAddressString = iSDNString;
  }

  /**
   * @param isdnAddressString the ISDNString to set
   */
  public void setIsdnAddressString(ISDNAddressString isdnAddressString) {
    this.isdnAddressString = isdnAddressString;
  }

  /**
   * @param localAddress the localAddress to set
   */
  public void setLocalAddress(SccpAddress localAddress) {
    this.localAddress = localAddress;
  }

  /**
   * @param remoteAddress the remoteAddress to set
   */
  public void setRemoteAddress(SccpAddress remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  /**
   * @param diameterSessionId the Diameter session Id to set
   */
  public void setDiameterSessionId(String diameterSessionId) {
    this.diameterSessionId = diameterSessionId;
  }

  /**
   * @param diameterOriginHost the Diameter origin host to set
   */
  public void setDiameterOriginHost(net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginHost) {
    this.diameterOriginHost = diameterOriginHost;
  }

  /**
   * @param diameterOriginRealm the Diameter origin realm to set
   */
  public void setDiameterOriginRealm(net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginRealm) {
    this.diameterOriginRealm = diameterOriginRealm;
  }

  /**
   * @param diameterOriginPort the Diameter origin port to set
   */
  public void setDiameterOriginPort(int diameterOriginPort) {
    this.diameterOriginPort = diameterOriginPort;
  }

  /**
   * @param diameterDestHost the Diameter destination host to set
   */
  public void setDiameterDestHost(net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestHost) {
    this.diameterDestHost = diameterDestHost;
  }

  /**
   * @param diameterDestRealm the Diameter destination realm to set
   */
  public void setDiameterDestRealm(net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestRealm) {
    this.diameterDestRealm = diameterDestRealm;
  }

  /**
   * @param diameterDestPort the Diameter destination port to set
   */
  public void setDiameterDestPort(int diameterDestPort) {
    this.diameterDestPort = diameterDestPort;
  }

  /**
   * @param slpSocketAddress the SLP (GMLC) socket InetAddress to set
   */
  public void setSlpSocketAddress(InetAddress slpSocketAddress) {
    this.slpSocketAddress = slpSocketAddress;
  }

  /**
   * @param slpSocketPort the SLP (GMLC) socket port to set
   */
  public void setSlpSocketPort(int slpSocketPort) {
    this.slpSocketPort = slpSocketPort;
  }

  /**
   * @param setSocketAddress the SET socket InetAddress to set
   */
  public void setSetSocketAddress(InetAddress setSocketAddress) {
    this.setSocketAddress = setSocketAddress;
  }

  /**
   * @param setSocketPort the SET socket port to set
   */
  public void setSetSocketPort(int setSocketPort) {
    this.setSocketPort = setSocketPort;
  }

  /**
   * @param localDialogId the localDialogId to set
   */
  public void setLocalDialogId(Long localDialogId) {
    this.localDialogId = localDialogId;
  }

  /**
   * @param remoteDialogId to set
   */
  public void setRemoteDialogId(Long remoteDialogId) {
    this.remoteDialogId = remoteDialogId;
  }

  /**
   * @param dialogStartTime to set
   */
  public void setDialogStartTime(DateTime dialogStartTime) {
    this.dialogStartTime = dialogStartTime;
  }

  /**
   * @param dialogEndTime to set
   */
  public void setDialogEndTime(DateTime dialogEndTime) {
    this.dialogEndTime = dialogEndTime;
  }

  /**
   * @param dialogDuration to set
   */
  public void setDialogDuration(Long dialogDuration) {
    this.dialogDuration = dialogDuration;
  }

  /**
   * @param recordStatus the recordStatus to set
   */
  public void setRecordStatus(RecordStatus recordStatus) {
    this.recordStatus = recordStatus;
  }

  /**
   * @param cellGlobalIdentity to set
   */
  public void setCellGlobalIdentity(String cellGlobalIdentity) {
    cellGlobalIdentity = cellGlobalIdentity;
  }

  /**
   * @param serviceAreaIdentity to set
   */
  public void setServiceAreaIdentity(String serviceAreaIdentity) {
    this.serviceAreaIdentity = serviceAreaIdentity;
  }

  /**
   * @param msClassmark2 to set
   */
  public void setMsClassmark2(MSClassmark2 msClassmark2) {
    this.msClassmark2 = msClassmark2;
  }

  /**
   * @param gprsmsClass to set
   */
  public void setGprsmsClass(GPRSMSClass gprsmsClass) {
    this.gprsmsClass = gprsmsClass;
  }

  /**
   * @param lcsClientID to set
   */
  public void setLcsClientID(LCSClientID lcsClientID) {
    this.lcsClientID = lcsClientID;
  }

  /**
   * @param lmsi to set
   */
  public void setLmsi(LMSI lmsi) {
    this.lmsi = lmsi;
  }

  /**
   * @param networkNodeNumber to set
   */
  public void setNetworkNodeNumber(ISDNAddressString networkNodeNumber) {
    this.networkNodeNumber = networkNodeNumber;
  }

  /**
   * @param gprsNodeIndicator to set
   */
  public void setGprsNodeIndicator(Boolean gprsNodeIndicator) {
    this.gprsNodeIndicator = gprsNodeIndicator;
  }

  /**
   * @param additionalNumber to set
   */
  public void setAdditionalNumber(AdditionalNumber additionalNumber) {
    this.additionalNumber = additionalNumber;
  }

  /**
   * @param additionalMscNumber to set
   */
  public void setMscNumber(ISDNAddressString additionalMscNumber) {
    this.mscNumber = mscNumber;
  }

  /**
   * @param additionalSgsnNumber to set
   */
  public void setSgsnNumber(ISDNAddressString additionalSgsnNumber) {
    this.sgsnNumber = sgsnNumber;
  }

  /**
   * @param mmeRealm to set
   */
  public void setMmeRealm(DiameterIdentity mmeRealm) {
    this.mmeRealm = mmeRealm;
  }

  /**
   * @param mmeName to set
   */
  public void setMmeName(DiameterIdentity mmeName) {
    this.mmeName = mmeName;
  }

  /**
   * @param sgsnName to set
   */
  public void setSgsnName(DiameterIdentity sgsnName) {
    this.sgsnName = sgsnName;
  }

  /**
   * @param sgsnRealm to set
   */
  public void setSgsnRealm(DiameterIdentity sgsnRealm) {
    this.sgsnRealm = sgsnRealm;
  }

  /**
   * @param aaaServerName to set
   */
  public void setAaaServerName(DiameterIdentity aaaServerName) {
    this.aaaServerName = aaaServerName;
  }

  /**
   * @param hGmlcAddress to set
   */
  public void sethGmlcAddress(GSNAddress hGmlcAddress) {
    this.hGmlcAddress = hGmlcAddress;
  }

  /**
   * @param vGmlcAddress to set
   */
  public void setvGmlcAddress(GSNAddress vGmlcAddress) {
    this.vGmlcAddress = vGmlcAddress;
  }

  /**
   * @param pprAddress to set
   */
  public void setPprAddress(GSNAddress pprAddress) {
    this.pprAddress = pprAddress;
  }

  /**
   * @param locationEstimate to set
   */
  public void setLocationEstimate(ExtGeographicalInformation locationEstimate) {
    this.locationEstimate = locationEstimate;
    // setup for polygon type of shape case
    if (locationEstimate != null) {
      if (locationEstimate.getTypeOfShape() == TypeOfShape.Polygon) {
        this.typeOfShape = TypeOfShape.Polygon;
      }
    }
  }

  /**
   * @param polygon to set
   */
  public void setPolygon(Polygon polygon) {
    this.polygon = polygon;
  }

  /**
   * @param numberOfPoints to set
   */
  public void setNumberOfPoints(Integer numberOfPoints) {
    this.numberOfPoints = numberOfPoints;
  }

  /**
   * @param polygonEllipsoidPoints to set
   */
  public void setPolygonEllipsoidPoints(EllipsoidPoint[] polygonEllipsoidPoints) {
    this.polygonEllipsoidPoints = polygonEllipsoidPoints;
  }

  /**
   * @param additionalPolygon to set
   */
  public void setAdditionalPolygon(Polygon additionalPolygon) {
    this.additionalPolygon = additionalPolygon;
  }

  /**
   * @param additionalNumberOfPoints to set
   */
  public void setAdditionalNumberOfPoints(Integer additionalNumberOfPoints) {
    this.additionalNumberOfPoints = additionalNumberOfPoints;
  }

  /**
   * @param additionalPolygonEllipsoidPoints to set
   */
  public void setAdditionalPolygonEllipsoidPoints(EllipsoidPoint[] additionalPolygonEllipsoidPoints) {
    this.additionalPolygonEllipsoidPoints = additionalPolygonEllipsoidPoints;
  }

  /**
   * @param moLrShortCircuitIndicator to set
   */
  public void setMoLrShortCircuitIndicator(Boolean moLrShortCircuitIndicator) {
    this.moLrShortCircuitIndicator = moLrShortCircuitIndicator;
  }

  /**
   * @param geranPositioningDataInformation to set
   */
  public void setGeranPositioningDataInformation(PositioningDataInformation geranPositioningDataInformation) {
    this.geranPositioningDataInformation = geranPositioningDataInformation;
  }

  /**
   * @param utranPositioningDataInfo to set
   */
  public void setUtranPositioningDataInfo(UtranPositioningDataInfo utranPositioningDataInfo) {
    this.utranPositioningDataInfo = utranPositioningDataInfo;
  }

  /**
   * @param geranGANSSpositioningData to set
   */
  public void setGeranGANSSpositioningData(GeranGANSSpositioningData geranGANSSpositioningData) {
    this.geranGANSSpositioningData = geranGANSSpositioningData;
  }

  /**
   * @param utranGANSSpositioningData to set
   */
  public void setUtranGANSSpositioningData(UtranGANSSpositioningData utranGANSSpositioningData) {
    this.utranGANSSpositioningData = utranGANSSpositioningData;
  }

  /**
   * @param ageOfLocationEstimate to set
   */
  public void setAgeOfLocationEstimate(Integer ageOfLocationEstimate) {
    this.ageOfLocationEstimate = ageOfLocationEstimate;
  }

  /**
   * @param additionalLocationEstimate to set
   */
  public void setAdditionalLocationEstimate(AddGeographicalInformation additionalLocationEstimate) {
    this.additionalLocationEstimate = additionalLocationEstimate;
    // setup for polygon type of shape case
    if (additionalLocationEstimate != null) {
      if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.Polygon) {
        this.additionalPolygon = new PolygonImpl(this.additionalLocationEstimate.getData());
        this.additionalNumberOfPoints = additionalPolygon.getNumberOfPoints();
        this.additionalPolygonEllipsoidPoints = new EllipsoidPoint[this.additionalNumberOfPoints];
        for (int point = 0; point < this.additionalNumberOfPoints; point++) {
          this.additionalPolygonEllipsoidPoints[point] = this.additionalPolygon.getEllipsoidPoint(point);
        }
        try {
          ((PolygonImpl) this.additionalPolygon).setData(this.additionalPolygonEllipsoidPoints);
        } catch (MAPException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * @param deferredMTLRResponseIndicator to set
   */
  public void setDeferredMTLRResponseIndicator(Boolean deferredMTLRResponseIndicator) {
    this.deferredMTLRResponseIndicator = deferredMTLRResponseIndicator;
  }

  /**
   * @param cellGlobalIdOrServiceAreaIdOrLAI to set
   */
  public void setCellGlobalIdOrServiceAreaIdOrLAI(CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI) {
    this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
  }

  /**
   * @param accuracyFulfilmentIndicator to set
   */
  public void setAccuracyFulfilmentIndicator(AccuracyFulfilmentIndicator accuracyFulfilmentIndicator) {
    this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
  }

  /**
   * @param velocityEstimate to set
   */
  public void setVelocityEstimate(VelocityEstimate velocityEstimate) {
    this.velocityEstimate = velocityEstimate;
  }

  /**
   * @param servingNodeAddress to set
   */
  public void setServingNodeAddress(ServingNodeAddress servingNodeAddress) {
    this.servingNodeAddress = servingNodeAddress;
  }

  /**
   * @param lcsQoS to set
   */
  public void setLcsQoS(LCSQoS lcsQoS) {
    this.lcsQoS = lcsQoS;
  }

  /**
   * @param lcsReferenceNumber to set
   */
  public void setLcsReferenceNumber(Integer lcsReferenceNumber) {
    this.lcsReferenceNumber = lcsReferenceNumber;
  }

  /**
   * @param clientReferenceNumber to set
   */
  public void setClientReferenceNumber(Integer clientReferenceNumber) {
    this.clientReferenceNumber = clientReferenceNumber;
  }

  /**
   * @param barometricPressureMeasurement to set
   */
  public void setBarometricPressureMeasurement(Long barometricPressureMeasurement) {
    this.barometricPressureMeasurement = barometricPressureMeasurement;
  }

  /**
   * @param civicAddress to set
   */
  public void setCivicAddress(String civicAddress) {
    this.civicAddress = civicAddress;
  }

  /**
   * @param lcsEvent to set
   */
  public void setLcsEvent(LCSEvent lcsEvent) {
    this.lcsEvent = lcsEvent;
  }

  /**
   * @param locationEvent to set
   */
  public void setLocationEvent(LocationEvent locationEvent) {
    this.locationEvent = locationEvent;
  }

  /**
   * @param msisdn to set
   */
  public void setMsisdn(ISDNAddressString msisdn) {
    this.msisdn = msisdn;
  }

  /**
   * @param imei to set
   */
  public void setImei(IMEI imei) {
    this.imei = imei;
  }

  /**
   * @param deferredmtlrData to set
   */
  public void setDeferredmtlrData(DeferredmtlrData deferredmtlrData) {
    this.deferredmtlrData = deferredmtlrData;
  }

  /**
   * @param lcsServiceTypeID to set
   */
  public void setLcsServiceTypeID(Integer lcsServiceTypeID) {
    this.lcsServiceTypeID = lcsServiceTypeID;
  }

  /**
   * @param pseudonymIndicator to set
   */
  public void setPseudonymIndicator(Boolean pseudonymIndicator) {
    this.pseudonymIndicator = pseudonymIndicator;
  }

  /**
   * @param sequenceNumber to set
   */
  public void setSequenceNumber(Integer sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  /**
   * @param periodicLDRInfo to set
   */
  public void setPeriodicLDRInfo(PeriodicLDRInfo periodicLDRInfo) {
    this.periodicLDRInfo = periodicLDRInfo;
  }

  /**
   * @param reportingPLMNList to set
   */
  public void setReportingPLMNList(ReportingPLMNList reportingPLMNList) {
    this.reportingPLMNList = reportingPLMNList;
  }

  /**
   * @param subscriberInfo to set
   */
  public void setSubscriberInfo(SubscriberInfo subscriberInfo) {
    this.subscriberInfo = subscriberInfo;
  }

  /**
   * @param locationInformation to set
   */
  public void setLocationInformation(LocationInformation locationInformation) {
    this.locationInformation = locationInformation;
  }

  /**
   * @param locationInformationEPS to set
   */
  public void setLocationInformationEPS(LocationInformationEPS locationInformationEPS) {
    this.locationInformationEPS = locationInformationEPS;
  }

  /**
   * @param locationInformationGPRS to set
   */
  public void setLocationInformationGPRS(LocationInformationGPRS locationInformationGPRS) {
    this.locationInformationGPRS = locationInformationGPRS;
  }

  /**
   * @param typeOfShape subscriber's location information to set
   */
  public void setTypeOfShape(TypeOfShape typeOfShape) {
    this.typeOfShape = typeOfShape;
  }

  /**
   * @param saiPresent subscriber's location information to set
   */
  public void setSaiPresent(Boolean saiPresent) {
    this.saiPresent = saiPresent;
  }

  public void setCurrentLocationRetrieved(Boolean currentLocationRetrieved) {
    this.currentLocationRetrieved = currentLocationRetrieved;
  }

  /**
   * @param mnpInfoRes subscriber's location information to set
   */
  public void setMnpInfoRes(MNPInfoRes mnpInfoRes) {
    this.mnpInfoRes = mnpInfoRes;
  }

  /**
   * @param taId subscriber's Tracking Area Id to set
   */
  public void setTaId(TAId taId) {
    this.taId = taId;
  }

  /**
   * @param eUtranCgi subscriber's E-UTRAN Cell Id  to set
   */
  public void setEUtranCgi(EUTRANCGI eUtranCgi) {
    this.eUtranCgi = eUtranCgi;
  }

  /**
   * @param cellPortionId subscriber's E-UTRAN Cell Id  to set
   */
  public void setCellPortionId(Long cellPortionId) {
    this.cellPortionId = cellPortionId;
  }

  /**
   * @param locationNumberMap subscriber's E-UTRAN Cell Id  to set
   */
  public void setLocationNumberMap(LocationNumberMap locationNumberMap) {
    this.locationNumberMap = locationNumberMap;
  }

  /**
   * @param eUTRANPositioningData subscriber's UTRAN positioning data to set
   */
  public void seteUTRANPositioningData(String eUTRANPositioningData) {
    this.eUTRANPositioningData = eUTRANPositioningData;
  }

  /**
   * @param utranAdditionalPositioningData subscriber's UTRAN additional positioning data to set
   */
  public void setUtranAdditionalPositioningData(String utranAdditionalPositioningData) {
    this.utranAdditionalPositioningData = utranAdditionalPositioningData;
  }

  /**
   * @param lteLcsQoSClass LCS QoS Class to set
   */
  public void setLteLcsQoSClass(LCSQoSClass lteLcsQoSClass) {
    this.lteLcsQoSClass = lteLcsQoSClass;
  }

  /**
   * @param oneXRTTRCID LCS QoS Class to set
   */
  public void setOneXRTTRCID(String oneXRTTRCID) {
    this.oneXRTTRCID = oneXRTTRCID;
  }

  /**
   * @param locationInformation5GS 5GS Location Information to set
   */
  public void setLocationInformation5GS(LocationInformation5GS locationInformation5GS) {
    this.locationInformation5GS = locationInformation5GS;
    if (locationInformation5GS != null) {
      this.nrCellGlobalId = locationInformation5GS.getNRCellGlobalIdentity();
      this.eUtranCgi = locationInformation5GS.getEUtranCellGlobalIdentity();
      this.taId = locationInformation5GS.getTrackingAreaIdentity();
      if (locationInformation5GS.getGeographicalInformation() != null) {
        this.typeOfShape = locationInformation5GS.getGeographicalInformation().getTypeOfShape();
      }
      this.amfAddress = locationInformation5GS.getAMFAddress();
      this.smsfAddress = locationInformation5GS.getSMSFAddress();
      this.currentLocationRetrieved = locationInformation5GS.getCurrentLocationRetrieved();
      this.ageOfLocationInformation = locationInformation5GS.getAgeOfLocationInformation();
      this.visitedPlmnId = locationInformation5GS.getVisitedPlmnId();
      LocalTimeZone sh5GSLocalTimeZone = new LocalTimeZone();
      sh5GSLocalTimeZone.setTimeZone(locationInformation5GS.getTimeZone());
      sh5GSLocalTimeZone.setDaylightSavingTime(locationInformation5GS.getDaylightSavingTime());
      this.localTimeZone = sh5GSLocalTimeZone;
      this.ratType = locationInformation5GS.getRatType();
    }
  }

  /**
   * @param nrCellGlobalId 5GS CGI to set
   */
  public void setNrCellGlobalId(NRCellGlobalId nrCellGlobalId) {
    this.nrCellGlobalId = nrCellGlobalId;
  }

  /**
   * @param ageOfLocationInformation 5GS Age of Location Information to set
   */
  public void setAgeOfLocationInformation(Integer ageOfLocationInformation) {
    this.ageOfLocationInformation = ageOfLocationInformation;
  }

  /**
   * @param amfAddress 5GS AMF Address to set
   */
  public void setAmfAddress(String amfAddress) {
    this.amfAddress = amfAddress;
  }

  /**
   * @param smsfAddress 5GS SMSF Address to set
   */
  public void setSmsfAddress(String smsfAddress) {
    this.smsfAddress = smsfAddress;
  }

  /**
   * @param visitedPlmnId visited PLMN ID to set
   */
  public void setVisitedPlmnId(PlmnId visitedPlmnId) {
    this.visitedPlmnId = visitedPlmnId;
  }

  /**
   * @param localTimeZone time zone to set
   */
  public void setLocalTimeZone(LocalTimeZone localTimeZone) {
    this.localTimeZone = localTimeZone;
  }

  /**
   * @param ratType RAT type to set
   */
  public void setRatType(Integer ratType) {
    this.ratType = ratType;
  }

  /*********************/
  /*** CONSTRUCTORS ***/
  /*******************/

  public GMLCCDRState() {
    super();
  }

  /*******************/
  /*** INITIATORS ***/
  /*****************/

  public void init(final Long dialogId, final AddressString destRef, final AddressString origRef, final ISDNAddressString isdnAddressString,
                   final SccpAddress localAddress, final SccpAddress remoteAddress) {
    this.localDialogId = dialogId;
    this.destReference = destRef;
    this.origReference = origRef;
    this.isdnAddressString = isdnAddressString;
    this.localAddress = localAddress;
    this.remoteAddress = remoteAddress;
    // This should be enough to be unique
    this.id = UUID.randomUUID().toString();
    this.initiated = true;
    this.dialogStartTime = null;
    this.dialogEndTime = null;
    this.dialogDuration = null;
  }

  public void init(String id, net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestRealm,
                   net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestHost, Integer diameterDestPort) {
    this.id = UUID.randomUUID().toString();
    this.initiated = true;
    this.dialogStartTime = null;
    this.dialogEndTime = null;
    this.dialogDuration = null;
    this.diameterSessionId = id;
    this.diameterDestRealm = diameterDestRealm;
    this.diameterDestHost = diameterDestHost;
    this.diameterDestPort = diameterDestPort;
  }

  public void init(String diameterSessionId, net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginHost,
                   net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginRealm,
                   net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestHost,
                   net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestRealm) {
    this.id = UUID.randomUUID().toString();
    this.initiated = true;
    this.dialogStartTime = null;
    this.dialogEndTime = null;
    this.dialogDuration = null;
    this.diameterSessionId = diameterSessionId;
    this.diameterOriginHost = diameterOriginHost;
    this.diameterOriginRealm = diameterOriginRealm;
    this.diameterDestHost = diameterDestHost;
    this.diameterDestRealm = diameterDestRealm;
  }

  public void init(InetAddress slpSocketAddress, int slpSocketPort, InetAddress setSocketAddress, int setSocketPort) {
    this.id = UUID.randomUUID().toString();
    this.initiated = true;
    this.dialogStartTime = null;
    this.dialogEndTime = null;
    this.dialogDuration = null;
    this.slpSocketAddress = slpSocketAddress;
    this.slpSocketPort = slpSocketPort;
    this.setSocketAddress = setSocketAddress;
    this.setSocketPort = setSocketPort;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((isdnAddressString == null) ? 0 : isdnAddressString.hashCode());
    result = prime * result + ((destReference == null) ? 0 : destReference.hashCode());
    result = prime * result + ((diameterOriginHost == null) ? 0 : diameterDestHost.hashCode());
    result = prime * result + ((diameterOriginRealm == null) ? 0 : diameterOriginRealm.hashCode());
    result = prime * result + ((diameterDestHost == null) ? 0 : diameterDestHost.hashCode());
    result = prime * result + ((diameterDestRealm == null) ? 0 : diameterDestRealm.hashCode());
    result = prime * result + ((diameterSessionId == null) ? 0 : diameterSessionId.hashCode());
    result = prime * result + ((slpSocketAddress == null) ? 0 : slpSocketAddress.hashCode());
    result = prime * result + ((setSocketAddress == null) ? 0 : setSocketAddress.hashCode());
    result = prime * result + ((localDialogId == null) ? 0 : localDialogId.hashCode());
    result = prime * result + ((remoteDialogId == null) ? 0 : remoteDialogId.hashCode());
    result = prime * result + (generated ? 1231 : 1237);
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + (initiated ? 1231 : 1237);
    result = prime * result + ((localAddress == null) ? 0 : localAddress.hashCode());
    result = prime * result + ((origReference == null) ? 0 : origReference.hashCode());
    result = prime * result + ((recordStatus == null) ? 0 : recordStatus.hashCode());
    result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
    result = prime * result + ((subscriberInfo == null) ? 0 : subscriberInfo.hashCode());
    result = prime * result + ((locationInformation == null) ? 0 : locationInformation.hashCode());
    result = prime * result + ((locationInformationEPS == null) ? 0 : locationInformationEPS.hashCode());
    result = prime * result + ((locationInformationGPRS == null) ? 0 : locationInformationGPRS.hashCode());
    result = prime * result + ((locationInformation5GS == null) ? 0 : locationInformation5GS.hashCode());
    result = prime * result + ((locationNumberMap == null) ? 0 : locationNumberMap.hashCode());
    result = prime * result + ((mnpInfoRes == null) ? 0 : mnpInfoRes.hashCode());
    result = prime * result + ((msClassmark2 == null) ? 0 : msClassmark2.hashCode());
    result = prime * result + ((gprsmsClass == null) ? 0 : gprsmsClass.hashCode());
    result = prime * result + ((eUtranCgi == null) ? 0 : eUtranCgi.hashCode());
    result = prime * result + ((taId == null) ? 0 : taId.hashCode());
    result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
    result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
    result = prime * result + ((lmsi == null) ? 0 : lmsi.hashCode());
    result = prime * result + ((imei == null) ? 0 : imei.hashCode());
    result = prime * result + ((lcsClientID == null) ? 0 : lcsClientID.hashCode());
    result = prime * result + ((networkNodeNumber == null) ? 0 : networkNodeNumber.hashCode());
    result = prime * result + ((additionalNumber == null) ? 0 : additionalNumber.hashCode());
    result = prime * result + ((vlrAddress == null) ? 0 : vlrAddress.hashCode());
    result = prime * result + ((mmeName == null) ? 0 : mmeName.hashCode());
    result = prime * result + ((mmeRealm == null) ? 0 : mmeRealm.hashCode());
    result = prime * result + ((sgsnName == null) ? 0 : sgsnName.hashCode());
    result = prime * result + ((sgsnRealm == null) ? 0 : sgsnRealm.hashCode());
    result = prime * result + ((aaaServerName == null) ? 0 : aaaServerName.hashCode());
    result = prime * result + ((hGmlcAddress == null) ? 0 : hGmlcAddress.hashCode());
    result = prime * result + ((vGmlcAddress == null) ? 0 : vGmlcAddress.hashCode());
    result = prime * result + ((pprAddress == null) ? 0 : pprAddress.hashCode());
    result = prime * result + ((locationEstimate == null) ? 0 : locationEstimate.hashCode());
    result = prime * result + ((geranPositioningDataInformation == null) ? 0 : geranPositioningDataInformation.hashCode());
    result = prime * result + ((utranPositioningDataInfo == null) ? 0 : utranPositioningDataInfo.hashCode());
    result = prime * result + ((geranGANSSpositioningData == null) ? 0 : geranGANSSpositioningData.hashCode());
    result = prime * result + ((utranGANSSpositioningData == null) ? 0 : utranGANSSpositioningData.hashCode());
    result = prime * result + ((additionalLocationEstimate == null) ? 0 : additionalLocationEstimate.hashCode());
    result = prime * result + ((cellGlobalIdOrServiceAreaIdOrLAI == null) ? 0 : cellGlobalIdOrServiceAreaIdOrLAI.hashCode());
    result = prime * result + ((accuracyFulfilmentIndicator == null) ? 0 : accuracyFulfilmentIndicator.hashCode());
    result = prime * result + ((velocityEstimate == null) ? 0 : velocityEstimate.hashCode());
    result = prime * result + ((servingNodeAddress == null) ? 0 : servingNodeAddress.hashCode());
    result = prime * result + ((lcsQoS == null) ? 0 : lcsQoS.hashCode());
    result = prime * result + ((lteLcsQoSClass == null) ? 0 : lteLcsQoSClass.hashCode());
    result = prime * result + ((barometricPressureMeasurement == null) ? 0 : barometricPressureMeasurement.hashCode());
    result = prime * result + ((civicAddress == null) ? 0 : civicAddress.hashCode());
    result = prime * result + ((lcsEvent == null) ? 0 : lcsEvent.hashCode());
    result = prime * result + ((locationEvent == null) ? 0 : locationEvent.hashCode());
    result = prime * result + ((deferredmtlrData == null) ? 0 : deferredmtlrData.hashCode());
    result = prime * result + ((periodicLDRInfo == null) ? 0 : periodicLDRInfo.hashCode());
    result = prime * result + ((reportingPLMNList == null) ? 0 : reportingPLMNList.hashCode());
    result = prime * result + ((nrCellGlobalId == null) ? 0 : nrCellGlobalId.hashCode());
    result = prime * result + ((ageOfLocationInformation == null) ? 0 : ageOfLocationInformation.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;

    if (obj == null)
      return false;

    if (getClass() != obj.getClass())
      return false;
    GMLCCDRState other = (GMLCCDRState) obj;

    if (curlUser == null) {
      if (other.curlUser != null)
        return false;
    } else if (!curlUser.equals(other.curlUser))
      return false;

    if (isdnAddressString == null) {
      if (other.isdnAddressString != null)
        return false;
    } else if (!isdnAddressString.equals(other.isdnAddressString))
      return false;

    if (destReference == null) {
      if (other.destReference != null)
        return false;
    } else if (!destReference.equals(other.destReference))
      return false;

    if (localDialogId == null) {
      if (other.localDialogId != null)
        return false;
    } else if (!localDialogId.equals(other.localDialogId))
      return false;

    if (remoteDialogId == null) {
      if (other.remoteDialogId != null)
        return false;
    } else if (!remoteDialogId.equals(other.remoteDialogId))
      return false;

    if (imsi == null) {
      if (other.imsi != null)
        return false;
    } else if (!imsi.equals(other.imsi))
      return false;

    if (vlrAddress == null) {
      if (other.vlrAddress != null)
        return false;
    } else if (!vlrAddress.equals(other.vlrAddress))
      return false;

    if (generated != other.generated)
      return false;

    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;

    if (initiated != other.initiated)
      return false;

    if (localAddress == null) {
      if (other.localAddress != null)
        return false;
    } else if (!localAddress.equals(other.localAddress))
      return false;

    if (origReference == null) {
      if (other.origReference != null)
        return false;
    } else if (!origReference.equals(other.origReference))
      return false;

    if (remoteAddress == null) {
      if (other.remoteAddress != null)
        return false;
    } else if (!remoteAddress.equals(other.remoteAddress))
      return false;

    if (diameterOriginHost == null) {
      if (other.diameterOriginHost != null)
        return false;
    } else if (!diameterOriginHost.equals(other.diameterOriginHost))
      return false;

    if (diameterOriginRealm == null) {
      if (other.diameterOriginRealm != null)
        return false;
    } else if (!diameterOriginRealm.equals(other.diameterOriginRealm))
      return false;

    if (diameterOriginPort == -1) {
      if (other.diameterOriginPort > 0)
        return false;
    } else if (diameterOriginPort != other.diameterOriginPort)
      return false;

    if (diameterDestHost == null) {
      if (other.diameterDestHost != null)
        return false;
    } else if (!diameterDestHost.equals(other.diameterDestHost))
      return false;

    if (diameterDestRealm == null) {
      if (other.diameterDestRealm != null)
        return false;
    } else if (!diameterDestRealm.equals(other.diameterDestRealm))
      return false;

    if (diameterDestPort == -1) {
      if (other.diameterDestPort > 0)
        return false;
    } else if (diameterDestPort != other.diameterDestPort)
      return false;

    if (diameterSessionId == null) {
      if (other.diameterSessionId != null)
        return false;
    } else if (!diameterSessionId.equals(other.diameterSessionId))
      return false;

    if (slpSocketAddress == null) {
      if (other.slpSocketAddress != null)
        return false;
    } else if (!slpSocketAddress.equals(other.slpSocketAddress))
      return false;

    if (slpSocketPort == -1) {
      if (other.slpSocketPort > 0)
        return false;
    } else if (slpSocketPort != other.slpSocketPort)
      return false;

    if (setSocketAddress == null) {
      if (other.setSocketAddress != null)
        return false;
    } else if (!setSocketAddress.equals(other.setSocketAddress))
      return false;

    if (setSocketPort == -1) {
      if (other.setSocketPort > 0)
        return false;
    } else if (setSocketPort != other.setSocketPort)
      return false;

    if (recordStatus == null) {
      if (other.recordStatus != null)
        return false;
    } else if (!recordStatus.equals(other.recordStatus))
      return false;

    if (statusCode == null) {
      if (other.statusCode != null)
        return false;
    } else if (!statusCode.equals(other.statusCode))
      return false;

    if (cellGlobalIdentity == null) {
      if (other.cellGlobalIdentity != null)
        return false;
    } else if (!cellGlobalIdentity.equals(other.cellGlobalIdentity))
      return false;

    if (serviceAreaIdentity == null) {
      if (other.serviceAreaIdentity != null)
        return false;
    } else if (!serviceAreaIdentity.equals(other.serviceAreaIdentity))
      return false;

    if (msClassmark2 == null) {
      if (other.msClassmark2 != null)
        return false;
    } else if (!msClassmark2.equals(other.msClassmark2))
      return false;

    if (gprsmsClass == null) {
      if (other.gprsmsClass != null)
        return false;
    } else if (!gprsmsClass.equals(other.gprsmsClass))
      return false;

    if (lcsClientID == null) {
      if (other.lcsClientID != null)
        return false;
    } else if (!lcsClientID.equals(other.lcsClientID))
      return false;

    if (lmsi == null) {
      if (other.lmsi != null)
        return false;
    } else if (!lmsi.equals(other.lmsi))
      return false;

    if (networkNodeNumber == null) {
      if (other.networkNodeNumber != null)
        return false;
    } else if (!networkNodeNumber.equals(other.networkNodeNumber))
      return false;

    if (gprsNodeIndicator != false && gprsNodeIndicator != true) {
      if (other.gprsNodeIndicator == false && other.gprsNodeIndicator == true)
        return false;
    } else if (!(gprsNodeIndicator == (other.gprsNodeIndicator)))
      return false;

    if (additionalNumber == null) {
      if (other.additionalNumber != null)
        return false;
    } else if (!additionalNumber.equals(other.additionalNumber))
      return false;

    if (mscNumber == null) {
      if (other.mscNumber != null)
        return false;
    } else if (!mscNumber.equals(other.mscNumber)) {
      return false;
    }

    if (sgsnNumber == null) {
      if (other.sgsnNumber != null)
        return false;
    } else if (!sgsnNumber.equals(other.sgsnNumber)) {
      return false;
    }

    if (mmeName == null) {
      if (other.mmeName != null)
        return false;
    } else if (!mmeName.equals(other.mmeName))
      return false;

    if (mmeRealm == null) {
      if (other.mmeRealm != null)
        return false;
    } else if (!mmeRealm.equals(other.mmeRealm))
      return false;

    if (sgsnName == null) {
      if (other.sgsnName != null)
        return false;
    } else if (!sgsnName.equals(other.sgsnName))
      return false;

    if (sgsnRealm == null) {
      if (other.sgsnRealm != null)
        return false;
    } else if (!sgsnRealm.equals(other.sgsnRealm))
      return false;

    if (aaaServerName == null) {
      if (other.aaaServerName != null)
        return false;
    } else if (!aaaServerName.equals(other.aaaServerName))
      return false;

    if (hGmlcAddress == null) {
      if (other.hGmlcAddress != null)
        return false;
    } else if (!hGmlcAddress.equals(other.hGmlcAddress))
      return false;

    if (vGmlcAddress == null) {
      if (other.vGmlcAddress != null)
        return false;
    } else if (!vGmlcAddress.equals(other.vGmlcAddress))
      return false;

    if (pprAddress == null) {
      if (other.pprAddress != null)
        return false;
    } else if (!pprAddress.equals(other.pprAddress))
      return false;

    if (locationEstimate == null) {
      if (other.locationEstimate != null)
        return false;
    } else if (!locationEstimate.equals(other.locationEstimate))
      return false;

    if (polygon == null) {
      if (other.polygon != null)
        return false;
    } else if (!polygon.equals(other.polygon))
      return false;

    if (numberOfPoints == null) {
      if (other.numberOfPoints != null)
        return false;
    } else if (!numberOfPoints.equals(other.numberOfPoints))
      return false;

    if (polygonEllipsoidPoints == null) {
      if (other.polygonEllipsoidPoints != null)
        return false;
    } else if (!polygonEllipsoidPoints.equals(other.polygonEllipsoidPoints))
      return false;

    if (additionalPolygon == null) {
      if (other.additionalPolygon != null)
        return false;
    } else if (!additionalPolygon.equals(other.additionalPolygon))
      return false;

    if (additionalNumberOfPoints == null) {
      if (other.additionalNumberOfPoints != null)
        return false;
    } else if (!additionalNumberOfPoints.equals(other.additionalNumberOfPoints))
      return false;

    if (additionalPolygonEllipsoidPoints == null) {
      if (other.additionalPolygonEllipsoidPoints != null)
        return false;
    } else if (!additionalPolygonEllipsoidPoints.equals(other.additionalPolygonEllipsoidPoints))
      return false;

    if (moLrShortCircuitIndicator != false && moLrShortCircuitIndicator != true) {
      if (other.moLrShortCircuitIndicator == false && other.moLrShortCircuitIndicator == true)
        return false;
    } else if (!(moLrShortCircuitIndicator == (other.moLrShortCircuitIndicator)))
      return false;

    if (geranPositioningDataInformation == null) {
      if (other.geranPositioningDataInformation != null)
        return false;
    } else if (!geranPositioningDataInformation.equals(other.geranPositioningDataInformation))
      return false;

    if (utranPositioningDataInfo == null) {
      if (other.utranPositioningDataInfo != null)
        return false;
    } else if (!utranPositioningDataInfo.equals(other.utranPositioningDataInfo))
      return false;

    if (utranAdditionalPositioningData == null) {
      if (other.utranAdditionalPositioningData != null)
        return false;
    } else if (!utranAdditionalPositioningData.equals(other.utranAdditionalPositioningData))
      return false;

    if (geranGANSSpositioningData == null) {
      if (other.geranGANSSpositioningData != null)
        return false;
    } else if (!geranGANSSpositioningData.equals(other.geranGANSSpositioningData))
      return false;

    if (utranGANSSpositioningData == null) {
      if (other.utranGANSSpositioningData != null)
        return false;
    } else if (!utranGANSSpositioningData.equals(other.utranGANSSpositioningData))
      return false;

    if (ageOfLocationEstimate < 0) {
      if (other.ageOfLocationEstimate > 0)
        return false;
    } else if (ageOfLocationEstimate != other.ageOfLocationEstimate)
      return false;

    if (additionalLocationEstimate == null) {
      if (other.additionalLocationEstimate != null)
        return false;
    } else if (!additionalLocationEstimate.equals(other.additionalLocationEstimate))
      return false;

    if (deferredMTLRResponseIndicator != false && deferredMTLRResponseIndicator != true) {
      if (other.deferredMTLRResponseIndicator == false && other.deferredMTLRResponseIndicator == true)
        return false;
    } else if (!(deferredMTLRResponseIndicator == (other.deferredMTLRResponseIndicator)))
      return false;

    if (cellGlobalIdOrServiceAreaIdOrLAI == null) {
      if (other.cellGlobalIdOrServiceAreaIdOrLAI != null)
        return false;
    } else if (!cellGlobalIdOrServiceAreaIdOrLAI.equals(other.cellGlobalIdOrServiceAreaIdOrLAI))
      return false;

    if (accuracyFulfilmentIndicator == null) {
      if (other.accuracyFulfilmentIndicator != null)
        return false;
    } else if (!accuracyFulfilmentIndicator.equals(other.accuracyFulfilmentIndicator))
      return false;

    if (velocityEstimate == null) {
      if (other.velocityEstimate != null)
        return false;
    } else if (!velocityEstimate.equals(other.velocityEstimate))
      return false;

    if (servingNodeAddress == null) {
      if (other.servingNodeAddress != null)
        return false;
    } else if (!servingNodeAddress.equals(other.servingNodeAddress))
      return false;

    if (lcsQoS == null) {
      if (other.lcsQoS != null)
        return false;
    } else if (!lcsQoS.equals(other.lcsQoS))
      return false;

    if (lteLcsQoSClass == null) {
      if (other.lteLcsQoSClass != null)
        return false;
    } else if (!lteLcsQoSClass.equals(other.lteLcsQoSClass))
      return false;

    if (lcsReferenceNumber < 0) {
      if (other.lcsReferenceNumber > 0)
        return false;
    } else if (lcsReferenceNumber != other.lcsReferenceNumber)
      return false;

    if (clientReferenceNumber < 0) {
      if (other.clientReferenceNumber > 0)
        return false;
    } else if (clientReferenceNumber != other.clientReferenceNumber)
      return false;

    if (barometricPressureMeasurement == null) {
      if (other.barometricPressureMeasurement != null)
        return false;
    } else if (!barometricPressureMeasurement.equals(other.barometricPressureMeasurement))
      return false;

    if (civicAddress == null) {
      if (other.civicAddress != null)
        return false;
    } else if (!civicAddress.equals(other.civicAddress))
      return false;

    if (lcsEvent == null) {
      if (other.lcsEvent != null)
        return false;
    } else if (!lcsEvent.equals(other.lcsEvent))
      return false;

    if (locationEvent == null) {
      if (other.locationEvent != null)
        return false;
    } else if (!locationEvent.equals(other.locationEvent))
      return false;

    if (msisdn == null) {
      if (other.msisdn != null)
        return false;
    } else if (!msisdn.equals(other.msisdn))
      return false;

    if (imei == null) {
      if (other.imei != null)
        return false;
    } else if (!imei.equals(other.imei))
      return false;

    if (deferredmtlrData == null) {
      if (other.deferredmtlrData != null)
        return false;
    } else if (!deferredmtlrData.equals(other.deferredmtlrData))
      return false;

    if (lcsServiceTypeID < 0) {
      if (other.lcsServiceTypeID > 0)
        return false;
    } else if (lcsServiceTypeID != other.lcsServiceTypeID)
      return false;

    if (pseudonymIndicator != false && pseudonymIndicator != true) {
      if (other.pseudonymIndicator == false && other.pseudonymIndicator == true)
        return false;
    } else if (!(pseudonymIndicator == (other.pseudonymIndicator)))
      return false;

    if (sequenceNumber < 0) {
      if (other.sequenceNumber > 0)
        return false;
    } else if (sequenceNumber != other.sequenceNumber)
      return false;

    if (periodicLDRInfo == null) {
      if (other.periodicLDRInfo != null)
        return false;
    } else if (!periodicLDRInfo.equals(other.periodicLDRInfo))
      return false;

    if (reportingPLMNList == null) {
      if (other.reportingPLMNList != null)
        return false;
    } else if (!reportingPLMNList.equals(other.reportingPLMNList))
      return false;

    if (subscriberInfo == null) {
      if (other.subscriberInfo != null)
        return false;
    } else if (!subscriberInfo.equals(other.subscriberInfo))
      return false;

    if (locationInformation == null) {
      if (other.locationInformation != null)
        return false;
    } else if (!locationInformation.equals(other.locationInformation))
      return false;

    if (locationInformationEPS == null) {
      if (other.locationInformationEPS != null)
        return false;
    } else if (!locationInformationEPS.equals(other.locationInformationEPS))
      return false;

    if (locationInformationGPRS == null) {
      if (other.locationInformationGPRS != null)
        return false;
    } else if (!locationInformationGPRS.equals(other.locationInformationGPRS))
      return false;

    if (typeOfShape == null) {
      if (other.typeOfShape != null)
        return false;
    } else if (!typeOfShape.equals(other.typeOfShape))
      return false;

    if (mnpInfoRes == null) {
      if (other.mnpInfoRes != null)
        return false;
    } else if (!mnpInfoRes.equals(other.mnpInfoRes))
      return false;

    if (saiPresent != false && saiPresent != true) {
      if (other.saiPresent == false && other.saiPresent == true)
        return false;
    } else if (!(saiPresent == (other.saiPresent)))
      return false;

    if (currentLocationRetrieved != false && currentLocationRetrieved != true) {
      if (other.currentLocationRetrieved == false && other.currentLocationRetrieved == true)
        return false;
    } else if (!(currentLocationRetrieved == (other.currentLocationRetrieved)))
      return false;

    if (taId == null) {
      if (other.taId != null)
        return false;
    } else if (!taId.equals(other.taId))
      return false;

    if (eUtranCgi == null) {
      if (other.eUtranCgi != null)
        return false;
    } else if (!eUtranCgi.equals(other.eUtranCgi))
      return false;

    if (cellPortionId == null) {
      if (other.cellPortionId != null)
        return false;
    } else if (!cellPortionId.equals(other.cellPortionId))
      return false;

    if (locationNumberMap == null) {
      if (other.locationNumberMap != null)
        return false;
    } else if (!locationNumberMap.equals(other.locationNumberMap))
      return false;

    if (eUTRANPositioningData == null) {
      if (other.eUTRANPositioningData != null)
        return false;
    } else if (!eUTRANPositioningData.equals(other.eUTRANPositioningData))
      return false;

    if (oneXRTTRCID == null) {
      if (other.oneXRTTRCID != null)
        return false;
    } else if (!oneXRTTRCID.equals(other.oneXRTTRCID))
      return false;

    if (nrCellGlobalId == null) {
      if (other.nrCellGlobalId != null)
        return false;
    } else if (!nrCellGlobalId.equals(other.nrCellGlobalId))
      return false;

    if (ageOfLocationInformation == null) {
      if (other.ageOfLocationInformation != null)
        return false;
    } else if (!ageOfLocationInformation.equals(other.ageOfLocationInformation))
      return false;

    if (amfAddress == null) {
      if (other.amfAddress != null)
        return false;
    } else if (!amfAddress.equals(other.amfAddress))
      return false;

    if (smsfAddress == null) {
      if (other.smsfAddress != null)
        return false;
    } else if (!smsfAddress.equals(other.smsfAddress))
      return false;

    if (visitedPlmnId == null) {
      if (other.visitedPlmnId != null)
        return false;
    } else if (!visitedPlmnId.equals(other.visitedPlmnId))
      return false;

    if (localTimeZone == null) {
      if (other.localTimeZone != null)
        return false;
    } else if (!localTimeZone.equals(other.localTimeZone))
      return false;

    if (ratType == null) {
      if (other.ratType != null)
        return false;
    } else if (!ratType.equals(other.ratType))
      return false;

    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {

    String msisdnStr = null, imsiStr = null, lmsiStr = null, imeiStr = null, msClassmark = null, msNetworkCapability = null, msRASCapability = null,
        lcsClientID_Name = null, lcsClientID_APN = null, lcsClientID_LCSClientExternalID = null, lcsClientID_LCSClientDialedByMS = null,
        lcsClientID_LCSRequestorID_IDString = null, additionalMSCNumber = null, additionalSGSNNumber = null, nnn = null, mmeNameStr = null,
        mmeRealmStr = null, sgsnNameStr = null, sgsnRealmStr = null, aaaServerNameStr = null, hGmlcAddressStr = null, vGmlcAddressStr = null,
        pprAddressStr = null, locEstTypeOfShape = null, geranPosDataInfo = null, utranPosDataInfo = null, geranGANSSPosDataInfo = null,
        utranGANSSPosDataInfo = null, utranAdditionalPosDataInfo = null, addLocEstTypeOfShape = null, moLrShortCircuitIndicatorStr = null,
        deferredMTLRResponseIndicatorStr = null, cgi = null, sai = null, servingNodeAddressMSC = null, servingNodeAddressSGSN = null,
        servingNodeAddressMME = null, lcsQoSVerticalCoordRequest = null, pseudonymIndicatorStr = null, deferredmtlrDataLMSI = null,
        deferredmtlrDataNNN = null, deferredmtlrDataMSCnum = null, deferredmtlrDataSGSNnum = null, deferredmtlrDataAAAServerName = null,
        deferredmtlrDataMMEname = null, deferredmtlrDataGPRSNodeInd = null, deferredmtlrDataEventType = null, reportingPLMNListArray = null,
        reportingPLMNListPrioritized = null, geogTypeOfShape = null, geodTypeOfShape = null, vlrNum = null, mscNum = null, sgsnNum = null,
        trackingAreaId = null, ecgi = null, mnpImsi = null, mnpMsisdn = null, mnpRouteingNum = null, saiPresent = null,
        currentLocRetrieved = null, subState = null, lsaIdentity = null, raIdentity = null, eUTRANPositioningDataStr = null,
        civicAddr = null, oneXRttRcid = null, nr5gsCellGlobalId = null, shTimeZone = null, sh5gsAmfAddress = null, sh5gsSmsfAddress = null, curlUsr = null;

    Integer lcsRefNum = null, clientRefNum = null, ageOfLocEstimate = null, ageOfLocation = null, lcsClientID_DCS = null, lcsClientID_FI = null,
        lcsClientID_LCSClientInternalID = null, lcsClientID_LCSClientType = null, lcsClientID_LCSRequestorID_DCS = null, lcsClientID_LCSRequestorID_FI = null,
        locEstConfidence = null, locEstAltitude = null, locEstInnerRadius = null, addLocEstConfidence = null, addLocEstAltitude = null,
        addLocEstInnerRadius = null, lsmMCC = null, lsmMNC = null, lsmLAC = null, lsmCI = null, accuracyFulfilmentIndicatorValue = null,
        sequenceNumberValue = null, velEstHorizontalSpeed = null, velEstVerticalSpeed = null, velEstBearing = null, velEstHorSpeedUncertainty = null,
        velEstVertSpeedUncertainty = null, velocityEstTypeValue = null, lcsQoSHorizontalAccuracy = null, lcsQoSVerticalAccuracy = null,
        lcsQoSResponseTimeCategory = null, lcsQosClass = null, lcsServiceTypeIDValue = null, lcsEventValue = null, locationEventValue = null,
        deferredmtlrDataTerminationCause = null, periodicLDRInfoReportingAmount = null, periodicLDRInfoReportingInterval = null, cgiSaiLaiMCC = null,
        cgiSaiLaiMNC = null, cgiSaiLaiLAC = null, cgiSaiLaiCI = null, aol = null, geodConfidence = null, geodScreeningAndPresentationInd = null,
        mnpStatus = null, daylightSaveTime = null, ratT = null;

    Long cellPortionIdentity = null, barometricPressure = null, errorCode = null;

    Double locEstLatitude = null, locEstLongitude = null, locEstUncertainty = null, locEstIncludeAngle = null, locEstOffsetAngle = null,
        locEstUncertaintyAltitude = null, locEstUncertaintyRadius = null, locEstAngleOfMajorAxis = null, locEstUncertaintySemiMajorAxis = null,
        locEstUncertaintySemiMinorAxis = null, addLocEstLatitude = null, addLocEstLongitude = null, addLocEstUncertainty = null, addLocEstIncludeAngle = null,
        addLocEstOffsetAngle = null, addLocEstUncertaintyAltitude = null, addLocEstUncertaintyRadius = null, addLocEstAngleOfMajorAxis = null,
        addLocEstUncertaintySemiMajorAxis = null, addLocEstUncertaintySemiMinorAxis = null, geogLat = null, geogLong = null, geogUncertainty = null,
        geodLat = null, geodLong = null, geodUncertainty = null;

    Boolean gprsNodeInd = false, psiIsPlmnSignificantLSA = false, currentLocationRetrieved = false;


    try {

      if (curlUser != null)
        curlUsr = curlUser;

      if (statusCode != null)
        errorCode = statusCode;

      if (msisdn != null)
        msisdnStr = msisdn.getAddress();

      if (imsi != null)
        imsiStr = imsi.getData();

      if (lmsi != null)
        lmsiStr = new String(lmsi.getData());

      if (imei != null)
        imeiStr = imei.getIMEI();

      if (networkNodeNumber != null)
        nnn = networkNodeNumber.getAddress();

      if (msClassmark2 != null)
        msClassmark = new String (msClassmark2.getData());

      if (gprsmsClass != null) {
        msNetworkCapability = new String (gprsmsClass.getMSNetworkCapability().getData());
        msRASCapability = new String (gprsmsClass.getMSRadioAccessCapability().getData());
      }

      if (locationEstimate != null) {
        locEstLatitude = locationEstimate.getLatitude();
        locEstLongitude = locationEstimate.getLongitude();
        locEstUncertainty = locationEstimate.getUncertainty();
        locEstAltitude = locationEstimate.getAltitude();
        locEstUncertaintyAltitude = locationEstimate.getUncertaintyAltitude();
        locEstConfidence = locationEstimate.getConfidence();
        locEstInnerRadius = locationEstimate.getInnerRadius();
        locEstUncertaintyRadius = locationEstimate.getUncertaintyRadius();
        locEstIncludeAngle = locationEstimate.getIncludedAngle();
        locEstOffsetAngle = locationEstimate.getOffsetAngle();
        locEstAngleOfMajorAxis = locationEstimate.getAngleOfMajorAxis();
        locEstUncertaintySemiMajorAxis = locationEstimate.getUncertaintySemiMajorAxis();
        locEstUncertaintySemiMinorAxis = locationEstimate.getUncertaintySemiMinorAxis();
        if (locationEstimate.getTypeOfShape() != null)
          locEstTypeOfShape = locationEstimate.getTypeOfShape().name();
      }

      if (ageOfLocationEstimate != null)
        ageOfLocEstimate = ageOfLocationEstimate;

      if (geranPositioningDataInformation!= null)
        geranPosDataInfo = new String(geranPositioningDataInformation.getData());

      if (utranPositioningDataInfo != null)
        utranPosDataInfo = new String(utranPositioningDataInfo.getData());

      if (utranAdditionalPositioningData != null)
        utranAdditionalPosDataInfo = utranAdditionalPositioningData;

      if (geranGANSSpositioningData != null)
        geranGANSSPosDataInfo = new String(geranGANSSpositioningData.getData());

      if (utranGANSSpositioningData != null)
        utranGANSSPosDataInfo = new String(utranGANSSpositioningData.getData());

      if (additionalNumber != null) {
        if (additionalNumber.getMSCNumber() != null)
          additionalMSCNumber = additionalNumber.getMSCNumber().getAddress();
        if (additionalNumber.getSGSNNumber() != null)
          additionalSGSNNumber = additionalNumber.getSGSNNumber().getAddress();
      }

      if (mmeName != null)
        mmeNameStr = new String(mmeName.getData());

      if (mmeRealm != null)
        mmeRealmStr = new String(mmeRealm.getData());

      if (sgsnName != null)
        sgsnNameStr = new String(sgsnName.getData());

      if (sgsnRealm != null)
        sgsnRealmStr = new String(sgsnRealm.getData());

      if (aaaServerName != null)
        aaaServerNameStr = new String(aaaServerName.getData());

      if (hGmlcAddress != null)
        hGmlcAddressStr = new String(hGmlcAddress.getData());

      if (vGmlcAddress != null)
        vGmlcAddressStr = new String(vGmlcAddress.getData());

      if (pprAddress != null)
        pprAddressStr = new String(pprAddress.getData());

      if (additionalLocationEstimate != null) {
        addLocEstLatitude = additionalLocationEstimate.getLatitude();
        addLocEstLongitude = additionalLocationEstimate.getLongitude();
        addLocEstUncertainty = additionalLocationEstimate.getUncertainty();
        addLocEstAltitude = additionalLocationEstimate.getAltitude();
        addLocEstUncertaintyAltitude = additionalLocationEstimate.getUncertaintyAltitude();
        addLocEstConfidence = additionalLocationEstimate.getConfidence();
        addLocEstInnerRadius = additionalLocationEstimate.getInnerRadius();
        addLocEstUncertaintyRadius = additionalLocationEstimate.getUncertaintyRadius();
        addLocEstIncludeAngle = additionalLocationEstimate.getIncludedAngle();
        addLocEstOffsetAngle = additionalLocationEstimate.getOffsetAngle();
        addLocEstAngleOfMajorAxis = additionalLocationEstimate.getAngleOfMajorAxis();
        addLocEstUncertaintySemiMajorAxis = additionalLocationEstimate.getUncertaintySemiMajorAxis();
        addLocEstUncertaintySemiMinorAxis = additionalLocationEstimate.getUncertaintySemiMinorAxis();
        if (additionalLocationEstimate.getTypeOfShape() != null)
          addLocEstTypeOfShape = additionalLocationEstimate.getTypeOfShape().name();
      }

      if (moLrShortCircuitIndicator != null)
        moLrShortCircuitIndicatorStr = String.valueOf(moLrShortCircuitIndicator);

      if (deferredMTLRResponseIndicator != null)
        deferredMTLRResponseIndicatorStr = String.valueOf(deferredMTLRResponseIndicator);

      if (cellGlobalIdOrServiceAreaIdOrLAI != null) {
        if (cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
          lsmMCC = + cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
          lsmMNC = + cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
          lsmLAC = + cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
          lsmCI = + cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
        }
        if (cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
          lsmMCC = + cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMCC();
          lsmMNC = + cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMNC();
          lsmLAC = + cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getLac();
        }
      }

      if (accuracyFulfilmentIndicator != null)
        accuracyFulfilmentIndicatorValue = accuracyFulfilmentIndicator.getIndicator();

      if (sequenceNumber != null) {
        try {
          sequenceNumberValue = sequenceNumber;
        } catch (NumberFormatException nfe) {
          sequenceNumberValue = -1;
        }
      }

      if (velocityEstimate != null) {
        if (velocityEstimate.getData() != null) {
          try {
            velEstHorizontalSpeed = velocityEstimate.getHorizontalSpeed();
            velEstVerticalSpeed = velocityEstimate.getVerticalSpeed();
            velEstHorSpeedUncertainty = velocityEstimate.getUncertaintyHorizontalSpeed();
            velEstVertSpeedUncertainty = velocityEstimate.getUncertaintyVerticalSpeed();
            velEstBearing = velocityEstimate.getBearing();
            velocityEstTypeValue = velocityEstimate.getVelocityType().getCode();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }

      if (lcsReferenceNumber != null)
        lcsRefNum = lcsReferenceNumber;

      if (clientReferenceNumber != null)
        clientRefNum = clientReferenceNumber;

      if (gprsNodeIndicator != null)
        gprsNodeInd = gprsNodeIndicator;

      if (pseudonymIndicator != null)
        pseudonymIndicatorStr = String.valueOf(pseudonymIndicator);

      if (servingNodeAddress != null) {
        if (servingNodeAddress.getMscNumber() != null)
          servingNodeAddressMSC = servingNodeAddress.getMscNumber().getAddress();
        if (servingNodeAddress.getSgsnNumber() != null)
          servingNodeAddressSGSN = servingNodeAddress.getSgsnNumber().getAddress();
        if (servingNodeAddress.getMmeNumber() != null)
          servingNodeAddressMME = new String(servingNodeAddress.getMmeNumber().getData());
      }

      if (lcsClientID != null) {
        if (lcsClientID.getLCSAPN() != null)
          lcsClientID_APN =  new String(lcsClientID.getLCSAPN().getApn());
        if (lcsClientID.getLCSClientName() != null) {
          lcsClientID_Name = lcsClientID.getLCSClientName().getNameString().toString();
          lcsClientID_DCS = lcsClientID.getLCSClientName().getDataCodingScheme().getCode();
          lcsClientID_FI = lcsClientID.getLCSClientName().getLCSFormatIndicator().getIndicator();
        }
        if (lcsClientID.getLCSClientExternalID() != null)
          lcsClientID_LCSClientExternalID = lcsClientID.getLCSClientExternalID().getExternalAddress().getAddress();
        if (lcsClientID.getLCSClientInternalID() != null)
          lcsClientID_LCSClientInternalID = lcsClientID.getLCSClientInternalID().getId();
        if (lcsClientID.getLCSClientDialedByMS() != null)
          lcsClientID_LCSClientDialedByMS = lcsClientID.getLCSClientDialedByMS().getAddress();
        if (lcsClientID.getLCSClientType() != null)
          lcsClientID_LCSClientType = lcsClientID.getLCSClientType().getType();
        if (lcsClientID.getLCSRequestorID() != null) {
          lcsClientID_LCSRequestorID_DCS = lcsClientID.getLCSRequestorID().getDataCodingScheme().getCode();
          lcsClientID_LCSRequestorID_FI = lcsClientID.getLCSRequestorID().getLCSFormatIndicator().getIndicator();
          lcsClientID_LCSRequestorID_IDString = lcsClientID.getLCSRequestorID().getRequestorIDString().getEncodedString().toString();
        }
      }

      if (lcsQoS != null) {
        if (lcsQoS.getHorizontalAccuracy() != null)
          lcsQoSHorizontalAccuracy = lcsQoS.getHorizontalAccuracy();
        if (lcsQoS.getVerticalAccuracy() != null)
          lcsQoSVerticalAccuracy = lcsQoS.getVerticalAccuracy();
        if (lcsQoS.getResponseTime() != null) {
          try {
            lcsQoSResponseTimeCategory = lcsQoS.getResponseTime().getResponseTimeCategory().getCategory();
          } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
          }
        }
        if (lcsQoS.getVerticalCoordinateRequest() == true || lcsQoS.getVerticalCoordinateRequest() == false)
          lcsQoSVerticalCoordRequest = String.valueOf(lcsQoSVerticalCoordRequest);
      }

      if (lcsQosClass != null) {
        if (lteLcsQoSClass != null)
          lcsQosClass = lteLcsQoSClass.getValue();
      }

      if (lcsServiceTypeID != null) {
        try {
          lcsServiceTypeIDValue = lcsServiceTypeID;
        } catch (NumberFormatException nfe) {
          nfe.printStackTrace();
        }
      }

      if (lcsEvent != null) {
        try {
          lcsEventValue = lcsEvent.getEvent();
        } catch (NumberFormatException nfe) {
          nfe.printStackTrace();
        }
      }

      if (locationEvent != null) {
        try {
          locationEventValue = locationEvent.getValue();
        } catch (NumberFormatException nfe) {
          nfe.printStackTrace();
        }
      }

      if (deferredmtlrData != null) {
        if (deferredmtlrData.getDeferredLocationEventType() != null) {
          if (deferredmtlrData.getDeferredLocationEventType().getEnteringIntoArea() == true)
            deferredmtlrDataEventType = "EnteringIntoArea";
          else if (deferredmtlrData.getDeferredLocationEventType().getBeingInsideArea() == true)
            deferredmtlrDataEventType = "BeingInsideArea";
          else if (deferredmtlrData.getDeferredLocationEventType().getLeavingFromArea() == true)
            deferredmtlrDataEventType = "LeavingFromArea";
          else if (deferredmtlrData.getDeferredLocationEventType().getMsAvailable() == true)
            deferredmtlrDataEventType = "MSAvailable";
        }
        if (deferredmtlrData.getLCSLocationInfo() != null) {
          if (deferredmtlrData.getLCSLocationInfo().getLMSI() != null)
            deferredmtlrDataLMSI = new String(deferredmtlrData.getLCSLocationInfo().getLMSI().getData());
          if (deferredmtlrData.getLCSLocationInfo().getNetworkNodeNumber() != null)
            deferredmtlrDataNNN = deferredmtlrData.getLCSLocationInfo().getNetworkNodeNumber().getAddress();
          if (deferredmtlrData.getLCSLocationInfo().getAdditionalNumber() != null) {
            if (deferredmtlrData.getLCSLocationInfo().getAdditionalNumber().getMSCNumber() != null)
              deferredmtlrDataMSCnum = deferredmtlrData.getLCSLocationInfo().getAdditionalNumber().getMSCNumber().getAddress();
            if (deferredmtlrData.getLCSLocationInfo().getAdditionalNumber().getSGSNNumber() != null)
              deferredmtlrDataSGSNnum = deferredmtlrData.getLCSLocationInfo().getAdditionalNumber().getSGSNNumber().getAddress();
          }
          if (deferredmtlrData.getLCSLocationInfo().getAaaServerName() != null)
            deferredmtlrDataAAAServerName = new String(deferredmtlrData.getLCSLocationInfo().getAaaServerName().getData());
          if (deferredmtlrData.getLCSLocationInfo().getMmeName() != null)
            deferredmtlrDataMMEname = new String(deferredmtlrData.getLCSLocationInfo().getMmeName().getData());
          if (deferredmtlrData.getLCSLocationInfo().getGprsNodeIndicator() == true ||
              deferredmtlrData.getLCSLocationInfo().getGprsNodeIndicator() == false)
            deferredmtlrDataGPRSNodeInd = String.valueOf(deferredmtlrData.getLCSLocationInfo().getGprsNodeIndicator());
        }
        if (deferredmtlrData.getTerminationCause() != null)
          deferredmtlrDataTerminationCause = deferredmtlrData.getTerminationCause().getCause();
      }

      if (periodicLDRInfo != null) {
        periodicLDRInfoReportingAmount = periodicLDRInfo.getReportingAmount();
        periodicLDRInfoReportingInterval = periodicLDRInfo.getReportingInterval();
      }

      if (reportingPLMNList != null) {
        if (reportingPLMNList.getPlmnList() != null) {
          int plmnCounter = 0;
          reportingPLMNListArray = "[ ";
          while (reportingPLMNList.getPlmnList().iterator().hasNext()) {
            reportingPLMNListArray = reportingPLMNListArray + reportingPLMNList.getPlmnList().get(plmnCounter);
            plmnCounter++;
            if (reportingPLMNList.getPlmnList().get(plmnCounter) != null) {
              reportingPLMNListArray = reportingPLMNListArray + ", ";
            } else {
              reportingPLMNListArray = reportingPLMNListArray + " ]";
            }
          }
        }
        if (reportingPLMNList.getPlmnListPrioritized() == true ||
            reportingPLMNList.getPlmnListPrioritized() == false)
          reportingPLMNListPrioritized = String.valueOf(reportingPLMNList.getPlmnListPrioritized());
      }

      if (subscriberInfo != null) {
        if (locationInformation != null) {
          if (locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
            cgiSaiLaiMCC = locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
            cgiSaiLaiMNC = locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
            cgiSaiLaiLAC = locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
            cgiSaiLaiCI = locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
          }
          if (locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
            cgiSaiLaiMCC = locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
            cgiSaiLaiMNC = locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
            cgiSaiLaiLAC = locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
          }

          if (locationInformation.getGeographicalInformation() != null) {
            geogLat = locationInformation.getGeographicalInformation().getLatitude();
            geogLong = locationInformation.getGeographicalInformation().getLongitude();
            geogUncertainty = locationInformation.getGeographicalInformation().getUncertainty();
            geogTypeOfShape = locationInformation.getGeographicalInformation().getTypeOfShape().name();
          }

          if (locationInformation.getGeodeticInformation() != null) {
            geodLat = locationInformation.getGeodeticInformation().getLatitude();
            geodLong = locationInformation.getGeodeticInformation().getLongitude();
            geodUncertainty = locationInformation.getGeodeticInformation().getUncertainty();
            geodTypeOfShape = locationInformation.getGeodeticInformation().getTypeOfShape().name();
            geodConfidence = locationInformation.getGeodeticInformation().getConfidence();
            geodScreeningAndPresentationInd = locationInformation.getGeodeticInformation().getScreeningAndPresentationIndicators();
          }

          if (locationInformation.getVlrNumber() != null)
            vlrNum = locationInformation.getVlrNumber().getAddress();

          if (locationInformation.getMscNumber() != null)
            mscNum = locationInformation.getMscNumber().getAddress();

          if (locationInformation.getSaiPresent() == true || locationInformation.getSaiPresent() == false)
            saiPresent = String.valueOf(locationInformation.getSaiPresent());

          if (locationInformation.getCurrentLocationRetrieved() == true || locationInformation.getCurrentLocationRetrieved() == false)
            currentLocRetrieved = String.valueOf(locationInformation.getCurrentLocationRetrieved());

          if (locationInformation.getAgeOfLocationInformation() <=Integer.MAX_VALUE && locationInformation.getAgeOfLocationInformation() >= Integer.MIN_VALUE)
            aol = locationInformation.getAgeOfLocationInformation();

          if (locationInformation.getLocationInformationEPS() != null) {
            if (locationInformationEPS.getGeographicalInformation() != null) {
              geogLat = locationInformationEPS.getGeographicalInformation().getLatitude();
              geogLong = locationInformationEPS.getGeographicalInformation().getLongitude();
              geogUncertainty = locationInformationEPS.getGeographicalInformation().getUncertainty();
              geogTypeOfShape = locationInformationEPS.getGeographicalInformation().getTypeOfShape().name();
            }

            if (locationInformationEPS.getGeodeticInformation() != null) {
              geodLat = locationInformationEPS.getGeodeticInformation().getLatitude();
              geodLong = locationInformationEPS.getGeodeticInformation().getLongitude();
              geodUncertainty = locationInformationEPS.getGeodeticInformation().getUncertainty();
              geodTypeOfShape = locationInformationEPS.getGeodeticInformation().getTypeOfShape().name();
              geodConfidence = locationInformationEPS.getGeodeticInformation().getConfidence();
              geodScreeningAndPresentationInd = locationInformationEPS.getGeodeticInformation().getScreeningAndPresentationIndicators();
            }
            if (locationInformation.getLocationInformationEPS().getTrackingAreaIdentity() != null)
              trackingAreaId = new String(locationInformation.getLocationInformationEPS().getTrackingAreaIdentity().getData());
            if (locationInformation.getLocationInformationEPS().getEUtranCellGlobalIdentity() != null)
              ecgi = new String(locationInformation.getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
          }
        }

        if (locationInformationGPRS != null) {

          if(locationInformationGPRS.getSGSNNumber() != null)
            sgsnNum = locationInformationGPRS.getSGSNNumber().getAddress();

          if(locationInformationGPRS.getLSAIdentity() != null) {
            lsaIdentity = new String(locationInformationGPRS.getLSAIdentity().getData());
            if(locationInformationGPRS.getLSAIdentity().isPlmnSignificantLSA())
              psiIsPlmnSignificantLSA = locationInformationGPRS.getLSAIdentity().isPlmnSignificantLSA();
          }

          if(locationInformationGPRS.getRouteingAreaIdentity() != null) {
            raIdentity = new String(locationInformationGPRS.getRouteingAreaIdentity().getData());
          }
        }

        if (subscriberInfo.getSubscriberState() != null)
          subState = subscriberInfo.getSubscriberState().getSubscriberStateChoice().toString();
        if (subscriberInfo.getMNPInfoRes() != null) {
          mnpImsi = subscriberInfo.getMNPInfoRes().getIMSI().getData();
          mnpMsisdn = subscriberInfo.getMNPInfoRes().getMSISDN().getAddress();
          mnpStatus = subscriberInfo.getMNPInfoRes().getNumberPortabilityStatus().getType();
          mnpRouteingNum = subscriberInfo.getMNPInfoRes().getRouteingNumber().getRouteingNumber();
        }

        if (eUTRANPositioningData != null)
          eUTRANPositioningDataStr = eUTRANPositioningData;

        if (cellGlobalIdentity != null)
          cgi = cellGlobalIdentity;

        if (sai != null)
          sai = serviceAreaIdentity;

        if (cellPortionId != null)
          cellPortionIdentity = cellPortionId;

        if (civicAddress != null)
          civicAddr = civicAddress;

        if (barometricPressureMeasurement != null)
          barometricPressure = barometricPressureMeasurement;

        if (oneXRTTRCID != null)
          oneXRttRcid = oneXRTTRCID;

        if (locationInformationEPS != null) {
          if (locationInformationEPS.getEUtranCellGlobalIdentity() != null) {
            ecgi = new String(locationInformation.getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
          }
          if (locationInformationEPS.getTrackingAreaIdentity() != null) {
            trackingAreaId = new String(locationInformation.getLocationInformationEPS().getTrackingAreaIdentity().getData());
          }
          if (locationInformationEPS.getGeographicalInformation() != null) {
            geogLat = locationInformationEPS.getGeographicalInformation().getLatitude();
            geogLong = locationInformationEPS.getGeographicalInformation().getLongitude();
            geogUncertainty = locationInformationEPS.getGeographicalInformation().getUncertainty();
            geogTypeOfShape = locationInformationEPS.getGeographicalInformation().getTypeOfShape().name();
          }
          if (locationInformationEPS.getGeodeticInformation() != null) {
            geodLat = locationInformationEPS.getGeodeticInformation().getLatitude();
            geodLong = locationInformationEPS.getGeodeticInformation().getLongitude();
            geodUncertainty = locationInformationEPS.getGeodeticInformation().getUncertainty();
            geodTypeOfShape = locationInformationEPS.getGeodeticInformation().getTypeOfShape().name();
            geodConfidence = locationInformationEPS.getGeodeticInformation().getConfidence();
            geodScreeningAndPresentationInd = locationInformationEPS.getGeodeticInformation().getScreeningAndPresentationIndicators();
          }
          if (locationInformationEPS.getCurrentLocationRetrieved()) {
            currentLocationRetrieved = true;
          }
          if (locationInformationEPS.getAgeOfLocationInformation() != null) {
            ageOfLocation = locationInformationEPS.getAgeOfLocationInformation().intValue();
            if (locationInformationEPS.getAgeOfLocationInformation() != 0)
              currentLocationRetrieved = false;
          }
          if (locationInformationEPS.getMmeName() != null) {
            mmeNameStr = new String(locationInformationEPS.getMmeName().getData());
          }
        }

        if (locationInformation5GS != null) {
          if (locationInformation5GS.getNRCellGlobalIdentity() != null) {
            nr5gsCellGlobalId = new String (locationInformation5GS.getNRCellGlobalIdentity().getData());
          }
          if (locationInformation5GS.getEUtranCellGlobalIdentity() != null) {
            ecgi = new String (locationInformation5GS.getEUtranCellGlobalIdentity().getData());
          }
          if (locationInformation5GS.getGeographicalInformation() != null) {
            geogLat = locationInformation5GS.getGeographicalInformation().getLatitude();
            geogLong = locationInformation5GS.getGeographicalInformation().getLongitude();
            geogUncertainty = locationInformation5GS.getGeographicalInformation().getUncertainty();
            geogTypeOfShape = locationInformation5GS.getGeographicalInformation().getTypeOfShape().name();
          }
          if (locationInformation5GS.getAMFAddress() != null) {
            sh5gsAmfAddress = locationInformation5GS.getAMFAddress();
          }
          if (locationInformation5GS.getSMSFAddress() != null) {
            sh5gsSmsfAddress = locationInformation5GS.getSMSFAddress();
          }
          if (locationInformation5GS.getCurrentLocationRetrieved()) {
            currentLocationRetrieved = true;
          }
          if (locationInformation5GS.getAgeOfLocationInformation() != null) {
            ageOfLocation = locationInformation5GS.getAgeOfLocationInformation().intValue();
            if (ageOfLocation != 0)
              currentLocationRetrieved = false;
          }
        }
      }

    } catch (MAPException e) {
      e.printStackTrace();
    }

    return "GMLCCDRState [initiated=" + initiated +
        ", generated=" + generated +
        ", user=" + curlUsr +
        ", id=" + id +
        ", recordStatus=" + recordStatus +
        ", statusCode=" + errorCode +
        ", localDialogId=" + localDialogId  +
        ", remoteDialogId=" + remoteDialogId +
        ", dialogDuration" + dialogDuration +
        ", origReference=" + origReference +
        ", destReference=" + destReference +
        ", localAddress=" + localAddress +
        ", remoteAddress=" + remoteAddress +
        ", diameterOriginHost=" + diameterOriginHost +
        ", diameterOriginRealm=" + diameterOriginRealm +
        ", diameterOriginPort=" + diameterOriginPort +
        ", diameterDestHost=" + diameterDestHost +
        ", diameterDestRealm=" + diameterDestRealm +
        ", diameterDestPort=" + diameterDestPort +
        ", slpSocketAddress=" + slpSocketAddress +
        ", slpSocketPort=" + slpSocketPort +
        ", setSocketAddress=" + setSocketAddress +
        ", setSocketPort=" + setSocketPort +
        ", vlrAddress=" + vlrAddress +
        ", ISDNString=" + isdnAddressString +

        // MAP ATI response parameters concerning detail records
        ", msClassmark=" + msClassmark +
        ", msNetworkCapability=" + msNetworkCapability +
        ", msRadioAcessCapability=" + msRASCapability +

        // MAP LSM / LTE LCS response parameters parameters concerning detail records
        ", IMSI=" + imsiStr +
        ", LMSI=" + lmsiStr +
        ", networkNodeNumber=" + nnn +
        ", GPRSNodeIndicator=" + gprsNodeInd +
        ", additionalMSCNumber=" + additionalMSCNumber +
        ", additionalSGSNNumber=" + additionalSGSNNumber +
        ", MMEName=" + mmeNameStr +
        ", MMERealm=" + mmeRealmStr +
        ", SGSNName=" + sgsnNameStr +
        ", SGSNRealm=" + sgsnRealmStr +
        ", AAAServerName=" + aaaServerNameStr +
        ", vGMLCAddressData=" + vGmlcAddressStr +
        ", hGMLCAddressData=" + hGmlcAddressStr +
        ", PPRAddressData=" + pprAddressStr +
        ", locationEstimateLatitude=" + locEstLatitude +
        ", locationEstimateLongitude=" + locEstLongitude +
        ", locationEstimateUncertainty=" + locEstUncertainty +
        ", locationEstimateAltitude=" + locEstAltitude +
        ", locationEstimateUncertaintyAltitude=" + locEstUncertaintyAltitude +
        ", locationEstimateConfidence=" + locEstConfidence +
        ", locationEstimateInnerRadius=" + locEstInnerRadius +
        ", locationEstimateUncertaintyInnerRadius=" + locEstUncertaintyRadius +
        ", locationEstimateIncludeAngle=" + locEstIncludeAngle +
        ", locationEstimateOffsetAngle=" + locEstOffsetAngle +
        ", locationEstimateAngleOfMajorAxis=" + locEstAngleOfMajorAxis +
        ", locationEstimateUncertaintySemiMajorAxis=" + locEstUncertaintySemiMajorAxis +
        ", locationEstimateUncertaintySemiMinorAxis=" + locEstUncertaintySemiMinorAxis +
        ", locationEstimateTypeOfShape=" + locEstTypeOfShape +
        ", geranPositioningDataInformation=" + geranPosDataInfo +
        ", utranPositioningDataInfo=" + utranPosDataInfo +
        ", utranAdditionalPositioningDataInfo=" + utranAdditionalPosDataInfo +
        ", geranGANSSPositioningData=" + geranGANSSPosDataInfo +
        ", utranGANSSPositioningData=" + utranGANSSPosDataInfo +
        ", ageOfLocationEstimate=" + ageOfLocEstimate +
        ", additionalLocationEstimateLatitude=" + addLocEstLatitude +
        ", additionalLocationEstimateLongitude=" + addLocEstLongitude +
        ", additionalLocationEstimateUncertainty=" + addLocEstUncertainty +
        ", additionalLocationEstimateAltitude=" + addLocEstAltitude +
        ", additionalLocationEstimateUncertaintyAltitude=" + addLocEstUncertaintyAltitude +
        ", additionalLocationEstimateConfidence=" + addLocEstConfidence +
        ", additionalLocationEstimateInnerRadius=" + addLocEstInnerRadius +
        ", additionalLocationEstimateUncertaintyInnerRadius=" + addLocEstUncertaintyRadius +
        ", additionalLocationEstimateIncludeAngle=" + addLocEstIncludeAngle +
        ", additionalLocationEstimateOffsetAngle=" + addLocEstOffsetAngle +
        ", additionalLocationEstimateAngleOfMajorAxis=" + addLocEstAngleOfMajorAxis +
        ", additionalLocationEstimateUncertaintySemiMajorAxis=" + addLocEstUncertaintySemiMajorAxis +
        ", additionalLocationEstimateUncertaintySemiMinorAxis=" + addLocEstUncertaintySemiMinorAxis +
        ", additionalLocationEstimateTypeOfShape=" + addLocEstTypeOfShape +
        ", deferredMTLRResponseIndicator=" + deferredMTLRResponseIndicatorStr +
        ", locationServicesMCC=" + lsmMCC +
        ", locationServicesMNC=" + lsmMNC +
        ", locationServicesLAC=" + lsmLAC +
        ", locationServicesCI=" + lsmCI +
        ", CGI=" + cgi +
        ", SAI=" + sai +
        ", accuracyFulfilmentIndicator=" + accuracyFulfilmentIndicatorValue +
        ", sequenceNumber" + sequenceNumberValue +
        ", horizontalVelocityEstimate=" + velEstHorizontalSpeed +
        ", verticalVelocityEstimate=" + velEstVerticalSpeed +
        ", horizontalVelocityEstimateUncertainty=" + velEstHorSpeedUncertainty +
        ", verticalVelocityEstimateUncertainty=" + velEstVertSpeedUncertainty +
        ", velocityEstimateType=" + velocityEstTypeValue +
        ", velocityEstimateBearing=" + velEstBearing +
        ", pseudonymIndicator" + pseudonymIndicatorStr +
        ", servingNodeAddressMSCNumber=" + servingNodeAddressMSC +
        ", servingNodeAddressSGSNNumber=" + servingNodeAddressSGSN +
        ", servingNodeAddressMMENumber=" + servingNodeAddressMME +
        ", lcsClientID_LCSClientName=" + lcsClientID_Name +
        ", lcsClientID_LCSClientName_DCS=" + lcsClientID_DCS +
        ", lcsClientID_LCSClientName_FormatIndicator=" + lcsClientID_FI +
        ", lcsClientID_LCS_APN=" + lcsClientID_APN +
        ", lcsClientID_LCSClientDialedByMS=" + lcsClientID_LCSClientDialedByMS +
        ", lcsClientID_LCSClientExternalID=" + lcsClientID_LCSClientExternalID +
        ", lcsClientID_LCSClientInternalID=" + lcsClientID_LCSClientInternalID +
        ", lcsClientID_LCSClientType=" + lcsClientID_LCSClientType +
        ", lcsClientID_LCSRequestorID_DCS=" + lcsClientID_LCSRequestorID_DCS +
        ", lcsClientID_LCSRequestorID_FormatIndicator=" +  lcsClientID_LCSRequestorID_FI +
        ", lcsClientID_LCSRequestorID_IDString=" + lcsClientID_LCSRequestorID_IDString +
        ", lcsQoShorizontalAccuracy=" + lcsQoSHorizontalAccuracy +
        ", lcsQoSverticalAccuracy=" + lcsQoSVerticalAccuracy +
        ", lcsQoSResponseTimeCategory=" + lcsQoSResponseTimeCategory +
        ", lcsQoSVertCoordRequest=" + lcsQoSVerticalCoordRequest +
        ", lcsQoClass=" + lcsQosClass +
        ", lcsReferenceNumber=" + lcsRefNum +
        ", clientReferenceNumber=" + clientRefNum +
        ", lcsServiceTypeID" + lcsServiceTypeIDValue +
        ", barometricPressureMeasurement=" + barometricPressure +
        ", civicAddress=" + civicAddr +
        ", lcsEvent=" + lcsEventValue +
        ", locationEvent=" + locationEventValue +
        ", MSISDN=" + msisdnStr +
        ", IMEI=" + imeiStr +
        ", deferredmtlrData_EventType=" + deferredmtlrDataEventType +
        ", deferredmtlrData_LMSI=" + deferredmtlrDataLMSI +
        ", deferredmtlrData_NNN=" + deferredmtlrDataNNN +
        ", deferredmtlrData_MSCnum=" + deferredmtlrDataMSCnum +
        ", deferredmtlrData_SGSNnum=" + deferredmtlrDataSGSNnum +
        ", deferredmtlrData_AAAserverName=" + deferredmtlrDataAAAServerName +
        ", deferredmtlrData_MMEName=" + deferredmtlrDataMMEname +
        ", deferredmtlrData_GPRSNodeIndicator=" + deferredmtlrDataGPRSNodeInd +
        ", deferredmtlrData_TerminationCause=" + deferredmtlrDataTerminationCause +
        ", PeriodicReportingAmount=" + periodicLDRInfoReportingAmount +
        ", PeriodicReportingInterval=" + periodicLDRInfoReportingInterval +
        ", moLrShortCircuitIndicator=" + moLrShortCircuitIndicatorStr +
        ", ReportingPLMNList=" + reportingPLMNListArray +
        ", ReportingPLMNList_Prioritized=" + reportingPLMNListPrioritized +
        ", EUTRANPositioningData" + eUTRANPositioningDataStr +
        ", 1XRTTRCID" + oneXRttRcid +

        // MAP PSI response parameters parameters concerning detail records
        ", cgiSaiLaiMCC=" + cgiSaiLaiMCC +
        ", cgiSaiLaiMNC=" + cgiSaiLaiMNC +
        ", cgiSaiLaiLAC=" + cgiSaiLaiLAC +
        ", cgiSaiLaiCI=" + cgiSaiLaiCI +
        ", geographicLatitude=" + geogLat +
        ", geographicLongitude=" + geogLong +
        ", geographicUncertainty=" + geogUncertainty +
        ", geographicTypeOfShape=" + geogTypeOfShape +
        ", geodeticLatitude=" + geodLat +
        ", geodeticLongitude=" + geodLong +
        ", geodeticUncertainty=" + geodUncertainty +
        ", geodeticConfidence=" + geodConfidence +
        ", geodeticScreeningAndPresentationIndicators=" + geodScreeningAndPresentationInd +
        ", geodeticTypeOfShape=" + geodTypeOfShape +
        ", vlrNumber=" + vlrNum +
        ", mscNumber=" + mscNum +
        ", sgsnNumber=" + sgsnNum +
        ", saiPresent=" + saiPresent +
        ", lsaIdentity" + lsaIdentity +
        ", psiIsPLMNSignificantLSA" + psiIsPlmnSignificantLSA +
        ", raIdentity" + raIdentity +
        ", currentLocationRetrieved=" + currentLocationRetrieved +
        ", ageOfLocationInformation=" + aol +
        ", trackingAreaId=" + trackingAreaId +
        ", E-UTRAN-CGI=" + ecgi +
        ", E-UTRAN-CellPortionId=" + cellPortionIdentity +
        ", mnpStatus=" + mnpStatus +
        ", mnpRouteingNumber=" + mnpRouteingNum +
        ", mnpImsi=" + mnpImsi +
        ", mnpMsisdn=" + mnpMsisdn +
        ", subState=" + subState +
        ", nrCellGlobalId=" + nr5gsCellGlobalId +
        ", ageOfLocation=" + ageOfLocation +
        "]@" + super.hashCode();
  }

}
