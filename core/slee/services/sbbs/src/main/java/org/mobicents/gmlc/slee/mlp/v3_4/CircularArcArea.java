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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}coord"/>
 *         &lt;element ref="{}inRadius"/>
 *         &lt;element ref="{}outRadius"/>
 *         &lt;element ref="{}startAngle"/>
 *         &lt;element ref="{}stopAngle"/>
 *         &lt;element ref="{}angularUnit" minOccurs="0"/>
 *         &lt;element ref="{}distanceUnit" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="gid" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="srsName" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "coord",
    "inRadius",
    "outRadius",
    "startAngle",
    "stopAngle",
    "angularUnit",
    "distanceUnit"
})
@XmlRootElement(name = "CircularArcArea")
public class CircularArcArea {

    @XmlElement(required = true)
    protected Coord coord;
    @XmlElement(required = true)
    protected String inRadius;
    @XmlElement(required = true)
    protected String outRadius;
    @XmlElement(required = true)
    protected String startAngle;
    @XmlElement(required = true)
    protected String stopAngle;
    protected String angularUnit;
    protected String distanceUnit;
    @XmlAttribute(name = "gid")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String gid;
    @XmlAttribute(name = "srsName")
    @XmlSchemaType(name = "anySimpleType")
    protected String srsName;

    /**
     * Gets the value of the coord property.
     * 
     * @return
     *     possible object is
     *     {@link Coord }
     *     
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Sets the value of the coord property.
     * 
     * @param value
     *     allowed object is
     *     {@link Coord }
     *     
     */
    public void setCoord(Coord value) {
        this.coord = value;
    }

    /**
     * Gets the value of the inRadius property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInRadius() {
        return inRadius;
    }

    /**
     * Sets the value of the inRadius property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInRadius(String value) {
        this.inRadius = value;
    }

    /**
     * Gets the value of the outRadius property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutRadius() {
        return outRadius;
    }

    /**
     * Sets the value of the outRadius property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutRadius(String value) {
        this.outRadius = value;
    }

    /**
     * Gets the value of the startAngle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartAngle() {
        return startAngle;
    }

    /**
     * Sets the value of the startAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartAngle(String value) {
        this.startAngle = value;
    }

    /**
     * Gets the value of the stopAngle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStopAngle() {
        return stopAngle;
    }

    /**
     * Sets the value of the stopAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStopAngle(String value) {
        this.stopAngle = value;
    }

    /**
     * Gets the value of the angularUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAngularUnit() {
        return angularUnit;
    }

    /**
     * Sets the value of the angularUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAngularUnit(String value) {
        this.angularUnit = value;
    }

    /**
     * Gets the value of the distanceUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistanceUnit() {
        return distanceUnit;
    }

    /**
     * Sets the value of the distanceUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistanceUnit(String value) {
        this.distanceUnit = value;
    }

    /**
     * Gets the value of the gid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGid() {
        return gid;
    }

    /**
     * Sets the value of the gid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGid(String value) {
        this.gid = value;
    }

    /**
     * Gets the value of the srsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrsName() {
        return srsName;
    }

    /**
     * Sets the value of the srsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrsName(String value) {
        this.srsName = value;
    }

}
