package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Extension")
public class CSLocationInformationExtension implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserCSGInformation userCSGInformation;
    private CSLocationInformationExtension2 csLocationInformationExtension2;

    public CSLocationInformationExtension() {
        super();
    }

    public UserCSGInformation getUserCSGInformation() {
        return userCSGInformation;
    }
    @XmlElement(name = "UserCSGInformation")
    public void setUserCSGInformation(UserCSGInformation userCSGInformation) {
        this.userCSGInformation = userCSGInformation;
    }

    public CSLocationInformationExtension2 getCsLocationInformationExtension2() {
        return csLocationInformationExtension2;
    }
    @XmlElement (name = "Extension")
    public void setCsLocationInformationExtension2(CSLocationInformationExtension2 csLocationInformationExtension2) {
        this.csLocationInformationExtension2 = csLocationInformationExtension2;
    }

    @Override
    public String toString() {
        return "CSLocationInformationExtension{" +
            "userCSGInformation=" + userCSGInformation +
            ", csLocationInformationExtension2=" + csLocationInformationExtension2 +
            '}';
    }
}
