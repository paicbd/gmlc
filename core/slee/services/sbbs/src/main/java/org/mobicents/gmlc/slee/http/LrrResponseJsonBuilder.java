package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.diameter.AVPHandler;
import org.mobicents.gmlc.slee.diameter.slg.SLgLrrAvpValues;
import org.mobicents.gmlc.slee.primitives.EUTRANCGI;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.mobicents.gmlc.slee.primitives.EllipsoidPoint;
import org.mobicents.gmlc.slee.primitives.Polygon;
import org.mobicents.gmlc.slee.primitives.PolygonImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;

import java.awt.geom.Point2D;
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
import static org.mobicents.gmlc.slee.http.JsonWriter.writeDeferredLocationType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeENBId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranEci;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeEUtranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranGanssPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeHorizontalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImei;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeIncludedAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInnerRadius;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSCapabilitySets;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSEPSClientFormatIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSEPSClientNameString;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSServiceTypeID;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLRRTerminationCause;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsPseudonymIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsQoSClass;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLocationEvent;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeRealm;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMscNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberOfPoints;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOffsetAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeReportingAmount;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeReportingInterval;
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

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class LrrResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(LrrResponseJsonBuilder.class);

    public LrrResponseJsonBuilder() {
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param lrr                   LRR Request values gathered from LRR request event
     * @param clientReferenceNumber Reference Number gathered from the originating HTTP request sent by the GMLC Client
     */
    public static String buildJsonReportForLRR(SLgLrrAvpValues lrr, Integer clientReferenceNumber) throws MAPException {

        Integer lcsReferenceNumber, clientRefNum, locationEvent, lcsFormatIndicator, altitude, innerRadius, confidence, numberOfPoints, accuracyFulfilmentIndicator,
            lcsQoSClassValue, cgiMcc, cgiMnc, cgiLac, ci, saiMcc, saiMnc, saiLac, sac, ecgiMcc, ecgiMnc, ecgiCi, horizontalSpeed, bearing, verticalSpeed,
            uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed, lcsPseudonymIndicator;
        lcsReferenceNumber = clientRefNum = locationEvent = lcsFormatIndicator = altitude = innerRadius = confidence = numberOfPoints = accuracyFulfilmentIndicator =
            lcsQoSClassValue = cgiMcc = cgiMnc = cgiLac = ci = saiMcc = saiMnc = saiLac = sac = ecgiMcc = ecgiMnc = ecgiCi = horizontalSpeed = bearing =
                verticalSpeed = uncertaintyHorizontalSpeed = uncertaintyVerticalSpeed = lcsPseudonymIndicator = null;
        Long lcsServiceTypeId, ageOfLocationEstimate, eci, eNBId, cellPortionId, reportingAmount, reportingInterval, deferredLocationType, dMtLrterminationCause,
            lcsCapabilitySets, dlrTerminationCause, mtlrLcsCapabilitySets, dlrLcsCapabilitySets, barometricPressure;
        lcsServiceTypeId = ageOfLocationEstimate = eci = eNBId = cellPortionId = reportingAmount = reportingInterval = deferredLocationType = dMtLrterminationCause =
            lcsCapabilitySets = dlrTerminationCause = mtlrLcsCapabilitySets = dlrLcsCapabilitySets = barometricPressure = null;
        String msisdn, imsi, imei, lcsEPSClientName, typeOfShape, geranPositioningData, geranGanssPositioningData,
            utranPositioningData, utranGanssPositioningData, utranAdditionalPositioningData, eUtranPositioningData, velocityType,
            mmeName, mmeRealm, sgsnName, sgsnRealm, sgsnNumber, tgppAAAServerName, mscNumber,
            mtlrMmeName, mtlrMmeRealm, mtlrSgsnName, mtlrSgsnRealm, mtlrSgsnNumber, mtlr3gppAAAServerName, mtlrMscNumber,
            dlrMmeName, dlrMmeRealm, dlrSgsnName, dlrSgsnRealm, dlrSgsnNumber, dlr3gppAAAServerName, dlrMscNumber, civicAddress;
        msisdn = imsi = imei = lcsEPSClientName = typeOfShape = geranPositioningData = geranGanssPositioningData = utranPositioningData = utranGanssPositioningData =
            utranAdditionalPositioningData = eUtranPositioningData = velocityType = mmeName = mmeRealm = sgsnName = sgsnRealm = sgsnNumber = tgppAAAServerName =
                mscNumber = mtlrMmeName = mtlrMmeRealm = mtlrSgsnName = mtlrSgsnRealm = mtlrSgsnNumber = mtlr3gppAAAServerName = mtlrMscNumber =
                    dlrMmeName = dlrMmeRealm = dlrSgsnName = dlrSgsnRealm = dlrSgsnNumber = dlr3gppAAAServerName = dlrMscNumber = civicAddress = null;
        Double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, uncertaintyAltitude, uncertaintyInnerRadius,
            angleOfMajorAxis, offsetAngle, includedAngle;
        latitude = longitude = uncertainty = uncertaintySemiMajorAxis = uncertaintySemiMinorAxis = uncertaintyAltitude = uncertaintyInnerRadius =
            angleOfMajorAxis = offsetAngle = includedAngle = null;
        Polygon polygon = null;
        EllipsoidPoint[] polygonEllipsoidPoints = null;
        Double[][] polygonArray = null;

        JsonObject lrrJsonObject = new JsonObject();
        writeNetwork("LTE", lrrJsonObject);
        writeProtocol("Diameter SLg (ELP)", lrrJsonObject);
        writeOperation("LRR", lrrJsonObject);
        writeOperationResult("SUCCESS", lrrJsonObject);
        JsonObject lcsEPSClientNameJsonObject = new JsonObject();
        JsonObject locationEstimateJsonObject = new JsonObject();
        JsonObject lrrLocationEstimatePolygonPointsJsonObject = new JsonObject();
        JsonObject lrrLocationEstimatePolygonCentroidObject = new JsonObject();
        JsonObject cellGlobalIdentityJsonObject = new JsonObject();
        JsonObject serviceAreaIdentityJsonObject = new JsonObject();
        JsonObject eCGIorESMLCCellInfoJsonObject = new JsonObject();
        JsonObject geranPositioningInfoJsonObject = new JsonObject();
        JsonObject utranPositioningInfoJsonObject = new JsonObject();
        JsonObject eUtranPositioningInfoJsonObject = new JsonObject();
        JsonObject servingNodeJsonObject = new JsonObject();
        JsonObject velocityEstimateJsonObject = new JsonObject();
        JsonObject periodicLDRInfoJsonObject = new JsonObject();
        JsonObject deferredMTLRDataJsonObject = new JsonObject();
        JsonObject deferredMTLRDataServingNodeJsonObject = new JsonObject();
        JsonObject delayedLocationReportingDataJsonObject = new JsonObject();
        JsonObject dlrDataServingNodeJsonObject = new JsonObject();

        if (lrr != null) {

            if (lrr.getMsisdn() != null) {
                msisdn = AVPHandler.tbcd2IsdnAddressString(lrr.getMsisdn()).getAddress();
                writeMsisdn(msisdn, lrrJsonObject);
            }

            if (lrr.getUserName() != null) {
                imsi = new String(AVPHandler.userName2Imsi(lrr.getUserName()).getData().getBytes());
                writeImsi(imsi, lrrJsonObject);
            }

            if (lrr.getLcsReferenceNumber() != null) {
                lcsReferenceNumber = AVPHandler.byte2Int(lrr.getLcsReferenceNumber());
                writeLcsReferenceNumber(lcsReferenceNumber, lrrJsonObject);
            }

            if (clientReferenceNumber != null) {
                clientRefNum = clientReferenceNumber;
                writeClientReferenceNumber(clientRefNum, lrrJsonObject);
            }

            if (lrr.getImei() != null) {
                imei = AVPHandler.string2MapImei(lrr.getImei()).getIMEI();
                writeImei(imei, lrrJsonObject);
            }

            if (lrr.getLcsServiceTypeId() != null) {
                lcsServiceTypeId = lrr.getLcsServiceTypeId();
                writeLCSServiceTypeID(lcsServiceTypeId, lrrJsonObject);
            }

            if (lrr.getLocationEvent() != null) {
                locationEvent = lrr.getLocationEvent().getValue();
                writeLocationEvent(locationEvent, lrrJsonObject);
            }

            /*** LCS-EPS-Client-Name AVP ***/
            if (lrr.getLcsEPSClientName() != null) {
                if (lrr.getLcsEPSClientName().getLCSNameString() != null) {
                    lcsEPSClientName = lrr.getLcsEPSClientName().getLCSNameString();
                    writeLCSEPSClientNameString(lcsEPSClientName, lcsEPSClientNameJsonObject);
                }
                if (lrr.getLcsEPSClientName().getLCSFormatIndicator() != null) {
                    lcsFormatIndicator = lrr.getLcsEPSClientName().getLCSFormatIndicator().getValue();
                    writeLCSEPSClientFormatIndicator(lcsFormatIndicator, lcsEPSClientNameJsonObject);
                }
                lrrJsonObject.add("lcsEPSClientName", lcsEPSClientNameJsonObject);
            }

            /*** Location-Estimate AVP ***/
            if (lrr.getLocationEstimate() != null) {
                ExtGeographicalInformation lteLocationEstimate = AVPHandler.lteLocationEstimate2ExtGeographicalInformation(lrr.getLocationEstimate());
                typeOfShape = lteLocationEstimate.getTypeOfShape().name();
                if (lteLocationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    latitude = lteLocationEstimate.getLatitude();
                    longitude = lteLocationEstimate.getLongitude();
                    uncertainty = lteLocationEstimate.getUncertainty();
                    uncertaintySemiMajorAxis = lteLocationEstimate.getUncertaintySemiMajorAxis();
                    uncertaintySemiMinorAxis = lteLocationEstimate.getUncertaintySemiMinorAxis();
                    altitude = lteLocationEstimate.getAltitude();
                    uncertaintyAltitude = lteLocationEstimate.getUncertaintyAltitude();
                    innerRadius = lteLocationEstimate.getInnerRadius();
                    uncertaintyInnerRadius = lteLocationEstimate.getUncertaintyRadius();
                    offsetAngle = lteLocationEstimate.getOffsetAngle();
                    includedAngle = lteLocationEstimate.getIncludedAngle();
                    confidence = lteLocationEstimate.getConfidence();
                } else {
                    polygon = new PolygonImpl(lteLocationEstimate.getData());
                    numberOfPoints = polygon.getNumberOfPoints();
                    polygonEllipsoidPoints = new EllipsoidPoint[numberOfPoints];
                    for (int point=0; point<numberOfPoints; point++) {
                        polygonEllipsoidPoints[point] = polygon.getEllipsoidPoint(point);
                    }
                    try {
                        ((PolygonImpl) polygon).setData(polygonEllipsoidPoints);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                }
                // Write Location-Estimate AVP values from LRR
                if (typeOfShape != null) {
                    writeTypeOfShape(typeOfShape, locationEstimateJsonObject);
                    if (typeOfShape.equalsIgnoreCase("EllipsoidPoint")) {
                        writeLatitude(latitude, locationEstimateJsonObject);
                        writeLongitude(longitude, locationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle")) {
                        writeLatitude(latitude, locationEstimateJsonObject);
                        writeLongitude(longitude, locationEstimateJsonObject);
                        writeUncertainty(uncertainty, locationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse")) {
                        writeLatitude(latitude, locationEstimateJsonObject);
                        writeLongitude(longitude, locationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, locationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, locationEstimateJsonObject);
                        writeAngleOfMajorAxis(angleOfMajorAxis, locationEstimateJsonObject);
                        writeConfidence(confidence, locationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("Polygon")) {
                        polygonArray = new Double[numberOfPoints][numberOfPoints];
                        Double lat, lon;
                        if (numberOfPoints > 2 && numberOfPoints <= 15) {
                            writeNumberOfPoints(numberOfPoints, locationEstimateJsonObject);
                            for (int index=0; index<numberOfPoints; index++) {
                                lat = polygon.getEllipsoidPoint(index).getLatitude();
                                lon = polygon.getEllipsoidPoint(index).getLongitude();
                                polygonArray[index][0] = lat;
                                polygonArray[index][1] = lon;
                                String polygonPoint = "polygonPoint"+(index+1);
                                writeLatitude(lat, lrrLocationEstimatePolygonPointsJsonObject);
                                writeLongitude(lon, lrrLocationEstimatePolygonPointsJsonObject);
                                locationEstimateJsonObject.add(polygonPoint, lrrLocationEstimatePolygonPointsJsonObject);
                                lrrLocationEstimatePolygonPointsJsonObject = new JsonObject();
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
                                writeLatitude(polygonCentroid(point2D).getX(), lrrLocationEstimatePolygonCentroidObject);
                                writeLongitude(polygonCentroid(point2D).getY(), lrrLocationEstimatePolygonCentroidObject);
                                locationEstimateJsonObject.add("polygonCentroid", lrrLocationEstimatePolygonCentroidObject);
                            }
                        }
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitude")) {
                        writeLatitude(latitude, locationEstimateJsonObject);
                        writeLongitude(longitude, locationEstimateJsonObject);
                        writeAltitude(altitude, locationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
                        writeLatitude(latitude, locationEstimateJsonObject);
                        writeLongitude(longitude, locationEstimateJsonObject);
                        writeAltitude(altitude, locationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(uncertaintySemiMajorAxis, locationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(uncertaintySemiMinorAxis, locationEstimateJsonObject);
                        writeAngleOfMajorAxis(angleOfMajorAxis, locationEstimateJsonObject);
                        writeUncertaintyAltitude(uncertaintyAltitude, locationEstimateJsonObject);
                        writeConfidence(confidence, locationEstimateJsonObject);
                    } else if (typeOfShape.equalsIgnoreCase("EllipsoidArc")) {
                        writeLatitude(latitude, locationEstimateJsonObject);
                        writeLongitude(longitude, locationEstimateJsonObject);
                        writeInnerRadius(innerRadius, locationEstimateJsonObject);
                        writeUncertaintyInnerRadius(uncertaintyInnerRadius, locationEstimateJsonObject);
                        writeOffsetAngle(offsetAngle, locationEstimateJsonObject);
                        writeIncludedAngle(includedAngle, locationEstimateJsonObject);
                        writeConfidence(confidence, locationEstimateJsonObject);
                    }
                }
                lrrJsonObject.add("LocationEstimate", locationEstimateJsonObject);
            }

            if (lrr.getAgeOfLocationEstimate() != null) {
                ageOfLocationEstimate = lrr.getAgeOfLocationEstimate();
                writeAgeOfLocationEstimate(ageOfLocationEstimate, lrrJsonObject);
            }

            if (lrr.getAccuracyFulfilmentIndicator() != null) {
                accuracyFulfilmentIndicator = AVPHandler.diamAccFulInd2MapAccFulInd(lrr.getAccuracyFulfilmentIndicator()).getIndicator();
                writeAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator, lrrJsonObject);
            }

            if (lrr.getLcsQoSClass() != null) {
                lcsQoSClassValue = lrr.getLcsQoSClass().getValue();
                writeLcsQoSClass(lcsQoSClassValue, lrrJsonObject);
            }

            /*** Serving Node AVP ***/
            if (lrr.getServingNodeAvp() != null) {
                if (lrr.getServingNodeAvp().getMMEName() != null)
                    mmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getMMEName()).getData());
                if (lrr.getServingNodeAvp().getMMERealm() != null)
                    mmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getMMERealm()).getData());
                if (lrr.getServingNodeAvp().getSGSNName() != null)
                    sgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getSGSNName()).getData());
                if (lrr.getServingNodeAvp().getSGSNRealm() != null)
                    sgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getSGSNRealm()).getData());
                if (lrr.getServingNodeAvp().getSGSNNumber() != null)
                    sgsnNumber = new String(AVPHandler.byte2String(lrr.getServingNodeAvp().getSGSNNumber()).getBytes());
                if (lrr.getServingNodeAvp().get3GPPAAAServerName() != null)
                    tgppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().get3GPPAAAServerName()).getData());
                if (lrr.getServingNodeAvp().getMSCNumber() != null)
                    mscNumber = new String(AVPHandler.byte2String(lrr.getServingNodeAvp().getMSCNumber()).getBytes());
                if (Long.valueOf(lrr.getServingNodeAvp().getLcsCapabilitiesSets()) != null)
                    lcsCapabilitySets = Long.valueOf(lrr.getServingNodeAvp().getLcsCapabilitiesSets());
                writeMmeName(mmeName, servingNodeJsonObject);
                writeMmeRealm(mmeRealm, servingNodeJsonObject);
                writeSgsnName(sgsnName, servingNodeJsonObject);
                writeSgsnRealm(sgsnRealm, servingNodeJsonObject);
                writeSgsnNumber(sgsnNumber, servingNodeJsonObject);
                write3gppAaaServerName(tgppAAAServerName, servingNodeJsonObject);
                writeMscNumber(mscNumber, servingNodeJsonObject);
                writeLCSCapabilitySets(lcsCapabilitySets, servingNodeJsonObject);
                lrrJsonObject.add("ServingNode", servingNodeJsonObject);
            }

            /*** CGI or SAI or ESMLC-Cell-Info AVPs ***/
            if (lrr.getCellGlobalIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength cellGlobalId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(lrr.getCellGlobalIdentity());
                try {
                    cgiMcc = cellGlobalId.getMCC();
                    cgiMnc = cellGlobalId.getMNC();
                    cgiLac = cellGlobalId.getLac();
                    ci = cellGlobalId.getCellIdOrServiceAreaCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Write CGI values from SLg LRR
                writeMcc(cgiMcc, cellGlobalIdentityJsonObject);
                writeMnc(cgiMnc, cellGlobalIdentityJsonObject);
                writeLac(cgiLac, cellGlobalIdentityJsonObject);
                writeCellId(ci, cellGlobalIdentityJsonObject);
                lrrJsonObject.add("CGI", cellGlobalIdentityJsonObject);
            }
            if (lrr.getServiceAreaIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength serviceAreaId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(lrr.getServiceAreaIdentity());
                try {
                    saiMcc = serviceAreaId.getMCC();
                    saiMnc = serviceAreaId.getMNC();
                    saiLac = serviceAreaId.getLac();
                    sac = serviceAreaId.getCellIdOrServiceAreaCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Write SAI values from SLg LRR
                writeMcc(saiMcc, serviceAreaIdentityJsonObject);
                writeMnc(saiMnc, serviceAreaIdentityJsonObject);
                writeLac(saiLac, serviceAreaIdentityJsonObject);
                writeServiceAreaCode(sac, serviceAreaIdentityJsonObject);
                lrrJsonObject.add("SAI", serviceAreaIdentityJsonObject);
            }
            EUTRANCGI eutranCgi;
            if (lrr.getEcgi() != null || lrr.getEsmlcCellInfoAvp() != null) {
                if (lrr.getEcgi() != null)
                    eutranCgi = new EUTRANCGIImpl(lrr.getEcgi());
                else
                    eutranCgi = new EUTRANCGIImpl(lrr.getEsmlcCellInfoAvp().getECGI());
                try {
                    ecgiMcc = eutranCgi.getMCC();
                    ecgiMnc = eutranCgi.getMNC();
                    eci = eutranCgi.getEci();
                    eNBId = eutranCgi.getENodeBId();
                    ecgiCi = eutranCgi.getCi();
                    if (lrr.getEsmlcCellInfoAvp() != null) {
                        if (lrr.getEsmlcCellInfoAvp().getCellPortionID() > -1) {
                            cellPortionId = lrr.getEsmlcCellInfoAvp().getCellPortionID();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Write ECGI or ESMLC Cell Info values from SLg LRR
                writeMcc(ecgiMcc, eCGIorESMLCCellInfoJsonObject);
                writeMnc(ecgiMnc, eCGIorESMLCCellInfoJsonObject);
                writeEUtranEci(eci, eCGIorESMLCCellInfoJsonObject);
                writeENBId(eNBId, eCGIorESMLCCellInfoJsonObject);
                writeCellId(ecgiCi, eCGIorESMLCCellInfoJsonObject);
                writeCellPortionId(cellPortionId, eCGIorESMLCCellInfoJsonObject);
                lrrJsonObject.add("ECGI", eCGIorESMLCCellInfoJsonObject);
            }

            /*** GERAN-Positioning-Info AVP ***/
            if (lrr.getGeranPositioningInfoAvp() != null) {
                if (lrr.getGeranPositioningInfoAvp().getGERANPositioningData() != null) {
                    geranPositioningData = bytesToHexString(AVPHandler.lteGeranPosDataInfo2MapGeranPosDataInfo(lrr.getGeranPositioningInfoAvp().getGERANPositioningData()).getData());
                    writeGeranPositioningData(geranPositioningData, geranPositioningInfoJsonObject);
                }
                if (lrr.getGeranPositioningInfoAvp().getGERANGANSSPositioningData() != null) {
                    geranGanssPositioningData = bytesToHexString(AVPHandler.lteGeranGanssPosDataInfo2MapGeranGanssPosDataInfo(lrr.getGeranPositioningInfoAvp().getGERANGANSSPositioningData()).getData());
                    writeGeranGanssPositioningData(geranGanssPositioningData, geranPositioningInfoJsonObject);
                }
                lrrJsonObject.add("GERANPositioningInfo", geranPositioningInfoJsonObject);
            }

            /*** UTRAN-Positioning-Info AVP ***/
            if (lrr.getUtranPositioningInfoAvp() != null) {
                if (lrr.getUtranPositioningInfoAvp().getUTRANPositioningData() != null) {
                    utranPositioningData = bytesToHexString(AVPHandler.lteUtranPosData2MapUtranPosDataInfo(lrr.getUtranPositioningInfoAvp().getUTRANPositioningData()).getData());
                    writeUtranPositioningData(utranPositioningData, utranPositioningInfoJsonObject);
                }
                if (lrr.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData() != null) {
                    utranGanssPositioningData = bytesToHexString(AVPHandler.lteUtranGanssPosData2MapUtranGanssPosDataInfo(lrr.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData()).getData());
                    writeUtranGanssPositioningData(utranGanssPositioningData, utranPositioningInfoJsonObject);
                }
                if (lrr.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData() != null) {
                    utranAdditionalPositioningData = bytesToHexString(AVPHandler.byte2String(lrr.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData()).getBytes());
                    writeUtranAdditionalPositioningData(utranAdditionalPositioningData, utranPositioningInfoJsonObject);
                }
                lrrJsonObject.add("UTRANPositioningInfo", utranPositioningInfoJsonObject);
            }

            /*** UTRAN-Positioning-Info AVP ***/
            if (lrr.geteUtranPositioningData() != null) {
                eUtranPositioningData = bytesToHexString(lrr.geteUtranPositioningData());
                writeEUtranPositioningData(eUtranPositioningData, eUtranPositioningInfoJsonObject);
                lrrJsonObject.add("E-UTRANPositioningInfo", eUtranPositioningInfoJsonObject);
            }

            /*** Velocity Estimate AVP ***/
            if (lrr.getVelocityEstimate() != null) {
                VelocityEstimate lteVelocityEstimate = AVPHandler.lteVelocityEstimate2MapVelocityEstimate(lrr.getVelocityEstimate());
                horizontalSpeed = lteVelocityEstimate.getHorizontalSpeed();
                bearing = lteVelocityEstimate.getBearing();
                verticalSpeed = lteVelocityEstimate.getVerticalSpeed();
                uncertaintyHorizontalSpeed = lteVelocityEstimate.getUncertaintyHorizontalSpeed();
                uncertaintyVerticalSpeed = lteVelocityEstimate.getUncertaintyVerticalSpeed();
                velocityType = lteVelocityEstimate.getVelocityType().name();
                writeHorizontalSpeed(horizontalSpeed, velocityEstimateJsonObject);
                writeBearing(bearing, velocityEstimateJsonObject);
                writeVerticalSpeed(verticalSpeed, velocityEstimateJsonObject);
                writeUncertaintyHorizontalSpeed(uncertaintyHorizontalSpeed, velocityEstimateJsonObject);
                writeUncertaintyVerticalSpeed(uncertaintyVerticalSpeed, velocityEstimateJsonObject);
                writeVelocityType(velocityType, velocityEstimateJsonObject);
                lrrJsonObject.add("VelocityEstimate", velocityEstimateJsonObject);
            }

            /*** Pseudonym-Indicator AVP ***/
            if (lrr.getPseudonymIndicator() != null) {
                lcsPseudonymIndicator = lrr.getPseudonymIndicator().getValue();
                writeLcsPseudonymIndicator(lcsPseudonymIndicator, lrrJsonObject);
            }

            /*** Periodic-LDR-Info AVP ***/
            if (lrr.getPeriodicLDRInformation() != null) {
                reportingAmount = lrr.getPeriodicLDRInformation().getReportingAmount();
                reportingInterval = lrr.getPeriodicLDRInformation().getReportingInterval();
                writeReportingAmount(reportingAmount, periodicLDRInfoJsonObject);
                writeReportingInterval(reportingInterval, periodicLDRInfoJsonObject);
                lrrJsonObject.add("PeriodicLDRInfo", periodicLDRInfoJsonObject);
            }

            /*** Deferred-MT-LR-Data AVP ***/
            if (lrr.getDeferredMTLRDataAvp() != null) {
                if (Long.valueOf(lrr.getDeferredMTLRDataAvp().getDeferredLocationType()) != null) {
                    deferredLocationType = lrr.getDeferredMTLRDataAvp().getDeferredLocationType();
                    writeDeferredLocationType(deferredLocationType, deferredMTLRDataJsonObject);
                }
                if (Long.valueOf(lrr.getDeferredMTLRDataAvp().getTerminationCause()) != null) {
                    dMtLrterminationCause = lrr.getDeferredMTLRDataAvp().getTerminationCause();
                    writeLRRTerminationCause(dMtLrterminationCause, deferredMTLRDataJsonObject);
                }
                if (lrr.getDeferredMTLRDataAvp().getServingNode() != null) {
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName() != null)
                        mtlrMmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMERealm() != null)
                        mtlrMmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getMMERealm()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNName() != null)
                        mtlrSgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNName()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNRealm() != null)
                        mtlrSgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNRealm()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNNumber() != null)
                        mtlrSgsnNumber = new String(AVPHandler.byte2String(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNNumber()).getBytes());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().get3GPPAAAServerName() != null)
                        mtlr3gppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().get3GPPAAAServerName()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMSCNumber() != null)
                        mtlrMscNumber = new String(AVPHandler.byte2String(lrr.getDeferredMTLRDataAvp().getServingNode().getMSCNumber()).getBytes());
                    if (Long.valueOf(lrr.getDeferredMTLRDataAvp().getServingNode().getLcsCapabilitiesSets()) != null)
                        mtlrLcsCapabilitySets = Long.valueOf(lrr.getDeferredMTLRDataAvp().getServingNode().getLcsCapabilitiesSets());
                    if (dMtLrterminationCause != null) {
                        if (dMtLrterminationCause == 4) {
                            writeMmeName(mtlrMmeName, deferredMTLRDataServingNodeJsonObject);
                            writeMmeRealm(mtlrMmeRealm, deferredMTLRDataServingNodeJsonObject);
                            writeSgsnName(mtlrSgsnName, deferredMTLRDataServingNodeJsonObject);
                            writeSgsnRealm(mtlrSgsnRealm, deferredMTLRDataServingNodeJsonObject);
                            writeSgsnNumber(mtlrSgsnNumber, deferredMTLRDataServingNodeJsonObject);
                            write3gppAaaServerName(mtlr3gppAAAServerName, deferredMTLRDataServingNodeJsonObject);
                            writeMscNumber(mtlrMscNumber, deferredMTLRDataServingNodeJsonObject);
                            writeLCSCapabilitySets(mtlrLcsCapabilitySets, deferredMTLRDataServingNodeJsonObject);
                        }
                    }
                    deferredMTLRDataJsonObject.add("ServingNode", deferredMTLRDataServingNodeJsonObject);
                }
                lrrJsonObject.add("DeferredMTLRData", deferredMTLRDataJsonObject);
            }

            /*** Delayed-Location-Reporting-Data AVP ***/
            if (lrr.getDelayedLocationReportingDataAvp() != null) {
                if (Long.valueOf(lrr.getDelayedLocationReportingDataAvp().getTerminationCause()) != null) {
                    dlrTerminationCause = lrr.getDelayedLocationReportingDataAvp().getTerminationCause();
                    writeLRRTerminationCause(dlrTerminationCause, delayedLocationReportingDataJsonObject);
                }
                if (lrr.getDelayedLocationReportingDataAvp().getServingNode() != null) {
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName() != null)
                        dlrMmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMEName()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMERealm() != null)
                        dlrMmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMERealm()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNName() != null)
                        dlrSgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNName()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNRealm() != null)
                        dlrSgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNRealm()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNNumber() != null)
                        dlrSgsnNumber = new String(AVPHandler.byte2String(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNNumber()).getBytes());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().get3GPPAAAServerName() != null)
                        dlr3gppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().get3GPPAAAServerName()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getMSCNumber() != null)
                        dlrMscNumber = new String(AVPHandler.byte2String(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMSCNumber()).getBytes());
                    if (Long.valueOf(lrr.getDelayedLocationReportingDataAvp().getServingNode().getLcsCapabilitiesSets()) != null)
                        dlrLcsCapabilitySets = Long.valueOf(lrr.getDelayedLocationReportingDataAvp().getServingNode().getLcsCapabilitiesSets());
                    if (dlrTerminationCause != null) {
                        if (dlrTerminationCause == 4) {
                            writeMmeName(dlrMmeName, dlrDataServingNodeJsonObject);
                            writeMmeRealm(dlrMmeRealm, dlrDataServingNodeJsonObject);
                            writeSgsnName(dlrSgsnName, dlrDataServingNodeJsonObject);
                            writeSgsnRealm(dlrSgsnRealm, dlrDataServingNodeJsonObject);
                            writeSgsnNumber(dlrSgsnNumber, dlrDataServingNodeJsonObject);
                            write3gppAaaServerName(dlr3gppAAAServerName, dlrDataServingNodeJsonObject);
                            writeMscNumber(dlrMscNumber, dlrDataServingNodeJsonObject);
                            writeLCSCapabilitySets(dlrLcsCapabilitySets, dlrDataServingNodeJsonObject);
                        }
                    }
                    delayedLocationReportingDataJsonObject.add("ServingNode", dlrDataServingNodeJsonObject);
                }
                lrrJsonObject.add("DelayedLocationReportingData", delayedLocationReportingDataJsonObject);
            }

            /*** Civic-Address AVP ***/
            if (lrr.getCivicAddress() != null) {
                civicAddress = AVPHandler.byte2String(lrr.getCivicAddress());
                writeCivicAddress(civicAddress, lrrJsonObject);
            }

            /*** Barometric-Pressure AVP ***/
            if (Long.valueOf(lrr.getBarometricPressure()) != null) {
                barometricPressure = lrr.getBarometricPressure();
                writeBarometricPressure(barometricPressure, lrrJsonObject);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String lrrReportJson = gson.toJson(lrrJsonObject);

        return lrrReportJson;
    }

}
