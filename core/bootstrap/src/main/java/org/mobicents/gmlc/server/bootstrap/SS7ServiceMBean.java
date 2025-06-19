package org.mobicents.gmlc.server.bootstrap;

/**
 *
 * @author kulikov
 */
public interface SS7ServiceMBean {

    public static final String ONAME = "org.mobicents.ss7:service=SS7Service";

    public void start() throws Exception;
    public void stop();
     /**
      * Returns SCCP Provider jndi name.
      */
     public String getJndiName();

     /**
      * Returns SS7 Name for this release
      * @return
      */
     public String getSS7Name();

     /**
      * Get Vendor supporting this SS7
      * @return
      */
     public String getSS7Vendor();

     /**
      * Return SS7 version of this release
      * @return
      */
     public String getSS7Version();
}
