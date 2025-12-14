public class Punctuation {
    private final char character;
    
    public Punctuation(char character) {
        this.character = character;
    }
    
    public char getCharacter() {
        return character;
    }
    
    public boolean isSentenceEnd() {
        return character == '.' || character == '!' || character == '?';
    }
    
    public boolean isQuestionMark() {
        return character == '?';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Punctuation that = (Punctuation) obj;
        return character == that.character;
    }
    
    @Override
    public int hashCode() {
        return Character.hashCode(character);
    }
    
    @Override
    public String toString() {
        return String.valueOf(character);
    }
}