package aiagent;

import dev.langchain4j.service.SystemMessage;

public interface LibraryAgent {

    @SystemMessage("You are a helpful library assistant.")
    String chat(String userMessage);
}