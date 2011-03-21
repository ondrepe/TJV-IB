package cz.cvut.fel.ondrepe1.x36tjv.ib.web.bean.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ondrepe
 */
@ManagedBean
@RequestScoped
//@Named(value="bankCode")
public class BankCode {

    private int code;
    private String name;

    public BankCode() {
    }

    public BankCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
