package slidingpuzzle;

import java.util.Objects;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameBoard {
    private final int maxSize = 9;
    private final int minSize = 3;
    private final int size;
    private final TreeMap<Location, GamePiece> pieces;
    private Location blankPieceLocation;

    public GameBoard(int size) throws Exception {
        if(size < minSize) {
            throw new Exception("Invalid board size. The minimum "
                    + "board size is " + minSize);
        }
        
        if(size > maxSize) {
            throw new Exception("Invalid board size. The maximum "
                    + "board size is " + maxSize);
        }

        blankPieceLocation = null;
        this.size = size;      
        this.pieces = new TreeMap<>();
    }
    
    public GameBoard(GameBoard board) {
        this.blankPieceLocation = board.blankPieceLocation;
        this.pieces = new TreeMap<>(board.pieces);
        this.size = board.size;
    }
    
    public String getDimension() {
        return size + "x" + size;
    }

    public int getOccupiedLocationsCount() {
        return pieces.size();
    }
    
    public int getSize() {
        return size;
    }
    
    public GamePiece getPieceAt(Location location) {
        return pieces.get(location);
    }
    
    public GameBoard moveBlankPieceUp() throws InvalidLocationException, Exception {
        Location up = adjacentTop();
        if(up == null) {
            throw new InvalidLocationException("Cannot move up");
        }
        
        return moveBlankPiece(up);
    }
    
    private GameBoard moveBlankPiece(Location location) throws Exception {
        TreeMap<Location, GamePiece> otherPieces = new TreeMap<>(pieces);
        GamePiece pieceAtLocation = otherPieces.get(location);
        GamePiece blankPiece = otherPieces.get(blankPieceLocation);
        
        otherPieces.put(blankPieceLocation, pieceAtLocation);
        otherPieces.put(location, blankPiece);
        
        GameBoard board = new GameBoard(size);
        board.place(otherPieces);
        return board;
    }
        
    public GameBoard moveBlankPieceDown() throws InvalidLocationException, Exception {
        Location down = adjacentBottom();
        if(down == null) {
            throw new InvalidLocationException("Cannot move down");
        }
        
        return moveBlankPiece(down);
    }
    
    public GameBoard moveBlankPieceLeft() throws InvalidLocationException, 
            Exception {
        Location left = adjacentLeft();
        if(left == null) {
            throw new InvalidLocationException("Cannot move left");
        }
        
        return moveBlankPiece(left);
    }
    
    public GameBoard moveBlankPieceRight() throws InvalidLocationException, 
            Exception {
        Location right = adjacentRight();
        if(right == null) {
            throw new InvalidLocationException("Cannot move right");
        }
        
        return moveBlankPiece(right);
    }
    
    private Location adjacentTop() throws InvalidLocationException {
        int row = blankPieceLocation.getRow() - 1;
        if(row < 0) {
            return null;
        }
        
        int column = blankPieceLocation.getColumn();
        return new Location(row, column);
    }
    
    private Location adjacentBottom() throws InvalidLocationException {
        int row = blankPieceLocation.getRow() + 1;
        if(row >= maxSize) {
            return null;
        }
        
        int column = blankPieceLocation.getColumn();
        return new Location(row, column);
    }
    
    private Location adjacentLeft() throws InvalidLocationException {
        int row = blankPieceLocation.getRow();
        int column = blankPieceLocation.getColumn() - 1;
        if(column < 0) {
            return null;
        }
        
        return new Location(row, column);
    }
    
    private Location adjacentRight() throws InvalidLocationException {
        int row = blankPieceLocation.getRow();
        int column = blankPieceLocation.getColumn() + 1;
        if(column >= maxSize) {
            return null;
        }
        
        return new Location(row, column);
    }
    
    public void place(TreeMap<Location, GamePiece> pieces) 
            throws InvalidLocationException, Exception {
        for(int row = 0; row < size; ++row) {
            for(int column = 0; column < size; ++column) {
                Location location = new Location(row, column);
                GamePiece piece = pieces.get(location);
                if(piece.isBlank()) {
                    if(blankPieceLocation != null) {
                        throw new Exception("There can be one and "
                                + "only one blank piece");
                    }
                    blankPieceLocation = location;
                }
                this.pieces.put(location, piece);
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GameBoard)) {
            return false;
        }
        
        GameBoard other = (GameBoard)obj;
        if(!getDimension().equals(other.getDimension())) {
            return false;
        }
        
        if(getOccupiedLocationsCount() != other.getOccupiedLocationsCount()) {
            return false;
        }
        
        for(int row = 0; row < size; ++row) {
            for(int column = 0; column < size; ++column) {
                try {
                    Location location = new Location(row, column);
                    GamePiece piece = pieces.get(location);
                    GamePiece otherPiece = other.getPieceAt(location);
                    if(!piece.equals(otherPiece)) {
                        return false;
                    }
                } catch (InvalidLocationException ex) {
                    Logger.getLogger(GameBoard.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.size;
        hash = 79 * hash + Objects.hashCode(this.pieces);
        return hash;
    }
}