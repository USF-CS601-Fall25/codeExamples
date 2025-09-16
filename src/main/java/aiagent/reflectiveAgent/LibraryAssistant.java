package aiagent.reflectiveAgent;

import dev.langchain4j.service.SystemMessage;

/**
 * Dynamic proxy interface built by AiServices.
 * The LLM will call tool methods (from LibraryTools) when useful.
 */
public interface LibraryAssistant {

    @SystemMessage("You are a helpful library assistant. " +
            "When appropriate, use tools to list available books or check out a title.")
    String chat(String userMessage);
}
