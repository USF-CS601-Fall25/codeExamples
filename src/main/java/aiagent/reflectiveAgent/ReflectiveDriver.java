package aiagent.reflectiveAgent;

import aiagent.LibraryCatalog;
import aiagent.LibraryTools;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class ReflectiveDriver {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        catalog.addBook("The Hobbit", "J.R.R. Tolkien");
        catalog.addBook("1984", "George Orwell");

        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Please set OPENAI_API_KEY environment variable.");
            System.exit(1);
        }
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")
                .temperature(0.2)
                .build();

        // expose tools
        LibraryAssistant assistant = AiServices.builder(LibraryAssistant.class)
                .chatLanguageModel(model)
                .tools(new LibraryTools(catalog))
                .build();

        // reflection wrapper
        ReflectiveLibraryAgent reflectiveAgent = new ReflectiveLibraryAgent(assistant, model);

        String goal = "Show me all the available books.";
        String result = reflectiveAgent.pursueGoal(goal);
        System.out.println(result);
    }
}
