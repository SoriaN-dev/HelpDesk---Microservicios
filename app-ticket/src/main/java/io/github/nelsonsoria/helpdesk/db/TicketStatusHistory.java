package io.github.nelsonsoria.helpdesk.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket_status_history")
public class TicketStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old_status",length = 50)
    private String oldStatus;

    @Column(name = "new_status",length = 50)
    private String newStatus;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
