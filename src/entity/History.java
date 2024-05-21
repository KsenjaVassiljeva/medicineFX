package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class History implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Medicine medicine;
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDate;
    private String action; // For example: "Stock Added", "Stock Removed", "Price Updated", etc.
    private int quantityChanged;
    private double priceChanged;
    @ManyToOne
    private User user;

    public History() {
    }

    public History(Medicine medicine, Date actionDate, String action, int quantityChanged, double priceChanged, User user) {
        this.medicine = medicine;
        this.actionDate = actionDate;
        this.action = action;
        this.quantityChanged = quantityChanged;
        this.priceChanged = priceChanged;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getQuantityChanged() {
        return quantityChanged;
    }

    public void setQuantityChanged(int quantityChanged) {
        this.quantityChanged = quantityChanged;
    }

    public double getPriceChanged() {
        return priceChanged;
    }

    public void setPriceChanged(double priceChanged) {
        this.priceChanged = priceChanged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.medicine);
        hash = 31 * hash + Objects.hashCode(this.actionDate);
        hash = 31 * hash + Objects.hashCode(this.action);
        hash = 31 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.medicine, other.medicine)) {
            return false;
        }
        if (!Objects.equals(this.actionDate, other.actionDate)) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", medicine=" + medicine +
                ", actionDate=" + actionDate +
                ", action='" + action + '\'' +
                ", quantityChanged=" + quantityChanged +
                ", priceChanged=" + priceChanged +
                ", user=" + user +
                '}';
    }
}
