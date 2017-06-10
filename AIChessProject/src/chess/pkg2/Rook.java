package chess.pkg2;


public class Rook extends Piece{
    int[] rookOffset = {-8,-1,1,8};
    
    public final static int[] whiteRookEvaluator = {
         0, 0, 0, 0, 0, 0, 0, 0,
         5,20,20,20,20,20,20, 5,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
         0, 0, 0, 5, 5, 0, 0, 0
    };
    public final static int[] blackRookEvaluator = {
         0, 0, 0, 5, 5, 0, 0, 0,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
        -5, 0, 0, 0, 0, 0, 0,-5,
         5,20,20,20,20,20,20, 5,
         0, 0, 0, 0, 0, 0, 0, 0
    };

    public Rook(int piecePosition, int Alliance) {
        super(piecePosition, Alliance);
    }

    @Override
    public int getPointStatic() {
        return 500;
    }
    
    
    
    @Override
    public String toString(){
//        if(alliance == 0){
            return "R";
//        }else{
//            return "r";
//        }
    }

    @Override
    public int[] getPossibleNextPosition(ChessPosition positionChess) {
        int[] possibleRookNextPosition = new int[14];
        int j;
        j=0;
        for(int i=0;i<14;++i){
            possibleRookNextPosition[i] = 100;
        }
        
        int testNextPosition;
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 7) && ((testNextPosition + rookOffset[2])>=0)){
            testNextPosition += rookOffset[2];
            if(positionChess.chessBoard.get(testNextPosition)==null){
                possibleRookNextPosition[j] = testNextPosition;
                ++j;
            }else{
                Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                if(pieceAtPosition.getAlliance() == this.getAlliance()){
                    break;
                }else{
                    possibleRookNextPosition[j] = testNextPosition;
                    ++j;
                    break;
                }
            }
        }
        
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) ){
            testNextPosition += rookOffset[3];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition)==null){
                    possibleRookNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleRookNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) && (testNextPosition % 8 != 0)){
            testNextPosition += rookOffset[1];
            if(positionChess.chessBoard.get(testNextPosition)==null){
                possibleRookNextPosition[j] = testNextPosition;
                ++j;
            }else{
                Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                if(pieceAtPosition.getAlliance() == this.getAlliance()){
                    break;
                }else{
                    possibleRookNextPosition[j] = testNextPosition;
                    ++j;
                    break;
                }
            }
        }
        testNextPosition = piecePosition;
        while((testNextPosition >= 0) && (testNextPosition <64) ){
            testNextPosition += rookOffset[0];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition)==null){
                    possibleRookNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == this.getAlliance()){
                        break;
                    }else{
                        possibleRookNextPosition[j] = testNextPosition;
                        ++j;
                        break;
                    }
                }
            }
        }
        return possibleRookNextPosition;
    }

    @Override
    public int getLocationPoint(int location) {
        if(alliance == 0 ){
            return whiteRookEvaluator[location];
        }else{
            return blackRookEvaluator[location];
        }
    }
    
}
