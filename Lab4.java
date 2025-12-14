import java.util.List;

public class Lab4 {
    
    public static void main(String[] args) {
        try {
            System.out.println("=== LABORATORY WORK #4 ===");
            System.out.println("Topic: Relationships between classes in Java");
            System.out.println("Task: Modified version of Lab #2 with separate classes\n");
            
            String inputText = "How are you?   This is  the   first sentence.  " +
                             "What are you doing today?  This is the second sentence!  " +
                             "Were you at   the store?   Why do you ask?  " +
                             "This is the last sentence   without a   question mark.";
            
            System.out.println("Original text (with extra spaces):");
            System.out.println(inputText);
            System.out.println();
            
            Text text = new Text(inputText);
            
            System.out.println("Normalized text (single spaces):");
            Text normalizedText = text.normalizeSpaces();
            System.out.println(normalizedText.toString());
            System.out.println();
            
            int wordLength = 3;
            System.out.println("Searching for words with length " + wordLength + " in questions");
            
            List<Word> uniqueWords = text.findUniqueWordsOfLengthInQuestions(wordLength);
            
            System.out.println("\n=== RESULT ===");
            if (uniqueWords.isEmpty()) {
                System.out.println("No words of length " + wordLength + " found in questions.");
            } else {
                System.out.println("Unique words with length " + wordLength + " from questions:");
                int count = 1;
                for (Word word : uniqueWords) {
                    System.out.println(count + ". " + word.toString());
                    count++;
                }
                System.out.println("\nTotal unique words found: " + uniqueWords.size());
            }
            
            System.out.println("\n=== ADDITIONAL INFO ===");
            System.out.println("Total sentences: " + text.getSentenceCount());
            System.out.println("Question sentences: " + text.getQuestionSentences().size());
            System.out.println("Total words in text: " + text.getAllWords().size());
            
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}