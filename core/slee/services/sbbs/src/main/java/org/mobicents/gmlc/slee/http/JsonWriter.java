package org.mobicents.gmlc.slee.http;

import org.apache.log4j.Logger;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class JsonWriter {

    protected static final Logger logger = Logger.getLogger(JsonWriter.class);

    protected static final DecimalFormat coordinatesFormat = new DecimalFormat("#0.000000");
    protected static final DecimalFormat uncertaintyFormat = new DecimalFormat("#0.000000");
    protected static final DecimalFormat areaFormat = new DecimalFormat("#0.00");

    public JsonWriter() {
    }

    /**********************/
    /*** JSON Writers ****/
    /********************/

    protected static void writeNetwork(final String network, final JsonObject jsonObject) {
        if (network != null) {
            jsonObject.addProperty("network", network);
        } else {
            jsonObject.add("network", JsonNull.INSTANCE);
        }
    }

    protected static void writeOperation(final String operation, final JsonObject jsonObject) {
        if (operation != null) {
            jsonObject.addProperty("operation", operation);
        } else {
            jsonObject.add("operation", JsonNull.INSTANCE);
        }
    }

    protected static void writeProtocol(final String protocol, final JsonObject jsonObject) {
        if (protocol != null) {
            jsonObject.addProperty("protocol", protocol);
        } else {
            jsonObject.add("protocol", JsonNull.INSTANCE);
        }
    }

    protected static void writeOperationResult(final String result, final JsonObject jsonObject) {
        if (result != null) {
            jsonObject.addProperty("result", result);
        } else {
            jsonObject.add("result", JsonNull.INSTANCE);
        }
    }

    protected static void writeDiameterResult(final String diameterResult, final JsonObject jsonObject) {
        if (diameterResult != null) {
            jsonObject.addProperty("diameterResult", diameterResult);
        } else {
            jsonObject.add("diameterResult", JsonNull.INSTANCE);
        }
    }

    protected static void writeOperationErrorReason(final String errorReason, final JsonObject jsonObject) {
        if (errorReason != null) {
            jsonObject.addProperty( "errorReason", "["+errorReason+"]");
        } else {
            jsonObject.add("errorReason", JsonNull.INSTANCE);
        }
    }

    protected static void writeLcsReferenceNumber(final Integer lcsReferenceNumber, final JsonObject jsonObject) {
        if (lcsReferenceNumber != null) {
            jsonObject.addProperty("lcsReferenceNumber", lcsReferenceNumber);
        } else {
            jsonObject.add("lcsReferenceNumber", JsonNull.INSTANCE);
        }
    }

    protected static void writeClientReferenceNumber(final Integer clientReferenceNumber, final JsonObject jsonObject) {
        if (clientReferenceNumber != null) {
            jsonObject.addProperty("clientReferenceNumber", clientReferenceNumber);
        } else {
            jsonObject.add("clientReferenceNumber", JsonNull.INSTANCE);
        }
    }

    protected static void writeMcc(final Integer mcc, final JsonObject jsonObject) {
        if (mcc != null) {
            jsonObject.addProperty("mcc", mcc);
        } else {
            jsonObject.add("mcc", JsonNull.INSTANCE);
        }
    }

    protected static void writeMnc(final Integer mnc, final JsonObject jsonObject) {
        if (mnc != null) {
            jsonObject.addProperty("mnc", mnc);
        } else {
            jsonObject.add("mnc", JsonNull.INSTANCE);
        }
    }

    protected static void writeLac(final Integer lac, final JsonObject jsonObject) {
        if (lac != null) {
            jsonObject.addProperty("lac", lac);
        } else {
            jsonObject.add("lac", JsonNull.INSTANCE);
        }
    }

    protected static void writeCellId(final Integer cellId, final JsonObject jsonObject) {
        if (cellId != null) {
            jsonObject.addProperty("ci", cellId);
        } else {
            jsonObject.add("ci", JsonNull.INSTANCE);
        }
    }

    protected static void writeAol(final Integer aol, final JsonObject jsonObject) {
        if (aol != null) {
            jsonObject.addProperty("ageOfLocationInformation", aol);
        } else {
            jsonObject.add("ageOfLocationInformation", JsonNull.INSTANCE);
        }
    }

    protected static void writeVlrNumber(final String vlrNumberAddress, final JsonObject jsonObject) {
        Long vlrNumber = null;
        try {
            if (vlrNumberAddress != null) {
                if (!vlrNumberAddress.equalsIgnoreCase(""))
                    vlrNumber = Long.valueOf(vlrNumberAddress);
            }
            if (vlrNumber != null) {
                jsonObject.addProperty("vlrNumber", vlrNumber);
            } else {
                jsonObject.add("vlrNumber", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("vlrNumber", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeSubscriberState(final String csSubscriberState, final JsonObject jsonObject) {
        if (csSubscriberState != null) {
            jsonObject.addProperty("subscriberState", csSubscriberState);
        } else {
            jsonObject.add("subscriberState", JsonNull.INSTANCE);
        }
    }

    protected static void writeNotReachableReason(final String notReachableReason, final JsonObject jsonObject) {
        if (notReachableReason != null) {
            jsonObject.addProperty("notReachableReason", notReachableReason);
        } else {
            jsonObject.add("notReachableReason", JsonNull.INSTANCE);
        }
    }

    protected static void writeMSClassmark(final String msClassmark, final JsonObject jsonObject) {
        if (msClassmark != null) {
            jsonObject.addProperty("msClassmark", msClassmark);
        } else {
            jsonObject.add("msClassmark", JsonNull.INSTANCE);
        }
    }

    protected static void writeMSNetworkCapability(final String msNetworkCapability, final JsonObject jsonObject) {
        if (msNetworkCapability != null) {
            jsonObject.addProperty("msNetworkCapability", msNetworkCapability);
        } else {
            jsonObject.add("msNetworkCapability", JsonNull.INSTANCE);
        }
    }

    protected static void writeMSRadioAccessCapability(final String msRadioAccessCapability, final JsonObject jsonObject) {
        if (msRadioAccessCapability != null) {
            jsonObject.addProperty("msRadioAccessCapability", msRadioAccessCapability);
        } else {
            jsonObject.add("msRadioAccessCapability", JsonNull.INSTANCE);
        }
    }

    protected static void writeImei(final String imei, final JsonObject jsonObject) {
        if (imei != null) {
            jsonObject.addProperty("imei", imei);
        } else {
            jsonObject.add("imei", JsonNull.INSTANCE);
        }
    }

    protected static void writeSaiPresent(final Boolean saiPresent, final JsonObject jsonObject) {
        if (saiPresent != null) {
            jsonObject.addProperty("saiPresent", saiPresent);
        } else {
            jsonObject.add("saiPresent", JsonNull.INSTANCE);
        }
    }

    protected static void writeOddFlag(final Boolean oddFlag, final JsonObject jsonObject) {
        if (oddFlag != null) {
            jsonObject.addProperty("oddFlag", oddFlag);
        } else {
            jsonObject.add("oddFlag", JsonNull.INSTANCE);
        }
    }

    protected static void writeNatureOfAddressIndicator(final Integer natureOfAddressIndicator, final JsonObject jsonObject) {
        if (natureOfAddressIndicator != null) {
            jsonObject.addProperty("natureOfAddressIndicator", natureOfAddressIndicator);
        } else {
            jsonObject.add("natureOfAddressIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeInternalNetworkNumberIndicator(final Integer internalNetworkNumberIndicator, final JsonObject jsonObject) {
        if (internalNetworkNumberIndicator != null) {
            jsonObject.addProperty("internalNetworkNumberIndicator", internalNetworkNumberIndicator);
        } else {
            jsonObject.add("internalNetworkNumberIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeNumberingPlanIndicator(final Integer numberingPlanIndicator, final JsonObject jsonObject) {
        if (numberingPlanIndicator != null) {
            jsonObject.addProperty("numberingPlanIndicator", numberingPlanIndicator);
        } else {
            jsonObject.add("numberingPlanIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeAddressPresentationRestrictedIndicator(final Integer addressPresentationRestrictedIndicator, final JsonObject jsonObject) {
        if (addressPresentationRestrictedIndicator != null) {
            jsonObject.addProperty("addressPresentationRestrictedIndicator", addressPresentationRestrictedIndicator);
        } else {
            jsonObject.add("addressPresentationRestrictedIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeScreeningIndicator(final Integer screeningIndicator, final JsonObject jsonObject) {
        if (screeningIndicator != null) {
            jsonObject.addProperty("screeningIndicator", screeningIndicator);
        } else {
            jsonObject.add("screeningIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeLocationNumberAddress(final String address, final JsonObject jsonObject) {
        Long locationNumberAddress = null;
        try {
            if (address != null) {
                if (!address.equalsIgnoreCase(""))
                    locationNumberAddress = Long.valueOf(address);
            }
            if (locationNumberAddress != null) {
                jsonObject.addProperty("address", locationNumberAddress);
            } else {
                jsonObject.add("address", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("address", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeMscNumber(final String mscNumberAddress, final JsonObject jsonObject) {
        Long mscNumber = null;
        try {
            if (mscNumberAddress != null) {
                if (!mscNumberAddress.equalsIgnoreCase(""))
                    mscNumber = Long.valueOf(mscNumberAddress);
            }
            if (mscNumber != null) {
                jsonObject.addProperty("mscNumber", mscNumber);
            } else {
                jsonObject.add("mscNumber", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("mscNumber", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeNumberOfPoints(final Integer numberOfPoints, final JsonObject jsonObject) {
        if (numberOfPoints != null) {
            jsonObject.addProperty("numberOfPoints", numberOfPoints);
        } else {
            jsonObject.add("numberOfPoints", JsonNull.INSTANCE);
        }
    }

    protected static void writeTypeOfShape(final String typeOfShape, final JsonObject jsonObject) {
        if (typeOfShape != null) {
            jsonObject.addProperty("typeOfShape", typeOfShape);
        } else {
            jsonObject.add("typeOfShape", JsonNull.INSTANCE);
        }
    }

    protected static void writeLatitude(Double latitude, final JsonObject jsonObject) {
        try {
            String formattedLatitude;
            if (latitude != null) {
                if (latitude != 0.0) {
                    formattedLatitude = coordinatesFormat.format(latitude);
                    latitude = Double.valueOf(formattedLatitude);
                    jsonObject.addProperty("latitude", latitude);
                }
            } else {
                jsonObject.add("latitude", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("latitude", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }

    }

    protected static void writeLongitude(Double longitude, final JsonObject jsonObject) {
        try {
            String formattedLongitude;
            if (longitude != null) {
                if (longitude != 0.0) {
                    formattedLongitude = coordinatesFormat.format(longitude);
                    longitude = Double.valueOf(formattedLongitude);
                    jsonObject.addProperty("longitude", longitude);
                }
            } else {
                jsonObject.add("longitude", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("longitude", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeUncertaintySemiMajorAxis(Double uncertaintySemiMajorAxis, final JsonObject jsonObject) {
        try {
            String formattedUncertaintySemiMajorAxis;
            if (uncertaintySemiMajorAxis != null) {
                formattedUncertaintySemiMajorAxis = uncertaintyFormat.format(uncertaintySemiMajorAxis);
                uncertaintySemiMajorAxis = Double.valueOf(formattedUncertaintySemiMajorAxis);
                jsonObject.addProperty("uncertaintySemiMajorAxis", uncertaintySemiMajorAxis);
            } else {
                jsonObject.add("uncertaintySemiMajorAxis", JsonNull.INSTANCE);
            }
        }  catch (NumberFormatException nfe) {
            jsonObject.add("uncertaintySemiMajorAxis", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeUncertaintySemiMinorAxis(Double uncertaintySemiMinorAxis, final JsonObject jsonObject) {
        try {
            String formattedUncertaintySemiMinorAxis;
            if (uncertaintySemiMinorAxis != null) {
                formattedUncertaintySemiMinorAxis = uncertaintyFormat.format(uncertaintySemiMinorAxis);
                uncertaintySemiMinorAxis = Double.valueOf(formattedUncertaintySemiMinorAxis);
                jsonObject.addProperty("uncertaintySemiMinorAxis", uncertaintySemiMinorAxis);
            } else {
                jsonObject.add("uncertaintySemiMinorAxis", JsonNull.INSTANCE);
            }
        }  catch (NumberFormatException nfe) {
            jsonObject.add("uncertaintySemiMinorAxis", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeAngleOfMajorAxis(final Double angleOfMajorAxis, final JsonObject jsonObject) {
        if (angleOfMajorAxis != null) {
            jsonObject.addProperty("angleOfMajorAxis", angleOfMajorAxis);
        } else {
            jsonObject.add("angleOfMajorAxis", JsonNull.INSTANCE);
        }
    }

    protected static void writeAltitude(final Integer altitude, final JsonObject jsonObject) {
        if (altitude != null) {
            jsonObject.addProperty("altitude", altitude);
        } else {
            jsonObject.add("altitude", JsonNull.INSTANCE);
        }
    }

    protected static void writeUncertaintyAltitude(Double uncertaintyAltitude, final JsonObject jsonObject) {
        try {
            String formattedUncertaintyAltitude;
            if (uncertaintyAltitude != null) {
                formattedUncertaintyAltitude = uncertaintyFormat.format(uncertaintyAltitude);
                uncertaintyAltitude = Double.valueOf(formattedUncertaintyAltitude);
                jsonObject.addProperty("uncertaintyAltitude", uncertaintyAltitude);
            } else {
                jsonObject.add("uncertaintyAltitude", JsonNull.INSTANCE);
            }
        }  catch (NumberFormatException nfe) {
            jsonObject.add("uncertaintyAltitude", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeInnerRadius(final Integer innerRadius, final JsonObject jsonObject) {
        if (innerRadius != null) {
            jsonObject.addProperty("innerRadius", innerRadius);
        } else {
            jsonObject.add("innerRadius", JsonNull.INSTANCE);
        }
    }

    protected static void writeUncertaintyInnerRadius(Double uncertaintyRadius, final JsonObject jsonObject) {
        try {
            String formattedUncertaintyRadius;
            if (uncertaintyRadius != null) {
                formattedUncertaintyRadius = uncertaintyFormat.format(uncertaintyRadius);
                uncertaintyRadius = Double.valueOf(formattedUncertaintyRadius);
                jsonObject.addProperty("uncertaintyInnerRadius", uncertaintyRadius);
            } else {
                jsonObject.add("uncertaintyInnerRadius", JsonNull.INSTANCE);
            }
        }  catch (NumberFormatException nfe) {
            jsonObject.add("uncertaintyInnerRadius", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeOffsetAngle(final Double offsetAngle, final JsonObject jsonObject) {
        if (offsetAngle != null) {
            jsonObject.addProperty("offsetAngle", offsetAngle);
        } else {
            jsonObject.add("offsetAngle", JsonNull.INSTANCE);
        }
    }

    protected static void writeIncludedAngle(final Double includedAngle, final JsonObject jsonObject) {
        if (includedAngle != null) {
            jsonObject.addProperty("includedAngle", includedAngle);
        } else {
            jsonObject.add("includedAngle", JsonNull.INSTANCE);
        }
    }

    protected static void writeUncertainty(Double uncertainty, final JsonObject jsonObject) {
        try {
            String formattedUncertainty;
            if (uncertainty != null) {
                formattedUncertainty = uncertaintyFormat.format(uncertainty);
                uncertainty = Double.valueOf(formattedUncertainty);
                jsonObject.addProperty("uncertainty", uncertainty);
            } else {
                jsonObject.add("uncertainty", JsonNull.INSTANCE);
            }
        }  catch (NumberFormatException nfe) {
            jsonObject.add("uncertainty", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeUncertainty(Double uncertainty, final Double latitude, final Double longitude, final JsonObject jsonObject) {
        try {
            String formattedUncertainty;
            if (uncertainty != null && latitude != 0.0 && longitude != 0.0) {
                formattedUncertainty = uncertaintyFormat.format(uncertainty);
                uncertainty = Double.valueOf(formattedUncertainty);
                jsonObject.addProperty("uncertainty", uncertainty);
            } else {
                jsonObject.add("uncertainty", JsonNull.INSTANCE);
            }
        }  catch (NumberFormatException nfe) {
            jsonObject.add("uncertainty", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeConfidence(final Integer confidence, final JsonObject jsonObject) {
        if (confidence != null) {
            jsonObject.addProperty("confidence", confidence);
        } else {
            jsonObject.add("confidence", JsonNull.INSTANCE);
        }
    }

    protected static void writeConfidence(final Integer confidence, final Double latitude, final Double longitude, final JsonObject jsonObject) {
        if (confidence != null && latitude != 0.0 && longitude != 0.0) {
            jsonObject.addProperty("confidence", confidence);
        } else {
            jsonObject.add("confidence", JsonNull.INSTANCE);
        }
    }

    protected static void writePolygonArea(Double area, final JsonObject jsonObject) {
        try {
            String formattedArea;
            if (area != null) {
                formattedArea = areaFormat.format(area);
                area = Double.valueOf(formattedArea);
                jsonObject.addProperty("area", area);
            } else {
                jsonObject.add("area", JsonNull.INSTANCE);
            }
        }  catch (NumberFormatException nfe) {
            jsonObject.add("area", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeScreeningAndPresentationIndicators(final Integer screeningAndPresentationIndicators, final JsonObject jsonObject) {
        if (screeningAndPresentationIndicators != null) {
            jsonObject.addProperty("screeningAndPresentationIndicators", screeningAndPresentationIndicators);
        } else {
            jsonObject.add("screeningAndPresentationIndicators", JsonNull.INSTANCE);
        }
    }

    protected static void writeScreeningAndPresentationIndicators(final Integer screeningAndPresentationIndicators, final Double latitude,
                                                                final Double longitude, final JsonObject jsonObject) {
        if (screeningAndPresentationIndicators != null && latitude != 0.0 && longitude != 0.0) {
            jsonObject.addProperty("screeningAndPresentationIndicators", screeningAndPresentationIndicators);
        } else {
            jsonObject.add("screeningAndPresentationIndicators", JsonNull.INSTANCE);
        }
    }

    protected static void writeCurrentLocationRetrieved(final Boolean currentLocationRetrieved, final JsonObject jsonObject) {
        if (currentLocationRetrieved != null) {
            jsonObject.addProperty("currentLocationRetrieved", currentLocationRetrieved);
        } else {
            jsonObject.add("currentLocationRetrieved", JsonNull.INSTANCE);
        }
    }

    protected static void writeMmeName(final String mmeName, final JsonObject jsonObject) {
        if (mmeName != null) {
            jsonObject.addProperty("mmeName", mmeName);
        } else {
            jsonObject.add("mmeName", JsonNull.INSTANCE);
        }
    }

    protected static void writeMmeRealm(final String mmeRealm, final JsonObject jsonObject) {
        if (mmeRealm != null) {
            jsonObject.addProperty("mmeRealm", mmeRealm);
        } else {
            jsonObject.add("mmeRealm", JsonNull.INSTANCE);
        }
    }

    protected static void writeRNCId(final Long rncId, final JsonObject jsonObject) {
        if (rncId != null) {
            jsonObject.addProperty("rncId", rncId);
        } else {
            jsonObject.add("rncId", JsonNull.INSTANCE);
        }
    }

    protected static void writeEUtranEci(final Long eci, final JsonObject jsonObject) {
        if (eci != null) {
            jsonObject.addProperty("eci", eci);
        } else {
            jsonObject.add("eci", JsonNull.INSTANCE);
        }
    }

    protected static void writeENBId(final Long eNBId, final JsonObject jsonObject) {
        if (eNBId != null) {
            jsonObject.addProperty("eNBId", eNBId);
        } else {
            jsonObject.add("eNBId", JsonNull.INSTANCE);
        }
    }

    protected static void writeEUtranCellId(final Integer eUtranCellId, final JsonObject jsonObject) {
        if (eUtranCellId != null) {
            jsonObject.addProperty("ci", eUtranCellId);
        } else {
            jsonObject.add("ci", JsonNull.INSTANCE);
        }
    }

    protected static void writeNrCellId(final Long nci, final JsonObject jsonObject) {
        if (nci != null) {
            jsonObject.addProperty("nci", nci);
        } else {
            jsonObject.add("nci", JsonNull.INSTANCE);
        }
    }

    protected static void writeTrackingAreaCode(final Integer trackingAreaCode, final JsonObject jsonObject) {
        if (trackingAreaCode != null) {
            jsonObject.addProperty("tac", trackingAreaCode);
        } else {
            jsonObject.add("tac", JsonNull.INSTANCE);
        }
    }

    protected static void writeSgsnNumber(final String sgsnNumberAddress, final JsonObject jsonObject) {
        Long sgsnNumber = null;
        try {
            if (sgsnNumberAddress != null) {
                if (!sgsnNumberAddress.equalsIgnoreCase(""))
                    sgsnNumber = Long.valueOf(sgsnNumberAddress);
            }
            if (sgsnNumber != null) {
                jsonObject.addProperty("sgsnNumber", sgsnNumber);
            } else {
                jsonObject.add("sgsnNumber", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("sgsnNumber", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeLsaId(final String lsaId, final JsonObject jsonObject) {
        if (lsaId != null) {
            jsonObject.addProperty("lsaId", lsaId);
        } else {
            jsonObject.add("lsaId", JsonNull.INSTANCE);
        }
    }

    protected static void writeLsaLSB(final Boolean lsaLSB, final JsonObject jsonObject) {
        String lsaType;
        if (lsaLSB != null) {
            if (lsaLSB == false)
                lsaType = "PLMN";
            else
                lsaType = "Universal";
            jsonObject.addProperty("lsaIdType", lsaType);
        } else {
            jsonObject.add("lsaIdType", JsonNull.INSTANCE);
        }
    }

    protected static void writeRoutingAreaCode(final Integer routingAreaCode, final JsonObject jsonObject) {
        if (routingAreaCode != null) {
            jsonObject.addProperty("rac", routingAreaCode);
        } else {
            jsonObject.add("rac", JsonNull.INSTANCE);
        }
    }

    protected static void writeMnpStatus(final Integer mnpStatusCode, final JsonObject jsonObject) {
        String mnpStatus;
        if (mnpStatusCode != null) {
            switch (mnpStatusCode) {
                case 0:
                    mnpStatus = "notKnownToBePorted";
                    break;
                case 1:
                    mnpStatus = "ownNumberPortedOut";
                    break;
                case 2:
                    mnpStatus = "foreignNumberPortedToForeignNetwork";
                    break;
                case 4:
                    mnpStatus = "ownNumberNotPortedOut";
                    break;
                case 5:
                    mnpStatus = "notKnownToBePorted";
                    break;
                default:
                    mnpStatus = null;
                    break;
            }
            jsonObject.addProperty("mnpStatus", mnpStatus);
        } else {
            jsonObject.add("mnpStatus", JsonNull.INSTANCE);
        }
    }

    protected static void writeMnpImsi(final String mnpImsiDigits, final JsonObject jsonObject) {
        Long mnpImsi = null;
        try {
            if (mnpImsiDigits != null) {
                if (!mnpImsiDigits.equalsIgnoreCase(""))
                    mnpImsi = Long.valueOf(mnpImsiDigits);
            }
            if (mnpImsi != null) {
                jsonObject.addProperty("mnpImsi", mnpImsi);
            } else {
                jsonObject.add("mnpImsi", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("mnpImsi", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeMnpMsisdn(final String mnpMsisdnAddress, final JsonObject jsonObject) {
        Long mnpMsisdn = null;
        try {
            if (mnpMsisdnAddress != null) {
                if (!mnpMsisdnAddress.equalsIgnoreCase(""))
                    mnpMsisdn = Long.valueOf(mnpMsisdnAddress);
            }
            if (mnpMsisdn != null) {
                jsonObject.addProperty("mnpMsisdn", mnpMsisdn);
            } else {
                jsonObject.add("mnpMsisdn", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("mnpMsisdn", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeMnpRouteingNumber(final String mnpRouteingNumber, final JsonObject jsonObject) {
        if (mnpRouteingNumber != null) {
            jsonObject.addProperty("mnpRouteingNumber", mnpRouteingNumber);
        } else {
            jsonObject.add("mnpRouteingNumber", JsonNull.INSTANCE);
        }
    }

    protected static void writeImsi(final String imsiDigits, final JsonObject jsonObject) {
        Long imsi = null;
        if (imsiDigits != null) {
            if (!imsiDigits.equalsIgnoreCase(""))
                imsi = Long.valueOf(imsiDigits);
        }
        if (imsi != null) {
            jsonObject.addProperty("imsi", imsi);
        } else {
            jsonObject.add("imsi", JsonNull.INSTANCE);
        }
    }

    protected static void writeLmsi(final String lmsi, final JsonObject jsonObject) {
        try {
            if (lmsi != null) {
                jsonObject.addProperty("lmsi", lmsi);
            } else {
                jsonObject.add("lmsi", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("lmsi", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeMsisdn(final String msisdnAddress, final JsonObject jsonObject) {
        Long msisdn = null;
        try {
            if (msisdnAddress != null) {
                if (!msisdnAddress.equalsIgnoreCase(""))
                    msisdn = Long.valueOf(msisdnAddress);
            }
            if (msisdn != null) {
                jsonObject.addProperty("msisdn", msisdn);
            } else {
                jsonObject.add("msisdn", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("msisdn", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeSubscriberIdentity(final String subscriberIdentityAddress, final JsonObject jsonObject) {
        Long subscriberIdentity = null;
        try {
            if (subscriberIdentityAddress != null) {
                if (!subscriberIdentityAddress.equalsIgnoreCase(""))
                    subscriberIdentity = Long.valueOf(subscriberIdentityAddress);
            }
            if (subscriberIdentity != null) {
                jsonObject.addProperty("subscriberIdentity", subscriberIdentity);
            } else {
                jsonObject.add("subscriberIdentity", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("subscriberIdentity", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeNetworkNodeNumber(final String networkNodeNumberAddress, final JsonObject jsonObject) {
        Long networkNodeNumber = null;
        try {
            if (networkNodeNumberAddress != null) {
                if (!networkNodeNumberAddress.equalsIgnoreCase(""))
                    networkNodeNumber = Long.valueOf(networkNodeNumberAddress);
            }
            if (networkNodeNumber != null) {
                jsonObject.addProperty("networkNodeNumber", networkNodeNumber);
            } else {
                jsonObject.add("networkNodeNumber", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("networkNodeNumber", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeAdditionalNetworkNodeNumber(final String additionalNetworkNodeNumberAddress, final JsonObject jsonObject) {
        Long additionalNetworkNodeNumber = null;
        try {
            if (additionalNetworkNodeNumberAddress != null) {
                if (!additionalNetworkNodeNumberAddress.equalsIgnoreCase(""))
                    additionalNetworkNodeNumber = Long.valueOf(additionalNetworkNodeNumberAddress);
            }
            if (additionalNetworkNodeNumber != null) {
                jsonObject.addProperty("additionalNetworkNodeNumber", additionalNetworkNodeNumber);
            } else {
                jsonObject.add("additionalNetworkNodeNumber", JsonNull.INSTANCE);
            }
        } catch (NumberFormatException nfe) {
            jsonObject.add("additionalNetworkNodeNumber", JsonNull.INSTANCE);
            nfe.printStackTrace();
        }
    }

    protected static void writeOriginHostName(final String originHost, final JsonObject jsonObject) {
        if (originHost != null) {
            jsonObject.addProperty("hostName", originHost);
        } else {
            jsonObject.add("hostName", JsonNull.INSTANCE);
        }
    }

    protected static void writeOriginHostRealm(final String originRealm, final JsonObject jsonObject) {
        if (originRealm != null) {
            jsonObject.addProperty("hostRealm", originRealm);
        } else {
            jsonObject.add("hostRealm", JsonNull.INSTANCE);
        }
    }

    protected static void writeGprsNodeIndicator(final Boolean gprsNodeIndicator, final JsonObject jsonObject) {
        if (gprsNodeIndicator != null) {
            jsonObject.addProperty("gprsNodeIndicator", gprsNodeIndicator);
        } else {
            jsonObject.add("gprsNodeIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeSgsnName(final String sgsnName, final JsonObject jsonObject) {
        if (sgsnName != null) {
            jsonObject.addProperty("sgsnName", sgsnName);
        } else {
            jsonObject.add("sgsnName", JsonNull.INSTANCE);
        }
    }

    protected static void writeSgsnRealm(final String sgsnRealm, final JsonObject jsonObject) {
        if (sgsnRealm != null) {
            jsonObject.addProperty("sgsnRealm", sgsnRealm);
        } else {
            jsonObject.add("sgsnRealm", JsonNull.INSTANCE);
        }
    }

    protected static void write3gppAaaServerName(final String tgppAaaServerName, final JsonObject jsonObject) {
        if (tgppAaaServerName != null) {
            jsonObject.addProperty("3GPPAAAServerName", tgppAaaServerName);
        } else {
            jsonObject.add("3GPPAAAServerName", JsonNull.INSTANCE);
        }
    }

    protected static void writeAaaServerName(final String aaaServerName, final JsonObject jsonObject) {
        if (aaaServerName != null) {
            jsonObject.addProperty("aaaServerName", aaaServerName);
        } else {
            jsonObject.add("aaaServerName", JsonNull.INSTANCE);
        }
    }

    protected static void writeGmlcAddress(final String gmlcAddress, final JsonObject jsonObject) {
        if (gmlcAddress != null) {
            jsonObject.addProperty("gmlcAddress", gmlcAddress);
        } else {
            jsonObject.add("gmlcAddress", JsonNull.INSTANCE);
        }
    }

    protected static void writeHGmlcAddress(final String hGmlcAddress, final JsonObject jsonObject) {
        if (hGmlcAddress != null) {
            jsonObject.addProperty("hGmlcAddress", hGmlcAddress);
        } else {
            jsonObject.add("hGmlcAddress", JsonNull.INSTANCE);
        }
    }

    protected static void writeVGmlcAddress(final String vGmlcAddress, final JsonObject jsonObject) {
        if (vGmlcAddress != null) {
            jsonObject.addProperty("vGmlcAddress", vGmlcAddress);
        } else {
            jsonObject.add("vGmlcAddress", JsonNull.INSTANCE);
        }
    }

    protected static void writePprAddress(final String pprAddress, final JsonObject jsonObject) {
        if (pprAddress != null) {
            jsonObject.addProperty("pprAddress", pprAddress);
        } else {
            jsonObject.add("pprAddress", JsonNull.INSTANCE);
        }
    }

    protected static void writeAgeOfLocationEstimate(final Integer ageOfLocationEstimate, final JsonObject jsonObject) {
        if (ageOfLocationEstimate != null) {
            jsonObject.addProperty("ageOfLocationEstimate", ageOfLocationEstimate);
        } else {
            jsonObject.add("ageOfLocationEstimate", JsonNull.INSTANCE);
        }
    }

    protected static void writeAgeOfLocationEstimate(final Long ageOfLocationEstimate, final JsonObject jsonObject) {
        if (ageOfLocationEstimate != null) {
            jsonObject.addProperty("ageOfLocationEstimate", ageOfLocationEstimate);
        } else {
            jsonObject.add("ageOfLocationEstimate", JsonNull.INSTANCE);
        }
    }

    protected static void writeHorizontalSpeed(final Integer horizontalSpeed, final JsonObject jsonObject) {
        if (horizontalSpeed != null) {
            jsonObject.addProperty("horizontalSpeed", horizontalSpeed);
        } else {
            jsonObject.add("horizontalSpeed", JsonNull.INSTANCE);
        }
    }

    protected static void writeBearing(final Integer bearing, final JsonObject jsonObject) {
        if (bearing != null) {
            jsonObject.addProperty("bearing", bearing);
        } else {
            jsonObject.add("bearing", JsonNull.INSTANCE);
        }
    }

    protected static void writeVerticalSpeed(final Integer verticalSpeed, final JsonObject jsonObject) {
        if (verticalSpeed != null) {
            jsonObject.addProperty("verticalSpeed", verticalSpeed);
        } else {
            jsonObject.add("verticalSpeed", JsonNull.INSTANCE);
        }
    }

    protected static void writeUncertaintyHorizontalSpeed(final Integer uncertaintyHorizontalSpeed, final JsonObject jsonObject) {
        if (uncertaintyHorizontalSpeed != null) {
            jsonObject.addProperty("uncertaintyHorizontalSpeed", uncertaintyHorizontalSpeed);
        } else {
            jsonObject.add("uncertaintyHorizontalSpeed", JsonNull.INSTANCE);
        }
    }

    protected static void writeUncertaintyVerticalSpeed(final Integer uncertaintyVerticalSpeed, final JsonObject jsonObject) {
        if (uncertaintyVerticalSpeed != null) {
            jsonObject.addProperty("uncertaintyVerticalSpeed", uncertaintyVerticalSpeed);
        } else {
            jsonObject.add("uncertaintyVerticalSpeed", JsonNull.INSTANCE);
        }
    }

    protected static void writeVelocityType(final String velocityType, final JsonObject jsonObject) {
        if (velocityType != null) {
            jsonObject.addProperty("velocityType", velocityType);
        } else {
            jsonObject.add("velocityType", JsonNull.INSTANCE);
        }
    }

    protected static void writeDeferredMTLRresponseIndicator(final Boolean deferredMTLRresponseIndicator, final JsonObject jsonObject) {
        if (deferredMTLRresponseIndicator != null) {
            jsonObject.addProperty("deferredMTLRresponseIndicator", deferredMTLRresponseIndicator);
        } else {
            jsonObject.add("deferredMTLRresponseIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeMoLrShortCircuitIndicator(final Boolean moLrShortCircuitIndicator, final JsonObject jsonObject) {
        if (moLrShortCircuitIndicator != null) {
            jsonObject.addProperty("moLrShortCircuitIndicator", moLrShortCircuitIndicator);
        } else {
            jsonObject.add("moLrShortCircuitIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeAccuracyFulfilmentIndicator(final Integer accuracyFulfilmentIndicatorCode, final JsonObject jsonObject) {
        String accuracyFulfilmentIndicator = null;
        if (accuracyFulfilmentIndicatorCode != null) {
            switch (accuracyFulfilmentIndicatorCode) {
                case 0:
                    accuracyFulfilmentIndicator = "REQUESTED_ACCURACY_FULFILLED";
                    break;
                case 1:
                    accuracyFulfilmentIndicator = "REQUESTED_ACCURACY_NOT_FULFILLED";
                    break;
                default:
                    accuracyFulfilmentIndicator = null;
                    break;
            }
        }
        if (accuracyFulfilmentIndicator != null) {
            jsonObject.addProperty("accuracyFulfilmentIndicator", accuracyFulfilmentIndicator);
        } else {
            jsonObject.add("accuracyFulfilmentIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeServiceAreaCode(final Integer serviceAreaCode, final JsonObject jsonObject) {
        if (serviceAreaCode != null) {
            jsonObject.addProperty("sac", serviceAreaCode);
        } else {
            jsonObject.add("sac", JsonNull.INSTANCE);
        }
    }

    protected static void writeCellPortionId(final Long cellPortionId, final JsonObject jsonObject) {
        if (cellPortionId != null) {
            jsonObject.addProperty("cellPortionId", cellPortionId);
        } else {
            jsonObject.add("cellPortionId", JsonNull.INSTANCE);
        }
    }

    protected static void writeCivicAddress(final String civicAddress, final JsonObject jsonObject) {
        if (civicAddress != null) {
            jsonObject.addProperty("civicAddress", civicAddress);
        } else {
            jsonObject.add("civicAddress", JsonNull.INSTANCE);
        }
    }

    protected static void writeBarometricPressure(final Long barometricPressure, final JsonObject jsonObject) {
        if (barometricPressure != null) {
            jsonObject.addProperty("barometricPressure", barometricPressure);
        } else {
            jsonObject.add("barometricPressure", JsonNull.INSTANCE);
        }
    }

    protected static void writeGeranPositioningData(final String geranPositioningData, final JsonObject jsonObject) {
        if (geranPositioningData != null) {
            jsonObject.addProperty("geranPositioningData", geranPositioningData);
        } else {
            jsonObject.add("geranPositioningData", JsonNull.INSTANCE);
        }
    }

    protected static void writeGeranGanssPositioningData(final String geranGanssPositioningData, final JsonObject jsonObject) {
        if (geranGanssPositioningData != null) {
            jsonObject.addProperty("geranGanssPositioningData", geranGanssPositioningData);
        } else {
            jsonObject.add("geranGanssPositioningData", JsonNull.INSTANCE);
        }
    }

    protected static void writeUtranPositioningData(final String utranPositioningData, final JsonObject jsonObject) {
        if (utranPositioningData != null) {
            jsonObject.addProperty("utranPositioningData", utranPositioningData);
        } else {
            jsonObject.add("utranPositioningData", JsonNull.INSTANCE);
        }
    }

    protected static void writeUtranGanssPositioningData(final String utranGanssPositioningData, final JsonObject jsonObject) {
        if (utranGanssPositioningData != null) {
            jsonObject.addProperty("utranGanssPositioningData", utranGanssPositioningData);
        } else {
            jsonObject.add("utranGanssPositioningData", JsonNull.INSTANCE);
        }
    }

    protected static void writeUtranAdditionalPositioningData(final String utranAdditionalPositioningData, final JsonObject jsonObject) {
        if (utranAdditionalPositioningData != null) {
            jsonObject.addProperty("utranAdditionalPositioningData", utranAdditionalPositioningData);
        } else {
            jsonObject.add("utranAdditionalPositioningData", JsonNull.INSTANCE);
        }
    }

    protected static void writeEUtranPositioningData(final String eUtranPositioningData, final JsonObject jsonObject) {
        if (eUtranPositioningData != null) {
            jsonObject.addProperty("eUtranPositioningData", eUtranPositioningData);
        } else {
            jsonObject.add("eUtranPositioningData", JsonNull.INSTANCE);
        }
    }

    protected static void writeIMSPublicIdentity(final String imsPublicIdentity, final JsonObject jsonObject) {
        if (imsPublicIdentity != null) {
            jsonObject.addProperty("imsPublicIdentity", imsPublicIdentity);
        } else {
            jsonObject.add("imsPublicIdentity", JsonNull.INSTANCE);
        }
    }

    protected static void writeCSGId(final String csgId, final JsonObject jsonObject) {
        if (csgId != null) {
            jsonObject.addProperty("csgId", csgId);
        } else {
            jsonObject.add("csgId", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSServiceTypeID(final Integer lcsServiceTypeID, final JsonObject jsonObject) {
        if (lcsServiceTypeID != null) {
            jsonObject.addProperty("lcsServiceTypeID", lcsServiceTypeID);
        } else {
            jsonObject.add("lcsServiceTypeID", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSServiceTypeID(final Long lcsServiceTypeID, final JsonObject jsonObject) {
        if (lcsServiceTypeID != null) {
            jsonObject.addProperty("lcsServiceTypeID", lcsServiceTypeID);
        } else {
            jsonObject.add("lcsServiceTypeID", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientIDType(final Integer lcsClientIDType, final JsonObject jsonObject) {
        if (lcsClientIDType != null) {
            jsonObject.addProperty("lcsClientType", lcsClientIDType);
        } else {
            jsonObject.add("lcsClientType", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientExternalID(final String lcsClientExternalID, final JsonObject jsonObject) {
        if (lcsClientExternalID != null) {
            jsonObject.addProperty("lcsClientExternalID", lcsClientExternalID);
        } else {
            jsonObject.add("lcsClientExternalID", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientInternalID(final Integer lcsClientInternalID, final JsonObject jsonObject) {
        if (lcsClientInternalID != null) {
            jsonObject.addProperty("lcsClientInternalID", lcsClientInternalID);
        } else {
            jsonObject.add("lcsClientInternalID", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientName(final String lcsClientName, final JsonObject jsonObject) {
        if (lcsClientName != null) {
            jsonObject.addProperty("lcsClientName", lcsClientName);
        } else {
            jsonObject.add("lcsClientName", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSEPSClientNameString(final String lcsEpsClientNameString, final JsonObject jsonObject) {
        if (lcsEpsClientNameString != null) {
            jsonObject.addProperty("lcsEPSClientNameString", lcsEpsClientNameString);
        } else {
            jsonObject.add("lcsEPSClientNameString", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientDataCodingScheme(final Integer lcsClientDataCodingScheme, final JsonObject jsonObject) {
        if (lcsClientDataCodingScheme != null) {
            jsonObject.addProperty("lcsClientDataCodingScheme", lcsClientDataCodingScheme);
        } else {
            jsonObject.add("lcsClientDataCodingScheme", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientDataFormatIndicator(final Integer lcsClientDataFormatIndicator, final JsonObject jsonObject) {
        if (lcsClientDataFormatIndicator != null) {
            jsonObject.addProperty("lcsClientDataFormatIndicator", lcsClientDataFormatIndicator);
        } else {
            jsonObject.add("lcsClientDataFormatIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSEPSClientFormatIndicator(final Integer lcsEPSClientDataFormatIndicator, final JsonObject jsonObject) {
        if (lcsEPSClientDataFormatIndicator != null) {
            jsonObject.addProperty("lcsEPSClientDataFormatIndicator", lcsEPSClientDataFormatIndicator);
        } else {
            jsonObject.add("lcsEPSClientDataFormatIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientAPN(final String lcsClientAPN, final JsonObject jsonObject) {
        if (lcsClientAPN != null) {
            jsonObject.addProperty("lcsClientAPN", lcsClientAPN);
        } else {
            jsonObject.add("lcsClientAPN", JsonNull.INSTANCE);
        }
    }

    protected static void writeRequestorIdEncodedString(final String requestorIdEncodedString, final JsonObject jsonObject) {
        if (requestorIdEncodedString != null) {
            jsonObject.addProperty("requestorIdEncodedString", requestorIdEncodedString);
        } else {
            jsonObject.add("requestorIdEncodedString", JsonNull.INSTANCE);
        }
    }

    protected static void writeRequestorIdDataCodingScheme(final Integer requestorIdDataCodingScheme, final JsonObject jsonObject) {
        if (requestorIdDataCodingScheme != null) {
            jsonObject.addProperty("requestorIdDataCodingScheme", requestorIdDataCodingScheme);
        } else {
            jsonObject.add("requestorIdDataCodingScheme", JsonNull.INSTANCE);
        }
    }

    protected static void writeRequestorIdDataFormatIndicator(final Integer requestorIdDataFormatIndicator, final JsonObject jsonObject) {
        if (requestorIdDataFormatIndicator != null) {
            jsonObject.addProperty("requestorIdDataFormatIndicator", requestorIdDataFormatIndicator);
        } else {
            jsonObject.add("requestorIdDataFormatIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSClientDialedByMS(final String lcsClientDialedByMS, final JsonObject jsonObject) {
        if (lcsClientDialedByMS != null) {
            jsonObject.addProperty("lcsClientDialedByMS", lcsClientDialedByMS);
        } else {
            jsonObject.add("lcsClientDialedByMS", JsonNull.INSTANCE);
        }
    }

    protected static void writePseudonymIndicator(final Boolean pseudonymIndicator, final JsonObject jsonObject) {
        if (pseudonymIndicator != null) {
            if (pseudonymIndicator)
                jsonObject.addProperty("pseudonymIndicator", "PSEUDONYM_REQUESTED");
            else
                jsonObject.addProperty("pseudonymIndicator", "PSEUDONYM_NOT_REQUESTED");
        } else {
            jsonObject.add("pseudonymIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeLCSEvent(final Integer lcsEventCode, final JsonObject jsonObject) {
        String lcsEvent = null;
        if (lcsEventCode != null) {
            switch (lcsEventCode) {
                case 0:
                    lcsEvent = "EMERGENCY_CALL_ORIGINATION";
                    break;
                case 1:
                    lcsEvent = "EMERGENCY_CALL_RELEASE";
                    break;
                case 2:
                    lcsEvent = "MO_LOCATION_REPORT";
                    break;
                case 3:
                    lcsEvent = "DEFERRED_MT_LR_RESPONSE";
                    break;
                default:
                    lcsEvent = null;
                    break;
            }
        }
        if (lcsEvent != null) {
            jsonObject.addProperty("lcsEvent", lcsEvent);
        } else {
            jsonObject.add("lcsEvent", JsonNull.INSTANCE);
        }
    }

    protected static void writeReportingAmount(final Integer reportingAmount, final JsonObject jsonObject) {
        if (reportingAmount != null) {
            jsonObject.addProperty("reportingAmount", reportingAmount);
        } else {
            jsonObject.add("reportingAmount", JsonNull.INSTANCE);
        }
    }

    protected static void writeReportingAmount(final Long reportingAmount, final JsonObject jsonObject) {
        if (reportingAmount != null) {
            jsonObject.addProperty("reportingAmount", reportingAmount);
        } else {
            jsonObject.add("reportingAmount", JsonNull.INSTANCE);
        }
    }

    protected static void writeReportingInterval(final Integer reportingInterval, final JsonObject jsonObject) {
        if (reportingInterval != null) {
            jsonObject.addProperty("reportingInterval", reportingInterval);
        } else {
            jsonObject.add("reportingInterval", JsonNull.INSTANCE);
        }
    }

    protected static void writeReportingInterval(final Long reportingInterval, final JsonObject jsonObject) {
        if (reportingInterval != null) {
            jsonObject.addProperty("reportingInterval", reportingInterval);
        } else {
            jsonObject.add("reportingInterval", JsonNull.INSTANCE);
        }
    }

    protected static void writeSequenceNumber(final Integer sequenceNumber, final JsonObject jsonObject) {
        if (sequenceNumber != null) {
            jsonObject.addProperty("sequenceNumber", sequenceNumber);
        } else {
            jsonObject.add("sequenceNumber", JsonNull.INSTANCE);
        }
    }

    protected static void writeDeferredLocationEventType(final Boolean deferredEventLocationType, final Boolean entering, final Boolean inside, final Boolean leaving,
                                                       final Boolean msAvailable, final Boolean periodicLDR, final JsonObject jsonObject) {

        if (deferredEventLocationType != null) {
            if (entering) {
                jsonObject.addProperty("deferredLocationEventType", "ENTERING_INTO_AREA");
            } else if (inside) {
                jsonObject.addProperty("deferredLocationEventType", "BEING_INSIDE_AREA");
            } else if (leaving) {
                jsonObject.addProperty("deferredLocationEventType", "LEAVING_FROM_AREA");
            } else if (msAvailable) {
                jsonObject.addProperty("deferredLocationEventType", "MS_AVAILABLE");
            } else if (periodicLDR) {
                jsonObject.addProperty("deferredLocationEventType", "PERIODIC_LDR");
            } else {
                jsonObject.add("deferredLocationEventType", JsonNull.INSTANCE);
            }
        } else {
            jsonObject.add("deferredLocationEventType", JsonNull.INSTANCE);
        }
    }

    protected static void writeDeferredLocationType(final Long deferredLocationTypeValue, final JsonObject jsonObject) {
        String deferredLocationType = null;
        if (deferredLocationTypeValue != null) {
            Integer deferredLocationTypeCode = (int) (long) deferredLocationTypeValue;
            switch (deferredLocationTypeCode) {
                case 0:
                    deferredLocationType = "UE-AVAILABLE";
                    break;
                case 1:
                    deferredLocationType = "ENTERING_INTO_AREA";
                    break;
                case 2:
                    deferredLocationType = "LEAVING_FROM_AREA";
                    break;
                case 3:
                    deferredLocationType = "BEING_INSIDE_AREA";
                    break;
                case 4:
                    deferredLocationType = "PERIODIC_LDR";
                    break;
                case 5:
                    deferredLocationType = "MOTION_EVENT";
                    break;
                case 6:
                    deferredLocationType = "LDR_ACTIVATED";
                    break;
                case 7:
                    deferredLocationType = "MAXIMUM_INTERVAL_EXPIRATION";
                    break;
                default:
                    deferredLocationType = null;
                    break;
            }
        }
        if (deferredLocationType != null) {
            jsonObject.addProperty("deferredLocationType", deferredLocationType);
        } else {
            jsonObject.add("deferredLocationType", JsonNull.INSTANCE);
        }
    }

    protected static void writeSLRTerminationCause(final Integer terminationCauseCode, final JsonObject jsonObject) {
        String terminationCause = null;
        if (terminationCauseCode != null) {
            switch (terminationCauseCode) {
                case 0:
                    terminationCause = "NORMAL";
                    break;
                case 1:
                    terminationCause = "ERROR_UNDEFINED";
                    break;
                case 2:
                    terminationCause = "INTERNAL_TIMEOUT";
                    break;
                case 3:
                    terminationCause = "CONGESTION";
                    break;
                case 4:
                    terminationCause = "MT_LR_RESTART";
                    break;
                case 5:
                    terminationCause = "PRIVACY_VIOLATION";
                    break;
                case 6:
                    terminationCause = "SHAPE_OF_LOCATION_ESTIMATE_NOT_SUPPORTED";
                    break;
                case 7:
                    terminationCause = "SUBSCRIBER_TERMINATION";
                    break;
                case 8:
                    terminationCause = "UE_TERMINATION";
                    break;
                case 9:
                    terminationCause = "NETWORK_TERMINATION";
                    break;
                default:
                    terminationCause = null;
                    break;
            }
        }
        if (terminationCause != null) {
            jsonObject.addProperty("terminationCause", terminationCause);
        } else {
            jsonObject.add("terminationCause", JsonNull.INSTANCE);
        }
    }

    protected static void writeLRRTerminationCause(final Long terminationCauseValue, final JsonObject jsonObject) {
        String terminationCause = null;
        if (terminationCauseValue != null) {
            Integer terminationCauseCode = (int) (long) terminationCauseValue;
            switch (terminationCauseCode) {
                case 0:
                    terminationCause = "NORMAL";
                    break;
                case 1:
                    terminationCause = "ERROR_UNDEFINED";
                    break;
                case 2:
                    terminationCause = "INTERNAL_TIMEOUT";
                    break;
                case 3:
                    terminationCause = "CONGESTION";
                    break;
                case 4:
                    terminationCause = "MT_LR_RESTART";
                    break;
                case 5:
                    terminationCause = "PRIVACY_VIOLATION";
                    break;
                case 6:
                    terminationCause = "SHAPE_OF_LOCATION_ESTIMATE_NOT_SUPPORTED";
                    break;
                case 7:
                    terminationCause = "SUBSCRIBER_TERMINATION";
                    break;
                case 8:
                    terminationCause = "UE_TERMINATION";
                    break;
                case 9:
                    terminationCause = "NETWORK_TERMINATION";
                    break;
                default:
                    terminationCause = null;
                    break;
            }
        }
        if (terminationCause != null) {
            jsonObject.addProperty("terminationCause", terminationCause);
        } else {
            jsonObject.add("terminationCause", JsonNull.INSTANCE);
        }
    }

    protected static void writeSupportedLCSCapabilitySets(final Boolean supportedLCSCapabilitySets, final Boolean r98_99, final Boolean r4, final Boolean r5,
                                                        final Boolean r6, final Boolean r7, final JsonObject jsonObject) {
        if (supportedLCSCapabilitySets != null) {
            if (r98_99 || r4 || r5 || r6 || r7) {
                jsonObject.addProperty("RELEASE98_99", r98_99);
                jsonObject.addProperty("RELEASE4", r4);
                jsonObject.addProperty("RELEASE5", r5);
                jsonObject.addProperty("RELEASE6", r6);
                jsonObject.addProperty("RELEASE7", r7);
            } else {
                jsonObject.add("lcsCapabilitySets", JsonNull.INSTANCE);
            }
        } else {
            jsonObject.add("lcsCapabilitySets", JsonNull.INSTANCE);
        }
    }

    protected static void writeAdditionalLCSCapabilitySets(final Boolean addSupportedLCSCapabilitySets, final Boolean r98_99, final Boolean r4, final Boolean r5, final Boolean r6,
                                                         final Boolean r7, final JsonObject jsonObject) {
        if (addSupportedLCSCapabilitySets != null) {
            if (r98_99 || r4 || r5 || r6 || r7) {
                jsonObject.addProperty("RELEASE98_99", r98_99);
                jsonObject.addProperty("RELEASE4", r4);
                jsonObject.addProperty("RELEASE5", r5);
                jsonObject.addProperty("RELEASE6", r6);
                jsonObject.addProperty("RELEASE7", r7);
            } else {
                jsonObject.add("additionalLCSCapabilitySets", JsonNull.INSTANCE);
            }
        } else {
            jsonObject.add("additionalLCSCapabilitySets", JsonNull.INSTANCE);
        }

    }

    protected static void writeLCSCapabilitySets(final Long lcsCapabilitySetsCode, final JsonObject jsonObject) {
        String lcsCapabilitySets = null;
        if (lcsCapabilitySetsCode != null) {
            Integer lcsCapabilitySetsValue = (int) (long) lcsCapabilitySetsCode;
            switch (lcsCapabilitySetsValue) {
                case 0:
                    lcsCapabilitySets = "RELEASE98_99";
                    break;
                case 1:
                    lcsCapabilitySets = "RELEASE4";
                    break;
                case 2:
                    lcsCapabilitySets = "RELEASE5";
                    break;
                case 3:
                    lcsCapabilitySets = "RELEASE6";
                    break;
                case 4:
                    lcsCapabilitySets = "RELEASE7";
                    break;
                default:
                    lcsCapabilitySets = null;
                    break;
            }
        }
        if (lcsCapabilitySets != null) {
            jsonObject.addProperty("lcsCapabilitySets", lcsCapabilitySets);
        } else {
            jsonObject.add("lcsCapabilitySets", JsonNull.INSTANCE);
        }
    }

    protected static void writeLocationEvent(final Integer locationEventCode, final JsonObject jsonObject) {
        String locationEvent = null;
        if (locationEventCode != null) {
            switch (locationEventCode) {
                case 0:
                    locationEvent = "EMERGENCY_CALL_ORIGINATION";
                    break;
                case 1:
                    locationEvent = "EMERGENCY_CALL_RELEASE";
                    break;
                case 2:
                    locationEvent = "MO_LR";
                    break;
                case 3:
                    locationEvent = "EMERGENCY_CALL_HANDOVER";
                    break;
                case 4:
                    locationEvent = "DEFERRED_MT_LR_RESPONSE";
                    break;
                case 5:
                    locationEvent = "DEFERRED_MO_LR_TTTP_INITIATION";
                    break;
                case 6:
                    locationEvent = "DELAYED_LOCATION_REPORTING";
                    break;
                default:
                    locationEvent = null;
                    break;
            }
        }
        if (locationEvent != null) {
            jsonObject.addProperty("locationEvent", locationEvent);
        } else {
            jsonObject.add("locationEvent", JsonNull.INSTANCE);
        }
    }

    protected static void writeLcsQoSClass(final Integer lcsQoSClassCode, final JsonObject jsonObject) {
        String lcsQoSClass = null;
        if (lcsQoSClassCode != null) {
            switch (lcsQoSClassCode) {
                case 0:
                    lcsQoSClass = "ASSURED";
                    break;
                case 1:
                    lcsQoSClass = "BEST_EFFORT";
                    break;
                default:
                    lcsQoSClass = null;
                    break;
            }
        }
        if (lcsQoSClass != null) {
            jsonObject.addProperty("lcsQoSClass", lcsQoSClass);
        } else {
            jsonObject.add("lcsQoSClass", JsonNull.INSTANCE);
        }
    }

    protected static void writeLcsPseudonymIndicator(final Integer pseudonymIndicatorCode, final JsonObject jsonObject) {
        String pseudonymIndicator = null;
        if (pseudonymIndicatorCode != null) {
            switch (pseudonymIndicatorCode) {
                case 0:
                    pseudonymIndicator = "PSEUDONYM_NOT_REQUESTED";
                    break;
                case 1:
                    pseudonymIndicator = "PSEUDONYM_REQUESTED";
                    break;
                default:
                    pseudonymIndicator = null;
                    break;
            }
        }
        if (pseudonymIndicator != null) {
            jsonObject.addProperty("pseudonymIndicator", pseudonymIndicator);
        } else {
            jsonObject.add("pseudonymIndicator", JsonNull.INSTANCE);
        }
    }

    protected static void writeAmfAddress(final String amfAddress, final JsonObject jsonObject) {
        if (amfAddress != null) {
            jsonObject.addProperty("amfAddress", amfAddress);
        } else {
            jsonObject.add("amfAddress", JsonNull.INSTANCE);
        }
    }

    protected static void writeSmsfAddress(final String smsfAddress, final JsonObject jsonObject) {
        if (smsfAddress != null) {
            jsonObject.addProperty("smsfAddress", smsfAddress);
        } else {
            jsonObject.add("smsfAddress", JsonNull.INSTANCE);
        }
    }

    protected static void writeTimeZone(final String timeZone, final JsonObject jsonObject) {
        if (timeZone != null) {
            jsonObject.addProperty("timeZone", timeZone);
        } else {
            jsonObject.add("timeZone", JsonNull.INSTANCE);
        }
    }

    protected static void writeDaylightSavingTime(final Integer daylightSavingTime, final JsonObject jsonObject) {
        if (daylightSavingTime != null) {
            jsonObject.addProperty("daylightSavingTime", daylightSavingTime);
        } else {
            jsonObject.add("daylightSavingTime", JsonNull.INSTANCE);
        }
    }

    protected static void writeVisitedPlmnIdMcc(final Integer mcc, final JsonObject jsonObject) {
        if (mcc != null) {
            jsonObject.addProperty("mcc", mcc);
        } else {
            jsonObject.add("mcc", JsonNull.INSTANCE);
        }
    }

    protected static void writeVisitedPlmnIdMnc(final Integer mnc, final JsonObject jsonObject) {
        if (mnc != null) {
            jsonObject.addProperty("mnc", mnc);
        } else {
            jsonObject.add("mnc", JsonNull.INSTANCE);
        }
    }

    protected static void writeRatType(final Integer ratTypeCode, final JsonObject jsonObject) {
        String ratType = null;
        if (ratTypeCode != null) {
            switch (ratTypeCode) {
                case 0:
                    ratType = "WLAN";
                    break;
                case 1:
                    ratType = "VIRTUAL";
                    break;
                case 1000:
                    ratType = "UTRAN";
                    break;
                case 1001:
                    ratType = "GERAN";
                    break;
                case 1002:
                    ratType = "GAN";
                    break;
                case 1003:
                    ratType = "HSPA_EVOLUTION";
                    break;
                case 1004:
                    ratType = "EUTRAN";
                    break;
                case 1005:
                    ratType = "EUTRAN-NB-IoT";
                    break;
                case 1006:
                    ratType = "NR";
                    break;
                case 1007:
                    ratType = "LTE-M";
                    break;
                case 2000:
                    ratType = "CDMA2000_1X";
                    break;
                case 2001:
                    ratType = "HRPD";
                    break;
                case 2002:
                    ratType = "UMB";
                    break;
                case 2003:
                    ratType = "EHRPD";
                    break;
                default:
                    ratType = null;
                    break;
            }
        }
        if (ratType != null) {
            jsonObject.addProperty("ratType", ratType);
        } else {
            jsonObject.add("ratType", JsonNull.INSTANCE);
        }
    }

    // UTILS

    public static String bytesToHexString(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
