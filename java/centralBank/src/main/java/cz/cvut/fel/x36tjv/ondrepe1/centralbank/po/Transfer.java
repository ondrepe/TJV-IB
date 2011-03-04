package cz.cvut.fel.x36tjv.ondrepe1.centralbank.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ondrepe
 */
@Entity
@Table(name = "transfer")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t"),
    @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id"),
    @NamedQuery(name = "Transfer.findByBankFrom", query = "SELECT t FROM Transfer t WHERE t.bankFrom = :bankFrom"),
    @NamedQuery(name = "Transfer.findByAccountFrom", query = "SELECT t FROM Transfer t WHERE t.accountFrom = :accountFrom"),
    @NamedQuery(name = "Transfer.findByBankTo", query = "SELECT t FROM Transfer t WHERE t.bankTo = :bankTo"),
    @NamedQuery(name = "Transfer.findByAccountTo", query = "SELECT t FROM Transfer t WHERE t.accountTo = :accountTo"),
    @NamedQuery(name = "Transfer.findByAmount", query = "SELECT t FROM Transfer t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transfer.findByCurrency", query = "SELECT t FROM Transfer t WHERE t.currency = :currency"),
    @NamedQuery(name = "Transfer.findByDescription", query = "SELECT t FROM Transfer t WHERE t.description = :description"),
    @NamedQuery(name = "Transfer.findByDateTime", query = "SELECT t FROM Transfer t WHERE t.dateTime = :dateTime")})
public class Transfer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "bankFrom")
    private int bankFrom;
    @Basic(optional = false)
    @Column(name = "accountFrom")
    private String accountFrom;
    @Basic(optional = false)
    @Column(name = "bankTo")
    private int bankTo;
    @Basic(optional = false)
    @Column(name = "accountTo")
    private String accountTo;
    @Basic(optional = false)
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "currency")
    private String currency;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Transfer(Integer id, int bankFrom, String accountFrom, int bankTo, String accountTo, BigDecimal amount, String currency, String description, Date dateTime) {
        this.id = id;
        this.bankFrom = bankFrom;
        this.accountFrom = accountFrom;
        this.bankTo = bankTo;
        this.accountTo = accountTo;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBankFrom() {
        return bankFrom;
    }

    public void setBankFrom(int bankFrom) {
        this.bankFrom = bankFrom;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getBankTo() {
        return bankTo;
    }

    public void setBankTo(int bankTo) {
        this.bankTo = bankTo;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.ondrepe1.centralbank.po.Transfer[id=" + id + "]";
    }

}
