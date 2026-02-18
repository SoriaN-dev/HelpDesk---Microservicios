package io.github.nelsonsoria.helpdesk.db;

import io.github.nelsonsoria.helpdesk.enums.Priority;
import io.github.nelsonsoria.helpdesk.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    public Status status;;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "employee_id")
    private Long employee;

    @Column(name = "asset_id")
    private Long asset;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TicketComment> ticketComments;

    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TicketStatusHistory> ticketStatusHistories;

}
