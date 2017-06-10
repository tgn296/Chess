package chess.pkg2;

public class Pawn extends Piece {

    public Pawn(int piecePosition, int alliance) {
        super(piecePosition, alliance);
    }
    
    public final static int[] whitePawnEvaluator = {
        0 ,0 ,0 , 0 , 0 ,0 ,0 ,0 ,
        50,50,50, 50, 50,50,50,50,
        10,10,20, 30, 30,20,10,10,
        5 ,5 ,10, 25, 25,10,5 ,5 ,
        0 ,0 ,0 , 20, 20,0 ,0 ,0 ,
        5, -5,-10, 0, 0,-10,-5, 5,
        5 ,10,10,-20,-20,10,10, 5,
        0 ,0 ,0 , 0 , 0 ,0 ,0 , 0
    };
    public final static int[] blackPawnEvaluator = {
        0 ,0 ,0 ,0  ,0  ,0 ,0 ,0 ,
        5 ,10,10,-20,-20,10,10,5 ,
        5, -5,-10,0,0,-10, -5 ,5 ,
        0 ,0 ,0 ,20 ,20 ,0 ,0 ,0 ,
        5 ,5 ,10, 25,25 ,10,5 ,5 ,
        10,10,20,30 ,30 ,20,10,10,
        50,50,50,50 ,50 ,50,50,50,
        0 ,0 ,0 ,0  ,0  ,0 ,0 ,0
    };
    @Override
    public int getLocationPoint(int location){
        if(this.getAlliance() == 0){
            return whitePawnEvaluator[location];
        }else{
            return blackPawnEvaluator[location];
        }
    }

    @Override
    public int getPointStatic() {
        return 100;
    }
    
    @Override
    public String toString(){
//        if(alliance == 0){
            return "P";
//        }else{
//            return "p";
//        }
    }

    @Override
    public int[] getPossibleNextPosition(ChessPosition positionChess) {
        int[] possiblePawnNextPosition = new int[4];
        int testNextPosition;
        int j;
        j=0;
        for(int i=0; i<4; ++i){
            possiblePawnNextPosition[i] = 100;
        }
        if(alliance == 0){
            if((piecePosition >= 48) && (piecePosition < 56)){
                testNextPosition = piecePosition - 16;
                if((positionChess.chessBoard.get(testNextPosition) == null) && (positionChess.chessBoard.get(piecePosition - 8) == null)){
                    possiblePawnNextPosition[j] = testNextPosition ;
                    j++;
                }
            }
            testNextPosition = piecePosition-8;
            if((testNextPosition >= 0) && (testNextPosition < 64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possiblePawnNextPosition[j] = testNextPosition ;
                    j++;
                }
            }
            
            if((piecePosition % 8 != 0) && (piecePosition % 8 != 7)){
                testNextPosition = piecePosition-9;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 1){
                        possiblePawnNextPosition[j] = testNextPosition;
                        j++;
                    }
                }
                testNextPosition = piecePosition - 7;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 1){
                        possiblePawnNextPosition[j] = testNextPosition ;
                    }
                }
            }else if(piecePosition % 8 != 0){
                testNextPosition = piecePosition-9;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 1){
                        possiblePawnNextPosition[j] = testNextPosition;
                        j++;
                    }
                }
            }else{
                testNextPosition = piecePosition - 7;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 1){
                        possiblePawnNextPosition[j] = testNextPosition ;
                    }
                }
            }
        }
        
        if(alliance == 1){
            if((piecePosition >= 8) && (piecePosition < 16)){
                testNextPosition = piecePosition + 16;
                if((positionChess.chessBoard.get(testNextPosition) == null) && (positionChess.chessBoard.get(piecePosition + 8) == null)){
                    possiblePawnNextPosition[j] = testNextPosition;
                    j++;
                }
            }
            testNextPosition = piecePosition + 8;
            if((testNextPosition >= 0) && (testNextPosition < 64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possiblePawnNextPosition[j] = testNextPosition;
                    j++;
                }
            }
            
            if((piecePosition % 8 != 0) && (piecePosition % 8 != 7)){
                testNextPosition = piecePosition + 9;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 0){
                        possiblePawnNextPosition[j] = testNextPosition;
                        j++;
                    }
                }
                testNextPosition = piecePosition + 7;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 0){
                        possiblePawnNextPosition[j] = testNextPosition ;
                    }
                }
            }else if(piecePosition % 8 != 0){
                testNextPosition = piecePosition + 7;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 0){
                        possiblePawnNextPosition[j] = testNextPosition;
                        j++;
                    }
                }
            }else{
                testNextPosition = piecePosition + 9;
                if(positionChess.chessBoard.get(testNextPosition) != null){
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() == 0){
                        possiblePawnNextPosition[j] = testNextPosition ;
                    }
                }
            }
        }
        return possiblePawnNextPosition;
    }
    
}
