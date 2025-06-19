package org.mobicents.gmlc.slee.cdr.plain;

import net.java.slee.resource.diameter.slg.events.avp.LCSQoSClass;
import net.java.slee.resource.diameter.slg.events.avp.LocationEvent;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.mobicents.gmlc.GmlcPropertiesManagement;
import org.mobicents.gmlc.slee.MobileCoreNetworkInterfaceSbb;
import org.mobicents.gmlc.slee.cdr.CDRInterface;
import org.mobicents.gmlc.slee.cdr.GMLCCDRState;
import org.mobicents.gmlc.slee.cdr.RecordStatus;
import org.mobicents.gmlc.slee.cdr.model.CDRModel;
import org.mobicents.gmlc.slee.cdr.tasks.TaskCDR;
import org.mobicents.gmlc.slee.cdr.tasks.TaskManager;
import org.mobicents.gmlc.slee.diameter.sh.LocalTimeZone;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.mobicents.gmlc.slee.primitives.LocationInformation5GS;
import org.mobicents.gmlc.slee.primitives.Polygon;
import org.mobicents.gmlc.slee.primitives.RoutingAreaId;
import org.mobicents.gmlc.slee.primitives.RoutingAreaIdImpl;
import org.mobicents.gmlc.slee.primitives.TrackingAreaId;
import org.mobicents.gmlc.slee.primitives.TrackingAreaIdImpl;
import org.restcomm.protocols.ss7.indicator.AddressIndicator;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;

import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSQoS;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationEPS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MNPInfoRes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

import javax.slee.ActivityContextInterface;
import javax.slee.CreateException;
import javax.slee.SbbContext;
import javax.slee.serviceactivity.ServiceStartedEvent;
import javax.xml.bind.DatatypeConverter;
import java.awt.geom.Point2D;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mobicents.gmlc.slee.gis.GeographicHelper.polygonCentroid;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:bbaranow@redhat.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public abstract class CDRGeneratorSbb extends MobileCoreNetworkInterfaceSbb implements CDRInterface {

    private static final GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();

    private static final Logger cdrTracer = Logger.getLogger(CDRGeneratorSbb.class);

    private static final String CDR_GENERATED_TO = "Textfile";

    protected static final DecimalFormat coordinatesFormat = new DecimalFormat("#0.000000");
    protected static final DecimalFormat uncertaintyFormat = new DecimalFormat("#0.000000");

    private final TaskManager taskManager = new TaskManager();

    private final boolean sendCdrToGlass = gmlcPropertiesManagement.getGlaasEnabled();

    public CDRGeneratorSbb() throws UnknownHostException {
        super();
    }

    // -------------------- SLEE Stuff -----------------------
    // --------------- CDRInterface methods ------------------
    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.cdr.CDRInterface#init(boolean)
     */
    @Override
    public void init(final boolean reset) {
        super.logger.info("Setting CDR_GENERATED_TO to " + CDR_GENERATED_TO);
    }

    /* (non-Javadoc)
     * @see org.mobicents.gmlc.slee.cdr.CDRInterface#createRecord(org.mobicents.gmlc.slee.cdr.Status)
     */
    @Override
    public void createRecord(RecordStatus outcome) {

        GMLCCDRState state = getState();

        if (state.isGenerated()) {
            super.logger.severe("");
        } else {
            if (super.logger.isFineEnabled()) {
                super.logger.fine("Generating record, status '" + outcome + "' for '" + state + "'");
            }
            DateTime startTime = state.getDialogStartTime();
            if (startTime != null) {
                DateTime endTime = DateTime.now();
                Long duration = endTime.getMillis() - startTime.getMillis();
                state.setDialogEndTime(endTime);
                state.setDialogDuration(duration);
            }
            state.setRecordStatus(outcome);
            state.setGenerated(true);
            this.setState(state);
            String data = this.toString(state);
            if (this.logger.isFineEnabled()) {
                this.logger.fine(data);
            } else {
                this.cdrTracer.debug(data);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.cdr.CDRInterface#setState(org.mobicents.gmlc.slee.cdr.GMLCCDRState)
     */
    @Override
    public void setState(GMLCCDRState state) {
        this.setGMLCCDRState(state);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.cdr.CDRInterface#getState()
     */
    @Override
    public GMLCCDRState getState() {
        return this.getGMLCCDRState();
    }

    // CMPs
    public abstract GMLCCDRState getGMLCCDRState();

    public abstract void setGMLCCDRState(GMLCCDRState state);

    public void onStartServiceEvent(ServiceStartedEvent event, ActivityContextInterface aci) {
        this.init(true);
    }

    // --------------- SBB callbacks ---------------

    /*
     * (non-Javadoc)
     *
     * @see javax.slee.Sbb#sbbCreate()
     */
    @Override
    public void sbbCreate() throws CreateException {
        this.setGMLCCDRState(new GMLCCDRState());
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.slee.Sbb#setSbbContext(javax.slee.SbbContext)
     */
    @Override
    public void setSbbContext(SbbContext ctx) {
        super.setSbbContext(ctx);
        super.logger = super.sbbContext.getTracer(TRACER_NAME);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.slee.Sbb#unsetSbbContext()
     */
    @Override
    public void unsetSbbContext() {
        super.unsetSbbContext();
    }

    // -------- helper methods
    private static final String SEPARATOR = "|";

    /**
     * @param gmlcCdrState
     * @return
     */
    protected String toString(GMLCCDRState gmlcCdrState) {

        CDRModel cdrModel = new CDRModel();

        final StringBuilder stringBuilder = new StringBuilder(); //StringBuilder is faster than StringBuffer

        final Timestamp time_stamp = new Timestamp(System.currentTimeMillis());
        String time_stamp_string = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time_stamp);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(time_stamp.getTime()));
        String time = new SimpleDateFormat("HH:mm:ss").format(new Time(time_stamp.getTime()));

        if(sendCdrToGlass)
            taskManager.start();

        // TIMESTAMP
        cdrModel.setTimeStamp(time_stamp_string);
        stringBuilder.append(cdrModel.getTimeStamp()).append(SEPARATOR);

        cdrModel.setCdrDate(date);
        cdrModel.setCdrTime(time);

        // ID
        stringBuilder.append(gmlcCdrState.getId()).append(SEPARATOR);
        cdrModel.setGmlcId(gmlcCdrState.getId());

        // HTTP Request (cURL) Username
        if (gmlcCdrState.getCurlUser() != null) {
            cdrModel.setCurlUser(gmlcCdrState.getCurlUser());
            stringBuilder.append(cdrModel.getCurlUser()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
            cdrModel.setCurlUser("");
        }

        // RECORD STATUS
        if (gmlcCdrState.getRecordStatus() != null) {
            cdrModel.setRecordStatus(gmlcCdrState.getRecordStatus().toString());
            stringBuilder.append(gmlcCdrState.getRecordStatus().toString()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
            cdrModel.setRecordStatus("");
        }

        // STATUS CODE
        if (gmlcCdrState.getStatusCode() != null) {
            cdrModel.setStatusCode(gmlcCdrState.getStatusCode().toString());
            stringBuilder.append(gmlcCdrState.getStatusCode()).append(SEPARATOR);
        } else {
            cdrModel.setStatusCode("");
            stringBuilder.append(SEPARATOR);
        }

        // LOCAL DIALOG_ID
        if (gmlcCdrState.getLocalDialogId() != null) {
            cdrModel.setLocalDialogId(gmlcCdrState.getLocalDialogId().toString());
            stringBuilder.append(gmlcCdrState.getLocalDialogId()).append(SEPARATOR);
        } else {
            cdrModel.setLocalDialogId("");
            stringBuilder.append(SEPARATOR);
        }

        // REMOTE DIALOG_ID
        if (gmlcCdrState.getRemoteDialogId() != null) {
            cdrModel.setRemoteDialogId(gmlcCdrState.getRemoteDialogId().toString());
            stringBuilder.append(gmlcCdrState.getRemoteDialogId()).append(SEPARATOR);
        } else {
            cdrModel.setRemoteDialogId("");
            stringBuilder.append(SEPARATOR);
        }

        // DIALOG_DURATION
        Long dialogDuration = gmlcCdrState.getDialogDuration();
        if (dialogDuration != null) {
            cdrModel.setDialogDuration(dialogDuration.toString());
            stringBuilder.append(dialogDuration).append(SEPARATOR);
        } else {
            cdrModel.setDialogDuration("");
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LOCAL Address
         */
        SccpAddress localAddress = gmlcCdrState.getLocalAddress();
        if (localAddress != null) {
            AddressIndicator addressIndicator = localAddress.getAddressIndicator();

            // LOCAL SPC
            if (addressIndicator.isPCPresent()) {
                cdrModel.setLocalSPC(localAddress.getSignalingPointCode() + "");
                stringBuilder.append(localAddress.getSignalingPointCode()).append(SEPARATOR);
            } else {
                cdrModel.setLocalSPC("");
                stringBuilder.append(SEPARATOR);
            }

            // LOCAL SSN
            if (addressIndicator.isSSNPresent()) {
                cdrModel.setLocalSSN(localAddress.getSubsystemNumber() + "");
                stringBuilder.append(localAddress.getSubsystemNumber()).append(SEPARATOR);
            } else {
                cdrModel.setLocalSSN("");
                stringBuilder.append(SEPARATOR);
            }
            // LOCAL ROUTING INDICATOR
            if (addressIndicator.getRoutingIndicator() != null) {
                cdrModel.setLocalRoutingIndicator((byte) addressIndicator.getRoutingIndicator().getValue() + "");
                stringBuilder.append((byte) addressIndicator.getRoutingIndicator().getValue()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
                cdrModel.setLocalRoutingIndicator("");
            }

            // Local GLOBAL TITLE
            GlobalTitle localAddressGlobalTitle = localAddress.getGlobalTitle();
            // Local GLOBAL TITLE INDICATOR
            if (localAddressGlobalTitle != null && localAddressGlobalTitle.getGlobalTitleIndicator() != null) {
                cdrModel.setLocalGlobalTitleIndicator((byte) localAddressGlobalTitle.getGlobalTitleIndicator().getValue() + "");
                stringBuilder.append((byte) localAddressGlobalTitle.getGlobalTitleIndicator().getValue()).append(SEPARATOR);
            } else {
                cdrModel.setLocalGlobalTitleIndicator("");
                stringBuilder.append(SEPARATOR);
            }
            // Local GLOBAL TITLE DIGITS
            if (localAddressGlobalTitle != null && localAddressGlobalTitle.getDigits() != null) {
                cdrModel.setLocalGlobalTitleDigits(localAddressGlobalTitle.getDigits());
                stringBuilder.append(localAddressGlobalTitle.getDigits()).append(SEPARATOR);
            } else {
                cdrModel.setLocalGlobalTitleDigits("");
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * REMOTE Address
         */
        SccpAddress remoteAddress = gmlcCdrState.getRemoteAddress();
        if (remoteAddress != null) {
            AddressIndicator addressIndicator = remoteAddress.getAddressIndicator();

            // REMOTE SPC
            if (addressIndicator.isPCPresent()) {
                cdrModel.setRemoteSPC(remoteAddress.getSignalingPointCode() + "");
                stringBuilder.append(remoteAddress.getSignalingPointCode()).append(SEPARATOR);
            } else {
                cdrModel.setRemoteSPC("");
                stringBuilder.append(SEPARATOR);
            }

            // REMOTE SSN
            if (addressIndicator.isSSNPresent()) {
                cdrModel.setRemoteSSN(remoteAddress.getSubsystemNumber() + "");
                stringBuilder.append(remoteAddress.getSubsystemNumber()).append(SEPARATOR);
            } else {
                cdrModel.setRemoteSSN("");
                stringBuilder.append(SEPARATOR);
            }

            // REMOTE ROUTING INDICATOR
            if (addressIndicator.getRoutingIndicator() != null) {
                cdrModel.setRemoteRoutingIndicator((byte) addressIndicator.getRoutingIndicator().getValue() + "");
                stringBuilder.append((byte) addressIndicator.getRoutingIndicator().getValue()).append(SEPARATOR);
            } else {
                cdrModel.setRemoteRoutingIndicator("");
                stringBuilder.append(SEPARATOR);
            }

            // REMOTE GLOBAL TITLE
            GlobalTitle remoteAddressGlobalTitle = remoteAddress.getGlobalTitle();
            if (remoteAddressGlobalTitle != null && remoteAddressGlobalTitle.getGlobalTitleIndicator() != null) {
                // REMOTE GLOBAL TITLE INDICATOR
                cdrModel.setRemoteGlobalTitleIndicator((byte) remoteAddressGlobalTitle.getGlobalTitleIndicator().getValue() + "");
                stringBuilder.append((byte) remoteAddressGlobalTitle.getGlobalTitleIndicator().getValue()).append(SEPARATOR);
            } else {
                cdrModel.setRemoteGlobalTitleIndicator("");
                stringBuilder.append(SEPARATOR);
            }
            // REMOTE GLOBAL TITLE DIGITS
            if (remoteAddressGlobalTitle != null && remoteAddressGlobalTitle.getDigits() != null) {
                cdrModel.setRemoteGlobalTitleDigits(remoteAddressGlobalTitle.getDigits());
                stringBuilder.append(remoteAddressGlobalTitle.getDigits()).append(SEPARATOR);
            } else {
                cdrModel.setRemoteGlobalTitleDigits("");
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * ISDN Address
         */
        ISDNAddressString isdnAddressString = gmlcCdrState.getISDNAddressString();
        if (isdnAddressString != null) {
            // ISDN ADDRESS NATURE
            cdrModel.setIsdnAddressNature((byte) isdnAddressString.getAddressNature().getIndicator() + "");
            stringBuilder.append((byte) isdnAddressString.getAddressNature().getIndicator()).append(SEPARATOR);
            // ISDN NUMBERING PLAN INDICATOR
            cdrModel.setIsdnNumberingPlanIndicator((byte) isdnAddressString.getNumberingPlan().getIndicator() + "");
            stringBuilder.append((byte) isdnAddressString.getNumberingPlan().getIndicator()).append(SEPARATOR);
            // ISDN ADDRESS DIGITS
            cdrModel.setIsdnAddressDigits(isdnAddressString.getAddress());
            stringBuilder.append(isdnAddressString.getAddress()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /*
         * Diameter Session Id
         */
        String diameterSessionId = gmlcCdrState.getDiameterSessionId();
        if (diameterSessionId != null) {
            cdrModel.setDiameterSessionId(diameterSessionId);
            stringBuilder.append(diameterSessionId).append(SEPARATOR); // DIAMETER SESSION ID
        } else {
            cdrModel.setDiameterSessionId("");
            stringBuilder.append(SEPARATOR);
        }

        /*
         * Diameter Command Origin Host and Realm
         */
        net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginHost = gmlcCdrState.getDiameterOriginHost();
        if (diameterOriginHost != null) {
            cdrModel.setDiameterOriginHost(diameterOriginHost.toString());
            stringBuilder.append(diameterOriginHost.toString()).append(SEPARATOR); // DIAMETER COMMAND ORIGIN HOST
        } else {
            cdrModel.setDiameterOriginHost("");
            stringBuilder.append(SEPARATOR);
        }
        net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterOriginRealm = gmlcCdrState.getDiameterOriginRealm();
        if (diameterOriginRealm != null) {
            cdrModel.setDiameterOriginRealm(diameterOriginRealm.toString());
            stringBuilder.append(diameterOriginRealm.toString()).append(SEPARATOR); // DIAMETER COMMAND ORIGIN REALM
        } else {
            cdrModel.setDiameterOriginRealm("");
            stringBuilder.append(SEPARATOR);
        }

        /*
         * Diameter Command Destination Host and Realm
         */
        net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestinationHost = gmlcCdrState.getDiameterDestHost();
        if (diameterDestinationHost != null) {
            cdrModel.setDiameterDestinationHost(diameterDestinationHost.toString());
            stringBuilder.append(diameterDestinationHost.toString()).append(SEPARATOR); // DIAMETER COMMAND DESTINATION HOST
        } else {
            cdrModel.setDiameterDestinationHost("");
            stringBuilder.append(SEPARATOR);
        }
        net.java.slee.resource.diameter.base.events.avp.DiameterIdentity diameterDestinationRealm = gmlcCdrState.getDiameterDestRealm();
        if (diameterDestinationRealm != null) {
            cdrModel.setDiameterDestinationRealm(diameterDestinationRealm.toString());
            stringBuilder.append(diameterDestinationRealm.toString()).append(SEPARATOR); // DIAMETER COMMAND DESTINATION REALM
        } else {
            cdrModel.setDiameterDestinationRealm("");
            stringBuilder.append(SEPARATOR);
        }

        /*
         * SUPL TLS SLP Socket (GMLC)
         */
        InetAddress slpSocketAddress = gmlcCdrState.getSlpSocketAddress();
        if (slpSocketAddress != null) {
            cdrModel.setSlpSocketAddress(slpSocketAddress.getHostAddress());
            stringBuilder.append(slpSocketAddress.getHostAddress()).append(SEPARATOR);
        } else {
            cdrModel.setSlpSocketAddress("");
            stringBuilder.append(SEPARATOR);
        }
        int slpSocketPort = gmlcCdrState.getSlpSocketPort();
        if (slpSocketPort > -1) {
            cdrModel.setSlpSocketPort(slpSocketPort + "");
            stringBuilder.append(slpSocketPort).append(SEPARATOR);
        } else {
            cdrModel.setSlpSocketPort("");
            stringBuilder.append(SEPARATOR);
        }
        /*
         * SUPL TLS SET Socket (GMLC)
         */
        InetAddress setSocketAddress = gmlcCdrState.getSetSocketAddress();
        if (setSocketAddress != null) {
            cdrModel.setSetSocketAddress(setSocketAddress.getHostAddress().toString());
            stringBuilder.append(setSocketAddress.getHostAddress()).append(SEPARATOR);
        } else {
            cdrModel.setSetSocketAddress("");
            stringBuilder.append(SEPARATOR);
        }
        int setSocketPort = gmlcCdrState.getSlpSocketPort();
        if (setSocketPort > -1) {
            cdrModel.setSetSocketPort(setSocketPort + "");
            stringBuilder.append(setSocketPort).append(SEPARATOR);
        } else {
            cdrModel.setSetSocketPort("");
            stringBuilder.append(SEPARATOR);
        }

        /**
         * CS Location Information (from Sh)
         */
        LocationInformation shLocationInformation = gmlcCdrState.getLocationInformation();

        /**
         * EPS Location Information (from Sh)
         */
        LocationInformationEPS shLocationInformationEPS = gmlcCdrState.getLocationInformationEPS();
        /**
         * CELL PORTION ID (from LTE LCS)
         */
        Long cellPortionId = gmlcCdrState.getCellPortionId();
        /**
         * Location Information GPRS (from Sh)
         */
        LocationInformationGPRS shLocationInformationPS = gmlcCdrState.getLocationInformationGPRS();
        /**
         * Sh 5GS Location Information (from Sh)
         */
        LocationInformation5GS locationInformation5GS = gmlcCdrState.getLocationInformation5GS();
        /**
         * Subscriber Information (from ATI or PSI)
         */
        SubscriberInfo subscriberInfo = gmlcCdrState.getSubscriberInfo();
        if (subscriberInfo != null || shLocationInformation != null || shLocationInformationEPS != null || shLocationInformationPS != null || locationInformation5GS != null) {
            Boolean saiPresent = null;
            /**
             * CS Location Information
             */
            LocationInformation locationInformation = null;
            if (subscriberInfo != null) {
                locationInformation = subscriberInfo.getLocationInformation();
            } else if (shLocationInformation != null || shLocationInformation != null || shLocationInformationEPS != null || locationInformation5GS != null) {
                locationInformation = shLocationInformation;
            }
            if (locationInformation != null || shLocationInformationEPS != null || locationInformation5GS != null) {
                if (locationInformation != null) {
                    /**
                     * LOCATION NUMBER
                     */
                    if (locationInformation.getLocationNumber() != null) {
                        try {
                            if (locationInformation.getLocationNumber().getLocationNumber() != null) {
                                cdrModel.setLocationNumberOddFlag(locationInformation.getLocationNumber().getLocationNumber().isOddFlag() + "");
                                stringBuilder.append(locationInformation.getLocationNumber().getLocationNumber().isOddFlag()).append(SEPARATOR);

                                cdrModel.setLocationNumberNAI(locationInformation.getLocationNumber().getLocationNumber().getNatureOfAddressIndicator() + "");
                                stringBuilder.append(locationInformation.getLocationNumber().getLocationNumber().getNatureOfAddressIndicator()).append(SEPARATOR);

                                cdrModel.setLocationNumberINNI(locationInformation.getLocationNumber().getLocationNumber().getInternalNetworkNumberIndicator() + "");
                                stringBuilder.append(locationInformation.getLocationNumber().getLocationNumber().getInternalNetworkNumberIndicator()).append(SEPARATOR);

                                cdrModel.setLocationNumberNPI(locationInformation.getLocationNumber().getLocationNumber().getNumberingPlanIndicator() + "");
                                stringBuilder.append(locationInformation.getLocationNumber().getLocationNumber().getNumberingPlanIndicator()).append(SEPARATOR);

                                cdrModel.setLocationNumberAPRI(locationInformation.getLocationNumber().getLocationNumber().getAddressRepresentationRestrictedIndicator() + "");
                                stringBuilder.append(locationInformation.getLocationNumber().getLocationNumber().getAddressRepresentationRestrictedIndicator()).append(SEPARATOR);

                                cdrModel.setLocationNumberSI(locationInformation.getLocationNumber().getLocationNumber().getScreeningIndicator() + "");
                                stringBuilder.append(locationInformation.getLocationNumber().getLocationNumber().getScreeningIndicator()).append(SEPARATOR);

                                cdrModel.setLocationNumberAddress(locationInformation.getLocationNumber().getLocationNumber().getAddress());
                                stringBuilder.append(locationInformation.getLocationNumber().getLocationNumber().getAddress()).append(SEPARATOR);
                            }

                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    } else {
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);

                    }
                    if (locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        /**
                         * CGI
                         */
                        if (locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            try {
                                cdrModel.setCellGlobalIdServiceAreaIdMCC(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC() + "");
                                stringBuilder.append(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC()).append(SEPARATOR);

                                cdrModel.setCellGlobalIdServiceAreaIdMNC(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC() + "");
                                stringBuilder.append(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC()).append(SEPARATOR);

                                cdrModel.setCellGlobalIdServiceAreaIdLac(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac() + "");
                                stringBuilder.append(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac()).append(SEPARATOR);

                                cdrModel.setCellGlobalIdServiceAreaIdCI(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode() + "");
                                stringBuilder.append(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode()).append(SEPARATOR);
                            } catch (MAPException e) {
                                e.printStackTrace();
                            }
                        }
                        if (locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                            try {

                                cdrModel.setCellGlobalIdServiceAreaIdMCC(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC() + "");
                                stringBuilder.append(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC()).append(SEPARATOR);

                                cdrModel.setCellGlobalIdServiceAreaIdMNC(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC() + "");
                                stringBuilder.append(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC()).append(SEPARATOR);

                                cdrModel.setCellGlobalIdServiceAreaIdLac(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac() + "");
                                stringBuilder.append(locationInformation.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac()).append(SEPARATOR);

                                cdrModel.setCellGlobalIdServiceAreaIdCI("");
                                stringBuilder.append(SEPARATOR);
                            } catch (MAPException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);

                    }
                    /**
                     * SAI Present
                     */
                    if (locationInformation.getSaiPresent())
                        saiPresent = locationInformation.getSaiPresent();
                    /**
                     * VLR NUMBER
                     */
                    if (locationInformation.getVlrNumber() != null) {
                        cdrModel.setVlrNumber(locationInformation.getVlrNumber().getAddress());
                        stringBuilder.append(locationInformation.getVlrNumber().getAddress()).append(SEPARATOR);
                    } else {
                        cdrModel.setVlrNumber("");
                        stringBuilder.append(SEPARATOR);
                    }
                    /**
                     * MSC NUMBER
                     */
                    if (locationInformation.getMscNumber() != null) {
                        cdrModel.setMscNumber(locationInformation.getMscNumber().getAddress());
                        stringBuilder.append(locationInformation.getMscNumber().getAddress()).append(SEPARATOR);
                    } else {
                        cdrModel.setMscNumber("");
                        stringBuilder.append(SEPARATOR);
                    }
                    /**
                     * AGE OF LOCATION INFORMATION
                     */
                    if (locationInformation.getAgeOfLocationInformation() != null) {
                        cdrModel.setAgeOfLocationInformation(locationInformation.getAgeOfLocationInformation().toString());
                        stringBuilder.append(locationInformation.getAgeOfLocationInformation().intValue()).append(SEPARATOR);
                    } else {
                        cdrModel.setAgeOfLocationInformation("");
                        stringBuilder.append(SEPARATOR);
                    }
                    /**
                     * GEOGRAPHICAL INFO
                     */
                    if (locationInformation.getGeographicalInformation() != null) {
                        // GEOGRAPHICAL INFO TYPE OF SHAPE
                        cdrModel.setGeographicalInfoTypeOfShape(locationInformation.getGeographicalInformation().getTypeOfShape().toString());
                        stringBuilder.append(locationInformation.getGeographicalInformation().getTypeOfShape()).append(SEPARATOR);
                        // GEOGRAPHICAL INFO LATITUDE
                        if (locationInformation.getGeographicalInformation().getLatitude() != 0.0) {
                            String formattedGeographicalInformationLatitude;
                            formattedGeographicalInformationLatitude = coordinatesFormat.format(locationInformation.getGeographicalInformation().getLatitude());
                            cdrModel.setGeographicalInfoLatitude(formattedGeographicalInformationLatitude);
                            stringBuilder.append(formattedGeographicalInformationLatitude).append(SEPARATOR);
                        } else {
                            cdrModel.setGeographicalInfoLatitude("");
                            stringBuilder.append(SEPARATOR);
                        }
                        // GEOGRAPHICAL INFO LONGITUDE
                        if (locationInformation.getGeographicalInformation().getLongitude() != 0.0) {
                            String formattedGeographicalInformationLongitude;
                            formattedGeographicalInformationLongitude = coordinatesFormat.format(locationInformation.getGeographicalInformation().getLongitude());
                            cdrModel.setGeographicalInfoLongitude(formattedGeographicalInformationLongitude);
                            stringBuilder.append(formattedGeographicalInformationLongitude).append(SEPARATOR);
                        } else {
                            cdrModel.setGeographicalInfoLongitude("");
                            stringBuilder.append(SEPARATOR);
                        }

                        // GEOGRAPHICAL INFO UNCERTAINTY
                        if (Double.valueOf(locationInformation.getGeographicalInformation().getUncertainty()) != null
                                && locationInformation.getGeographicalInformation().getLatitude() != 0.0
                                && locationInformation.getGeographicalInformation().getLongitude() != 0.0) {
                            cdrModel.setGeographicalInfoUncertainty(uncertaintyFormat.format(locationInformation.getGeographicalInformation().getUncertainty()));
                            stringBuilder.append(uncertaintyFormat.format(locationInformation.getGeographicalInformation().getUncertainty())).append(SEPARATOR);
                        } else {
                            cdrModel.setGeographicalInfoUncertainty("");
                            stringBuilder.append(SEPARATOR);
                        }
                    } else {
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);

                    }
                    /**
                     * GEODETIC INFO
                     */
                    if (locationInformation.getGeodeticInformation() != null) {
                        // GEODETIC INFO TYPE OF SHAPE
                        cdrModel.setGeodeticInfoTypeOfShape(locationInformation.getGeodeticInformation().getTypeOfShape().toString());
                        stringBuilder.append(locationInformation.getGeodeticInformation().getTypeOfShape()).append(SEPARATOR);
                        // GEODETIC INFO LATITUDE
                        if (locationInformation.getGeodeticInformation().getLatitude() != 0.0) {
                            String formattedGeodeticInformationLatitude;
                            formattedGeodeticInformationLatitude = coordinatesFormat.format(locationInformation.getGeodeticInformation().getLatitude());
                            cdrModel.setGeodeticInfoLatitude(formattedGeodeticInformationLatitude);
                            stringBuilder.append(formattedGeodeticInformationLatitude).append(SEPARATOR);
                        } else {
                            cdrModel.setGeodeticInfoLatitude("");
                            stringBuilder.append(SEPARATOR);
                        }

                        // GEODETIC INFO LONGITUDE
                        if (locationInformation.getGeodeticInformation().getLongitude() != 0.0) {
                            String formattedGeodeticInformationLongitude;
                            formattedGeodeticInformationLongitude = coordinatesFormat.format(locationInformation.getGeodeticInformation().getLongitude());
                            cdrModel.setGeodeticInfoLongitude(formattedGeodeticInformationLongitude);
                            stringBuilder.append(formattedGeodeticInformationLongitude).append(SEPARATOR);
                        } else {
                            cdrModel.setGeodeticInfoLongitude("");
                            stringBuilder.append(SEPARATOR);
                        }
                        // GEODETIC INFO UNCERTAINTY
                        if (Double.valueOf(locationInformation.getGeodeticInformation().getUncertainty()) != null &&
                                locationInformation.getGeodeticInformation().getLatitude() != 0.0 &&
                                locationInformation.getGeodeticInformation().getLongitude() != 0.0) {
                            cdrModel.setGeodeticInfoUncertainty(uncertaintyFormat.format(locationInformation.getGeodeticInformation().getUncertainty()));
                            stringBuilder.append(uncertaintyFormat.format(locationInformation.getGeodeticInformation().getUncertainty())).append(SEPARATOR);
                        } else {
                            cdrModel.setGeodeticInfoUncertainty("");
                            stringBuilder.append(SEPARATOR);
                        }
                        // GEODETIC INFO CONFIDENCE
                        if (locationInformation.getGeodeticInformation().getLatitude() != 0.0 &&
                                locationInformation.getGeodeticInformation().getLongitude() != 0.0) {
                            cdrModel.setGeodeticInfoConfidence(locationInformation.getGeodeticInformation().getConfidence() + "");
                            stringBuilder.append(locationInformation.getGeodeticInformation().getConfidence()).append(SEPARATOR);
                        } else {
                            cdrModel.setGeodeticInfoConfidence("");
                            stringBuilder.append(SEPARATOR);
                        }
                        // GEODETIC INFO SCREENING AND PRESENTATION INDICATORS
                        if (locationInformation.getGeodeticInformation().getLatitude() != 0.0 &&
                                locationInformation.getGeodeticInformation().getLongitude() != 0.0) {
                            cdrModel.setGeodeticInfoScreeningAndPresentationIndicators(locationInformation.getGeodeticInformation().getScreeningAndPresentationIndicators() + "");
                            stringBuilder.append(locationInformation.getGeodeticInformation().getScreeningAndPresentationIndicators()).append(SEPARATOR);
                        } else {
                            cdrModel.setGeodeticInfoScreeningAndPresentationIndicators("");
                            stringBuilder.append(SEPARATOR);
                        }
                    } else {
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);

                    }
                }
                /**
                 * EPS Location Information
                 */
                LocationInformationEPS locationInformationEPS = null;
                if (subscriberInfo != null) {
                    if (subscriberInfo.getLocationInformation() != null)
                        locationInformationEPS = subscriberInfo.getLocationInformation().getLocationInformationEPS();
                } else if (shLocationInformationEPS != null) {
                    locationInformationEPS = shLocationInformationEPS;
                }

                if (locationInformationEPS != null || locationInformation5GS != null) {

                    if (locationInformationEPS != null) {
                        /**
                         * E-UTRAN CGI from EPS location information from ATI, PSI or Sh
                         */
                        if (locationInformationEPS.getEUtranCellGlobalIdentity() != null) {
                            EUTRANCGI eutrancgi = new EUTRANCGIImpl(locationInformationEPS.getEUtranCellGlobalIdentity().getData());
                            try {
                                // ECGI MCC
                                int ecgiMcc = eutrancgi.getMCC();
                                cdrModel.setEutranCellGlobalIdMCC(String.valueOf(ecgiMcc));
                                stringBuilder.append(ecgiMcc).append(SEPARATOR);
                                // ECGI MNC
                                int ecgiMnc = eutrancgi.getMNC();
                                cdrModel.setEutranCellGlobalIdMNC(String.valueOf(ecgiMnc));
                                stringBuilder.append(ecgiMnc).append(SEPARATOR);
                                // ECGI ECI
                                long eci = eutrancgi.getEci();
                                cdrModel.setEutranCellGlobalIdECI(String.valueOf(eci));
                                stringBuilder.append(eci).append(SEPARATOR);
                                // ECGI ENBID
                                long ecgiENBId = eutrancgi.getENodeBId();
                                cdrModel.setEutranCellGlobalIdENBID(String.valueOf(ecgiENBId));
                                stringBuilder.append(ecgiENBId).append(SEPARATOR);
                                // ECGI CI
                                int ecgiCi = eutrancgi.getCi();
                                cdrModel.setEutranCellGlobalIdCI(String.valueOf(ecgiCi));
                                stringBuilder.append(ecgiCi).append(SEPARATOR);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);

                        }
                        if (cellPortionId != null) {
                            cdrModel.setCellPortionId(String.valueOf(cellPortionId));
                            stringBuilder.append(cellPortionId).append(SEPARATOR);
                        } else {
                            cdrModel.setCellPortionId("");
                            stringBuilder.append(SEPARATOR);
                        }
                        /*
                         * TRACKING AREA IDENTITY from EPS location information from ATI, PSI or Sh
                         */
                        if (locationInformationEPS.getTrackingAreaIdentity() != null) {
                            TrackingAreaId tai = new TrackingAreaIdImpl(locationInformationEPS.getTrackingAreaIdentity().getData());
                            try {
                                // TAI MCC
                                int taiMcc = tai.getMCC();
                                cdrModel.setTaiMCC(String.valueOf(taiMcc));
                                stringBuilder.append(taiMcc).append(SEPARATOR);
                                // TAI MNC
                                int taiMnc = tai.getMNC();
                                cdrModel.setTaiMNC(String.valueOf(taiMnc));
                                stringBuilder.append(taiMnc).append(SEPARATOR);
                                // TAI TAC
                                int taiTac = tai.getTAC();
                                cdrModel.setTaiTAC(String.valueOf(taiTac));
                                stringBuilder.append(taiTac).append(SEPARATOR);
                            } catch (MAPException e) {
                                e.printStackTrace();
                            }
                        } else {
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);

                        }
                        /**
                         * EPS GEOGRAPHICAL INFORMATION from EPS location information from ATI, PSI or Sh
                         */
                        if (locationInformationEPS.getGeographicalInformation() != null) {
                            // EPS LOCATION INFO GEOGRAPHICAL TYPE OF SHAPE
                            cdrModel.setEpsLocationInfoGeographicalTypeOfShape(locationInformationEPS.getGeographicalInformation().getTypeOfShape().toString());
                            stringBuilder.append(locationInformationEPS.getGeographicalInformation().getTypeOfShape()).append(SEPARATOR);
                            // EPS LOCATION INFO GEOGRAPHICAL LATITUDE
                            if (locationInformationEPS.getGeographicalInformation().getLatitude() != 0.0) {
                                String formattedEPSGeographicalInformationLatitude;
                                formattedEPSGeographicalInformationLatitude = coordinatesFormat.format(locationInformationEPS.getGeographicalInformation().getLatitude());
                                cdrModel.setEpsLocationInfoGeographicalLatitude(formattedEPSGeographicalInformationLatitude);
                                stringBuilder.append(formattedEPSGeographicalInformationLatitude).append(SEPARATOR);
                            } else {
                                cdrModel.setEpsLocationInfoGeographicalLatitude("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // EPS LOCATION INFO GEOGRAPHICAL LONGITUDE
                            if (locationInformationEPS.getGeographicalInformation().getLongitude() != 0.0) {
                                String formattedEPSGeographicalInformationLongitude;
                                formattedEPSGeographicalInformationLongitude = coordinatesFormat.format(locationInformationEPS.getGeographicalInformation().getLongitude());
                                cdrModel.setEpsLocationInfoGeographicalLongitude(formattedEPSGeographicalInformationLongitude);
                                stringBuilder.append(formattedEPSGeographicalInformationLongitude).append(SEPARATOR);
                            } else {
                                cdrModel.setEpsLocationInfoGeographicalLongitude("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // EPS LOCATION INFO GEOGRAPHICAL UNCERTAINTY
                            if (Double.valueOf(locationInformationEPS.getGeographicalInformation().getUncertainty()) != null &&
                                    locationInformationEPS.getGeographicalInformation().getLatitude() != 0.0 &&
                                    locationInformationEPS.getGeographicalInformation().getLongitude() != 0.0) {
                                cdrModel.setEpsLocationInfoGeographicalUncertainty(uncertaintyFormat.format(locationInformationEPS.getGeographicalInformation().getUncertainty()));
                                stringBuilder.append(uncertaintyFormat.format(locationInformationEPS.getGeographicalInformation().getUncertainty())).append(SEPARATOR);
                            } else {
                                cdrModel.setEpsLocationInfoGeographicalUncertainty("");
                                stringBuilder.append(SEPARATOR);
                            }
                        }

                    } else if (locationInformation5GS != null) {
                        /**
                         * E-UTRAN CGI from 5GS location information from Sh
                         */
                        if (locationInformation5GS.getEUtranCellGlobalIdentity() != null) {
                            EUTRANCGI eutrancgi = new EUTRANCGIImpl(locationInformation5GS.getEUtranCellGlobalIdentity().getData());
                            try {
                                // ECGI MCC
                                int ecgiMcc = eutrancgi.getMCC();
                                cdrModel.setEutranCellGlobalId5GsMCC(String.valueOf(ecgiMcc));
                                stringBuilder.append(ecgiMcc).append(SEPARATOR);
                                // ECGI MNC
                                int ecgiMnc = eutrancgi.getMNC();
                                cdrModel.setEutranCellGlobalId5GsMNC(String.valueOf(ecgiMnc));
                                stringBuilder.append(ecgiMnc).append(SEPARATOR);
                                // ECGI ECI
                                long eci = eutrancgi.getEci();
                                cdrModel.setEutranCellGlobalId5GsECI(String.valueOf(eci));
                                stringBuilder.append(eci).append(SEPARATOR);
                                // ECGI ENBID
                                long ecgiENBId = eutrancgi.getENodeBId();
                                cdrModel.setEutranCellGlobalId5GsENBID(String.valueOf(ecgiENBId));
                                stringBuilder.append(ecgiENBId).append(SEPARATOR);
                                // ECGI CI
                                int ecgiCi = eutrancgi.getCi();
                                cdrModel.setEutranCellGlobalId5GsCI(String.valueOf(ecgiCi));
                                stringBuilder.append(ecgiCi).append(SEPARATOR);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                        }
                        if (cellPortionId != null) {
                            cdrModel.setCellPortionId5Gs(String.valueOf(cellPortionId));
                            stringBuilder.append(cellPortionId).append(SEPARATOR);
                        } else {
                            cdrModel.setCellPortionId5Gs("");
                            stringBuilder.append(SEPARATOR);
                        }
                        /**
                         * TRACKING AREA IDENTITY from 5GS location information from Sh
                         */
                        if (locationInformation5GS.getTrackingAreaIdentity() != null) {
                            TrackingAreaId trackingAreaId = new TrackingAreaIdImpl(locationInformation5GS.getTrackingAreaIdentity().getData());
                            try {
                                // TAI MCC
                                cdrModel.setTai5GsMCC(String.valueOf(trackingAreaId.getMCC()));
                                stringBuilder.append(trackingAreaId.getMCC()).append(SEPARATOR);
                                // TAI MNC
                                cdrModel.setTai5GsMNC(String.valueOf(trackingAreaId.getMNC()));
                                stringBuilder.append(trackingAreaId.getMNC()).append(SEPARATOR);
                                // TAI TAC
                                cdrModel.setTai5GsTAC(String.valueOf(trackingAreaId.getTAC()));
                                stringBuilder.append(trackingAreaId.getTAC()).append(SEPARATOR);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);

                        }
                        /**
                         * GEOGRAPHICAL INFORMATION from 5GS location information from Sh
                         */
                        if (locationInformation5GS.getGeographicalInformation() != null) {
                            // 5GS GEOGRAPHICAL INFO TYPE OF SHAPE
                            cdrModel.setGeographicalInfo5GsTypeOfShape(gmlcCdrState.getTypeOfShape().toString());
                            stringBuilder.append(gmlcCdrState.getTypeOfShape()).append(SEPARATOR);
                            // 5GS GEOGRAPHICAL INFO LATITUDE
                            Double latitude = locationInformation5GS.getGeographicalInformation().getLatitude();
                            if (latitude != 0.0) {
                                String formatted5GSGeographicalInformationLatitude;
                                formatted5GSGeographicalInformationLatitude = coordinatesFormat.format(latitude);
                                cdrModel.setGeographicalInfo5GsLatitude(formatted5GSGeographicalInformationLatitude);
                                stringBuilder.append(formatted5GSGeographicalInformationLatitude).append(SEPARATOR);
                            } else {
                                cdrModel.setGeographicalInfo5GsLatitude("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // 5GS GEOGRAPHICAL INFO LONGITUDE
                            Double longitude = locationInformation5GS.getGeographicalInformation().getLongitude();
                            if (longitude != 0.0) {
                                String formatted5GSGeographicalInformationLongitude;
                                formatted5GSGeographicalInformationLongitude = coordinatesFormat.format(longitude);
                                cdrModel.setGeographicalInfo5GsLongitude(formatted5GSGeographicalInformationLongitude);
                                stringBuilder.append(formatted5GSGeographicalInformationLongitude).append(SEPARATOR);
                            } else {
                                cdrModel.setGeographicalInfo5GsLongitude("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // 5GS GEOGRAPHICAL INFO UNCERTAINTY
                            Double uncertainty = locationInformation5GS.getGeographicalInformation().getUncertainty();
                            if (uncertainty != null && latitude != 0.0 && longitude != 0.0) {
                                cdrModel.setGeographicalInfo5GsUncertainty(uncertaintyFormat.format(uncertainty));
                                stringBuilder.append(uncertaintyFormat.format(uncertainty)).append(SEPARATOR);
                            } else {
                                cdrModel.setGeographicalInfo5GsUncertainty("");
                                stringBuilder.append(SEPARATOR);
                            }
                        } else {
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);

                        }
                    } else {
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);

                    }

                    if (locationInformationEPS != null) {
                        /**
                         * EPS GEODETIC INFORMATION
                         */
                        if (locationInformationEPS.getGeodeticInformation() != null) {
                            // EPS LOCATION INFO GEODETIC TYPE OF SHAPE
                            cdrModel.setEpsLocationInfoGeodeticTypeOfShape(locationInformationEPS.getGeodeticInformation().getTypeOfShape().toString());
                            stringBuilder.append(locationInformationEPS.getGeodeticInformation().getTypeOfShape()).append(SEPARATOR);
                            // EPS LOCATION INFO GEODETIC LATITUDE
                            if (locationInformationEPS.getGeodeticInformation().getLatitude() != 0.0) {
                                String formattedEPSGeodeticInformationLatitude;
                                formattedEPSGeodeticInformationLatitude = coordinatesFormat.format(locationInformationEPS.getGeodeticInformation().getLatitude());
                                cdrModel.setEpsLocationInfoGeodeticLatitude(formattedEPSGeodeticInformationLatitude);
                                stringBuilder.append(formattedEPSGeodeticInformationLatitude).append(SEPARATOR);
                            } else {
                                cdrModel.setEpsLocationInfoGeodeticLatitude("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // EPS LOCATION INFO GEODETIC LONGITUDE
                            if (locationInformationEPS.getGeodeticInformation().getLongitude() != 0.0) {
                                String formattedEPSGeodeticInformationLongitude;
                                formattedEPSGeodeticInformationLongitude = coordinatesFormat.format(locationInformationEPS.getGeodeticInformation().getLongitude());
                                cdrModel.setEpsLocationInfoGeodeticLongitude(formattedEPSGeodeticInformationLongitude);
                                stringBuilder.append(formattedEPSGeodeticInformationLongitude).append(SEPARATOR);
                            } else {
                                cdrModel.setEpsLocationInfoGeodeticLongitude("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // EPS LOCATION INFO GEODETIC UNCERTAINTY
                            if (Double.valueOf(locationInformationEPS.getGeodeticInformation().getUncertainty()) != null &&
                                    locationInformationEPS.getGeodeticInformation().getLatitude() != 0.0 &&
                                    locationInformationEPS.getGeodeticInformation().getLongitude() != 0.0) {
                                cdrModel.setEpsLocationInfoGeodeticUncertainty(uncertaintyFormat.format(locationInformationEPS.getGeodeticInformation().getUncertainty()));
                                stringBuilder.append(uncertaintyFormat.format(locationInformationEPS.getGeodeticInformation().getUncertainty())).append(SEPARATOR);
                            } else {
                                cdrModel.setEpsLocationInfoGeodeticUncertainty("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // EPS LOCATION INFO GEODETIC CONFIDENCE
                            if (locationInformationEPS.getGeodeticInformation().getLatitude() != 0.0 &&
                                    locationInformationEPS.getGeodeticInformation().getLongitude() != 0.0) {
                                stringBuilder.append(locationInformationEPS.getGeodeticInformation().getConfidence()).append(SEPARATOR);
                                cdrModel.setEpsLocationInfoGeodeticConfidence(locationInformationEPS.getGeodeticInformation().getConfidence() + "");
                            } else {
                                cdrModel.setEpsLocationInfoGeodeticConfidence("");
                                stringBuilder.append(SEPARATOR);
                            }
                            // EPS LOCATION INFO GEODETIC SCREENING AND PRESENTATION INDICATORS
                            if (locationInformationEPS.getGeodeticInformation().getLatitude() != 0.0 &&
                                    locationInformationEPS.getGeodeticInformation().getLongitude() != 0.0) {
                                cdrModel.setEpsLocationInfoGeodeticScreeningAndPresentationInd(locationInformationEPS.getGeodeticInformation().getScreeningAndPresentationIndicators() + "");
                                stringBuilder.append(locationInformationEPS.getGeodeticInformation().getScreeningAndPresentationIndicators()).append(SEPARATOR);
                            } else {
                                cdrModel.setEpsLocationInfoGeodeticScreeningAndPresentationInd("");
                                stringBuilder.append(SEPARATOR);
                            }
                        } else {
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);
                            stringBuilder.append(SEPARATOR);

                        }
                        /**
                         * MME NAME
                         */
                        if (locationInformationEPS.getMmeName() != null) {
                            cdrModel.setEpsLocationInfoGeodeticMMEName(new String(locationInformationEPS.getMmeName().getData()));
                            stringBuilder.append(new String(locationInformationEPS.getMmeName().getData())).append(SEPARATOR);
                        } else {
                            cdrModel.setEpsLocationInfoGeodeticMMEName("");
                            stringBuilder.append(SEPARATOR);
                        }
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
            } else {
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
            }
            /**
             * Location Information GPRS
             */
            LocationInformationGPRS locationInformationGPRS = null;
            if (subscriberInfo != null) {
                if (subscriberInfo.getLocationInformationGPRS() != null)
                    locationInformationGPRS = subscriberInfo.getLocationInformationGPRS();
            } else if (shLocationInformationPS != null) {
                locationInformationGPRS = shLocationInformationPS;
            }
            if (locationInformationGPRS != null) {
                if (locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                    /**
                     * PS CGI
                     */
                    if (locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                        try {
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAIMCC(String.valueOf(locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC()));
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAIMNC(String.valueOf(locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC()));
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAILac(String.valueOf(locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac()));
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAICI(String.valueOf(locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode()));
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                        try {
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAIMCC(String.valueOf(locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC()));
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAIMNC(String.valueOf(locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC()));
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAILac(String.valueOf(locationInformationGPRS.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac()));
                            cdrModel.setPsCellGlobalIdServiceAreaIdOrLAICI("");
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    stringBuilder.append(cdrModel.getPsCellGlobalIdServiceAreaIdOrLAIMCC()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getPsCellGlobalIdServiceAreaIdOrLAIMNC()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getPsCellGlobalIdServiceAreaIdOrLAILac()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getPsCellGlobalIdServiceAreaIdOrLAICI()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                /**
                 * GPRS SAI PRESENT
                 */
                if (locationInformationGPRS.isSaiPresent())
                    saiPresent = locationInformationGPRS.isSaiPresent();
                /**
                 * ROUTEING AREA IDENTITY
                 */
                if (locationInformationGPRS.getRouteingAreaIdentity() != null) {
                    RoutingAreaId rai = new RoutingAreaIdImpl(locationInformationGPRS.getRouteingAreaIdentity().getData());
                    try {
                        // RAI MCC
                        int raiMcc = rai.getMCC();
                        cdrModel.setRaiMCC(String.valueOf(raiMcc));
                        stringBuilder.append(cdrModel.getRaiMCC()).append(SEPARATOR);
                        // RAI MNC
                        int raiMnc = rai.getMNC();
                        cdrModel.setRaiMNC(String.valueOf(raiMnc));
                        stringBuilder.append(cdrModel.getRaiMNC()).append(SEPARATOR);
                        // RAI LAC
                        int raiLac = rai.getLAC();
                        cdrModel.setRaiLAC(String.valueOf(raiLac));
                        stringBuilder.append(cdrModel.getRaiLAC()).append(SEPARATOR);
                        // RAI RAC
                        int raiRac = rai.getRAC();
                        cdrModel.setRaiRAC(String.valueOf(raiRac));
                        stringBuilder.append(cdrModel.getRaiRAC()).append(SEPARATOR);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                /**
                 * PS AGE OF LOCATION INFORMATION
                 */
                if (locationInformationGPRS.getAgeOfLocationInformation() != null) {
                    cdrModel.setPsAgeOfLocationInformation(String.valueOf(locationInformationGPRS.getAgeOfLocationInformation().intValue()));
                    stringBuilder.append(cdrModel.getPsAgeOfLocationInformation()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
                /**
                 * GPRS GEOGRAPHICAL INFORMATION
                 */
                if (locationInformationGPRS.getGeographicalInformation() != null) {
                    // GPRS GEOGRAPHICAL INFO TYPE OF SHAPE
                    cdrModel.setPsGeographicalInfoTypeOfShape(String.valueOf(locationInformationGPRS.getGeographicalInformation().getTypeOfShape()));
                    stringBuilder.append(cdrModel.getPsGeographicalInfoTypeOfShape()).append(SEPARATOR);
                    // GPRS GEOGRAPHICAL INFO LATITUDE
                    if (locationInformationGPRS.getGeographicalInformation().getLatitude() != 0.0) {
                        String formattedPSGeographicalInformationLatitude;
                        formattedPSGeographicalInformationLatitude = coordinatesFormat.format(locationInformationGPRS.getGeographicalInformation().getLatitude());
                        cdrModel.setPsGeographicalInfoLatitude(formattedPSGeographicalInformationLatitude);
                        stringBuilder.append(cdrModel.getPsGeographicalInfoLatitude()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                    // GPRS GEOGRAPHICAL INFO LONGITUDE
                    if (locationInformationGPRS.getGeographicalInformation().getLongitude() != 0.0) {
                        String formattedPSGeographicalInformationLongitude;
                        formattedPSGeographicalInformationLongitude = coordinatesFormat.format(locationInformationGPRS.getGeographicalInformation().getLongitude());
                        cdrModel.setPsGeographicalInfoLongitude(formattedPSGeographicalInformationLongitude);
                        stringBuilder.append(cdrModel.getPsGeographicalInfoLongitude()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                    // GPRS GEOGRAPHICAL INFO UNCERTAINTY
                    if (Double.valueOf(locationInformationGPRS.getGeographicalInformation().getUncertainty()) != null &&
                            locationInformationGPRS.getGeographicalInformation().getLatitude() != 0.0 &&
                            locationInformationGPRS.getGeographicalInformation().getLongitude() != 0.0) {
                        cdrModel.setPsGeographicalInfoUncertainty(uncertaintyFormat.format(locationInformationGPRS.getGeographicalInformation().getUncertainty()));
                        stringBuilder.append(cdrModel.getPsGeographicalInfoUncertainty()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                /**
                 * GPRS GEODETIC INFORMATION
                 */
                if (locationInformationGPRS.getGeodeticInformation() != null) {
                    // GPRS GEODETIC INFO TYPE OF SHAPE
                    cdrModel.setPsGeodeticInfoTypeOfShape(String.valueOf(locationInformationGPRS.getGeodeticInformation().getTypeOfShape()));
                    stringBuilder.append(cdrModel.getPsGeodeticInfoTypeOfShape()).append(SEPARATOR);
                    // GPRS GEODETIC INFO LATITUDE
                    if (locationInformationGPRS.getGeodeticInformation().getLatitude() != 0.0) {
                        String formattedPSGeodeticInformationLatitude;
                        formattedPSGeodeticInformationLatitude = coordinatesFormat.format(locationInformationGPRS.getGeodeticInformation().getLatitude());
                        cdrModel.setPsGeodeticInfoLatitude(formattedPSGeodeticInformationLatitude);
                        stringBuilder.append(cdrModel.getPsGeodeticInfoLatitude()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                    // GPRS GEODETIC INFO LONGITUDE
                    if (locationInformationGPRS.getGeodeticInformation().getLongitude() != 0.0) {
                        String formattedPSGeodeticInformationLongitude;
                        formattedPSGeodeticInformationLongitude = coordinatesFormat.format(locationInformationGPRS.getGeodeticInformation().getLongitude());
                        cdrModel.setPsGeodeticInfoLongitude(formattedPSGeodeticInformationLongitude);
                        stringBuilder.append(cdrModel.getPsGeodeticInfoLongitude()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                    // GPRS GEODETIC INFO UNCERTAINTY
                    if (Double.valueOf(locationInformationGPRS.getGeodeticInformation().getUncertainty()) != null &&
                            locationInformationGPRS.getGeodeticInformation().getLatitude() != 0.0 &&
                            locationInformationGPRS.getGeodeticInformation().getLongitude() != 0.0) {
                        cdrModel.setPsGeodeticInfoUncertainty(uncertaintyFormat.format(locationInformationGPRS.getGeodeticInformation().getUncertainty()));
                        stringBuilder.append(cdrModel.getPsGeodeticInfoUncertainty()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                    // GPRS GEODETIC INFO CONFIDENCE
                    if (locationInformationGPRS.getGeodeticInformation().getLatitude() != 0.0 &&
                            locationInformationGPRS.getGeodeticInformation().getLongitude() != 0.0) {
                        cdrModel.setPsGeodeticInfoConfidence(String.valueOf(locationInformationGPRS.getGeodeticInformation().getConfidence()));
                        stringBuilder.append(cdrModel.getPsGeodeticInfoConfidence()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                    // GPRS GEODETIC INFO SCREENING AND PRESENTATION INDICATORS
                    if (locationInformationGPRS.getGeodeticInformation().getLatitude() != 0.0 &&
                            locationInformationGPRS.getGeodeticInformation().getLongitude() != 0.0) {
                        cdrModel.setPsGeodeticInfoScreeningAndPresentationIndicators(String.valueOf(locationInformationGPRS.getGeodeticInformation().getScreeningAndPresentationIndicators()));
                        stringBuilder.append(cdrModel.getPsGeodeticInfoScreeningAndPresentationIndicators()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                /**
                 * LSA ID
                 */
                if (locationInformationGPRS.getLSAIdentity() != null) {
                    // LSA ID
                    cdrModel.setLsaId(new String(locationInformationGPRS.getLSAIdentity().getData()));
                    stringBuilder.append(cdrModel.getLsaId()).append(SEPARATOR);
                    // LSA ID PLMN SIGNIFICANT
                    if (locationInformationGPRS.getLSAIdentity().isPlmnSignificantLSA()) { // isPlmnSignificantLSA means the opposite in jSS7 implementation
                        cdrModel.setLsaIdPLMNSignificant("Universal");
                        stringBuilder.append(cdrModel.getLsaIdPLMNSignificant()).append(SEPARATOR);
                    } else {
                        cdrModel.setLsaIdPLMNSignificant("PLMN");
                        stringBuilder.append(cdrModel.getLsaIdPLMNSignificant()).append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                /**
                 * SGSN NUMBER
                 */
                if (locationInformationGPRS.getSGSNNumber() != null) {
                    cdrModel.setSgsnNumber(locationInformationGPRS.getSGSNNumber().getAddress());
                    stringBuilder.append(cdrModel.getSgsnNumber()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
            } else {
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
            }
            /**
             * SAI Present
             */
            if (saiPresent != null) {
                cdrModel.setSaiPresent(String.valueOf(saiPresent));
                stringBuilder.append(cdrModel.getSaiPresent()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            if (subscriberInfo != null) {
                /**
                 * CS and PS Subscriber STATE
                 */
                SubscriberState subscriberState = subscriberInfo.getSubscriberState();
                PSSubscriberState psSubscriberState = subscriberInfo.getPSSubscriberState();
                if (subscriberState != null || psSubscriberState != null) {
                    if (subscriberState != null) {
                        if (subscriberState.getSubscriberStateChoice() != null)
                            cdrModel.setSubscirberState(subscriberState.getSubscriberStateChoice().toString());
                        if (subscriberState.getNotReachableReason() != null)
                            cdrModel.setNotReachableReasonState(subscriberState.getSubscriberStateChoice().toString());
                    }
                    if (psSubscriberState != null) {
                        if (psSubscriberState.getChoice() != null)
                            cdrModel.setSubscirberState(psSubscriberState.getChoice().toString());
                        if (psSubscriberState.getNetDetNotReachable() != null)
                            cdrModel.setNotReachableReasonState(psSubscriberState.getNetDetNotReachable().name());
                    }
                    stringBuilder.append(cdrModel.getSubscirberState()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getNotReachableReasonState()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }

                // MS CLASSMARK 2
                MSClassmark2 msClassmark = subscriberInfo.getMSClassmark2();
                if (msClassmark != null) {
                    cdrModel.setMsClassmark(bytesToHexString(msClassmark.getData()));
                    stringBuilder.append(cdrModel.getMsClassmark()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // GPRS MS CLASS
                GPRSMSClass gprsMsClass = subscriberInfo.getGPRSMSClass();
                if (gprsMsClass != null) {
                    // GPRS MS CLASS MS RADIO ACCESS CAPABILITY
                    if (gprsMsClass.getMSRadioAccessCapability() != null) {
                        cdrModel.setGprsMSRadioAccessCapability(bytesToHexString(gprsMsClass.getMSRadioAccessCapability().getData()));
                        stringBuilder.append(cdrModel.getGprsMSRadioAccessCapability()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                    // GPRS MS CLASS MS NETWORK CAPABILITY
                    if (gprsMsClass.getMSNetworkCapability() != null) {
                        cdrModel.setGprsMSNetworkCapability(bytesToHexString(gprsMsClass.getMSNetworkCapability().getData()));
                        stringBuilder.append(cdrModel.getGprsMSNetworkCapability()).append(SEPARATOR);
                    } else
                        stringBuilder.append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }

                /**
                 * MNP INFO RESULT (from ATI or PSI)
                 */
                MNPInfoRes mnpInfoResult = subscriberInfo.getMNPInfoRes();
                if (mnpInfoResult != null) {
                    // MNP NUMBER PORTABILITY STATUS
                    if (mnpInfoResult.getNumberPortabilityStatus() != null)
                        cdrModel.setMnpNumberPortabilityStatus(String.valueOf(mnpInfoResult.getNumberPortabilityStatus().getType()));
                    // MNP IMSI
                    if (mnpInfoResult.getIMSI() != null)
                        cdrModel.setMnpIMSI(new String(mnpInfoResult.getIMSI().getData().getBytes()));
                    // MNP MSISDN
                    if (mnpInfoResult.getMSISDN() != null)
                        cdrModel.setMnpMSISDN(mnpInfoResult.getMSISDN().getAddress());
                    // MNP ROUTEING NUMBER
                    if (mnpInfoResult.getRouteingNumber() != null)
                        cdrModel.setMnpRouteingNumber(mnpInfoResult.getRouteingNumber().getRouteingNumber());

                    stringBuilder.append(cdrModel.getMnpNumberPortabilityStatus()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getMnpIMSI()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getMnpMSISDN()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getMnpRouteingNumber()).append(SEPARATOR);

                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
            } else {
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
            }

            /**
             * Sh 5GS Location Information only parameters
             */
            if (locationInformation5GS != null) {
                /*
                 * NR Cell Global Id
                 */
                if (locationInformation5GS.getNRCellGlobalIdentity() != null) {
                    try {
                        // NRCGI MCC
                        cdrModel.setNrCellGlobalId5GsMCC(String.valueOf(locationInformation5GS.getNRCellGlobalIdentity().getMCC()));
                        stringBuilder.append(cdrModel.getNrCellGlobalId5GsMCC()).append(SEPARATOR);

                        // NRCGI MNC
                        cdrModel.setNrCellGlobalId5GsMNC(String.valueOf(locationInformation5GS.getNRCellGlobalIdentity().getMNC()));
                        stringBuilder.append(cdrModel.getNrCellGlobalId5GsMNC()).append(SEPARATOR);

                        // NRCGI NCI
                        cdrModel.setNrCellGlobalId5GsNCI(String.valueOf(locationInformation5GS.getNRCellGlobalIdentity().getNCI()));
                        stringBuilder.append(cdrModel.getNrCellGlobalId5GsNCI()).append(SEPARATOR);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                /*
                 * 5GS AMF Address
                 */
                if (locationInformation5GS.getAMFAddress() != null) {
                    cdrModel.setAmfAddress5Gs(locationInformation5GS.getAMFAddress());
                    stringBuilder.append(cdrModel.getAmfAddress5Gs()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
                /*
                 * 5GS SMSF Address
                 */
                if (locationInformation5GS.getSMSFAddress() != null) {
                    cdrModel.setSmsfAddress5gs(locationInformation5GS.getSMSFAddress());
                    stringBuilder.append(cdrModel.getSmsfAddress5gs()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
            } else {
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
            }

            /*
             * Visited PLMN ID from Sh UDR/UDA
             */
            PlmnId visitedPlmnId = gmlcCdrState.getVisitedPlmnId();
            if (visitedPlmnId != null) {
                try {
                    cdrModel.setVisitedPlmnIdMCC(String.valueOf(visitedPlmnId.getMcc()));
                    cdrModel.setVisitedPlmnIdMNC(String.valueOf(visitedPlmnId.getMnc()));
                    stringBuilder.append(cdrModel.getVisitedPlmnIdMCC()).append(SEPARATOR);
                    stringBuilder.append(cdrModel.getVisitedPlmnIdMNC()).append(SEPARATOR);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
            }
            /*
             * Local Time Zone from Sh UDR/UDA
             */
            LocalTimeZone localTimeZone = gmlcCdrState.getLocalTimeZone();
            if (localTimeZone != null) {
                if (localTimeZone.getTimeZone() != null)
                    cdrModel.setLocalTimeZone(localTimeZone.getTimeZone());
                if (localTimeZone.getDaylightSavingTime() != null)
                    cdrModel.setDaylightSavingTime(String.valueOf(localTimeZone.getDaylightSavingTime()));

                stringBuilder.append(cdrModel.getLocalTimeZone()).append(SEPARATOR);
                stringBuilder.append(cdrModel.getDaylightSavingTime()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
                stringBuilder.append(SEPARATOR);
            }
            /*
             * RAT type from Sh UDR/UDA
             */
            Integer ratTypeCode = gmlcCdrState.getRatType();
            if (ratTypeCode != null) {
                String ratType = getRatType(ratTypeCode);
                cdrModel.setRatType(ratType);
                stringBuilder.append(cdrModel.getRatType()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * Current Location Retrieved
         */
        Boolean currentLocationRetrieved = gmlcCdrState.isCurrentLocationRetrieved();
        if (currentLocationRetrieved != null) {
            cdrModel.setCurrentLocationRetrieved(String.valueOf(currentLocationRetrieved));
            stringBuilder.append(cdrModel.getCurrentLocationRetrieved()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * IMSI
         */
        IMSI imsi = gmlcCdrState.getImsi();
        if (imsi != null) {
            String imsiStr;
            if (imsi.getData() != null) {
                imsiStr = new String(imsi.getData().getBytes(), StandardCharsets.ISO_8859_1);
                cdrModel.setImsi(imsiStr);
                stringBuilder.append(cdrModel.getImsi()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LMSI
         */
        LMSI lmsi = gmlcCdrState.getLmsi();
        if (lmsi != null) {
            String lmsiStr = bytesToHex(lmsi.getData());
            cdrModel.setLmsi(lmsiStr);
            stringBuilder.append(cdrModel.getLmsi()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }


        /**
         * MAP LSM (SRILCS, PSL & SLR) and LTE LCS (RIR, PLR, LRR) gathered parameters
         */

        /**
         * NETWORK NODE NUMBER
         */
        ISDNAddressString networkNodeNumber = gmlcCdrState.getNetworkNodeNumber();
        if (networkNodeNumber != null) {
            cdrModel.setNetworkNodeNumber(networkNodeNumber.getAddress());
            stringBuilder.append(cdrModel.getNetworkNodeNumber()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * GPRS NODE INDICATOR
         */
        Boolean gprsNodeIndicator = gmlcCdrState.isGprsNodeIndicator();
        if (gprsNodeIndicator != null) {
            cdrModel.setGprsNodeIndicator(String.valueOf(gprsNodeIndicator));
            stringBuilder.append(cdrModel.getGprsNodeIndicator()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * ADDITIONAL NUMBER
         */
        AdditionalNumber additionalNumber = gmlcCdrState.getAdditionalNumber();
        if (additionalNumber != null) {
            if (additionalNumber.getMSCNumber() != null) {
                // MAP LSM ADDITIONAL NUMBER (MSC)
                cdrModel.setMapMSCNumber(additionalNumber.getMSCNumber().getAddress());
                stringBuilder.append(cdrModel.getMapMSCNumber()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
            if (additionalNumber.getSGSNNumber() != null) {
                // MAP LSM ADDITIONAL NUMBER (SGSN)
                cdrModel.setMapSGSNNumber(additionalNumber.getSGSNNumber().getAddress());
                stringBuilder.append(cdrModel.getMapSGSNNumber()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * MME NAME
         */
        DiameterIdentity mmeName = gmlcCdrState.getMmeName();
        if (mmeName != null) {
            String mmeNameStr = new String(mmeName.getData(), StandardCharsets.ISO_8859_1);
            cdrModel.setMmeName(mmeNameStr);
            stringBuilder.append(cdrModel.getMmeName()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * MME REALM
         */
        DiameterIdentity mmeRealm = gmlcCdrState.getMmeRealm();
        if (mmeRealm != null) {
            String mmeRealmStr = new String(mmeRealm.getData(), StandardCharsets.ISO_8859_1);
            cdrModel.setMmeRealm(mmeRealmStr);
            stringBuilder.append(cdrModel.getMmeRealm()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * SGSN NAME
         */
        DiameterIdentity sgsnName = gmlcCdrState.getSgsnName();
        if (sgsnName != null) {
            String sgsnNameStr = new String(sgsnName.getData(), StandardCharsets.ISO_8859_1);
            cdrModel.setSgsnName(sgsnNameStr);
            stringBuilder.append(cdrModel.getSgsnName()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * SGSN REALM
         */
        DiameterIdentity sgsnRealm = gmlcCdrState.getSgsnRealm();
        if (sgsnRealm != null) {
            String sgsnRealmStr = new String(sgsnRealm.getData(), StandardCharsets.ISO_8859_1);
            cdrModel.setSgsnRealm(sgsnRealmStr);
            stringBuilder.append(cdrModel.getSgsnRealm()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * H-GMLC ADDRESS
         */
        GSNAddress hGmlcAddress = gmlcCdrState.gethGmlcAddress();
        if (hGmlcAddress != null) {
            // H-GMLC ADDRESS TYPE
            if (hGmlcAddress.getGSNAddressAddressType() != null) {
                cdrModel.setHgmlcAddressType(String.valueOf(hGmlcAddress.getGSNAddressAddressType().getCode()));
                stringBuilder.append(cdrModel.getHgmlcAddressType()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
            // H-GMLC ADDRESS DATA
            if (hGmlcAddress.getGSNAddressData() != null) {
                String hGmlcAddressData = bytesToHexString(hGmlcAddress.getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(hGmlcAddressData));
                    hGmlcAddressData = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                cdrModel.setHgmlcAddressData(hGmlcAddressData);
                stringBuilder.append(cdrModel.getHgmlcAddressData()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * V-GMLC ADDRESS
         */
        GSNAddress vGmlcAddress = gmlcCdrState.getvGmlcAddress();
        if (vGmlcAddress != null) {
            // V-GMLC ADDRESS TYPE
            if (vGmlcAddress.getGSNAddressAddressType() != null) {
                cdrModel.setVgmlcAddressType(String.valueOf(vGmlcAddress.getGSNAddressAddressType().getCode()));
                stringBuilder.append(cdrModel.getVgmlcAddressType()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
            // V-GMLC ADDRESS DATA
            if (vGmlcAddress.getGSNAddressData() != null) {
                String vGmlcAddressData = bytesToHexString(vGmlcAddress.getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(vGmlcAddressData));
                    vGmlcAddressData = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                cdrModel.setVgmlcAddressData(vGmlcAddressData);
                stringBuilder.append(cdrModel.getVgmlcAddressData()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * PPR ADDRESS
         */
        GSNAddress pprAddress = gmlcCdrState.getPprAddress();
        if (pprAddress != null) {
            // PPR ADDRESS TYPE
            if (pprAddress.getGSNAddressAddressType() != null) {
                cdrModel.setPprAddressType(String.valueOf(pprAddress.getGSNAddressAddressType().getCode()));
                stringBuilder.append(cdrModel.getPprAddressType()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
            // PPR ADDRESS DATA
            if (pprAddress.getGSNAddressData() != null) {
                String pprAddressData = bytesToHexString(pprAddress.getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(pprAddressData));
                    pprAddressData = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                cdrModel.setPprAddressData(pprAddressData);
                stringBuilder.append(cdrModel.getPprAddressData()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LOCATION ESTIMATE
         */
        ExtGeographicalInformation locationEstimate = gmlcCdrState.getLocationEstimate();
        if (locationEstimate != null) {
            // LOCATION ESTIMATE TYPE OF SHAPE
            if (locationEstimate.getTypeOfShape() != null) {
                cdrModel.setLocationEstimateTypeOfShape(String.valueOf(locationEstimate.getTypeOfShape()));
                stringBuilder.append(cdrModel.getLocationEstimateTypeOfShape()).append(SEPARATOR);
            } else
                stringBuilder.append(SEPARATOR);

            // LOCATION ESTIMATE LATITUDE
            if (Double.valueOf(locationEstimate.getLatitude()) != null) {
                if (locationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    String formattedLatitude;
                    formattedLatitude = coordinatesFormat.format(Double.valueOf(locationEstimate.getLatitude()));
                    cdrModel.setLocationEstimateLatitude(formattedLatitude);
                    stringBuilder.append(cdrModel.getLocationEstimateLatitude()).append(SEPARATOR);
                }
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE LONGITUDE
            if (Double.valueOf(locationEstimate.getLongitude()) != null) {
                if (locationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    String formattedLongitude;
                    formattedLongitude = coordinatesFormat.format(Double.valueOf(locationEstimate.getLongitude()));
                    cdrModel.setLocationEstimateLongitude(formattedLongitude);
                    stringBuilder.append(cdrModel.getLocationEstimateLongitude()).append(SEPARATOR);
                }
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE UNCERTAINTY
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyCircle) {
                if (Double.valueOf(locationEstimate.getUncertainty()) != null) {
                    cdrModel.setLocationEstimateUncertainty(uncertaintyFormat.format(locationEstimate.getUncertainty()));
                    stringBuilder.append(cdrModel.getLocationEstimateUncertainty()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE UNCERTAINTY SEMI MAJOR AXIS
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                    locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                if (Double.valueOf(locationEstimate.getUncertaintySemiMajorAxis()) != null) {
                    cdrModel.setLocationEstimateUncertaintySemiMajorAxis(uncertaintyFormat.format(locationEstimate.getUncertaintySemiMajorAxis()));
                    stringBuilder.append(cdrModel.getLocationEstimateUncertaintySemiMajorAxis()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE UNCERTAINTY SEMI MINOR AXIS
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                    locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                if (Double.valueOf(locationEstimate.getUncertaintySemiMinorAxis()) != null) {
                    cdrModel.setLocationEstimateUncertaintySemiMinorAxis(uncertaintyFormat.format(locationEstimate.getUncertaintySemiMinorAxis()));
                    stringBuilder.append(cdrModel.getLocationEstimateUncertaintySemiMinorAxis()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE CONFIDENCE
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                    locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid ||
                    locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                if (Integer.valueOf(locationEstimate.getConfidence()) != null) {
                    cdrModel.setLocationEstimateConfidence(String.valueOf(locationEstimate.getConfidence()));
                    stringBuilder.append(cdrModel.getLocationEstimateConfidence()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE ANGLE OF MAJOR AXIS
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                    locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                if (Double.valueOf(locationEstimate.getAngleOfMajorAxis()) != null) {
                    cdrModel.setLocationEstimateAngleOfMajorAxis(String.valueOf(locationEstimate.getAngleOfMajorAxis()));
                    stringBuilder.append(cdrModel.getLocationEstimateAngleOfMajorAxis()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE ALTITUDE
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                    locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid ||
                    locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                if (Integer.valueOf(locationEstimate.getAltitude()) != null) {
                    cdrModel.setLocationEstimateAltitude(String.valueOf(locationEstimate.getAltitude()));
                    stringBuilder.append(cdrModel.getLocationEstimateAltitude()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE UNCERTAINTY ALTITUDE
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                if (Double.valueOf(locationEstimate.getUncertaintyAltitude()) != null) {
                    cdrModel.setLocationEstimateUncertaintyAltitude(uncertaintyFormat.format(locationEstimate.getUncertaintyAltitude()));
                    stringBuilder.append(cdrModel.getLocationEstimateUncertaintyAltitude()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE INNER RADIUS
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                if (Integer.valueOf(locationEstimate.getInnerRadius()) != null) {
                    cdrModel.setLocationEstimateInnerRadius(String.valueOf(locationEstimate.getInnerRadius()));
                    stringBuilder.append(cdrModel.getLocationEstimateInnerRadius()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE UNCERTAINTY RADIUS
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                if (Double.valueOf(locationEstimate.getUncertaintyRadius()) != null) {
                    cdrModel.setLocationEstimateUncertaintyRadius(uncertaintyFormat.format(locationEstimate.getUncertaintyRadius()));
                    stringBuilder.append(cdrModel.getLocationEstimateUncertaintyRadius()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE OFFSET ANGLE
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                if (Double.valueOf(locationEstimate.getOffsetAngle()) != null) {
                    cdrModel.setLocationEstimateOffSetAngle(String.valueOf(locationEstimate.getOffsetAngle()));
                    stringBuilder.append(cdrModel.getLocationEstimateOffSetAngle()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

            // LOCATION ESTIMATE INCLUDED ANGLE
            if (locationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                if (Double.valueOf(locationEstimate.getIncludedAngle()) != null) {
                    cdrModel.setLocationEstimateIncludedAngle(String.valueOf(locationEstimate.getIncludedAngle()));
                    stringBuilder.append(cdrModel.getLocationEstimateIncludedAngle()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }

        } else {
            // NULL LOCATION ESTIMATE
            stringBuilder.append(SEPARATOR); // TYPE OF SHAPE
            stringBuilder.append(SEPARATOR); // LATITUDE
            stringBuilder.append(SEPARATOR); // LONGITUDE
            stringBuilder.append(SEPARATOR); // UNCERTAINTY
            stringBuilder.append(SEPARATOR); // UNCERTAINTY SEMI MAJOR AXIS
            stringBuilder.append(SEPARATOR); // UNCERTAINTY SEMI MINOR AXIS
            stringBuilder.append(SEPARATOR); // CONFIDENCE
            stringBuilder.append(SEPARATOR); // ANGLE OF MAJOR AXIS
            stringBuilder.append(SEPARATOR); // ALTITUDE
            stringBuilder.append(SEPARATOR); // UNCERTAINTY ALTITUDE
            stringBuilder.append(SEPARATOR); // INNER RADIUS
            stringBuilder.append(SEPARATOR); // UNCERTAINTY RADIUS
            stringBuilder.append(SEPARATOR); // OFFSET ANGLE
            stringBuilder.append(SEPARATOR); // INCLUDED ANGLE
        }

        /**
         * ADDITIONAL LOCATION ESTIMATE
         */
        AddGeographicalInformation additionalLocationEstimate = gmlcCdrState.getAdditionalLocationEstimate();
        if (additionalLocationEstimate != null) {

            // ADDITIONAL LOCATION ESTIMATE TYPE OF SHAPE
            if (additionalLocationEstimate.getTypeOfShape() != null) {
                cdrModel.setAdditionalLocationEstimateTypeOfShape(String.valueOf(additionalLocationEstimate.getTypeOfShape()));
                stringBuilder.append(cdrModel.getAdditionalLocationEstimateTypeOfShape()).append(SEPARATOR);
            } else
                stringBuilder.append(SEPARATOR);

            // ADDITIONAL LOCATION ESTIMATE TYPE OF SHAPE == Polygon
            if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.Polygon) {

                // ADDITIONAL LOCATION ESTIMATE POLYGON NUMBER OF POINTS
                Integer additionalPolygonNumberOfPoints = gmlcCdrState.getAdditionalNumberOfPoints();
                if (additionalPolygonNumberOfPoints != null) {
                    cdrModel.setAdditionalLocationEstimatePolygonNumberOfPoint(String.valueOf(additionalPolygonNumberOfPoints));
                    stringBuilder.append(cdrModel.getAdditionalLocationEstimatePolygonNumberOfPoint()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE LATITUDES & LONGITUDES of each POLYGON point
                Polygon additionalLocationEstimatePolygon = gmlcCdrState.getAdditionalPolygon();
                int additionalCoordinates = 0;
                StringBuilder locationsPoints = new StringBuilder();
                if (additionalLocationEstimatePolygon.getNumberOfPoints() > 2 && additionalLocationEstimatePolygon.getNumberOfPoints() <= 15) {
                    Double[][] polygonArray = new Double[additionalLocationEstimatePolygon.getNumberOfPoints()][additionalLocationEstimatePolygon.getNumberOfPoints()];
                    Double lat, lon;
                    String formattedLatitude, formattedLongitude;
                    for (additionalCoordinates = 0; additionalCoordinates < additionalLocationEstimatePolygon.getNumberOfPoints(); additionalCoordinates++) {
                        lat = additionalLocationEstimatePolygon.getEllipsoidPoint(additionalCoordinates).getLatitude();
                        lon = additionalLocationEstimatePolygon.getEllipsoidPoint(additionalCoordinates).getLongitude();
                        polygonArray[additionalCoordinates][0] = lat;
                        polygonArray[additionalCoordinates][1] = lon;
                        formattedLatitude = coordinatesFormat.format(lat);
                        formattedLongitude = coordinatesFormat.format(lon);
                        locationsPoints.append("-{").append(formattedLatitude).append(",").append(formattedLongitude).append("}");
                        stringBuilder.append(formattedLatitude).append(SEPARATOR);
                        stringBuilder.append(formattedLongitude).append(SEPARATOR);

                    }
                    while (additionalCoordinates < 15) {
                        stringBuilder.append(SEPARATOR);
                        stringBuilder.append(SEPARATOR);
                        locationsPoints.append("-{0").append(",").append("0}");
                        additionalCoordinates++;
                    }
                    cdrModel.setAdditionalLocationEstPolyListLatLongPoints(locationsPoints.toString());
                    List<Point2D> listOfPoints = new ArrayList<>();
                    Point2D[] point2D = new Point2D.Double[polygonArray.length];
                    Point2D polygonPoint;
                    for (int point = 0; point < polygonArray.length; point++) {
                        lat = polygonArray[point][0];
                        lon = polygonArray[point][1];
                        polygonPoint = new Point2D.Double(lat, lon);
                        listOfPoints.add(polygonPoint);
                        point2D[point] = listOfPoints.get(point);
                    }
                    formattedLatitude = coordinatesFormat.format(polygonCentroid(point2D).getX());
                    formattedLongitude = coordinatesFormat.format(polygonCentroid(point2D).getY());
                    cdrModel.setPolygonCentroIdLatitude(formattedLatitude);
                    cdrModel.setPolygonCentroIdLongitude(formattedLongitude);
                    stringBuilder.append(formattedLatitude).append(SEPARATOR);
                    stringBuilder.append(formattedLongitude).append(SEPARATOR);
                }

            } else {

                // ADDITIONAL LOCATION ESTIMATE LATITUDE
                if (Double.valueOf(additionalLocationEstimate.getLatitude()) != null) {
                    String formattedAdditionalLocationEstimateLatitude;
                    formattedAdditionalLocationEstimateLatitude = coordinatesFormat.format(Double.valueOf(additionalLocationEstimate.getLatitude()));
                    cdrModel.setAdditionalLocationEstimateLatitude(formattedAdditionalLocationEstimateLatitude);
                    stringBuilder.append(cdrModel.getAdditionalLocationEstimateLatitude()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE LONGITUDE
                if (Double.valueOf(additionalLocationEstimate.getLongitude()) != null) {
                    String formattedAdditionalLocationEstimateLongitude;
                    formattedAdditionalLocationEstimateLongitude = coordinatesFormat.format(Double.valueOf(additionalLocationEstimate.getLongitude()));
                    cdrModel.setAdditionalLocationEstimateLongitude(formattedAdditionalLocationEstimateLongitude);
                    stringBuilder.append(cdrModel.getAdditionalLocationEstimateLongitude()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyCircle) {
                    if (Double.valueOf(additionalLocationEstimate.getUncertainty()) != null) {
                        cdrModel.setAdditionalLocationEstimateUncertainty(uncertaintyFormat.format(Double.valueOf(additionalLocationEstimate.getUncertainty())));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateUncertainty()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY SEMI MAJOR AXIS
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                        additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                    if (Double.valueOf(additionalLocationEstimate.getUncertaintySemiMajorAxis()) != null) {
                        cdrModel.setAdditionalLocationEstimateUncertaintySemiMajorAxis(uncertaintyFormat.format(Double.valueOf(additionalLocationEstimate.getUncertaintySemiMajorAxis())));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateUncertaintySemiMajorAxis()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY SEMI MINOR AXIS
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                        additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                    if (Double.valueOf(additionalLocationEstimate.getUncertaintySemiMinorAxis()) != null) {
                        cdrModel.setAdditionalLocationEstimateUncertaintySemiMinorAxis(uncertaintyFormat.format(Double.valueOf(additionalLocationEstimate.getUncertaintySemiMinorAxis())));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateUncertaintySemiMinorAxis()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE ANGLE OF MAJOR AXIS
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                        additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                    if (Double.valueOf(additionalLocationEstimate.getAngleOfMajorAxis()) != null) {
                        cdrModel.setAdditionalLocationEstimateAngleOfMajorAxis(String.valueOf(additionalLocationEstimate.getAngleOfMajorAxis()));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateAngleOfMajorAxis()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE CONFIDENCE
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithUncertaintyEllipse ||
                        additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid ||
                        additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                    if (Integer.valueOf(additionalLocationEstimate.getConfidence()) != null) {
                        cdrModel.setAdditionalLocationEstimateConfidence(String.valueOf(additionalLocationEstimate.getConfidence()));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateConfidence()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE ALTITUDE
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitude ||
                        additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitude ||
                        additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                    if (Integer.valueOf(additionalLocationEstimate.getAltitude()) != null &&
                            (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitude ||
                                    additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitude ||
                                    additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid)) {
                        cdrModel.setAdditionalLocationEstimateAltitude(String.valueOf(additionalLocationEstimate.getAltitude()));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateAltitude()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY ALTITUDE
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid) {
                    if (Double.valueOf(additionalLocationEstimate.getUncertaintyAltitude()) != null) {
                        cdrModel.setAdditionalLocationEstimateUncertaintyAltitude(uncertaintyFormat.format(additionalLocationEstimate.getUncertaintyAltitude()));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateUncertaintyAltitude()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE INNER RADIUS
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                    if (Integer.valueOf(additionalLocationEstimate.getInnerRadius()) != null) {
                        cdrModel.setAdditionalLocationEstimateInnerRadius(String.valueOf(additionalLocationEstimate.getInnerRadius()));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateInnerRadius()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY RADIUS
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                    if (Double.valueOf(additionalLocationEstimate.getUncertaintyRadius()) != null &&
                            additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                        cdrModel.setAdditionalLocationEstimateUncertaintyRadius(uncertaintyFormat.format(Double.valueOf(additionalLocationEstimate.getUncertaintyRadius())));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateUncertaintyRadius()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE OFFSET ANGLE
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                    if (Double.valueOf(additionalLocationEstimate.getOffsetAngle()) != null &&
                            additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                        cdrModel.setAdditionalLocationEstimateOffSetAngle(String.valueOf(additionalLocationEstimate.getOffsetAngle()));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateOffSetAngle()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

                // ADDITIONAL LOCATION ESTIMATE INCLUDED ANGLE
                if (additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                    if (Double.valueOf(additionalLocationEstimate.getIncludedAngle()) != null &&
                            additionalLocationEstimate.getTypeOfShape() == TypeOfShape.EllipsoidArc) {
                        cdrModel.setAdditionalLocationEstimateIncludedAngle(String.valueOf(additionalLocationEstimate.getIncludedAngle()));
                        stringBuilder.append(cdrModel.getAdditionalLocationEstimateIncludedAngle()).append(SEPARATOR);
                    } else {
                        stringBuilder.append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }

            }
        } else {
            // NULL ADDITIONAL LOCATION ESTIMATE
            stringBuilder.append(SEPARATOR); // TYPE OF SHAPE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON NUMBER OF POINTS
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION POLYGON LATITUDE POINT 1
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION POLYGON LONGITUDE POINT 1
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 2
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 2
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 3
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 3
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 4
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 4
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 5
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 5
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 6
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 6
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 7
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 7
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 8
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 8
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 9
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 9
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 10
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 10
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 11
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 11
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 12
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 12
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 13
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 13
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 14
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 14
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LATITUDE POINT 15
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON LONGITUDE POINT 15
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON CENTROID LATITUDE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE POLYGON CENTROID LONGITUDE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE LATITUDE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE LONGITUDE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY SEMI MAJOR AXIS
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY SEMI MINOR AXIS
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE ANGLE OF MAJOR AXIS
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE CONFIDENCE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE ALTITUDE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY ALTITUDE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE INNER RADIUS
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE UNCERTAINTY RADIUS
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE OFFSET ANGLE
            stringBuilder.append(SEPARATOR); // ADDITIONAL LOCATION ESTIMATE INCLUDED ANGLE
        }

        /**
         * AGE OF LOCATION ESTIMATE
         */
        Integer ageOfLocationEstimate = gmlcCdrState.getAgeOfLocationEstimate();
        if (ageOfLocationEstimate != null) {
            cdrModel.setAgeOfLocationEstimate(String.valueOf(ageOfLocationEstimate));
            stringBuilder.append(cdrModel.getAgeOfLocationEstimate()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * GERAN POSITIONING DATA
         */
        PositioningDataInformation geranPositioningDataInformation = gmlcCdrState.getGeranPositioningDataInformation();
        if (geranPositioningDataInformation != null) {
            cdrModel.setGeranPositioningDataInformation(bytesToHexString(geranPositioningDataInformation.getData()));
            stringBuilder.append(cdrModel.getGeranPositioningDataInformation()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * GERAN GANSS POSITIONING DATA
         */
        GeranGANSSpositioningData geranGANSSPositioningDataInformation = gmlcCdrState.getGeranGANSSpositioningData();
        if (geranGANSSPositioningDataInformation != null) {
            cdrModel.setGeranGANSSPositioningDataInformation(bytesToHexString(geranGANSSPositioningDataInformation.getData()));
            stringBuilder.append(cdrModel.getGeranGANSSPositioningDataInformation()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * UTRAN POSITIONING DATA
         */
        UtranPositioningDataInfo utranPositioningDataInfo = gmlcCdrState.getUtranPositioningDataInfo();
        if (utranPositioningDataInfo != null) {
            cdrModel.setUtranPositioningDataInfo(bytesToHexString(utranPositioningDataInfo.getData()));
            stringBuilder.append(cdrModel.getUtranPositioningDataInfo()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * UTRAN GANSS POSITIONING DATA
         */
        UtranGANSSpositioningData utranGANSSpositioningData = gmlcCdrState.getUtranGANSSpositioningData();
        if (utranGANSSpositioningData != null) {
            cdrModel.setUtranGANSSpositioningData(bytesToHexString(utranGANSSpositioningData.getData()));
            stringBuilder.append(cdrModel.getUtranGANSSpositioningData()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * UTRAN ADDITIONAL POSITIONING DATA
         */
        String utranAddPositioningDataInfo = gmlcCdrState.getUtranAdditionalPositioningData();
        if (utranAddPositioningDataInfo != null) {
            cdrModel.setUtranAdditionalPositioningDataInfo(utranAddPositioningDataInfo);
            stringBuilder.append(cdrModel.getUtranAdditionalPositioningDataInfo()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * E-UTRAN POSITIONING DATA
         */
        String eUtranPositioningDataInfo = gmlcCdrState.geteUTRANPositioningData();
        if (eUtranPositioningDataInfo != null) {
            cdrModel.setEutranPositioningDataInfo(eUtranPositioningDataInfo);
            stringBuilder.append(cdrModel.getEutranPositioningDataInfo()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * DEFERRED MT LR RESPONSE INDICATOR
         */
        Boolean deferredMTLRResponseIndicator = gmlcCdrState.isDeferredMTLRResponseIndicator();
        if (deferredMTLRResponseIndicator != null) {
            cdrModel.setDeferredMTLRResponseIndicator(String.valueOf(deferredMTLRResponseIndicator));
            stringBuilder.append(cdrModel.getDeferredMTLRResponseIndicator()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * CGI or SAI or LAI
         */
        CellGlobalIdOrServiceAreaIdOrLAI lcsCGIorSAIorLAI = gmlcCdrState.getCellGlobalIdOrServiceAreaIdOrLAI();
        if (lcsCGIorSAIorLAI != null) {
            if (lcsCGIorSAIorLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                try {
                    // LCS CGI MCC
                    cdrModel.setLcsCGIorSAIorLAIMCC(String.valueOf(lcsCGIorSAIorLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC()));
                    stringBuilder.append(cdrModel.getLcsCGIorSAIorLAIMCC()).append(SEPARATOR);
                    // LCS CGI MNC
                    cdrModel.setLcsCGIorSAIorLAIMNC(String.valueOf(lcsCGIorSAIorLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC()));
                    stringBuilder.append(cdrModel.getLcsCGIorSAIorLAIMNC()).append(SEPARATOR);
                    // LCS CGI LAC
                    cdrModel.setLcsCGIorSAIorLAILAC(String.valueOf(lcsCGIorSAIorLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getLac()));
                    stringBuilder.append(cdrModel.getLcsCGIorSAIorLAILAC()).append(SEPARATOR);
                    // LCS CGI CI
                    cdrModel.setLcsCGIorSAIorLAICI(String.valueOf(lcsCGIorSAIorLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode()));
                    stringBuilder.append(cdrModel.getLcsCGIorSAIorLAICI()).append(SEPARATOR);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
            }
            if (lcsCGIorSAIorLAI.getLAIFixedLength() != null) {
                try {
                    // LCS CGI MCC
                    cdrModel.setLcsCGIorSAIorLAIMCC(String.valueOf(lcsCGIorSAIorLAI.getLAIFixedLength().getMCC()));
                    stringBuilder.append(cdrModel.getLcsCGIorSAIorLAIMCC()).append(SEPARATOR);
                    // LCS CGI MNC
                    cdrModel.setLcsCGIorSAIorLAIMNC(String.valueOf(lcsCGIorSAIorLAI.getLAIFixedLength().getMNC()));
                    stringBuilder.append(cdrModel.getLcsCGIorSAIorLAIMNC()).append(SEPARATOR);
                    // LCS CGI LAC
                    cdrModel.setLcsCGIorSAIorLAILAC(String.valueOf(lcsCGIorSAIorLAI.getLAIFixedLength().getLac()));
                    stringBuilder.append(cdrModel.getLcsCGIorSAIorLAILAC()).append(SEPARATOR);

                    stringBuilder.append(SEPARATOR);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /*
         * LTE ECGI (obtained from SLg)
         */
        EUTRANCGI lcsEutranCgi = gmlcCdrState.getEUtranCgi();
        if (lcsEutranCgi != null) {
            try {
                // ECGI MCC
                int ecgiMcc = lcsEutranCgi.getMCC();
                cdrModel.setLcsEutranCgiMCC(String.valueOf(ecgiMcc));
                stringBuilder.append(cdrModel.getLcsEutranCgiMCC()).append(SEPARATOR);
                // ECGI MNC
                int ecgiMnc = lcsEutranCgi.getMNC();
                cdrModel.setLcsEutranCgiMNC(String.valueOf(ecgiMnc));
                stringBuilder.append(cdrModel.getLcsEutranCgiMNC()).append(SEPARATOR);
                // ECGI ECI
                long eci = lcsEutranCgi.getEci();
                cdrModel.setLcsEutranCgiECI(String.valueOf(eci));
                stringBuilder.append(cdrModel.getLcsEutranCgiECI()).append(SEPARATOR);
                // ECGI ENBID
                long ecgiENBId = lcsEutranCgi.getENodeBId();
                cdrModel.setLcsEutranCgiENBID(String.valueOf(ecgiENBId));
                stringBuilder.append(cdrModel.getLcsEutranCgiENBID()).append(SEPARATOR);
                // ECGI CI
                int ecgiCi = lcsEutranCgi.getCi();
                cdrModel.setLcsEutranCgiCI(String.valueOf(ecgiCi));
                stringBuilder.append(cdrModel.getLcsEutranCgiCI()).append(SEPARATOR);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }
        if (cellPortionId != null) {
            cdrModel.setLcsCellPortionId(String.valueOf(cellPortionId));
            stringBuilder.append(cdrModel.getLcsCellPortionId()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * PSEUDONYM INDICATOR
         */
        Boolean pseudonymIndicator = gmlcCdrState.isPseudonymIndicator();
        if (pseudonymIndicator != null) {
            cdrModel.setPseudonymIndicator(String.valueOf(pseudonymIndicator));
            stringBuilder.append(cdrModel.getPseudonymIndicator()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * ACCURACY FULFILLMENT INDICATOR
         */
        AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = gmlcCdrState.getAccuracyFulfilmentIndicator();
        if (accuracyFulfilmentIndicator != null) {
            cdrModel.setAccuracyFulfilmentIndicator(String.valueOf(accuracyFulfilmentIndicator.getIndicator()));
            stringBuilder.append(cdrModel.getAccuracyFulfilmentIndicator()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * SEQUENCE NUMBER
         */
        Integer sequenceNumber = gmlcCdrState.getSequenceNumber();
        if (sequenceNumber != null) {
            cdrModel.setSequenceNumber(String.valueOf(sequenceNumber));
            stringBuilder.append(cdrModel.getSequenceNumber()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * VELOCITY ESTIMATE
         */
        // HORIZONTAL VELOCITY ESTIMATE
        VelocityEstimate horizontalVelocityEstimate = gmlcCdrState.getVelocityEstimate();
        if (horizontalVelocityEstimate != null) {
            cdrModel.setHorizontalVelocityEstimate(String.valueOf(horizontalVelocityEstimate.getHorizontalSpeed()));
            stringBuilder.append(cdrModel.getHorizontalVelocityEstimate()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        // VELOCITY ESTIMATE BEARING
        VelocityEstimate velocityEstimateBearing = gmlcCdrState.getVelocityEstimate();
        if (velocityEstimateBearing != null) {
            cdrModel.setVelocityEstimateBearing(String.valueOf(velocityEstimateBearing.getBearing()));
            stringBuilder.append(cdrModel.getVelocityEstimateBearing()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        // VERTICAL VELOCITY ESTIMATE
        VelocityEstimate verticalVelocityEstimate = gmlcCdrState.getVelocityEstimate();
        if (verticalVelocityEstimate != null) {
            cdrModel.setVerticalVelocityEstimate(String.valueOf(verticalVelocityEstimate.getVerticalSpeed()));
            stringBuilder.append(cdrModel.getVerticalVelocityEstimate()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        // VELOCITY ESTIMATE HORIZONTAL UNCERTAINTY
        VelocityEstimate velocityHorizontalUncertainty = gmlcCdrState.getVelocityEstimate();
        if (velocityHorizontalUncertainty != null) {
            cdrModel.setVelocityHorizontalUncertainty(String.valueOf(velocityHorizontalUncertainty.getUncertaintyHorizontalSpeed()));
            stringBuilder.append(cdrModel.getVelocityHorizontalUncertainty()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        // VELOCITY ESTIMATE VERTICAL UNCERTAINTY
        VelocityEstimate velocityVerticalUncertainty = gmlcCdrState.getVelocityEstimate();
        if (velocityVerticalUncertainty != null) {
            cdrModel.setVelocityVerticalUncertainty(String.valueOf(velocityVerticalUncertainty.getUncertaintyVerticalSpeed()));
            stringBuilder.append(cdrModel.getVelocityVerticalUncertainty()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        // VELOCITY ESTIMATE TYPE
        VelocityEstimate velocityType = gmlcCdrState.getVelocityEstimate();
        if (velocityType != null) {
            cdrModel.setVelocityType(String.valueOf(velocityType.getVelocityType().name()));
            stringBuilder.append(cdrModel.getVelocityType()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * SERVING NODE ADDRESS
         */
        ServingNodeAddress servingNodeAddress = gmlcCdrState.getServingNodeAddress();
        if (servingNodeAddress != null) {
            if (servingNodeAddress.getMscNumber() == null) {
                stringBuilder.append(SEPARATOR);
            } else {
                // SERVING NODE ADDRESS MSC NUMBER
                cdrModel.setServingNodeAddressMSCNumber(servingNodeAddress.getMscNumber().getAddress());
                stringBuilder.append(cdrModel.getServingNodeAddressMSCNumber()).append(SEPARATOR);
            }
            if (servingNodeAddress.getSgsnNumber() == null) {
                stringBuilder.append(SEPARATOR);
            } else {
                // SERVING NODE ADDRESS SGSN Number
                cdrModel.setServingNodeAddressSGSNNumber(servingNodeAddress.getSgsnNumber().getAddress());
                stringBuilder.append(cdrModel.getServingNodeAddressSGSNNumber()).append(SEPARATOR);
            }
            if (servingNodeAddress.getMmeNumber() == null) {
                stringBuilder.append(SEPARATOR);
            } else {
                // SERVING NODE ADDRESS MME NUMBER
                String mmeNumStr = new String(servingNodeAddress.getMmeNumber().getData(), StandardCharsets.ISO_8859_1);
                cdrModel.setServingNodeAddressMMENumber(mmeNumStr);
                stringBuilder.append(cdrModel.getServingNodeAddressMMENumber()).append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LCSClientID
         */
        LCSClientID lcsClientID = gmlcCdrState.getLcsClientID();
        if (lcsClientID != null) {
            try {
                // LCS CLIENT ID TYPE
                if (lcsClientID.getLCSClientType() != null && (lcsClientID.getLCSClientType().getType() > Integer.MIN_VALUE
                        && lcsClientID.getLCSClientType().getType() < Integer.MAX_VALUE)) {
                    cdrModel.setLcsClientType(String.valueOf(lcsClientID.getLCSClientType().getType()));
                    stringBuilder.append(cdrModel.getLcsClientType()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
                if (lcsClientID.getLCSClientName() != null) {
                    if (lcsClientID.getLCSClientName().getNameString() != null) {
                        // LCS CLIENT ID NAME
                        cdrModel.setLcsClientName(new String(lcsClientID.getLCSClientName().getNameString().getEncodedString()));
                        stringBuilder.append(cdrModel.getLcsClientName()).append(SEPARATOR);
                    }
                    // LCS CLIENT ID NAME DCS
                    cdrModel.setLcsClientDCS(String.valueOf(lcsClientID.getLCSClientName().getDataCodingScheme().getCode()));
                    stringBuilder.append(cdrModel.getLcsClientDCS()).append(SEPARATOR);
                    // LCS CLIENT ID NAME FI
                    cdrModel.setLcsClientFI(String.valueOf(lcsClientID.getLCSClientName().getLCSFormatIndicator().getIndicator()));
                    stringBuilder.append(cdrModel.getLcsClientFI()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                if (lcsClientID.getLCSAPN() != null) {
                    // LCS CLIENT ID APN
                    if (lcsClientID.getLCSAPN().getApn() != null) {
                        cdrModel.setLcsClientAPN(new String(lcsClientID.getLCSAPN().getApn().getBytes(), StandardCharsets.ISO_8859_1));
                        stringBuilder.append(cdrModel.getLcsClientAPN()).append(SEPARATOR);
                    }
                } else {
                    stringBuilder.append(SEPARATOR);
                }
                // LCS CLIENT DIALED BY MS
                if (lcsClientID.getLCSClientDialedByMS() != null) {
                    cdrModel.setLcsClientDialedByMS(lcsClientID.getLCSClientDialedByMS().getAddress());
                    stringBuilder.append(cdrModel.getLcsClientDialedByMS()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
                // LCS CLIENT EXT ID
                if (lcsClientID.getLCSClientExternalID() != null) {
                    cdrModel.setLcsClientExternalID(lcsClientID.getLCSClientExternalID().getExternalAddress().getAddress());
                    stringBuilder.append(cdrModel.getLcsClientExternalID()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
                // LCS CLIENT INT ID
                if (lcsClientID.getLCSClientInternalID() != null && (lcsClientID.getLCSClientInternalID().getId() > Integer.MIN_VALUE
                        && lcsClientID.getLCSClientInternalID().getId() < Integer.MAX_VALUE)) {
                    cdrModel.setLcsClientInternalID(String.valueOf(lcsClientID.getLCSClientInternalID().getId()));
                    stringBuilder.append(cdrModel.getLcsClientInternalID()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
                if (lcsClientID.getLCSRequestorID() != null) {
                    // LCS CLIENT REQUESTOR DCS
                    cdrModel.setLcsClientRequestorDCS(String.valueOf(lcsClientID.getLCSRequestorID().getDataCodingScheme().getCode()));
                    stringBuilder.append(cdrModel.getLcsClientRequestorDCS()).append(SEPARATOR);
                    // LCS CLIENT REQUESTOR FI
                    cdrModel.setLcsClientRequestorFI(String.valueOf(lcsClientID.getLCSRequestorID().getLCSFormatIndicator().getIndicator()));
                    stringBuilder.append(cdrModel.getLcsClientRequestorFI()).append(SEPARATOR);
                    // LCS CLIENT REQUESTOR STRING
                    cdrModel.setLcsClientRequestorString(Arrays.toString(lcsClientID.getLCSRequestorID().getRequestorIDString().getEncodedString()));
                    stringBuilder.append(cdrModel.getLcsClientRequestorString()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LCS QoS
         */
        // LCS QoS HORIZONTAL ACCURACY
        LCSQoS horizontalAccuracy = gmlcCdrState.getLcsQoS();
        if (horizontalAccuracy != null) {
            if (horizontalAccuracy.getHorizontalAccuracy() != null) {
                cdrModel.setLcsQoSHorizontalAccuracy(String.valueOf(horizontalAccuracy.getHorizontalAccuracy().intValue()));
                stringBuilder.append(cdrModel.getLcsQoSHorizontalAccuracy()).append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
        }
        // LCS QoS VERTICAL ACCURACY
        LCSQoS verticalAccuracy = gmlcCdrState.getLcsQoS();
        if (verticalAccuracy != null) {
            if (verticalAccuracy.getVerticalAccuracy() != null) {
                cdrModel.setLcsQoSverticalAccuracy(String.valueOf(verticalAccuracy.getVerticalAccuracy().intValue()));
                stringBuilder.append(cdrModel.getLcsQoSverticalAccuracy()).append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
        }
        // LCS QoS VERTICAL COORDINATE REQUESTED
        LCSQoS verticalCoordinateRequest = gmlcCdrState.getLcsQoS();
        if (verticalCoordinateRequest != null) {
            if (verticalCoordinateRequest.getVerticalCoordinateRequest() || !verticalCoordinateRequest.getVerticalCoordinateRequest()) {
                cdrModel.setLcsQoSVerticalCoordinateRequest(String.valueOf(verticalCoordinateRequest.getVerticalCoordinateRequest()));
                stringBuilder.append(cdrModel.getLcsQoSVerticalCoordinateRequest()).append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
        }
        // LCS QoS RESPONSE TIME
        LCSQoS responseTime = gmlcCdrState.getLcsQoS();
        if (responseTime != null) {
            if (responseTime.getResponseTime() != null) {
                cdrModel.setLcsQoSResponseTime(String.valueOf(responseTime.getResponseTime().getResponseTimeCategory()));
                stringBuilder.append(cdrModel.getLcsQoSResponseTime()).append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
        }
        // LCS QoS CLASS
        LCSQoSClass lcsQoSClass = gmlcCdrState.getLteLcsQoSClass();
        if (lcsQoSClass != null) {
            if (lcsQoSClass.getValue() == 0)
                cdrModel.setLcsQoSClass("LCS-QoS-ASSURED");
            if (lcsQoSClass.getValue() == 1)
                cdrModel.setLcsQoSClass("LCS-QoS-BESTEFFORT");

            stringBuilder.append(cdrModel.getLcsQoSClass()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * Client LCS REFERENCE NUMBER
         */
        Integer clientReferenceNumber = gmlcCdrState.getClientReferenceNumber();
        if (clientReferenceNumber != null) {
            cdrModel.setClientReferenceNumber(String.valueOf(clientReferenceNumber));
            stringBuilder.append(cdrModel.getClientReferenceNumber()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LCS REFERENCE NUMBER
         */
        Integer lcsReferenceNumber = gmlcCdrState.getLcsReferenceNumber();
        if (lcsReferenceNumber != null) {
            cdrModel.setLcsReferenceNumber(String.valueOf(lcsReferenceNumber));
            stringBuilder.append(cdrModel.getLcsReferenceNumber()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LCS SERVICE TYPE ID
         */
        Integer lcsServiceTypeID = gmlcCdrState.getLcsServiceTypeID();
        if (lcsServiceTypeID != null) {
            cdrModel.setLcsServiceTypeID(String.valueOf(lcsServiceTypeID));
            stringBuilder.append(cdrModel.getLcsServiceTypeID()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * BAROMETRIC PRESSURE
         */
        Long barometricPressureMeasurement = gmlcCdrState.getBarometricPressureMeasurement();
        if (barometricPressureMeasurement != null) {
            cdrModel.setBarometricPressureMeasurement(String.valueOf(barometricPressureMeasurement));
            stringBuilder.append(cdrModel.getBarometricPressureMeasurement()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * CIVIC ADDRESS
         */
        String civicAddress = gmlcCdrState.getCivicAddress();
        if (civicAddress != null) {
            cdrModel.setCivicAddress(civicAddress);
            stringBuilder.append(cdrModel.getCivicAddress()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * LCS EVENT (MAP/LTE)
         */
        LCSEvent lcsEvent = gmlcCdrState.getLcsEvent();
        LocationEvent locationEvent = gmlcCdrState.getLocationEvent();
        if (lcsEvent != null) {
            // LCS EVENT (MAP)
            cdrModel.setLcsEvent(String.valueOf(lcsEvent.getEvent()));
            stringBuilder.append(cdrModel.getLcsEvent()).append(SEPARATOR);
        } else {
            if (locationEvent != null) {
                // LCS EVENT (LTE)
                cdrModel.setLcsEvent(String.valueOf(locationEvent.getValue()));
                stringBuilder.append(cdrModel.getLcsEvent()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
        }

        /**
         * MSISDN
         */
        ISDNAddressString msisdn = gmlcCdrState.getMsisdn();
        if (msisdn != null) {
            cdrModel.setMsisdnAddress(msisdn.getAddress());
            stringBuilder.append(cdrModel.getMsisdnAddress()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * IMEI
         */
        IMEI imei = gmlcCdrState.getImei();
        if (imei != null) {
            String imeiStr = new String(imei.getIMEI().getBytes());
            cdrModel.setImei(imeiStr);
            stringBuilder.append(cdrModel.getImei()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * DEFERRED MT LR DATA
         */
        // LOCATION EVENT TYPE
        DeferredmtlrData deferredLocationEventType = gmlcCdrState.getDeferredmtlrData();
        if (deferredLocationEventType != null) {
            if (deferredLocationEventType.getDeferredLocationEventType() != null) {
                if (deferredLocationEventType.getDeferredLocationEventType().getEnteringIntoArea())
                    cdrModel.setDeferredLocationEventType("EnteringIntoArea");
                if (deferredLocationEventType.getDeferredLocationEventType().getBeingInsideArea())
                    cdrModel.setDeferredLocationEventType("InsideArea");
                if (deferredLocationEventType.getDeferredLocationEventType().getMsAvailable())
                    cdrModel.setDeferredLocationEventType("Available");
                if (deferredLocationEventType.getDeferredLocationEventType().getLeavingFromArea())
                    cdrModel.setDeferredLocationEventType("LeavingFromArea");

                stringBuilder.append(cdrModel.getDeferredLocationEventType()).append(SEPARATOR);
            } else {
                stringBuilder.append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /*
         * DEFERRED MT-LR DATA
         */
        DeferredmtlrData deferredLcsLocationInfo = gmlcCdrState.getDeferredmtlrData();
        if (deferredLcsLocationInfo != null) {
            if (deferredLcsLocationInfo.getLCSLocationInfo() != null) {
                if (deferredLcsLocationInfo.getLCSLocationInfo().getNetworkNodeNumber() != null) {
                    // DEFERRED MT-LR DATA NETWORK NODE NUMBER
                    cdrModel.setDeferredLcsLocationInfoNetworkNodeNumber(deferredLcsLocationInfo.getLCSLocationInfo().getNetworkNodeNumber().getAddress());
                    stringBuilder.append(cdrModel.getDeferredLcsLocationInfoNetworkNodeNumber()).append(SEPARATOR);
                } else
                    stringBuilder.append(SEPARATOR);
                if (deferredLcsLocationInfo.getLCSLocationInfo().getGprsNodeIndicator()) {
                    // DEFERRED MT-LR DATA GPRS NODE IND
                    cdrModel.setDeferredLcsLocationInfoGprsNodeIndicator(String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getGprsNodeIndicator()));
                    stringBuilder.append(cdrModel.getDeferredLcsLocationInfoGprsNodeIndicator()).append(SEPARATOR);
                } else
                    stringBuilder.append(false).append(SEPARATOR);
                if (deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalNumber() != null) {
                    // DEFERRED MT-LR DATA ADDITIONAL NUMBER
                    if (deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalNumber().getMSCNumber() != null)
                        cdrModel.setDeferredLcsLocationInfoAdditionalNumber(deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalNumber().getMSCNumber().getAddress());

                    if (deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalNumber().getSGSNNumber() != null)
                        cdrModel.setDeferredLcsLocationInfoAdditionalNumber(deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalNumber().getSGSNNumber().getAddress());

                    stringBuilder.append(cdrModel.getDeferredLcsLocationInfoAdditionalNumber()).append(SEPARATOR);
                }
                if (deferredLcsLocationInfo.getLCSLocationInfo().getLMSI() != null) {
                    // DEFERRED MT-LR DATA LMSI
                    String lmsiStr = new String(deferredLcsLocationInfo.getLCSLocationInfo().getLMSI().getData(), StandardCharsets.ISO_8859_1);
                    cdrModel.setDeferredLcsLocationInfoLMSI(lmsiStr);
                    stringBuilder.append(cdrModel.getDeferredLcsLocationInfoLMSI()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
                if (deferredLcsLocationInfo.getLCSLocationInfo().getMmeName() != null) {
                    // DEFERRED MT-LR DATA MME NAME
                    String mmeNameStr = new String(deferredLcsLocationInfo.getLCSLocationInfo().getMmeName().getData(), StandardCharsets.ISO_8859_1);
                    cdrModel.setDeferredLcsLocationInfoMmeName(mmeNameStr);
                    stringBuilder.append(cdrModel.getDeferredLcsLocationInfoMmeName()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
                if (deferredLcsLocationInfo.getLCSLocationInfo().getAaaServerName() != null) {
                    // DEFERRED MT-LR DATA AAA SERVER NAME
                    String aaaServerNameStr = new String(deferredLcsLocationInfo.getLCSLocationInfo().getAaaServerName().getData(), StandardCharsets.ISO_8859_1);
                    cdrModel.setDeferredLcsLocationInfoAaaServerName(aaaServerNameStr);
                    stringBuilder.append(cdrModel.getDeferredLcsLocationInfoAaaServerName()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                }
                if (deferredLcsLocationInfo.getLCSLocationInfo().getSupportedLCSCapabilitySets() != null) {
                    String LcsCsR98_99 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease98_99());
                    String LcsCsR4 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease4());
                    String LcsCsR5 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease5());
                    String LcsCsR6 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease6());
                    String LcsCsR7 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease7());
                    // DEFERRED MT-LR DATA LCS CAPS R98_99
                    cdrModel.setDeferredLcsCsR98_99(LcsCsR98_99);
                    stringBuilder.append(cdrModel.getDeferredLcsCsR98_99()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA LCS CAPS R4
                    cdrModel.setDeferredLcsCsR4(LcsCsR4);
                    stringBuilder.append(cdrModel.getDeferredLcsCsR4()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA LCS CAPS R5
                    cdrModel.setDeferredLcsCsR5(LcsCsR5);
                    stringBuilder.append(cdrModel.getDeferredLcsCsR5()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA LCS CAPS R6
                    cdrModel.setDeferredLcsCsR6(LcsCsR6);
                    stringBuilder.append(cdrModel.getDeferredLcsCsR6()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA LCS CAPS R7
                    cdrModel.setDeferredLcsCsR7(LcsCsR7);
                    stringBuilder.append(cdrModel.getDeferredLcsCsR7()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
                if (deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalLCSCapabilitySets() != null) {
                    String aLCSCSR98_99 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease98_99());
                    String aLCSCSR4 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease4());
                    String aLCSCSR5 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease5());
                    String aLCSCSR6 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease6());
                    String aLCSCSR7 = String.valueOf(deferredLcsLocationInfo.getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease7());
                    // DEFERRED MT-LR DATA ADD LCS CAPS R98_99
                    cdrModel.setDeferredALcsCsR98_99(aLCSCSR98_99);
                    stringBuilder.append(cdrModel.getDeferredALcsCsR98_99()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA ADD LCS CAPS R4
                    cdrModel.setDeferredALcsCsR4(aLCSCSR4);
                    stringBuilder.append(cdrModel.getDeferredALcsCsR4()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA ADD LCS CAPS R5
                    cdrModel.setDeferredALcsCsR5(aLCSCSR5);
                    stringBuilder.append(cdrModel.getDeferredALcsCsR5()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA ADD LCS CAPS R6
                    cdrModel.setDeferredALcsCsR6(aLCSCSR6);
                    stringBuilder.append(cdrModel.getDeferredALcsCsR6()).append(SEPARATOR);
                    // DEFERRED MT-LR DATA ADD LCS CAPS R7
                    cdrModel.setDeferredALcsCsR7(aLCSCSR7);
                    stringBuilder.append(cdrModel.getDeferredALcsCsR7()).append(SEPARATOR);
                } else {
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                    stringBuilder.append(SEPARATOR);
                }
            }
        } else {
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
            stringBuilder.append(SEPARATOR);
        }

        // TERMINATION CAUSE
        DeferredmtlrData deferredTerminationCause = gmlcCdrState.getDeferredmtlrData();
        if (deferredTerminationCause != null) {
            cdrModel.setDeferredTerminationCause(String.valueOf(deferredTerminationCause.getTerminationCause()));
            stringBuilder.append(cdrModel.getDeferredTerminationCause()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * PERIODIC LDR INFO
         */
        // REPORTING AMOUNT
        PeriodicLDRInfo periodicReportingAmount = gmlcCdrState.getPeriodicLDRInfo();
        if (periodicReportingAmount != null) {
            cdrModel.setPeriodicReportingAmount(String.valueOf(periodicReportingAmount.getReportingAmount()));
            stringBuilder.append(cdrModel.getPeriodicReportingAmount()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        // REPORTING INTERVAL
        PeriodicLDRInfo periodicReportingInterval = gmlcCdrState.getPeriodicLDRInfo();
        if (periodicReportingInterval != null) {
            cdrModel.setPeriodicReportingInterval(String.valueOf(periodicReportingInterval.getReportingInterval()));
            stringBuilder.append(cdrModel.getPeriodicReportingInterval()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * MO-LR SHORT-CIRCUIT INDICATOR
         */
        Boolean moLrShortCircuitIndicator = gmlcCdrState.isMoLrShortCircuitIndicator();
        if (moLrShortCircuitIndicator != null) {
            cdrModel.setMoLrShortCircuitIndicator(String.valueOf(moLrShortCircuitIndicator));
            stringBuilder.append(cdrModel.getMoLrShortCircuitIndicator()).append(SEPARATOR);
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * REPORTING PLMN LIST
         */
        ReportingPLMNList reportingPLMNList = gmlcCdrState.getReportingPLMNList();
        if (reportingPLMNList != null) {
            int plmnCounter = 0;
            String reportingPLMNListArray = "[ ";
            while (reportingPLMNList.getPlmnList().iterator().hasNext()) {
                reportingPLMNListArray = reportingPLMNListArray + reportingPLMNList.getPlmnList().get(plmnCounter);
                plmnCounter++;
                if (reportingPLMNList.getPlmnList().get(plmnCounter) != null) {
                    reportingPLMNListArray = reportingPLMNListArray + ", ";
                } else {
                    reportingPLMNListArray = reportingPLMNListArray + " ]";
                }
                cdrModel.setReportingPLMNList(reportingPLMNListArray);
                stringBuilder.append(cdrModel.getReportingPLMNList()).append(SEPARATOR);
            }
        } else {
            stringBuilder.append(SEPARATOR);
        }

        /**
         * 1xRTT-RCID
         */
        String oneXRTTRCID = gmlcCdrState.getOneXRTTRCID();
        if (oneXRTTRCID != null) {
            cdrModel.setOneXRTTRCID(oneXRTTRCID);
            stringBuilder.append(cdrModel.getOneXRTTRCID()); //.append(SEPARATOR); uncomment if further fields apply
        } else {
            //stringBuilder.append(SEPARATOR); //Uncomment if further fields apply
        }

        if (sendCdrToGlass) {
            try {
                taskManager.addTask(new TaskCDR(cdrModel));
            }catch (Exception ex) {
                logger.severe("Error on try to send CDR to GLaaS " + ex.getMessage());
            }
        }

        return stringBuilder.toString();
    }

    private String getRatType(Integer ratTypeCode) {
        String ratType = null;
        if (ratTypeCode != null) {
            switch (ratTypeCode) {
                case 0:
                    ratType = "WLAN";
                    break;
                case 1:
                    ratType = "VIRTUAL";
                    break;
                case 1000:
                    ratType = "UTRAN";
                    break;
                case 1001:
                    ratType = "GERAN";
                    break;
                case 1002:
                    ratType = "GAN";
                    break;
                case 1003:
                    ratType = "HSPA_EVOLUTION";
                    break;
                case 1004:
                    ratType = "EUTRAN";
                    break;
                case 1005:
                    ratType = "EUTRAN-NB-IoT";
                    break;
                case 1006:
                    ratType = "NR";
                    break;
                case 1007:
                    ratType = "LTE-M";
                    break;
                case 2000:
                    ratType = "CDMA2000_1X";
                    break;
                case 2001:
                    ratType = "HRPD";
                    break;
                case 2002:
                    ratType = "UMB";
                    break;
                case 2003:
                    ratType = "EHRPD";
                    break;
                default:
                    ratType = null;
                    break;
            }
        }
        return ratType;
    }

    public String bytesToHexString(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
