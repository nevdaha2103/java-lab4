import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private final List<Object> elements;
    
    public Sentence(List<Object> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("Sentence cannot be null or empty");
        }
        this.elements = new ArrayList<>(elements);
    }
    
    public Sentence(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            throw new IllegalArgumentException("Sentence string cannot be null or empty");
        }
        this.elements = parseSentence(sentence);
    }
    
    private List<Object> parseSentence(String sentence) {
        List<Object> result = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            
            if (Character.isLetter(c) || c == '\'' || c == '-') {
                currentWord.append(c);
            } else {
                if (currentWord.length() > 0) {
                    result.add(new Word(currentWord.toString()));
                    currentWord = new StringBuilder();
                }
                
                if (!Character.isWhitespace(c) && c != '\t') {
                    result.add(new Punctuation(c));
                }
            }
        }
        
        if (currentWord.length() > 0) {
            result.add(new Word(currentWord.toString()));
        }
        
        return result;
    }
    
    public List<Object> getElements() {
        return new ArrayList<>(elements);
    }
    
    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (Object element : elements) {
            if (element instanceof Word) {
                words.add((Word) element);
            }
        }
        return words;
    }
    
    public int getWordCount() {
        return getWords().size();
    }
    
    public boolean isQuestion() {
        if (elements.isEmpty()) return false;
        
        Object lastElement = elements.get(elements.size() - 1);
        if (lastElement instanceof Punctuation) {
            Punctuation punctuation = (Punctuation) lastElement;
            return punctuation.isQuestionMark();
        }
        
        return false;
    }
    
    public List<Word> findWordsOfLength(int length) {
        List<Word> result = new ArrayList<>();
        for (Word word : getWords()) {
            if (word.length() == length) {
                result.add(word);
            }
        }
        return result;
    }
    
    public Sentence normalizeSpaces() {
        List<Object> normalized = new ArrayList<>();
        boolean lastWasSpace = false;
        
        for (Object element : elements) {
            if (element instanceof Word) {
                normalized.add(element);
                lastWasSpace = false;
            } else if (element instanceof Punctuation) {
                Punctuation p = (Punctuation) element;
                if (!normalized.isEmpty() && !lastWasSpace) {
                    if (p.getCharacter() != ',' && p.getCharacter() != ';' && 
                        p.getCharacter() != ':' && p.getCharacter() != '.') {
                        lastWasSpace = true;
                    }
                }
                normalized.add(element);
            }
        }
        
        return new Sentence(normalized);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();  // Використовуємо StringBuilder
        for (int i = 0; i < elements.size(); i++) {
            Object element = elements.get(i);
            sb.append(element.toString());
            
            if (i < elements.size() - 1) {
                Object nextElement = elements.get(i + 1);
                if (element instanceof Word && nextElement instanceof Word) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}