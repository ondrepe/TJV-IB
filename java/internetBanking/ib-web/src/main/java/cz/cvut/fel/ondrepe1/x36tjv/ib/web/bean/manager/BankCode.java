package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.manager;

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
public class BankCode {

    private Integer code;
    private String name;

    public BankCode() {
    }

    public BankCode(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String add() {
        System.out.println("name:" + name);
        System.out.println("code: "+ code);
        return null;
    }

    public String delete() {
        System.out.println("name:" + name);
        return null;
    }

    public List<BankCode> getBanks() {
        ArrayList<BankCode> codes = new ArrayList<BankCode>();
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

    public boolean validateCode() {
        return false;
    }
}
