package slidingpuzzle;

import java.util.TreeMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {
    private GameBoard board;    
    
    @Before
    public void setUp() throws Exception {
        TreeMap<Location, GamePiece> pieces = 
                new TreeMap<>(generateConsecutivePieces(3));
        board = new GameBoard(3);
        board.place(pieces);
    } 
    
    private TreeMap<Location, GamePiece> generateConsecutivePieces(int size) 
            throws InvalidLocationException {
        TreeMap<Location, GamePiece> pieces = new TreeMap<>();
        int value = 0;
        for(int row = 0; row < size; ++row) {
            for(int column = 0; column < size; ++column) {
                Location location = new Location(row, column);
                GamePiece piece = new GamePiece(value, value == 0);
                pieces.put(location, piece);
                value++;
            }
        }
        return pieces;
    }
    
    @Test
    public void boardDimensionShouldEqual3x3ForABoardSizeOf3() {
        final String expectedBoardDimension = "3x3";
        String actualBoardDimension = board.getDimension();
        
        assertEquals(expectedBoardDimension, actualBoardDimension);
    }
    
    @Test
    public void gamePlayPrototyp() throws Exception {
        board = board.moveBlankPieceDown();
        board = board.moveBlankPieceRight();
        
        ConsoleSlidingPuzzle puzzle = new ConsoleSlidingPuzzle(board);
        System.out.println("Initial State");
        puzzle.print();
        
        System.out.println();
        
        // Player I: Play to win
        puzzle.movePieceRight();
        puzzle.movePieceLeft();
        puzzle.movePieceUp();
        puzzle.print();
        if(puzzle.isInWinState()) {
            System.out.println("I won :-)!!");
            return;
        } else {
            System.out.println("I did not win :-(");
        }
        
        System.out.println();
        
        // Player II: Play to win
        puzzle.movePieceDown();
        puzzle.movePieceRight();
        puzzle.movePieceDown();
        puzzle.print();
        if(puzzle.isInWinState()) {
            System.out.println("I won :-)!!");
        } else {
            System.out.println("I did not win :-(");
        }        
    }
}
