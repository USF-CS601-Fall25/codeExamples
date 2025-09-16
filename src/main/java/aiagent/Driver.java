package aiagent;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Set your OPENAI_API_KEY env variable.");
            return;
        }

        // Set up domain
        LibraryCatalog catalog = new LibraryCatalog();
        catalog.addBook("The Hobbit", "J.R.R. Tolkien");
        catalog.addBook("1984", "George Orwell");
        catalog.addBook("Clean Code", "Robert C. Martin");

        // Set up model + tools
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")
                .temperature(0.2)
                .build();

        LibraryTools tools = new LibraryTools(catalog);

        LibraryAgent agent = AiServices.builder(LibraryAgent.class)
                .chatLanguageModel(model)
                .tools(tools)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        // User interface
        System.out.println("Library Agent ready. Type 'Q' to quit.");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine();
            if ("Q".equalsIgnoreCase(line)) break;
            System.out.println(agent.chat(line));
        }
    }
}