public class Letter {
    private final char character;
    
    public Letter(char character) {
        this.character = character;
    }
    
    public char getCharacter() {
        return character;
    }
    
    public boolean isLetter() {
        return Character.isLetter(character);
    }
    
    public boolean isVowel() {
        char lowerChar = Character.toLowerCase(character);
        return lowerChar == 'a' || lowerChar == 'e' || lowerChar == 'i' || 
               lowerChar == 'o' || lowerChar == 'u' || lowerChar == 'y';
    }
    
    public Letter toLowerCase() {
        return new Letter(Character.toLowerCase(character));
    }
    
    public Letter toUpperCase() {
        return new Letter(Character.toUpperCase(character));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Letter letter = (Letter) obj;
        return character == letter.character;
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