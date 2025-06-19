package org.mobicents.gmlc.slee;

import com.objsys.asn1j.runtime.Asn1BitString;
import com.objsys.asn1j.runtime.Asn1Boolean;
import com.objsys.asn1j.runtime.Asn1Integer;
import com.paicbd.slee.resource.ulp.ULPMessageFactory;
import com.paicbd.slee.resource.ulp.ULPSessionActivity;
import com.paicbd.slee.resource.ulp.UlpActivityContextInterfaceFactory;
import com.paicbd.slee.resource.ulp.UlpResourceAdaptorSbbInterface;
import net.java.slee.resource.diameter.base.DiameterActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.base.DiameterAvpFactory;
import net.java.slee.resource.diameter.base.DiameterMessageFactory;
import net.java.slee.resource.diameter.base.DiameterProvider;
import net.java.slee.resource.diameter.base.events.ErrorAnswer;
import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.AddressType;
import net.java.slee.resource.diameter.base.events.avp.AuthSessionStateType;
import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.sh.DiameterShAvpFactory;
import net.java.slee.resource.diameter.sh.client.ShClientActivity;
import net.java.slee.resource.diameter.sh.client.ShClientActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.sh.client.ShClientMessageFactory;
import net.java.slee.resource.diameter.sh.client.ShClientProvider;
import net.java.slee.resource.diameter.sh.events.UserDataAnswer;
import net.java.slee.resource.diameter.sh.events.UserDataRequest;
import net.java.slee.resource.diameter.sh.events.avp.CurrentLocationType;
import net.java.slee.resource.diameter.sh.events.avp.DataReferenceType;
import net.java.slee.resource.diameter.sh.events.avp.DiameterShAvpCodes;
import net.java.slee.resource.diameter.sh.events.avp.RequestedDomainType;
import net.java.slee.resource.diameter.sh.events.avp.UserIdentityAvp;
import net.java.slee.resource.diameter.sh.server.ShServerActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.sh.server.ShServerMessageFactory;
import net.java.slee.resource.diameter.sh.server.ShServerProvider;
import net.java.slee.resource.diameter.slg.SLgAVPFactory;
import net.java.slee.resource.diameter.slg.SLgActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.slg.SLgClientSessionActivity;
import net.java.slee.resource.diameter.slg.SLgMessageFactory;
import net.java.slee.resource.diameter.slg.SLgProvider;
import net.java.slee.resource.diameter.slg.events.LocationReportAnswer;
import net.java.slee.resource.diameter.slg.events.LocationReportRequest;
import net.java.slee.resource.diameter.slg.events.ProvideLocationAnswer;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;
import net.java.slee.resource.diameter.slg.events.avp.AdditionalAreaAvp;
import net.java.slee.resource.diameter.slg.events.avp.AreaAvp;
import net.java.slee.resource.diameter.slg.events.avp.AreaDefinitionAvp;
import net.java.slee.resource.diameter.slg.events.avp.AreaEventInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.DeferredMTLRDataAvp;
import net.java.slee.resource.diameter.slg.events.avp.DelayedLocationReportingDataAvp;
import net.java.slee.resource.diameter.slg.events.avp.ELPAVPCodes;
import net.java.slee.resource.diameter.slg.events.avp.ESMLCCellInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.GERANPositioningInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.LCSClientType;
import net.java.slee.resource.diameter.slg.events.avp.LCSEPSClientNameAvp;
import net.java.slee.resource.diameter.slg.events.avp.LCSFormatIndicator;
import net.java.slee.resource.diameter.slg.events.avp.LCSPrivacyCheckNonSessionAvp;
import net.java.slee.resource.diameter.slg.events.avp.LCSPrivacyCheckSessionAvp;
import net.java.slee.resource.diameter.slg.events.avp.LCSQoSAvp;
import net.java.slee.resource.diameter.slg.events.avp.LCSQoSClass;
import net.java.slee.resource.diameter.slg.events.avp.LCSRequestorNameAvp;
import net.java.slee.resource.diameter.slg.events.avp.LocationEvent;
import net.java.slee.resource.diameter.slg.events.avp.MotionEventInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.PLMNIDListAvp;
import net.java.slee.resource.diameter.slg.events.avp.PeriodicLDRInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.PeriodicLocationSupportIndicator;
import net.java.slee.resource.diameter.slg.events.avp.PrioritizedListIndicator;
import net.java.slee.resource.diameter.slg.events.avp.PseudonymIndicator;
import net.java.slee.resource.diameter.slg.events.avp.ReportingPLMNListAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgLocationType;
import net.java.slee.resource.diameter.slg.events.avp.UTRANPositioningInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.VelocityRequested;
import net.java.slee.resource.diameter.slg.events.avp.VerticalRequested;
import net.java.slee.resource.diameter.slh.SLhAVPFactory;
import net.java.slee.resource.diameter.slh.SLhActivityContextInterfaceFactory;
import net.java.slee.resource.diameter.slh.SLhClientSessionActivity;
import net.java.slee.resource.diameter.slh.SLhMessageFactory;
import net.java.slee.resource.diameter.slh.SLhProvider;
import net.java.slee.resource.diameter.slh.events.LCSRoutingInfoAnswer;
import net.java.slee.resource.diameter.slh.events.LCSRoutingInfoRequest;
import net.java.slee.resource.diameter.slh.events.avp.AdditionalServingNodeAvp;
import net.java.slee.resource.diameter.slh.events.avp.ServingNodeAvp;
import net.java.slee.resource.http.events.HttpServletRequestEvent;
import org.joda.time.DateTime;
import org.mobicents.gmlc.GmlcPropertiesManagement;
import org.mobicents.gmlc.slee.cdr.CDRCreationHelper;
import org.mobicents.gmlc.slee.cdr.CDRInterface;
import org.mobicents.gmlc.slee.cdr.CDRInterfaceParent;
import org.mobicents.gmlc.slee.cdr.GMLCCDRState;
import org.mobicents.gmlc.slee.cdr.RecordStatus;
import org.mobicents.gmlc.slee.concurrent.MlpResponseExecutor;
import org.mobicents.gmlc.slee.concurrent.SuplSessionStatus;
import org.mobicents.gmlc.slee.concurrent.SuplTransaction;
import org.mobicents.gmlc.slee.concurrent.Transaction;
import org.mobicents.gmlc.slee.diameter.AVPHandler;
import org.mobicents.gmlc.slee.diameter.DiameterBaseError;
import org.mobicents.gmlc.slee.diameter.DiameterLcsResponseHelperForMLP;
import org.mobicents.gmlc.slee.diameter.DiameterShUdrResponseHelperForMLP;
import org.mobicents.gmlc.slee.diameter.sh.CSLocationInformation;
import org.mobicents.gmlc.slee.diameter.sh.CSLocationInformationExtension;
import org.mobicents.gmlc.slee.diameter.sh.CSLocationInformationExtension2;
import org.mobicents.gmlc.slee.diameter.sh.CSLocationInformationExtension3;
import org.mobicents.gmlc.slee.diameter.sh.EPSLocationInformation;
import org.mobicents.gmlc.slee.diameter.sh.EPSLocationInformationExtension;
import org.mobicents.gmlc.slee.diameter.sh.EPSLocationInformationExtension2;
import org.mobicents.gmlc.slee.diameter.sh.Extension;
import org.mobicents.gmlc.slee.diameter.sh.LocalTimeZone;
import org.mobicents.gmlc.slee.diameter.sh.PSLocationInformation;
import org.mobicents.gmlc.slee.diameter.sh.PSLocationInformationExtension;
import org.mobicents.gmlc.slee.diameter.sh.PSLocationInformationExtension2;
import org.mobicents.gmlc.slee.diameter.sh.PSLocationInformationExtension3;
import org.mobicents.gmlc.slee.diameter.sh.PublicIds;
import org.mobicents.gmlc.slee.diameter.sh.Sh5GSLocationInformation;
import org.mobicents.gmlc.slee.diameter.sh.ShDataReader;
import org.mobicents.gmlc.slee.diameter.sh.ShUdaAvpValues;
import org.mobicents.gmlc.slee.diameter.sh.UserCSGInformation;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShCellGlobalId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShEUTRANCellGlobalId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShGeodeticInformation;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShGeographicalInformation;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShLocationAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShLocationNumber;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShNRCellGlobalId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShRoutingAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShServiceAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShTrackingAreaId;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShUserCSGInformation;
import org.mobicents.gmlc.slee.diameter.sh.elements.ShVisitedPLMNId;
import org.mobicents.gmlc.slee.diameter.slg.SLgLrrAvpValues;
import org.mobicents.gmlc.slee.diameter.slg.SLgPlaAvpValues;
import org.mobicents.gmlc.slee.diameter.slh.SLhException;
import org.mobicents.gmlc.slee.diameter.slh.SLhRiaAvpValues;
import org.mobicents.gmlc.slee.diameter.slh.SLhRirAvpValues;
import org.mobicents.gmlc.slee.http.AtiResponseJsonBuilder;
import org.mobicents.gmlc.slee.http.HttpReport;
import org.mobicents.gmlc.slee.http.MongoGmlc;
import org.mobicents.gmlc.slee.http.OnErrorResponseJsonBuilder;
import org.mobicents.gmlc.slee.http.PlrResponseJsonBuilder;
import org.mobicents.gmlc.slee.http.PsiResponseJsonBuilder;
import org.mobicents.gmlc.slee.http.PslResponseJsonBuilder;
import org.mobicents.gmlc.slee.http.SriResponseJsonBuilder;
import org.mobicents.gmlc.slee.http.UdrResponseJsonBuiler;
import org.mobicents.gmlc.slee.map.AtiResponseValues;
import org.mobicents.gmlc.slee.map.MapAtiResponseHelperForMLP;
import org.mobicents.gmlc.slee.map.MapLsmResponseHelperForMLP;
import org.mobicents.gmlc.slee.map.MapSriPsiResponseHelperForMLP;
import org.mobicents.gmlc.slee.map.PsiResponseValues;
import org.mobicents.gmlc.slee.map.PslResponseValues;
import org.mobicents.gmlc.slee.map.SlrRequestValues;
import org.mobicents.gmlc.slee.map.SriForLcsResponseValues;
import org.mobicents.gmlc.slee.map.SriForSmResponseValues;
import org.mobicents.gmlc.slee.map.SriResponseValues;
import org.mobicents.gmlc.slee.mlp.MLPException;
import org.mobicents.gmlc.slee.mlp.MLPLocationRequest;
import org.mobicents.gmlc.slee.mlp.MLPRequest;
import org.mobicents.gmlc.slee.mlp.MLPResponse;
import org.mobicents.gmlc.slee.primitives.LocationInformation5GS;
import org.mobicents.gmlc.slee.supl.NetworkInitiatedSuplLocation;
import org.mobicents.gmlc.slee.supl.SUPL_INIT.SLPMode;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.AreaEventParams;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.AreaEventParams_areaIdLists;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.AreaEventType;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.AreaId;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.AreaIdList;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.AreaIdSet;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.GeographicTargetArea;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.GeographicTargetAreaList;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.LTEAreaId;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.PeriodicParams;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.RepeatedReportingParams;
import org.mobicents.gmlc.slee.supl.SUPL_TRIGGERED_START.TriggerParams;
import org.mobicents.gmlc.slee.supl.SuplGeoTargetArea;
import org.mobicents.gmlc.slee.supl.SuplResponseHelperForMLP;
import org.mobicents.gmlc.slee.supl.SuplTriggerType;
import org.mobicents.gmlc.slee.supl.ULP_Components.PosMethod;
import org.mobicents.gmlc.slee.supl.ULP_Components.QoP;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.CircularArea;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.Coordinate;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.Coordinate_latitudeSign;
import org.mobicents.gmlc.slee.supl.Ver2_ULP_Components.EllipticalArea;
import org.mobicents.gmlc.slee.utils.GADShapesUtils;
import org.mobicents.slee.ChildRelationExt;
import org.mobicents.slee.SbbContextExt;
import org.mobicents.slee.resource.diameter.sh.events.avp.UserIdentityAvpImpl;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.dialog.MAPRefuseReason;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ExtendedRoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.InterrogationType;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPDialogCallHandling;
import org.restcomm.protocols.ss7.map.api.service.callhandling.SendRoutingInformationRequest;
import org.restcomm.protocols.ss7.map.api.service.callhandling.SendRoutingInformationResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.Area;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaDefinition;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaEventInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaIdentification;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaType;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredLocationEventType;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientExternalID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientInternalID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientName;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPriority;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPrivacyCheck;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSQoS;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSRequestorID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationEstimateType;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationType;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.OccurrenceInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.RANTechnology;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMN;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;
import org.restcomm.protocols.ss7.map.api.service.lsm.ResponseTime;
import org.restcomm.protocols.ss7.map.api.service.lsm.ResponseTimeCategory;
import org.restcomm.protocols.ss7.map.api.service.lsm.SLRArgExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.SupportedGADShapes;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.DomainType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationEPS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.APN;
import org.restcomm.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPDialogSms;
import org.restcomm.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMResponse;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.PlmnIdImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;
import org.restcomm.protocols.ss7.map.service.lsm.AreaDefinitionImpl;
import org.restcomm.protocols.ss7.map.service.lsm.AreaEventInfoImpl;
import org.restcomm.protocols.ss7.map.service.lsm.AreaIdentificationImpl;
import org.restcomm.protocols.ss7.map.service.lsm.AreaImpl;
import org.restcomm.protocols.ss7.map.service.lsm.DeferredLocationEventTypeImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSClientExternalIDImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSClientIDImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSClientNameImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSCodewordImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSQoSImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSRequestorIDImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LocationTypeImpl;
import org.restcomm.protocols.ss7.map.service.lsm.PeriodicLDRInfoImpl;
import org.restcomm.protocols.ss7.map.service.lsm.ReportingPLMNImpl;
import org.restcomm.protocols.ss7.map.service.lsm.ReportingPLMNListImpl;
import org.restcomm.protocols.ss7.map.service.lsm.ResponseTimeImpl;
import org.restcomm.protocols.ss7.map.service.lsm.SupportedGADShapesImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RequestedInfoImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.sccp.parameter.EncodingScheme;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.MessageType;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.comp.InvokeProblemType;
import org.restcomm.slee.resource.map.MAPContextInterfaceFactory;
import org.restcomm.slee.resource.map.events.DialogAccept;
import org.restcomm.slee.resource.map.events.DialogClose;
import org.restcomm.slee.resource.map.events.DialogDelimiter;
import org.restcomm.slee.resource.map.events.DialogNotice;
import org.restcomm.slee.resource.map.events.DialogProviderAbort;
import org.restcomm.slee.resource.map.events.DialogReject;
import org.restcomm.slee.resource.map.events.DialogRelease;
import org.restcomm.slee.resource.map.events.DialogTimeout;
import org.restcomm.slee.resource.map.events.DialogUserAbort;
import org.restcomm.slee.resource.map.events.ErrorComponent;
import org.restcomm.slee.resource.map.events.InvokeTimeout;
import org.restcomm.slee.resource.map.events.RejectComponent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.slee.ActivityContextInterface;
import javax.slee.CreateException;
import javax.slee.EventContext;
import javax.slee.RolledBackContext;
import javax.slee.SLEEException;
import javax.slee.Sbb;
import javax.slee.SbbContext;
import javax.slee.SbbLocalObject;
import javax.slee.TransactionRequiredLocalException;
import javax.slee.facilities.TimerEvent;
import javax.slee.facilities.TimerFacility;
import javax.slee.facilities.TimerID;
import javax.slee.facilities.TimerOptions;
import javax.slee.facilities.TimerPreserveMissed;
import javax.slee.facilities.Tracer;
import javax.slee.resource.ResourceAdaptorTypeID;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.mobicents.gmlc.slee.diameter.slg.SLgAreaEventInfoHelper.convertAreaTypeToString;
import static org.mobicents.gmlc.slee.diameter.slg.SLgAreaEventInfoHelper.setAreaIdtoTbcd;
import static org.mobicents.gmlc.slee.http.HttpResponseType.setHttpServletResponseStatusCode;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;
import static org.mobicents.gmlc.slee.utils.TBCDUtil.parseTBCD;
import static org.mobicents.gmlc.slee.utils.TBCDUtil.setAreaIdParams;
import static org.mobicents.gmlc.slee.utils.TBCDUtil.toTBCDString;
import static org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientInternalID.getLCSClientInternalID;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public abstract class MobileCoreNetworkInterfaceSbb extends GMLCBaseSbb implements Sbb, CDRInterfaceParent {

    protected SbbContextExt sbbContext;

    protected Tracer logger;

    private static final GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();

    private static final SuplTransaction suplTransaction = SuplTransaction.Instance();

    protected MAPContextInterfaceFactory mapAcif;
    protected MAPProvider mapProvider;
    protected MAPParameterFactory mapParameterFactory;
    protected ParameterFactory sccpParameterFact;

    private static final int MAP_OPERATION_TIMEOUT = 60000; // milliseconds
    protected static final ResourceAdaptorTypeID mapRATypeID = new ResourceAdaptorTypeID("MAPResourceAdaptorType", "org.restcomm", "2.0");
    protected static final String mapRaLink = "MAPRA";

    private SccpAddress gmlcSCCPAddress = null;
    private MAPApplicationContext anyTimeEnquiryContext = null;
    private MAPApplicationContext locationSvcEnquiryContext = null;
    private MAPApplicationContext locationSvcGatewayContext = null;
    private MAPApplicationContext shortMsgGatewayContext = null;
    private MAPApplicationContext subscriberInfoEnquiryContext = null;
    private MAPApplicationContext locationInfoRetrievalContext = null;

    private static final int DIAMETER_COMMAND_TIMEOUT = 60000; // milliseconds
    public static final int SLh_VENDOR_ID = 10415;
    public static final int SLg_VENDOR_ID = 10415;
    public static final int SLh_APP_ID = 16777291;
    public static final int SLg_APP_ID = 16777255;

    protected SLhProvider slhProvider;
    protected SLhMessageFactory slhMessageFactory;
    protected SLhAVPFactory slhAVPFactory;
    protected SLhActivityContextInterfaceFactory slhAcif;

    protected SLgProvider slgProvider;
    protected SLgMessageFactory slgMessageFactory;
    protected SLgAVPFactory slgAVPFactory;
    protected SLgActivityContextInterfaceFactory slgAcif;

    protected static final ResourceAdaptorTypeID diameterSLhRATypeID = new ResourceAdaptorTypeID("Diameter SLh", "java.net", "0.8.1");
    protected static final String slhRaLink = "DiameterSLh";

    protected static final ResourceAdaptorTypeID diameterSLgRATypeID = new ResourceAdaptorTypeID("Diameter SLg", "java.net", "0.8.1");
    protected static final String slgRaLink = "DiameterSLg";

    private static final int Sh_VENDOR_ID = 10415;
    private static final int Sh_APP_ID = 16777217;

    protected ShClientProvider shClientProvider;
    protected ShServerProvider shServerProvider;
    protected ShClientMessageFactory shClientMessageFactory;
    protected ShServerMessageFactory shServerMessageFactory;
    protected DiameterShAvpFactory shAvpFactory;
    protected ShClientActivityContextInterfaceFactory shClientAcif;
    protected ShServerActivityContextInterfaceFactory shServerAcif;

    protected static final ResourceAdaptorTypeID diameterShClientRATypeID = new ResourceAdaptorTypeID("Diameter Sh-Client", "java.net", "0.8.1");
    protected static final String shClientRaLink = "DiameterShClient";

    protected static final ResourceAdaptorTypeID diameterShServerRATypeID = new ResourceAdaptorTypeID("Diameter Sh-Server", "java.net", "0.8.1");
    protected static final String shServerRaLink = "DiameterShServer";

    protected DiameterProvider diameterBaseProvider;
    protected DiameterMessageFactory diameterBaseMessageFactory;
    protected DiameterAvpFactory diameterBaseAvpFactory;
    protected DiameterActivityContextInterfaceFactory diameterBaseAcif;

    protected static final ResourceAdaptorTypeID diameterBaseRATypeID = new ResourceAdaptorTypeID("Diameter Base", "java.net", "0.8.1");
    protected static final String diameterBaseRaLink = "DiameterBaseResourceAdaptor";

    protected UlpResourceAdaptorSbbInterface ulpProvider;
    protected ULPMessageFactory ulpMessageFactory;
    protected ULPSessionActivity ulpSessionActivity;
    protected UlpActivityContextInterfaceFactory ulpActContextIFactory;

    protected static final ResourceAdaptorTypeID suplRATypeID = new ResourceAdaptorTypeID("ULPResourceAdaptorType", "com.paicbd", "1.0");
    protected static final String suplRaLink = "ULPRA";

    private TimerFacility timerFacility = null;
    private static final TimerOptions defaultTimerOptions = createDefaultTimerOptions();
    private NetworkInitiatedSuplLocation networkInitiatedSuplLocation;

    //time in millisecond
    private static final int SUPL_SESSION_TIMEOUT = 15000;

    //time in millisecond
    private static final int SUPL_SESSION_SCHEDULE = 500;

    private static TimerOptions createDefaultTimerOptions() {
        TimerOptions timerOptions = new TimerOptions();
        timerOptions.setTimeout(gmlcPropertiesManagement.getEventContextSuspendDeliveryTimeout());
        timerOptions.setPreserveMissed(TimerPreserveMissed.ALL);
        return timerOptions;
    }

    private Transaction mobileCoreNetworkTransactions = Transaction.Instance();

    private HttpReport httpSubscriberLocationReport;
    private MongoGmlc mongoGmlc;
    public static final Map<Integer, LocationRequestParams> listLocationRequestParams = new HashMap<>();
    // private SuplEventProcessing suplEventProcessing;

    /**
     * Creates a new instance of MobileCoreNetworkInterfaceSbb
     */
    public MobileCoreNetworkInterfaceSbb() throws UnknownHostException {
        super("MobileCoreNetworkInterfaceSbb");
    }


    /**
     * For debugging - fake location data
     */
    private String fakeNumber = "19395550113";
    private MLPResponse.MLPResultType fakeLocationType = MLPResponse.MLPResultType.OK;
    private String fakeLocationAdditionalInfoErrorString = "Internal positioning failure occurred";
    private int fakeCellId = 300;
    private String fakeLocationX = "27 28 25.00S";
    private String fakeLocationY = "153 01 43.00E";
    private String fakeLocationRadius = "5000";


    ////////////////////
    // Sbb callbacks //
    //////////////////

    public void setSbbContext(SbbContext sbbContext) {

        this.sbbContext = (SbbContextExt) sbbContext;
        this.logger = sbbContext.getTracer(MobileCoreNetworkInterfaceSbb.class.getSimpleName());

        try {
            /*
             * SS7 MAP
             */
            this.mapAcif = (MAPContextInterfaceFactory) this.sbbContext.getActivityContextInterfaceFactory(mapRATypeID);
            this.mapProvider = (MAPProvider) this.sbbContext.getResourceAdaptorInterface(mapRATypeID, mapRaLink);
            this.mapParameterFactory = this.mapProvider.getMAPParameterFactory();
            this.sccpParameterFact = new ParameterFactoryImpl();

            /*
             * Diameter
             */
            Context diameterCtx = (Context) new InitialContext().lookup("java:comp/env");

            // Diameter SLh
            try {
                this.slhProvider = (SLhProvider) this.sbbContext.getResourceAdaptorInterface(diameterSLhRATypeID, slhRaLink);
                this.slhMessageFactory = slhProvider.getSLhMessageFactory();
                this.slhAVPFactory = slhProvider.getSLhAvpFactory();
                this.slhAcif = (SLhActivityContextInterfaceFactory) diameterCtx.lookup("slee/resources/JDiameterSLhResourceAdaptor/java.net/0.8.1/acif");
            } catch (Exception e) {
                logger.severe("Unable to set SBB context parameters for SLh", e);
            }

            // Diameter SLg
            try {
                this.slgProvider = (SLgProvider) this.sbbContext.getResourceAdaptorInterface(diameterSLgRATypeID, slgRaLink);
                this.slgMessageFactory = slgProvider.getSLgMessageFactory();
                this.slgAVPFactory = slgProvider.getSLgAVPFactory();
                this.slgAcif = (SLgActivityContextInterfaceFactory) diameterCtx.lookup("slee/resources/JDiameterSLgResourceAdaptor/java.net/0.8.1/acif");
            } catch (Exception e) {
                logger.severe("Unable to set SBB context parameters for SLg", e);
            }

            // Diameter Sh Client
            try {
                this.shClientProvider = (ShClientProvider) this.sbbContext.getResourceAdaptorInterface(diameterShClientRATypeID, shClientRaLink);
                this.shClientMessageFactory = shClientProvider.getClientMessageFactory();
                this.shAvpFactory = shClientProvider.getClientAvpFactory();
                this.shClientAcif = (ShClientActivityContextInterfaceFactory) diameterCtx.lookup("slee/resources/JDiameterShClientResourceAdaptor/java.net/0.8.1/acif");
            } catch (Exception e) {
                logger.severe("Unable to set SBB context parameters for Sh", e);
            }

            // Diameter Sh Server
            try {
                this.shServerProvider = (ShServerProvider) this.sbbContext.getResourceAdaptorInterface(diameterShServerRATypeID, shServerRaLink);
                this.shServerMessageFactory = shServerProvider.getServerMessageFactory();
                this.shAvpFactory = shServerProvider.getServerAvpFactory();
                this.shServerAcif = (ShServerActivityContextInterfaceFactory) diameterCtx.lookup("slee/resources/JDiameterShServerResourceAdaptor/java.net/0.8.1/acif");
            } catch (Exception e) {
                logger.severe("Unable to set SBB context parameters for Sh", e);
            }

            // Diameter Base
            try {
                this.diameterBaseProvider = (DiameterProvider) this.sbbContext.getResourceAdaptorInterface(diameterBaseRATypeID, diameterBaseRaLink);
                this.diameterBaseMessageFactory = diameterBaseProvider.getDiameterMessageFactory();
                this.diameterBaseAvpFactory = diameterBaseProvider.getDiameterAvpFactory();
                this.diameterBaseAcif = (DiameterActivityContextInterfaceFactory) diameterCtx.lookup("slee/resources/DiameterBaseResourceAdaptor/java.net/0.8.1/acif");
            } catch (Exception e) {
                logger.severe("Unable to set SBB context parameters for Diameter Base", e);
            }

            /*
             * OMA ULP for SUPL
             */
            /* try {
                this.ulpActContextIFactory = (UlpActivityContextInterfaceFactory) this.sbbContext.getActivityContextInterfaceFactory(suplRATypeID);
                this.ulpProvider = (UlpResourceAdaptorSbbInterface) this.sbbContext.getResourceAdaptorInterface(suplRATypeID, suplRaLink);
                this.ulpMessageFactory = this.ulpProvider.getULPMessageFactory();
                this.ulpSessionActivity = this.ulpProvider.createActivity();
            } catch (Exception e) {
                logger.severe("Unable to set SBB context parameters for OMA ULP", e);
            }
            logger.info("ULP-Provider: "+this.ulpProvider);
            */

            this.timerFacility = this.sbbContext.getTimerFacility();
            // Initialize SUPL Server
            if (gmlcPropertiesManagement.getSuplEnabled()) {
                networkInitiatedSuplLocation = new NetworkInitiatedSuplLocation();
            }

        } catch (Exception e) {
            logger.severe("Could not set SBB context:", e);
        }

        // this.suplEventProcessing = new SuplEventProcessing(this.logger, this.ulpSessionActivity);
    }

    public void sbbCreate() throws CreateException {
    }

    public void sbbPostCreate() throws CreateException {
    }

    public void sbbActivate() {
    }

    public void sbbPassivate() {
    }

    public void sbbLoad() {
    }

    public void sbbStore() {
    }

    public void sbbRemove() {
    }

    public void sbbExceptionThrown(Exception exception, Object object, ActivityContextInterface activityContextInterface) {
    }

    public void sbbRolledBack(RolledBackContext rolledBackContext) {
    }

    protected SbbContext getSbbContext() {
        return sbbContext;
    }

    private void forwardEvent(SbbLocalObject child, ActivityContextInterface aci) {
        try {
            aci.attach(child);
            aci.detach(sbbContext.getSbbLocalObject());
        } catch (Exception e) {
            logger.severe("Unexpected error: ", e);
        }
    }

    // ///////////////////////
    // MAP Events handlers //
    // /////////////////////

    /**
     * Subscriber Information services
     * MAP_ANY_TIME_INTERROGATION (ATI) Events
     */

    /**
     * MAP ATI Request Event
     */
    public void onAnyTimeInterrogationRequest(AnyTimeInterrogationRequest event, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onAnyTimeInterrogationRequest = " + event);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onAnyTimeInterrogationRequest=%s", event), e);
        }
    }

    /**
     * MAP ATI Response Event
     */
    public void onAnyTimeInterrogationResponse(AnyTimeInterrogationResponse event, ActivityContextInterface aci) {

        MAPErrorMessage mapErrorMessage = this.getErrorResponse();
        String msisdnDigitsForAti = null, nnn = null;
        String atiHttpRespType, curlUser;
        Long transaction;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onAnyTimeInterrogationResponse = " + event);
            }

            MAPDialogMobility mapDialogMobility = event.getMAPDialog();
            SubscriberInfo subscriberInfo = event.getSubscriberInfo();
            AtiResponseValues atiResponseValues = new AtiResponseValues();
            MLPResponse.MLPResultType mlpRespResult = null;
            String mlpClientErrorMessage = null;

            // CDR initialization
            CDRCreationHelper.GmlcCdrStateString gmlcCdrStateString = CDRCreationHelper.mapAtiCdrInitializer(aci, this.getCDRInterface(), event, nnn);
            GMLCCDRState gmlcCdrState = gmlcCdrStateString.getGmlcCdrState();
            nnn = gmlcCdrStateString.getNnn();
            // Set timer last
            this.setTimer(aci);

            // Transaction
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(mapDialogMobility.getLocalDialogId());

            if (transaction != null) {
                TimerID atiTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
                if (atiTimerID != null)
                    this.timerFacility.cancelTimer(atiTimerID);
                msisdnDigitsForAti = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                atiHttpRespType = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiHttpRespType");
                curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
                gmlcCdrState.setDialogStartTime(dialogStartTime);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setCurlUser(curlUser);
                    if (dialogStartTime != null && eventTime != null) {
                        Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                        gmlcCdrState.setDialogDuration(dialogDuration);
                    }
                }
            } else {
                throw new Exception();
            }
            mobileCoreNetworkTransactions.Instance().destroy(transaction);

            if (msisdnDigitsForAti != null) {
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, msisdnDigitsForAti));
                }
            }

            MessageType tcapMessageType = mapDialogMobility.getTCAPMessageType();
            if (tcapMessageType == MessageType.Abort) {
                mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                mlpClientErrorMessage = "TCAP ABORT on ATI";
                if (gmlcCdrState.isInitialized()) {
                    this.createCDRRecord(RecordStatus.ATI_TCAP_DIALOG_ABORT);
                }
                if (mapDialogMobility.getRemoteAddress() != null)
                    if (mapDialogMobility.getRemoteAddress().getGlobalTitle() != null)
                        nnn = mapDialogMobility.getRemoteAddress().getGlobalTitle().getDigits();
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "ATI", msisdnDigitsForAti,
                    null, null, nnn, null, null, null, false);
                return;
            }

            // Inquire if MAP ATI response includes subscriber's info
            if (subscriberInfo != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (gmlcCdrState.isInitialized()) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting Subscriber Info to CDR");
                    }
                    gmlcCdrState.setSubscriberInfo(subscriberInfo);
                }
                // Inquire if CS subscriber state is included in MAP ATI response subscriber's info
                if (subscriberInfo.getSubscriberState() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonAnyTimeInterrogationResponse: received CS Subscriber State");
                    }
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // CS Subscriber state is included in MAP ATI response, get it and store it as a response parameter
                    atiResponseValues.setSubscriberState(subscriberInfo.getSubscriberState());
                }
                // Inquire if PS subscriber state is included in MAP ATI response subscriber's info
                if (subscriberInfo.getPSSubscriberState() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonAnyTimeInterrogationResponse: received PS Subscriber State");
                    }
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // PS subscriber state is included in MAP ATI response, get it and store it as a response parameter
                    atiResponseValues.setPsSubscriberState(subscriberInfo.getPSSubscriberState());
                }
                // Inquire if Location information is included in MAP ATI response subscriber's info
                if (subscriberInfo.getLocationInformation() != null) {
                    // Location information is included in MAP ATI response
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonAnyTimeInterrogationResponse: received location information, " +
                            "decoding CGI or SAI or LAI, Geographical and Geodetic information, MSC and/or VLR Number, EPS location information");
                    }
                    atiResponseValues.setLocationInformation(subscriberInfo.getLocationInformation());
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Inquire if Cell Global Identity (CGI) or Service Area Identity (SAI) or Location Area Identity (LAI) are included in MAP ATI response
                    if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        // CGI or SAI or LAI are included in MAP ATI response
                        CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = subscriberInfo.getLocationInformation()
                            .getCellGlobalIdOrServiceAreaIdOrLAI();
                        atiResponseValues.setCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdOrLAI);
                        // Inquire and get parameters of CGI or SAI or LAI included in MAP ATI response
                        // Case when CGI or SAI length is fixed
                        if (cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: received CellGlobalIdOrServiceAreaIdFixedLength, decoding MCC, MNC, LAC, CI");
                            }
                            try {
                                atiResponseValues.setMcc(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC());
                                atiResponseValues.setMnc(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC());
                                atiResponseValues.setLac(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getLac());
                                atiResponseValues.setCi(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode());
                            } catch (MAPException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
                            // Case when LAI length is fixed
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: received laiFixedLength, decoding MCC, MNC, LAC (no CI)");
                            }
                            atiResponseValues.setMcc(cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMCC());
                            atiResponseValues.setMcc(cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMNC());
                            atiResponseValues.setLac(cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getLac());
                        }
                    }
                    // Inquire if Current Location Retrieved information is included in MAP ATI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getCurrentLocationRetrieved()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: received currentLocationRetrieved");
                        }
                        atiResponseValues.setCurrentLocationRetrieved(true);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, currentLocationRetrieved set to true");
                            }
                            gmlcCdrState.setCurrentLocationRetrieved(true);
                        }
                    } else {
                        atiResponseValues.setCurrentLocationRetrieved(false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, currentLocationRetrieved set to false");
                            }
                            gmlcCdrState.setCurrentLocationRetrieved(false);
                        }
                    }
                    // Inquire if Age of Location Information is included in MAP ATI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getAgeOfLocationInformation() != null) {
                        atiResponseValues.setAgeOfLocationInfo(subscriberInfo.getLocationInformation().getAgeOfLocationInformation().intValue());
                    }
                    // Inquire if VLR number (Global Title) is included in MAP ATI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getVlrNumber() != null) {
                        atiResponseValues.setVlrNumber(subscriberInfo.getLocationInformation().getVlrNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting VLR number value");
                        }
                    }
                    // Inquire if MSC number (Global Title) is included in MAP ATI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getMscNumber() != null) {
                        atiResponseValues.setVlrNumber(subscriberInfo.getLocationInformation().getMscNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting MSC number value");
                        }
                    }
                    // Inquire if SAI is present in MAP ATI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getSaiPresent()) {
                        atiResponseValues.setSaiPresent(subscriberInfo.getLocationInformation().getSaiPresent());
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting SAI present to true");
                            }
                            gmlcCdrState.setSaiPresent(atiResponseValues.isSaiPresent());
                        }
                    } else {
                        atiResponseValues.setSaiPresent(false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting SAI present to false");
                            }
                            gmlcCdrState.setSaiPresent(false);
                        }
                    }
                    // Inquires if subscriber Geographical information is included in MAP ATI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getGeographicalInformation() != null) {
                        // Geographical information is included in MAP ATI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: "
                                + "received Geographical information, decoding latitude, longitude, uncertainty and type of shape");
                        }
                        atiResponseValues.setGeographicalLatitude(subscriberInfo.getLocationInformation().getGeographicalInformation().getLatitude());
                        atiResponseValues.setGeographicalLongitude(subscriberInfo.getLocationInformation().getGeographicalInformation().getLongitude());
                        atiResponseValues.setGeographicalUncertainty(subscriberInfo.getLocationInformation().getGeographicalInformation().getUncertainty());
                        atiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getGeographicalInformation().getTypeOfShape());
                    }
                    // Inquires if subscriber Geodetic information is included in MAP ATI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getGeodeticInformation() != null) {
                        // Geodetic information is included in MAP ATI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: "
                                + "received Geodetic information, decoding latitude, longitude, uncertainty, confidence, type of shape and screening and presentation indicators");
                        }
                        atiResponseValues.setGeodeticLatitude(subscriberInfo.getLocationInformation().getGeodeticInformation().getLatitude());
                        atiResponseValues.setGeodeticLongitude(subscriberInfo.getLocationInformation().getGeodeticInformation().getLongitude());
                        atiResponseValues.setGeodeticUncertainty(subscriberInfo.getLocationInformation().getGeodeticInformation().getUncertainty());
                        atiResponseValues.setGeodeticConfidence(subscriberInfo.getLocationInformation().getGeodeticInformation().getConfidence());
                        atiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getGeodeticInformation().getTypeOfShape());
                        atiResponseValues.setScreeningAndPresentationIndicators(subscriberInfo.getLocationInformation().getGeodeticInformation().getScreeningAndPresentationIndicators());
                    }
                    // Inquire if location information includes EPS location info (LTE)
                    if (subscriberInfo.getLocationInformation().getLocationInformationEPS() != null) {
                        // EPS location information is included in MAP ATI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: received EPS location information, " +
                                "decoding Tracking Area Identity, E-UTRAN CGI, MME Name, AoL, Geographical and Geodetic information");
                        }
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting EPS Location Information to CDR" +
                                    "which includes TAI, E-UTRAN CGI, Geographical and Geodetic information, MME name");
                            }
                            gmlcCdrState.setLocationInformationEPS(atiResponseValues.getLocationInformation().getLocationInformationEPS());
                        }
                        // Inquire if EPS location information includes MME Name
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
                            atiResponseValues.setMmeName(subscriberInfo.getLocationInformation().getLocationInformationEPS().getMmeName());
                        }
                        // Inquire if EPS location information includes Tracking Area Identity
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                            atiResponseValues.setTaId(subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity());
                        }
                        // Inquire if EPS location information includes E-UTRAN Cell Global Identity
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                            atiResponseValues.seteUtranCgi(subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity());
                        }
                        // Inquire if EPS location information includes Age of Location Info
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation() != null) {
                            atiResponseValues.setAgeOfLocationInfo(subscriberInfo.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation());
                        }
                        // Inquire if EPS location information retrieves the current location info
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved() ||
                            !subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved()) {
                            atiResponseValues.setCurrentLocationRetrieved(subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved()
                                ? subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved() : false);
                            if (gmlcCdrState.isInitialized()) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting EPS CurrentLocationRetrieved value to CDR");
                                }
                                gmlcCdrState.setCurrentLocationRetrieved(subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved()
                                    ? subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved() : false);
                            }
                        }
                        // Inquire if EPS location information includes Geographical Information
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
                            atiResponseValues.setGeographicalLatitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude());
                            atiResponseValues.setGeographicalLongitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude());
                            atiResponseValues.setGeographicalUncertainty(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty());
                            atiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape());
                        }
                        // Inquire if EPS location information includes Geodetic Information
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                            // Geographical information is included in MAP ATI response
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: received EPS Geodetic information, " +
                                    "decoding latitude, longitude, uncertainty, confidence, type of shape and screening and presentation indicators");
                            }
                            atiResponseValues.setGeographicalLatitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude());
                            atiResponseValues.setGeographicalLongitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude());
                            atiResponseValues.setGeographicalUncertainty(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty());
                            atiResponseValues.setGeodeticConfidence(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getConfidence());
                            atiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape());
                            atiResponseValues.setScreeningAndPresentationIndicators(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getScreeningAndPresentationIndicators());
                        }
                    }
                }

                // Inquire if location information includes GPRS location information
                if (subscriberInfo.getLocationInformationGPRS() != null) {
                    // GPRS location information is included in MAP ATI response
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonAnyTimeInterrogationResponse: received GPRS location information, " +
                            "decoding CGI or SAI or LAI, RAI, LSA, Geographical and Geodetic information, SGSN Number");
                    }
                    atiResponseValues.setLocationInformationGPRS(subscriberInfo.getLocationInformationGPRS());
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting GPRS Location Information to CDR");
                        }
                        gmlcCdrState.setLocationInformationGPRS(atiResponseValues.getLocationInformationGPRS());
                    }
                    // Inquire GPRS location information includes SGSN number (Global Title)
                    if (subscriberInfo.getLocationInformationGPRS().getSGSNNumber() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: SGSN number is included");
                        }
                        atiResponseValues.setSgsnNumber(subscriberInfo.getLocationInformationGPRS().getSGSNNumber());
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting SGSN number to CDR");
                            }
                            gmlcCdrState.setSgsnNumber(subscriberInfo.getLocationInformationGPRS().getSGSNNumber());
                        }
                    }
                    // Inquire if GPRS location information includes Localised Service Area Identity (LSAId)
                    if (subscriberInfo.getLocationInformationGPRS().getLSAIdentity() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: LSA Identity is included");
                        }
                        atiResponseValues.setLsaIdentity(subscriberInfo.getLocationInformationGPRS().getLSAIdentity());
                        if (subscriberInfo.getLocationInformationGPRS().getLSAIdentity().isPlmnSignificantLSA())
                            atiResponseValues.setPLMNSignificantLSA(subscriberInfo.getLocationInformationGPRS().getLSAIdentity().isPlmnSignificantLSA());
                        else
                            atiResponseValues.setPLMNSignificantLSA(false);
                    }
                    // Inquire if GPRS location information includes Routing Area Identity
                    if (subscriberInfo.getLocationInformationGPRS().getRouteingAreaIdentity() != null) {
                        atiResponseValues.setRaIdentity(subscriberInfo.getLocationInformationGPRS().getRouteingAreaIdentity());
                    }
                    // Inquire if GPRS location information includes Age of Location information
                    if (subscriberInfo.getLocationInformationGPRS().getAgeOfLocationInformation() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: GPRS Age of Location information is included");
                        }
                        atiResponseValues.setAgeOfLocationInfo(subscriberInfo.getLocationInformationGPRS().getAgeOfLocationInformation().intValue());
                    }
                    // Inquire if GPRS location information is current in MAP ATI
                    // Get if GPRS location information is current
                    if (subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved() ||
                        !subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved()) {
                        atiResponseValues.setCurrentLocationRetrieved(subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved()
                            ? subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved() : false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting current location retrieved value Name to CDR");
                            }
                            gmlcCdrState.setCurrentLocationRetrieved(atiResponseValues.isCurrentLocationRetrieved() ? atiResponseValues.isCurrentLocationRetrieved() : false);
                        }
                    }
                    // Inquire if GPRS location information includes Cell Global Identity (CGI) or Service Area Identity (SAI) or Location Area Identity (LAI)
                    if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        // GPRS CGI or SAI or LAI are included in MAP ATI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: GPRS CGI or SAI or LAI number is included");
                        }
                        CellGlobalIdOrServiceAreaIdOrLAI gprsCellGlobalIdOrServiceAreaIdOrLAI = subscriberInfo.getLocationInformationGPRS()
                            .getCellGlobalIdOrServiceAreaIdOrLAI();
                        atiResponseValues.setCellGlobalIdOrServiceAreaIdOrLAI(gprsCellGlobalIdOrServiceAreaIdOrLAI);
                        // Inquire and get parameters of CGI or SAI or LAI included in MAP ATI response from GPRS
                        // Case when CGI or SAI length is fixed
                        if (gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: received CellGlobalIdOrServiceAreaIdFixedLength, decoding MCC, MNC, LAC, CI");
                            }
                            try {
                                atiResponseValues.setMcc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC());
                                atiResponseValues.setMnc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC());
                                atiResponseValues.setLac(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getLac());
                                atiResponseValues.setCi(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode());

                            } catch (MAPException e1) {
                                e1.printStackTrace();
                            }
                        } else if (gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
                            // Case when LAI length is fixed
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: received laiFixedLength, decoding MCC, MNC, LAC (no CI)");
                            }
                            atiResponseValues.setMcc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMCC());
                            atiResponseValues.setMcc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMNC());
                            atiResponseValues.setLac(gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getLac());
                        }
                    }
                    // Get if GPRS location information is current
                    if (subscriberInfo.getLocationInformationGPRS().isSaiPresent() ||
                        !subscriberInfo.getLocationInformationGPRS().isSaiPresent()) {
                        atiResponseValues.setSaiPresent(subscriberInfo.getLocationInformationGPRS().isSaiPresent()
                            ? subscriberInfo.getLocationInformationGPRS().isSaiPresent() : false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting current location retrieved value Name to CDR");
                            }
                            gmlcCdrState.setSaiPresent(atiResponseValues.isSaiPresent() ? atiResponseValues.isSaiPresent() : false);
                        }
                    }
                    // Inquires if GPRS location information includes GPRS Geographical information
                    if (subscriberInfo.getLocationInformationGPRS().getGeographicalInformation() != null) {
                        // Geographical information is included in MAP ATI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: received GPRS Geographical information, " +
                                "decoding latitude, longitude, uncertainty and type of shape");
                        }
                        atiResponseValues.setGeographicalLatitude(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getLatitude());
                        atiResponseValues.setGeographicalLongitude(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getLongitude());
                        atiResponseValues.setGeographicalUncertainty(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getUncertainty());
                        atiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape());
                    }
                    // Inquires if GPRS location information includes Geodetic information
                    if (subscriberInfo.getLocationInformationGPRS().getGeodeticInformation() != null) {
                        // Geographical information is included in MAP ATI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: received GPRS Geodetic information, " +
                                "decoding latitude, longitude, uncertainty, confidence, type of shape and screening and presentation indicators");
                        }
                        atiResponseValues.setGeodeticLatitude(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getLatitude());
                        atiResponseValues.setGeodeticLongitude(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getLongitude());
                        atiResponseValues.setGeodeticUncertainty(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getUncertainty());
                        atiResponseValues.setGeodeticConfidence(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getConfidence());
                        atiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape());
                        atiResponseValues.setScreeningAndPresentationIndicators(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getScreeningAndPresentationIndicators());
                    }
                }

                // Inquire if subscriber's IMEI is included in MAP ATI response
                if (subscriberInfo.getIMEI() != null) {
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's IMEI is included in MAP ATI response, get it and store it as a response parameter
                    atiResponseValues.setImei(subscriberInfo.getIMEI());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting IMEI value to CDR");
                        }
                        gmlcCdrState.setImei(subscriberInfo.getIMEI());
                    }
                }

                // Inquire if subscriber's MS Classmark is included in MAP ATI response
                if (subscriberInfo.getMSClassmark2() != null) {
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's MS Classmark is included in MAP ATI response, get it and store it as a response parameter
                    atiResponseValues.setMsClassmark2(subscriberInfo.getMSClassmark2());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting MS Classmark value to CDR");
                        }
                        gmlcCdrState.setMsClassmark2(atiResponseValues.getMsClassmark2());
                    }
                }

                // Inquire if subscriber's GPRS MS Class is included in MAP ATI response
                if (subscriberInfo.getGPRSMSClass() != null) {
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's GPRS MS Class is included in MAP ATI response, get it and store it as a response parameter
                    atiResponseValues.setGprsmsClass(subscriberInfo.getGPRSMSClass());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting GPRS MS Class value to CDR");
                        }
                        gmlcCdrState.setGprsmsClass(atiResponseValues.getGprsmsClass());
                    }
                }

                // Inquire if MNP Information Result is included in MAP ATI response
                if (subscriberInfo.getMNPInfoRes() != null) {
                    // MNP info result is included in MAP ATI response
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's MNP Info Result is included in MAP ATI response, get it and store it as a response parameter
                    atiResponseValues.setMnpInfoRes(subscriberInfo.getMNPInfoRes());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonAnyTimeInterrogationResponse: received MNP info result, " +
                            "decoding number portability status, MSISDN, IMSI, Routeing number");
                    }
                    if (subscriberInfo.getMNPInfoRes().getNumberPortabilityStatus() != null)
                        atiResponseValues.setNumberPortabilityStatus(subscriberInfo.getMNPInfoRes().getNumberPortabilityStatus().getType());
                    if (subscriberInfo.getMNPInfoRes().getMSISDN() != null)
                        atiResponseValues.setMsisdnAddress(subscriberInfo.getMNPInfoRes().getMSISDN().getAddress());
                    if (subscriberInfo.getMNPInfoRes().getIMSI() != null)
                        atiResponseValues.setImsiData(subscriberInfo.getMNPInfoRes().getIMSI().getData());
                    if (subscriberInfo.getMNPInfoRes().getRouteingNumber() != null)
                        atiResponseValues.setRouteingNumber(subscriberInfo.getMNPInfoRes().getRouteingNumber());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting MNP Info Result to CDR");
                        }
                        gmlcCdrState.setMnpInfoRes(subscriberInfo.getMNPInfoRes());
                    }
                }

                if (gmlcCdrState.isInitialized()) {
                    if (subscriberInfo != null) {
                        if (subscriberInfo.getLocationInformation() != null) {
                            if (subscriberInfo.getLocationInformation().getGeographicalInformation() != null ||
                                subscriberInfo.getLocationInformation().getGeodeticInformation() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_GEO_SUCCESS");
                                }
                                if (gmlcCdrState.isInitialized()) {
                                    this.createCDRRecord(RecordStatus.ATI_GEO_SUCCESS);
                                }
                            } else if (subscriberInfo.getLocationInformation().getLocationInformationEPS() != null) {
                                if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null
                                    || subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonAnyTimeInterrogationResponse: EPSLocationInformation is not null, ATI_GEO_SUCCESS");
                                    }
                                    if (gmlcCdrState.isInitialized()) {
                                        this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_GEO_SUCCESS");
                                        this.createCDRRecord(RecordStatus.ATI_GEO_SUCCESS);
                                    }
                                } else if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                                    if (gmlcCdrState.isInitialized()) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_ECGI_STATE_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.ATI_ECGI_STATE_SUCCESS);
                                    }
                                } else if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                                    if (gmlcCdrState.isInitialized()) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_TAI_STATE_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.ATI_TAI_SUCCESS);
                                    }
                                }
                            } else if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                                if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                    if (subscriberInfo.getSubscriberState() != null) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_CGI_STATE_SUCCESS");
                                        }
                                        if (subscriberInfo.getLocationInformation().getSaiPresent())
                                            this.createCDRRecord(RecordStatus.ATI_SAI_STATE_SUCCESS);
                                        else
                                            this.createCDRRecord(RecordStatus.ATI_CGI_STATE_SUCCESS);
                                    } else {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_CGI_SUCCESS");
                                        }
                                        if (subscriberInfo.getLocationInformation().getSaiPresent())
                                            this.createCDRRecord(RecordStatus.ATI_CGI_SUCCESS);
                                        else
                                            this.createCDRRecord(RecordStatus.ATI_CGI_SUCCESS);
                                    }
                                } else if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                    if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                                        if (subscriberInfo.getSubscriberState() != null) {
                                            if (this.logger.isFineEnabled()) {
                                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_LAI_STATE_SUCCESS");
                                            }
                                            this.createCDRRecord(RecordStatus.ATI_LAI_STATE_SUCCESS);
                                        } else {
                                            if (this.logger.isFineEnabled()) {
                                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_LAI_SUCCESS");
                                            }
                                            this.createCDRRecord(RecordStatus.ATI_LAI_SUCCESS);
                                        }
                                    }
                                } else if (subscriberInfo.getLocationInformation().getVlrNumber() != null) {
                                    if (gmlcCdrState.getVlrAddress() != null) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_NNN_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.ATI_NNN_SUCCESS);
                                    }
                                } else if (subscriberInfo.getLocationInformation().getMscNumber() != null) {
                                    if (gmlcCdrState.getMscNumber() != null) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_NNN_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.ATI_NNN_SUCCESS);
                                    }
                                }
                            }
                        } else if (subscriberInfo.getLocationInformationGPRS() != null) {
                            if (subscriberInfo.getLocationInformationGPRS().getGeographicalInformation() != null ||
                                subscriberInfo.getLocationInformationGPRS().getGeodeticInformation() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_GEO_SUCCESS");
                                }
                                this.createCDRRecord(RecordStatus.ATI_PS_GEO_SUCCESS);
                            } else if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                                if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                    if (subscriberInfo.getPSSubscriberState() != null) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_CGI_STATE_SUCCESS");
                                        }
                                        if (subscriberInfo.getLocationInformationGPRS().isSaiPresent())
                                            this.createCDRRecord(RecordStatus.ATI_PS_SAI_STATE_SUCCESS);
                                        else
                                            this.createCDRRecord(RecordStatus.ATI_PS_CGI_STATE_SUCCESS);
                                    } else {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_CGI_SUCCESS");
                                        }
                                        if (subscriberInfo.getLocationInformationGPRS().isSaiPresent())
                                            this.createCDRRecord(RecordStatus.ATI_PS_SAI_SUCCESS);
                                        else
                                            this.createCDRRecord(RecordStatus.ATI_PS_CGI_SUCCESS);
                                    }
                                } else if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                    if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                                        if (subscriberInfo.getPSSubscriberState() != null) {
                                            if (this.logger.isFineEnabled()) {
                                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_LAI_STATE_SUCCESS");
                                            }
                                            this.createCDRRecord(RecordStatus.ATI_PS_LAI_STATE_SUCCESS);
                                        } else {
                                            if (this.logger.isFineEnabled()) {
                                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_LAI_SUCCESS");
                                            }
                                            this.createCDRRecord(RecordStatus.ATI_PS_LAI_SUCCESS);
                                        }
                                    }
                                } else if (subscriberInfo.getLocationInformationGPRS().getRouteingAreaIdentity() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_RAI_SUCCESS");
                                    }
                                    this.createCDRRecord(RecordStatus.ATI_PS_RAI_SUCCESS);
                                } else if (subscriberInfo.getLocationInformationGPRS().getLSAIdentity() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_LSA_SUCCESS");
                                    }
                                    this.createCDRRecord(RecordStatus.ATI_PS_LSA_SUCCESS);
                                } else if (subscriberInfo.getLocationInformationGPRS().getSGSNNumber() != null) {
                                    if (gmlcCdrState.getSgsnNumber() != null) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_PS_NNN_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.ATI_PS_NNN_SUCCESS);
                                    }
                                }
                            }
                        } else if (subscriberInfo.getIMEI() != null) {
                            if (gmlcCdrState.getImei() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_IMEI_SUCCESS");
                                }
                                this.createCDRRecord(RecordStatus.ATI_IMEI_SUCCESS);
                            }
                        } else if (subscriberInfo.getMNPInfoRes() != null) {
                            if (gmlcCdrState.getMnpInfoRes() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_MNP_INFO_SUCCESS");
                                }
                                this.createCDRRecord(RecordStatus.ATI_MNP_INFO_SUCCESS);
                            }
                        } else if (subscriberInfo.getSubscriberState() != null) {
                            if (subscriberInfo.getSubscriberState().getNotReachableReason() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_STATE_NOT_REACHABLE");
                                }
                                this.createCDRRecord(RecordStatus.ATI_STATE_NOT_REACHABLE);
                            } else {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_STATE_SUCCESS");
                                }
                                this.createCDRRecord(RecordStatus.ATI_STATE_SUCCESS);
                            }
                        } else if (subscriberInfo.getPSSubscriberState() != null) {
                            if (subscriberInfo.getPSSubscriberState().getNetDetNotReachable() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_STATE_NOT_REACHABLE");
                                }
                                this.createCDRRecord(RecordStatus.ATI_STATE_NOT_REACHABLE);
                            } else {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, ATI_STATE_SUCCESS");
                                }
                                this.createCDRRecord(RecordStatus.ATI_STATE_SUCCESS);
                            }
                        }
                    } else {
                        // ATI Error CDR creation
                        if (mapErrorMessage != null) {
                            if (gmlcCdrState.isInitialized()) {
                                mlpClientErrorMessage = handleRecordAndLocationReportOnMapError(mapErrorMessage, mlpRespResult, msisdnDigitsForAti,
                                    null, "ATI", null, nnn, null, gmlcCdrState, false);
                                return;
                            }
                        }
                    }
                }
            }
            // Handle successful retrieval of subscriber's info
            handleAtiLocationResponse(mlpRespResult, atiResponseValues, msisdnDigitsForAti, mlpClientErrorMessage, "SUCCESS", atiHttpRespType);

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process AnyTimeInterrogationResponse=%s", event), e);
            this.createCDRRecord(RecordStatus.ATI_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP ATI response: " + e.getMessage(),
                "ATI", msisdnDigitsForAti, null, null, null, null, null, null, false);
        } finally {
            detachFromMAPDialogMobility(aci);
        }
    }

    /**
     * Subscriber Information Services (another way to get location information via MAP)
     * MAP-SEND-ROUTING-INFO-FOR-SM (SRI) Events
     */
    public void onSendRoutingInfoForSMRequest(SendRoutingInfoForSMRequest event, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSendRoutingInfoForSmRequest = " + event);
            }

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onSendRoutingInfoForSmRequest=%s", event), e);
        }
    }

    public void onSendRoutingInfoForSMResponse(SendRoutingInfoForSMResponse event, ActivityContextInterface aci) {

        MAPErrorMessage mapErrorMessage = this.getErrorResponse();
        String psiMsisdn = null, psiImsi = null, nnn = null, eps, curlUser;
        Long sriForSMTransaction, transaction = null;
        boolean sriSmOnly = false;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSendRoutingInfoForSmResponse = " + event);
            }
            MAPDialog mapDialogSriForSM = event.getMAPDialog();
            IMSI imsi = event.getIMSI();
            LocationInfoWithLMSI locationInfoWithLMSI = event.getLocationInfoWithLMSI();
            MAPExtensionContainer mapExtensionContainer = event.getExtensionContainer();
            if (imsi != null)
                psiImsi = imsi.getData();
            if (locationInfoWithLMSI != null)
                if (locationInfoWithLMSI.getNetworkNodeNumber() != null)
                    nnn = locationInfoWithLMSI.getNetworkNodeNumber().getAddress();

            SriForSmResponseValues sriForSmResponseValues = new SriForSmResponseValues();
            MLPResponse.MLPResultType mlpRespResult = null;
            String mlpClientErrorMessage = null;

            // CDR initialization
            GMLCCDRState gmlcCdrState = CDRCreationHelper.mapSriSmCdrInitializer(aci, this.getCDRInterface(), event);
            // Set timer last
            this.setTimer(aci);

            sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(mapDialogSriForSM.getLocalDialogId());
            if (sriForSMTransaction == null) {
                throw new Exception();
            }
            TimerID sriSmTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "timerID");
            if (sriSmTimerID != null)
                this.timerFacility.cancelTimer(sriSmTimerID);
            String domainForPsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "sriPsiDomain");
            psiMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
            eps = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "locationInfoEPS");
            curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
            sriSmOnly = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "sriSmOnly");
            DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "transactionStart");
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (gmlcCdrState.isInitialized()) {
                gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, psiMsisdn));
                gmlcCdrState.setCurlUser(curlUser);
                if (dialogStartTime != null && eventTime != null) {
                    Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                    gmlcCdrState.setDialogDuration(dialogDuration);
                }
            }
            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);

            MessageType tcapMessageType = mapDialogSriForSM.getTCAPMessageType();
            if (tcapMessageType == MessageType.Abort) {
                mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                mlpClientErrorMessage = "TCAP ABORT on SRISM";
                if (gmlcCdrState.isInitialized()) {
                    this.createCDRRecord(RecordStatus.SRISM_TCAP_DIALOG_ABORT);
                }
                if (mapDialogSriForSM.getRemoteAddress() != null)
                    if (mapDialogSriForSM.getRemoteAddress().getGlobalTitle() != null)
                        nnn = mapDialogSriForSM.getRemoteAddress().getGlobalTitle().getDigits();
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "SRISM", psiMsisdn, psiImsi, null, nnn,
                    null, null, null, false);
                return;
            }

            if (locationInfoWithLMSI != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForSmResponse: received LocationInfoWithLMSI parameter");
                }
                if (locationInfoWithLMSI.getNetworkNodeNumber() != null) {
                    sriForSmResponseValues.setNetworkNodeNumber(locationInfoWithLMSI.getNetworkNodeNumber());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setNetworkNodeNumber(sriForSmResponseValues.getNetworkNodeNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForSmResponse: CDR state is initialized, NNN set");
                        }
                    }
                }
                if (locationInfoWithLMSI.getAdditionalNumber() != null) {
                    sriForSmResponseValues.setAdditionalNumber(locationInfoWithLMSI.getAdditionalNumber());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setAdditionalNumber(sriForSmResponseValues.getAdditionalNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForSmResponse: CDR state is initialized, Additional Number set");
                        }
                    }
                }
                if (locationInfoWithLMSI.getLMSI() != null) {
                    sriForSmResponseValues.setLmsi(locationInfoWithLMSI.getLMSI());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setLmsi(sriForSmResponseValues.getLmsi());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForSmResponse: CDR state is initialized, LMSI set");
                        }
                    }
                }
                if (locationInfoWithLMSI.getGprsNodeIndicator()) {
                    sriForSmResponseValues.setGprsNodeIndicator(locationInfoWithLMSI.getGprsNodeIndicator());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setGprsNodeIndicator(sriForSmResponseValues.isGprsNodeIndicator());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForSmResponse: CDR state is initialized, GPRS Node Indicator set");
                        }
                    }
                } else {
                    sriForSmResponseValues.setGprsNodeIndicator(false);
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setGprsNodeIndicator(false);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForSmResponse: CDR state is initialized, GPRS Node Indicator set");
                        }
                    }
                }
            }

            if (imsi != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForSmResponse: received IMSI parameter");
                }
                sriForSmResponseValues.setImsi(imsi);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setImsi(sriForSmResponseValues.getImsi());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForSmResponse: CDR state is initialized, IMSI set");
                    }
                }
            }

            this.setSendRoutingInfoForSMResponse(event);

            if (this.getSendRoutingInfoForSMResponse() != null) {

                if (sriSmOnly) {
                    // handle retrieval of SRISM response when SRISM is the operation requested
                    if (gmlcCdrState.isInitialized()) {
                        if (imsi != null)
                            this.createCDRRecord(RecordStatus.SRISM_SUCCESS);
                        else
                            this.createCDRRecord(RecordStatus.SRISM_ERROR);
                    }
                    handleSriResponseValue(mlpRespResult, null, sriForSmResponseValues, null, "SRISM", psiMsisdn, null, mlpClientErrorMessage);
                    // destroy transactions if exist, detach and return
                    if (transaction != null)
                        mobileCoreNetworkTransactions.Instance().destroy(transaction);
                    if (sriForSMTransaction != null)
                        mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);
                    detachFromMAPDialogSms(aci);
                    return;
                }

                LMSI lmsi = null;
                if (locationInfoWithLMSI.getLMSI() != null) {
                    lmsi = locationInfoWithLMSI.getLMSI();
                }
                boolean locationInformation = true;
                boolean subscriberState = true;
                MAPExtensionContainer extensionContainer = null;
                boolean currentLocation = true;
                DomainType requestedDomain;
                if (domainForPsi.equalsIgnoreCase("ps"))
                    requestedDomain = DomainType.psDomain;
                else
                    requestedDomain = DomainType.csDomain;
                boolean imei = true;
                boolean msClassmark = true;
                boolean mnpRequestedInfo = true;
                boolean locationInformationEPSsupported;
                if (eps == null)
                    locationInformationEPSsupported = true;
                else
                    locationInformationEPSsupported = Boolean.valueOf(eps);
                RequestedInfo requestedInfo = new RequestedInfoImpl(locationInformation, subscriberState, extensionContainer, currentLocation,
                    requestedDomain, imei, msClassmark, mnpRequestedInfo, locationInformationEPSsupported);

                AddressString originAddressString, destinationAddressString;
                originAddressString = destinationAddressString = null;
                EMLPPPriority emlppPriority = null;

                MAPDialogMobility mapDialogMobility = null;

                try {
                    if (this.subscriberInfoEnquiryContext == null) {
                        mapDialogMobility = this.mapProvider.getMAPServiceMobility().createNewDialog(
                                this.getMAPPsiApplicationContext(), this.getGmlcSccpAddress(), originAddressString,
                                locationInfoWithLMSI.getGprsNodeIndicator() ?
                                        getSgsnSccpAddress(locationInfoWithLMSI.getNetworkNodeNumber().getAddress()) :
                                        getVlrSccpAddress(locationInfoWithLMSI.getNetworkNodeNumber().getAddress()), destinationAddressString);
                    } else {
                        if (this.subscriberInfoEnquiryContext != null) {
                            mapDialogMobility = this.mapProvider.getMAPServiceMobility().createNewDialog(
                                    this.subscriberInfoEnquiryContext, this.getGmlcSccpAddress(), originAddressString,
                                    locationInfoWithLMSI.getGprsNodeIndicator() ?
                                            getSgsnSccpAddress(locationInfoWithLMSI.getNetworkNodeNumber().getAddress()) :
                                            getVlrSccpAddress(locationInfoWithLMSI.getNetworkNodeNumber().getAddress()), destinationAddressString);
                        }
                    }
                } catch (MAPException e) {
                    e.printStackTrace();
                }

                // Transaction
                transaction = mobileCoreNetworkTransactions.Instance().create();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", curlUser);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriForSMImsi", new String(imsi.getData().getBytes()));
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiMsisdn", psiMsisdn);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiNNN", nnn);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriForSmResponse", sriForSmResponseValues);
                mobileCoreNetworkTransactions.Instance().setDialog(transaction, mapDialogMobility.getLocalDialogId());
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", dialogStartTime);
                mapDialogMobility.addProvideSubscriberInfoRequest(imsi, lmsi, requestedInfo, mapExtensionContainer, emlppPriority);

                // Keep ACI in across MAP dialog for PSI
                ActivityContextInterface psiDialogACI = this.mapAcif.getActivityContextInterface(mapDialogMobility);
                psiDialogACI.attach(this.sbbContext.getSbbLocalObject());

                // set new timer for the case PSI does not arrive in time
                TimerID timerID = timerFacility.setTimer(psiDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);

                // ProvideSubscriberInfoRequest is now composed by values taken from SRISM response
                // Send PSI
                mapDialogMobility.send();

            } else {
                if (mapErrorMessage != null) {
                    // SRISM error CDR creation
                    if (gmlcCdrState.isInitialized()) {
                        mlpClientErrorMessage = handleRecordAndLocationReportOnMapError(mapErrorMessage, mlpRespResult, psiMsisdn, psiImsi,
                            "SRISM", null, nnn, null, gmlcCdrState, false);
                    }
                    if (transaction != null)
                        mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onSendRoutingInfoForSmResponse=%s", event), e);
            this.createCDRRecord(RecordStatus.SRISM_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP SRISM response: " + e.getMessage(),
                "SRISM", psiMsisdn, psiImsi, null, nnn, null, null, null, false);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } finally {
            detachFromMAPDialogSms(aci);
        }
    }

    /**
     * Subscriber Information Services (via MAP call handling service)
     * MAP-SEND-ROUTING-INFO (SRI) Events
     */
    public void onSendRoutingInformationRequest(SendRoutingInformationRequest event, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSendRoutingInformationRequest = " + event);
            }

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onSendRoutingInformationRequest=%s", event), e);
        }
    }

    public void onSendRoutingInformationResponse(SendRoutingInformationResponse event, ActivityContextInterface aci) {

        MAPErrorMessage mapErrorMessage = this.getErrorResponse();
        String psiMsisdn = null, psiImsi = null, nnn = null, eps, curlUser;
        Long sriTransaction, transaction = null;
        boolean sriOnly = false;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSendRoutingInformationResponse = " + event);
            }
            MAPDialogCallHandling mapDialogSri = event.getMAPDialog();
            IMSI imsi = event.getIMSI();
            ISDNAddressString vmscAddress = event.getVmscAddress();
            ExtendedRoutingInfo extendedRoutingInfo = event.getExtendedRoutingInfo();
            ISDNAddressString roamingNumber = null;
            MAPExtensionContainer mapExtensionContainer = event.getExtensionContainer();
            if (imsi != null)
                psiImsi = imsi.getData();
            if (vmscAddress != null)
                nnn = vmscAddress.getAddress();

            SriResponseValues sriResponseValues = new SriResponseValues();
            MLPResponse.MLPResultType mlpRespResult = null;
            String mlpClientErrorMessage = null;

            // CDR initialization
            GMLCCDRState gmlcCdrState = CDRCreationHelper.mapSriCdrInitializer(aci, this.getCDRInterface(), event);
            // Set timer last
            this.setTimer(aci);

            sriTransaction = mobileCoreNetworkTransactions.getTransactionId(mapDialogSri.getLocalDialogId());
            TimerID sriTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "timerID");
            if (sriTimerID != null)
                this.timerFacility.cancelTimer(sriTimerID);
            String domainForPsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "sriPsiDomain");
            eps = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "locationInfoEPS");
            psiMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
            curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "curlUser");
            DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "transactionStart");
            sriOnly = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "sriOnly");
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (gmlcCdrState.isInitialized()) {
                gmlcCdrState.setCurlUser(curlUser);
                if (dialogStartTime != null && eventTime != null) {
                    Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                    gmlcCdrState.setDialogDuration(dialogDuration);
                }
                if (psiMsisdn != null)
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, psiMsisdn));
            }
            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);

            MessageType tcapMessageType = mapDialogSri.getTCAPMessageType();
            if (tcapMessageType == MessageType.Abort) {
                mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                mlpClientErrorMessage = "TCAP ABORT on SRI";
                if (gmlcCdrState.isInitialized()) {
                    this.createCDRRecord(RecordStatus.SRI_TCAP_DIALOG_ABORT);
                }
                if (mapDialogSri.getRemoteAddress() != null)
                    if (mapDialogSri.getRemoteAddress().getGlobalTitle() != null)
                        nnn = mapDialogSri.getRemoteAddress().getGlobalTitle().getDigits();
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "SRI", psiMsisdn, psiImsi, null, nnn, null,
                    null, null, false);
                return;
            }


            if (imsi != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInformationResponse: received IMSI parameter");
                }
                sriResponseValues.setImsi(imsi);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setImsi(sriResponseValues.getImsi());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInformationResponse: CDR state is initialized, IMSI set");
                    }
                }
            } else {
                mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                mlpClientErrorMessage = "SYSTEM FAILURE on SRI response, IMSI is null";
                this.createCDRRecord(RecordStatus.SRI_SYSTEM_FAILURE);
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "SRI", psiMsisdn, psiImsi, null, nnn, null,
                    null, null, false);
                return;
            }

            if (vmscAddress != null) {
                sriResponseValues.setVmscAddress(vmscAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setNetworkNodeNumber(sriResponseValues.getVmscAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInformationResponse: CDR state is initialized, NNN (VMSC Address) set");
                    }
                }
            } else {
                if (extendedRoutingInfo != null) {
                    sriResponseValues.setExtendedRoutingInfo(extendedRoutingInfo);
                    if (extendedRoutingInfo.getRoutingInfo() != null)
                        roamingNumber = extendedRoutingInfo.getRoutingInfo().getRoamingNumber();
                }
                mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (extendedRoutingInfo.getRoutingInfo().getRoamingNumber() != null)
                    mlpClientErrorMessage = "SYSTEM FAILURE on SRI response, vmscAddress is null, got extended routing info roaming number: " + roamingNumber.getAddress();
                else
                    mlpClientErrorMessage = "SYSTEM FAILURE on SRI response, vmscAddress is null";
                this.createCDRRecord(RecordStatus.SRI_VMSC_ADDRESS_NOT_PRESENT);
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "SRI", psiMsisdn, psiImsi, null, nnn, null,
                    null, null, false);
                return;
            }

            this.setSendRoutingInformationResponse(event);

            if (this.getSendRoutingInformationResponse() != null) {

                if (sriOnly) {
                    // handle retrieval of SRI response when SRI is the operation requested
                    if (gmlcCdrState.isInitialized()) {
                        if (imsi != null)
                            this.createCDRRecord(RecordStatus.SRI_SUCCESS);
                        else
                            this.createCDRRecord(RecordStatus.SRI_ERROR);
                    }
                    handleSriResponseValue(mlpRespResult, sriResponseValues, null, null, "SRI", psiMsisdn, null, mlpClientErrorMessage);
                    // destroy transactions if exist, detach and return
                    if (transaction != null)
                        mobileCoreNetworkTransactions.Instance().destroy(transaction);
                    if (sriTransaction != null)
                        mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);
                    detachFromMAPDialogCallHandling(aci);
                    return;
                }

                boolean locationInformation = true;
                boolean subscriberState = true;
                MAPExtensionContainer extensionContainer = null;
                boolean currentLocation = true;
                DomainType requestedDomain;
                if (domainForPsi.equalsIgnoreCase("ps"))
                    requestedDomain = DomainType.psDomain;
                else
                    requestedDomain = DomainType.csDomain;
                boolean imei = true;
                boolean msClassmark = true;
                boolean mnpRequestedInfo = true;
                boolean locationInformationEPSsupported;
                if (eps == null)
                    locationInformationEPSsupported = true;
                else
                    locationInformationEPSsupported = Boolean.valueOf(eps);
                RequestedInfo requestedInfo = new RequestedInfoImpl(locationInformation, subscriberState, extensionContainer, currentLocation,
                    requestedDomain, imei, msClassmark, mnpRequestedInfo, locationInformationEPSsupported);

                AddressString originAddressString, destinationAddressString;
                originAddressString = destinationAddressString = null;
                LMSI lmsi = null;
                EMLPPPriority emlppPriority = null;

                MAPDialogMobility mapDialogMobility = null;

                try {
                    if (this.subscriberInfoEnquiryContext == null) {
                        if (vmscAddress != null)
                            mapDialogMobility = this.mapProvider.getMAPServiceMobility().createNewDialog(this.getMAPPsiApplicationContext(),
                                this.getGmlcSccpAddress(), originAddressString,
                                getVlrSccpAddress(vmscAddress.getAddress()), destinationAddressString);
                    } else {
                        if (this.subscriberInfoEnquiryContext != null) {
                            if (vmscAddress != null)
                                mapDialogMobility = this.mapProvider.getMAPServiceMobility().createNewDialog(this.subscriberInfoEnquiryContext,
                                    this.getGmlcSccpAddress(), originAddressString,
                                    getVlrSccpAddress(vmscAddress.getAddress()), destinationAddressString);
                        }
                    }
                } catch (MAPException e) {
                    e.printStackTrace();
                }

                // Transaction
                transaction = mobileCoreNetworkTransactions.Instance().create();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", curlUser);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriImsi", new String(imsi.getData().getBytes()));
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiMsisdn", psiMsisdn);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriResponse", sriResponseValues);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiNNN", nnn);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", dialogStartTime);
                mobileCoreNetworkTransactions.Instance().setDialog(transaction, mapDialogMobility.getLocalDialogId());

                mapDialogMobility.addProvideSubscriberInfoRequest(imsi, lmsi, requestedInfo, mapExtensionContainer, emlppPriority);

                // Keep ACI in across MAP dialog for PSI
                ActivityContextInterface psiDialogACI = this.mapAcif.getActivityContextInterface(mapDialogMobility);
                psiDialogACI.attach(this.sbbContext.getSbbLocalObject());

                // set new timer for the PSI request/response cycle
                TimerID timerID = timerFacility.setTimer(psiDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);

                // ProvideSubscriberInfoRequest is now composed by values taken from SRI response
                // Send PSI
                mapDialogMobility.send();

            } else {
                if (mapErrorMessage != null) {
                    // SRI error CDR creation
                    if (gmlcCdrState.isInitialized()) {
                        mlpClientErrorMessage = handleRecordAndLocationReportOnMapError(mapErrorMessage, mlpRespResult, psiMsisdn, psiImsi,
                            "SRI", null, null, nnn, gmlcCdrState, false);
                    }
                    if (transaction != null)
                        mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onSendRoutingInformationResponse=%s", event), e);
            this.createCDRRecord(RecordStatus.SRI_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP SRI response: " + e.getMessage(),
                "SRI", psiMsisdn, psiImsi, null, nnn, null, null, null, false);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } finally {
            detachFromMAPDialogCallHandling(aci);
        }
    }

    /**
     * MAP-PROVIDE-SUBSCRIBER-INFO (PSI) Events
     */
    public void onProvideSubscriberInformationRequest(ProvideSubscriberInfoRequest event, ActivityContextInterface aci) {

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onProvideSubscriberInformationRequest = " + event);
            }

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onProvideSubscriberInformationRequest=%s", event), e);
        }
    }

    /**
     * MAP-PROVIDE-SUBSCRIBER-INFO (PSI)
     */
    public void provideSubscriberInfoRequestFirst(String imsiStr, String nnn, String domain, String psiMsisdn, String epsLocationInfo,
                                                  Integer translationType, String curlUser) {

        IMSI imsi = new IMSIImpl(imsiStr);
        LMSI lmsi = null;
        boolean locationInformation = true;
        boolean subscriberState = true;
        MAPExtensionContainer mapExtensionContainer = null;
        boolean currentLocation = true;
        DomainType requestedDomain;
        Long transaction;
        if (domain.equalsIgnoreCase("ps"))
            requestedDomain = DomainType.psDomain;
        else
            requestedDomain = DomainType.csDomain;
        boolean imei = true;
        boolean msClassmark = true;
        boolean mnpRequestedInfo = true;
        boolean locationInformationEPSsupported;
        if (epsLocationInfo == null)
            locationInformationEPSsupported = true;
        else
            locationInformationEPSsupported = Boolean.valueOf(epsLocationInfo);
        RequestedInfo requestedInfo = new RequestedInfoImpl(locationInformation, subscriberState, mapExtensionContainer, currentLocation,
            requestedDomain, imei, msClassmark, mnpRequestedInfo, locationInformationEPSsupported);

        AddressString originAddressString, destinationAddressString;
        originAddressString = destinationAddressString = null;
        SccpAddress networkNodeAddress = requestedDomain.equals(DomainType.psDomain) ? getSgsnSccpAddress(nnn) : getVlrSccpAddress(nnn);

        MAPDialogMobility mapDialogMobility = null;
        try {
            if (this.subscriberInfoEnquiryContext == null) {
                mapDialogMobility = this.mapProvider.getMAPServiceMobility().createNewDialog(this.getMAPPsiApplicationContext(),
                        this.getGmlcSccpAddress(), originAddressString, networkNodeAddress, destinationAddressString);
            } else {
                mapDialogMobility = this.mapProvider.getMAPServiceMobility().createNewDialog(this.subscriberInfoEnquiryContext,
                    this.getGmlcSccpAddress(), originAddressString, networkNodeAddress, destinationAddressString);
            }

        } catch (MAPException e) {
            e.printStackTrace();
        }

        try {
            mapDialogMobility.addProvideSubscriberInfoRequest(imsi, lmsi, requestedInfo, mapExtensionContainer, null);
        } catch (MAPException e) {
            logger.severe(String.format("MAP Exception while trying to add PSI to MAP dialog mobility dialog on provideSubscriberInfoRequestFirst=%s", e.getMessage()));
            e.printStackTrace();
        }

        // Keep ACI in across MAP dialog for PSI
        ActivityContextInterface psiDialogACI = this.mapAcif.getActivityContextInterface(mapDialogMobility);
        psiDialogACI.attach(this.sbbContext.getSbbLocalObject());

        // Transaction
        transaction = mobileCoreNetworkTransactions.Instance().create();
        mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiServiceType", "psiFirst");
        mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiOnlyImsi", imsiStr);
        mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiOnlyNnn", nnn);
        mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiMsisdn", psiMsisdn);
        mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", curlUser);
        DateTime transactionStart = DateTime.now();
        mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", transactionStart);
        mobileCoreNetworkTransactions.Instance().setDialog(transaction, mapDialogMobility.getLocalDialogId());
        // set new timer for the PSI request/response cycle
        TimerID timerID = timerFacility.setTimer(psiDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
        mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);

        // RequestedInfo for ProvideSubscriberInfoRequest is composed by values taken from HTTP request (IMSI and NNN)
        try {
            // Send PSI
            mapDialogMobility.send();

        } catch (MAPException e) {
            logger.severe(String.format("MAP Exception while trying to send PSI on provideSubscriberInfoRequestFirst=%s", e.getMessage()));
            e.printStackTrace();
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        }
    }

    public void onProvideSubscriberInformationResponse(ProvideSubscriberInfoResponse event, ActivityContextInterface aci) {

        MAPErrorMessage mapErrorMessage = this.getErrorResponse();
        this.setProvideSubscriberInformationResponse(event);
        String psiMsisdn = null, psiImsi = null, nnn = null, curlUser = null;
        Long transaction = null;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onProvideSubscriberInformationResponse = " + event);
            }
            MAPDialogMobility mapDialogMobility = event.getMAPDialog();
            SubscriberInfo subscriberInfo = event.getSubscriberInfo();
            MAPExtensionContainer mapExtensionContainer = event.getExtensionContainer();

            PsiResponseValues psiResponseValues = new PsiResponseValues();
            MLPResponse.MLPResultType mlpRespResult = null;
            String mlpClientErrorMessage = null;

            // CDR initialization
            CDRCreationHelper.GmlcCdrStateString gmlcCdrStateString = CDRCreationHelper.mapPsiCdrInitializer(aci, this.getCDRInterface(), event, nnn);
            GMLCCDRState gmlcCdrState = gmlcCdrStateString.getGmlcCdrState();
            nnn = gmlcCdrStateString.getNnn();
            // Set timer last
            this.setTimer(aci);

            // Transaction
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(mapDialogMobility.getLocalDialogId());

            if (transaction != null) {

                TimerID psiTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
                if (psiTimerID != null)
                    this.timerFacility.cancelTimer(psiTimerID);

                // PSI only
                String psiServiceType = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiServiceType");
                String psiOnlyImsi = psiImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                String psiOnlyNnn = nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyNnn");
                curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");

                psiResponseValues.setPsiServiceType(psiServiceType);
                psiResponseValues.setPsiOnlyImsi(psiOnlyImsi);
                psiResponseValues.setPsiOnlyNnn(psiOnlyNnn);

                if (this.logger.isFineEnabled()) {
                    logger.fine(String.format("*** psiOnly values fixed to { '%s', '%s', '%s' } obtained from Transaction.", psiServiceType, psiOnlyImsi, psiOnlyNnn));
                }

                // SRISM/SRI - PSI
                String sriForSMImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                String sriImsi = psiImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                SriForSmResponseValues sriForSmResponse = (SriForSmResponseValues) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSmResponse");
                SriResponseValues sriResponse = (SriResponseValues) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriResponse");
                psiMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
                gmlcCdrState.setDialogStartTime(dialogStartTime);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setCurlUser(curlUser);
                    if (dialogStartTime != null && eventTime != null) {
                        Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                        gmlcCdrState.setDialogDuration(dialogDuration);
                    }
                }
                mobileCoreNetworkTransactions.Instance().destroy(transaction);

                if (sriForSMImsi != null)
                    psiResponseValues.setSriForSMImsi(sriForSMImsi);
                else if (sriImsi != null)
                    psiResponseValues.setSriForSMImsi(sriImsi);
                if (sriForSmResponse != null)
                    psiResponseValues.setSriForSmResponse(sriForSmResponse);
                else if (sriResponse != null)
                    psiResponseValues.setSriResponse(sriResponse);

                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setCurlUser(curlUser);
                    if (psiServiceType != null) {
                        if (psiServiceType.equalsIgnoreCase("psiFirst") && psiOnlyImsi != null && psiOnlyNnn != null) {
                            IMSI imsi = new IMSIImpl(psiOnlyImsi);
                            gmlcCdrState.setImsi(imsi);
                        }
                    }
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, psiMsisdn));
                }
            } else {
                throw new Exception();
            }

            MessageType tcapMessageType = mapDialogMobility.getTCAPMessageType();
            if (tcapMessageType == MessageType.Abort) {
                mobileCoreNetworkTransactions.destroy(transaction);
                mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                mlpClientErrorMessage = "TCAP ABORT on PSI";
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, psiMsisdn));
                    this.createCDRRecord(RecordStatus.SRISM_TCAP_DIALOG_ABORT);
                }
                if (mapDialogMobility.getRemoteAddress() != null)
                    if (mapDialogMobility.getRemoteAddress().getGlobalTitle() != null)
                        nnn = mapDialogMobility.getRemoteAddress().getGlobalTitle().getDigits();
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "PSI", psiMsisdn, psiImsi, null, nnn, null,
                    null, null, false);
                return;
            }

            if (subscriberInfo != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (gmlcCdrState.isInitialized()) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberInformationResponse: setting retrieved Subscriber Info to CDR");
                    }
                    gmlcCdrState.setSubscriberInfo(subscriberInfo);
                }

                // Inquire if Location information is included in MAP PSI response subscriber's info
                if (subscriberInfo.getLocationInformation() != null) {
                    // Location information is included in MAP PSI response
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberInformationResponse: Location Information included, decoding location information parameters");
                    }
                    psiResponseValues.setLocationInformation(subscriberInfo.getLocationInformation());
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setLocationInformation(psiResponseValues.getLocationInformation());
                    }

                    // Inquire if Location Number is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getLocationNumber() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: Location Number is included");
                        }
                        psiResponseValues.setLocationNumberMap(subscriberInfo.getLocationInformation().getLocationNumber());
                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setLocationNumberMap(subscriberInfo.getLocationInformation().getLocationNumber());
                        }
                    }

                    // Inquire if VLR number (Global Title) is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getVlrNumber() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: VLR number is included");
                        }
                        psiResponseValues.setVlrNumber(subscriberInfo.getLocationInformation().getVlrNumber());
                    }

                    // Inquire if MSC number (Global Title) is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getMscNumber() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: MSC number is included");
                        }
                        psiResponseValues.setMscNumber(subscriberInfo.getLocationInformation().getMscNumber());
                    }

                    // Inquire if Age of Location information is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getAgeOfLocationInformation() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: Age of Location information is included");
                        }
                        psiResponseValues.setAgeOfLocationInfo(subscriberInfo.getLocationInformation().getAgeOfLocationInformation().intValue());
                    }

                    // Inquire if Cell Global Identity (CGI) or Service Area Identity (SAI) or Location Area Identity (LAI) are included in MAP PSI response
                    if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        // CGI or SAI or LAI are included in MAP PSI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: CGI or SAI or LAI number is included");
                        }
                        CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = subscriberInfo.getLocationInformation()
                            .getCellGlobalIdOrServiceAreaIdOrLAI();
                        psiResponseValues.setCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdOrLAI);
                        // Inquire and get parameters of CGI or SAI or LAI included in MAP PSI response
                        // Case when CGI or SAI length is fixed
                        if (cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: received CellGlobalIdOrServiceAreaIdFixedLength, decoding MCC, MNC, LAC, CI");
                            }
                            try {
                                psiResponseValues.setMcc(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC());
                                psiResponseValues.setMnc(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC());
                                psiResponseValues.setLac(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getLac());
                                psiResponseValues.setCi(cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode());

                            } catch (MAPException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
                            // Case when LAI length is fixed
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: received laiFixedLength, decoding MCC, MNC, LAC (no CI)");
                            }
                            psiResponseValues.setMcc(cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMCC());
                            psiResponseValues.setMcc(cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMNC());
                            psiResponseValues.setLac(cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getLac());
                        }
                    }

                    // Inquire if SAI is present in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getSaiPresent()) {
                        psiResponseValues.setSaiPresent(subscriberInfo.getLocationInformation().getSaiPresent());
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting SAI present value to CDR");
                            }
                            gmlcCdrState.setSaiPresent(subscriberInfo.getLocationInformation().getSaiPresent());
                        }
                    } else {
                        psiResponseValues.setSaiPresent(false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting SAI present value to CDR");
                            }
                            gmlcCdrState.setSaiPresent(false);
                        }
                    }

                    // Inquire if current location retrieval is present in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getCurrentLocationRetrieved()) {
                        psiResponseValues.setCurrentLocationRetrieved(subscriberInfo.getLocationInformation().getCurrentLocationRetrieved());
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting current location retrieved value to CDR");
                            }
                            gmlcCdrState.setCurrentLocationRetrieved(subscriberInfo.getLocationInformation().getCurrentLocationRetrieved());
                        }
                    } else {
                        psiResponseValues.setCurrentLocationRetrieved(false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting current location retrieved value to CDR");
                            }
                            gmlcCdrState.setCurrentLocationRetrieved(false);
                        }
                    }

                    // Inquires if subscriber Geographical information is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getGeographicalInformation() != null) {
                        // Geographical information is included in MAP PSI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: "
                                + "received Geographical information, decoding latitude, longitude, uncertainty and type of shape");
                        }
                        psiResponseValues.setGeographicalLatitude(subscriberInfo.getLocationInformation().getGeographicalInformation().getLatitude());
                        psiResponseValues.setGeographicalLongitude(subscriberInfo.getLocationInformation().getGeographicalInformation().getLongitude());
                        psiResponseValues.setGeographicalUncertainty(subscriberInfo.getLocationInformation().getGeographicalInformation().getUncertainty());
                        psiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getGeographicalInformation().getTypeOfShape());
                    }

                    // Inquires if subscriber Geodetic information is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformation().getGeodeticInformation() != null) {
                        // Geographical information is included in MAP PSI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: "
                                + "received Geodetic information, decoding latitude, longitude, uncertainty, confidence, type of shape and screening and presentation indicators");
                        }
                        psiResponseValues.setGeodeticLatitude(subscriberInfo.getLocationInformation().getGeodeticInformation().getLatitude());
                        psiResponseValues.setGeodeticLongitude(subscriberInfo.getLocationInformation().getGeodeticInformation().getLongitude());
                        psiResponseValues.setGeodeticUncertainty(subscriberInfo.getLocationInformation().getGeodeticInformation().getUncertainty());
                        psiResponseValues.setGeodeticConfidence(subscriberInfo.getLocationInformation().getGeodeticInformation().getConfidence());
                        psiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getGeodeticInformation().getTypeOfShape());
                        psiResponseValues.setScreeningAndPresentationIndicators(subscriberInfo.getLocationInformation().getGeodeticInformation().getScreeningAndPresentationIndicators());
                    }

                    // Inquire if location information includes EPS location info (LTE)
                    if (subscriberInfo.getLocationInformation().getLocationInformationEPS() != null) {
                        // EPS location information is included in MAP PSI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: received EPS location information, " +
                                "decoding Tracking Area Identity, E-UTRAN CGI, MME Name, AoL, Geographical and Geodetic information");
                        }
                        psiResponseValues.setLocationInformationEPS(subscriberInfo.getLocationInformation().getLocationInformationEPS());

                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: received MME Name");
                            }
                            psiResponseValues.setMmeName(subscriberInfo.getLocationInformation().getLocationInformationEPS().getMmeName());
                        }

                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                            psiResponseValues.setTaId(subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity());
                        }

                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                            psiResponseValues.seteUtranCgi(subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity());
                        }

                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
                            // EPS Geographical information is included in MAP PSI response
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: received EPS Geographical information, "
                                    + "decoding latitude, longitude, uncertainty, confidence, type of shape and screening and presentation indicators");
                            }
                            psiResponseValues.setGeographicalLatitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude());
                            psiResponseValues.setGeographicalLongitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude());
                            psiResponseValues.setGeographicalUncertainty(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty());
                            psiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape());
                        }
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                            // EPS Geodetic information is included in MAP PSI response
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: received EPS Geodetic information, "
                                    + "decoding latitude, longitude, uncertainty, confidence, type of shape and screening and presentation indicators");
                            }
                            psiResponseValues.setGeodeticLatitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude());
                            psiResponseValues.setGeodeticLongitude(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude());
                            psiResponseValues.setGeodeticUncertainty(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty());
                            psiResponseValues.setGeodeticConfidence(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getConfidence());
                            psiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape());
                            psiResponseValues.setScreeningAndPresentationIndicators(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getScreeningAndPresentationIndicators());
                        }
                        // Get EPS Age of Location Information if included in MAP PSI response
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: received EPS Age of Location information");
                            }
                            psiResponseValues.setAgeOfLocationInfo(subscriberInfo.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation());
                        }
                        // Get if EPS location information is current
                        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved() ||
                            !subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved()) {
                            psiResponseValues.setCurrentLocationRetrieved(subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved()
                                ? subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved() : false);
                            if (gmlcCdrState.isInitialized()) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting current location retrieved value Name to CDR");
                                }
                                gmlcCdrState.setCurrentLocationRetrieved(psiResponseValues.isCurrentLocationRetrieved() ? psiResponseValues.isCurrentLocationRetrieved() : false);
                            }
                        }
                    }
                }

                // Inquire if location information includes GPRS location information
                if (subscriberInfo.getLocationInformationGPRS() != null) {
                    // EPS location information is included in MAP PSI response
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberInformationResponse: "
                            + "received GPRS location information, decoding CGI or SAI or LAI, Geographical and Geodetic information, SGSN Number");
                    }
                    psiResponseValues.setLocationInformationGPRS(subscriberInfo.getLocationInformationGPRS());
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setLocationInformationGPRS(psiResponseValues.getLocationInformationGPRS());
                    }

                    // Inquire if VLR number (Global Title) is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformationGPRS().getSGSNNumber() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: SGSN number is included");
                        }
                        psiResponseValues.setSgsnNumber(subscriberInfo.getLocationInformationGPRS().getSGSNNumber());
                    }

                    // Inquire if Localised Service Area is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformationGPRS().getLSAIdentity() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: LSA Identity is included");
                        }
                        psiResponseValues.setLsaIdentity(subscriberInfo.getLocationInformationGPRS().getLSAIdentity());
                        if (subscriberInfo.getLocationInformationGPRS().getLSAIdentity().isPlmnSignificantLSA())
                            psiResponseValues.setPLMNSignificantLSA(subscriberInfo.getLocationInformationGPRS().getLSAIdentity().isPlmnSignificantLSA());
                        else
                            psiResponseValues.setPLMNSignificantLSA(false);
                    }

                    // Inquire if Routing Area Identity is present in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformationGPRS().getRouteingAreaIdentity() != null) {
                        psiResponseValues.setRaIdentity(subscriberInfo.getLocationInformationGPRS().getRouteingAreaIdentity());
                    }

                    // Inquire if Age of Location information is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformationGPRS().getAgeOfLocationInformation() != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: GPRS Age of Location information is included");
                        }
                        psiResponseValues.setAgeOfLocationInfo(subscriberInfo.getLocationInformationGPRS().getAgeOfLocationInformation().intValue());
                    }

                    // Inquire if Cell Global Identity (CGI) or Service Area Identity (SAI) or Location Area Identity (LAI) are included in MAP PSI response
                    if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        // CGI or SAI or LAI are included in MAP PSI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: GPRS CGI or SAI or LAI number is included");
                        }
                        CellGlobalIdOrServiceAreaIdOrLAI gprsCellGlobalIdOrServiceAreaIdOrLAI = subscriberInfo.getLocationInformationGPRS()
                            .getCellGlobalIdOrServiceAreaIdOrLAI();
                        psiResponseValues.setCellGlobalIdOrServiceAreaIdOrLAI(gprsCellGlobalIdOrServiceAreaIdOrLAI);
                        // Inquire and get parameters of CGI or SAI or LAI included in MAP PSI response
                        // Case when CGI or SAI length is fixed
                        if (gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: "
                                    + "received CellGlobalIdOrServiceAreaIdFixedLength, decoding MCC, MNC, LAC, CI");
                            }
                            try {
                                psiResponseValues.setMcc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC());
                                psiResponseValues.setMnc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC());
                                psiResponseValues.setLac(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getLac());
                                psiResponseValues.setCi(gprsCellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode());

                            } catch (MAPException e1) {
                                e1.printStackTrace();
                            }
                        } else if (gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
                            // Case when LAI length is fixed
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: "
                                    + "received laiFixedLength, decoding MCC, MNC, LAC (no CI)");
                            }
                            psiResponseValues.setMcc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMCC());
                            psiResponseValues.setMcc(gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getMNC());
                            psiResponseValues.setLac(gprsCellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength().getLac());
                        }
                    }

                    // Get if GPRS location information is current
                    if (subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved() ||
                        !subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved()) {
                        psiResponseValues.setCurrentLocationRetrieved(subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved()
                            ? subscriberInfo.getLocationInformationGPRS().isCurrentLocationRetrieved() : false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonAnyTimeInterrogationResponse: CDR state is initialized, setting current location retrieved value Name to CDR");
                            }
                            gmlcCdrState.setCurrentLocationRetrieved(psiResponseValues.isCurrentLocationRetrieved() ? psiResponseValues.isCurrentLocationRetrieved() : false);
                        }
                    }

                    // Get if GPRS location information is current
                    if (subscriberInfo.getLocationInformationGPRS().isSaiPresent() ||
                        !subscriberInfo.getLocationInformationGPRS().isSaiPresent()) {
                        psiResponseValues.setSaiPresent(subscriberInfo.getLocationInformationGPRS().isSaiPresent()
                            ? subscriberInfo.getLocationInformationGPRS().isSaiPresent() : false);
                        if (gmlcCdrState.isInitialized()) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting current location retrieved value Name to CDR");
                            }
                            gmlcCdrState.setSaiPresent(psiResponseValues.isSaiPresent() ? psiResponseValues.isSaiPresent() : false);
                        }
                    }

                    // Inquires if subscriber Geographical information is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformationGPRS().getGeographicalInformation() != null) {
                        // Geographical information is included in MAP PSI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: "
                                + "received Geographical information, decoding latitude, longitude, uncertainty and type of shape");
                        }
                        psiResponseValues.setGeographicalLatitude(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getLatitude());
                        psiResponseValues.setGeographicalLongitude(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getLongitude());
                        psiResponseValues.setGeographicalUncertainty(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getUncertainty());
                        psiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape());
                    }

                    // Inquires if subscriber Geodetic information is included in MAP PSI response subscriber's info
                    if (subscriberInfo.getLocationInformationGPRS().getGeodeticInformation() != null) {
                        // Geographical information is included in MAP PSI response
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: received Geodetic information, " +
                                "decoding latitude, longitude, uncertainty, confidence, type of shape and screening and presentation indicators");
                        }
                        psiResponseValues.setGeodeticLatitude(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getLatitude());
                        psiResponseValues.setGeodeticLongitude(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getLongitude());
                        psiResponseValues.setGeodeticUncertainty(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getUncertainty());
                        psiResponseValues.setGeodeticConfidence(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getConfidence());
                        psiResponseValues.setTypeOfShape(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape());
                        psiResponseValues.setScreeningAndPresentationIndicators(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getScreeningAndPresentationIndicators());
                    }
                }

                // Inquire if subscriber's state is included in MAP PSI response subscriber's info
                if (subscriberInfo.getSubscriberState() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberInformationResponse: received subscriber's state");
                    }
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's state is included in MAP PSI response, get it and store it as a response parameter
                    psiResponseValues.setSubscriberState(subscriberInfo.getSubscriberState());
                }

                if (subscriberInfo.getPSSubscriberState() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberInformationResponse: received subscriber's PS state");
                    }
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's PS state is included in MAP PSI response, get it and store it as a response parameter
                    psiResponseValues.setPsSubscriberState(subscriberInfo.getPSSubscriberState());
                }

                // Inquire if subscriber's is included in MAP PSI response subscriber's info
                if (subscriberInfo.getIMEI() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberInformationResponse: received subscriber's IMEI");
                    }
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's IMEI is included in MAP PSI response, get it and store it as a response parameter
                    psiResponseValues.setImei(subscriberInfo.getIMEI());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting subscriber's IMEI to CDR");
                        }
                        gmlcCdrState.setImei(subscriberInfo.getIMEI());
                    }
                }

                // Inquire if MNP Information Result is included in subscriber info
                if (subscriberInfo.getMNPInfoRes() != null) {
                    // MNP info result is included in MAP PSI response
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's MNP Info Result is included in MAP PSI response, get it and store it as a response parameter
                    psiResponseValues.setMnpInfoRes(subscriberInfo.getMNPInfoRes());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberInformationResponse: "
                            + "received MNP info result, decoding number portability status, MSISDN, IMSI, Routeing number");
                    }
                    if (subscriberInfo.getMNPInfoRes().getNumberPortabilityStatus() != null)
                        psiResponseValues.setNumberPortabilityStatus(subscriberInfo.getMNPInfoRes().getNumberPortabilityStatus().getType());
                    if (subscriberInfo.getMNPInfoRes().getMSISDN() != null)
                        psiResponseValues.setMsisdnAddress(subscriberInfo.getMNPInfoRes().getMSISDN().getAddress());
                    if (subscriberInfo.getMNPInfoRes().getIMSI() != null)
                        psiResponseValues.setImsiData(subscriberInfo.getMNPInfoRes().getIMSI().getData());
                    if (subscriberInfo.getMNPInfoRes().getRouteingNumber() != null)
                        psiResponseValues.setRouteingNumber(subscriberInfo.getMNPInfoRes().getRouteingNumber());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting subscriber's MNP Info result to CDR");
                        }
                        gmlcCdrState.setMnpInfoRes(subscriberInfo.getMNPInfoRes());
                    }
                }

                // Inquire if subscriber's MS Classmark is included in MAP PSI response
                if (subscriberInfo.getMSClassmark2() != null) {
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's MS Classmark is included in MAP PSI response, get it and store it as a response parameter
                    psiResponseValues.setMsClassmark2(subscriberInfo.getMSClassmark2());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting MS Classmark value to CDR");
                        }
                        gmlcCdrState.setMsClassmark2(psiResponseValues.getMsClassmark2());
                    }
                }

                // Inquire if subscriber's GPRS MS Class is included in MAP PSI response
                if (subscriberInfo.getGPRSMSClass() != null) {
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    // Subscriber's GPRS MS Class is included in MAP PSI response, get it and store it as a response parameter
                    psiResponseValues.setGprsmsClass(subscriberInfo.getGPRSMSClass());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, setting GPRS MS Class value to CDR");
                        }
                        gmlcCdrState.setGprsmsClass(psiResponseValues.getGprsmsClass());
                    }
                }
            }

            if (gmlcCdrState.isInitialized()) {
                if (subscriberInfo != null) {
                    if (subscriberInfo.getLocationInformation() != null) {
                        if (subscriberInfo.getLocationInformation().getGeographicalInformation() != null ||
                            subscriberInfo.getLocationInformation().getGeodeticInformation() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_GEO_SUCCESS");
                            }
                            if (gmlcCdrState.isInitialized()) {
                                this.createCDRRecord(RecordStatus.PSI_GEO_SUCCESS);
                            }
                        } else if (subscriberInfo.getLocationInformation().getLocationInformationEPS() != null) {
                            if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null
                                || subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonProvideSubscriberInformationResponse: EPSLocationInformation is not null, PSI_GEO_SUCCESS");
                                }
                                if (gmlcCdrState.isInitialized()) {
                                    this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_GEO_SUCCESS");
                                    this.createCDRRecord(RecordStatus.PSI_GEO_SUCCESS);
                                }
                            } else if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                                if (gmlcCdrState.isInitialized()) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_ECGI_STATE_SUCCESS");
                                    }
                                    this.createCDRRecord(RecordStatus.PSI_ECGI_STATE_SUCCESS);
                                }
                            } else if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                                if (gmlcCdrState.isInitialized()) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_TAI_STATE_SUCCESS");
                                    }
                                    this.createCDRRecord(RecordStatus.PSI_TAI_SUCCESS);
                                }
                            }
                        } else if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                            if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                if (subscriberInfo.getSubscriberState() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_CGI_STATE_SUCCESS");
                                    }
                                    if (subscriberInfo.getLocationInformation().getSaiPresent())
                                        this.createCDRRecord(RecordStatus.PSI_SAI_STATE_SUCCESS);
                                    else
                                        this.createCDRRecord(RecordStatus.PSI_CGI_STATE_SUCCESS);
                                } else {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_CGI_SUCCESS");
                                    }
                                    if (subscriberInfo.getLocationInformation().getSaiPresent())
                                        this.createCDRRecord(RecordStatus.PSI_CGI_SUCCESS);
                                    else
                                        this.createCDRRecord(RecordStatus.PSI_CGI_SUCCESS);
                                }
                            } else if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                                    if (subscriberInfo.getSubscriberState() != null) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_LAI_STATE_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.PSI_LAI_STATE_SUCCESS);
                                    } else {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_LAI_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.PSI_LAI_SUCCESS);
                                    }
                                }
                            } else if (subscriberInfo.getLocationInformation().getVlrNumber() != null) {
                                if (gmlcCdrState.getVlrAddress() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_NNN_SUCCESS");
                                    }
                                    this.createCDRRecord(RecordStatus.PSI_NNN_SUCCESS);
                                }
                            } else if (subscriberInfo.getLocationInformation().getMscNumber() != null) {
                                if (gmlcCdrState.getMscNumber() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_NNN_SUCCESS");
                                    }
                                    this.createCDRRecord(RecordStatus.PSI_NNN_SUCCESS);
                                }
                            }
                        }
                    } else if (subscriberInfo.getLocationInformationGPRS() != null) {
                        if (subscriberInfo.getLocationInformationGPRS().getGeographicalInformation() != null ||
                            subscriberInfo.getLocationInformationGPRS().getGeodeticInformation() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_GEO_SUCCESS");
                            }
                            this.createCDRRecord(RecordStatus.PSI_PS_GEO_SUCCESS);
                        } else if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                            if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                if (subscriberInfo.getPSSubscriberState() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_CGI_STATE_SUCCESS");
                                    }
                                    if (subscriberInfo.getLocationInformationGPRS().isSaiPresent())
                                        this.createCDRRecord(RecordStatus.PSI_PS_SAI_STATE_SUCCESS);
                                    else
                                        this.createCDRRecord(RecordStatus.PSI_PS_CGI_STATE_SUCCESS);
                                } else {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_CGI_SUCCESS");
                                    }
                                    if (subscriberInfo.getLocationInformationGPRS().isSaiPresent())
                                        this.createCDRRecord(RecordStatus.PSI_PS_SAI_SUCCESS);
                                    else
                                        this.createCDRRecord(RecordStatus.PSI_PS_CGI_SUCCESS);
                                }
                            } else if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                                if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                                    if (subscriberInfo.getPSSubscriberState() != null) {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_LAI_STATE_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.PSI_PS_LAI_STATE_SUCCESS);
                                    } else {
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_LAI_SUCCESS");
                                        }
                                        this.createCDRRecord(RecordStatus.PSI_PS_LAI_SUCCESS);
                                    }
                                }
                            } else if (subscriberInfo.getLocationInformationGPRS().getRouteingAreaIdentity() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_RAI_SUCCESS");
                                }
                                this.createCDRRecord(RecordStatus.PSI_PS_RAI_SUCCESS);
                            } else if (subscriberInfo.getLocationInformationGPRS().getLSAIdentity() != null) {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_LSA_SUCCESS");
                                }
                                this.createCDRRecord(RecordStatus.PSI_PS_LSA_SUCCESS);
                            } else if (subscriberInfo.getLocationInformationGPRS().getSGSNNumber() != null) {
                                if (gmlcCdrState.getSgsnNumber() != null) {
                                    if (this.logger.isFineEnabled()) {
                                        this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_PS_NNN_SUCCESS");
                                    }
                                    this.createCDRRecord(RecordStatus.PSI_PS_NNN_SUCCESS);
                                }
                            }
                        }
                    } else if (subscriberInfo.getIMEI() != null) {
                        if (gmlcCdrState.getImei() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_IMEI_SUCCESS");
                            }
                            this.createCDRRecord(RecordStatus.PSI_IMEI_SUCCESS);
                        }
                    } else if (subscriberInfo.getMNPInfoRes() != null) {
                        if (gmlcCdrState.getMnpInfoRes() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_MNP_INFO_SUCCESS");
                            }
                            this.createCDRRecord(RecordStatus.PSI_MNP_INFO_SUCCESS);
                        }
                    } else if (subscriberInfo.getSubscriberState() != null) {
                        if (subscriberInfo.getSubscriberState().getNotReachableReason() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_STATE_NOT_REACHABLE");
                            }
                            this.createCDRRecord(RecordStatus.PSI_STATE_NOT_REACHABLE);
                        } else {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_STATE_SUCCESS");
                            }
                            this.createCDRRecord(RecordStatus.PSI_STATE_SUCCESS);
                        }
                    } else if (subscriberInfo.getPSSubscriberState() != null) {
                        if (subscriberInfo.getPSSubscriberState().getNetDetNotReachable() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_STATE_NOT_REACHABLE");
                            }
                            this.createCDRRecord(RecordStatus.PSI_STATE_NOT_REACHABLE);
                        } else {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberInformationResponse: CDR state is initialized, PSI_STATE_SUCCESS");
                            }
                            this.createCDRRecord(RecordStatus.PSI_STATE_SUCCESS);
                        }
                    }
                } else {
                    if (mapErrorMessage != null) {
                        // PSI error CDR creation
                        if (gmlcCdrState.isInitialized()) {
                            mlpClientErrorMessage = handleRecordAndLocationReportOnMapError(mapErrorMessage, mlpRespResult, psiMsisdn, psiImsi,
                                "PSI", null, nnn, null, gmlcCdrState, false);
                        }
                    }
                }
            }

            // handle successful retrieval of PSI response
            handlePsiResponse(mlpRespResult, psiResponseValues, psiMsisdn, mlpClientErrorMessage);

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onProvideSubscriberInformationResponse=%s", event), e);
            this.createCDRRecord(RecordStatus.PSI_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP PSI response: " + e.getMessage(),
                "PSI", psiMsisdn, psiImsi, null, nnn, null, null, null, false);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } finally {
            detachFromMAPDialogMobility(aci);
        }
    }


    /**
     * Location Service Management (LSM) services
     * MAP_SEND_ROUTING_INFO_FOR_LCS (SRILCS) Events
     */
    /**
     * MAP SRILCS Request Event
     */
    public void onSendRoutingInfoForLCSRequest(SendRoutingInfoForLCSRequest event, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSendRoutingInfoForLCSRequest = " + event);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onSendRoutingInfoForLCSRequest=%s", event), e);
        }
    }

    /**
     * MAP SRILCS Response Event
     */
    public void onSendRoutingInfoForLCSResponse(SendRoutingInfoForLCSResponse event, ActivityContextInterface aci, EventContext eventContext) {

        MAPErrorMessage mapErrorMessage = this.getErrorResponse();
        String pslMsisdn = null, pslImsi = null, nnn = null, remotePslAddress = null, additionalNumberAddress = null, curlUser, httpRequestType;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        boolean sriLcsOnly = false;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSendRoutingInfoForLCSResponse = " + event);
            }

            MAPDialogLsm mapDialogLsm = event.getMAPDialog();
            SubscriberIdentity subscriberIdentity = event.getTargetMS();
            LCSLocationInfo lcsLocationInfo = event.getLCSLocationInfo();
            Boolean gprsNodeIndicator = null;
            MAPExtensionContainer mapExtensionContainer = event.getExtensionContainer();
            GSNAddress vGmlcAddress = event.getVgmlcAddress();
            GSNAddress hGmlcAddress = event.getHGmlcAddress();
            GSNAddress pprAddress = event.getPprAddress();
            GSNAddress additionalVGmlcAddress = event.getAdditionalVGmlcAddress();

            SriForLcsResponseValues sriForLcsResponseValues = new SriForLcsResponseValues();
            MLPResponse.MLPResultType mlpRespResult = null;
            String mlpClientErrorMessage = null;

            // CDR initialization
            GMLCCDRState gmlcCdrState = CDRCreationHelper.mapSriLcsCdrInitializer(aci, this.getCDRInterface(), event);
            // Set timer last
            this.setTimer(aci);

            transaction = mobileCoreNetworkTransactions.getTransactionId(mapDialogLsm.getLocalDialogId());
            if (transaction == null) {
                throw new Exception();
            }
            pslMsisdn = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslMsisdn");
            pslImsi = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslImsi");
            curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
            httpRequestType = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "httpRequestType");
            sriLcsOnly = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriLcsOnly");
            DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (gmlcCdrState.isInitialized()) {
                gmlcCdrState.setCurlUser(curlUser);
                if (dialogStartTime != null && eventTime != null) {
                    Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                    gmlcCdrState.setDialogDuration(dialogDuration);
                }
                if (pslImsi != null) {
                    gmlcCdrState.setImsi(new IMSIImpl(pslImsi));
                } else if (pslMsisdn != null) {
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, pslMsisdn));
                }
            }

            MessageType tcapMessageType = mapDialogLsm.getTCAPMessageType();
            if (tcapMessageType == MessageType.Abort) {
                mobileCoreNetworkTransactions.destroy(transaction);
                mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                mlpClientErrorMessage = "TCAP ABORT on SRILCS";
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, pslMsisdn));
                    this.createCDRRecord(RecordStatus.SRISM_TCAP_DIALOG_ABORT);
                }
                if (mapDialogLsm.getRemoteAddress() != null)
                    if (mapDialogLsm.getRemoteAddress().getGlobalTitle() != null) {
                        nnn = mapDialogLsm.getRemoteAddress().getGlobalTitle().getDigits();
                        if (event != null) {
                            if (event.getLCSLocationInfo() != null) {
                                if (event.getLCSLocationInfo().getAdditionalNumber() != null) {
                                    if (event.getLCSLocationInfo().getGprsNodeIndicator()) {
                                        if (event.getLCSLocationInfo().getAdditionalNumber().getSGSNNumber() != null) {
                                            additionalNumberAddress = event.getLCSLocationInfo().getAdditionalNumber().getSGSNNumber().getAddress();
                                        }
                                    } else {
                                        if (event.getLCSLocationInfo().getAdditionalNumber().getMSCNumber() != null) {
                                            additionalNumberAddress = event.getLCSLocationInfo().getAdditionalNumber().getMSCNumber().getAddress();
                                        }
                                    }
                                }
                            }
                        }
                    }
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "SRILCS", pslMsisdn, pslImsi,
                    null, nnn, additionalNumberAddress, null, null, mlpTriggeredReportingService);
                return;
            }

            if (subscriberIdentity != null) {
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForLCSResponse: received SubscriberIdentity");
                }
                if (subscriberIdentity.getMSISDN() != null) {
                    sriForLcsResponseValues.setMsisdn(subscriberIdentity.getMSISDN());
                    pslMsisdn = subscriberIdentity.getMSISDN().getAddress();
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setMsisdn(sriForLcsResponseValues.getMsisdn());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                                + "CDR state is initialized, MSISDN set: " + pslMsisdn);
                        }
                    }
                }
                if (subscriberIdentity.getIMSI() != null) {
                    sriForLcsResponseValues.setImsi(subscriberIdentity.getIMSI());
                    pslImsi = subscriberIdentity.getIMSI().getData();
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setImsi(sriForLcsResponseValues.getImsi());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                                + "CDR state is initialized, IMSI set: " + pslImsi);
                        }
                    }
                }
            } else {
                this.createCDRRecord(RecordStatus.SRILCS_UNKNOWN_SUBSCRIBER);
            }

            if (lcsLocationInfo != null) {

                mlpRespResult = MLPResponse.MLPResultType.OK;

                if (lcsLocationInfo.getNetworkNodeNumber() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: received NetworkNodeNumber: " + lcsLocationInfo.getNetworkNodeNumber().getAddress());
                    }
                    nnn = lcsLocationInfo.getNetworkNodeNumber().getAddress();
                    sriForLcsResponseValues.setNetworkNodeNumber(lcsLocationInfo.getNetworkNodeNumber());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setNetworkNodeNumber(sriForLcsResponseValues.getNetworkNodeNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                                + "CDR state is initialized, NNN set: " + lcsLocationInfo.getNetworkNodeNumber());
                        }
                    }
                }

                if (lcsLocationInfo.getLMSI() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: received LMSI");
                    }
                    sriForLcsResponseValues.setLmsi(lcsLocationInfo.getLMSI());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setLmsi(sriForLcsResponseValues.getLmsi());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                                + "CDR state is initialized, LMSI set: " + lcsLocationInfo.getLMSI());
                        }
                    }
                }

                if (lcsLocationInfo.getAdditionalNumber() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: received AdditionalNumber");
                    }
                    sriForLcsResponseValues.setAdditionalNumber(lcsLocationInfo.getAdditionalNumber());
                    /*if (event.getLCSLocationInfo().getGprsNodeIndicator()) {
                        if (lcsLocationInfo.getAdditionalNumber().getSGSNNumber() != null) {
                            additionalNumberAddress = remotePslAddress = event.getLCSLocationInfo().getAdditionalNumber().getSGSNNumber().getAddress();
                        }
                    } else {
                        if (event.getLCSLocationInfo().getAdditionalNumber().getMSCNumber() != null) {
                            additionalNumberAddress = remotePslAddress = lcsLocationInfo.getAdditionalNumber().getMSCNumber().getAddress();
                        }
                    }*/
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setAdditionalNumber(sriForLcsResponseValues.getAdditionalNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                                + "CDR state is initialized, Additional Number set: " + lcsLocationInfo.getAdditionalNumber());
                        }
                    }
                }

                if (lcsLocationInfo.getSupportedLCSCapabilitySets() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: received SupportedLCSCapabilitySets: " + lcsLocationInfo.getSupportedLCSCapabilitySets());
                    }
                    sriForLcsResponseValues.setSupportedLCSCapabilitySets(lcsLocationInfo.getSupportedLCSCapabilitySets());
                }

                if (lcsLocationInfo.getAdditionalLCSCapabilitySets() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: received AdditionalLCSCapabilitySets: " + lcsLocationInfo.getAdditionalLCSCapabilitySets());
                    }
                    sriForLcsResponseValues.setAddSupportedLCSCapabilitySets(lcsLocationInfo.getAdditionalLCSCapabilitySets());
                }

                gprsNodeIndicator = lcsLocationInfo.getGprsNodeIndicator();
                if (gprsNodeIndicator != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse, GPRS Node indicator: " + gprsNodeIndicator);
                    }
                    sriForLcsResponseValues.setGprsNodeIndicator(gprsNodeIndicator.booleanValue());
                    if (gmlcCdrState.isInitialized()) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: CDR state is initialized, GPRS Node indicator set to"
                                + sriForLcsResponseValues.isGprsNodeIndicator());
                        }
                        gmlcCdrState.setGprsNodeIndicator(sriForLcsResponseValues.isGprsNodeIndicator());
                    }
                }

                if (lcsLocationInfo.getMmeName() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: received MMEName");
                    }
                    sriForLcsResponseValues.setMmeName(lcsLocationInfo.getMmeName());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setMmeName(sriForLcsResponseValues.getMmeName());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                                + "CDR state is initialized, MME Name set: " + lcsLocationInfo.getMmeName());
                        }
                    }
                }

                if (lcsLocationInfo.getAaaServerName() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: received AAAServerName");
                    }
                    sriForLcsResponseValues.setAaaServerName(lcsLocationInfo.getAaaServerName());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setAaaServerName(sriForLcsResponseValues.getAaaServerName());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                                + "CDR state is initialized, AAA Server Name set: " + lcsLocationInfo.getAaaServerName());
                        }
                    }
                }
            } else {
                this.createCDRRecord(RecordStatus.SRILCS_ABSENT_SUBSCRIBER);
            }

            if (mapExtensionContainer != null) {
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForLCSResponse: received MAPExtensionContainer");
                }
            }

            if (vGmlcAddress != null) {
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForLCSResponse: received VGMLCAddress");
                }
                sriForLcsResponseValues.setvGmlcAddress(vGmlcAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setvGmlcAddress(sriForLcsResponseValues.getvGmlcAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                            + "CDR state is initialized, V-GMLC Address set: " + vGmlcAddress);
                    }
                }
            }

            if (hGmlcAddress != null) {
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForLCSResponse: received HGMLCAddress");
                }
                sriForLcsResponseValues.sethGmlcAddress(hGmlcAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.sethGmlcAddress(sriForLcsResponseValues.gethGmlcAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                            + "CDR state is initialized, H-GMLC Address set: " + hGmlcAddress);
                    }
                }
            }

            if (pprAddress != null) {
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForLCSResponse: received HGMLCAddress, decoding Data, GSNAddressAddressType and GSNAddressAddressData parameters");
                }
                sriForLcsResponseValues.setPprAddress(pprAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setPprAddress(sriForLcsResponseValues.getPprAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                            + "CDR state is initialized, PPR Address set: " + pprAddress);
                    }
                }
            }

            if (additionalVGmlcAddress != null) {
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSendRoutingInfoForLCSResponse: received AdditionalVGmlcAddress, decoding Data, GSNAddressAddressType and GSNAddressAddressData parameters");
                }
                sriForLcsResponseValues.setAddVGmlcAddress(additionalVGmlcAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setvGmlcAddress(sriForLcsResponseValues.getAddVGmlcAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSendRoutingInfoForLCSResponse: "
                            + "CDR state is initialized, Additional V-GMLC Address set: " + additionalVGmlcAddress);
                    }
                }
            }

            this.setSendRoutingInfoForLCSResponse(event);

            mobileCoreNetworkTransactions.setValue(transaction, "sriForLcsResponseValues", sriForLcsResponseValues);

            if (this.getSendRoutingInfoForLCSResponse() != null && transaction != null) {

                if (sriLcsOnly) {
                    // handle retrieval of SRILCS response when SRILCS is the operation requested
                    if (gmlcCdrState.isInitialized()) {
                        if (subscriberIdentity != null)
                            this.createCDRRecord(RecordStatus.SRILCS_SUCCESS);
                        else
                            this.createCDRRecord(RecordStatus.SRILCS_ERROR);
                    }
                    handleSriResponseValue(mlpRespResult, null, null, sriForLcsResponseValues, "SRILCS", pslMsisdn, pslImsi, mlpClientErrorMessage);
                    // destroy transactions if exist, detach and return
                    if (transaction != null)
                        mobileCoreNetworkTransactions.Instance().destroy(transaction);
                    detachFromMAPDialogLsm(aci);
                    return;
                }

                SriForLcsResponseValues sriForLcsResponse = sriForLcsResponseValues;

                ISDNAddressString mlcNumber = new ISDNAddressStringImpl(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN,
                    this.getGmlcSccpAddress().getGlobalTitle().getDigits());

                ISDNAddressString msisdnForPsl = null;
                if (sriForLcsResponseValues.getMsisdn() != null) {
                    msisdnForPsl = sriForLcsResponseValues.getMsisdn();
                } else {
                    if (pslMsisdn != null)
                        msisdnForPsl = new ISDNAddressStringImpl(AddressNature.international_number,
                            org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, pslMsisdn);
                }

                IMSI imsiForPsl = null;
                if (sriForLcsResponseValues.getImsi() != null) {
                    imsiForPsl = sriForLcsResponseValues.getImsi();
                } else {
                    if (pslImsi != null)
                        imsiForPsl = new IMSIImpl(pslImsi);
                }

                // LocationType for PSL composed from HTTP request values
                LocationType locationType = null;
                LocationEstimateType locationEstimateType = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslLocationEstimateType") != null)
                    locationEstimateType = LocationEstimateType.valueOf((String) mobileCoreNetworkTransactions.getValue(transaction, "pslLocationEstimateType"));

                String requestedDeferredLocationEventType = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslDeferredLocationEventType") != null)
                    requestedDeferredLocationEventType = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslDeferredLocationEventType");
                DeferredLocationEventType deferredLocationEventType = null;
                if (requestedDeferredLocationEventType != null) {
                    switch (requestedDeferredLocationEventType) {
                        case "available":
                            deferredLocationEventType = new DeferredLocationEventTypeImpl(true, false, false, false, false);
                            break;
                        case "entering":
                            deferredLocationEventType = new DeferredLocationEventTypeImpl(false, true, false, false, false);
                            break;
                        case "leaving":
                            deferredLocationEventType = new DeferredLocationEventTypeImpl(false, false, true, false, false);
                            break;
                        case "inside":
                            deferredLocationEventType = new DeferredLocationEventTypeImpl(false, false, false, true, false);
                            break;
                        case "periodicLDR":
                            deferredLocationEventType = new DeferredLocationEventTypeImpl(false, false, false, false, true);
                            break;
                        default:
                            deferredLocationEventType = null;
                            break;
                    }
                }

                locationType = new LocationTypeImpl(locationEstimateType, deferredLocationEventType);

                // LCSClientID
                int lcsClientTypeFromCurl = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsClientType");
                org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientType lcsClientType =
                    org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientType.getLCSClientType(lcsClientTypeFromCurl);
                LCSClientExternalID lcsClientExternalID = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslClientExternalID") != null) {
                    String lcsClientExternalIdFromCurl = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslClientExternalID");
                    ISDNAddressString lcsClExtIdIsdnAddress = new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, lcsClientExternalIdFromCurl);
                    lcsClientExternalID = new LCSClientExternalIDImpl(lcsClExtIdIsdnAddress, null);
                }
                LCSClientInternalID lcsClientInternalID = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslClientInternalID") != null) {
                    Integer lcsClientInternalIdFromCurl = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslClientInternalID");
                    lcsClientInternalID = getLCSClientInternalID(lcsClientInternalIdFromCurl);
                }
                LCSClientName lcsClientName = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslClientName") != null) {
                    String lcsClientNameFromCurl = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslClientName");
                    if (mobileCoreNetworkTransactions.getValue(transaction, "pslClientFormatIndicator") != null) {
                        Integer lcsClientFormatIndFromCurl = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslClientFormatIndicator");
                        int cbsDataCodingSchemeCode = 15;
                        CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(cbsDataCodingSchemeCode);
                        Charset gsm8Charset = Charset.defaultCharset();
                        USSDString ussdString = new USSDStringImpl(lcsClientNameFromCurl, cbsDataCodingScheme, gsm8Charset);
                        org.restcomm.protocols.ss7.map.api.service.lsm.LCSFormatIndicator lcsFormatIndicator;
                        lcsFormatIndicator = org.restcomm.protocols.ss7.map.api.service.lsm.LCSFormatIndicator.getLCSFormatIndicator(lcsClientFormatIndFromCurl);
                        lcsClientName = new LCSClientNameImpl(cbsDataCodingScheme, ussdString, lcsFormatIndicator);
                    }
                }
                // lcsClientDialedByMS (O*) : This component shall be present if the MT-LR is associated to either CS call or PS session.
                AddressString lcsClientDialedByMS = null;  // set to null
                APN lcsAPN = null; // set to null (Otherwise if the MT-LR is associated with the PS session, the APN-NI is used)
                LCSRequestorID lcsRequestorID = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslRequestorIdString") != null) {
                    String lcsRequestorIdStringFromCurl = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslRequestorIdString");
                    if (mobileCoreNetworkTransactions.getValue(transaction, "pslRequestorFormatIndicator") != null) {
                        Integer lcsRequestorFormatIndFromCurl = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslRequestorFormatIndicator");
                        int cbsDataCodingSchemeCode = 15;
                        CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(cbsDataCodingSchemeCode);
                        Charset gsm8Charset = Charset.defaultCharset();
                        USSDString ussdString = new USSDStringImpl(lcsRequestorIdStringFromCurl, cbsDataCodingScheme, gsm8Charset);
                        org.restcomm.protocols.ss7.map.api.service.lsm.LCSFormatIndicator lcsFormatIndicator = null;
                        lcsFormatIndicator = org.restcomm.protocols.ss7.map.api.service.lsm.LCSFormatIndicator.getLCSFormatIndicator(lcsRequestorFormatIndFromCurl);
                        lcsRequestorID = new LCSRequestorIDImpl(cbsDataCodingScheme, ussdString, lcsFormatIndicator);
                    }
                }
                LCSClientID lcsClientID = new LCSClientIDImpl(lcsClientType, lcsClientExternalID, lcsClientInternalID, lcsClientName, lcsClientDialedByMS, lcsAPN, lcsRequestorID);

                // Privacy Override
                // This parameter indicates if MS privacy is overridden by the LCS client when the GMLC and VMSC/SGSN for an MTLR are in the same country.
                boolean privacyOverride = false;

                // IMEI
                IMEI imei;
                String pslImei = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslImei");
                if (pslImei != null)
                    imei = mapProvider.getMAPParameterFactory().createIMEI(pslImei);
                else
                    imei = null;

                // LCSPriority for PSL composed from HTTP request values
                LCSPriority lcsPriority = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslLcsPriority") != null)
                    lcsPriority = LCSPriority.valueOf((String) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsPriority"));

                // LCSQoS for PSL composed from HTTP request values
                LCSQoS lcsQoS = null;
                Integer horizontalAccuracy = null, verticalAccuracy = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslLcsHorizontalAccuracy") != null)
                    horizontalAccuracy = Integer.valueOf(String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslLcsHorizontalAccuracy")));
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslLcsVerticalAccuracy") != null)
                    verticalAccuracy = Integer.valueOf(String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslLcsVerticalAccuracy")));
                Boolean verticalCoordinateRequest = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslVerticalCoordinateRequest") != null)
                    verticalCoordinateRequest = Boolean.parseBoolean(String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslVerticalCoordinateRequest")));
                ResponseTime responseTime = null;
                ResponseTimeCategory responseTimeCategory = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslResponseTimeCategory") != null)
                    responseTimeCategory = ResponseTimeCategory.valueOf(String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslResponseTimeCategory")));
                if (responseTimeCategory != null)
                    responseTime = new ResponseTimeImpl(responseTimeCategory);
                MAPExtensionContainer pslMapExtensionContainer = null;
                if (horizontalAccuracy != null && verticalAccuracy != null && verticalCoordinateRequest != null && responseTime != null)
                    lcsQoS = new LCSQoSImpl(horizontalAccuracy, verticalAccuracy, verticalCoordinateRequest, responseTime, pslMapExtensionContainer);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setLcsQoS(lcsQoS);
                }

                // SupportedGADShapes hardcoded to true for all shapes for now
                boolean ellipsoidPoint = true;
                boolean ellipsoidPointWithUncertaintyCircle = true;
                boolean ellipsoidPointWithUncertaintyEllipse = true;
                boolean polygon = true;
                boolean ellipsoidPointWithAltitude = true;
                boolean ellipsoidPointWithAltitudeAndUncertaintyEllipsoid = true;
                boolean ellipsoidArc = true;
                SupportedGADShapes supportedGADShapes = new SupportedGADShapesImpl(ellipsoidPoint, ellipsoidPointWithUncertaintyCircle,
                    ellipsoidPointWithUncertaintyEllipse, polygon, ellipsoidPointWithAltitude, ellipsoidPointWithAltitudeAndUncertaintyEllipsoid, ellipsoidArc);

                // LCSCodeword
                LCSCodeword lcsCodeword = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslLcsCodeword") != null) {
                    String lcsCodeW = null;
                    if (mobileCoreNetworkTransactions.getValue(transaction, "pslLcsCodeword") != null)
                        lcsCodeW = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsCodeword");
                    int cbsDataCodingSchemeCode = 15;
                    CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(cbsDataCodingSchemeCode);
                    Charset gsm8Charset = Charset.defaultCharset();
                    USSDString ussdString = new USSDStringImpl(lcsCodeW, cbsDataCodingScheme, gsm8Charset);
                    lcsCodeword = new LCSCodewordImpl(cbsDataCodingScheme, ussdString);
                }

                // LCSPrivacyCheck hardcoded to nul for now
                LCSPrivacyCheck lcsPrivacyCheck = null;

                // AreaEventInfo for PSL composed from HTTP request values
                ArrayList<Area> areaList = new ArrayList<>();
                AreaType areaType = null;
                AreaIdentification areaIdentification = null;
                Area area = null;
                AreaDefinition areaDefinition = null;
                OccurrenceInfo occurrenceInfo = null;
                Integer intervalTime = null;
                AreaEventInfo areaEventInfo = null;
                String[] areaIdArray;
                String areaIdStr;
                Integer[] areaIdParams;
                PlmnIdImpl plmnId;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslAreaId") != null) {
                    areaIdStr = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslAreaId");
                    areaIdArray = areaIdStr.split("-");
                    areaIdParams = setAreaIdParams(areaIdArray, (String) mobileCoreNetworkTransactions.getValue(transaction, "pslAreaType"));
                    if (mobileCoreNetworkTransactions.getValue(transaction, "pslAreaType") != null) {
                        areaType = AreaType.valueOf((String) mobileCoreNetworkTransactions.getValue(transaction, "pslAreaType"));
                        if (areaType.equals(AreaType.countryCode)) {
                            areaIdentification = new AreaIdentificationImpl(parseTBCD(areaIdArray[0]));
                        } else if (areaType.equals(AreaType.plmnId)) {
                            plmnId = new PlmnIdImpl(Integer.valueOf(areaIdParams[0]), Integer.valueOf(areaIdParams[1]));
                            areaIdentification = new AreaIdentificationImpl(plmnId.getData());
                        } else if (areaType.equals(AreaType.locationAreaId)) {
                            areaIdentification = new AreaIdentificationImpl(AreaType.locationAreaId, areaIdParams[0], areaIdParams[1], areaIdParams[2], 0);
                        } else if (areaType.equals(AreaType.routingAreaId)) {
                            areaIdentification = new AreaIdentificationImpl(AreaType.routingAreaId, areaIdParams[0], areaIdParams[1], areaIdParams[2], areaIdParams[3]);
                        } else if (areaType.equals(AreaType.cellGlobalId)) {
                            areaIdentification = new AreaIdentificationImpl(AreaType.cellGlobalId, areaIdParams[0], areaIdParams[1], areaIdParams[2], areaIdParams[3]);
                        } else if (areaType.equals(AreaType.utranCellId)) {
                            areaIdentification = new AreaIdentificationImpl(AreaType.utranCellId, areaIdParams[0], areaIdParams[1], -1, areaIdParams[3]);
                        }
                    }
                }
                if (areaType != null && areaIdentification != null)
                    area = new AreaImpl(areaType, areaIdentification);
                if (area != null)
                    areaList.add(area);
                if (areaList != null)
                    areaDefinition = new AreaDefinitionImpl(areaList);
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslOccurrenceInfo") != null)
                    occurrenceInfo = OccurrenceInfo.valueOf((String) mobileCoreNetworkTransactions.getValue(transaction, "pslOccurrenceInfo"));
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslIntervalTime") != null)
                    intervalTime = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslIntervalTime");
                if (areaDefinition != null && occurrenceInfo != null)
                    areaEventInfo = new AreaEventInfoImpl(areaDefinition, occurrenceInfo, intervalTime);

                // PeriodicLDRInfo for PSL composed from HTTP request values
                PeriodicLDRInfo periodicLDRInfo = null;
                if (deferredLocationEventType != null) {
                    if (deferredLocationEventType.getPeriodicLDR()) {
                        if (mobileCoreNetworkTransactions.getValue(transaction, "pslReportingAmount") != null &&
                            mobileCoreNetworkTransactions.getValue(transaction, "pslReportingInterval") != null)
                            periodicLDRInfo = new PeriodicLDRInfoImpl((Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslReportingAmount"),
                                (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslReportingInterval"));
                    }
                }

                // moLrShortCircuitIndicator hardcoded to false for now
                boolean moLrShortCircuitIndicator = false;

                // ReportingPLMNList
                ReportingPLMNList reportingPLMNList = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslPLMNIdList") != null) {
                    String vPlmnId = (String) mobileCoreNetworkTransactions.getValue(transaction, "pslPLMNIdList");
                    String[] vPlmnIdArray = vPlmnId.split("-");
                    PlmnId visitedPlmnId = new PlmnIdImpl(Integer.valueOf(vPlmnIdArray[0]), Integer.valueOf(vPlmnIdArray[1]));
                    Integer ranTechInd = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslVisitedPLMNIdRAN");
                    RANTechnology ranTechnology = RANTechnology.gsm;
                    if (ranTechInd != null) {
                        if (ranTechInd == 1)
                            ranTechnology = RANTechnology.umts;
                    }
                    Integer ranPeriodicLocationSupportInd = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslPeriodicLocationSupportIndicator");
                    boolean ranPeriodicLocationSupport = false;
                    if (ranPeriodicLocationSupportInd != null) {
                        if (ranPeriodicLocationSupportInd == 1)
                            ranPeriodicLocationSupport = true;
                    }
                    ReportingPLMN reportingPLMN = new ReportingPLMNImpl(visitedPlmnId, ranTechnology, ranPeriodicLocationSupport);
                    ArrayList<ReportingPLMN> plmnList = new ArrayList<>();
                    plmnList.add(reportingPLMN);
                    Integer plmnListPrioritizedInd = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslPrioritizedListIndicator");
                    boolean plmnListPrioritized = false;
                    if (plmnListPrioritizedInd != null) {
                        if (plmnListPrioritizedInd == 1)
                            plmnListPrioritized = true;
                    }
                    reportingPLMNList = new ReportingPLMNListImpl(plmnListPrioritized, plmnList);
                }

                Integer pslLcsServiceTypeID = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslLcsServiceTypeID") != null)
                    pslLcsServiceTypeID = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsServiceTypeID");

                Integer pslLcsReferenceNumber = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber") != null)
                    pslLcsReferenceNumber = referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");

                String slrCallbackUrl = null;
                if (mobileCoreNetworkTransactions.getValue(transaction, "slrCallbackUrl") != null)
                    slrCallbackUrl = (String) mobileCoreNetworkTransactions.getValue(transaction, "slrCallbackUrl");

                Integer pslReferenceNumber = null;
                if (deferredLocationEventType != null) {
                    httpSubscriberLocationReport = getHttpSubscriberLocationReport();
                    boolean mlp = false;
                    if (httpRequestType != null) {
                        if (httpRequestType.equalsIgnoreCase("MLP"))
                            mlp = true;
                    }
                    pslReferenceNumber = httpSubscriberLocationReport.Register(pslLcsReferenceNumber, slrCallbackUrl, null, mlp, curlUser);
                    if (this.logger.isFineEnabled()) {
                        logger.fine(String.format("Sending MAP PSL request with LCS-ReferenceNumber %d from HTTP request clientReferenceNumber# %d and callback URL: '%s'",
                            pslReferenceNumber, pslLcsReferenceNumber, slrCallbackUrl));
                    }
                    httpSubscriberLocationReport.closeMongo();
                }

                AddressString originAddressString, destinationAddressString;
                originAddressString = destinationAddressString = null;

                MAPDialogLsm mapDialogLsmPsl = this.mapProvider.getMAPServiceLsm().createNewDialog(
                    this.getMAPPslSlrApplicationContext(), this.getGmlcSccpAddress(), originAddressString,
                    getMscSccpAddress(lcsLocationInfo.getNetworkNodeNumber().getAddress()), destinationAddressString);

                if (lcsLocationInfo.getAdditionalNumber() != null) {
                        ISDNAddressString additionalNumber = null;
                        if (lcsLocationInfo.getAdditionalNumber().getSGSNNumber() != null) {
                            additionalNumber = lcsLocationInfo.getAdditionalNumber().getSGSNNumber();
                            mapDialogLsmPsl = this.mapProvider.getMAPServiceLsm().createNewDialog(
                                this.getMAPPslSlrApplicationContext(), this.getGmlcSccpAddress(), originAddressString,
                                getSgsnSccpAddress(additionalNumber.getAddress()), destinationAddressString);
                        } else if (lcsLocationInfo.getAdditionalNumber().getMSCNumber() != null) {
                            additionalNumber = lcsLocationInfo.getAdditionalNumber().getMSCNumber();
                            mapDialogLsmPsl = this.mapProvider.getMAPServiceLsm().createNewDialog(
                                this.getMAPPslSlrApplicationContext(), this.getGmlcSccpAddress(), originAddressString,
                                getMscSccpAddress(additionalNumber.getAddress()), destinationAddressString);
                        }
                    remotePslAddress = additionalNumber.getAddress();
                }

                // We are selecting the IMSI over the MSISDN if present for MAP PSL, although as per 3GPP TS 29.002 v15.5.0 both could be included:
                // The IMSI is provided to identify the target MS. At least one of the IMSI or MSISDN is mandatory.
                // The MSISDN is provided to identify the target MS. At least one of the IMSI or MSISDN is mandatory.
                if (imsiForPsl != null) {
                    pslImsi = imsiForPsl.getData();
                    msisdnForPsl = null;
                }

                // 3GPP TS 29.002 v15.5.0
                // H-GMLC address
                // The parameter shall be included if a deferred MT-LR procedure is performed for a UE available event, an area event or a periodic positioning event.
                if (deferredLocationEventType == null)
                    hGmlcAddress = null;

                mapDialogLsmPsl.addProvideSubscriberLocationRequest(locationType, mlcNumber, lcsClientID, privacyOverride,
                    imsiForPsl, msisdnForPsl, sriForLcsResponseValues.getLmsi(), imei, lcsPriority, lcsQoS, mapExtensionContainer,
                    supportedGADShapes, pslReferenceNumber, pslLcsServiceTypeID, lcsCodeword, lcsPrivacyCheck,
                    areaEventInfo, hGmlcAddress, moLrShortCircuitIndicator, periodicLDRInfo, reportingPLMNList);

                mobileCoreNetworkTransactions.setValue(transaction, "pslReferenceNumber", pslReferenceNumber);
                mobileCoreNetworkTransactions.setValue(transaction, "pslNNN", nnn);
                mobileCoreNetworkTransactions.setValue(transaction, "pslRemoteAddress", remotePslAddress);
                mobileCoreNetworkTransactions.setValue(transaction, "pslImsi", pslImsi);
                mobileCoreNetworkTransactions.setValue(transaction, "curlUser", curlUser);
                mobileCoreNetworkTransactions.setValue(transaction, "mlpTriggeredReportingService", mlpTriggeredReportingService);
                mobileCoreNetworkTransactions.setValue(transaction, "transactionStart", dialogStartTime);
                mobileCoreNetworkTransactions.unsetDialog(mapDialogLsm.getLocalDialogId());
                mobileCoreNetworkTransactions.setDialog(transaction, mapDialogLsmPsl.getLocalDialogId());

                // Keep ACI in across MAP dialog for PSL
                ActivityContextInterface pslDialogACI = this.mapAcif.getActivityContextInterface(mapDialogLsmPsl);
                pslDialogACI.attach(this.sbbContext.getSbbLocalObject());

                // ProvideSubscriberLocationRequest is now composed by values taken from SRILCS response and HTTP request
                // Send PSL
                mapDialogLsmPsl.send();

                // set new timer for the PSL request/response cycle
                TimerID timerID = timerFacility.setTimer(pslDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
                mobileCoreNetworkTransactions.setValue(transaction, "timerID", timerID);

            } else {
                // SRILCS Error CDR creation
                if (mapErrorMessage != null) {
                    if (gmlcCdrState.isInitialized()) {
                        if (transaction != null) {
                            referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                            mobileCoreNetworkTransactions.Instance().destroy(transaction);
                        }
                        mlpClientErrorMessage = handleRecordAndLocationReportOnMapError(mapErrorMessage, mlpRespResult, pslMsisdn, pslImsi,
                            "SRILCS", referenceNumber, nnn, additionalNumberAddress, gmlcCdrState, mlpTriggeredReportingService);
                    }
                }
            }
        } catch (MAPException me) {
            logger.severe(String.format("MAPException while trying to process SendRoutingInfoForLCSResponse=%s", event), me);
            logger.severe("MAP error message when processing onSendRoutingInfoForLCSResponse: " + mapErrorMessage);
            this.createCDRRecord(RecordStatus.SRILCS_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP SRILCS response: " + me.getMessage(),
                "SRILCS", pslMsisdn, pslImsi, referenceNumber, nnn, additionalNumberAddress, null, null, mlpTriggeredReportingService);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process SendRoutingInfoForLCSResponse=%s", event), e);
            this.createCDRRecord(RecordStatus.SRILCS_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP SRILCS response: " + e.getMessage(),
                "SRILCS", pslMsisdn, pslImsi, referenceNumber, nnn, additionalNumberAddress, null, null, mlpTriggeredReportingService);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } finally {
            detachFromMAPDialogLsm(aci);
        }
    }

    /**
     * Location Service Management (LSM) services
     * MAP_PROVIDE_SUBSCRIBER_LOCATION (PSL) Events
     */

    /**
     * MAP PSL Request Event
     */
    public void onProvideSubscriberLocationRequest(ProvideSubscriberLocationRequest event, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onProvideSubscriberLocationRequest = " + event);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onProvideSubscriberLocationRequest=%s", event), e);
        }
    }

    /**
     * MAP PSL Response Event
     */
    public void onProvideSubscriberLocationResponse(ProvideSubscriberLocationResponse event, ActivityContextInterface aci) {

        MAPErrorMessage mapErrorMessage = this.getErrorResponse();
        this.setProvideSubscriberLocationResponse(event);
        Long transaction = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        String pslMsisdn = null, pslImsi = null, pslNnn = null, pslRemoteAddress = null, curlUser;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onProvideSubscriberLocationResponse = " + event);
            }

            MAPDialogLsm mapDialogLsm = event.getMAPDialog();
            ExtGeographicalInformation extGeographicalInformation = event.getLocationEstimate();
            Integer ageOfLocationEstimate = event.getAgeOfLocationEstimate();
            PositioningDataInformation geranPositioningData = event.getGeranPositioningData();
            UtranPositioningDataInfo utranPositioningDataInfo = event.getUtranPositioningData();
            AddGeographicalInformation addGeographicalInformation = event.getAdditionalLocationEstimate();
            MAPExtensionContainer mapExtensionContainer = event.getExtensionContainer();
            Boolean deferredMTLRResponseIndicator = event.getDeferredMTLRResponseIndicator();
            CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = event.getCellIdOrSai();
            Boolean saiPresent = event.getSaiPresent();
            AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = event.getAccuracyFulfilmentIndicator();
            VelocityEstimate velocityEstimate = event.getVelocityEstimate();
            Boolean moLrShortCircuitIndicator = event.getMoLrShortCircuitIndicator();
            GeranGANSSpositioningData geranGANSSpositioningData = event.getGeranGANSSpositioningData();
            UtranGANSSpositioningData utranGANSSpositioningData = event.getUtranGANSSpositioningData();
            ServingNodeAddress servingNodeAddress = event.getTargetServingNodeForHandover();

            PslResponseValues pslResponseValues = new PslResponseValues();
            MLPResponse.MLPResultType mlpRespResult = null;
            String mlpClientErrorMessage = null;

            // CDR initialization
            GMLCCDRState gmlcCdrState = CDRCreationHelper.mapPSLCdrInitializer(aci, this.getCDRInterface(), event);
            // Set timer last
            this.setTimer(aci);

            transaction = mobileCoreNetworkTransactions.getTransactionId(mapDialogLsm.getLocalDialogId());
            if (transaction == null) {
                throw new Exception();
            }
            pslMsisdn = String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslMsisdn"));
            pslImsi = String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslImsi"));
            pslNnn = String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslNNN"));
            pslRemoteAddress = String.valueOf(mobileCoreNetworkTransactions.getValue(transaction, "pslRemoteAddress"));
            curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
            DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (gmlcCdrState.isInitialized()) {
                gmlcCdrState.setCurlUser(curlUser);
                if (dialogStartTime != null && eventTime != null) {
                    Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                    gmlcCdrState.setDialogDuration(dialogDuration);
                }
                if (pslImsi != null) {
                    gmlcCdrState.setImsi(new IMSIImpl(pslImsi));
                } else if (pslMsisdn != null) {
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, pslMsisdn));
                }
            }

            MessageType tcapMessageType = mapDialogLsm.getTCAPMessageType();
            if (tcapMessageType == MessageType.Abort) {
                mobileCoreNetworkTransactions.destroy(transaction);
                mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                mlpClientErrorMessage = "TCAP ABORT on PSL";
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, pslMsisdn));
                    IMSI imsi = new IMSIImpl(pslImsi);
                    gmlcCdrState.setImsi(imsi);
                    this.createCDRRecord(RecordStatus.SRISM_TCAP_DIALOG_ABORT);
                }
                if (mapDialogLsm.getRemoteAddress() != null)
                    if (mapDialogLsm.getRemoteAddress().getGlobalTitle() != null) {
                        pslNnn = mapDialogLsm.getRemoteAddress().getGlobalTitle().getDigits();

                    }
                this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, "PSL", pslMsisdn, pslImsi, null, pslNnn, pslRemoteAddress,
                    null, null, mlpTriggeredReportingService);
                return;
            }

            if (extGeographicalInformation != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: "
                        + "received LocationEstimate, decoding ExtGeographicalInformation parameters");
                }
                if (extGeographicalInformation.getTypeOfShape() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "LocationEstimate type of shape: " + extGeographicalInformation.getTypeOfShape());
                    }
                    if (extGeographicalInformation.getTypeOfShape() != TypeOfShape.Polygon) {
                        pslResponseValues.setLocationEstimate(extGeographicalInformation);
                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setLocationEstimate(pslResponseValues.getLocationEstimate());
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "CDR state is initialized, Location Estimate set");
                            }
                        }
                        if (extGeographicalInformation.getLatitude() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate latitude: " + extGeographicalInformation.getLatitude());
                            }
                        }
                        if (extGeographicalInformation.getLongitude() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate longitude: " + extGeographicalInformation.getLongitude());
                            }
                        }
                        if (extGeographicalInformation.getUncertainty() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate uncertainty: " + extGeographicalInformation.getUncertainty());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintySemiMajorAxis() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate uncertainty Semi Major Axis: " + extGeographicalInformation.getUncertaintySemiMajorAxis());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintySemiMinorAxis() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate uncertainty Semi Minor Axis: " + extGeographicalInformation.getUncertaintySemiMinorAxis());
                            }
                        }
                        if (extGeographicalInformation.getAngleOfMajorAxis() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate angle of major axis: " + extGeographicalInformation.getAngleOfMajorAxis());
                            }
                        }
                        if (extGeographicalInformation.getConfidence() <= Integer.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate confidence: " + extGeographicalInformation.getConfidence());
                            }
                        }
                        if (extGeographicalInformation.getAltitude() <= Integer.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate altitude: " + extGeographicalInformation.getAltitude());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintyAltitude() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate altitude uncertainty: " + extGeographicalInformation.getUncertaintyAltitude());
                            }
                        }
                        if (extGeographicalInformation.getInnerRadius() <= Integer.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate inner radius: " + extGeographicalInformation.getInnerRadius());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintyRadius() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate radius uncertainty: " + extGeographicalInformation.getUncertaintyRadius());
                            }
                        }
                        if (extGeographicalInformation.getOffsetAngle() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate offset angle: " + extGeographicalInformation.getOffsetAngle());
                            }
                        }
                        if (extGeographicalInformation.getIncludedAngle() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "LocationEstimate include angle: " + extGeographicalInformation.getIncludedAngle());
                            }
                        }
                    } else {
                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setTypeOfShape(extGeographicalInformation.getTypeOfShape());
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                    + "CDR state is initialized, Location Estimate Type of Shape set (Polygon)");
                            }
                        }
                    }
                }
            }

            if (geranPositioningData != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received GeranPositioningDataInformation");
                }
                pslResponseValues.setGeranPositioningDataInformation(geranPositioningData);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setGeranPositioningDataInformation(pslResponseValues.getGeranPositioningDataInformation());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, GERAN Positioning Data Info set: " + geranPositioningData);
                    }
                }
            }

            if (utranPositioningDataInfo != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received UtranPositioningDataInformation");
                }
                pslResponseValues.setUtranPositioningDataInfo(utranPositioningDataInfo);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setUtranPositioningDataInfo(pslResponseValues.getUtranPositioningDataInfo());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, UTRAN Positioning Data Info set: " + utranPositioningDataInfo);
                    }
                }
            }

            if (ageOfLocationEstimate != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received AgeOfLocationInformation parameter");
                }
                pslResponseValues.setAgeOfLocationEstimate(ageOfLocationEstimate);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setAgeOfLocationEstimate(pslResponseValues.getAgeOfLocationEstimate());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, Age of Location Estimate set: " + ageOfLocationEstimate);
                    }
                }
            }

            if (addGeographicalInformation != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received AdditionalLocationEstimate");
                }
                pslResponseValues.setAdditionalLocationEstimate(addGeographicalInformation);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setAdditionalLocationEstimate(pslResponseValues.getAdditionalLocationEstimate());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: CDR state is initialized, Additional Location Estimate set");
                    }
                }
                if (addGeographicalInformation.getTypeOfShape() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate type of shape: " + addGeographicalInformation.getTypeOfShape());
                    }
                }
                if (addGeographicalInformation.getLatitude() <= Double.MAX_VALUE) {
                    mlpRespResult = MLPResponse.MLPResultType.OK;
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate latitude: " + addGeographicalInformation.getLatitude());
                    }
                }
                if (addGeographicalInformation.getLongitude() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate longitude: " + addGeographicalInformation.getLongitude());
                    }
                }
                if (addGeographicalInformation.getUncertainty() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate uncertainty: " + addGeographicalInformation.getUncertainty());
                    }
                }
                if (addGeographicalInformation.getUncertaintySemiMajorAxis() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate uncertainty Semi Major Axis: " + addGeographicalInformation.getUncertaintySemiMajorAxis());
                    }
                }
                if (addGeographicalInformation.getUncertaintySemiMinorAxis() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate uncertainty Semi Minor Axis: " + addGeographicalInformation.getUncertaintySemiMinorAxis());
                    }
                }
                if (addGeographicalInformation.getAngleOfMajorAxis() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate angle of Major Axis: " + addGeographicalInformation.getAngleOfMajorAxis());
                    }
                }
                if (addGeographicalInformation.getConfidence() <= Integer.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate confidence: " + addGeographicalInformation.getConfidence());
                    }
                }
                if (addGeographicalInformation.getAltitude() <= Integer.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate altitude: " + addGeographicalInformation.getAltitude());
                    }
                }
                if (addGeographicalInformation.getUncertaintyAltitude() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate altitude uncertainty: " + addGeographicalInformation.getUncertaintyAltitude());
                    }
                }
                if (addGeographicalInformation.getInnerRadius() <= Integer.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate inner radius: " + addGeographicalInformation.getInnerRadius());
                    }
                }
                if (addGeographicalInformation.getUncertaintyRadius() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate radius uncertainty: " + addGeographicalInformation.getUncertaintyRadius());
                    }
                }
                if (addGeographicalInformation.getOffsetAngle() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate offset angle: " + addGeographicalInformation.getOffsetAngle());
                    }
                }
                if (addGeographicalInformation.getIncludedAngle() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "AdditionalLocationEstimate include angle: " + addGeographicalInformation.getIncludedAngle());
                    }
                }
            }

            if (mapExtensionContainer != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received MAPExtensionContainer");
                }
            }

            if (deferredMTLRResponseIndicator != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                pslResponseValues.setDeferredMTLRResponseIndicator(deferredMTLRResponseIndicator);
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received Deferred MT-LR ResponseIndicator, set to: " + deferredMTLRResponseIndicator);
                }
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setCellGlobalIdOrServiceAreaIdOrLAI(pslResponseValues.getCellGlobalIdOrServiceAreaIdOrLAI());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: CDR state is initialized, setting Deferred MT-LR Response Indicator ");
                    }
                    gmlcCdrState.setDeferredMTLRResponseIndicator(deferredMTLRResponseIndicator);
                }
            }

            if (cellGlobalIdOrServiceAreaIdOrLAI != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received CellGlobalIdOrServiceAreaIdOrLAI");
                }
                if (cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                    pslResponseValues.setCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdOrLAI);
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setCellGlobalIdOrServiceAreaIdOrLAI(pslResponseValues.getCellGlobalIdOrServiceAreaIdOrLAI());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                + "CDR state is initialized, CGI or SAI or LAI set: " + cellGlobalIdOrServiceAreaIdOrLAI);
                        }
                    }
                } else if (cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
                    pslResponseValues.setCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdOrLAI);
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setCellGlobalIdOrServiceAreaIdOrLAI(pslResponseValues.getCellGlobalIdOrServiceAreaIdOrLAI());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideSubscriberLocationResponse: "
                                + "CDR state is initialized, CGI or SAI or LAI set:" + cellGlobalIdOrServiceAreaIdOrLAI);
                        }
                    }
                }
            }

            if (saiPresent != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received SAI Present");
                }
                pslResponseValues.setSaiPresent(saiPresent);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setSaiPresent(saiPresent);
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, SAI Present set: " + saiPresent);
                    }
                }
            }

            if (accuracyFulfilmentIndicator != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received AccuracyFulfilmentIndicator");
                }
                pslResponseValues.setAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setAccuracyFulfilmentIndicator(pslResponseValues.getAccuracyFulfilmentIndicator());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, Accuracy fulfillment indicator set: " + accuracyFulfilmentIndicator);
                    }
                }
            }

            if (velocityEstimate != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received VelocityEstimate");
                }
                pslResponseValues.setVelocityEstimate(velocityEstimate);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setVelocityEstimate(pslResponseValues.getVelocityEstimate());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, Velocity Estimate set: " + velocityEstimate);
                    }
                }
            }

            if (moLrShortCircuitIndicator != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received MoLrShortCircuitIndicator");
                }
                pslResponseValues.setMoLrShortCircuitIndicator(moLrShortCircuitIndicator);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setMoLrShortCircuitIndicator(moLrShortCircuitIndicator);
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, MO-LR Short Circuit Indicator set: " + moLrShortCircuitIndicator);
                    }
                }
            }

            if (geranGANSSpositioningData != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received GeranGANSSpositioningData");
                }
                pslResponseValues.setGeranGANSSpositioningData(geranGANSSpositioningData);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setGeranGANSSpositioningData(pslResponseValues.getGeranGANSSpositioningData());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, GERAN GANSS Positioning Data set: " + geranGANSSpositioningData);
                    }
                }
            }

            if (utranGANSSpositioningData != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received UtranGANSSpositioningData");
                }
                pslResponseValues.setUtranGANSSpositioningData(utranGANSSpositioningData);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setUtranGANSSpositioningData(pslResponseValues.getUtranGANSSpositioningData());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, UTRAN GANSS Positioning Data set: " + utranGANSSpositioningData);
                    }
                }
            }

            if (servingNodeAddress != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonProvideSubscriberLocationResponse: received ServingNodeAddress");
                }
                pslResponseValues.setServingNodeAddress(servingNodeAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setServingNodeAddress(pslResponseValues.getServingNodeAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonProvideSubscriberLocationResponse: "
                            + "CDR state is initialized, Serving Node Address set: " + servingNodeAddress);
                    }
                }
            }

            SriForLcsResponseValues sriForLcsResponse = (SriForLcsResponseValues) mobileCoreNetworkTransactions.getValue(transaction, "sriForLcsResponseValues");

            sriForLcsResponse.setPslMsisdn(pslMsisdn);
            sriForLcsResponse.setPslImsi(pslImsi);
            referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
            String pslLcsReferenceNumber = (String.valueOf(referenceNumber));
            sriForLcsResponse.setPslLcsReferenceNumber(Integer.parseInt(pslLcsReferenceNumber));
            Integer pslReferenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslReferenceNumber");
            sriForLcsResponse.setPslReferenceNumber(pslReferenceNumber);
            mobileCoreNetworkTransactions.destroy(transaction);
            if (gmlcCdrState.isInitialized()) {
                if (pslImsi != null) {
                    IMSI imsi = new IMSIImpl(pslImsi);
                    gmlcCdrState.setImsi(imsi);
                }
                gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, pslMsisdn));
                gmlcCdrState.setClientReferenceNumber(referenceNumber);
                gmlcCdrState.setLcsReferenceNumber(pslReferenceNumber);
            }

            // PSL CDR creation
            if (extGeographicalInformation != null || geranPositioningData != null || utranPositioningDataInfo != null || geranPositioningData != null
                || utranPositioningDataInfo != null || addGeographicalInformation != null || cellGlobalIdOrServiceAreaIdOrLAI != null
                || velocityEstimate != null || geranGANSSpositioningData != null || utranGANSSpositioningData != null || servingNodeAddress != null) {
                if (gmlcCdrState.isInitialized()) {
                    this.createCDRRecord(RecordStatus.PSL_SUCCESS);
                } else {
                    if (mapErrorMessage != null) {
                        if (gmlcCdrState.isInitialized()) {
                            mlpClientErrorMessage = handleRecordAndLocationReportOnMapError(mapErrorMessage, mlpRespResult,
                                pslMsisdn, pslImsi, "PSL", referenceNumber, pslNnn, pslRemoteAddress, gmlcCdrState, mlpTriggeredReportingService);
                        }
                    }
                }
            }

            // Handle successful retrieval of response to subscriber's location request (PSL response) info
            this.handleLsmLocationResponse(mlpRespResult, sriForLcsResponse, pslResponseValues, null, mlpClientErrorMessage, mlpTriggeredReportingService);

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onProvideSubscriberLocationResponse=%s", event), e);
            this.createCDRRecord(RecordStatus.PSL_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP PSL response: " + e.getMessage(),
                "PSL", pslMsisdn, pslImsi, referenceNumber, pslNnn, pslRemoteAddress, null, null, mlpTriggeredReportingService);
            if (transaction != null)
                mobileCoreNetworkTransactions.destroy(transaction);
        } finally {
            detachFromMAPDialogLsm(aci);
        }
    }


    /**
     * Location Service Management (LSM) services
     * MAP_SUBSCRIBER_LOCATION_REPORT (SLR) Events
     */

    /**
     * MAP SLR Request Event
     */
    public void onSubscriberLocationReportRequest(SubscriberLocationReportRequest event, ActivityContextInterface aci) {

        MAPErrorMessage mapErrorMessage = this.getErrorResponse();
        String slrNnn = null, slrAddNum = null;

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSubscriberLocationReportRequest = " + event);
            }

            MAPDialogLsm mapDialogLsmSlr = event.getMAPDialog();
            LCSEvent lcsEvent = event.getLCSEvent();
            LCSClientID lcsClientID = event.getLCSClientID();
            LCSLocationInfo lcsLocationInfo = event.getLCSLocationInfo();
            ISDNAddressString msisdn = event.getMSISDN();
            IMSI imsi = event.getIMSI();
            IMEI imei = event.getIMEI();
            ISDNAddressString naESRD = event.getNaESRD();
            ISDNAddressString naESRK = event.getNaESRK();
            ExtGeographicalInformation extGeographicalInformation = event.getLocationEstimate();
            Integer ageOfLocationEstimate = event.getAgeOfLocationEstimate();
            SLRArgExtensionContainer slrArgExtensionContainer = event.getSLRArgExtensionContainer();
            AddGeographicalInformation addGeographicalInformation = event.getAdditionalLocationEstimate();
            DeferredmtlrData deferredmtlrData = event.getDeferredmtlrData();
            Integer lcsReferenceNumber = event.getLCSReferenceNumber();
            PositioningDataInformation geranPositioningData = event.getGeranPositioningData();
            UtranPositioningDataInfo utranPositioningDataInfo = event.getUtranPositioningData();
            CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = event.getCellGlobalIdOrServiceAreaIdOrLAI();
            GSNAddress hgmlcAddress = event.getHGMLCAddress();
            Integer lcsServiceTypeID = event.getLCSServiceTypeID();
            Boolean saiPresent = event.getSaiPresent();
            Boolean pseudonymIndicator = event.getPseudonymIndicator();
            AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = event.getAccuracyFulfilmentIndicator();
            VelocityEstimate velocityEstimate = event.getVelocityEstimate();
            Integer sequenceNumber = event.getSequenceNumber();
            PeriodicLDRInfo periodicLDRInfo = event.getPeriodicLDRInfo();
            Boolean moLrShortCircuitIndicator = event.getMoLrShortCircuitIndicator();
            GeranGANSSpositioningData geranGANSSpositioningData = event.getGeranGANSSpositioningData();
            UtranGANSSpositioningData utranGANSSpositioningData = event.getUtranGANSSpositioningData();
            ServingNodeAddress servingNodeAddress = event.getTargetServingNodeForHandover();

            SlrRequestValues slrRequestValues = new SlrRequestValues();
            MLPResponse.MLPResultType mlpRespResult = null;
            String mlpClientErrorMessage = null;

            // CDR initialization
            GMLCCDRState gmlcCdrState = CDRCreationHelper.mapSLRCdrInitializer(aci, this.getCDRInterface(), event, msisdn);
            // Set timer last
            this.setTimer(aci);

            if (lcsEvent != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received LCSEvent parameter");
                }
                slrRequestValues.setLcsEvent(lcsEvent);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setLcsEvent(slrRequestValues.getLcsEvent());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Serving Node Address set");
                    }
                }
            }

            if (lcsClientID != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClientID");
                }
                slrRequestValues.setLcsClientID(lcsClientID);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setLcsClientID(slrRequestValues.getLcsClientID());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Serving Node Address set");
                    }
                }
                if (lcsClientID.getLCSClientType() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClient type");
                    }
                }
                if (lcsClientID.getLCSClientExternalID() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClient External ID");
                    }
                }
                if (lcsClientID.getLCSClientInternalID() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClient Internal ID");
                    }
                }
                if (lcsClientID.getLCSClientDialedByMS() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClientDialedByMS");
                    }
                }
                if (lcsClientID.getLCSClientName() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClient name");
                    }
                }
                if (lcsClientID.getLCSAPN() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClient APN");
                    }
                }
                if (lcsClientID.getLCSRequestorID() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received LCSClient requestor ID");
                    }
                }
            }

            if (lcsLocationInfo != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received LCSLocationInfo parameters");
                }
                if (lcsLocationInfo.getNetworkNodeNumber() != null) {
                    slrNnn = lcsLocationInfo.getNetworkNodeNumber().getAddress();
                    slrRequestValues.setNetworkNodeNumber(lcsLocationInfo.getNetworkNodeNumber());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setNetworkNodeNumber(slrRequestValues.getNetworkNodeNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: "
                                + "CDR state is initialized, Network Node Number set");
                        }
                    }
                }
                if (lcsLocationInfo.getLMSI() != null) {
                    slrRequestValues.setLmsi(lcsLocationInfo.getLMSI());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setLmsi(slrRequestValues.getLmsi());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: "
                                + "CDR state is initialized, LMSI set");
                        }
                    }
                }
                if (lcsLocationInfo.getExtensionContainer() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received extension container");
                    }
                }
                if (lcsLocationInfo.getGprsNodeIndicator()) {
                    slrRequestValues.setGprsNodeIndicator(lcsLocationInfo.getGprsNodeIndicator());
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setGprsNodeIndicator(true);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: CDR state is initialized, GPRS Node Indicator set");
                        }
                    }
                } else {
                    slrRequestValues.setGprsNodeIndicator(false);
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setGprsNodeIndicator(false);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: CDR state is initialized, GPRS Node Indicator set");
                        }
                    }
                }
                if (lcsLocationInfo.getAdditionalNumber() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received AdditionalNumber");
                    }
                    if (lcsLocationInfo.getGprsNodeIndicator()) {
                        if (lcsLocationInfo.getAdditionalNumber().getSGSNNumber() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: received SGSN number");
                            }
                            slrAddNum = lcsLocationInfo.getAdditionalNumber().getSGSNNumber().getAddress();
                        }
                    } else {
                        if (lcsLocationInfo.getAdditionalNumber().getMSCNumber() != null) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: received MSC number");
                            }
                            slrAddNum = lcsLocationInfo.getAdditionalNumber().getMSCNumber().getAddress();
                        }
                    }
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setAdditionalNumber(lcsLocationInfo.getAdditionalNumber());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: "
                                + "CDR state is initialized, Additional Number set");
                        }
                    }
                }
                if (lcsLocationInfo.getSupportedLCSCapabilitySets() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received SupportedLCSCapabilitySets parameters");
                    }
                    if (lcsLocationInfo.getSupportedLCSCapabilitySets().getCapabilitySetRelease98_99() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: CapabilitySetRelease98_99 = false");
                        }
                    }
                    if (lcsLocationInfo.getSupportedLCSCapabilitySets().getCapabilitySetRelease4() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: CapabilitySetRelease4 = false");
                        }
                    }
                    if (lcsLocationInfo.getSupportedLCSCapabilitySets().getCapabilitySetRelease5() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: CapabilitySetRelease5 = false");
                        }
                    }
                    if (lcsLocationInfo.getSupportedLCSCapabilitySets().getCapabilitySetRelease6() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: CapabilitySetRelease6 = false");
                        }
                    }
                    if (lcsLocationInfo.getSupportedLCSCapabilitySets().getCapabilitySetRelease7() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: CapabilitySetRelease7 = false");
                        }
                    }
                }
                if (lcsLocationInfo.getAdditionalLCSCapabilitySets() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received AdditionalLCSCapabilitySets parameters");
                    }
                    if (lcsLocationInfo.getAdditionalLCSCapabilitySets().getCapabilitySetRelease98_99() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: additional CapabilitySetRelease98_99() = false");
                        }
                    }
                    if (lcsLocationInfo.getAdditionalLCSCapabilitySets().getCapabilitySetRelease4() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: additional CapabilitySetRelease4 = false");
                        }
                    }
                    if (lcsLocationInfo.getAdditionalLCSCapabilitySets().getCapabilitySetRelease5() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: additional CapabilitySetRelease5 = false");
                        }
                    }
                    if (lcsLocationInfo.getAdditionalLCSCapabilitySets().getCapabilitySetRelease6() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: additional CapabilitySetRelease6 = false");
                        }
                    }
                    if (lcsLocationInfo.getAdditionalLCSCapabilitySets().getCapabilitySetRelease7() != true) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: additional CapabilitySetRelease7 = false");
                        }
                    }
                }
                if (lcsLocationInfo.getMmeName() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received MMEName parameter");
                    }
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setMmeName(lcsLocationInfo.getMmeName());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: "
                                + "CDR state is initialized, MME Name set");
                        }
                    }
                }
                if (lcsLocationInfo.getAaaServerName() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: received AAAServerName parameter");
                    }
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setAaaServerName(lcsLocationInfo.getAaaServerName());
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonSubscriberLocationReportRequest: "
                                + "CDR state is initialized, AAA Server Name set");
                        }
                    }
                }

            }

            if (msisdn != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received MSISDN parameter");
                }
                slrRequestValues.setMsisdn(msisdn);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setMsisdn(slrRequestValues.getMsisdn());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, MSISDN set");
                    }
                }
            }

            if (imsi != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received IMSI parameter");
                }
                slrRequestValues.setImsi(imsi);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setImsi(slrRequestValues.getImsi());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, IMSI set");
                    }
                }
            }

            if (imei != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received IMEI parameter");
                }
                slrRequestValues.setImei(imei);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setImei(slrRequestValues.getImei());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, IMEI set");
                    }
                }
            }

            if (naESRD != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received NaESRD parameter");
                }
                slrRequestValues.setNaESRD(naESRD);
            }

            if (naESRK != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received NaESRK parameter");
                }
                slrRequestValues.setNaESRK(naESRK);
            }

            if (extGeographicalInformation != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: "
                        + "received LocationEstimate, decoding ExtGeographicalInformation parameters");
                }
                if (extGeographicalInformation.getTypeOfShape() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "LocationEstimate type of shape: " + extGeographicalInformation.getTypeOfShape());
                    }
                    if (extGeographicalInformation.getTypeOfShape() != TypeOfShape.Polygon) {
                        slrRequestValues.setLocationEstimate(extGeographicalInformation);
                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setLocationEstimate(slrRequestValues.getLocationEstimate());
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "CDR state is initialized, Location Estimate set");
                            }
                        }
                        if (extGeographicalInformation.getLatitude() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate latitude: " + extGeographicalInformation.getLatitude());
                            }
                        }
                        if (extGeographicalInformation.getLongitude() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate longitude: " + extGeographicalInformation.getLongitude());
                            }
                        }
                        if (extGeographicalInformation.getUncertainty() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate uncertainty: " + extGeographicalInformation.getUncertainty());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintySemiMajorAxis() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate uncertainty Semi Major Axis: " + extGeographicalInformation.getUncertaintySemiMajorAxis());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintySemiMinorAxis() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate uncertainty Semi Minor Axis: " + extGeographicalInformation.getUncertaintySemiMinorAxis());
                            }
                        }
                        if (extGeographicalInformation.getAngleOfMajorAxis() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate angle of major axis: " + extGeographicalInformation.getAngleOfMajorAxis());
                            }
                        }
                        if (extGeographicalInformation.getConfidence() <= Integer.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate confidence: " + extGeographicalInformation.getConfidence());
                            }
                        }
                        if (extGeographicalInformation.getAltitude() <= Integer.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate altitude: " + extGeographicalInformation.getAltitude());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintyAltitude() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate altitude uncertainty: " + extGeographicalInformation.getUncertaintyAltitude());
                            }
                        }
                        if (extGeographicalInformation.getInnerRadius() <= Integer.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate inner radius: " + extGeographicalInformation.getInnerRadius());
                            }
                        }
                        if (extGeographicalInformation.getUncertaintyRadius() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate radius uncertainty: " + extGeographicalInformation.getUncertaintyRadius());
                            }
                        }
                        if (extGeographicalInformation.getOffsetAngle() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate offset angle: " + extGeographicalInformation.getOffsetAngle());
                            }
                        }
                        if (extGeographicalInformation.getIncludedAngle() <= Double.MAX_VALUE) {
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "LocationEstimate include angle: " + extGeographicalInformation.getIncludedAngle());
                            }
                        }
                    } else {
                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setTypeOfShape(extGeographicalInformation.getTypeOfShape());
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonSubscriberLocationReportRequest: "
                                    + "CDR state is initialized, Location Estimate Type of Shape set (Polygon)");
                            }
                        }
                    }

                }
            }

            if (ageOfLocationEstimate != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received AgeOfLocationInformation parameter");
                }
                slrRequestValues.setAgeOfLocationEstimate(ageOfLocationEstimate);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setAgeOfLocationEstimate(slrRequestValues.getAgeOfLocationEstimate());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Age of Location Estimate set");
                    }
                }
            }

            if (addGeographicalInformation != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: "
                        + "received AdditionalLocationEstimate, decoding AddGeographicalInformation parameters");
                }
                slrRequestValues.setAdditionalLocationEstimate(addGeographicalInformation);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setAdditionalLocationEstimate(slrRequestValues.getAdditionalLocationEstimate());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Additional Location Estimate set");
                    }
                }
                if (addGeographicalInformation.getTypeOfShape() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate type of shape: " + extGeographicalInformation.getTypeOfShape());
                    }
                }
                if (addGeographicalInformation.getLatitude() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate latitude: " + extGeographicalInformation.getLatitude());
                    }
                }
                if (addGeographicalInformation.getLongitude() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate longitude: " + extGeographicalInformation.getLongitude());
                    }
                }
                if (addGeographicalInformation.getUncertainty() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate uncertainty: " + extGeographicalInformation.getUncertainty());
                    }
                }
                if (addGeographicalInformation.getUncertaintySemiMajorAxis() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate uncertainty Semi Major Axis: " + extGeographicalInformation.getUncertaintySemiMajorAxis());
                    }
                }
                if (addGeographicalInformation.getUncertaintySemiMinorAxis() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate uncertainty Semi Minor Axis: " + extGeographicalInformation.getUncertaintySemiMinorAxis());
                    }
                }
                if (addGeographicalInformation.getAngleOfMajorAxis() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate angle of major axis: " + extGeographicalInformation.getAngleOfMajorAxis());
                    }
                }
                if (addGeographicalInformation.getConfidence() <= Integer.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate confidence: " + extGeographicalInformation.getConfidence());
                    }
                }
                if (addGeographicalInformation.getAltitude() <= Integer.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate altitude: " + extGeographicalInformation.getAltitude());
                    }
                }
                if (addGeographicalInformation.getUncertaintyAltitude() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate altitude uncertainty: " + extGeographicalInformation.getUncertaintyAltitude());
                    }
                }
                if (addGeographicalInformation.getInnerRadius() <= Integer.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate inner radius: " + extGeographicalInformation.getInnerRadius());
                    }
                }
                if (addGeographicalInformation.getUncertaintyRadius() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate radius uncertainty: " + extGeographicalInformation.getUncertaintyRadius());
                    }
                }
                if (addGeographicalInformation.getOffsetAngle() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate offset angle: " + extGeographicalInformation.getOffsetAngle());
                    }
                }
                if (addGeographicalInformation.getIncludedAngle() <= Double.MAX_VALUE) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "AdditionalLocationEstimate include angle: " + extGeographicalInformation.getIncludedAngle());
                    }
                }
            }

            if (deferredmtlrData != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received DeferredMTLRData");
                }
                slrRequestValues.setDeferredmtlrData(deferredmtlrData);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setDeferredmtlrData(slrRequestValues.getDeferredmtlrData());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Deferred MT LR Data set");
                    }
                }
                if (deferredmtlrData.getDeferredLocationEventType() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "received DeferredMTLRData event type");
                    }
                }
                if (deferredmtlrData.getTerminationCause() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "received DeferredMTLRData termination cause");
                    }
                }
                if (deferredmtlrData.getLCSLocationInfo() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "received DeferredMTLRData location info");
                    }
                }
            }

            if (lcsReferenceNumber != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received LCS Reference Number");
                }
                slrRequestValues.setLcsReferenceNumber(lcsReferenceNumber);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setLcsReferenceNumber(slrRequestValues.getLcsReferenceNumber());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, LCS Reference Number set");
                    }
                }
            }

            if (geranPositioningData != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received GeranPositioningDataInformation");
                }
                slrRequestValues.setGeranPositioningDataInformation(geranPositioningData);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setGeranPositioningDataInformation(slrRequestValues.getGeranPositioningDataInformation());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, GERAN Positining Data Info set");
                    }
                }
            }

            if (utranPositioningDataInfo != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received UTRAN Positioning Data Information");
                }
                slrRequestValues.setUtranPositioningDataInfo(utranPositioningDataInfo);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setUtranPositioningDataInfo(slrRequestValues.getUtranPositioningDataInfo());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, GERAN Positioning Data Info set");
                    }
                }
            }

            if (cellGlobalIdOrServiceAreaIdOrLAI != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received cellGlobalIdOrServiceAreaIdOrLAI");
                }
                slrRequestValues.setCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdOrLAI);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setCellGlobalIdOrServiceAreaIdOrLAI(slrRequestValues.getCellGlobalIdOrServiceAreaIdOrLAI());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, CGI or SAI or LAI set");
                    }
                }
                if (cellGlobalIdOrServiceAreaIdOrLAI.getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "received CellGlobalIdOrServiceAreaIdFixedLength on cellGlobalIdOrServiceAreaIdOrLAI");
                    }
                } else if (cellGlobalIdOrServiceAreaIdOrLAI.getLAIFixedLength() != null) {
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "received CellGlobalIdOrServiceAreaIdFixedLength on cellGlobalIdOrServiceAreaIdOrLAI");
                    }
                }
            }

            if (hgmlcAddress != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received HGMLCAddress parameter");
                }
                slrRequestValues.sethGmlcAddress(hgmlcAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.sethGmlcAddress(slrRequestValues.gethGmlcAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, H-GMLC Address set");
                    }
                }
            }

            if (lcsServiceTypeID != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received LCSServiceTypeID parameter");
                }
                slrRequestValues.setLcsServiceTypeID(lcsServiceTypeID);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setLcsServiceTypeID(slrRequestValues.getLcsServiceTypeID());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, LCS Service Type ID set");
                    }
                }
            }

            if (saiPresent != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received SAIPresent parameter");
                }
                slrRequestValues.setSaiPresent(saiPresent.booleanValue());
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setSaiPresent(saiPresent.booleanValue());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: CDR state is initialized, SAI Present set: " + saiPresent);
                    }
                }
            }

            if (pseudonymIndicator != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received PseudonymIndicator parameter");
                }
                slrRequestValues.setPseudonymIndicator(pseudonymIndicator.booleanValue());
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setPseudonymIndicator(pseudonymIndicator.booleanValue());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: CDR state is initialized, PseudonymIndicator set: " + pseudonymIndicator);
                    }
                }

            }

            if (accuracyFulfilmentIndicator != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received AccuracyFulfilmentIndicator");
                }
                slrRequestValues.setAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setAccuracyFulfilmentIndicator(slrRequestValues.getAccuracyFulfilmentIndicator());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Accuracy Fulfilment Indicator set");
                    }
                }
            }

            if (velocityEstimate != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received VelocityEstimate");
                }
                slrRequestValues.setVelocityEstimate(velocityEstimate);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setVelocityEstimate(slrRequestValues.getVelocityEstimate());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Velocity Estimate set");
                    }
                }
            }

            if (sequenceNumber != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received SequenceNumber");
                }
                slrRequestValues.setSequenceNumber(sequenceNumber);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setSequenceNumber(slrRequestValues.getSequenceNumber());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Sequence Number set");
                    }
                }
            }

            if (periodicLDRInfo != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received periodicLDRInfo");
                }
                slrRequestValues.setPeriodicLDRInfo(periodicLDRInfo);
            }

            if (moLrShortCircuitIndicator != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received MoLrShortCircuitIndicator");
                }
                slrRequestValues.setMoLrShortCircuitIndicator(moLrShortCircuitIndicator);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setPeriodicLDRInfo(slrRequestValues.getPeriodicLDRInfo());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Periodic LDR Info set");
                    }
                }
            }

            if (geranGANSSpositioningData != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received GeranGANSSpositioningData");
                }
                slrRequestValues.setGeranGANSSpositioningData(geranGANSSpositioningData);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setGeranGANSSpositioningData(slrRequestValues.getGeranGANSSpositioningData());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, GERAN GANSS Positioning Data set");
                    }
                }
            }

            if (utranGANSSpositioningData != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received UtranGANSSpositioningData");
                }
                slrRequestValues.setUtranGANSSpositioningData(utranGANSSpositioningData);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setUtranGANSSpositioningData(slrRequestValues.getUtranGANSSpositioningData());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, UTRAN GANSS Positioning Data set");
                    }
                }
            }

            if (servingNodeAddress != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received ServingNodeAddress");
                }
                slrRequestValues.setServingNodeAddress(servingNodeAddress);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setServingNodeAddress(slrRequestValues.getServingNodeAddress());
                    if (this.logger.isFineEnabled()) {
                        this.logger.fine("\nonSubscriberLocationReportRequest: "
                            + "CDR state is initialized, Serving Node address set");
                    }
                }
            }

            if (slrArgExtensionContainer != null) {
                mlpRespResult = MLPResponse.MLPResultType.OK;
                if (this.logger.isFineEnabled()) {
                    this.logger.fine("\nonSubscriberLocationReportRequest: received SLRArgExtensionContainer parameter");
                }
            }

            // SLR CDR creation
            if (lcsEvent != null || lcsClientID != null || lcsLocationInfo != null || extGeographicalInformation != null || geranPositioningData != null || utranPositioningDataInfo != null || geranPositioningData != null
                || utranPositioningDataInfo != null || addGeographicalInformation != null || deferredmtlrData != null || cellGlobalIdOrServiceAreaIdOrLAI != null
                || velocityEstimate != null || geranGANSSpositioningData != null || utranGANSSpositioningData != null || servingNodeAddress != null
                || periodicLDRInfo != null) {
                if (gmlcCdrState.isInitialized()) {
                    this.createCDRRecord(RecordStatus.SLR_SUCCESS);
                }
            } else {
                if (mapErrorMessage != null) {
                    if (gmlcCdrState.isInitialized()) {
                        String msisdnAddress = null, imsiStr = null;
                        if (msisdn != null)
                            msisdnAddress = msisdn.getAddress();
                        if (imsi != null)
                            imsiStr = imsi.getData();
                        mlpClientErrorMessage = handleRecordAndLocationReportOnMapError(mapErrorMessage, mlpRespResult, msisdnAddress, imsiStr,
                            "SLR", lcsReferenceNumber, slrNnn, slrAddNum, gmlcCdrState, false);
                    }
                }
            }

            this.setSubscriberLocationReportRequest(event);
            if (this.getSubscriberLocationReportRequest() != null) {

                MAPExtensionContainer mapExtensionContainer = null;

                mapDialogLsmSlr.addSubscriberLocationReportResponse(event.getInvokeId(), naESRD, naESRK, mapExtensionContainer);

                // SubscriberLocationReportResponse is now composed by values taken from SubscriberLocationReportRequest and ready to be sent:
                mapDialogLsmSlr.close(false);

                // Handle successful retrieval of subscriber's location report request (SLR request) info by sending HTTP POST back to the requestor
                if (this.logger.isFineEnabled()) {
                    logger.fine(String.format("Handling SubscriberLocationReport POST ReferenceNumber '%s'\n", lcsReferenceNumber));
                }
                httpSubscriberLocationReport = getHttpSubscriberLocationReport();
                httpSubscriberLocationReport.Perform(HttpReport.HttpMethod.POST, lcsReferenceNumber, slrRequestValues, true);
                httpSubscriberLocationReport.closeMongo();
            }
        } catch (MAPException me) {
            logger.severe(String.format("MAPException while trying to process onSubscriberLocationReportRequest=%s", event), me);
            logger.severe("MAP error message when processing onSubscriberLocationReportRequest: " + mapErrorMessage);
            this.createCDRRecord(RecordStatus.SLR_SYSTEM_FAILURE);
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onSubscriberLocationReportRequest=%s", event), e);
            this.createCDRRecord(RecordStatus.SLR_SYSTEM_FAILURE);
        }
    }

    /**
     * MAP SLR Response Event
     */
    public void onSubscriberLocationReportResponse(SubscriberLocationReportResponse event, ActivityContextInterface aci) {

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onSubscriberLocationReportResponse = " + event);
            }

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onSubscriberLocationReportResponse=%s", event), e);
        }

    }

    /**
     * SS7 DIALOG Events
     */
    public void onDialogTimeout(DialogTimeout event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nRx :  onDialogTimeout " + event);
        }
        String operation = null, targetMsisdn = null, targetImsi = null, nnn = null, addNum = null, curlUser = null;
        Long transaction = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        DateTime eventTime = DateTime.now();
        DateTime dialogStartTime = null;
        RecordStatus onDialogTimeoutRecordStatus = RecordStatus.MAP_DIALOG_TIMEOUT;

        if (event.getMAPDialog() != null) {
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
            dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            TimerID timerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            if (timerID != null)
                this.timerFacility.cancelTimer(timerID);
            if (event.getMAPDialog().getApplicationContext() != null) {
                MAPApplicationContextName mapApplicationContextName = event.getMAPDialog().getApplicationContext().getApplicationContextName();
                MAPApplicationContextVersion mapApplicationContextVersion = event.getMAPDialog().getApplicationContext().getApplicationContextVersion();
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "ATI";
                    onDialogTimeoutRecordStatus = RecordStatus.ATI_DIALOG_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "SRILCS";
                    onDialogTimeoutRecordStatus = RecordStatus.SRILCS_DIALOG_TIMEOUT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSL";
                    onDialogTimeoutRecordStatus = RecordStatus.PSL_DIALOG_TIMEOUT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRI";
                    onDialogTimeoutRecordStatus = RecordStatus.SRI_DIALOG_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiNNN");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
                if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRISM";
                    onDialogTimeoutRecordStatus = RecordStatus.SRISM_DIALOG_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiNNN");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSI";
                    onDialogTimeoutRecordStatus = RecordStatus.PSI_DIALOG_TIMEOUT;
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiNNN");
                    if (nnn == null)
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyNnn");
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
                }
            }
        }
        if (transaction != null)
            mobileCoreNetworkTransactions.Instance().destroy(transaction);
        this.reportLocationRequestError(MLPResponse.MLPResultType.TIMEOUT, "MAP Dialog Timeout", operation, targetMsisdn,
            targetImsi, referenceNumber, nnn, addNum, null, null, mlpTriggeredReportingService);
        MAPDialog mapDialog = event.getMAPDialog();
        GMLCCDRState gmlcCdrState = CDRCreationHelper.onMapDialogEventCdrInitializer(aci, this.getCDRInterface(), mapDialog);
        this.setTimer(aci);
        if (gmlcCdrState.isInitialized()) {
            gmlcCdrState.setCurlUser(curlUser);
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (dialogStartTime != null && eventTime != null) {
                Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                gmlcCdrState.setDialogDuration(dialogDuration);
            }
            gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn));
            gmlcCdrState.setImsi(new IMSIImpl(targetImsi));
            this.createCDRRecord(onDialogTimeoutRecordStatus);
        }
    }

    public void onDialogDelimiter(DialogDelimiter event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nReceived onDialogDelimiter = " + event);
        }
    }

    public void onDialogAccept(DialogAccept event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nReceived onDialogAccept = " + event);
        }
    }

    public void onDialogReject(DialogReject event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nRx :  onDialogReject " + event);
        }
        String operation = "NA", reason = "NA", targetMsisdn = null, targetImsi = null, nnn = null, addNum = null, curlUser = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        MAPApplicationContextName mapApplicationContextName = null;
        MAPApplicationContextVersion mapApplicationContextVersion;
        MAPRefuseReason mapRefuseReason = null;
        int translationType = 0;
        DateTime eventTime = DateTime.now();
        DateTime dialogStartTime = null;
        RecordStatus onDialogRejectRecordStatus = RecordStatus.MAP_DIALOG_REJECTED;
        LocationRequestParams locationRequestParams;

        try {

            if (event != null) {
                mapRefuseReason = event.getRefuseReason();
                if (event.getMAPDialog() != null) {
                    transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                    dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
                    TimerID timerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
                    mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
                    if (timerID != null)
                        this.timerFacility.cancelTimer(timerID);
                    if (event.getMAPDialog().getApplicationContext() != null)
                        mapApplicationContextName = event.getMAPDialog().getApplicationContext().getApplicationContextName();
                    if (event.getMAPDialog().getRemoteAddress() != null)
                        if (event.getMAPDialog().getRemoteAddress().getGlobalTitle() != null)
                            nnn = event.getMAPDialog().getRemoteAddress().getGlobalTitle().getDigits();
                }
            }

            if (mapRefuseReason == null) {
                mapApplicationContextVersion = event.getMAPDialog().getApplicationContext().getApplicationContextVersion();
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "ATI";
                    onDialogRejectRecordStatus = RecordStatus.ATI_MAP_DIALOG_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "SRILCS";
                    onDialogRejectRecordStatus = RecordStatus.SRILCS_MAP_DIALOG_TIMEOUT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSL";
                    onDialogRejectRecordStatus = RecordStatus.PSL_MAP_DIALOG_TIMEOUT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRI";
                    onDialogRejectRecordStatus = RecordStatus.SRI_MAP_DIALOG_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRISM";
                    onDialogRejectRecordStatus = RecordStatus.SRISM_MAP_DIALOG_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSI";
                    onDialogRejectRecordStatus = RecordStatus.PSI_MAP_DIALOG_TIMEOUT;
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                }

            } else {

                /*****************************/
                /**** ACN not supported  ****/
                /***************************/
                // If ACN not supported, swap to the new one suggested
                if (mapRefuseReason == MAPRefuseReason.ApplicationContextNotSupported) {
                    if (logger.isWarningEnabled()) {
                        this.logger.warning("Rx : onDialogReject / ApplicationContextNotSupported: " + event);
                    }

                    // Now, send new the SS7 operation with supported ACN
                    ApplicationContextName tcapApplicationContextName = event.getAlternativeApplicationContext();
                    MAPApplicationContext supportedMAPApplicationContext = MAPApplicationContext.getInstance(tcapApplicationContextName.getOid());

                    // SRI ACN check and refactor to the appropriate value
                    if (supportedMAPApplicationContext.getApplicationContextName() == MAPApplicationContextName.locationInfoRetrievalContext) {
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("WARNING: ACN version " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion() +
                                    " not supported for MAP SRI (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() + ")");
                        }
                        operation = "SRI";
                        reason = "SRI Application Context Not Supported";
                        onDialogRejectRecordStatus = RecordStatus.SRI_REJECTED_ACN_NOT_SUPPORTED;
                        // Transaction
                        Long sriTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        String domainForPsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "sriPsiDomain");
                        String locationInformationEps = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "locationInfoEPS");
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
                        String sriAcnVersion = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "sriAcnVersion");
                        translationType = (Integer) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "tt");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "curlUser");
                        boolean sriOnly = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "sriOnly");
                        if (sriTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);

                        //this.locationInfoRetrievalContext = supportedMAPApplicationContext;
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("Reattempt SRI (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() +
                                    ") with suggested version: " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion());
                        }
                        if (sriAcnVersion.equalsIgnoreCase("version3")) {
                            locationRequestParams = new LocationRequestParams();
                            locationRequestParams.setTargetingMSISDN(targetMsisdn);
                            locationRequestParams.setDomainType(domainForPsi);
                            locationRequestParams.setLocationInfoEps(locationInformationEps);
                            locationRequestParams.setTranslationType(translationType);
                            locationRequestParams.setCurlUser(curlUser);
                            getLocationViaSubscriberInformationCallHandling(locationRequestParams, true, sriOnly);
                            return;
                        }
                    }

                    // SRISM ACN check and refactor to the appropriate value
                    if (supportedMAPApplicationContext.getApplicationContextName() == MAPApplicationContextName.shortMsgGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("WARNING: ACN version " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion() +
                                    " not supported for MAP SRISM (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() + ")");
                        }
                        operation = "SRISM";
                        reason = "SRISM Application Context Not Supported";
                        onDialogRejectRecordStatus = RecordStatus.SRISM_REJECTED_ACN_NOT_SUPPORTED;
                        // Transaction
                        Long sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        String domainForPsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "sriPsiDomain");
                        String locationInformationEps = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "locationInfoEPS");
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
                        String sriAcnVersion = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "sriAcnVersion");
                        translationType = (Integer) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "tt");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
                        boolean sriSmOnly = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "sriSmOnly");
                        if (sriForSMTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);

                        //this.shortMsgGatewayContext = supportedMAPApplicationContext;
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("Reattempt SRISM (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() +
                                    ") with suggested version: " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion());
                        }
                        if (sriAcnVersion.equalsIgnoreCase("version3")) {
                            locationRequestParams = new LocationRequestParams();
                            locationRequestParams.setTargetingMSISDN(targetMsisdn);
                            locationRequestParams.setDomainType(domainForPsi);
                            locationRequestParams.setLocationInfoEps(locationInformationEps);
                            locationRequestParams.setTranslationType(translationType);
                            locationRequestParams.setCurlUser(curlUser);
                            getLocationViaSubscriberInformation(locationRequestParams, true, sriSmOnly);
                            return;
                        }
                    }

                    // ATI ACN check and log if rejected for not being supported by the network
                    if (supportedMAPApplicationContext.getApplicationContextName() == MAPApplicationContextName.anyTimeEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("WARNING: ACN version " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion() +
                                    " not supported for MAP ATI (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() + ")");
                        }
                        operation = "ATI";
                        reason = "ATI Application Context Not Supported";
                        onDialogRejectRecordStatus = RecordStatus.ATI_REJECTED_ACN_NOT_SUPPORTED;
                        Long atiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "atiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "curlUser");
                        if (atiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(atiTransaction);
                    }
                    // PSI ACN check and log if rejected for not being supported by the network
                    if (supportedMAPApplicationContext.getApplicationContextName() == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("WARNING: ACN version " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion() +
                                    " not supported for MAP PSI (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() + ")");
                        }
                        operation = "PSI";
                        reason = "PSI Application Context Not Supported";
                        onDialogRejectRecordStatus = RecordStatus.PSI_REJECTED_ACN_NOT_SUPPORTED;
                        Long psiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "curlUser");
                        if (psiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(psiTransaction);
                    }

                    // SRILCS ACN check and log if rejected for not being supported by the network
                    if (supportedMAPApplicationContext.getApplicationContextName() == MAPApplicationContextName.locationSvcGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("WARNING: ACN version " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion() +
                                    " not supported for MAP SRILCS (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() + ")");
                        }
                        operation = "SRILCS";
                        reason = "SRILCS Application Context Not Supported";
                        onDialogRejectRecordStatus = RecordStatus.SRILCS_REJECTED_ACN_NOT_SUPPORTED;
                        Long sriForLcsTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(sriForLcsTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (sriForLcsTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForLcsTransaction);
                    }

                    // PSL ACN check and log if rejected for not being supported by the network
                    if (supportedMAPApplicationContext.getApplicationContextName() == MAPApplicationContextName.locationSvcEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            if (supportedMAPApplicationContext.getApplicationContextVersion() != null)
                                logger.warning("WARNING: ACN version " + supportedMAPApplicationContext.getApplicationContextVersion().getVersion() +
                                    " not supported for MAP PSL (ACN code " + supportedMAPApplicationContext.getApplicationContextName().getApplicationContextCode() + ")");
                        }
                        operation = "PSL";
                        reason = "PSL Application Context Not Supported";
                        onDialogRejectRecordStatus = RecordStatus.PSL_REJECTED_ACN_NOT_SUPPORTED;
                        Long pslTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(pslTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (pslTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(pslTransaction);
                    }
                }

                /***************************************/
                /**** InvalidDestinationReference ****/
                /************************************/
                if (mapRefuseReason == MAPRefuseReason.InvalidDestinationReference) {
                    if (logger.isWarningEnabled()) {
                        this.logger.warning("Rx : onDialogReject / InvalidDestinationReference: " + event);
                    }
                    // SRI InvalidDestinationReference
                    if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidDestinationReference for attempted SRI");
                        }
                        operation = "SRI";
                        reason = "SRI Invalid Destination Reference";
                        onDialogRejectRecordStatus = RecordStatus.SRI_REJECTED_INVALID_DESTINATION_REFERENCE;
                        // Transaction
                        Long sriTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "curlUser");
                        if (sriTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);
                    }
                    // SRISM InvalidDestinationReference
                    if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidDestinationReference for attempted SRISM");
                        }
                        operation = "SRISM";
                        reason = "SRISM Invalid Destination Reference";
                        onDialogRejectRecordStatus = RecordStatus.SRISM_REJECTED_INVALID_DESTINATION_REFERENCE;
                        // Transaction
                        Long sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
                        if (sriForSMTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);
                    }
                    // ATI InvalidDestinationReference
                    if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidDestinationReference for attempted ATI");
                        }
                        operation = "ATI";
                        reason = "ATI Invalid Destination Reference";
                        onDialogRejectRecordStatus = RecordStatus.ATI_REJECTED_INVALID_DESTINATION_REFERENCE;
                        // Transaction
                        Long atiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "atiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "curlUser");
                        if (atiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(atiTransaction);
                    }
                    // PSI InvalidDestinationReference
                    if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidDestinationReference for attempted PSI");
                        }
                        operation = "PSI";
                        reason = "PSI Invalid Destination Reference";
                        onDialogRejectRecordStatus = RecordStatus.PSI_REJECTED_INVALID_DESTINATION_REFERENCE;
                        Long psiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "curlUser");
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriForSMImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiOnlyImsi");
                        if (psiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(psiTransaction);
                    }
                    // SRILCS InvalidDestinationReference
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidDestinationReference for attempted SRILCS");
                        }
                        operation = "SRILCS";
                        reason = "SRILCS Invalid Destination Reference";
                        onDialogRejectRecordStatus = RecordStatus.SRILCS_REJECTED_INVALID_DESTINATION_REFERENCE;
                        Long sriForLcsTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(sriForLcsTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (sriForLcsTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForLcsTransaction);
                    }
                    // PSL InvalidDestinationReference
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidDestinationReference for attempted PSL");
                        }
                        operation = "PSL";
                        reason = "PSL Invalid Destination Reference";
                        onDialogRejectRecordStatus = RecordStatus.PSL_REJECTED_INVALID_DESTINATION_REFERENCE;
                        Long pslTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(pslTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (pslTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(pslTransaction);
                    }
                }

                /**************************************/
                /**** InvalidOriginatingReference ****/
                /************************************/
                if (mapRefuseReason == MAPRefuseReason.InvalidOriginatingReference) {
                    if (logger.isWarningEnabled()) {
                        this.logger.warning("Rx : onDialogReject / InvalidOriginatingReference: " + event);
                    }
                    // SRI InvalidOriginatingReference
                    if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidOriginatingReference for attempted SRI");
                        }
                        operation = "SRI";
                        reason = "SRI Invalid Originating Reference";
                        onDialogRejectRecordStatus = RecordStatus.SRI_REJECTED_INVALID_ORIGINATING_REFERENCE;
                        // Transaction
                        Long sriTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "curlUser");
                        if (sriTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);
                    }
                    // SRISM InvalidOriginatingReference
                    if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidOriginatingReference for attempted SRISM");
                        }
                        operation = "SRISM";
                        reason = "SRISM Invalid Originating Reference";
                        onDialogRejectRecordStatus = RecordStatus.SRISM_REJECTED_INVALID_ORIGINATING_REFERENCE;
                        // Transaction
                        Long sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
                        if (sriForSMTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);
                    }
                    // ATI InvalidOriginatingReference
                    if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidOriginatingReference for attempted ATI");
                        }
                        operation = "ATI";
                        reason = "ATI Invalid Originating Reference";
                        onDialogRejectRecordStatus = RecordStatus.ATI_REJECTED_INVALID_ORIGINATING_REFERENCE;
                        // Transaction
                        Long atiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "atiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "curlUser");
                        if (atiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(atiTransaction);
                    }
                    // PSI InvalidOriginatingReference
                    if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidOriginatingReference for attempted PSI");
                        }
                        operation = "PSI";
                        reason = "PSI Invalid Originating Reference";
                        onDialogRejectRecordStatus = RecordStatus.PSI_REJECTED_INVALID_ORIGINATING_REFERENCE;
                        Long psiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "curlUser");
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriForSMImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiOnlyImsi");
                        if (psiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(psiTransaction);
                    }
                    // SRILCS InvalidOriginatingReference
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidOriginatingReference for attempted SRILCS");
                        }
                        operation = "SRILCS";
                        reason = "SRILCS Invalid Originating Reference";
                        onDialogRejectRecordStatus = RecordStatus.SRILCS_REJECTED_INVALID_ORIGINATING_REFERENCE;
                        Long sriForLcsTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(sriForLcsTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (sriForLcsTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForLcsTransaction);
                    }
                    // PSL InvalidOriginatingReference
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: InvalidOriginatingReference for attempted PSL");
                        }
                        operation = "PSL";
                        reason = "PSL Invalid Originating Reference";
                        onDialogRejectRecordStatus = RecordStatus.PSL_REJECTED_INVALID_ORIGINATING_REFERENCE;
                        Long pslTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(pslTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (pslTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(pslTransaction);
                    }
                }

                /************************/
                /**** NoReasonGiven ****/
                /**********************/
                if (mapRefuseReason == MAPRefuseReason.NoReasonGiven) {
                    if (logger.isWarningEnabled()) {
                        this.logger.warning("Rx : onDialogReject / NoReasonGiven: " + event);
                    }
                    // SRI NoReasonGiven
                    if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: NoReasonGiven for attempted SRI");
                        }
                        operation = "SRI";
                        reason = "SRI No Reason Given";
                        onDialogRejectRecordStatus = RecordStatus.SRI_REJECTED_NO_REASON_GIVEN;
                        // Transaction
                        Long sriTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "curlUser");
                        if (sriTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);
                    }
                    // SRISM NoReasonGiven
                    if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: NoReasonGiven for attempted SRISM");
                        }
                        operation = "SRISM";
                        reason = "SRISM No Reason Given";
                        onDialogRejectRecordStatus = RecordStatus.SRISM_REJECTED_NO_REASON_GIVEN;
                        // Transaction
                        Long sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
                        if (sriForSMTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);
                    }
                    // ATI NoReasonGiven
                    if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: NoReasonGiven for attempted ATI");
                        }
                        operation = "ATI";
                        reason = "ATI No Reason Given";
                        onDialogRejectRecordStatus = RecordStatus.ATI_REJECTED_NO_REASON_GIVEN;
                        // Transaction
                        Long atiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "atiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "curlUser");
                        if (atiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(atiTransaction);
                    }
                    // PSI NoReasonGiven
                    if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: NoReasonGiven for attempted PSI");
                        }
                        operation = "PSI";
                        reason = "PSI No Reason Given";
                        onDialogRejectRecordStatus = RecordStatus.PSI_REJECTED_NO_REASON_GIVEN;
                        Long psiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "curlUser");
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriForSMImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiOnlyImsi");
                        if (psiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(psiTransaction);
                    }
                    // SRILCS NoReasonGiven
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: NoReasonGiven for attempted SRILCS");
                        }
                        operation = "SRILCS";
                        reason = "SRILCS No Reason Given";
                        onDialogRejectRecordStatus = RecordStatus.SRILCS_REJECTED_NO_REASON_GIVEN;
                        Long sriForLcsTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(sriForLcsTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (sriForLcsTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForLcsTransaction);
                    }
                    // PSL NoReasonGiven
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: NoReasonGiven for attempted PSL");
                        }
                        operation = "PSL";
                        reason = "PSL No Reason Given";
                        onDialogRejectRecordStatus = RecordStatus.PSL_REJECTED_NO_REASON_GIVEN;
                        Long pslTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(pslTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (pslTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(pslTransaction);
                    }
                }

                /*********************************/
                /**** RemoteNodeNotReachable ****/
                /*******************************/
                if (mapRefuseReason == MAPRefuseReason.RemoteNodeNotReachable) {
                    if (logger.isWarningEnabled()) {
                        this.logger.warning("Rx : onDialogReject / RemoteNodeNotReachable: " + event);
                    }
                    // SRI RemoteNodeNotReachable
                    if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: RemoteNodeNotReachable for attempted SRI");
                        }
                        operation = "SRI";
                        reason = "SRI Remote Node Not Reachable";
                        onDialogRejectRecordStatus = RecordStatus.SRI_REJECTED_REMOTE_NODE_NOT_REACHABLE;
                        // Transaction
                        Long sriTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
                        if (sriTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);
                    }
                    // SRISM RemoteNodeNotReachable
                    if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: RemoteNodeNotReachable for attempted SRISM");
                        }
                        operation = "SRISM";
                        reason = "SRISM Remote Node Not Reachable";
                        onDialogRejectRecordStatus = RecordStatus.SRISM_REJECTED_REMOTE_NODE_NOT_REACHABLE;
                        // Transaction
                        Long sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
                        if (sriForSMTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);
                    }
                    // ATI RemoteNodeNotReachable
                    if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: RemoteNodeNotReachable for attempted ATI");
                        }
                        operation = "ATI";
                        reason = "ATI Remote Node Not Reachable";
                        onDialogRejectRecordStatus = RecordStatus.ATI_REJECTED_REMOTE_NODE_NOT_REACHABLE;
                        // Transaction
                        Long atiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "atiMsisdn");
                        if (atiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(atiTransaction);
                    }
                    // PSI RemoteNodeNotReachable
                    if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: RemoteNodeNotReachable for attempted PSI");
                        }
                        operation = "PSI";
                        reason = "PSI Remote Node Not Reachable";
                        onDialogRejectRecordStatus = RecordStatus.PSI_REJECTED_REMOTE_NODE_NOT_REACHABLE;
                        Long psiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "curlUser");
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriForSMImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiOnlyImsi");
                        if (psiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(psiTransaction);
                    }
                    // SRILCS RemoteNodeNotReachable
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: RemoteNodeNotReachable for attempted SRILCS");
                        }
                        operation = "SRILCS";
                        reason = "SRILCS Remote Node Not Reachable";
                        onDialogRejectRecordStatus = RecordStatus.SRILCS_REJECTED_REMOTE_NODE_NOT_REACHABLE;
                        Long sriForLcsTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(sriForLcsTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (sriForLcsTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForLcsTransaction);
                    }
                    // PSL RemoteNodeNotReachable
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: RemoteNodeNotReachable for attempted PSL");
                        }
                        operation = "PSL";
                        reason = "PSL Remote Node Not Reachable";
                        onDialogRejectRecordStatus = RecordStatus.PSL_REJECTED_REMOTE_NODE_NOT_REACHABLE;
                        Long pslTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(pslTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (pslTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(pslTransaction);
                    }
                }

                /******************************************/
                /**** PotentialVersionIncompatibility ****/
                /****************************************/
                if (mapRefuseReason == MAPRefuseReason.PotentialVersionIncompatibility) {
                    if (logger.isWarningEnabled()) {
                        this.logger.warning("Rx : onDialogReject / PotentialVersionIncompatibility: " + event);
                    }
                    // SRI PotentialVersionIncompatibility
                    if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibility for attempted SRI");
                        }
                        operation = "SRI";
                        reason = "SRI Potential Version Incompatibility";
                        onDialogRejectRecordStatus = RecordStatus.SRI_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY;
                        // Transaction
                        Long sriTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "curlUser");
                        if (sriTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);
                    }
                    // SRISM PotentialVersionIncompatibility
                    if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibility for attempted SRISM");
                        }
                        operation = "SRISM";
                        reason = "SRISM Potential Version Incompatibility";
                        onDialogRejectRecordStatus = RecordStatus.SRISM_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY;
                        // Transaction
                        Long sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
                        if (sriForSMTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);
                    }
                    // ATI PotentialVersionIncompatibility
                    if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibility for attempted ATI");
                        }
                        operation = "ATI";
                        reason = "ATI Potential Version Incompatibility";
                        onDialogRejectRecordStatus = RecordStatus.ATI_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY;
                        // Transaction
                        Long atiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "atiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "curlUser");
                        if (atiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(atiTransaction);
                    }
                    // PSI PotentialVersionIncompatibility
                    if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibility for attempted PSI");
                        }
                        operation = "PSI";
                        reason = "PSI Potential Version Incompatibility";
                        onDialogRejectRecordStatus = RecordStatus.PSI_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY;
                        Long psiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "curlUser");
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriForSMImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiOnlyImsi");
                        if (psiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(psiTransaction);
                    }
                    // SRILCS PotentialVersionIncompatibility
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibility for attempted SRILCS");
                        }
                        operation = "SRILCS";
                        reason = "SRILCS Potential Version Incompatibility";
                        onDialogRejectRecordStatus = RecordStatus.SRILCS_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY;
                        Long sriForLcsTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(sriForLcsTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (sriForLcsTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForLcsTransaction);
                    }
                    // PSL PotentialVersionIncompatibility
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibility for attempted PSL");
                        }
                        operation = "PSL";
                        reason = "PSL Potential Version Incompatibility";
                        onDialogRejectRecordStatus = RecordStatus.PSL_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY;
                        Long pslTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(pslTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (pslTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(pslTransaction);
                    }
                }

                /**********************************************/
                /**** PotentialVersionIncompatibilityTcap ****/
                /********************************************/
                if (mapRefuseReason == MAPRefuseReason.PotentialVersionIncompatibilityTcap) {
                    if (logger.isWarningEnabled()) {
                        this.logger.warning("Rx : onDialogReject / PotentialVersionIncompatibilityTcap: " + event);
                    }
                    // SRI PotentialVersionIncompatibilityTcap
                    if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibilityTcap for attempted SRI");
                        }
                        operation = "SRI";
                        reason = "SRI Potential Version Incompatibility TCAP";
                        onDialogRejectRecordStatus = RecordStatus.SRI_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY_TCAP;
                        // Transaction
                        Long sriTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriTransaction, "curlUser");
                        if (sriTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriTransaction);
                    }
                    // SRISM PotentialVersionIncompatibilityTcap
                    if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibilityTcap for attempted SRISM");
                        }
                        operation = "SRISM";
                        reason = "SRISM Potential Version Incompatibility TCAP";
                        onDialogRejectRecordStatus = RecordStatus.SRISM_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY_TCAP;
                        // Transaction
                        Long sriForSMTransaction = mobileCoreNetworkTransactions.getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "psiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForSMTransaction, "curlUser");
                        if (sriForSMTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForSMTransaction);
                    }
                    // ATI PotentialVersionIncompatibilityTcap
                    if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibilityTcap for attempted ATI");
                        }
                        operation = "ATI";
                        reason = "ATI Potential Version Incompatibility TCAP";
                        onDialogRejectRecordStatus = RecordStatus.ATI_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY_TCAP;
                        // Transaction
                        Long atiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "atiMsisdn");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(atiTransaction, "curlUser");
                        if (atiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(atiTransaction);
                    }
                    // PSI PotentialVersionIncompatibilityTcap
                    if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibilityTcap for attempted PSI");
                        }
                        operation = "PSI";
                        reason = "PSI Potential Version Incompatibility TCAP";
                        onDialogRejectRecordStatus = RecordStatus.PSI_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY_TCAP;
                        Long psiTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "psiMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriForSMImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(psiTransaction, "sriImsi");
                        if (targetImsi == null)
                            targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                        if (psiTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(psiTransaction);
                    }
                    // SRILCS PotentialVersionIncompatibilityTcap
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibilityTcap for attempted SRILCS");
                        }
                        operation = "SRILCS";
                        reason = "SRILCS Potential Version Incompatibility TCAP";
                        onDialogRejectRecordStatus = RecordStatus.SRILCS_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY_TCAP;
                        Long sriForLcsTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(sriForLcsTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(sriForLcsTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (sriForLcsTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(sriForLcsTransaction);
                    }
                    // PSL PotentialVersionIncompatibilityTcap
                    if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext) {
                        if (logger.isWarningEnabled()) {
                            logger.warning("WARNING: PotentialVersionIncompatibilityTcap for attempted PSL");
                        }
                        operation = "PSL";
                        reason = "PSL Potential Version Incompatibility TCAP";
                        onDialogRejectRecordStatus = RecordStatus.PSL_REJECTED_POTENTIAL_VERSION_INCOMPATIBILITY_TCAP;
                        Long pslTransaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
                        targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslMsisdn");
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "pslImsi");
                        referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(pslTransaction, "pslLcsReferenceNumber");
                        curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(pslTransaction, "curlUser");
                        nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                        addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                        if (pslTransaction != null)
                            mobileCoreNetworkTransactions.Instance().destroy(pslTransaction);
                    }
                }

            }

        } catch (Exception e) {
            logger.severe("Exception on onDialogReject(): " + e.getMessage(), e);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on MAP Dialog Rejected",
                operation, targetMsisdn, targetImsi, referenceNumber, nnn, addNum, null, null, mlpTriggeredReportingService);
        }

        if (transaction != null)
            mobileCoreNetworkTransactions.Instance().destroy(transaction);
        this.reportLocationRequestError(MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED, "Dialog Rejected: " + reason,
            operation, targetMsisdn, targetImsi, referenceNumber, nnn, addNum,null, null, mlpTriggeredReportingService);
        MAPDialog mapDialog = event.getMAPDialog();
        GMLCCDRState gmlcCdrState = CDRCreationHelper.onMapDialogEventCdrInitializer(aci, this.getCDRInterface(), mapDialog);
        this.setTimer(aci);
        if (gmlcCdrState.isInitialized()) {
            gmlcCdrState.setCurlUser(curlUser);
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (dialogStartTime != null && eventTime != null) {
                Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                gmlcCdrState.setDialogDuration(dialogDuration);
            }
            gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn));
            gmlcCdrState.setImsi(new IMSIImpl(targetImsi));
            this.createCDRRecord(onDialogRejectRecordStatus);
        }
    }

    public void onDialogUserAbort(DialogUserAbort event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nRx :  onDialogUserAbort " + event);
        }
        String operation = null, targetMsisdn = null, targetImsi = null, nnn = null, addNum = null, curlUser = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        DateTime eventTime = DateTime.now();
        DateTime dialogStartTime = null;
        RecordStatus onDialogUserAbortRecordStatus = RecordStatus.MAP_USER_DIALOG_ABORT;

        if (event.getMAPDialog() != null) {
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
            dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            TimerID timerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            if (timerID != null)
                this.timerFacility.cancelTimer(timerID);
            if (event.getMAPDialog().getRemoteAddress() != null)
                if (event.getMAPDialog().getRemoteAddress().getGlobalTitle() != null)
                    nnn = event.getMAPDialog().getRemoteAddress().getGlobalTitle().getDigits();
            if (event.getMAPDialog().getApplicationContext() != null) {
                MAPApplicationContextName mapApplicationContextName = event.getMAPDialog().getApplicationContext().getApplicationContextName();
                MAPApplicationContextVersion mapApplicationContextVersion = event.getMAPDialog().getApplicationContext().getApplicationContextVersion();
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "ATI";
                    onDialogUserAbortRecordStatus = RecordStatus.ATI_DIALOG_U_ABORT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "SRILCS";
                    onDialogUserAbortRecordStatus = RecordStatus.SRILCS_DIALOG_U_ABORT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSL";
                    onDialogUserAbortRecordStatus = RecordStatus.PSL_DIALOG_U_ABORT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRI";
                    onDialogUserAbortRecordStatus = RecordStatus.SRI_DIALOG_U_ABORT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRISM";
                    onDialogUserAbortRecordStatus = RecordStatus.SRISM_DIALOG_U_ABORT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSI";
                    onDialogUserAbortRecordStatus = RecordStatus.PSI_DIALOG_U_ABORT;
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                }
            }
        }
        if (transaction != null)
            mobileCoreNetworkTransactions.Instance().destroy(transaction);
        this.reportLocationRequestError(MLPResponse.MLPResultType.UNSPECIFIED_ERROR, "Dialog U-ABORT", operation, targetMsisdn,
            targetImsi, referenceNumber, nnn, addNum, null, null, mlpTriggeredReportingService);
        MAPDialog mapDialog = event.getMAPDialog();
        GMLCCDRState gmlcCdrState = CDRCreationHelper.onMapDialogEventCdrInitializer(aci, this.getCDRInterface(), mapDialog);
        this.setTimer(aci);
        if (gmlcCdrState.isInitialized()) {
            gmlcCdrState.setCurlUser(curlUser);
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (dialogStartTime != null && eventTime != null) {
                Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                gmlcCdrState.setDialogDuration(dialogDuration);
            }
            gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn));
            gmlcCdrState.setImsi(new IMSIImpl(targetImsi));
            this.createCDRRecord(onDialogUserAbortRecordStatus);
        }
    }

    public void onDialogProviderAbort(DialogProviderAbort event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nRx :  onDialogProviderAbort " + event);
        }
        String operation = null, targetMsisdn = null, targetImsi = null, nnn = null, addNum = null, curlUser = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        DateTime eventTime = DateTime.now();
        DateTime dialogStartTime = null;
        RecordStatus onDialogProviderAbortRecordStatus = RecordStatus.MAP_PROVIDER_DIALOG_ABORT;

        if (event.getMAPDialog() != null) {
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
            dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            TimerID timerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            if (timerID != null)
                this.timerFacility.cancelTimer(timerID);
            if (event.getMAPDialog().getRemoteAddress() != null)
                if (event.getMAPDialog().getRemoteAddress().getGlobalTitle() != null)
                    nnn = event.getMAPDialog().getRemoteAddress().getGlobalTitle().getDigits();
            if (event.getMAPDialog().getApplicationContext() != null) {
                MAPApplicationContextName mapApplicationContextName = event.getMAPDialog().getApplicationContext().getApplicationContextName();
                MAPApplicationContextVersion mapApplicationContextVersion = event.getMAPDialog().getApplicationContext().getApplicationContextVersion();
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "ATI";
                    onDialogProviderAbortRecordStatus = RecordStatus.ATI_DIALOG_P_ABORT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "SRILCS";
                    onDialogProviderAbortRecordStatus = RecordStatus.SRILCS_DIALOG_P_ABORT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSL";
                    onDialogProviderAbortRecordStatus = RecordStatus.PSL_DIALOG_P_ABORT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRI";
                    onDialogProviderAbortRecordStatus = RecordStatus.SRI_DIALOG_P_ABORT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRISM";
                    onDialogProviderAbortRecordStatus = RecordStatus.SRISM_DIALOG_P_ABORT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSI";
                    onDialogProviderAbortRecordStatus = RecordStatus.PSI_DIALOG_P_ABORT;
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                }
            }
        }
        if (transaction != null)
            mobileCoreNetworkTransactions.Instance().destroy(transaction);
        this.reportLocationRequestError(MLPResponse.MLPResultType.UNSPECIFIED_ERROR, "Dialog P-ABORT", operation, targetMsisdn,
            targetImsi, referenceNumber, nnn, addNum, null, null, mlpTriggeredReportingService);
        MAPDialog mapDialog = event.getMAPDialog();
        GMLCCDRState gmlcCdrState = CDRCreationHelper.onMapDialogEventCdrInitializer(aci, this.getCDRInterface(), mapDialog);
        this.setTimer(aci);
        if (gmlcCdrState.isInitialized()) {
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (dialogStartTime != null && eventTime != null) {
                Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                gmlcCdrState.setDialogDuration(dialogDuration);
            }
            gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn));
            gmlcCdrState.setImsi(new IMSIImpl(targetImsi));
            this.createCDRRecord(onDialogProviderAbortRecordStatus);
        }
    }

    public void onDialogClose(DialogClose event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nReceived onDialogClose = " + event);
        }
    }

    public void onDialogNotice(DialogNotice event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nReceived onDialogNotice = " + event);
        }
    }

    public void onDialogRelease(DialogRelease event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nReceived onDialogRelease = " + event);
        }
    }

    public void onInvokeTimeout(InvokeTimeout event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nReceived onInvokeTimeout = " + event);
        }
        String operation = null, targetMsisdn = null, targetImsi = null, nnn = null, addNum = null, curlUser = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        DateTime eventTime = DateTime.now();
        DateTime dialogStartTime = null;
        RecordStatus onInvokeTimeoutRecordStatus = RecordStatus.FAILED_INVOKE_TIMEOUT;

        if (event.getMAPDialog() != null) {
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
            dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            TimerID timerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            if (timerID != null)
                this.timerFacility.cancelTimer(timerID);
            if (event.getMAPDialog().getApplicationContext() != null) {
                MAPApplicationContextName mapApplicationContextName = event.getMAPDialog().getApplicationContext().getApplicationContextName();
                MAPApplicationContextVersion mapApplicationContextVersion = event.getMAPDialog().getApplicationContext().getApplicationContextVersion();
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "ATI";
                    onInvokeTimeoutRecordStatus = RecordStatus.ATI_INVOKE_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "SRILCS";
                    onInvokeTimeoutRecordStatus = RecordStatus.SRILCS_INVOKE_TIMEOUT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSL";
                    onInvokeTimeoutRecordStatus = RecordStatus.PSL_INVOKE_TIMEOUT;
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRI";
                    onInvokeTimeoutRecordStatus = RecordStatus.SRI_INVOKE_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiNNN");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRISM";
                    onInvokeTimeoutRecordStatus = RecordStatus.SRISM_INVOKE_TIMEOUT;
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiNNN");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSI";
                    onInvokeTimeoutRecordStatus = RecordStatus.PSI_INVOKE_TIMEOUT;
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiNNN");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                }
            }
        }
        if (transaction != null)
            mobileCoreNetworkTransactions.Instance().destroy(transaction);
        this.reportLocationRequestError(MLPResponse.MLPResultType.TIMEOUT, "Invoke timeout", operation, targetMsisdn, targetImsi,
            referenceNumber, nnn, addNum, null, null, mlpTriggeredReportingService);
        MAPDialog mapDialog = event.getMAPDialog();
        GMLCCDRState gmlcCdrState = CDRCreationHelper.onMapDialogEventCdrInitializer(aci, this.getCDRInterface(), mapDialog);
        this.setTimer(aci);
        if (gmlcCdrState.isInitialized()) {
            gmlcCdrState.setCurlUser(curlUser);
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (dialogStartTime != null && eventTime != null) {
                Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                gmlcCdrState.setDialogDuration(dialogDuration);
            }
            gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn));
            gmlcCdrState.setImsi(new IMSIImpl(targetImsi));
            this.createCDRRecord(onInvokeTimeoutRecordStatus);
        }
    }

    public void onErrorComponent(ErrorComponent event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nReceived onErrorComponent = " + event);
        }
        String operation = null, targetMsisdn = null, targetImsi = null, nnn = null, addNum = null, curlUser = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        String mapErrorComponentString;
        DateTime eventTime = DateTime.now();
        DateTime dialogStartTime = null;
        RecordStatus onErrorComponentRecordStatus = RecordStatus.MAP_COMPONENT_ERROR;

        if (event.getMAPDialog() != null) {
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
            dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            TimerID timerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            if (timerID != null)
                this.timerFacility.cancelTimer(timerID);
            if (event.getMAPDialog().getRemoteAddress() != null)
                if (event.getMAPDialog().getRemoteAddress().getGlobalTitle() != null)
                    nnn = event.getMAPDialog().getRemoteAddress().getGlobalTitle().getDigits();
            if (event.getMAPDialog().getApplicationContext() != null) {
                MAPApplicationContextName mapApplicationContextName = event.getMAPDialog().getApplicationContext().getApplicationContextName();
                MAPApplicationContextVersion mapApplicationContextVersion = event.getMAPDialog().getApplicationContext().getApplicationContextVersion();
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "ATI";
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "SRILCS";
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSL";
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRI";
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRISM";
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSI";
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                }
            }
        }
        MAPErrorMessage mapErrorMessage = event.getMAPErrorMessage();
        long error_code = mapErrorMessage.getErrorCode().longValue();
        MLPResponse.MLPResultType mlpResultType;
        switch ((int) error_code) {
            case 1:
                mapErrorComponentString = "Unknown Subscriber";
                mlpResultType = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_UNKNOWN_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_UNKNOWN_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_UNKNOWN_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_UNKNOWN_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_UNKNOWN_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_UNKNOWN_SUBSCRIBER;
                break;
            case 2:
                mapErrorComponentString = "Unknown Base Station";
                mlpResultType = MLPResponse.MLPResultType.TARGET_MOVED_TO_NEW_MSC_SGSN;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 3:
                mapErrorComponentString = "Unknown MSC";
                mlpResultType = MLPResponse.MLPResultType.TARGET_MOVED_TO_NEW_MSC_SGSN;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_UNKNOWN_MSC;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_UNKNOWN_MSC;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_UNKNOWN_MSC;
                break;
            case 5:
                mapErrorComponentString = "Unidentified Subscriber";
                mlpResultType = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_UNIDENTIFIED_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_UNIDENTIFIED_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_UNIDENTIFIED_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_UNIDENTIFIED_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_UNIDENTIFIED_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_UNIDENTIFIED_SUBSCRIBER;
                break;
            case 6:
                mapErrorComponentString = "Absent Subscriber SM";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 7:
                mapErrorComponentString = "Unknown Equipment";
                mlpResultType = MLPResponse.MLPResultType.UNSUPPORTED_VERSION;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_UNKNOWN_EQUIPMENT;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_UNKNOWN_EQUIPMENT;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_UNKNOWN_EQUIPMENT;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_UNKNOWN_EQUIPMENT;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_UNKNOWN_EQUIPMENT;
                break;
            case 8:
                mapErrorComponentString = "Roaming Not Allowed";
                mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ROAMING_NOT_ALLOWED;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ROAMING_NOT_ALLOWED;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ROAMING_NOT_ALLOWED;
                break;
            case 9:
                mapErrorComponentString = "Illegal Subscriber";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ILLEGAL_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ILLEGAL_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ILLEGAL_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ILLEGAL_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ILLEGAL_SUBSCRIBER;
                break;
            case 10:
                mapErrorComponentString = "Bearer Service Not Provisioned";
                mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_BEARER_SERVICE_NOT_PROVISIONED;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_BEARER_SERVICE_NOT_PROVISIONED;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_BEARER_SERVICE_NOT_PROVISIONED;
                break;
            case 11:
                mapErrorComponentString = "Teleservice Not Provisioned";
                mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_TELESERVICE_NOT_PROVISIONED;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_TELESERVICE_NOT_PROVISIONED;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_TELESERVICE_NOT_PROVISIONED;
                break;
            case 12:
                mapErrorComponentString = "Illegal Equipment";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ILLEGAL_EQUIPMENT;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ILLEGAL_EQUIPMENT;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ILLEGAL_EQUIPMENT;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ILLEGAL_EQUIPMENT;
                break;
            case 13:
                mapErrorComponentString = "Call Barred";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_CALL_BARRED;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 14:
                mapErrorComponentString = "Forwarding Violation";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_FORWARDING_VIOLATION;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 15:
                mapErrorComponentString = "CUG Reject";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_CUG_REJECT;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 16:
                mapErrorComponentString = "Illegal SS Operation";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 17:
                mapErrorComponentString = "SS Error Status";
                mlpResultType = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 18:
                mapErrorComponentString = "SS Not Available";
                mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 19:
                mapErrorComponentString = "SS Subscription Violation";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 20:
                mapErrorComponentString = "SS Incompatibility";
                mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 21:
                mapErrorComponentString = "Facility Not Supported";
                mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_FACILITY_NOT_SUPPORTED;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_FACILITY_NOT_SUPPORTED;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_FACILITY_NOT_SUPPORTED;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_FACILITY_NOT_SUPPORTED;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_FACILITY_NOT_SUPPORTED;
                break;
            case 22:
                mapErrorComponentString = "Ongoing Group Call";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 23:
                mapErrorComponentString = "Invalid Target Base Station";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 24:
                mapErrorComponentString = "No Radio Resource Available";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 25:
                mapErrorComponentString = "No Handover Number Available";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 26:
                mapErrorComponentString = "Subsequent Handover Failure";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 27:
                mapErrorComponentString = "Absent Subscriber";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ABSENT_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ABSENT_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ABSENT_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ABSENT_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ABSENT_SUBSCRIBER;
                break;
            case 28:
                mapErrorComponentString = "Incompatible Terminal";
                mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_INCOMPATIBLE_TERMINAL;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_INCOMPATIBLE_TERMINAL;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_INCOMPATIBLE_TERMINAL;
                break;
            case 29:
                mapErrorComponentString = "Short Term Denial";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 30:
                mapErrorComponentString = "Long Term Denial";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 31:
                mapErrorComponentString = "Subscriber Busy For MT SMS";
                mlpResultType = MLPResponse.MLPResultType.CONGESTION_IN_MOBILE_NETWORK;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 32:
                mapErrorComponentString = "SM Delivery Failure";
                mlpResultType = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 33:
                mapErrorComponentString = "Message Waiting List Full";
                mlpResultType = MLPResponse.MLPResultType.CONGESTION_IN_MOBILE_NETWORK;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 34:
                mapErrorComponentString = "System Failure";
                mlpResultType = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_SYSTEM_FAILURE;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_SYSTEM_FAILURE;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_SYSTEM_FAILURE;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_SYSTEM_FAILURE;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_SYSTEM_FAILURE;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_SYSTEM_FAILURE;
                break;
            case 35:
                mapErrorComponentString = "Data Missing";
                mlpResultType = MLPResponse.MLPResultType.FORMAT_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_DATA_MISSING;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_DATA_MISSING;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_DATA_MISSING;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_DATA_MISSING;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_DATA_MISSING;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_DATA_MISSING;
                break;
            case 36:
                mapErrorComponentString = "Unexpected Data Value";
                mlpResultType = MLPResponse.MLPResultType.FORMAT_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_UNEXPECTED_DATA_VALUE;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_UNEXPECTED_DATA_VALUE;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_UNEXPECTED_DATA_VALUE;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_UNEXPECTED_DATA_VALUE;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_UNEXPECTED_DATA_VALUE;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_UNEXPECTED_DATA_VALUE;
                break;
            case 37:
                mapErrorComponentString = "PW Registration Failure";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 38:
                mapErrorComponentString = "Negative PW Check";
                mlpResultType = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 39:
                mapErrorComponentString = "No Roaming Number Available";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 40:
                mapErrorComponentString = "Tracing Buffer Full";
                mlpResultType = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 42:
                mapErrorComponentString = "Target Cell Outside Group Call Area";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 43:
                mapErrorComponentString = "Number Of PW Attempts Violation";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 44:
                mapErrorComponentString = "Number Changed";
                mlpResultType = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_NUMBER_CHANGED;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_NUMBER_CHANGED;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_NUMBER_CHANGED;
                break;
            case 45:
                mapErrorComponentString = "Busy Subscriber";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_BUSY_SUBSCRIBER;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 46:
                mapErrorComponentString = "No Subscriber Reply";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 47:
                mapErrorComponentString = "Forwarding Failed";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 48:
                mapErrorComponentString = "OR Not Allowed";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 49:
                mapErrorComponentString = "ATI Not Allowed";
                mlpResultType = MLPResponse.MLPResultType.POSITIONING_NOT_ALLOWED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_OR_NOT_ALLOWED;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 50:
                mapErrorComponentString = "No Group Call Number Available";
                mlpResultType = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 51:
                mapErrorComponentString = "Resource Limitation";
                mlpResultType = MLPResponse.MLPResultType.QOP_NOT_ATTAINABLE;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_RESOURCE_LIMITATION;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_RESOURCE_LIMITATION;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_RESOURCE_LIMITATION;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_RESOURCE_LIMITATION;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_RESOURCE_LIMITATION;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_RESOURCE_LIMITATION;
                break;
            case 52:
                mapErrorComponentString = "Unauthorized Requesting Network";
                mlpResultType = MLPResponse.MLPResultType.POSITIONING_NOT_ALLOWED;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_UNAUTHORIZED_REQUESTING_NETWORK;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_UNAUTHORIZED_REQUESTING_NETWORK;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 53:
                mapErrorComponentString = "Unauthorized LCS Client";
                mlpResultType = MLPResponse.MLPResultType.UNAUTHORIZED_APPLICATION;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_UNAUTHORIZED_LCS_CLIENT;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_UNAUTHORIZED_LCS_CLIENT;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 54:
                mapErrorComponentString = "Position Method Failure";
                mlpResultType = MLPResponse.MLPResultType.POSITION_METHOD_FAILURE;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_POSITION_METHOD_FAILURE;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 58:
                mapErrorComponentString = "Unknown or Unreachable LCS Client";
                mlpResultType = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 59:
                mapErrorComponentString = "MM Event Not Supported";
                mlpResultType = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 60:
                mapErrorComponentString = "ATSI Not Allowed";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 61:
                mapErrorComponentString = "ATM Not Allowed";
                mlpResultType = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 62:
                mapErrorComponentString = "Information Not Available";
                mlpResultType = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 71:
                mapErrorComponentString = "Unknown Alphabet";
                mlpResultType = MLPResponse.MLPResultType.UNSUPPORTED_VERSION;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            case 72:
                mapErrorComponentString = "USSD Busy";
                mlpResultType = MLPResponse.MLPResultType.CONGESTION_IN_MOBILE_NETWORK;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
            default:
                mapErrorComponentString = "System Failure";
                mlpResultType = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                if (operation.equalsIgnoreCase("ATI"))
                    onErrorComponentRecordStatus = RecordStatus.ATI_ERROR;
                else if (operation.equalsIgnoreCase("SRILCS"))
                    onErrorComponentRecordStatus = RecordStatus.SRILCS_ERROR;
                else if (operation.equalsIgnoreCase("PSL"))
                    onErrorComponentRecordStatus = RecordStatus.PSL_ERROR;
                else if (operation.equalsIgnoreCase("SRI"))
                    onErrorComponentRecordStatus = RecordStatus.SRI_ERROR;
                else if (operation.equalsIgnoreCase("SRISM"))
                    onErrorComponentRecordStatus = RecordStatus.SRISM_ERROR;
                else if (operation.equalsIgnoreCase("PSI"))
                    onErrorComponentRecordStatus = RecordStatus.PSI_ERROR;
                break;
        }
        if (transaction != null)
            mobileCoreNetworkTransactions.Instance().destroy(transaction);
        this.reportLocationRequestError(mlpResultType, "MAP Component error: " + mapErrorComponentString + ", MAP error code value: " + error_code,
            operation, targetMsisdn, targetImsi, referenceNumber, nnn, addNum, null, null, mlpTriggeredReportingService);
        MAPDialog mapDialog = event.getMAPDialog();
        GMLCCDRState gmlcCdrState = CDRCreationHelper.onMapDialogEventCdrInitializer(aci, this.getCDRInterface(), mapDialog);
        this.setTimer(aci);
        if (gmlcCdrState.isInitialized()) {
            gmlcCdrState.setCurlUser(curlUser);
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (dialogStartTime != null && eventTime != null) {
                Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                gmlcCdrState.setDialogDuration(dialogDuration);
            }
            gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn));
            gmlcCdrState.setImsi(new IMSIImpl(targetImsi));
            if (gmlcCdrState != null) {
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setStatusCode(error_code);
                }
            }
            this.createCDRRecord(onErrorComponentRecordStatus);
        }
    }

    public void onRejectComponent(RejectComponent event, ActivityContextInterface aci) {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("\nRx : onRejectComponent " + event);
        }
        String operation = null, targetMsisdn = null, targetImsi = null, nnn = null, addNum = null, curlUser = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        DateTime eventTime = DateTime.now();
        DateTime dialogStartTime = null;
        RecordStatus onRejectComponentRecordStatus = null;
        InvokeProblemType onRejectComponentInvokeProblemType = null;
        MLPResponse.MLPResultType mlpResultType = MLPResponse.MLPResultType.SYSTEM_FAILURE;
        if (event.getMAPDialog() != null) {
            transaction = mobileCoreNetworkTransactions.Instance().getTransactionId(event.getMAPDialog().getLocalDialogId());
            dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
            TimerID timerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
            mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
            if (timerID != null)
                this.timerFacility.cancelTimer(timerID);
            if (event.getMAPDialog().getRemoteAddress() != null)
                if (event.getMAPDialog().getRemoteAddress().getGlobalTitle() != null)
                    nnn = event.getMAPDialog().getRemoteAddress().getGlobalTitle().getDigits();
            if (event.getMAPDialog().getApplicationContext() != null) {
                MAPApplicationContextName mapApplicationContextName = event.getMAPDialog().getApplicationContext().getApplicationContextName();
                MAPApplicationContextVersion mapApplicationContextVersion = event.getMAPDialog().getApplicationContext().getApplicationContextVersion();
                if (mapApplicationContextName == MAPApplicationContextName.anyTimeEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "ATI";
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "atiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcGatewayContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "SRILCS";
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationSvcEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSL";
                    referenceNumber = (Integer) mobileCoreNetworkTransactions.getValue(transaction, "pslLcsReferenceNumber");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslImsi");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    nnn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslNNN");
                    addNum = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "pslRemoteAddress");
                }
                if (mapApplicationContextName == MAPApplicationContextName.locationInfoRetrievalContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRI";
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.shortMsgGatewayContext &&
                    (mapApplicationContextVersion == MAPApplicationContextVersion.version3 || mapApplicationContextVersion == MAPApplicationContextVersion.version2)) {
                    operation = "SRISM";
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                }
                if (mapApplicationContextName == MAPApplicationContextName.subscriberInfoEnquiryContext && mapApplicationContextVersion == MAPApplicationContextVersion.version3) {
                    operation = "PSI";
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    targetMsisdn = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiMsisdn");
                    targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriForSMImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "sriImsi");
                    if (targetImsi == null)
                        targetImsi = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "psiOnlyImsi");
                }
            }
        }
        if (transaction != null)
            mobileCoreNetworkTransactions.Instance().destroy(transaction);
        if (event.getProblem() != null) {
            onRejectComponentInvokeProblemType = event.getProblem().getInvokeProblemType();
            if (event.getProblem().getInvokeProblemType() != null)
                switch ((int) event.getProblem().getInvokeProblemType().getType()) {
                    case 0:
                        mlpResultType = MLPResponse.MLPResultType.INVALID_PROTOCOL_ELEMENT_ATTRIBUTE_VALUE; // DuplicateInvokeID
                        break;
                    case 1:
                        mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED; // UnrecognizedOperation
                        break;
                    case 2:
                        mlpResultType = MLPResponse.MLPResultType.FORMAT_ERROR; // MistypedParameter
                        break;
                    case 3:
                        mlpResultType = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED; // ResourceLimitation
                        break;
                    case 4:
                        mlpResultType = MLPResponse.MLPResultType.SYSTEM_FAILURE; // InitiatingRelease
                        break;
                    case 5:
                        mlpResultType = MLPResponse.MLPResultType.INVALID_PROTOCOL_ELEMENT_ATTRIBUTE_VALUE; // UnrechognizedLinkedID
                        break;
                    case 6:
                        mlpResultType = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_ATTRIBUTE_VALUE_NOT_SUPPORTED; // LinkedResponseUnexpected
                        break;
                    case 7:
                        mlpResultType = MLPResponse.MLPResultType.INVALID_PROTOCOL_ELEMENT_VALUE; // LinkedResponseUnexpected
                        break;
                    default:
                        mlpResultType = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                        break;
                }

            this.reportLocationRequestError(mlpResultType, "Rejected, event type: " + event.getEventTypeName() + ", " +
                    "invoke problem: " + onRejectComponentInvokeProblemType.name(), operation, targetMsisdn, targetImsi, referenceNumber,
                nnn, addNum, null, null, mlpTriggeredReportingService);
        } else {
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Rejected, event type: "
                    + event.getEventTypeName(), operation, targetMsisdn, targetImsi, referenceNumber, nnn, addNum, null, null,
                mlpTriggeredReportingService);
        }

        MAPDialog mapDialog = event.getMAPDialog();
        GMLCCDRState gmlcCdrState = CDRCreationHelper.onMapDialogEventCdrInitializer(aci, this.getCDRInterface(), mapDialog);
        this.setTimer(aci);
        if (gmlcCdrState.isInitialized()) {
            gmlcCdrState.setCurlUser(curlUser);
            gmlcCdrState.setDialogStartTime(dialogStartTime);
            if (dialogStartTime != null && eventTime != null) {
                Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                gmlcCdrState.setDialogDuration(dialogDuration);
            }
            gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn));
            gmlcCdrState.setImsi(new IMSIImpl(targetImsi));
            if (onRejectComponentInvokeProblemType != null) {
                if (onRejectComponentInvokeProblemType == InvokeProblemType.DuplicateInvokeID) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_DUPLICATE_INVOKE_ID;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_DUPLICATE_INVOKE_ID;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_DUPLICATE_INVOKE_ID;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_DUPLICATE_INVOKE_ID;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_DUPLICATE_INVOKE_ID;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_DUPLICATE_INVOKE_ID;
                } else if (onRejectComponentInvokeProblemType == InvokeProblemType.UnrecognizedOperation) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_UNRECOGNIZED_OPERATION;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_UNRECOGNIZED_OPERATION;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_UNRECOGNIZED_OPERATION;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_UNRECOGNIZED_OPERATION;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_UNRECOGNIZED_OPERATION;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_UNRECOGNIZED_OPERATION;
                } else if (onRejectComponentInvokeProblemType == InvokeProblemType.MistypedParameter) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_MISTYPED_PARAMETER;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_MISTYPED_PARAMETER;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_MISTYPED_PARAMETER;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_MISTYPED_PARAMETER;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_MISTYPED_PARAMETER;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_MISTYPED_PARAMETER;
                } else if (onRejectComponentInvokeProblemType == InvokeProblemType.ResourceLimitation) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_RESOURCE_LIMITATION;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_RESOURCE_LIMITATION;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_RESOURCE_LIMITATION;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_RESOURCE_LIMITATION;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_RESOURCE_LIMITATION;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_RESOURCE_LIMITATION;
                } else if (onRejectComponentInvokeProblemType == InvokeProblemType.InitiatingRelease) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_INITIATING_RELEASE;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_INITIATING_RELEASE;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_INITIATING_RELEASE;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_INITIATING_RELEASE;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_INITIATING_RELEASE;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_INITIATING_RELEASE;
                } else if (onRejectComponentInvokeProblemType == InvokeProblemType.UnrechognizedLinkedID) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_UNRECOGNIZED_LINKED_ID;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_UNRECOGNIZED_LINKED_ID;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_UNRECOGNIZED_LINKED_ID;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_UNRECOGNIZED_LINKED_ID;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_UNRECOGNIZED_LINKED_ID;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_UNRECOGNIZED_LINKED_ID;
                } else if (onRejectComponentInvokeProblemType == InvokeProblemType.LinkedResponseUnexpected) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_LINKED_RESPONSE_UNEXPECTED;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_LINKED_RESPONSE_UNEXPECTED;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_LINKED_RESPONSE_UNEXPECTED;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_LINKED_RESPONSE_UNEXPECTED;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_LINKED_RESPONSE_UNEXPECTED;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_LINKED_RESPONSE_UNEXPECTED;
                } else if (onRejectComponentInvokeProblemType == InvokeProblemType.UnexpectedLinkedOperation) {
                    if (operation.equalsIgnoreCase("ATI"))
                        onRejectComponentRecordStatus = RecordStatus.ATI_UNEXPECTED_LINKED_OPERATION;
                    if (operation.equalsIgnoreCase("SRILCS"))
                        onRejectComponentRecordStatus = RecordStatus.SRILCS_UNEXPECTED_LINKED_OPERATION;
                    if (operation.equalsIgnoreCase("PSL"))
                        onRejectComponentRecordStatus = RecordStatus.PSL_UNEXPECTED_LINKED_OPERATION;
                    if (operation.equalsIgnoreCase("SRI"))
                        onRejectComponentRecordStatus = RecordStatus.SRI_UNEXPECTED_LINKED_OPERATION;
                    if (operation.equalsIgnoreCase("SRISM"))
                        onRejectComponentRecordStatus = RecordStatus.SRISM_UNEXPECTED_LINKED_OPERATION;
                    if (operation.equalsIgnoreCase("PSI"))
                        onRejectComponentRecordStatus = RecordStatus.PSI_UNEXPECTED_LINKED_OPERATION;
                }
            }
            this.createCDRRecord(onRejectComponentRecordStatus);
        }
    }

    public String handleRecordAndLocationReportOnMapError(MAPErrorMessage mapErrorMessage, MLPResponse.MLPResultType mlpRespResult,
                                                          String msisdn, String imsi, String mapOperation, Integer refNumber,
                                                          String networkNodeNumber, String addNetworkNodeNumber, GMLCCDRState gmlcCdrState,
                                                          Boolean mlpTriggeredReportingService) {

        if (gmlcCdrState != null) {
            if (gmlcCdrState.isInitialized()) {
                if (mapErrorMessage != null)
                    gmlcCdrState.setStatusCode(mapErrorMessage.getErrorCode());
            }
        }

        String mlpClientErrorMessage;
        /*** UNKNOWN_SUBSCRIBER ***/
        if (mapErrorMessage.getErrorCode() == 1) {
            mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
            mlpClientErrorMessage = "UNKNOWN SUBSCRIBER on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("ATI"))
                this.createCDRRecord(RecordStatus.ATI_UNKNOWN_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_UNKNOWN_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_UNKNOWN_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_UNKNOWN_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_UNKNOWN_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_UNKNOWN_SUBSCRIBER);
        }
        /*** UNKNOWN_MSC ***/
        else if (mapErrorMessage.getErrorCode() == 3) {
            mlpRespResult = MLPResponse.MLPResultType.TARGET_MOVED_TO_NEW_MSC_SGSN;
            mlpClientErrorMessage = "UNKNOWN MSC on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_UNKNOWN_MSC);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_UNKNOWN_MSC);
        }
        /*** NUMBER_CHANGED ***/
        else if (mapErrorMessage.getErrorCode() == 4) {
            mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
            mlpClientErrorMessage = "NUMBER CHANGED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_NUMBER_CHANGED);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_NUMBER_CHANGED);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_NUMBER_CHANGED);
        }
        /*** UNIDENTIFIED_SUBSCRIBER ***/
        else if (mapErrorMessage.getErrorCode() == 5) {
            mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
            mlpClientErrorMessage = "UNIDENTIFIED SUBSCRIBER on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_UNIDENTIFIED_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_UNIDENTIFIED_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_UNIDENTIFIED_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_UNIDENTIFIED_SUBSCRIBER);
        }
        /*** SM_ABSENT_SUBSCRIBER ***/
        else if (mapErrorMessage.getErrorCode() == 6) {
            mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
            mlpClientErrorMessage = "ABSENT SUBSCRIBER SM on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            this.createCDRRecord(RecordStatus.SRISM_ABSENT_SUBSCRIBER);
        }
        /*** UNKNOWN_EQUIPMENT ***/
        else if (mapErrorMessage.getErrorCode() == 7) {
            mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
            mlpClientErrorMessage = "UNKNOWN EQUIPMENT on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_UNKNOWN_EQUIPMENT);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_UNKNOWN_EQUIPMENT);
        }
        /*** ROAMING_NOT_ALLOWED ***/
        if (mapErrorMessage.getErrorCode() == 8) {
            mlpRespResult = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
            mlpClientErrorMessage = "ROAMING NOT ALLOWED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_ROAMING_NOT_ALLOWED);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_ROAMING_NOT_ALLOWED);
        }
        /*** ILLEGAL_SUBSCRIBER ***/
        else if (mapErrorMessage.getErrorCode() == 9) {
            mlpRespResult = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
            mlpClientErrorMessage = "ILLEGAL SUBSCRIBER on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_ILLEGAL_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_ILLEGAL_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_ILLEGAL_SUBSCRIBER);
        }
        /*** BEARER_SERVICE_NOT_PROVISIONED ***/
        else if (mapErrorMessage.getErrorCode() == 10) {
            mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
            mlpClientErrorMessage = "BEARER SERVICE NOT PROVISIONED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_BEARER_SERVICE_NOT_PROVISIONED);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_BEARER_SERVICE_NOT_PROVISIONED);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_BEARER_SERVICE_NOT_PROVISIONED);
        }
        /*** TELESERVICE NOT PROVISIONED ***/
        if (mapErrorMessage.getErrorCode() == 11) {
            mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
            mlpClientErrorMessage = "TELESERVICE NOT PROVISIONED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_TELESERVICE_NOT_PROVISIONED);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_TELESERVICE_NOT_PROVISIONED);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_TELESERVICE_NOT_PROVISIONED);
        }
        /*** ILLEGAL_EQUIPMENT ***/
        else if (mapErrorMessage.getErrorCode() == 12) {
            mlpRespResult = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
            mlpClientErrorMessage = "ILLEGAL EQUIPMENT on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_ILLEGAL_EQUIPMENT);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_ILLEGAL_EQUIPMENT);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_ILLEGAL_EQUIPMENT);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_ILLEGAL_EQUIPMENT);
        }
        /*** CALL_BARRED ***/
        else if (mapErrorMessage.getErrorCode() == 13) {
            mlpRespResult = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
            mlpClientErrorMessage = "CALL BARRED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_CALL_BARRED);
        }
        /*** FORWARDING_VIOLATION ***/
        else if (mapErrorMessage.getErrorCode() == 14) {
            mlpRespResult = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
            mlpClientErrorMessage = "FORWARDING VIOLATION on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_FORWARDING_VIOLATION);
        }
        /*** CUG_REJECT ***/
        else if (mapErrorMessage.getErrorCode() == 15) {
            mlpRespResult = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
            mlpClientErrorMessage = "CUG REJECT on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_CUG_REJECT);
        }
        /*** FACILITY_NOT_SUPPORTED ***/
        else if (mapErrorMessage.getErrorCode() == 21) {
            mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
            mlpClientErrorMessage = "FACILITY NOT SUPPORTED on SRISM, MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_FACILITY_NOT_SUPPORTED);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_FACILITY_NOT_SUPPORTED);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_FACILITY_NOT_SUPPORTED);
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_FACILITY_NOT_SUPPORTED);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_FACILITY_NOT_SUPPORTED);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_FACILITY_NOT_SUPPORTED);
        }
        /*** ABSENT_SUBSCRIBER ***/
        else if (mapErrorMessage.getErrorCode() == 27) {
            mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
            mlpClientErrorMessage = "ABSENT SUBSCRIBER on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_ABSENT_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_ABSENT_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_ABSENT_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_ABSENT_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_ABSENT_SUBSCRIBER);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_FACILITY_NOT_SUPPORTED);
        }
        /*** INCOMPATIBLE_TERMINAL ***/
        else if (mapErrorMessage.getErrorCode() == 28) {
            mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
            mlpClientErrorMessage = "INCOMPATIBLE TERMINAL on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_INCOMPATIBLE_TERMINAL);
        }
        /*** SYSTEM_FAILURE ***/
        if (mapErrorMessage.getErrorCode() == 34) {
            mlpRespResult = MLPResponse.MLPResultType.SYSTEM_FAILURE;
            mlpClientErrorMessage = "SYSTEM FAILURE on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("ATI"))
                this.createCDRRecord(RecordStatus.ATI_SYSTEM_FAILURE);
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_SYSTEM_FAILURE);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_SYSTEM_FAILURE);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_SYSTEM_FAILURE);
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_SYSTEM_FAILURE);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_SYSTEM_FAILURE);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_SYSTEM_FAILURE);
        }
        /*** DATA_MISSING ***/
        if (mapErrorMessage.getErrorCode() == 35) {
            mlpRespResult = MLPResponse.MLPResultType.FORMAT_ERROR;
            mlpClientErrorMessage = "DATA MISSING on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("ATI"))
                this.createCDRRecord(RecordStatus.ATI_DATA_MISSING);
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_DATA_MISSING);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_DATA_MISSING);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_DATA_MISSING);
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_DATA_MISSING);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_DATA_MISSING);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_DATA_MISSING);
        }
        /*** UNEXPECTED_DATA_VALUE ***/
        else if (mapErrorMessage.getErrorCode() == 36) {
            mlpRespResult = MLPResponse.MLPResultType.FORMAT_ERROR;
            mlpClientErrorMessage = "UNEXPECTED DATA VALUE on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("ATI"))
                this.createCDRRecord(RecordStatus.ATI_UNEXPECTED_DATA_VALUE);
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_UNEXPECTED_DATA_VALUE);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_UNEXPECTED_DATA_VALUE);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_UNEXPECTED_DATA_VALUE);
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_UNEXPECTED_DATA_VALUE);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_UNEXPECTED_DATA_VALUE);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_UNEXPECTED_DATA_VALUE);
        }
        /*** NUMBER_CHANGED ***/
        else if (mapErrorMessage.getErrorCode() == 44) {
            mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
            mlpClientErrorMessage = "NUMBER CHANGED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_NUMBER_CHANGED);
        }
        /*** BUSY_SUBSCRIBER ***/
        else if (mapErrorMessage.getErrorCode() == 45) {
            mlpRespResult = MLPResponse.MLPResultType.CONGESTION_IN_MOBILE_NETWORK;
            mlpClientErrorMessage = "BUSY SUBSCRIBER on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_BUSY_SUBSCRIBER);
        }
        /*** OR_NOT_ALLOWED ***/
        else if (mapErrorMessage.getErrorCode() == 48) {
            mlpRespResult = MLPResponse.MLPResultType.DISALLOWED_BY_LOCAL_REGULATIONS;
            mlpClientErrorMessage = "OR NOT ALLOWED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_OR_NOT_ALLOWED);
        }
        /*** ATI_NOT_ALLOWED ***/
        else if (mapErrorMessage.getErrorCode() == 49) {
            mlpRespResult = MLPResponse.MLPResultType.POSITIONING_NOT_ALLOWED;
            mlpClientErrorMessage = "ATI NOT ALLOWED, MAP error code: " + mapErrorMessage.getErrorCode();
            this.createCDRRecord(RecordStatus.ATI_NOT_ALLOWED);
        }
        /*** RESOURCE_LIMITATION ***/
        else if (mapErrorMessage.getErrorCode() == 51) {
            mlpRespResult = MLPResponse.MLPResultType.QOP_NOT_ATTAINABLE;
            mlpClientErrorMessage = "RESOURCE LIMITATION on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_RESOURCE_LIMITATION);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_RESOURCE_LIMITATION);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_RESOURCE_LIMITATION);
        }
        /*** UNAUTHORIZED_REQUESTING_NETWORK ***/
        else if (mapErrorMessage.getErrorCode() == 52) {
            mlpRespResult = MLPResponse.MLPResultType.POSITIONING_NOT_ALLOWED;
            mlpClientErrorMessage = "SYSTEM_FAILURE on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_UNAUTHORIZED_REQUESTING_NETWORK);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_UNAUTHORIZED_REQUESTING_NETWORK);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_UNAUTHORIZED_REQUESTING_NETWORK);
        }
        /*** UNAUTHORIZED_LCS_CLIENT ***/
        else if (mapErrorMessage.getErrorCode() == 53) {
            mlpRespResult = MLPResponse.MLPResultType.UNAUTHORIZED_APPLICATION;
            mlpClientErrorMessage = "UNAUTHORIZED LCS CLIENT on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_UNAUTHORIZED_LCS_CLIENT);
        }
        /*** POSITION_METHOD_FAILURE ***/
        else if (mapErrorMessage.getErrorCode() == 54) {
            mlpRespResult = MLPResponse.MLPResultType.POSITION_METHOD_FAILURE;
            mlpClientErrorMessage = "POSITION METHOD FAILURE on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_POSITION_METHOD_FAILURE);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_POSITION_METHOD_FAILURE);
        }
        /*** UNKNOWN_OR_UNREACHABLE_LCS_CLIENT ***/
        else if (mapErrorMessage.getErrorCode() == 58) {
            mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
            mlpClientErrorMessage = "UNKNOWN OR UNREACHABLE LCS CLIENT on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_UNKNOWN_OR_UNREACHABLE_LCS_CLIENT);
        }
        /*** MM_EVENT_NOT_SUPPORTED ***/
        else if (mapErrorMessage.getErrorCode() == 59) {
            mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
            mlpClientErrorMessage = "MM EVENT NOT SUPPORTED on " + mapOperation + ", MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_MM_EVENT_NOT_SUPPORTED);
        } else {
            mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
            mlpClientErrorMessage = "MAP " + mapOperation + " ERROR, MAP error code: " + mapErrorMessage.getErrorCode();
            if (mapOperation.equalsIgnoreCase("ATI"))
                this.createCDRRecord(RecordStatus.ATI_ERROR);
            if (mapOperation.equalsIgnoreCase("SRI"))
                this.createCDRRecord(RecordStatus.SRI_ERROR);
            if (mapOperation.equalsIgnoreCase("SRISM"))
                this.createCDRRecord(RecordStatus.SRISM_ERROR);
            if (mapOperation.equalsIgnoreCase("PSI"))
                this.createCDRRecord(RecordStatus.PSI_ERROR);
            if (mapOperation.equalsIgnoreCase("SRILCS"))
                this.createCDRRecord(RecordStatus.SRILCS_ERROR);
            if (mapOperation.equalsIgnoreCase("PSL"))
                this.createCDRRecord(RecordStatus.PSL_ERROR);
            if (mapOperation.equalsIgnoreCase("SLR"))
                this.createCDRRecord(RecordStatus.SLR_ERROR);
        }

        if (!mapOperation.equalsIgnoreCase("SLR"))
            this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, mapOperation, msisdn, imsi, refNumber, networkNodeNumber,
                addNetworkNodeNumber, null, null, mlpTriggeredReportingService);

        return mlpClientErrorMessage;
    }


    //////////////////////////
    // LTE Events handlers //
    /////////////////////////

    /**
     * LTE Location Services
     * SLh and SLg Diameter-based interfaces Events for LCS according to 3GPP TS 29.172 / 29.173
     */

    /**
     * SLh RIR Event
     */
    public void onLCSRoutingInfoRequest(LCSRoutingInfoRequest rirEvent, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onLCSRoutingInfoRequest = " + rirEvent);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onLCSRoutingInfoRequest=%s", rirEvent), e);
        }
    }

    /**
     * SLh RIA Event
     */
    public void onLCSRoutingInfoAnswer(LCSRoutingInfoAnswer riaEvent, ActivityContextInterface aci) {

        String imsi = null, msisdnAddress = null, curlUser = null, httpRequestType = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                logger.fine("\n\nReceived SLh RIA with session Id: " + riaEvent.getSessionId() + ", host '" + riaEvent.getOriginHost()
                    + "', realm '" + riaEvent.getOriginRealm() + "'");
                this.logger.fine("\nonLCSRoutingInfoAnswer event details : " + riaEvent);
            }
            net.java.slee.resource.diameter.base.events.avp.DiameterIdentity riaOriginHost, riaOriginRealm, gmlcHost, gmlcRealm, destHost = null, destRealm = null;
            if (riaEvent != null) {
                riaOriginHost = riaEvent.getOriginHost();
                riaOriginRealm = riaEvent.getOriginRealm();
            } else {
                String origHost = gmlcPropertiesManagement.getDiameterDestHost();
                String origRealm = gmlcPropertiesManagement.getDiameterDestRealm();
                riaOriginHost = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(origHost);
                riaOriginRealm = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(origRealm);
            }
            SLhRiaAvpValues slhRiaAvpValues = new SLhRiaAvpValues();
            byte[] msisdn;

            try {

                MLPResponse.MLPResultType mlpRespResult = null;
                String mlpClientErrorMessage = null;
                gmlcHost = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginHost());
                gmlcRealm = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginRealm());

                // CDR initialization
                GMLCCDRState gmlcCdrState = CDRCreationHelper.slhSlgCdrInitializer(aci, this.getCDRInterface(), riaEvent, null, null,
                    riaOriginHost, riaOriginRealm, gmlcHost, gmlcRealm);
                // Set timer last
                this.setTimer(aci);

                if (riaEvent != null) {

                    transaction = mobileCoreNetworkTransactions.getTransactionId(riaEvent.getSessionId());
                    if (transaction == null) {
                        throw new SLhException("No transaction found from RIA Diameter Session Id");
                    }
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    httpRequestType = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "httpRequestType");
                    DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
                    mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
                    gmlcCdrState.setDialogStartTime(dialogStartTime);
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setCurlUser(curlUser);
                        if (dialogStartTime != null && eventTime != null) {
                            Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                            gmlcCdrState.setDialogDuration(dialogDuration);
                        }
                    }
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setCurlUser(curlUser);
                    }
                    TimerID riaTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
                    if (riaTimerID != null)
                        this.timerFacility.cancelTimer(riaTimerID);
                    SLhRirAvpValues slhRirRequestValues = (SLhRirAvpValues) mobileCoreNetworkTransactions.getValue(transaction, "slhRirRequestValues");
                    if (slhRirRequestValues != null) {
                        if (slhRirRequestValues.plrMsisdn != null) {
                            byte[] tbcdMsisdn = parseTBCD(slhRirRequestValues.plrMsisdn);
                            msisdnAddress = toTBCDString(tbcdMsisdn);
                        }
                        if (slhRirRequestValues.getUserName() != null)
                            imsi = slhRirRequestValues.getUserName();
                        if (slhRirRequestValues.plrLcsReferenceNumber != null)
                            referenceNumber = slhRirRequestValues.plrLcsReferenceNumber;
                    }

                    long resultCode = riaEvent.getResultCode();
                    ExperimentalResultAvp experimentalResultAvp = riaEvent.getExperimentalResult();
                    if (experimentalResultAvp != null)
                        resultCode = experimentalResultAvp.getExperimentalResultCode();

                    if (resultCode == 2001) {

                        imsi = riaEvent.getUserName();
                        if (imsi != null) {
                            slhRiaAvpValues.setUserName(imsi);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLCSRoutingInfoAnswer: received User-Name AVP (IMSI): " + imsi);
                            }
                        }/* else {
                            imsi = Arrays.stream(riaEvent.getAvps()).filter(filter -> filter.getCode() == 1).findFirst().get().stringValue();
                            this.logger.info("\nonLCSRoutingInfoAnswer: received User-Name AVP (IMSI): " + imsi);
                        }*/
                        msisdn = riaEvent.getMSISDN();
                        if (msisdn != null) {
                            slhRiaAvpValues.setMsisdn(msisdn);
                            msisdnAddress = AVPHandler.tbcd2IsdnAddressString(msisdn).getAddress();
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLCSRoutingInfoAnswer: received MSISDN AVP: " + msisdn);
                            }
                        }
                        byte[] lmsi = riaEvent.getLMSI();
                        if (lmsi != null) {
                            slhRiaAvpValues.setLmsi(lmsi);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLCSRoutingInfoAnswer: received LMSI AVP: " + lmsi);
                            }
                        }
                        byte[] sgsnNumber, mscNumber;
                        sgsnNumber = mscNumber = null;
                        DiameterIdentity sgsnName, sgsnRealm, mmeName, mmeRealm, tgppAAAServerName;
                        sgsnName = sgsnRealm = mmeName = mmeRealm = tgppAAAServerName = null;
                        long lcsCapabilitiesSets = -1;
                        Address gmlcAddress = null;
                        ServingNodeAvp servingNodeAvp = riaEvent.getServingNode();
                        if (servingNodeAvp != null) {
                            slhRiaAvpValues.setServingNodeAvp(servingNodeAvp);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLCSRoutingInfoAnswer: received Serving-Node AVP: " + servingNodeAvp);
                            }
                            sgsnNumber = servingNodeAvp.getSGSNNumber();
                            sgsnName = servingNodeAvp.getSGSNName();
                            sgsnRealm = servingNodeAvp.getSGSNRealm();
                            mmeName = servingNodeAvp.getMMEName();
                            mmeRealm = servingNodeAvp.getMMERealm();
                            mscNumber = servingNodeAvp.getMSCNumber();
                            tgppAAAServerName = servingNodeAvp.get3GPPAAAServerName();
                            gmlcAddress = servingNodeAvp.getGMLCAddress();
                        }
                        byte[] additionalSgsnNumber, additionalMscNumber;
                        additionalSgsnNumber = additionalMscNumber = null;
                        DiameterIdentity additionalSgsnName, additionalSgsnRealm, additionalMmeName, additionalMmeRealm, additional3gppAAAServerName;
                        additionalSgsnName = additionalSgsnRealm = additionalMmeName = additionalMmeRealm = additional3gppAAAServerName = null;
                        long additionalLcsCapabilitiesSets = -1;
                        Address additionalGmlcAddress = null;
                        AdditionalServingNodeAvp additionalServingNodeAvp = riaEvent.getAdditionalServingNode();
                        if (additionalServingNodeAvp != null) {
                            slhRiaAvpValues.setAdditionalServingNodeAvp(additionalServingNodeAvp);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLCSRoutingInfoAnswer: received Additional-Serving-Node AVP: " + additionalServingNodeAvp);
                            }
                            additionalSgsnNumber = additionalServingNodeAvp.getSGSNNumber();
                            additionalSgsnName = additionalServingNodeAvp.getSGSNName();
                            additionalSgsnRealm = additionalServingNodeAvp.getSGSNRealm();
                            additionalMmeName = additionalServingNodeAvp.getMMEName();
                            additionalMmeRealm = additionalServingNodeAvp.getMMERealm();
                            additionalMscNumber = additionalServingNodeAvp.getMSCNumber();
                            additional3gppAAAServerName = additionalServingNodeAvp.get3GPPAAAServerName();
                            additionalGmlcAddress = additionalServingNodeAvp.getGMLCAddress();
                        }

                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setStatusCode(resultCode);
                            gmlcCdrState.setImsi(AVPHandler.userName2Imsi(imsi));
                            gmlcCdrState.setISDNAddressString(AVPHandler.tbcd2IsdnAddressString(msisdn));
                            gmlcCdrState.setLmsi(AVPHandler.byte2Lmsi(lmsi));
                            if (additionalServingNodeAvp != null) {
                                gmlcCdrState.setSgsnNumber(AVPHandler.byte2IsdnAddressString(additionalSgsnNumber));
                                gmlcCdrState.setSgsnName(AVPHandler.diameterIdToMapDiameterId(additionalSgsnName));
                                gmlcCdrState.setSgsnRealm(AVPHandler.diameterIdToMapDiameterId(additionalSgsnRealm));
                                gmlcCdrState.setMmeName(AVPHandler.diameterIdToMapDiameterId(additionalMmeName));
                                gmlcCdrState.setMmeRealm(AVPHandler.diameterIdToMapDiameterId(additionalMmeRealm));
                                gmlcCdrState.setMscNumber(AVPHandler.byte2IsdnAddressString(additionalMscNumber));
                                gmlcCdrState.setAaaServerName(AVPHandler.diameterIdToMapDiameterId(additional3gppAAAServerName));
                                gmlcCdrState.sethGmlcAddress(AVPHandler.address2GsnAddress(additionalGmlcAddress));
                            }
                            if (servingNodeAvp != null) {
                                gmlcCdrState.setSgsnNumber(AVPHandler.byte2IsdnAddressString(sgsnNumber));
                                gmlcCdrState.setSgsnName(AVPHandler.diameterIdToMapDiameterId(sgsnName));
                                gmlcCdrState.setSgsnRealm(AVPHandler.diameterIdToMapDiameterId(sgsnRealm));
                                gmlcCdrState.setMmeName(AVPHandler.diameterIdToMapDiameterId(mmeName));
                                gmlcCdrState.setMmeRealm(AVPHandler.diameterIdToMapDiameterId(mmeRealm));
                                gmlcCdrState.setMscNumber(AVPHandler.byte2IsdnAddressString(mscNumber));
                                gmlcCdrState.setAaaServerName(AVPHandler.diameterIdToMapDiameterId(tgppAAAServerName));
                                gmlcCdrState.sethGmlcAddress(AVPHandler.address2GsnAddress(gmlcAddress));
                            }
                        }

                        if (sgsnName != null || additionalSgsnName != null) {
                            if (sgsnName != null) {
                                if (!sgsnName.toString().isEmpty())
                                    destHost = sgsnName;
                            } else if (additionalSgsnName != null) {
                                if (!additionalSgsnName.toString().isEmpty())
                                    destHost = additionalSgsnName;
                            }
                        }
                        if (sgsnRealm != null || additionalSgsnRealm != null) {
                            if (sgsnRealm != null) {
                                if (!sgsnRealm.toString().isEmpty()) {
                                    destRealm = sgsnRealm;
                                }
                            } else if (additionalSgsnRealm != null) {
                                if (!additionalSgsnRealm.toString().isEmpty()) {
                                    destRealm = additionalSgsnRealm;
                                }
                            }
                        }
                        if (mmeName != null || additionalMmeName != null) {
                            if (mmeName != null) {
                                if (!mmeName.toString().isEmpty())
                                    destHost = mmeName;
                            } else if (additionalMmeName != null) {
                                if (!additionalMmeName.toString().isEmpty())
                                    destHost = additionalMmeName;
                            }
                        }
                        if (mmeRealm != null || additionalMmeRealm != null) {
                            if (mmeRealm != null) {
                                if (!mmeRealm.toString().isEmpty()) {
                                    destRealm = mmeRealm;
                                }
                            } else if (additionalMmeRealm != null) {
                                if (!additionalMmeRealm.toString().isEmpty()) {
                                    destRealm = additionalMmeRealm;
                                }
                            }
                        }
                        if (destHost == null || destRealm == null) {
                            throw new SLhException("MME or SGSN hostname/realm not found in RIA Serving-Node AVP");
                        }

                        if (logger.isFineEnabled()) {
                            this.logger.fine(String.format("\nRecovered slhRirRequestValues as %s object from Diameter session '%s'",
                                slhRiaAvpValues == null ? "null" : "valid", riaEvent.getSessionId()));
                        }

                        SLhRiaAvpValues sLhRiaAvpResponseValues = slhRiaAvpValues;
                        mlpRespResult = MLPResponse.MLPResultType.OK;

                        SLgClientSessionActivity slgClientSessionActivity = this.slgProvider.createSLgClientSessionActivity(destHost, destRealm);
                        // Keep ACI in across Diameter session for PLR/PLA
                        ActivityContextInterface slgACIF = slgAcif.getActivityContextInterface(slgClientSessionActivity);
                        slgACIF.attach(this.sbbContext.getSbbLocalObject());

                        // < Provide-Location-Request> ::=	< Diameter Header: 8388620, REQ, PXY, 16777255 >
                        ProvideLocationRequest plr = slgClientSessionActivity.createProvideLocationRequest();

                        SLgLocationType slgLocationType;
                        LCSEPSClientNameAvp lcsEpsClientNameAvp;
                        LCSRequestorNameAvp lcsRequestorNameAvp;
                        LCSFormatIndicator lcsFormatIndicator;
                        LCSClientType lcsClientType;
                        LCSFormatIndicator lcsReqFormatIndicator;
                        LCSQoSClass lcsQoSClass;
                        VerticalRequested verticalRequested;
                        VelocityRequested velocityRequested;
                        LCSPrivacyCheckSessionAvp lcsPrivacyCheckSessionAvp;
                        net.java.slee.resource.diameter.slg.events.avp.LCSPrivacyCheck lcsPrivacyCheckSession;
                        LCSPrivacyCheckNonSessionAvp lcsPrivacyCheckNonSessionAvp;
                        net.java.slee.resource.diameter.slg.events.avp.LCSPrivacyCheck lcsPrivacyCheckNonSession;
                        LCSQoSAvp lcsQoSAvp;
                        net.java.slee.resource.diameter.slg.events.avp.ResponseTime responseTime;
                        PLMNIDListAvp plmnidListAvp;
                        AreaEventInfoAvp areaEventInfoAvp = null;
                        AreaDefinitionAvp areaDefinitionAvp;
                        AreaAvp areaAvp;
                        AdditionalAreaAvp additionalAreaAvp = null;
                        net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo areaEventOccurrenceInfo;
                        MotionEventInfoAvp motionEventInfoAvp = null;
                        net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo motionEventOccurrenceInfo;
                        PeriodicLDRInfoAvp periodicLDRInfoAvp;
                        ReportingPLMNListAvp reportingPLMNListAvp;
                        PeriodicLocationSupportIndicator periodicLocationSupportIndicator = null;
                        PrioritizedListIndicator prioritizedListIndicator = null;

                        if (slhRirRequestValues != null) {

                            /*** PLR conditional AVP: [ User-Name ] ***/
                            if (imsi != null)
                                plr.setUserName(imsi);

                            /*** PLR conditional AVP: [ MSISDN ] ***/
                            if (msisdn != null)
                                plr.setMSISDN(msisdn);

                            /*** PLR mandatory AVP: { SLg-Location-Type } ***/
                            // SLg-Location-Type AVP is of type Enumerated. The following values are defined:
                            // CURRENT_LOCATION (0), CURRENT_OR_LAST_KNOWN_LOCATION (1), INITIAL_LOCATION (2)
                            // ACTIVATE_DEFERRED_LOCATION (3), CANCEL_DEFERRED_LOCATION (4), NOTIFICATION_VERIFICATION_ONLY (5)
                            if (slhRirRequestValues.plrSlgLocationType != null) {
                                if (slhRirRequestValues.plrSlgLocationType == SLgLocationType._CURRENT_LOCATION ||
                                    slhRirRequestValues.plrSlgLocationType == SLgLocationType._CURRENT_OR_LAST_KNOWN_LOCATION ||
                                    slhRirRequestValues.plrSlgLocationType == SLgLocationType._INITIAL_LOCATION ||
                                    slhRirRequestValues.plrSlgLocationType == SLgLocationType._ACTIVATE_DEFERRED_LOCATION ||
                                    slhRirRequestValues.plrSlgLocationType == SLgLocationType._CANCEL_DEFERRED_LOCATION ||
                                    slhRirRequestValues.plrSlgLocationType == SLgLocationType._NOTIFICATION_VERIFICATION_ONLY) {
                                    slgLocationType = SLgLocationType.fromInt(slhRirRequestValues.plrSlgLocationType);
                                    plr.setSLgLocationType(slgLocationType);
                                }
                            }

                            /*** PLR mandatory AVP: { LCS-EPS-Client-Name } ***/
                            // The LCS-EPS-Client-Name AVP is of type Grouped.
                            //   LCS-EPS-Client-Name ::= <AVP header: 2501 10415>
                            //      [ LCS-Name-String ]
                            //      [ LCS-Format-Indicator ]
                            if (slhRirRequestValues.plrLcsNameString != null && slhRirRequestValues.plrLcsFormatInd != null) {
                                lcsEpsClientNameAvp = this.slgAVPFactory.createLCSEPSClientName();
                                // [ LCS-Name-String ]
                                // The LCS-Name-String AVP is of type UTF8String and contains the LCS Client name.
                                lcsEpsClientNameAvp.setLCSNameString(slhRirRequestValues.plrLcsNameString);
                                // [ LCS-Format-Indicator ]
                                // The LCS-Format-Indicator AVP is of type Enumerated
                                // and contains the format of the LCS Client name.
                                // It can be one of the following values: 0	(LOGICAL_NAME), 1 (EMAIL_ADDRESS), 2 (MSISDN),
                                // 3 URL, 4	SIP_URL
                                lcsFormatIndicator = LCSFormatIndicator.fromInt(slhRirRequestValues.plrLcsFormatInd);
                                lcsEpsClientNameAvp.setLCSFormatIndicator(lcsFormatIndicator);
                                // { LCS-EPS-Client-Name }
                                plr.setLCSEPSClientName(lcsEpsClientNameAvp);
                            }

                            /*** PLR mandatory AVP: { LCS-Client-Type } ***/
                            // The LCS-Client-Type AVP is of type Enumerated and contains the type of services requested by the LCS Client.
                            // It can be one of the following values: 0	EMERGENCY_SERVICES, 1 VALUE_ADDED_SERVICES,
                            // 2 PLMN_OPERATOR_SERVICES, 3 LAWFUL_INTERCEPT_SERVICES
                            if (slhRirRequestValues.plrLcsClientType != null) {
                                if (slhRirRequestValues.plrLcsClientType == LCSClientType._EMERGENCY_SERVICES ||
                                    slhRirRequestValues.plrLcsClientType == LCSClientType._VALUE_ADDED_SERVICES ||
                                    slhRirRequestValues.plrLcsClientType == LCSClientType._PLMN_OPERATOR_SERVICES ||
                                    slhRirRequestValues.plrLcsClientType == LCSClientType._LAWFUL_INTERCEPT_SERVICES) {
                                    lcsClientType = LCSClientType.fromInt(slhRirRequestValues.plrLcsClientType);
                                    plr.setLCSClientType(lcsClientType);
                                }
                            }

                            /*** PLR optional AVP: [ LCS-Requestor-Name ] ***/
                            if (slhRirRequestValues.plrLcsRequestorIdString != null) {
                                if (slhRirRequestValues.plrLcsRequestorFormatIndicator != null) {
                                    if (slhRirRequestValues.plrLcsRequestorFormatIndicator == LCSFormatIndicator._LOGICAL_NAME ||
                                        slhRirRequestValues.plrLcsRequestorFormatIndicator == LCSFormatIndicator._EMAIL_ADDRESS ||
                                        slhRirRequestValues.plrLcsRequestorFormatIndicator == LCSFormatIndicator._MSISDN ||
                                        slhRirRequestValues.plrLcsRequestorFormatIndicator == LCSFormatIndicator._URL ||
                                        slhRirRequestValues.plrLcsRequestorFormatIndicator == LCSFormatIndicator._SIP_URL) {
                                        lcsRequestorNameAvp = this.slgAVPFactory.createLCSRequestorName();
                                        lcsRequestorNameAvp.setLCSRequestorIDString(slhRirRequestValues.plrLcsRequestorIdString);
                                        lcsReqFormatIndicator = LCSFormatIndicator.fromInt(slhRirRequestValues.plrLcsRequestorFormatIndicator);
                                        if (lcsReqFormatIndicator != null) {
                                            lcsRequestorNameAvp.setLCSFormatIndicator(lcsReqFormatIndicator);
                                            plr.setLCSRequestorName(lcsRequestorNameAvp);
                                        }
                                    }
                                }
                            }

                            /*** PLR optional AVP: [ LCS-QoS ] ***/
                            // LCS-QoS ::= <AVP header: 2504 10415>
                            //      [ LCS-QoS-Class ]
                            //      [ Horizontal-Accuracy ]
                            //      [ Vertical-Accuracy ]
                            //      [ Vertical-Requested ]
                            //      [ Response-Time]
                            if (slhRirRequestValues.plrQoSClass != null || slhRirRequestValues.plrHorizontalAccuracy != null ||
                                slhRirRequestValues.plrVerticalAccuracy != null || slhRirRequestValues.plrVerticalRequested != null ||
                                slhRirRequestValues.plrResponseTime != null) {
                                lcsQoSAvp = this.slgAVPFactory.createLCSQoS();
                                // [ LCS-QoS-Class ]
                                // The LCS-QoS-Class AVP is of the type Enumerated. The following values are defined: ASSURED (0), BEST EFFORT (1)
                                if (slhRirRequestValues.plrQoSClass != null) {
                                    if (slhRirRequestValues.plrQoSClass == LCSQoSClass._ASSURED ||
                                        slhRirRequestValues.plrQoSClass == LCSQoSClass._BEST_EFFORT) {
                                        lcsQoSClass = LCSQoSClass.fromInt(slhRirRequestValues.plrQoSClass);
                                        lcsQoSAvp.setLCSQoSClass(lcsQoSClass);
                                    }
                                } else {
                                    lcsQoSAvp.setLCSQoSClass(LCSQoSClass.BEST_EFFORT);
                                }
                                // [ Horizontal-Accuracy ]
                                // The Horizontal-Accuracy AVP is of type Unsigned32.
                                // Bits 6-0 corresponds to Uncertainty Code defined in 3GPP TS 23.032 [3].
                                // The horizontal location error should be less than the error indicated by the uncertainty code with 67% confidence.
                                // Bits 7 to 31 shall be ignored.
                                if (slhRirRequestValues.plrHorizontalAccuracy != null) {
                                    if (slhRirRequestValues.plrHorizontalAccuracy > -1 && slhRirRequestValues.plrHorizontalAccuracy < 128)
                                        lcsQoSAvp.setHorizontalAccuracy(slhRirRequestValues.plrHorizontalAccuracy);
                                }
                                // [ Vertical-Accuracy ]
                                // The Vertical-Accuracy AVP is of type Unsigned32.
                                // Bits 6-0 corresponds to Uncertainty Code defined in 3GPP TS 23.032 [3].
                                // The vertical location error should be less than the error indicated by the uncertainty code with 67% confidence.
                                // Bits 7 to 31 shall be ignored
                                if (slhRirRequestValues.plrVerticalAccuracy != null) {
                                    if (slhRirRequestValues.plrVerticalAccuracy > -1 && slhRirRequestValues.plrVerticalAccuracy < 128)
                                        lcsQoSAvp.setVerticalAccuracy(slhRirRequestValues.plrVerticalAccuracy);
                                }
                                // [ Vertical-Requested ]
                                // The Vertical-Requested AVP is of type Enumerated. The following values are defined:
                                //	VERTICAL_COORDINATE_IS_NOT REQUESTED (0)
                                //	VERTICAL_COORDINATE_IS_REQUESTED (1)
                                // Default value if AVP is not present is: VERTICAL_COORDINATE_IS_NOT_REQUESTED (0).
                                if (slhRirRequestValues.plrVerticalRequested != null) {
                                    if (slhRirRequestValues.plrVerticalRequested == VerticalRequested._VERTICAL_COORDINATE_IS_NOT_REQUESTED ||
                                        slhRirRequestValues.plrVerticalRequested == VerticalRequested._VERTICAL_COORDINATE_IS_REQUESTED) {
                                        verticalRequested = VerticalRequested.fromInt(Integer.valueOf(slhRirRequestValues.plrVerticalRequested));
                                        lcsQoSAvp.setVerticalRequested(verticalRequested);
                                    }
                                } else {
                                    // Default value if Vertical-Requested AVP is not present is: VERTICAL_COORDINATE_IS_NOT_REQUESTED (0)
                                    lcsQoSAvp.setVerticalRequested(VerticalRequested.VERTICAL_COORDINATE_IS_NOT_REQUESTED);
                                }
                                // [ Response-Time]
                                // The Response-Time AVP is of type Enumerated. The following values are defined: LOW_DELAY (0), DELAY_TOLERANT (1)
                                if (slhRirRequestValues.plrResponseTime != null) {
                                    if (slhRirRequestValues.plrResponseTime == net.java.slee.resource.diameter.slg.events.avp.ResponseTime._LOW_DELAY ||
                                        slhRirRequestValues.plrResponseTime == net.java.slee.resource.diameter.slg.events.avp.ResponseTime._DELAY_TOLERANT) {
                                        responseTime = net.java.slee.resource.diameter.slg.events.avp.ResponseTime.fromInt((Integer.valueOf(slhRirRequestValues.plrResponseTime)));
                                        lcsQoSAvp.setResponseTime(responseTime);
                                    }
                                } else {
                                    lcsQoSAvp.setResponseTime(net.java.slee.resource.diameter.slg.events.avp.ResponseTime.DELAY_TOLERANT);
                                }
                                plr.setLCSQoS(lcsQoSAvp);
                                if (gmlcCdrState.isInitialized()) {
                                    LCSQoS lcsQoS = AVPHandler.lteLcsQos2MapLcsQoS(lcsQoSAvp);
                                    gmlcCdrState.setLcsQoS(lcsQoS);
                                }
                            }

                            /*** PLR optional AVP: [ LCS-Priority ] ***/
                            if (slhRirRequestValues.plrLcsPriority != null)
                                plr.setLCSPriority(slhRirRequestValues.plrLcsPriority);

                            /*** PLR optional AVP: [ LCS-Service-Type-ID ] ***/
                            if (slhRirRequestValues.plrLcsServiceTypeId != null)
                                plr.setLCSServiceTypeID(slhRirRequestValues.plrLcsServiceTypeId);

                            /*** PLR optional AVP: [ Velocity-Requested ] ***/
                            if (slhRirRequestValues.plrVelocityRequested != null) {
                                velocityRequested = VelocityRequested.fromInt(Integer.valueOf(slhRirRequestValues.plrVelocityRequested));
                                plr.setVelocityRequested(velocityRequested);
                            }

                            /*** PLR optional AVP: [ LCS-Privacy-Check-Non-Session ] ***/
                            if (slhRirRequestValues.plrPrivacyCheckNonSession != null) {
                                if (slhRirRequestValues.plrPrivacyCheckNonSession > -1) {
                                    lcsPrivacyCheckNonSession = net.java.slee.resource.diameter.slg.events.avp.LCSPrivacyCheck.fromInt(Integer.valueOf(slhRirRequestValues.plrPrivacyCheckNonSession));
                                    lcsPrivacyCheckNonSessionAvp = this.slgAVPFactory.createLCSPrivacyCheckNonSession();
                                    lcsPrivacyCheckNonSessionAvp.setLCSPrivacyCheck(lcsPrivacyCheckNonSession);
                                    plr.setLCSPrivacyCheckNonSession(lcsPrivacyCheckNonSessionAvp);
                                }
                            }

                            /*** PLR optional AVP: [ LCS-Privacy-Check-Session ] ***/
                            if (slhRirRequestValues.plrPrivacyCheckSession != null) {
                                if (slhRirRequestValues.plrPrivacyCheckSession > -1) {
                                    lcsPrivacyCheckSession = net.java.slee.resource.diameter.slg.events.avp.LCSPrivacyCheck.fromInt(Integer.valueOf(slhRirRequestValues.plrPrivacyCheckSession));
                                    lcsPrivacyCheckSessionAvp = this.slgAVPFactory.createLCSPrivacyCheckSession();
                                    lcsPrivacyCheckSessionAvp.setLCSPrivacyCheck(lcsPrivacyCheckSession);
                                    plr.setLCSPrivacyCheckSession(lcsPrivacyCheckSessionAvp);
                                }
                            }

                            /*** PLR optional AVP: [ Deferred-Location-Type ] ***/
                            if (slhRirRequestValues.plrDeferredLocationType != null)
                                plr.setDeferredLocationType(slhRirRequestValues.plrDeferredLocationType);

                            /*** PLR optional AVP: [ Area-Event-Info ] ***/
                            // Area-Event-Info grouped AVP
                            // Area-Event-Info ::= <AVP header: 2533 10415>
                            //      { Area-Definition }
                            //      [ Occurrence-Info ]
                            //      [ Interval-Time ]
                            //      [ Maximum-Interval ]
                            //      [ Sampling-Interval ]
                            //      [ Reporting-Duration ]
                            //      [ Reporting-Location-Requirements ]
                            // Area-Definition grouped AVP
                            // Area-Definition ::= <AVP header: 2534 10415>
                            //        1*10{ Area }
                            //        *240[ Additional-Area ]
                            // Area grouped AVP
                            // Area ::= <AVP header: 2535 10415>
                            //      { Area-Type }
                            //      { Area-Identification }

                            // { Area-Definition }
                            // { Area }
                            if (slhRirRequestValues.plrAreaType != null) {
                                // AreaDefinitionAvp areaDefinitionAvp = this.slgAVPFactory.createAreaDefinition();
                                /* AreaAvp areaAvp = this.slgAVPFactory.createArea(); */
                                /*AdditionalAreaAvp additionalAreaAvp = this.slgAVPFactory.createAdditionalArea();*/
                                // { Area-Type } of { Area }
                                // The Area-Type AVP is of type Unsigned32. The following values are defined:
                                // "Country Code" 0, "PLMN ID", "Location Area ID 2, "Routing Area ID"	3, "Cell Global ID"	4, "UTRAN Cell ID"	5,
                                // "Tracking Area ID" 6 and "E-UTRAN Cell Global ID" 7
                                if (slhRirRequestValues.plrAreaType >= 0 &&
                                    slhRirRequestValues.plrAreaType <= 7) {
                                    // { Area-Identification } of { Area }
                                    // The Area-Identification AVP is of type OctetString and shall contain the identification
                                    // of the area applicable for the change of area event based deferred location reporting.
                                    if (slhRirRequestValues.plrAreaIdentification != null) {
                                        String[] areaIdArray = slhRirRequestValues.plrAreaIdentification.split("-");
                                        long lcsAreaTypeValue = slhRirRequestValues.plrAreaType;
                                        String lcsAreaType = convertAreaTypeToString(lcsAreaTypeValue);
                                        byte[] areaIdTbcd = setAreaIdtoTbcd(areaIdArray, lcsAreaType);
                                        areaAvp = this.slgAVPFactory.createArea();
                                        areaAvp.setAreaType(slhRirRequestValues.plrAreaType);
                                        areaAvp.setAreaIdentification(areaIdTbcd);
                                        // [ Additional-Area ]
                                        if (slhRirRequestValues.plrAdditionalAreaType != null) {
                                            // { Area-Type } of [ Additional-Area ]
                                            if (slhRirRequestValues.plrAdditionalAreaType >= 0 &&
                                                slhRirRequestValues.plrAdditionalAreaType <= 7) {
                                                // { Area-Identification } of [ Additional-Area ]
                                                if (slhRirRequestValues.plrAdditionalAreaIdentification != null) {
                                                    String[] additionalAreaIdArray = slhRirRequestValues.plrAdditionalAreaIdentification.split("-");
                                                    long lcsAdditionalAreaTypeValue = slhRirRequestValues.plrAdditionalAreaType;
                                                    String lcsAdditionalAreaType = convertAreaTypeToString(lcsAdditionalAreaTypeValue);
                                                    byte[] additionalAreaIdTbcd = setAreaIdtoTbcd(additionalAreaIdArray, lcsAdditionalAreaType);
                                                    additionalAreaAvp = this.slgAVPFactory.createAdditionalArea();
                                                    additionalAreaAvp.setAreaType(slhRirRequestValues.plrAdditionalAreaType);
                                                    additionalAreaAvp.setAreaIdentification(additionalAreaIdTbcd);
                                                }
                                            }
                                        }
                                        if (areaAvp != null) {
                                            areaDefinitionAvp = this.slgAVPFactory.createAreaDefinition();
                                            areaDefinitionAvp.setArea(areaAvp);
                                            if (additionalAreaAvp != null)
                                                areaDefinitionAvp.setAdditionalArea(additionalAreaAvp);
                                            areaEventInfoAvp = this.slgAVPFactory.createAreaEventInfo();
                                            areaEventInfoAvp.setAreaDefinition(areaDefinitionAvp);
                                        }
                                    }
                                }
                            }
                            // [ Occurrence-Info ]
                            if (slhRirRequestValues.plrAreaEventOccurrenceInfo != null) {
                                if (areaEventInfoAvp != null) {
                                    if (slhRirRequestValues.plrAreaEventOccurrenceInfo == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._ONE_TIME_EVENT ||
                                        slhRirRequestValues.plrAreaEventOccurrenceInfo == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                        areaEventOccurrenceInfo = net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo.fromInt(slhRirRequestValues.plrAreaEventOccurrenceInfo);
                                        areaEventInfoAvp.setOccurrenceInfo(areaEventOccurrenceInfo);
                                    }
                                }
                            } else {
                                if (areaEventInfoAvp != null) {
                                    // If not included,
                                    // the default value of Occurrence-Info shall be considered as "ONE_TIME_EVENT" (0).
                                    areaEventOccurrenceInfo = net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo.ONE_TIME_EVENT;
                                    areaEventInfoAvp.setOccurrenceInfo(areaEventOccurrenceInfo);
                                }
                            }
                            // [ Interval-Time ]
                            if (slhRirRequestValues.plrAreaEventIntervalTime != null) {
                                // Interval-Time AVP is only applicable when the Occurrence-Info is set to "MULTIPLE_TIME_EVENT" (1).
                                if (areaEventInfoAvp != null) {
                                    if (areaEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (areaEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            // The Interval-Time AVP (Unsigned32) contains the minimum time interval
                                            // between area reports or motion reports, in seconds.
                                            // The minimum value shall be 1 second and the maximum value 32767 seconds.
                                            if (slhRirRequestValues.plrAreaEventIntervalTime >= 1 &&
                                                slhRirRequestValues.plrAreaEventIntervalTime <= 32767) {
                                                areaEventInfoAvp.setIntervalTime(slhRirRequestValues.plrAreaEventIntervalTime);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (areaEventInfoAvp != null) {
                                    if (areaEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (areaEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            // If not included,
                                            // the default value of Interval-Time shall be considered as one.
                                            areaEventInfoAvp.setIntervalTime(1);
                                        }
                                    }
                                }
                            }
                            // [ Maximum-Interval ]
                            if (slhRirRequestValues.plrAreaEventMaxInterval != null) {
                                // The Maximum-Interval AVP (Unsigned32) contains the maximum time interval between consecutive event reports, in seconds.
                                // The minimum value shall be 1 second and the maximum value 86400 seconds.
                                //  Maximum-Interval AVP is only applicable when the Occurrence-Info is set to "MULTIPLE_TIME_EVENT" (1).
                                if (areaEventInfoAvp != null) {
                                    if (areaEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (areaEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            if (slhRirRequestValues.plrAreaEventMaxInterval >= 1 &&
                                                slhRirRequestValues.plrAreaEventMaxInterval <= 86400) {
                                                areaEventInfoAvp.setMaximumInterval(slhRirRequestValues.plrAreaEventMaxInterval);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (areaEventInfoAvp != null) {
                                    if (areaEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (areaEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            // If not included,
                                            // the default value of Maximum-Interval shall be considered as the maximum value.
                                            areaEventInfoAvp.setMaximumInterval(86400);
                                        }
                                    }
                                }
                            }
                            // [ Sampling-Interval ]
                            if (slhRirRequestValues.plrAreaEventSamplingInterval != null) {
                                // The Sampling-Interval AVP (Unsigned32) contains the maximum time interval between consecutive evaluations by a UE
                                // of an area event or motion event, in seconds.
                                // The minimum value shall be 1 second and the maximum value 3600 seconds.
                                if (areaEventInfoAvp != null) {
                                    if (slhRirRequestValues.plrAreaEventSamplingInterval >= 1 &&
                                        slhRirRequestValues.plrAreaEventSamplingInterval <= 3600) {
                                        areaEventInfoAvp.setSamplingInterval(slhRirRequestValues.plrAreaEventSamplingInterval);
                                    }
                                }
                            } else {
                                if (areaEventInfoAvp != null) {
                                    // If not included,
                                    // the default value of Sampling-Interval shall be considered as the maximum value.
                                    areaEventInfoAvp.setSamplingInterval(3600);
                                }
                            }
                            // [ Reporting-Duration ]
                            if (slhRirRequestValues.plrAreaEventReportingDuration != null) {
                                // The Reporting-Duration AVP (Unsigned32) contains the maximum duration of event reporting, in seconds.
                                // Its minimum value shall be 1 and maximum value shall be 8640000.
                                if (areaEventInfoAvp != null) {
                                    if (slhRirRequestValues.plrAreaEventReportingDuration >= 1 &&
                                        slhRirRequestValues.plrAreaEventReportingDuration <= 8640000) {
                                        areaEventInfoAvp.setReportDuration(slhRirRequestValues.plrAreaEventReportingDuration);
                                    }
                                }
                            } else {
                                if (areaEventInfoAvp != null) {
                                    // If not included,
                                    // the default value of Reporting-Duration shall be considered as the maximum value.
                                    areaEventInfoAvp.setReportDuration(8640000);
                                }
                            }
                            // [ Reporting-Location-Requirements ]
                            if (slhRirRequestValues.plrAreaEventRepLocRequirements != null) {
                                if (areaEventInfoAvp != null) {
                                    areaEventInfoAvp.setReportingLocationRequirements(slhRirRequestValues.plrAreaEventRepLocRequirements);
                                }
                            } else {
                                if (areaEventInfoAvp != null) {
                                    // Bit 0 - A location estimate is required for each area event,
                                    // motion event report or expiration of the maximum time interval between event reports.
                                    areaEventInfoAvp.setReportingLocationRequirements(1);
                                }
                            }
                            // [ Area-Event-Info ]
                            if (areaEventInfoAvp != null) {
                                plr.setAreaEventInfo(areaEventInfoAvp);
                            }

                            /*** PLR optional AVP: [ Periodic-LDR-Information ] ***/
                            // Periodic-LDR-Info grouped AVP
                            // Periodic-LDR-Info ::= <AVP header: 2540 10415>
                            //    { Reporting-Amount }
                            //    { Reporting-Interval }
                            if (slhRirRequestValues.plrPeriodicLDRReportingAmount != null &&
                                slhRirRequestValues.plrPeriodicLDRReportingInterval != null) {
                                periodicLDRInfoAvp = this.slgAVPFactory.createPeriodicLDRInformation();
                                // { Reporting-Amount }
                                this.slgAVPFactory.createPeriodicLDRInformation();
                                if (slhRirRequestValues.plrPeriodicLDRReportingAmount != null) {
                                    // The Reporting-Amount AVP is of type Unsigned32 and it contains reporting frequency.
                                    // Its minimum value shall be 1 and maximum value shall be 8639999
                                    if (slhRirRequestValues.plrPeriodicLDRReportingAmount >= 1 &&
                                        slhRirRequestValues.plrPeriodicLDRReportingAmount <= 8639999)
                                        periodicLDRInfoAvp.setReportingAmount(slhRirRequestValues.plrPeriodicLDRReportingAmount);
                                }
                                // { Reporting-Interval }
                                if (slhRirRequestValues.plrPeriodicLDRReportingInterval != null) {
                                    // The Interval-Time AVP is of type Unsigned32 and it contains reporting interval in seconds.
                                    // Its minimum value shall be 1 and maximum value shall be 8639999.
                                    if (slhRirRequestValues.plrPeriodicLDRReportingInterval >= 1 &&
                                        slhRirRequestValues.plrPeriodicLDRReportingInterval <= 8639999)
                                        periodicLDRInfoAvp.setReportingInterval(slhRirRequestValues.plrPeriodicLDRReportingInterval);
                                }
                                // [ Periodic-LDR-Information ]
                                if (periodicLDRInfoAvp.getReportingAmount() >= 1 && periodicLDRInfoAvp.getReportingInterval() >= 1) {
                                    plr.setPeriodicLDRInformation(periodicLDRInfoAvp);
                                }
                            }

                            /*** PLR optional AVP: [ Motion-Event-Info ] ***/
                            // Motion-Event-Info ::= <AVP header: 2559 10415>
                            //      { Linear-Distance }
                            //      [ Occurrence-Info ]
                            //      [ Interval-Time ]
                            //      [ Maximum-Interval ]
                            //      [ Sampling-Interval ]
                            //      [ Reporting-Duration ]
                            //      [ Reporting-Location-Requirements ]
                            // { Linear-Distance }
                            if (slhRirRequestValues.plrMotionEventlinearDistance != null) {
                                // The Linear-Distance AVP is of type Unsigned32 and it contains the minimum linear (straight line) distance
                                // for motion event reports, in meters. The minimum value shall be 1 and maximum value shall be 10,000.
                                if (slhRirRequestValues.plrMotionEventlinearDistance >= 0 && slhRirRequestValues.plrMotionEventlinearDistance <= 10000) {
                                    motionEventInfoAvp = this.slgAVPFactory.createMotionEventInfo();
                                    motionEventInfoAvp.setLinearDistance(slhRirRequestValues.plrMotionEventlinearDistance);
                                }
                            }
                            // [ Occurrence-Info ]
                            if (slhRirRequestValues.plrMotionEventOccurrenceInfo != null) {
                                if (motionEventInfoAvp != null) {
                                    if (slhRirRequestValues.plrMotionEventOccurrenceInfo == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._ONE_TIME_EVENT ||
                                        slhRirRequestValues.plrMotionEventOccurrenceInfo == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                        motionEventOccurrenceInfo = net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo.fromInt(slhRirRequestValues.plrMotionEventOccurrenceInfo);
                                        motionEventInfoAvp.setOccurrenceInfo(motionEventOccurrenceInfo);
                                    }
                                }
                            } else {
                                if (motionEventInfoAvp != null) {
                                    // If not included, the default value of Occurrence-Info shall be considered as "ONE_TIME_EVENT" (0)
                                    motionEventOccurrenceInfo = net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo.ONE_TIME_EVENT;
                                    motionEventInfoAvp.setOccurrenceInfo(motionEventOccurrenceInfo);
                                }
                            }
                            // [ Interval-Time ]
                            if (slhRirRequestValues.plrMotionEventIntervalTime != null) {
                                // Interval-Time AVP is only applicable when the Occurrence-Info is set to "MULTIPLE_TIME_EVENT" (1).
                                if (motionEventInfoAvp != null) {
                                    if (motionEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (motionEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            // The Interval-Time AVP (Unsigned32) contains the minimum time interval
                                            // between area reports or motion reports, in seconds.
                                            // The minimum value shall be 1 second and the maximum value 32767 seconds.
                                            if (slhRirRequestValues.plrMotionEventIntervalTime >= 1 &&
                                                slhRirRequestValues.plrMotionEventIntervalTime <= 32767) {
                                                motionEventInfoAvp.setIntervalTime(slhRirRequestValues.plrMotionEventIntervalTime);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (motionEventInfoAvp != null) {
                                    if (motionEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (motionEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            // If not included, the default value of Interval-Time shall be considered as one.
                                            motionEventInfoAvp.setIntervalTime(1);
                                        }
                                    }
                                }
                            }
                            // [ Maximum-Interval ]
                            if (slhRirRequestValues.plrMotionEventMaximumInterval != null) {
                                // The Maximum-Interval AVP (Unsigned32) contains the maximum time interval between consecutive event reports, in seconds.
                                // The minimum value shall be 1 second and the maximum value 86400 seconds.
                                //  Maximum-Interval AVP is only applicable when the Occurrence-Info is set to "MULTIPLE_TIME_EVENT" (1).
                                if (motionEventInfoAvp != null) {
                                    if (motionEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (motionEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            if (slhRirRequestValues.plrMotionEventMaximumInterval >= 1 &&
                                                slhRirRequestValues.plrMotionEventMaximumInterval <= 86400) {
                                                motionEventInfoAvp.setMaximumInterval(slhRirRequestValues.plrMotionEventMaximumInterval);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (motionEventInfoAvp != null) {
                                    if (motionEventInfoAvp.getOccurrenceInfo() != null) {
                                        if (motionEventInfoAvp.getOccurrenceInfo().getValue() == net.java.slee.resource.diameter.slg.events.avp.OccurrenceInfo._MULTIPLE_TIME_EVENT) {
                                            // If not included,
                                            // the default value of Maximum-Interval shall be considered as the maximum value.
                                            motionEventInfoAvp.setMaximumInterval(86400);
                                        }
                                    }
                                }
                            }
                            // [ Sampling-Interval ]
                            if (slhRirRequestValues.plrMotionEventSamplingInterval != null) {
                                // The Sampling-Interval AVP (Unsigned32) contains the maximum time interval between consecutive evaluations by a UE
                                // of an area event or motion event, in seconds.
                                // The minimum value shall be 1 second and the maximum value 3600 seconds.
                                if (motionEventInfoAvp != null) {
                                    if (slhRirRequestValues.plrMotionEventSamplingInterval >= 1 &&
                                        slhRirRequestValues.plrMotionEventSamplingInterval <= 3600) {
                                        motionEventInfoAvp.setSamplingInterval(slhRirRequestValues.plrMotionEventSamplingInterval);
                                    }
                                }
                            } else {
                                if (motionEventInfoAvp != null) {
                                    // If not included,
                                    // the default value of Sampling-Interval shall be considered as the maximum value.
                                    motionEventInfoAvp.setSamplingInterval(3600);
                                }
                            }
                            // [ Reporting-Duration ]
                            if (slhRirRequestValues.plrMotionEvenReportingDuration != null) {
                                // The Reporting-Duration AVP (Unsigned32) contains the maximum duration of event reporting, in seconds.
                                // Its minimum value shall be 1 and maximum value shall be 8640000.
                                if (motionEventInfoAvp != null) {
                                    if (slhRirRequestValues.plrMotionEvenReportingDuration >= 1 &&
                                        slhRirRequestValues.plrMotionEvenReportingDuration <= 8640000) {
                                        motionEventInfoAvp.setReportDuration(slhRirRequestValues.plrMotionEvenReportingDuration);
                                    }
                                }
                            } else {
                                if (motionEventInfoAvp != null) {
                                    // If not included,
                                    // the default value of Reporting-Duration shall be considered as the maximum value.
                                    motionEventInfoAvp.setReportDuration(8640000);
                                }
                            }
                            // [ Reporting-Location-Requirements ]
                            if (slhRirRequestValues.plrMotionEvenReportingLocationRequirements != null) {
                                if (motionEventInfoAvp != null) {
                                    motionEventInfoAvp.setReportingLocationRequirements(slhRirRequestValues.plrMotionEvenReportingLocationRequirements);
                                }
                            } else {
                                if (motionEventInfoAvp != null) {
                                    // Bit 0 - A location estimate is required for each area event,
                                    // motion event report or expiration of the maximum time interval between event reports.
                                    motionEventInfoAvp.setReportingLocationRequirements(1);
                                }
                            }
                            // [ Motion-Event-Info ]
                            if (motionEventInfoAvp != null) {
                                plr.setMotionEventInfo(motionEventInfoAvp);
                            }

                            /*** PLR optional AVP: [ Reporting-PLMN-List ] ***/
                            // The Reporting-PLMN-List AVP is of type Grouped.
                            //  AVP format:
                            //      Reporting-PLMN-List ::= <AVP header: 2543 10415>
                            //          1*20{ PLMN-ID-List }
                            //          [ Prioritized-List-Indicator ]
                            if (slhRirRequestValues.plrVisitedPLMNIdList != null) {
                                // { PLMN-ID-List }
                                // The PLMN-ID-List AVP is of type Grouped.
                                //  PLMN-ID-List ::= <AVP header: 2544 10415>
                                //      { Visited-PLMN-Id }
                                //      [ Periodic-Location-Support-Indicator ]
                                //      *[ AVP ]
                                // { Visited-PLMN-Id }
                                String[] vPlmnIdArray = slhRirRequestValues.plrVisitedPLMNIdList.split("-");
                                PlmnIdImpl visitedPlmnId = new PlmnIdImpl(Integer.valueOf(vPlmnIdArray[0]), Integer.valueOf(vPlmnIdArray[1]));
                                // [ Periodic-Location-Support-Indicator ]
                                // The Periodic-Location-Support-Indicator AVP is of type Enumerated
                                // and it indicates if the given PLMN-ID (indicated by Visited-PLMN-Id) supports periodic location or not.
                                // The following values are defined: NOT_SUPPORTED (0), SUPPORTED (1)
                                if (slhRirRequestValues.plrPeriodicLocationSupportIndicator != null) {
                                    if (slhRirRequestValues.plrPeriodicLocationSupportIndicator == PeriodicLocationSupportIndicator._NOT_SUPPORTED ||
                                        slhRirRequestValues.plrPeriodicLocationSupportIndicator == PeriodicLocationSupportIndicator._SUPPORTED) {
                                        periodicLocationSupportIndicator = PeriodicLocationSupportIndicator.fromInt(slhRirRequestValues.plrPeriodicLocationSupportIndicator);

                                        // [ Prioritized-List-Indicator ]
                                        // The Prioritized-List-Indicator AVP is of type Enumerated
                                        // and it indicates if the PLMN-ID-List is provided in prioritized order or not. The following values are defined:
                                        //NOT_PRIORITIZED  (0)
                                        //PRIORITIZED (1)
                                        if (slhRirRequestValues.plrPrioritizedListIndicator != null) {
                                            if (slhRirRequestValues.plrPrioritizedListIndicator == PrioritizedListIndicator._NOT_PRIORITIZED ||
                                                slhRirRequestValues.plrPrioritizedListIndicator == PrioritizedListIndicator._PRIORITIZED) {
                                                prioritizedListIndicator = PrioritizedListIndicator.fromInt(slhRirRequestValues.plrPrioritizedListIndicator);
                                            }
                                        } else {
                                            // If not included,
                                            // the default value of Prioritized-List-Indicator shall be considered as "NOT_PRIORITIZED" (0).
                                            prioritizedListIndicator = PrioritizedListIndicator.NOT_PRIORITIZED;
                                        }
                                    }
                                } else {
                                    //  If not included,
                                    //  the default value of Periodic-Location-Support-Indicator shall be considered as "NOT_SUPPORTED" (0).
                                    periodicLocationSupportIndicator = PeriodicLocationSupportIndicator.NOT_SUPPORTED;
                                }
                                if (visitedPlmnId != null) {
                                    plmnidListAvp = this.slgAVPFactory.createPLMNIDList();
                                    plmnidListAvp.setVisitedPLMNId(visitedPlmnId.getData());
                                    if (periodicLocationSupportIndicator != null) {
                                        plmnidListAvp.setPeriodicLocationSupportIndicator(periodicLocationSupportIndicator);
                                        reportingPLMNListAvp = this.slgAVPFactory.createReportingPLMNList();
                                        reportingPLMNListAvp.setPLMNIDList(plmnidListAvp);
                                        if (slhRirRequestValues.reportingPLMNListAvp != null)
                                            slhRirRequestValues.reportingPLMNListAvp = reportingPLMNListAvp;
                                        if (prioritizedListIndicator != null)
                                            reportingPLMNListAvp.setPrioritizedListIndicator(prioritizedListIndicator);
                                        plr.setReportingPLMNList(reportingPLMNListAvp);
                                    }
                                }
                            }

                            // Manage lcsReferenceNumber and lrrCallbackUrl for deferred location reports
                            referenceNumber = slhRirRequestValues.plrLcsReferenceNumber;
                            if (slhRirRequestValues.plrDeferredLocationType != null) {
                                httpSubscriberLocationReport = getHttpSubscriberLocationReport();
                                boolean mlp = false;
                                if (httpRequestType != null) {
                                    if (httpRequestType.equalsIgnoreCase("MLP"))
                                        mlp = true;
                                }
                                slhRiaAvpValues.lteLcsReferenceNumber = httpSubscriberLocationReport.Register(slhRirRequestValues.plrLcsReferenceNumber, slhRirRequestValues.lrrCallbackUrl, null, mlp, curlUser);
                                if (logger.isFineEnabled()) {
                                    logger.fine(String.format("Sending SLg PLR with LCS-Reference-Number: %d from HTTP request clientReferenceNumber: %d and callback URL: '%s'", slhRiaAvpValues.lteLcsReferenceNumber,
                                        Integer.valueOf(slhRirRequestValues.plrLcsReferenceNumber), slhRirRequestValues.lrrCallbackUrl));
                                }
                                httpSubscriberLocationReport.closeMongo();
                                if (slhRiaAvpValues.lteLcsReferenceNumber != null) {
                                    byte[] lcsReferenceNumber = ByteBuffer.allocate(Integer.SIZE / 8).putInt(slhRiaAvpValues.lteLcsReferenceNumber).array();
                                    if (lcsReferenceNumber != null)
                                        plr.setLCSReferenceNumber(lcsReferenceNumber);
                                }
                            }

                            /*** PLR conditional AVP: [ IMEI ] ***/
                            if (slhRirRequestValues.plrImei != null)
                                plr.setIMEI(slhRirRequestValues.plrImei);

                            /*** PLR optional AVP: [ LCS-Supported-GAD-Shapes ] ***/
                            // The Supported-GAD-Shapes AVP is of type Unsigned32 and it shall contain a bitmask.
                            //  A node shall mark in the BIT STRING all Shapes defined in 3GPP TS 23.032 [3] it supports.
                            //  Bits 8-0 in shall indicate the supported Shapes defined in 3GPP TS 23.032 [3]. Bits 9 to 31 shall be ignored.
                            //  ellipsoidPoint (0)
                            //  ellipsoidPointWithUncertaintyCircle (1)
                            //  ellipsoidPointWithUncertaintyEllipse (2)
                            //  polygon (3)
                            //  ellipsoidPointWithAltitude (4)
                            //  ellipsoidPointWithAltitudeAndUncertaintyEllipsoid (5)
                            //  ellipsoidArc (6)
                            //  highAccuracyEllipsoidPointWithUncertaintyEllipse (7)
                            //  highAccuracyEllipsoidPointWithAltitudeAndUncertaintyEllipsoid (8)
                            if (slhRirRequestValues.plrLcsSupportedGadShapes != null) {
                                plr.setLCSSupportedGADShapes(slhRirRequestValues.plrLcsSupportedGadShapes);
                            }

                            /*** PLR optional AVP: [ LCS-Codeword ] ***/
                            if (slhRirRequestValues.plrLcsCodeword != null)
                                plr.setLCSCodeword(slhRirRequestValues.plrLcsCodeword);

                            /*** PLR optional AVP: [ Service-Selection ] ***/
                            if (slhRirRequestValues.plrServiceSelection != null)
                                plr.setServiceSelection(slhRirRequestValues.plrServiceSelection);

                            /*** PLR optional AVP: [ PLR-Flags ] ***/
                            if (slhRirRequestValues.plrFlags != null)
                                plr.setPLRFlags(slhRirRequestValues.plrFlags);

                            /*** { Auth-Session-State } AVP for PLR according to 3GPP TS 29.172 ***/
                            AuthSessionStateType authSessionStateType = AuthSessionStateType.NO_STATE_MAINTAINED;
                            plr.setAuthSessionState(authSessionStateType);

                            mobileCoreNetworkTransactions.setValue(transaction, "sLhRiaAvpResponseValues", slhRiaAvpValues);
                            mobileCoreNetworkTransactions.setValue(transaction, "slhRirRequestValues", slhRirRequestValues);
                            mobileCoreNetworkTransactions.setValue(transaction, "curlUser", curlUser);
                            mobileCoreNetworkTransactions.setValue(transaction, "transactionStart", dialogStartTime);
                            mobileCoreNetworkTransactions.setValue(transaction, "mlpTriggeredReportingService", mlpTriggeredReportingService);
                            mobileCoreNetworkTransactions.setSession(transaction, plr.getSessionId());

                            // set new timer for PLR/PLA cycle
                            TimerID timerID = timerFacility.setTimer(slgACIF, null, System.currentTimeMillis() + DIAMETER_COMMAND_TIMEOUT, defaultTimerOptions);
                            mobileCoreNetworkTransactions.setValue(transaction, "timerID", timerID);

                            slgClientSessionActivity.sendProvideLocationRequest(plr);
                            if (logger.isFineEnabled()) {
                                logger.fine("\n\nSent SLg PLR with session Id: " + plr.getSessionId() + ", host '" + plr.getDestinationHost()
                                    + "', realm '" + plr.getDestinationRealm() + "'");
                                logger.fine("\nSLg Provide-Location-Request details: " + plr);
                            }
                        } else {
                            //this should never happen
                            if (logger.isFineEnabled())
                                logger.warning("\nRIA received on session Id: " + riaEvent.getSessionId() + ", for MSISDN '" + riaEvent.getMSISDN()
                                    + "', IMSI '" + riaEvent.getUserName() + "' but no PLR parameters set. No PLR to send.");
                        }
                    } else {
                        if (gmlcCdrState.isInitialized()) {
                            if (transaction != null)
                                mobileCoreNetworkTransactions.Instance().destroy(transaction);
                            handleRecordAndLocationReportOnDiameterResultCode(resultCode, mlpRespResult, mlpClientErrorMessage, msisdnAddress, imsi,
                                "RIR", referenceNumber, gmlcCdrState, true,
                                riaOriginHost != null ? riaOriginHost.toString() : null, riaOriginRealm != null ? riaOriginRealm.toString() : null,
                                mlpTriggeredReportingService);
                        }
                    }
                }
            } catch (SLhException slhe) {
                logger.warning("Exception when processing LCSRoutingInfoAnswer response: " + slhe.getMessage(), slhe);
                if (destHost == null || destRealm == null)
                    this.createCDRRecord(RecordStatus.LTE_RIR_UNKNOWN_MME_SGSN);
                else
                    this.createCDRRecord(RecordStatus.LTE_RIR_SYSTEM_FAILURE);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, slhe.getMessage(), "RIR", msisdnAddress, imsi, referenceNumber,
                    null, null,
                    riaOriginHost != null ? riaOriginHost.toString() : null, riaOriginRealm != null ? riaOriginRealm.toString() : null,
                    mlpTriggeredReportingService);
                if (transaction != null)
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onLCSRoutingInfoAnswer=%s", riaEvent), e);
            this.createCDRRecord(RecordStatus.LTE_RIR_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on SLh RIA: " + e.getMessage(),
                "RIR", msisdnAddress, imsi, referenceNumber, null, null,
                riaEvent != null ? riaEvent.getOriginHost().toString() : null, riaEvent != null ? riaEvent.getOriginRealm().toString() : null,
                mlpTriggeredReportingService);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } finally {
            detachFromSLhClientActivity(aci);
        }
    }

    /**
     * SLg PLR Event
     */
    public void onProvideLocationRequest(ProvideLocationRequest plrEvent, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onProvideLocationRequest = " + plrEvent);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onProvideLocationRequest=%s", plrEvent), e);
        }
    }

    /**
     * SLg PLA Event
     */
    public void onProvideLocationAnswer(ProvideLocationAnswer plaEvent, ActivityContextInterface aci) {

        String msisdnAddress = null, imsi = null, curlUser;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        Long transaction = null;
        DateTime eventTime = DateTime.now();
        net.java.slee.resource.diameter.base.events.avp.DiameterIdentity plaOriginHost, plaOriginRealm, gmlcHost, gmlcRealm;
        if (plaEvent != null) {
            plaOriginHost = plaEvent.getOriginHost();
            plaOriginRealm = plaEvent.getOriginRealm();
        } else {
            String origHost = gmlcPropertiesManagement.getDiameterDestHost();
            String origRealm = gmlcPropertiesManagement.getDiameterDestRealm();
            plaOriginHost = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(origHost);
            plaOriginRealm = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(origRealm);
        }

        try {
            if (this.logger.isFineEnabled()) {
                logger.fine("\n\nReceived SLg PLA with session Id: " + plaEvent.getSessionId() + ", host '" + plaEvent.getOriginHost()
                    + "', realm '" + plaEvent.getOriginRealm() + "'");
                this.logger.fine("\nonProvideLocationAnswer event details " + plaEvent);
            }
            SLgPlaAvpValues slgPlaAvpValues = new SLgPlaAvpValues();

            try {
                MLPResponse.MLPResultType mlpRespResult = null;
                String mlpClientErrorMessage = null;
                gmlcHost = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginHost());
                gmlcRealm = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginRealm());

                // CDR initialization
                GMLCCDRState gmlcCdrState = CDRCreationHelper.slhSlgCdrInitializer(aci, this.getCDRInterface(), null, plaEvent, null,
                    plaOriginHost, plaOriginRealm, gmlcHost, gmlcRealm);
                // Set timer last
                this.setTimer(aci);

                // Transaction
                transaction = mobileCoreNetworkTransactions.getTransactionId(plaEvent.getSessionId());
                if (transaction == null) {
                    throw new Exception();
                }
                curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
                gmlcCdrState.setDialogStartTime(dialogStartTime);
                if (gmlcCdrState.isInitialized()) {
                    gmlcCdrState.setCurlUser(curlUser);
                    if (dialogStartTime != null && eventTime != null) {
                        Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                        gmlcCdrState.setDialogDuration(dialogDuration);
                    }
                }
                TimerID plaTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
                if (plaTimerID != null)
                    this.timerFacility.cancelTimer(plaTimerID);
                SLhRiaAvpValues sLhRiaAvpResponseValues = (SLhRiaAvpValues) mobileCoreNetworkTransactions.getValue(transaction, "sLhRiaAvpResponseValues");
                SLhRirAvpValues sLhRirRequestValues = (SLhRirAvpValues) mobileCoreNetworkTransactions.getValue(transaction, "slhRirRequestValues");
                referenceNumber = sLhRirRequestValues.plrLcsReferenceNumber;
                mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
                mobileCoreNetworkTransactions.destroy(transaction);
                if (sLhRirRequestValues != null) {
                    if (sLhRirRequestValues.plrMsisdn != null) {
                        byte[] tbcdMsisdn = parseTBCD(sLhRirRequestValues.plrMsisdn);
                        msisdnAddress = toTBCDString(tbcdMsisdn);
                    }
                } else if (sLhRiaAvpResponseValues != null) {
                    if (sLhRiaAvpResponseValues.getMsisdn() != null) {
                        msisdnAddress = AVPHandler.byte2IsdnAddressString(sLhRiaAvpResponseValues.getMsisdn()).getAddress();
                    }
                }
                if (sLhRirRequestValues != null) {
                    if (sLhRirRequestValues.plrUserName != null) {
                        imsi = sLhRirRequestValues.plrUserName;
                    }
                } else if (sLhRiaAvpResponseValues != null) {
                    if (sLhRiaAvpResponseValues.getUserName() != null) {
                        imsi = sLhRiaAvpResponseValues.getUserName();
                    }
                }
                if (gmlcCdrState.isInitialized()) {
                    if (imsi != null) {
                        IMSI slgImsi = new IMSIImpl(imsi);
                        gmlcCdrState.setImsi(slgImsi);
                    }
                    gmlcCdrState.setMsisdn(new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, msisdnAddress));
                    if (sLhRiaAvpResponseValues != null)
                        gmlcCdrState.setLcsReferenceNumber(sLhRiaAvpResponseValues.lteLcsReferenceNumber);
                    gmlcCdrState.setClientReferenceNumber(referenceNumber);
                }

                if (plaEvent != null) {

                    long resultCode = plaEvent.getResultCode();
                    ExperimentalResultAvp experimentalResultAvp = plaEvent.getExperimentalResult();
                    if (experimentalResultAvp != null)
                        resultCode = experimentalResultAvp.getExperimentalResultCode();

                    byte[] locationEstimate = plaEvent.getLocationEstimate();
                    ExtGeographicalInformation lteLocationEstimate = null;
                    AddGeographicalInformation lteAddLocationEstimate = null;
                    if (locationEstimate != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Location-Estimate AVP: " + locationEstimate);
                        }
                        lteLocationEstimate = AVPHandler.lteLocationEstimate2ExtGeographicalInformation(locationEstimate);
                        TypeOfShape typeOfShape = lteLocationEstimate.getTypeOfShape();
                        if (typeOfShape == TypeOfShape.Polygon) {
                            lteAddLocationEstimate = AVPHandler.lteLocationEstimate2AddGeographicalInformation(locationEstimate);
                            slgPlaAvpValues.setLocationEstimate(lteAddLocationEstimate.getData());
                        } else {
                            slgPlaAvpValues.setLocationEstimate(locationEstimate);
                        }
                    }

                    net.java.slee.resource.diameter.slg.events.avp.AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = plaEvent.getAccuracyFulfilmentIndicator();
                    if (accuracyFulfilmentIndicator != null) {
                        slgPlaAvpValues.setAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Accuracy-Fulfilment-Indicator AVP: " + accuracyFulfilmentIndicator);
                        }
                    }

                    long ageOfLocationEstimate = plaEvent.getAgeOfLocationEstimate();
                    if (ageOfLocationEstimate > -1) {
                        slgPlaAvpValues.setAgeOfLocationEstimate(ageOfLocationEstimate);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Age-Of-Location-Estimate AVP: " + ageOfLocationEstimate);
                        }
                    }

                    byte[] velocityEstimate = plaEvent.getVelocityEstimate();
                    VelocityEstimate lteVelocityEstimate = null;
                    if (velocityEstimate != null) {
                        slgPlaAvpValues.setVelocityEstimate(velocityEstimate);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Velocity-Estimate AVP: " + velocityEstimate);
                        }
                        lteVelocityEstimate = AVPHandler.lteVelocityEstimate2MapVelocityEstimate(velocityEstimate);
                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setVelocityEstimate(lteVelocityEstimate);
                        }
                    }

                    byte[] eUtranPositioningData = plaEvent.getEUTRANPositioningData();
                    if (eUtranPositioningData != null) {
                        slgPlaAvpValues.seteUtranPositioningData(eUtranPositioningData);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received E-UTRAN-Positioning-Data AVP: " + eUtranPositioningData);
                        }
                    }

                    byte[] ecgi = plaEvent.getECGI();
                    if (ecgi != null) {
                        slgPlaAvpValues.setEcgi(ecgi);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received ECGI AVP: " + ecgi);
                        }
                    }

                    GERANPositioningInfoAvp geranPositioningInfoAvp = plaEvent.getGERANPositioningInfo();
                    if (geranPositioningInfoAvp != null) {
                        slgPlaAvpValues.setGeranPositioningInfoAvp(geranPositioningInfoAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received GERAN-Positioning-Info AVP: " + geranPositioningInfoAvp);
                        }
                        byte[] geranPositioningData = geranPositioningInfoAvp.getGERANPositioningData();
                        if (geranPositioningData != null) {
                            slgPlaAvpValues.setGeranPositioningData(geranPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideLocationAnswer: received GERAN-Positioning-Data AVP: " + geranPositioningData);
                            }
                        }
                        byte[] geranGANSSPositioningData = geranPositioningInfoAvp.getGERANGANSSPositioningData();
                        if (geranGANSSPositioningData != null) {
                            slgPlaAvpValues.setGeranGANSSPositioningData(geranGANSSPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideLocationAnswer: received GERAN-GANSS-Positioning-Data AVP: " + geranGANSSPositioningData);
                            }
                        }
                    }

                    byte[] cellGlobalIdentity = plaEvent.getCellGlobalIdentity();
                    if (cellGlobalIdentity != null) {
                        slgPlaAvpValues.setCellGlobalIdentity(cellGlobalIdentity);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Cell-Global-Identity AVP: " + cellGlobalIdentity);
                        }
                    }

                    UTRANPositioningInfoAvp utranPositioningInfoAvp = plaEvent.getUTRANPositioningInfo();
                    if (utranPositioningInfoAvp != null) {
                        slgPlaAvpValues.setUtranPositioningInfoAvp(utranPositioningInfoAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received UTRAN-Positioning-Info AVP: " + utranPositioningInfoAvp);
                        }
                        byte[] utranPositioningData = utranPositioningInfoAvp.getUTRANPositioningData();
                        if (utranPositioningData != null) {
                            slgPlaAvpValues.setUtranPositioningData(utranPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideLocationAnswer: received UTRAN-Positioning-Data AVP: " + utranPositioningData);
                            }
                        }
                        byte[] utranGANSSPositioningData = utranPositioningInfoAvp.getUTRANGANSSPositioningData();
                        if (utranGANSSPositioningData != null) {
                            slgPlaAvpValues.setUtranGANSSPositioningData(utranGANSSPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideLocationAnswer: received UTRAN-GANSS-Positioning-Data AVP: " + utranGANSSPositioningData);
                            }
                        }
                        byte[] utranAdditionalPositioningData = utranPositioningInfoAvp.getUTRANAdditionalPositioningData();
                        if (utranAdditionalPositioningData != null) {
                            slgPlaAvpValues.setUtranAdditionalPositioningData(utranAdditionalPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideLocationAnswer: received UTRAN-Additional-Positioning-Data AVP: " + utranAdditionalPositioningData);
                            }
                        }
                    }

                    byte[] serviceAreaIdentity = plaEvent.getServiceAreaIdentity();
                    if (serviceAreaIdentity != null) {
                        slgPlaAvpValues.setServiceAreaIdentity(serviceAreaIdentity);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Service-Area-Identity AVP: " + serviceAreaIdentity);
                        }
                    }

                    byte[] sgsnNumber, mscNumber;
                    sgsnNumber = mscNumber = null;
                    DiameterIdentity sgsnName, sgsnRealm, mmeName, mmeRealm, tgppAAAServerName;
                    sgsnName = sgsnRealm = mmeName = mmeRealm = tgppAAAServerName = null;
                    long lcsCapabilitiesSets = -1;
                    Address gmlcAddress = null;
                    net.java.slee.resource.diameter.slg.events.avp.ServingNodeAvp servingNodeAvp = plaEvent.getServingNode();
                    if (servingNodeAvp != null) {
                        slgPlaAvpValues.setServingNodeAvp(servingNodeAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Serving-Node AVP: " + servingNodeAvp);
                        }
                        sgsnNumber = servingNodeAvp.getSGSNNumber();
                        sgsnName = servingNodeAvp.getSGSNName();
                        sgsnRealm = servingNodeAvp.getSGSNRealm();
                        mmeName = servingNodeAvp.getMMEName();
                        mmeRealm = servingNodeAvp.getMMERealm();
                        mscNumber = servingNodeAvp.getMSCNumber();
                        tgppAAAServerName = servingNodeAvp.get3GPPAAAServerName();
                        lcsCapabilitiesSets = servingNodeAvp.getLcsCapabilitiesSets();
                        gmlcAddress = servingNodeAvp.getGMLCAddress();
                    }

                    long plaFlags = plaEvent.getPLAFlags();
                    if (plaFlags > -1) {
                        slgPlaAvpValues.setPlaFlags(plaFlags);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received PLA-Flags AVP: " + plaFlags);
                        }
                    }

                    ESMLCCellInfoAvp esmlcCellInfoAvp = plaEvent.getESMLCCellInfo();
                    if (esmlcCellInfoAvp != null) {
                        slgPlaAvpValues.setEsmlcCellInfoAvp(esmlcCellInfoAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received ESMLC-Cell-Info AVP: " + esmlcCellInfoAvp);
                        }
                        if (ecgi == null)
                            ecgi = esmlcCellInfoAvp.getECGI();

                        long cellPortionId = esmlcCellInfoAvp.getCellPortionID();
                        if (cellPortionId > -1) {
                            slgPlaAvpValues.setCellPortionId(cellPortionId);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonProvideLocationAnswer: received Cell-Portion-ID AVP: " + cellPortionId);
                            }
                        }
                    }

                    byte[] civicAddress = plaEvent.getCivicAddress();
                    if (civicAddress != null) {
                        slgPlaAvpValues.setCivicAddress(civicAddress);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Civic-Address AVP: " + civicAddress);
                        }
                    }

                    long barometricPressure = plaEvent.getBarometricPressure();
                    if (barometricPressure > -1) {
                        slgPlaAvpValues.setBarometricPressure(barometricPressure);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonProvideLocationAnswer: received Barometric-Pressure AVP: " + barometricPressure);
                        }
                    }

                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setStatusCode(resultCode);
                        gmlcCdrState.setAccuracyFulfilmentIndicator(AVPHandler.diamAccFulInd2MapAccFulInd(accuracyFulfilmentIndicator));
                        gmlcCdrState.setLocationEstimate(lteLocationEstimate);
                        gmlcCdrState.setAdditionalLocationEstimate(lteAddLocationEstimate);
                        gmlcCdrState.setAgeOfLocationEstimate(AVPHandler.long2Int(ageOfLocationEstimate));
                        gmlcCdrState.setVelocityEstimate(lteVelocityEstimate);
                        gmlcCdrState.seteUTRANPositioningData(bytesToHex(eUtranPositioningData));
                        if (geranPositioningInfoAvp != null) {
                            gmlcCdrState.setGeranPositioningDataInformation(AVPHandler.lteGeranPosDataInfo2MapGeranPosDataInfo(geranPositioningInfoAvp.getGERANPositioningData()));
                            gmlcCdrState.setGeranGANSSpositioningData(AVPHandler.lteGeranGanssPosDataInfo2MapGeranGanssPosDataInfo(geranPositioningInfoAvp.getGERANGANSSPositioningData()));
                        }
                        gmlcCdrState.setCellGlobalIdentity(AVPHandler.byte2String(cellGlobalIdentity));
                        if (utranPositioningInfoAvp != null) {
                            gmlcCdrState.setUtranPositioningDataInfo(AVPHandler.lteUtranPosData2MapUtranPosDataInfo(utranPositioningInfoAvp.getUTRANPositioningData()));
                            gmlcCdrState.setUtranGANSSpositioningData(AVPHandler.lteUtranGanssPosData2MapUtranGanssPosDataInfo(utranPositioningInfoAvp.getUTRANGANSSPositioningData()));
                            gmlcCdrState.setUtranAdditionalPositioningData(AVPHandler.byte2String(utranPositioningInfoAvp.getUTRANAdditionalPositioningData()));
                        }
                        gmlcCdrState.setServiceAreaIdentity(AVPHandler.byte2String(serviceAreaIdentity));
                        gmlcCdrState.setSgsnNumber(AVPHandler.byte2IsdnAddressString(sgsnNumber));
                        gmlcCdrState.setSgsnName(AVPHandler.diameterIdToMapDiameterId(sgsnName));
                        gmlcCdrState.setSgsnRealm(AVPHandler.diameterIdToMapDiameterId(sgsnRealm));
                        gmlcCdrState.setMmeName(AVPHandler.diameterIdToMapDiameterId(mmeName));
                        gmlcCdrState.setMmeRealm(AVPHandler.diameterIdToMapDiameterId(mmeRealm));
                        gmlcCdrState.setMscNumber(AVPHandler.byte2IsdnAddressString(mscNumber));
                        gmlcCdrState.setAaaServerName(AVPHandler.diameterIdToMapDiameterId(tgppAAAServerName));
                        gmlcCdrState.sethGmlcAddress(AVPHandler.address2GsnAddress(gmlcAddress));
                        gmlcCdrState.setEUtranCgi(AVPHandler.lteEcgi2MapEutranCgi(ecgi));
                        if (esmlcCellInfoAvp != null)
                            gmlcCdrState.setCellPortionId(esmlcCellInfoAvp.getCellPortionID());
                        gmlcCdrState.setCivicAddress(AVPHandler.byte2String(civicAddress));
                        gmlcCdrState.setBarometricPressureMeasurement(barometricPressure);
                        if (resultCode == 2001) {
                            mlpRespResult = MLPResponse.MLPResultType.OK;
                            // Handle successful retrieval of response to subscriber's location request (SLg ELP Provide-Location-Answer)
                            this.handleLTELocationServicesResponse(mlpRespResult, sLhRirRequestValues, sLhRiaAvpResponseValues, slgPlaAvpValues, null, mlpClientErrorMessage, mlpTriggeredReportingService);
                            this.createCDRRecord(RecordStatus.LTE_PLR_SUCCESS);
                        } else {
                            Map<MLPResponse.MLPResultType, String> mlpResultTypeStringMap = handleRecordAndLocationReportOnDiameterResultCode(resultCode, mlpRespResult, mlpClientErrorMessage, msisdnAddress, imsi,
                                "PLR", referenceNumber, gmlcCdrState, false,
                                plaOriginHost != null ? plaOriginHost.toString() : null, plaOriginHost != null ? plaOriginHost.toString() : null,
                                mlpTriggeredReportingService);
                            if (mlpResultTypeStringMap != null) {
                                mlpRespResult = mlpResultTypeStringMap.entrySet().iterator().next().getKey();
                                mlpClientErrorMessage = mlpResultTypeStringMap.entrySet().iterator().next().getValue();
                            }
                            this.handleLTELocationServicesResponse(mlpRespResult, sLhRirRequestValues, sLhRiaAvpResponseValues, slgPlaAvpValues, null, mlpClientErrorMessage, mlpTriggeredReportingService);
                        }
                    }
                }
            } catch (Throwable e) {
                logger.warning("Exception when processing onProvideLocationAnswer: " + e.getMessage(), e);
                this.createCDRRecord(RecordStatus.LTE_PLR_SYSTEM_FAILURE);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on SLg PLA: " + e.getMessage(),
                    "PLR", msisdnAddress, imsi, referenceNumber, null, null,
                    plaOriginHost != null ? plaOriginHost.toString() : null, plaOriginRealm != null ? plaOriginRealm.toString() : null,
                    mlpTriggeredReportingService);
                if (transaction != null)
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onProvideLocationAnswer=%s", plaEvent), e);
            this.createCDRRecord(RecordStatus.LTE_PLR_SYSTEM_FAILURE);
            String host = null, realm = null;
            if (plaEvent != null) {
                host = plaEvent.getOriginHost().toString();
                realm = plaEvent.getOriginRealm().toString();
            }
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on SLg PLA: " + e.getMessage(),
                "PLR", msisdnAddress, imsi, referenceNumber, null, null, host, realm, mlpTriggeredReportingService);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } finally {
            detachFromSLgClientActivity(aci);
        }
    }

    /**
     * SLg LRR Event
     */
    public void onLocationReportRequest(LocationReportRequest lrrEvent, ActivityContextInterface aci) {

        try {
            if (this.logger.isFineEnabled()) {
                logger.fine("\n\nReceived SLg LRA with session Id: " + lrrEvent.getSessionId() + ", host '" + lrrEvent.getOriginHost()
                    + "', realm '" + lrrEvent.getOriginRealm() + "'");
                this.logger.fine("\nonLocationReportRequest event details= " + lrrEvent);
            }
            net.java.slee.resource.diameter.base.events.avp.DiameterIdentity lrrOriginHost, lrrOriginRealm, gmlcHost, gmlcRealm;
            if (lrrEvent != null) {
                lrrOriginHost = lrrEvent.getOriginHost();
                lrrOriginRealm = lrrEvent.getOriginRealm();
            } else {
                String origHost = gmlcPropertiesManagement.getDiameterDestHost();
                String origRealm = gmlcPropertiesManagement.getDiameterDestRealm();
                lrrOriginHost = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(origHost);
                lrrOriginRealm = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(origRealm);
            }
            SLgLrrAvpValues slgLrrAvpValues = new SLgLrrAvpValues();

            try {

                MLPResponse.MLPResultType mlpRespResult = null;
                String mlpClientErrorMessage = null;
                gmlcHost = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginHost());
                gmlcRealm = new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginRealm());

                // CDR initialization
                GMLCCDRState gmlcCdrState = CDRCreationHelper.slhSlgCdrInitializer(aci, this.getCDRInterface(), null, null, lrrEvent,
                    lrrOriginHost, lrrOriginRealm, gmlcHost, gmlcRealm);
                // Set timer last
                this.setTimer(aci);

                if (lrrEvent != null) {
                    // < Location-Report-Request> ::=	< Diameter Header: 8388621, REQ, PXY, 16777255 >
                    mlpRespResult = MLPResponse.MLPResultType.OK;

                    LocationEvent locationEvent = lrrEvent.getLocationEvent();
                    if (locationEvent != null) {
                        slgLrrAvpValues.setLocationEvent(locationEvent);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Location-Event AVP: " + locationEvent);
                        }
                    }

                    LCSEPSClientNameAvp lcsEPSClientName = lrrEvent.getLCSEPSClientName();
                    String lcsNameString = null;
                    LCSFormatIndicator lcsFormatIndicator = null;
                    if (lcsEPSClientName != null) {
                        slgLrrAvpValues.setLcsEPSClientName(lcsEPSClientName);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received LCS-EPS-Client-Name AVP: " + lcsEPSClientName);
                        }
                        lcsNameString = lcsEPSClientName.getLCSNameString();
                        if (lcsNameString != null) {
                            slgLrrAvpValues.setLcsNameString(lcsNameString);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received LCS-Name-String AVP: " + lcsNameString);
                            }
                        }
                        lcsFormatIndicator = lcsEPSClientName.getLCSFormatIndicator();
                        if (lcsFormatIndicator != null) {
                            slgLrrAvpValues.setLcsFormatIndicator(lcsFormatIndicator);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received LCS-Format-Indicator AVP: " + lcsFormatIndicator);
                            }
                        }
                    }

                    String userName = lrrEvent.getUserName();
                    if (userName != null) {
                        slgLrrAvpValues.setUserName(userName);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received User-Name AVP: " + userName);
                        }
                    }

                    byte[] msisdn = lrrEvent.getMSISDN();
                    if (msisdn != null) {
                        slgLrrAvpValues.setMsisdn(msisdn);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received MSISDN AVP: " + msisdn);
                        }
                    }

                    String imei = lrrEvent.getIMEI();
                    if (imei != null) {
                        slgLrrAvpValues.setImei(imei);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received IMEI AVP: " + imei);
                        }
                    }

                    byte[] locationEstimate = lrrEvent.getLocationEstimate();
                    ExtGeographicalInformation lteLocationEstimate = null;
                    AddGeographicalInformation lteAddLocationEstimate = null;
                    if (locationEstimate != null) {
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nnonLocationReportRequest: received Location-Estimate AVP: " + locationEstimate);
                        }
                        lteLocationEstimate = AVPHandler.lteLocationEstimate2ExtGeographicalInformation(locationEstimate);
                        TypeOfShape typeOfShape = lteLocationEstimate.getTypeOfShape();
                        if (typeOfShape == TypeOfShape.Polygon) {
                            lteAddLocationEstimate = AVPHandler.lteLocationEstimate2AddGeographicalInformation(locationEstimate);
                            slgLrrAvpValues.setLocationEstimate(lteAddLocationEstimate.getData());
                        } else {
                            slgLrrAvpValues.setLocationEstimate(locationEstimate);
                        }
                    }

                    net.java.slee.resource.diameter.slg.events.avp.AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = lrrEvent.getAccuracyFulfilmentIndicator();
                    if (accuracyFulfilmentIndicator != null) {
                        slgLrrAvpValues.setAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Accuracy-Fulfilment-Indicator AVP: " + accuracyFulfilmentIndicator);
                        }
                    }

                    long ageOfLocationEstimate = lrrEvent.getAgeOfLocationEstimate();
                    if (ageOfLocationEstimate > -1) {
                        slgLrrAvpValues.setAgeOfLocationEstimate(ageOfLocationEstimate);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Age-Of-Location-Estimate AVP: " + ageOfLocationEstimate);
                        }
                    }

                    byte[] velocityEstimate = lrrEvent.getVelocityEstimate();
                    VelocityEstimate lteVelocityEstimate = null;
                    if (velocityEstimate != null) {
                        slgLrrAvpValues.setVelocityEstimate(velocityEstimate);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Velocity-Estimate AVP: " + velocityEstimate);
                        }
                        lteVelocityEstimate = AVPHandler.lteVelocityEstimate2MapVelocityEstimate(velocityEstimate);
                    }

                    byte[] eUtranPositioningData = lrrEvent.getEUTRANPositioningData();
                    if (eUtranPositioningData != null) {
                        slgLrrAvpValues.seteUtranPositioningData(eUtranPositioningData);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received E-UTRAN-Positioning-Data AVP: " + eUtranPositioningData);
                        }
                    }

                    byte[] ecgi = lrrEvent.getECGI();
                    if (ecgi != null) {
                        slgLrrAvpValues.setEcgi(ecgi);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received ECGI AVP: " + ecgi);
                        }
                    }

                    GERANPositioningInfoAvp geranPositioningInfoAvp = lrrEvent.getGERANPositioningInfo();
                    byte[] geranPositioningData = null;
                    byte[] geranGANSSPositioningData = null;
                    if (geranPositioningInfoAvp != null) {
                        slgLrrAvpValues.setGeranPositioningInfoAvp(geranPositioningInfoAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received GERAN-Positioning-Info AVP: " + geranPositioningInfoAvp);
                        }
                        geranPositioningInfoAvp.getGERANPositioningData();
                        if (geranPositioningData != null) {
                            slgLrrAvpValues.setGeranPositioningData(geranPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received GERAN-Positioning-Data AVP: " + geranPositioningData);
                            }
                        }
                        geranGANSSPositioningData = geranPositioningInfoAvp.getGERANGANSSPositioningData();
                        if (geranGANSSPositioningData != null) {
                            slgLrrAvpValues.setGeranGANSSPositioningData(geranGANSSPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received GERAN-GANSS-Positioning-Data AVP: " + geranGANSSPositioningData);
                            }
                        }
                    }

                    byte[] cellGlobalIdentity = lrrEvent.getCellGlobalIdentity();
                    if (cellGlobalIdentity != null) {
                        slgLrrAvpValues.setCellGlobalIdentity(cellGlobalIdentity);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Cell-Global-Identity AVP: " + cellGlobalIdentity);
                        }
                    }

                    UTRANPositioningInfoAvp utranPositioningInfoAvp = lrrEvent.getUTRANPositioningInfo();
                    byte[] utranPositioningData = null;
                    byte[] utranGANSSPositioningData = null;
                    byte[] utranAdditionalPositioningData = null;
                    if (utranPositioningInfoAvp != null) {
                        slgLrrAvpValues.setUtranPositioningInfoAvp(utranPositioningInfoAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received UTRAN-Positioning-Info AVP: " + utranPositioningInfoAvp);
                        }
                        utranPositioningData = utranPositioningInfoAvp.getUTRANPositioningData();
                        if (utranPositioningData != null) {
                            slgLrrAvpValues.setUtranPositioningData(utranPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received UTRAN-Positioning-Data AVP: " + utranPositioningData);
                            }
                        }
                        utranGANSSPositioningData = utranPositioningInfoAvp.getUTRANGANSSPositioningData();
                        if (utranGANSSPositioningData != null) {
                            slgLrrAvpValues.setUtranGANSSPositioningData(utranGANSSPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received UTRAN-GANSS-Positioning-Data AVP: " + utranGANSSPositioningData);
                            }
                        }
                        utranAdditionalPositioningData = utranPositioningInfoAvp.getUTRANAdditionalPositioningData();
                        if (utranAdditionalPositioningData != null) {
                            slgLrrAvpValues.setUtranAdditionalPositioningData(utranAdditionalPositioningData);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received UTRAN-Additional-Positioning-Data AVP: " + utranAdditionalPositioningData);
                            }
                        }
                    }

                    byte[] serviceAreaIdentity = lrrEvent.getServiceAreaIdentity();
                    if (serviceAreaIdentity != null) {
                        slgLrrAvpValues.setServiceAreaIdentity(serviceAreaIdentity);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Service-Area-Identity AVP: " + serviceAreaIdentity);
                        }
                    }

                    long lcsServiceTypeId = lrrEvent.getLCSServiceTypeID();
                    if (lcsServiceTypeId > -1) {
                        slgLrrAvpValues.setLcsServiceTypeId(lcsServiceTypeId);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received LCS-Service-Type-ID AVP: " + lcsServiceTypeId);
                        }
                    }

                    PseudonymIndicator pseudonymIndicator = lrrEvent.getPseudonymIndicator();
                    if (pseudonymIndicator != null) {
                        slgLrrAvpValues.setPseudonymIndicator(pseudonymIndicator);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Pseudonym-Indicator AVP: " + pseudonymIndicator);
                        }
                    }

                    LCSQoSClass lcsQoSClass = lrrEvent.getLCSQoSClass();
                    if (lcsQoSClass != null) {
                        slgLrrAvpValues.setLcsQoSClass(lcsQoSClass);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received LCS-QoS-Class AVP: " + lcsQoSClass);
                        }
                    }

                    byte[] sgsnNumber, mscNumber;
                    sgsnNumber = mscNumber = null;
                    DiameterIdentity sgsnName, sgsnRealm, mmeName, mmeRealm, tgppAAAServerName;
                    sgsnName = sgsnRealm = mmeName = mmeRealm = tgppAAAServerName = null;
                    long lcsCapabilitiesSets = -1;
                    Address servingNodeGmlcAddress = null;
                    net.java.slee.resource.diameter.slg.events.avp.ServingNodeAvp servingNodeAvp = lrrEvent.getServingNode();
                    if (servingNodeAvp != null) {
                        slgLrrAvpValues.setServingNodeAvp(servingNodeAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Serving-Node AVP: " + servingNodeAvp);
                        }
                        sgsnNumber = servingNodeAvp.getSGSNNumber();
                        slgLrrAvpValues.setSgsnNumber(sgsnNumber);
                        sgsnName = servingNodeAvp.getSGSNName();
                        slgLrrAvpValues.setSgsnName(sgsnName);
                        sgsnRealm = servingNodeAvp.getSGSNRealm();
                        slgLrrAvpValues.setSgsnRealm(sgsnRealm);
                        mmeName = servingNodeAvp.getMMEName();
                        slgLrrAvpValues.setMmeName(mmeName);
                        mmeRealm = servingNodeAvp.getMMERealm();
                        slgLrrAvpValues.setMmeRealm(mmeRealm);
                        mscNumber = servingNodeAvp.getMSCNumber();
                        slgLrrAvpValues.setMscNumber(mscNumber);
                        tgppAAAServerName = servingNodeAvp.get3GPPAAAServerName();
                        slgLrrAvpValues.setTgppAAAServerName(tgppAAAServerName);
                        lcsCapabilitiesSets = servingNodeAvp.getLcsCapabilitiesSets();
                        slgLrrAvpValues.setLcsCapabilitiesSets(lcsCapabilitiesSets);
                        servingNodeGmlcAddress = servingNodeAvp.getGMLCAddress();
                        slgLrrAvpValues.setGmlcAddress(servingNodeGmlcAddress);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nServing node values: " +
                                "\nsgsnNumber = " + new String(sgsnNumber) +
                                "\nsgsnName = " + slgLrrAvpValues.getSgsnName() +
                                "\nsgsnRealm = " + slgLrrAvpValues.getSgsnRealm() +
                                "\nmmeName = " + slgLrrAvpValues.getMmeName() +
                                "\nmmeRealm = " + slgLrrAvpValues.getMmeRealm() +
                                "\nmscNumber = " + new String(slgLrrAvpValues.getMscNumber()) +
                                "\ntgppAAAServerName = " + slgLrrAvpValues.getTgppAAAServerName() +
                                "\nlcsCapabilitiesSets = " + slgLrrAvpValues.getLcsCapabilitiesSets() +
                                "\ngmlcAddress = " + slgLrrAvpValues.getGmlcAddress());
                        }
                    }

                    byte[] lcsReferenceNumber = lrrEvent.getLCSReferenceNumber();
                    if (lcsReferenceNumber != null) {
                        slgLrrAvpValues.setLcsReferenceNumber(lcsReferenceNumber);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received LCS-Reference-Number AVP: " + lcsReferenceNumber);
                        }
                    }

                    long deferredLocationType, terminationCause;
                    deferredLocationType = terminationCause = -1;
                    DeferredMTLRDataAvp deferredMTLRDataAvp = lrrEvent.getDeferredMTLRData();
                    if (deferredMTLRDataAvp != null) {
                        slgLrrAvpValues.setDeferredMTLRDataAvp(deferredMTLRDataAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Deferred-MT-LR-Data AVP");
                        }
                        deferredLocationType = deferredMTLRDataAvp.getDeferredLocationType();
                        terminationCause = deferredMTLRDataAvp.getTerminationCause();
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Deferred-MT-LR-Data AVP, Deferred-Location-Type=" + deferredLocationType +
                                ",Termination-Cause=" + terminationCause);
                        }
                    }
                    long reportingAmount, reportingInterval;
                    reportingAmount = reportingInterval = -1;
                    PeriodicLDRInfoAvp periodicLDRInformationAvp = lrrEvent.getPeriodicLDRInformation();
                    if (periodicLDRInformationAvp != null) {
                        slgLrrAvpValues.setPeriodicLDRInformation(periodicLDRInformationAvp);
                        reportingAmount = periodicLDRInformationAvp.getReportingAmount();
                        reportingInterval = periodicLDRInformationAvp.getReportingInterval();
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Periodic-LDR-Information AVP, Reporting-Amount=" + reportingAmount +
                                ",Reporting-Interval=" + reportingInterval);
                        }
                    }

                    ESMLCCellInfoAvp esmlcCellInfoAvp = lrrEvent.getESMLCCellInfo();
                    long cellPortionId = -1L;
                    if (esmlcCellInfoAvp != null) {
                        slgLrrAvpValues.setEsmlcCellInfoAvp(esmlcCellInfoAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received ESMLC-Cell-Info AVP: " + esmlcCellInfoAvp);
                        }
                        if (ecgi == null) {
                            ecgi = esmlcCellInfoAvp.getECGI();
                            slgLrrAvpValues.setEcgi(ecgi);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received ECGI AVP: " + ecgi);
                            }
                        }
                        cellPortionId = esmlcCellInfoAvp.getCellPortionID();
                        if (cellPortionId > -1) {
                            slgLrrAvpValues.setCellPortionId(cellPortionId);
                            if (this.logger.isFineEnabled()) {
                                this.logger.fine("\nonLocationReportRequest: received Cell-Portion-ID AVP: " + cellPortionId);
                            }
                        }
                    }

                    byte[] oneXRTTRCID = lrrEvent.get1xRTTRCID();
                    if (oneXRTTRCID != null) {
                        slgLrrAvpValues.setOneXRttRcid(oneXRTTRCID);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received 1x-RTT-RCID AVP: " + oneXRTTRCID);
                        }
                    }

                    DelayedLocationReportingDataAvp delayedLocationReportingDataAvp = lrrEvent.getDelayedLocationReportingData();
                    if (delayedLocationReportingDataAvp != null) {
                        slgLrrAvpValues.setDelayedLocationReportingDataAvp(delayedLocationReportingDataAvp);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Delayed-Location-Reporting-Data AVP: " + delayedLocationReportingDataAvp);
                        }
                        terminationCause = delayedLocationReportingDataAvp.getTerminationCause();
                    }

                    long lrrFlags = lrrEvent.getLRRFlags();
                    if (lrrFlags > -1) {
                        slgLrrAvpValues.setLrrFlags(lrrFlags);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received LRR-Flags AVP");
                        }
                    }

                    byte[] civicAddress = lrrEvent.getCivicAddress();
                    if (civicAddress != null) {
                        slgLrrAvpValues.setCivicAddress(civicAddress);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Civic-Address AVP");
                        }
                    }

                    long barometricPressure = lrrEvent.getBarometricPressure();
                    if (barometricPressure > -1) {
                        slgLrrAvpValues.setBarometricPressure(barometricPressure);
                        if (this.logger.isFineEnabled()) {
                            this.logger.fine("\nonLocationReportRequest: received Barometric-Pressure AVP");
                        }
                    }

                    Long lraFlags = 1L;

                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setStatusCode(2001L);
                        gmlcCdrState.setLocationEvent(locationEvent);
                        gmlcCdrState.setImsi(AVPHandler.userName2Imsi(userName));
                        gmlcCdrState.setMsisdn(AVPHandler.tbcd2IsdnAddressString(msisdn));
                        gmlcCdrState.setImei(AVPHandler.string2MapImei(imei));
                        gmlcCdrState.setLocationEstimate(lteLocationEstimate);
                        gmlcCdrState.setAdditionalLocationEstimate(lteAddLocationEstimate);
                        gmlcCdrState.setAccuracyFulfilmentIndicator(AVPHandler.diamAccFulInd2MapAccFulInd(accuracyFulfilmentIndicator));
                        gmlcCdrState.setAgeOfLocationEstimate(AVPHandler.long2Int(ageOfLocationEstimate));
                        gmlcCdrState.setVelocityEstimate(lteVelocityEstimate);
                        gmlcCdrState.setVelocityEstimate(AVPHandler.lteVelocityEstimate2MapVelocityEstimate(velocityEstimate));
                        gmlcCdrState.seteUTRANPositioningData(bytesToHex(eUtranPositioningData));
                        gmlcCdrState.setEUtranCgi(AVPHandler.lteEcgi2MapEutranCgi(ecgi));
                        gmlcCdrState.setGeranPositioningDataInformation(AVPHandler.lteGeranPosDataInfo2MapGeranPosDataInfo(geranPositioningData));
                        gmlcCdrState.setGeranGANSSpositioningData(AVPHandler.lteGeranGanssPosDataInfo2MapGeranGanssPosDataInfo(geranGANSSPositioningData));
                        gmlcCdrState.setCellGlobalIdentity(AVPHandler.byte2String(cellGlobalIdentity));
                        gmlcCdrState.setUtranPositioningDataInfo(AVPHandler.lteUtranPosData2MapUtranPosDataInfo(utranPositioningData));
                        gmlcCdrState.setUtranGANSSpositioningData(AVPHandler.lteUtranGanssPosData2MapUtranGanssPosDataInfo(utranGANSSPositioningData));
                        gmlcCdrState.setUtranAdditionalPositioningData(AVPHandler.byte2String(utranAdditionalPositioningData));
                        gmlcCdrState.setServiceAreaIdentity(AVPHandler.byte2String(serviceAreaIdentity));
                        gmlcCdrState.setLcsServiceTypeID(AVPHandler.long2Int(lcsServiceTypeId));
                        gmlcCdrState.setPseudonymIndicator(AVPHandler.ltePseudonymInd2Boolean(pseudonymIndicator));
                        gmlcCdrState.setLteLcsQoSClass(lcsQoSClass);
                        gmlcCdrState.setDeferredmtlrData(AVPHandler.lteDeferredMtlrData2MapDeferredmtlrData(deferredMTLRDataAvp));
                        if (lcsReferenceNumber != null)
                            gmlcCdrState.setLcsReferenceNumber(AVPHandler.byte2Int(lcsReferenceNumber));
                        gmlcCdrState.setPeriodicLDRInfo(AVPHandler.ltePeriodicLDRInfo2MapPeriodicLDRInfo(periodicLDRInformationAvp));
                        gmlcCdrState.setSgsnNumber(AVPHandler.byte2IsdnAddressString(sgsnNumber));
                        gmlcCdrState.setSgsnName(AVPHandler.diameterIdToMapDiameterId(sgsnName));
                        gmlcCdrState.setSgsnRealm(AVPHandler.diameterIdToMapDiameterId(sgsnRealm));
                        gmlcCdrState.setMmeName(AVPHandler.diameterIdToMapDiameterId(mmeName));
                        gmlcCdrState.setMmeRealm(AVPHandler.diameterIdToMapDiameterId(mmeRealm));
                        gmlcCdrState.setMscNumber(AVPHandler.byte2IsdnAddressString(mscNumber));
                        gmlcCdrState.setAaaServerName(AVPHandler.diameterIdToMapDiameterId(tgppAAAServerName));
                        gmlcCdrState.sethGmlcAddress(AVPHandler.address2GsnAddress(servingNodeGmlcAddress));
                        gmlcCdrState.setCellPortionId(cellPortionId);
                        gmlcCdrState.setCivicAddress(AVPHandler.byte2String(civicAddress));
                        gmlcCdrState.setBarometricPressureMeasurement(barometricPressure);
                        this.createCDRRecord(RecordStatus.LTE_LRR_SUCCESS);
                    }

                    SLgClientSessionActivity slgClientSessionActivity = this.slgProvider.createSLgClientSessionActivity(lrrEvent.getOriginHost(), lrrEvent.getOriginRealm(), lrrEvent.getSessionId());
                    ActivityContextInterface slgACIF = slgAcif.getActivityContextInterface(slgClientSessionActivity);
                    slgACIF.attach(getSbbContext().getSbbLocalObject());

                    // < Location-Report-Answer > ::=    < Diameter Header: 8388621, PXY, 16777255>
                    LocationReportAnswer lra = slgClientSessionActivity.createLocationReportAnswer(lrrEvent.getHeader());

                    String gmlcAddressStr = gmlcPropertiesManagement.getDiameterGmlcNumber();
                    byte[] gmlcAddressByteArray = gmlcAddressStr.getBytes();
                    Address gmlcAddress = new Address(AddressType.ADDRESS_E164, gmlcAddressByteArray);
                    DiameterAvp lraFlagsAvp = slgAVPFactory.createAvp(SLg_VENDOR_ID, ELPAVPCodes.LRA_FLAGS, lraFlags);
                    DiameterAvp lcsReferenceNumberAvp = slgAVPFactory.createAvp(SLg_VENDOR_ID, ELPAVPCodes.LCS_REFERENCE_NUMBER, lcsReferenceNumber);
                    if (gmlcAddress != null)
                        lra.setGMLCAddress(gmlcAddress);
                    if (lraFlags != null)
                        lra.setLRAFlags(lraFlagsAvp.longValue());
                    if (lcsReferenceNumber != null)
                        lra.setLCSReferenceNumber(lcsReferenceNumberAvp.byteArrayValue());
                    /*
                    if (this.reportingPLMNListAvp != null) {
                        lra.setReportingPLMNList(this.reportingPLMNListAvp);
                    }
                     */

                    slgClientSessionActivity.sendLocationReportAnswer(lra);

                    // Handle successful retrieval of subscriber's location report request (SLR request) info by sending HTTP POST back to the requestor
                    if (this.logger.isFineEnabled()) {
                        if (lcsReferenceNumber != null)
                            logger.fine(String.format("Handling SubscriberLocationReport POST ReferenceNumber '%s'\n", AVPHandler.byte2Int(lcsReferenceNumber)));
                        else
                            logger.fine(String.format("HandlingL SubscriberLocationReport POST (non-triggered by PSL)\n"));
                    }
                    httpSubscriberLocationReport = getHttpSubscriberLocationReport();
                    if (lcsReferenceNumber != null)
                        httpSubscriberLocationReport.Perform(HttpReport.HttpMethod.POST, AVPHandler.byte2Int(lcsReferenceNumber), slgLrrAvpValues, false);
                    else
                        httpSubscriberLocationReport.Perform(HttpReport.HttpMethod.POST, null, slgLrrAvpValues, false);
                    httpSubscriberLocationReport.closeMongo();
                } else {
                    if (getGMLCCDRState().isInitialized()) {
                        this.createCDRRecord(RecordStatus.LTE_LRR_UNSPECIFIED_ERROR);
                    }
                }
            } catch (Throwable e) {
                logger.warning("Exception when processing onLocationReportRequest response: " + e.getMessage(), e);
                this.createCDRRecord(RecordStatus.LTE_LRR_SYSTEM_FAILURE);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onLocationReportRequest=%s", lrrEvent), e);
            this.createCDRRecord(RecordStatus.LTE_LRR_SYSTEM_FAILURE);
        } finally {
            detachFromSLgClientActivity(aci);
        }
    }

    /**
     * SLg LRA Event
     */
    public void onLocationReportAnswer(LocationReportAnswer lraEvent, ActivityContextInterface aci) {
        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onLocationReportAnswer = " + lraEvent);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onLocationReportAnswer=%s", lraEvent), e);
        }
    }

    //////////////////////////
    // IMS Events handlers //
    /////////////////////////

    /**
     * IMS User Data retrieval between AS (GMLC) and HSS
     * Sh (AS-HSS) Diameter-based interface events for user data retrieval according to 3GPP TS 29.328 / 29.329
     */

    /**
     * Sh UDR Event
     */
    public void onUserDataRequest(UserDataRequest udrEvent, ActivityContextInterface aci) {

        try {
            if (this.logger.isFineEnabled()) {
                this.logger.fine("\nReceived onUserDataRequest = " + udrEvent);
            }

        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onUserDataRequest=%s", udrEvent), e);
        }

    }

    /**
     * Sh UDA Event
     */
    public void onUserDataAnswer(UserDataAnswer udaEvent, ActivityContextInterface aci) {

        String shUdrMsisdn = null, publicIdsMsisdn = null, imsPublicIdentity = null, eUtranCellGlobalId = null, trackingAreaId = null, geographicalInfoEps = null,
            geodeticInfoEps = null, mmeName = null, currentLocationRetrieved = null, ageOfLocationInformation = null, csgId = null, visitedPlmnId = null,
            ratType = null, nrCellGlobalId = null, amfAddress = null, smsfAddress = null, curlUser = null;
        LocalTimeZone localTimeZone = null;
        Long transaction = null;
        byte[] shUserData = null;
        DateTime eventTime = DateTime.now();

        try {
            if (this.logger.isFineEnabled()) {
                logger.fine("\n\nReceived Sh UDA with session Id: " + udaEvent.getSessionId() + ", host '" + udaEvent.getOriginHost()
                    + "', realm '" + udaEvent.getOriginRealm() + "'");
                this.logger.fine("\nonUserDataAnswer event details: " + udaEvent);
            }
            ShUdaAvpValues shUdaAvpValues = new ShUdaAvpValues();

            try {

                MLPResponse.MLPResultType mlpRespResult = null;
                String mlpClientErrorMessage = null;
                net.java.slee.resource.diameter.base.events.avp.DiameterIdentity gmlcHost =
                    new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginHost());
                net.java.slee.resource.diameter.base.events.avp.DiameterIdentity gmlcRealm =
                    new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginRealm());

                // CDR initialization
                GMLCCDRState gmlcCdrState = CDRCreationHelper.shUdaCdrInitializer(aci, this.getCDRInterface(), udaEvent, gmlcHost, gmlcRealm);
                // Set timer last
                this.setTimer(aci);

                if (udaEvent != null) {

                    long resultCode = udaEvent.getResultCode();
                    ExperimentalResultAvp experimentalResultAvp = udaEvent.getExperimentalResult();
                    if (experimentalResultAvp != null)
                        resultCode = experimentalResultAvp.getExperimentalResultCode();

                    // Transaction
                    transaction = mobileCoreNetworkTransactions.getTransactionId(udaEvent.getSessionId());
                    if (transaction == null) {
                        throw new Exception();
                    }
                    TimerID udaTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
                    if (udaTimerID != null)
                        this.timerFacility.cancelTimer(udaTimerID);
                    shUdrMsisdn = (String) mobileCoreNetworkTransactions.getValue(transaction, "shUdrMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
                    gmlcCdrState.setDialogStartTime(dialogStartTime);
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setCurlUser(curlUser);
                        if (dialogStartTime != null && eventTime != null) {
                            Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                            gmlcCdrState.setDialogDuration(dialogDuration);
                        }
                    }
                    mobileCoreNetworkTransactions.destroy(transaction);

                    if (resultCode == 2001) {

                        mlpRespResult = MLPResponse.MLPResultType.OK;

                        if (udaEvent.getUserData() != null) {
                            shUserData = udaEvent.getUserData();
                            shUdaAvpValues.setUserData(shUserData);
                            String xmlShuserData = new String(shUserData, "UTF8");
                            ShDataReader shDataReader = new ShDataReader();
                            shDataReader.ShXMLReader(xmlShuserData);

                            PublicIds shPublicIds = shDataReader.getShPublicIdentifiers();
                            if (shPublicIds != null) {
                                publicIdsMsisdn = shPublicIds.getMsisdn();
                                shUdaAvpValues.setMsisdn(publicIdsMsisdn);
                                imsPublicIdentity = shPublicIds.getImsPublicIdentity();
                                shUdaAvpValues.setImsPublicIdentity(imsPublicIdentity);
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nPublicIdentifiers:");
                                    if (shUdaAvpValues.getMsisdn() != null)
                                        this.logger.fine("\nMSISDN: " + shUdaAvpValues.getMsisdn());
                                    if (shUdaAvpValues.getCsCellGlobalId() != null)
                                        this.logger.fine("\nIMSPublicIdentity: " + shUdaAvpValues.getImsPublicIdentity());
                                }
                            } else {
                                if (this.logger.isFineEnabled()) {
                                    this.logger.fine("\nPublicIdentifiers is NULL !!!");
                                }
                                publicIdsMsisdn = shUdrMsisdn;
                                shUdaAvpValues.setMsisdn(publicIdsMsisdn);
                            }

                            CSLocationInformation shDataCsLocation = shDataReader.getShCSLocationInfo();
                            if (shDataCsLocation != null) {
                                shUdaAvpValues.setCsLocationInformation(shDataCsLocation);
                                String cellGlobalId = shDataCsLocation.getCellGlobalId();
                                String serviceAreaId = shDataCsLocation.getServiceAreaId();
                                String locationAreaId = shDataCsLocation.getLocationAreaId();
                                String locationNumber = shDataCsLocation.getLocationNumber();
                                String geographicalInfo = shDataCsLocation.getGeographicalInformation();
                                String geodeticInfo = shDataCsLocation.getGeodeticInformation();
                                ShCellGlobalId csCellGlobalId = new ShCellGlobalId();
                                ShServiceAreaId csServiceAreaId = new ShServiceAreaId();
                                ShLocationAreaId csLocationAreaId = new ShLocationAreaId();
                                ShLocationNumber csLocationNumber = new ShLocationNumber();
                                ShGeographicalInformation csGeographicalInformation = new ShGeographicalInformation();
                                ShGeodeticInformation csGeodeticInformation = new ShGeodeticInformation();
                                if (cellGlobalId != null) {
                                    csCellGlobalId.setCellGlobalIdStr(cellGlobalId);
                                    shUdaAvpValues.setCsCellGlobalId(csCellGlobalId);
                                }
                                if (serviceAreaId != null) {
                                    csServiceAreaId.setServiceAreaIdStr(serviceAreaId);
                                    shUdaAvpValues.setCsServiceAreaId(csServiceAreaId);
                                }
                                if (locationAreaId != null) {
                                    csLocationAreaId.setLocationAreIdStr(locationAreaId);
                                    shUdaAvpValues.setCsLocationAreaId(csLocationAreaId);
                                }
                                if (csLocationNumber != null) {
                                    csLocationNumber.setLocationNumberStr(locationNumber);
                                    shUdaAvpValues.setLocationNumber(csLocationNumber);
                                }
                                if (csGeographicalInformation != null) {
                                    csGeographicalInformation.setGeographicalInfoStr(geographicalInfo);
                                    shUdaAvpValues.setCsGeographicalInformation(csGeographicalInformation);
                                }
                                if (csGeodeticInformation != null) {
                                    csGeodeticInformation.setGeodeticInfoStr(geodeticInfo);
                                    shUdaAvpValues.setCsGeodeticInformation(csGeodeticInformation);
                                }
                                if (shDataCsLocation.getMscNumber() != null)
                                    shUdaAvpValues.setMscNumber(shDataCsLocation.getMscNumber());
                                if (shDataCsLocation.getVlrNumber() != null)
                                    shUdaAvpValues.setVlrNumber(shDataCsLocation.getVlrNumber());
                                if (shDataCsLocation.getCurrentLocationRetrieved() != null)
                                    shUdaAvpValues.setCsCurrentLocationInfoRetrieved(shDataCsLocation.getCurrentLocationRetrieved());
                                if (shDataCsLocation.getAgeOfLocationInformation() != null)
                                    shUdaAvpValues.setCsAgeOfLocationInfo(Integer.valueOf(shDataCsLocation.getAgeOfLocationInformation()));
                                CSLocationInformationExtension csLocationExtension = shDataCsLocation.getCsLocationInformationExtension();
                                if (csLocationExtension != null) {
                                    UserCSGInformation userCSGInformationCS = csLocationExtension.getUserCSGInformation();
                                    if (userCSGInformationCS != null) {
                                        csgId = userCSGInformationCS.getCsgid();
                                        ShUserCSGInformation shCsUserCSGInformation = new ShUserCSGInformation();
                                        shCsUserCSGInformation.setUserCSGInformationStr(csgId);
                                        shUdaAvpValues.setUserCSGInformation(shCsUserCSGInformation);
                                        // TODO CDR (doesn't seem to be needed)
                                    }
                                    CSLocationInformationExtension2 csLocationInformationExtension2 = csLocationExtension.getCsLocationInformationExtension2();
                                    if (csLocationInformationExtension2 != null) {
                                        eUtranCellGlobalId = csLocationInformationExtension2.geteUTRANCellGlobalId();
                                        if (eUtranCellGlobalId != null) {
                                            ShEUTRANCellGlobalId shCsEUTRANCellGlobalId = new ShEUTRANCellGlobalId();
                                            shCsEUTRANCellGlobalId.setECGIStr(eUtranCellGlobalId);
                                            shUdaAvpValues.setEutrancgi(shCsEUTRANCellGlobalId);
                                        }
                                        trackingAreaId = csLocationInformationExtension2.getTrackingAreaId();
                                        if (trackingAreaId != null) {
                                            ShTrackingAreaId shCsTrackingAreaId = new ShTrackingAreaId();
                                            shCsTrackingAreaId.setTrackingAreaIdStr(trackingAreaId);
                                            shUdaAvpValues.setTrackingAreaId(shCsTrackingAreaId);
                                        }
                                        CSLocationInformationExtension3 csLocationInformationExtension3 = csLocationInformationExtension2.getCsLocationInformationExtension3();
                                        if (csLocationInformationExtension3 != null) {
                                            localTimeZone = csLocationInformationExtension3.getLocalTimeZone();
                                            if (localTimeZone != null) {
                                                shUdaAvpValues.setCsLocalTimeZone(localTimeZone);
                                                if (gmlcCdrState.isInitialized()) {
                                                    gmlcCdrState.setLocalTimeZone(localTimeZone);
                                                }
                                            }
                                        }
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nCSLocationInformation:");
                                            if (csLocationNumber != null)
                                                this.logger.fine("\nLocationNumber: " + shUdaAvpValues.getLocationNumber().toString());
                                            if (csCellGlobalId != null)
                                                this.logger.fine("\nCellGlobalId: " + shUdaAvpValues.getCsCellGlobalId().toString());
                                            if (csGeographicalInformation != null)
                                                this.logger.fine("\nGeographicalInformation: " + shUdaAvpValues.getCsGeographicalInformation().toString());
                                            if (csGeodeticInformation != null)
                                                this.logger.fine("\nGeodeticInformation: " + shUdaAvpValues.getCsGeodeticInformation().toString());
                                            this.logger.fine("\nMSC Number [address = " + shUdaAvpValues.getMscNumber().getAddress() + "]");
                                            this.logger.fine("\nVLR Number [address = " + shUdaAvpValues.getVlrNumber().getAddress() + "]");
                                            this.logger.fine("\nCurrentLocationRetrieved = " + shUdaAvpValues.getCsCurrentLocationInfoRetrieved());
                                            this.logger.fine("\nAgeOfLocationInformation = " + shUdaAvpValues.getCsAgeOfLocationInfo());
                                            if (shUdaAvpValues.getUserCSGInformation() != null)
                                                this.logger.fine("\nUserCSGInformation [ " + shUdaAvpValues.getUserCSGInformation().toString() + "]");
                                            if (eUtranCellGlobalId != null)
                                                this.logger.fine("\nE-UTRANCellGlobalId: " + shUdaAvpValues.getEutrancgi().toString());
                                            if (trackingAreaId != null)
                                                this.logger.fine("\nE-UTRANCellGlobalId: " + shUdaAvpValues.getTrackingAreaId().toString());
                                            if (localTimeZone != null) {
                                                this.logger.fine("\nTime Zone = " + shUdaAvpValues.getCsLocalTimeZone());
                                            }
                                        }
                                    }
                                }
                            }

                            PSLocationInformation shDataPsLocation = shDataReader.getShPSLocationInfo();
                            if (shDataPsLocation != null) {
                                shUdaAvpValues.setPsLocationInformation(shDataPsLocation);
                                String cellGlobalIdPs = shDataPsLocation.getCellGlobalId();
                                String serviceAreaIdPs = shDataPsLocation.getServiceAreaId();
                                String locationAreaIdPs = shDataPsLocation.getLocationAreaId();
                                String routingAreaIdPs = shDataPsLocation.getRoutingAreaId();
                                String geographicalInfoPs = shDataPsLocation.getGeographicalInformation();
                                String geodeticInfoPs = shDataPsLocation.getGeodeticInformation();
                                ShCellGlobalId psCellGlobalId = new ShCellGlobalId();
                                ShServiceAreaId psServiceAreaId = new ShServiceAreaId();
                                ShLocationAreaId psLocationAreaId = new ShLocationAreaId();
                                ShRoutingAreaId psRAI = new ShRoutingAreaId();
                                ShGeographicalInformation psGeographicalInformation = new ShGeographicalInformation();
                                ShGeodeticInformation psGeodeticInformation = new ShGeodeticInformation();
                                if (cellGlobalIdPs != null) {
                                    psCellGlobalId.setCellGlobalIdStr(cellGlobalIdPs);
                                    shUdaAvpValues.setPsCellGlobalId(psCellGlobalId);
                                }
                                if (serviceAreaIdPs != null) {
                                    psServiceAreaId.setServiceAreaIdStr(serviceAreaIdPs);
                                    shUdaAvpValues.setPsServiceAreaId(psServiceAreaId);
                                }
                                if (locationAreaIdPs != null) {
                                    psLocationAreaId.setLocationAreIdStr(locationAreaIdPs);
                                    shUdaAvpValues.setPsLocationAreaId(psLocationAreaId);
                                }
                                if (routingAreaIdPs != null) {
                                    psRAI.setRoutingAreaIdentityStr(routingAreaIdPs);
                                    shUdaAvpValues.setRoutingAreaId(psRAI);
                                }
                                if (geographicalInfoPs != null) {
                                    psGeographicalInformation.setGeographicalInfoStr(geographicalInfoPs);
                                    shUdaAvpValues.setPsGeographicalInformation(psGeographicalInformation);
                                }
                                if (geodeticInfoPs != null) {
                                    psGeodeticInformation.setGeodeticInfoStr(geodeticInfoPs);
                                    shUdaAvpValues.setPsGeodeticInformation(psGeodeticInformation);
                                }
                                if (shDataPsLocation.getSgsnNumber() != null)
                                    shUdaAvpValues.setSgsnNumber(shDataPsLocation.getSgsnNumber());
                                if (shDataPsLocation.getCurrentLocationRetrieved() != null)
                                    shUdaAvpValues.setPsCurrentLocationInfoRetrieved(shDataPsLocation.getCurrentLocationRetrieved());
                                if (shDataPsLocation.getAgeOfLocationInformation() != null)
                                    shUdaAvpValues.setPsAgeOfLocationInfo(Integer.valueOf(shDataPsLocation.getAgeOfLocationInformation()));
                                PSLocationInformationExtension psLocationInformationExtension = shDataPsLocation.getPsLocationInformationExtension();
                                if (psLocationInformationExtension != null) {
                                    UserCSGInformation userCSGInformationPS = psLocationInformationExtension.getUserCSGInformation();
                                    if (userCSGInformationPS != null) {
                                        csgId = userCSGInformationPS.getCsgid();
                                        ShUserCSGInformation shPsUserCSGInformation = new ShUserCSGInformation();
                                        shPsUserCSGInformation.setUserCSGInformationStr(csgId);
                                        shUdaAvpValues.setUserCSGInformation(shPsUserCSGInformation);
                                        // TODO CDR (doesn't seem to be needed)
                                    }
                                    PSLocationInformationExtension2 psLocationInformationExtension2 = psLocationInformationExtension.getPsLocationInformationExtension2();
                                    if (psLocationInformationExtension2 != null) {
                                        visitedPlmnId = psLocationInformationExtension2.getVisitedPLMNId();
                                        if (visitedPlmnId != null) {
                                            ShVisitedPLMNId shPsVisitedPLMNId = new ShVisitedPLMNId();
                                            shPsVisitedPLMNId.setVisitedPlmnIdStr(visitedPlmnId);
                                            shUdaAvpValues.setPsVisitedPLMNId(shPsVisitedPLMNId);
                                            if (gmlcCdrState.isInitialized()) {
                                                gmlcCdrState.setVisitedPlmnId(shUdaAvpValues.getPsVisitedPLMNId().getVisitedPlmnId());
                                            }
                                        }
                                        localTimeZone = psLocationInformationExtension2.getLocalTimeZone();
                                        if (localTimeZone != null) {
                                            shUdaAvpValues.setPsLocalTimeZone(localTimeZone);
                                            if (gmlcCdrState.isInitialized()) {
                                                gmlcCdrState.setLocalTimeZone(localTimeZone);
                                            }
                                        }
                                        PSLocationInformationExtension3 psLocationInformationExtension3 = psLocationInformationExtension2.getPsLocationInformationExtension3();
                                        if (psLocationInformationExtension3 != null) {
                                            ratType = psLocationInformationExtension3.getRatType();
                                            if (ratType != null) {
                                                shUdaAvpValues.setPsRatType(Integer.valueOf(ratType));
                                                if (gmlcCdrState.isInitialized()) {
                                                    gmlcCdrState.setRatType(Integer.valueOf(ratType));
                                                }
                                            }
                                        }
                                        if (this.logger.isFineEnabled()) {
                                            this.logger.fine("\nPSLocationInformation:");
                                            if (shUdaAvpValues.getPsCellGlobalId() != null)
                                                this.logger.fine("\nCellGlobalId: " + shUdaAvpValues.getPsCellGlobalId().toString());
                                            if (shUdaAvpValues.getRoutingAreaId() != null)
                                                this.logger.fine("\nRouting Area Id: " + shUdaAvpValues.getRoutingAreaId().toString());
                                            if (shUdaAvpValues.getPsGeographicalInformation() != null)
                                                this.logger.fine("\nGeographicalInformation: " + shUdaAvpValues.getPsGeographicalInformation().toString());
                                            if (shUdaAvpValues.getPsGeodeticInformation() != null)
                                                this.logger.fine("\nGeodeticInformation: " + shUdaAvpValues.getPsGeodeticInformation().toString());
                                            this.logger.fine("\nSGSN Number [address = " + shUdaAvpValues.getSgsnNumber().toString() + "]");
                                            this.logger.fine("\nCurrentLocationRetrieved = " + shUdaAvpValues.getPsCurrentLocationInfoRetrieved());
                                            this.logger.fine("\nAgeOfLocationInformation = " + shUdaAvpValues.getPsAgeOfLocationInfo().toString());
                                            if (visitedPlmnId != null) {
                                                this.logger.fine("\nVisitedPLMNID = " + shUdaAvpValues.getPsVisitedPLMNId().toString());
                                            }
                                            if (localTimeZone != null) {
                                                this.logger.fine("\nTime Zone = " + shUdaAvpValues.getPsLocalTimeZone());
                                            }
                                            if (ratType != null) {
                                                this.logger.fine("\nRAT Type = " + shUdaAvpValues.getPsRatType());
                                            }
                                        }
                                    }
                                }
                            }

                            EPSLocationInformation epsLocationInformation = null;
                            Extension shDataExtensionEpsLocation = shDataReader.getShEPSLocationInfo();
                            if (shDataExtensionEpsLocation != null) {
                                if (shDataExtensionEpsLocation.getExtension() != null) {
                                    if (shDataExtensionEpsLocation.getExtension().getExtension() != null) {
                                        if (shDataExtensionEpsLocation.getExtension().getExtension().getExtension() != null) {
                                            Extension shDataExtension4 = shDataExtensionEpsLocation.getExtension().getExtension().getExtension();
                                            if (shDataExtension4.getEpsLocationInformation() != null) {
                                                epsLocationInformation = shDataExtension4.getEpsLocationInformation();
                                                shUdaAvpValues.setEpsLocationInformation(epsLocationInformation);
                                                eUtranCellGlobalId = epsLocationInformation.geteUTRANCellGlobalId();
                                                trackingAreaId = epsLocationInformation.getTrackingAreaId();
                                                geographicalInfoEps = epsLocationInformation.getGeographicalInformation();
                                                geodeticInfoEps = epsLocationInformation.getGeodeticInformation();
                                                mmeName = epsLocationInformation.getMmeName();
                                                currentLocationRetrieved = epsLocationInformation.getCurrentLocationRetrieved();
                                                ageOfLocationInformation = epsLocationInformation.getAgeOfLocationInformation();
                                                if (epsLocationInformation.getUserCSGInformation() != null) {
                                                    csgId = epsLocationInformation.getUserCSGInformation().getCsgid();
                                                }
                                                if (eUtranCellGlobalId != null) {
                                                    ShEUTRANCellGlobalId shEUTRANCellGlobalId = new ShEUTRANCellGlobalId();
                                                    shEUTRANCellGlobalId.setECGIStr(eUtranCellGlobalId);
                                                    shUdaAvpValues.setEutrancgi(shEUTRANCellGlobalId);
                                                }
                                                if (trackingAreaId != null) {
                                                    ShTrackingAreaId shTrackingAreaId = new ShTrackingAreaId();
                                                    shTrackingAreaId.setTrackingAreaIdStr(trackingAreaId);
                                                    shUdaAvpValues.setTrackingAreaId(shTrackingAreaId);
                                                }
                                                if (geographicalInfoEps != null) {
                                                    ShGeographicalInformation shGeographicalInformationEps = new ShGeographicalInformation();
                                                    shGeographicalInformationEps.setGeographicalInfoStr(geographicalInfoEps);
                                                    shUdaAvpValues.setEpsGeographicalInformation(shGeographicalInformationEps);
                                                }
                                                if (geodeticInfoEps != null) {
                                                    ShGeodeticInformation shGeodeticInformationEps = new ShGeodeticInformation();
                                                    shGeodeticInformationEps.setGeodeticInfoStr(geodeticInfoEps);
                                                    shUdaAvpValues.setEpsGeodeticInformation(shGeodeticInformationEps);
                                                }
                                                if (mmeName != null)
                                                    shUdaAvpValues.setMmeName(mmeName);
                                                if (currentLocationRetrieved != null)
                                                    shUdaAvpValues.setEpsCurrentLocationInfoRetrieved(currentLocationRetrieved);
                                                if (ageOfLocationInformation != null)
                                                    shUdaAvpValues.setEpsAgeOfLocationInfo(Integer.valueOf(ageOfLocationInformation));
                                                if (csgId != null) {
                                                    ShUserCSGInformation shUserCSGInformation = new ShUserCSGInformation();
                                                    shUserCSGInformation.setUserCSGInformationStr(csgId);
                                                    shUdaAvpValues.setUserCSGInformation(shUserCSGInformation);
                                                    // TODO CDR (doesn't seem to be needed)
                                                }
                                                EPSLocationInformationExtension epsLocationInformationExtension = epsLocationInformation.getEpsLocationInformationExtension();
                                                if (epsLocationInformationExtension != null) {
                                                    visitedPlmnId = epsLocationInformationExtension.getVisitedPLMNId();
                                                    if (visitedPlmnId != null) {
                                                        ShVisitedPLMNId shEpsVisitedPLMNId = new ShVisitedPLMNId();
                                                        shEpsVisitedPLMNId.setVisitedPlmnIdStr(visitedPlmnId);
                                                        shUdaAvpValues.setEpsVisitedPLMNId(shEpsVisitedPLMNId);
                                                        if (gmlcCdrState.isInitialized()) {
                                                            gmlcCdrState.setVisitedPlmnId(shUdaAvpValues.getEpsVisitedPLMNId().getVisitedPlmnId());
                                                        }
                                                    }
                                                    localTimeZone = epsLocationInformationExtension.getLocalTimeZone();
                                                    if (localTimeZone != null) {
                                                        shUdaAvpValues.setEpsLocalTimeZone(localTimeZone);
                                                        if (gmlcCdrState.isInitialized()) {
                                                            gmlcCdrState.setLocalTimeZone(localTimeZone);
                                                        }
                                                    }
                                                    EPSLocationInformationExtension2 epsLocationInformationExtension2 = epsLocationInformationExtension.getEpsLocationInformationExtension2();
                                                    if (epsLocationInformationExtension2 != null) {
                                                        ratType = epsLocationInformationExtension2.getRatType();
                                                        shUdaAvpValues.setEpsRatType(Integer.valueOf(ratType));
                                                        if (gmlcCdrState.isInitialized()) {
                                                            gmlcCdrState.setRatType(Integer.valueOf(ratType));
                                                        }
                                                    }
                                                    if (this.logger.isFineEnabled()) {
                                                        this.logger.fine("\nEPSLocationInformation:");
                                                        if (shUdaAvpValues.getEutrancgi() != null)
                                                            this.logger.fine("\nE-UTRANCellGlobalId: " + shUdaAvpValues.getEutrancgi().toString());
                                                        if (shUdaAvpValues.getTrackingAreaId() != null)
                                                            this.logger.fine("\nTracking Area Id: " + shUdaAvpValues.getTrackingAreaId().toString());
                                                        if (shUdaAvpValues.getEpsGeographicalInformation() != null)
                                                            this.logger.fine("\nGeographicalInformation: " + shUdaAvpValues.getEpsGeographicalInformation().toString());
                                                        if (shUdaAvpValues.getEpsGeodeticInformation() != null)
                                                            this.logger.fine("\nGeodeticInformation: " + shUdaAvpValues.getEpsGeodeticInformation().toString());
                                                        this.logger.fine("\nMME name = " + shUdaAvpValues.getMmeName());
                                                        this.logger.fine("\nCurrentLocationRetrieved = " + shUdaAvpValues.getEpsCurrentLocationInfoRetrieved());
                                                        this.logger.fine("\nAgeOfLocationInformation = " + shUdaAvpValues.getEpsAgeOfLocationInfo());
                                                        if (shUdaAvpValues.getUserCSGInformation() != null)
                                                            this.logger.fine("\nUserCSGInformation [ " + shUdaAvpValues.getUserCSGInformation().toString() + "]");
                                                        if (visitedPlmnId != null) {
                                                            this.logger.fine("\nVisitedPLMNID = " + shUdaAvpValues.getEpsVisitedPLMNId().toString());
                                                        }
                                                        if (localTimeZone != null) {
                                                            this.logger.fine("\nTime Zone = " + shUdaAvpValues.getEpsLocalTimeZone());
                                                        }
                                                        if (ratType != null) {
                                                            this.logger.fine("\nRAT Type = " + shUdaAvpValues.getEpsRatType());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            Sh5GSLocationInformation sh5GSLocationInformation = null;
                            Extension shDataExtension5GSLocation = shDataReader.getSh5GSLocationInfo();
                            if (shDataExtension5GSLocation != null) {
                                if (shDataExtension5GSLocation.getExtension() != null) {
                                    if (shDataExtension5GSLocation.getExtension().getExtension() != null) {
                                        if (shDataExtension5GSLocation.getExtension().getExtension().getExtension() != null) {
                                            if (shDataExtension5GSLocation.getExtension().getExtension().getExtension().getExtension() != null) {
                                                if (shDataExtension5GSLocation.getExtension().getExtension().getExtension().getExtension().getExtension() != null) {
                                                    if (shDataExtension5GSLocation.getExtension().getExtension().getExtension().getExtension().getExtension().getExtension() != null) {
                                                        Extension shDataExtension7 = shDataExtension5GSLocation.getExtension().getExtension().getExtension().getExtension().getExtension().getExtension();
                                                        if (shDataExtension7 != null) {
                                                            sh5GSLocationInformation = shDataExtension7.getSh5GSLocationInformation();
                                                            shUdaAvpValues.setSh5GSLocationInformation(sh5GSLocationInformation);
                                                            nrCellGlobalId = sh5GSLocationInformation.getNRCellGlobalId();
                                                            eUtranCellGlobalId = sh5GSLocationInformation.getEUTRANCellGlobalId();
                                                            trackingAreaId = sh5GSLocationInformation.getTrackingAreaId();
                                                            amfAddress = sh5GSLocationInformation.getAMFAddress();
                                                            smsfAddress = sh5GSLocationInformation.getSMSFAddress();
                                                            currentLocationRetrieved = sh5GSLocationInformation.getCurrentLocationRetrieved();
                                                            ageOfLocationInformation = sh5GSLocationInformation.getAgeOfLocationInformation();
                                                            visitedPlmnId = sh5GSLocationInformation.getVisitedPLMNId();
                                                            ratType = sh5GSLocationInformation.getRatType();
                                                            localTimeZone = sh5GSLocationInformation.getLocalTimeZone();
                                                            if (nrCellGlobalId != null) {
                                                                ShNRCellGlobalId shNRCellGlobalId = new ShNRCellGlobalId();
                                                                shNRCellGlobalId.setNRCellGlobalIdStr(nrCellGlobalId);
                                                                shUdaAvpValues.setShNRCellGlobalId(shNRCellGlobalId);
                                                            }
                                                            if (eUtranCellGlobalId != null) {
                                                                ShEUTRANCellGlobalId shEUTRANCellGlobalId = new ShEUTRANCellGlobalId();
                                                                shEUTRANCellGlobalId.setECGIStr(eUtranCellGlobalId);
                                                                shUdaAvpValues.setEutrancgi(shEUTRANCellGlobalId);
                                                            }
                                                            if (trackingAreaId != null) {
                                                                ShTrackingAreaId shTrackingAreaId = new ShTrackingAreaId();
                                                                shTrackingAreaId.setTrackingAreaIdStr(trackingAreaId);
                                                                shUdaAvpValues.setTrackingAreaId(shTrackingAreaId);
                                                            }
                                                            if (geographicalInfoEps != null) {
                                                                ShGeographicalInformation shGeographicalInformation5GS = new ShGeographicalInformation();
                                                                shGeographicalInformation5GS.setGeographicalInfoStr(geographicalInfoEps);
                                                                shUdaAvpValues.setSh5GSGeographicalInformation(shGeographicalInformation5GS);
                                                            }
                                                            if (amfAddress != null) {
                                                                shUdaAvpValues.setAmfAddress(amfAddress);
                                                            }
                                                            if (smsfAddress != null) {
                                                                shUdaAvpValues.setSmsfAddress(smsfAddress);
                                                            }
                                                            if (currentLocationRetrieved != null) {
                                                                shUdaAvpValues.setSh5GSCurrentLocationInfoRetrieved(currentLocationRetrieved);
                                                            }
                                                            if (ageOfLocationInformation != null) {
                                                                shUdaAvpValues.setSh5GSAgeOfLocationInfo(Integer.valueOf(ageOfLocationInformation));
                                                            }
                                                            if (visitedPlmnId != null) {
                                                                ShVisitedPLMNId shVisitedPLMNId = new ShVisitedPLMNId();
                                                                shVisitedPLMNId.setVisitedPlmnIdStr(visitedPlmnId);
                                                                shUdaAvpValues.setSh5gsVisitedPLMNId(shVisitedPLMNId);
                                                                if (gmlcCdrState.isInitialized()) {
                                                                    gmlcCdrState.setVisitedPlmnId(shUdaAvpValues.getSh5gsVisitedPLMNId().getVisitedPlmnId());
                                                                }
                                                            }
                                                            if (localTimeZone != null) {
                                                                shUdaAvpValues.setSh5gsLocalTimeZone(localTimeZone);
                                                                if (gmlcCdrState.isInitialized()) {
                                                                    gmlcCdrState.setLocalTimeZone(localTimeZone);
                                                                }
                                                            }
                                                            if (ratType != null) {
                                                                shUdaAvpValues.setSh5gsRatType(Integer.valueOf(ratType));
                                                            }
                                                            if (this.logger.isFineEnabled()) {
                                                                this.logger.fine("\n5GSLocationInformation:");
                                                                if (shUdaAvpValues.getShNRCellGlobalId() != null)
                                                                    this.logger.fine("\nNRCellGlobalId: " + shUdaAvpValues.getShNRCellGlobalId().toString());
                                                                if (shUdaAvpValues.getEutrancgi() != null)
                                                                    this.logger.fine("\nE-UTRANCellGlobalId: " + shUdaAvpValues.getEutrancgi().toString());
                                                                if (shUdaAvpValues.getTrackingAreaId() != null)
                                                                    this.logger.fine("\nTracking Area Id: " + shUdaAvpValues.getTrackingAreaId().toString());
                                                                if (shUdaAvpValues.getEpsGeographicalInformation() != null)
                                                                    this.logger.fine("\nGeographicalInformation: " + shUdaAvpValues.getSh5GSGeographicalInformation().toString());
                                                                this.logger.fine("\nAMF address = " + shUdaAvpValues.getAmfAddress());
                                                                this.logger.fine("\nSMSF address = " + shUdaAvpValues.getSmsfAddress());
                                                                this.logger.fine("\nCurrentLocationRetrieved = " + shUdaAvpValues.getSh5GSCurrentLocationInfoRetrieved());
                                                                this.logger.fine("\nAgeOfLocationInformation = " + shUdaAvpValues.getSh5GSAgeOfLocationInfo());
                                                                if (visitedPlmnId != null) {
                                                                    this.logger.fine("\nVisitedPLMNID = " + shUdaAvpValues.getSh5gsVisitedPLMNId().toString());
                                                                }
                                                                if (localTimeZone != null) {
                                                                    this.logger.fine("\nTime Zone = " + shUdaAvpValues.getSh5gsLocalTimeZone());
                                                                }
                                                                if (ratType != null) {
                                                                    this.logger.fine("\nRAT Type = " + shUdaAvpValues.getSh5gsRatType());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            // Handle successful retrieval of response to subscriber's location request (SLg ELP Provide-Location-Answer)
                            this.handleShUserDataRequestResponse(mlpRespResult, shUdaAvpValues, mlpClientErrorMessage);
                            detachFromShClientActivity(aci);

                            if (gmlcCdrState.isInitialized()) {
                                gmlcCdrState.setStatusCode(2001L);
                                ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, publicIdsMsisdn);
                                gmlcCdrState.setMsisdn(msisdn);
                                LocationInformation locationInformation;
                                LocationInformationGPRS locationInformationGPRS;
                                LocationInformationEPS locationInformationEPS;
                                LocationInformation5GS locationInformation5GS;
                                if (shDataCsLocation != null) {
                                    locationInformation = AVPHandler.shLocationInfo2MapLocationInformation(shUdaAvpValues);
                                    gmlcCdrState.setLocationInformation(locationInformation);
                                    locationInformationEPS = locationInformation.getLocationInformationEPS();
                                    gmlcCdrState.setLocationInformationEPS(locationInformationEPS);
                                }
                                if (shDataPsLocation != null) {
                                    locationInformationGPRS = AVPHandler.shPSLocationInfo2MapLocationInformationGPRS(shUdaAvpValues);
                                    gmlcCdrState.setLocationInformationGPRS(locationInformationGPRS);
                                }
                                if (epsLocationInformation != null) {
                                    locationInformation = AVPHandler.shLocationInfo2MapLocationInformation(shUdaAvpValues);
                                    locationInformationEPS = locationInformation.getLocationInformationEPS();
                                    gmlcCdrState.setLocationInformationEPS(locationInformationEPS);
                                }
                                if (sh5GSLocationInformation != null) {
                                    locationInformation5GS = AVPHandler.sh5GSLocationInfo2LocationInformation5GS(shUdaAvpValues);
                                    gmlcCdrState.setLocationInformation5GS(locationInformation5GS);
                                }
                                this.createCDRRecord(RecordStatus.IMS_UDR_SUCCESS);
                            }

                        } else {
                            ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN,
                                shUdrMsisdn);
                            if (gmlcCdrState.isInitialized()) {
                                gmlcCdrState.setMsisdn(msisdn);
                                handleRecordAndLocationReportOnDiameterResultCode(resultCode, mlpRespResult, mlpClientErrorMessage, shUdrMsisdn, null, "UDR", null, gmlcCdrState, true,
                                    udaEvent.getOriginHost().toString(), udaEvent.getOriginRealm().toString(), false);
                            }
                        }

                    } else {
                        ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN,
                            shUdrMsisdn);
                        if (gmlcCdrState.isInitialized()) {
                            gmlcCdrState.setMsisdn(msisdn);
                            handleRecordAndLocationReportOnDiameterResultCode(resultCode, mlpRespResult, mlpClientErrorMessage, shUdrMsisdn, null, "UDR", null, gmlcCdrState, true,
                                udaEvent.getOriginHost().toString(), udaEvent.getOriginRealm().toString(), false);
                        }
                    }
                }

            } catch (Throwable e) {
                logger.warning("Exception when processing onUserDataAnswer response: " + e.getMessage(), e);
                this.createCDRRecord(RecordStatus.IMS_UDR_SYSTEM_FAILURE);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on Sh UDA: " + e.getMessage(),
                    "UDR", shUdrMsisdn, null, null, null, null,
                    udaEvent.getOriginHost().toString(),
                    udaEvent.getOriginRealm().toString(),
                    false);
                if (transaction != null)
                    mobileCoreNetworkTransactions.Instance().destroy(transaction);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onUserDataAnswer=%s", udaEvent), e);
            this.createCDRRecord(RecordStatus.IMS_UDR_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on Sh UDA: " + e.getMessage(),
                "UDR", shUdrMsisdn, null, null, null, null,
                udaEvent != null ? udaEvent.getOriginHost().toString() : null,
                udaEvent != null ? udaEvent.getOriginRealm().toString() : null,
                false);
            if (transaction != null)
                mobileCoreNetworkTransactions.Instance().destroy(transaction);
        } finally {
            detachFromShClientActivity(aci);
        }
    }

    /*
     * Diameter Base Error-Answer Event
     */
    public void onErrorAnswer(ErrorAnswer diameterErrorAnswerEvent, ActivityContextInterface aci) {

        String msisdnAddress = null, imsiStr = null, diameterCommand = null, mlpClientErrorMessage = "", curlUser;
        MLPResponse.MLPResultType mlpResultType = null;
        Long transaction = null;
        Integer referenceNumber = null;
        Boolean isImsi = false, mlpTriggeredReportingService = false;
        long resultCode = -1;
        DateTime eventTime = DateTime.now();
        DiameterBaseError error = new DiameterBaseError();
        if (diameterErrorAnswerEvent != null) {
            resultCode = diameterErrorAnswerEvent.getResultCode();
            mlpClientErrorMessage = error.diameterErrorMessage(resultCode);
        }

        if (this.logger.isFineEnabled()) {
            logger.fine("\n\nReceived Diameter error answer with session Id: " + diameterErrorAnswerEvent.getSessionId() + ", host '"
                + diameterErrorAnswerEvent.getOriginHost() + "', realm '" + diameterErrorAnswerEvent.getOriginRealm() + "'");
            this.logger.fine("\nReceived Diameter errorAnswer = " + diameterErrorAnswerEvent);
        }

        try {

            try {
                net.java.slee.resource.diameter.base.events.avp.DiameterIdentity gmlcHost =
                    new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginHost());
                net.java.slee.resource.diameter.base.events.avp.DiameterIdentity gmlcRealm =
                    new net.java.slee.resource.diameter.base.events.avp.DiameterIdentity(gmlcPropertiesManagement.getDiameterOriginRealm());

                // CDR initialization
                GMLCCDRState gmlcCdrState = CDRCreationHelper.diameterErrorCdrInitializer(aci, this.getCDRInterface(), diameterErrorAnswerEvent,
                    gmlcHost, gmlcRealm);
                // Set timer last
                this.setTimer(aci);

                if (diameterErrorAnswerEvent != null) {

                    // Transaction
                    transaction = mobileCoreNetworkTransactions.getTransactionId(diameterErrorAnswerEvent.getSessionId());
                    if (transaction == null) {
                        throw new Exception();
                    }
                    TimerID diameterOperationTimerID = (TimerID) mobileCoreNetworkTransactions.Instance().getValue(transaction, "timerID");
                    if (diameterOperationTimerID != null)
                        this.timerFacility.cancelTimer(diameterOperationTimerID);
                    msisdnAddress = (String) mobileCoreNetworkTransactions.getValue(transaction, "shUdrMsisdn");
                    curlUser = (String) mobileCoreNetworkTransactions.Instance().getValue(transaction, "curlUser");
                    DateTime dialogStartTime = (DateTime) mobileCoreNetworkTransactions.Instance().getValue(transaction, "transactionStart");
                    mlpTriggeredReportingService = (Boolean) mobileCoreNetworkTransactions.Instance().getValue(transaction, "mlpTriggeredReportingService");
                    gmlcCdrState.setDialogStartTime(dialogStartTime);
                    if (gmlcCdrState.isInitialized()) {
                        gmlcCdrState.setCurlUser(curlUser);
                        if (dialogStartTime != null && diameterErrorAnswerEvent != null) {
                            Long dialogDuration = eventTime.getMillis() - dialogStartTime.getMillis();
                            gmlcCdrState.setDialogDuration(dialogDuration);
                        }
                    }
                    if (msisdnAddress != null) {
                        diameterCommand = "UDR";
                    } else {
                        SLhRirAvpValues slhRirRequestValues = (SLhRirAvpValues) mobileCoreNetworkTransactions.getValue(transaction, "slhRirRequestValues");
                        if (slhRirRequestValues != null) {
                            if (slhRirRequestValues.plrMsisdn != null)
                                msisdnAddress = AVPHandler.byte2IsdnAddressString(slhRirRequestValues.plrMsisdn.getBytes()).getAddress();
                            if (slhRirRequestValues.getUserName() != null) {
                                imsiStr = slhRirRequestValues.getUserName();
                                isImsi = true;
                            }
                            if (msisdnAddress != null || imsiStr != null) {
                                diameterCommand = "RIR";
                            }
                            if (slhRirRequestValues.plrLcsReferenceNumber != null)
                                referenceNumber = slhRirRequestValues.plrLcsReferenceNumber;
                        }
                        SLhRiaAvpValues sLhRiaAvpResponseValues = (SLhRiaAvpValues) mobileCoreNetworkTransactions.getValue(transaction, "sLhRiaAvpResponseValues");
                        SLhRirAvpValues sLhRirRequestValues = (SLhRirAvpValues) mobileCoreNetworkTransactions.getValue(transaction, "slhRirRequestValues");
                        referenceNumber = sLhRirRequestValues.plrLcsReferenceNumber;
                        if (sLhRirRequestValues != null) {
                            if (sLhRirRequestValues.plrUserName != null) {
                                imsiStr = sLhRirRequestValues.plrUserName;
                            }
                            if (sLhRirRequestValues.plrMsisdn != null) {
                                msisdnAddress = AVPHandler.byte2IsdnAddressString(sLhRirRequestValues.plrMsisdn.getBytes()).getAddress();
                            }
                        } else if (sLhRiaAvpResponseValues != null) {
                            if (sLhRiaAvpResponseValues.getUserName() != null) {
                                imsiStr = sLhRiaAvpResponseValues.getUserName();
                                diameterCommand = "PLR";
                            }
                            if (sLhRiaAvpResponseValues.getMsisdn() != null) {
                                msisdnAddress = AVPHandler.byte2IsdnAddressString(sLhRiaAvpResponseValues.getMsisdn()).getAddress();
                                diameterCommand = "PLR";
                            }
                        }
                    }

                    if (gmlcCdrState.isInitialized()) {
                        if (isImsi == true) {
                            IMSI imsi = new IMSIImpl(imsiStr);
                            gmlcCdrState.setImsi(imsi);
                        } else {
                            ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number, org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN,
                                msisdnAddress);
                            gmlcCdrState.setMsisdn(msisdn);
                        }
                        handleRecordAndLocationReportOnDiameterResultCode(resultCode, MLPResponse.MLPResultType.SYSTEM_FAILURE, mlpClientErrorMessage,
                            msisdnAddress, imsiStr, diameterCommand, referenceNumber, gmlcCdrState, true,
                            diameterErrorAnswerEvent.getOriginHost() != null ? diameterErrorAnswerEvent.getOriginHost().toString() : null,
                            diameterErrorAnswerEvent.getOriginRealm() != null ? diameterErrorAnswerEvent.getOriginRealm().toString() : null,
                            mlpTriggeredReportingService);
                    }
                }
            } catch (Throwable e) {
                logger.warning("Exception when processing Diameter Error Answer: " + e.getMessage(), e);
                this.createCDRRecord(RecordStatus.DIAMETER_SYSTEM_FAILURE);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, mlpClientErrorMessage,
                    diameterCommand, msisdnAddress, imsiStr, referenceNumber, null, null,
                    diameterErrorAnswerEvent.getOriginHost() != null ? diameterErrorAnswerEvent.getOriginHost().toString() : null,
                    diameterErrorAnswerEvent.getOriginRealm() != null ? diameterErrorAnswerEvent.getOriginRealm().toString() : null,
                    mlpTriggeredReportingService);
            }
        } catch (Exception e) {
            logger.severe(String.format("Error while trying to process onErrorAnswer=%s", diameterErrorAnswerEvent), e);
            this.createCDRRecord(RecordStatus.DIAMETER_SYSTEM_FAILURE);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception on Diameter ErrorAnswer: " + e.getMessage(),
                diameterCommand, msisdnAddress, imsiStr, referenceNumber, null, null,
                diameterErrorAnswerEvent != null ? diameterErrorAnswerEvent.getOriginHost().toString() : null,
                diameterErrorAnswerEvent != null ? diameterErrorAnswerEvent.getOriginRealm().toString() : null,
                mlpTriggeredReportingService);
        } finally {
            mobileCoreNetworkTransactions.destroy(transaction);
            aci.detach(this.sbbContext.getSbbLocalObject());
        }
    }

    /*
     * Diameter error handling for CDR and HTTP response
     */
    public Map<MLPResponse.MLPResultType, String> handleRecordAndLocationReportOnDiameterResultCode(long resultCode, MLPResponse.MLPResultType mlpRespResult, String mlpClientErrorMessage,
                                                                                                    String msisdn, String imsi, String diameterCommand, Integer refNumber,
                                                                                                    GMLCCDRState gmlcCdrState, Boolean reportError, String host, String realm, Boolean mlpTriggeredReportingService) {

        Map<MLPResponse.MLPResultType, String> mlpResultTypeStringMap = new LinkedHashMap<>();
        if (gmlcCdrState != null) {
            if (gmlcCdrState.isInitialized()) {
                gmlcCdrState.setStatusCode(resultCode);

                switch ((int) resultCode) {
                    case 3001:
                        mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_COMMAND_UNSUPPORTED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_COMMAND_UNSUPPORTED);
                        break;
                    case 3002:
                        mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_UNABLE_TO_DELIVER, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_UNABLE_TO_DELIVER);
                        break;
                    case 3003:
                        mlpRespResult = MLPResponse.MLPResultType.MISCONFIGURATION_OF_LOCATION_SERVER;
                        mlpClientErrorMessage = "DIAMETER_REALM_NOT_SERVED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_REALM_NOT_SERVED);
                        break;
                    case 3004:
                        mlpRespResult = MLPResponse.MLPResultType.CONGESTION_IN_MOBILE_NETWORK;
                        mlpClientErrorMessage = "DIAMETER_TOO_BUSY, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_TOO_BUSY);
                        break;
                    case 3005:
                        mlpRespResult = MLPResponse.MLPResultType.MISCONFIGURATION_OF_LOCATION_SERVER;
                        mlpClientErrorMessage = "DIAMETER_LOOP_DETECTED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_LOOP_DETECTED);
                        break;
                    case 3006:
                        mlpRespResult = MLPResponse.MLPResultType.MISCONFIGURATION_OF_LOCATION_SERVER;
                        mlpClientErrorMessage = "DIAMETER_REDIRECT_INDICATION, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_REDIRECT_INDICATION);
                        break;
                    case 3007:
                        mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_APPLICATION_UNSUPPORTED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_APPLICATION_UNSUPPORTED);
                        break;
                    case 3008:
                        mlpRespResult = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_INVALID_HDR_BITS, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_INVALID_HDR_BITS);
                        break;
                    case 3009:
                        mlpRespResult = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_INVALID_AVP_BITS, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_INVALID_AVP_BITS);
                        break;
                    case 3010:
                        mlpRespResult = MLPResponse.MLPResultType.MISCONFIGURATION_OF_LOCATION_SERVER;
                        mlpClientErrorMessage = "DIAMETER_UNKNOWN_PEER, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_UNKNOWN_PEER);
                        break;
                    case 4001:
                        mlpRespResult = MLPResponse.MLPResultType.UNAUTHORIZED_APPLICATION;
                        mlpClientErrorMessage = "DIAMETER_AUTHENTICATION_REJECTED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_AUTHENTICATION_REJECTED);
                        break;
                    case 4002:
                        mlpRespResult = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                        mlpClientErrorMessage = "DIAMETER_OUT_OF_SPACE, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_OUT_OF_SPACE);
                        break;
                    case 4003:
                        mlpRespResult = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                        mlpClientErrorMessage = "DIAMETER_ELECTION_LOST, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_ELECTION_LOST);
                        break;
                    case 4100:
                        mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_USER_DATA_NOT_AVAILABLE, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_USER_DATA_NOT_AVAILABLE);
                        break;
                    case 4101:
                        mlpRespResult = MLPResponse.MLPResultType.UNSPECIFIED_ERROR;
                        mlpClientErrorMessage = "DIAMETER_ERROR_PRIOR_UPDATE_IN_PROGRESS, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_PRIOR_UPDATE_IN_PROGRESS);
                        break;
                    case 4201:
                        mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_ABSENT_USER, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.LTE_RIR_DIAMETER_ERROR_ABSENT_USER);
                        break;
                    case 4221:
                        mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_UNREACHABLE USER, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_UNREACHABLE_USER);
                        break;
                    case 4222:
                        mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_SUSPENDED USER, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_SUSPENDED_USER);
                        break;
                    case 4223:
                        mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_DETACHED USER, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_DETACHED_USER);
                        break;
                    case 4224:
                        mlpRespResult = MLPResponse.MLPResultType.POSITIONING_NOT_ALLOWED;
                        mlpClientErrorMessage = "DIAMETER_ERROR_POSITIONING DENIED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_POSITIONING_DENIED);
                        break;
                    case 4225:
                        mlpRespResult = MLPResponse.MLPResultType.POSITION_METHOD_FAILURE;
                        mlpClientErrorMessage = "DIAMETER_ERROR_POSITIONING FAILED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_POSITIONING_FAILED);
                        break;
                    case 4226:
                        mlpRespResult = MLPResponse.MLPResultType.MLS_CLIENT_ERROR;
                        mlpClientErrorMessage = "DIAMETER_ERROR_UNKNOWN OR UNREACHABLE LCS CLIENT, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_UNKNOWN_UNREACHABLE_LCS_CLIENT);
                        break;
                    case 5001:
                        mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_USER_UNKNOWN, Diameter error code: " + resultCode;
                        if (diameterCommand.equalsIgnoreCase("RIR"))
                            this.createCDRRecord(RecordStatus.LTE_RIR_DIAMETER_ERROR_USER_UNKNOWN);
                        else if (diameterCommand.equalsIgnoreCase("PLR"))
                            this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_USER_UNKNOWN);
                        break;
                    case 5002:
                        mlpRespResult = MLPResponse.MLPResultType.FORMAT_ERROR;
                        mlpClientErrorMessage = "DIAMETER_UNKNOWN_SESSION_ID, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_UNKNOWN_SESSION_ID);
                        break;
                    case 5003:
                        mlpRespResult = MLPResponse.MLPResultType.UNAUTHORIZED_APPLICATION;
                        mlpClientErrorMessage = "DIAMETER_AUTHORIZATION_REJECTED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_AUTHORIZATION_REJECTED);
                        break;
                    case 5004:
                        mlpRespResult = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_INVALID_AVP_VALUE, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_INVALID_AVP_VALUE);
                        break;
                    case 5005:
                        mlpRespResult = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_MISSING_AVP, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_MISSING_AVP);
                        break;
                    case 5006:
                        mlpRespResult = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                        mlpClientErrorMessage = "DIAMETER_RESOURCES_EXCEEDED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_RESOURCES_EXCEEDED);
                        break;
                    case 5007:
                        mlpRespResult = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_CONTRADICTING_AVPS, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_CONTRADICTING_AVPS);
                        break;
                    case 5008:
                        mlpRespResult = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_AVP_NOT_ALLOWED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_AVP_NOT_ALLOWED);
                        break;
                    case 5009:
                        mlpRespResult = MLPResponse.MLPResultType.PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_AVP_OCCURS_TOO_MANY_TIMES, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_AVP_OCCURS_TOO_MANY_TIMES);
                        break;
                    case 5010:
                        mlpRespResult = MLPResponse.MLPResultType.INVALID_PROTOCOL_ELEMENT_VALUE;
                        mlpClientErrorMessage = "DIAMETER_NO_COMMON_APPLICATION, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_NO_COMMON_APPLICATION);
                        break;
                    case 5011:
                        mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_UNSUPPORTED_VERSION, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_UNSUPPORTED_VERSION);
                        break;
                    case 5012:
                        mlpRespResult = MLPResponse.MLPResultType.SERVICE_NOT_SUPPORTED;
                        mlpClientErrorMessage = "DIAMETER_UNABLE_TO_COMPLY, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_UNABLE_TO_COMPLY);
                        break;
                    case 5013:
                        mlpRespResult = MLPResponse.MLPResultType.INVALID_PROTOCOL_ELEMENT_VALUE;
                        mlpClientErrorMessage = "DIAMETER_INVALID_BIT_IN_HEADER, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_INVALID_BIT_IN_HEADER);
                        break;
                    case 5014:
                        mlpRespResult = MLPResponse.MLPResultType.INVALID_PROTOCOL_ELEMENT_VALUE;
                        mlpClientErrorMessage = "DIAMETER_INVALID_AVP_LENGTH, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.DIAMETER_INVALID_AVP_LENGTH);
                        break;
                    case 5100:
                        mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_USER_DATA_NOT_RECOGNIZED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_USER_DATA_NOT_RECOGNIZED);
                        break;
                    case 5101:
                        mlpRespResult = MLPResponse.MLPResultType.POSITIONING_NOT_ALLOWED;
                        mlpClientErrorMessage = "DIAMETER_ERROR_OPERATION_NOT_ALLOWED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_OPERATION_NOT_ALLOWED);
                        break;
                    case 5102:
                        mlpRespResult = MLPResponse.MLPResultType.FORMAT_ERROR;
                        mlpClientErrorMessage = "DIAMETER_ERROR_USER_DATA_CANNOT_BE_READ, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_USER_DATA_CANNOT_BE_READ);
                        break;
                    case 5103:
                        mlpRespResult = MLPResponse.MLPResultType.UNAUTHORIZED_APPLICATION;
                        mlpClientErrorMessage = "DIAMETER_ERROR_USER_DATA_CANNOT_BE_MODIFIED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_USER_DATA_CANNOT_BE_MODIFIED);
                        break;
                    case 5104:
                        mlpRespResult = MLPResponse.MLPResultType.UNAUTHORIZED_APPLICATION;
                        mlpClientErrorMessage = "DIAMETER_ERROR_USER_DATA_CANNOT_BE_NOTIFIED, Diameter error code: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_USER_DATA_CANNOT_BE_NOTIFIED);
                        break;
                    case 5105:
                        mlpRespResult = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                        mlpClientErrorMessage = "DIAMETER_ERROR_ERROR_TRANSPARENT_DATA_OUT_OF_SYNC: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_TRANSPARENT_DATA_OUT_OF_SYNC);
                        break;
                    case 5106:
                        mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_SUBS_DATA_ABSENT: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_SUBS_DATA_ABSENT);
                        break;
                    case 5107:
                        mlpRespResult = MLPResponse.MLPResultType.UNKNOWN_SUBSCRIBER;
                        mlpClientErrorMessage = "DIAMETER_ERROR_NO_SUBSCRIPTION_TO_DATA: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_NO_SUBSCRIPTION_TO_DATA);
                        break;
                    case 5108:
                        mlpRespResult = MLPResponse.MLPResultType.FORMAT_ERROR;
                        mlpClientErrorMessage = "DIAMETER_ERROR_DSAI_NOT_AVAILABLE: " + resultCode;
                        this.createCDRRecord(RecordStatus.IMS_UDR_DIAMETER_ERROR_DSAI_NOT_AVAILABLE);
                        break;
                    case 5490:
                        mlpRespResult = MLPResponse.MLPResultType.POSITIONING_NOT_ALLOWED;
                        mlpClientErrorMessage = "DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK, Diameter error code: " + resultCode;
                        if (diameterCommand.equalsIgnoreCase("RIR"))
                            this.createCDRRecord(RecordStatus.LTE_RIR_DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK);
                        else if (diameterCommand.equalsIgnoreCase("PLR"))
                            this.createCDRRecord(RecordStatus.LTE_PLR_DIAMETER_ERROR_UNAUTHORIZED_REQUESTING_NETWORK);
                        break;
                    case 2001:
                        if (diameterCommand.equalsIgnoreCase("UDR")) {
                            // Special Case: Diameter SUCCESS in Sh UDA but Sh-User-Data AVP is absent
                            mlpRespResult = MLPResponse.MLPResultType.ABSENT_SUBSCRIBER;
                            mlpClientErrorMessage = "DIAMETER SUCCESS (2001) for UDR to " + msisdn + " but Sh-User-Data AVP is absent in UDA";
                            this.createCDRRecord(RecordStatus.IMS_UDA_Sh_USER_DATA_ABSENT);
                        }
                        break;
                    default:
                        mlpRespResult = MLPResponse.MLPResultType.SYSTEM_FAILURE;
                        mlpClientErrorMessage = "Diameter error code: " + resultCode;
                        if (diameterCommand.equalsIgnoreCase("RIR"))
                            this.createCDRRecord(RecordStatus.LTE_RIR_SYSTEM_FAILURE);
                        else if (diameterCommand.equalsIgnoreCase("PLR"))
                            this.createCDRRecord(RecordStatus.LTE_PLR_SYSTEM_FAILURE);
                        else if (diameterCommand.equalsIgnoreCase("UDR"))
                            this.createCDRRecord(RecordStatus.IMS_UDR_SYSTEM_FAILURE);
                        break;
                }
            }
        }
        mlpResultTypeStringMap.put(mlpRespResult, mlpClientErrorMessage);
        if (reportError) {
            this.reportLocationRequestError(mlpRespResult, mlpClientErrorMessage, diameterCommand, msisdn, imsi, refNumber,
                null, null, host, realm, mlpTriggeredReportingService);
        }
        return mlpResultTypeStringMap;
    }

    /**
     * SUPL POS INIT Event
     */
    /* public void onSuplInit(SuplInit suplInit, ActivityContextInterface aci){
        logger.info("[TEST] SuplInit");
         this.suplEventProcessing.onSuplInit(suplInit, aci);
    }
    public void onSuplPosInit(com.paicbd.slee.resource.ulp.events.SuplPosInit suplPosInit, ActivityContextInterface aci){
        logger.info("[TEST] SuplPosInit" + suplPosInit+", ac="+aci);
        // this.suplEventProcessing.onSuplPosInit(suplPosInit,aci);
    }
    public void onSuplPos(SuplPos suplPos, ActivityContextInterface aci){
        logger.info("[TEST] SuplPos" + suplPos+", ac="+aci);
        this.suplEventProcessing.onSuplPos(suplPos, aci);
    }
    public void onSuplEnd(SuplEnd suplEnd, ActivityContextInterface aci){
        logger.info("[TEST] SuplPos" + suplEnd+", ac="+aci);
        this.suplEventProcessing.onSuplEnd(suplEnd, aci);
    }
    public void onSuplTriggeredStart(SuplTriggeredStart suplTriggeredStart, ActivityContextInterface aci){
        logger.info("[TEST] SuplPos" + suplTriggeredStart+", ac="+aci);
        this.suplEventProcessing.onSuplTriggeredStart(suplTriggeredStart, aci);
    }
    public void onSuplTriggeredResponse(SuplTriggeredResponse suplTriggeredResponse, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplTriggeredResponse(suplTriggeredResponse, aci);
    }
    public void onSuplTriggeredStop(SuplTriggeredStop suplTriggeredStop, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplTriggeredStop(suplTriggeredStop, aci);
    }
    public void onSuplReport(SuplReport suplReport, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplReport(suplReport, aci);
    }
    public void onSuplStart(SuplStart suplStart, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplStart(suplStart, aci);
    }
    public void onSuplAuthReq(SuplAuthReq suplAuthReq, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplAuthReq(suplAuthReq, aci);
    }
    public void onSuplAuthResp(SuplAuthResp suplAuthResp, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplAuthResp(suplAuthResp, aci);
    }
    public void onSuplNotify(SuplNotify suplNotify, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplNotify(suplNotify, aci);
    }
    public void onSuplNotifyResponse(SuplNotifyResponse suplNotifyResponse,ActivityContextInterface aci){
        this.suplEventProcessing.onSuplNotifyResponse(suplNotifyResponse, aci);
    }
    public void onSuplResponse(SuplResponse suplResponse, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplResponse(suplResponse,aci);
    }
    public void onSuplSetInit(SuplSetInit suplSetInit, ActivityContextInterface aci){
        this.suplEventProcessing.onSuplSetInit(suplSetInit, aci);
    }*/

    /**
     * Handle HTTP POST request
     *
     * @param event
     * @param aci
     * @param eventContext
     */
    public void onPost(HttpServletRequestEvent event, ActivityContextInterface aci, EventContext eventContext) {
        onRequest(event, aci, eventContext);
    }

    /**
     * Handle HTTP GET request
     *
     * @param event
     * @param aci
     * @param eventContext
     */
    public void onGet(HttpServletRequestEvent event, ActivityContextInterface aci, EventContext eventContext) {
        onRequest(event, aci, eventContext);
    }

    /**
     * Entry point for all location lookups
     * Assigns a protocol handler to the request based on the path
     */
    private void onRequest(HttpServletRequestEvent event, ActivityContextInterface aci, EventContext eventContext) throws IllegalArgumentException {
        setEventContext(eventContext);
        HttpServletRequest httpServletRequest = event.getRequest();
        HttpRequestType httpRequestType = HttpRequestType.fromPath(httpServletRequest.getPathInfo());
        String operation = null, inputIllegalArgument;

        LocationRequestParams locationRequestParams = new LocationRequestParams();
        locationRequestParams.targetingMSISDN = locationRequestParams.targetingIMSI = locationRequestParams.httpRespType = "";
        locationRequestParams.translationType = 0;

        HttpServletRequestParams httpServletRequestParams = new HttpServletRequestParams();

        switch (httpRequestType) {
            case REST: {
                try {

                    locationRequestParams.pslMsisdn = locationRequestParams.plrMsisdn = locationRequestParams.targetingMSISDN = httpServletRequest.getParameter("msisdn");

                    locationRequestParams.pslImsi = locationRequestParams.plrUserName = locationRequestParams.targetingIMSI = httpServletRequest.getParameter("imsi");

                    locationRequestParams = httpServletRequestParams.createGlobalLocationRequestParamsFromHttpRequest(httpServletRequest, locationRequestParams, mongoGmlc);
                    if (locationRequestParams.numberFormatException != null) {
                        handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, operation, locationRequestParams.numberFormatException);
                        return;
                    }

                    /*********************************************************************************************************************************************************************/
                    /** SS7 operations arguments for ATI and PSI ************************************************************************************************************************/
                    /*******************************************************************************************************************************************************************/

                    locationRequestParams = httpServletRequestParams.createLocationRequestParamsForMapAtiOrPsi(httpServletRequest, locationRequestParams);
                    if (locationRequestParams.numberFormatException != null) {
                        handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, operation, locationRequestParams.numberFormatException);
                        return;
                    }

                    /*********************************************************************************************************************************************************************/
                    /** SS7 LSM operations (SRILCS-PSL) arguments *********************************************************************************************************************/
                    /*********************************************************************************************************************************************************************/
                    if (locationRequestParams.operation.equalsIgnoreCase("PSL")) {

                        operation = "SRILCS"; // operation for error report on illegal/incorrect arguments

                        locationRequestParams = httpServletRequestParams.createLocationRequestParamsForMapPsl(httpServletRequest, locationRequestParams);
                        if (locationRequestParams.numberFormatException != null) {
                            handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, operation, locationRequestParams.numberFormatException);
                            return;
                        }
                    }

                    /*********************************************************************************************************************************************************************/
                    /** LTE SLh & SLg operations arguments *******************************************************************************************************************************/
                    /*********************************************************************************************************************************************************************/
                    if (locationRequestParams.operation.equalsIgnoreCase("PLR")) {

                        operation = "RIR"; // operation for error report on illegal/incorrect arguments

                        locationRequestParams = httpServletRequestParams.createLocationRequestParamsForDiameterPLR(httpServletRequest, locationRequestParams);
                        if (locationRequestParams.numberFormatException != null) {
                            handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, operation, locationRequestParams.numberFormatException);
                            return;
                        }
                    }

                    /** LTE/IMS Sh User-Data-Request operation arguments **/
                    // Already defined domain and locationInfoEps also apply //
                    if (locationRequestParams.operation.equalsIgnoreCase("UDR")) {
                        locationRequestParams.udrMsisdn = locationRequestParams.targetingMSISDN = httpServletRequest.getParameter("msisdn");
                        locationRequestParams.udrImsPublicId = httpServletRequest.getParameter("imsPublicId");
                        if (locationRequestParams.udrMsisdn == null && locationRequestParams.udrImsPublicId == null) {
                            inputIllegalArgument = "One of MSISDN or Public Identity AVP are mandatory in UDR, at least one of msisdn or imsPublicId parameters must be not null";
                            throw new IllegalArgumentException(inputIllegalArgument);
                        }
                        locationRequestParams.hssDiameterHost = httpServletRequest.getParameter("hssDiameterHost");
                        locationRequestParams.hssDiameterRealm = httpServletRequest.getParameter("hssDiameterRealm");
                    }

                    /*********************************************************************************************************************************************************************/
                    /** SUPL arguments *******************************************************************************************************************************/
                    /*********************************************************************************************************************************************************************/
                    if (locationRequestParams.operation.equalsIgnoreCase("SUPL")) {

                        operation = "SUPL"; // operation for error report on illegal/incorrect arguments

                        // TODO
                        /*locationRequestParams = httpServletRequestParams.createLocationRequestParamsForSUPL(httpServletRequest, locationRequestParams);
                        if (locationRequestParams.numberFormatException != null) {
                            handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, operation, locationRequestParams.numberFormatException);
                            return;
                        }*/
                    }

                    setHttpRequest(httpRequestType, locationRequestParams);

                } catch (IllegalArgumentException iae) {
                    if (locationRequestParams != null) {
                        if (locationRequestParams.operation != null) {
                            if (locationRequestParams.operation.equalsIgnoreCase("ATI"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "ATI", iae.getMessage());
                            else if (locationRequestParams.operation.equalsIgnoreCase("PSI"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "PSI", iae.getMessage());
                            else if (locationRequestParams.operation.equalsIgnoreCase("SRI"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "SRI", iae.getMessage());
                            else if (locationRequestParams.operation.equalsIgnoreCase("SRISM"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "SRISM", iae.getMessage());
                            else if (locationRequestParams.operation.equalsIgnoreCase("PSL"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "SRILCS", iae.getMessage());
                            else if (locationRequestParams.operation.equalsIgnoreCase("PLR"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "RIR", iae.getMessage());
                            else if (locationRequestParams.operation.equalsIgnoreCase("UDR"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "UDR", iae.getMessage());
                            else if (locationRequestParams.operation.equalsIgnoreCase("SUPL"))
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, "SUPL", iae.getMessage());
                            else {
                                handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, locationRequestParams.operation, iae.getMessage());
                                return;
                            }
                            return;
                        } else {
                            handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, null, iae.getMessage());
                            return;
                        }
                    } else {
                        handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, null, iae.getMessage());
                        return;
                    }
                }
            }
            break;

            case MLP:
                try {
                    // Get the XML request from the POST data
                    InputStream body = httpServletRequest.getInputStream();
                    // Parse the request and retrieve its parameters (MSISDN, operation, token, etc.)
                    MLPRequest mlpRequest = new MLPRequest(logger);

                    MLPLocationRequest mlpLocationRequest = mlpRequest.parse(body);
                    locationRequestParams = httpServletRequestParams.createLocationRequestParamsFromMLP(locationRequestParams, mlpLocationRequest);
                    if (locationRequestParams.numberFormatException != null) {
                        handleExceptionOnRequest(httpRequestType, locationRequestParams, eventContext, operation, locationRequestParams.numberFormatException);
                        return;
                    }

                    locationRequestParams.httpRespType = "MLP";
                    setHttpRequest(httpRequestType, locationRequestParams);
                } catch (MLPException mlpException) {
                    mlpException.printStackTrace();
                    setHttpRequest(httpRequestType, locationRequestParams);
                    eventContext.suspendDelivery();
                    setEventContextCMP(eventContext);
                    Boolean mlpTriggeredReportingService = false;
                    MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
                    if (reportingService == MLPLocationRequest.ReportingService.Triggered)
                        mlpTriggeredReportingService = true;
                    if (locationRequestParams != null) {
                        if (locationRequestParams.operation != null) {
                            if (locationRequestParams.operation.equalsIgnoreCase("PSI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "PSI",
                                    locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null,
                                    mlpTriggeredReportingService);
                            if (locationRequestParams.operation.equalsIgnoreCase("SRI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "SRI",
                                    locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null,
                                    mlpTriggeredReportingService);
                            if (locationRequestParams.operation.equalsIgnoreCase("SRISM"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "SRISM", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("PSL"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "PSL", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("SRILCS"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("PLR"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "RIR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.plrLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("UDR"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN or IMSPublicIdentity) is null", "UDR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("ATI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "ATI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("SUPL"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", "SUPL", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "MLP Exception", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            return;
                        } else {
                            reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "operation request parameter is null", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            return;
                        }
                    } else {
                        reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Location request parameters are null", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                        return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    setHttpRequest(httpRequestType, locationRequestParams);
                    eventContext.suspendDelivery();
                    setEventContextCMP(eventContext);
                    Boolean mlpTriggeredReportingService = false;
                    MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
                    if (reportingService == MLPLocationRequest.ReportingService.Triggered)
                        mlpTriggeredReportingService = true;
                    if (locationRequestParams != null) {
                        if (locationRequestParams.operation != null) {
                            if (locationRequestParams.operation.equalsIgnoreCase("PSI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "PSI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            if (locationRequestParams.operation.equalsIgnoreCase("SRI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "SRI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            if (locationRequestParams.operation.equalsIgnoreCase("SRISM"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "SRISM", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("PSL"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("SRILCS"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("PLR"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "RIR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.plrLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("UDR"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "UDR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("ATI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "ATI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("SUPL"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", "SUPL", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Internal IO or parsing error occurred", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            return;
                        } else {
                            reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "operation request parameter is null", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            return;
                        }
                    } else {
                        reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Location request parameters are null", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                        return;
                    }
                } catch (IllegalArgumentException iae) {
                    setHttpRequest(httpRequestType, locationRequestParams);
                    eventContext.suspendDelivery();
                    setEventContextCMP(eventContext);
                    Boolean mlpTriggeredReportingService = false;
                    MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
                    if (reportingService == MLPLocationRequest.ReportingService.Triggered)
                        mlpTriggeredReportingService = true;
                    if (locationRequestParams != null) {
                        if (locationRequestParams.operation != null) {
                            if (locationRequestParams.operation.equalsIgnoreCase("PSI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "PSI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            if (locationRequestParams.operation.equalsIgnoreCase("SRI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "SRI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            if (locationRequestParams.operation.equalsIgnoreCase("SRISM"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "SRISM", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("PSL"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("SRILCS"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("PLR"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "RIR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.plrLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("UDR"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "UDR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("ATI"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "ATI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else if (locationRequestParams.operation.equalsIgnoreCase("SUPL"))
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), "SUPL", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            else
                                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, iae.getMessage(), null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            return;
                        } else {
                            reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "operation request parameter is null", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                            return;
                        }
                    } else {
                        reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Location request parameters are null", null, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                        return;
                    }
                }
                break;
            default:
                sendHTTPResult(HttpServletResponse.SC_NOT_FOUND, "Request URI unsupported");
                return;
        }

        if (logger.isFineEnabled()) {
            logger.fine(String.format("Handling %s request, MSISDN: %s via %s operation", httpRequestType.name().toUpperCase(), locationRequestParams.targetingMSISDN,
                locationRequestParams.operation));
        }

        if (locationRequestParams.targetingMSISDN != null || locationRequestParams.targetingIMSI != null) {
            eventContext.suspendDelivery(gmlcPropertiesManagement.getEventContextSuspendDeliveryTimeout());
            setEventContextCMP(eventContext);
            if (locationRequestParams.operation != null) {
                if (locationRequestParams.operation.equalsIgnoreCase("PSI")) {
                    if (!locationRequestParams.psiServiceType.equalsIgnoreCase("psiFirst")) {
                        if (locationRequestParams.psiServiceType.equalsIgnoreCase("useSri"))
                            getLocationViaSubscriberInformationCallHandling(locationRequestParams, false, false);
                        else if (locationRequestParams.psiServiceType.equalsIgnoreCase("useSriSm") || locationRequestParams.psiServiceType == null)
                            getLocationViaSubscriberInformation(locationRequestParams, false, false);
                    } else {
                        if (locationRequestParams.psiOnlyImsi != null && locationRequestParams.psiOnlyNnn != null) {
                            if (locationRequestParams.targetingMSISDN != null)
                                provideSubscriberInfoRequestFirst(locationRequestParams.psiOnlyImsi, locationRequestParams.psiOnlyNnn,
                                    locationRequestParams.domainType, locationRequestParams.targetingMSISDN, locationRequestParams.locationInfoEps,
                                    locationRequestParams.translationType, locationRequestParams.curlUser);
                        } else {
                            if (locationRequestParams.targetingMSISDN != null)
                                if (locationRequestParams.psiServiceType.equalsIgnoreCase("useSri"))
                                    getLocationViaSubscriberInformationCallHandling(locationRequestParams, false, false);
                                else if (locationRequestParams.psiServiceType.equalsIgnoreCase("useSriSm") || locationRequestParams.psiServiceType == null)
                                    getLocationViaSubscriberInformation(locationRequestParams, false, false);
                        }
                    }
                } else {
                    if (locationRequestParams.operation.equalsIgnoreCase("PSL")) {
                        getLocationViaMapLsm(locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams, false);
                    } else if (locationRequestParams.operation.equalsIgnoreCase("ATI")) {
                        getLocationViaMapAti(locationRequestParams);
                    } else if (locationRequestParams.operation.equalsIgnoreCase("PLR")) {
                        getLocationViaLTELocationServices(locationRequestParams.plrUserName, locationRequestParams.plrMsisdn, locationRequestParams);
                    } else if (locationRequestParams.operation.equalsIgnoreCase("UDR")) {
                        getLocationViaIMSSh(locationRequestParams);
                    } else if (locationRequestParams.operation.equalsIgnoreCase("SRI")) {
                        getLocationViaSubscriberInformationCallHandling(locationRequestParams, false, true);
                    } else if (locationRequestParams.operation.equalsIgnoreCase("SRISM")) {
                        getLocationViaSubscriberInformation(locationRequestParams, false, true);
                    } else if (locationRequestParams.operation.equalsIgnoreCase("SRILCS")) {
                        getLocationViaMapLsm(locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams, true);
                    } else if (locationRequestParams.operation.equalsIgnoreCase("SUPL")) {
                        getLocationViaSUPL(locationRequestParams, locationRequestParams.suplTransactionId, aci);
                    }
                }
            } else {
                logger.warning("Operation is null");
                Boolean mlpTriggeredReportingService = false;
                MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
                if (reportingService == MLPLocationRequest.ReportingService.Triggered)
                    mlpTriggeredReportingService = true;
                if (locationRequestParams.pslLcsReferenceNumber != null)
                    reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Operation is null", "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                else if (locationRequestParams.plrLcsReferenceNumber != null)
                    reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Operation is null", "RIR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.plrLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
                else
                    reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Operation is null", "UDR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
            }
        } else {
            logger.warning("Target Subscriber Identity (MSISDN or IMSI) is null");
            Boolean mlpTriggeredReportingService = false;
            MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
            if (reportingService == MLPLocationRequest.ReportingService.Triggered)
                mlpTriggeredReportingService = true;
            if (locationRequestParams.operation.equalsIgnoreCase("PSI"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN) is null", "PSI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, false);
            else if (locationRequestParams.operation.equalsIgnoreCase("PSL"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN or IMSI) is null", "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
            else if (locationRequestParams.operation.equalsIgnoreCase("PLR"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN or IMSI) is null", "RIR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.plrLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
            else if (locationRequestParams.operation.equalsIgnoreCase("UDR"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN or IMSPublicIdentity) is null", "UDR", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, false);
            else if (locationRequestParams.operation.equalsIgnoreCase("ATI"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN) is null", "ATI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, false);
            else if (locationRequestParams.operation.equalsIgnoreCase("SRI"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN) is null", "SRI", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, false);
            else if (locationRequestParams.operation.equalsIgnoreCase("SRISM"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN) is null", "SRISM", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, false);
            else if (locationRequestParams.operation.equalsIgnoreCase("SRILCS"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN) is null", "SRILCS", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, false);
            else if (locationRequestParams.operation.equalsIgnoreCase("SUPL"))
                reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Target Subscriber Identity (MSISDN) is null", "SUPL", locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
        }
    }

    private void handleExceptionOnRequest(HttpRequestType httpRequestType, LocationRequestParams locationRequestParams, EventContext eventContext,
                                          String operation, String exceptionString) {
        setHttpRequest(httpRequestType, locationRequestParams);
        eventContext.suspendDelivery();
        setEventContextCMP(eventContext);
        String msisdn = null, imsi = null;
        Integer referenceNumber = null;
        Boolean mlpTriggeredReportingService = false;
        MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
        if (reportingService == MLPLocationRequest.ReportingService.Triggered)
            mlpTriggeredReportingService = true;
        if (locationRequestParams.targetingMSISDN != null)
            msisdn = locationRequestParams.targetingMSISDN;
        else if (locationRequestParams.targetingIMSI != null)
            imsi = locationRequestParams.targetingIMSI;
        if (locationRequestParams.pslLcsReferenceNumber != null)
            referenceNumber = locationRequestParams.pslLcsReferenceNumber;
        else if (locationRequestParams.plrLcsReferenceNumber != null)
            referenceNumber = locationRequestParams.plrLcsReferenceNumber;
        if (msisdn != null || imsi != null)
            reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, exceptionString, operation, msisdn, imsi,
                referenceNumber, null, null, null, null, mlpTriggeredReportingService);
    }

    private void setHttpRequest(HttpRequestType httpRequestType, LocationRequestParams locationRequestParams) {
        if (locationRequestParams != null) {
            if (locationRequestParams.operation != null) {
                if (!locationRequestParams.operation.equalsIgnoreCase("PLR")) {
                    if (locationRequestParams.operation.equalsIgnoreCase("UDR")) {
                        // set HttpRequest with params for Sh UDR
                        setHttpRequest(new HttpRequest(httpRequestType, locationRequestParams.udrMsisdn, "", locationRequestParams.udrImsPublicId,
                            locationRequestParams.operation, locationRequestParams.domainType, null, null, null, null,
                            null, null, null, null, null, null, null,
                            null, null, null, null, null, null,
                            null, null, null, null, null, null,
                            null, null, null, null, null,
                            locationRequestParams.httpRespType, locationRequestParams.locationInfoEps, locationRequestParams.activeLocation,
                            locationRequestParams.locationInfo5gs, locationRequestParams.ratTypeRequested, null));
                    } else {
                        // set HttpRequest with params for MAP PSL, MAP ATI and MAP SRIx-PSI
                        setHttpRequest(new HttpRequest(httpRequestType, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, locationRequestParams.udrImsPublicId,
                            locationRequestParams.operation, locationRequestParams.domainType, locationRequestParams.pslLcsPriority, locationRequestParams.pslLcsHorizontalAccuracy,
                            locationRequestParams.pslLcsVerticalAccuracy, locationRequestParams.pslVerticalCoordinateRequest, locationRequestParams.pslResponseTimeCategory,
                            locationRequestParams.pslLcsReferenceNumber, locationRequestParams.pslLcsServiceTypeID, locationRequestParams.pslAreaType, locationRequestParams.pslAreaId,
                            null, null, locationRequestParams.pslOccurrenceInfo, locationRequestParams.pslIntervalTime, null, null,
                            null, null, null, null, null, null, null,
                            null, null, locationRequestParams.pslReportingAmount, locationRequestParams.pslReportingInterval,
                            locationRequestParams.slrCallbackUrl, null, locationRequestParams.httpRespType,
                            null, null, null, null, locationRequestParams.translationType));
                    }
                } else {
                    // set HttpRequest with params for SLh-Slg RIR/PLR
                    String priority = null, verticalRequested = null, responseTime = null, areaType = null, additionalAreaType = null, areaEventOccurrenceInfo = null;
                    Integer horizontalAccuracy = null, verticalAccuracy = null, serviceTypeId = null, areaEventIntervalTime = null, periodicLDRReportingAmount = null,
                        periodicLDRReportingInterval = null;
                    if (locationRequestParams.plrLcsPriority != null)
                        priority = String.valueOf(locationRequestParams.plrLcsPriority);
                    if (locationRequestParams.plrHorizontalAccuracy != null)
                        horizontalAccuracy = locationRequestParams.plrHorizontalAccuracy.intValue();
                    if (locationRequestParams.plrVerticalAccuracy != null)
                        verticalAccuracy = locationRequestParams.plrVerticalAccuracy.intValue();
                    if (locationRequestParams.plrVerticalRequested != null)
                        verticalRequested = String.valueOf(locationRequestParams.plrVerticalRequested);
                    if (locationRequestParams.plrResponseTime != null)
                        responseTime = String.valueOf(locationRequestParams.plrResponseTime);
                    if (locationRequestParams.plrLcsServiceTypeId != null)
                        serviceTypeId = locationRequestParams.plrLcsServiceTypeId.intValue();
                    if (locationRequestParams.plrAreaType != null)
                        areaType = String.valueOf(locationRequestParams.plrAreaType);
                    if (locationRequestParams.plrAdditionalAreaType != null)
                        additionalAreaType = String.valueOf(locationRequestParams.plrAdditionalAreaType);
                    if (locationRequestParams.plrAreaEventOccurrenceInfo != null)
                        areaEventOccurrenceInfo = String.valueOf(locationRequestParams.plrAreaEventOccurrenceInfo);
                    if (locationRequestParams.plrAreaEventIntervalTime != null)
                        areaEventIntervalTime = locationRequestParams.plrAreaEventIntervalTime.intValue();
                    if (locationRequestParams.plrPeriodicLDRReportingAmount != null)
                        periodicLDRReportingAmount = locationRequestParams.plrPeriodicLDRReportingAmount.intValue();
                    if (locationRequestParams.plrPeriodicLDRReportingInterval != null)
                        periodicLDRReportingInterval = locationRequestParams.plrPeriodicLDRReportingInterval.intValue();

                    setHttpRequest(new HttpRequest(httpRequestType, locationRequestParams.plrMsisdn, locationRequestParams.plrUserName, locationRequestParams.udrImsPublicId,
                        locationRequestParams.operation, locationRequestParams.domainType, priority, horizontalAccuracy, verticalAccuracy, verticalRequested,
                        responseTime, locationRequestParams.plrLcsReferenceNumber, serviceTypeId, areaType, locationRequestParams.plrAreaIdentification, additionalAreaType,
                        locationRequestParams.plrAdditionalAreaIdentification, areaEventOccurrenceInfo, areaEventIntervalTime, locationRequestParams.plrAreaEventMaxInterval,
                        locationRequestParams.plrAreaEventSamplingInterval, locationRequestParams.plrAreaEventReportingDuration, locationRequestParams.plrAreaEventRepLocRequirements,
                        locationRequestParams.plrMotionEventOccurrenceInfo, locationRequestParams.plrMotionEventLinearDistance, locationRequestParams.plrMotionEventSamplingInterval,
                        locationRequestParams.plrMotionEventIntervalTime, locationRequestParams.plrMotionEventMaximumInterval, locationRequestParams.plrMotionEvenReportingDuration,
                        locationRequestParams.plrMotionEvenReportingLocationRequirements, periodicLDRReportingAmount, periodicLDRReportingInterval,
                        locationRequestParams.lrrCallbackUrl, locationRequestParams.plrQoSClass, locationRequestParams.httpRespType,
                        locationRequestParams.locationInfoEps, locationRequestParams.activeLocation, locationRequestParams.locationInfo5gs, locationRequestParams.ratTypeRequested,
                        null));
                }
            } else {
                setHttpRequest(new HttpRequest(httpRequestType, locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    null, null, null, null, null,
                    null, null, null, null, null, null));
            }
        } else {
            setHttpRequest(new HttpRequest(httpRequestType, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null,
                null, null, null, null, null, null));
        }
    }

    /**
     * CMP
     */
    public abstract void setEventContext(EventContext eventContext);

    public abstract EventContext getEventContext();

    public abstract void setEventContextCMP(EventContext eventContext);

    public abstract EventContext getEventContextCMP();

    public abstract void setHttpRequest(HttpRequest httpRequest);

    public abstract HttpRequest getHttpRequest();

    public abstract void setTimerID(TimerID value);

    public abstract TimerID getTimerID();

    public abstract void setGMLCCDRState(GMLCCDRState gmlcSdrState);

    public abstract GMLCCDRState getGMLCCDRState();

    public abstract void setSendRoutingInfoForLCSResponse(SendRoutingInfoForLCSResponse sendRoutingInfoForLCSResponse);

    public abstract SendRoutingInfoForLCSResponse getSendRoutingInfoForLCSResponse();

    public abstract void setProvideSubscriberLocationResponse(ProvideSubscriberLocationResponse provideSubscriberLocationResponse);

    public abstract ProvideSubscriberLocationResponse getProvideSubscriberLocationResponse();

    public abstract void setSubscriberLocationReportRequest(SubscriberLocationReportRequest subscriberLocationReportRequest);

    public abstract SubscriberLocationReportRequest getSubscriberLocationReportRequest();

    public abstract void setSendRoutingInfoForSMResponse(SendRoutingInfoForSMResponse sendRoutingInfoForSMResponse);

    public abstract SendRoutingInfoForSMResponse getSendRoutingInfoForSMResponse();

    public abstract void setProvideSubscriberInformationResponse(ProvideSubscriberInfoResponse subscriberLocationReportRequest);

    public abstract ProvideSubscriberInfoResponse getProvideSubscriberInformationResponse();

    public abstract void setSendRoutingInformationResponse(SendRoutingInformationResponse sendRoutingInformationResponse);

    public abstract SendRoutingInformationResponse getSendRoutingInformationResponse();

    public abstract void setErrorResponse(MAPErrorMessage errorResponse);

    public abstract MAPErrorMessage getErrorResponse();


    /*** Private helper methods ***/

    /**
     * Handle generating the appropriate ATI generated by the HTTP request
     *
     * @param locationRequestParams Parameters to set in MAP ATI gathered from HTTP request
     */
    private void getLocationViaMapAti(LocationRequestParams locationRequestParams) {

        String requestingMSISDN = locationRequestParams.targetingMSISDN;
        String domain = locationRequestParams.domainType;
        String epsLocationInfo = locationRequestParams.locationInfoEps;
        String extraInfo = locationRequestParams.atiExtraInfoRequested;
        Integer calledPartyTranslationType = locationRequestParams.translationType;
        String curlUser = locationRequestParams.curlUser;
        String httpResponseType = locationRequestParams.httpRespType;

        if (!requestingMSISDN.equals(fakeNumber)) {
            try {
                AddressString originAddressString, destinationAddressString;
                originAddressString = destinationAddressString = null;
                MAPDialogMobility mapDialogMobility;
                int translationType = 0; // Translation Type = 0 : Unknown (default value)
                if (calledPartyTranslationType != null)
                    translationType = calledPartyTranslationType; // Translation Type taken from HTTP request
                mapDialogMobility = this.mapProvider.getMAPServiceMobility().createNewDialog(
                    this.getMAPAtiApplicationContext(), this.getGmlcSccpAddress(), originAddressString,
                    getHlrSccpAddress(requestingMSISDN, translationType), destinationAddressString);
                ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, requestingMSISDN);
                SubscriberIdentity subscriberIdentity = new SubscriberIdentityImpl(msisdn);
                boolean locationInformation = true;
                boolean subscriberState = true;
                MAPExtensionContainer mapExtensionContainer = null;
                boolean currentLocation = true;
                DomainType requestedDomain = null;
                if (domain.equalsIgnoreCase("ps"))
                    requestedDomain = DomainType.psDomain;
                else
                    requestedDomain = DomainType.csDomain;
                boolean imei = true;
                boolean msClassmark = true;
                boolean mnpRequestedInfo = true;
                if (extraInfo.equalsIgnoreCase("false")) {
                    imei = false;
                    msClassmark = false;
                    mnpRequestedInfo = false;
                }

                boolean locationInformationEPSsupported;
                if (epsLocationInfo == null)
                    locationInformationEPSsupported = true;
                else
                    locationInformationEPSsupported = Boolean.valueOf(epsLocationInfo);
                RequestedInfo requestedInfo = new RequestedInfoImpl(locationInformation, subscriberState, mapExtensionContainer, currentLocation,
                    requestedDomain, imei, msClassmark, mnpRequestedInfo, locationInformationEPSsupported);

                ISDNAddressString gscmSCFAddress = new ISDNAddressStringImpl(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN,
                    gmlcPropertiesManagement.getGmlcGt());

                mapDialogMobility.addAnyTimeInterrogationRequest(subscriberIdentity, requestedInfo, gscmSCFAddress, mapExtensionContainer);

                Long transaction = mobileCoreNetworkTransactions.Instance().create();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "atiMsisdn", requestingMSISDN);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "atiHttpRespType", httpResponseType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", curlUser);
                mobileCoreNetworkTransactions.Instance().setDialog(transaction, mapDialogMobility.getLocalDialogId());
                DateTime transactionStart = DateTime.now();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", transactionStart);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "mlpTriggeredReportingService", false);

                ActivityContextInterface atiDialogACI = this.mapAcif.getActivityContextInterface(mapDialogMobility);
                atiDialogACI.attach(this.sbbContext.getSbbLocalObject());
                mapDialogMobility.send();

                // set new timer for ATI cycle
                TimerID timerID = timerFacility.setTimer(atiDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);


            } catch (MAPException e) {
                this.logger.severe("MAPException while trying to send MAP ATI request for MSISDN=" + requestingMSISDN, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP ATI request",
                    "ATI", requestingMSISDN, null, null, null, null, null, null, false);
            } catch (Exception e) {
                this.logger.severe("Exception while trying to send MAP ATI request for MSISDN=" + requestingMSISDN, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP ATI request",
                    "ATI", requestingMSISDN, null, null, null, null, null, null, false);
            }

        } else {
            // Handle fake location type given fake number set as MSISDN
            if (this.fakeLocationType == MLPResponse.MLPResultType.OK) {
                AtiResponseValues response = new AtiResponseValues();
                try {
                    response.setCi(fakeCellId);
                    response.setMcc(Integer.parseInt(fakeLocationX));
                    response.setMcc(Integer.parseInt(fakeLocationY));
                    response.setLac(Integer.parseInt(fakeLocationRadius));
                } catch (Exception e) {
                    logger.severe("MAP Exception while assigning ATI response values for MLP fake location:" + e);
                }

                String mlpClientErrorMessage = null;
                this.handleAtiLocationResponse(MLPResponse.MLPResultType.OK, response, requestingMSISDN, mlpClientErrorMessage, "Fake number", httpResponseType);

            } else {
                AtiResponseValues response;
                response = null;
                this.handleAtiLocationResponse(this.fakeLocationType, response, requestingMSISDN, this.fakeLocationAdditionalInfoErrorString, "Fake number", httpResponseType);
            }
        }
    }


    /**
     * Handle generating the appropriate SRILCS-PSL (LSM) operations generated by the HTTP request
     *
     * @param targetMsisdn          Target MSISDN
     * @param targetImsi            Target IMSI
     * @param locationRequestParams Parameters to set in SRILCS and PSL gathered from HTTP request
     */
    private void getLocationViaMapLsm(String targetMsisdn, String targetImsi, LocationRequestParams locationRequestParams, boolean sriLcsOnly) {

        String targetSubscriberIdentity;
        if (targetMsisdn != null)
            targetSubscriberIdentity = targetMsisdn;
        else
            targetSubscriberIdentity = targetImsi;
        SubscriberIdentity subscriberIdentity = null;
        Boolean mlpTriggeredReportingService = false;
        MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
        if (reportingService == MLPLocationRequest.ReportingService.Triggered)
            mlpTriggeredReportingService = true;

        if (!targetSubscriberIdentity.equals(fakeNumber)) {
            try {
                AddressString originAddressString, destinationAddressString;
                originAddressString = destinationAddressString = null;
                MAPDialogLsm mapDialogLsmSRIforLCS = null;
                int translationType = 0; // Translation Type = 0 : Unknown (default value)
                if (locationRequestParams.translationType != null)
                    translationType = locationRequestParams.translationType; // Translation Type taken from HTTP request
                if (targetMsisdn != null) {
                    mapDialogLsmSRIforLCS = this.mapProvider.getMAPServiceLsm().createNewDialog(
                        this.getMAPSriforLcsApplicationContext(), this.getGmlcSccpAddress(), originAddressString,
                        getHlrSccpAddress(targetMsisdn, translationType), destinationAddressString);
                    ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number,
                        org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, targetMsisdn);
                    subscriberIdentity = new SubscriberIdentityImpl(msisdn);
                } else if (targetImsi != null) {
                    mapDialogLsmSRIforLCS = this.mapProvider.getMAPServiceLsm().createNewDialog(
                        this.getMAPSriforLcsApplicationContext(), this.getGmlcSccpAddress(), originAddressString,
                        getHlrSccpAddress(targetImsi, translationType), destinationAddressString);
                    targetSubscriberIdentity = targetImsi;
                    IMSI imsi = new IMSIImpl(targetImsi);
                    subscriberIdentity = new SubscriberIdentityImpl(imsi);
                }
                ISDNAddressString gmlcAddress = new ISDNAddressStringImpl(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, this.getGmlcSccpAddress().getGlobalTitle().getDigits());
                MAPExtensionContainer mapExtensionContainer = null;
                mapDialogLsmSRIforLCS.addSendRoutingInfoForLCSRequest(gmlcAddress, subscriberIdentity, mapExtensionContainer);

                // Transaction
                Long transaction = mobileCoreNetworkTransactions.Instance().create();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslMsisdn", locationRequestParams.pslMsisdn);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslImsi", locationRequestParams.pslImsi);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLcsReferenceNumber", locationRequestParams.pslLcsReferenceNumber);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslImei", locationRequestParams.pslImei);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLocationEstimateType", locationRequestParams.pslLocationEstimateType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslDeferredLocationEventType", locationRequestParams.pslDeferredLocationEventType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLcsClientType", locationRequestParams.pslLcsClientType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslClientExternalID", locationRequestParams.pslClientExternalID);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslClientInternalID", locationRequestParams.pslClientInternalID);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslClientName", locationRequestParams.pslClientName);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslClientFormatIndicator", locationRequestParams.pslClientFormatIndicator);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslRequestorIdString", locationRequestParams.pslRequestorIdString);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslRequestorFormatIndicator", locationRequestParams.pslRequestorFormatIndicator);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLcsServiceTypeID", locationRequestParams.pslLcsServiceTypeID);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLcsPriority", locationRequestParams.pslLcsPriority);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLcsHorizontalAccuracy", locationRequestParams.pslLcsHorizontalAccuracy);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLcsVerticalAccuracy", locationRequestParams.pslLcsVerticalAccuracy);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslVerticalCoordinateRequest", locationRequestParams.pslVerticalCoordinateRequest);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslResponseTimeCategory", locationRequestParams.pslResponseTimeCategory);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslAreaType", locationRequestParams.pslAreaType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslAreaId", locationRequestParams.pslAreaId);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslOccurrenceInfo", locationRequestParams.pslOccurrenceInfo);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslIntervalTime", locationRequestParams.pslIntervalTime);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslReportingAmount", locationRequestParams.pslReportingAmount);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslReportingInterval", locationRequestParams.pslReportingInterval);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslPLMNIdList", locationRequestParams.pslPLMNIdList);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslVisitedPLMNIdRAN", locationRequestParams.pslVisitedPLMNIdRAN);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslPeriodicLocationSupportIndicator", locationRequestParams.pslPeriodicLocationSupportIndicator);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslPrioritizedListIndicator", locationRequestParams.pslPrioritizedListIndicator);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "pslLcsCodeword", locationRequestParams.pslLcsCodeword);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "slrCallbackUrl", locationRequestParams.slrCallbackUrl);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "httpRequestType", locationRequestParams.httpRespType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", locationRequestParams.curlUser);
                mobileCoreNetworkTransactions.Instance().setDialog(transaction, mapDialogLsmSRIforLCS.getLocalDialogId());
                DateTime transactionStart = DateTime.now();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", transactionStart);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriLcsOnly", sriLcsOnly);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "mlpTriggeredReportingService", mlpTriggeredReportingService);

                // Create the ACI and attach this SBB
                ActivityContextInterface sriForLcsDialogACI = this.mapAcif.getActivityContextInterface(mapDialogLsmSRIforLCS);
                sriForLcsDialogACI.attach(this.sbbContext.getSbbLocalObject());
                mapDialogLsmSRIforLCS.send();

                // set new timer for SRILCS cycle
                TimerID timerID = timerFacility.setTimer(sriForLcsDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);

            } catch (MAPException e) {
                this.logger.severe("MAPException while trying to send MAP SRILCS request for Subscriber Identity=" + targetSubscriberIdentity, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP SRILCS request",
                    "SRILCS", targetMsisdn, targetImsi, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
            } catch (Exception e) {
                this.logger.severe("Exception while trying to send MAP SRILCS request for Subscriber Identity=" + targetSubscriberIdentity, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP SRILCS request",
                    "SRILCS", targetMsisdn, targetImsi, locationRequestParams.pslLcsReferenceNumber, null, null, null, null, mlpTriggeredReportingService);
            }

        } else {
            // Handle fake location type given fake number set as MSISDN
            if (this.fakeLocationType == MLPResponse.MLPResultType.OK) {
                PslResponseValues response = new PslResponseValues();
                final CellGlobalIdOrServiceAreaIdFixedLength cellFake = new CellGlobalIdOrServiceAreaIdFixedLength() {
                    @Override
                    public byte[] getData() {
                        return new byte[0];
                    }

                    @Override
                    public int getMCC() {
                        return 0;
                    }

                    @Override
                    public int getMNC() {
                        return 0;
                    }

                    @Override
                    public int getLac() {
                        return 0;
                    }

                    @Override
                    public int getCellIdOrServiceAreaCode() {
                        return fakeCellId;
                    }
                };
                CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = new CellGlobalIdOrServiceAreaIdOrLAI() {
                    @Override
                    public CellGlobalIdOrServiceAreaIdFixedLength getCellGlobalIdOrServiceAreaIdFixedLength() {
                        return cellFake;
                    }

                    @Override
                    public LAIFixedLength getLAIFixedLength() {
                        return null;
                    }
                };
                response.setCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdOrLAI);
                ExtGeographicalInformation extGeographicalInformation = new ExtGeographicalInformation() {
                    @Override
                    public byte[] getData() {
                        return new byte[0];
                    }

                    @Override
                    public TypeOfShape getTypeOfShape() {
                        return null;
                    }

                    @Override
                    public double getLatitude() {
                        return Double.parseDouble(fakeLocationX);
                    }

                    @Override
                    public double getLongitude() {
                        return Double.parseDouble(fakeLocationY);
                    }

                    @Override
                    public double getUncertainty() {
                        return 0;
                    }

                    @Override
                    public double getUncertaintySemiMajorAxis() {
                        return 0;
                    }

                    @Override
                    public double getUncertaintySemiMinorAxis() {
                        return 0;
                    }

                    @Override
                    public double getAngleOfMajorAxis() {
                        return 0;
                    }

                    @Override
                    public int getConfidence() {
                        return 0;
                    }

                    @Override
                    public int getAltitude() {
                        return 0;
                    }

                    @Override
                    public double getUncertaintyAltitude() {
                        return 0;
                    }

                    @Override
                    public int getInnerRadius() {
                        return Integer.valueOf(fakeLocationRadius);
                    }

                    @Override
                    public double getUncertaintyRadius() {
                        return 0;
                    }

                    @Override
                    public double getOffsetAngle() {
                        return 0;
                    }

                    @Override
                    public double getIncludedAngle() {
                        return 0;
                    }
                };
                response.setLocationEstimate(extGeographicalInformation);
                String mlpClientErrorMessage = null;
                this.handleLsmLocationResponse(MLPResponse.MLPResultType.OK, null, response, null, mlpClientErrorMessage, mlpTriggeredReportingService);

            } else {
                SriForLcsResponseValues response;
                response = null;
                this.handleLsmLocationResponse(this.fakeLocationType, response, null, null, this.fakeLocationAdditionalInfoErrorString, mlpTriggeredReportingService);
            }
        }
    }

    /**
     * Handle generating the appropriate SRISM-PSI generated by the HTTP request
     *
     * @param locationRequestParams Parameters to set in SRISM and PSI gathered from HTTP request
     * @param onDialogRejected      Boolean value for set in when the dialog has been rejected by the SS7 network
     * @param sriSmOnly             Boolean value that indicates if the PSI shall be executed after SRISM or not
     */
    private void getLocationViaSubscriberInformation(LocationRequestParams locationRequestParams,
                                                     boolean onDialogRejected, boolean sriSmOnly) {

        String requestingMSISDN = locationRequestParams.targetingMSISDN;
        String domain = locationRequestParams.domainType;
        String epsLocationInfo = locationRequestParams.locationInfoEps;
        Integer calledPartyTranslationType = locationRequestParams.translationType;
        String curlUser = locationRequestParams.curlUser;

        if (!requestingMSISDN.equals(fakeNumber)) {
            try {
                AddressString originAddressString, destinationAddressString;
                originAddressString = destinationAddressString = null;
                MAPDialogSms mapDialogSms;
                MAPApplicationContext mapApplicationContext = this.getMAPSRIforSMApplicationContext(onDialogRejected);
                String acnVersion;
                if (mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2)
                    acnVersion = "version2";
                else
                    acnVersion = "version3";

                int translationType = 0; // Translation Type = 0 : Unknown (default value)
                if (calledPartyTranslationType != null)
                    translationType = calledPartyTranslationType; // Translation Type taken from HTTP request
                mapDialogSms = this.mapProvider.getMAPServiceSms().createNewDialog(mapApplicationContext, this.getGmlcSccpAddress(),
                    originAddressString, getHlrSccpAddress(requestingMSISDN, translationType), destinationAddressString);
                ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, requestingMSISDN);
                boolean sm_RP_PRI = true;
                AddressString serviceCentreAddressString = this.mapParameterFactory.createAddressString(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, gmlcPropertiesManagement.getGmlcGt());
                mapDialogSms.addSendRoutingInfoForSMRequest(msisdn, sm_RP_PRI, serviceCentreAddressString,
                    null, false, null, null, null, false, null, false, false, null, null);

                // Transaction
                Long transaction = mobileCoreNetworkTransactions.Instance().create();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriPsiDomain", domain);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "locationInfoEPS", epsLocationInfo);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiMsisdn", requestingMSISDN);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriAcnVersion", acnVersion);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "tt", translationType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", curlUser);
                mobileCoreNetworkTransactions.Instance().setDialog(transaction, mapDialogSms.getLocalDialogId());
                DateTime transactionStart = DateTime.now();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", transactionStart);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriSmOnly", sriSmOnly);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "mlpTriggeredReportingService", false);

                // Create the ACI and attach this SBB
                ActivityContextInterface sriForSmDialogACI = this.mapAcif.getActivityContextInterface(mapDialogSms);
                sriForSmDialogACI.attach(this.sbbContext.getSbbLocalObject());

                // Send SRISM
                mapDialogSms.send();

                // set new timer for SRISM cycle
                TimerID timerID = timerFacility.setTimer(sriForSmDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);

            } catch (MAPException e) {
                this.logger.severe("MAP Exception while trying to send MAP SRISM request for MSISDN=" + requestingMSISDN, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP SRISM request",
                    "SRISM", requestingMSISDN, null, null, null, null, null, null, false);
            } catch (Exception e) {
                this.logger.severe("MAP Exception while trying to send MAP SRISM request for MSISDN=" + requestingMSISDN, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP SRISM request",
                    "SRISM", requestingMSISDN, null, null, null, null, null, null, false);
            }

        } else {
            // Handle fake location type given fake number set as MSISDN
            PsiResponseValues response = new PsiResponseValues();
            if (this.fakeLocationType == MLPResponse.MLPResultType.OK) {
                try {
                    response.setMcc(Integer.parseInt(fakeLocationX));
                    response.setMcc(Integer.parseInt(fakeLocationY));
                    response.setLac(Integer.parseInt(fakeLocationRadius));
                    response.setCi(fakeCellId);
                } catch (Exception e) {
                    logger.severe("MAP Exception while assigning ATI response values for MLP fake location:" + e);
                }

                String mlpClientErrorMessage = null;
                this.handlePsiResponse(MLPResponse.MLPResultType.OK, response, requestingMSISDN, mlpClientErrorMessage);

            } else {
                response = null;
                this.handlePsiResponse(this.fakeLocationType, response, requestingMSISDN, this.fakeLocationAdditionalInfoErrorString);
            }
        }
    }

    /**
     * Handle generating the appropriate SRI-PSI generated by the HTTP request
     *
     * @param locationRequestParams Parameters to set in SRI and PSI gathered from HTTP request
     * @param onDialogRejected      Boolean value for set in when the dialog has been rejected by the SS7 network
     * @param sriOnly               Boolean value that indicates if the PSI shall be executed after SRI or not
     */
    private void getLocationViaSubscriberInformationCallHandling(LocationRequestParams locationRequestParams,
                                                                 boolean onDialogRejected, boolean sriOnly) {

        String requestingMSISDN = locationRequestParams.targetingMSISDN;
        String domain = locationRequestParams.domainType;
        String epsLocationInfo = locationRequestParams.locationInfoEps;
        Integer calledPartyTranslationType = locationRequestParams.translationType;
        String curlUser = locationRequestParams.curlUser;

        if (!requestingMSISDN.equals(fakeNumber)) {
            try {
                AddressString originAddressString, destinationAddressString;
                originAddressString = destinationAddressString = null;
                MAPDialogCallHandling mapDialogCallHandling;
                MAPApplicationContext mapApplicationContext = this.getMAPSRIApplicationContext(onDialogRejected);
                String acnVersion;
                if (mapApplicationContext.getApplicationContextVersion() == MAPApplicationContextVersion.version2)
                    acnVersion = "version2";
                else
                    acnVersion = "version3";

                int translationType = 0; // Translation Type = 0 : Unknown (default value)
                if (calledPartyTranslationType != null)
                    translationType = calledPartyTranslationType; // Translation Type taken from HTTP request
                mapDialogCallHandling = this.mapProvider.getMAPServiceCallHandling().createNewDialog(mapApplicationContext,
                    this.getGmlcSccpAddress(), originAddressString, getHlrSccpAddress(requestingMSISDN, translationType), destinationAddressString);
                ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, requestingMSISDN);
                ISDNAddressString gmlcAddress = new ISDNAddressStringImpl(AddressNature.international_number,
                    org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, this.getGmlcSccpAddress().getGlobalTitle().getDigits());

                mapDialogCallHandling.addSendRoutingInformationRequest(msisdn, null, null,
                    InterrogationType.basicCall, false, null, gmlcAddress, null,
                    null, null, null, null, false,
                    null, null, false, null, null,
                    null, false, null, false, false, false,
                    false, null, null, null, false, null);

                // Transaction
                Long transaction = mobileCoreNetworkTransactions.Instance().create();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriPsiDomain", domain);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "locationInfoEPS", epsLocationInfo);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "psiMsisdn", requestingMSISDN);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriAcnVersion", acnVersion);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "tt", translationType);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", curlUser);
                mobileCoreNetworkTransactions.Instance().setDialog(transaction, mapDialogCallHandling.getLocalDialogId());
                DateTime transactionStart = DateTime.now();
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", transactionStart);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "sriOnly", sriOnly);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "mlpTriggeredReportingService", false);

                // Create the ACI and attach this SBB
                ActivityContextInterface sriDialogACI = this.mapAcif.getActivityContextInterface(mapDialogCallHandling);
                sriDialogACI.attach(this.sbbContext.getSbbLocalObject());

                // Send SRI
                mapDialogCallHandling.send();

                // set new timer for SRI cycle
                TimerID timerID = timerFacility.setTimer(sriDialogACI, null, System.currentTimeMillis() + MAP_OPERATION_TIMEOUT, defaultTimerOptions);
                mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);

            } catch (MAPException e) {
                this.logger.severe("MAP Exception while trying to send MAP SRI request for MSISDN=" + requestingMSISDN, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP SRI request",
                    "SRI", requestingMSISDN, null, null, null, null, null, null, false);
            } catch (Exception e) {
                this.logger.severe("MAP Exception while trying to send MAP SRI request for MSISDN=" + requestingMSISDN, e);
                this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "MAPException while trying to send MAP SRI request",
                    "SRI", requestingMSISDN, null, null, null, null, null, null, false);
            }
        } else {
            // Handle fake location type given fake number set as MSISDN
            PsiResponseValues response = new PsiResponseValues();
            if (this.fakeLocationType == MLPResponse.MLPResultType.OK) {
                try {
                    response.setMcc(Integer.parseInt(fakeLocationX));
                    response.setMcc(Integer.parseInt(fakeLocationY));
                    response.setLac(Integer.parseInt(fakeLocationRadius));
                    response.setCi(fakeCellId);
                } catch (Exception e) {
                    logger.severe("MAP Exception while assigning SRI response values for MLP fake location:" + e);
                }

                String mlpClientErrorMessage = null;
                this.handlePsiResponse(MLPResponse.MLPResultType.OK, response, requestingMSISDN, mlpClientErrorMessage);

            } else {
                response = null;
                this.handlePsiResponse(this.fakeLocationType, response, requestingMSISDN, this.fakeLocationAdditionalInfoErrorString);
            }
        }
    }

    /**
     * Handle generating the appropriate SLh-SLg Diameter commands generated by the HTTP request
     *
     * @param targetImsi            Target IMSI
     * @param targetMsisdn          Target MSISDN
     * @param locationRequestParams Parameters to set in RIR and PLR gathered from HTTP request
     */
    public void getLocationViaLTELocationServices(String targetImsi, String targetMsisdn, LocationRequestParams locationRequestParams) {

        Boolean mlpTriggeredReportingService = false;
        MLPLocationRequest.ReportingService reportingService = locationRequestParams.getReportingService();
        if (reportingService == MLPLocationRequest.ReportingService.Triggered)
            mlpTriggeredReportingService = true;

        try {
            DiameterIdentity destHost = new DiameterIdentity(locationRequestParams.hssDiameterHost != null ? locationRequestParams.hssDiameterHost : gmlcPropertiesManagement.getDiameterDestHost());
            DiameterIdentity destRealm = new DiameterIdentity(locationRequestParams.hssDiameterRealm != null ? locationRequestParams.hssDiameterRealm : gmlcPropertiesManagement.getDiameterDestRealm());
            SLhClientSessionActivity slhClientSessionActivity = this.slhProvider.createSLhClientSessionActivity(destHost, destRealm);
            // Keep ACI across Diameter session for RIR/RIA
            ActivityContextInterface slhACIF = slhAcif.getActivityContextInterface(slhClientSessionActivity);
            slhACIF.attach(this.sbbContext.getSbbLocalObject());

            SLhRirAvpValues slhRirRequestValues = new SLhRirAvpValues();
            slhRirRequestValues.plrUserName = targetImsi;
            slhRirRequestValues.plrMsisdn = targetMsisdn;
            slhRirRequestValues.plrSlgLocationType = locationRequestParams.plrSlgLocationType;
            slhRirRequestValues.plrLcsNameString = locationRequestParams.plrLcsNameString;
            slhRirRequestValues.plrLcsFormatInd = locationRequestParams.plrLcsFormatInd;
            slhRirRequestValues.plrLcsClientType = locationRequestParams.plrLcsClientType;
            slhRirRequestValues.plrLcsRequestorIdString = locationRequestParams.plrLcsRequestorIdString;
            slhRirRequestValues.plrLcsRequestorFormatIndicator = locationRequestParams.plrLcsRequestorFormatIndicator;
            slhRirRequestValues.plrQoSClass = locationRequestParams.plrQoSClass;
            slhRirRequestValues.plrHorizontalAccuracy = locationRequestParams.plrHorizontalAccuracy;
            slhRirRequestValues.plrVerticalAccuracy = locationRequestParams.plrVerticalAccuracy;
            slhRirRequestValues.plrVerticalRequested = locationRequestParams.plrVerticalRequested;
            slhRirRequestValues.plrResponseTime = locationRequestParams.plrResponseTime;
            slhRirRequestValues.plrVelocityRequested = locationRequestParams.plrVelocityRequested;
            slhRirRequestValues.plrPrivacyCheckNonSession = locationRequestParams.plrPrivacyCheckNonSession;
            slhRirRequestValues.plrPrivacyCheckSession = locationRequestParams.plrPrivacyCheckSession;
            slhRirRequestValues.plrAreaType = locationRequestParams.plrAreaType;
            slhRirRequestValues.plrAreaIdentification = locationRequestParams.plrAreaIdentification;
            slhRirRequestValues.plrAdditionalAreaType = locationRequestParams.plrAdditionalAreaType;
            slhRirRequestValues.plrAdditionalAreaIdentification = locationRequestParams.plrAdditionalAreaIdentification;
            slhRirRequestValues.plrAreaEventOccurrenceInfo = locationRequestParams.plrAreaEventOccurrenceInfo;
            slhRirRequestValues.plrAreaEventIntervalTime = locationRequestParams.plrAreaEventIntervalTime;
            slhRirRequestValues.plrAreaEventMaxInterval = locationRequestParams.plrAreaEventMaxInterval;
            slhRirRequestValues.plrAreaEventSamplingInterval = locationRequestParams.plrAreaEventSamplingInterval;
            slhRirRequestValues.plrAreaEventReportingDuration = locationRequestParams.plrAreaEventReportingDuration;
            slhRirRequestValues.plrAreaEventRepLocRequirements = locationRequestParams.plrAreaEventRepLocRequirements;
            slhRirRequestValues.plrPeriodicLDRReportingAmount = locationRequestParams.plrPeriodicLDRReportingAmount;
            slhRirRequestValues.plrPeriodicLDRReportingInterval = locationRequestParams.plrPeriodicLDRReportingInterval;
            slhRirRequestValues.plrVisitedPLMNIdList = locationRequestParams.plrVisitedPLMNIdList;
            slhRirRequestValues.plrPeriodicLocationSupportIndicator = locationRequestParams.plrPeriodicLocationSupportIndicator;
            slhRirRequestValues.reportingPLMNListAvp = locationRequestParams.reportingPLMNListAvp;
            slhRirRequestValues.plrPrioritizedListIndicator = locationRequestParams.plrPrioritizedListIndicator;
            slhRirRequestValues.plrMotionEventOccurrenceInfo = locationRequestParams.plrMotionEventOccurrenceInfo;
            slhRirRequestValues.plrMotionEventlinearDistance = locationRequestParams.plrMotionEventLinearDistance;
            slhRirRequestValues.plrMotionEventSamplingInterval = locationRequestParams.plrMotionEventSamplingInterval;
            slhRirRequestValues.plrMotionEventIntervalTime = locationRequestParams.plrMotionEventIntervalTime;
            slhRirRequestValues.plrMotionEventMaximumInterval = locationRequestParams.plrMotionEventMaximumInterval;
            slhRirRequestValues.plrMotionEvenReportingDuration = locationRequestParams.plrMotionEvenReportingDuration;
            slhRirRequestValues.plrMotionEvenReportingLocationRequirements = locationRequestParams.plrMotionEvenReportingLocationRequirements;
            slhRirRequestValues.plrLcsReferenceNumber = locationRequestParams.plrLcsReferenceNumber;
            slhRirRequestValues.lrrCallbackUrl = locationRequestParams.lrrCallbackUrl;
            slhRirRequestValues.plrImei = locationRequestParams.plrImei;
            slhRirRequestValues.plrLcsPriority = locationRequestParams.plrLcsPriority;
            slhRirRequestValues.plrLcsSupportedGadShapes = locationRequestParams.plrLcsSupportedGadShapes;
            slhRirRequestValues.plrLcsServiceTypeId = locationRequestParams.plrLcsServiceTypeId;
            slhRirRequestValues.plrLcsCodeword = locationRequestParams.plrLcsCodeword;
            slhRirRequestValues.plrServiceSelection = locationRequestParams.plrServiceSelection;
            slhRirRequestValues.plrServiceSelection = locationRequestParams.plrServiceSelection;
            slhRirRequestValues.plrDeferredLocationType = locationRequestParams.plrDeferredLocationType;
            slhRirRequestValues.plrFlags = locationRequestParams.plrFlags;

            Long transaction = mobileCoreNetworkTransactions.create();
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "slhRirRequestValues", slhRirRequestValues);
            mobileCoreNetworkTransactions.Instance().setSession(transaction, slhClientSessionActivity.getSessionId());
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", locationRequestParams.curlUser);
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "httpRequestType", locationRequestParams.httpRespType);
            DateTime transactionStart = DateTime.now();
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", transactionStart);
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "mlpTriggeredReportingService", mlpTriggeredReportingService);

            // set the new timer for the RIR/RIA cycle
            TimerID timerID = timerFacility.setTimer(slhACIF, null, System.currentTimeMillis() + DIAMETER_COMMAND_TIMEOUT, defaultTimerOptions);
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerID", timerID);

            if (logger.isFineEnabled()) {
                this.logger.fine(String.format("Saving RIR values with session '%s' and transaction '%d'.", slhClientSessionActivity.getSessionId(), transaction));
            }
            LCSRoutingInfoRequest rir = slhClientSessionActivity.createLCSRoutingInfoRequest();

            String gmlcNumber = gmlcPropertiesManagement.getDiameterGmlcNumber();

            if (targetImsi != null) {
                rir.setUserName(targetImsi);
            }

            if (targetMsisdn != null) {
                byte[] tbcdMsisdn = parseTBCD(targetMsisdn);
                rir.setMSISDN(tbcdMsisdn);
            }

            byte[] tbcdGmlcNumber = parseTBCD(gmlcNumber);
            rir.setGMLCNumber(tbcdGmlcNumber);

            AuthSessionStateType authSessionStateType = AuthSessionStateType.NO_STATE_MAINTAINED;
            rir.setAuthSessionState(authSessionStateType);

            slhClientSessionActivity.sendLCSRoutingInfoRequest(rir);
            if (logger.isFineEnabled()) {
                logger.fine("Sent SLh RIR: " + rir);
            }

        } catch (Exception e) {
            logger.severe("setupLteLocationServicesInterface(): exception while sending LCSRoutingInfoRequest: " + e.getMessage(), e);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception while setting up LCSRoutingInfoRequest",
                "RIR", targetMsisdn, targetImsi, locationRequestParams.plrLcsReferenceNumber, null, null,
                null, null, mlpTriggeredReportingService);
        }
    }

    /**
     * Handle generating the appropriate Sh User-Data-Request command generated by the HTTP request
     *
     * @param locationRequestParams Parameters to set in Diameter Sh UDR gathered from HTTP request
     */
    public void getLocationViaIMSSh(LocationRequestParams locationRequestParams) {

        String msisdn = locationRequestParams.udrMsisdn;
        String publicIdentity = locationRequestParams.udrImsPublicId;
        int dataReferenceType = DataReferenceType._LOCATION_INFORMATION;
        String domain = locationRequestParams.domainType;
        String active = locationRequestParams.activeLocation;
        String epsLocInfo = locationRequestParams.locationInfoEps;
        String nr5gLocationInfo = locationRequestParams.locationInfo5gs;
        String ratTypeRequested = locationRequestParams.ratTypeRequested;
        String curlUser = locationRequestParams.curlUser;
        String hssHost = locationRequestParams.hssDiameterHost;
        String hssRealm = locationRequestParams.hssDiameterRealm;

        try {
            DiameterIdentity destHost = new DiameterIdentity(hssHost != null ? hssHost : gmlcPropertiesManagement.getDiameterDestHost());
            DiameterIdentity destRealm = new DiameterIdentity(hssRealm != null ? hssRealm : gmlcPropertiesManagement.getDiameterDestRealm());
            ShClientActivity shClientActivity = this.shClientProvider.createShClientActivity();
            // Keep ACI across Diameter session for UDR/UDA
            ActivityContextInterface shACIF = shClientAcif.getActivityContextInterface(shClientActivity);
            shACIF.attach(this.sbbContext.getSbbLocalObject());

            UserDataRequest udr = shClientActivity.getClientMessageFactory().createUserDataRequest();

            UserIdentityAvp userIdentityAvp = new UserIdentityAvpImpl(DiameterShAvpCodes.USER_IDENTITY, Sh_VENDOR_ID, 1, 0, new byte[]{});
            if (msisdn != null) {
                byte[] tbcdMsisdn = parseTBCD(msisdn);
                userIdentityAvp.setMsisdn(tbcdMsisdn);
            }
            if (publicIdentity != null)
                userIdentityAvp.setPublicIdentity(publicIdentity);
            udr.setUserIdentity(userIdentityAvp);

            DataReferenceType dataReference = DataReferenceType.fromInt(dataReferenceType);
            udr.setDataReference(dataReference);

            RequestedDomainType requestedDomainType = RequestedDomainType.PS_DOMAIN;
            if (domain != null) {
                if (domain.equalsIgnoreCase("cs")) {
                    requestedDomainType = RequestedDomainType.CS_DOMAIN;
                }
            }
            if (epsLocInfo == null || epsLocInfo.equalsIgnoreCase("true")) {
                if (ratTypeRequested != null) {
                    if (ratTypeRequested.equalsIgnoreCase("true")) {
                        udr.setUDRFlags(3L);
                    } else {
                        udr.setUDRFlags(1L);
                    }
                } else {
                    udr.setUDRFlags(1L);
                }
                if (nr5gLocationInfo != null) {
                    if (nr5gLocationInfo.equalsIgnoreCase("true")) {
                        udr.setRequestedNodes(8L);
                    } else {
                        udr.setRequestedNodes(3L);
                    }
                } else {
                    udr.setRequestedNodes(3L);
                }
            } else {
                if (ratTypeRequested != null) {
                    if (ratTypeRequested.equalsIgnoreCase("true"))
                        udr.setUDRFlags(2L);
                }
            }
            udr.setRequestedDomain(requestedDomainType);

            CurrentLocationType currentLocationType = CurrentLocationType.INITIATE_ACTIVE_LOCATION_RETRIEVAL;
            if (active != null) {
                if (active.equalsIgnoreCase("false"))
                    currentLocationType = CurrentLocationType.DO_NOT_NEED_INITIATE_ACTIVE_LOCATION_RETRIEVAL;
            }
            udr.setCurrentLocation(currentLocationType);

            udr.setDestinationHost(destHost);
            udr.setDestinationRealm(destRealm);

            AuthSessionStateType authSessionStateType = AuthSessionStateType.NO_STATE_MAINTAINED;
            udr.setAuthSessionState(authSessionStateType);

            Long transaction = mobileCoreNetworkTransactions.create();
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "shUdrMsisdn", msisdn);
            mobileCoreNetworkTransactions.Instance().setSession(transaction, shClientActivity.getSessionId());
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "curlUser", curlUser);
            DateTime transactionStart = DateTime.now();
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "transactionStart", transactionStart);
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "mlpTriggeredReportingService", false);

            // set the new timer for the UDR/UDA cycle
            TimerID timerID = timerFacility.setTimer(shACIF, null, System.currentTimeMillis() + DIAMETER_COMMAND_TIMEOUT, defaultTimerOptions);
            mobileCoreNetworkTransactions.Instance().setValue(transaction, "timerId", timerID);

            shClientActivity.sendUserDataRequest(udr);
            if (logger.isFineEnabled()) {
                logger.fine("Sent Sh UDR for MSISDN=" + udr.getUserIdentity().getMsisdn() + " with session Id: " + udr.getSessionId() +
                    ", host '" + udr.getDestinationHost() + "', realm '" + udr.getDestinationRealm());
                logger.fine("Sh UDR details: " + udr);
            }

        } catch (Exception e) {
            logger.severe("getLocationViaIMSSh: exception while sending Sh UDR: " + e.getMessage(), e);
            this.reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, "Exception while setting up Sh UDR", "RIR",
                msisdn, null, null, null, null, null, null, false);
        }
    }

    /**
     * Handle generating the appropriate SUPL session generated by the HTTP request
     *
     * @param locationRequestParams MLP Request Parameters for SUPL
     */
    public void getLocationViaSUPL(LocationRequestParams locationRequestParams, Integer transactionId, ActivityContextInterface aci) {
        InetAddress localSocketAddress = null, remoteSocketAddress = null;
        int localSocketPort = -1, remoteSocketPort = -1;
        Boolean mlpTriggeredReportingService = false;
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        if (networkInitiatedSuplLocation != null) {
            if (networkInitiatedSuplLocation.getServerSocket() != null) {
                localSocketAddress = networkInitiatedSuplLocation.getServerSocket().getInetAddress();
                localSocketPort = networkInitiatedSuplLocation.getServerSocket().getLocalPort();
                SocketAddress socketAddress = networkInitiatedSuplLocation.getServerSocket().getLocalSocketAddress();
                InetSocketAddress remoteInetSocketAddress = (InetSocketAddress) socketAddress;
                remoteSocketAddress = remoteInetSocketAddress.getAddress();
                remoteSocketPort = remoteInetSocketAddress.getPort();
            }
        }
        // CDR initialization
        GMLCCDRState gmlcCdrState = CDRCreationHelper.suplCdrInitializer(aci, this.getCDRInterface(), locationRequestParams,
                localSocketAddress, localSocketPort, remoteSocketAddress, remoteSocketPort);
        // Set timer last
        this.setTimer(aci);

        try {
            QoP qoP = null;
            if (locationRequestParams.suplHorizontalAccuracy != null) {
                qoP = new QoP(locationRequestParams.suplHorizontalAccuracy);
                if (locationRequestParams.suplVerticalAccuracy != null && locationRequestParams.suplMaximumLocationAge != null &&
                        locationRequestParams.suplDelay != null && locationRequestParams.suplResponseTime != null) {
                    if (locationRequestParams.suplResponseTime != null) {
                        qoP = new QoP(locationRequestParams.suplHorizontalAccuracy, locationRequestParams.suplVerticalAccuracy,
                                locationRequestParams.suplMaximumLocationAge, locationRequestParams.suplDelay, locationRequestParams.suplResponseTime);
                    }
                }
            }
            SuplResponseHelperForMLP suplResponseHelperForMLP = null;
            PosMethod posMethod = PosMethod.agpsSETassisted(); // FIXME hardcoded for now
            TriggerParams triggerParams = null;
            if (locationRequestParams.reportingService == MLPLocationRequest.ReportingService.Triggered) {
                // The Location Request arms triggers for reports on area events or periodically (originated by MLP TLRR)
                mlpTriggeredReportingService = true;
                triggerParams = new TriggerParams();
                if (locationRequestParams.suplAreaEventType != null) {
                    Integer areaType = locationRequestParams.suplAreaEventType;
                    AreaEventType areaEventType;
                    switch (areaType) {
                        case 0:
                            areaEventType = AreaEventType.enteringArea();
                            break;
                        case 1:
                            areaEventType = AreaEventType.insideArea();
                            break;
                        case 2:
                            areaEventType = AreaEventType.outsideArea();
                            break;
                        case 3:
                            areaEventType = AreaEventType.leavingArea();
                            break;
                        default:
                            areaEventType = AreaEventType.valueOf(-999);
                            break;
                    }
                    Asn1Boolean locationEstimateRequired = new Asn1Boolean(locationRequestParams.suplLocationEstimateRequested);
                    Asn1Integer minimumIntervalTime = new Asn1Integer(locationRequestParams.suplMinimumIntervalTime);
                    Asn1Integer maxNumberOfReports = new Asn1Integer(locationRequestParams.suplMaximumNumberOfReports);
                    RepeatedReportingParams repeatedReportingParams = new RepeatedReportingParams(minimumIntervalTime, maxNumberOfReports);
                    Asn1Integer startTime = null, stopTime = null;
                    if (locationRequestParams.suplStartTime != null)
                        startTime = new Asn1Integer(locationRequestParams.suplStartTime);
                    if (locationRequestParams.suplStopTime != null)
                        stopTime = new Asn1Integer(locationRequestParams.suplStopTime);
                    GeographicTargetArea geographicTargetArea = new GeographicTargetArea();
                    Coordinate_latitudeSign latitudeSign = Coordinate_latitudeSign.north();
                    if (locationRequestParams.suplAreaEventLatitude < 0) {
                        latitudeSign = Coordinate_latitudeSign.south();
                        locationRequestParams.suplAreaEventLatitude *= -1;
                    }
                    Asn1Integer asn1Latitude = new Asn1Integer(GADShapesUtils.encodeLatitude(locationRequestParams.suplAreaEventLatitude));
                    Asn1Integer asn1Longitude = new Asn1Integer(GADShapesUtils.encodeLongitude(locationRequestParams.suplAreaEventLongitude));
                    Coordinate areaCoordinates = new Coordinate(latitudeSign, asn1Latitude, asn1Longitude);
                    if (locationRequestParams.suplGeographicTargetArea == SuplGeoTargetArea.CircularArea.name()) {
                        Asn1Integer radius = new Asn1Integer(locationRequestParams.suplAreaEventRadius);
                        CircularArea circularArea = new CircularArea(areaCoordinates, radius);
                        geographicTargetArea.isCircularArea();
                        geographicTargetArea.setCircularArea(circularArea);
                    }
                    if (locationRequestParams.suplGeographicTargetArea == SuplGeoTargetArea.EllipticalArea.name()) {
                        Asn1Integer semiMajor = new Asn1Integer(GADShapesUtils.encodeUncertainty(locationRequestParams.suplAreaEventSemiMajor));
                        Asn1Integer semiMinor = new Asn1Integer(GADShapesUtils.encodeUncertainty(locationRequestParams.suplAreaEventSemiMinor));
                        Asn1Integer angle = new Asn1Integer((long) ((locationRequestParams.suplAreaEventAngle / 2) - 1));
                        EllipticalArea ellipticalArea = new EllipticalArea();
                        ellipticalArea.setCoordinate(areaCoordinates);
                        ellipticalArea.setSemiMajor(semiMajor);
                        ellipticalArea.setSemiMinor(semiMinor);
                        ellipticalArea.setAngle(angle);
                        geographicTargetArea.isEllipticalArea();
                        geographicTargetArea.setEllipticalArea(ellipticalArea);
                    }
                    GeographicTargetArea[] geoTargetAreaArray = new GeographicTargetArea[]{geographicTargetArea};
                    GeographicTargetAreaList geographicTargetAreaList = new GeographicTargetAreaList(geoTargetAreaArray);
                    // TODO fill AreaEventParams_areaIdLists according to locationRequestParams.suplAreaId
                    byte[] eci = new byte[]{(byte) 0xc6, 0x03, 0x30, 0x30}; // FIXME hardcoded for now
                    LTEAreaId lteAreaId = new LTEAreaId(748L, 1L, new Asn1BitString(29, eci)); // FIXME hardcoded for now
                    AreaId areaId = new AreaId();
                    areaId.setLTEAreaId(lteAreaId);
                    AreaId[] areaIds = new AreaId[]{areaId};
                    /*for (AreaId areaId : areaIds) { }*/
                    AreaIdSet areaIdSet = new AreaIdSet(areaIds);
                    AreaIdList areaIdList = new AreaIdList(areaIdSet);
                    AreaIdList[] areaIdListArray = new AreaIdList[]{areaIdList};
                    AreaEventParams_areaIdLists areaIdLists = new AreaEventParams_areaIdLists(areaIdListArray);
                    AreaEventParams areaEventParams = new AreaEventParams(areaEventType, locationEstimateRequired, repeatedReportingParams,
                            startTime, stopTime, geographicTargetAreaList, areaIdLists);
                    triggerParams.setAreaEventParams(areaEventParams);
                    suplResponseHelperForMLP = networkInitiatedSuplLocation.processNetworkInitiatedSuplBySmppAreaEventTriggeredService(qoP, SuplTriggerType.AreaEvent.getSuplTriggerType(), posMethod, SLPMode.nonProxy(), triggerParams, transactionId, locationRequestParams.getTargetingMSISDN());
                    String splId = new String(suplResponseHelperForMLP.getSessionID().getSlpSessionID().getSessionID().value);
                    exec.scheduleWithFixedDelay(new MlpResponseExecutor(exec, splId), SUPL_SESSION_SCHEDULE, SUPL_SESSION_SCHEDULE, TimeUnit.MILLISECONDS);
                    try {
                        logger.info("EGMLC Exec ->  ScheduledThreadPoolExecutor");
                        exec.awaitTermination(SUPL_SESSION_TIMEOUT,TimeUnit.MILLISECONDS);
                        exec.shutdown();
                        int sessionStatus = (int) suplTransaction.getValue(splId,"sessionStatus");
                        if (sessionStatus == SuplSessionStatus.SUPL_SESSION_END.getSuplSessionStatus()) {
                            suplResponseHelperForMLP = (SuplResponseHelperForMLP) suplTransaction.getValue(splId,"suplResponseHelperForMLP");
                            suplTransaction.destroy(splId);
                        } else {
                            suplTransaction.destroy(splId);
                            suplResponseHelperForMLP.setTimeOut(true);
                        }
                    } catch (InterruptedException e) {
                        logger.severe("Error on start SULP session with slpId -> " + splId);
                    }
                    if (suplResponseHelperForMLP != null) {
                        suplResponseHelperForMLP.setMsisdn(locationRequestParams.targetingMSISDN);
                        suplResponseHelperForMLP.setImsi(locationRequestParams.targetingIMSI);
                        httpSubscriberLocationReport = getHttpSubscriberLocationReport();
                        boolean isMlp = true;
                        suplResponseHelperForMLP.suplReportReferenceNumber = httpSubscriberLocationReport.Register(transactionId,
                                locationRequestParams.suplAgentCallbackUrl, null, isMlp, locationRequestParams.curlUser);
                        httpSubscriberLocationReport.closeMongo();
                    }
                } else {
                    // Is periodic Event
                    Asn1Integer numberOfFixes = new Asn1Integer(locationRequestParams.suplReportingAmount);
                    Asn1Integer intervalBetweenFixes = new Asn1Integer(locationRequestParams.suplReportingInterval);
                    Asn1Integer periodicStartTime = new Asn1Integer(locationRequestParams.suplStartTime);
                    PeriodicParams periodicParams = new PeriodicParams(numberOfFixes, intervalBetweenFixes, periodicStartTime);
                    triggerParams.setPeriodicParams(periodicParams);
                    suplResponseHelperForMLP = networkInitiatedSuplLocation.processNetworkInitiatedSuplBySmppAreaEventTriggeredService(qoP, SuplTriggerType.AreaEvent.getSuplTriggerType(), posMethod, SLPMode.nonProxy(), triggerParams, transactionId, locationRequestParams.getTargetingMSISDN());
                    String splId = new String(suplResponseHelperForMLP.getSessionID().getSlpSessionID().getSessionID().value);
                    exec.scheduleWithFixedDelay(new MlpResponseExecutor(exec, splId), SUPL_SESSION_SCHEDULE, SUPL_SESSION_SCHEDULE, TimeUnit.MILLISECONDS);
                    try {
                        logger.info("EGMLC Exec ->  ScheduledThreadPoolExecutor");
                        exec.awaitTermination(SUPL_SESSION_TIMEOUT,TimeUnit.MILLISECONDS);
                        exec.shutdown();
                        int sessionStatus = (int) suplTransaction.getValue(splId,"sessionStatus");
                        if (sessionStatus == SuplSessionStatus.SUPL_SESSION_END.getSuplSessionStatus()) {
                            suplResponseHelperForMLP = (SuplResponseHelperForMLP) suplTransaction.getValue(splId,"suplResponseHelperForMLP");
                            suplTransaction.destroy(splId);
                        } else {
                            suplTransaction.destroy(splId);
                            suplResponseHelperForMLP.setTimeOut(true);
                            // Removing from the concurrent hashmap
                            // TimeOut Error
                        }
                    } catch (InterruptedException e) {
                        logger.severe("Error on start SULP session with slpId -> " + splId);
                    }
                    if (suplResponseHelperForMLP != null) {
                        suplResponseHelperForMLP.setMsisdn(locationRequestParams.targetingMSISDN);
                        suplResponseHelperForMLP.setImsi(locationRequestParams.targetingIMSI);
                        httpSubscriberLocationReport = getHttpSubscriberLocationReport();
                        boolean isMlp = true;
                        suplResponseHelperForMLP.suplReportReferenceNumber = httpSubscriberLocationReport.Register(transactionId,
                                locationRequestParams.suplAgentCallbackUrl, null, isMlp, locationRequestParams.curlUser);
                        httpSubscriberLocationReport.closeMongo();
                    }
                }
            } else {
                logger.warning("EGMLC -> MSISDN " + locationRequestParams.getTargetingMSISDN());
                // Location Request it is standard (originated by MLP SLIR), does not arm triggers for periodic or area event reporting
                //TODO Check when the request contains IMSI
                suplResponseHelperForMLP = networkInitiatedSuplLocation.processNetworkInitiatedSuplBySmppStandardService(qoP, SLPMode.nonProxy(), transactionId, locationRequestParams.getTargetingMSISDN());
                String splId = new String(suplResponseHelperForMLP.getSessionID().getSlpSessionID().getSessionID().value);
                exec.scheduleWithFixedDelay(new MlpResponseExecutor(exec, splId), SUPL_SESSION_SCHEDULE, SUPL_SESSION_SCHEDULE, TimeUnit.MILLISECONDS);
                try {
                    logger.info("EGMLC Exec ->  ScheduledThreadPoolExecutor");
                    exec.awaitTermination(SUPL_SESSION_TIMEOUT,TimeUnit.MILLISECONDS);
                    exec.shutdown();
                    int sessionStatus = (int) suplTransaction.getValue(splId,"sessionStatus");
                    if (sessionStatus == SuplSessionStatus.SUPL_SESSION_END.getSuplSessionStatus()) {
                        suplResponseHelperForMLP = (SuplResponseHelperForMLP) suplTransaction.getValue(splId,"suplResponseHelperForMLP");
                        suplTransaction.destroy(splId);
                    } else {
                        suplTransaction.destroy(splId);
                        suplResponseHelperForMLP.setTimeOut(true);
                        // Removing from the concurrent hashmap
                        // TimeOut Error
                    }
                } catch (InterruptedException e) {
                    logger.severe("Error on start SULP session with slpId -> " + splId);
                }

            }
            if (suplResponseHelperForMLP != null && !suplResponseHelperForMLP.getTimeOut()) {
                handleSUPLResponse(MLPResponse.MLPResultType.OK, "", locationRequestParams.targetingMSISDN,
                        locationRequestParams.targetingIMSI, transactionId, suplResponseHelperForMLP, mlpTriggeredReportingService, false);
                if (suplResponseHelperForMLP.isReport()) {
                    handleSuplReports(suplResponseHelperForMLP, aci, triggerParams, locationRequestParams, gmlcCdrState, transactionId, mlpTriggeredReportingService);
                } else {
                    if (gmlcCdrState.isInitialized()) {
                        if (triggerParams != null) {
                            if (triggerParams.getAreaEventParams() != null) {
                                CDRCreationHelper.updateSuplCdrState(gmlcCdrState, suplResponseHelperForMLP, networkInitiatedSuplLocation, transactionId);
                                this.createCDRRecord(RecordStatus.SUPL_NI_AREA_TRIGGERED_SUCCESS);
                            }
                            if (triggerParams.isPeriodicParams()) {
                                CDRCreationHelper.updateSuplCdrState(gmlcCdrState, suplResponseHelperForMLP, networkInitiatedSuplLocation, transactionId);
                                this.createCDRRecord(RecordStatus.SUPL_NI_PERIODIC_TRIGGERED_SUCCESS);
                            }
                        } else {
                            CDRCreationHelper.updateSuplCdrState(gmlcCdrState, suplResponseHelperForMLP, networkInitiatedSuplLocation, transactionId);
                            this.createCDRRecord(RecordStatus.SUPL_NI_STANDARD_SUCCESS);
                        }
                    } else {
                        if (gmlcCdrState.isInitialized()) {
                            this.createCDRRecord(RecordStatus.SUPL_SYSTEM_FAILURE);
                        }
                    }
                }
            } else {
                if (suplResponseHelperForMLP.getTimeOut()) {
                    this.createCDRRecord(RecordStatus.SUPL_TIMEOUT);
                    reportLocationRequestError(MLPResponse.MLPResultType.TIMEOUT, "Network Initiated SUPL positioning timeout", "SUPL",
                            locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
                } else {
                    if (gmlcCdrState.isInitialized()) {
                        if (triggerParams != null) {
                            if (locationRequestParams.suplAreaEventType != null) {
                                this.createCDRRecord(RecordStatus.SUPL_NI_AREA_TRIGGERED_FAILURE);
                            }
                            if (locationRequestParams.suplReportingInterval != null && locationRequestParams.suplReportingAmount != null) {
                                this.createCDRRecord(RecordStatus.SUPL_NI_PERIODIC_TRIGGERED_FAILURE);
                            }
                        } else {
                            this.createCDRRecord(RecordStatus.SUPL_NI_STANDARD_FAILURE);
                        }
                    } else {
                        this.createCDRRecord(RecordStatus.SUPL_SYSTEM_FAILURE);
                    }
                    reportLocationRequestError(MLPResponse.MLPResultType.POSITION_METHOD_FAILURE, "Network Initiated SUPL positioning failure", "SUPL",
                            locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);

                }
            }

        } catch (Exception e) {
            logger.severe("Exception on getLocationViaSUPL ", e);
            if (gmlcCdrState.isInitialized()) {
                this.createCDRRecord(RecordStatus.SUPL_SYSTEM_FAILURE);
            }
            reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Exception on SUPL request", "SUPL", locationRequestParams.targetingMSISDN,
                    locationRequestParams.targetingIMSI, null, null, null, null, null, mlpTriggeredReportingService);
        } finally {
            aci.detach(this.sbbContext.getSbbLocalObject());
        }
    }

    public void handleSuplReports(SuplResponseHelperForMLP suplResponseHelperForMLP, ActivityContextInterface aci, TriggerParams triggerParams,
                                  LocationRequestParams locationRequestParams, GMLCCDRState gmlcCdrState, int transactionId, boolean mlpTriggeredReportingService) {
        try {
            httpSubscriberLocationReport = getHttpSubscriberLocationReport();
            httpSubscriberLocationReport.Perform(HttpReport.HttpMethod.POST, suplResponseHelperForMLP.suplReportReferenceNumber,
                suplResponseHelperForMLP, false);
            httpSubscriberLocationReport.closeMongo();
            if (gmlcCdrState.isInitialized()) {
                if (triggerParams != null) {
                    if (triggerParams.getAreaEventParams() != null) {
                        CDRCreationHelper.updateSuplCdrState(gmlcCdrState, suplResponseHelperForMLP, null, transactionId);
                        this.createCDRRecord(RecordStatus.SUPL_NI_AREA_TRIGGERED_REPORT_SUCCESS);
                    }
                    if (triggerParams.isPeriodicParams()) {
                        CDRCreationHelper.updateSuplCdrState(gmlcCdrState, suplResponseHelperForMLP, null, transactionId);
                        this.createCDRRecord(RecordStatus.SUPL_NI_PERIODIC_TRIGGERED_REPORT_SUCCESS);
                    }
                }
            } else {
                this.createCDRRecord(RecordStatus.SUPL_SYSTEM_FAILURE);
            }
        } catch (Exception e) {
            logger.severe("Exception on handleSuplReports ", e);
            reportLocationRequestError(MLPResponse.MLPResultType.FORMAT_ERROR, "Exception on SUPL report processing", "SUPL",
                locationRequestParams.targetingMSISDN, locationRequestParams.targetingIMSI, null, null, null, null,
                null, mlpTriggeredReportingService);
            aci.detach(this.sbbContext.getSbbLocalObject());
            if (gmlcCdrState.isInitialized()) {
                this.createCDRRecord(RecordStatus.SUPL_SYSTEM_FAILURE);
            }
        }
    }

    /*
     * MAP Application Context creation for ATI
     */
    private MAPApplicationContext getMAPAtiApplicationContext() {
        if (this.anyTimeEnquiryContext == null) {
            this.anyTimeEnquiryContext = MAPApplicationContext.getInstance(
                MAPApplicationContextName.anyTimeEnquiryContext, MAPApplicationContextVersion.version3);
        }
        return this.anyTimeEnquiryContext;
    }

    /*
     * MAP Application Context creation for SRILCS
     */
    private MAPApplicationContext getMAPSriforLcsApplicationContext() {
        if (this.locationSvcGatewayContext == null) {
            this.locationSvcGatewayContext = MAPApplicationContext.getInstance(MAPApplicationContextName.locationSvcGatewayContext,
                MAPApplicationContextVersion.version3);
        }
        return this.locationSvcGatewayContext;
    }

    /*
     * MAP Application Context creation for PSL and SLR
     */
    private MAPApplicationContext getMAPPslSlrApplicationContext() {
        if (this.locationSvcEnquiryContext == null) {
            this.locationSvcEnquiryContext = MAPApplicationContext.getInstance(MAPApplicationContextName.locationSvcEnquiryContext,
                MAPApplicationContextVersion.version3);
        }
        return this.locationSvcEnquiryContext;
    }

    /*
     * MAP Application Context creation for SRISM
     */
    private MAPApplicationContext getMAPSRIforSMApplicationContext(boolean onDialogRejected) {
        if (!onDialogRejected)
            this.shortMsgGatewayContext = MAPApplicationContext.getInstance(MAPApplicationContextName.shortMsgGatewayContext,
                MAPApplicationContextVersion.version3);
        else
            this.shortMsgGatewayContext = MAPApplicationContext.getInstance(MAPApplicationContextName.shortMsgGatewayContext,
                MAPApplicationContextVersion.version2);
        return this.shortMsgGatewayContext;
    }

    /*
     * MAP Application Context creation for SRI (call handling)
     */
    private MAPApplicationContext getMAPSRIApplicationContext(boolean onDialogRejected) {
        if (!onDialogRejected)
            this.locationInfoRetrievalContext = MAPApplicationContext.getInstance(MAPApplicationContextName.locationInfoRetrievalContext,
                MAPApplicationContextVersion.version3);
        else
            this.locationInfoRetrievalContext = MAPApplicationContext.getInstance(MAPApplicationContextName.locationInfoRetrievalContext,
                MAPApplicationContextVersion.version2);
        return this.locationInfoRetrievalContext;
    }

    /*
     * MAP Application Context creation for PSI
     */
    private MAPApplicationContext getMAPPsiApplicationContext() {
        if (this.subscriberInfoEnquiryContext == null) {
            this.subscriberInfoEnquiryContext = MAPApplicationContext.getInstance(
                MAPApplicationContextName.subscriberInfoEnquiryContext, MAPApplicationContextVersion.version3);
        }
        return this.subscriberInfoEnquiryContext;
    }

    /*
     * GMLC SCCP Address creation
     */
    protected SccpAddress getGmlcSccpAddress() {
        if (this.gmlcSCCPAddress == null) {
            int tt = 0; // Translation Type = 0 : Unknown
            EncodingScheme es = null;
            GlobalTitle gt = sccpParameterFact.createGlobalTitle(gmlcPropertiesManagement.getGmlcGt(), tt, NumberingPlan.ISDN_TELEPHONY,
                es, NatureOfAddress.INTERNATIONAL);
            this.gmlcSCCPAddress = sccpParameterFact.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                gt, 0, gmlcPropertiesManagement.getGmlcSsn());
        }
        return this.gmlcSCCPAddress;
    }

    /*
     * HLR SCCP Address creation
     */
    private SccpAddress getHlrSccpAddress(String address, Integer translationType) {
        int tt = 0; // Translation Type = 0 : Unknown (default value)
        if (translationType != null)
            tt = translationType; // Translation Type taken from HTTP request
        EncodingScheme es = null;
        GlobalTitle gt = sccpParameterFact.createGlobalTitle(address, tt, NumberingPlan.ISDN_TELEPHONY, es, NatureOfAddress.INTERNATIONAL);
        return sccpParameterFact.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, 0, gmlcPropertiesManagement.getHlrSsn());
    }

    /*
     * MSC SCCP Address creation
     */
    private SccpAddress getMscSccpAddress(String address) {
        int tt = 0; // Translation Type = 0 : Unknown (default value)
        EncodingScheme es = null;
        GlobalTitle gt = sccpParameterFact.createGlobalTitle(address, tt, NumberingPlan.ISDN_TELEPHONY, es, NatureOfAddress.INTERNATIONAL);
        return sccpParameterFact.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, 0, gmlcPropertiesManagement.getMscSsn());
    }

    /*
     * VLR SCCP Address creation
     */
    private SccpAddress getVlrSccpAddress(String address) {
        int tt = 0; // Translation Type = 0 : Unknown (default value)
        EncodingScheme es = null;
        GlobalTitle gt = sccpParameterFact.createGlobalTitle(address, tt, NumberingPlan.ISDN_TELEPHONY, es, NatureOfAddress.INTERNATIONAL);
        return sccpParameterFact.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, 0, gmlcPropertiesManagement.getVlrSsn());
    }

    /*
     * SGSN SCCP Address creation
     */
    private SccpAddress getSgsnSccpAddress(String address) {
        int tt = 0; // Translation Type = 0 : Unknown (default value)
        EncodingScheme es = null;
        GlobalTitle gt = sccpParameterFact.createGlobalTitle(address, tt, NumberingPlan.ISDN_TELEPHONY, es, NatureOfAddress.INTERNATIONAL);
        return sccpParameterFact.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, 0, gmlcPropertiesManagement.getSgsnSsn());
    }

    /*
     * Called Party ISDN Address creation
     */
    protected ISDNAddressString getCalledPartyISDNAddressString(String destinationAddress, int ton, int npi) {
        return this.mapParameterFactory.createISDNAddressString(AddressNature.getInstance(ton),
            org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.getInstance(npi), destinationAddress);
    }

    /**
     * Handle generating the appropriate HTTP response
     * We're making use of the MLPResponse class for both GET/POST requests for convenience and
     * because eventually the GET method will likely be removed
     *
     * @param mlpResultType         OK or error type to return to client
     * @param atiResponseValues     ATIResponse on location attempt
     * @param mlpClientErrorMessage Error message to send to the client
     */
    protected void handleAtiLocationResponse(MLPResponse.MLPResultType mlpResultType, AtiResponseValues atiResponseValues, String atiMsisdnDigits,
                                             String mlpClientErrorMessage, String dialogErrorMessage, String httpResponseType) {

        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();

            MapAtiResponseHelperForMLP atiHelper = new MapAtiResponseHelperForMLP();
            atiHelper.handleAtiResponseValues(atiResponseValues);

            switch (request.type) {
                case REST:
                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {

                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                        if (httpResponseType != null && httpResponseType.equalsIgnoreCase("plain")) {

                            /***** Plain HTTP Response *****/
                            // for retro-compatibility with Restcomm Geolocation API and GMLC v1.0.0
                            StringBuilder atiResponseSb = new StringBuilder();
                            atiResponseSb.append("mcc=");
                            atiResponseSb.append(atiHelper.getMcc());
                            atiResponseSb.append(", mnc=");
                            atiResponseSb.append(atiHelper.getMnc());
                            atiResponseSb.append(", lac=");
                            atiResponseSb.append(atiHelper.getLac());
                            atiResponseSb.append(", cellid=");
                            atiResponseSb.append(atiHelper.getCi());
                            atiResponseSb.append(", aol=");
                            atiResponseSb.append(atiHelper.getAgeOfLocationInfo());
                            atiResponseSb.append(", vlrNumber=");
                            atiResponseSb.append(atiHelper.getVlrNumber());
                            atiResponseSb.append(", subscriberState=");
                            atiResponseSb.append(atiHelper.getSubscriberState());
                            atiResponseSb.append("\n");

                            this.sendHTTPResult(httpServletResponse.SC_OK, atiResponseSb.toString());

                        } else {
                            /***** JSON Response *****/
                            String jsonResponse = "";
                            try {
                                if (atiResponseValues != null)
                                    jsonResponse = AtiResponseJsonBuilder.buildJsonResponseForAti(atiResponseValues, atiMsisdnDigits, dialogErrorMessage);
                                else
                                    reportLocationRequestError(MLPResponse.MLPResultType.SYSTEM_FAILURE, dialogErrorMessage,
                                        "ATI", atiMsisdnDigits, null, null, atiHelper != null ? atiHelper.getNnn() : null,
                                        null, null, null, false);
                            } catch (MAPException me) {
                                this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "{\"error\": \"Failure: MAP exception\"}");
                                me.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            this.sendHTTPResult(httpServletResponse.SC_OK, jsonResponse);
                            break;
                        }
                    } else {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mlpClientErrorMessage);
                    }
                    break;

                case MLP:
                    String svcResultXml;
                    MLPResponse mlpResponse = new MLPResponse(this.logger);

                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        MLPResponseParams atiResponse = new MLPResponseParams();
                        atiResponse.mlpTypeOfShape = atiHelper.getTypeOfShape();
                        atiResponse.x = atiHelper.getLatitude();
                        atiResponse.y = atiHelper.getLongitude();
                        atiResponse.radius = atiHelper.getRadius();
                        atiResponse.mlpMcc = atiHelper.getMcc();
                        atiResponse.mlpMnc = atiHelper.getMnc();
                        atiResponse.mlpLac = atiHelper.getLac();
                        atiResponse.mlpCi = atiHelper.getCi();
                        atiResponse.mlpSac = atiHelper.getSac();
                        atiResponse.mlpEci = atiHelper.getEci();
                        atiResponse.mlpRac = atiHelper.getRac();
                        atiResponse.mlpTac = atiHelper.getTac();
                        atiResponse.mlpNci = null;
                        atiResponse.mlpMmeName = atiHelper.getMmeName();
                        atiResponse.mlpSgsnName = null;
                        atiResponse.mlpMscNo = atiHelper.getMscNumber();
                        atiResponse.mlpVlrNo = atiHelper.getVlrNumber();
                        atiResponse.mlpMsisdn = atiMsisdnDigits;
                        atiResponse.mlpImei = atiHelper.getImei();
                        atiResponse.mlpImsi = null;
                        atiResponse.mlpAge = atiHelper.getAgeOfLocationInfo();
                        atiResponse.mlpLmsi = null;
                        atiResponse.mlpRatType = null;

                        svcResultXml = mlpResponse.getCoreNetworkSinglePositionXML("ATI", atiResponse.mlpTypeOfShape, atiResponse.x, atiResponse.y,
                            atiResponse.radius, null, null, null, null, null, null, null, null,
                            atiResponse.mlpMcc, atiResponse.mlpMnc, atiResponse.mlpLac, atiResponse.mlpCi, atiResponse.mlpSac, atiResponse.mlpEci,
                            atiResponse.mlpRac, atiResponse.mlpTac, atiResponse.mlpNci, atiResponse.mlpMmeName, atiResponse.mlpSgsnName, atiResponse.mlpMscNo,
                            atiResponse.mlpVlrNo, atiResponse.mlpMsisdn, atiResponse.mlpImei, atiResponse.mlpImsi, atiResponse.mlpAge, atiResponse.mlpLmsi,
                            null, null, atiResponse.mlpRatType, mlpResultType, false, false);

                    } else if (MLPResponse.isSystemError(mlpResultType)) {
                        svcResultXml = mlpResponse.getSystemErrorResponseXML(mlpResultType, mlpClientErrorMessage, false);
                    } else {
                        String vlrNumber = null, mscNumber = null;
                        if (atiResponseValues.getVlrNumber() != null)
                            vlrNumber = atiResponseValues.getVlrNumber().getAddress();
                        if (atiResponseValues.getMscNumber() != null)
                            mscNumber = atiResponseValues.getMscNumber().getAddress();
                        svcResultXml = mlpResponse.getPositionErrorResponseXML(atiMsisdnDigits, null, mscNumber, vlrNumber, mlpResultType, mlpClientErrorMessage, false);
                    }

                    this.sendHTTPResult(HttpServletResponse.SC_OK, svcResultXml);
                    break;
            }
        } catch (Exception e) {
            logger.severe("Exception on handleAtiLocationResponse: ", e);
        }
    }

    /**
     * Handle generating the appropriate HTTP response
     * We're making use of the MLPResponse class for both GET/POST requests for convenience and
     * because eventually the GET method will likely be removed
     *
     * @param mlpResultType         OK or error type to return to client
     * @param sri                   SRIForLCS response on location attempt
     * @param psl                   PSL response on location attempt
     * @param slrReq                SLR request report after location attempt
     * @param mlpClientErrorMessage Error message to send to the client
     */
    protected void handleLsmLocationResponse(MLPResponse.MLPResultType mlpResultType, SriForLcsResponseValues sri, PslResponseValues psl,
                                             SlrRequestValues slrReq, String mlpClientErrorMessage, Boolean mlpTriggeredReportingService) {

        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();

            switch (request.type) {
                case REST:
                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {

                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                        String jsonResponse = "";
                        try {
                            jsonResponse = PslResponseJsonBuilder.buildJsonResponseForPsl(sri, psl, sri.getPslMsisdn(), sri.getPslImsi(),
                                sri.getPslLcsReferenceNumber(), sri.getPslReferenceNumber());
                        } catch (MAPException me) {
                            this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "{\"error\": \"Fail MAP params\"}");
                            me.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.sendHTTPResult(httpServletResponse.SC_OK, jsonResponse);
                        break;
                    }

                case MLP:
                    String svcResultXml;
                    MLPResponse mlpResponse = new MLPResponse(this.logger);

                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        MapLsmResponseHelperForMLP mapLsmHelper = new MapLsmResponseHelperForMLP();
                        String pslMsisdn = null, pslImsi = null;
                        Integer pslClientTransId = null, pslLcsRefNum = null;
                        if (sri != null) {
                            pslMsisdn = sri.getPslMsisdn();
                            pslImsi = sri.getPslImsi();
                            mapLsmHelper.setLcsReferenceNumber(sri.getPslReferenceNumber());
                            pslLcsRefNum = mapLsmHelper.getLcsReferenceNumber();
                            pslClientTransId = sri.getPslLcsReferenceNumber();
                        }
                        mapLsmHelper.handleSriLcsResponseValue(sri, pslMsisdn, pslImsi);
                        mapLsmHelper.handlePslResponseValues(psl);
                        MLPResponseParams mapLsmResponse = new MLPResponseParams();
                        mapLsmResponse.mlpMsisdn = mapLsmHelper.getMsisdn();
                        mapLsmResponse.mlpImsi = mapLsmHelper.getImsi();
                        mapLsmResponse.mlpTransId = sri.getPslLcsReferenceNumber();
                        mapLsmResponse.mlpLcsRefNumber = sri.getPslReferenceNumber();
                        mapLsmResponse.x = mapLsmHelper.getLatitude();
                        mapLsmResponse.y = mapLsmHelper.getLongitude();
                        mapLsmResponse.mlpTypeOfShape = mapLsmHelper.getTypeOfShape();
                        mapLsmResponse.radius = mapLsmHelper.getRadius();
                        mapLsmResponse.mlpUncertaintySemiMajorAxis = mapLsmHelper.getUncertaintySemiMajorAxis();
                        mapLsmResponse.mlpUncertaintySemiMinorAxis = mapLsmHelper.getUncertaintySemiMinorAxis();
                        mapLsmResponse.mlpAngleOfMajorAxis = mapLsmHelper.getAngleOfMajorAxis();
                        mapLsmResponse.mlpOffsetAngle = mapLsmHelper.getOffsetAngle();
                        mapLsmResponse.mlpIncludedAngle = mapLsmHelper.getIncludedAngle();
                        mapLsmResponse.mlpAltitude = mapLsmHelper.getAltitude();
                        mapLsmResponse.mlpPolygon = mapLsmHelper.getPolygon();
                        mapLsmResponse.mlpNumberOfPoints = mapLsmHelper.getNumberOfPoints();
                        mapLsmResponse.mlpMcc = mapLsmHelper.getMcc();
                        mapLsmResponse.mlpMnc = mapLsmHelper.getMnc();
                        mapLsmResponse.mlpLac = mapLsmHelper.getLac();
                        mapLsmResponse.mlpCi = mapLsmHelper.getCi();
                        mapLsmResponse.mlpSac = mapLsmHelper.getSac();
                        mapLsmResponse.mlpEci = null;
                        mapLsmResponse.mlpRac = null;
                        mapLsmResponse.mlpTac = null;
                        mapLsmResponse.mlpNci = null;
                        mapLsmResponse.mlpMmeName = mapLsmHelper.getMmeName();
                        mapLsmResponse.mlpSgsnName = mapLsmHelper.getSgsnName();
                        mapLsmResponse.mlpMscNo = mapLsmHelper.getMscNumber();
                        mapLsmResponse.mlpVlrNo = mapLsmHelper.getVlrNumber();
                        mapLsmResponse.mlpImei = mapLsmHelper.getImei();
                        mapLsmResponse.mlpLmsi = mapLsmHelper.getLmsi();
                        mapLsmResponse.mlpAge = mapLsmHelper.getAgeOfLocationEstimate();
                        mapLsmResponse.mlpTargetHorizontalSpeed = mapLsmHelper.getHorizontalSpeed();
                        mapLsmResponse.mlpTargetVerticalSpeed = mapLsmHelper.getVerticalSpeed();
                        mapLsmResponse.mlpVelocityType = mapLsmHelper.getVelocityType();
                        mapLsmResponse.mlpTransId = pslClientTransId;
                        mapLsmResponse.mlpLcsRefNumber = pslLcsRefNum;
                        mapLsmResponse.mlpRatType = null;

                        svcResultXml = mlpResponse.getCoreNetworkSinglePositionXML("PSL", mapLsmResponse.mlpTypeOfShape,
                            mapLsmResponse.x, mapLsmResponse.y, mapLsmResponse.radius, mapLsmResponse.mlpUncertaintySemiMajorAxis,
                            mapLsmResponse.mlpUncertaintySemiMinorAxis, mapLsmResponse.mlpAngleOfMajorAxis, mapLsmResponse.mlpOffsetAngle,
                            mapLsmResponse.mlpIncludedAngle, mapLsmResponse.mlpAltitude, mapLsmResponse.mlpPolygon, mapLsmResponse.mlpNumberOfPoints,
                            mapLsmResponse.mlpMcc, mapLsmResponse.mlpMnc, mapLsmResponse.mlpLac, mapLsmResponse.mlpCi, mapLsmResponse.mlpSac,
                            mapLsmResponse.mlpEci, mapLsmResponse.mlpRac, mapLsmResponse.mlpTac, mapLsmResponse.mlpNci, mapLsmResponse.mlpMmeName,
                            mapLsmResponse.mlpSgsnName, mapLsmResponse.mlpMscNo, mapLsmResponse.mlpVlrNo, mapLsmResponse.mlpMsisdn,
                            mapLsmResponse.mlpImei, mapLsmResponse.mlpImsi, mapLsmResponse.mlpAge,
                            mapLsmResponse.mlpLmsi, mapLsmResponse.mlpTransId, mapLsmResponse.mlpLcsRefNumber, mapLsmResponse.mlpRatType,
                            mlpResultType, mlpTriggeredReportingService, false);

                    } else if (MLPResponse.isSystemError(mlpResultType)) {
                        svcResultXml = mlpResponse.getSystemErrorResponseXML(mlpResultType, mlpClientErrorMessage, mlpTriggeredReportingService);
                    } else {
                        String vlrNumber = null, mscNumber = null;
                        if (sri.getNetworkNodeNumber() != null)
                            mscNumber = vlrNumber = sri.getNetworkNodeNumber().getAddress();
                        if (psl.getServingNodeAddress() != null) {
                            if (psl.getServingNodeAddress().getMscNumber() != null)
                                mscNumber = vlrNumber = psl.getServingNodeAddress().getMscNumber().getAddress();
                        }
                        svcResultXml = mlpResponse.getPositionErrorResponseXML(sri.getPslMsisdn(), sri.getPslImsi(), mscNumber, vlrNumber, mlpResultType, mlpClientErrorMessage, mlpTriggeredReportingService);
                    }

                    this.sendHTTPResult(HttpServletResponse.SC_OK, svcResultXml);
                    break;
            }
        } catch (Exception e) {
            logger.severe("Exception on handleLsmLocationResponse : ", e);
        }
    }

    /**
     * Handle generating the appropriate HTTP response
     * We're making use of the MLPResponse class for both GET/POST requests for convenience and
     * because eventually the GET method will likely be removed
     *
     * @param mlpResultType         OK or error type to return to client
     * @param ria                   SRIForLCS response on location attempt
     * @param pla                   PSL response on location attempt
     * @param lrr                   SLR request report after location attempt
     * @param mlpClientErrorMessage Error message to send to the client
     */
    protected void handleLTELocationServicesResponse(MLPResponse.MLPResultType mlpResultType, SLhRirAvpValues rir, SLhRiaAvpValues ria,
                                                     SLgPlaAvpValues pla, SLgLrrAvpValues lrr, String mlpClientErrorMessage,
                                                     Boolean mlpTriggeredReportingService) {

        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();

            switch (request.type) {
                case REST:
                    if (mlpResultType != null && httpEventContext != null) {
                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                        String jsonResponse = "";
                        try {
                            jsonResponse = PlrResponseJsonBuilder.buildJsonResponseForPlr(ria, pla, rir.plrMsisdn, rir.plrUserName, rir.plrLcsReferenceNumber, ria.lteLcsReferenceNumber, mlpClientErrorMessage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.sendHTTPResult(httpServletResponse.SC_OK, jsonResponse);
                        break;

                    } else {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mlpClientErrorMessage);
                    }
                    break;

                case MLP:
                    String svcResultXml;
                    MLPResponse mlpResponse = new MLPResponse(this.logger);

                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        DiameterLcsResponseHelperForMLP slgLcsHelper = new DiameterLcsResponseHelperForMLP();
                        String plrMsisdn = null, plrImsi = null;
                        Integer plrClientTransId = null, plrLcsRefNum = null;
                        if (rir != null) {
                            plrMsisdn = rir.plrMsisdn;
                            plrImsi = rir.plrUserName;
                            slgLcsHelper.setLcsReferenceNumber(ria.lteLcsReferenceNumber);
                            plrLcsRefNum = slgLcsHelper.getLcsReferenceNumber();
                            plrClientTransId = rir.plrLcsReferenceNumber;
                        }
                        slgLcsHelper.handleRirAnswerValues(ria, plrMsisdn, plrImsi);
                        slgLcsHelper.handlePlrAnswerValues(pla);
                        MLPResponseParams slgLcsResponse = new MLPResponseParams();
                        slgLcsResponse.mlpMsisdn = slgLcsHelper.getMsisdn();
                        slgLcsResponse.mlpImsi = slgLcsHelper.getImsi();
                        slgLcsResponse.x = slgLcsHelper.getLatitude();
                        slgLcsResponse.y = slgLcsHelper.getLongitude();
                        slgLcsResponse.mlpTypeOfShape = slgLcsHelper.getTypeOfShape();
                        slgLcsResponse.radius = slgLcsHelper.getRadius();
                        slgLcsResponse.mlpUncertaintySemiMajorAxis = slgLcsHelper.getUncertaintySemiMajorAxis();
                        slgLcsResponse.mlpUncertaintySemiMinorAxis = slgLcsHelper.getUncertaintySemiMinorAxis();
                        slgLcsResponse.mlpAngleOfMajorAxis = slgLcsHelper.getAngleOfMajorAxis();
                        slgLcsResponse.mlpOffsetAngle = slgLcsHelper.getOffsetAngle();
                        slgLcsResponse.mlpIncludedAngle = slgLcsHelper.getIncludedAngle();
                        slgLcsResponse.mlpAltitude = slgLcsHelper.getAltitude();
                        slgLcsResponse.mlpPolygon = slgLcsHelper.getPolygon();
                        slgLcsResponse.mlpNumberOfPoints = slgLcsHelper.getNumberOfPoints();
                        slgLcsResponse.mlpMcc = slgLcsHelper.getMcc();
                        slgLcsResponse.mlpMnc = slgLcsHelper.getMnc();
                        slgLcsResponse.mlpLac = slgLcsHelper.getLac();
                        slgLcsResponse.mlpCi = slgLcsHelper.getCi();
                        slgLcsResponse.mlpSac = slgLcsHelper.getSac();
                        slgLcsResponse.mlpEci = slgLcsHelper.getEci();
                        slgLcsResponse.mlpRac = null;
                        slgLcsResponse.mlpTac = null;
                        slgLcsResponse.mlpNci = null;
                        slgLcsResponse.mlpMmeName = slgLcsHelper.getMmeName();
                        slgLcsResponse.mlpSgsnName = slgLcsHelper.getSgsnName();
                        slgLcsResponse.mlpMscNo = slgLcsHelper.getMscNumber();
                        slgLcsResponse.mlpVlrNo = slgLcsHelper.getVlrNumber();
                        slgLcsResponse.mlpImei = slgLcsHelper.getImei();
                        slgLcsResponse.mlpLmsi = slgLcsHelper.getLmsi();
                        slgLcsResponse.mlpAge = (int) (long) slgLcsHelper.getAgeOfLocationEstimate();
                        slgLcsResponse.mlpTargetHorizontalSpeed = slgLcsHelper.getHorizontalSpeed();
                        slgLcsResponse.mlpTargetVerticalSpeed = slgLcsHelper.getVerticalSpeed();
                        slgLcsResponse.mlpVelocityType = slgLcsHelper.getVelocityType();
                        slgLcsResponse.mlpCivicAddress = slgLcsHelper.getCivicAddress();
                        slgLcsResponse.mlpBarometricPressure = slgLcsHelper.getBarometricPressure();
                        slgLcsResponse.mlpTransId = plrClientTransId;
                        slgLcsResponse.mlpLcsRefNumber = plrLcsRefNum;
                        slgLcsResponse.mlpRatType = null;

                        svcResultXml = mlpResponse.getCoreNetworkSinglePositionXML("PLR", slgLcsResponse.mlpTypeOfShape,
                            slgLcsResponse.x, slgLcsResponse.y, slgLcsResponse.radius, slgLcsResponse.mlpUncertaintySemiMajorAxis,
                            slgLcsResponse.mlpUncertaintySemiMinorAxis, slgLcsResponse.mlpAngleOfMajorAxis, slgLcsResponse.mlpOffsetAngle,
                            slgLcsResponse.mlpIncludedAngle, slgLcsResponse.mlpAltitude, slgLcsResponse.mlpPolygon, slgLcsResponse.mlpNumberOfPoints,
                            slgLcsResponse.mlpMcc, slgLcsResponse.mlpMnc, slgLcsResponse.mlpLac, slgLcsResponse.mlpCi, slgLcsResponse.mlpSac,
                            slgLcsResponse.mlpEci, slgLcsResponse.mlpRac, slgLcsResponse.mlpTac, slgLcsResponse.mlpNci, slgLcsResponse.mlpMmeName, slgLcsResponse.mlpSgsnName,
                            slgLcsResponse.mlpMscNo, slgLcsResponse.mlpVlrNo, slgLcsResponse.mlpMsisdn, slgLcsResponse.mlpImei, slgLcsResponse.mlpImsi,
                            slgLcsResponse.mlpAge, slgLcsResponse.mlpLmsi, slgLcsResponse.mlpTransId, slgLcsResponse.mlpLcsRefNumber,
                            slgLcsResponse.mlpRatType, mlpResultType, mlpTriggeredReportingService, false);

                    } else if (MLPResponse.isSystemError(mlpResultType)) {
                        svcResultXml = mlpResponse.getSystemErrorResponseXML(mlpResultType, mlpClientErrorMessage, mlpTriggeredReportingService);
                    } else {
                        String vlrNumber = null, mscNumber = null;
                        if (ria.getMscNumber() != null) {
                            mscNumber = vlrNumber = AVPHandler.byte2IsdnAddressString(ria.getMscNumber()).getAddress();
                        }
                        if (pla.getMscNumber() != null) {
                            mscNumber = vlrNumber = AVPHandler.byte2IsdnAddressString(pla.getMscNumber()).getAddress();
                        }
                        svcResultXml = mlpResponse.getPositionErrorResponseXML(rir.plrMsisdn, rir.plrUserName, mscNumber, vlrNumber, mlpResultType, mlpClientErrorMessage, mlpTriggeredReportingService);
                    }

                    this.sendHTTPResult(HttpServletResponse.SC_OK, svcResultXml);
                    break;
            }

        } catch (Exception e) {
            logger.severe("Exception on handleLTELocationServicesResponse: ", e);
        }
    }

    /**
     * Handle generating the appropriate HTTP response
     * We're making use of the MLPResponse class for both GET/POST requests for convenience and
     * because eventually the GET method will likely be removed
     *
     * @param mlpResultType         OK or error type to return to client
     * @param uda                   UDA parameters on Sh User-Data-Request attempt
     * @param mlpClientErrorMessage Error message to send to the client
     */
    protected void handleShUserDataRequestResponse(MLPResponse.MLPResultType mlpResultType, ShUdaAvpValues uda, String mlpClientErrorMessage) {

        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();

            switch (request.type) {
                case REST:
                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                        String jsonResponse = "";
                        try {
                            jsonResponse = UdrResponseJsonBuiler.buildJsonResponseForUdr(uda);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.sendHTTPResult(httpServletResponse.SC_OK, jsonResponse);
                        break;

                    } else {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mlpClientErrorMessage);
                    }
                    break;

                case MLP:
                    String svcResultXml;
                    MLPResponse mlpResponse = new MLPResponse(this.logger);
                    DiameterShUdrResponseHelperForMLP shUdrHelper = new DiameterShUdrResponseHelperForMLP();
                    shUdrHelper.handleUdrAnswerValues(uda);

                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        MLPResponseParams udrResponse = new MLPResponseParams();
                        udrResponse.mlpTypeOfShape = shUdrHelper.getTypeOfShape();
                        udrResponse.x = shUdrHelper.getLatitude();
                        udrResponse.y = shUdrHelper.getLongitude();
                        udrResponse.radius = shUdrHelper.getRadius();
                        udrResponse.mlpMcc = shUdrHelper.getMcc();
                        udrResponse.mlpMnc = shUdrHelper.getMnc();
                        udrResponse.mlpLac = shUdrHelper.getLac();
                        udrResponse.mlpCi = shUdrHelper.getCi();
                        udrResponse.mlpSac = shUdrHelper.getSac();
                        udrResponse.mlpEci = shUdrHelper.getEci();
                        udrResponse.mlpRac = shUdrHelper.getRac();
                        udrResponse.mlpTac = shUdrHelper.getTac();
                        udrResponse.mlpNci = shUdrHelper.getNci();
                        udrResponse.mlpMmeName = shUdrHelper.getMmeName();
                        udrResponse.mlpSgsnName = null;
                        udrResponse.mlpMscNo = shUdrHelper.getMscNumber();
                        udrResponse.mlpVlrNo = shUdrHelper.getVlrNumber();
                        udrResponse.mlpMsisdn = shUdrHelper.getMsisdn();
                        udrResponse.mlpImei = shUdrHelper.getImei();
                        udrResponse.mlpImsi = null;
                        udrResponse.mlpAge = shUdrHelper.getAgeOfLocationInfo();
                        udrResponse.mlpLmsi = null;
                        udrResponse.mlpRatType = shUdrHelper.getRatType();

                        svcResultXml = mlpResponse.getCoreNetworkSinglePositionXML("UDR", udrResponse.mlpTypeOfShape, udrResponse.x, udrResponse.y,
                            udrResponse.radius, null, null, null, null, null, null, null, null,
                            udrResponse.mlpMcc, udrResponse.mlpMnc, udrResponse.mlpLac, udrResponse.mlpCi, udrResponse.mlpSac,
                            udrResponse.mlpEci, udrResponse.mlpRac, udrResponse.mlpTac, udrResponse.mlpNci, udrResponse.mlpMmeName, udrResponse.mlpSgsnName,
                            udrResponse.mlpMscNo, udrResponse.mlpVlrNo, udrResponse.mlpMsisdn, udrResponse.mlpImei, udrResponse.mlpImsi,
                            udrResponse.mlpAge, udrResponse.mlpLmsi, null, null, udrResponse.mlpRatType,
                            mlpResultType, false, false);

                    } else if (MLPResponse.isSystemError(mlpResultType)) {
                        svcResultXml = mlpResponse.getSystemErrorResponseXML(mlpResultType, mlpClientErrorMessage, false);
                    } else {
                        String msisdn = uda.getMsisdn();
                        String vlrNumber = null, mscNumber = null;
                        if (uda.getMscNumber() != null)
                            mscNumber = uda.getMscNumber().getAddress();
                        if (uda.getVlrNumber() != null)
                            vlrNumber = uda.getVlrNumber().getAddress();
                        svcResultXml = mlpResponse.getPositionErrorResponseXML(msisdn, null, mscNumber, vlrNumber, mlpResultType, mlpClientErrorMessage, false);
                    }

                    this.sendHTTPResult(HttpServletResponse.SC_OK, svcResultXml);
                    break;
            }

        } catch (Exception e) {
            logger.severe("Exception on handleShUserDataRequestResponse: ", e);
        }
    }

    /**
     * Handle generating the appropriate HTTP response
     * We're making use of the MLPResponse class for both GET/POST requests for convenience and
     * because eventually the GET method will likely be removed
     *
     * @param mlpResultType           OK or error type to return to client
     * @param sriResponseValues       SRI response values
     * @param sriForSmResponseValues  SRISM response values
     * @param sriForLcsResponseValues SRILCS response values
     * @param operation               MAP operation involved (SRI, SRISM or SRILCS)
     * @param msisdn                  MSISDN used on the operation attempt
     * @param imsi                    IMSI used on the operation attempt
     * @param mlpClientErrorMessage   Error message to send to the client
     */
    protected void handleSriResponseValue(MLPResponse.MLPResultType mlpResultType, SriResponseValues sriResponseValues,
                                          SriForSmResponseValues sriForSmResponseValues, SriForLcsResponseValues sriForLcsResponseValues,
                                          String operation, String msisdn, String imsi, String mlpClientErrorMessage) {

        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();

            switch (request.type) {
                case REST:
                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                        String jsonResponse = "";
                        try {
                            if (operation.equalsIgnoreCase("SRI"))
                                jsonResponse = SriResponseJsonBuilder.buildJsonResponseForSri(imsi, msisdn, "SRI", sriResponseValues, null, null);
                            if (operation.equalsIgnoreCase("SRISM"))
                                jsonResponse = SriResponseJsonBuilder.buildJsonResponseForSri(imsi, msisdn, "SRISM", null, sriForSmResponseValues, null);
                            if (operation.equalsIgnoreCase("SRILCS"))
                                jsonResponse = SriResponseJsonBuilder.buildJsonResponseForSri(imsi, msisdn, "SRILCS", null, null, sriForLcsResponseValues);
                        } catch (MAPException me) {
                            this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "{\"error\": \"Fail MAP params\"}");
                            me.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.sendHTTPResult(httpServletResponse.SC_OK, jsonResponse);
                        break;
                    } else {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mlpClientErrorMessage);
                    }
                    break;
            }
        } catch (Exception e) {
            logger.severe("Exception on handleSriResponseValue: ", e);
        }
    }

    /**
     * Handle generating the appropriate HTTP response
     * We're making use of the MLPResponse class for both GET/POST requests for convenience and
     * because eventually the GET method will likely be removed
     *
     * @param mlpResultType         OK or error type to return to client
     * @param psiResponseValues     PSI parameters on PSI attempt
     * @param mlpClientErrorMessage Error message to send to the client
     */
    protected void handlePsiResponse(MLPResponse.MLPResultType mlpResultType, PsiResponseValues psiResponseValues,
                                     String psiMsisdn, String mlpClientErrorMessage) {

        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();

            String imsi = "";

            switch (request.type) {
                case REST:
                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                        if (psiResponseValues != null) {
                            if (psiResponseValues.getPsiOnlyImsi() != null && psiResponseValues.getPsiOnlyNnn() != null) {
                                if (psiResponseValues.getPsiServiceType() != null) {
                                    if (psiResponseValues.getPsiServiceType().equalsIgnoreCase("psiFirst"))
                                        imsi = psiResponseValues.getPsiOnlyImsi();
                                }
                                if (logger.isFineEnabled()) {
                                    logger.fine(String.format("psiOnlyImsi global value '%s' fixed to '%s' obtained from Transaction.", psiResponseValues.getPsiOnlyImsi(), imsi));
                                }
                            } else {
                                imsi = psiResponseValues.getSriForSMImsi();
                                if (logger.isFineEnabled()) {
                                    logger.fine(String.format("sriForSMImsi global value '%s' fixed to '%s' obtained from Transaction.", psiResponseValues.getSriForSMImsi(), imsi));
                                }
                            }
                        }

                        String jsonResponse = "";
                        try {
                            if (psiResponseValues.getPsiOnlyImsi() != null && psiResponseValues.getPsiOnlyNnn() != null &&
                                psiResponseValues.getPsiServiceType() != null && psiResponseValues.getPsiServiceType().equalsIgnoreCase("psiFirst")) {
                                jsonResponse = PsiResponseJsonBuilder.buildJsonResponseForPsi(psiResponseValues, imsi, null, null, psiMsisdn);
                            } else if (psiResponseValues.getSriForSmResponse() != null) {
                                SriForSmResponseValues sriForSmResponse = psiResponseValues.getSriForSmResponse();
                                jsonResponse = PsiResponseJsonBuilder.buildJsonResponseForPsi(psiResponseValues, imsi, sriForSmResponse, null, psiMsisdn);
                            } else if (psiResponseValues.getSriResponse() != null) {
                                SriResponseValues sriResponse = psiResponseValues.getSriResponse();
                                jsonResponse = PsiResponseJsonBuilder.buildJsonResponseForPsi(psiResponseValues, imsi, null, sriResponse, psiMsisdn);
                            }
                        } catch (MAPException me) {
                            this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "{\"error\": \"Fail MAP params\"}");
                            me.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.sendHTTPResult(httpServletResponse.SC_OK, jsonResponse);
                        break;

                    } else {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mlpClientErrorMessage);
                    }
                    break;

                case MLP:
                    String svcResultXml;
                    MLPResponse mlpResponse = new MLPResponse(this.logger);
                    MapSriPsiResponseHelperForMLP psiHelper = new MapSriPsiResponseHelperForMLP();
                    psiHelper.handlePsiResponseValues(psiResponseValues);
                    try {
                        if (psiResponseValues.getPsiOnlyImsi() != null) {
                            imsi = psiResponseValues.getPsiOnlyImsi();
                        } else if (psiResponseValues.getSriForSmResponse() != null) {
                            SriForSmResponseValues sriForSmResponse = psiResponseValues.getSriForSmResponse();
                            if (sriForSmResponse.getImsi() != null)
                                imsi = sriForSmResponse.getImsi().getData();
                        } else if (psiResponseValues.getSriResponse() != null) {
                            SriResponseValues sriResponse = psiResponseValues.getSriResponse();
                            if (sriResponse.getImsi() != null)
                                imsi = sriResponse.getImsi().getData();
                        }
                    } catch (Exception e) {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mlpClientErrorMessage);
                        e.printStackTrace();
                    }

                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        MLPResponseParams psiResponse = new MLPResponseParams();
                        psiResponse.mlpTypeOfShape = psiHelper.getTypeOfShape();
                        psiResponse.x = psiHelper.getLatitude();
                        psiResponse.y = psiHelper.getLongitude();
                        psiResponse.radius = psiHelper.getRadius();
                        psiResponse.mlpMcc = psiHelper.getMcc();
                        psiResponse.mlpMnc = psiHelper.getMnc();
                        psiResponse.mlpLac = psiHelper.getLac();
                        psiResponse.mlpCi = psiHelper.getCi();
                        psiResponse.mlpSac = psiHelper.getSac();
                        psiResponse.mlpEci = psiHelper.getEci();
                        psiResponse.mlpRac = psiHelper.getRac();
                        psiResponse.mlpTac = psiHelper.getTac();
                        psiResponse.mlpNci = null;
                        psiResponse.mlpMmeName = psiHelper.getMmeName();
                        psiResponse.mlpSgsnName = null;
                        psiResponse.mlpMscNo = psiHelper.getMscNumber();
                        psiResponse.mlpVlrNo = psiHelper.getVlrNumber();
                        psiResponse.mlpMsisdn = psiMsisdn;
                        psiResponse.mlpImei = psiHelper.getImei();
                        psiResponse.mlpImsi = imsi;
                        psiResponse.mlpAge = psiHelper.getAgeOfLocationInfo();
                        psiResponse.mlpLmsi = null;
                        psiResponse.mlpRatType = null;

                        svcResultXml = mlpResponse.getCoreNetworkSinglePositionXML("PSI", psiResponse.mlpTypeOfShape, psiResponse.x, psiResponse.y,
                            psiResponse.radius, null, null, null, null, null, null, null, null,
                            psiResponse.mlpMcc, psiResponse.mlpMnc, psiResponse.mlpLac, psiResponse.mlpCi, psiResponse.mlpSac,
                            psiResponse.mlpEci, psiResponse.mlpRac, psiResponse.mlpTac, psiResponse.mlpNci, psiResponse.mlpMmeName, psiResponse.mlpSgsnName,
                            psiResponse.mlpMscNo, psiResponse.mlpVlrNo, psiResponse.mlpMsisdn, psiResponse.mlpImei, psiResponse.mlpImsi,
                            psiResponse.mlpAge, psiResponse.mlpLmsi, null, null, psiResponse.mlpRatType, mlpResultType,
                            false, false);

                    } else if (MLPResponse.isSystemError(mlpResultType)) {
                        svcResultXml = mlpResponse.getSystemErrorResponseXML(mlpResultType, mlpClientErrorMessage, false);
                    } else {
                        String vlrNumber = null, mscNumber = null;
                        if (psiResponseValues.getMscNumber() != null)
                            mscNumber = psiResponseValues.getMscNumber().getAddress();
                        if (psiResponseValues.getVlrNumber() != null)
                            vlrNumber = psiResponseValues.getVlrNumber().getAddress();
                        svcResultXml = mlpResponse.getPositionErrorResponseXML(psiMsisdn, imsi, mscNumber, vlrNumber, mlpResultType, mlpClientErrorMessage, false);
                    }

                    this.sendHTTPResult(HttpServletResponse.SC_OK, svcResultXml);
                    break;
            }
        } catch (Exception e) {
            logger.severe("Exception on handlePsiResponse: ", e);
        }
    }

    /**
     * Handle generating the appropriate HTTP response out of the SUPL session
     *
     * @param mlpResultType            OK or error type to return to client
     * @param mlpClientErrorMessage    Error message to send to the client
     * @param msisdn                   MSISDN of the SET
     * @param imsi                     IMSI of the SET
     * @param transId                  transaction Id of the location attempt sent by the SLP Client
     * @param suplResponseHelperForMLP
     */
    protected void handleSUPLResponse(MLPResponse.MLPResultType mlpResultType, String mlpClientErrorMessage, String msisdn,
                                      String imsi, Integer transId, SuplResponseHelperForMLP suplResponseHelperForMLP,
                                      Boolean mlpTriggeredReportingService, Boolean isReport) {

        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();

            switch (request.type) {
                case REST:
                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                        String jsonResponse = "";
                        try {
                            logger.info("On handleSUPLResponse, REST not implemented yet");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.sendHTTPResult(httpServletResponse.SC_OK, jsonResponse);
                        break;

                    } else {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mlpClientErrorMessage);
                    }
                    break;

                case MLP:
                    String svcResultXml;
                    MLPResponse mlpResponse = new MLPResponse(this.logger);

                    if (mlpResultType == MLPResponse.MLPResultType.OK && httpEventContext != null) {
                        MLPResponseParams suplResponse = new MLPResponseParams();
                        suplResponse.mlpMsisdn = msisdn;
                        suplResponse.mlpImsi = imsi;
                        suplResponse.x = suplResponseHelperForMLP.getLatitude();
                        suplResponse.y = suplResponseHelperForMLP.getLongitude();
                        if (suplResponseHelperForMLP.getTypeOfShape() != null)
                            suplResponse.mlpTypeOfShape = suplResponseHelperForMLP.getTypeOfShape().name();
                        suplResponse.radius = suplResponseHelperForMLP.getRadius();
                        suplResponse.mlpUncertaintySemiMajorAxis = suplResponseHelperForMLP.getUncertaintySemiMajorAxis();
                        suplResponse.mlpUncertaintySemiMinorAxis = suplResponseHelperForMLP.getUncertaintySemiMinorAxis();
                        suplResponse.mlpAngleOfMajorAxis = suplResponseHelperForMLP.getOrientationMajorAxis();
                        suplResponse.mlpOffsetAngle = suplResponseHelperForMLP.getOffsetAngle();
                        suplResponse.mlpIncludedAngle = suplResponseHelperForMLP.getIncludedAngle();
                        suplResponse.mlpAltitude = suplResponseHelperForMLP.getAltitude();
                        suplResponse.mlpPolygon = null;
                        suplResponse.mlpNumberOfPoints = null;
                        suplResponse.mlpMcc = suplResponseHelperForMLP.getMcc();
                        suplResponse.mlpMnc = suplResponseHelperForMLP.getMnc();
                        suplResponse.mlpLac = suplResponseHelperForMLP.getLac();
                        suplResponse.mlpCi = suplResponseHelperForMLP.getCi();
                        suplResponse.mlpSac = suplResponseHelperForMLP.getSac();
                        suplResponse.mlpEci = suplResponseHelperForMLP.getEci();
                        suplResponse.mlpRac = suplResponseHelperForMLP.getRac();
                        suplResponse.mlpTac = suplResponseHelperForMLP.getTac();
                        suplResponse.mlpNci = suplResponseHelperForMLP.getNrCi();
                        suplResponse.mlpMmeName = null;
                        suplResponse.mlpSgsnName = null;
                        suplResponse.mlpMscNo = null;
                        suplResponse.mlpVlrNo = null;
                        suplResponse.mlpImei = null;
                        suplResponse.mlpLmsi = null;
                        suplResponse.mlpAge = null;
                        suplResponse.mlpTargetHorizontalSpeed = null;
                        suplResponse.mlpTargetVerticalSpeed = null;
                        suplResponse.mlpVelocityType = null;
                        suplResponse.mlpCivicAddress = null;
                        suplResponse.mlpBarometricPressure = null;
                        suplResponse.mlpTransId = transId;
                        if (suplResponseHelperForMLP.getTriggerParams() != null)
                            suplResponse.mlpLcsRefNumber = transId;
                        else
                            suplResponse.mlpLcsRefNumber = null;
                        suplResponse.mlpRatType = null;

                        svcResultXml = mlpResponse.getCoreNetworkSinglePositionXML("SUPL", suplResponse.mlpTypeOfShape,
                            suplResponse.x, suplResponse.y, suplResponse.radius, suplResponse.mlpUncertaintySemiMajorAxis,
                            suplResponse.mlpUncertaintySemiMinorAxis, suplResponse.mlpAngleOfMajorAxis, suplResponse.mlpOffsetAngle,
                            suplResponse.mlpIncludedAngle, suplResponse.mlpAltitude, suplResponse.mlpPolygon, suplResponse.mlpNumberOfPoints,
                            suplResponse.mlpMcc, suplResponse.mlpMnc, suplResponse.mlpLac, suplResponse.mlpCi, suplResponse.mlpSac,
                            suplResponse.mlpEci, suplResponse.mlpRac, suplResponse.mlpTac, suplResponse.mlpNci, suplResponse.mlpMmeName, suplResponse.mlpSgsnName,
                            suplResponse.mlpMscNo, suplResponse.mlpVlrNo, suplResponse.mlpMsisdn, suplResponse.mlpImei, suplResponse.mlpImsi,
                            suplResponse.mlpAge, suplResponse.mlpLmsi, suplResponse.mlpTransId, suplResponse.mlpLcsRefNumber,
                            suplResponse.mlpRatType, mlpResultType, mlpTriggeredReportingService, isReport);

                    } else if (MLPResponse.isSystemError(mlpResultType)) {
                        svcResultXml = mlpResponse.getSystemErrorResponseXML(mlpResultType, mlpClientErrorMessage, mlpTriggeredReportingService);
                    } else {
                        svcResultXml = mlpResponse.getPositionErrorResponseXML(msisdn, imsi, null, null, mlpResultType, mlpClientErrorMessage, mlpTriggeredReportingService);
                    }

                    this.sendHTTPResult(HttpServletResponse.SC_OK, svcResultXml);
                    break;
            }
        } catch (Exception e) {
            logger.severe("Exception on handleSUPLResponse: ", e);
        }
    }

    /**
     * Handle generating the appropriate HTTP response
     * We're making use of the MLPResponse class for both GET/POST requests for convenience and
     * because eventually the GET method will likely be removed
     *
     * @param mlpResultType                OK or error type to return to the GMLC client
     * @param dialogErrorMessage           Error message to send to the GMLC client
     * @param operation                    Location operation that caused the error to report to the GMLC client
     * @param targetMs                     MSISDN of the target subscriber
     * @param targetImsi                   IMSI of the target subscriber
     * @param refNumber                    Location Services reference number of the operation that caused the error to report to the GMLC client
     * @param networkNodeNumber            Network Node Number to where the failed operation was conveyed
     * @param diameterHost                 Diameter Host name to where the failed operation was conveyed
     * @param diameterRealm                Diameter Realm name to where the failed operation was conveyed
     * @param mlpTriggeredReportingService
     */
    protected void reportLocationRequestError(MLPResponse.MLPResultType mlpResultType, String dialogErrorMessage,
                                              String operation, String targetMs, String targetImsi, Integer refNumber,
                                              String networkNodeNumber, String addNetworkNodeNumber, String diameterHost, String diameterRealm,
                                              Boolean mlpTriggeredReportingService) {
        try {
            HttpRequest request = getHttpRequest();
            EventContext httpEventContext = this.resumeHttpEventContext();
            String jsonResponse = "";

            HttpRequestType httpRequestType;
            if (request == null) {
                httpRequestType = HttpRequestType.MLP;
            } else {
                httpRequestType = request.type;
            }

            switch (httpRequestType) {
                case REST:
                    if (httpEventContext != null) {
                        HttpServletRequestEvent httpRequest = (HttpServletRequestEvent) httpEventContext.getEvent();
                        HttpServletResponse httpServletResponse = httpRequest.getResponse();
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                        jsonResponse = OnErrorResponseJsonBuilder.buildJsonResponseOnError(targetMs, targetImsi, operation, dialogErrorMessage, refNumber,
                            networkNodeNumber, addNetworkNodeNumber, diameterHost, diameterRealm);
                        this.sendHTTPResult(setHttpServletResponseStatusCode(mlpResultType), jsonResponse);
                        break;
                    } else {
                        this.sendHTTPResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
                        break;
                    }

                case MLP:
                    String svcResultXml;
                    MLPResponse mlpResponse = new MLPResponse(this.logger);
                    svcResultXml = mlpResponse.getPositionErrorResponseXML(targetMs, targetImsi, networkNodeNumber, networkNodeNumber, mlpResultType, dialogErrorMessage, mlpTriggeredReportingService);
                    this.sendHTTPResult(setHttpServletResponseStatusCode(mlpResultType), svcResultXml);
                    break;
            }
        } catch (Exception e) {
            logger.severe("Exception on reportLocationRequestError: ", e);
        }
    }

    /**
     * Return the specified response data to the HTTP client
     *
     * @param responseData Response data to send to client
     */
    protected void sendHTTPResult(int statusCode, String responseData) {
        try {
            EventContext ctx = this.getEventContext();
            if (ctx == null) {
                if (logger.isFineEnabled()) {
                    logger.warning("When responding to HTTP no pending HTTP request is found, responseData=" + responseData);
                    return;
                }
            }

            HttpServletRequestEvent event = (HttpServletRequestEvent) ctx.getEvent();

            HttpServletResponse response = event.getResponse();
            response.setStatus(statusCode);
            PrintWriter w;
            w = response.getWriter();
            w.print(responseData);
            w.flush();
            response.flushBuffer();

            if (ctx.isSuspended()) {
                ctx.resumeDelivery();
            }

            if (logger.isFineEnabled()) {
                logger.fine("HTTP Request received and response sent, responseData=" + responseData);
            }

            // getNullActivity().endActivity();
        } catch (Exception e) {
            logger.severe("Error while sending back HTTP response", e);
        }
    }

    /**
     * Resume the HTTP Event Context
     */
    private EventContext resumeHttpEventContext() {
        EventContext httpEventContext = getEventContextCMP();

        if (httpEventContext == null) {
            logger.severe("No HTTP event context, can not resume ");
            return null;
        }

        httpEventContext.resumeDelivery();
        return httpEventContext;
    }

    private HttpReport getHttpSubscriberLocationReport() {
        String mongoHost = gmlcPropertiesManagement.getMongoHost();
        if (mongoHost != null && mongoHost.length() > 0) {
            Integer mongoPort = gmlcPropertiesManagement.getMongoPort();
            String mongoDatabase = gmlcPropertiesManagement.getMongoDatabase();
            httpSubscriberLocationReport = new HttpReport(mongoHost, mongoPort, mongoDatabase);
        } else {
            httpSubscriberLocationReport = new HttpReport();
        }
        return httpSubscriberLocationReport;
    }

    // //////////////////
    // SBB LO methods //
    // ////////////////

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.cdr.CDRInterfaceParent#
     * recordGenerationSucceeded(org.mobicents.gmlc.slee.cdr.CDRInterfaceParent.RecordType)
     */
    @Override
    public void recordGenerationSucceeded() {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("Generated CDR for Status: " + getCDRInterface().getState());
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.cdr.CDRInterfaceParent#
     * recordGenerationFailed(java.lang.String)
     */
    @Override
    public void recordGenerationFailed(String message) {
        if (this.logger.isSevereEnabled()) {
            this.logger.severe("Failed to generate CDR! Message: '" + message + "'");
            this.logger.severe("Status: " + getCDRInterface().getState());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.cdr.CDRInterfaceParent#
     * recordGenerationFailed(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void recordGenerationFailed(String message, Throwable t) {
        if (this.logger.isSevereEnabled()) {
            this.logger.severe("Failed to generate CDR! Message: '" + message + "'", t);
            this.logger.severe("Status: " + getCDRInterface().getState());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.mobicents.gmlc.slee.cdr.CDRInterfaceParent#initFailed(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void initFailed(String message, Throwable t) {
        if (this.logger.isSevereEnabled()) {
            this.logger.severe("Failed to initialize CDR Database! Message: '" + message + "'", t);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.mobicents.gmlc.slee.cdr.CDRInterfaceParent#initSuccessed()
     */
    @Override
    public void initSucceeded() {
        if (this.logger.isFineEnabled()) {
            this.logger.fine("CDR Database has been initialized!");
        }

    }

    //////////////////////
    //  CDR interface  //
    ////////////////////

    protected static final String CDR = "CDR";

    public abstract ChildRelationExt getCDRPlainInterfaceChildRelation();

    public CDRInterface getCDRInterface() {
        GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();
        ChildRelationExt childExt;
        if (gmlcPropertiesManagement.getCdrLoggingTo() == GmlcPropertiesManagement.CdrLoggedType.Textfile) {
            childExt = getCDRPlainInterfaceChildRelation();
        } else {
            //childExt = getCDRInterfaceChildRelation();
            childExt = null; // temporary
        }

        CDRInterface child = (CDRInterface) childExt.get(CDR);
        if (child == null) {
            try {
                child = (CDRInterface) childExt.create(CDR);
            } catch (TransactionRequiredLocalException e) {
                logger.severe("TransactionRequiredLocalException when creating CDR child", e);
            } catch (IllegalArgumentException e) {
                logger.severe("IllegalArgumentException when creating CDR child", e);
            } catch (NullPointerException e) {
                logger.severe("NullPointerException when creating CDR child", e);
            } catch (SLEEException e) {
                logger.severe("SLEEException when creating CDR child", e);
            } catch (CreateException e) {
                logger.severe("CreateException when creating CDR child", e);
            }
        }

        return child;
    }

    protected void createCDRRecord(RecordStatus recordStatus) {
        try {
            this.getCDRInterface().createRecord(recordStatus);
        } catch (Exception e) {
            logger.severe("Error while trying to create CDR Record", e);
        }
    }

    // ///////////////////////////////////////////////
    // protected child stuff, to be used in parent //
    // /////////////////////////////////////////////

    protected void detachFromMAPDialogMobility(ActivityContextInterface aci) {
        aci.detach(this.sbbContext.getSbbLocalObject());
        MAPDialogMobility mapDialogMobility = (MAPDialogMobility) aci.getActivity();
        mapDialogMobility.release();
    }

    protected void detachFromMAPDialogSms(ActivityContextInterface aci) {
        aci.detach(this.sbbContext.getSbbLocalObject());
        MAPDialogSms mapDialogSms = (MAPDialogSms) aci.getActivity();
        mapDialogSms.release();
    }

    protected void detachFromMAPDialogCallHandling(ActivityContextInterface aci) {
        aci.detach(this.sbbContext.getSbbLocalObject());
        MAPDialogCallHandling mapDialogCallHandling = (MAPDialogCallHandling) aci.getActivity();
        mapDialogCallHandling.release();
    }

    protected void detachFromMAPDialogLsm(ActivityContextInterface aci) {
        aci.detach(this.sbbContext.getSbbLocalObject());
        MAPDialogLsm mapDialogLsm = (MAPDialogLsm) aci.getActivity();
        mapDialogLsm.release();
    }

    protected void detachFromSLhClientActivity(ActivityContextInterface aci) {
        aci.detach(this.sbbContext.getSbbLocalObject());
        SLhClientSessionActivity slhClientSessionActivity = (SLhClientSessionActivity) aci.getActivity();
        slhClientSessionActivity.endActivity();
    }

    protected void detachFromSLgClientActivity(ActivityContextInterface aci) {
        aci.detach(this.sbbContext.getSbbLocalObject());
        SLgClientSessionActivity slgClientSessionActivity = (SLgClientSessionActivity) aci.getActivity();
        slgClientSessionActivity.endActivity();
    }

    protected void detachFromShClientActivity(ActivityContextInterface aci) {
        aci.detach(this.sbbContext.getSbbLocalObject());
        ShClientActivity slhClientActivity = (ShClientActivity) aci.getActivity();
        slhClientActivity.endActivity();
    }

    protected void cancelTimer() {
        try {
            TimerID timerID = this.getTimerID();
            if (timerID != null) {
                this.timerFacility.cancelTimer(timerID);
            }
        } catch (Exception e) {
            logger.severe("Could not cancel Timer", e);
        }
    }

    protected void setTimer(ActivityContextInterface aci) {
        TimerOptions options = new TimerOptions();
        long waitingTime = gmlcPropertiesManagement.getEventContextSuspendDeliveryTimeout();
        options.setTimeout(waitingTime);
        // Set the timer on ACI
        TimerID timerID = this.timerFacility.setTimer(aci, null, System.currentTimeMillis() + waitingTime, options);
        this.setTimerID(timerID);
    }

    public void onTimerEvent(TimerEvent event, ActivityContextInterface aci) {
        // method for firing eventual location requests/reports on timer events
        if (logger.isFineEnabled()) {
            Long expiryTime = event.getExpiryTime();
            if (aci != null) {
                logger.warning("\nonTimerEvent, expiry time=" + expiryTime + ". ACI=" + aci.hashCode());
            } else {
                logger.warning("\nonTimerEvent, expiry time=" + expiryTime + ". ACI=" + aci);
            }
        }
        if (aci != null)
            aci.detach(this.sbbContext.getSbbLocalObject());
    }

    public Tracer getTracer(){
        return this.logger;
    }
}
