package slidingpuzzle;

public interface SlidingPuzzle {
    public void movePieceUp();
    public void movePieceDown();
    public void movePieceLeft();
    public void movePieceRight();
    public void print();
    public boolean isInWinState();
    public void undoLastMove();
}
