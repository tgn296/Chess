package chess.pkg2;

import java.util.HashMap;

public class Knight extends Piece {
    
    int[] offsetGeneral = {6,10,15,17,-6,-10,-15,-17};
    int[] offsetFirstColumn = {10,17,-6,-15};
    int[] offsetSecondColumn = {10,15,17,-6,-15,-17};
    int[] offsetSeventhColumn = {-10,-15,-17,6,15,17};
    int[] offsetEighthColumn = {-10,-17,6,15};
    
    public final static int[] whiteKnightEvaluator = {
        -50,-40,-30,-30,-30,-30,-40,-50,
        -40,-20,  0,  0,  0,  0,-20,-40,
        -30,  0, 10, 15, 15, 10,  0,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  0, 15, 20, 20, 15,  0,-30,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -40,-20,  0,  5,  5,  0,-20,-40,
        -50,-40,-30,-30,-30,-30,-40,-50
    };
    public final static int[] blackKnightEvaluator = {
        -50,-40,-30,-30,-30,-30,-40,-50,
        -40,-20,  0,  5,  5,  0,-20,-40,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -30,  0, 15, 20, 20, 15,  0,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  0, 10, 15, 15, 10,  0,-30,
        -40,-20,  0,  0,  0,  0,-20,-40,
        -50,-40,-30,-30,-30,-30,-40,-50
    };
    
    public Knight(int piecePosition, int alliance){
        super(piecePosition, alliance);
        
    }

    @Override
    public int getPointStatic() {
        return 320;
    }
    
    @Override
    public String toString(){
//       if(alliance == 0){
            return "N";
//        }else{
//            return "n";
//        }
    }
    
    

    @Override
    public int[] getPossibleNextPosition(ChessPosition positionChess) {
        int[] possibleKnightNextPosition = new int[8];
        int testNextPosition;
        for(int i=0;i<8;++i){
            possibleKnightNextPosition[i] = 100;
        }
        if(piecePosition % 8 == 0){
            for(int i=0; i<4; ++i){
                testNextPosition = piecePosition + offsetFirstColumn[i];
                if((testNextPosition) >=0 && (testNextPosition <64)){
                    if(positionChess.chessBoard.get(testNextPosition) == null){
                        possibleKnightNextPosition[i] = piecePosition + offsetFirstColumn[i];
                    }else{
                        Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                        if(pieceAtPosition.getAlliance() == this.getAlliance()){
                            continue;
                        }else{
                            possibleKnightNextPosition[i] = piecePosition + offsetFirstColumn[i];
                        }
                    }
                        
                }
            }
        }else if(piecePosition % 8 ==1){
            for(int i=0; i<6; ++i){
                testNextPosition = piecePosition + offsetSecondColumn[i];
                if((testNextPosition) >=0 && (testNextPosition <64)){
                    if(positionChess.chessBoard.get(testNextPosition) == null){
                        possibleKnightNextPosition[i] = testNextPosition;
                    }else{
                        Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                        if(pieceAtPosition.getAlliance() == this.getAlliance()){
                            continue;
                        }else{
                            possibleKnightNextPosition[i] = testNextPosition;
                        }
                    }
                        
                }
            }
        }else if(piecePosition % 8 ==6){
            for(int i=0; i<6; ++i){
                testNextPosition = piecePosition + offsetSeventhColumn[i];
                if((testNextPosition) >=0 && (testNextPosition <64)){
                    if(positionChess.chessBoard.get(testNextPosition) == null){
                        possibleKnightNextPosition[i] = testNextPosition;
                    }else{
                        Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                        if(pieceAtPosition.getAlliance() == this.getAlliance()){
                            continue;
                        }else{
                            possibleKnightNextPosition[i] = testNextPosition;
                        }
                    }
                        
                }
            }
        }else if(piecePosition % 8 ==7){
            for(int i=0; i<4; ++i){
                testNextPosition = piecePosition + offsetEighthColumn[i];
                if((testNextPosition) >=0 && (testNextPosition <64)){
                    if(positionChess.chessBoard.get(testNextPosition) == null){
                        possibleKnightNextPosition[i] = testNextPosition;
                    }else{
                        Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                        if(pieceAtPosition.getAlliance() == this.getAlliance()){
                            continue;
                        }else{
                            possibleKnightNextPosition[i] = testNextPosition;
                        }
                    }
                        
                }
            }
        }else{
            for(int i=0; i<8; ++i){
                testNextPosition = piecePosition + offsetGeneral[i];
                if((testNextPosition) >=0 && (testNextPosition <64)){
                    if(positionChess.chessBoard.get(testNextPosition) == null){
                        possibleKnightNextPosition[i] = testNextPosition;
                    }else{
                        Piece pieceAtPosition = positionChess.chessBoard.get(testNextPosition);
                        if(pieceAtPosition.getAlliance() == this.getAlliance()){
                            continue;
                        }else{
                            possibleKnightNextPosition[i] = testNextPosition;
                        }
                    }
                }
            }
        }
        return possibleKnightNextPosition;
    }

    @Override
    public int getLocationPoint(int location) {
        
        if(this.getAlliance() == 0){
            return whiteKnightEvaluator[location];
        }else{
            return blackKnightEvaluator[location];
        }
        
    }
}
