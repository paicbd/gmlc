package org.mobicents.gmlc.slee.smpp;

import com.cloudhopper.commons.util.windowing.WindowFuture;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.DeliverSmResp;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.type.Address;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import org.apache.log4j.Logger;

public class ClientSmppSessionHandler extends DefaultSmppSessionHandler {

    protected Logger logger = Logger.getLogger(ClientSmppSessionHandler.class);
    private SmppSession session;

    public ClientSmppSessionHandler() {

    }

    public void setSession(SmppSession session) {
        this.session = session;
    }

    @Override
    public void firePduRequestExpired(PduRequest pduRequest) {
        logger.warn("PDU request expired: {}" + pduRequest);
    }

    @Override
    public PduResponse firePduRequestReceived(PduRequest pduRequest) {
      PduResponse response = pduRequest.createResponse();

        if (pduRequest.getCommandId() == SmppConstants.CMD_ID_SUBMIT_SM) {
            SubmitSm mt = (SubmitSm) pduRequest;
            Address mtSourceAddress = mt.getSourceAddress();
            Address mtDestinationAddress = mt.getDestAddress();
            byte dataCoding = mt.getDataCoding();
            byte[] shortMessage = mt.getShortMessage();
            sendMoMessage(session, mtDestinationAddress, mtSourceAddress, shortMessage, dataCoding);
        }

        return response;
    }

    private void sendMoMessage(SmppSession session, Address moSourceAddress, Address moDestinationAddress, byte[] textBytes, byte dataCoding) {

        DeliverSm deliver = new DeliverSm();

        deliver.setSourceAddress(moSourceAddress);
        deliver.setDestAddress(moDestinationAddress);
        deliver.setDataCoding(dataCoding);
        try {
            deliver.setShortMessage(textBytes);
        } catch (Exception e) {
            logger.error("Error!", e);
        }

        sendRequestPdu(session, deliver);
    }

    private void sendRequestPdu(SmppSession session, DeliverSm deliver) {
        try {
            WindowFuture<Integer, PduRequest, PduResponse> future = session.sendRequestPdu(deliver, 10000, false);
            if (!future.await()) {
                logger.error("Failed to receive deliver_sm_resp within specified time");
            } else if (future.isSuccess()) {
                DeliverSmResp deliverSmResp = (DeliverSmResp) future.getResponse();
                logger.info("deliver_sm_resp: commandStatus [" + deliverSmResp.getCommandStatus() + "=" + deliverSmResp.getResultMessage() + "]");
            } else {
                logger.error("Failed to properly receive deliver_sm_resp: " + future.getCause());
            }
        } catch (Exception e) {
            logger.error("Error sending RequestPdu: " + e.getMessage());
        }
    }
}
