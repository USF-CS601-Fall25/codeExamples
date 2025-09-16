package aiagent.reflectiveAgent;

import dev.langchain4j.model.chat.ChatLanguageModel;

public class ReflectiveLibraryAgent {

    private final LibraryAssistant assistant;
    private final ChatLanguageModel model;

    public ReflectiveLibraryAgent(LibraryAssistant assistant, ChatLanguageModel model) {
        this.assistant = assistant;
        this.model = model;
    }

    public String pursueGoal(String goalDescription) {
        StringBuilder log = new StringBuilder();
        int iterations = 0;

        String lastOutput = "";
        String feedback = "None yet.";

        while (iterations < 3) {
            iterations++;

            // Step 1: act
            String attemptPrompt = """
                    You are a helpful library assistant.

                    Goal: %s
                    Previous attempt: %s
                    Feedback: %s

                    Try again, improving the result if needed.
                    """.formatted(goalDescription, lastOutput, feedback);

            String output = assistant.chat(attemptPrompt);
            log.append("Iteration ").append(iterations).append(":\n")
                    .append(output).append("\n");

            // Step 2: reflect
            String evalPrompt = """
                    Goal: %s
                    Output: %s

                    Did this fully achieve the goal? 
                    Answer YES if satisfied. If NO, explain briefly what is missing.
                    """.formatted(goalDescription, output);

            String evaluation = model.generate(evalPrompt);
            log.append("Evaluation: ").append(evaluation).append("\n\n");

            if (evaluation.toUpperCase().contains("YES")) {
                log.append("✅ Goal completed.\n");
                break;
            } else {
                log.append("⏳ Not satisfied, will try again...\n");
                feedback = evaluation;
                lastOutput = output;
            }
        }
        return log.toString();
    }
}