package slidingpuzzle;

public class Location implements Comparable<Location> {
    private final int lowestRow = 0;
    private final int lowestColumn = 0;
    
    private final int row;
    private final int column;
    private final String name;
    
    public Location(int row, int column) throws InvalidLocationException {
        if(row < lowestRow) {
            throw new InvalidLocationException("Invalid row. The lowest "
                    + "possible row is " + lowestRow);
        }
        
        if(column < lowestColumn) {
            throw new InvalidLocationException("Invalid column. The lowest "
                    + "possible column is " + lowestColumn);
        }
        
        this.row = row;
        this.column = column;
        name = this.row + "x" + this.column;
    }

    public String getName() {
        return name;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Location)) {
            return false;
        }
        
        Location other = (Location)obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int compareTo(Location o) {
        return name.compareTo(o.name);
    }
}
