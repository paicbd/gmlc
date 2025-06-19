package org.mobicents.gmlc.slee.diameter;

import org.mobicents.gmlc.slee.diameter.slg.SLgLrrAvpValues;
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

import static org.mobicents.gmlc.slee.http.JsonWriter.bytesToHexString;
import static org.mobicents.gmlc.slee.utils.ByteUtils.bytesToHex;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class DiameterLcsResponseHelperForMLP {

    protected static final DecimalFormat coordinatesFormat = new DecimalFormat("#0.000000");
    protected static final DecimalFormat radiusFormat = new DecimalFormat("#0.00");
    protected static final DecimalFormat angleFormat = new DecimalFormat("#0.00");
    protected static final DecimalFormat axisFormat = new DecimalFormat("#0.00");

    String msisdn, imsi, lmsi, imei;
    private Integer mcc, mnc, lac, ci, sac;
    private Long eci, eNBId, cellPortionId;
    private String vlrNumber, mscNumber, sgsnNumber;
    private Boolean gprsNodeIndicator;
    private String nnn, mmeName, mmeRealm, sgsnName, sgsnRealm, tgppAAAServerName, gmlcAddress;
    private String typeOfShape;
    private Double latitude;
    private Double longitude;
    private Double uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis,
        uncertaintyAltitude, uncertaintyInnerRadius, offsetAngle, includedAngle;
    private Integer confidence, altitude, innerRadius, numberOfPoints;
    private Double radius = null;
    Polygon polygon = null;
    EllipsoidPoint[] polygonEllipsoidPoints;
    Double[][] polygonArray;
    private Long ageOfLocationEstimate;
    Integer accuracyFulfilmentIndicator;
    private Boolean deferredMTLRresponseIndicator, molrShortCircuitIndicator, saiPresent = false;
    private Integer horizontalSpeed, bearing, verticalSpeed, uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed;
    private Integer lcsQoSClassValue;
    private String geranPositioningInfo, geranGanssPositioningData, geranPositioningData, utranPositioningData, utranGanssPositioningData,
        utranAdditionalPositioningData, eUtranPositioningData;
    private String lcsEPSClientName, velocityType, civicAddress,
        mtlrMmeName, mtlrMmeRealm, mtlrSgsnName, mtlrSgsnRealm, mtlrSgsnNumber, mtlr3gppAAAServerName, mtlrMscNumber,
        dlrMmeName, dlrMmeRealm, dlrSgsnName, dlrSgsnRealm, dlrSgsnNumber, dlr3gppAAAServerName, dlrMscNumber;
    Long lcsServiceTypeId, reportingAmount, reportingInterval, deferredLocationType, dMtLrterminationCause,barometricPressure,
        lcsCapabilitySets, mtlrLcsCapabilitySets, dlrTerminationCause, dlrLcsCapabilitySets;
    Integer lcsReferenceNumber, clientReferenceNumber, lcsFormatIndicator, locationEvent, lcsPseudonymIndicator;

    public DiameterLcsResponseHelperForMLP() {
    }

    public void handleRirAnswerValues(SLhRiaAvpValues ria, String txMsisdn, String txIMSI) {

        if (ria != null) {
            // Get SLh Routing-Information-Answer values
            if (ria.getMsisdn() != null) {
                this.msisdn = AVPHandler.tbcd2IsdnAddressString(ria.getMsisdn()).getAddress();
            } else {
                this.msisdn = txMsisdn;
            }

            if (ria.getUserName() != null) {
                this.imsi = AVPHandler.userName2Imsi(ria.getUserName()).getData();
            } else {
                this.imsi = txIMSI;
            }

            if (ria.getLmsi() != null) {
                this.lmsi = bytesToHex(AVPHandler.byte2Lmsi(ria.getLmsi()).getData());
            }

            if (ria.getMmeName() != null) {
                this.mmeName = new String(AVPHandler.diameterIdToMapDiameterId(ria.getMmeName()).getData());
            }

            if (ria.getMmeRealm() != null) {
                this.mmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(ria.getMmeRealm()).getData());
            }

            if (ria.getSgsnNumber() != null) {
                this.sgsnNumber = AVPHandler.byte2IsdnAddressString(ria.getSgsnNumber()).getAddress();
            }

            if (ria.getSgsnName() != null) {
                this.sgsnName = new String(AVPHandler.diameterIdToMapDiameterId(ria.getSgsnName()).getData());
            }

            if (ria.getSgsnRealm() != null) {
                this.sgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(ria.getSgsnRealm()).getData());
            }

            if (ria.getMscNumber() != null) {
                this.mscNumber = AVPHandler.byte2IsdnAddressString(ria.getMscNumber()).getAddress();
            }

            if (ria.getTgppAAAServerName() != null) {
                this.tgppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(ria.getTgppAAAServerName()).getData());
            }

            if (ria.getGmlcAddress() != null) {
                this.gmlcAddress = bytesToHexString(ria.getGmlcAddress().getAddress());
                try {
                    InetAddress address = InetAddress.getByAddress(DatatypeConverter.parseHexBinary(this.gmlcAddress));
                    this.gmlcAddress = address.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void handlePlrAnswerValues(SLgPlaAvpValues pla) {

        if (pla != null) {
            // Get SLg Provide-Location-Answer values
            if (pla.getLocationEstimate() != null) {
                ExtGeographicalInformation locationEstimate = AVPHandler.lteLocationEstimate2ExtGeographicalInformation(pla.getLocationEstimate());
                this.typeOfShape = locationEstimate.getTypeOfShape().name();
                if (locationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    this.latitude = locationEstimate.getLatitude();
                    this.longitude = locationEstimate.getLongitude();
                    this.uncertainty = locationEstimate.getUncertainty();
                    this.uncertaintySemiMajorAxis = locationEstimate.getUncertaintySemiMajorAxis();
                    this.uncertaintySemiMinorAxis = locationEstimate.getUncertaintySemiMinorAxis();
                    this.angleOfMajorAxis = locationEstimate.getAngleOfMajorAxis();
                    this.confidence = locationEstimate.getConfidence();
                    this.altitude = locationEstimate.getAltitude();
                    this.uncertaintyAltitude = locationEstimate.getUncertaintyAltitude();
                    this.innerRadius = locationEstimate.getInnerRadius();
                    this.uncertaintyInnerRadius = locationEstimate.getUncertaintyRadius();
                    this.offsetAngle = locationEstimate.getOffsetAngle();
                    this.includedAngle = locationEstimate.getIncludedAngle();
                } else {
                    this.polygon = new PolygonImpl(pla.getLocationEstimate());
                    this.numberOfPoints = polygon.getNumberOfPoints();
                    this.polygonEllipsoidPoints = new EllipsoidPoint[numberOfPoints];
                    for (int point = 0; point < numberOfPoints; point++) {
                        polygonEllipsoidPoints[point] = polygon.getEllipsoidPoint(point);
                    }
                    try {
                        ((PolygonImpl) polygon).setData(polygonEllipsoidPoints);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (pla.getAgeOfLocationEstimate() <= Integer.MAX_VALUE && pla.getAgeOfLocationEstimate() >= Integer.MIN_VALUE) {
                this.ageOfLocationEstimate = Long.valueOf(AVPHandler.long2Int(pla.getAgeOfLocationEstimate()));
            }

            if (pla.getAccuracyFulfilmentIndicator() != null) {
                this.accuracyFulfilmentIndicator = Integer.valueOf(AVPHandler.diamAccFulInd2MapAccFulInd(pla.getAccuracyFulfilmentIndicator()).getIndicator());
            }

            if (pla.getCellGlobalIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength cellGlobalId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(pla.getCellGlobalIdentity());
                try {
                    this.mcc = cellGlobalId.getMCC();
                    this.mnc = cellGlobalId.getMNC();
                    this.lac = cellGlobalId.getLac();
                    this.ci = cellGlobalId.getCellIdOrServiceAreaCode();
                } catch (MAPException e) {
                    e.printStackTrace();
                }
            }

            if (pla.getServiceAreaIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength serviceAreaId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(pla.getServiceAreaIdentity());
                try {
                    this.mcc = serviceAreaId.getMCC();
                    this.mnc = serviceAreaId.getMNC();
                    this.lac = serviceAreaId.getLac();
                    this.sac = serviceAreaId.getCellIdOrServiceAreaCode();
                } catch (MAPException e) {
                    e.printStackTrace();
                }
            }

            if (pla.getEcgi() != null) {
                EUTRANCGI eutranCgi = new EUTRANCGIImpl(pla.getEcgi());
                try {
                    this.mcc = eutranCgi.getMCC();
                    this.mnc = eutranCgi.getMNC();
                    this.eci = eutranCgi.getEci();
                    this.eNBId = eutranCgi.getENodeBId();
                    this.ci = eutranCgi.getCi();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pla.getEsmlcCellInfoAvp() != null) {
                if (pla.getEsmlcCellInfoAvp().getECGI() != null) {
                    EUTRANCGI eutranCgi = new EUTRANCGIImpl(pla.getEsmlcCellInfoAvp().getECGI());
                    try {
                        this.mcc = eutranCgi.getMCC();
                        this.mnc = eutranCgi.getMNC();
                        this.eci = eutranCgi.getEci();
                        this.eNBId = eutranCgi.getENodeBId();
                        this.ci = eutranCgi.getCi();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (pla.getEsmlcCellInfoAvp().getCellPortionID() > -1)
                    this.cellPortionId = pla.getEsmlcCellInfoAvp().getCellPortionID();
            }

            if (pla.getMscNumber() != null) {
                this.mscNumber = AVPHandler.byte2IsdnAddressString(pla.getMscNumber()).getAddress();
            }

            if (pla.getMscNumber() != null) {
                this.sgsnNumber = AVPHandler.byte2IsdnAddressString(pla.getSgsnNumber()).getAddress();
            }

            if (pla.getGeranPositioningInfoAvp() != null) {
                if (pla.getGeranPositioningInfoAvp().getGERANPositioningData() != null) {
                    PositioningDataInformation geranPositioningDataInformation = AVPHandler.lteGeranPosDataInfo2MapGeranPosDataInfo(pla.getGeranPositioningInfoAvp().getGERANPositioningData());
                    this.geranPositioningInfo = bytesToHexString(geranPositioningDataInformation.getData());
                }
                if (pla.getGeranPositioningInfoAvp().getGERANGANSSPositioningData() != null) {
                    GeranGANSSpositioningData geranGANSSpositioningData = AVPHandler.lteGeranGanssPosDataInfo2MapGeranGanssPosDataInfo(pla.getGeranPositioningInfoAvp().getGERANGANSSPositioningData());
                    this.geranGanssPositioningData = bytesToHexString(geranGANSSpositioningData.getData());
                }
            }

            if (pla.getUtranPositioningInfoAvp() != null) {
                if (pla.getUtranPositioningInfoAvp().getUTRANPositioningData() != null) {
                    UtranPositioningDataInfo utranPositioningDataInfo = AVPHandler.lteUtranPosData2MapUtranPosDataInfo(pla.getUtranPositioningInfoAvp().getUTRANPositioningData());
                    this.utranPositioningData = bytesToHexString(utranPositioningDataInfo.getData());
                }
                if (pla.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData() != null) {
                    UtranGANSSpositioningData utranGANSSpositioningData = AVPHandler.lteUtranGanssPosData2MapUtranGanssPosDataInfo(pla.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData());
                    this.utranGanssPositioningData = bytesToHexString(utranGANSSpositioningData.getData());
                }
                if (pla.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData() != null) {
                    this.utranAdditionalPositioningData = bytesToHexString(pla.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData());
                }
            }

            if (pla.geteUtranPositioningData() != null) {
                this.eUtranPositioningData = bytesToHexString(pla.geteUtranPositioningData());
            }

            if (pla.getVelocityEstimate() != null) {
                VelocityEstimate lteVelocityEstimate = AVPHandler.lteVelocityEstimate2MapVelocityEstimate(pla.getVelocityEstimate());
                if (lteVelocityEstimate.getHorizontalSpeed() > -1)
                    this.horizontalSpeed = lteVelocityEstimate.getHorizontalSpeed();
                if (lteVelocityEstimate.getBearing() > -1)
                    this.bearing = lteVelocityEstimate.getBearing();
                if (lteVelocityEstimate.getVerticalSpeed() > -1)
                    this.verticalSpeed = lteVelocityEstimate.getVerticalSpeed();
                if (lteVelocityEstimate.getUncertaintyHorizontalSpeed() > -1)
                    this.uncertaintyHorizontalSpeed = lteVelocityEstimate.getUncertaintyHorizontalSpeed();
                if (lteVelocityEstimate.getUncertaintyVerticalSpeed() > -1)
                    this.uncertaintyVerticalSpeed = lteVelocityEstimate.getUncertaintyVerticalSpeed();
                if (lteVelocityEstimate.getVelocityType() != null)
                    this.velocityType = lteVelocityEstimate.getVelocityType().name();
            }

            if (pla.getCivicAddress() != null) {
                this.civicAddress = AVPHandler.byte2String(pla.getCivicAddress());
            }

            if (pla.getBarometricPressure() > -1) {
                this.barometricPressure = pla.getBarometricPressure();
            }
        }
    }


    public void handleLrrResponseValues(SLgLrrAvpValues lrr, Integer clientReferenceNumber) {

        if (lrr != null) {

            if (lrr.getMsisdn() != null) {
                this.msisdn = AVPHandler.tbcd2IsdnAddressString(lrr.getMsisdn()).getAddress();
            }

            if (lrr.getUserName() != null) {
                this.imsi = new String(AVPHandler.userName2Imsi(lrr.getUserName()).getData().getBytes());
            }

            if (lrr.getLcsReferenceNumber() != null) {
                this.lcsReferenceNumber = AVPHandler.byte2Int(lrr.getLcsReferenceNumber());
            }

            if (clientReferenceNumber != null) {
                this.clientReferenceNumber = clientReferenceNumber;
            }

            if (lrr.getImei() != null) {
                this.imei = AVPHandler.string2MapImei(lrr.getImei()).getIMEI();
            }

            if (lrr.getLcsServiceTypeId() != null) {
                this.lcsServiceTypeId = lrr.getLcsServiceTypeId();
            }

            if (lrr.getLocationEvent() != null) {
                this.locationEvent = lrr.getLocationEvent().getValue();
            }

            /*** LCS-EPS-Client-Name AVP ***/
            if (lrr.getLcsEPSClientName() != null) {
                if (lrr.getLcsEPSClientName().getLCSNameString() != null) {
                    this.lcsEPSClientName = lrr.getLcsEPSClientName().getLCSNameString();
                }
                if (lrr.getLcsEPSClientName().getLCSFormatIndicator() != null) {
                    this.lcsFormatIndicator = lrr.getLcsEPSClientName().getLCSFormatIndicator().getValue();
                }
            }

            /*** Location-Estimate AVP ***/
            if (lrr.getLocationEstimate() != null) {
                ExtGeographicalInformation lteLocationEstimate = AVPHandler.lteLocationEstimate2ExtGeographicalInformation(lrr.getLocationEstimate());
                this.typeOfShape = lteLocationEstimate.getTypeOfShape().name();
                if (lteLocationEstimate.getTypeOfShape() != TypeOfShape.Polygon) {
                    this.latitude = lteLocationEstimate.getLatitude();
                    this.longitude = lteLocationEstimate.getLongitude();
                    this.uncertainty = lteLocationEstimate.getUncertainty();
                    this.uncertaintySemiMajorAxis = lteLocationEstimate.getUncertaintySemiMajorAxis();
                    this.uncertaintySemiMinorAxis = lteLocationEstimate.getUncertaintySemiMinorAxis();
                    this.altitude = lteLocationEstimate.getAltitude();
                    this.uncertaintyAltitude = lteLocationEstimate.getUncertaintyAltitude();
                    this.innerRadius = lteLocationEstimate.getInnerRadius();
                    this.uncertaintyInnerRadius = lteLocationEstimate.getUncertaintyRadius();
                    this.offsetAngle = lteLocationEstimate.getOffsetAngle();
                    this.includedAngle = lteLocationEstimate.getIncludedAngle();
                    this.confidence = lteLocationEstimate.getConfidence();
                } else {
                    this.polygon = new PolygonImpl(lteLocationEstimate.getData());
                    this.numberOfPoints = polygon.getNumberOfPoints();
                    this.polygonEllipsoidPoints = new EllipsoidPoint[numberOfPoints];
                    for (int point = 0; point < numberOfPoints; point++) {
                        this.polygonEllipsoidPoints[point] = polygon.getEllipsoidPoint(point);
                    }
                    try {
                        ((PolygonImpl) polygon).setData(polygonEllipsoidPoints);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (lrr.getAgeOfLocationEstimate() != null) {
                this.ageOfLocationEstimate = lrr.getAgeOfLocationEstimate();
            }

            if (lrr.getAccuracyFulfilmentIndicator() != null) {
                this.accuracyFulfilmentIndicator = AVPHandler.diamAccFulInd2MapAccFulInd(lrr.getAccuracyFulfilmentIndicator()).getIndicator();
            }

            if (lrr.getLcsQoSClass() != null) {
                this.lcsQoSClassValue = lrr.getLcsQoSClass().getValue();
            }

            /*** Serving Node AVP ***/
            if (lrr.getServingNodeAvp() != null) {
                if (lrr.getServingNodeAvp().getMMEName() != null)
                    this.mmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getMMEName()).getData());
                if (lrr.getServingNodeAvp().getMMERealm() != null)
                    this.mmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getMMERealm()).getData());
                if (lrr.getServingNodeAvp().getSGSNName() != null)
                    this.sgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getSGSNName()).getData());
                if (lrr.getServingNodeAvp().getSGSNRealm() != null)
                    this.sgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().getSGSNRealm()).getData());
                if (lrr.getServingNodeAvp().getSGSNNumber() != null)
                    this.sgsnNumber = new String(AVPHandler.byte2String(lrr.getServingNodeAvp().getSGSNNumber()).getBytes());
                if (lrr.getServingNodeAvp().get3GPPAAAServerName() != null)
                    this.tgppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getServingNodeAvp().get3GPPAAAServerName()).getData());
                if (lrr.getServingNodeAvp().getMSCNumber() != null)
                    mscNumber = new String(AVPHandler.byte2String(lrr.getServingNodeAvp().getMSCNumber()).getBytes());
                if (Long.valueOf(lrr.getServingNodeAvp().getLcsCapabilitiesSets()) != null)
                    this.lcsCapabilitySets = Long.valueOf(lrr.getServingNodeAvp().getLcsCapabilitiesSets());
            }

            /*** CGI or SAI or ESMLC-Cell-Info AVPs ***/
            if (lrr.getCellGlobalIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength cellGlobalId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(lrr.getCellGlobalIdentity());
                try {
                    this.mcc = cellGlobalId.getMCC();
                    this.mnc = cellGlobalId.getMNC();
                    this.lac = cellGlobalId.getLac();
                    this.ci = cellGlobalId.getCellIdOrServiceAreaCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (lrr.getServiceAreaIdentity() != null) {
                CellGlobalIdOrServiceAreaIdFixedLength serviceAreaId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(lrr.getServiceAreaIdentity());
                try {
                    this.mcc = serviceAreaId.getMCC();
                    this.mnc = serviceAreaId.getMNC();
                    this.lac = serviceAreaId.getLac();
                    sac = serviceAreaId.getCellIdOrServiceAreaCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            EUTRANCGI eutranCgi;
            if (lrr.getEcgi() != null || lrr.getEsmlcCellInfoAvp() != null) {
                if (lrr.getEcgi() != null)
                    eutranCgi = new EUTRANCGIImpl(lrr.getEcgi());
                else
                    eutranCgi = new EUTRANCGIImpl(lrr.getEsmlcCellInfoAvp().getECGI());
                try {
                    this.mcc = eutranCgi.getMCC();
                    this.mnc = eutranCgi.getMNC();
                    this.eci = eutranCgi.getEci();
                    this.eNBId = eutranCgi.getENodeBId();
                    this.ci = eutranCgi.getCi();
                    if (lrr.getEsmlcCellInfoAvp() != null) {
                        if (lrr.getEsmlcCellInfoAvp().getCellPortionID() > -1) {
                            cellPortionId = lrr.getEsmlcCellInfoAvp().getCellPortionID();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /*** GERAN-Positioning-Info AVP ***/
            if (lrr.getGeranPositioningInfoAvp() != null) {
                if (lrr.getGeranPositioningInfoAvp().getGERANPositioningData() != null) {
                    this.geranPositioningData = bytesToHexString(AVPHandler.lteGeranPosDataInfo2MapGeranPosDataInfo(lrr.getGeranPositioningInfoAvp().getGERANPositioningData()).getData());
                }
                if (lrr.getGeranPositioningInfoAvp().getGERANGANSSPositioningData() != null) {
                    this.geranGanssPositioningData = bytesToHexString(AVPHandler.lteGeranGanssPosDataInfo2MapGeranGanssPosDataInfo(lrr.getGeranPositioningInfoAvp().getGERANGANSSPositioningData()).getData());
                }
            }

            /*** UTRAN-Positioning-Info AVP ***/
            if (lrr.getUtranPositioningInfoAvp() != null) {
                if (lrr.getUtranPositioningInfoAvp().getUTRANPositioningData() != null) {
                    this.utranPositioningData = bytesToHexString(AVPHandler.lteUtranPosData2MapUtranPosDataInfo(lrr.getUtranPositioningInfoAvp().getUTRANPositioningData()).getData());
                }
                if (lrr.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData() != null) {
                    this.utranGanssPositioningData = bytesToHexString(AVPHandler.lteUtranGanssPosData2MapUtranGanssPosDataInfo(lrr.getUtranPositioningInfoAvp().getUTRANGANSSPositioningData()).getData());
                }
                if (lrr.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData() != null) {
                    this.utranAdditionalPositioningData = bytesToHexString(AVPHandler.byte2String(lrr.getUtranPositioningInfoAvp().getUTRANAdditionalPositioningData()).getBytes());
                }
            }

            /*** UTRAN-Positioning-Info AVP ***/
            if (lrr.geteUtranPositioningData() != null) {
                this.eUtranPositioningData = bytesToHexString(lrr.geteUtranPositioningData());
            }

            /*** Velocity Estimate AVP ***/
            if (lrr.getVelocityEstimate() != null) {
                VelocityEstimate lteVelocityEstimate = AVPHandler.lteVelocityEstimate2MapVelocityEstimate(lrr.getVelocityEstimate());
                this.horizontalSpeed = lteVelocityEstimate.getHorizontalSpeed();
                this.bearing = lteVelocityEstimate.getBearing();
                this.verticalSpeed = lteVelocityEstimate.getVerticalSpeed();
                this.uncertaintyHorizontalSpeed = lteVelocityEstimate.getUncertaintyHorizontalSpeed();
                this.uncertaintyVerticalSpeed = lteVelocityEstimate.getUncertaintyVerticalSpeed();
                this.velocityType = lteVelocityEstimate.getVelocityType().name();
            }

            /*** Pseudonym-Indicator AVP ***/
            if (lrr.getPseudonymIndicator() != null) {
                this.lcsPseudonymIndicator = lrr.getPseudonymIndicator().getValue();
            }

            /*** Periodic-LDR-Info AVP ***/
            if (lrr.getPeriodicLDRInformation() != null) {
                this.reportingAmount = lrr.getPeriodicLDRInformation().getReportingAmount();
                this.reportingInterval = lrr.getPeriodicLDRInformation().getReportingInterval();
            }

            /*** Deferred-MT-LR-Data AVP ***/
            if (lrr.getDeferredMTLRDataAvp() != null) {
                if (Long.valueOf(lrr.getDeferredMTLRDataAvp().getDeferredLocationType()) != null) {
                    this.deferredLocationType = lrr.getDeferredMTLRDataAvp().getDeferredLocationType();
                }
                if (Long.valueOf(lrr.getDeferredMTLRDataAvp().getTerminationCause()) != null) {
                    this.dMtLrterminationCause = lrr.getDeferredMTLRDataAvp().getTerminationCause();
                }
                if (lrr.getDeferredMTLRDataAvp().getServingNode() != null) {
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName() != null)
                        this.mtlrMmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMERealm() != null)
                        this.mtlrMmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getMMERealm()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNName() != null)
                        this.mtlrSgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNName()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNRealm() != null)
                        this.mtlrSgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNRealm()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNNumber() != null)
                        this.mtlrSgsnNumber = new String(AVPHandler.byte2String(lrr.getDeferredMTLRDataAvp().getServingNode().getSGSNNumber()).getBytes());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().get3GPPAAAServerName() != null)
                        this.mtlr3gppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDeferredMTLRDataAvp().getServingNode().get3GPPAAAServerName()).getData());
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMSCNumber() != null)
                        this.mtlrMscNumber = new String(AVPHandler.byte2String(lrr.getDeferredMTLRDataAvp().getServingNode().getMSCNumber()).getBytes());
                    if (Long.valueOf(lrr.getDeferredMTLRDataAvp().getServingNode().getLcsCapabilitiesSets()) != null)
                        this.mtlrLcsCapabilitySets = Long.valueOf(lrr.getDeferredMTLRDataAvp().getServingNode().getLcsCapabilitiesSets());
                }
            }

            /*** Delayed-Location-Reporting-Data AVP ***/
            if (lrr.getDelayedLocationReportingDataAvp() != null) {
                if (Long.valueOf(lrr.getDelayedLocationReportingDataAvp().getTerminationCause()) != null) {
                    this.dlrTerminationCause = lrr.getDelayedLocationReportingDataAvp().getTerminationCause();
                }
                if (lrr.getDelayedLocationReportingDataAvp().getServingNode() != null) {
                    if (lrr.getDeferredMTLRDataAvp().getServingNode().getMMEName() != null)
                        this.dlrMmeName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMEName()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMERealm() != null)
                        this.dlrMmeRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMMERealm()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNName() != null)
                        this.dlrSgsnName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNName()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNRealm() != null)
                        this.dlrSgsnRealm = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNRealm()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNNumber() != null)
                        this.dlrSgsnNumber = new String(AVPHandler.byte2String(lrr.getDelayedLocationReportingDataAvp().getServingNode().getSGSNNumber()).getBytes());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().get3GPPAAAServerName() != null)
                        this.dlr3gppAAAServerName = new String(AVPHandler.diameterIdToMapDiameterId(lrr.getDelayedLocationReportingDataAvp().getServingNode().get3GPPAAAServerName()).getData());
                    if (lrr.getDelayedLocationReportingDataAvp().getServingNode().getMSCNumber() != null)
                        this.dlrMscNumber = new String(AVPHandler.byte2String(lrr.getDelayedLocationReportingDataAvp().getServingNode().getMSCNumber()).getBytes());
                    if (Long.valueOf(lrr.getDelayedLocationReportingDataAvp().getServingNode().getLcsCapabilitiesSets()) != null)
                        this.dlrLcsCapabilitySets = Long.valueOf(lrr.getDelayedLocationReportingDataAvp().getServingNode().getLcsCapabilitiesSets());
                }
            }

            /*** Civic-Address AVP ***/
            if (lrr.getCivicAddress() != null) {
                this.civicAddress = AVPHandler.byte2String(lrr.getCivicAddress());
            }

            /*** Barometric-Pressure AVP ***/
            if (Long.valueOf(lrr.getBarometricPressure()) != null) {
                this.barometricPressure = lrr.getBarometricPressure();
            }
        }

    }

    //////////////////////////
    // Getters and Setters //
    ////////////////////////


    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    public Integer getMnc() {
        return mnc;
    }

    public void setMnc(Integer mnc) {
        this.mnc = mnc;
    }

    public Integer getLac() {
        return lac;
    }

    public void setLac(Integer lac) {
        this.lac = lac;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Integer getSac() {
        return sac;
    }

    public void setSac(Integer sac) {
        this.sac = sac;
    }

    public Long getEci() {
        return eci;
    }

    public void setEci(Long eci) {
        this.eci = eci;
    }

    public Long geteNBId() {
        return eNBId;
    }

    public void seteNBId(Long eNBId) {
        this.eNBId = eNBId;
    }

    public Long getCellPortionId() {
        return cellPortionId;
    }

    public void setCellPortionId(Long cellPortionId) {
        this.cellPortionId = cellPortionId;
    }

    public String getMmeRealm() {
        return mmeRealm;
    }

    public void setMmeRealm(String mmeRealm) {
        this.mmeRealm = mmeRealm;
    }

    public String getSgsnRealm() {
        return sgsnRealm;
    }

    public void setSgsnRealm(String sgsnRealm) {
        this.sgsnRealm = sgsnRealm;
    }

    public String getCivicAddress() {
        return civicAddress;
    }

    public void setCivicAddress(String civicAddress) {
        this.civicAddress = civicAddress;
    }

    public Long getBarometricPressure() {
        return barometricPressure;
    }

    public void setBarometricPressure(Long barometricPressure) {
        this.barometricPressure = barometricPressure;
    }

    public String getVlrNumber() {
        return vlrNumber;
    }

    public void setVlrNumber(String vlrNumber) {
        this.vlrNumber = vlrNumber;
    }

    public String getMscNumber() {
        return mscNumber;
    }

    public void setMscNumber(String mscNumber) {
        this.mscNumber = mscNumber;
    }

    public String getSgsnNumber() {
        return sgsnNumber;
    }

    public void setSgsnNumber(String sgsnNumber) {
        this.sgsnNumber = sgsnNumber;
    }

    public Boolean getGprsNodeIndicator() {
        return gprsNodeIndicator;
    }

    public void setGprsNodeIndicator(Boolean gprsNodeIndicator) {
        this.gprsNodeIndicator = gprsNodeIndicator;
    }

    public String getNnn() {
        return nnn;
    }

    public void setNnn(String nnn) {
        this.nnn = nnn;
    }

    public String getMmeName() {
        return mmeName;
    }

    public void setMmeName(String mmeName) {
        this.mmeName = mmeName;
    }

    public String getSgsnName() {
        return sgsnName;
    }

    public void setSgsnName(String sgsnName) {
        this.sgsnName = sgsnName;
    }

    public String getTgppAAAServerName() {
        return tgppAAAServerName;
    }

    public void setTgppAAAServerName(String tgppAAAServerName) {
        this.tgppAAAServerName = tgppAAAServerName;
    }

    public String getGmlcAddress() {
        return gmlcAddress;
    }

    public void setGmlcAddress(String gmlcAddress) {
        this.gmlcAddress = gmlcAddress;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLmsi() {
        return lmsi;
    }

    public void setLmsi(String lmsi) {
        this.lmsi = lmsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTypeOfShape() {
        return typeOfShape;
    }

    public void setTypeOfShape(String typeOfShape) {
        this.typeOfShape = typeOfShape;
    }

    public Double getLatitude() {
        if (this.latitude != null) {
            String formattedLatitude = coordinatesFormat.format(this.latitude);
            return Double.valueOf(formattedLatitude);
        }
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        if (this.longitude != null) {
            String formattedLongitude = coordinatesFormat.format(this.longitude);
            return Double.valueOf(formattedLongitude);
        }
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getUncertainty() {
        return uncertainty;
    }

    public void setUncertainty(Double uncertainty) {
        this.uncertainty = uncertainty;
    }

    public Double getRadius() {
        if (this.typeOfShape != null) {
            if (this.typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyCircle") ||
                this.typeOfShape.equalsIgnoreCase("EllipsoidPointWithUncertaintyEllipse") ||
                this.typeOfShape.equalsIgnoreCase("EllipsoidPointWithAltitudeAndUncertaintyEllipsoid")) {
                if (this.uncertainty != null) {
                    this.radius = this.uncertainty;
                    String formattedRadius = radiusFormat.format(this.radius);
                    return Double.valueOf(formattedRadius);
                }
            } else if (this.typeOfShape.equalsIgnoreCase("EllipsoidArc")) {
                if (this.innerRadius != null) {
                    this.radius = Double.valueOf(this.innerRadius);
                    String formattedRadius = radiusFormat.format(this.radius);
                    return Double.valueOf(formattedRadius);
                }
            }
        }
        return null;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getUncertaintySemiMajorAxis() {
        if (this.uncertaintySemiMajorAxis != null) {
            String formattedMajorAxis = axisFormat.format(this.uncertaintySemiMajorAxis);
            return Double.valueOf(formattedMajorAxis);
        } else {
            return null;
        }
    }

    public void setUncertaintySemiMajorAxis(Double uncertaintySemiMajorAxis) {
        this.uncertaintySemiMajorAxis = uncertaintySemiMajorAxis;
    }

    public Double getUncertaintySemiMinorAxis() {
        if (this.uncertaintySemiMinorAxis != null) {
            String formattedMinorAxis = axisFormat.format(this.uncertaintySemiMinorAxis);
            return Double.valueOf(formattedMinorAxis);
        } else {
            return null;
        }
    }

    public void setUncertaintySemiMinorAxis(Double uncertaintySemiMinorAxis) {
        this.uncertaintySemiMinorAxis = uncertaintySemiMinorAxis;
    }

    public Double getAngleOfMajorAxis() {
        if (this.angleOfMajorAxis != null) {
            String formattedAngle = angleFormat.format(this.angleOfMajorAxis);
            return Double.valueOf(formattedAngle);
        } else {
            return null;
        }
    }

    public void setAngleOfMajorAxis(Double angleOfMajorAxis) {
        this.angleOfMajorAxis = angleOfMajorAxis;
    }

    public Double getOffsetAngle() {
        if (this.offsetAngle != null) {
            String formattedAngle = angleFormat.format(this.offsetAngle);
            return Double.valueOf(formattedAngle);
        } else {
            return null;
        }
    }

    public void setOffsetAngle(Double offsetAngle) {
        this.offsetAngle = offsetAngle;
    }

    public Double getIncludedAngle() {
        if (this.includedAngle != null) {
            String formattedAngle = angleFormat.format(this.includedAngle);
            return Double.valueOf(formattedAngle);
        } else {
            return null;
        }
    }

    public void setIncludedAngle(Double includedAngle) {
        this.includedAngle = includedAngle;
    }

    public Double getUncertaintyAltitude() {
        return uncertaintyAltitude;
    }

    public void setUncertaintyAltitude(Double uncertaintyAltitude) {
        this.uncertaintyAltitude = uncertaintyAltitude;
    }

    public Double getUncertaintyInnerRadius() {
        return uncertaintyInnerRadius;
    }

    public void setUncertaintyInnerRadius(Double uncertaintyInnerRadius) {
        this.uncertaintyInnerRadius = uncertaintyInnerRadius;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(Integer innerRadius) {
        this.innerRadius = innerRadius;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public EllipsoidPoint[] getPolygonEllipsoidPoints() {
        return polygonEllipsoidPoints;
    }

    public void setPolygonEllipsoidPoints(EllipsoidPoint[] polygonEllipsoidPoints) {
        this.polygonEllipsoidPoints = polygonEllipsoidPoints;
    }

    public Double[][] getPolygonArray() {
        return this.polygonArray;
    }

    public void setPolygonArray(Double[][] polygonArray) {
        this.polygonArray = polygonArray;
    }

    public Long getAgeOfLocationEstimate() {
        return ageOfLocationEstimate;
    }

    public void setAgeOfLocationEstimate(Long ageOfLocationEstimate) {
        this.ageOfLocationEstimate = ageOfLocationEstimate;
    }

    public Integer getAccuracyFulfilmentIndicator() {
        return accuracyFulfilmentIndicator;
    }

    public void setAccuracyFulfilmentIndicator(Integer accuracyFulfilmentIndicator) {
        this.accuracyFulfilmentIndicator = accuracyFulfilmentIndicator;
    }

    public Boolean getDeferredMTLRresponseIndicator() {
        return deferredMTLRresponseIndicator;
    }

    public void setDeferredMTLRresponseIndicator(Boolean deferredMTLRresponseIndicator) {
        this.deferredMTLRresponseIndicator = deferredMTLRresponseIndicator;
    }

    public Boolean getMolrShortCircuitIndicator() {
        return molrShortCircuitIndicator;
    }

    public void setMolrShortCircuitIndicator(Boolean molrShortCircuitIndicator) {
        this.molrShortCircuitIndicator = molrShortCircuitIndicator;
    }

    public Boolean getSaiPresent() {
        return saiPresent;
    }

    public void setSaiPresent(Boolean saiPresent) {
        this.saiPresent = saiPresent;
    }

    public Integer getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setHorizontalSpeed(Integer horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public Integer getBearing() {
        return bearing;
    }

    public void setBearing(Integer bearing) {
        this.bearing = bearing;
    }

    public Integer getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(Integer verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public Integer getUncertaintyHorizontalSpeed() {
        return uncertaintyHorizontalSpeed;
    }

    public void setUncertaintyHorizontalSpeed(Integer uncertaintyHorizontalSpeed) {
        this.uncertaintyHorizontalSpeed = uncertaintyHorizontalSpeed;
    }

    public Integer getUncertaintyVerticalSpeed() {
        return uncertaintyVerticalSpeed;
    }

    public void setUncertaintyVerticalSpeed(Integer uncertaintyVerticalSpeed) {
        this.uncertaintyVerticalSpeed = uncertaintyVerticalSpeed;
    }

    public String getVelocityType() {
        return velocityType;
    }

    public void setVelocityType(String velocityType) {
        this.velocityType = velocityType;
    }

    public Integer getLcsReferenceNumber() {
        return lcsReferenceNumber;
    }

    public void setLcsReferenceNumber(Integer lcsReferenceNumber) {
        this.lcsReferenceNumber = lcsReferenceNumber;
    }

    public Integer getLcsQoSClassValue() {
        return lcsQoSClassValue;
    }

    public void setLcsQoSClassValue(Integer lcsQoSClassValue) {
        this.lcsQoSClassValue = lcsQoSClassValue;
    }

    public String getLcsEPSClientName() {
        return lcsEPSClientName;
    }

    public void setLcsEPSClientName(String lcsEPSClientName) {
        this.lcsEPSClientName = lcsEPSClientName;
    }

    public String getMtlrMmeName() {
        return mtlrMmeName;
    }

    public void setMtlrMmeName(String mtlrMmeName) {
        this.mtlrMmeName = mtlrMmeName;
    }

    public String getMtlrMmeRealm() {
        return mtlrMmeRealm;
    }

    public void setMtlrMmeRealm(String mtlrMmeRealm) {
        this.mtlrMmeRealm = mtlrMmeRealm;
    }

    public String getMtlrSgsnName() {
        return mtlrSgsnName;
    }

    public void setMtlrSgsnName(String mtlrSgsnName) {
        this.mtlrSgsnName = mtlrSgsnName;
    }

    public String getMtlrSgsnRealm() {
        return mtlrSgsnRealm;
    }

    public void setMtlrSgsnRealm(String mtlrSgsnRealm) {
        this.mtlrSgsnRealm = mtlrSgsnRealm;
    }

    public String getMtlrSgsnNumber() {
        return mtlrSgsnNumber;
    }

    public void setMtlrSgsnNumber(String mtlrSgsnNumber) {
        this.mtlrSgsnNumber = mtlrSgsnNumber;
    }

    public String getMtlr3gppAAAServerName() {
        return mtlr3gppAAAServerName;
    }

    public void setMtlr3gppAAAServerName(String mtlr3gppAAAServerName) {
        this.mtlr3gppAAAServerName = mtlr3gppAAAServerName;
    }

    public String getMtlrMscNumber() {
        return mtlrMscNumber;
    }

    public void setMtlrMscNumber(String mtlrMscNumber) {
        this.mtlrMscNumber = mtlrMscNumber;
    }

    public String getDlrMmeName() {
        return dlrMmeName;
    }

    public void setDlrMmeName(String dlrMmeName) {
        this.dlrMmeName = dlrMmeName;
    }

    public String getDlrMmeRealm() {
        return dlrMmeRealm;
    }

    public void setDlrMmeRealm(String dlrMmeRealm) {
        this.dlrMmeRealm = dlrMmeRealm;
    }

    public String getDlrSgsnName() {
        return dlrSgsnName;
    }

    public void setDlrSgsnName(String dlrSgsnName) {
        this.dlrSgsnName = dlrSgsnName;
    }

    public String getDlrSgsnRealm() {
        return dlrSgsnRealm;
    }

    public void setDlrSgsnRealm(String dlrSgsnRealm) {
        this.dlrSgsnRealm = dlrSgsnRealm;
    }

    public String getDlrSgsnNumber() {
        return dlrSgsnNumber;
    }

    public void setDlrSgsnNumber(String dlrSgsnNumber) {
        this.dlrSgsnNumber = dlrSgsnNumber;
    }

    public String getDlr3gppAAAServerName() {
        return dlr3gppAAAServerName;
    }

    public void setDlr3gppAAAServerName(String dlr3gppAAAServerName) {
        this.dlr3gppAAAServerName = dlr3gppAAAServerName;
    }

    public String getDlrMscNumber() {
        return dlrMscNumber;
    }

    public void setDlrMscNumber(String dlrMscNumber) {
        this.dlrMscNumber = dlrMscNumber;
    }

    public Long getLcsServiceTypeId() {
        return lcsServiceTypeId;
    }

    public void setLcsServiceTypeId(Long lcsServiceTypeId) {
        this.lcsServiceTypeId = lcsServiceTypeId;
    }

    public Long getReportingAmount() {
        return reportingAmount;
    }

    public void setReportingAmount(Long reportingAmount) {
        this.reportingAmount = reportingAmount;
    }

    public Long getReportingInterval() {
        return reportingInterval;
    }

    public void setReportingInterval(Long reportingInterval) {
        this.reportingInterval = reportingInterval;
    }

    public Long getDeferredLocationType() {
        return deferredLocationType;
    }

    public void setDeferredLocationType(Long deferredLocationType) {
        this.deferredLocationType = deferredLocationType;
    }

    public Integer getClientReferenceNumber() {
        return clientReferenceNumber;
    }

    public void setClientReferenceNumber(Integer clientReferenceNumber) {
        this.clientReferenceNumber = clientReferenceNumber;
    }

    public Integer getLcsFormatIndicator() {
        return lcsFormatIndicator;
    }

    public void setLcsFormatIndicator(Integer lcsFormatIndicator) {
        this.lcsFormatIndicator = lcsFormatIndicator;
    }

    public Integer getLocationEvent() {
        return locationEvent;
    }

    public void setLocationEvent(Integer locationEvent) {
        this.locationEvent = locationEvent;
    }

    public Integer getLcsPseudonymIndicator() {
        return lcsPseudonymIndicator;
    }

    public void setLcsPseudonymIndicator(Integer lcsPseudonymIndicator) {
        this.lcsPseudonymIndicator = lcsPseudonymIndicator;
    }
}
