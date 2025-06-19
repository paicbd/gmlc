package org.mobicents.gmlc.slee.http;

import org.mobicents.gmlc.slee.mlp.MLPResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class HttpResponseType {

    public static int setHttpServletResponseStatusCode(MLPResponse.MLPResultType mlpResultType) {
        switch (mlpResultType) {
            case OK:
                return HttpServletResponse.SC_OK;
            case SYSTEM_FAILURE:
                return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            case UNSPECIFIED_ERROR:
                return HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            case UNAUTHORIZED_APPLICATION:
                return HttpServletResponse.SC_UNAUTHORIZED;
            case UNKNOWN_SUBSCRIBER:
                return HttpServletResponse.SC_NOT_FOUND;
            case ABSENT_SUBSCRIBER:
                return HttpServletResponse.SC_GONE;
            case POSITION_METHOD_FAILURE:
                return HttpServletResponse.SC_EXPECTATION_FAILED;
            case TIMEOUT:
                return HttpServletResponse.SC_REQUEST_TIMEOUT;
            case CONGESTION_IN_LOCATION_SERVER:
                return HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            case UNSUPPORTED_VERSION:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case TOO_MANY_POSITION_ITEMS:
                return HttpServletResponse.SC_BAD_REQUEST;
            case FORMAT_ERROR:
                return HttpServletResponse.SC_BAD_REQUEST;
            case SYNTAX_ERROR:
                return HttpServletResponse.SC_BAD_REQUEST;
            case PROTOCOL_ELEMENT_NOT_SUPPORTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case SERVICE_NOT_SUPPORTED:
                return HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            case PROTOCOL_ELEMENT_ATTRIBUTE_NOT_SUPPORTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case INVALID_PROTOCOL_ELEMENT_VALUE:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case INVALID_PROTOCOL_ELEMENT_ATTRIBUTE_VALUE:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case PROTOCOL_ELEMENT_VALUE_NOT_SUPPORTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case PROTOCOL_ELEMENT_ATTRIBUTE_VALUE_NOT_SUPPORTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case CANCELLATION_OF_TRIGGERED_LOCATION_REQUEST:
                return HttpServletResponse.SC_RESET_CONTENT;
            case INVALID_MSID_IN_TLRSR:
                return HttpServletResponse.SC_BAD_REQUEST;
            case TLRSR_FOR_INDIVIDUAL_TARGET_NOT_SUPPORTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case QOP_NOT_ATTAINABLE:
                return HttpServletResponse.SC_EXPECTATION_FAILED;
            case POSITIONING_NOT_ALLOWED:
                return HttpServletResponse.SC_FORBIDDEN;
            case CONGESTION_IN_MOBILE_NETWORK:
                return HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            case DISALLOWED_BY_LOCAL_REGULATIONS:
                return HttpServletResponse.SC_FORBIDDEN;
            case MISCONFIGURATION_OF_LOCATION_SERVER:
                return HttpServletResponse.SC_BAD_GATEWAY;
            case TARGET_MOVED_TO_NEW_MSC_SGSN:
                return HttpServletResponse.SC_GONE;
            case STANDARD_LOCATION_REPORT_SERVICE_NOT_SUPPORTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case MLS_CLIENT_ERROR:
                return HttpServletResponse.SC_BAD_GATEWAY;
            case STANDARD_LOCATION_REPORT_SERVICE_NOT_ACCEPTED:
                return HttpServletResponse.SC_NOT_ACCEPTABLE;
            case SUBSCRIBER_IN_STANDARD_LOCATION_REPORT_SERVICE_NOT_VALID:
                return HttpServletResponse.SC_NOT_FOUND;
            case INVALID_SERVICE_ID_IN_STANDARD_LOCATION_REPORT_SERVICE:
                return HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            default:
                return HttpServletResponse.SC_OK;
        }
    }

}
