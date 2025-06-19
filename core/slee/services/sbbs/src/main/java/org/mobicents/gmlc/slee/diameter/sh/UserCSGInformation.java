package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "UserCSGInformation")
public class UserCSGInformation implements Serializable {

  private static final long serialVersionUID = 1L;
  private String csgid;

  public UserCSGInformation() {
    super();
  }

  public String getCsgid() {
    return csgid;
  }

  @XmlElement(name = "CSGId")
  public void setCsgid(String csgid) {
    this.csgid = csgid;
  }

  @Override
  public String toString() {
    return "UserCSGInformation [csgid=" + csgid + "]";
  }
}
