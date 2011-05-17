package cz.cvut.fel.ondrepe1.x36tjv.exchangeoffice.ws;

import java.math.BigDecimal;
import java.math.MathContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ondrepe
 */
@Stateless
@LocalBean
@Path("exchangerate/{currencyFromTo}")
public class EOResource {

  @Context
  private UriInfo uriInfo;

  @PersistenceContext
  private EntityManager em;
  
  @GET
  @Produces("application/xml")
  public ExchangeRate getRate(@PathParam("currencyFromTo") String currs) {
    if(currs == null) {
      throw new WebApplicationException(Status.NO_CONTENT);
    }
    if(currs.trim().length() != 6) {
      throw new WebApplicationException(Status.BAD_REQUEST);
    }
    String currFrom = currs.trim().substring(0, 3).toUpperCase();
    String currTo = currs.trim().substring(3).toUpperCase();
    BigDecimal rate = getRate(currFrom, currTo);
    return new ExchangeRate(currs, rate, uriInfo.getAbsolutePath());
  }
  
  private BigDecimal getRate(String from, String to) {
    ExchangeRatePO fromPo = em.find(ExchangeRatePO.class, from);
    ExchangeRatePO toPo = em.find(ExchangeRatePO.class, to);
    if(fromPo == null || toPo == null) {
      throw new WebApplicationException(Status.BAD_REQUEST);
    }
    BigDecimal fromRate = fromPo.getRate();
    BigDecimal toRate = toPo.getRate();
    BigDecimal rate = fromRate.divide(toRate, new MathContext(4));
    System.out.println("Rate " + from + " / " + to + " = " + rate.toPlainString());
    return rate;
  }
}
