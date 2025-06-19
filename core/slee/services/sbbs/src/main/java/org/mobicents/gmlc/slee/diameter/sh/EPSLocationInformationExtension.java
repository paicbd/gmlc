package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Extension")
public class EPSLocationInformationExtension implements Serializable {

    private static final long serialVersionUID = 1L;
    private String visitedPLMNId;
    private LocalTimeZone localTimeZone;
    private EPSLocationInformationExtension2 epsLocationInformationExtension2;

    public EPSLocationInformationExtension() {
        super();
    }

    public String getVisitedPLMNId() {
        return visitedPLMNId;
    }
    @XmlElement(name = "VisitedPLMNID")
    public void setVisitedPLMNId(String visitedPLMNId) {
        this.visitedPLMNId = visitedPLMNId;
    }

    public LocalTimeZone getLocalTimeZone() {
        return localTimeZone;
    }
    @XmlElement (name = "LocalTimeZone")
    public void setLocalTimeZone(LocalTimeZone localTimeZone) {
        this.localTimeZone = localTimeZone;
    }

    public EPSLocationInformationExtension2 getEpsLocationInformationExtension2() {
        return epsLocationInformationExtension2;
    }
    @XmlElement (name = "Extension")
    public void setEpsLocationInformationExtension2(EPSLocationInformationExtension2 epsLocationInformationExtension2) {
        this.epsLocationInformationExtension2 = epsLocationInformationExtension2;
    }

    @Override
    public String toString() {
        return "EPSLocationInformationExtension{" +
            "visitedPLMNId='" + visitedPLMNId + '\'' +
            ", localTimeZone=" + localTimeZone +
            ", epsLocationInformationExtension2=" + epsLocationInformationExtension2 +
            '}';
    }
}
