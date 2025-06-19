package org.mobicents.gmlc.slee.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author <a href="mailto:joram.herrera2@gmail.com"> Joram Herrera </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class GADShapesUtils {

    /**
     * @param encodedLatitude
     * @return decoded Latitude in degrees
     */
    public static double decodeLatitude(Integer encodedLatitude) {
        BigDecimal decimal = new BigDecimal(((encodedLatitude / (Math.pow(2, 23) - 1)) * 90));
        return decimal.setScale(6, RoundingMode.DOWN).doubleValue();
    }

    /**
     * @param encodedLongitude
     * @return decoded Longitude in degrees
     */
    public static double decodeLongitude(Integer encodedLongitude) {
        BigDecimal decimal = new BigDecimal(((encodedLongitude / (Math.pow(2, 24) - 1)) * 360));
        return decimal.setScale(6, RoundingMode.DOWN).doubleValue();
    }

    /**
     * @param latitude
     * @return encoded latitude (to be converted to ASN1 Integer)
     */
    public static int encodeLatitude(double latitude) {
        int encodedLatitude = (int) (((Math.pow(2, 23)/90) * latitude));
        return encodedLatitude;
    }

    /**
     * @param longitude
     * @return encoded longitude (to be converted to ASN1 Integer)
     */
    public static int encodeLongitude(double longitude) {
        int encodedLatitude = (int) (((Math.pow(2, 24)/360) * longitude));
        return encodedLatitude;
    }

    /**
     * @param encodedUncertaintySemiMajor
     * @return decoded uncertainty SemiMajor in meters (m)
     */
    public static double decodeUncertaintySemiMajor(Integer encodedUncertaintySemiMajor) {
        return decodeUncertainty(10, 0.1, encodedUncertaintySemiMajor, 1000);
    }

    /**
     * @param encodedUncertaintySemiMinor
     * @return decoded uncertainty SemiMinor in meters (m)
     */
    public static double decodeUncertaintySemiMinor(Integer encodedUncertaintySemiMinor) {
        return decodeUncertainty(10, 0.1, encodedUncertaintySemiMinor, 1000);
    }

    /**
     * @param uncertaintyValue (metres)
     * @return encoded uncertainty (to be converted to ASN1 Integer)
     */
    public static int encodeUncertainty(double uncertaintyValue) {
        double[] uncertaintyTable = initUncertaintyTable(10, 0.1, false);
        for (int i = 0; i < (uncertaintyTable.length -1); i++) {
            if (uncertaintyValue < uncertaintyTable[i + 1]) {
                return i;
            }
        }
        return 127;
    }

    /**
     * @param highAccuracyUncertaintyValue (metres)
     * @return encoded high accuracy uncertainty (to be converted to ASN1 Integer)
     */
    public static int encodeHighAccuracyUncertainty(double highAccuracyUncertaintyValue) {
        double[] uncertaintyTable = initUncertaintyTable(0.3, 0.02, true);
        for (int i = 0; i < (uncertaintyTable.length -1); i++) {
            if (highAccuracyUncertaintyValue < uncertaintyTable[i + 1]) {
                return i;
            }
        }
        return 127;
    }

    /**
     * @param uncertaintyValue (metres)
     * @return encoded uncertainty (to be converted to ASN1 Integer)
     */
    public static int encodeUncertaintyAltitude(double uncertaintyValue) {
        double[] uncertaintyTable = initUncertaintyTable(45, 0.025, false);
        for (int i = 0; i < (uncertaintyTable.length -1); i++) {
            if (uncertaintyValue < uncertaintyTable[i + 1]) {
                return i;
            }
        }
        return 127;
    }

    private static double[] initUncertaintyTable(double C, double x, boolean highAccuracy) {
        double[] uncertaintyArray = new double[128];
        if (highAccuracy)
            uncertaintyArray = new double[256];
        for (int i = 1; i < uncertaintyArray.length; i++) {
            uncertaintyArray[i] = C * (Math.pow(1 + x, i) - 1);
        }
        return uncertaintyArray;
    }

    /**
     * @param encodedUncertaintyAltitude
     * @return decoded uncertainty altitude in meters (m)
     */
    public static double decodeUncertaintyAltitude(Integer encodedUncertaintyAltitude) {
        return decodeUncertainty(45, 0.025, encodedUncertaintyAltitude, 1);
    }

    /**
     * @param constant_C
     * @param constant_x
     * @param number
     * @return decoded uncertainty value in Kilometers or Meters according to scale value
     */
    private static double decodeUncertainty(int constant_C, double constant_x, int number, int distanceScale) {
        BigDecimal decimal = new BigDecimal((constant_C * (Math.pow((1 + constant_x), number) - 1))/distanceScale);
        return decimal.setScale(6, RoundingMode.DOWN).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println("Decode latitude in ASN1 (3487983) to degrees of latitude = " + decodeLatitude(3487983));
        System.out.println("Decode longitude ASN1 (-5689535) to degrees of longitude = " + decodeLongitude(3487983));
        System.out.println("37.422002 degrees of latitude encoded = " + encodeLatitude(37.422002));
        System.out.println("-122.084177 degrees of longitude encoded = " + encodeLongitude(-122.084177));
        System.out.println("Decode uncertainty semi major in ASN1 (40) to Km  = " + decodeUncertaintySemiMajor(40));
        System.out.println("Decode uncertainty semi minor in ASN1 (40) to Km = " + decodeUncertaintySemiMinor(40));
        System.out.println("Encode uncertainty in metres to ASN1 integer = " + encodeUncertainty(57.3));
        System.out.println("Encode high accuracy uncertainty in metres to ASN1 integer = " + encodeHighAccuracyUncertainty(1.16263));
        System.out.println("Decode uncertainty altitude in ASN1 (127) to metres = " + decodeUncertaintyAltitude(127));
        System.out.println("Encode uncertainty altitude in metres to ASN1 integer = " + encodeUncertaintyAltitude(826.1));
    }
}
