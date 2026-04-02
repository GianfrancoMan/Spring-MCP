package com.ezybytes.mcpserverremote.config;

import com.ezybytes.mcpserverremote.tool.HelpDeskTools;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/*
* In questa classe di configurazione espongo i tool implementati nella classe HelpDeskTools*/
@Configuration
public class McpServerConfig {

    //Per esporre i tool di un server MCP si deve creare un Bean che restituisce
    // una lista di reference ToolCallBack
    @Bean
    List<ToolCallback> toolCallbacks(HelpDeskTools helpDeskTools) {
        return List.of(ToolCallbacks.from(helpDeskTools));
    }

}
