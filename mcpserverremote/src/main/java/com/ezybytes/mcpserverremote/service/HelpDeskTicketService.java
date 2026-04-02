package com.ezybytes.mcpserverremote.service;

import com.ezybytes.mcpserverremote.entity.HelpDeskTicket;
import com.ezybytes.mcpserverremote.model.TicketRequest;
import com.ezybytes.mcpserverremote.repository.HelpDeskTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor //creerà un costrutore in base agli attributi che vengono dichiarati nella classe
//nella sostanza mi evita di dover scrivere il costruttore per iniettare i beans
public class HelpDeskTicketService {

    //iniettato automaticamento grazie alle funzionalità Lombok richiamate con @RequiredArgsConstructor
    private final HelpDeskTicketRepository helpdeskTicketRepository;

    //Crea un nuovo ticket da parte di un utente
    public HelpDeskTicket createTicket(TicketRequest ticketInput) {
        HelpDeskTicket ticket = HelpDeskTicket.builder()
                .issues(ticketInput.issue())
                .username(ticketInput.username())
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();
        return helpdeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpdeskTicketRepository.findByUsername(username);
    }

}
