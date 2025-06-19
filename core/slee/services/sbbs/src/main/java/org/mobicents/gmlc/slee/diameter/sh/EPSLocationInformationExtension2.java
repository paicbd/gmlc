package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Extension")
public class EPSLocationInformationExtension2 implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ratType;

    public EPSLocationInformationExtension2() {
        super();
    }

    public String getRatType() {
        return ratType;
    }
    @XmlElement(name = "RATtype")
    public void setRatType(String ratType) {
        this.ratType = ratType;
    }

    @Override
    public String toString() {
        return "EPSLocationInformationExtension2{" +
            "ratType='" + ratType + '\'' +
            '}';
    }
}
