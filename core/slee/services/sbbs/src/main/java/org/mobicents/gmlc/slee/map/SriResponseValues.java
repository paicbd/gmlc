package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NAEAPreferredCI;
import org.restcomm.protocols.ss7.map.api.service.callhandling.AllowedServices;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CUGCheckInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ExtendedRoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.RoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.UnavailabilityCause;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NumberPortabilityStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SriResponseValues implements Serializable {

    private static final long serialVersionUID = 1L;

    /*

    10 Call handling services

        10.1 MAP_SEND_ROUTING_INFORMATION service

            10.1.1 Definition
            This service is used between the Gateway MSC and the HLR. The service is invoked by the Gateway MSC to perform the interrogation of the HLR
            in order to route a call towards the called MS.
            This is a confirmed service using the primitives listed in table 10.1/1.
            This service is also used between the GMSC and the NPLR and between the gsmSCF and the HLR.


        10.1.2 Service primitives

            Table 10.1/1: MAP_SEND_ROUTING_INFORMATION parameters
            Parameter name	                                Request	    Indication	     Response      Confirm
            Invoke Id	                                        M	        M(=)	        M(=)	    M(=)
            Interrogation Type	                                M	        M(=)
            GMSC or gsmSCF Address	                            M	        M(=)
            MSISDN	                                            M	        M(=)	        C	        C(=)
            OR Interrogation	                                C	        C(=)
            OR Capability	                                    C	        C(=)
            CUG Interlock	                                    C	        C(=)	        C	        C(=)
            CUG Outgoing Access	                                C	        C(=)	        C	        C(=)
            Number of Forwarding	                            C	        C(=)
            Network Signal Info	                                C	        C(=)
            Supported CAMEL Phases	                            C	        C(=)	        C	        C(=)
            Suppress T-CSI	                                    C	        C(=)
            Offered CAMEL 4 CSIs	                            C	        C(=)
            Suppression of Announcement	                        C	        C(=)
            Call Reference Number	                            C	        C(=)
            Forwarding Reason	                                C	        C(=)
            Basic Service Group	                                C	        C(=)
            Basic Service Group 2	                            C	        C(=)
            Alerting Pattern	                                C	        C(=)
            CCBS Call	                                        C	        C(=)
            Supported CCBS Phase	                            C	        C(=)
            Additional Signal Info	                            C	        C(=)
            IST Support Indicator	                            C	        C(=)
            Pre-paging supported	                            C	        C(=)
            Call Diversion Treatment Indicator	                C	        C(=)
            Long FTN Supported	                                C	        C(=)
            Suppress VT-CSI	                                    C	        C(=)
            Suppress Incoming Call Barring	                    C	        C(=)
            SuppressMTSS	                                    C	        C(=)
            gsmSCF Initiated Call	                            C	        C(=)
            Network Signal Info 2	                            C	        C(=)
            MT Roaming Retry Supported	                        U	        C(=)
            Call Priority	                                    U	        C(=)
            IMSI			                                                                C	        C(=)
            MSRN			                                                                C	        C(=)
            Forwarding Data			                                                        C	        C(=)
            Forwarding Interrogation Required			                                    C	        C(=)
            VMSC address			                                                        C	        C(=)
            ReleaseResourcesSupported			                                            C	        C(=)
            GMSC Camel Subscription Info			                                        C	        C(=)
            Location Information			                                                C	        C(=)
            Subscriber State			                                                    C	        C(=)
            Basic Service Code			                                                    C	        C(=)
            CUG Subscription Flag			                                                C	        C(=)
            North American Equal Access preferred Carrier Id			                    U	        C(=)
            User error			                                                            C	        C(=)
            SS-List			                                                                U	        C(=)
            CCBS Target			                                                            C	        C(=)
            Keep CCBS Call Indicator			                                            C	        C(=)
            IST Alert Timer			                                                        C	        C(=)
            Number Portability Status			                                            U	        C(=)
            Supported CAMEL Phases in VMSC			                                        C
            Offered CAMEL 4 CSIs in VMSC			                                        C	        C(=)
            MSRN 2			                                                                C	        C(=)
            Forwarding Data 2			                                                    C	        C(=)
            SS-List 2			                                                            C	        C(=)
            Basic Service Code 2			                                                C	        C(=)
            Allowed Services			                                                    C	        C(=)
            Unavailability Cause			                                                C	        C(=)
            Provider error				                                                                O
            GSM Bearer Capability			                                                U	        C(=)
     */

    private IMSI imsi;                              // International Mobile Subscriber Identity defined in 3GPP TS 23.003.
    private ISDNAddressString vmscAddress;          // ISDN number of visited MSC.
    // other parameters not used by this service on this platform for now.
    private ExtendedRoutingInfo extendedRoutingInfo;
    private CUGCheckInfo cugCheckInfo;
    Boolean cugSubscriptionFlag;
    ArrayList<SSCode> ssCodeArrayList;
    ExtBasicServiceCode extBasicServiceCode;
    Boolean forwardingInterrogationRequired;
    MAPExtensionContainer mapExtensionContainer;
    NAEAPreferredCI naeaPreferredCI;
    ISDNAddressString msisdn;
    NumberPortabilityStatus numberPortabilityStatus;
    Integer istAlertTimer;
    SupportedCamelPhases supportedCamelPhases;
    OfferedCamel4CSIs offeredCamel4CSIs;
    RoutingInfo routingInfo;    // This is used as RoutingInfo parameter for V2 and as RoutingInfo2 parameter for MAP V3
    ArrayList<SSCode> ssCodeArrayList2;
    ExtBasicServiceCode extBasicServiceCode2;
    AllowedServices allowedServices;
    UnavailabilityCause unavailabilityCause;
    Boolean releaseResourcesSupported;
    ExternalSignalInfo externalSignalInfo;
    Long mapProtocolVersion;

    public SriResponseValues() {
        super();
    }

    public SriResponseValues(IMSI imsi, ISDNAddressString vmscAddress) {
        super();
        this.imsi = imsi;
        this.vmscAddress = vmscAddress;
    }

    public IMSI getImsi() {
        return imsi;
    }

    public void setImsi(IMSI imsi) {
        this.imsi = imsi;
    }

    public ISDNAddressString getVmscAddress() {
        return vmscAddress;
    }

    public void setVmscAddress(ISDNAddressString vmscAddress) {
        this.vmscAddress = vmscAddress;
    }

    public ExtendedRoutingInfo getExtendedRoutingInfo() {
        return extendedRoutingInfo;
    }

    public void setExtendedRoutingInfo(ExtendedRoutingInfo extendedRoutingInfo) {
        this.extendedRoutingInfo = extendedRoutingInfo;
    }

    public CUGCheckInfo getCugCheckInfo() {
        return cugCheckInfo;
    }

    public void setCugCheckInfo(CUGCheckInfo cugCheckInfo) {
        this.cugCheckInfo = cugCheckInfo;
    }

    public Boolean getCugSubscriptionFlag() {
        return cugSubscriptionFlag;
    }

    public void setCugSubscriptionFlag(Boolean cugSubscriptionFlag) {
        this.cugSubscriptionFlag = cugSubscriptionFlag;
    }

    public ArrayList<SSCode> getSsCodeArrayList() {
        return ssCodeArrayList;
    }

    public void setSsCodeArrayList(ArrayList<SSCode> ssCodeArrayList) {
        this.ssCodeArrayList = ssCodeArrayList;
    }

    public ExtBasicServiceCode getExtBasicServiceCode() {
        return extBasicServiceCode;
    }

    public void setExtBasicServiceCode(ExtBasicServiceCode extBasicServiceCode) {
        this.extBasicServiceCode = extBasicServiceCode;
    }

    public Boolean getForwardingInterrogationRequired() {
        return forwardingInterrogationRequired;
    }

    public void setForwardingInterrogationRequired(Boolean forwardingInterrogationRequired) {
        this.forwardingInterrogationRequired = forwardingInterrogationRequired;
    }

    public MAPExtensionContainer getMapExtensionContainer() {
        return mapExtensionContainer;
    }

    public void setMapExtensionContainer(MAPExtensionContainer mapExtensionContainer) {
        this.mapExtensionContainer = mapExtensionContainer;
    }

    public NAEAPreferredCI getNaeaPreferredCI() {
        return naeaPreferredCI;
    }

    public void setNaeaPreferredCI(NAEAPreferredCI naeaPreferredCI) {
        this.naeaPreferredCI = naeaPreferredCI;
    }

    public ISDNAddressString getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(ISDNAddressString msisdn) {
        this.msisdn = msisdn;
    }

    public NumberPortabilityStatus getNumberPortabilityStatus() {
        return numberPortabilityStatus;
    }

    public void setNumberPortabilityStatus(NumberPortabilityStatus numberPortabilityStatus) {
        this.numberPortabilityStatus = numberPortabilityStatus;
    }

    public Integer getIstAlertTimer() {
        return istAlertTimer;
    }

    public void setIstAlertTimer(Integer istAlertTimer) {
        this.istAlertTimer = istAlertTimer;
    }

    public SupportedCamelPhases getSupportedCamelPhases() {
        return supportedCamelPhases;
    }

    public void setSupportedCamelPhases(SupportedCamelPhases supportedCamelPhases) {
        this.supportedCamelPhases = supportedCamelPhases;
    }

    public OfferedCamel4CSIs getOfferedCamel4CSIs() {
        return offeredCamel4CSIs;
    }

    public void setOfferedCamel4CSIs(OfferedCamel4CSIs offeredCamel4CSIs) {
        this.offeredCamel4CSIs = offeredCamel4CSIs;
    }

    public RoutingInfo getRoutingInfo() {
        return routingInfo;
    }

    public void setRoutingInfo(RoutingInfo routingInfo) {
        this.routingInfo = routingInfo;
    }

    public ArrayList<SSCode> getSsCodeArrayList2() {
        return ssCodeArrayList2;
    }

    public void setSsCodeArrayList2(ArrayList<SSCode> ssCodeArrayList2) {
        this.ssCodeArrayList2 = ssCodeArrayList2;
    }

    public ExtBasicServiceCode getExtBasicServiceCode2() {
        return extBasicServiceCode2;
    }

    public void setExtBasicServiceCode2(ExtBasicServiceCode extBasicServiceCode2) {
        this.extBasicServiceCode2 = extBasicServiceCode2;
    }

    public AllowedServices getAllowedServices() {
        return allowedServices;
    }

    public void setAllowedServices(AllowedServices allowedServices) {
        this.allowedServices = allowedServices;
    }

    public UnavailabilityCause getUnavailabilityCause() {
        return unavailabilityCause;
    }

    public void setUnavailabilityCause(UnavailabilityCause unavailabilityCause) {
        this.unavailabilityCause = unavailabilityCause;
    }

    public Boolean getReleaseResourcesSupported() {
        return releaseResourcesSupported;
    }

    public void setReleaseResourcesSupported(Boolean releaseResourcesSupported) {
        this.releaseResourcesSupported = releaseResourcesSupported;
    }

    public ExternalSignalInfo getExternalSignalInfo() {
        return externalSignalInfo;
    }

    public void setExternalSignalInfo(ExternalSignalInfo externalSignalInfo) {
        this.externalSignalInfo = externalSignalInfo;
    }

    public Long getMapProtocolVersion() {
        return mapProtocolVersion;
    }

    public void setMapProtocolVersion(Long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    @Override
    public String toString() {
        return "SriResponseValues{" +
            "imsi=" + imsi +
            ", vmscAddress=" + vmscAddress +
            ", extendedRoutingInfo=" + extendedRoutingInfo +
            ", cugCheckInfo=" + cugCheckInfo +
            ", cugSubscriptionFlag=" + cugSubscriptionFlag +
            ", ssCodeArrayList=" + ssCodeArrayList +
            ", extBasicServiceCode=" + extBasicServiceCode +
            ", forwardingInterrogationRequired=" + forwardingInterrogationRequired +
            ", mapExtensionContainer=" + mapExtensionContainer +
            ", naeaPreferredCI=" + naeaPreferredCI +
            ", msisdn=" + msisdn +
            ", numberPortabilityStatus=" + numberPortabilityStatus +
            ", istAlertTimer=" + istAlertTimer +
            ", supportedCamelPhases=" + supportedCamelPhases +
            ", offeredCamel4CSIs=" + offeredCamel4CSIs +
            ", routingInfo=" + routingInfo +
            ", ssCodeArrayList2=" + ssCodeArrayList2 +
            ", extBasicServiceCode2=" + extBasicServiceCode2 +
            ", allowedServices=" + allowedServices +
            ", unavailabilityCause=" + unavailabilityCause +
            ", releaseResourcesSupported=" + releaseResourcesSupported +
            ", externalSignalInfo=" + externalSignalInfo +
            ", mapProtocolVersion=" + mapProtocolVersion +
            '}';
    }
}
