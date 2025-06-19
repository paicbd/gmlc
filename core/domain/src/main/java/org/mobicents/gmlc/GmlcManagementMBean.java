package org.mobicents.gmlc;

/**
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 */
public interface GmlcManagementMBean {

  String getName();

  String getPersistDir();

  void setPersistDir(String persistDir);

  boolean isStarted();

}
