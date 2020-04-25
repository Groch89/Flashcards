package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, String> flashcardsMap = new LinkedHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static void menu() {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = scanner.nextLine();
            switch (action) {
                case "add":
                    addCards();
                    System.out.println("");
                    break;
                case "remove":
                    removeCard();
                    System.out.println("");
                    break;
                case "import":
                    loadCardsFromFile();
                    System.out.println("");
                    break;
                case "export":
                    saveAsFile();
                    System.out.println("");
                    break;
                case "ask":
                    checkKnowledge();
                    System.out.println("");
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    isRunning = false;
                    break;
                default:
                    break;
            }
        }
    }

    private static void checkKnowledge() {
        System.out.println("How many times to ask?");
        int timesToAsk = Integer.parseInt(scanner.nextLine());
        int timesAsked = 0;

        while (timesToAsk > timesAsked) {
            for (var content : flashcardsMap.entrySet()) {
                System.out.println("Print the definition of \"" + content.getKey() + "\":");
                timesAsked++;

                String usersAnswer = scanner.nextLine();
                if (usersAnswer.equals(content.getValue())) {
                    System.out.println("Correct answer.");
                } else if (flashcardsMap.containsValue(usersAnswer)) {
                    for (Map.Entry<String, String> entry : flashcardsMap.entrySet()) {
                        if (entry.getValue().equalsIgnoreCase(usersAnswer)) {
                            System.out.println("Wrong answer. The correct one is \"" + content.getValue() +
                                    "\", you've just written the definition of \"" + entry.getKey() + "\".");
                        }
                    }
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + content.getValue() + "\".");
                }
            }
        }
    }

    private static void saveAsFile() {
        System.out.println("File name:");
        String saveAs = scanner.nextLine();
        File file = new File(saveAs);
        int numberOfPairs = 0;

        try (FileWriter writer = new FileWriter(file)) {
            for (var entry : flashcardsMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
                numberOfPairs++;
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
        System.out.println(numberOfPairs + " cards have been saved.");
    }

    private static void loadCardsFromFile() {
        System.out.println("File name:");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        int numberOfLoadedCards = 0;

        try (Scanner sc = new Scanner(file).useDelimiter(":|\\n")) {
            while (sc.hasNext()) {
                String key = sc.next();
                String value = sc.next();
                flashcardsMap.put(key, value);
                numberOfLoadedCards++;
            }
            System.out.println(numberOfLoadedCards + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void removeCard() {
        System.out.println("The card:");
        String cardToRemove = scanner.nextLine();
        if (flashcardsMap.containsKey(cardToRemove)) {
            flashcardsMap.remove(cardToRemove);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + cardToRemove + "\": there is no such card.");
        }
    }

    private static void addCards() {
        System.out.println("The card:");
        String card = scanner.nextLine();

        if (flashcardsMap.containsKey(card)) {
            System.out.println("The card " + card + " already exists.");
            return;
        }

        System.out.println("The definition of the card:");
        String definition = scanner.nextLine();
        if (flashcardsMap.containsValue(definition)) {
            System.out.println("The definition " + definition + " already exists.");
            return;
        }

        flashcardsMap.put(card, definition);
        System.out.println("The pair \"" + card + "\":\"" + definition + "\" has been added.");

    }

    public static void main(String[] args) {

        menu();


/*

    public static jakasMetoda() {
        flashcardsMap = new LinkedHashMap<>();
        System.out.println("Input the number of cards: ");
        int numberOfCards = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= numberOfCards; i++) {
            System.out.println("The card #" + i + ":");
            String term = scanner.nextLine();

            while (flashcardsMap.containsKey(term)) {
                System.out.println("The card \"" + term + "\" already exists. Try again:");
                term = scanner.nextLine();
            }

            System.out.println("The definition of the card #" + i + ":");
            String definition = scanner.nextLine();

            while (flashcardsMap.containsValue(definition)) {
                System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                definition = scanner.nextLine();
            }

            flashcardsMap.put(term, definition);
        }

        for (Map.Entry<String, String> content : flashcardsMap.entrySet()) {
            System.out.println("Print the definition of \"" + content.getKey() + "\":");

            String usersAnswer = scanner.nextLine();
            if (usersAnswer.equals(content.getValue())) {
                System.out.println("Correct answer.");
            } else if (flashcardsMap.containsValue(usersAnswer)){
                for (Map.Entry<String, String> entry : flashcardsMap.entrySet()) {
                    if (entry.getValue().equalsIgnoreCase(usersAnswer)) {
                        System.out.println("Wrong answer. The correct one is \"" + content.getValue() + "\", you've just written the definition of \"" + entry.getKey() + "\".");
                    }
                }
            } else {
                System.out.println("Wrong answer. The correct one is \"" + content.getValue() + "\".");
            }
        }

 */
    }
}
