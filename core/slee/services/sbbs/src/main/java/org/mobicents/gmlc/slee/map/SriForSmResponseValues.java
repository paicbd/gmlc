package org.mobicents.gmlc.slee.map;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;

import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;

import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 * @author <a href="mailto:aferreiraguido@gmail.com"> Alejandro Ferreira Guido </a>
 */
public class SriForSmResponseValues implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Charset isoCharset = Charset.forName("ISO-8859-1");

  /*
    3GPP TS 29.002 MAP Specification v15.2.0 (2018-03)

    12.1	MAP-SEND-ROUTING-INFO-FOR-SM service
        12.1.1	Definition
            This service is used between the gateway MSC and the HLR to retrieve the routing information
            needed for routing the short message to the servicing MSC or MME but not both, or SGSN,
            or (for T4-device triggering via the IMS) IP-SM-GW. This service is also used between the gateway
            MSC and SMS Router, and SMS Router and HLR in order to enforce routing of the SM delivery via the
            HPLMN of the receiving MS. This service is also used between HLR and IP-SM-GW, and between
            IP-SM-GW and HLR  in order to allow MT-SM delivery (other than T4-device triggering) via the IMS.

            This service is also used with an IWF interfacing the S6c interface.
            The MAP-SEND-ROUTING-INFO-FOR-SM is a confirmed service using the primitives from table 12.1/1.

    12.1.2	Service primitives

        Table 12.1/1: MAP-SEND-ROUTING-INFO-FOR-SM
        Parameter name	                                Request	    Indication	        Response    Confirm
        Invoke Id	                                        M	            M(=)	        M(=)	   M(=)
        MSISDN	                                            M	            M(=)
        SM-RP-PRI	                                        M	            M(=)
        Service Centre Address	                            M	            M(=)
        SM-RP-MTI	                                        C	            C(=)
        SM-RP-SMEA	                                        C	            C(=)
        GPRS Support Indicator	                            C	            C(=)
        SM-Delivery Not Intended	                        U	            C(=)
        IP-SM-GW Guidance Support Indicator	                U	            C(=)
        Single Attempt Delivery	                            C	            C(=)
        IMSI	                                            C	            C(=)	        C	        C(=)
        Correlation ID	                                    C	            C(=)
        T4 Trigger Indicator	                            C	            C(=)
        Network Node Number			                                                        C	        C(=)
        Network Node Diameter Address			                                            C	        C(=)
        LMSI			                                                                    C	        C(=)
        GPRS Node Indicator			                                                        C	        C(=)
        Additional Number			                                                        C	        C(=)
        Additional Network Node Diameter Address			                                C	        C(=)
        IP-SM-GW Guidance			                                                        U	        C(=)
        Third Number			                                                            C	        C(=)
        Third Network Node Diameter Address			                                        C	        C(=)
        IMS Node Indicator			                                                        C	        C(=)
        User error			                                                                C	        C(=)
        Provider error				                                                                      O

        ? (M): mandatory parameter.
        ? (O): provider option.
        ? (C): conditional parameter (i.e. it will always be present in the indication type primitive
               if it was present in the corresponding request type primitive).
        ? (U): TC-user optional parameter.
        ? (=): the parameter must have the same value in the indication primitive as provided in the
               corresponding request primitive.
        ? A blank Indicates that the parameter is not applicable.

  */

    private IMSI imsi;                              // International Mobile Subscriber Identity defined in 3GPP TS 23.003.
    private ISDNAddressString networkNodeNumber;    // ISDN number of LCS target node (MSC or MME, SGSN, or IP-SM-GW) or of an LCS Router.
    // Network Node Diameter Address
    private LMSI lmsi;                              // Local MS identity allocated by the VLR to a given subscriber for internal management of data in the VLR
    private Boolean gprsNodeIndicator;              // Indication that the Network Node Number received from HLR, etc. is to be considered as the SGSN number.
    private AdditionalNumber additionalNumber;      // This parameter refers to the ISDN number of an additional LCS target node (MSC or MME or SGSN) or of an LCS Router.
    // Additional Network Node Diameter Address
    // IP-SM-GW Guidance
    // Third Number
    // Third Network Node Diameter Address
    // IMS Node Indicator

    public SriForSmResponseValues() {
        super();
    }

    public SriForSmResponseValues(IMSI imsi, ISDNAddressString networkNodeNumber) {
        super();
        this.imsi = imsi;
        this.networkNodeNumber = networkNodeNumber;
    }

    public SriForSmResponseValues(IMSI imsi, ISDNAddressString networkNodeNumber, LMSI lmsi, Boolean gprsNodeIndicator, AdditionalNumber additionalNumber) {
        super();
        this.imsi = imsi;
        this.networkNodeNumber = networkNodeNumber;
        this.lmsi = lmsi;
        this.gprsNodeIndicator = gprsNodeIndicator;
        this.additionalNumber = additionalNumber;
    }

    public IMSI getImsi() {
        return imsi;
    }

    public void setImsi(IMSI imsi) {
        this.imsi = imsi;
    }

    public ISDNAddressString getNetworkNodeNumber() {
        return networkNodeNumber;
    }

    public void setNetworkNodeNumber(ISDNAddressString networkNodeNumber) {
        this.networkNodeNumber = networkNodeNumber;
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

    public AdditionalNumber getAdditionalNumber() {
        return additionalNumber;
    }

    public void setAdditionalNumber(AdditionalNumber additionalNumber) {
        this.additionalNumber = additionalNumber;
    }

    @Override
    public String toString() {
        return "SriForSmResponseValues{" +
            "imsi=" + imsi +
            ", networkNodeNumber=" + networkNodeNumber +
            ", lmsi=" + lmsi +
            ", gprsNodeIndicator=" + gprsNodeIndicator +
            ", additionalNumber=" + additionalNumber +
            '}';
    }
}
