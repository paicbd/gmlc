package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.diameter.sh.ShUdaAvpValues;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.NRCellGlobalId;
import org.mobicents.gmlc.slee.primitives.RoutingAreaId;
import org.mobicents.gmlc.slee.primitives.TrackingAreaId;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;

import static org.mobicents.gmlc.slee.http.JsonWriter.writeAddressPresentationRestrictedIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAmfAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCSGId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCurrentLocationRetrieved;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeDaylightSavingTime;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeENBId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranEci;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeIMSPublicIdentity;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInternalNetworkNumberIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLocationNumberAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMscNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNatureOfAddressIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNrCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberingPlanIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOddFlag;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRatType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRoutingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningAndPresentationIndicators;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSmsfAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTimeZone;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTrackingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTypeOfShape;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertainty;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVisitedPlmnIdMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVisitedPlmnIdMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVlrNumber;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class UdrResponseJsonBuiler {

    protected static final Logger logger = Logger.getLogger(UdrResponseJsonBuiler.class);

    public UdrResponseJsonBuiler() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param uda       Sh-Data location information values gathered from UDA response event
     */
    public static String buildJsonResponseForUdr(ShUdaAvpValues uda) {

        Integer csMcc, csMnc, csLac, csCi, csEcgiMcc, csEcgiMnc, csEcgiCi, csTaiMcc, csTaiMnc, csTac, csSac, psMcc, psMnc, psLac, psCi, psSac, raiMcc, raiMnc, raiLac, rac, ecgiMcc,
            ecgiMnc, ecgiCi, taiMcc, taiMnc, tac, csAol, psAol, epsAol, sh5gsAol, natureOfAddressIndicator, numberingPlanIndicator, internalNetworkNumberIndicator,
            addressPresentationRestrictedIndicator, screeningIndicator, csConfidence, csScreeningAndPresentationIndicators, psConfidence, psScreeningAndPresentationIndicators,
            epsConfidence, epsScreeningAndPresentationIndicators, nrCgiMcc, nrCgiMnc, sh5gsEcgiMcc, sh5gsEcgiMnc, sh5gsEcgiCi, sh5gsTaiMcc, sh5gsTaiMnc, sh5gsTac,
            csDaylightSavingTime, psVPlmnIdMcc, psVPlmnIdMnc, epsVPlmnIdMcc, epsVPlmnIdMnc, sh5gsVPlmnIdMcc, sh5gsVPlmnIdMnc,
            psDaylightSavingTime, epsDaylightSavingTime, sh5gsDaylightSavingTime, psRatType, epsRatType, sh5gsRatType;
        csMcc = csMnc = csLac = csCi = csSac = csEcgiMcc = csEcgiMnc = csEcgiCi = csTaiMcc = csTaiMnc = csTac = psMcc = psMnc = psLac = psCi = psSac = raiMcc = raiMnc = raiLac = rac =
            ecgiMcc = ecgiMnc = ecgiCi = taiMcc = taiMnc = tac = csAol = psAol = epsAol = sh5gsAol = natureOfAddressIndicator = numberingPlanIndicator = internalNetworkNumberIndicator =
                addressPresentationRestrictedIndicator = screeningIndicator = csConfidence = csScreeningAndPresentationIndicators =
                    psConfidence = psScreeningAndPresentationIndicators = epsConfidence = epsScreeningAndPresentationIndicators =
                        nrCgiMcc = nrCgiMnc = sh5gsEcgiMcc = sh5gsEcgiMnc = sh5gsEcgiCi = sh5gsTaiMcc = sh5gsTaiMnc = sh5gsTac =
                            csDaylightSavingTime = psVPlmnIdMcc = psVPlmnIdMnc = epsVPlmnIdMcc = epsVPlmnIdMnc = sh5gsVPlmnIdMcc = sh5gsVPlmnIdMnc =
                                psDaylightSavingTime = epsDaylightSavingTime = sh5gsDaylightSavingTime = psRatType = epsRatType = sh5gsRatType = null;
        String msisdn, imsPublicId, locationNumberAddressDigits, csGeographicalTypeOfShape, csGeodeticTypeOfshape, psGeographicalTypeOfShape, psGeodeticTypeOfshape,
            epsGeographicalTypeOfShape, epsGeodeticTypeOfshape, sh5gsGeographicalTypeOfShape, mscNumberAddress, vlrNumberAddress, sgsnNumberAddress, mmeName, csCsgId,
            psCsgId, epsCsgId, csCurrentLocationInfoRetrieved, psCurrentLocationInfoRetrieved, epsCurrentLocationInfoRetrieved, sh5gsCurrentLocationInfoRetrieved, amfAddress,
            smsfAddress, csTimeZone, psTimeZone, epsTimeZone, sh5gsTimeZone;
        msisdn = imsPublicId = locationNumberAddressDigits = csGeographicalTypeOfShape = csGeodeticTypeOfshape = psGeographicalTypeOfShape = psGeodeticTypeOfshape =
            epsGeographicalTypeOfShape = epsGeodeticTypeOfshape = sh5gsGeographicalTypeOfShape = mscNumberAddress = vlrNumberAddress = sgsnNumberAddress = mmeName = csCsgId
                = psCsgId = epsCsgId = csCurrentLocationInfoRetrieved = psCurrentLocationInfoRetrieved = epsCurrentLocationInfoRetrieved = sh5gsCurrentLocationInfoRetrieved =
                amfAddress = smsfAddress = csTimeZone = psTimeZone = epsTimeZone = sh5gsTimeZone = null;
        Boolean oddFlag = null, cgi = null, sai = null, lai = null;
        Double csGeographicalLatitude, csGeographicalLongitude, csGeographicalUncertainty, csGeodeticLatitude, csGeodeticLongitude, csGeodeticUncertainty;
        csGeographicalLatitude = csGeographicalLongitude = csGeographicalUncertainty = csGeodeticLatitude = csGeodeticLongitude = csGeodeticUncertainty = null;
        Double psGeographicalLatitude, psGeographicalLongitude, psGeographicalUncertainty, psGeodeticLatitude, psGeodeticLongitude, psGeodeticUncertainty;
        psGeographicalLatitude = psGeographicalLongitude = psGeographicalUncertainty = psGeodeticLatitude = psGeodeticLongitude = psGeodeticUncertainty = null;
        Double epsGeographicalLatitude, epsGeographicalLongitude, epsGeographicalUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, epsGeodeticUncertainty;
        epsGeographicalLatitude = epsGeographicalLongitude = epsGeographicalUncertainty = epsGeodeticLatitude = epsGeodeticLongitude = epsGeodeticUncertainty = null;
        Double sh5gsGeographicalLatitude, sh5gsGeographicalLongitude, sh5gsGeographicalUncertainty;
        sh5gsGeographicalLatitude = sh5gsGeographicalLongitude = sh5gsGeographicalUncertainty = null;
        Long eci = null, eNBId = null, csEci = null, csENBId = null, nrCgiNci = null, sh5gsEci = null, sh5gsENBid = null;

        JsonObject udaJsonObject = new JsonObject();
        writeNetwork("IMS", udaJsonObject);
        writeProtocol("Diameter Sh", udaJsonObject);
        writeOperation("UDR-UDA", udaJsonObject);
        writeOperationResult("SUCCESS", udaJsonObject);
        JsonObject publicIdentifiersJsonObject = new JsonObject();
        JsonObject csLocationInfoJsonObject = new JsonObject();
        JsonObject csEpsLocationInfoJsonObject = new JsonObject();
        JsonObject psLocationInfoJsonObject = new JsonObject();
        JsonObject epsLocationInfoJsonObject = new JsonObject();
        JsonObject sh5gsLocationInfoJsonObject = new JsonObject();
        JsonObject locationNumberJsonObject = new JsonObject();
        JsonObject csCgiOrSaiOrLaiJsonObject = new JsonObject();
        JsonObject csGeographicalInfoJsonObject = new JsonObject();
        JsonObject csGeodeticInfoJsonObject= new JsonObject();
        JsonObject routingAreaIdJsonObject = new JsonObject();
        JsonObject psCgiOrSaiOrLaiJsonObject = new JsonObject();
        JsonObject psGeographicalInfoJsonObject = new JsonObject();
        JsonObject psGeodeticInfoJsonObject= new JsonObject();
        JsonObject csTrackingAreaIdJsonObject = new JsonObject();
        JsonObject epsTrackingAreaIdJsonObject = new JsonObject();
        JsonObject sh5gsTrackingAreaIdJsonObject = new JsonObject();
        JsonObject csEUtranCgiJsonObject = new JsonObject();
        JsonObject epsEUtranCgiJsonObject = new JsonObject();
        JsonObject sh5gsEUtranCgiJsonObject = new JsonObject();
        JsonObject epsGeographicalInfoJsonObject = new JsonObject();
        JsonObject epsGeodeticInfoJsonObject = new JsonObject();
        JsonObject nrCgiJsonObject = new JsonObject();
        JsonObject sh5gsGeographicalInfoJsonObject = new JsonObject();
        JsonObject psVisitedPlmnIdJsonObject = new JsonObject();
        JsonObject epsVisitedPlmnIdJsonObject = new JsonObject();
        JsonObject sh5gsVisitedPlmnIdJsonObject = new JsonObject();
        JsonObject csLocalTimeZoneJsonObject = new JsonObject();
        JsonObject psLocalTimeZoneJsonObject = new JsonObject();
        JsonObject epsLocalTimeZoneJsonObject = new JsonObject();
        JsonObject sh5gsLocalTimeZoneJsonObject = new JsonObject();

        if (uda != null) {
            // Get User-Data-Answer values
            if (uda.getUserData() != null) {
                // Public Identifiers
                if (uda.getImsPublicIdentity() != null) {
                    imsPublicId = uda.getImsPublicIdentity();
                    writeIMSPublicIdentity(imsPublicId, publicIdentifiersJsonObject);
                }
                if (uda.getMsisdn() != null) {
                    msisdn = uda.getMsisdn();
                    writeMsisdn(msisdn, publicIdentifiersJsonObject);
                }

                // CS Location Information
                if (uda.getCsLocationInformation() != null) {
                    if (uda.getLocationNumber() != null) {
                        LocationNumber locationNumber = uda.getLocationNumber().getLocationNumber();
                        oddFlag = locationNumber.isOddFlag();
                        natureOfAddressIndicator = locationNumber.getNatureOfAddressIndicator();
                        internalNetworkNumberIndicator = locationNumber.getInternalNetworkNumberIndicator();
                        numberingPlanIndicator = locationNumber.getNumberingPlanIndicator();
                        addressPresentationRestrictedIndicator = locationNumber.getAddressRepresentationRestrictedIndicator();
                        screeningIndicator = locationNumber.getScreeningIndicator();
                        locationNumberAddressDigits = locationNumber.getAddress();
                        if (oddFlag != null)
                            writeOddFlag(oddFlag, locationNumberJsonObject);
                        writeNatureOfAddressIndicator(natureOfAddressIndicator, locationNumberJsonObject);
                        writeInternalNetworkNumberIndicator(internalNetworkNumberIndicator, locationNumberJsonObject);
                        writeNumberingPlanIndicator(numberingPlanIndicator, locationNumberJsonObject);
                        writeAddressPresentationRestrictedIndicator(addressPresentationRestrictedIndicator, locationNumberJsonObject);
                        writeScreeningIndicator(screeningIndicator, locationNumberJsonObject);
                        writeLocationNumberAddress(locationNumberAddressDigits, locationNumberJsonObject);
                        csLocationInfoJsonObject.add("LocationNumber", locationNumberJsonObject);
                    }
                    if (uda.getCsCellGlobalId() != null) {
                        cgi = true;
                        CellGlobalIdOrServiceAreaIdFixedLength csCgi = uda.getCsCellGlobalId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            csMcc = csCgi.getMCC();
                            csMnc = csCgi.getMNC();
                            csLac = csCgi.getLac();
                            csCi = csCgi.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getCsServiceAreaId() != null) {
                        sai = true;
                        CellGlobalIdOrServiceAreaIdFixedLength csSai = uda.getCsServiceAreaId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            csMcc = csSai.getMCC();
                            csMnc = csSai.getMNC();
                            csLac = csSai.getLac();
                            csSac = csSai.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getCsLocationAreaId() != null) {
                        lai = true;
                        LAIFixedLength csLai = uda.getCsLocationAreaId().getLaiFixedLength();
                        try {
                            csMcc = csLai.getMCC();
                            csMnc = csLai.getMNC();
                            csLac = csLai.getLac();
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (csMcc != null)
                        writeMcc(csMcc, csCgiOrSaiOrLaiJsonObject);
                    if (csMnc != null)
                        writeMnc(csMnc, csCgiOrSaiOrLaiJsonObject);
                    if (csLac != null)
                        writeLac(csLac, csCgiOrSaiOrLaiJsonObject);
                    if (cgi != null) {
                        writeCellId(csCi, csCgiOrSaiOrLaiJsonObject);
                        csLocationInfoJsonObject.add("CGI", csCgiOrSaiOrLaiJsonObject);
                    } else if (sai != null) {
                        writeServiceAreaCode(csSac, csCgiOrSaiOrLaiJsonObject);
                        csLocationInfoJsonObject.add("SAI", csCgiOrSaiOrLaiJsonObject);
                    } else if (lai != null) {
                        csLocationInfoJsonObject.add("LAI", csCgiOrSaiOrLaiJsonObject);
                    }
                    if (uda.getCsGeographicalInformation() != null) {
                        if (uda.getCsGeographicalInformation().getGeographicalInformation() != null) {
                            csGeographicalTypeOfShape = uda.getCsGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            csGeographicalLatitude = uda.getCsGeographicalInformation().getGeographicalInformation().getLatitude();
                            csGeographicalLongitude = uda.getCsGeographicalInformation().getGeographicalInformation().getLongitude();
                            csGeographicalUncertainty = uda.getCsGeographicalInformation().getGeographicalInformation().getUncertainty();
                            writeTypeOfShape(csGeographicalTypeOfShape, csGeographicalInfoJsonObject);
                            writeLatitude(csGeographicalLatitude, csGeographicalInfoJsonObject);
                            writeLongitude(csGeographicalLongitude, csGeographicalInfoJsonObject);
                            writeUncertainty(csGeographicalUncertainty, csGeographicalInfoJsonObject);
                            csLocationInfoJsonObject.add("GeographicalInformation", csGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getCsGeodeticInformation() != null) {
                        if (uda.getCsGeodeticInformation().getGeodeticInformation() != null) {
                            csGeodeticTypeOfshape = uda.getCsGeodeticInformation().getGeodeticInformation().getTypeOfShape().name();
                            csGeodeticLatitude = uda.getCsGeodeticInformation().getGeodeticInformation().getLatitude();
                            csGeodeticLongitude = uda.getCsGeodeticInformation().getGeodeticInformation().getLongitude();
                            csGeodeticUncertainty = uda.getCsGeodeticInformation().getGeodeticInformation().getUncertainty();
                            csConfidence = uda.getCsGeodeticInformation().getGeodeticInformation().getConfidence();
                            csScreeningAndPresentationIndicators = uda.getCsGeodeticInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                            writeTypeOfShape(csGeodeticTypeOfshape, csGeodeticInfoJsonObject);
                            writeLatitude(csGeodeticLatitude, csGeodeticInfoJsonObject);
                            writeLongitude(csGeodeticLongitude, csGeodeticInfoJsonObject);
                            writeUncertainty(csGeodeticUncertainty, csGeodeticInfoJsonObject);
                            writeConfidence(csConfidence, csGeodeticInfoJsonObject);
                            writeScreeningAndPresentationIndicators(csScreeningAndPresentationIndicators, csGeodeticInfoJsonObject);
                            csLocationInfoJsonObject.add("GeodeticInformation", csGeodeticInfoJsonObject);
                        }
                    }
                    if (uda.getMscNumber() != null) {
                        mscNumberAddress = uda.getMscNumber().getAddress();
                        writeMscNumber(mscNumberAddress, csLocationInfoJsonObject);
                    }
                    if (uda.getVlrNumber() != null) {
                        vlrNumberAddress = uda.getVlrNumber().getAddress();
                        writeVlrNumber(vlrNumberAddress, csLocationInfoJsonObject);
                    }
                    if (uda.getCsCurrentLocationInfoRetrieved() != null) {
                        csCurrentLocationInfoRetrieved = uda.getCsCurrentLocationInfoRetrieved();
                        if (csCurrentLocationInfoRetrieved.equalsIgnoreCase("0")||
                            csCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            csCurrentLocationInfoRetrieved = "true";
                        else if (csCurrentLocationInfoRetrieved.equalsIgnoreCase("1")||
                            csCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            csCurrentLocationInfoRetrieved = "false";
                        if (csCurrentLocationInfoRetrieved != null)
                            writeCurrentLocationRetrieved(Boolean.valueOf(csCurrentLocationInfoRetrieved), csLocationInfoJsonObject);
                    }
                    if (uda.getCsAgeOfLocationInfo() != null) {
                        csAol = uda.getCsAgeOfLocationInfo();
                        writeAol(csAol, csLocationInfoJsonObject);
                        if (csAol > 0)
                            csCurrentLocationInfoRetrieved = "false";
                        if (csCurrentLocationInfoRetrieved != null)
                            writeCurrentLocationRetrieved(Boolean.valueOf(csCurrentLocationInfoRetrieved), csLocationInfoJsonObject);
                    }
                    if (uda.getUserCSGInformation() != null) {
                        csCsgId = uda.getUserCSGInformation().getUserCSGInformationStr();
                        writeCSGId(csCsgId, csLocationInfoJsonObject);
                    }
                    if (uda.getEutrancgi() != null) {
                        EUTRANCGI eUtranCgi = uda.getEutrancgi().getEutranCgi();
                        try {
                            csEcgiMcc = eUtranCgi.getMCC();
                            csEcgiMnc = eUtranCgi.getMNC();
                            csENBId = eUtranCgi.getENodeBId();
                            csEci = eUtranCgi.getEci();
                            csEcgiCi = eUtranCgi.getCi();
                            writeMcc(csEcgiMcc, csEUtranCgiJsonObject);
                            writeMnc(csEcgiMnc, csEUtranCgiJsonObject);
                            writeEUtranEci(csEci, csEUtranCgiJsonObject);
                            writeENBId(csENBId, csEUtranCgiJsonObject);
                            writeEUtranCellId(csEcgiCi, csEUtranCgiJsonObject);
                            csEpsLocationInfoJsonObject.add("ECGI", csEUtranCgiJsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getTrackingAreaId() != null) {
                        TrackingAreaId trackingAreaId = uda.getTrackingAreaId().getTrackingAreaId();
                        try {
                            csTaiMcc = trackingAreaId.getMCC();
                            csTaiMnc = trackingAreaId.getMNC();
                            csTac = trackingAreaId.getTAC();
                            writeMcc(csTaiMcc, csTrackingAreaIdJsonObject);
                            writeMnc(csTaiMnc, csTrackingAreaIdJsonObject);
                            writeTrackingAreaCode(csTac, csTrackingAreaIdJsonObject);
                            csEpsLocationInfoJsonObject.add("TAI", csTrackingAreaIdJsonObject);
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getCsLocalTimeZone() != null) {
                        csTimeZone = uda.getCsLocalTimeZone().getTimeZone();
                        writeTimeZone(csTimeZone, csLocalTimeZoneJsonObject);
                        csDaylightSavingTime = uda.getCsLocalTimeZone().getDaylightSavingTime();
                        writeDaylightSavingTime(csDaylightSavingTime, csLocalTimeZoneJsonObject);
                        csEpsLocationInfoJsonObject.add("LocalTimeZone", csLocalTimeZoneJsonObject);
                    }
                    // Write EPS Location Information values from CS Location Information extension
                    csLocationInfoJsonObject.add("EPSLocationInformation", csEpsLocationInfoJsonObject);
                }

                // PS Location Information
                if (uda.getPsLocationInformation() != null) {
                    if (uda.getPsCellGlobalId() != null) {
                        cgi = true;
                        CellGlobalIdOrServiceAreaIdFixedLength psCgi = uda.getPsCellGlobalId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            psMcc = psCgi.getMCC();
                            psMnc = psCgi.getMNC();
                            psLac = psCgi.getLac();
                            psCi = psCgi.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getPsServiceAreaId() != null) {
                        sai = true;
                        CellGlobalIdOrServiceAreaIdFixedLength psSai = uda.getPsServiceAreaId().getCellGlobalIdOrServiceAreaIdFixedLength();
                        try {
                            psMcc = psSai.getMCC();
                            psMnc = psSai.getMNC();
                            psLac = psSai.getLac();
                            psSac = psSai.getCellIdOrServiceAreaCode();
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getPsLocationAreaId() != null) {
                        lai = true;
                        LAIFixedLength psLai = uda.getPsLocationAreaId().getLaiFixedLength();
                        try {
                            psMcc = psLai.getMCC();
                            psMnc = psLai.getMNC();
                            psLac = psLai.getLac();
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (psMcc != null)
                        writeMcc(psMcc, psCgiOrSaiOrLaiJsonObject);
                    if (psMnc != null)
                        writeMnc(psMnc, psCgiOrSaiOrLaiJsonObject);
                    if (psLac != null)
                        writeLac(psLac, psCgiOrSaiOrLaiJsonObject);
                    if (cgi != null) {
                        writeCellId(psCi, psCgiOrSaiOrLaiJsonObject);
                        psLocationInfoJsonObject.add("CGI", psCgiOrSaiOrLaiJsonObject);
                    } else if (sai != null) {
                        writeServiceAreaCode(psSac, psCgiOrSaiOrLaiJsonObject);
                        psLocationInfoJsonObject.add("SAI", psCgiOrSaiOrLaiJsonObject);
                    } else if (lai != null) {
                        psLocationInfoJsonObject.add("LAI", psCgiOrSaiOrLaiJsonObject);
                    }
                    if (uda.getRoutingAreaId() != null) {
                        RoutingAreaId raId = uda.getRoutingAreaId().getRoutingAreaIdentity();
                        try {
                            raiMcc = raId.getMCC();
                            raiMnc = raId.getMNC();
                            raiLac = raId.getLAC();
                            rac = raId.getRAC();
                            writeMcc(raiMcc, routingAreaIdJsonObject);
                            writeMnc(raiMnc, routingAreaIdJsonObject);
                            writeLac(raiLac, routingAreaIdJsonObject);
                            writeRoutingAreaCode(rac, routingAreaIdJsonObject);
                            psLocationInfoJsonObject.add("RAI", routingAreaIdJsonObject);
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getPsGeographicalInformation() != null) {
                        if (uda.getPsGeographicalInformation().getGeographicalInformation() != null) {
                            psGeographicalTypeOfShape = uda.getPsGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            psGeographicalLatitude = uda.getPsGeographicalInformation().getGeographicalInformation().getLatitude();
                            psGeographicalLongitude = uda.getPsGeographicalInformation().getGeographicalInformation().getLongitude();
                            psGeographicalUncertainty = uda.getPsGeographicalInformation().getGeographicalInformation().getUncertainty();
                            writeTypeOfShape(psGeographicalTypeOfShape, psGeographicalInfoJsonObject);
                            writeLatitude(psGeographicalLatitude, psGeographicalInfoJsonObject);
                            writeLongitude(psGeographicalLongitude, psGeographicalInfoJsonObject);
                            writeUncertainty(psGeographicalUncertainty, psGeographicalInfoJsonObject);
                            psLocationInfoJsonObject.add("GeographicalInformation", psGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getPsGeodeticInformation() != null) {
                        if (uda.getPsGeodeticInformation().getGeodeticInformation() != null) {
                            psGeodeticTypeOfshape = uda.getPsGeodeticInformation().getGeodeticInformation().getTypeOfShape().name();
                            psGeodeticLatitude = uda.getPsGeodeticInformation().getGeodeticInformation().getLatitude();
                            psGeodeticLongitude = uda.getPsGeodeticInformation().getGeodeticInformation().getLongitude();
                            psGeodeticUncertainty = uda.getPsGeodeticInformation().getGeodeticInformation().getUncertainty();
                            psConfidence = uda.getPsGeodeticInformation().getGeodeticInformation().getConfidence();
                            psScreeningAndPresentationIndicators = uda.getPsGeodeticInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                            writeTypeOfShape(psGeodeticTypeOfshape, psGeodeticInfoJsonObject);
                            writeLatitude(psGeodeticLatitude, psGeodeticInfoJsonObject);
                            writeLongitude(psGeodeticLongitude, psGeodeticInfoJsonObject);
                            writeUncertainty(psGeodeticUncertainty, psGeodeticInfoJsonObject);
                            writeConfidence(psConfidence, psGeodeticInfoJsonObject);
                            writeScreeningAndPresentationIndicators(psScreeningAndPresentationIndicators, psGeodeticInfoJsonObject);
                            psLocationInfoJsonObject.add("GeodeticInformation", psGeodeticInfoJsonObject);
                        }
                    }
                    if (uda.getSgsnNumber() != null) {
                        sgsnNumberAddress = uda.getSgsnNumber().getAddress();
                        writeSgsnNumber(sgsnNumberAddress, psLocationInfoJsonObject);
                    }
                    if (uda.getPsCurrentLocationInfoRetrieved() != null) {
                        psCurrentLocationInfoRetrieved = uda.getPsCurrentLocationInfoRetrieved();
                        if (psCurrentLocationInfoRetrieved.equalsIgnoreCase("0") ||
                            psCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            psCurrentLocationInfoRetrieved = "true";
                        else if (psCurrentLocationInfoRetrieved.equalsIgnoreCase("1") ||
                            psCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            psCurrentLocationInfoRetrieved = "false";
                        if (psCurrentLocationInfoRetrieved != null)
                            writeCurrentLocationRetrieved(Boolean.valueOf(psCurrentLocationInfoRetrieved), psLocationInfoJsonObject);
                    }
                    if (uda.getPsAgeOfLocationInfo() != null) {
                        psAol = uda.getPsAgeOfLocationInfo();
                        writeAol(psAol, psLocationInfoJsonObject);
                        if (psAol > 0) {
                            psCurrentLocationInfoRetrieved = "false";
                            if (psCurrentLocationInfoRetrieved != null)
                                writeCurrentLocationRetrieved(Boolean.valueOf(psCurrentLocationInfoRetrieved), psLocationInfoJsonObject);
                        }
                    }
                    if (uda.getUserCSGInformation() != null) {
                        psCsgId = uda.getUserCSGInformation().getUserCSGInformationStr();
                        writeCSGId(psCsgId, psLocationInfoJsonObject);
                    }
                    if (uda.getPsVisitedPLMNId() != null) {
                        if (uda.getPsVisitedPLMNId().getVisitedPlmnId() != null) {
                            psVPlmnIdMcc = uda.getPsVisitedPLMNId().getVisitedPlmnId().getMcc();
                            psVPlmnIdMnc = uda.getPsVisitedPLMNId().getVisitedPlmnId().getMnc();
                            writeVisitedPlmnIdMcc(psVPlmnIdMcc, psVisitedPlmnIdJsonObject);
                            writeVisitedPlmnIdMnc(psVPlmnIdMnc, psVisitedPlmnIdJsonObject);
                            psLocationInfoJsonObject.add("VisitedPLMNId", psVisitedPlmnIdJsonObject);
                        }
                    }
                    if (uda.getPsLocalTimeZone() != null) {
                        psTimeZone = uda.getPsLocalTimeZone().getTimeZone();
                        writeTimeZone(psTimeZone, psLocalTimeZoneJsonObject);
                        psDaylightSavingTime = uda.getPsLocalTimeZone().getDaylightSavingTime();
                        writeDaylightSavingTime(psDaylightSavingTime, psLocalTimeZoneJsonObject);
                        psLocationInfoJsonObject.add("LocalTimeZone", psLocalTimeZoneJsonObject);
                    }
                    if (uda.getPsRatType() != null) {
                        psRatType = uda.getPsRatType();
                        writeRatType(psRatType, psLocationInfoJsonObject);
                    }
                }

                // EPS Location Information
                if (uda.getEpsLocationInformation() != null) {
                    if (uda.getEutrancgi() != null) {
                        EUTRANCGI eUtranCgi = uda.getEutrancgi().getEutranCgi();
                        try {
                            ecgiMcc = eUtranCgi.getMCC();
                            ecgiMnc = eUtranCgi.getMNC();
                            eci = eUtranCgi.getEci();
                            eNBId = eUtranCgi.getENodeBId();
                            ecgiCi = eUtranCgi.getCi();
                            writeMcc(ecgiMcc, epsEUtranCgiJsonObject);
                            writeMnc(ecgiMnc, epsEUtranCgiJsonObject);
                            writeEUtranEci(eci, epsEUtranCgiJsonObject);
                            writeENBId(eNBId, epsEUtranCgiJsonObject);
                            writeEUtranCellId(ecgiCi, epsEUtranCgiJsonObject);
                            epsLocationInfoJsonObject.add("ECGI", epsEUtranCgiJsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getTrackingAreaId() != null) {
                        TrackingAreaId trackingAreaId = uda.getTrackingAreaId().getTrackingAreaId();
                        try {
                            taiMcc = trackingAreaId.getMCC();
                            taiMnc = trackingAreaId.getMNC();
                            tac = trackingAreaId.getTAC();
                            writeMcc(taiMcc, epsTrackingAreaIdJsonObject);
                            writeMnc(taiMnc, epsTrackingAreaIdJsonObject);
                            writeTrackingAreaCode(tac, epsTrackingAreaIdJsonObject);
                            epsLocationInfoJsonObject.add("TAI", epsTrackingAreaIdJsonObject);
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getEpsGeographicalInformation() != null) {
                        if (uda.getEpsGeographicalInformation().getGeographicalInformation() != null) {
                            epsGeographicalTypeOfShape = uda.getEpsGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            epsGeographicalLatitude = uda.getEpsGeographicalInformation().getGeographicalInformation().getLatitude();
                            epsGeographicalLongitude = uda.getEpsGeographicalInformation().getGeographicalInformation().getLongitude();
                            epsGeographicalUncertainty = uda.getEpsGeographicalInformation().getGeographicalInformation().getUncertainty();
                            writeTypeOfShape(epsGeographicalTypeOfShape, epsGeographicalInfoJsonObject);
                            writeLatitude(epsGeographicalLatitude, epsGeographicalInfoJsonObject);
                            writeLongitude(epsGeographicalLongitude, epsGeographicalInfoJsonObject);
                            writeUncertainty(epsGeographicalUncertainty, epsGeographicalInfoJsonObject);
                            epsLocationInfoJsonObject.add("GeographicalInformation", epsGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getEpsGeodeticInformation() != null) {
                        if (uda.getEpsGeodeticInformation().getGeodeticInformation() != null) {
                            epsGeodeticTypeOfshape = uda.getEpsGeodeticInformation().getGeodeticInformation().getTypeOfShape().name();
                            epsGeodeticLatitude = uda.getEpsGeodeticInformation().getGeodeticInformation().getLatitude();
                            epsGeodeticLongitude = uda.getEpsGeodeticInformation().getGeodeticInformation().getLongitude();
                            epsGeodeticUncertainty = uda.getEpsGeodeticInformation().getGeodeticInformation().getUncertainty();
                            epsConfidence = uda.getEpsGeodeticInformation().getGeodeticInformation().getConfidence();
                            epsScreeningAndPresentationIndicators = uda.getEpsGeodeticInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                            writeTypeOfShape(epsGeodeticTypeOfshape, epsGeodeticInfoJsonObject);
                            writeLatitude(epsGeodeticLatitude, epsGeodeticInfoJsonObject);
                            writeLongitude(epsGeodeticLongitude, epsGeodeticInfoJsonObject);
                            writeUncertainty(epsGeodeticUncertainty, epsGeodeticInfoJsonObject);
                            writeConfidence(epsConfidence, epsGeodeticInfoJsonObject);
                            writeScreeningAndPresentationIndicators(epsScreeningAndPresentationIndicators, epsGeodeticInfoJsonObject);
                            epsLocationInfoJsonObject.add("GeodeticInformation", epsGeodeticInfoJsonObject);
                        }
                    }
                    if (uda.getMmeName() != null) {
                        mmeName = uda.getMmeName();
                        writeMmeName(mmeName, epsLocationInfoJsonObject);
                    }
                    if (uda.getEpsCurrentLocationInfoRetrieved() != null) {
                        epsCurrentLocationInfoRetrieved = uda.getEpsCurrentLocationInfoRetrieved();
                        if (epsCurrentLocationInfoRetrieved.equalsIgnoreCase("0") ||
                            epsCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            epsCurrentLocationInfoRetrieved = "true";
                        else if (epsCurrentLocationInfoRetrieved.equalsIgnoreCase("1") ||
                            epsCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            epsCurrentLocationInfoRetrieved = "false";
                        if (epsCurrentLocationInfoRetrieved != null)
                            writeCurrentLocationRetrieved(Boolean.valueOf(epsCurrentLocationInfoRetrieved), epsLocationInfoJsonObject);
                    }
                    if (uda.getEpsAgeOfLocationInfo() != null) {
                        epsAol = uda.getEpsAgeOfLocationInfo();
                        writeAol(epsAol, epsLocationInfoJsonObject);
                        if (epsAol > 0) {
                            epsCurrentLocationInfoRetrieved = "false";
                            if (epsCurrentLocationInfoRetrieved != null)
                                writeCurrentLocationRetrieved(Boolean.valueOf(epsCurrentLocationInfoRetrieved), epsLocationInfoJsonObject);
                        }
                    }
                    if (uda.getUserCSGInformation() != null) {
                        epsCsgId = uda.getUserCSGInformation().getUserCSGInformationStr();
                        writeCSGId(epsCsgId, epsLocationInfoJsonObject);
                    }
                    if (uda.getEpsVisitedPLMNId() != null) {
                        if (uda.getEpsVisitedPLMNId().getVisitedPlmnId() != null) {
                            epsVPlmnIdMcc = uda.getEpsVisitedPLMNId().getVisitedPlmnId().getMcc();
                            epsVPlmnIdMnc = uda.getEpsVisitedPLMNId().getVisitedPlmnId().getMnc();
                            writeVisitedPlmnIdMcc(epsVPlmnIdMcc, epsVisitedPlmnIdJsonObject);
                            writeVisitedPlmnIdMnc(epsVPlmnIdMnc, epsVisitedPlmnIdJsonObject);
                            epsLocationInfoJsonObject.add("VisitedPLMNId", epsVisitedPlmnIdJsonObject);
                        }
                    }
                    if (uda.getEpsLocalTimeZone() != null) {
                        epsTimeZone = uda.getEpsLocalTimeZone().getTimeZone();
                        writeTimeZone(epsTimeZone, epsLocalTimeZoneJsonObject);
                        epsDaylightSavingTime = uda.getEpsLocalTimeZone().getDaylightSavingTime();
                        writeDaylightSavingTime(epsDaylightSavingTime, epsLocalTimeZoneJsonObject);
                        epsLocationInfoJsonObject.add("LocalTimeZone", epsLocalTimeZoneJsonObject);
                    }
                    if (uda.getEpsRatType() != null) {
                        epsRatType = uda.getEpsRatType();
                        writeRatType(epsRatType, epsLocationInfoJsonObject);
                    }
                }

                // 5GS Location Information
                if (uda.getSh5GSLocationInformation() != null) {
                    if (uda.getShNRCellGlobalId() != null) {
                        NRCellGlobalId nrCellGlobalId = uda.getShNRCellGlobalId().getNRCellGlobalId();
                        try {
                            nrCgiMcc = nrCellGlobalId.getMCC();
                            nrCgiMnc = nrCellGlobalId.getMNC();
                            nrCgiNci = nrCellGlobalId.getNCI();
                            writeMcc(nrCgiMcc, nrCgiJsonObject);
                            writeMnc(nrCgiMnc, nrCgiJsonObject);
                            writeNrCellId(nrCgiNci, nrCgiJsonObject);
                            sh5gsLocationInfoJsonObject.add("NCGI", nrCgiJsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getEutrancgi() != null) {
                        EUTRANCGI eUtranCgi = uda.getEutrancgi().getEutranCgi();
                        try {
                            sh5gsEcgiMcc = eUtranCgi.getMCC();
                            sh5gsEcgiMnc = eUtranCgi.getMNC();
                            sh5gsEci =eUtranCgi.getEci();
                            sh5gsENBid = eUtranCgi.getENodeBId();
                            sh5gsEcgiCi = eUtranCgi.getCi();
                            writeMcc(sh5gsEcgiMcc, sh5gsEUtranCgiJsonObject);
                            writeMnc(sh5gsEcgiMnc, sh5gsEUtranCgiJsonObject);
                            writeEUtranEci(sh5gsEci, sh5gsEUtranCgiJsonObject);
                            writeENBId(sh5gsENBid, sh5gsEUtranCgiJsonObject);
                            writeEUtranCellId(sh5gsEcgiCi, sh5gsEUtranCgiJsonObject);
                            sh5gsLocationInfoJsonObject.add("ECGI", sh5gsEUtranCgiJsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getTrackingAreaId() != null) {
                        TrackingAreaId trackingAreaId = uda.getTrackingAreaId().getTrackingAreaId();
                        try {
                            sh5gsTaiMcc = trackingAreaId.getMCC();
                            sh5gsTaiMnc = trackingAreaId.getMNC();
                            sh5gsTac = trackingAreaId.getTAC();
                            writeMcc(sh5gsTaiMcc, sh5gsTrackingAreaIdJsonObject);
                            writeMnc(sh5gsTaiMnc, sh5gsTrackingAreaIdJsonObject);
                            writeTrackingAreaCode(sh5gsTac, sh5gsTrackingAreaIdJsonObject);
                            sh5gsLocationInfoJsonObject.add("TAI", sh5gsTrackingAreaIdJsonObject);
                        } catch (MAPException e) {
                            e.printStackTrace();
                        }
                    }
                    if (uda.getSh5GSGeographicalInformation() != null) {
                        if (uda.getSh5GSGeographicalInformation().getGeographicalInformation() != null) {
                            sh5gsGeographicalTypeOfShape = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getTypeOfShape().name();
                            sh5gsGeographicalLatitude = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getLatitude();
                            sh5gsGeographicalLongitude = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getLongitude();
                            sh5gsGeographicalUncertainty = uda.getSh5GSGeographicalInformation().getGeographicalInformation().getUncertainty();
                            writeTypeOfShape(sh5gsGeographicalTypeOfShape, sh5gsGeographicalInfoJsonObject);
                            writeLatitude(sh5gsGeographicalLatitude, sh5gsGeographicalInfoJsonObject);
                            writeLongitude(sh5gsGeographicalLongitude, sh5gsGeographicalInfoJsonObject);
                            writeUncertainty(sh5gsGeographicalUncertainty, sh5gsGeographicalInfoJsonObject);
                            sh5gsLocationInfoJsonObject.add("GeographicalInformation", sh5gsGeographicalInfoJsonObject);
                        }
                    }
                    if (uda.getAmfAddress() != null) {
                        amfAddress = uda.getAmfAddress();
                        writeAmfAddress(amfAddress, sh5gsLocationInfoJsonObject);
                    }
                    if (uda.getSmsfAddress() != null) {
                        smsfAddress = uda.getSmsfAddress();
                        writeSmsfAddress(smsfAddress, sh5gsLocationInfoJsonObject);
                    }
                    if (uda.getSh5GSCurrentLocationInfoRetrieved() != null) {
                         sh5gsCurrentLocationInfoRetrieved = uda.getSh5GSCurrentLocationInfoRetrieved();
                        if (sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("0") ||
                            sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("true"))
                            sh5gsCurrentLocationInfoRetrieved = "true";
                        else if (sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("1") ||
                            sh5gsCurrentLocationInfoRetrieved.equalsIgnoreCase("false"))
                            sh5gsCurrentLocationInfoRetrieved = "false";
                        if (sh5gsCurrentLocationInfoRetrieved != null)
                            writeCurrentLocationRetrieved(Boolean.valueOf(sh5gsCurrentLocationInfoRetrieved), sh5gsLocationInfoJsonObject);
                    }
                    if (uda.getSh5GSAgeOfLocationInfo() != null) {
                        sh5gsAol = uda.getSh5GSAgeOfLocationInfo();
                        writeAol(sh5gsAol, sh5gsLocationInfoJsonObject);
                        if (sh5gsAol > 0) {
                            sh5gsCurrentLocationInfoRetrieved = "false";
                            if (sh5gsCurrentLocationInfoRetrieved != null)
                                writeCurrentLocationRetrieved(Boolean.valueOf(sh5gsCurrentLocationInfoRetrieved), sh5gsLocationInfoJsonObject);
                        }
                    }
                    if (uda.getSh5gsVisitedPLMNId() != null) {
                        if (uda.getSh5gsVisitedPLMNId().getVisitedPlmnId() != null) {
                            sh5gsVPlmnIdMcc = uda.getSh5gsVisitedPLMNId().getVisitedPlmnId().getMcc();
                            sh5gsVPlmnIdMnc = uda.getSh5gsVisitedPLMNId().getVisitedPlmnId().getMnc();
                            writeVisitedPlmnIdMcc(sh5gsVPlmnIdMcc, sh5gsVisitedPlmnIdJsonObject);
                            writeVisitedPlmnIdMnc(sh5gsVPlmnIdMnc, sh5gsVisitedPlmnIdJsonObject);
                            sh5gsLocationInfoJsonObject.add("VisitedPLMNId", sh5gsVisitedPlmnIdJsonObject);
                        }
                    }
                    if (uda.getSh5gsLocalTimeZone() != null) {
                        sh5gsTimeZone = uda.getSh5gsLocalTimeZone().getTimeZone();
                        writeTimeZone(sh5gsTimeZone, sh5gsLocalTimeZoneJsonObject);
                        sh5gsDaylightSavingTime = uda.getSh5gsLocalTimeZone().getDaylightSavingTime();
                        writeDaylightSavingTime(sh5gsDaylightSavingTime, sh5gsLocalTimeZoneJsonObject);
                        sh5gsLocationInfoJsonObject.add("LocalTimeZone", sh5gsLocalTimeZoneJsonObject);
                    }
                    if (uda.getSh5gsRatType() != null) {
                        sh5gsRatType = uda.getSh5gsRatType();
                        writeRatType(sh5gsRatType, sh5gsLocationInfoJsonObject);
                    }
                }
            }
        }

        // Write Public Identifiers values from UDA
        udaJsonObject.add("PublicIdentifiers", publicIdentifiersJsonObject);
        // Write CS Location Information values from UDA
        udaJsonObject.add("CSLocationInformation", csLocationInfoJsonObject);
        // Write PS Location Information values from UDA
        udaJsonObject.add("PSLocationInformation", psLocationInfoJsonObject);
        // Write EPS Location Information values from UDA
        udaJsonObject.add("EPSLocationInformation", epsLocationInfoJsonObject);
        // Write 5GS Location Information from UDA
        udaJsonObject.add("5GSLocationInformation", sh5gsLocationInfoJsonObject);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String udrResponseJson = gson.toJson(udaJsonObject);
        return udrResponseJson;

    }
}
