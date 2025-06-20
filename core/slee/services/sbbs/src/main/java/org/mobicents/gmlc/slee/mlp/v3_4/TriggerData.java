//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.18 at 10:01:58 PM EST 
//


package org.mobicents.gmlc.slee.mlp.v3_4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}req_id"/>
 *         &lt;element ref="{}msid" maxOccurs="unbounded"/>
 *         &lt;element ref="{}tlrr" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reqId",
    "msid",
    "tlrr"
})
@XmlRootElement(name = "trigger_data")
public class TriggerData {

    @XmlElement(name = "req_id", required = true)
    protected String reqId;
    @XmlElement(required = true)
    protected List<Msid> msid;
    protected Tlrr tlrr;

    /**
     * Gets the value of the reqId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * Sets the value of the reqId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqId(String value) {
        this.reqId = value;
    }

    /**
     * Gets the value of the msid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the msid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMsid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Msid }
     * 
     * 
     */
    public List<Msid> getMsid() {
        if (msid == null) {
            msid = new ArrayList<Msid>();
        }
        return this.msid;
    }

    /**
     * Gets the value of the tlrr property.
     * 
     * @return
     *     possible object is
     *     {@link Tlrr }
     *     
     */
    public Tlrr getTlrr() {
        return tlrr;
    }

    /**
     * Sets the value of the tlrr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tlrr }
     *     
     */
    public void setTlrr(Tlrr value) {
        this.tlrr = value;
    }

}
