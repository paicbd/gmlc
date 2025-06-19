package org.mobicents.gmlc.slee.map;

import org.mobicents.gmlc.slee.diameter.AVPHandler;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;

import javax.slee.facilities.Tracer;
import java.text.DecimalFormat;

/**
 * Helper class to handle a MAP ATI response value and populate an MLP SLIA
 *
 * @author  <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class MapAtiResponseHelperForMLP {

    private Tracer logger;
    protected static final DecimalFormat coordinatesFormat = new DecimalFormat("#0.000000");
    protected static final DecimalFormat radiusFormat = new DecimalFormat("#0.00");

    private Integer mcc, mnc, lac, ci, sac, tac, rac, ratType;
    private Long eci;
    private Integer ageOfLocationInfo;
    private String vlrNumber, mscNumber, sgsnNumber, nnn, mmeName, subscriberState, imei;
    private String typeOfShape;
    private Double latitude, longitude, uncertainty, radius;
    private Boolean saiPresent = false;
    private Integer ciOrsac;

    public MapAtiResponseHelperForMLP() {
    }

    public void handleAtiResponseValues(AtiResponseValues atiResponseValues) {
        try {
            if (atiResponseValues != null) {

                if (atiResponseValues.isSaiPresent()) {
                    this.saiPresent = true;
                }

                if (atiResponseValues.getLocationInformation() != null) {
                    if (atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        if (atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                            this.mcc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                            this.mnc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                            this.lac = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                        } else if (atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            this.mcc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                            this.mnc = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                            this.lac = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                            ciOrsac = atiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                            if (this.saiPresent)
                                this.sac = ciOrsac;
                            else
                                this.ci = ciOrsac;
                        }
                    }
                    if (atiResponseValues.getLocationInformation().getGeographicalInformation() != null) {
                        if (atiResponseValues.getLocationInformation().getGeographicalInformation().getTypeOfShape() != null)
                            this.typeOfShape = atiResponseValues.getLocationInformation().getGeographicalInformation().getTypeOfShape().name();
                        this.latitude = atiResponseValues.getLocationInformation().getGeographicalInformation().getLatitude();
                        this.longitude = atiResponseValues.getLocationInformation().getGeographicalInformation().getLongitude();
                        this.uncertainty = atiResponseValues.getLocationInformation().getGeographicalInformation().getUncertainty();
                    } else if (atiResponseValues.getLocationInformation().getGeodeticInformation() != null) {
                        if (atiResponseValues.getLocationInformation().getGeodeticInformation().getTypeOfShape() != null)
                            this.typeOfShape = atiResponseValues.getLocationInformation().getGeodeticInformation().getTypeOfShape().name();
                        this.latitude = atiResponseValues.getLocationInformation().getGeodeticInformation().getLatitude();
                        this.longitude = atiResponseValues.getLocationInformation().getGeodeticInformation().getLongitude();
                        this.uncertainty = atiResponseValues.getLocationInformation().getGeodeticInformation().getUncertainty();
                    }
                    if (atiResponseValues.getLocationInformation().getAgeOfLocationInformation() != null) {
                        this.ageOfLocationInfo = atiResponseValues.getLocationInformation().getAgeOfLocationInformation().intValue();
                    }
                    if (atiResponseValues.getLocationInformation().getLocationInformationEPS() != null) {
                        this.ageOfLocationInfo = atiResponseValues.getLocationInformation().getAgeOfLocationInformation();
                        if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                            EUtranCgi eUtranCgi = atiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity();
                            this.mcc = eUtranCgi.getMCC();
                            this.mnc = eUtranCgi.getMNC();
                            long enbId = eUtranCgi.getENodeBId();
                            this.ci = eUtranCgi.getCi();
                            EUTRANCGIImpl eutrancgi = new EUTRANCGIImpl(atiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
                            this.eci = eutrancgi.getEci();
                        }
                        if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                            this.mcc = atiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getMCC();
                            this.mnc = atiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getMNC();
                            this.tac = atiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getTAC();
                        }
                        if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
                            if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation()  != null)
                                this.typeOfShape = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape().name();
                            this.latitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude();
                            this.longitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude();
                            this.uncertainty = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty();
                        } else if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                            if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape() != null)
                                this.typeOfShape = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape().name();
                            this.latitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude();
                            this.longitude = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude();
                            this.uncertainty = atiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty();
                        }
                        if (atiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
                            byte[] mmeNameData = atiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName().getData();
                            this.mmeName = AVPHandler.byte2String(mmeNameData);
                        }
                    }
                    if (atiResponseValues.getLocationInformation().getVlrNumber() != null) {
                        this.nnn = this.vlrNumber = atiResponseValues.getLocationInformation().getVlrNumber().getAddress();
                    }
                    if (atiResponseValues.getLocationInformation().getMscNumber() != null) {
                        this.nnn = this.mscNumber = atiResponseValues.getLocationInformation().getMscNumber().getAddress();
                    }

                }
                if (atiResponseValues.getLocationInformationGPRS() != null) {
                    if (atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        if (atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                            this.mcc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                            this.mnc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                            this.lac = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                        } else if (atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            this.mcc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                            this.mnc = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                            this.lac = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                            ciOrsac = atiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                            if (this.saiPresent)
                                this.sac = ciOrsac;
                            else
                                this.ci = ciOrsac;
                        }
                    }
                    if (atiResponseValues.getLocationInformationGPRS().getGeographicalInformation() != null) {
                        if (atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape() != null)
                            this.typeOfShape = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape().name();
                        this.latitude = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLatitude();
                        this.longitude = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLongitude();
                        this.uncertainty = atiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getUncertainty();
                    } else if (atiResponseValues.getLocationInformationGPRS().getGeodeticInformation() != null) {
                        if (atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape() != null)
                            this.typeOfShape = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape().name();
                        this.latitude = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLatitude();
                        this.longitude = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLongitude();
                        this.uncertainty = atiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getUncertainty();
                    }
                    if (atiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation() != null) {
                        this.ageOfLocationInfo = atiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation().intValue();
                    }
                    if (atiResponseValues.getLocationInformationGPRS().getSGSNNumber() != null) {
                        this.nnn = this.sgsnNumber = atiResponseValues.getLocationInformationGPRS().getSGSNNumber().getAddress();
                    }
                }
                if (atiResponseValues.getSubscriberState() != null) {
                    if (atiResponseValues.getSubscriberState().getSubscriberStateChoice() != null) {
                        this.subscriberState = atiResponseValues.getSubscriberState().getSubscriberStateChoice().toString();
                    }
                }
                if (atiResponseValues.getImei() != null) {
                    this.imei = atiResponseValues.getImei().getIMEI();
                }
            }

        } catch (MAPException me) {
            logger.severe("Map exception while retrieving ATI response values: " + me);
        } catch (Exception e) {
            logger.severe("Exception while retrieving ATI response values: " + e);
        }
    }

    /////////////////////////
    // Getter and Setters //
    ///////////////////////


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

    public Boolean getSaiPresent() {
        return saiPresent;
    }

    public void setSaiPresent(Boolean saiPresent) {
        this.saiPresent = saiPresent;
    }

    public Integer getTac() {
        return tac;
    }

    public void setTac(Integer tac) {
        this.tac = tac;
    }

    public Integer getRac() {
        return rac;
    }

    public void setRac(Integer rac) {
        this.rac = rac;
    }

    public Integer getRatType() {
        return ratType;
    }

    public void setRatType(Integer ratType) {
        this.ratType = ratType;
    }

    public Long getEci() {
        return eci;
    }

    public void setEci(Long eci) {
        this.eci = eci;
    }

    public Integer getAgeOfLocationInfo() {
        return ageOfLocationInfo;
    }

    public void setAgeOfLocationInfo(Integer ageOfLocationInfo) {
        this.ageOfLocationInfo = ageOfLocationInfo;
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

    public String getSubscriberState() {
        return subscriberState;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
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
        if (this.uncertainty != null) {
            this.radius = this.uncertainty;
            String formattedRadius = radiusFormat.format(this.radius);
            return Double.valueOf(formattedRadius);
        } else {
            return this.radius;
        }
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
