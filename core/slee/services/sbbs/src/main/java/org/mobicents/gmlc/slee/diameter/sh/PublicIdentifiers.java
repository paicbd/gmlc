package org.mobicents.gmlc.slee.diameter.sh;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
@XmlRootElement(name = "Sh-Data")
public class PublicIdentifiers implements Serializable {

  private static final long serialVersionUID = 1L;
  private PublicIds publicIdentifiers;

  public PublicIdentifiers() {
    super();
  }

  public PublicIds getPublicIdentifiers() {
    return publicIdentifiers;
  }
  @XmlElement(name = "PublicIdentifiers")
  public void setPublicIdentifiers(PublicIds publicIdentifiers) {
    this.publicIdentifiers = publicIdentifiers;
  }

  @Override
  public String toString() {
    return "PublicIdentifiers{" +
        "publicIdentifiers=" + publicIdentifiers +
        '}';
  }
}
