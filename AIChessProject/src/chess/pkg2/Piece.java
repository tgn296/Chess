package chess.pkg2;
import chess.pkg2.ChessPosition;

public abstract class Piece {
    int piecePosition;
    int alliance; // 0 for white and 1 for black
    int pointStatic;
    
    
    
    public Piece(int piecePosition,int alliance){
        this.piecePosition = piecePosition;
        this.alliance = alliance;
        this.pointStatic = getPointStatic();
    }
    public abstract int getLocationPoint(int location);
    public abstract int getPointStatic();
    public abstract int[] getPossibleNextPosition(ChessPosition position);
    public int getAlliance(){
        return this.alliance;
    }
    @Override
    public abstract String toString();
}
