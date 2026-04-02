package com.ezybytes.mcpserverstdio.tool;

import com.ezybytes.mcpserverstdio.entity.HelpDeskTicket;
import com.ezybytes.mcpserverstdio.model.TicketRequest;
import com.ezybytes.mcpserverstdio.service.HelpDeskTicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
/*in questa classe vengono implementati i metodi che rapresentano le funzionalita' del Server MCP
* che sto costruendo i metodi che rappresentano i tools vengono marcati con l'annotazione @Tool;
* Essendo che questo server è implementato in un'applicazione isolata non si può fare riferimento
* al Tool Context per recuperare i dati neccessari, ma devono essere passati dall'utente
*/
@Component
@RequiredArgsConstructor //creazione di un costruttore con i parametri individuati dai campi dichiarati nella classe
public class HelpDeskTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskTools.class);

    private final HelpDeskTicketService helpdeskTicketService;//Bean iniettato automaticamente nel costruttore creato da Lobmbok durante la compilazione.

    /*
     * Metodo Tool per creare il ticket richiesto dall'utente
     */
    @Tool(name = "createTicket", description = "Create the Support Ticket")
    String createTicket(
            @ToolParam(description = "Details to create a Support ticket") TicketRequest ticketRequest) {

        LOGGER.info("Creating ticket for user: {} with details: {}", ticketRequest.issue(), ticketRequest.username());
        HelpDeskTicket savedTicket = helpdeskTicketService.createTicket(ticketRequest);
        LOGGER.info("Ticket created successfully. Ticket ID: {}, username: {}", savedTicket.getId(), savedTicket.getUsername());

        String result = "Ticket #" + savedTicket.getId() + " created successfully for user " + savedTicket.getUsername();
        return result;
    }

    /*
     * Metodo Tool per recuperare i ticket di un utente, anche qui entra in gioco
     * il Tool Context
     */
    @Tool(name = "getTicketStatus", description = "Fetchs the status of the tickets based a given username")
    List<HelpDeskTicket> getTicketStatus(
            @ToolParam(description = "Username to fetch the status of the help desk tickets") String username) {

        List<HelpDeskTicket> tickets =  helpdeskTicketService.getTicketsByUsername(username);
        LOGGER.info("Found {} tickets for user: {}", tickets.size(), username);

        return tickets;
    }

}
