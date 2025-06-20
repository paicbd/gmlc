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
 *         &lt;element ref="{}angle"/>
 *         &lt;element ref="{}semiMajor"/>
 *         &lt;element ref="{}semiMinor"/>
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
    "angle",
    "semiMajor",
    "semiMinor",
    "angularUnit",
    "distanceUnit"
})
@XmlRootElement(name = "EllipticalArea")
public class EllipticalArea {

    @XmlElement(required = true)
    protected Coord coord;
    @XmlElement(required = true)
    protected String angle;
    @XmlElement(required = true)
    protected String semiMajor;
    @XmlElement(required = true)
    protected String semiMinor;
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
     * Gets the value of the angle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAngle() {
        return angle;
    }

    /**
     * Sets the value of the angle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAngle(String value) {
        this.angle = value;
    }

    /**
     * Gets the value of the semiMajor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSemiMajor() {
        return semiMajor;
    }

    /**
     * Sets the value of the semiMajor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSemiMajor(String value) {
        this.semiMajor = value;
    }

    /**
     * Gets the value of the semiMinor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSemiMinor() {
        return semiMinor;
    }

    /**
     * Sets the value of the semiMinor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSemiMinor(String value) {
        this.semiMinor = value;
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
