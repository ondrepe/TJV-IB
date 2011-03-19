package cz.cvut.fel.x36tjv.ondrepe1.centralbank.db.po;

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
    @NamedQuery(name = "TransferPO.findAll", query = "SELECT t FROM TransferPO t"),
    @NamedQuery(name = "TransferPO.findById", query = "SELECT t FROM TransferPO t WHERE t.idTransfer = :idTransfer"),
    @NamedQuery(name = "TransferPO.findByBankFrom", query = "SELECT t FROM TransferPO t WHERE t.bankFrom = :bankFrom"),
    @NamedQuery(name = "TransferPO.findByAccountFrom", query = "SELECT t FROM TransferPO t WHERE t.accountFrom = :accountFrom"),
    @NamedQuery(name = "TransferPO.findByBankTo", query = "SELECT t FROM TransferPO t WHERE t.bankTo = :bankTo"),
    @NamedQuery(name = "TransferPO.findByAccountTo", query = "SELECT t FROM TransferPO t WHERE t.accountTo = :accountTo"),
    @NamedQuery(name = "TransferPO.findByAmount", query = "SELECT t FROM TransferPO t WHERE t.amount = :amount"),
    @NamedQuery(name = "TransferPO.findByCurrency", query = "SELECT t FROM TransferPO t WHERE t.currency = :currency"),
    @NamedQuery(name = "TransferPO.findByDescription", query = "SELECT t FROM TransferPO t WHERE t.description = :description"),
    @NamedQuery(name = "TransferPO.findByDateTime", query = "SELECT t FROM TransferPO t WHERE t.transferTime = :transferTime")})
public class TransferPO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTransfer")
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long idTransfer;
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
    @Column(name = "transferTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferTime;

    public TransferPO() {
    }

    public TransferPO(Long id) {
        this.idTransfer = id;
    }

    public TransferPO(Long id, int bankFrom, String accountFrom, int bankTo, String accountTo, BigDecimal amount, String currency, String description, Date dateTime) {
        this.idTransfer = id;
        this.bankFrom = bankFrom;
        this.accountFrom = accountFrom;
        this.bankTo = bankTo;
        this.accountTo = accountTo;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.transferTime = dateTime;
    }

    public Long getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(Long idTransfer) {
        this.idTransfer = idTransfer;
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

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransfer != null ? idTransfer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferPO)) {
            return false;
        }
        TransferPO other = (TransferPO) object;
        if ((this.idTransfer == null && other.idTransfer != null) || (this.idTransfer != null && !this.idTransfer.equals(other.idTransfer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.x36tjv.ondrepe1.centralbank.po.Transfer[id=" + idTransfer + "]";
    }

}
