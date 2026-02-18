package io.github.nelsonsoria.helpdesk.repository;

import io.github.nelsonsoria.helpdesk.db.Ticket;
import io.github.nelsonsoria.helpdesk.enums.Priority;
import io.github.nelsonsoria.helpdesk.enums.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class TicketRepository implements PanacheRepositoryBase<Ticket,Long> {

    public Long countByStatus(Long employeeid , Status status){
        return count("employee=?1 and status=?2",employeeid,status);
    };
    public Long countByPriority(Long employeeid , Priority priority){
        return count("employee=?1 and priority=?2",employeeid,priority);
    };
    public Optional<Ticket> findLastByEmployee(Long employeeId) {
        return find("employee=?1 ORDER BY createdAt DESC", employeeId)
                .firstResultOptional();
    }


}
