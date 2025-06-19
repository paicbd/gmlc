package org.mobicents.gmlc.slee.supl;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public enum SuplGeoTargetArea {

    CircularArea (1), EllipticalArea(2), PolygonArea(3), ExtElem1(4);

    public final int suplGeoTargetArea;

    SuplGeoTargetArea(int suplGeoTargetArea) {
        this.suplGeoTargetArea = suplGeoTargetArea;
    }

    public int getSuplGeoTargetArea() {
        return suplGeoTargetArea;
    }

    @Override
    public String toString() {
        return "SuplGeoTargetArea{" +
            "suplGeoTargetArea=" + suplGeoTargetArea +
            '}';
    }
}
