package com.eazybytes.mcpclient.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MCPClientController {

    //dichiaro il chat client che configuro direttamente nel costruttore
    //di questa classe controller
    private final ChatClient chatClient;

    @Autowired
    public MCPClientController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
        //l'implementazione di ChatClient viene fornita dalla dipendenza open ai,
        // configuro l'advisor predefinito per avere il logs delle operazioni con l'LLM
        //Il Bean reference di ToolCallProvider viene creato allo startup dell'applicazione
        //sulla base delle proprietà configurate nell'application.yaml
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(toolCallbackProvider) //Qui inietto i  dettagli del tool implementato dall'mcp-server "FileSystem"
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    /*
    * Metodo REST basilare che prende il prompt dell'utente come parametro
    *  e restituisce la risposta dell'LLM model OpenAI
    */
    @GetMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestHeader(value = "username", required = false) String username,
            @RequestParam("message") String message) {

        String LLMResponse = chatClient.prompt().user(message + " My username is " + username).call().content();
        return ResponseEntity.ok(LLMResponse);
    }

}
