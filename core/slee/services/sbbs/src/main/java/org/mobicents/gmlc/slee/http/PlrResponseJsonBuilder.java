package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.diameter.AVPHandler;
import org.mobicents.gmlc.slee.diameter.slg.SLgPlaAvpValues;
import org.mobicents.gmlc.slee.diameter.slh.SLhRiaAvpValues;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.mobicents.gmlc.slee.primitives.EllipsoidPoint;
import org.mobicents.gmlc.slee.primitives.Polygon;
import org.mobicents.gmlc.slee.primitives.PolygonImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;

import javax.xml.bind.DatatypeConverter;
import java.awt.geom.Point2D;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.mobicents.gmlc.slee.gis.GeographicHelper.polygonCentroid;
import static org.mobicents.gmlc.slee.http.JsonWriter.bytesToHexString;
import static org.mobicents.gmlc.slee.http.JsonWriter.write3gppAaaServerName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAccuracyFulfilmentIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAgeOfLocationEstimate;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAltitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAngleOfMajorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeBarometricPressure;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeBearing;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellPortionId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCivicAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeClientReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeDiameterResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeENBId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranEci;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranGanssPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGmlcAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeHorizontalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeIncludedAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInnerRadius;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLmsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeRealm;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberOfPoints;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOffsetAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnRealm;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTypeOfShape;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertainty;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyAltitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyHorizontalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyInnerRadius;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintySemiMajorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintySemiMinorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyVerticalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranAdditionalPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranGanssPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVelocityType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVerticalSpeed;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class PlrResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(PlrResponseJsonBuilder.class);

    public PlrResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param ria                   Subscriber Information values gathered from RIA response event
     * @param pla                   Subscriber Information values gathered from PSL response event
     * @param plrMsisdn             Subscriber's MSISDN
     * @param plrImsi               Subscriber's IMSI
     * @param clientReferenceNumber Reference Number gathered from the originating HTTP request sent by the GMLC Client
     * @param lcsReferenceNumber    LCS-Reference-Number exchanged between the GMLC and the LTE EPC network
     */
    public static String buildJsonResponseForPlr(SLhRiaAvpValues ria, SLgPlaAvpValues pla, String plrMsisdn, String plrImsi, Integer clientReferenceNumber,
                                                 Integer lcsReferenceNumber, String diameterResultMessage) {

        String msisdn, imsi, lmsi, mmeName, mmeRealm, sgsnName, sgsnRealm, sgsnNumber, tgppAAAServerName, gmlcAddress, typeOfShape, velocityType,
            civicAddress, geranPositioningInfo, geranGanssPositioningData, utranPositioningData, utranGanssPositioningData, utranAdditionalPositioningData,
            eUtranPositioningData;
        msisdn = imsi = lmsi = mmeName = mmeRealm = sgsnName = sgsnRealm = sgsnNumber = tgppAAAServerName = gmlcAddress = typeOfShape = velocityType =
            civicAddress = geranPositioningInfo = geranGanssPositioningData = utranPositioningData = utranGanssPositioningData =
                utranAdditionalPositioningData = eUtranPositioningData = null;
        Double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, uncertaintyAltitude, uncertaintyInnerRadius,
            angleOfMajorAxis, offsetAngle, includedAngle;
        latitude = longitude = uncertainty = uncertaintySemiMajorAxis = uncertaintySemiMinorAxis = uncertaintyAltitude = uncertaintyInnerRadius =
            angleOfMajorAxis = offsetAngle = includedAngle = null;
        Integer cgiMcc, cgiMnc, cgiLac, cgiCi, saiMcc, saiMnc, saiLac, sac, ecgiMcc, ecgiMnc, ecgiCi, ageOfLocationEstimate, numberOfPoints, confidence, altitude,
            innerRadius, accuracyFulfilmentIndicator, horizontalSpeed, bearing, verticalSpeed, uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed;
        cgiMcc = cgiMnc = cgiLac = cgiCi = saiMcc = saiMnc = saiLac = sac = ecgiMcc = ecgiMnc = ecgiCi = ageOfLocationEstimate = numberOfPoints = confidence =
            altitude = innerRadius = accuracyFulfilmentIndicator = horizontalSpeed = bearing = verticalSpeed = uncertaintyHorizontalSpeed = uncertaintyVerticalSpeed = null;
        Long eci, eNBId, cellPortionId, barometricPressure;
        eci = eNBId = cellPortionId = barometricPressure = null;
        EUTRANCGI eutranCgi;
        Polygon polygon = null;
        EllipsoidPoint[] polygonEllipsoidPoints;
        Double[][] polygonArray;

        JsonObject riaPlaJsonObject = new JsonObject();
        writeNetwork("LTE", riaPlaJsonObject);
        writeProtocol("Diameter SLh-SLg(ELP)", riaPlaJsonObject);
        writeOperation("RIR-RIA-PLR-PLA", riaPlaJsonObject);
        writeOperationResult("SUCCESS", riaPlaJsonObject);
        writeDiameterResult(diameterResultMessage, riaPlaJsonObject);
        writeClientReferenceNumber(clientReferenceNumber, riaPlaJsonObject);
        writeLcsReferenceNumber(lcsReferenceNumber, riaPlaJsonObject);
        JsonObject riaJsonObject = new JsonObject();
        JsonObject plaJsonObject = new JsonObject();
        JsonObject plaLocationEstimateJsonObject = new JsonObject();
        JsonObject plaLocationEstimatePolygonPointsJsonObject = new JsonObject();
        JsonObject plaLocationEstimatePolygonCentroidObject = new JsonObject();
        JsonObject plaVelocityEstimateJsonObject = new JsonObject();
        JsonObject cgiJsonObject = new JsonObject();
        JsonObject saiJsonObject = new JsonObject();
        JsonObject ecgiJsonObject = new JsonObject();
        JsonObject plaGeranPosInfoJsonObject = new JsonObject();
        JsonObject plaUtranPosInfoJsonObject = new JsonObject();
        JsonObject plaEUtranPosInfoJsonObject = new JsonObject();

        if (ria != null) {
            // Get SLh Routing-Information-Answer values
            if (ria.getMsisdn() != null) {
                msisdn = AVPHandler.tbcd2IsdnAddressString(ria.getMsisdn()).getAddress();
                writeMsisdn(msisdn, riaJsonObject);
            } else {
                msisdn = plrMsisdn;
                writeMsisdn(msisdn, riaJsonObject);
            }

            if (ria.getUserName() != null) {
                imsi = AVPHandler.userName2Imsi(ria.getUserName()).getData();
                writeImsi(imsi, riaJsonObject);
            } else {
                imsi = plrImsi;
                writeImsi(imsi, riaJsonObject);
            }

            if (ria.getLmsi() != null) {
                lmsi = bytesToHex(AVPHandler.byte2Lmsi(ria.getLmsi()).getData());
                writeLmsi(lmsi, riaJsonObject);
            }

            if (ria.getMmeName() != null) {
                mmeName = new String(AVPHandler.diameterIdToMapDiameterId(ria.getMmeName()).getData());
                writeMmeName(mmeName, riaJsonObject);
            }

            if (ria.getMmeRealm() != null) {
                mmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(ria.getMmeRealm()).getData());
                writeMmeRealm(mmeRealm, riaJsonObject);
            }

            if (ria.getSgsnNumber() != null) {
                sgsnNumber = AVPHandler.byte2IsdnAddressString(ria.getSgsnNumber()).getAddress();
                writeSgsnNumber(sgsnNumber, riaJsonObject);
            }

            if (ria.getSgsnName() != null) {
                sgsnName = new String(AVPHandler.diameterIdToMapDiameterId(ria.getSgsnName()).getData());
                writeSgsnName(sgsnName, riaJsonObject);
            }

            if (ria.getSgsnRealm() != null) {
                sgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(ria.getSgsnRealm()).getData());
                writeSgsnRealm(sgsnRealm, riaJsonObject);
            }

            if (ria.getTgppAAAServerName() != null) {
                tgppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(ria.getTgppAAAServerName()).getData());
                write3gppAaaServerName(tgppAAAServerName, riaJsonObject);
            }

            if (ria.getGmlcAddress() != null) {
                gmlcAddress = bytesToHexString(ria.getGmlcAddress().getAddress());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(gmlcAddress));
                    gmlcAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                writeGmlcAddress(gmlcAddress, riaJsonObject);
            }
        }

        if (pla != null) {
            // Get SLg Provide-Location-Answer values
            if (pla.getLocationEstimate() != null) {
                ExtGeographicalInformation lteLocationEstimate = AVPHandler.lteLocationEstimate2ExtGeographicalInformation(pla.getLocationEstimate());
                typeOfShape = lteLocationEstimate.getTypeOfShape().name();
                if (lteLocationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    latitude = lteLocationEstimate.getLatitude();
                    longitude = lteLocationEstimate.getLongitude();
                    uncertainty = lteLocationEstimate.getUncertainty();
                    uncertaintySemiMajorAxis = lteLocationEstimate.getUncertaintySemiMajorAxis();
                    uncertaintySemiMinorAxis = lteLocationEstimate.getUncertaintySemiMinorAxis();
                    angleOfMajorAxis = lteLocationEstimate.getAngleOfMajorAxis();
                    confidence = lteLocationEstimate.getConfidence();
                    altitude = lteLocationEstimate.getAltitude();
                    uncertaintyAltitude = lteLocationEstimate.getUncertaintyAltitude();
                    innerRadius = lteLocationEstimate.getInnerRadius();
                    uncertaintyInnerRadius = lteLocationEstimate.getUncertaintyRadius();
                    offsetAngle = lteLocationEstimate.getOffsetAngle();
                    includedAngle = lteLocationEstimate.getIncludedAngle();
                } else {
                    polygon = new PolygonImpl(pla.getLocationEstimate());
                    numberOfPoints = polygon.getNumberOfPoints();
                    polygonEllipsoidPoints = new EllipsoidPoint[numberOfPoints];
                    for (int point = 0; point < numberOfPoints; point++) {
                        polygonEllipsoidPoints[point] = polygon.getEllipsoidPoint(point);
                    }
                    try {
                        ((PolygonImpl) polygon).setData(polygonEllipsoidPoints);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                }
                // Write Location-Estimate AVP values from SLg PLA
                if (typeOfShape != null) {
                    writeTypeOfShape(typeOfShape, plaLocationEstimateJsonObject);
                    if (typeOfShape.equalsIgnoreCase("EllipsoidPoint")) {
                        writeLatitude(latitude, plaLocationEstimateJsonObject);
                        writeLongitude(longitude, plaLocationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle")) {
                        writeLatitude(latitude, plaLocationEstimateJsonObject);
                        writeLongitude(longitude, plaLocationEstimateJsonObject);
                        writeUncertainty(uncertainty, plaLocationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse")) {
                        writeLatitude(latitude, plaLocationEstimateJsonObject);
                        writeLongitude(longitude, plaLocationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, plaLocationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, plaLocationEstimateJsonObject);
                        writeAngleOfMajorAxis(angleOfMajorAxis, plaLocationEstimateJsonObject);
                        writeConfidence(confidence, plaLocationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("Polygon")) {
                        polygonArray = new Double[numberOfPoints][numberOfPoints];
                        Double lat, lon;
                        writeNumberOfPoints(numberOfPoints, plaLocationEstimateJsonObject);
                        if (numberOfPoints > 2 && numberOfPoints <= 15) {
                            for (int index=0; index<numberOfPoints; index++) {
                                lat = polygon.getEllipsoidPoint(index).getLatitude();
                                lon = polygon.getEllipsoidPoint(index).getLongitude();
                                polygonArray[index][0] = lat;
                                polygonArray[index][1] = lon;
                                String polygonPoint = "polygonPoint"+(index+1);
                                writeLatitude(lat, plaLocationEstimatePolygonPointsJsonObject);
                                writeLongitude(lon, plaLocationEstimatePolygonPointsJsonObject);
                                plaLocationEstimateJsonObject.add(polygonPoint, plaLocationEstimatePolygonPointsJsonObject);
                                plaLocationEstimatePolygonPointsJsonObject = new JsonObject();
                            }
                            List<Point2D> listOfPoints = new ArrayList<>();
                            Point2D[] point2D = new Point2D.Double[polygonArray.length];
                            Point2D polygonPoint;
                            for (int point = 0; point < polygonArray.length; point++) {
                                lat = polygonArray[point][0];
                                lon = polygonArray[point][1];
                                polygonPoint = new Point2D.Double(lat,lon);
                                listOfPoints.add(polygonPoint);
                                point2D[point] = listOfPoints.get(point);
                            }
                            if (polygonCentroid(point2D) != null) {
                                writeLatitude(polygonCentroid(point2D).getX(), plaLocationEstimatePolygonCentroidObject);
                                writeLongitude(polygonCentroid(point2D).getY(), plaLocationEstimatePolygonCentroidObject);
                                plaLocationEstimateJsonObject.add("polygonCentroid", plaLocationEstimatePolygonCentroidObject);
                            }
                        }
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitude")) {
                        writeLatitude(latitude, plaLocationEstimateJsonObject);
                        writeLongitude(longitude, plaLocationEstimateJsonObject);
                        writeAltitude(altitude, plaLocationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
                        writeLatitude(latitude, plaLocationEstimateJsonObject);
                        writeLongitude(longitude, plaLocationEstimateJsonObject);
                        writeAltitude(altitude, plaLocationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, plaLocationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, plaLocationEstimateJsonObject);
                        writeAngleOfMajorAxis(angleOfMajorAxis, plaLocationEstimateJsonObject);
                        writeUncertaintyAltitude(uncertaintyAltitude, plaLocationEstimateJsonObject);
                        writeConfidence(confidence, plaLocationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidArc")) {
                        writeLatitude(latitude, plaLocationEstimateJsonObject);
                        writeLongitude(longitude, plaLocationEstimateJsonObject);
                        writeInnerRadius(innerRadius, plaLocationEstimateJsonObject);
                        writeUncertaintyInnerRadius(uncertaintyInnerRadius, plaLocationEstimateJsonObject);
                        writeOffsetAngle(offsetAngle, plaLocationEstimateJsonObject);
                        writeIncludedAngle(includedAngle, plaLocationEstimateJsonObject);
                        writeConfidence(confidence, plaLocationEstimateJsonObject);
                    }
                }
                plaJsonObject.add("LocationEstimate", plaLocationEstimateJsonObject);
            }

            if (pla.getAgeOfLocationEstimate() != null) {
                if (pla.getAgeOfLocationEstimate() <= Integer.MAX_VALUE && pla.getAgeOfLocationEstimate() >= Integer.MIN_VALUE) {
                    ageOfLocationEstimate = Integer.valueOf(AVPHandler.long2Int(pla.getAgeOfLocationEstimate()));
                    writeAgeOfLocationEstimate(ageOfLocationEstimate, plaJsonObject);
                }
            }

            if (pla.getAccuracyFulfilmentIndicator() != null) {
                accuracyFulfilmentIndicator = Integer.valueOf(AVPHandler.diamAccFulInd2MapAccFulInd(pla.getAccuracyFulfilmentIndicator()).getIndicator());
                writeAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator, plaJsonObject);
            }

            if (pla.getCellGlobalIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength cellGlobalId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(pla.getCellGlobalIdentity());
                try {
                    cgiMcc = cellGlobalId.getMCC();
                    cgiMnc = cellGlobalId.getMNC();
                    cgiLac = cellGlobalId.getLac();
                    cgiCi = cellGlobalId.getCellIdOrServiceAreaCode();
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                // Write CGI values from SLg PLA
                writeMcc(cgiMcc, cgiJsonObject);
                writeMnc(cgiMnc, cgiJsonObject);
                writeLac(cgiLac, cgiJsonObject);
                writeCellId(cgiCi, cgiJsonObject);
                plaJsonObject.add("CGI", cgiJsonObject);
            }

            if (pla.getServiceAreaIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength serviceAreaId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(pla.getServiceAreaIdentity());
                try {
                    saiMcc = serviceAreaId.getMCC();
                    saiMnc = serviceAreaId.getMNC();
                    saiLac = serviceAreaId.getLac();
                    sac = serviceAreaId.getCellIdOrServiceAreaCode();
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                // Write SAI values from SLg PLA
                writeMcc(saiMcc, saiJsonObject);
                writeMnc(saiMnc, saiJsonObject);
                writeLac(saiLac, saiJsonObject);
                writeServiceAreaCode(sac, saiJsonObject);
                plaJsonObject.add("SAI", saiJsonObject);
            }

            if (pla.getEcgi() != null) {
                eutranCgi = new EUTRANCGIImpl(pla.getEcgi());
                try {
                    ecgiMcc = eutranCgi.getMCC();
                    ecgiMnc = eutranCgi.getMNC();
                    eci = eutranCgi.getEci();
                    eNBId = eutranCgi.getENodeBId();
                    ecgiCi = eutranCgi.getCi();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pla.getEsmlcCellInfoAvp() != null) {
                if (pla.getEsmlcCellInfoAvp().getECGI() != null) {
                    eutranCgi = new EUTRANCGIImpl(pla.getEsmlcCellInfoAvp().getECGI());
                    try {
                        ecgiMcc = eutranCgi.getMCC();
                        ecgiMnc = eutranCgi.getMNC();
                        eci = eutranCgi.getEci();
                        eNBId = eutranCgi.getENodeBId();
                        ecgiCi = eutranCgi.getCi();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (pla.getEsmlcCellInfoAvp().getCellPortionID() > -1)
                    cellPortionId = pla.getEsmlcCellInfoAvp().getCellPortionID();
            }
            if (pla.getEcgi() != null || pla.getEsmlcCellInfoAvp() != null) {
                // Write ECGI or ESMLC Cell Info values from SLg PLA
                writeMcc(ecgiMcc, ecgiJsonObject);
                writeMnc(ecgiMnc, ecgiJsonObject);
                writeEUtranEci(eci, ecgiJsonObject);
                writeENBId(eNBId, ecgiJsonObject);
                writeEUtranCellId(ecgiCi, ecgiJsonObject);
                writeCellPortionId(cellPortionId, ecgiJsonObject);
                plaJsonObject.add("ECGI", ecgiJsonObject);
            }

            if (pla.getGeranPositioningInfoAvp() != null) {
                if (pla.getGeranPositioningInfoAvp().getGERANPositioningData() != null) {
                    PositioningDataInformation geranPositioningDataInformation = AVPHandler.lteGeranPosDataInfo2MapGeranPosDataInfo(pla.getGeranPositioningInfoAvp().getGERANPositioningData());
                    geranPositioningInfo = bytesToHexString(geranPositioningDataInformation.getData());
                }
                if (pla.getGeranPositioningInfoAvp().getGERANGANSSPositioningData() != null) {
                    GeranGANSSpositioningData geranGANSSpositioningData = AVPHandler.lteGeranGanssPosDataInfo2MapGeranGanssPosDataInfo(pla.getGeranPositioningInfoAvp().getGERANGANSSPositioningData());
                    geranGanssPositioningData = bytesToHexString(geranGANSSpositioningData.getData());
                }
                // Write GERAN Positioning Info values from SLg PLA
                writeGeranPositioningData(geranPositioningInfo, plaGeranPosInfoJsonObject);
                writeGeranGanssPositioningData(geranGanssPositioningData, plaGeranPosInfoJsonObject);
                plaJsonObject.add("GERANPositioningInfo", plaGeranPosInfoJsonObject);
            }

            if (pla.getUtranPositioningInfoAvp() != null) {
                if (pla.getUtranPositioningInfoAvp().getUTRANPositioningData() != null) {
                    UtranPositioningDataInfo utranPositioningDataInfo = AVPHandler.lteUtranPosData2MapUtranPosDataInfo(pla.getUtranPositioningInfoAvp().getUTRANPositioningData());
                    utranPositioningData = bytesToHexString(utranPositioningDataInfo.getData());
                }
                if (pla.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData() != null) {
                    UtranGANSSpositioningData utranGANSSpositioningData = AVPHandler.lteUtranGanssPosData2MapUtranGanssPosDataInfo(pla.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData());
                    utranGanssPositioningData = bytesToHexString(utranGANSSpositioningData.getData());
                }
                if (pla.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData() != null) {
                    utranAdditionalPositioningData = bytesToHexString(pla.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData());
                }
                // Write UTRAN Positioning Info values from SLg PLA
                writeUtranPositioningData(utranPositioningData, plaUtranPosInfoJsonObject);
                writeUtranGanssPositioningData(utranGanssPositioningData, plaUtranPosInfoJsonObject);
                writeUtranAdditionalPositioningData(utranAdditionalPositioningData, plaUtranPosInfoJsonObject);
                plaJsonObject.add("UTRANPositioningInfo", plaUtranPosInfoJsonObject);
            }

            if (pla.geteUtranPositioningData() != null) {
                eUtranPositioningData = bytesToHexString(pla.geteUtranPositioningData());
                // Write EUTRAN Positioning Info values from SLg PLA
                writeEUtranPositioningData(eUtranPositioningData, plaEUtranPosInfoJsonObject);
                plaJsonObject.add("E-UTRANPositioningInfo", plaEUtranPosInfoJsonObject);
            }

            if (pla.getVelocityEstimate() != null) {
                VelocityEstimate lteVelocityEstimate = AVPHandler.lteVelocityEstimate2MapVelocityEstimate(pla.getVelocityEstimate());
                if (lteVelocityEstimate.getHorizontalSpeed() > -1)
                    horizontalSpeed = lteVelocityEstimate.getHorizontalSpeed();
                if (lteVelocityEstimate.getBearing() > -1)
                    bearing = lteVelocityEstimate.getBearing();
                if (lteVelocityEstimate.getVerticalSpeed() > -1)
                    verticalSpeed = lteVelocityEstimate.getVerticalSpeed();
                if (lteVelocityEstimate.getUncertaintyHorizontalSpeed() > -1)
                    uncertaintyHorizontalSpeed = lteVelocityEstimate.getUncertaintyHorizontalSpeed();
                if (lteVelocityEstimate.getUncertaintyVerticalSpeed() > -1)
                    uncertaintyVerticalSpeed = lteVelocityEstimate.getUncertaintyVerticalSpeed();
                if (lteVelocityEstimate.getVelocityType() != null)
                    velocityType = lteVelocityEstimate.getVelocityType().name();
                // Write Velocity Estimate values from SLg PLA
                writeHorizontalSpeed(horizontalSpeed, plaVelocityEstimateJsonObject);
                writeBearing(bearing, plaVelocityEstimateJsonObject);
                writeVerticalSpeed(verticalSpeed, plaVelocityEstimateJsonObject);
                writeUncertaintyHorizontalSpeed(uncertaintyHorizontalSpeed, plaVelocityEstimateJsonObject);
                writeUncertaintyVerticalSpeed(uncertaintyVerticalSpeed, plaVelocityEstimateJsonObject);
                writeVelocityType(velocityType, plaVelocityEstimateJsonObject);
                plaJsonObject.add("VelocityEstimate", plaVelocityEstimateJsonObject);
            }

            if (pla.getCivicAddress() != null) {
                civicAddress = AVPHandler.byte2String(pla.getCivicAddress());
                // Write Civic Address from SLg PLA
                writeCivicAddress(civicAddress, plaJsonObject);
            }

            if (pla.getBarometricPressure() != null) {
                barometricPressure = pla.getBarometricPressure();
                // Write Barometric Pressure from SLg PLA
                writeBarometricPressure(barometricPressure, plaJsonObject);
            }
        }

        // Write values retrieved from SLh RIA
        riaPlaJsonObject.add("Routing-Info-Answer", riaJsonObject);
        // Write values retrieved from SLg PLA
        riaPlaJsonObject.add("Provide-Location-Answer", plaJsonObject);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String sriPlrResponseJson = gson.toJson(riaPlaJsonObject);
        return sriPlrResponseJson;
    }
}
