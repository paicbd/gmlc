package org.mobicents.gmlc.slee.primitives;

import org.restcomm.protocols.ss7.map.api.MAPException;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface UTRANCellId extends Serializable {

    byte[] getData();

    int getMCC() throws IOException, Exception;

    int getMNC() throws IOException, Exception;

    int getUci() throws MAPException;
}
