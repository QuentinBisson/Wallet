package jee.wallet.model.entities;

import java.io.Serializable;

/**
 *
 * @author Quentin
 */
public class DisplayableTransaction implements Serializable {
    
    private Company company;
    private long actions;
    private double total;
    private TransactionType type;
    private long transactionId;
    private OperationType operationType;

    public DisplayableTransaction() {
    }
    
    public DisplayableTransaction(long id, Company company, long actions,
            double value, TransactionType type, OperationType op) {
        transactionId = id;
        this.company = company;
        this.actions = actions;
        total = actions * value;
        this.type = type;
        this.operationType = op;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public long getActions() {
        return actions;
    }

    public void setActions(long actions) {
        this.actions = actions;
    }

    public double getTotal() {
        return total;
    }

    public TransactionType getType() {
        return type;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
    
    public long getTransactionId() {
        return transactionId;
    }

    public OperationType getOperationType() {
        return operationType;
    }
    
}
