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

@Entity
public class User implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String username;

   @Column
   private String password;

   @Column
   private String salt;

   @Temporal(TemporalType.DATE)
   private Date lastConnection;

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
         return id.equals(((User) that).id);
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

   public String getUsername()
   {
      return this.username;
   }

   public void setUsername(final String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return this.password;
   }

   public void setPassword(final String password)
   {
      this.password = password;
   }

   public String getSalt()
   {
      return this.salt;
   }

   public void setSalt(final String salt)
   {
      this.salt = salt;
   }

   public Date getLastConnection()
   {
      return this.lastConnection;
   }

   public void setLastConnection(final Date lastConnection)
   {
      this.lastConnection = lastConnection;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (username != null && !username.trim().isEmpty())
         result += "username: " + username;
      if (password != null && !password.trim().isEmpty())
         result += ", password: " + password;
      if (salt != null && !salt.trim().isEmpty())
         result += ", salt: " + salt;
      return result;
   }
}