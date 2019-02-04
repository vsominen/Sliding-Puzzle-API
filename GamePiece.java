package slidingpuzzle;

public class GamePiece {
    private final int value;
    private final boolean isBlank;
    
    public GamePiece(int value, boolean isEmpty) {
        this.value = value;
        this.isBlank = isEmpty;
    }

    public int getValue() {
        return value;
    }

    public boolean isBlank() {
        return isBlank;
    }
    
    @Override
    public String toString() {
        String representation = 
                "+----+" + System.getProperty("line.separator")
              + "| xx |" + System.getProperty("line.separator")
              + "+----+";
        if(isBlank) {
            representation = representation.replace("xx", "  ");
        } 
        else 
        {
            String valueRepresentation = (value + "").format("%02d", value);
            representation = representation.replace("xx", valueRepresentation);
        }
        return representation;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GamePiece)) {
            return false;
        }
        
        GamePiece other = (GamePiece)obj;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.value;
        return hash;
    }
}