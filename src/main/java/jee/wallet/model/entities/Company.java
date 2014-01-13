package jee.wallet.model.entities;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.StringUtils;

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
    @Column
    private Float lastSale;
    @Column
    private Double marketCap;
    @Column
    private Long adrTso;
    @Column
    private Integer ipoYear;
    @Column
    private String industry;
    @Column
    private String summaryQuote;
    @OneToMany
    private List<History> history;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<StockExchange> stockExchanges;
    @OneToMany(cascade=CascadeType.ALL)
    private List<StockOption> options;

    public Company() {
        history = new ArrayList<History>();
        options = new ArrayList<StockOption>();
        stockExchanges = new ArrayList<StockExchange>();
    }

    public Company(String line) {
        history = new ArrayList<History>();
        options = new ArrayList<StockOption>();
        stockExchanges = new ArrayList<StockExchange>();
    
        List<String> i = Lists.newArrayList(
                Splitter.on("\",\"").split(line));
        
        code = i.get(0).replaceAll("\"", "");
        name = i.get(1);
        
        String tmp = i.get(2);
        if (StringUtils.isNumeric(tmp)) {
            lastSale = Float.valueOf(tmp);
        }
        
        tmp = i.get(3);
        if (StringUtils.isNumeric(tmp)) {
            marketCap = Double.valueOf(tmp);
        }
        
        tmp = i.get(4);
        if (StringUtils.isNumeric(tmp)) {
            adrTso = Long.valueOf(tmp);
        }
        
        tmp = i.get(5);
        if (StringUtils.isNumeric(tmp)) {
            ipoYear = Integer.valueOf(tmp);
        }
        
        sector = i.get(6);
        industry = i.get(7);
        summaryQuote = i.get(8).replaceAll("\",", "");
    }

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

    public Float getLastSale() {
        return lastSale;
    }

    public void setLastSale(Float lastSale) {
        this.lastSale = lastSale;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public Long getAdrTso() {
        return adrTso;
    }

    public void setAdrTso(Long adrTso) {
        this.adrTso = adrTso;
    }

    public Integer getIpoYear() {
        return ipoYear;
    }

    public void setIpoYear(Integer ipoYear) {
        this.ipoYear = ipoYear;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSummaryQuote() {
        return summaryQuote;
    }

    public void setSummaryQuote(String summaryQuote) {
        this.summaryQuote = summaryQuote;
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

    public List<StockExchange> getStockExchanges() {
        return stockExchanges;
    }

    public void setStockExchanges(List<StockExchange> stockExchanges) {
        this.stockExchanges = stockExchanges;
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