package jee.wallet.model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Administrator extends User implements Serializable {

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (getId() != null)
            result += "id: " + getId();
        return result;
    }
}