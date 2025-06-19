package org.mobicents.gmlc.slee.primitives;

import org.restcomm.protocols.ss7.map.api.MAPException;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface EUTRANCGI extends Serializable {

  byte[] getData();

  int getMCC() throws MAPException;

  int getMNC() throws MAPException;

  long getEci() throws MAPException;

  long getENodeBId() throws MAPException;

  int getCi() throws MAPException;

}
