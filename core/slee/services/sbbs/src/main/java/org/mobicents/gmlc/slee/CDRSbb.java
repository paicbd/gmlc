package org.mobicents.gmlc.slee;

import org.mobicents.gmlc.GmlcPropertiesManagement;
import org.mobicents.gmlc.GmlcPropertiesManagementMBean;
import org.mobicents.gmlc.slee.cdr.CDRInterface;
import org.mobicents.gmlc.slee.cdr.CDRInterfaceParent;
import org.mobicents.gmlc.slee.cdr.RecordStatus;
import org.mobicents.slee.ChildRelationExt;

import javax.slee.ActivityContextInterface;
import javax.slee.CreateException;
import javax.slee.SLEEException;
import javax.slee.TransactionRequiredLocalException;
import javax.slee.facilities.TimerEvent;
import javax.slee.facilities.TimerFacility;
import javax.slee.facilities.TimerID;
import javax.slee.facilities.TimerOptions;

/**
 *
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public abstract class CDRSbb extends GMLCBaseSbb implements CDRInterfaceParent {

  protected GmlcPropertiesManagementMBean gmlcPropertiesManagementMBean = null;
  protected TimerFacility timerFacility = null;

  public CDRSbb(String loggerName) {
    super(loggerName);
  }

  /**
   * Timer event
   */
  public void onTimerEvent(TimerEvent event, ActivityContextInterface aci) {

    if (super.logger.isWarningEnabled()) {
      super.logger.warning(String.format(
          "Application didn't revert in %d milliseconds. Sending back dialog timeout error message for MAPDialog %s",
          gmlcPropertiesManagementMBean.getDialogTimeout(), gmlcPropertiesManagementMBean.getDialogTimeoutErrorMessage()));
    }

    try {

      String errorMsg = gmlcPropertiesManagementMBean.getDialogTimeoutErrorMessage();
      // TODO send error message to MLP Client
      this.sendErrorMessage(errorMsg);

      // TODO ifIsMLP()

    } catch (Exception e) {
      logger.severe("Error while sending an error message to a peer " + e.getMessage(), e);
    }

    this.terminateProtocolConnection();

    /// TODO Stat update
    //this.gmlcStatAggregator.updateAppTimeouts();
    //this.updateDialogFailureStat();

    this.createCDRRecord(RecordStatus.FAILED_APP_TIMEOUT);
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
    long waitingTime = gmlcPropertiesManagementMBean.getDialogTimeout();
    options.setTimeout(waitingTime);
    // Set the timer on ACI
    TimerID timerID = this.timerFacility.setTimer(aci, null, System.currentTimeMillis() + waitingTime, options);
    this.setTimerID(timerID);
  }


  protected void sendErrorMessage(String errorMsg) {
    this.sendErrorMessage(errorMsg);
  }

  // //////////////////////////
  // Abstract child methods //
  // ////////////////////////

  protected abstract void terminateProtocolConnection();
  protected abstract void updateDialogFailureStat();
  public abstract void setTimerID(TimerID value);
  public abstract TimerID getTimerID();
  public abstract void setFinalMessageSent(boolean value);
  public abstract boolean getFinalMessageSent();

  //////////////////////
  //  CDR interface  //
  ////////////////////

  private static final String CDR = "CDR";

  public abstract ChildRelationExt getCDRInterfaceChildRelation();

  public abstract ChildRelationExt getCDRPlainInterfaceChildRelation();

  public CDRInterface getCDRInterface() {
    GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();
    ChildRelationExt childExt;
    if (gmlcPropertiesManagement.getCdrLoggingTo() == GmlcPropertiesManagement.CdrLoggedType.Textfile) {
      childExt = getCDRPlainInterfaceChildRelation();
    } else {
      childExt = getCDRInterfaceChildRelation();
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

  @Override
  public void recordGenerationSucceeded() {
    if (this.logger.isFineEnabled()) {
      this.logger.fine("Generated CDR for Status: " + getCDRInterface().getState());
    }
  }

  @Override
  public void recordGenerationFailed(String message) {
    this.logger.severe("Failed to generate CDR! Message: '" + message + "'");
    this.logger.severe("Status: " + getCDRInterface().getState());
  }

  @Override
  public void recordGenerationFailed(String message, Throwable t) {
    this.logger.severe("Failed to generate CDR! Message: '" + message + "'", t);
    this.logger.severe("Status: " + getCDRInterface().getState());
  }

  @Override
  public void initFailed(String message, Throwable t) {
    this.logger.severe("Failed to initialize CDR Database! Message: '" + message + "'", t);
  }

  @Override
  public void initSucceeded() {
    if (this.logger.isFineEnabled()) {
      this.logger.fine("CDR Database has been initialized!");
    }
  }

}
