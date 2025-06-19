package org.mobicents.gmlc.slee.primitives;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;
import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.primitives.PlmnIdImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.EUtranCgiImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeographicalInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.TAIdImpl;

import java.io.IOException;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class LocationInformation5GSImpl extends SequenceBase implements LocationInformation5GS {

    public static final int _ID_nrCellGlobalIdentity = 0;
    public static final int _ID_eUtranCellGlobalIdentity = 1;
    public static final int _ID_trackingAreaIdentity = 2;
    public static final int _ID_geographicalInformation = 3;
    public static final int _ID_amfAddress = 4;
    public static final int _ID_smsfAddress = 5;
    public static final int _ID_currentLocationRetrieved = 6;
    public static final int _ID_ageOfLocationInformation = 7;
    public static final int _ID_visitedPlmnId = 8;
    public static final int _ID_timeZone = 9;
    public static final int _ID_daylightSavingTime = 10;
    public static final int _ID_ratType = 11;

    private static final String NR_CELL_GLOBAL_IDENTITY = "eUtranCellGlobalIdentity";
    private static final String E_UTRAN_CELL_GLOBAL_IDENTITY = "eUtranCellGlobalIdentity";
    private static final String TRACKING_AREA_IDENTITY = "trackingAreaIdentity";
    private static final String GEOGRAPHICAL_INFORMATION = "geographicalInformation";
    private static final String AMF_ADDRESS = "amfAddress";
    private static final String SMSF_ADDRESS = "smsfAddress";
    private static final String CURRENT_LOCATION_RETRIEVED = "currentLocationRetrieved";
    private static final String AGE_OF_LOCATION_INFORMATION = "ageOfLocationInformation";
    private static final String VISITED_PLMN_ID = "visitedPlmnId";
    private static final String TIME_ZONE = "timeZone";
    private static final String DAYLIGHT_SAVING_TIME = "daylightSavingTime";
    private static final String RAT_TYPE = "ratType";

    private NRCellGlobalId nrCellGlobalIdentity = null;
    private EUTRANCGI eUtranCellGlobalIdentity = null;
    private TAId trackingAreaIdentity = null;
    private GeographicalInformation geographicalInformation = null;
    private String amfAddress = null;
    private String smsfAddress = null;
    private boolean currentLocationRetrieved = false;
    private Integer ageOfLocationInformation = null;
    private PlmnId visitedPlmnId = null;
    private String timeZone = null;
    private Integer daylightSavingTime = null;
    private Integer ratType = null;

    /**
     *
     */
    public LocationInformation5GSImpl() {
        super("LocationInformation5GS");
    }

    /**
     * @param nrCellGlobalIdentity
     * @param eUtranCellGlobalIdentity
     * @param trackingAreaIdentity
     * @param amfAddress
     * @param smsfAddress
     * @param geographicalInformation
     * @param currentLocationRetrieved
     * @param ageOfLocationInformation
     * @param visitedPlmnId
     * @param timeZone
     * @param daylightSavingTime
     * @param ratType
     */
    public LocationInformation5GSImpl(NRCellGlobalId nrCellGlobalIdentity, EUTRANCGI eUtranCellGlobalIdentity, TAId trackingAreaIdentity,
                                      GeographicalInformation geographicalInformation, String amfAddress, String smsfAddress, boolean currentLocationRetrieved,
                                      Integer ageOfLocationInformation, PlmnId visitedPlmnId, String timeZone, Integer daylightSavingTime, Integer ratType) {
        super("LocationInformation5GS");
        this.nrCellGlobalIdentity = nrCellGlobalIdentity;
        this.eUtranCellGlobalIdentity = eUtranCellGlobalIdentity;
        this.trackingAreaIdentity = trackingAreaIdentity;
        this.geographicalInformation = geographicalInformation;
        this.amfAddress = amfAddress;
        this.smsfAddress = smsfAddress;
        this.currentLocationRetrieved = currentLocationRetrieved;
        this.ageOfLocationInformation = ageOfLocationInformation;
        this.visitedPlmnId = visitedPlmnId;
        this.timeZone = timeZone;
        this.daylightSavingTime = daylightSavingTime;
        this.ratType = ratType;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformation5GS#getNRCellGlobalId()
     */
    public NRCellGlobalId getNRCellGlobalIdentity() {
        return this.nrCellGlobalIdentity;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformation5GS#getEUtranCellGlobalIdentity()
     */
    public EUTRANCGI getEUtranCellGlobalIdentity() {
        return this.eUtranCellGlobalIdentity;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformation5GS#getTrackingAreaIdentity()
     */
    public TAId getTrackingAreaIdentity() {
        return this.trackingAreaIdentity;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformation5GS#getGeographicalInformation()
     */
    public GeographicalInformation getGeographicalInformation() {
        return this.geographicalInformation;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformationEPS#getAMFAddress()
     */
    public String getAMFAddress() {
        return this.amfAddress;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformationEPS#getSMSFAddress()
     */
    public String getSMSFAddress() {
        return this.smsfAddress;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformation5GS#getCurrentLocationRetrieved()
     */
    public boolean getCurrentLocationRetrieved() {
        return this.currentLocationRetrieved;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformationEPS#getAgeOfLocationInformation()
     */
    public Integer getAgeOfLocationInformation() {
        return this.ageOfLocationInformation;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformationEPS#getVisitedPlmnId()
     */
    public PlmnId getVisitedPlmnId() {
        return this.visitedPlmnId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformationEPS#getTimeZone()
     */
    public String getTimeZone() {
        return this.timeZone;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformationEPS#getDaylightSavingTime()
     */
    public Integer getDaylightSavingTime() {
        return this.daylightSavingTime;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.gmlc.slee.primitives.LocationInformationEPS#getRatType()
     */
    public Integer getRatType() {
        return this.ratType;
    }

    protected void _decode(AsnInputStream ansIS, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.nrCellGlobalIdentity = null;
        this.eUtranCellGlobalIdentity = null;
        this.trackingAreaIdentity = null;
        this.geographicalInformation = null;
        this.amfAddress = null;
        this.smsfAddress = null;
        this.currentLocationRetrieved = false;
        this.ageOfLocationInformation = null;
        this.visitedPlmnId = null;
        this.timeZone = null;
        this.daylightSavingTime = null;
        this.ratType = null;

        AsnInputStream ais = ansIS.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_nrCellGlobalIdentity:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " nrCellGlobalIdentity: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        this.nrCellGlobalIdentity = new NRCellGlobalIdImpl();
                        ((NRCellGlobalIdImpl) this.nrCellGlobalIdentity).decodeAll(ais);
                        break;
                    case _ID_eUtranCellGlobalIdentity:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " eUtranCellGlobalIdentity: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        this.eUtranCellGlobalIdentity = new EUTRANCGIImpl();
                        ((EUtranCgiImpl) this.eUtranCellGlobalIdentity).decodeAll(ais);
                        break;
                    case _ID_trackingAreaIdentity:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " trackingAreaIdentity: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        this.trackingAreaIdentity = new TAIdImpl();
                        ((TAIdImpl) this.trackingAreaIdentity).decodeAll(ais);
                        break;
                    case _ID_geographicalInformation:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " geographicalInformation: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        this.geographicalInformation = new GeographicalInformationImpl();
                        ((GeographicalInformationImpl) this.geographicalInformation).decodeAll(ais);
                        break;
                    case _ID_amfAddress:
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " amfAddress: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                        }
                        this.amfAddress = ais.readUTF8String();
                        break;
                    case _ID_smsfAddress:
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " smsfAddress: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                        }
                        this.smsfAddress = ais.readUTF8String();
                        break;
                    case _ID_currentLocationRetrieved:
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException(
                                "Error while decoding LocationInformation: Parameter [currentLocationRetrieved [8] NULL ] not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        ais.readNull();
                        this.currentLocationRetrieved = true;
                        break;
                    case _ID_ageOfLocationInformation:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " ageOfLocationInformation: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        this.ageOfLocationInformation = (int) ais.readInteger();
                        break;
                    case _ID_visitedPlmnId:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " visitedPlmnId: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        this.visitedPlmnId = new PlmnIdImpl();
                        ((PlmnIdImpl) this.visitedPlmnId).decodeAll(ais);
                        break;
                    case _ID_timeZone:
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " timeZone: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                        }
                        this.timeZone = ais.readUTF8String();
                        break;
                    case _ID_daylightSavingTime:
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " daylightSavingTime: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                        }
                        this.daylightSavingTime = (int) ais.readInteger();
                        break;
                    case _ID_ratType:
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + " ratType: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                        }
                        this.ratType = (int) ais.readInteger();
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOs) throws MAPException {
        try {

            if (this.nrCellGlobalIdentity != null)
                ((NRCellGlobalIdImpl) this.nrCellGlobalIdentity).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _ID_nrCellGlobalIdentity);

            if (this.eUtranCellGlobalIdentity != null)
                ((EUtranCgiImpl) this.eUtranCellGlobalIdentity).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _ID_eUtranCellGlobalIdentity);

            if (this.trackingAreaIdentity != null) {
                ((TAIdImpl) this.trackingAreaIdentity).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _ID_trackingAreaIdentity);
            }

            if (this.amfAddress != null)
                asnOs.writeStringUTF8(Tag.CLASS_CONTEXT_SPECIFIC, _ID_amfAddress, this.amfAddress);

            if (this.smsfAddress != null)
                asnOs.writeStringUTF8(Tag.CLASS_CONTEXT_SPECIFIC, _ID_smsfAddress, this.smsfAddress);

            if (this.geographicalInformation != null)
                ((GeographicalInformationImpl) this.geographicalInformation).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _ID_geographicalInformation);

            if (this.currentLocationRetrieved) {
                try {
                    asnOs.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_currentLocationRetrieved);
                } catch (IOException e) {
                    throw new MAPException(
                        "Error while encoding LocationInformation the optional parameter currentLocationRetrieved encoding failed ",
                        e);
                } catch (AsnException e) {
                    throw new MAPException(
                        "Error while encoding LocationInformation the optional parameter currentLocationRetrieved encoding failed ",
                        e);
                }
            }

            if (this.ageOfLocationInformation != null)
                asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_ageOfLocationInformation, this.ageOfLocationInformation);

            if (this.visitedPlmnId != null) {
                ((PlmnIdImpl) this.visitedPlmnId).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _ID_visitedPlmnId);
            }

            if (this.timeZone != null)
                asnOs.writeStringUTF8(Tag.CLASS_CONTEXT_SPECIFIC, _ID_timeZone, this.timeZone);

            if (this.daylightSavingTime != null)
                asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_daylightSavingTime, this.daylightSavingTime);

            if (this.ratType != null)
                asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_ratType, this.ratType);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.nrCellGlobalIdentity != null) {
            sb.append("nrCellGlobalIdentity=");
            sb.append(this.nrCellGlobalIdentity);
        }

        if (this.eUtranCellGlobalIdentity != null) {
            sb.append(", eUtranCellGlobalIdentity=");
            sb.append(this.eUtranCellGlobalIdentity);
        }

        if (this.trackingAreaIdentity != null) {
            sb.append(", trackingAreaIdentity=");
            sb.append(this.trackingAreaIdentity);
        }

        if (this.geographicalInformation != null) {
            sb.append(", geographicalInformation=");
            sb.append(this.geographicalInformation);
        }

        if (this.amfAddress != null) {
            sb.append(", amfAddress=");
            sb.append(this.amfAddress);
        }

        if (this.smsfAddress != null) {
            sb.append(", smsfAddress=");
            sb.append(this.smsfAddress);
        }

        if (currentLocationRetrieved) {
            sb.append(", currentLocationRetrieved");
        }

        if (this.ageOfLocationInformation != null) {
            sb.append(", ageOfLocationInformation=");
            sb.append(this.ageOfLocationInformation);
        }

        if (this.visitedPlmnId != null) {
            sb.append(", visitedPlmnId=");
            sb.append(this.visitedPlmnId);
        }

        if (this.timeZone != null) {
            sb.append(", timeZone=");
            sb.append(this.timeZone);
        }

        if (this.daylightSavingTime != null) {
            sb.append(", daylightSavingTime=");
            sb.append(this.daylightSavingTime);
        }

        if (this.ratType != null) {
            sb.append(", ratType=");
            sb.append(this.ratType);
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<LocationInformation5GSImpl> LOCATION_INFORMATION_5GS_XML = new XMLFormat<LocationInformation5GSImpl>(
        LocationInformation5GSImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, LocationInformation5GSImpl locationInformation5GS)
            throws XMLStreamException {
            locationInformation5GS.nrCellGlobalIdentity = xml.get(NR_CELL_GLOBAL_IDENTITY, NRCellGlobalIdImpl.class);
            locationInformation5GS.eUtranCellGlobalIdentity = xml.get(E_UTRAN_CELL_GLOBAL_IDENTITY, EUTRANCGIImpl.class);
            locationInformation5GS.trackingAreaIdentity = xml.get(TRACKING_AREA_IDENTITY, TAIdImpl.class);
            locationInformation5GS.geographicalInformation = xml.get(GEOGRAPHICAL_INFORMATION, GeographicalInformationImpl.class);
            locationInformation5GS.amfAddress = xml.get(AMF_ADDRESS, String.class);
            locationInformation5GS.smsfAddress = xml.get(SMSF_ADDRESS, String.class);
            Boolean bval = xml.get(CURRENT_LOCATION_RETRIEVED, Boolean.class);
            if (bval != null)
                locationInformation5GS.currentLocationRetrieved = bval;
            locationInformation5GS.ageOfLocationInformation = xml.get(AGE_OF_LOCATION_INFORMATION, Integer.class);
            locationInformation5GS.visitedPlmnId = xml.get(VISITED_PLMN_ID, PlmnIdImpl.class);
            locationInformation5GS.timeZone = xml.get(TIME_ZONE, String.class);
            locationInformation5GS.daylightSavingTime = xml.get(DAYLIGHT_SAVING_TIME, Integer.class);
            locationInformation5GS.ratType = xml.get(RAT_TYPE, Integer.class);
        }

        @Override
        public void write(LocationInformation5GSImpl locationInformation5GS, javolution.xml.XMLFormat.OutputElement xml)
            throws XMLStreamException {
            if (locationInformation5GS.nrCellGlobalIdentity != null) {
                xml.add((NRCellGlobalIdImpl) locationInformation5GS.nrCellGlobalIdentity, NR_CELL_GLOBAL_IDENTITY, NRCellGlobalIdImpl.class);
            }
            if (locationInformation5GS.eUtranCellGlobalIdentity != null) {
                xml.add((EUtranCgiImpl) locationInformation5GS.eUtranCellGlobalIdentity, E_UTRAN_CELL_GLOBAL_IDENTITY, EUtranCgiImpl.class);
            }
            if (locationInformation5GS.trackingAreaIdentity != null) {
                xml.add((TAIdImpl) locationInformation5GS.trackingAreaIdentity, TRACKING_AREA_IDENTITY, TAIdImpl.class);
            }
            if (locationInformation5GS.geographicalInformation != null) {
                xml.add((GeographicalInformationImpl) locationInformation5GS.geographicalInformation, GEOGRAPHICAL_INFORMATION, GeographicalInformationImpl.class);
            }
            if(locationInformation5GS.amfAddress != null) {
                xml.add(locationInformation5GS.amfAddress, AMF_ADDRESS, String.class);
            }
            if(locationInformation5GS.smsfAddress != null) {
                xml.add(locationInformation5GS.smsfAddress, SMSF_ADDRESS, String.class);
            }
            if (locationInformation5GS.currentLocationRetrieved) {
                xml.add(locationInformation5GS.currentLocationRetrieved, CURRENT_LOCATION_RETRIEVED, Boolean.class);
            }
            if (locationInformation5GS.ageOfLocationInformation != null) {
                xml.add(locationInformation5GS.ageOfLocationInformation, AGE_OF_LOCATION_INFORMATION, Integer.class);
            }
            if (locationInformation5GS.visitedPlmnId != null) {
                xml.add((PlmnIdImpl) locationInformation5GS.visitedPlmnId, VISITED_PLMN_ID, PlmnIdImpl.class);
            }
            if(locationInformation5GS.timeZone != null) {
                xml.add(locationInformation5GS.timeZone, TIME_ZONE, String.class);
            }
            if (locationInformation5GS.daylightSavingTime != null) {
                xml.add(locationInformation5GS.daylightSavingTime, DAYLIGHT_SAVING_TIME, Integer.class);
            }
            if (locationInformation5GS.ratType != null) {
                xml.add(locationInformation5GS.ratType, RAT_TYPE, Integer.class);
            }
        }
    };

}
