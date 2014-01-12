package jee.wallet.model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Client extends User implements Serializable {
    
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Enumerated
    private ClientType type;
    @Enumerated
    private ClientStatusType status;
    @OneToOne
    private Wallet wallet;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public void setWallet(final Wallet wallet) {
        this.wallet = wallet;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public ClientStatusType getStatus() {
        return status;
    }

    public void setStatus(ClientStatusType status) {
        this.status = status;
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
        if (getId() != null) {
            return getId().equals(((Client) that).getId());
        }
        return super.equals(that);
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (firstName != null && !firstName.trim().isEmpty())
            result += "firstName: " + firstName;
        if (lastName != null && !lastName.trim().isEmpty())
            result += ", lastName: " + lastName;
        return result;
    }
}