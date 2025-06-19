package org.mobicents.gmlc.slee.http.report;

/**
 * @author <a href="mailto:aferreiraguido@gmail.com"> Alejandro Ferreira Guido </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class ReportElement {

    private Integer transactionNumber;              // this attribute is the reference to look for callback string
    private ReportParameters reportParameters;      // this attribute refers to the extra parameters for registration HTTP sending
    private Boolean api;                            // this attribute refers if the HTTP sending shall be made via MLP (true) or JSON (false)
    private String httpRequestor;                   // this attribute refers to the cURL user performing the location request (PSL or PLR)

    public ReportElement() {
    }

    public ReportElement(Integer transactionNumber, ReportParameters reportParameters, Boolean api, String httpRequestor) {
        this.transactionNumber = transactionNumber;
        this.reportParameters = reportParameters;
        this.api = api;
        this.httpRequestor = httpRequestor;
    }

    public Integer getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Integer transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public ReportParameters getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(ReportParameters reportParameters) {
        this.reportParameters = reportParameters;
    }

    public Boolean getApi() {
        return api;
    }

    public void setApi(Boolean api) {
        this.api = api;
    }

    public String getHttpRequestor() {
        return httpRequestor;
    }

    public void setHttpRequestor(String httpRequestor) {
        this.httpRequestor = httpRequestor;
    }

}