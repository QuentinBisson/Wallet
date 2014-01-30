package jee.wallet.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Column
    private Double balance;

    @OneToMany
    private List<Transaction> transactions;

    public Wallet() {
        this.transactions = new ArrayList<Transaction>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        if (id != null) {
            return id.equals(((Wallet) that).id);
        }
        return super.equals(that);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    public List<DisplayableTransaction> getDisplayableTransactions() {
        List<DisplayableTransaction> result = new ArrayList<DisplayableTransaction>();
        for (Transaction t : transactions) {
            Company c = t.getStockOptions().get(0).getCompany();
            int actions = t.getStockOptions().size();
            DisplayableTransaction dt = new DisplayableTransaction(t.getId(), c, actions,
                    c.getLastSale() - t.getPrice(), t.getTransactionType(), t.getOperationType());
            result.add(dt);
        }
        return result;
    }

    public List<StockOption> getOptionsForCompany(Company c) {
        List<StockOption> options = new ArrayList<StockOption>();
        for (Transaction t : transactions) {
            Company tmp = t.getStockOptions().get(0)
                    .getCompany();
            if (c.equals(tmp) || c.getCode().equals(tmp.getCode())) {
                options.addAll(t.getStockOptions());
            }
        }
        return options;
    }

    public List<StockOption> getPurchaseOptionsForCompany(Company c) {
        List<StockOption> options = new ArrayList<StockOption>();
        for (Transaction t : transactions) {
            Company tmp = t.getStockOptions().get(0)
                    .getCompany();
            if (c.equals(tmp) || c.getCode().equals(tmp.getCode())) {
                if (t.getOperationType() == OperationType.PURCHASE) {
                    System.out.println("J'ajoute !!!");
                    options.addAll(t.getStockOptions());
                } else {
                    System.out.println("J'enleve !!!");
                    for(int i = 0; i< t.getStockOptions().size(); i++){
                        options.remove(0);
                    }
                }
            }
        }
        return options;
    }

}
