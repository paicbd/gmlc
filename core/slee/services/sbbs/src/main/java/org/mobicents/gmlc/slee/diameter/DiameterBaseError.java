package org.mobicents.gmlc.slee.diameter;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class DiameterBaseError {

    private String errorMessage;

    public DiameterBaseError() {
    }

    public String diameterErrorMessage(long resultCode) {

        if (resultCode == 3001)
            errorMessage = "DIAMETER_COMMAND_UNSUPPORTED, Diameter error code: " + resultCode;
        else if (resultCode == 3002)
            errorMessage = "DIAMETER_UNABLE_TO_DELIVER, Diameter error code: " + resultCode;
        else if (resultCode == 3003)
            errorMessage = "DIAMETER_REALM_NOT_SERVED, Diameter error code: " + resultCode;
        else if (resultCode == 3004)
            errorMessage = "DIAMETER_TOO_BUSY, Diameter error code: " + resultCode;
        else if (resultCode == 3005)
            errorMessage = "DIAMETER_LOOP_DETECTED, Diameter error code: " + resultCode;
        else if (resultCode == 3006)
            errorMessage = "DIAMETER_REDIRECT_INDICATION, Diameter error code: " + resultCode;
        else if (resultCode == 3007)
            errorMessage = "DIAMETER_APPLICATION_UNSUPPORTED, Diameter error code: " + resultCode;
        else if (resultCode == 3008)
            errorMessage = "DIAMETER_INVALID_HDR_BITS, Diameter error code: " + resultCode;
        else if (resultCode == 3009)
            errorMessage = "DIAMETER_INVALID_AVP_BITS, Diameter error code: " + resultCode;
        else if (resultCode == 3010)
            errorMessage = "DIAMETER_UNKNOWN_PEER, Diameter error code: " + resultCode;
        else if (resultCode == 4001)
            errorMessage = "DIAMETER_AUTHENTICATION_REJECTED, Diameter error code: " + resultCode;
        else if (resultCode == 4002)
            errorMessage = "DIAMETER_OUT_OF_SPACE, Diameter error code: " + resultCode;
        else if (resultCode == 4003)
            errorMessage = "DIAMETER_ELECTION_LOST, Diameter error code: " + resultCode;
        else if (resultCode == 5002)
            errorMessage = "DIAMETER_UNKNOWN_SESSION_ID, Diameter error code: " + resultCode;
        else if (resultCode == 5003)
            errorMessage = "DIAMETER_AUTHORIZATION_REJECTED, Diameter error code: " + resultCode;
        else if (resultCode == 5004)
            errorMessage = "DIAMETER_INVALID_AVP_VALUE, Diameter error code: " + resultCode;
        else if (resultCode == 5005)
            errorMessage = "DIAMETER_MISSING_AVP, Diameter error code: " + resultCode;
        else if (resultCode == 5006)
            errorMessage = "DIAMETER_RESOURCES_EXCEEDED, Diameter error code: " + resultCode;
        else if (resultCode == 5007)
            errorMessage = "DIAMETER_CONTRADICTING_AVPS, Diameter error code: " + resultCode;
        else if (resultCode == 5008)
            errorMessage = "DIAMETER_AVP_NOT_ALLOWED, Diameter error code: " + resultCode;
        else if (resultCode == 5009)
            errorMessage = "DIAMETER_AVP_OCCURS_TOO_MANY_TIMES, Diameter error code: " + resultCode;
        else if (resultCode == 5010)
            errorMessage = "DIAMETER_NO_COMMON_APPLICATION, Diameter error code: " + resultCode;
        else if (resultCode == 5011)
            errorMessage = "DIAMETER_UNSUPPORTED_VERSION, Diameter error code: " + resultCode;
        else if (resultCode == 5012)
            errorMessage = "DIAMETER_UNABLE_TO_COMPLY, Diameter error code: " + resultCode;
        else if (resultCode == 5013)
            errorMessage = "DIAMETER_INVALID_BIT_IN_HEADER, Diameter error code: " + resultCode;
        else if (resultCode == 5014)
            errorMessage = "DIAMETER_INVALID_AVP_LENGTH, Diameter error code: " + resultCode;

        return errorMessage;
    }

}
