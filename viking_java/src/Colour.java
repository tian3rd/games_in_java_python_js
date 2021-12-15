public enum Colour {
    RED('R'), GREEN('G'), BLUE('B'), YELLOW('Y');

    private final char characterRep;

    Colour(char characterRep) {
        this.characterRep = characterRep;
    }

    public char toChar() { 
        return characterRep; 
    }
    
}
