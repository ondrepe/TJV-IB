package cz.cvut.fel.x36tjv.ondrepe1.centralbank.endpoint;

import cz.cvut.fel.x36tjv.ondrepe1.centralbankws.TransferRequest;
import cz.cvut.fel.x36tjv.ondrepe1.centralbankws.TransferResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author ondrepe
 */
@Endpoint
public class CentralBankWS {

    @PayloadRoot(localPart="transferRequest", namespace="http://fel.cvut.cz/x36tjv/ondrepe1/CentralBankWS/")
    @ResponsePayload
    public TransferResponse transfer(@RequestPayload TransferRequest request) {
        return new TransferResponse();
    }
}
