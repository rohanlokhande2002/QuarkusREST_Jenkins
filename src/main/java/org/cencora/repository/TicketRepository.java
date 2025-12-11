package org.cencora.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.cencora.model.Ticket;

@ApplicationScoped
public class TicketRepository implements PanacheRepository<Ticket> {
}
