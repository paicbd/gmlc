package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Sh-Data")
public class PacketSwitchedLocationInformation implements Serializable {

  private static final long serialVersionUID = 1L;
  private PSLocationInformation psLocationInformation;

  public PacketSwitchedLocationInformation() {
    super();
  }

  public PSLocationInformation getPsLocationInformation() {
    return psLocationInformation;
  }
  @XmlElement (name = "PSLocationInformation")
  public void setPsLocationInformation(PSLocationInformation psLocationInformation) {
    this.psLocationInformation = psLocationInformation;
  }

  @Override
  public String toString() {
    return "PacketSwitchedLocationInformation [psLocationInformation=" + psLocationInformation + "]";
  }
}

