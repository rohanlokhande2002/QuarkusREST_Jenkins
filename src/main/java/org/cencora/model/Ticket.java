package org.cencora.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="ticket")
public class Ticket extends PanacheEntityBase {
    @Id
    public Integer id;
    public String description;
    public LocalDate raisedon;

    static final double pi = 3.14;

    public Ticket() {
    }

    public Ticket(Integer id, String description, LocalDate raisedon) {
        this.id = id;
        this.description = description;
        this.raisedon = raisedon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return raisedon;
    }

    public void setDate(LocalDate raisedon) {
        this.raisedon = raisedon;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateTime=" + raisedon +
                '}';
    }
}
