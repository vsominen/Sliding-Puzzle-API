package slidingpuzzle;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleSlidingPuzzle implements SlidingPuzzle {

    private GameBoard previousState;
    private GameBoard currentState;
    private GameBoard winState;

    public ConsoleSlidingPuzzle(GameBoard startState)
            throws InvalidLocationException, Exception {
        if(startState == null) {
            throw new Exception("Start State cannot be empty");
        }
        previousState = null;
        currentState = new GameBoard(startState);
        generateWinState();
    }

    @Override
    public void movePieceUp() {
        previousState = new GameBoard(currentState);
        try {
            currentState = currentState.moveBlankPieceDown();
        } catch (Exception ex) {
            Logger.getLogger(ConsoleSlidingPuzzle.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void movePieceDown() {
        previousState = new GameBoard(currentState);
        try {
            currentState = currentState.moveBlankPieceUp();
        } catch (Exception ex) {
            Logger.getLogger(ConsoleSlidingPuzzle.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void movePieceLeft() {
        previousState = new GameBoard(currentState);
        try {
            currentState = currentState.moveBlankPieceRight();
        } catch (Exception ex) {
            Logger.getLogger(ConsoleSlidingPuzzle.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void movePieceRight() {
        previousState = new GameBoard(currentState);
        try {
            currentState = currentState.moveBlankPieceLeft();
        } catch (Exception ex) {
            Logger.getLogger(ConsoleSlidingPuzzle.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void print() {
        String format = "| %d ";
        for (int row = 0; row < currentState.getSize(); ++row) {
            for (int column = 0; column < currentState.getSize(); ++column) {
                try {
                    Location currentLocation = new Location(row, column);
                    GamePiece currentPiece
                            = currentState.getPieceAt(currentLocation);
                    if (currentPiece.isBlank()) {
                        System.out.print("|   ");
                    } else {
                        System.out.format(format, currentPiece.getValue());
                    }
                } catch (InvalidLocationException ex) {
                    Logger.getLogger(ConsoleSlidingPuzzle.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
            System.out.print("|");
            System.out.println();
        }
    }

    @Override
    public void undoLastMove() {
        if(previousState == null) {
            return;
        }
        currentState = new GameBoard(previousState);
        previousState = null;
    }

    @Override
    public boolean isInWinState() {
        return currentState.equals(winState);
    }

    private void generateWinState() throws InvalidLocationException, Exception {
        TreeMap<Location, GamePiece> pieces = new TreeMap<>();
        int value = 0;
        for (int row = 0; row < currentState.getSize(); ++row) {
            for (int column = 0; column < currentState.getSize(); ++column) {
                Location location = new Location(row, column);
                GamePiece piece = new GamePiece(value, value == 0);
                pieces.put(location, piece);
                value++;
            }
        }

        winState = new GameBoard(currentState.getSize());
        winState.place(pieces);
    }
}
