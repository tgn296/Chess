package chess.pkg2;

public class Bishop extends Piece{
    
    int[] bishopOffset = {-7,-9,7,9};
    
    public final static int[] whiteBishopEvaluator = {
        -20,-10,-10,-10,-10,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5, 10, 10,  5,  0,-10,
        -10,  5,  5, 10, 10,  5,  5,-10,
        -10,  0, 10, 10, 10, 10,  0,-10,
        -10, 10, 10, 10, 10, 10, 10,-10,
        -10,  5,  0,  0,  0,  0,  5,-10,
        -20,-10,-10,-10,-10,-10,-10,-20
    };
    public final static int[] blackBishopEvaluator = {
        -20,-10,-10,-10,-10,-10,-10,-20,
        -10,  5,  0,  0,  0,  0,  5,-10,
        -10, 10, 10, 10, 10, 10, 10,-10,
        -10,  0, 10, 10, 10, 10,  0,-10,
        -10,  5,  5, 10, 10,  5,  5,-10,
        -10,  0,  5, 10, 10,  5,  0,-10,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -20,-10,-10,-10,-10,-10,-10,-20
    };
    
    public Bishop(int piecePosition, int alliance){
        super(piecePosition, alliance);
    }

    @Override
    public int getPointStatic() {
        return 330;
    }
    
    @Override
    public String toString(){
//        if(alliance == 0){
            return "B";
//        }else{
//            return "b";
//        }
    }

    @Override
    public int[] getPossibleNextPosition(ChessPosition positionChess) {
        int[] possibleBishopNextPosition = new int[13];
        int j;
        j=0;
        for(int i=0;i<13;++i){
            possibleBishopNextPosition[i] = 100;
        }
        int testNextPosition;
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 7)){
            testNextPosition += bishopOffset[0];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleBishopNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleBishopNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 7)){
            testNextPosition += bishopOffset[3];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleBishopNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleBishopNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 0)){
            testNextPosition += bishopOffset[1];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleBishopNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleBishopNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 0)){
            testNextPosition += bishopOffset[2];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleBishopNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleBishopNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        return possibleBishopNextPosition;
    }

    @Override
    public int getLocationPoint(int location) {
        if(alliance ==0){
            return whiteBishopEvaluator[location];
        }else{
            return blackBishopEvaluator[location];
        }
    }
    
}
