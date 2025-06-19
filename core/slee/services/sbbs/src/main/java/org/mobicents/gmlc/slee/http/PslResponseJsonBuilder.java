package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.map.PslResponseValues;
import org.mobicents.gmlc.slee.map.SriForLcsResponseValues;
import org.mobicents.gmlc.slee.primitives.EllipsoidPoint;
import org.mobicents.gmlc.slee.primitives.Polygon;
import org.mobicents.gmlc.slee.primitives.PolygonImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;

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
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAdditionalNetworkNodeNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAgeOfLocationEstimate;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAltitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAngleOfMajorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeBearing;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeClientReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeDeferredMTLRresponseIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranGanssPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGprsNodeIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeHGmlcAddress;
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
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMoLrShortCircuitIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetworkNodeNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberOfPoints;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOffsetAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writePprAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeTypeOfShape;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertainty;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyAltitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyHorizontalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyInnerRadius;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintySemiMajorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintySemiMinorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUncertaintyVerticalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranGanssPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeUtranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVGmlcAddress;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVelocityType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVerticalSpeed;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class PslResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(PslResponseJsonBuilder.class);

    public PslResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param sri                   Subscriber Information values gathered from SRILCS response event
     * @param psl                   Subscriber Information values gathered from PSL response event
     * @param sriPslMsisdn          Subscriber's MSISDN
     * @param sriPslImsi            Subscriber's IMSI
     * @param clientReferenceNumber Reference Number gathered from the originating HTTP request sent by the GMLC Client
     * @param lcsReferenceNumber    LCS-ReferenceNumber exchanged between the GMLC and the UMTS core network
     */
    public static String buildJsonResponseForPsl(SriForLcsResponseValues sri, PslResponseValues psl, String sriPslMsisdn, String sriPslImsi,
                                                 Integer clientReferenceNumber, Integer lcsReferenceNumber) throws MAPException {

        String msisdn, imsi, lmsi, networkNodeNumber, additionalNumber, mmeName, sgsnName, tgppAAAServerName, hGmlcAddress, vGmlcAddress, pprAddress,
            typeOfShape,
            additionalTypeOfShape, velocityType, geranPositioningInfo, geranGanssPositioningData, utranPositioningData, utranGanssPositioningData;
        msisdn = imsi = lmsi = networkNodeNumber = additionalNumber = mmeName = sgsnName = tgppAAAServerName = hGmlcAddress = vGmlcAddress = pprAddress =
            typeOfShape = additionalTypeOfShape = velocityType = geranPositioningInfo = geranGanssPositioningData = utranPositioningData =
                utranGanssPositioningData = null;
        Boolean gprsNodeIndicator, deferredMTLRresponseIndicator, molrShortCircuitIndicator;
        gprsNodeIndicator = deferredMTLRresponseIndicator = molrShortCircuitIndicator = null;
        Double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, uncertaintyAltitude, uncertaintyInnerRadius,
            angleOfMajorAxis, offsetAngle, includedAngle;
        latitude = longitude = uncertainty = uncertaintySemiMajorAxis = uncertaintySemiMinorAxis = uncertaintyAltitude = uncertaintyInnerRadius =
            angleOfMajorAxis = offsetAngle = includedAngle = null;
        Double additionalLatitude, additionalLongitude, additionalUncertainty, additionalUncertaintySemiMajorAxis, additionalUncertaintySemiMinorAxis,
            additionalUncertaintyAltitude, additionalUncertaintyInnerRadius, additionalAngleOfMajorAxis, additionalOffsetAngle, additionalIncludedAngle;
        additionalLatitude = additionalLongitude = additionalUncertainty = additionalUncertaintySemiMajorAxis = additionalUncertaintySemiMinorAxis =
            additionalUncertaintyAltitude = additionalUncertaintyInnerRadius = additionalAngleOfMajorAxis = additionalOffsetAngle =
                additionalIncludedAngle = null;
        Integer mcc, mnc, lac, ciOrSac, aol, additionalNumberOfPoints, confidence, additionalConfidence, altitude, additionalAltitude,
            innerRadius, additionalInnerRadius, accuracyFulfilmentIndicator, horizontalSpeed, bearing, verticalSpeed, uncertaintyHorizontalSpeed,
            uncertaintyVerticalSpeed;
        mcc = mnc = lac = ciOrSac = aol = additionalNumberOfPoints = confidence = additionalConfidence = altitude = additionalAltitude =
            innerRadius = additionalInnerRadius = accuracyFulfilmentIndicator = horizontalSpeed = bearing = verticalSpeed = uncertaintyHorizontalSpeed =
                uncertaintyVerticalSpeed = null;
        Boolean saiPresent = false;
        Polygon polygon = null, additionalPolygon = null;
        EllipsoidPoint[] polygonEllipsoidPoints = null, additionalPolygonEllipsoidPoints = null;
        Double[][] polygonArray = null;

        JsonObject sriPslJsonObject = new JsonObject();
        writeNetwork("GSM/UMTS", sriPslJsonObject);
        writeProtocol("MAP", sriPslJsonObject);
        writeOperation("SRILCS-PSL", sriPslJsonObject);
        writeOperationResult("SUCCESS", sriPslJsonObject);
        writeClientReferenceNumber(clientReferenceNumber, sriPslJsonObject);
        writeLcsReferenceNumber(lcsReferenceNumber, sriPslJsonObject);
        JsonObject sriForLcsJsonObject = new JsonObject();
        JsonObject pslJsonObject = new JsonObject();
        JsonObject pslLocationEstimateJsonObject = new JsonObject();
        JsonObject pslAdditionalLocationEstimateJsonObject = new JsonObject();
        JsonObject pslAdditionalLocationEstimatePolygonPointsJsonObject = new JsonObject();
        JsonObject pslAdditionalLocationEstimatePolygonCentroidObject = new JsonObject();
        JsonObject pslVelocityEstimateJsonObject = new JsonObject();
        JsonObject pslCgiOrSaiOrLaiJsonObject = new JsonObject();
        JsonObject pslGeranInfoJsonObject = new JsonObject();
        JsonObject pslUtranInfoJsonObject = new JsonObject();


        if (sri != null) {
            // Get SRILCS response values
            if (sri.getMsisdn() != null) {
                msisdn = sri.getMsisdn().getAddress();
                writeMsisdn(msisdn, sriForLcsJsonObject);
            } else {
                msisdn = sriPslMsisdn;
                writeMsisdn(msisdn, sriForLcsJsonObject);
            }

            if (sri.getImsi() != null) {
                imsi = new String(sri.getImsi().getData().getBytes());
                writeImsi(imsi, sriForLcsJsonObject);
            } else {
                imsi = sriPslImsi;
                writeImsi(imsi, sriForLcsJsonObject);
            }

            if (sri.getLmsi() != null) {
                lmsi = bytesToHex(sri.getLmsi().getData());
                writeLmsi(lmsi, sriForLcsJsonObject);
            }

            if (sri.getNetworkNodeNumber() != null) {
                networkNodeNumber = sri.getNetworkNodeNumber().getAddress();
                writeNetworkNodeNumber(networkNodeNumber, sriForLcsJsonObject);
            }

            if (sri.getAdditionalNumber() != null) {
                if (sri.getAdditionalNumber().getMSCNumber() != null)
                    additionalNumber = sri.getAdditionalNumber().getMSCNumber().getAddress();
                else if (sri.getAdditionalNumber().getSGSNNumber() != null)
                    additionalNumber = sri.getAdditionalNumber().getSGSNNumber().getAddress();
                writeAdditionalNetworkNodeNumber(additionalNumber, sriForLcsJsonObject);
            }

            if (sri.isGprsNodeIndicator() != null) {
                gprsNodeIndicator = sri.isGprsNodeIndicator();
                writeGprsNodeIndicator(gprsNodeIndicator, sriForLcsJsonObject);
            }

            if (sri.getMmeName() != null) {
                mmeName = new String(sri.getMmeName().getData());
                writeMmeName(mmeName, sriForLcsJsonObject);
            }

            if (sri.getSgsnName() != null) {
                sgsnName = new String(sri.getSgsnName().getData());
                writeSgsnName(sgsnName, sriForLcsJsonObject);
            }

            if (sri.getAaaServerName() != null) {
                tgppAAAServerName = new String(sri.getAaaServerName().getData());
                write3gppAaaServerName(tgppAAAServerName, sriForLcsJsonObject);
            }

            if (sri.gethGmlcAddress() != null) {
                hGmlcAddress = bytesToHexString(sri.gethGmlcAddress().getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(hGmlcAddress));
                    hGmlcAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                writeHGmlcAddress(hGmlcAddress, sriForLcsJsonObject);
            }

            if (sri.getvGmlcAddress() != null) {
                vGmlcAddress = bytesToHexString(sri.getvGmlcAddress().getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(vGmlcAddress));
                    vGmlcAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                writeVGmlcAddress(vGmlcAddress, sriForLcsJsonObject);
            }

            if (sri.getPprAddress() != null) {
                pprAddress = bytesToHexString(sri.getPprAddress().getGSNAddressData());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(pprAddress));
                    pprAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                writePprAddress(pprAddress, sriForLcsJsonObject);
            }
        }

        if (psl != null) {
            // Get PSL response values
            // Location Estimate
            if (psl.getLocationEstimate() != null) {
                ExtGeographicalInformation locationEstimate = psl.getLocationEstimate();
                typeOfShape = locationEstimate.getTypeOfShape().name();
                if (locationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    latitude = locationEstimate.getLatitude();
                    longitude = locationEstimate.getLongitude();
                    uncertainty = locationEstimate.getUncertainty();
                    uncertaintySemiMajorAxis = locationEstimate.getUncertaintySemiMajorAxis();
                    uncertaintySemiMinorAxis = locationEstimate.getUncertaintySemiMinorAxis();
                    angleOfMajorAxis = locationEstimate.getAngleOfMajorAxis();
                    confidence = locationEstimate.getConfidence();
                    altitude = locationEstimate.getAltitude();
                    uncertaintyAltitude = locationEstimate.getUncertaintyAltitude();
                    innerRadius = locationEstimate.getInnerRadius();
                    uncertaintyInnerRadius = locationEstimate.getUncertaintyRadius();
                    offsetAngle = locationEstimate.getOffsetAngle();
                    includedAngle = locationEstimate.getIncludedAngle();
                }
            }

            // Additional Location Estimate
            if (psl.getAdditionalLocationEstimate() != null) {
                AddGeographicalInformation additionalLocationEstimate = psl.getAdditionalLocationEstimate();
                additionalTypeOfShape = additionalLocationEstimate.getTypeOfShape().name();
                if (additionalLocationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    additionalLatitude = additionalLocationEstimate.getLatitude();
                    additionalLongitude = additionalLocationEstimate.getLongitude();
                    additionalUncertainty = additionalLocationEstimate.getUncertainty();
                    additionalUncertaintySemiMajorAxis = additionalLocationEstimate.getUncertaintySemiMajorAxis();
                    additionalUncertaintySemiMinorAxis = additionalLocationEstimate.getUncertaintySemiMinorAxis();
                    additionalAngleOfMajorAxis = additionalLocationEstimate.getAngleOfMajorAxis();
                    additionalConfidence = additionalLocationEstimate.getConfidence();
                    additionalAltitude = additionalLocationEstimate.getAltitude();
                    additionalUncertaintyAltitude = additionalLocationEstimate.getUncertaintyAltitude();
                    additionalInnerRadius = additionalLocationEstimate.getInnerRadius();
                    additionalUncertaintyInnerRadius = additionalLocationEstimate.getUncertaintyRadius();
                    additionalOffsetAngle = additionalLocationEstimate.getOffsetAngle();
                    additionalIncludedAngle = additionalLocationEstimate.getIncludedAngle();
                } else {
                    // PSL Additional Location Estimate for TypeOfShape.Polygon
                    additionalPolygon = new PolygonImpl(additionalLocationEstimate.getData());
                    additionalNumberOfPoints = additionalPolygon.getNumberOfPoints();
                    additionalPolygonEllipsoidPoints = new EllipsoidPoint[additionalNumberOfPoints];
                    for (int point = 0; point < additionalNumberOfPoints; point++) {
                        additionalPolygonEllipsoidPoints[point] = additionalPolygon.getEllipsoidPoint(point);
                    }
                    try {
                        ((PolygonImpl) additionalPolygon).setData(additionalPolygonEllipsoidPoints);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Write Location Estimate values from PSL
            if (typeOfShape != null) {
                writeTypeOfShape(typeOfShape, pslLocationEstimateJsonObject);
                if (typeOfShape.equalsIgnoreCase("EllipsoidPoint")) {
                    writeLatitude(latitude, pslLocationEstimateJsonObject);
                    writeLongitude(longitude, pslLocationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle")) {
                    writeLatitude(latitude, pslLocationEstimateJsonObject);
                    writeLongitude(longitude, pslLocationEstimateJsonObject);
                    writeUncertainty(uncertainty, pslLocationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse")) {
                    writeLatitude(latitude, pslLocationEstimateJsonObject);
                    writeLongitude(longitude, pslLocationEstimateJsonObject);
                    writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, pslLocationEstimateJsonObject);
                    writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, pslLocationEstimateJsonObject);
                    writeAngleOfMajorAxis(angleOfMajorAxis, pslLocationEstimateJsonObject);
                    writeConfidence(confidence, pslLocationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
                    writeLatitude(latitude, pslLocationEstimateJsonObject);
                    writeLongitude(longitude, pslLocationEstimateJsonObject);
                    writeAltitude(altitude, pslLocationEstimateJsonObject);
                    writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, pslLocationEstimateJsonObject);
                    writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, pslLocationEstimateJsonObject);
                    writeAngleOfMajorAxis(angleOfMajorAxis, pslLocationEstimateJsonObject);
                    writeUncertaintyAltitude(uncertaintyAltitude, pslLocationEstimateJsonObject);
                    writeConfidence(confidence, pslLocationEstimateJsonObject);
                } else if (typeOfShape.equalsIgnoreCase("EllipsoidArc")) {
                    writeLatitude(latitude, pslLocationEstimateJsonObject);
                    writeLongitude(longitude, pslLocationEstimateJsonObject);
                    writeInnerRadius(innerRadius, pslLocationEstimateJsonObject);
                    writeUncertaintyInnerRadius(uncertaintyInnerRadius, pslLocationEstimateJsonObject);
                    writeOffsetAngle(offsetAngle, pslLocationEstimateJsonObject);
                    writeIncludedAngle(includedAngle, pslLocationEstimateJsonObject);
                    writeConfidence(confidence, pslLocationEstimateJsonObject);
                }
            }
            if (additionalTypeOfShape != null) {
                if (additionalTypeOfShape.equalsIgnoreCase("Polygon")) {
                    typeOfShape = "Polygon";
                    writeTypeOfShape(typeOfShape, pslLocationEstimateJsonObject);
                }
            }
            pslJsonObject.add("LocationEstimate", pslLocationEstimateJsonObject);

            if (psl.getAdditionalLocationEstimate() != null) {
                // Write Additional Location Estimate values from PSL
                writeTypeOfShape(additionalTypeOfShape, pslAdditionalLocationEstimateJsonObject);
                if (additionalTypeOfShape != null) {
                    if (additionalTypeOfShape.equalsIgnoreCase("EllipsoidPoint")) {
                        writeLatitude(additionalLatitude, pslAdditionalLocationEstimateJsonObject);
                        writeLongitude(additionalLongitude, pslAdditionalLocationEstimateJsonObject);
                    } else if (additionalTypeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle")) {
                        writeLatitude(additionalLatitude, pslAdditionalLocationEstimateJsonObject);
                        writeLongitude(additionalLongitude, pslAdditionalLocationEstimateJsonObject);
                        writeUncertainty(additionalUncertainty, pslAdditionalLocationEstimateJsonObject);
                    } else if (additionalTypeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse")) {
                        writeLatitude(additionalLatitude, pslAdditionalLocationEstimateJsonObject);
                        writeLongitude(additionalLongitude, pslAdditionalLocationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(additionalUncertaintySemiMajorAxis, pslAdditionalLocationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(additionalUncertaintySemiMinorAxis, pslAdditionalLocationEstimateJsonObject);
                        writeAngleOfMajorAxis(additionalAngleOfMajorAxis, pslAdditionalLocationEstimateJsonObject);
                        writeConfidence(additionalConfidence, pslAdditionalLocationEstimateJsonObject);
                    } else if (additionalTypeOfShape.equalsIgnoreCase("Polygon")) {
                        polygonArray = new Double[additionalNumberOfPoints][additionalNumberOfPoints];
                        Double lat, lon;
                        if (additionalNumberOfPoints > 2 && additionalNumberOfPoints <= 15) {
                            writeNumberOfPoints(additionalNumberOfPoints, pslAdditionalLocationEstimateJsonObject);
                            for (int index=0; index < additionalNumberOfPoints; index++) {
                                lat = additionalPolygon.getEllipsoidPoint(index).getLatitude();
                                lon = additionalPolygon.getEllipsoidPoint(index).getLongitude();
                                polygonArray[index][0] = lat;
                                polygonArray[index][1] = lon;
                                String additionalPolygonPoint = "polygonPoint"+(index+1);
                                writeLatitude(lat, pslAdditionalLocationEstimatePolygonPointsJsonObject);
                                writeLongitude(lon, pslAdditionalLocationEstimatePolygonPointsJsonObject);
                                pslAdditionalLocationEstimateJsonObject.add(additionalPolygonPoint, pslAdditionalLocationEstimatePolygonPointsJsonObject);
                                pslAdditionalLocationEstimatePolygonPointsJsonObject = new JsonObject();
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
                                writeLatitude(polygonCentroid(point2D).getX(), pslAdditionalLocationEstimatePolygonCentroidObject);
                                writeLongitude(polygonCentroid(point2D).getY(), pslAdditionalLocationEstimatePolygonCentroidObject);
                                pslAdditionalLocationEstimateJsonObject.add("polygonCentroid", pslAdditionalLocationEstimatePolygonCentroidObject);
                            }
                        }
                    } else if (additionalTypeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitude")) {
                        writeLatitude(additionalLatitude, pslAdditionalLocationEstimateJsonObject);
                        writeLongitude(additionalLongitude, pslAdditionalLocationEstimateJsonObject);
                        writeAltitude(additionalAltitude, pslAdditionalLocationEstimateJsonObject);
                    } else if (additionalTypeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
                        writeLatitude(additionalLatitude, pslAdditionalLocationEstimateJsonObject);
                        writeLongitude(additionalLongitude, pslAdditionalLocationEstimateJsonObject);
                        writeAltitude(additionalAltitude, pslAdditionalLocationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(additionalUncertaintySemiMajorAxis, pslAdditionalLocationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(additionalUncertaintySemiMinorAxis, pslAdditionalLocationEstimateJsonObject);
                        writeAngleOfMajorAxis(additionalAngleOfMajorAxis, pslAdditionalLocationEstimateJsonObject);
                        writeUncertaintyAltitude(additionalUncertaintyAltitude, pslAdditionalLocationEstimateJsonObject);
                        writeConfidence(additionalConfidence, pslAdditionalLocationEstimateJsonObject);
                    } else if (additionalTypeOfShape.equalsIgnoreCase("EllipsoidArc")) {
                        writeLatitude(additionalLatitude, pslAdditionalLocationEstimateJsonObject);
                        writeLongitude(additionalLongitude, pslAdditionalLocationEstimateJsonObject);
                        writeInnerRadius(additionalInnerRadius, pslAdditionalLocationEstimateJsonObject);
                        writeUncertaintyInnerRadius(additionalUncertaintyInnerRadius, pslAdditionalLocationEstimateJsonObject);
                        writeOffsetAngle(additionalOffsetAngle, pslAdditionalLocationEstimateJsonObject);
                        writeIncludedAngle(additionalIncludedAngle, pslAdditionalLocationEstimateJsonObject);
                        writeConfidence(additionalConfidence, pslAdditionalLocationEstimateJsonObject);
                    }
                }
                pslJsonObject.add("AdditionalLocationEstimate", pslAdditionalLocationEstimateJsonObject);
            }

            if (psl.getAgeOfLocationEstimate() <= Integer.MAX_VALUE && psl.getAgeOfLocationEstimate() >= Integer.MIN_VALUE) {
                aol = psl.getAgeOfLocationEstimate();
                writeAgeOfLocationEstimate(aol, pslJsonObject);
            }

            if (psl.getAccuracyFulfilmentIndicator() != null) {
                accuracyFulfilmentIndicator = psl.getAccuracyFulfilmentIndicator().getIndicator();
                writeAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator, pslJsonObject);
            }

            if (psl.isDeferredMTLRResponseIndicator() != null) {
                deferredMTLRresponseIndicator = psl.isDeferredMTLRResponseIndicator();
                writeDeferredMTLRresponseIndicator(deferredMTLRresponseIndicator, pslJsonObject);
            }

            if (psl.isMoLrShortCircuitIndicator() != null) {
                molrShortCircuitIndicator = psl.isMoLrShortCircuitIndicator();
                writeMoLrShortCircuitIndicator(molrShortCircuitIndicator, pslJsonObject);
            }

            if (psl.getSaiPresent() != null) {
                if (psl.getSaiPresent() == true)
                    saiPresent = true;
            }
            if (psl.getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                CellGlobalIdOrServiceAreaIdOrLAI cgiOrSaiOrLai = psl.getCellGlobalIdOrServiceAreaIdOrLAI();
                LAIFixedLength laiFixedLength = cgiOrSaiOrLai.getLAIFixedLength();
                CellGlobalIdOrServiceAreaIdFixedLength cellGlobalIdOrServiceAreaIdFixedLength = cgiOrSaiOrLai.getCellGlobalIdOrServiceAreaIdFixedLength();
                if (laiFixedLength != null) {
                    mcc = laiFixedLength.getMCC();
                    mnc = laiFixedLength.getMNC();
                    lac = laiFixedLength.getLac();
                } else if (cellGlobalIdOrServiceAreaIdFixedLength != null) {
                    mcc = cellGlobalIdOrServiceAreaIdFixedLength.getMCC();
                    mnc = cellGlobalIdOrServiceAreaIdFixedLength.getMNC();
                    lac = cellGlobalIdOrServiceAreaIdFixedLength.getLac();
                    ciOrSac = cellGlobalIdOrServiceAreaIdFixedLength.getCellIdOrServiceAreaCode();
                }
                // Write CGI or SAI values from PSL
                writeMcc(mcc, pslCgiOrSaiOrLaiJsonObject);
                writeMnc(mnc, pslCgiOrSaiOrLaiJsonObject);
                writeLac(lac, pslCgiOrSaiOrLaiJsonObject);
                if (!saiPresent) {
                    if (ciOrSac != null) {
                        writeCellId(ciOrSac, pslCgiOrSaiOrLaiJsonObject);
                        pslJsonObject.add("CGI", pslCgiOrSaiOrLaiJsonObject);
                    } else {
                        if (lac != null)
                            pslJsonObject.add("LAI", pslCgiOrSaiOrLaiJsonObject);
                    }
                } else {
                    if (ciOrSac != null) {
                        writeServiceAreaCode(ciOrSac, pslCgiOrSaiOrLaiJsonObject);
                        pslJsonObject.add("SAI", pslCgiOrSaiOrLaiJsonObject);
                    } else {
                        if (lac != null)
                            pslJsonObject.add("LAI", pslCgiOrSaiOrLaiJsonObject);
                    }
                }
            }

            if (psl.getGeranPositioningDataInformation() != null || psl.getGeranGANSSpositioningData() != null) {
                if (psl.getGeranPositioningDataInformation() != null) {
                    geranPositioningInfo = bytesToHexString(psl.getGeranPositioningDataInformation().getData());
                    writeGeranPositioningData(geranPositioningInfo, pslGeranInfoJsonObject);
                }
                if (psl.getGeranGANSSpositioningData() != null) {
                    geranGanssPositioningData = bytesToHexString(psl.getGeranGANSSpositioningData().getData());
                    writeGeranGanssPositioningData(geranGanssPositioningData, pslGeranInfoJsonObject);
                }
                // Write GERAN Positioning Info values from PSL
                pslJsonObject.add("GERANPositioningInfo", pslGeranInfoJsonObject);
            }

            if (psl.getUtranPositioningDataInfo() != null || psl.getUtranGANSSpositioningData() != null) {
                if (psl.getUtranPositioningDataInfo() != null) {
                    utranPositioningData = bytesToHexString(psl.getUtranPositioningDataInfo().getData());
                    writeUtranPositioningData(utranPositioningData, pslUtranInfoJsonObject);
                }
                if (psl.getUtranGANSSpositioningData() != null) {
                    utranGanssPositioningData = bytesToHexString(psl.getUtranGANSSpositioningData().getData());
                    writeUtranGanssPositioningData(utranGanssPositioningData, pslUtranInfoJsonObject);
                }
                // Write UTRAN Positioning Info values from PSL
                pslJsonObject.add("UTRANPositioningInfo", pslUtranInfoJsonObject);
            }

            if (psl.getVelocityEstimate() != null) {
                if (psl.getVelocityEstimate().getHorizontalSpeed() > -1)
                    horizontalSpeed = psl.getVelocityEstimate().getHorizontalSpeed();
                if (psl.getVelocityEstimate().getBearing() > -1)
                    bearing = psl.getVelocityEstimate().getBearing();
                if (psl.getVelocityEstimate().getVerticalSpeed() > -1)
                    verticalSpeed = psl.getVelocityEstimate().getVerticalSpeed();
                if (psl.getVelocityEstimate().getUncertaintyHorizontalSpeed() > -1)
                    uncertaintyHorizontalSpeed = psl.getVelocityEstimate().getUncertaintyHorizontalSpeed();
                if (psl.getVelocityEstimate().getUncertaintyVerticalSpeed() > -1)
                    uncertaintyVerticalSpeed = psl.getVelocityEstimate().getUncertaintyVerticalSpeed();
                if (psl.getVelocityEstimate().getVelocityType() != null)
                    velocityType = psl.getVelocityEstimate().getVelocityType().name();
                // Write Velocity Estimate values from PSL
                writeHorizontalSpeed(horizontalSpeed, pslVelocityEstimateJsonObject);
                writeBearing(bearing, pslVelocityEstimateJsonObject);
                writeVerticalSpeed(verticalSpeed, pslVelocityEstimateJsonObject);
                writeUncertaintyHorizontalSpeed(uncertaintyHorizontalSpeed, pslVelocityEstimateJsonObject);
                writeUncertaintyVerticalSpeed(uncertaintyVerticalSpeed, pslVelocityEstimateJsonObject);
                writeVelocityType(velocityType, pslVelocityEstimateJsonObject);
                pslJsonObject.add("VelocityEstimate", pslVelocityEstimateJsonObject);
            }
        }

        // Write values retrieved from SRILCS
        sriPslJsonObject.add("SRILCS", sriForLcsJsonObject);
        // Write values retrieved from PSL
        sriPslJsonObject.add("PSL", pslJsonObject);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String sriPslResponseJson = gson.toJson(sriPslJsonObject);
        return sriPslResponseJson;
    }
}
