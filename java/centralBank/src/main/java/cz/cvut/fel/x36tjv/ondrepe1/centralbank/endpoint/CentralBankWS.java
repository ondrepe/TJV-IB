package cz.cvut.fel.x36tjv.ondrepe1.centralbank.endpoint;

import cz.cvut.fel.x36tjv.ondrepe1.centralbank.db.dao.TransferDao;
import cz.cvut.fel.x36tjv.ondrepe1.centralbank.db.po.TransferPO;
import cz.cvut.fel.x36tjv.ondrepe1.centralbankws.TransferRequest;
import cz.cvut.fel.x36tjv.ondrepe1.centralbankws.TransferResponse;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    private TransferDao dao;

    @PayloadRoot(localPart="transferRequest", namespace="http://fel.cvut.cz/x36tjv/ondrepe1/CentralBankWS/")
    @ResponsePayload
    public TransferResponse transfer(@RequestPayload TransferRequest request) {
        boolean result = dao.setTransfer(request);
        TransferResponse response = new TransferResponse();
        response.setResult(result);
        return response;
    }

    public void setDao(TransferDao dao) {
        this.dao = dao;
    }
}
