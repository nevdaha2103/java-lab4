import java.util.ArrayList;
import java.util.List;

public class Word {
    private final List<Letter> letters;
    
    public Word(List<Letter> letters) {
        if (letters == null || letters.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        this.letters = new ArrayList<>(letters);
    }
    
    public Word(String word) {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("Word string cannot be null or empty");
        }
        this.letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            letters.add(new Letter(c));
        }
    }
    
    public List<Letter> getLetters() {
        return new ArrayList<>(letters);
    }
    
    public int length() {
        return letters.size();
    }
    
    public Word toLowerCase() {
        List<Letter> lowerLetters = new ArrayList<>();
        for (Letter letter : letters) {
            lowerLetters.add(letter.toLowerCase());
        }
        return new Word(lowerLetters);
    }
    
    public Word toUpperCase() {
        List<Letter> upperLetters = new ArrayList<>();
        for (Letter letter : letters) {
            upperLetters.add(letter.toUpperCase());
        }
        return new Word(upperLetters);
    }
    
    public boolean startsWithVowel() {
        if (letters.isEmpty()) return false;
        return letters.get(0).isVowel();
    }
    
    public boolean startsWithConsonant() {
        if (letters.isEmpty()) return false;
        char firstChar = letters.get(0).getCharacter();
        return Character.isLetter(firstChar) && !letters.get(0).isVowel();
    }
    
    public int countVowels() {
        int count = 0;
        for (Letter letter : letters) {
            if (letter.isVowel()) {
                count++;
            }
        }
        return count;
    }
    
    public int countLetter(Letter letter) {
        int count = 0;
        for (Letter l : letters) {
            if (l.equals(letter)) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word other = (Word) obj;
        if (this.length() != other.length()) return false;
        for (int i = 0; i < letters.size(); i++) {
            if (!letters.get(i).equals(other.letters.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return letters.hashCode();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter.toString());
        }
        return sb.toString();
    }
}