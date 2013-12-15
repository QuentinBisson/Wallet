package jee.wallet.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String sector;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @OneToMany
    private List<History> history = new ArrayList<History>();
    @OneToMany
    private List<StockOption> options = new ArrayList<StockOption>();

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(final String sector) {
        this.sector = sector;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<History> getHistory() {
        return this.history;
    }

    public void setHistory(final List<History> history) {
        this.history = history;
    }

    public List<StockOption> getOptions() {
        return this.options;
    }

    public void setOptions(final List<StockOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (code != null && !code.trim().isEmpty())
            result += "code: " + code;
        if (name != null && !name.trim().isEmpty())
            result += ", name: " + name;
        if (sector != null && !sector.trim().isEmpty())
            result += ", sector: " + sector;
        return result;
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
            return id.equals(((Company) that).id);
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