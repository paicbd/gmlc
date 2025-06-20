//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.18 at 10:01:58 PM EST 
//


package org.mobicents.gmlc.slee.mlp.v3_4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}msids"/>
 *         &lt;element ref="{}interval" minOccurs="0"/>
 *         &lt;element ref="{}start_time" minOccurs="0"/>
 *         &lt;element ref="{}stop_time" minOccurs="0"/>
 *         &lt;element ref="{}duration" minOccurs="0"/>
 *         &lt;element ref="{}tlrr_event" minOccurs="0"/>
 *         &lt;element ref="{}qop" minOccurs="0"/>
 *         &lt;element ref="{}geo_info" minOccurs="0"/>
 *         &lt;element ref="{}pushaddr" minOccurs="0"/>
 *         &lt;element ref="{}loc_type" minOccurs="0"/>
 *         &lt;element ref="{}prio" minOccurs="0"/>
 *         &lt;element ref="{}service_coverage" minOccurs="0"/>
 *         &lt;element ref="{}MapRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ver" default="3.4.0">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="3.4.0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msids",
    "interval",
    "startTime",
    "stopTime",
    "duration",
    "tlrrEvent",
    "qop",
    "geoInfo",
    "pushaddr",
    "locType",
    "prio",
    "serviceCoverage",
    "mapRequest"
})
@XmlRootElement(name = "tlrr")
public class Tlrr {

    @XmlElement(required = true)
    protected Msids msids;
    protected String interval;
    @XmlElement(name = "start_time")
    protected StartTime startTime;
    @XmlElement(name = "stop_time")
    protected StopTime stopTime;
    protected String duration;
    @XmlElement(name = "tlrr_event")
    protected TlrrEvent tlrrEvent;
    protected Qop qop;
    @XmlElement(name = "geo_info")
    protected GeoInfo geoInfo;
    protected Pushaddr pushaddr;
    @XmlElement(name = "loc_type")
    protected LocType locType;
    protected Prio prio;
    @XmlElement(name = "service_coverage")
    protected ServiceCoverage serviceCoverage;
    @XmlElement(name = "MapRequest")
    protected MapRequest mapRequest;
    @XmlAttribute(name = "ver")
    protected String ver;

    /**
     * Gets the value of the msids property.
     * 
     * @return
     *     possible object is
     *     {@link Msids }
     *     
     */
    public Msids getMsids() {
        return msids;
    }

    /**
     * Sets the value of the msids property.
     * 
     * @param value
     *     allowed object is
     *     {@link Msids }
     *     
     */
    public void setMsids(Msids value) {
        this.msids = value;
    }

    /**
     * Gets the value of the interval property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Sets the value of the interval property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterval(String value) {
        this.interval = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link StartTime }
     *     
     */
    public StartTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link StartTime }
     *     
     */
    public void setStartTime(StartTime value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the stopTime property.
     * 
     * @return
     *     possible object is
     *     {@link StopTime }
     *     
     */
    public StopTime getStopTime() {
        return stopTime;
    }

    /**
     * Sets the value of the stopTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link StopTime }
     *     
     */
    public void setStopTime(StopTime value) {
        this.stopTime = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the tlrrEvent property.
     * 
     * @return
     *     possible object is
     *     {@link TlrrEvent }
     *     
     */
    public TlrrEvent getTlrrEvent() {
        return tlrrEvent;
    }

    /**
     * Sets the value of the tlrrEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link TlrrEvent }
     *     
     */
    public void setTlrrEvent(TlrrEvent value) {
        this.tlrrEvent = value;
    }

    /**
     * Gets the value of the qop property.
     * 
     * @return
     *     possible object is
     *     {@link Qop }
     *     
     */
    public Qop getQop() {
        return qop;
    }

    /**
     * Sets the value of the qop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Qop }
     *     
     */
    public void setQop(Qop value) {
        this.qop = value;
    }

    /**
     * Gets the value of the geoInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GeoInfo }
     *     
     */
    public GeoInfo getGeoInfo() {
        return geoInfo;
    }

    /**
     * Sets the value of the geoInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoInfo }
     *     
     */
    public void setGeoInfo(GeoInfo value) {
        this.geoInfo = value;
    }

    /**
     * Gets the value of the pushaddr property.
     * 
     * @return
     *     possible object is
     *     {@link Pushaddr }
     *     
     */
    public Pushaddr getPushaddr() {
        return pushaddr;
    }

    /**
     * Sets the value of the pushaddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pushaddr }
     *     
     */
    public void setPushaddr(Pushaddr value) {
        this.pushaddr = value;
    }

    /**
     * Gets the value of the locType property.
     * 
     * @return
     *     possible object is
     *     {@link LocType }
     *     
     */
    public LocType getLocType() {
        return locType;
    }

    /**
     * Sets the value of the locType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocType }
     *     
     */
    public void setLocType(LocType value) {
        this.locType = value;
    }

    /**
     * Gets the value of the prio property.
     * 
     * @return
     *     possible object is
     *     {@link Prio }
     *     
     */
    public Prio getPrio() {
        return prio;
    }

    /**
     * Sets the value of the prio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prio }
     *     
     */
    public void setPrio(Prio value) {
        this.prio = value;
    }

    /**
     * Gets the value of the serviceCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceCoverage }
     *     
     */
    public ServiceCoverage getServiceCoverage() {
        return serviceCoverage;
    }

    /**
     * Sets the value of the serviceCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceCoverage }
     *     
     */
    public void setServiceCoverage(ServiceCoverage value) {
        this.serviceCoverage = value;
    }

    /**
     * Gets the value of the mapRequest property.
     * 
     * @return
     *     possible object is
     *     {@link MapRequest }
     *     
     */
    public MapRequest getMapRequest() {
        return mapRequest;
    }

    /**
     * Sets the value of the mapRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapRequest }
     *     
     */
    public void setMapRequest(MapRequest value) {
        this.mapRequest = value;
    }

    /**
     * Gets the value of the ver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVer() {
        if (ver == null) {
            return "3.4.0";
        } else {
            return ver;
        }
    }

    /**
     * Sets the value of the ver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVer(String value) {
        this.ver = value;
    }

}
