package chess.pkg2;

public class Queen extends Piece{
    int[] queenOffset = {-9,-8,-7,-1,1,7,8,9};
    
    public final static int[] whiteQueenEvaluator = {
        -20,-10,-10, -5, -5,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5,  5,  5,  5,  0,-10,
         -5,  0,  5,  5,  5,  5,  0, -5,
          0,  0,  5,  5,  5,  5,  0, -5,
        -10,  5,  5,  5,  5,  5,  0,-10,
        -10,  0,  5,  0,  0,  0,  0,-10,
        -20,-10,-10, -5, -5,-10,-10,-20
    };
    public final static int[] blackQueenEvaluator = {
        -20,-10,-10, -5, -5,-10,-10,-20,
        -10,  0,  5,  0,  0,  0,  0,-10,
        -10,  5,  5,  5,  5,  5,  0,-10,
          0,  0,  5,  5,  5,  5,  0, -5,
          0,  0,  5,  5,  5,  5,  0, -5,
        -10,  0,  5,  5,  5,  5,  0,-10,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -20,-10,-10, -5, -5,-10,-10,-20
    };
    
    public Queen(int piecePosition, int alliance) {
        super(piecePosition, alliance);
    }

    @Override
    public int getPointStatic() {
        return 900;
    }
    
    @Override
    public String toString(){
//        if(alliance == 0){
            return "Q";
//        }else{
//            return "q";
//        }
    }
    
    @Override
    public int getLocationPoint(int location){
        if(alliance == 0){
            return whiteQueenEvaluator[location];
        }else{
            return blackQueenEvaluator[location];
        }
    }

    @Override
    public int[] getPossibleNextPosition(ChessPosition positionChess) {
        int[] possibleQueenNextPosition = new int[27];
        int j;
        j=0;
        int testNextPosition;
        for(int i=0;i<27;++i){
            possibleQueenNextPosition[i] = 100;
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 7)){
            testNextPosition += queenOffset[2];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 7)){
            testNextPosition += queenOffset[4];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 7)){
            testNextPosition += queenOffset[7];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 0)){
            testNextPosition += queenOffset[5];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 0)){
            testNextPosition += queenOffset[3];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 0)){
            testNextPosition += queenOffset[0];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) ){
            testNextPosition += queenOffset[1];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
                
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) ){
            testNextPosition += queenOffset[6];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleQueenNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleQueenNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        
        return possibleQueenNextPosition;
    }
    
}
