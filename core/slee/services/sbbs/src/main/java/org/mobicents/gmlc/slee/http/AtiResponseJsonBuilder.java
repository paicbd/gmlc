package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.map.AtiResponseValues;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.mobicents.gmlc.slee.primitives.RoutingAreaId;
import org.mobicents.gmlc.slee.primitives.RoutingAreaIdImpl;
import org.mobicents.gmlc.slee.primitives.TrackingAreaId;
import org.mobicents.gmlc.slee.primitives.TrackingAreaIdImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.LSAIdentityImpl;

import static org.mobicents.gmlc.slee.http.JsonWriter.bytesToHexString;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAddressPresentationRestrictedIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCurrentLocationRetrieved;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeENBId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranEci;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImei;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInternalNetworkNumberIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLocationNumberAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLsaId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLsaLSB;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMSClassmark;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMSNetworkCapability;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMSRadioAccessCapability;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpRouteingNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnpStatus;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMscNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNatureOfAddressIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNotReachableReason;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberingPlanIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOddFlag;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRoutingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSaiPresent;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningAndPresentationIndicators;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeScreeningIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSubscriberState;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTrackingAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTypeOfShape;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertainty;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVlrNumber;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class AtiResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(AtiResponseJsonBuilder.class);

    public AtiResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param atiResponseValues values gathered from MAP ATI response event
     */
    public static String buildJsonResponseForAti(AtiResponseValues atiResponseValues, String atiMsisdnDigits, String dialogErrorMessage) throws MAPException {

        Integer csMcc, csMnc, csLac, csCiOrSac, psMcc, psMnc, psLac, psCiOrSac, ecgiMcc, ecgiMnc, ecgiCi, taiMcc, taiMnc, tac, raiMcc, raiMnc, raiLac, rac,
            ageOfLocationInfo, natureOfAddressIndicator, internalNetworkNumberIndicator, numberingPlanIndicator, addressPresentationRestrictedIndicator,
            screeningIndicator, geodeticConfidence, geodeticScreeningAndPresentationIndicators, epsGeodeticConfidence, epsGeodeticScreeningAndPresentationIndicators,
            gprsGeodeticConfidence, gprsGeodeticScreeningAndPresentationIndicators, mnpInfoResultNumberPortabilityStatus;
        csMcc = csMnc = csLac = csCiOrSac = psMcc = psMnc = psLac = psCiOrSac = ecgiMcc = ecgiMnc = ecgiCi = taiMcc = taiMnc = tac = raiMcc = raiMnc = raiLac = rac =
            ageOfLocationInfo = natureOfAddressIndicator = internalNetworkNumberIndicator = numberingPlanIndicator = addressPresentationRestrictedIndicator =
                screeningIndicator = geodeticConfidence = geodeticScreeningAndPresentationIndicators = epsGeodeticConfidence =
                    epsGeodeticScreeningAndPresentationIndicators = gprsGeodeticConfidence = gprsGeodeticScreeningAndPresentationIndicators =
                        mnpInfoResultNumberPortabilityStatus = null;
        String vlrNumber, mscNumber, csSubscriberState, psSubscriberState, notReachableReason, sgsnNumber, locationNumberAddressDigits, geographicalTypeOfShape, geodeticTypeOfShape,
            gprsGeographicalTypeOfShape, gprsGeodeticTypeOfShape, epsGeographicalTypeOfShape, epsGeodeticTypeOfShape, mnpInfoResultMSISDN, mnpInfoResultIMSI,
            mnpInfoResultRouteingNumber, mmeName, lsaId, msClassmark, msNetCap, msRASCap, imei;
        vlrNumber = mscNumber = csSubscriberState = psSubscriberState = notReachableReason = sgsnNumber = locationNumberAddressDigits = geographicalTypeOfShape = geodeticTypeOfShape =
            gprsGeographicalTypeOfShape = gprsGeodeticTypeOfShape = epsGeographicalTypeOfShape = epsGeodeticTypeOfShape = mnpInfoResultMSISDN = mnpInfoResultIMSI =
                mnpInfoResultRouteingNumber = mmeName = lsaId = msClassmark = msNetCap = msRASCap = imei = null;
        Double geographicalLatitude, geographicalLongitude, geographicalUncertainty, geodeticLatitude, geodeticLongitude, geodeticUncertainty;
        geographicalLatitude = geographicalLongitude = geographicalUncertainty = geodeticLatitude = geodeticLongitude = geodeticUncertainty = null;
        Double epsGeographicalLatitude, epsGeographicalLongitude, epsGeographicalUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, epsGeodeticUncertainty;
        epsGeographicalLatitude = epsGeographicalLongitude = epsGeographicalUncertainty = epsGeodeticLatitude = epsGeodeticLongitude = epsGeodeticUncertainty = null;
        Double gprsGeographicalLatitude, gprsGeographicalLongitude, gprsGeographicalUncertainty, gprsGeodeticLatitude, gprsGeodeticLongitude, gprsGeodeticUncertainty;
        gprsGeographicalLatitude = gprsGeographicalLongitude = gprsGeographicalUncertainty = gprsGeodeticLatitude = gprsGeodeticLongitude = gprsGeodeticUncertainty = null;
        Long ecgiEci = null, ecgiENBId = null;
        Boolean oddFlag, lsaUniversal, currentLocationRetrieved, saiPresent;
        oddFlag = lsaUniversal = currentLocationRetrieved = null;
        saiPresent = false;

        JsonObject atiResponseJsonObject = new JsonObject();
        writeNetwork("GSM/UMTS", atiResponseJsonObject);
        writeProtocol("MAP", atiResponseJsonObject);
        writeOperation("ATI", atiResponseJsonObject);
        writeOperationResult("SUCCESS", atiResponseJsonObject);
        JsonObject atiLocationInformationJsonObject = new JsonObject();
        JsonObject atiLocationInformationEPSJsonObject = new JsonObject();
        JsonObject atiLocationInformationGPRSJsonObject = new JsonObject();
        JsonObject csCgiOrLaiOrSaiJsonObject = new JsonObject();
        JsonObject psCgiOrLaiOrSaiJsonObject = new JsonObject();
        JsonObject locationNumberJsonObject = new JsonObject();
        JsonObject eUtranCgiJsonObject = new JsonObject();
        JsonObject raiJsonObject = new JsonObject();
        JsonObject lsaJsonObject = new JsonObject();
        JsonObject taiJsonObject = new JsonObject();
        JsonObject atiCsGeographicalInformationJsonObject = new JsonObject();
        JsonObject atiCsGeodeticInformationJsonObject = new JsonObject();
        JsonObject atiEpsGeographicalInformationJsonObject = new JsonObject();
        JsonObject atiEpsGeodeticInformationJsonObject = new JsonObject();
        JsonObject atiGprsGeographicalInformationJsonObject = new JsonObject();
        JsonObject atiGprsGeodeticInformationJsonObject = new JsonObject();
        JsonObject atiMnpInfoResultJsonObject = new JsonObject();
        JsonObject atiGprsMsClassJsonObject = new JsonObject();

        if (atiResponseValues != null) {

            if (atiResponseValues.isSaiPresent())
                saiPresent = true;

            if (atiResponseValues.getLocationInformation() != null) {

                if (atiResponseValues.getLocationInformation().getLocationNumber() != null) {
                    if (atiResponseValues.getLocationInformation().getLocationNumber().getLocationNumber() != null) {
                        LocationNumber locationNumber = atiResponseValues.getLocationInformation().getLocationNumber().getLocationNumber();
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
                    }
                }
                atiLocationInformationJsonObject.add("LocationNumber", locationNumberJsonObject);

                if (atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                    if (atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                        csMcc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                        csMnc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                        csLac = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                    } else if (atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                        csMcc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                        csMnc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                        csLac = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                        csCiOrSac = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                    }
                    writeMcc(csMcc, csCgiOrLaiOrSaiJsonObject);
                    writeMnc(csMnc, csCgiOrLaiOrSaiJsonObject);
                    writeLac(csLac, csCgiOrLaiOrSaiJsonObject);
                    if (!saiPresent) {
                        if (csCiOrSac != null) {
                            writeCellId(csCiOrSac, csCgiOrLaiOrSaiJsonObject);
                            atiLocationInformationJsonObject.add("CGI", csCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (csLac != null)
                                atiLocationInformationJsonObject.add("LAI", csCgiOrLaiOrSaiJsonObject);
                        }
                    } else {
                        if (csCiOrSac != null) {
                            writeServiceAreaCode(csCiOrSac, csCgiOrLaiOrSaiJsonObject);
                            atiLocationInformationJsonObject.add("SAI", csCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (csLac != null)
                                atiLocationInformationJsonObject.add("LAI", csCgiOrLaiOrSaiJsonObject);
                        }
                    }
                }
                if (csCiOrSac != null)
                    writeSaiPresent(saiPresent, atiLocationInformationJsonObject);

                if (atiResponseValues.getLocationInformation().getGeographicalInformation() != null) {
                    geographicalLatitude = atiResponseValues.getLocationInformation().getGeographicalInformation().getLatitude();
                    geographicalLongitude = atiResponseValues.getLocationInformation().getGeographicalInformation().getLongitude();
                    geographicalTypeOfShape = atiResponseValues.getLocationInformation().getGeographicalInformation().getTypeOfShape().name();
                    geographicalUncertainty = atiResponseValues.getLocationInformation().getGeographicalInformation().getUncertainty();
                    writeTypeOfShape(geographicalTypeOfShape, atiCsGeographicalInformationJsonObject);
                    writeLatitude(geographicalLatitude, atiCsGeographicalInformationJsonObject);
                    writeLongitude(geographicalLongitude, atiCsGeographicalInformationJsonObject);
                    writeUncertainty(geographicalUncertainty, geographicalLatitude, geographicalLongitude, atiCsGeographicalInformationJsonObject);
                }
                atiLocationInformationJsonObject.add("GeographicalInformation", atiCsGeographicalInformationJsonObject);

                if (atiResponseValues.getLocationInformation().getGeodeticInformation() != null) {
                    geodeticLatitude = atiResponseValues.getLocationInformation().getGeodeticInformation().getLatitude();
                    geodeticLongitude = atiResponseValues.getLocationInformation().getGeodeticInformation().getLongitude();
                    geodeticTypeOfShape = atiResponseValues.getLocationInformation().getGeodeticInformation().getTypeOfShape().name();
                    geodeticUncertainty = atiResponseValues.getLocationInformation().getGeodeticInformation().getUncertainty();
                    geodeticConfidence = atiResponseValues.getLocationInformation().getGeodeticInformation().getConfidence();
                    geodeticScreeningAndPresentationIndicators = atiResponseValues.getLocationInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(geodeticTypeOfShape, atiCsGeodeticInformationJsonObject);
                    writeLatitude(geodeticLatitude, atiCsGeodeticInformationJsonObject);
                    writeLongitude(geodeticLongitude, atiCsGeodeticInformationJsonObject);
                    writeUncertainty(geodeticUncertainty, geodeticLatitude, geodeticLongitude, atiCsGeodeticInformationJsonObject);
                    writeConfidence(geodeticConfidence, geodeticLatitude, geodeticLongitude, atiCsGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(geodeticScreeningAndPresentationIndicators, geodeticLatitude, geodeticLongitude, atiCsGeodeticInformationJsonObject);
                }
                atiLocationInformationJsonObject.add("GeodeticInformation", atiCsGeodeticInformationJsonObject);

                if (atiResponseValues.getLocationInformation().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = atiResponseValues.getLocationInformation().getAgeOfLocationInformation().intValue();
                    writeAol(ageOfLocationInfo, atiLocationInformationJsonObject);
                }

                if (atiResponseValues.getLocationInformation().getCurrentLocationRetrieved() != false)
                    currentLocationRetrieved = true;
                else
                    currentLocationRetrieved = false;
                if (currentLocationRetrieved != null)
                    writeCurrentLocationRetrieved(currentLocationRetrieved, atiLocationInformationJsonObject);

                if (atiResponseValues.getLocationInformation().getVlrNumber() != null) {
                    vlrNumber = atiResponseValues.getLocationInformation().getVlrNumber().getAddress();
                    writeVlrNumber(vlrNumber, atiLocationInformationJsonObject);
                }

                if (atiResponseValues.getLocationInformation().getMscNumber() != null) {
                    mscNumber = atiResponseValues.getLocationInformation().getMscNumber().getAddress();
                    writeMscNumber(mscNumber, atiLocationInformationJsonObject);
                }

                if (atiResponseValues.getLocationInformation().getLocationInformationEPS() != null) {

                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                        EUTRANCGI eutrancgi = new EUTRANCGIImpl(atiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
                        try {
                            ecgiMcc = eutrancgi.getMCC();
                            ecgiMnc = eutrancgi.getMNC();
                            ecgiEci = eutrancgi.getEci();
                            ecgiENBId = eutrancgi.getENodeBId();
                            ecgiCi = eutrancgi.getCi();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        writeMcc(ecgiMcc, eUtranCgiJsonObject);
                        writeMnc(ecgiMnc, eUtranCgiJsonObject);
                        writeEUtranEci(ecgiEci, eUtranCgiJsonObject);
                        writeENBId(ecgiENBId, eUtranCgiJsonObject);
                        writeEUtranCellId(ecgiCi, eUtranCgiJsonObject);
                    }
                    atiLocationInformationEPSJsonObject.add("ECGI", eUtranCgiJsonObject);

                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                        TrackingAreaId tai = new TrackingAreaIdImpl(atiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getData());
                        try {
                            taiMcc = tai.getMCC();
                            taiMnc = tai.getMNC();
                            tac = tai.getTAC();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        writeMcc(taiMcc, taiJsonObject);
                        writeMnc(taiMnc, taiJsonObject);
                        writeTrackingAreaCode(tac, taiJsonObject);
                    }
                    atiLocationInformationEPSJsonObject.add("TAI", taiJsonObject);

                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
                        epsGeographicalLatitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude();
                        epsGeographicalLongitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude();
                        epsGeographicalTypeOfShape = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape().name();
                        epsGeographicalUncertainty = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty();
                        writeTypeOfShape(epsGeographicalTypeOfShape, atiEpsGeographicalInformationJsonObject);
                        writeLatitude(epsGeographicalLatitude, atiEpsGeographicalInformationJsonObject);
                        writeLongitude(epsGeographicalLongitude, atiEpsGeographicalInformationJsonObject);
                        writeUncertainty(epsGeographicalUncertainty, epsGeographicalLatitude, epsGeographicalLongitude, atiEpsGeographicalInformationJsonObject);
                    }
                    atiLocationInformationEPSJsonObject.add("GeographicalInformation", atiEpsGeographicalInformationJsonObject);

                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                        epsGeodeticLatitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude();
                        epsGeodeticLongitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude();
                        epsGeodeticTypeOfShape = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape().name();
                        epsGeodeticUncertainty = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty();
                        epsGeodeticConfidence = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getConfidence();
                        epsGeodeticScreeningAndPresentationIndicators = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                        writeTypeOfShape(epsGeodeticTypeOfShape, atiEpsGeodeticInformationJsonObject);
                        writeLatitude(epsGeodeticLatitude, atiEpsGeodeticInformationJsonObject);
                        writeLongitude(epsGeodeticLongitude, atiEpsGeodeticInformationJsonObject);
                        writeUncertainty(epsGeodeticUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, atiEpsGeodeticInformationJsonObject);
                        writeConfidence(epsGeodeticConfidence, epsGeodeticLatitude, epsGeodeticLongitude, atiEpsGeodeticInformationJsonObject);
                        writeScreeningAndPresentationIndicators(epsGeodeticScreeningAndPresentationIndicators, epsGeodeticLatitude, epsGeodeticLongitude, atiEpsGeodeticInformationJsonObject);
                    }
                    atiLocationInformationEPSJsonObject.add("GeodeticInformation", atiEpsGeodeticInformationJsonObject);

                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation() != null) {
                        ageOfLocationInfo = atiResponseValues.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation().intValue();
                        writeAol(ageOfLocationInfo, atiLocationInformationEPSJsonObject);
                    }

                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved())
                        currentLocationRetrieved = true;
                    else
                        currentLocationRetrieved = false;
                    if (currentLocationRetrieved != null)
                        writeCurrentLocationRetrieved(currentLocationRetrieved, atiLocationInformationEPSJsonObject);

                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
                        mmeName = new String(atiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName().getData());
                        writeMmeName(mmeName, atiLocationInformationEPSJsonObject);
                    }
                }
            }

            if (atiResponseValues.getLocationInformationGPRS() != null) {

                if (atiResponseValues.getLocationInformationGPRS().isSaiPresent())
                    saiPresent = true;

                if (atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                    if (atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                        psMcc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                        psMnc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                        psLac = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                    } else if (atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                        psMcc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                        psMnc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                        psLac = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                        psCiOrSac = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                    }
                    writeMcc(psMcc, psCgiOrLaiOrSaiJsonObject);
                    writeMnc(psMnc, psCgiOrLaiOrSaiJsonObject);
                    writeLac(psLac, psCgiOrLaiOrSaiJsonObject);
                    if (!saiPresent) {
                        if (psCiOrSac != null) {
                            writeCellId(psCiOrSac, psCgiOrLaiOrSaiJsonObject);
                            atiLocationInformationGPRSJsonObject.add("CGI", psCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (psLac != null)
                                atiLocationInformationGPRSJsonObject.add("LAI", psCgiOrLaiOrSaiJsonObject);
                        }
                    } else {
                        if (psCiOrSac != null) {
                            writeServiceAreaCode(psCiOrSac, psCgiOrLaiOrSaiJsonObject);
                            atiLocationInformationGPRSJsonObject.add("SAI", psCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (psLac != null)
                                atiLocationInformationGPRSJsonObject.add("LAI", psCgiOrLaiOrSaiJsonObject);
                        }
                    }
                }
                if (psCiOrSac != null)
                    writeSaiPresent(saiPresent, atiLocationInformationGPRSJsonObject);

                if (atiResponseValues.getLocationInformationGPRS().getRouteingAreaIdentity() != null) {
                    RoutingAreaId rai = new RoutingAreaIdImpl(atiResponseValues.getLocationInformationGPRS().getRouteingAreaIdentity().getData());
                    try {
                        raiMcc = rai.getMCC();
                        raiMnc = rai.getMNC();
                        raiLac = rai.getLAC();
                        rac = rai.getRAC();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    writeMcc(raiMcc, raiJsonObject);
                    writeMnc(raiMnc, raiJsonObject);
                    writeLac(raiLac, raiJsonObject);
                    writeRoutingAreaCode(rac, raiJsonObject);
                }
                atiLocationInformationGPRSJsonObject.add("RAI", raiJsonObject);

                if (atiResponseValues.getLocationInformationGPRS().getLSAIdentity() != null) {
                    LSAIdentity lsaIdentity = new LSAIdentityImpl(atiResponseValues.getLocationInformationGPRS().getLSAIdentity().getData());
                    lsaUniversal = lsaIdentity.isPlmnSignificantLSA(); // isPlmnSignificantLSA means the opposite in jSS7 implementation
                    lsaId = new String(atiResponseValues.getLocationInformationGPRS().getLSAIdentity().getData());
                    writeLsaLSB(lsaUniversal, lsaJsonObject);
                    writeLsaId(lsaId, lsaJsonObject);
                }
                atiLocationInformationGPRSJsonObject.add("LSA", lsaJsonObject);

                if (atiResponseValues.getLocationInformationGPRS().getGeographicalInformation() != null) {
                    gprsGeographicalLatitude = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLatitude();
                    gprsGeographicalLongitude = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLongitude();
                    gprsGeographicalTypeOfShape = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape().name();
                    gprsGeographicalUncertainty = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getUncertainty();
                    writeTypeOfShape(gprsGeographicalTypeOfShape, atiGprsGeographicalInformationJsonObject);
                    writeLatitude(gprsGeographicalLatitude, atiGprsGeographicalInformationJsonObject);
                    writeLongitude(gprsGeographicalLongitude, atiGprsGeographicalInformationJsonObject);
                    writeUncertainty(gprsGeographicalUncertainty, gprsGeographicalLatitude, gprsGeographicalLongitude, atiGprsGeographicalInformationJsonObject);
                }
                atiLocationInformationGPRSJsonObject.add("GeographicalInformation", atiGprsGeographicalInformationJsonObject);

                if (atiResponseValues.getLocationInformationGPRS().getGeodeticInformation() != null) {
                    gprsGeodeticLatitude = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLatitude();
                    gprsGeodeticLongitude = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLongitude();
                    gprsGeodeticTypeOfShape = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape().name();
                    gprsGeodeticUncertainty = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getUncertainty();
                    gprsGeodeticConfidence = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getConfidence();
                    gprsGeodeticScreeningAndPresentationIndicators = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(gprsGeodeticTypeOfShape, atiGprsGeodeticInformationJsonObject);
                    writeLatitude(gprsGeodeticLatitude, atiGprsGeodeticInformationJsonObject);
                    writeLongitude(gprsGeodeticLongitude, atiGprsGeodeticInformationJsonObject);
                    writeUncertainty(gprsGeodeticUncertainty, gprsGeodeticLatitude, gprsGeodeticLongitude, atiGprsGeodeticInformationJsonObject);
                    writeConfidence(gprsGeodeticConfidence, gprsGeodeticLatitude, gprsGeodeticLongitude, atiGprsGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(gprsGeodeticScreeningAndPresentationIndicators, gprsGeodeticLatitude, gprsGeodeticLongitude, atiGprsGeodeticInformationJsonObject);
                }
                atiLocationInformationGPRSJsonObject.add("GeodeticInformation", atiGprsGeodeticInformationJsonObject);

                if (atiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = atiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation().intValue();
                    writeAol(ageOfLocationInfo, atiLocationInformationGPRSJsonObject);
                }

                if (atiResponseValues.getLocationInformationGPRS().isCurrentLocationRetrieved())
                    currentLocationRetrieved = true;
                else
                    currentLocationRetrieved = false;
                if (currentLocationRetrieved != null)
                    writeCurrentLocationRetrieved(currentLocationRetrieved, atiLocationInformationGPRSJsonObject);

                if (atiResponseValues.getLocationInformationGPRS().getSGSNNumber() != null) {
                    sgsnNumber = atiResponseValues.getLocationInformationGPRS().getSGSNNumber().getAddress();
                    writeSgsnNumber(sgsnNumber, atiLocationInformationGPRSJsonObject);
                }

            }

            if (atiResponseValues.getImei() != null)
                imei = atiResponseValues.getImei().getIMEI();

            if (atiResponseValues.getSubscriberState() != null) {
                csSubscriberState = atiResponseValues.getSubscriberState().getSubscriberStateChoice().toString();
                if (atiResponseValues.getSubscriberState().getNotReachableReason() != null)
                    notReachableReason = atiResponseValues.getSubscriberState().getNotReachableReason().name();
            }
            if (atiResponseValues.getPsSubscriberState() != null) {
                psSubscriberState = atiResponseValues.getPsSubscriberState().getChoice().toString();
                if (atiResponseValues.getPsSubscriberState().getNetDetNotReachable() != null)
                    notReachableReason = atiResponseValues.getPsSubscriberState().getNetDetNotReachable().name();
            }

            if (atiResponseValues.getMsClassmark2() != null) {
                msClassmark = bytesToHexString(atiResponseValues.getMsClassmark2().getData());
            }

            if (atiResponseValues.getGprsmsClass() != null) {
                msNetCap = bytesToHexString(atiResponseValues.getGprsmsClass().getMSNetworkCapability().getData());
                msRASCap = bytesToHexString(atiResponseValues.getGprsmsClass().getMSRadioAccessCapability().getData());
            }

            if (atiResponseValues.getMnpInfoRes() != null) {
                if (atiResponseValues.getMnpInfoRes().getNumberPortabilityStatus() != null) {
                    mnpInfoResultNumberPortabilityStatus = atiResponseValues.getMnpInfoRes().getNumberPortabilityStatus().getType();
                }
                if (atiResponseValues.getMnpInfoRes().getMSISDN() != null) {
                    mnpInfoResultMSISDN = atiResponseValues.getMnpInfoRes().getMSISDN().getAddress();
                }
                if (atiResponseValues.getMnpInfoRes().getIMSI() != null) {
                    mnpInfoResultIMSI = new String(atiResponseValues.getMnpInfoRes().getIMSI().getData().getBytes());
                }
                if (atiResponseValues.getRouteingNumber() != null) {
                    mnpInfoResultRouteingNumber = atiResponseValues.getRouteingNumber().getRouteingNumber();
                }
            }
        }

        // Write EPS Location Information values
        atiLocationInformationJsonObject.add("EPSLocationInformation", atiLocationInformationEPSJsonObject);
        // Write CS Location Information values which might include EPS Location Information values
        atiResponseJsonObject.add("CSLocationInformation", atiLocationInformationJsonObject);

        // Write GPRS Location Information values
        atiResponseJsonObject.add("PSLocationInformation", atiLocationInformationGPRSJsonObject);

        // Write rest of ATI values
        writeMsisdn(atiMsisdnDigits, atiResponseJsonObject);
        writeImei(imei, atiResponseJsonObject);
        if (csSubscriberState != null)
            writeSubscriberState(csSubscriberState, atiResponseJsonObject);
        else if (psSubscriberState != null)
            writeSubscriberState(psSubscriberState, atiResponseJsonObject);
        writeNotReachableReason(notReachableReason, atiResponseJsonObject);
        // Write MNP Result Info values
        if (mnpInfoResultNumberPortabilityStatus != null || mnpInfoResultMSISDN != null || mnpInfoResultIMSI != null || mnpInfoResultRouteingNumber != null) {
            writeMnpStatus(mnpInfoResultNumberPortabilityStatus, atiMnpInfoResultJsonObject);
            writeMnpMsisdn(mnpInfoResultMSISDN, atiMnpInfoResultJsonObject);
            writeMnpImsi(mnpInfoResultIMSI, atiMnpInfoResultJsonObject);
            writeMnpRouteingNumber(mnpInfoResultRouteingNumber, atiMnpInfoResultJsonObject);
            atiResponseJsonObject.add("MNPInfoResult", atiMnpInfoResultJsonObject);
        }
        writeMSClassmark(msClassmark, atiResponseJsonObject);
        // Write GPRS MS Class values
        if (msNetCap != null || msRASCap != null) {
            writeMSNetworkCapability(msNetCap, atiGprsMsClassJsonObject);
            writeMSRadioAccessCapability(msRASCap, atiGprsMsClassJsonObject);
            atiResponseJsonObject.add("GPRSMSClass", atiGprsMsClassJsonObject);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String atiResponseJson = gson.toJson(atiResponseJsonObject);
        return atiResponseJson;
    }

}
