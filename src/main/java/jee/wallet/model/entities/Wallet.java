package jee.wallet.model.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.persistence.OneToOne;
import jee.wallet.model.entities.User;
import jee.wallet.model.entities.StockOption;
import javax.persistence.OneToMany;

@Entity
public class Wallet implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;

   @OneToOne
   private User user;

   @OneToMany
   private List<StockOption> stockOptions = new ArrayList<StockOption>();

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
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
         return id.equals(((Wallet) that).id);
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

   public User getUser()
   {
      return this.user;
   }

   public void setUser(final User user)
   {
      this.user = user;
   }

   public List<StockOption> getStockOptions()
   {
      return this.stockOptions;
   }

   public void setStockOptions(final List<StockOption> stockOptions)
   {
      this.stockOptions = stockOptions;
   }
}