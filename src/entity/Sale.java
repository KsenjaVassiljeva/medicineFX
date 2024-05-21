/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Sale implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Medicine medicine;
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
    private int quantitySold;

    public Sale() {
    }

    public Sale(Customer customer, Medicine medicine, Date saleDate, int quantitySold) {
        this.customer = customer;
        this.medicine = medicine;
        this.saleDate = saleDate;
        this.quantitySold = quantitySold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.customer);
        hash = 31 * hash + Objects.hashCode(this.medicine);
        hash = 31 * hash + Objects.hashCode(this.saleDate);
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
        final Sale other = (Sale) obj;
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.medicine, other.medicine)) {
            return false;
        }
        if (!Objects.equals(this.saleDate, other.saleDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customer=" + customer +
                ", medicine=" + medicine +
                ", saleDate=" + saleDate +
                ", quantitySold=" + quantitySold +
                '}';
    }
}
