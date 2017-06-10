package chess.pkg2;

import java.util.ArrayList;

public class King extends Piece{

    int[] kingOffset = {-9,-8,-7,-1,1,7,8,9};
    
    
    
    
    public final static int[] whiteKingEvaluator = {
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -20,-30,-30,-40,-40,-30,-30,-20,
        -10,-20,-20,-20,-20,-20,-20,-10,
         20, 20,  0,  0,  0,  0, 20, 20,
         20, 30, 10,  0,  0, 10, 30, 20
    };
    public final static int[] blackKingEvaluator = {
         20, 30, 10,  0,  0, 10, 30, 20,
         20, 20,  0,  0,  0,  0, 20, 20,
        -10,-20,-20,-20,-20,-20,-20,-10,
        -20,-30,-30,-40,-40,-30,-30,-20,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30
    };

    
    public King(int piecePosition, int alliance) {
        super(piecePosition, alliance);
    }

    @Override
    public int getPointStatic() {
        return 10000;
    }
    
    @Override
    public String toString(){
//        if(alliance == 0){
            return "K";
//        }else{
//            return "k";
//        }
    }
    

    @Override
    public int[] getPossibleNextPosition(ChessPosition positionChess) {
        int[] possibleKingNextPosition = new int[10];
        int j;
        j=0;
        int testNextPosition;
        for(int i=0; i<10; ++i){
            possibleKingNextPosition[i] = 100;
        }
        
        testNextPosition = piecePosition;
        if((testNextPosition % 8 != 7)){
            testNextPosition += kingOffset[2];
            if(testNextPosition >=0 && testNextPosition < 64){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        if((testNextPosition % 8 != 7)){
            testNextPosition += kingOffset[4];
            if(testNextPosition >=0 && testNextPosition < 64){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        if((testNextPosition % 8 != 7)){
            testNextPosition += kingOffset[7];
            if(testNextPosition >=0 && testNextPosition < 64){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        if((testNextPosition % 8 != 0)){
            testNextPosition += kingOffset[5];
            if(testNextPosition >=0 && testNextPosition < 64){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        if(testNextPosition % 8 != 0){
            testNextPosition += kingOffset[3];
            if(testNextPosition >=0 && testNextPosition < 64){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        if(testNextPosition % 8 != 0){
            testNextPosition += kingOffset[0];
            if(testNextPosition >=0 && testNextPosition < 64){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        if(true ){
            testNextPosition += kingOffset[1];
            if((testNextPosition >= 0) && (testNextPosition <64)){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        testNextPosition = piecePosition;
        if(true){
            testNextPosition += kingOffset[6];
            if(testNextPosition >=0 && testNextPosition < 64){
                if(positionChess.chessBoard.get(testNextPosition) == null){
                    possibleKingNextPosition[j] = testNextPosition;
                    ++j;
                }else{
                    Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                    if(pieceAtPosition.getAlliance() != this.getAlliance()){
                        possibleKingNextPosition[j] = testNextPosition;
                        ++j;
                    }
                }
            }
        }
        
        //White King Castle
        if(this.getAlliance() == 0){
            ArrayList<Integer> tileAttackedByBlack = positionChess.getAllTileNotIncludeKing(1);
            if(positionChess.getWhiteQueenSideCastleCondition() == 0 ){
                if((positionChess.chessBoard.get(59) == null) && (positionChess.chessBoard.get(58) == null) && (positionChess.chessBoard.get(57) == null) && (piecePosition == 60) && 
                        ((positionChess.chessBoard.get(49) == null) || (!(positionChess.chessBoard.get(49).toString().equals("K")))) ){
                    for(int i : tileAttackedByBlack){
                        if(!((i == 59) || (i==58) || (i==60))){
                            possibleKingNextPosition[j] = 58;
                        }else{
                            possibleKingNextPosition[j] = 100;
                            break;
                        }
                    }
                    
                }
                ++j;
            }
            
            if(positionChess.getWhiteKingSideCastleCondition() == 0 ){
                if((positionChess.chessBoard.get(61) == null) && (positionChess.chessBoard.get(62) == null) && (piecePosition == 60) && 
                        ((positionChess.chessBoard.get(54) == null) || (!(positionChess.chessBoard.get(54).toString().equals("K"))))){
                    for(int i : tileAttackedByBlack){
                        if(!((i == 61) || (i==62) || (i==60))){
                            possibleKingNextPosition[j] = 62;
                        }else{
                            possibleKingNextPosition[j] = 100;
                            break;
                        }
                    }
                }
            }
        }
        
        //Black King Castle
        if(this.getAlliance() == 1){
            ArrayList<Integer> tileAttackedByWhite = positionChess.getAllTileNotIncludeKing(0);
            if(positionChess.getBlackQueenSideCastleCondition() == 0 ){
                if((positionChess.chessBoard.get(1) == null) && (positionChess.chessBoard.get(2) == null) && (positionChess.chessBoard.get(3) == null) && (piecePosition == 4) && 
                        ((positionChess.chessBoard.get(9) == null) || (!(positionChess.chessBoard.get(9).toString().equals("K"))))){
                    for(int i : tileAttackedByWhite){
                        if(!((i == 3) || (i==2) || (i==4))){
                            possibleKingNextPosition[j] = 2;
                        }else{
                            possibleKingNextPosition[j] = 100;
                            break;
                        }
                    }
                    
                }
                ++j;
            }
            
            if(positionChess.getBlackKingSideCastleCondition() == 0 ){
                if((positionChess.chessBoard.get(6) == null) && (positionChess.chessBoard.get(5) == null) && (piecePosition == 4) &&
                        ((positionChess.chessBoard.get(14) == null) || (!(positionChess.chessBoard.get(14).toString().equals("K"))))){
                    for(int i : tileAttackedByWhite){
                        if(!((i == 6) || (i==5) || (i==4))){
                            possibleKingNextPosition[j] = 6;
                        }else{
                            possibleKingNextPosition[j] = 100;
                            break;
                        }
                    }
                    
                }
            }
            
        }
        
        return possibleKingNextPosition;
    }

    @Override
    public int getLocationPoint(int location) {
       if(alliance == 0){
           return whiteKingEvaluator[location];
       }else{
           return blackKingEvaluator[location];
       }
    }
    
}
