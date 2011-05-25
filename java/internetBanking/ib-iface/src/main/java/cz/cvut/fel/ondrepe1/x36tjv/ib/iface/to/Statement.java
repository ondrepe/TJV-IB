package cz.cvut.fel.ondrepe1.x36tjv.ib.iface.to;

import java.util.ArrayList;

/**
 *
 * @author ondrepe
 */
public class Statement extends CommonTO {
  
  private Account account;
  private Customer customer;
  private ArrayList<Transaction> transactions;

  
  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }
  
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(ArrayList<Transaction> transactions) {
    this.transactions = transactions;
  }
}
