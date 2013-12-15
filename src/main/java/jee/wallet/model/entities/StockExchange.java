package jee.wallet.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StockExchange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Column
    private String name;
    @ManyToMany
    private List<Company> companies;
    @OneToMany
    private List<StockOption> options;

    public StockExchange() {
        companies = new ArrayList<Company>();
        options = new ArrayList<StockOption>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Company> getCompanies() {
        return this.companies;
    }

    public void setCompanies(final List<Company> companies) {
        this.companies = companies;
    }

    public List<StockOption> getOptions() {
        return this.options;
    }

    public void setOptions(final List<StockOption> options) {
        this.options = options;
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
            return id.equals(((StockExchange) that).id);
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

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (name != null && !name.trim().isEmpty())
            result += "name: " + name;
        return result;
    }

}