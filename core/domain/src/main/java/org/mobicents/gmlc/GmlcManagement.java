package org.mobicents.gmlc;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.StandardMBean;

import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 *
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 *
 */
public class GmlcManagement implements GmlcManagementMBean {

  private static final Logger logger = Logger.getLogger(GmlcManagement.class);

  public static final String JMX_DOMAIN = "org.mobicents.gmlc";

  protected static final String GMLC_PERSIST_DIR_KEY = "gmlc.persist.dir";
  protected static final String USER_DIR_KEY = "user.dir";

  private String persistDir = null;
  private final String name;

  private GmlcPropertiesManagement gmlcPropertiesManagement = null;

  private MBeanServer mbeanServer = null;

  protected boolean isStarted;

  protected static GmlcManagement instance = null;

  public GmlcManagement(String name) {
    this.name = name;
  }

  public static GmlcManagement getInstance(String name) {
    if (instance == null) {
      instance = new GmlcManagement(name);
    }
    return instance;
  }

  public static GmlcManagement getInstance() {
    return instance;
  }

  public String getName() {
    return name;
  }

  public String getPersistDir() {
    return persistDir;
  }

  public void setPersistDir(String persistDir) {
    this.persistDir = persistDir;
  }

  public void start() throws Exception {
    this.gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance(this.name);
    this.gmlcPropertiesManagement.setPersistDir(this.persistDir);
    this.gmlcPropertiesManagement.start();

    // Register the MBeans
    boolean servFound = false;
    String agentId = "jboss";
    List<MBeanServer> servers = MBeanServerFactory.findMBeanServer(null);
    if (servers != null && servers.size() > 0) {
      for (MBeanServer server : servers) {
        String defaultDomain = server.getDefaultDomain();

        if (defaultDomain != null && defaultDomain.equals(agentId)) {
          mbeanServer = server;
          servFound = true;
          logger.info(String.format("Found MBeanServer matching for agentId=%s", agentId));
        } else {
          logger.warn(String.format("Found non-matching MBeanServer with default domain = %s", defaultDomain));
        }
      }
    }

    if (!servFound) {
      this.mbeanServer = ManagementFactory.getPlatformMBeanServer();
    }

    ObjectName gmlcPropObjNname = new ObjectName(GmlcManagement.JMX_DOMAIN + ":name=GmlcPropertiesManagement");
    StandardMBean gmlcPropMxBean = new StandardMBean(this.gmlcPropertiesManagement,
        GmlcPropertiesManagementMBean.class, true);
    this.mbeanServer.registerMBean(gmlcPropMxBean, gmlcPropObjNname);

    this.isStarted = true;

    logger.info("Started GMLC Management");
  }

  public void stop() throws Exception {
    this.gmlcPropertiesManagement.stop();

    if (this.mbeanServer != null) {

      ObjectName gmlcPropObjNname = new ObjectName(GmlcManagement.JMX_DOMAIN + ":name=GmlcPropertiesManagement");
      this.mbeanServer.unregisterMBean(gmlcPropObjNname);
    }

    this.isStarted = false;
  }

  @Override
  public boolean isStarted() {
    return isStarted;
  }

}