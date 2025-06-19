package org.mobicents.protocols.ss7.gmlc.load;

import org.apache.log4j.Logger;
import org.mobicents.protocols.api.IpChannelType;
import org.mobicents.protocols.sctp.netty.NettySctpManagementImpl;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.m3ua.As;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.AspFactory;
import org.restcomm.protocols.ss7.m3ua.ExchangeType;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.IPSPType;
import org.restcomm.protocols.ss7.m3ua.impl.M3UAManagementImpl;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;
import org.restcomm.protocols.ss7.map.MAPParameterFactoryImpl;
import org.restcomm.protocols.ss7.map.MAPStackImpl;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessage;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.dialog.MAPAbortProviderReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPAbortSource;
import org.restcomm.protocols.ss7.map.api.dialog.MAPNoticeProblemDiagnostic;
import org.restcomm.protocols.ss7.map.api.dialog.MAPRefuseReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ForwardCheckSSIndicationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ResetRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SupportedLCSCapabilitySets;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeRequest_Mobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeResponse_Mobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeodeticInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationEPS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationNumberMap;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MNPInfoRes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NotReachableReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberStateChoice;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UserCSGInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.GSNAddressImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;
import org.restcomm.protocols.ss7.map.service.lsm.AdditionalNumberImpl;
import org.restcomm.protocols.ss7.map.service.lsm.GeranGANSSpositioningDataImpl;
import org.restcomm.protocols.ss7.map.service.lsm.UtranGANSSpositioningDataImpl;
import org.restcomm.protocols.ss7.map.service.lsm.UtranPositioningDataInfoImpl;
import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.SupportedLCSCapabilitySetsImpl;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.Router;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.SccpResource;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.BCDEvenEncodingScheme;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SccpAddressImpl;
import org.restcomm.protocols.ss7.sccp.parameter.EncodingScheme;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.sccpext.router.RouterExt;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;
import org.restcomm.protocols.ss7.tcap.TCAPStackImpl;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;

import java.math.BigInteger;

/**
 *
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 *
 */
public class Server extends TestHarness {

  private static Logger logger = Logger.getLogger(Server.class);

  // MAP
  private MAPStackImpl mapStack;
  private MAPProvider mapProvider;

  // TCAP
  private TCAPStack tcapStack;

  // SCCP
  SccpExtModuleImpl sccpExtModule;
  private SccpStackImpl sccpStack;
  private SccpResource sccpResource;
  private Router router;
  private RouterExt routerExt;

  // M3UA
  private M3UAManagementImpl serverM3UAMgmt;

  // SCTP
  private NettySctpManagementImpl sctpManagement;

  int endCount = 0;
  volatile long start = System.currentTimeMillis();

  protected void initializeStack(IpChannelType ipChannelType) throws Exception {

    this.initSCTP(ipChannelType);

    // Initialize M3UA first
    this.initM3UA();

    // Initialize SCCP
    this.initSCCP();

    // Initialize TCAP
    this.initTCAP();

    // Initialize MAP
    this.initMAP();

    // 7. Start ASP
    serverM3UAMgmt.startAsp("RASP1");
  }

  private void initSCTP(IpChannelType ipChannelType) throws Exception {
    this.sctpManagement = new NettySctpManagementImpl("Server");
    // this.sctpManagement.setSingleThread(false);
    this.sctpManagement.start();
    this.sctpManagement.setConnectDelay(10000);
    this.sctpManagement.removeAllResources();

    // 1. Create SCTP Server
    sctpManagement.addServer(SERVER_NAME, SERVER_IP, SERVER_PORT, ipChannelType, null);

    // 2. Create SCTP Server Association
    sctpManagement.addServerAssociation(CLIENT_IP, CLIENT_PORT, SERVER_NAME, SERVER_ASSOCIATION_NAME, ipChannelType);

    // 3. Start Server
    sctpManagement.startServer(SERVER_NAME);
  }

  private void initM3UA() throws Exception {
    this.serverM3UAMgmt = new M3UAManagementImpl("Server", null,new Ss7ExtInterfaceImpl() );
    this.serverM3UAMgmt.setTransportManagement(this.sctpManagement);
    this.serverM3UAMgmt.setDeliveryMessageThreadCount(DELIVERY_TRANSFER_MESSAGE_THREAD_COUNT);
    this.serverM3UAMgmt.start();
    this.serverM3UAMgmt.removeAllResources();

    // Step 1 : Create App Server
    RoutingContext rc = factory.createRoutingContext(new long[]{100L});
    TrafficModeType trafficModeType = factory.createTrafficModeType(TrafficModeType.Loadshare);
    As as = this.serverM3UAMgmt.createAs("RAS1", Functionality.SGW, ExchangeType.SE, IPSPType.CLIENT, rc, trafficModeType,
        1, null);

    // Step 2 : Create ASP
    AspFactory aspFactor = this.serverM3UAMgmt.createAspFactory("RASP1", SERVER_ASSOCIATION_NAME);

    // Step3 : Assign ASP to AS
    Asp asp = this.serverM3UAMgmt.assignAspToAs("RAS1", "RASP1");

    // Step 4: Add Route. Remote point code is 2
    this.serverM3UAMgmt.addRoute(CLIENT_SPC, -1, -1, "RAS1");
  }

  private void initSCCP() throws Exception {
    Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
    sccpExtModule = new SccpExtModuleImpl();
    ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule);
    this.sccpStack = new SccpStackImpl("MapLoadServerSccpStack", ss7ExtInterface);
    this.sccpStack.setMtp3UserPart(1, this.serverM3UAMgmt);

    this.sccpStack.start();
    this.sccpStack.removeAllResources();

    this.routerExt = sccpExtModule.getRouterExt();

    this.sccpStack.getSccpResource().addRemoteSpc(0, CLIENT_SPC, 0, 0);
    this.sccpStack.getSccpResource().addRemoteSsn(0, CLIENT_SPC, CLIENT_SSN, 0, false);

    this.sccpStack.getRouter().addMtp3ServiceAccessPoint(1, 1, SERVER_SPC, NETWORK_INDICATOR, 0, null);
    this.sccpStack.getRouter().addMtp3Destination(1, 1, CLIENT_SPC, CLIENT_SPC, 0, 255, 255);

    ParameterFactoryImpl fact = new ParameterFactoryImpl();
    EncodingScheme ec = new BCDEvenEncodingScheme();
    GlobalTitle gt1 = fact.createGlobalTitle("-", 0, org.restcomm.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY, ec,
        NatureOfAddress.INTERNATIONAL);
    GlobalTitle gt2 = fact.createGlobalTitle("-", 0, org.restcomm.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY, ec,
        NatureOfAddress.INTERNATIONAL);
    SccpAddress localAddress = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt1, SERVER_SPC, SERVER_SSN);
    this.routerExt.addRoutingAddress(1, localAddress);
    SccpAddress remoteAddress = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt2, CLIENT_SPC, CLIENT_SSN);
    this.routerExt.addRoutingAddress(2, remoteAddress);

    GlobalTitle gt = fact.createGlobalTitle("*", 0, org.restcomm.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY, ec,
        NatureOfAddress.INTERNATIONAL);
    SccpAddress pattern = new SccpAddressImpl(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, 0, 0);
    this.routerExt.addRule(1, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.REMOTE, pattern,
        "K", 1, -1, null, 0, null);
    this.routerExt.addRule(2, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K",
        2, -1, null, 0, null);
    this.sccpStack.getRouter().addLongMessageRule(1, 1, 16384, LongMessageRuleType.XUDT_ENABLED);
  }

  private void initTCAP() throws Exception {
    this.tcapStack = new TCAPStackImpl("TestServer", this.sccpStack.getSccpProvider(), SERVER_SSN);
    this.tcapStack.start();
    this.tcapStack.setDialogIdleTimeout(60000);
    this.tcapStack.setInvokeTimeout(30000);
    this.tcapStack.setMaxDialogs(MAX_DIALOGS);
  }

  private void initMAP() throws Exception {
    this.mapStack = new MAPStackImpl("TestServer", this.tcapStack.getProvider());
    this.mapProvider = this.mapStack.getMAPProvider();

    this.mapProvider.addMAPDialogListener(this);

    this.mapProvider.getMAPServiceMobility().addMAPServiceListener(this);
    this.mapProvider.getMAPServiceMobility().activate();

    this.mapProvider.getMAPServiceLsm().addMAPServiceListener(this);
    this.mapProvider.getMAPServiceLsm().activate();

    this.mapStack.start();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogDelimiter(org.mobicents.protocols.ss7.map.api.MAPDialog)
   *
   */
  @Override
  public void onDialogDelimiter(MAPDialog mapDialog) {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("onDialogDelimiter for DialogId=%d", mapDialog.getLocalDialogId()));
    } else {
      logger.info(String.format("onDialogDelimiter for DialogId=%d", mapDialog.getLocalDialogId()));
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogRequest(org.mobicents.protocols.ss7.map.api.MAPDialog,
   * org.mobicents.protocols.ss7.map.api.primitives.AddressString,
   * org.mobicents.protocols.ss7.map.api.primitives.AddressString,
   * org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer)
   */
  @Override
  public void onDialogRequest(MAPDialog mapDialog, AddressString destReference, AddressString origReference,
                              MAPExtensionContainer extensionContainer) {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format(
          "onDialogRequest for DialogId=%d DestinationReference=%s OriginReference=%s MAPExtensionContainer=%s",
          mapDialog.getLocalDialogId(), destReference, origReference, extensionContainer));
    } else {
      logger.info(String.format(
          "onDialogRequest for DialogId=%d DestinationReference=%s OriginReference=%s MAPExtensionContainer=%s",
          mapDialog.getLocalDialogId(), destReference, origReference, extensionContainer));
    }
  }

  @Override
  public void onDialogRequestEricsson(MAPDialog mapDialog, AddressString destReference, AddressString origReference,
                                      AddressString addressString, AddressString vlr) {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("onDialogRequest for DialogId=%d DestinationReference=%s OriginReference=%s ",
          mapDialog.getLocalDialogId(), destReference, origReference));
    } else {
      logger.info(String.format("onDialogRequest for DialogId=%d DestinationReference=%s OriginReference=%s ",
          mapDialog.getLocalDialogId(), destReference, origReference));
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogAccept( org.mobicents.protocols.ss7.map.api.MAPDialog,
   * org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer)
   */
  @Override
  public void onDialogAccept(MAPDialog mapDialog, MAPExtensionContainer extensionContainer) {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("onDialogAccept for DialogId=%d MAPExtensionContainer=%s", mapDialog.getLocalDialogId(),
          extensionContainer));
    } else {
      logger.info(String.format("onDialogAccept for DialogId=%d MAPExtensionContainer=%s", mapDialog.getLocalDialogId(),
          extensionContainer));
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogReject( org.mobicents.protocols.ss7.map.api.MAPDialog,
   * org.mobicents.protocols.ss7.map.api.dialog.MAPRefuseReason, org.mobicents.protocols.ss7.map.api.dialog.MAPProviderError,
   * org.mobicents.protocols.ss7.tcap.asn.ApplicationContextName,
   * org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer)
   */
  @Override
  public void onDialogReject(MAPDialog mapDialog, MAPRefuseReason refuseReason,
                             ApplicationContextName alternativeApplicationContext, MAPExtensionContainer extensionContainer) {
    logger.error(String.format(
        "onDialogReject for DialogId=%d MAPRefuseReason=%s ApplicationContextName=%s MAPExtensionContainer=%s",
        mapDialog.getLocalDialogId(), refuseReason, alternativeApplicationContext, extensionContainer));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogUserAbort(org.mobicents.protocols.ss7.map.api.MAPDialog,
   * org.mobicents.protocols.ss7.map.api.dialog.MAPUserAbortChoice,
   * org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer)
   */
  @Override
  public void onDialogUserAbort(MAPDialog mapDialog, MAPUserAbortChoice userReason,
                                MAPExtensionContainer extensionContainer) {
    logger.error(String.format("onDialogUserAbort for DialogId=%d MAPUserAbortChoice=%s MAPExtensionContainer=%s",
        mapDialog.getLocalDialogId(), userReason, extensionContainer));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogProviderAbort(org.mobicents.protocols.ss7.map.api.MAPDialog,
   * org.mobicents.protocols.ss7.map.api.dialog.MAPAbortProviderReason,
   * org.mobicents.protocols.ss7.map.api.dialog.MAPAbortSource,
   * org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer)
   */
  @Override
  public void onDialogProviderAbort(MAPDialog mapDialog, MAPAbortProviderReason abortProviderReason,
                                    MAPAbortSource abortSource, MAPExtensionContainer extensionContainer) {
    logger.error(String.format(
        "onDialogProviderAbort for DialogId=%d MAPAbortProviderReason=%s MAPAbortSource=%s MAPExtensionContainer=%s",
        mapDialog.getLocalDialogId(), abortProviderReason, abortSource, extensionContainer));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogClose(org .mobicents.protocols.ss7.map.api.MAPDialog)
   */
  @Override
  public void onDialogClose(MAPDialog mapDialog) {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("DialogClose for Dialog=%d", mapDialog.getLocalDialogId()));
    } else {
      logger.info(String.format("DialogClose for Dialog=%d", mapDialog.getLocalDialogId()));
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogNotice(org.mobicents.protocols.ss7.map.api.MAPDialog,
   * org.mobicents.protocols.ss7.map.api.dialog.MAPNoticeProblemDiagnostic)
   */
  @Override
  public void onDialogNotice(MAPDialog mapDialog, MAPNoticeProblemDiagnostic noticeProblemDiagnostic) {
    logger.error(String.format("onDialogNotice for DialogId=%d MAPNoticeProblemDiagnostic=%s ",
        mapDialog.getLocalDialogId(), noticeProblemDiagnostic));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogResease(org.mobicents.protocols.ss7.map.api.MAPDialog)
   *
   */
  @Override
  public void onDialogRelease(MAPDialog mapDialog) {
    if (logger.isDebugEnabled()) {
      logger.debug(String.format("onDialogResease for DialogId=%d", mapDialog.getLocalDialogId()));
    } else {
      logger.info(String.format("onDialogResease for DialogId=%d", mapDialog.getLocalDialogId()));
    }

    this.endCount++;

    if (((this.endCount % 10000) == 0) && endCount > 1) {
      long currentTime = System.currentTimeMillis();
      long processingTime = currentTime - start;
      start = currentTime;
      logger.warn("Completed 10000 Dialogs in=" + processingTime + " ms. Dialogs per sec: " + +(float) (10000000 / processingTime));
    }

  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPDialogListener#onDialogTimeout(org.mobicents.protocols.ss7.map.api.MAPDialog)
   *
   */
  @Override
  public void onDialogTimeout(MAPDialog mapDialog) {
    logger.error(String.format("onDialogTimeout for DialogId=%d", mapDialog.getLocalDialogId()));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPServiceListener#onErrorComponent(org.mobicents.protocols.ss7.map.api.MAPDialog,
   * java.lang.Long, org.mobicents.protocols.ss7.map.api.errors.MAPErrorMessage)
   *
   */
  // @Override
  // public void onErrorComponent(MAPDialog mapDialog, Long invokeId, MAPErrorMessage mapErrorMessage) {
  // logger.error(String.format("onErrorComponent for Dialog=%d and invokeId=%d MAPErrorMessage=%s",
  // mapDialog.getLocalDialogId(), invokeId, mapErrorMessage));
  // }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPServiceListener#onRejectComponent(org.mobicents.protocols.ss7.map.api.MAPDialog,
   * java.lang.Long, org.mobicents.protocols.ss7.tcap.asn.comp.Problem)
   */
  // @Override
  // public void onRejectComponent(MAPDialog mapDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
  // logger.error(String.format("onRejectComponent for Dialog=%d and invokeId=%d Problem=%s isLocalOriginated=%s",
  // mapDialog.getLocalDialogId(), invokeId, problem, isLocalOriginated));
  // }

  /*
   * (non-Javadoc)
   *
   * @see org.mobicents.protocols.ss7.map.api.MAPServiceListener#onInvokeTimeout
   * (org.mobicents.protocols.ss7.map.api.MAPDialog, java.lang.Long)
   */
  // @Override
  // public void onInvokeTimeout(MAPDialog mapDialog, Long invokeId) {
  // logger.error(String.format("onInvokeTimeout for Dialog=%d and invokeId=%d", mapDialog.getLocalDialogId(), invokeId));
  // }

  public static void main(String[] args) {
    IpChannelType ipChannelType = IpChannelType.SCTP;
    if (args.length >= 1 && args[0].toLowerCase().equals("tcp")) {
      ipChannelType = IpChannelType.TCP;
    } else {
      ipChannelType = IpChannelType.SCTP;
    }
    logger.info("IpChannelType=" + ipChannelType);

    if (args.length >= 2) {
      TestHarness.CLIENT_IP = args[1];
    }
    logger.info("CLIENT_IP=" + TestHarness.CLIENT_IP);

    if (args.length >= 3) {
      TestHarness.CLIENT_PORT = Integer.parseInt(args[2]);
    }
    logger.info("CLIENT_PORT=" + TestHarness.CLIENT_PORT);

    if (args.length >= 4) {
      TestHarness.SERVER_IP = args[3];
    }
    logger.info("SERVER_IP=" + TestHarness.SERVER_IP);

    if (args.length >= 5) {
      TestHarness.SERVER_PORT = Integer.parseInt(args[4]);
    }
    logger.info("SERVER_PORT=" + TestHarness.SERVER_PORT);

    if (args.length >= 6) {
      TestHarness.CLIENT_SPC = Integer.parseInt(args[5]);
    }
    logger.info("CLIENT_SPC=" + TestHarness.CLIENT_SPC);

    if (args.length >= 7) {
      TestHarness.SERVER_SPC = Integer.parseInt(args[6]);
    }
    logger.info("SERVER_SPC=" + TestHarness.SERVER_SPC);

    if (args.length >= 8) {
      TestHarness.NETWORK_INDICATOR = Integer.parseInt(args[7]);
    }
    logger.info("NETWORK_INDICATOR=" + TestHarness.NETWORK_INDICATOR);

    if (args.length >= 9) {
      TestHarness.SERVICE_INDICATOR = Integer.parseInt(args[8]);
    }
    logger.info("SERVICE_INDICATOR=" + TestHarness.SERVICE_INDICATOR);

    if (args.length >= 10) {
      TestHarness.SERVER_SSN = Integer.parseInt(args[9]);
    }
    logger.info("SSN=" + TestHarness.SERVER_SSN);

    if (args.length >= 11) {
      TestHarness.ROUTING_CONTEXT = Integer.parseInt(args[10]);
    }
    logger.info("ROUTING_CONTEXT=" + TestHarness.ROUTING_CONTEXT);

    if (args.length >= 12) {
      TestHarness.DELIVERY_TRANSFER_MESSAGE_THREAD_COUNT = Integer.parseInt(args[11]);
    }
    logger.info("DELIVERY_TRANSFER_MESSAGE_THREAD_COUNT=" + TestHarness.DELIVERY_TRANSFER_MESSAGE_THREAD_COUNT);

    final Server server = new Server();
    try {
      server.initializeStack(ipChannelType);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onAnyTimeInterrogationRequest(AnyTimeInterrogationRequest atiReq) {

    if (logger.isDebugEnabled()) {
      logger.debug(
          String.format("onAnyTimeInterrogationRequest for DialogId=%d", atiReq.getMAPDialog().getLocalDialogId()));
    }
    if (logger.isInfoEnabled()) {
      logger.info(String.format("onAnyTimeInterrogationRequest for DialogId=%d", atiReq.getMAPDialog().getLocalDialogId()));
    } /*else {
            logger.warn(String.format("onAnyTimeInterrogationRequest for DialogId=%d", atiReq.getMAPDialog().getLocalDialogId()));
        }*/

    try {
      long invokeId = atiReq.getInvokeId();
      MAPDialogMobility mapDialogMobility = atiReq.getMAPDialog();
      mapDialogMobility.setUserObject(invokeId);

      MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();

      // Create Subscriber Information parameters including Location Information and Subscriber State
      // for concerning MAP operation
      int mcc, mnc, lac, cellIdOrServiceAreaCode;
      mcc = 748;
      mnc = 1;
      lac = 23;
      cellIdOrServiceAreaCode = 369;
      CellGlobalIdOrServiceAreaIdFixedLength cellGlobalIdOrServiceAreaIdFixedLength = mapFactory
          .createCellGlobalIdOrServiceAreaIdFixedLength(mcc, mnc, lac, cellIdOrServiceAreaCode);
      CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = mapFactory
          .createCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdFixedLength);
      ISDNAddressString vlrNumber = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, "5982123007");
      ISDNAddressString mscNumber = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, "5982123007");
      Integer ageOfLocationInformation = 0; // ageOfLocationInformation
      GeographicalInformation geographicalInformation = null;
      LocationNumberMap locationNumber = null;
      MAPExtensionContainer mapExtensionContainer = null;
      LSAIdentity selectedLSAId = null;
      GeodeticInformation geodeticInformation = null;
      boolean currentLocationRetrieved = false;
      boolean saiPresent = false;
      LocationInformationEPS locationInformationEPS = null;
      UserCSGInformation userCSGInformation = null;
      LocationInformationGPRS locationInformationGPRS = null;
      PSSubscriberState psSubscriberState = null;
      IMEI imei = null;
      MSClassmark2 msClassmark2 = null;
      GPRSMSClass gprsMSClass = null;
      MNPInfoRes mnpInfoRes = null;
      SubscriberStateChoice subscriberStateChoice = SubscriberStateChoice.assumedIdle;
      // 0=assumedIdle, 1=camelBusy, 2=notProvidedFromVLR
      NotReachableReason notReachableReason = null;

      LocationInformation locationInformation = mapFactory.createLocationInformation(ageOfLocationInformation,
          geographicalInformation, vlrNumber, locationNumber, cellGlobalIdOrServiceAreaIdOrLAI, mapExtensionContainer,
          selectedLSAId, mscNumber, geodeticInformation, currentLocationRetrieved, saiPresent, locationInformationEPS,
          userCSGInformation);

      SubscriberState subscriberState = mapFactory.createSubscriberState(subscriberStateChoice, notReachableReason);

      SubscriberInfo subscriberInfo = mapFactory.createSubscriberInfo(locationInformation, subscriberState,
          mapExtensionContainer, locationInformationGPRS, psSubscriberState, imei, msClassmark2, gprsMSClass,
          mnpInfoRes);

      mapDialogMobility.addAnyTimeInterrogationResponse(invokeId, subscriberInfo, mapExtensionContainer);

      // This will initiate the TC-BEGIN with INVOKE component
      mapDialogMobility.close(false);

    } catch (MAPException mapException) {
      logger.error("MAP Exception while processing AnyTimeInterrogationRequest ", mapException);
    } catch (Exception e) {
      logger.error("Exception while processing AnyTimeInterrogationRequest ", e);
    }

  }

  public void onAnyTimeInterrogationResponse(AnyTimeInterrogationResponse atiResp) {
    /*
     * This is an error condition. Server should never receive onAnyTimeInterrogationResponse.
     */
    logger.error(String.format("onAnyTimeInterrogationRequest for Dialog=%d and invokeId=%d",
        atiResp.getMAPDialog().getLocalDialogId(), atiResp.getInvokeId()));

  }

  @Override
  public void onAnyTimeSubscriptionInterrogationRequest(AnyTimeSubscriptionInterrogationRequest anyTimeSubscriptionInterrogationRequest) {

  }

  @Override
  public void onAnyTimeSubscriptionInterrogationResponse(AnyTimeSubscriptionInterrogationResponse anyTimeSubscriptionInterrogationResponse) {

  }

  @Override
  public void onSendRoutingInfoForLCSRequest(SendRoutingInfoForLCSRequest sendRoutingInforForLCSRequest) {

    if (logger.isDebugEnabled()) {
      logger.debug(
          String.format("onSendRoutingInfoForLCSRequest for DialogId=%d", sendRoutingInforForLCSRequest.getMAPDialog().getLocalDialogId()));
    }
    if (logger.isInfoEnabled()) {
      logger.info(String.format("onAnyTimeInterrogationRequest for DialogId=%d", sendRoutingInforForLCSRequest.getMAPDialog().getLocalDialogId()));
    }

    try {
      long invokeId = sendRoutingInforForLCSRequest.getInvokeId();
      MAPDialogLsm mapDialogLsm = sendRoutingInforForLCSRequest.getMAPDialog();
      mapDialogLsm.setUserObject(invokeId);

      // Create Routing Information parameters for concerning MAP operation
      MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();
      String mscAddress = "5982123007";
      String sgsnAddress = "5982123009";
      ISDNAddressString mscNumber = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, mscAddress);
      ISDNAddressString sgsnNumber = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, sgsnAddress);
      AdditionalNumber additionalNumber = new AdditionalNumberImpl(null, sgsnNumber);
      String lmsiStr = "12345678";
      byte[] Lmsi = lmsiStr.getBytes();
      LMSI lmsi = mapFactory.createLMSI(Lmsi);
      Boolean gprsNodeIndicator = false;
      boolean lcsCapabilitySetRelease98_99 = true;
      boolean lcsCapabilitySetRelease4 = true;
      boolean lcsCapabilitySetRelease5 = true;
      boolean lcsCapabilitySetRelease6 = true;
      boolean lcsCapabilitySetRelease7 = false;
      SupportedLCSCapabilitySets supportedLCSCapabilitySets = new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4,
          lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
      lcsCapabilitySetRelease7 = true;
      SupportedLCSCapabilitySets additionalLCSCapabilitySets = new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4,
          lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
      MAPExtensionContainer mapExtensionContainer = null;
      byte[] mmeNom = new BigInteger("00112233445566778899", 16).toByteArray();
      DiameterIdentity mmeName = new DiameterIdentityImpl(mmeNom);
      byte[] aaaSN = new BigInteger("0011223344556677889900", 16).toByteArray();
      DiameterIdentity aaaServerName = new DiameterIdentityImpl(aaaSN);
      ISDNAddressString isdnAdd = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, "59899077937");
      SubscriberIdentity msisdn = new SubscriberIdentityImpl(isdnAdd);
      byte[] visitedGmlcAddress = new BigInteger("112233445500", 16).toByteArray();
      GSNAddress vGmlcAddress = new GSNAddressImpl(visitedGmlcAddress);
      byte[] homeGmlcAddress = new BigInteger("11223344556677", 16).toByteArray();
      GSNAddress hGmlcAddress = new GSNAddressImpl(homeGmlcAddress);
      byte[] pivacyProfileRegisterAddress = new BigInteger("112233445566", 16).toByteArray();
      GSNAddress pprAddress = new GSNAddressImpl(pivacyProfileRegisterAddress);
      byte[] addVGmlcAddress = new BigInteger("8122334455", 16).toByteArray();
      GSNAddress additionalVGmlcAddress = new GSNAddressImpl(addVGmlcAddress);

      LCSLocationInfo lcsLocationInfo = mapFactory.createLCSLocationInfo(mscNumber, lmsi, mapExtensionContainer, gprsNodeIndicator,
          additionalNumber, supportedLCSCapabilitySets, additionalLCSCapabilitySets, mmeName, aaaServerName);
//            addSendRoutingInfoForLCSResponse(long invokeId, SubscriberIdentity targetMS, LCSLocationInfo lcsLocationInfo,
//                                             MAPExtensionContainer extensionContainer,
//                                            byte[] vgmlcAddress, byte[] hGmlcAddress, byte[] pprAddress, byte[] additionalVGmlcAddress)
      mapDialogLsm.addSendRoutingInfoForLCSResponse(invokeId, msisdn, lcsLocationInfo, mapExtensionContainer, vGmlcAddress, hGmlcAddress,
          pprAddress, additionalVGmlcAddress);
      // This will initiate the TC-BEGIN with INVOKE component
      mapDialogLsm.close(false);

    } catch (MAPException mapException) {
      logger.error("MAP Exception while processing onSendRoutingInfoForLCSRequest ", mapException);
    } catch (Exception e) {
      logger.error("Exception while processing onSendRoutingInfoForLCSRequest ", e);
    }
  }

  @Override
  public void onSendRoutingInfoForLCSResponse(SendRoutingInfoForLCSResponse sendRoutingInforForLCSResponse) {
    /*
     * This is an error condition. Server should never receive onSendRoutingInfoForLCSResponse.
     */
    logger.error(String.format("onSendRoutingInfoForLCSResponse for Dialog=%d and invokeId=%d",
        sendRoutingInforForLCSResponse.getMAPDialog().getLocalDialogId(), sendRoutingInforForLCSResponse.getInvokeId()));

  }

  @Override
  public void onProvideSubscriberLocationRequest(ProvideSubscriberLocationRequest provideSubscriberLocationRequest) {

    if (logger.isDebugEnabled()) {
      logger.debug(
          String.format("onProvideSubscriberLocationRequest for DialogId=%d", provideSubscriberLocationRequest.getMAPDialog().getLocalDialogId()));
    }
    if (logger.isInfoEnabled()) {
      logger.info(String.format("onProvideSubscriberLocationRequest for DialogId=%d", provideSubscriberLocationRequest.getMAPDialog().getLocalDialogId()));
    }

    try {
      long invokeId = provideSubscriberLocationRequest.getInvokeId();
      MAPDialogLsm mapDialogLsm = provideSubscriberLocationRequest.getMAPDialog();
      mapDialogLsm.setUserObject(invokeId);

      // Create Routing Information parameters for concerning MAP operation
      MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();
      ISDNAddressString mscNumber = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, "5982123007");
      ISDNAddressString sgsnNumber = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, "5982123009");
      String lmsiStr = "87654321";
      byte[] Lmsi = lmsiStr.getBytes();
      LMSI lmsi = mapFactory.createLMSI(Lmsi);
      byte[] eGeographicalInformation = new BigInteger("014321a10022337799", 16).toByteArray();
      ExtGeographicalInformation extGeographicalInformation = mapFactory.createExtGeographicalInformation(eGeographicalInformation);
      String posDataInfo = "55338901";
      byte[] posDataInformation = posDataInfo.getBytes();
      PositioningDataInformation positioningDataInformation = mapFactory.createPositioningDataInformation(posDataInformation);
      byte[] utranData = new BigInteger("43210987654321", 16).toByteArray();
      UtranPositioningDataInfo utranPositioningDataInfo = new UtranPositioningDataInfoImpl(utranData);
      Integer ageOfLocationEstimate = 1;
      byte[] addLocationEstimate = new BigInteger("5533a100223377", 16).toByteArray();
      AddGeographicalInformation additionalLocationEstimate = mapFactory.createAddGeographicalInformation(addLocationEstimate);
/*
            long[] oid = {0, 0, 17, 773, 1, 1, 1};
            String pExtData = "1144":
            byte[] privateExtData = pExtData.getBytes();
            MAPPrivateExtension mapPrivateExtension = new MAPPrivateExtensionImpl(oid, privateExtData);
            ArrayList<MAPPrivateExtension> mapPrivateExtensions = new ArrayList<MAPPrivateExtension>();
            mapPrivateExtensions.add(mapPrivateExtension);
            String pcsExt = "1033";
            byte[] pcsExtensions = pcsExt.getBytes();
            MAPExtensionContainer mapExtensionContainer = new MAPExtensionContainerImpl(mapPrivateExtensions, pcsExtensions);
*/
      MAPExtensionContainer mapExtensionContainer = null;
      Boolean deferredMTLRResponseIndicator = true;
      int mcc, mnc, lac, cellIdOrServiceAreaCode;
      mcc = 748;
      mnc = 1;
      lac = 23;
      cellIdOrServiceAreaCode = 369;
      CellGlobalIdOrServiceAreaIdFixedLength cellGlobalIdOrServiceAreaIdFixedLength = mapFactory
          .createCellGlobalIdOrServiceAreaIdFixedLength(mcc, mnc, lac, cellIdOrServiceAreaCode);
      CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = mapFactory
          .createCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdFixedLength);
      Boolean saiPresent = true;
      AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = AccuracyFulfilmentIndicator.getAccuracyFulfilmentIndicator(0);
      // AccuracyFulfilmentIndicator ::= ENUMERATED { requestedAccuracyFulfilled (0), requestedAccuracyNotFulfilled (1), ... }
      String velEstStr = "00000001";
      byte[] velEst = velEstStr.getBytes();
      VelocityEstimate velocityEstimate = mapFactory.createVelocityEstimate(velEst);
      Boolean moLrShortCircuitIndicator = true;
      byte[] gGanss = new BigInteger("666601019999", 16).toByteArray();
      GeranGANSSpositioningData geranGANSSpositioningData = new GeranGANSSpositioningDataImpl(gGanss);
      byte[] uGanss = new BigInteger("777701019898", 16).toByteArray();
      UtranGANSSpositioningData utranGANSSpositioningData = new UtranGANSSpositioningDataImpl(uGanss);
      ServingNodeAddress servingNodeAddress = mapFactory.createServingNodeAddressMscNumber(mscNumber);

      mapDialogLsm.addProvideSubscriberLocationResponse(invokeId, extGeographicalInformation, positioningDataInformation, utranPositioningDataInfo,
          ageOfLocationEstimate, additionalLocationEstimate, mapExtensionContainer, deferredMTLRResponseIndicator, cellGlobalIdOrServiceAreaIdOrLAI,
          saiPresent, accuracyFulfilmentIndicator, velocityEstimate, moLrShortCircuitIndicator, geranGANSSpositioningData, utranGANSSpositioningData,
          servingNodeAddress);
      // This will initiate the TC-BEGIN with INVOKE component
      mapDialogLsm.close(false);

    } catch (MAPException mapException) {
      logger.error("MAP Exception while processing onProvideSubscriberLocationRequest ", mapException);
    } catch (Exception e) {
      logger.error("Exception while processing onProvideSubscriberLocationRequest ", e);
    }

  }

  @Override
  public void onProvideSubscriberLocationResponse(ProvideSubscriberLocationResponse provideSubscriberLocationResponse) {

    /*
     * This is an error condition. Server should never receive onProvideSubscriberLocationResponse.
     */
    logger.error(String.format("onProvideSubscriberLocationResponse for Dialog=%d and invokeId=%d",
        provideSubscriberLocationResponse.getMAPDialog().getLocalDialogId(), provideSubscriberLocationResponse.getInvokeId()));

  }

  @Override
  public void onSubscriberLocationReportRequest(SubscriberLocationReportRequest subscriberLocationReportRequest) {

    if (logger.isDebugEnabled()) {
      logger.debug(
          String.format("onSubscriberLocationReportRequest for DialogId=%d", subscriberLocationReportRequest.getMAPDialog().getLocalDialogId()));
    }
    if (logger.isInfoEnabled()) {
      logger.info(String.format("onSubscriberLocationReportRequest for DialogId=%d", subscriberLocationReportRequest.getMAPDialog().getLocalDialogId()));
    }

    try {
      long invokeId = subscriberLocationReportRequest.getInvokeId();
      MAPDialogLsm mapDialogLsm = subscriberLocationReportRequest.getMAPDialog();
      mapDialogLsm.setUserObject(invokeId);

      // Create Routing Information parameters for concerning MAP operation
      MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();
      ISDNAddressString naEsrd = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, "5982123007");
      ISDNAddressString naEsrk = new ISDNAddressStringImpl(AddressNature.international_number,
          NumberingPlan.ISDN, "5982123009");
/*
            long[] oid = {0, 0, 17, 773, 1, 1, 1};
            String privExtData = "1144";
            byte[] privateExtData = privExtData.getBytes();
            MAPPrivateExtension mapPrivateExtension = new MAPPrivateExtensionImpl(oid, privateExtData);
            ArrayList<MAPPrivateExtension> mapPrivateExtensions = new ArrayList<MAPPrivateExtension>();
            mapPrivateExtensions.add(mapPrivateExtension);
            String pcsExtStr = "1033";
            byte[] pcsExtensions = pcsExtStr.getBytes();
            MAPExtensionContainer mapExtensionContainer = new MAPExtensionContainerImpl(mapPrivateExtensions, pcsExtensions);
*/
      MAPExtensionContainer mapExtensionContainer = null;

      mapDialogLsm.addSubscriberLocationReportResponse(invokeId, naEsrd, naEsrk, mapExtensionContainer);

      // This will initiate the TC-BEGIN with INVOKE component
      mapDialogLsm.close(false);

    } catch (MAPException mapException) {
      logger.error("MAP Exception while processing onSubscriberLocationReportRequest ", mapException);
    } catch (Exception e) {
      logger.error("Exception while processing onSubscriberLocationReportRequest ", e);
    }

  }

  @Override
  public void onSubscriberLocationReportResponse(SubscriberLocationReportResponse subscriberLocationReportResponse) {

    /*
     * This is an error condition. Server should never receive onProvideSubscriberLocationResponse.
     */
    logger.error(String.format("onSubscriberLocationReportResponse for Dialog=%d and invokeId=%d",
        subscriberLocationReportResponse.getMAPDialog().getLocalDialogId(), subscriberLocationReportResponse.getInvokeId()));
  }

  public void onInsertSubscriberDataRequest(InsertSubscriberDataRequest insertSubscriberData) {

  }

  public void onDeleteSubscriberDataRequest(DeleteSubscriberDataRequest deleteSubsData) {

  }

  public void onErrorComponent(MAPDialog arg0, Long arg1, MAPErrorMessage arg2) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onInsertSubscriberDataResponse(InsertSubscriberDataResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onProvideSubscriberInfoRequest(ProvideSubscriberInfoRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onProvideSubscriberInfoResponse(ProvideSubscriberInfoResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onPurgeMSRequest(PurgeMSRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onPurgeMSResponse(PurgeMSResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onResetRequest(ResetRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onRestoreDataRequest(RestoreDataRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onRestoreDataResponse(RestoreDataResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onSendAuthenticationInfoRequest(SendAuthenticationInfoRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onSendAuthenticationInfoResponse(SendAuthenticationInfoResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onSendIdentificationRequest(SendIdentificationRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onSendIdentificationResponse(SendIdentificationResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUpdateGprsLocationRequest(UpdateGprsLocationRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUpdateGprsLocationResponse(UpdateGprsLocationResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUpdateLocationRequest(UpdateLocationRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUpdateLocationResponse(UpdateLocationResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onActivateTraceModeRequest_Mobility(ActivateTraceModeRequest_Mobility arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onActivateTraceModeResponse_Mobility(ActivateTraceModeResponse_Mobility arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onAuthenticationFailureReportRequest(AuthenticationFailureReportRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onAuthenticationFailureReportResponse(AuthenticationFailureReportResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onCancelLocationRequest(CancelLocationRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onCancelLocationResponse(CancelLocationResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onCheckImeiRequest(CheckImeiRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onCheckImeiResponse(CheckImeiResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onDeleteSubscriberDataResponse(DeleteSubscriberDataResponse arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onForwardCheckSSIndicationRequest(ForwardCheckSSIndicationRequest arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onInvokeTimeout(MAPDialog arg0, Long arg1) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onMAPMessage(MAPMessage arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onRejectComponent(MAPDialog arg0, Long arg1, Problem arg2, boolean arg3) {
    // TODO Auto-generated method stub

  }

}
