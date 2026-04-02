package com.ezybytes.mcpserverstdio.repository;

import com.ezybytes.mcpserverstdio.entity.HelpDeskTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTicket, Long> {

    //metodo derivato  jpa-hibernate implementera' per me durante la compilazione.
    List<HelpDeskTicket> findByUsername(String username);
}
