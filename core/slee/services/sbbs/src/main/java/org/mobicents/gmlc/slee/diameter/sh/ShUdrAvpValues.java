package org.mobicents.gmlc.slee.diameter.sh;

import net.java.slee.resource.diameter.sh.events.avp.CurrentLocationType;
import net.java.slee.resource.diameter.sh.events.avp.DataReferenceType;
import net.java.slee.resource.diameter.sh.events.avp.IdentitySetType;
import net.java.slee.resource.diameter.sh.events.avp.RequestedDomainType;
import net.java.slee.resource.diameter.sh.events.avp.SessionPriorityType;
import net.java.slee.resource.diameter.sh.events.avp.UserIdentityAvp;

import java.io.Serializable;
import java.util.Arrays;

public class ShUdrAvpValues implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final int DIAMETER_Sh_VENDOR_ID = 10415;

  /*
  3GPP TS 29.329 v15.1.0 reference
    6.1.1	User-Data-Request (UDR) Command
      The User-Data-Request (UDR) command, indicated by the Command-Code field set to 306 and the ?R? bit set in the Command Flags field,
      is sent by a Diameter client to a Diameter server in order to request user data.

      Message Format

      < User-Data -Request> ::=	< Diameter Header: 306, REQ, PXY, 16777217 >

								< Session-Id >
									[ DRMP ]
								    { Vendor-Specific-Application-Id }
                                    { Auth-Session-State }
                                    { Origin-Host }
                                    { Origin-Realm }
                                    [ Destination-Host ]
								    { Destination-Realm }
                                    *[ Supported-Features ]
                                    { User-Identity }
                                    [ Wildcarded-Public-Identity ]
                                    [ Wildcarded-IMPU ]
                                    [ Server-Name ]
                                    *[ Service-Indication ]
                                    *{ Data-Reference }
                                    *[ Identity-Set ]
                                    [ Requested-Domain ]
                                    [ Current-Location ]
                                    *[ DSAI-Tag ]
                                    [ Session-Priority ]
                                    [ User-Name ]
                                    [ Requested-Nodes ]
                                    [ Serving-Node-Indication ]
                                    [ Pre-paging-Supported ]
                                    [ Local-Time-Zone-Indication ]
                                    [ UDR-Flags ]
                                    [ Call-Reference-Info ]
                                    [ OC-Supported-Features ]
								    *[ AVP ]
									*[ Proxy-Info ]
									*[ Route-Record ]
   */

  private UserIdentityAvp userIdentityAvp;
  private String wildcardedPublicIdentity;
  private String wildcardedIMPU;
  private String serverName;
  private byte[] serviceIndication;
  private DataReferenceType dataReferenceType;
  private IdentitySetType identitySetType;
  private RequestedDomainType requestedDomainType;
  private CurrentLocationType currentLocationType;
  private byte[] dsaiTag;
  private SessionPriorityType sessionPriorityType;
  private String userName;
  private Long requestedNodes;
  //private ServingNodeIndication servingNodeIndication; //not implemented yet in jain-slee.diameter
  //private PrepagingSupported prepagingSupported; //not implemented yet in jain-slee.diameter
  private Long udrFlags;
  //private CallReferenceInfo callReferenceInfo; //not implemented yet in jain-slee.diameter
  //private OCSupportedFeatures ocSupportedFeatures //not implemented yet in jain-slee.diameter


  public ShUdrAvpValues() {
    super();
  }

  public ShUdrAvpValues(UserIdentityAvp userIdentityAvp, String wildcardedPublicIdentity, String wildcardedIMPU,
                        String serverName, byte[] serviceIndication, DataReferenceType dataReferenceType, IdentitySetType identitySetType,
                        RequestedDomainType requestedDomainType, CurrentLocationType currentLocationType, byte[] dsaiTag, SessionPriorityType sessionPriorityType,
                        String userName, Long requestedNodes, Long udrFlags) {
    super();
    this.userIdentityAvp = userIdentityAvp;
    this.wildcardedPublicIdentity = wildcardedPublicIdentity;
    this.wildcardedIMPU = wildcardedIMPU;
    this.serverName = serverName;
    this.serviceIndication = serviceIndication;
    this.dataReferenceType = dataReferenceType;
    this.identitySetType = identitySetType;
    this.requestedDomainType = requestedDomainType;
    this.currentLocationType = currentLocationType;
    this.dsaiTag = dsaiTag;
    this.sessionPriorityType = sessionPriorityType;
    this.userName = userName;
    this.requestedNodes = requestedNodes;
    this.udrFlags = udrFlags;
  }

  public UserIdentityAvp getUserIdentityAvp() {
    return userIdentityAvp;
  }

  public void setUserIdentityAvp(UserIdentityAvp userIdentityAvp) {
    this.userIdentityAvp = userIdentityAvp;
  }

  public String getWildcardedPublicIdentity() {
    return wildcardedPublicIdentity;
  }

  public void setWildcardedPublicIdentity(String wildcardedPublicIdentity) {
    this.wildcardedPublicIdentity = wildcardedPublicIdentity;
  }

  public String getWildcardedIMPU() {
    return wildcardedIMPU;
  }

  public void setWildcardedIMPU(String wildcardedIMPU) {
    this.wildcardedIMPU = wildcardedIMPU;
  }

  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  public byte[] getServiceIndication() {
    return serviceIndication;
  }

  public void setServiceIndication(byte[] serviceIndication) {
    this.serviceIndication = serviceIndication;
  }

  public DataReferenceType getDataReferenceType() {
    return dataReferenceType;
  }

  public void setDataReferenceType(DataReferenceType dataReferenceType) {
    this.dataReferenceType = dataReferenceType;
  }

  public IdentitySetType getIdentitySetType() {
    return identitySetType;
  }

  public void setIdentitySetType(IdentitySetType identitySetType) {
    this.identitySetType = identitySetType;
  }

  public RequestedDomainType getRequestedDomainType() {
    return requestedDomainType;
  }

  public void setRequestedDomainType(RequestedDomainType requestedDomainType) {
    this.requestedDomainType = requestedDomainType;
  }

  public CurrentLocationType getCurrentLocationType() {
    return currentLocationType;
  }

  public void setCurrentLocationType(CurrentLocationType currentLocationType) {
    this.currentLocationType = currentLocationType;
  }

  public byte[] getDsaiTag() {
    return dsaiTag;
  }

  public void setDsaiTag(byte[] dsaiTag) {
    this.dsaiTag = dsaiTag;
  }

  public SessionPriorityType getSessionPriorityType() {
    return sessionPriorityType;
  }

  public void setSessionPriorityType(SessionPriorityType sessionPriorityType) {
    this.sessionPriorityType = sessionPriorityType;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getRequestedNodes() {
    return requestedNodes;
  }

  public void setRequestedNodes(Long requestedNodes) {
    this.requestedNodes = requestedNodes;
  }

  public Long getUdrFlags() {
    return udrFlags;
  }

  public void setUdrFlags(Long udrFlags) {
    this.udrFlags = udrFlags;
  }


  @Override
  public String toString() {
    return "ShUdrAvpValues{" +
        "userIdentityAvp=" + userIdentityAvp +
        ", wildcardedPublicIdentity='" + wildcardedPublicIdentity + '\'' +
        ", wildcardedIMPU='" + wildcardedIMPU + '\'' +
        ", serverName='" + serverName + '\'' +
        ", serviceIndication=" + Arrays.toString(serviceIndication) +
        ", dataReferenceType=" + dataReferenceType +
        ", identitySetType=" + identitySetType +
        ", requestedDomainType=" + requestedDomainType +
        ", currentLocationType=" + currentLocationType +
        ", dsaiTag=" + Arrays.toString(dsaiTag) +
        ", sessionPriorityType=" + sessionPriorityType +
        ", userName='" + userName + '\'' +
        ", requestedNodes=" + requestedNodes +
        ", udrFlags=" + udrFlags +
        '}';
  }
}
