package org.mobicents.gmlc.server.bootstrap;

import org.apache.log4j.Logger;
import org.jboss.system.ServiceMBeanSupport;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author amit bhayani
 */
public class SS7Service extends ServiceMBeanSupport implements SS7ServiceMBean {

    private static final Logger logger = Logger.getLogger(SS7Service.class);

    private Object stack;

    private String jndiName;

    private static final String rLogo = " ]]]]]]]]] ";
    private static final String lLogo = " [[[[[[[[[ ";

    @Override
    public void startService() throws Exception {
        // starting
        rebind(stack);
        logger.info(generateMessageWithLogo("service started"));
    }

    private String generateMessageWithLogo(String message) {
        return lLogo + getSS7Name() + " " + getSS7Version() + " " + message + rLogo;
    }

    public String getSS7Name() {
        String name = Version.instance.getProperty("name");
        if (name != null) {
            return name;
        } else {
            return "Extended GMLC";
        }
    }

    public String getSS7Vendor() {
        String vendor = Version.instance.getProperty("vendor");
        if (vendor != null) {
            return vendor;
        } else {
            return "PAiC";
        }
    }

    public String getSS7Version() {
        String version = Version.instance.getProperty("version");
        if (version != null) {
            return version;
        } else {
            return "8.3";
        }
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public String getJndiName() {
        return jndiName;
    }

    public Object getStack() {
        return stack;
    }

    public void setStack(Object stack) {
        this.stack = stack;
    }

    @Override
    public void stopService() {
        try {
            unbind(jndiName);
        } catch (Exception e) {

        }

        logger.info(generateMessageWithLogo("service stopped"));
    }

    /**
     * Binds trunk object to the JNDI under the jndiName.
     */
    private void rebind(Object stack) throws NamingException {
        Context ctx = new InitialContext();
        String tokens[] = jndiName.split("/");

        for (int i = 0; i < tokens.length - 1; i++) {
            if (tokens[i].trim().length() > 0) {
                try {
                    ctx = (Context) ctx.lookup(tokens[i]);
                } catch (NamingException e) {
                    ctx = ctx.createSubcontext(tokens[i]);
                }
            }
        }

        ctx.bind(tokens[tokens.length - 1], stack);
    }

    /**
     * Unbinds object under specified name.
     *
     * @param jndiName
     *            the JNDI name of the object to be unbound.
     */
    private void unbind(String jndiName) throws NamingException {
        InitialContext initialContext = new InitialContext();
        initialContext.unbind(jndiName);
    }

}