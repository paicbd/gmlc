package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Extension")
public class CSLocationInformationExtension3 implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalTimeZone localTimeZone;

    public CSLocationInformationExtension3() {
        super();
    }

    public LocalTimeZone getLocalTimeZone() {
        return localTimeZone;
    }
    @XmlElement(name = "LocalTimeZone")
    public void setLocalTimeZone(LocalTimeZone localTimeZone) {
        this.localTimeZone = localTimeZone;
    }

    @Override
    public String toString() {
        return "CSLocationInformationExtension3{" +
            "localTimeZone=" + localTimeZone +
            '}';
    }
}
