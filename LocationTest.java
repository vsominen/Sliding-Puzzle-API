package slidingpuzzle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    private Location location;

    @Before
    public void setUp()
            throws InvalidLocationException {
        int row = 0;
        int column = 0;
        location = new Location(row, column);
    }

    @Test
    public void canCreateInstance()
            throws InvalidLocationException {
        int row = 4;
        int column = 4;
        Location newLocation = new Location(row, column);
    }

    @Test(expected = InvalidLocationException.class)
    public void throwsInvalidLocationExceptionForRowLessThanZero()
            throws InvalidLocationException {
        final int row = -1;
        final int column = 0;

        Location other = new Location(row, column);
    }
    
    @Test(expected = InvalidLocationException.class)
    public void throwsInvalidLocationExceptionForColumnLessThanZero()
            throws InvalidLocationException {
        final int row = 5;
        final int column = -1;

        Location other = new Location(row, column);
    }

    @Test
    public void canGetName() {
        final String expectedName = "0x0";
        String actualName = location.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void toStringShouldEqualName() {
        String expectedToString = location.getName();
        String actualToString = location.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void hashCodeShouldEqualNameHashCode() {
        int expectedHashCode = location.getName().hashCode();
        int actualHashCode = location.hashCode();

        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void equalLocaionsShouldHaveSameHashCode()
            throws InvalidLocationException {
        final int row = 0;
        final int column = 0;
        Location other = new Location(row, column);

        int expectedHashCode = other.hashCode();
        int actualHashCode = location.hashCode();

        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void sameReferenceLocaionsShouldHaveSameHashCode() {
        Location other = location;

        int expectedHashCode = other.hashCode();
        int actualHashCode = location.hashCode();

        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void unEqualLocaionsShouldHaveDifferentHashCodes()
            throws InvalidLocationException {
        final int row = 0;
        final int column = 1;
        Location other = new Location(row, column);

        int expectedHashCode = other.hashCode();
        int actualHashCode = location.hashCode();

        assertNotEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void locationsWithSameRowAndColumnShouldBeEqual()
            throws InvalidLocationException {
        final int row = 0;
        final int column = 0;
        Location other = new Location(row, column);

        assertTrue(location.equals(other));
    }

    @Test
    public void locationsWithSameReferenceShouldBeEqual() {
        Location other = location;

        assertTrue(location.equals(other));
    }

    @Test
    public void locationsWithDifferentRowAndColumnShouldNotBeEqual()
            throws InvalidLocationException {
        final int row = 0;
        final int column = 1;
        Location other = new Location(row, column);

        assertFalse(location.equals(other));
    }
    
    @Test
    public void equalLocationsShouldReturnZeroForCompareTo() 
            throws InvalidLocationException {
        final int row = 0;
        final int column = 0;
        Location other = new Location(row, column);

        final int expected = 0;
        int actual = location.compareTo(other);
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void compareToReturnsNegativeValueForLessThan() 
            throws InvalidLocationException {
        final int row = 0;
        final int column = 1;
        Location first = new Location(row, column);
        
        final int row1 = 1;
        final int column1 = 0;
        Location second = new Location(row1, column1);

        int result = first.compareTo(second);
        
        assertTrue(result < 0);
    }
    
    @Test
    public void compareToReturnsPositiveValueForGreaterThan() 
            throws InvalidLocationException {
        final int row = 2;
        final int column = 1;
        Location first = new Location(row, column);
        
        final int row1 = 1;
        final int column1 = 2;
        Location second = new Location(row1, column1);

        int result = first.compareTo(second);
        
        assertTrue(result > 0);
    }
}
