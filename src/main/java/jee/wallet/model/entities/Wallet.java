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
    @OneToOne
    private User user;
    @OneToMany
    private List<Transaction> history = new ArrayList<Transaction>();

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public void setHistory(List<Transaction> history) {
        this.history = history;
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
}