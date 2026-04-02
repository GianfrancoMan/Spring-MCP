package com.ezybytes.mcpserverremote.repository;

import com.ezybytes.mcpserverremote.entity.HelpDeskTicket;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTicket, Long> {

    //metodo derivato  jpa-hibernate implementera' per me durante la compilazione.
    List<HelpDeskTicket> findByUsername(String username);
}
