package jee.wallet.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Wallet_Transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Column
    private double price;
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @Enumerated
    private OperationType operationType;
    @Enumerated
    private TransactionType transactionType;
    @OneToMany(cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<StockOption> stockOptions;

    public Transaction() {
        this.stockOptions = new ArrayList<StockOption>();
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<StockOption> getStockOptions() {
        return this.stockOptions;
    }

    public void setStockOptions(final List<StockOption> stockOptions) {
        this.stockOptions = stockOptions;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType type) {
        this.operationType = type;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }

        Transaction that = (Transaction) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (id != null) {
            result += "id: " + id;
        }
        return result;
    }
}
