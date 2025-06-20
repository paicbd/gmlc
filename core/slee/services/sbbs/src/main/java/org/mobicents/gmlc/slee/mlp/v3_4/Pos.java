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
 *         &lt;element ref="{}msid"/>
 *         &lt;choice>
 *           &lt;element ref="{}pd"/>
 *           &lt;element ref="{}poserr"/>
 *         &lt;/choice>
 *         &lt;element ref="{}gsm_net_param" minOccurs="0"/>
 *         &lt;element ref="{}trans_id" minOccurs="0"/>
 *         &lt;element ref="{}add_info" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="pos_method">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="CELL"/>
 *             &lt;enumeration value="OTDOA"/>
 *             &lt;enumeration value="GPS"/>
 *             &lt;enumeration value="A-GPS"/>
 *             &lt;enumeration value="GNSS"/>
 *             &lt;enumeration value="A-GNSS"/>
 *             &lt;enumeration value="E-OTD"/>
 *             &lt;enumeration value="U-TDOA"/>
 *             &lt;enumeration value="AFLT"/>
 *             &lt;enumeration value="EFLT"/>
 *             &lt;enumeration value="E-CID"/>
 *             &lt;enumeration value="UNKNOWN"/>
 *             &lt;enumeration value="OTHER"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="result_type" default="FINAL">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="INTERMEDIATE"/>
 *             &lt;enumeration value="FINAL"/>
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
    "msid",
    "pd",
    "poserr",
    "gsmNetParam",
    "servingCell",
    "routingAreaCode",
    "trackingAreaCode",
    "transId",
    "addInfo"
})
@XmlRootElement(name = "pos")
public class Pos {

    @XmlElement(required = true)
    protected Msid msid;
    protected Pd pd;
    protected Poserr poserr;
    @XmlElement(name = "gsm_net_param")
    protected GsmNetParam gsmNetParam;
    @XmlElement(name = "trans_id")
    protected String transId;
    @XmlElement(name = "add_info")
    protected String addInfo;
    @XmlAttribute(name = "pos_method")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String posMethod;
    @XmlAttribute(name = "result_type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String resultType;
    // the following parameters are a customization for SS7 MAP operations and Diameter PLR/UDR commands
    @XmlElement(name = "serving_cell")
    protected ServingCell servingCell;
    @XmlElement(name = "rac")
    protected String routingAreaCode;
    @XmlElement(name = "tac")
    protected String trackingAreaCode;

    /**
     * Gets the value of the msid property.
     * 
     * @return
     *     possible object is
     *     {@link Msid }
     *     
     */
    public Msid getMsid() {
        return msid;
    }

    /**
     * Sets the value of the msid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Msid }
     *     
     */
    public void setMsid(Msid value) {
        this.msid = value;
    }

    /**
     * Gets the value of the pd property.
     * 
     * @return
     *     possible object is
     *     {@link Pd }
     *     
     */
    public Pd getPd() {
        return pd;
    }

    /**
     * Sets the value of the pd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pd }
     *     
     */
    public void setPd(Pd value) {
        this.pd = value;
    }

    /**
     * Gets the value of the poserr property.
     * 
     * @return
     *     possible object is
     *     {@link Poserr }
     *     
     */
    public Poserr getPoserr() {
        return poserr;
    }

    /**
     * Sets the value of the poserr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Poserr }
     *     
     */
    public void setPoserr(Poserr value) {
        this.poserr = value;
    }

    /**
     * Gets the value of the gsmNetParam property.
     * 
     * @return
     *     possible object is
     *     {@link GsmNetParam }
     *     
     */
    public GsmNetParam getGsmNetParam() {
        return gsmNetParam;
    }

    /**
     * Sets the value of the gsmNetParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link GsmNetParam }
     *     
     */
    public void setGsmNetParam(GsmNetParam value) {
        this.gsmNetParam = value;
    }

    /**
     * Gets the value of the transId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransId() {
        return transId;
    }

    /**
     * Sets the value of the transId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransId(String value) {
        this.transId = value;
    }

    /**
     * Gets the value of the addInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddInfo() {
        return addInfo;
    }

    /**
     * Sets the value of the addInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddInfo(String value) {
        this.addInfo = value;
    }

    /**
     * Gets the value of the posMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosMethod() {
        return posMethod;
    }

    /**
     * Sets the value of the posMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosMethod(String value) {
        this.posMethod = value;
    }

    /**
     * Gets the value of the resultType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultType() {
        if (resultType == null) {
            return "FINAL";
        } else {
            return resultType;
        }
    }

    /**
     * Sets the value of the resultType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultType(String value) {
        this.resultType = value;
    }

    /**
     * Gets the value of the servingCell property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public ServingCell getServingCell() {
        return servingCell;
    }

    /**
     * Sets the value of the servingCell property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setServingCell(ServingCell value) {
        this.servingCell = value;
    }

    /**
     * Gets the value of the routingAreaCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRoutingAreaCode() {
        return routingAreaCode;
    }

    /**
     * Sets the value of the routingAreaCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRoutingAreaCode(String value) {
        this.routingAreaCode = value;
    }

    /**
     * Gets the value of the trackingAreaCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTrackingAreaCode() {
        return trackingAreaCode;
    }

    /**
     * Sets the value of the trackingAreaCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTrackingAreaCode(String value) {
        this.trackingAreaCode = value;
    }
}
