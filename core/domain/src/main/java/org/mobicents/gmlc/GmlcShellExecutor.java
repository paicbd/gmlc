package org.mobicents.gmlc;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.restcomm.ss7.management.console.ShellExecutor;

/**
 *
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class GmlcShellExecutor implements ShellExecutor {

  private static final Logger logger = Logger.getLogger(GmlcShellExecutor.class);

  private GmlcManagement gmlcManagement;
  private GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();

  /**
   *
   */
  public GmlcShellExecutor() {
    // TODO Auto-generated constructor stub
  }

  public GmlcManagement getGmlcManagement() {
    return gmlcManagement;
  }

  public void setGmlcManagement(GmlcManagement gmlcManagement) {
    this.gmlcManagement = gmlcManagement;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.mobicents.ss7.management.console.ShellExecutor#execute(java.lang.String[])
   */
  @Override
  public String execute(String[] commands) {

    try {
      if (commands.length < 2) {
        return GmlcOAMMessages.INVALID_COMMAND;
      }
      String command = commands[1];

      if (command.equals("set")) {
        return this.manageSet(commands);
      } else if (command.equals("get")) {
        return this.manageGet(commands);
      }
      return GmlcOAMMessages.INVALID_COMMAND;
    } catch (Exception e) {
      logger.error(String.format("Error while executing command %s", Arrays.toString(commands)), e);
      return e.getMessage();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.mobicents.ss7.management.console.ShellExecutor#handles(java.lang.String)
   */
  @Override
  public boolean handles(String command) {
    return "gmlc".equals(command);
  }

  private String manageSet(String[] options) throws Exception {
    if (options.length < 4) {
      return GmlcOAMMessages.INVALID_COMMAND;
    }

    String parameterName = options[2].toLowerCase();
    if (parameterName.equals(GmlcPropertiesManagement.GMLC_GT)) {
      gmlcPropertiesManagement.setGmlcGt(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.GMLC_SSN)) {
      int val = Integer.parseInt(options[3]);
      gmlcPropertiesManagement.setGmlcSsn(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.HLR_SSN)) {
      int val = Integer.parseInt(options[3]);
      gmlcPropertiesManagement.setHlrSsn(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.MSC_SSN)) {
      int val = Integer.parseInt(options[3]);
      gmlcPropertiesManagement.setMscSsn(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.VLR_SSN)) {
      int val = Integer.parseInt(options[3]);
      gmlcPropertiesManagement.setVlrSsn(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.MAX_MAP_VERSION)) {
      int val = Integer.parseInt(options[3]);
      gmlcPropertiesManagement.setMaxMapVersion(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.MAX_ACTIVITY_COUNT)) {
      int val = Integer.parseInt(options[3]);
      gmlcPropertiesManagement.setMaxActivityCount(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.DIALOG_TIMEOUT)) {
      long val = Long.parseLong(options[3]);
      gmlcPropertiesManagement.setDialogTimeout(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.EVENT_CONTEXT_SUSPEND_DELIVERY_TIMEOUT)) {
      long val = Long.parseLong(options[3]);
      gmlcPropertiesManagement.setDialogTimeout(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_ORIGIN_REALM)) {
      gmlcPropertiesManagement.setDiameterOriginRealm(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_ORIGIN_HOST)) {
      gmlcPropertiesManagement.setDiameterOriginHost(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_DEST_REALM)) {
      gmlcPropertiesManagement.setDiameterDestRealm(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_DEST_HOST)) {
      gmlcPropertiesManagement.setDiameterDestHost(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_GMLC_NUMBER)) {
      gmlcPropertiesManagement.setDiameterGmlcNumber(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.LCS_NON_TRIGGERED_REPORT_OPTION)) {
      gmlcPropertiesManagement.setLcsNonTriggeredReportOption(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.LCS_URL_CALLBACK)) {
      gmlcPropertiesManagement.setLcsUrlCallback(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.MONGO_DB_HOST)) {
      gmlcPropertiesManagement.setMongoHost(options[3]);
    } else if (parameterName.equals(GmlcPropertiesManagement.MONGO_DB_PORT)) {
      int val = Integer.parseInt(options[3]);
      gmlcPropertiesManagement.setMongoPort(val);
    } else if (parameterName.equals(GmlcPropertiesManagement.MONGO_DB_DATABASE)) {
      gmlcPropertiesManagement.setMongoDatabase(options[3]);
    } else {
      return GmlcOAMMessages.INVALID_COMMAND;
    }

    return GmlcOAMMessages.PARAMETER_SUCCESSFULLY_SET;
  }

  private String manageGet(String[] options) throws Exception {
    if (options.length == 3) {
      String parameterName = options[2].toLowerCase();

      StringBuilder sb = new StringBuilder();
      sb.append(options[2]);
      sb.append(" = ");
      if (parameterName.equals(GmlcPropertiesManagement.GMLC_GT)) {
        sb.append(gmlcPropertiesManagement.getGmlcGt());
      } else if (parameterName.equals(GmlcPropertiesManagement.GMLC_SSN)) {
        sb.append(gmlcPropertiesManagement.getGmlcSsn());
      } else if (parameterName.equals(GmlcPropertiesManagement.HLR_SSN)) {
        sb.append(gmlcPropertiesManagement.getHlrSsn());
      } else if (parameterName.equals(GmlcPropertiesManagement.MSC_SSN)) {
        sb.append(gmlcPropertiesManagement.getMscSsn());
      } else if (parameterName.equals(GmlcPropertiesManagement.VLR_SSN)) {
        sb.append(gmlcPropertiesManagement.getVlrSsn());
      } else if (parameterName.equals(GmlcPropertiesManagement.MAX_MAP_VERSION)) {
        sb.append(gmlcPropertiesManagement.getMaxMapVersion());
      } else if (parameterName.equals(GmlcPropertiesManagement.MAX_ACTIVITY_COUNT)) {
        sb.append(gmlcPropertiesManagement.getMaxActivityCount());
      } else if (parameterName.equals(GmlcPropertiesManagement.DIALOG_TIMEOUT)) {
        sb.append(gmlcPropertiesManagement.getDialogTimeout());
      } else if (parameterName.equals(GmlcPropertiesManagement.EVENT_CONTEXT_SUSPEND_DELIVERY_TIMEOUT)) {
        sb.append(gmlcPropertiesManagement.getEventContextSuspendDeliveryTimeout());
      } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_ORIGIN_HOST)) {
        sb.append(gmlcPropertiesManagement.getDiameterOriginHost());
      } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_ORIGIN_REALM)) {
        sb.append(gmlcPropertiesManagement.getDiameterOriginRealm());
      } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_DEST_HOST)) {
        sb.append(gmlcPropertiesManagement.getDiameterDestHost());
      } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_DEST_REALM)) {
        sb.append(gmlcPropertiesManagement.getDiameterDestRealm());
      } else if (parameterName.equals(GmlcPropertiesManagement.DIAMETER_GMLC_NUMBER)) {
        sb.append(gmlcPropertiesManagement.getDiameterGmlcNumber());
      } else if (parameterName.equals(GmlcPropertiesManagement.LCS_NON_TRIGGERED_REPORT_OPTION)) {
        sb.append(gmlcPropertiesManagement.getLcsNonTriggeredReportOption());
      } else if (parameterName.equals(GmlcPropertiesManagement.LCS_URL_CALLBACK)) {
        sb.append(gmlcPropertiesManagement.getLcsUrlCallback());
      } else if (parameterName.equals(GmlcPropertiesManagement.MONGO_DB_HOST)) {
        sb.append(gmlcPropertiesManagement.getMongoHost());
      } else if (parameterName.equals(GmlcPropertiesManagement.MONGO_DB_PORT)) {
        sb.append(gmlcPropertiesManagement.getMongoPort());
      } else if (parameterName.equals(GmlcPropertiesManagement.MONGO_DB_DATABASE)) {
        sb.append(gmlcPropertiesManagement.getMongoDatabase());
      } else {
        return GmlcOAMMessages.INVALID_COMMAND;
      }

      return sb.toString();
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append(GmlcPropertiesManagement.GMLC_GT + " = ");
      sb.append(gmlcPropertiesManagement.getGmlcGt());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.GMLC_SSN + " = ");
      sb.append(gmlcPropertiesManagement.getGmlcSsn());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.HLR_SSN + " = ");
      sb.append(gmlcPropertiesManagement.getHlrSsn());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.MSC_SSN + " = ");
      sb.append(gmlcPropertiesManagement.getMscSsn());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.VLR_SSN + " = ");
      sb.append(gmlcPropertiesManagement.getVlrSsn());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.MAX_MAP_VERSION + " = ");
      sb.append(gmlcPropertiesManagement.getMaxMapVersion());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.MAX_ACTIVITY_COUNT + " = ");
      sb.append(gmlcPropertiesManagement.getMaxActivityCount());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.DIALOG_TIMEOUT + " = ");
      sb.append(gmlcPropertiesManagement.getDialogTimeout());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.EVENT_CONTEXT_SUSPEND_DELIVERY_TIMEOUT + " = ");
      sb.append(gmlcPropertiesManagement.getEventContextSuspendDeliveryTimeout());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.DIAMETER_ORIGIN_HOST + " = ");
      sb.append(gmlcPropertiesManagement.getDiameterOriginHost());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.DIAMETER_ORIGIN_REALM + " = ");
      sb.append(gmlcPropertiesManagement.getDiameterOriginRealm());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.DIAMETER_DEST_HOST + " = ");
      sb.append(gmlcPropertiesManagement.getDiameterDestHost());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.DIAMETER_DEST_REALM + " = ");
      sb.append(gmlcPropertiesManagement.getDiameterDestRealm());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.DIAMETER_GMLC_NUMBER + " = ");
      sb.append(gmlcPropertiesManagement.getDiameterGmlcNumber());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.LCS_NON_TRIGGERED_REPORT_OPTION + " = ");
      sb.append(gmlcPropertiesManagement.getLcsNonTriggeredReportOption());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.LCS_URL_CALLBACK + " = ");
      sb.append(gmlcPropertiesManagement.getLcsUrlCallback());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.MONGO_DB_HOST + " = ");
      sb.append(gmlcPropertiesManagement.getMongoHost());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.MONGO_DB_PORT + " = ");
      sb.append(gmlcPropertiesManagement.getMongoPort());
      sb.append("\n");

      sb.append(GmlcPropertiesManagement.MONGO_DB_DATABASE + " = ");
      sb.append(gmlcPropertiesManagement.getMongoDatabase());
      sb.append("\n");

      return sb.toString();
    }
  }
}
