package jee.wallet.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Temporal(TemporalType.DATE)
    @Column(name="history_date")
    private Date date;
    @Column(name = "history_open")
    private Float open;
    @Column(name = "history_high")
    private Float high;
    @Column(name="history_low")
    private Float low;
    @Column(name="history_close")
    private Float close;
    @Column(name="history_volume")
    private Long volume;
    @Column(name="history_adjClose")
    private Float adjClose;
    @ManyToOne
    private Company company;

    public History() {		
    }
	
    public History(String line) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] s = line.split(",");
        
        try {
            date = dateFormat.parse(s[0]);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        open = Float.valueOf(s[1]);
        high = Float.valueOf(s[2]);
        low = Float.valueOf(s[3]);
        close = Float.valueOf(s[4]);
        volume = Long.valueOf(s[5]);
        adjClose = Float.valueOf(s[6]);
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getOpen() {
        return open;
    }

    public void setOpen(Float open) {
        this.open = open;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Float getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Float adjClose) {
        this.adjClose = adjClose;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (id != null)
            result += "id: " + id;
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
            return id.equals(((History) that).id);
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