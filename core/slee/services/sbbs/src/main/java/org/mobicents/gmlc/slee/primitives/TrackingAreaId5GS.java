package org.mobicents.gmlc.slee.primitives;

import org.restcomm.protocols.ss7.map.api.MAPException;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface TrackingAreaId5GS extends Serializable {

    byte[] getData();

    int getMCC() throws MAPException;

    int getMNC() throws MAPException;

    int get5GSTAC() throws MAPException;
}
