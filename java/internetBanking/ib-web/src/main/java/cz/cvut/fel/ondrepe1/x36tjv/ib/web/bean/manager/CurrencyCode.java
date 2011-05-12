/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class CurrencyCode {

    private String code;
    private String name;
    private Integer decimalDigits;

    public CurrencyCode() {
    }

    public CurrencyCode(String code, String name, Integer decimalDigits) {
        this.code = code;
        this.name = name;
        this.decimalDigits = decimalDigits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CurrencyCode> getCodes() {
        ArrayList<CurrencyCode> list = new ArrayList<CurrencyCode>();
        list.add(new CurrencyCode("CZK", "Koruna èeská", 2));
        list.add(new CurrencyCode("USD", "Americký dolar", 2));
        list.add(new CurrencyCode("EUR", "Euro", 2));
        return list;
    }

    public List<String> getNames() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Koruna èeská");
        list.add("Americký dolar");
        list.add("Euro");
        return list;
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

}
