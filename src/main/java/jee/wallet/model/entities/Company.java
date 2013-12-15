package jee.wallet.model.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import jee.wallet.model.entities.History;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import jee.wallet.model.entities.StockOption;

@Entity
public class Company implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String code;

   @Column
   private String name;

   @Column
   private String sector;

   @Temporal(TemporalType.DATE)
   private Date creationDate;

   @OneToMany
   private Set<History> history = new HashSet<History>();

   @OneToMany
   private Set<StockOption> options = new HashSet<StockOption>();

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((Company) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getCode()
   {
      return this.code;
   }

   public void setCode(final String code)
   {
      this.code = code;
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public String getSector()
   {
      return this.sector;
   }

   public void setSector(final String sector)
   {
      this.sector = sector;
   }

   public Date getCreationDate()
   {
      return this.creationDate;
   }

   public void setCreationDate(final Date creationDate)
   {
      this.creationDate = creationDate;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (code != null && !code.trim().isEmpty())
         result += "code: " + code;
      if (name != null && !name.trim().isEmpty())
         result += ", name: " + name;
      if (sector != null && !sector.trim().isEmpty())
         result += ", sector: " + sector;
      return result;
   }

   public Set<History> getHistory()
   {
      return this.history;
   }

   public void setHistory(final Set<History> history)
   {
      this.history = history;
   }

   public Set<StockOption> getOptions()
   {
      return this.options;
   }

   public void setOptions(final Set<StockOption> options)
   {
      this.options = options;
   }
}