package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.map.PsiResponseValues;
import org.mobicents.gmlc.slee.map.SriForSmResponseValues;
import org.mobicents.gmlc.slee.map.SriResponseValues;
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
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInternalNetworkNumberIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLmsi;
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
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class PsiResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(PsiResponseJsonBuilder.class);

    public PsiResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param psiResponseValues         Subscriber Information values gathered from PSI response event
     * @param imsi                      IMSI value used on PSI attempt
     * @param sriForSmResponseValues    Subscriber Information values gathered from SRISM response event
     * @param sriResponseValues         Subscriber Information values gathered from SRI response event
     */
    public static String buildJsonResponseForPsi(PsiResponseValues psiResponseValues, String imsi, SriForSmResponseValues sriForSmResponseValues,
                                                 SriResponseValues sriResponseValues, String msisdn) throws MAPException {

        Integer csMcc, csMnc, csLac, csCiOrSac, psMcc, psMnc, psLac, psCiOrSac, ecgiMcc, ecgiMnc, ecgiCi, raiMcc, raiMnc, raiLac, rac, taiMcc, taiMnc, tac,
            natureOfAddressIndicator, numberingPlanIndicator, internalNetworkNumberIndicator, addressPresentationRestrictedIndicator, screeningIndicator,
            ageOfLocationInfo, geodeticConfidence, gprsGeodeticConfidence, epsGeodeticConfidence, geodeticScreeningAndPresentationIndicators,
            gprsGeodeticScreeningAndPresentationIndicators, epsGeodeticScreeningAndPresentationIndicators, mnpInfoResultNumberPortabilityStatus;
        csMcc = csMnc = csLac = csCiOrSac = psMcc = psMnc = psLac = psCiOrSac = ecgiMcc = ecgiMnc = ecgiCi = raiMcc = raiMnc = raiLac = rac = taiMcc = taiMnc = tac =
            natureOfAddressIndicator = numberingPlanIndicator = internalNetworkNumberIndicator = addressPresentationRestrictedIndicator =
                screeningIndicator = ageOfLocationInfo = geodeticConfidence = gprsGeodeticConfidence = epsGeodeticConfidence =
                    geodeticScreeningAndPresentationIndicators = gprsGeodeticScreeningAndPresentationIndicators = epsGeodeticScreeningAndPresentationIndicators =
                        mnpInfoResultNumberPortabilityStatus = null;
        String locationNumberAddressDigits, csSubscriberState, psSubscriberState, notReachableReason, vlrNumber, mscNumber, mmeName, sgsnNumber, lsaId,
            imei, mnpInfoResultMSISDN, mnpInfoResultIMSI, mnpInfoResultRouteingNumber, geographicalTypeOfShape, geodeticTypeOfShape, gprsGeographicalTypeOfShape,
            gprsGeodeticTypeOfShape, epsGeographicalTypeOfShape, epsGeodeticTypeOfShape, lmsi, msClassmark, msNetCap, msRASCap;
        locationNumberAddressDigits = csSubscriberState = psSubscriberState = notReachableReason = vlrNumber = mscNumber = mmeName = sgsnNumber = lsaId = imei = mnpInfoResultMSISDN =
            mnpInfoResultIMSI = mnpInfoResultRouteingNumber = geographicalTypeOfShape = geodeticTypeOfShape = gprsGeographicalTypeOfShape = gprsGeodeticTypeOfShape =
                epsGeographicalTypeOfShape = epsGeodeticTypeOfShape = lmsi = msClassmark = msNetCap = msRASCap = null;
        Double geographicalLatitude, geographicalLongitude, geographicalUncertainty, geodeticLatitude, geodeticLongitude, geodeticUncertainty;
        geographicalLatitude = geographicalLongitude = geographicalUncertainty = geodeticLatitude = geodeticLongitude = geodeticUncertainty = null;
        Double epsGeographicalLatitude, epsGeographicalLongitude, epsGeographicalUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, epsGeodeticUncertainty;
        epsGeographicalLatitude = epsGeographicalLongitude = epsGeographicalUncertainty = epsGeodeticLatitude = epsGeodeticLongitude = epsGeodeticUncertainty = null;
        Double gprsGeographicalLatitude, gprsGeographicalLongitude, gprsGeographicalUncertainty, gprsGeodeticLatitude, gprsGeodeticLongitude, gprsGeodeticUncertainty;
        gprsGeographicalLatitude = gprsGeographicalLongitude = gprsGeographicalUncertainty = gprsGeodeticLatitude = gprsGeodeticLongitude = gprsGeodeticUncertainty = null;
        Long ecgiEci = null, ecgiENBId = null;
        Boolean oddFlag, saiPresent, currentLocationRetrieved, lsaUniversal;
        oddFlag = currentLocationRetrieved = lsaUniversal = null;
        saiPresent = false;

        JsonObject psiSubscriberInformationJsonObject = new JsonObject();
        writeNetwork("GSM/UMTS", psiSubscriberInformationJsonObject);
        writeProtocol("MAP", psiSubscriberInformationJsonObject);
        writeOperation("PSI", psiSubscriberInformationJsonObject);
        writeOperationResult("SUCCESS", psiSubscriberInformationJsonObject);
        JsonObject psiLocationInformationJsonObject = new JsonObject();
        JsonObject psiLocationInformationEPSJsonObject = new JsonObject();
        JsonObject psiLocationInformationGPRSJsonObject = new JsonObject();
        JsonObject csCgiOrLaiOrSaiJsonObject = new JsonObject();
        JsonObject psCgiOrLaiOrSaiJsonObject = new JsonObject();
        JsonObject locationNumberJsonObject = new JsonObject();
        JsonObject eUtranCgiJsonObject = new JsonObject();
        JsonObject raiJsonObject = new JsonObject();
        JsonObject lsaJsonObject = new JsonObject();
        JsonObject taiJsonObject = new JsonObject();
        JsonObject psiGeographicalInformationJsonObject = new JsonObject();
        JsonObject psiGeodeticInformationJsonObject = new JsonObject();
        JsonObject psiEPSGeographicalInformationJsonObject = new JsonObject();
        JsonObject psiEPSGeodeticInformationJsonObject = new JsonObject();
        JsonObject psiGPRSGeographicalInformationJsonObject = new JsonObject();
        JsonObject psiGPRSGeodeticInformationJsonObject = new JsonObject();
        JsonObject psiMnpInfoResultJsonObject = new JsonObject();
        JsonObject psiGprsMsClassJsonObject = new JsonObject();

        if (psiResponseValues != null) {

            if (psiResponseValues.getLocationInformation() != null) {

                if (psiResponseValues.getLocationInformation().getSaiPresent() != false) {
                    saiPresent = true;
                }

                if (psiResponseValues.getLocationInformation().getLocationNumber() != null) {
                    if (psiResponseValues.getLocationInformation().getLocationNumber().getLocationNumber() != null) {
                        LocationNumber locationNumber = psiResponseValues.getLocationInformation().getLocationNumber().getLocationNumber();
                        oddFlag = locationNumber.isOddFlag();
                        natureOfAddressIndicator = locationNumber.getNatureOfAddressIndicator();
                        internalNetworkNumberIndicator = locationNumber.getInternalNetworkNumberIndicator();
                        numberingPlanIndicator = locationNumber.getNumberingPlanIndicator();
                        addressPresentationRestrictedIndicator = locationNumber.getAddressRepresentationRestrictedIndicator();
                        screeningIndicator = locationNumber.getScreeningIndicator();
                        locationNumberAddressDigits = locationNumber.getAddress();
                    }
                    // Write CS Location Information values
                    if (oddFlag != null)
                        writeOddFlag(oddFlag, locationNumberJsonObject);
                    writeNatureOfAddressIndicator(natureOfAddressIndicator, locationNumberJsonObject);
                    writeInternalNetworkNumberIndicator(internalNetworkNumberIndicator, locationNumberJsonObject);
                    writeNumberingPlanIndicator(numberingPlanIndicator, locationNumberJsonObject);
                    writeAddressPresentationRestrictedIndicator(addressPresentationRestrictedIndicator, locationNumberJsonObject);
                    writeScreeningIndicator(screeningIndicator, locationNumberJsonObject);
                    writeLocationNumberAddress(locationNumberAddressDigits, locationNumberJsonObject);
                }
                psiLocationInformationJsonObject.add("LocationNumber", locationNumberJsonObject);

                if (psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                    if (psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                        csMcc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                        csMnc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                        csLac = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                    } else if (psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                        csMcc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                        csMnc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                        csLac = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                        csCiOrSac = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                    }
                    writeMcc(csMcc, csCgiOrLaiOrSaiJsonObject);
                    writeMnc(csMnc, csCgiOrLaiOrSaiJsonObject);
                    writeLac(csLac, csCgiOrLaiOrSaiJsonObject);
                    if (!saiPresent) {
                        if (csCiOrSac != null) {
                            writeCellId(csCiOrSac, csCgiOrLaiOrSaiJsonObject);
                            psiLocationInformationJsonObject.add("CGI", csCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (csLac != null)
                                psiLocationInformationJsonObject.add("LAI", csCgiOrLaiOrSaiJsonObject);
                        }
                    } else {
                        if (csCiOrSac != null) {
                            writeServiceAreaCode(csCiOrSac, csCgiOrLaiOrSaiJsonObject);
                            psiLocationInformationJsonObject.add("SAI", csCgiOrLaiOrSaiJsonObject);
                        } else {
                            if (csLac != null)
                                psiLocationInformationJsonObject.add("LAI", csCgiOrLaiOrSaiJsonObject);
                        }
                    }
                }
                if (csCiOrSac != null)
                    writeSaiPresent(saiPresent, psiLocationInformationJsonObject);

                if (psiResponseValues.getLocationInformation().getGeographicalInformation() != null) {
                    geographicalLatitude = psiResponseValues.getLocationInformation().getGeographicalInformation().getLatitude();
                    geographicalLongitude = psiResponseValues.getLocationInformation().getGeographicalInformation().getLongitude();
                    geographicalTypeOfShape = psiResponseValues.getLocationInformation().getGeographicalInformation().getTypeOfShape().name();
                    geographicalUncertainty = psiResponseValues.getLocationInformation().getGeographicalInformation().getUncertainty();
                    writeTypeOfShape(geographicalTypeOfShape, psiGeographicalInformationJsonObject);
                    writeLatitude(geographicalLatitude, psiGeographicalInformationJsonObject);
                    writeLongitude(geographicalLongitude, psiGeographicalInformationJsonObject);
                    writeUncertainty(geographicalUncertainty, geographicalLatitude, geographicalLongitude, psiGeographicalInformationJsonObject);
                }
                psiLocationInformationJsonObject.add("GeographicalInformation", psiGeographicalInformationJsonObject);

                if (psiResponseValues.getLocationInformation().getGeodeticInformation() != null) {
                    geodeticLatitude = psiResponseValues.getLocationInformation().getGeodeticInformation().getLatitude();
                    geodeticLongitude = psiResponseValues.getLocationInformation().getGeodeticInformation().getLongitude();
                    geodeticTypeOfShape = psiResponseValues.getLocationInformation().getGeodeticInformation().getTypeOfShape().name();
                    geodeticUncertainty = psiResponseValues.getLocationInformation().getGeodeticInformation().getUncertainty();
                    geodeticConfidence = psiResponseValues.getLocationInformation().getGeodeticInformation().getConfidence();
                    geodeticScreeningAndPresentationIndicators = psiResponseValues.getLocationInformation().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(geodeticTypeOfShape, psiGeodeticInformationJsonObject);
                    writeLatitude(geodeticLatitude, psiGeodeticInformationJsonObject);
                    writeLongitude(geodeticLongitude, psiGeodeticInformationJsonObject);
                    writeUncertainty(geodeticUncertainty, geodeticLatitude, geodeticLongitude, psiGeodeticInformationJsonObject);
                    writeConfidence(geodeticConfidence, geodeticLatitude, geodeticLongitude, psiGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(geodeticScreeningAndPresentationIndicators, geodeticLatitude, geodeticLongitude, psiGeodeticInformationJsonObject);
                }
                psiLocationInformationJsonObject.add("GeodeticInformation", psiGeodeticInformationJsonObject);

                if (psiResponseValues.getLocationInformation().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = psiResponseValues.getLocationInformation().getAgeOfLocationInformation().intValue();
                    writeAol(ageOfLocationInfo, psiLocationInformationJsonObject);
                }

                if (psiResponseValues.getLocationInformation().getCurrentLocationRetrieved() != false)
                    currentLocationRetrieved = true;
                else
                    currentLocationRetrieved = false;
                if (currentLocationRetrieved != null)
                    writeCurrentLocationRetrieved(currentLocationRetrieved, psiLocationInformationJsonObject);

                if (psiResponseValues.getLocationInformation().getVlrNumber() != null) {
                    vlrNumber = psiResponseValues.getLocationInformation().getVlrNumber().getAddress();
                    writeVlrNumber(vlrNumber, psiLocationInformationJsonObject);
                }

                if (psiResponseValues.getLocationInformation().getMscNumber() != null) {
                    mscNumber = psiResponseValues.getLocationInformation().getMscNumber().getAddress();
                    writeMscNumber(mscNumber, psiLocationInformationJsonObject);
                }

                if (psiResponseValues.getLocationInformation().getLocationInformationEPS() != null) {

                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                        EUTRANCGI eutrancgi = new EUTRANCGIImpl(psiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
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
                    psiLocationInformationEPSJsonObject.add("ECGI", eUtranCgiJsonObject);

                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                        TrackingAreaId tai = new TrackingAreaIdImpl(psiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getData());
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
                    psiLocationInformationEPSJsonObject.add("TAI", taiJsonObject);

                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
                        epsGeographicalLatitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude();
                        epsGeographicalLongitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude();
                        epsGeographicalTypeOfShape = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape().name();
                        epsGeographicalUncertainty = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty();
                        writeTypeOfShape(epsGeographicalTypeOfShape, psiEPSGeographicalInformationJsonObject);
                        writeLatitude(epsGeographicalLatitude, psiEPSGeographicalInformationJsonObject);
                        writeLongitude(epsGeographicalLongitude, psiEPSGeographicalInformationJsonObject);
                        writeUncertainty(epsGeographicalUncertainty, epsGeographicalLatitude, epsGeographicalLongitude, psiEPSGeographicalInformationJsonObject);
                    }
                    psiLocationInformationEPSJsonObject.add("GeographicalInformation", psiEPSGeographicalInformationJsonObject);

                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                        epsGeodeticLatitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude();
                        epsGeodeticLongitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude();
                        epsGeodeticTypeOfShape = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape().name();
                        epsGeodeticUncertainty = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty();
                        epsGeodeticConfidence = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getConfidence();
                        epsGeodeticScreeningAndPresentationIndicators = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                        writeTypeOfShape(epsGeodeticTypeOfShape, psiEPSGeodeticInformationJsonObject);
                        writeLatitude(epsGeodeticLatitude, psiEPSGeodeticInformationJsonObject);
                        writeLongitude(epsGeodeticLongitude, psiEPSGeodeticInformationJsonObject);
                        writeUncertainty(epsGeodeticUncertainty, epsGeodeticLatitude, epsGeodeticLongitude, psiEPSGeodeticInformationJsonObject);
                        writeConfidence(epsGeodeticConfidence, epsGeodeticLatitude, epsGeodeticLongitude, psiEPSGeodeticInformationJsonObject);
                        writeScreeningAndPresentationIndicators(epsGeodeticScreeningAndPresentationIndicators, epsGeodeticLatitude, epsGeodeticLongitude, psiEPSGeodeticInformationJsonObject);
                    }
                    psiLocationInformationEPSJsonObject.add("GeodeticInformation", psiEPSGeodeticInformationJsonObject);

                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation() != null) {
                        ageOfLocationInfo = psiResponseValues.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation().intValue();
                        writeAol(ageOfLocationInfo, psiLocationInformationEPSJsonObject);
                    }

                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved() != false)
                        currentLocationRetrieved = true;
                    else
                        currentLocationRetrieved = false;
                    if (currentLocationRetrieved != null)
                        writeCurrentLocationRetrieved(currentLocationRetrieved, psiLocationInformationEPSJsonObject);

                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
                        mmeName = new String(psiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName().getData());
                        writeMmeName(mmeName, psiLocationInformationEPSJsonObject);
                    }
                }
            }

            if (psiResponseValues.getLocationInformationGPRS() != null) {

                if (psiResponseValues.getLocationInformationGPRS().isSaiPresent())
                    saiPresent = true;

                if (psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                    psMcc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                    psMnc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                    psLac = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                } else if (psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                    psMcc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                    psMnc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                    psLac = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                    psCiOrSac = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                }
                writeMcc(psMcc, psCgiOrLaiOrSaiJsonObject);
                writeMnc(psMnc, psCgiOrLaiOrSaiJsonObject);
                writeLac(psLac, psCgiOrLaiOrSaiJsonObject);
                if (!saiPresent) {
                    if (psCiOrSac != null) {
                        writeCellId(psCiOrSac, psCgiOrLaiOrSaiJsonObject);
                        psiLocationInformationGPRSJsonObject.add("CGI", psCgiOrLaiOrSaiJsonObject);
                    } else {
                        if (psLac != null)
                            psiLocationInformationGPRSJsonObject.add("LAI", psCgiOrLaiOrSaiJsonObject);
                    }
                } else {
                    if (psCiOrSac != null) {
                        writeServiceAreaCode(psCiOrSac, psCgiOrLaiOrSaiJsonObject);
                        psiLocationInformationGPRSJsonObject.add("SAI", psCgiOrLaiOrSaiJsonObject);
                    } else {
                        if (psLac != null)
                            psiLocationInformationGPRSJsonObject.add("LAI", psCgiOrLaiOrSaiJsonObject);
                    }
                }
                if (psCiOrSac != null)
                    writeSaiPresent(saiPresent, psiLocationInformationGPRSJsonObject);

                if (psiResponseValues.getLocationInformationGPRS().getRouteingAreaIdentity() != null) {
                    RoutingAreaId rai = new RoutingAreaIdImpl(psiResponseValues.getLocationInformationGPRS().getRouteingAreaIdentity().getData());
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
                psiLocationInformationGPRSJsonObject.add("RAI", raiJsonObject);

                if (psiResponseValues.getLocationInformationGPRS().getLSAIdentity() != null) {
                    LSAIdentity lsaIdentity = new LSAIdentityImpl(psiResponseValues.getLocationInformationGPRS().getLSAIdentity().getData());
                    lsaUniversal = lsaIdentity.isPlmnSignificantLSA(); // isPlmnSignificantLSA means the opposite in jSS7 implementation
                    lsaId = new String(psiResponseValues.getLocationInformationGPRS().getLSAIdentity().getData());
                    writeLsaLSB(lsaUniversal, lsaJsonObject);
                    writeLsaId(lsaId, lsaJsonObject);
                }
                psiLocationInformationGPRSJsonObject.add("LSA", lsaJsonObject);

                if (psiResponseValues.getLocationInformationGPRS().getGeographicalInformation() != null) {
                    gprsGeographicalLatitude = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLatitude();
                    gprsGeographicalLongitude = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLongitude();
                    gprsGeographicalUncertainty = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getUncertainty();
                    gprsGeographicalTypeOfShape = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape().name();
                    writeTypeOfShape(gprsGeographicalTypeOfShape, psiGPRSGeographicalInformationJsonObject);
                    writeLatitude(gprsGeographicalLatitude, psiGPRSGeographicalInformationJsonObject);
                    writeLongitude(gprsGeographicalLongitude, psiGPRSGeographicalInformationJsonObject);
                    writeUncertainty(gprsGeographicalUncertainty, gprsGeographicalLatitude, gprsGeographicalLongitude, psiGPRSGeographicalInformationJsonObject);
                }
                psiLocationInformationGPRSJsonObject.add("GeographicalInformation", psiGPRSGeographicalInformationJsonObject);

                if (psiResponseValues.getLocationInformationGPRS().getGeodeticInformation() != null) {
                    gprsGeodeticTypeOfShape = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape().name();
                    gprsGeodeticLatitude = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLatitude();
                    gprsGeodeticLongitude = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLongitude();
                    gprsGeodeticUncertainty = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getUncertainty();
                    gprsGeodeticConfidence = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getConfidence();
                    gprsGeodeticScreeningAndPresentationIndicators = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getScreeningAndPresentationIndicators();
                    writeTypeOfShape(gprsGeodeticTypeOfShape, psiGPRSGeodeticInformationJsonObject);
                    writeLatitude(gprsGeodeticLatitude, psiGPRSGeodeticInformationJsonObject);
                    writeLongitude(gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                    writeUncertainty(gprsGeodeticUncertainty, gprsGeodeticLatitude, gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                    writeConfidence(gprsGeodeticConfidence, gprsGeodeticLatitude, gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                    writeScreeningAndPresentationIndicators(gprsGeodeticScreeningAndPresentationIndicators, gprsGeodeticLatitude, gprsGeodeticLongitude, psiGPRSGeodeticInformationJsonObject);
                }
                psiLocationInformationGPRSJsonObject.add("GeodeticInformation", psiGPRSGeodeticInformationJsonObject);

                if (psiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation() != null) {
                    ageOfLocationInfo = psiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation().intValue();
                    writeAol(ageOfLocationInfo, psiLocationInformationGPRSJsonObject);
                }

                if (psiResponseValues.getLocationInformationGPRS().isCurrentLocationRetrieved())
                    currentLocationRetrieved = true;
                else
                    currentLocationRetrieved= false;
                if (currentLocationRetrieved != null)
                    writeCurrentLocationRetrieved(currentLocationRetrieved, psiLocationInformationGPRSJsonObject);

                if (psiResponseValues.getLocationInformationGPRS().getSGSNNumber() != null) {
                    sgsnNumber = psiResponseValues.getLocationInformationGPRS().getSGSNNumber().getAddress();
                    writeSgsnNumber(sgsnNumber, psiLocationInformationGPRSJsonObject);
                }
            }

            if (psiResponseValues.getMnpInfoRes() != null) {
                if (psiResponseValues.getMnpInfoRes().getNumberPortabilityStatus() != null) {
                    mnpInfoResultNumberPortabilityStatus = psiResponseValues.getMnpInfoRes().getNumberPortabilityStatus().getType();
                }
                if (psiResponseValues.getMnpInfoRes().getMSISDN() != null) {
                    mnpInfoResultMSISDN = psiResponseValues.getMnpInfoRes().getMSISDN().getAddress();
                }
                if (psiResponseValues.getMnpInfoRes().getIMSI() != null) {
                    mnpInfoResultIMSI = new String(psiResponseValues.getMnpInfoRes().getIMSI().getData().getBytes());
                }
                if (psiResponseValues.getRouteingNumber() != null) {
                    mnpInfoResultRouteingNumber = psiResponseValues.getRouteingNumber().getRouteingNumber();
                }
            }

            if (psiResponseValues.getImei() != null) {
                imei = psiResponseValues.getImei().getIMEI();
            }

            if (psiResponseValues.getSubscriberState() != null) {
                csSubscriberState = psiResponseValues.getSubscriberState().getSubscriberStateChoice().toString();
                if (psiResponseValues.getSubscriberState().getNotReachableReason() != null)
                    notReachableReason = psiResponseValues.getSubscriberState().getNotReachableReason().name();
            }
            if (psiResponseValues.getPsSubscriberState() != null) {
                psSubscriberState = psiResponseValues.getPsSubscriberState().getChoice().toString();
                if (psiResponseValues.getPsSubscriberState().getNetDetNotReachable() != null)
                    notReachableReason = psiResponseValues.getPsSubscriberState().getNetDetNotReachable().name();
            }

            if (psiResponseValues.getMsClassmark2() != null) {
                msClassmark = bytesToHexString(psiResponseValues.getMsClassmark2().getData());
            }

            if (psiResponseValues.getGprsmsClass() != null) {
                msNetCap = bytesToHexString(psiResponseValues.getGprsmsClass().getMSNetworkCapability().getData());
                msRASCap = bytesToHexString(psiResponseValues.getGprsmsClass().getMSRadioAccessCapability().getData());
            }
        }

        if (sriForSmResponseValues != null) {
            if (sriForSmResponseValues.getLmsi() != null)
                lmsi = bytesToHex(sriForSmResponseValues.getLmsi().getData());
        }

        // Write EPS Location Information values
        psiLocationInformationJsonObject.add("EPSLocationInformation", psiLocationInformationEPSJsonObject);
        // Write CS Location Information values which might include EPS Location Information values
        psiSubscriberInformationJsonObject.add("CSLocationInformation", psiLocationInformationJsonObject);

        // Write GPRS Location Information values
        psiSubscriberInformationJsonObject.add("PSLocationInformation", psiLocationInformationGPRSJsonObject);

        // Write rest of Subscriber Information values
        writeMsisdn(msisdn, psiSubscriberInformationJsonObject);
        writeImsi(imsi, psiSubscriberInformationJsonObject);
        writeImei(imei, psiSubscriberInformationJsonObject);
        writeLmsi(lmsi, psiSubscriberInformationJsonObject);
        if (csSubscriberState != null)
            writeSubscriberState(csSubscriberState, psiSubscriberInformationJsonObject);
        else if (psSubscriberState != null)
            writeSubscriberState(psSubscriberState, psiSubscriberInformationJsonObject);
        writeNotReachableReason(notReachableReason, psiSubscriberInformationJsonObject);
        // Write MNP Information Result values
        if (mnpInfoResultNumberPortabilityStatus != null || mnpInfoResultMSISDN != null || mnpInfoResultIMSI != null || mnpInfoResultRouteingNumber != null) {
            writeMnpStatus(mnpInfoResultNumberPortabilityStatus, psiMnpInfoResultJsonObject);
            writeMnpMsisdn(mnpInfoResultMSISDN, psiMnpInfoResultJsonObject);
            writeMnpImsi(mnpInfoResultIMSI, psiMnpInfoResultJsonObject);
            writeMnpRouteingNumber(mnpInfoResultRouteingNumber, psiMnpInfoResultJsonObject);
            psiSubscriberInformationJsonObject.add("MNPInfoResult", psiMnpInfoResultJsonObject);
        }
        writeMSClassmark(msClassmark, psiSubscriberInformationJsonObject);
        // Write GPRS MS Class values
        if (msNetCap != null || msRASCap != null) {
            writeMSNetworkCapability(msNetCap, psiGprsMsClassJsonObject);
            writeMSRadioAccessCapability(msRASCap, psiGprsMsClassJsonObject);
            psiSubscriberInformationJsonObject.add("GPRSMSClass", psiGprsMsClassJsonObject);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String psiResponseJson = gson.toJson(psiSubscriberInformationJsonObject);
        return psiResponseJson;
    }

}
