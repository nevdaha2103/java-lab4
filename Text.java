import java.util.ArrayList;
import java.util.List;

public class Text {
    private final List<Sentence> sentences;
    
    public Text(List<Sentence> sentences) {
        if (sentences == null) {
            throw new IllegalArgumentException("Sentences cannot be null");
        }
        this.sentences = new ArrayList<>(sentences);
    }
    
    public Text(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text string cannot be null or empty");
        }
        this.sentences = parseText(text);
    }
    
    private List<Sentence> parseText(String text) {
        List<Sentence> result = new ArrayList<>();
        StringBuilder currentSentence = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            currentSentence.append(c);
            
            if (c == '.' || c == '!' || c == '?') {
                String sentenceStr = currentSentence.toString().trim();
                if (!sentenceStr.isEmpty()) {
                    result.add(new Sentence(sentenceStr));
                }
                currentSentence = new StringBuilder();
                
                while (i + 1 < text.length() && Character.isWhitespace(text.charAt(i + 1))) {
                    i++;
                }
            }
        }
        
        String lastSentence = currentSentence.toString().trim();
        if (!lastSentence.isEmpty()) {
            result.add(new Sentence(lastSentence));
        }
        
        return result;
    }
    
    public List<Sentence> getSentences() {
        return new ArrayList<>(sentences);
    }
    
    public int getSentenceCount() {
        return sentences.size();
    }
    
    public List<Word> getAllWords() {
        List<Word> allWords = new ArrayList<>();
        for (Sentence sentence : sentences) {
            allWords.addAll(sentence.getWords());
        }
        return allWords;
    }
    
    public List<Sentence> getQuestionSentences() {
        List<Sentence> questions = new ArrayList<>();
        for (Sentence sentence : sentences) {
            if (sentence.isQuestion()) {
                questions.add(sentence);
            }
        }
        return questions;
    }
    
    public List<Word> findUniqueWordsOfLengthInQuestions(int length) {
        List<Word> result = new ArrayList<>();
        List<String> seenWords = new ArrayList<>();
        
        for (Sentence sentence : getQuestionSentences()) {
            List<Word> wordsOfLength = sentence.findWordsOfLength(length);
            for (Word word : wordsOfLength) {
                String wordStr = word.toLowerCase().toString();
                if (!seenWords.contains(wordStr)) {
                    seenWords.add(wordStr);
                    result.add(word);
                }
            }
        }
        
        return result;
    }
    
    public Text normalizeSpaces() {
        List<Sentence> normalizedSentences = new ArrayList<>();
        for (Sentence sentence : sentences) {
            normalizedSentences.add(sentence.normalizeSpaces());
        }
        return new Text(normalizedSentences);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentences.size(); i++) {
            sb.append(sentences.get(i).toString());
            if (i < sentences.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}