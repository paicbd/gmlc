package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Extension")
public class PSLocationInformationExtension2 implements Serializable {

    private static final long serialVersionUID = 1L;
    private String visitedPLMNId;
    private LocalTimeZone localTimeZone;
    private PSLocationInformationExtension3 psLocationInformationExtension3;

    public PSLocationInformationExtension2() {
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

    public PSLocationInformationExtension3 getPsLocationInformationExtension3() {
        return psLocationInformationExtension3;
    }
    @XmlElement (name = "Extension")
    public void setPsLocationInformationExtension3(PSLocationInformationExtension3 psLocationInformationExtension3) {
        this.psLocationInformationExtension3 = psLocationInformationExtension3;
    }

    @Override
    public String toString() {
        return "PSLocationInformationExtension2{" +
            "visitedPLMNId='" + visitedPLMNId + '\'' +
            ", localTimeZone=" + localTimeZone +
            ", psLocationInformationExtension3=" + psLocationInformationExtension3 +
            '}';
    }
}
