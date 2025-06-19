package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.mobicents.gmlc.slee.map.SriForLcsResponseValues;
import org.mobicents.gmlc.slee.map.SriForSmResponseValues;
import org.mobicents.gmlc.slee.map.SriResponseValues;
import org.restcomm.protocols.ss7.map.api.MAPException;

import javax.xml.bind.DatatypeConverter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.mobicents.gmlc.slee.http.JsonWriter.bytesToHexString;
import static org.mobicents.gmlc.slee.http.JsonWriter.write3gppAaaServerName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGprsNodeIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeHGmlcAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLmsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpStatus;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetworkNodeNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writePprAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVGmlcAddress;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SriResponseJsonBuilder {

    public SriResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param msisdn        MSISDN value used on SRI/SRISM/SRILCS attempt
     * @param imsi          IMSI value used on SRI/SRISM/SRILCS attempt
     * @param sri           Subscriber Information values gathered from SRI response event
     * @param sriSm         Subscriber Information values gathered from SRISM response event
     * @param sriLcs        Subscriber Information values gathered from SRILCS response event
     */
    public static String buildJsonResponseForSri(String imsi, String msisdn, String operation, 
                                                 SriResponseValues sri, SriForSmResponseValues sriSm, 
                                                 SriForLcsResponseValues sriLcs) throws MAPException {

        Integer numberPortabilityStatusType;
        numberPortabilityStatusType = null;
        Boolean gprsNodeIndicator = null;
        String networkNodeNumber, lmsi, mmeName, sgsnName, tgppAAAServerName, hGmlcAddress, vGmlcAddress, pprAddress;
        networkNodeNumber = lmsi = mmeName = sgsnName = tgppAAAServerName = hGmlcAddress = vGmlcAddress = pprAddress = null;

        /***************************/
        /*** SRI response values ***/
        /***************************/
        if (sri != null) {
            if (sri.getImsi() != null)
                imsi = new String(sri.getImsi().getData().getBytes());

            if (sri.getMsisdn() != null)
                msisdn = sri.getMsisdn().getAddress();

            if (sri.getVmscAddress() != null)
                networkNodeNumber = sri.getVmscAddress().getAddress();

            if (sri.getNumberPortabilityStatus() != null) {
                if (sri.getNumberPortabilityStatus() != null) {
                    numberPortabilityStatusType = sri.getNumberPortabilityStatus().getType();
                }
            }
        }

        /*****************************/
        /*** SRISM response values ***/
        /*****************************/
        if (sriSm != null) {
            if (sriSm.getImsi() != null)
                imsi = new String(sriSm.getImsi().getData().getBytes());

            if (sriSm.getNetworkNodeNumber() != null)
                networkNodeNumber = sriSm.getNetworkNodeNumber().getAddress();

            if (sriSm.getLmsi() != null)
                lmsi = bytesToHex(sriSm.getLmsi().getData());
        }

        /******************************/
        /*** SRILCS response values ***/
        /******************************/
        if (sriLcs != null) {
            if (sriLcs.getMsisdn() != null)
                msisdn = sriLcs.getMsisdn().getAddress();

            if (sriLcs.getImsi() != null)
                imsi = new String(sriLcs.getImsi().getData().getBytes());

            if (sriLcs.getLmsi() != null)
                lmsi = bytesToHex(sriLcs.getLmsi().getData());

            if (sriLcs.getNetworkNodeNumber() != null)
                networkNodeNumber = sriLcs.getNetworkNodeNumber().getAddress();

            if (sriLcs.isGprsNodeIndicator() != null)
                gprsNodeIndicator = sriLcs.isGprsNodeIndicator();

            if (sriLcs.getMmeName() != null)
                mmeName = new String(sriLcs.getMmeName().getData());

            if (sriLcs.getSgsnName() != null)
                sgsnName = new String(sriLcs.getSgsnName().getData());

            if (sriLcs.getAaaServerName() != null) {
                tgppAAAServerName = new String(sriLcs.getAaaServerName().getData());
            }

            if (sriLcs.gethGmlcAddress() != null) {
                hGmlcAddress = bytesToHexString(sriLcs.gethGmlcAddress().getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(hGmlcAddress));
                    hGmlcAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }

            if (sriLcs.getvGmlcAddress() != null) {
                vGmlcAddress = bytesToHexString(sriLcs.getvGmlcAddress().getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(vGmlcAddress));
                    vGmlcAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }

            if (sriLcs.getPprAddress() != null) {
                pprAddress = bytesToHexString(sriLcs.getPprAddress().getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(pprAddress));
                    pprAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        JsonObject sriResponseJsonObject = new JsonObject();
        writeNetwork("GSM/UMTS", sriResponseJsonObject);
        writeProtocol("MAP", sriResponseJsonObject);
        writeOperation(operation, sriResponseJsonObject);
        writeMsisdn(msisdn, sriResponseJsonObject);
        writeImsi(imsi, sriResponseJsonObject);
        writeLmsi(lmsi, sriResponseJsonObject);
        writeNetworkNodeNumber(networkNodeNumber, sriResponseJsonObject);
        writeMmeName(mmeName, sriResponseJsonObject);
        writeSgsnName(sgsnName, sriResponseJsonObject);
        write3gppAaaServerName(tgppAAAServerName, sriResponseJsonObject);
        writePprAddress(pprAddress, sriResponseJsonObject);
        writeGprsNodeIndicator(gprsNodeIndicator, sriResponseJsonObject);
        writeHGmlcAddress(hGmlcAddress, sriResponseJsonObject);
        writeVGmlcAddress(vGmlcAddress, sriResponseJsonObject);
        writeMnpStatus(numberPortabilityStatusType, sriResponseJsonObject);
        writeOperationResult("SUCCESS", sriResponseJsonObject);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String errorReportJson = gson.toJson(sriResponseJsonObject);
        return errorReportJson;
    }

}
