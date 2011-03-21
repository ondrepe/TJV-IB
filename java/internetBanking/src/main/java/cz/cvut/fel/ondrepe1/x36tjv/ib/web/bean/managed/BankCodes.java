package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.managed;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
public class BankCodes {

    public BankCodes() {
    }

    public List<BankCode> getBanks() {
        ArrayList<BankCode> codes = new ArrayList<BankCode>();
        //codes.add(new BankCode("123", "Komercka"));
        //codes.add(new BankCode("456", "AirBank"));
        //codes.add(new BankCode("789", "Raiffaisen"));
        codes.add(new BankCode(123, "Komercka"));
        codes.add(new BankCode(456, "AirBank"));
        codes.add(new BankCode(789, "Raiffaisen"));
        return codes;
    }

    public List<String> getNames() {
        ArrayList<String> codes = new ArrayList<String>();
        codes.add("Komercka");
        codes.add("AirBank");
        codes.add("Raiffaisen");
        return codes;
    }

}
