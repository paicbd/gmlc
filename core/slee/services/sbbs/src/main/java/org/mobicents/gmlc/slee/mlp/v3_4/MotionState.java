//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.18 at 10:01:58 PM EST 
//


package org.mobicents.gmlc.slee.mlp.v3_4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MotionState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MotionState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}MotionState"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MotionState", propOrder = {
    "motionState"
})
public class MotionState {

    @XmlElement(name = "MotionState", required = true)
    protected String motionState;

    /**
     * Gets the value of the motionState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotionState() {
        return motionState;
    }

    /**
     * Sets the value of the motionState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotionState(String value) {
        this.motionState = value;
    }

}
