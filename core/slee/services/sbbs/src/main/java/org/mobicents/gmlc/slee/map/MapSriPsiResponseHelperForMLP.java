package org.mobicents.gmlc.slee.map;

import org.mobicents.gmlc.slee.diameter.AVPHandler;
import org.mobicents.gmlc.slee.primitives.EUTRANCGIImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;

import javax.slee.facilities.Tracer;
import java.text.DecimalFormat;

/**
 * Helper class to handle a MAP PSI response value and populate an MLP SLIA
 *
 * @author  <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class MapSriPsiResponseHelperForMLP {

    private Tracer logger;
    protected static final DecimalFormat coordinatesFormat = new DecimalFormat("#0.000000");
    protected static final DecimalFormat radiusFormat = new DecimalFormat("#0.00");

    private Integer mcc, mnc, lac, ci, sac, tac, rac, ratType;
    private Long eci;
    private Integer ageOfLocationInfo;
    private String vlrNumber, mscNumber, sgsnNumber, nnn, mmeName, subscriberState, imsi, imei;
    private String typeOfShape;
    private Double latitude, longitude, uncertainty, radius;
    private Boolean saiPresent = false;
    private Integer ciOrsac;

    public MapSriPsiResponseHelperForMLP() {
    }

    public void handlePsiResponseValues(PsiResponseValues psiResponseValues) {
        try {
            if (psiResponseValues != null) {

                if (psiResponseValues.isSaiPresent()) {
                    this.saiPresent = true;
                }

                if (psiResponseValues.getLocationInformation() != null) {
                    if (psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        if (psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                            this.mcc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                            this.mnc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                            this.lac = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                        } else if (psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            this.mcc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                            this.mnc = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                            this.lac = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                            ciOrsac = psiResponseValues.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                            if (this.saiPresent)
                                this.sac = ciOrsac;
                            else
                                this.ci = ciOrsac;
                        }
                    }
                    if (psiResponseValues.getLocationInformation().getGeographicalInformation() != null) {
                        if (psiResponseValues.getLocationInformation().getGeographicalInformation().getTypeOfShape() != null)
                            this.typeOfShape = psiResponseValues.getLocationInformation().getGeographicalInformation().getTypeOfShape().name();
                        this.latitude = psiResponseValues.getLocationInformation().getGeographicalInformation().getLatitude();
                        this.longitude = psiResponseValues.getLocationInformation().getGeographicalInformation().getLongitude();
                        this.uncertainty = psiResponseValues.getLocationInformation().getGeographicalInformation().getUncertainty();
                    } else if (psiResponseValues.getLocationInformation().getGeodeticInformation() != null) {
                        if (psiResponseValues.getLocationInformation().getGeodeticInformation().getTypeOfShape() != null)
                            this.typeOfShape = psiResponseValues.getLocationInformation().getGeodeticInformation().getTypeOfShape().name();
                        this.latitude = psiResponseValues.getLocationInformation().getGeodeticInformation().getLatitude();
                        this.longitude = psiResponseValues.getLocationInformation().getGeodeticInformation().getLongitude();
                        this.uncertainty = psiResponseValues.getLocationInformation().getGeodeticInformation().getUncertainty();
                    }
                    if (psiResponseValues.getLocationInformation().getAgeOfLocationInformation() != null) {
                        this.ageOfLocationInfo = psiResponseValues.getLocationInformation().getAgeOfLocationInformation().intValue();
                    }
                    if (psiResponseValues.getLocationInformation().getLocationInformationEPS() != null) {
                        this.ageOfLocationInfo = psiResponseValues.getLocationInformation().getAgeOfLocationInformation();
                        if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
                            EUtranCgi eUtranCgi = psiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity();
                            this.mcc = eUtranCgi.getMCC();
                            this.mnc = eUtranCgi.getMNC();
                            long enbId = eUtranCgi.getENodeBId();
                            this.ci = eUtranCgi.getCi();
                            EUTRANCGIImpl eutrancgi = new EUTRANCGIImpl(psiResponseValues.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity().getData());
                            this.eci = eutrancgi.getEci();
                        }
                        if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
                            this.mcc = psiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getMCC();
                            this.mnc = psiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getMNC();
                            this.tac = psiResponseValues.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getTAC();
                        }
                        if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
                            if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape() != null)
                                this.typeOfShape = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getTypeOfShape().name();
                            this.latitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude();
                            this.longitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude();
                            this.uncertainty = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty();
                        } else if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
                            if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape() != null)
                                this.typeOfShape = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape().name();
                            this.latitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude();
                            this.longitude = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude();
                            this.uncertainty = psiResponseValues.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty();
                        }
                        if (psiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
                            byte[] mmeNameData = psiResponseValues.getLocationInformation().getLocationInformationEPS().getMmeName().getData();
                            this.mmeName = AVPHandler.byte2String(mmeNameData);
                        }
                    }
                    if (psiResponseValues.getLocationInformation().getVlrNumber() != null) {
                        this.nnn = this.vlrNumber = psiResponseValues.getLocationInformation().getVlrNumber().getAddress();
                    }
                    if (psiResponseValues.getLocationInformation().getMscNumber() != null) {
                        this.nnn = this.mscNumber = psiResponseValues.getLocationInformation().getMscNumber().getAddress();
                    }

                }
                if (psiResponseValues.getLocationInformationGPRS() != null) {
                    if (psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
                        if (psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength() != null) {
                            this.mcc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMCC();
                            this.mnc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getMNC();
                            this.lac = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getLAIFixedLength().getLac();
                        } else if (psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
                            this.mcc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC();
                            this.mnc = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC();
                            this.lac = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac();
                            ciOrsac = psiResponseValues.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode();
                            if (this.saiPresent)
                                this.sac = ciOrsac;
                            else
                                this.ci = ciOrsac;
                        }
                    }
                    if (psiResponseValues.getLocationInformationGPRS().getGeographicalInformation() != null) {
                        if (psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape() != null)
                            this.typeOfShape = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape().name();
                        this.latitude = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLatitude();
                        this.longitude = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getLongitude();
                        this.uncertainty = psiResponseValues.getLocationInformationGPRS().getGeographicalInformation().getUncertainty();
                    } else if (psiResponseValues.getLocationInformationGPRS().getGeodeticInformation() != null) {
                        if (psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape() != null)
                            this.typeOfShape = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape().name();
                        this.latitude = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLatitude();
                        this.longitude = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getLongitude();
                        this.uncertainty = psiResponseValues.getLocationInformationGPRS().getGeodeticInformation().getUncertainty();
                    }
                    if (psiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation() != null) {
                        this.ageOfLocationInfo = psiResponseValues.getLocationInformationGPRS().getAgeOfLocationInformation().intValue();
                    }
                    if (psiResponseValues.getLocationInformationGPRS().getSGSNNumber() != null) {
                        this.nnn = this.sgsnNumber = psiResponseValues.getLocationInformationGPRS().getSGSNNumber().getAddress();
                    }
                }
                if (psiResponseValues.getSubscriberState() != null) {
                    if (psiResponseValues.getSubscriberState().getSubscriberStateChoice() != null) {
                        this.subscriberState = psiResponseValues.getSubscriberState().getSubscriberStateChoice().toString();
                    }
                }
                if (psiResponseValues.getImei() != null) {
                    this.imei = psiResponseValues.getImei().getIMEI();
                }
                if (psiResponseValues.getImsi() != null) {
                    this.imsi = psiResponseValues.getImsi().getData();
                }
            }

        } catch (MAPException me) {
            logger.severe("Map exception while retrieving ATI response values: " + me);
        } catch (Exception e) {
            logger.severe("Exception while retrieving ATI response values: " + e);
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

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
