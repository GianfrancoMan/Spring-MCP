package com.ezybytes.mcpserverremote.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "helpdesk_tickets")
/**
 * Entity class for HelpdeskTicket
 * Rappresenta la tabella helpdesk_ticket del db
 */
public class HelpDeskTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String issues;
    private String status; //per esempio OPEN, CLOSED, IN_PROGRESS ...
    private LocalDateTime createdAt;
    private LocalDateTime eta; //eta = Estimated Time of Arrival (data di chiusura ticket prevista)


}
