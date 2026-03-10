import java.util.Scanner;

public class AIChatbot {

    static class Chatbot {

        public String getResponse(String input) {

            input = input.toLowerCase();

            if (input.contains("hello") || input.contains("hi")) {
                return "Hello! How can I help you today?";
            }

            else if (input.contains("your name")) {
                return "I am an AI Chatbot created using Java.";
            }

            else if (input.contains("how are you")) {
                return "I am functioning perfectly. Thanks for asking!";
            }

            else if (input.contains("time")) {
                return "Sorry, I cannot access real time yet.";
            }

            else if (input.contains("help")) {
                return "You can ask me about greetings, my name, or general questions.";
            }

            else if (input.contains("bye")) {
                return "Goodbye! Have a great day!";
            }

            else {
                return "I'm not sure how to respond to that. Can you try asking something else?";
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Chatbot bot = new Chatbot();

        System.out.println("AI Chatbot Started!");
        System.out.println("Type 'bye' to exit.");

        while (true) {

            System.out.print("You: ");
            String userInput = sc.nextLine();

            String response = bot.getResponse(userInput);

            System.out.println("Bot: " + response);

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
        }

        sc.close();
    }
}