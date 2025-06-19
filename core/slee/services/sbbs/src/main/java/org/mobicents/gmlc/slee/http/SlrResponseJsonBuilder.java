package org.mobicents.gmlc.slee.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.mobicents.gmlc.slee.map.SlrRequestValues;
import org.mobicents.gmlc.slee.primitives.EllipsoidPoint;
import org.mobicents.gmlc.slee.primitives.Polygon;
import org.mobicents.gmlc.slee.primitives.PolygonImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mobicents.gmlc.slee.gis.GeographicHelper.polygonCentroid;
import static org.mobicents.gmlc.slee.http.JsonWriter.bytesToHexString;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAaaServerName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAccuracyFulfilmentIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAdditionalLCSCapabilitySets;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAgeOfLocationEstimate;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAltitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeAngleOfMajorAxis;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeBearing;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeCellId;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeClientReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeConfidence;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeDeferredLocationEventType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranGanssPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGeranPositioningData;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeGprsNodeIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeHorizontalSpeed;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImei;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeImsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeIncludedAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeInnerRadius;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientAPN;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientDataCodingScheme;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientDataFormatIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientDialedByMS;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientExternalID;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientIDType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientInternalID;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSClientName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSEvent;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLCSServiceTypeID;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLac;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLatitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLcsReferenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLmsi;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeLongitude;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMcc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMmeName;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMnc;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMoLrShortCircuitIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMscNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeMsisdn;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetwork;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNetworkNodeNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeNumberOfPoints;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOffsetAngle;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperation;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeOperationResult;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeProtocol;
import static org.mobicents.gmlc.slee.http.JsonWriter.writePseudonymIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeReportingAmount;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeReportingInterval;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRequestorIdDataCodingScheme;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRequestorIdDataFormatIndicator;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeRequestorIdEncodedString;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSLRTerminationCause;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSequenceNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeServiceAreaCode;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSgsnNumber;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeSupportedLCSCapabilitySets;
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
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVelocityType;
import static org.mobicents.gmlc.slee.http.JsonWriter.writeVerticalSpeed;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class SlrResponseJsonBuilder {

    protected static final Logger logger = Logger.getLogger(SlrResponseJsonBuilder.class);

    public SlrResponseJsonBuilder() {
    }

    public static String buildJsonReportForSlrFromStringList(List<String> slrReportParameters) {
        String reportParameterString = "";

        if (slrReportParameters != null && slrReportParameters.size() > 0) {
            StringBuilder reportParameterStringBuilder = new StringBuilder();

            Iterator<String> slrReportParametersIterator = slrReportParameters.iterator();
            while (slrReportParametersIterator.hasNext()) {
                reportParameterStringBuilder.append((slrReportParametersIterator.next() + ", ").getBytes());
            }

            // remove trailng comma and space
            reportParameterString = reportParameterStringBuilder.substring(0, reportParameterStringBuilder.length() - 2);
        }

        return reportParameterString;
    }

    /**
     * Handle generating the appropriate HTTP response in JSON format
     *
     * @param slrReq                SLR Request values gathered from SLR request event
     * @param clientReferenceNumber Reference Number gathered from the originating HTTP request sent by the GMLC Client
     */
    public static String buildJsonReportforSLR(SlrRequestValues slrReq, Integer clientReferenceNumber) throws MAPException {

        Integer lcsReferenceNumber, clientRefNum, lcsServiceTypeID, lcsClientIDType, lcsClientIDInternalID, lcsClientDataCodingScheme, lcsClientFormatIndicator,
            requestorIDFormatIndicator, requestorIDDataCodingScheme, numberOfPoints, addNumberOfPoints, confidence, altitude, innerRadius, addConfidence,
            addAltitude, addInnerRadius, ageOfLocationEstimate, accuracyFulfilmentIndicator, mcc, mnc, lac, ciOrSac, horizontalSpeed, bearing, verticalSpeed,
            uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed, lcsEvent, reportingAmount, reportingInterval, sequenceNumber, terminationCause, lmsi;
        lcsReferenceNumber = clientRefNum =lcsServiceTypeID = lcsClientIDType = lcsClientIDInternalID = lcsClientDataCodingScheme = lcsClientFormatIndicator =
            requestorIDFormatIndicator = requestorIDDataCodingScheme = numberOfPoints = addNumberOfPoints = confidence = altitude = innerRadius =
                addConfidence = addAltitude = addInnerRadius = ageOfLocationEstimate = accuracyFulfilmentIndicator = horizontalSpeed = bearing = verticalSpeed =
                    uncertaintyHorizontalSpeed = uncertaintyVerticalSpeed = lcsEvent = reportingAmount = reportingInterval = sequenceNumber = terminationCause =
                        lmsi = mcc = mnc = lac = ciOrSac = null;
        String msisdn, imsi, imei, lcsClientIDExternalID, lcsClientName, lcsClientIDAPN, requestorIDEncodedString, lcsClientDialedByMS, typeOfShape, addTypeOfShape,
            geranPositioningData, geranGanssPositioningData, utranPositioningData, utranGanssPositioningData, velocityType, networkNodeNumber, lmsiStr,
            mmeName, aaaServerName, mscNumber, sgsnNumber;
        msisdn = imsi = imei = lcsClientIDExternalID = lcsClientName = lcsClientIDAPN = requestorIDEncodedString = lcsClientDialedByMS =
            typeOfShape = addTypeOfShape = geranPositioningData = geranGanssPositioningData = utranPositioningData = utranGanssPositioningData = velocityType =
                networkNodeNumber = lmsiStr = mmeName = aaaServerName = mscNumber = sgsnNumber = null;
        Double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, uncertaintyAltitude, uncertaintyInnerRadius,
            angleOfMajorAxis, offsetAngle, includedAngle;
        latitude = longitude = uncertainty = uncertaintySemiMajorAxis = uncertaintySemiMinorAxis = uncertaintyAltitude = uncertaintyInnerRadius =
            angleOfMajorAxis = offsetAngle = includedAngle = null;
        Double addLatitude, addLongitude, addUncertainty, addUncertaintySemiMajorAxis, addUncertaintySemiMinorAxis, addUncertaintyAltitude,
            addUncertaintyInnerRadius, addAngleOfMajorAxis, addOffsetAngle, addIncludedAngle;
        addLatitude = addLongitude = addUncertainty = addUncertaintySemiMajorAxis = addUncertaintySemiMinorAxis = addUncertaintyAltitude =
            addUncertaintyInnerRadius = addAngleOfMajorAxis = addOffsetAngle = addIncludedAngle = null;
        Boolean pseudonymIndicator, moLrShortCircuitIndicator, deferredEventLocationType, enteringArea, insideArea, leavingArea, msAvailable, periodicLDR,
            gprsNodeIndicator, supportedLCSCapabilitySets, addSupportedLCSCapabilitySets, saiPresent,
            supportedLCSCapabilitySetRelease98_99, supportedLCSCapabilitySetRelease4, supportedLCSCapabilitySetRelease5, supportedLCSCapabilitySetRelease6,
            supportedLCSCapabilitySetRelease7, addSupportedLCSCapabilitySetRelease98_99, addSupportedLCSCapabilitySetRelease4,
            addSupportedLCSCapabilitySetRelease5, addSupportedLCSCapabilitySetRelease6, addSupportedLCSCapabilitySetRelease7;
        pseudonymIndicator = moLrShortCircuitIndicator = deferredEventLocationType = enteringArea = insideArea = leavingArea = msAvailable = periodicLDR =
            gprsNodeIndicator = supportedLCSCapabilitySets = addSupportedLCSCapabilitySets = supportedLCSCapabilitySetRelease98_99 = supportedLCSCapabilitySetRelease4 =
                supportedLCSCapabilitySetRelease5 = supportedLCSCapabilitySetRelease6 = supportedLCSCapabilitySetRelease7 =
                    addSupportedLCSCapabilitySetRelease98_99 = addSupportedLCSCapabilitySetRelease4 = addSupportedLCSCapabilitySetRelease5 =
                        addSupportedLCSCapabilitySetRelease6 = addSupportedLCSCapabilitySetRelease7 = null;
        saiPresent = false;
        Polygon polygon = null, additionalPolygon = null;
        EllipsoidPoint[] polygonEllipsoidPoints = null, additionalPolygonEllipsoidPoints = null;
        Double[][] polygonArray = null;

        JsonObject slrJsonObject = new JsonObject();
        writeNetwork("GSM/UMTS", slrJsonObject);
        writeProtocol("MAP", slrJsonObject);
        writeOperation("SLR", slrJsonObject);
        writeOperationResult("SUCCESS", slrJsonObject);
        JsonObject lcsClientIDJsonObject = new JsonObject();
        JsonObject lcsClientNameJsonObject = new JsonObject();
        JsonObject lcsClientRequestorIDJsonObject = new JsonObject();
        JsonObject locationEstimateJsonObject = new JsonObject();
        JsonObject additionalLocationEstimateJsonObject = new JsonObject();
        JsonObject slrAdditionalLocationEstimatePolygonPointsJsonObject = new JsonObject();
        JsonObject slrAdditionalLocationEstimatePolygonCentroidObject = new JsonObject();
        JsonObject slrCGIorSAIorLAIJsonObject = new JsonObject();
        JsonObject geranPositioningInfoJsonObject = new JsonObject();
        JsonObject utranPositioningInfoJsonObject = new JsonObject();
        JsonObject velocityEstimateJsonObject = new JsonObject();
        JsonObject periodicLDRInfoJsonObject = new JsonObject();
        JsonObject deferredmtlrDataJsonObject = new JsonObject();
        JsonObject supportedLCSCapSetsJsonObject = new JsonObject();
        JsonObject additionalLCSCapSetsJsonObject = new JsonObject();

        if (slrReq != null) {

            if (slrReq.getLcsReferenceNumber() != null) {
                if (slrReq.getLcsReferenceNumber() < 0)
                    lcsReferenceNumber = slrReq.getLcsReferenceNumber() + 256;
                else
                    lcsReferenceNumber = slrReq.getLcsReferenceNumber();
                writeLcsReferenceNumber(lcsReferenceNumber, slrJsonObject);
            }

            if (clientReferenceNumber != null) {
                clientRefNum = clientReferenceNumber;
                writeClientReferenceNumber(clientRefNum, slrJsonObject);
            }

            if (slrReq.getMsisdn() != null) {
                msisdn = slrReq.getMsisdn().getAddress();
                writeMsisdn(msisdn, slrJsonObject);
            }

            if (slrReq.getImsi() != null) {
                imsi = new String(slrReq.getImsi().getData().getBytes());
                writeImsi(imsi, slrJsonObject);
            }

            if (slrReq.getImei() != null) {
                imei = slrReq.getImei().getIMEI();
                writeImei(imei, slrJsonObject);
            }

            if (slrReq.getLcsServiceTypeID() != null) {
                lcsServiceTypeID = slrReq.getLcsServiceTypeID();
                writeLCSServiceTypeID(lcsServiceTypeID, slrJsonObject);
            }

            if (slrReq.getLcsClientID() != null) {
                if (slrReq.getLcsClientID().getLCSClientType() != null && (slrReq.getLcsClientID().getLCSClientType().getType() > Integer.MIN_VALUE
                    && slrReq.getLcsClientID().getLCSClientType().getType() < Integer.MAX_VALUE)) {
                    lcsClientIDType = slrReq.getLcsClientID().getLCSClientType().getType();
                    writeLCSClientIDType(lcsClientIDType, lcsClientIDJsonObject);
                }
                if (slrReq.getLcsClientID().getLCSClientExternalID() != null) {
                    if (slrReq.getLcsClientID().getLCSClientExternalID().getExternalAddress() != null)
                        lcsClientIDExternalID = slrReq.getLcsClientID().getLCSClientExternalID().getExternalAddress().getAddress();
                    writeLCSClientExternalID(lcsClientIDExternalID, lcsClientIDJsonObject);
                }
                if (slrReq.getLcsClientID().getLCSClientInternalID() != null
                    && (slrReq.getLcsClientID().getLCSClientInternalID().getId() > Integer.MIN_VALUE
                    && slrReq.getLcsClientID().getLCSClientInternalID().getId() < Integer.MAX_VALUE)) {
                    lcsClientIDInternalID = slrReq.getLcsClientID().getLCSClientInternalID().getId();
                    writeLCSClientInternalID(lcsClientIDInternalID, lcsClientIDJsonObject);
                }
                if (slrReq.getLcsClientID().getLCSClientName() != null) {
                    if (slrReq.getLcsClientID().getLCSClientName().getNameString() != null) {
                        lcsClientName = new String(slrReq.getLcsClientID().getLCSClientName().getNameString().getEncodedString());
                        writeLCSClientName(lcsClientName, lcsClientNameJsonObject);
                    }
                    if (slrReq.getLcsClientID().getLCSClientName().getDataCodingScheme() != null) {
                        lcsClientDataCodingScheme = slrReq.getLcsClientID().getLCSClientName().getDataCodingScheme().getCode();
                        writeLCSClientDataCodingScheme(lcsClientDataCodingScheme, lcsClientNameJsonObject);
                    }
                    if (slrReq.getLcsClientID().getLCSClientName().getLCSFormatIndicator() != null) {
                        lcsClientFormatIndicator = slrReq.getLcsClientID().getLCSClientName().getLCSFormatIndicator().getIndicator();
                        writeLCSClientDataFormatIndicator(lcsClientFormatIndicator, lcsClientNameJsonObject);
                    }
                    lcsClientIDJsonObject.add("lcsClientIDName", lcsClientNameJsonObject);
                }
                if (slrReq.getLcsClientID().getLCSAPN() != null) {
                    lcsClientIDAPN = new String(slrReq.getLcsClientID().getLCSAPN().getApn().getBytes());
                    writeLCSClientAPN(lcsClientIDAPN, lcsClientIDJsonObject);
                }
                if (slrReq.getLcsClientID().getLCSRequestorID() != null) {
                    if (slrReq.getLcsClientID().getLCSRequestorID().getRequestorIDString() != null) {
                        requestorIDEncodedString = new String(slrReq.getLcsClientID().getLCSRequestorID().getRequestorIDString().getEncodedString());
                        writeRequestorIdEncodedString(requestorIDEncodedString, lcsClientRequestorIDJsonObject);
                    }
                    if (slrReq.getLcsClientID().getLCSRequestorID().getDataCodingScheme() != null) {
                        requestorIDDataCodingScheme = slrReq.getLcsClientID().getLCSRequestorID().getDataCodingScheme().getCode();
                        writeRequestorIdDataCodingScheme(requestorIDDataCodingScheme, lcsClientRequestorIDJsonObject);
                    }
                    if (slrReq.getLcsClientID().getLCSRequestorID().getLCSFormatIndicator() != null) {
                        requestorIDFormatIndicator = slrReq.getLcsClientID().getLCSRequestorID().getLCSFormatIndicator().getIndicator();
                        writeRequestorIdDataFormatIndicator(requestorIDFormatIndicator, lcsClientRequestorIDJsonObject);
                    }
                    lcsClientIDJsonObject.add("lcsClientRequestorID", lcsClientRequestorIDJsonObject);
                }
                if (slrReq.getLcsClientID().getLCSClientDialedByMS() != null) {
                    lcsClientDialedByMS = slrReq.getLcsClientID().getLCSClientDialedByMS().getAddress();
                    writeLCSClientDialedByMS(lcsClientDialedByMS, lcsClientIDJsonObject);
                }
                slrJsonObject.add("lcsClientID", lcsClientIDJsonObject);
            }

            if (slrReq.getLocationEstimate() != null) {
                ExtGeographicalInformation locationEstimate = slrReq.getLocationEstimate();
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
            if (slrReq.getAdditionalLocationEstimate() != null) {
                AddGeographicalInformation additionalLocationEstimate = slrReq.getAdditionalLocationEstimate();
                addTypeOfShape = additionalLocationEstimate.getTypeOfShape().name();
                if (additionalLocationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    addTypeOfShape = additionalLocationEstimate.getTypeOfShape().name();
                    addLatitude = additionalLocationEstimate.getLatitude();
                    addLongitude = additionalLocationEstimate.getLongitude();
                    addUncertainty = additionalLocationEstimate.getUncertainty();
                    addConfidence = additionalLocationEstimate.getConfidence();
                    addUncertaintySemiMajorAxis = additionalLocationEstimate.getUncertaintySemiMajorAxis();
                    addUncertaintySemiMinorAxis = additionalLocationEstimate.getUncertaintySemiMinorAxis();
                    addAltitude = additionalLocationEstimate.getAltitude();
                    addUncertaintyAltitude = additionalLocationEstimate.getUncertaintyAltitude();
                    addInnerRadius = additionalLocationEstimate.getInnerRadius();
                    addUncertaintyInnerRadius = additionalLocationEstimate.getUncertaintyRadius();
                    addOffsetAngle = additionalLocationEstimate.getOffsetAngle();
                    addIncludedAngle = additionalLocationEstimate.getIncludedAngle();
                } else {
                    // SLR Additional Location Estimate Additional Location Estimate for TypeOfShape.Polygon
                    additionalPolygon = new PolygonImpl(additionalLocationEstimate.getData());
                    addNumberOfPoints = additionalPolygon.getNumberOfPoints();
                    additionalPolygonEllipsoidPoints = new EllipsoidPoint[addNumberOfPoints];
                    for (int point = 0; point < addNumberOfPoints; point++) {
                        additionalPolygonEllipsoidPoints[point] = additionalPolygon.getEllipsoidPoint(point);
                    }
                    try {
                        ((PolygonImpl) additionalPolygon).setData(additionalPolygonEllipsoidPoints);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                }
            }
            // Write Location Estimate values from SLR
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
            if (addTypeOfShape != null) {
                if (addTypeOfShape.equalsIgnoreCase("Polygon")) {
                    typeOfShape = "Polygon";
                    writeTypeOfShape(typeOfShape, locationEstimateJsonObject);
                }
            }
            slrJsonObject.add("LocationEstimate", locationEstimateJsonObject);

            if (slrReq.getAdditionalLocationEstimate() != null) {
                // Write Additional Location Estimate values from SLR
                if (addTypeOfShape != null) {
                    writeTypeOfShape(addTypeOfShape, additionalLocationEstimateJsonObject);
                    if (addTypeOfShape.equalsIgnoreCase("EllipsoidPoint")) {
                        writeLatitude(addLatitude, additionalLocationEstimateJsonObject);
                        writeLongitude(addLongitude, additionalLocationEstimateJsonObject);
                    } else if (addTypeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle")) {
                        writeLatitude(addLatitude, additionalLocationEstimateJsonObject);
                        writeLongitude(addLongitude, additionalLocationEstimateJsonObject);
                        writeUncertainty(addUncertainty, additionalLocationEstimateJsonObject);
                    } else if (addTypeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse")) {
                        writeLatitude(addLatitude, additionalLocationEstimateJsonObject);
                        writeLongitude(addLongitude, additionalLocationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(addUncertaintySemiMajorAxis, additionalLocationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(addUncertaintySemiMinorAxis, additionalLocationEstimateJsonObject);
                        writeAngleOfMajorAxis(addAngleOfMajorAxis, additionalLocationEstimateJsonObject);
                        writeConfidence(addConfidence, additionalLocationEstimateJsonObject);
                    } else if (addTypeOfShape.equalsIgnoreCase("Polygon")) {
                        polygonArray = new Double[addNumberOfPoints][addNumberOfPoints];
                        Double lat, lon;
                        if (addNumberOfPoints > 2 && addNumberOfPoints <= 15) {
                            writeNumberOfPoints(addNumberOfPoints, additionalLocationEstimateJsonObject);
                            for (int index=0; index<addNumberOfPoints; index++) {
                                lat = additionalPolygon.getEllipsoidPoint(index).getLatitude();
                                lon = additionalPolygon.getEllipsoidPoint(index).getLongitude();
                                polygonArray[index][0] = lat;
                                polygonArray[index][1] = lon;
                                String additionalPolygonPoint = "polygonPoint"+(index+1);
                                writeLatitude(lat, slrAdditionalLocationEstimatePolygonPointsJsonObject);
                                writeLongitude(lon, slrAdditionalLocationEstimatePolygonPointsJsonObject);
                                additionalLocationEstimateJsonObject.add(additionalPolygonPoint, slrAdditionalLocationEstimatePolygonPointsJsonObject);
                                slrAdditionalLocationEstimatePolygonPointsJsonObject = new JsonObject();
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
                                writeLatitude(polygonCentroid(point2D).getX(), slrAdditionalLocationEstimatePolygonCentroidObject);
                                writeLongitude(polygonCentroid(point2D).getY(), slrAdditionalLocationEstimatePolygonCentroidObject);
                                additionalLocationEstimateJsonObject.add("polygonCentroid", slrAdditionalLocationEstimatePolygonCentroidObject);
                            }
                        }
                    } else if (addTypeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitude")) {
                        writeLatitude(addLatitude, additionalLocationEstimateJsonObject);
                        writeLongitude(addLongitude, additionalLocationEstimateJsonObject);
                        writeAltitude(addAltitude, additionalLocationEstimateJsonObject);
                    } else if (addTypeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
                        writeLatitude(addLatitude, additionalLocationEstimateJsonObject);
                        writeLongitude(addLongitude, additionalLocationEstimateJsonObject);
                        writeAltitude(addAltitude, additionalLocationEstimateJsonObject);
                        writeUncertaintySemiMajorAxis(addUncertaintySemiMajorAxis, additionalLocationEstimateJsonObject);
                        writeUncertaintySemiMinorAxis(addUncertaintySemiMinorAxis, additionalLocationEstimateJsonObject);
                        writeAngleOfMajorAxis(addAngleOfMajorAxis, additionalLocationEstimateJsonObject);
                        writeUncertaintyAltitude(addUncertaintyAltitude, additionalLocationEstimateJsonObject);
                        writeConfidence(addConfidence, additionalLocationEstimateJsonObject);
                    } else if (addTypeOfShape.equalsIgnoreCase("EllipsoidArc")) {
                        writeLatitude(addLatitude, additionalLocationEstimateJsonObject);
                        writeLongitude(addLongitude, additionalLocationEstimateJsonObject);
                        writeInnerRadius(addInnerRadius, additionalLocationEstimateJsonObject);
                        writeUncertaintyInnerRadius(addUncertaintyInnerRadius, additionalLocationEstimateJsonObject);
                        writeOffsetAngle(addOffsetAngle, additionalLocationEstimateJsonObject);
                        writeIncludedAngle(addIncludedAngle, additionalLocationEstimateJsonObject);
                        writeConfidence(addConfidence, additionalLocationEstimateJsonObject);
                    }
                }
                slrJsonObject.add("AdditionalLocationEstimate", additionalLocationEstimateJsonObject);
            }

            if (slrReq.getAgeOfLocationEstimate() != null) {
                ageOfLocationEstimate = slrReq.getAgeOfLocationEstimate();
                writeAgeOfLocationEstimate(ageOfLocationEstimate, slrJsonObject);
            }

            if (slrReq.getAccuracyFulfilmentIndicator() != null) {
                accuracyFulfilmentIndicator = slrReq.getAccuracyFulfilmentIndicator().getIndicator();
                writeAccuracyFulfilmentIndicator(accuracyFulfilmentIndicator, slrJsonObject);
            }

            if (slrReq.getSaiPresent())
                saiPresent = true;

            if (slrReq.getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                /*** LAI fixed length ***/
                if (slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                    mcc = slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                    mnc = slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                    lac = slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                }
                /*** CGI or SAI fixed length ***/
                if (slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                    mcc = slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                    mnc = slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                    lac = slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                    ciOrSac = slrReq.getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                }
                writeMcc(mcc, slrCGIorSAIorLAIJsonObject);
                writeMnc(mnc, slrCGIorSAIorLAIJsonObject);
                writeLac(lac, slrCGIorSAIorLAIJsonObject);
                if (!saiPresent) {
                    if (ciOrSac != null) {
                        writeCellId(ciOrSac, slrCGIorSAIorLAIJsonObject);
                        slrJsonObject.add("CGI", slrCGIorSAIorLAIJsonObject);
                    } else {
                        if (lac != null)
                            slrJsonObject.add("LAI", slrCGIorSAIorLAIJsonObject);
                    }
                } else {
                    if (ciOrSac != null) {
                        writeServiceAreaCode(ciOrSac, slrCGIorSAIorLAIJsonObject);
                        slrJsonObject.add("SAI", slrCGIorSAIorLAIJsonObject);
                    } else {
                        if (lac != null)
                            slrJsonObject.add("LAI", slrCGIorSAIorLAIJsonObject);
                    }
                }
            }

            if (slrReq.getGeranPositioningDataInformation() != null) {
                geranPositioningData = bytesToHexString(slrReq.getGeranPositioningDataInformation().getData());
                geranGanssPositioningData = bytesToHexString(slrReq.getGeranGANSSpositioningData().getData());
                writeGeranPositioningData(geranPositioningData, geranPositioningInfoJsonObject);
                writeGeranGanssPositioningData(geranGanssPositioningData, geranPositioningInfoJsonObject);
                slrJsonObject.add("GERANPositioningInfo", geranPositioningInfoJsonObject);
            }

            if (slrReq.getUtranPositioningDataInfo() != null) {
                utranPositioningData = bytesToHexString(slrReq.getUtranPositioningDataInfo().getData());
                utranGanssPositioningData = bytesToHexString(slrReq.getUtranGANSSpositioningData().getData());
                writeUtranPositioningData(utranPositioningData, utranPositioningInfoJsonObject);
                writeUtranGanssPositioningData(utranGanssPositioningData, utranPositioningInfoJsonObject);
                slrJsonObject.add("UTRANPositioningInfo", utranPositioningInfoJsonObject);
            }

            if (slrReq.getVelocityEstimate() != null) {
                horizontalSpeed = slrReq.getVelocityEstimate().getHorizontalSpeed();
                bearing = slrReq.getVelocityEstimate().getBearing();
                verticalSpeed = slrReq.getVelocityEstimate().getVerticalSpeed();
                uncertaintyHorizontalSpeed = slrReq.getVelocityEstimate().getUncertaintyHorizontalSpeed();
                uncertaintyVerticalSpeed = slrReq.getVelocityEstimate().getUncertaintyVerticalSpeed();
                if (slrReq.getVelocityEstimate().getVelocityType() != null)
                    velocityType = slrReq.getVelocityEstimate().getVelocityType().name();
                writeHorizontalSpeed(horizontalSpeed, velocityEstimateJsonObject);
                writeBearing(bearing, velocityEstimateJsonObject);
                writeVerticalSpeed(verticalSpeed, velocityEstimateJsonObject);
                writeUncertaintyHorizontalSpeed(uncertaintyHorizontalSpeed, velocityEstimateJsonObject);
                writeUncertaintyVerticalSpeed(uncertaintyVerticalSpeed, velocityEstimateJsonObject);
                writeVelocityType(velocityType, velocityEstimateJsonObject);
                slrJsonObject.add("VelocityEstimate", velocityEstimateJsonObject);
            }

            if (slrReq.getPseudonymIndicator() != null) {
                pseudonymIndicator = slrReq.getPseudonymIndicator();
                writePseudonymIndicator(pseudonymIndicator, slrJsonObject);
            }

            if (slrReq.getLcsEvent() != null) {
                lcsEvent = slrReq.getLcsEvent().getEvent();
                writeLCSEvent(lcsEvent, slrJsonObject);
            }

            if (slrReq.isMoLrShortCircuitIndicator()) {
                moLrShortCircuitIndicator = true;
            } else {
                moLrShortCircuitIndicator = false;
            }
            writeMoLrShortCircuitIndicator(moLrShortCircuitIndicator, slrJsonObject);

            if (slrReq.getPeriodicLDRInfo() != null) {
                reportingAmount = slrReq.getPeriodicLDRInfo().getReportingAmount();
                reportingInterval = slrReq.getPeriodicLDRInfo().getReportingInterval();
                writeReportingAmount(reportingAmount, periodicLDRInfoJsonObject);
                writeReportingInterval(reportingInterval, periodicLDRInfoJsonObject);
                slrJsonObject.add("PeriodicLDRInfo", periodicLDRInfoJsonObject);
            }

            if (slrReq.getSequenceNumber() != null) {
                sequenceNumber = slrReq.getSequenceNumber();
                writeSequenceNumber(sequenceNumber, slrJsonObject);
            }

            /*** Deferred MT LR Data ***/
            if (slrReq.getDeferredmtlrData() != null) {
                // Deferred Location Event Type
                if (slrReq.getDeferredmtlrData().getDeferredLocationEventType() != null) {
                    deferredEventLocationType = true;
                    enteringArea = slrReq.getDeferredmtlrData().getDeferredLocationEventType().getEnteringIntoArea();
                    insideArea = slrReq.getDeferredmtlrData().getDeferredLocationEventType().getBeingInsideArea();
                    leavingArea = slrReq.getDeferredmtlrData().getDeferredLocationEventType().getLeavingFromArea();
                    msAvailable = slrReq.getDeferredmtlrData().getDeferredLocationEventType().getMsAvailable();
                    periodicLDR = slrReq.getDeferredmtlrData().getDeferredLocationEventType().getPeriodicLDR();
                    writeDeferredLocationEventType(deferredEventLocationType, enteringArea, insideArea, leavingArea, msAvailable, periodicLDR, deferredmtlrDataJsonObject);
                }
                // Termination Cause
                if (slrReq.getDeferredmtlrData().getTerminationCause() != null) {
                    terminationCause = slrReq.getDeferredmtlrData().getTerminationCause().getCause();
                    writeSLRTerminationCause(terminationCause, deferredmtlrDataJsonObject);
                }

                // LCS Location Info
                if (slrReq.getDeferredmtlrData().getLCSLocationInfo() != null) {

                    // GPRS Node Indicator
                    if (slrReq.isGprsNodeIndicator()) {
                        gprsNodeIndicator = true;
                    } else {
                        gprsNodeIndicator = false;
                    }
                    writeGprsNodeIndicator(gprsNodeIndicator, deferredmtlrDataJsonObject);

                    // Network Node Number
                    if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getNetworkNodeNumber() != null) {
                        networkNodeNumber = slrReq.getDeferredmtlrData().getLCSLocationInfo().getNetworkNodeNumber().getAddress();
                        writeNetworkNodeNumber(networkNodeNumber, deferredmtlrDataJsonObject);
                    }

                    // LMSI
                    if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getLMSI() != null) {
                        lmsiStr = bytesToHex(slrReq.getDeferredmtlrData().getLCSLocationInfo().getLMSI().getData());
                        writeLmsi(lmsiStr, deferredmtlrDataJsonObject);
                    }

                    // MME Name
                    if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getMmeName() != null) {
                        mmeName = new String(slrReq.getDeferredmtlrData().getLCSLocationInfo().getMmeName().getData());
                        writeMmeName(mmeName, deferredmtlrDataJsonObject);
                    }

                    // AAA Server Name
                    if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getAaaServerName() != null) {
                        aaaServerName = new String(slrReq.getDeferredmtlrData().getLCSLocationInfo().getAaaServerName().getData());
                        writeAaaServerName(aaaServerName, deferredmtlrDataJsonObject);
                    }

                    // Additional Number
                    if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalNumber() != null) {
                        if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalNumber().getMSCNumber() != null) {
                            mscNumber = slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalNumber().getMSCNumber().getAddress();
                            writeMscNumber(mscNumber, deferredmtlrDataJsonObject);
                        }
                        if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalNumber().getSGSNNumber() != null) {
                            sgsnNumber = slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalNumber().getSGSNNumber().getAddress();
                            writeSgsnNumber(sgsnNumber, deferredmtlrDataJsonObject);
                        }
                    }

                    // Supported LCS Capability Sets
                    if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getSupportedLCSCapabilitySets() != null) {
                        supportedLCSCapabilitySets = true;
                        supportedLCSCapabilitySetRelease98_99 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease98_99();
                        supportedLCSCapabilitySetRelease4 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease4();
                        supportedLCSCapabilitySetRelease5 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease5();
                        supportedLCSCapabilitySetRelease6 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease6();
                        supportedLCSCapabilitySetRelease7 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease7();
                        writeSupportedLCSCapabilitySets(supportedLCSCapabilitySets, supportedLCSCapabilitySetRelease98_99, supportedLCSCapabilitySetRelease4, supportedLCSCapabilitySetRelease5,
                            supportedLCSCapabilitySetRelease6, supportedLCSCapabilitySetRelease7, supportedLCSCapSetsJsonObject);
                        deferredmtlrDataJsonObject.add("SupportedLCSCapabilitySets", supportedLCSCapSetsJsonObject);
                    }
                    // Additional Supported LCS Capability Sets
                    if (slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalLCSCapabilitySets() != null) {
                        addSupportedLCSCapabilitySets = true;
                        addSupportedLCSCapabilitySetRelease98_99 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getSupportedLCSCapabilitySets().getCapabilitySetRelease98_99();
                        addSupportedLCSCapabilitySetRelease4 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease4();
                        addSupportedLCSCapabilitySetRelease5 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease5();
                        addSupportedLCSCapabilitySetRelease6 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease6();
                        addSupportedLCSCapabilitySetRelease7 = slrReq.getDeferredmtlrData().getLCSLocationInfo().getAdditionalLCSCapabilitySets().getCapabilitySetRelease7();
                        writeAdditionalLCSCapabilitySets(addSupportedLCSCapabilitySets, addSupportedLCSCapabilitySetRelease98_99, addSupportedLCSCapabilitySetRelease4, addSupportedLCSCapabilitySetRelease5,
                            addSupportedLCSCapabilitySetRelease6, addSupportedLCSCapabilitySetRelease7, additionalLCSCapSetsJsonObject);
                        deferredmtlrDataJsonObject.add("AdditionalLCSCapabilitySets", additionalLCSCapSetsJsonObject);
                    }
                }
                slrJsonObject.add("DeferredMTLRData", deferredmtlrDataJsonObject);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String slrReportJson = gson.toJson(slrJsonObject);

        return slrReportJson;
    }
}
