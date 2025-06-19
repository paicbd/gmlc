package org.mobicents.gmlc.slee.concurrent;

import com.objsys.asn1j.runtime.Asn1OctetString;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.GmlcPropertiesManagement;
import org.mobicents.gmlc.slee.supl.SuplSessionType;
import org.mobicents.gmlc.slee.supl.ULP_Components.IPAddress;
import org.mobicents.gmlc.slee.supl.ULP_Components.SLPAddress;
import org.mobicents.gmlc.slee.supl.ULP_Components.SlpSessionID;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:enmanuelcalero61@gmail.com"> Enmanuel Calero </a>
 */
public class SuplTransaction {

    protected Logger logger = Logger.getLogger(SuplTransaction.class);
    private static final GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();
    int globalTransactionIdentity = -1;
    int slpIdGlobalIdentity = -1;
    // Jedis jedisClient;
    Integer suplSessionExpired;
    Integer suplPeriodicSessionExpired;

    ConcurrentHashMap<String, DataElement> transactionDataElements;

    static SuplTransaction s_instance = null;

    public static SuplTransaction Instance() {
        if (s_instance == null)
            s_instance = new SuplTransaction();
        return s_instance;
    }

    private SuplTransaction() {
        suplSessionExpired = gmlcPropertiesManagement.getSuplSessionExpired();
        suplPeriodicSessionExpired = gmlcPropertiesManagement.getSuplPeriodicSessionExpired();
        transactionDataElements = new ConcurrentHashMap<>();
    }


    /*
     *  TRANSACTIONS
     */
    private String getSlpId() {
        DecimalFormat df = new DecimalFormat("0000");
        int transaction;
        String slpId;
        if (slpIdGlobalIdentity >= 9999) {
            slpIdGlobalIdentity = 0;
        }
        transaction = ++slpIdGlobalIdentity;
        ++globalTransactionIdentity;
        slpId = df.format(transaction);

        if (transactionDataElements.containsKey(slpId)) {
            slpId = getSlpId();
        }
        logger.info("SLP ID -> " + slpId);
        return slpId;
    }

    public SLPAddress getSLPAddress() {
        SLPAddress slpAddress = new SLPAddress();
        IPAddress ipAddress = new IPAddress();
        try {
            InetAddress host = InetAddress.getByName(gmlcPropertiesManagement.getFqdn());
            ipAddress.setIpv4Address(new Asn1OctetString(host.getAddress()));
        } catch (UnknownHostException e) {
            logger.error("Error on try to create the SlpSessionID " + e.getMessage());
        }
        logger.info("SLP Session Id with IP Address -> " + ipAddress.getIpv4Address());
        slpAddress.setIPAddress(ipAddress);
        return slpAddress;
    }
    public SlpSessionID create(SuplSessionType sessionType) {
        String slpId = getSlpId();
        SlpSessionID slpSessionId = new SlpSessionID();
        slpSessionId.setSessionID(new Asn1OctetString(slpId.getBytes(StandardCharsets.US_ASCII)));
        slpSessionId.setSlpId(getSLPAddress());
        if (logger.isInfoEnabled()) {
            logger.warn("\nCreated SUPL transaction with Id = " + slpId);
            logger.warn("\nglobalTransactionIdentity = " + globalTransactionIdentity);
        }
        Calendar expireSessionDate = Calendar.getInstance();
        if (sessionType == SuplSessionType.SUPL_PERIODIC) {
            expireSessionDate.add(Calendar.MINUTE, suplPeriodicSessionExpired);
        } else {
            expireSessionDate.add(Calendar.MINUTE, suplSessionExpired);
        }
        setValue(slpSessionId, "type", sessionType.toString());
        setValue(slpSessionId, "sessionStatus", SuplSessionStatus.SUPL_SESSION_ACTIVE.getSuplSessionStatus());
        return slpSessionId;
    }

    public void destroy(String slpId) {
        transactionDataElements.remove(slpId);
        logger.warn("\nDestroyed SUPL transaction data element for transaction = " + slpId);
    }


    /*
     *  DATA ELEMENTS
     */

    public Object getValue(String splId, String key) {
        Object value = null;
        if (transactionDataElements.containsKey(splId)) {
            DataElement dataElement;
            if (key != null) {
                dataElement = transactionDataElements.get(splId);
                value = dataElement.get(key);
            }
        }
        return value;
    }

    public void setValue(SlpSessionID slpSessionId, String key, Object value) {
        String slpId = new String(slpSessionId.getSessionID().value);
        DataElement dataElement;
        if (transactionDataElements.containsKey(slpId)) {
            dataElement = transactionDataElements.get(slpId);
            dataElement.set(key, value);
            transactionDataElements.replace(slpId, dataElement);
        } else {
            dataElement = new DataElement();
            dataElement.set(key, value);
            transactionDataElements.put(slpId, dataElement);
        }
        //setExpireSession(slpId);
    }


}


