package slidingpuzzle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GamePieceTest {
    private GamePiece gamePiece;
    
    @Before
    public void setUp() throws InvalidLocationException {
        final int value = 5;
        final int row = 2;
        final int column = 1;
        gamePiece = new GamePiece(value, false);
    }   
    
    @Test
    public void canCreateInsance() throws InvalidLocationException {
        final int value = 5;
        final int row = 2;
        final int column = 1;
        GamePiece gamePiece = new GamePiece(value, false);
    }
    
    @Test
    public void canGetValue() {
        final int expectedValue = 5;
        int actualValue = gamePiece.getValue();
        
        assertEquals(expectedValue, actualValue);
    }        
   
    @Test
    public void toStringReturnsStringRepresentationOfGamePiece() {
        final String expectedRepresentation = 
                "+----+" + System.getProperty("line.separator")
              + "| 05 |" + System.getProperty("line.separator")
              + "+----+";
        String actualRepresentation = gamePiece.toString();
        
        assertEquals(expectedRepresentation, actualRepresentation);
    }
    
    @Test
    public void gamePiecesWithEqualValuesAreEqual() 
            throws InvalidLocationException {
        final int value = 5;
        final int row = 2;
        final int column = 1;
        GamePiece other = new GamePiece(value, false);
        
        assertEquals(gamePiece, other);
    }
    
    @Test
    public void gamePiecesWithUnEqualValuesAreNotEqual() 
            throws InvalidLocationException {
        final int value = 4;
        final int row = 2;
        final int column = 1;
        GamePiece other = new GamePiece(value, false);
        
        assertNotEquals(gamePiece, other);
    }
    
    @Test
    public void equalGamePiecesHaveEqualHashCodes() 
            throws InvalidLocationException {
        final int value = 5;
        final int row = 2;
        final int column = 1;
        GamePiece other = new GamePiece(value, false);
        
        int expectedHashCode = other.hashCode();
        int actualHashCode = gamePiece.hashCode();
        
        assertEquals(expectedHashCode, actualHashCode);
    }
    
    @Test
    public void unEqualGamePiecesHaveDifferentHashCodes() 
            throws InvalidLocationException {
        final int value = 4;
        final int row = 2;
        final int column = 1;
        GamePiece other = new GamePiece(value, false);
        
        int expectedHashCode = other.hashCode();
        int actualHashCode = gamePiece.hashCode();
        
        assertNotEquals(expectedHashCode, actualHashCode);
    }
}
