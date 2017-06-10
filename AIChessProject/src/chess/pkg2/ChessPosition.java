package chess.pkg2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessPosition {

    private int allianceTurn;
    Map<Integer, Piece> chessBoard;
    private int positionPoint;

    // Bien cho phep nhap thanh
    //Bang 0 -> Vua va Xe deu chua di chuyen
    private int whiteKingSideCastle;
    private int whiteQueenSideCastle;
    private int blackKingSideCastle;
    private int blackQueenSideCastle;
    private Piece pieceJustMoved;
    int pieceJustMovedNewPosition;

    public ChessPosition() {
        this.chessBoard = new HashMap<>();
        this.positionPoint = 0;
        this.allianceTurn = 0;
        this.whiteKingSideCastle = 0;
        this.blackKingSideCastle = 0;
        this.whiteQueenSideCastle = 0;
        this.blackQueenSideCastle = 0;
        this.pieceJustMoved = null;
        this.pieceJustMovedNewPosition = 100;

    }

    public ChessPosition(ChessPosition formerPosition) {
        this.chessBoard = copyChessBoard(formerPosition);
        this.positionPoint = 0;
        this.allianceTurn = 1 - formerPosition.allianceTurn;
        this.whiteKingSideCastle = formerPosition.whiteKingSideCastle;
        this.blackKingSideCastle = formerPosition.blackKingSideCastle;
        this.whiteQueenSideCastle = formerPosition.whiteQueenSideCastle;
        this.blackQueenSideCastle = formerPosition.blackQueenSideCastle;
    }

    public void createSimilarPosition(ChessPosition position) {

        chessBoard = copyChessBoard(position);
        positionPoint = position.positionPoint;
        allianceTurn = position.allianceTurn;
        whiteKingSideCastle = position.whiteKingSideCastle;
        blackKingSideCastle = position.blackKingSideCastle;
        whiteQueenSideCastle = position.whiteQueenSideCastle;
        blackQueenSideCastle = position.blackQueenSideCastle;
        pieceJustMoved = position.pieceJustMoved;
        pieceJustMovedNewPosition = position.pieceJustMovedNewPosition;

    }

    public int getAllianceTurn() {
        return this.allianceTurn;
    }

    public void setCurrentAlliance(int alliance) {
        this.allianceTurn = alliance;
    }

    public void setPointCalculated() {
        this.positionPoint = this.calculatePositionPoint();
    }

    public int getWhiteKingSideCastle() {
        return this.whiteKingSideCastle;
    }

    public void setPositionPointBottomUp(int point) {
        this.positionPoint = point;
    }

    public int getPositionPoint() {
        return this.positionPoint;
    }

    public Piece getPieceJustMoved() {
        return this.pieceJustMoved;
    }

    public int getPieceJustMovedNewPosition() {
        return this.pieceJustMovedNewPosition;
    }

    public void setNewPosition(int newPosition) {
        this.pieceJustMovedNewPosition = newPosition;
    }

    public void setPieceJustMoved(Piece piece) {
        if (piece.toString().equals("P")) {
            this.pieceJustMoved = new Pawn(piece.piecePosition, piece.getAlliance());
        } else if (piece.toString().equals("K")) {
            this.pieceJustMoved = new King(piece.piecePosition, piece.getAlliance());
        } else if (piece.toString().equals("Q")) {
            this.pieceJustMoved = new Queen(piece.piecePosition, piece.getAlliance());
        } else if (piece.toString().equals("B")) {
            this.pieceJustMoved = new Bishop(piece.piecePosition, piece.getAlliance());
        } else if (piece.toString().equals("N")) {
            this.pieceJustMoved = new Knight(piece.piecePosition, piece.getAlliance());
        } else {
            this.pieceJustMoved = new Rook(piece.piecePosition, piece.getAlliance());
        }
    }

    //Hàm tính điểm cho thế cờ
    public int calculatePositionPoint() {
        int point = 0;
        for (int i = 0; i < 64; ++i) {
            if (chessBoard.get(i) != null) {
                Piece pieceAtPosition = chessBoard.get(i);
                if (pieceAtPosition.getAlliance() == 0) {
                    point += (pieceAtPosition.getPointStatic() + pieceAtPosition.getLocationPoint(i));
                    
                } else {
                    point += ((pieceAtPosition.getPointStatic() + pieceAtPosition.getLocationPoint(i)) * (-1));
                }
            }
        }
        
        
        //PawnStacked
        for (int column = 1; column < 9; column++) {
            if (countPawnInColumn(column, 1) > 1) {
                point += 20;
            }
            if (countPawnInColumn(column, 0) > 1) {
                point -= 20;
            }
        }
        //IsolatedPawn
        point -= (countIsolatedPawn(0) * 15);
        point += (countIsolatedPawn(1) * 15);
        //Open-File Rook
        for (int column = 1; column < 9; column++) {
            if ((countPawnInColumn(column, 1) == 0) && (countPawnInColumn(column, 0) == 0)) {
                if (isRookIncluded(column, 1) == 1) {
                    point -= 40;
                }
                if (isRookIncluded(column, 0) == 1) {
                    point += 40;
                }
            }
        }
        return point;
    }

    public int isRookIncluded(int column, int alliance) {
        int init = column - 1;
        while (init < 64) {
            if (chessBoard.get(init) != null) {
                if (chessBoard.get(init).toString().equals("R") && chessBoard.get(init).getAlliance() == alliance) {
                    return 1;
                }
            }
            init += 8;
        }
        return 0;
    }

    public int countPawnInColumn(int column, int alliance) {
        int pawnNumber = 0;
        int init = column - 1;
        while (init < 64) {
            if (chessBoard.get(init) != null) {
                if ((chessBoard.get(init).toString().equals("P")) && (chessBoard.get(init).getAlliance() == alliance)) {
                    pawnNumber++;
                }
            }
            init += 8;
        }
        return pawnNumber;
    }

    public int countIsolatedPawn(int alliance) {
        int result = 0;
        for (int column = 2; column < 8; column++) {
            int pawnNumberInThisColumn = countPawnInColumn(column, alliance);
            if (pawnNumberInThisColumn > 0) {
                if ((countPawnInColumn(column - 1, alliance) == 0) && (countPawnInColumn(column + 1, alliance) == 0)) {
                    result += pawnNumberInThisColumn;
                }
            }
        }

        if (countPawnInColumn(1, alliance) > 0 && countPawnInColumn(2, alliance) == 0) {
            result += countPawnInColumn(1, alliance);
        }

        if (countPawnInColumn(8, alliance) > 0 && countPawnInColumn(7, alliance) == 0) {
            result += countPawnInColumn(8, alliance);
        }

        return result;
    }

    public int endGameAnnoucement() {
        int isWhiteKingLeft = 0;
        int isBlackKingLeft = 0;
        for (int i = 0; i < 64; ++i) {
            if (chessBoard.get(i) != null) {
                if (chessBoard.get(i).toString().equals("K")) {
                    if (chessBoard.get(i).getAlliance() == 1) {
                        isBlackKingLeft = 1;
                    }
                    if (chessBoard.get(i).getAlliance() == 0) {
                        isWhiteKingLeft = 1;
                    }
                }
            }
        }
        if (isWhiteKingLeft == 0 || isBlackKingLeft == 0) {
            new EndGameFrame();
            return 1;
        }
        return 0;
    }

    public static Map<Integer, Piece> copyChessBoard(ChessPosition chessPositionNeedToCopy) {
        Map<Integer, Piece> chessBoardCopied = new HashMap<>();
        for (int i = 0; i < 64; ++i) {
            if (chessPositionNeedToCopy.chessBoard.get(i) != null) {
                Piece pieceAtPosition = chessPositionNeedToCopy.chessBoard.get(i);
                if (pieceAtPosition.toString().equals("K")) {
                    chessBoardCopied.put(i, new King(i, pieceAtPosition.getAlliance()));
                } else if (pieceAtPosition.toString().equals("R")) {
                    chessBoardCopied.put(i, new Rook(i, pieceAtPosition.getAlliance()));
                } else if (pieceAtPosition.toString().equals("N")) {
                    chessBoardCopied.put(i, new Knight(i, pieceAtPosition.getAlliance()));
                } else if (pieceAtPosition.toString().equals("B")) {
                    chessBoardCopied.put(i, new Bishop(i, pieceAtPosition.getAlliance()));
                } else if (pieceAtPosition.toString().equals("P")) {
                    chessBoardCopied.put(i, new Pawn(i, pieceAtPosition.getAlliance()));
                } else if (pieceAtPosition.toString().equals("Q")) {
                    chessBoardCopied.put(i, new Queen(i, pieceAtPosition.getAlliance()));
                }
            }
        }
        return chessBoardCopied;
    }

    public int getWhiteKingSideCastleCondition() {
        return whiteKingSideCastle;
    }

    public int getWhiteQueenSideCastleCondition() {
        return whiteQueenSideCastle;
    }

    public int getBlackKingSideCastleCondition() {
        return blackKingSideCastle;
    }

    public int getBlackQueenSideCastleCondition() {
        return blackQueenSideCastle;
    }

    //Kiem tra Vua va Xe da di chuyen chua
    public void fixCastleCondition() {
        Piece pieceAtWhiteKingLocation = chessBoard.get(60);
        Piece pieceAtWhiteKingSideRookLocation = chessBoard.get(63);
        Piece pieceAtWhiteQueenSideRookLocation = chessBoard.get(56);
        Piece pieceAtBlackKingLocation = chessBoard.get(4);
        Piece pieceAtBlackKingSideRookLocation = chessBoard.get(7);
        Piece pieceAtBlackQueenSideRookLocation = chessBoard.get(0);

        if ((pieceAtWhiteKingLocation == null) || (!((pieceAtWhiteKingLocation.toString()).equals("K"))) || (pieceAtWhiteKingLocation.getAlliance() == 1)) {
            whiteKingSideCastle = 1;
            whiteQueenSideCastle = 1;
        } else {
            if ((pieceAtWhiteKingSideRookLocation == null) || (!((pieceAtWhiteKingSideRookLocation.toString()).equals("R"))) || (pieceAtWhiteKingSideRookLocation.getAlliance() == 1)) {
                whiteKingSideCastle = 1;
            }
            if ((pieceAtWhiteQueenSideRookLocation == null) || (!((pieceAtWhiteQueenSideRookLocation.toString()).equals("R"))) || (pieceAtWhiteQueenSideRookLocation.getAlliance() == 1)) {
                whiteQueenSideCastle = 1;
            }
        }

        if ((pieceAtBlackKingLocation == null) || (!((pieceAtBlackKingLocation.toString()).equals("K"))) || (pieceAtBlackKingLocation.getAlliance() == 0)) {
            blackKingSideCastle = 1;
            blackQueenSideCastle = 1;
        } else {
            if ((pieceAtBlackKingSideRookLocation == null) || (!((pieceAtBlackKingSideRookLocation.toString()).equals("R"))) || (pieceAtBlackKingSideRookLocation.getAlliance() == 0)) {
                blackKingSideCastle = 1;
            }
            if ((pieceAtBlackQueenSideRookLocation == null) || (!((pieceAtBlackQueenSideRookLocation.toString()).equals("R"))) || (pieceAtBlackQueenSideRookLocation.getAlliance() == 0)) {
                blackQueenSideCastle = 1;
            }
        }
    }

    //di chuyển quân
    public void normalMove(Piece piece, int prePosition, int desPosition) {
        if ((piece.toString()).equals("K")) {
            if ((piece.getAlliance() == 0) && (desPosition == 62) && (whiteKingSideCastle == 0) && (piece.piecePosition == 60)) {
                piece.piecePosition = desPosition;
                this.chessBoard.put(desPosition, piece);
                this.chessBoard.remove(prePosition);
                normalMove(this.chessBoard.get(63), 63, 61);
            } else if ((piece.getAlliance() == 0) && (desPosition == 58) && (whiteQueenSideCastle == 0) && (piece.piecePosition == 60)) {
                piece.piecePosition = desPosition;
                this.chessBoard.put(desPosition, piece);
                this.chessBoard.remove(prePosition);
                normalMove(this.chessBoard.get(56), 56, 59);
            }
            if ((piece.getAlliance() == 1) && (desPosition == 6) && (blackKingSideCastle == 0) && (piece.piecePosition == 4)) {
                piece.piecePosition = desPosition;
                this.chessBoard.put(desPosition, piece);
                this.chessBoard.remove(prePosition);
                movePiece(this.chessBoard.get(7), 7, 5);
            } else if ((piece.getAlliance() == 1) && (desPosition == 2) && (blackQueenSideCastle == 0) && (piece.piecePosition == 4)) {
                piece.piecePosition = desPosition;
                this.chessBoard.put(desPosition, piece);
                this.chessBoard.remove(prePosition);
                movePiece(this.chessBoard.get(0), 0, 3);
            } else {
                piece.piecePosition = desPosition;
                this.chessBoard.put(desPosition, piece);
                this.chessBoard.remove(prePosition);
            }

        } else if ((piece.toString()).equals("P")) {
            if ((piece.getAlliance() == 0) && ((desPosition >= 0) && (desPosition < 8))) {
                this.chessBoard.put(desPosition, new Queen(desPosition, 0));
                this.chessBoard.remove(prePosition);
            } else if ((piece.getAlliance() == 1) && ((desPosition >= 56) && (desPosition < 64))) {
                this.chessBoard.put(desPosition, new Queen(desPosition, 1));
                this.chessBoard.remove(prePosition);
            } else {
                piece.piecePosition = desPosition;
                this.chessBoard.put(desPosition, piece);
                this.chessBoard.remove(prePosition);
            }
        } else {
            piece.piecePosition = desPosition;
            this.chessBoard.put(desPosition, piece);
            this.chessBoard.remove(prePosition);
        }
    }

    //tấn công quân đối phương
    public void attackMove(Piece piece, int prePosition, int desPosition) {
        if (piece.toString().equals("P")) {
            if (piece.getAlliance() == 0 && desPosition >= 0 && desPosition < 8) {
                this.chessBoard.remove(desPosition);
                this.chessBoard.remove(prePosition);
                this.chessBoard.put(desPosition, new Queen(desPosition, 0));
            } else if (piece.getAlliance() == 1 && desPosition > 55 && desPosition < 64) {
                this.chessBoard.remove(desPosition);
                this.chessBoard.remove(prePosition);
                this.chessBoard.put(desPosition, new Queen(desPosition, 1));
            } else if (piece.getAlliance() == 0 && this.chessBoard.get(desPosition) == null && (desPosition == prePosition - 7 || desPosition == prePosition - 9)) {
                this.chessBoard.remove(prePosition);
                this.chessBoard.remove(desPosition + 8);
                this.chessBoard.put(desPosition, new Pawn(desPosition, 0));
            } else if (piece.getAlliance() == 1 && this.chessBoard.get(desPosition) == null && (desPosition == prePosition + 7 || desPosition == prePosition + 9)) {
                this.chessBoard.remove(prePosition);
                this.chessBoard.remove(desPosition - 8);
                this.chessBoard.put(desPosition, new Pawn(desPosition, 1));
            } else {
                piece.piecePosition = desPosition;
                this.chessBoard.remove(prePosition);
                this.chessBoard.remove(desPosition);
                this.chessBoard.put(desPosition, piece);
            }
        } else {
            piece.piecePosition = desPosition;
            this.chessBoard.remove(prePosition);
            this.chessBoard.remove(desPosition);
            this.chessBoard.put(desPosition, piece);
        }
    }

    public void movePiece(Piece piece, int prePosition, int desPosition) {
        setPieceJustMoved(piece);
        setNewPosition(desPosition);
        if (!piece.toString().equals("P")) {
            if (this.chessBoard.get(desPosition) == null) {
                normalMove(piece, prePosition, desPosition);
            } else if (this.chessBoard.get(desPosition).getAlliance() != piece.getAlliance()) {
                attackMove(piece, prePosition, desPosition);
            }
        } else if (desPosition == prePosition + 8 || desPosition == prePosition + 16 || desPosition == prePosition - 8 || desPosition == prePosition - 16) {
            normalMove(piece, prePosition, desPosition);
        } else {
            attackMove(piece, prePosition, desPosition);
        }
    }

    //lấy ra tất cả các ô của một bên có thể đi tới
    public ArrayList<Integer> getAllTile(int alliance) {
        ArrayList<Integer> allMoves = new ArrayList<>();
        for (Integer i : this.chessBoard.keySet()) {
            int[] moves;
            if (this.chessBoard.get(i).alliance == alliance) {
                moves = this.chessBoard.get(i).getPossibleNextPosition(this);

                for (int j = 0; j < moves.length; j++) {
                    allMoves.add(moves[j]);
                }
            }
        }
        return allMoves;
    }

    //Lay tat ca cac o mot ben co the di toi ko tinh quan Vua
    //Chi danh cho Castle
    public ArrayList<Integer> getAllTileNotIncludeKing(int alliance) {
        ArrayList<Integer> allMoves = new ArrayList<>();
        for (Integer i : this.chessBoard.keySet()) {
            int[] moves;
            Piece pieceAtPosition = this.chessBoard.get(i);
            if (!(pieceAtPosition.toString().equals("K"))) {
                if (this.chessBoard.get(i).alliance == alliance) {
                    moves = this.chessBoard.get(i).getPossibleNextPosition(this);

                    for (int j = 0; j < moves.length; j++) {
                        allMoves.add(moves[j]);
                    }
                }
            }
        }
        return allMoves;
    }

    //Lay ra tat ca cac nuoc tiep theo co the 
    public Map<Integer, Piece> getAllPossibleMoves() {
        Map<Integer, Piece> allMoves = new HashMap<>();
        for (int i = 0; i < 64; ++i) {
            Piece pieceAtThisPosition = this.chessBoard.get(i);
            if (pieceAtThisPosition != null) {
                if (pieceAtThisPosition.getAlliance() == allianceTurn) {
                    for (int j : pieceAtThisPosition.getPossibleNextPosition(this)) {
                        if (allMoves.get(j) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 500:
                                        allMoves.put(j, new Rook(i, allianceTurn));
                                        break;
                                    case 320:
                                        allMoves.put(j, new Knight(i, allianceTurn));
                                        break;
                                    case 330:
                                        allMoves.put(j, new Bishop(i, allianceTurn));
                                        break;
                                    case 900:
                                        allMoves.put(j, new Queen(i, allianceTurn));
                                        break;
                                    case 10000:
                                        allMoves.put(j, new King(i, allianceTurn));
                                        break;
                                    case 100:
                                        allMoves.put(j, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 64)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 500:
                                        allMoves.put(j - 64, new Rook(i, allianceTurn));
                                        break;
                                    case 320:
                                        allMoves.put(j - 64, new Knight(i, allianceTurn));
                                        break;
                                    case 330:
                                        allMoves.put(j - 64, new Bishop(i, allianceTurn));
                                        break;
                                    case 900:
                                        allMoves.put(j - 64, new Queen(i, allianceTurn));
                                        break;
                                    case 10000:
                                        allMoves.put(j - 64, new King(i, allianceTurn));
                                        break;
                                    case 100:
                                        allMoves.put(j - 64, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 128)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 500:
                                        allMoves.put(j - 128, new Rook(i, allianceTurn));
                                        break;
                                    case 320:
                                        allMoves.put(j - 128, new Knight(i, allianceTurn));
                                        break;
                                    case 330:
                                        allMoves.put(j - 128, new Bishop(i, allianceTurn));
                                        break;
                                    case 900:
                                        allMoves.put(j - 128, new Queen(i, allianceTurn));
                                        break;
                                    case 10000:
                                        allMoves.put(j - 128, new King(i, allianceTurn));
                                        break;
                                    case 100:
                                        allMoves.put(j - 128, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 192)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 5:
                                        allMoves.put(j - 192, new Rook(i, allianceTurn));
                                        break;
                                    case 3:
                                        allMoves.put(j - 192, new Knight(i, allianceTurn));
                                        break;
                                    case 4:
                                        allMoves.put(j - 192, new Bishop(i, allianceTurn));
                                        break;
                                    case 9:
                                        allMoves.put(j - 192, new Queen(i, allianceTurn));
                                        break;
                                    case 1000:
                                        allMoves.put(j - 192, new King(i, allianceTurn));
                                        break;
                                    case 1:
                                        allMoves.put(j - 192, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 256)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 5:
                                        allMoves.put(j - 256, new Rook(i, allianceTurn));
                                        break;
                                    case 3:
                                        allMoves.put(j - 256, new Knight(i, allianceTurn));
                                        break;
                                    case 4:
                                        allMoves.put(j - 256, new Bishop(i, allianceTurn));
                                        break;
                                    case 9:
                                        allMoves.put(j - 256, new Queen(i, allianceTurn));
                                        break;
                                    case 1000:
                                        allMoves.put(j - 256, new King(i, allianceTurn));
                                        break;
                                    case 1:
                                        allMoves.put(j - 256, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 320)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 5:
                                        allMoves.put(j - 320, new Rook(i, allianceTurn));
                                        break;
                                    case 3:
                                        allMoves.put(j - 320, new Knight(i, allianceTurn));
                                        break;
                                    case 4:
                                        allMoves.put(j - 320, new Bishop(i, allianceTurn));
                                        break;
                                    case 9:
                                        allMoves.put(j - 320, new Queen(i, allianceTurn));
                                        break;
                                    case 1000:
                                        allMoves.put(j - 320, new King(i, allianceTurn));
                                        break;
                                    case 1:
                                        allMoves.put(j - 320, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 384)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 5:
                                        allMoves.put(j - 384, new Rook(i, allianceTurn));
                                        break;
                                    case 3:
                                        allMoves.put(j - 384, new Knight(i, allianceTurn));
                                        break;
                                    case 4:
                                        allMoves.put(j - 384, new Bishop(i, allianceTurn));
                                        break;
                                    case 9:
                                        allMoves.put(j - 384, new Queen(i, allianceTurn));
                                        break;
                                    case 1000:
                                        allMoves.put(j - 384, new King(i, allianceTurn));
                                        break;
                                    case 1:
                                        allMoves.put(j - 384, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 448)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 5:
                                        allMoves.put(j - 448, new Rook(i, allianceTurn));
                                        break;
                                    case 3:
                                        allMoves.put(j - 448, new Knight(i, allianceTurn));
                                        break;
                                    case 4:
                                        allMoves.put(j - 448, new Bishop(i, allianceTurn));
                                        break;
                                    case 9:
                                        allMoves.put(j - 448, new Queen(i, allianceTurn));
                                        break;
                                    case 1000:
                                        allMoves.put(j - 448, new King(i, allianceTurn));
                                        break;
                                    case 1:
                                        allMoves.put(j - 448, new Pawn(i, allianceTurn));
                                }
                            }
                        } else if ((allMoves.get(j - 514)) == null) {
                            if (j != 100) {
                                switch (pieceAtThisPosition.getPointStatic()) {
                                    case 5:
                                        allMoves.put(j - 514, new Rook(i, allianceTurn));
                                        break;
                                    case 3:
                                        allMoves.put(j - 514, new Knight(i, allianceTurn));
                                        break;
                                    case 4:
                                        allMoves.put(j - 514, new Bishop(i, allianceTurn));
                                        break;
                                    case 9:
                                        allMoves.put(j - 514, new Queen(i, allianceTurn));
                                        break;
                                    case 1000:
                                        allMoves.put(j - 514, new King(i, allianceTurn));
                                        break;
                                    case 1:
                                        allMoves.put(j - 514, new Pawn(i, allianceTurn));
                                }
                            }
                        }
                    }
                }
            }
        }
        return allMoves;
    }

    //Tao ra cac trang thai co co the xay ra
    public List<ChessPosition> createAllNextPosition() {
        List<ChessPosition> nextPositions = new ArrayList<>();

        Map<Integer, Piece> possibleMoves = getAllPossibleMoves();
        for (int i = -514; i < 64; ++i) {
            if (possibleMoves.get(i) != null) {
                int fixFuturePosition;
                fixFuturePosition = i;
                while (fixFuturePosition < 0) {
                    fixFuturePosition += 64;
                }
                ChessPosition demoPosition = new ChessPosition(this);
                Piece pieceAtPosition = possibleMoves.get(i);
//                demoPosition.setNewPosition(fixFuturePosition);
//                demoPosition.setPieceJustMoved(pieceAtPosition);
                demoPosition.movePiece(pieceAtPosition, pieceAtPosition.piecePosition, fixFuturePosition);

//                boolean test = ChessPosition.isChecked(demoPosition, demoPosition.getAllianceTurn());
//                if(test == false){
//                    demoPosition.fixCastleCondition();
//                    nextPositions.add(demoPosition);
//                }
                demoPosition.fixCastleCondition();
                nextPositions.add(demoPosition);
            }
        }

        return nextPositions;
    }

    public static boolean isChecked(ChessPosition position, int alliance) {
        for (int i = 0; i < 64; ++i) {
            if (position.chessBoard.get(i) != null) {
                Piece pieceAtPosition = position.chessBoard.get(i);
                if ((pieceAtPosition.toString().equals("K")) && (pieceAtPosition.getAlliance() == alliance)) {
                    ArrayList<Integer> tileAttacked = position.getAllTileNotIncludeKing(1 - alliance);
                    if (tileAttacked.contains(i)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static ChessPosition createBasicChessPosition() {
        ChessPosition basicPosition = new ChessPosition();

        //Black
        basicPosition.chessBoard.put(0, new Rook(0, 1));
        basicPosition.chessBoard.put(1, new Knight(1, 1));
        basicPosition.chessBoard.put(2, new Bishop(2, 1));
        basicPosition.chessBoard.put(3, new Queen(3, 1));
        basicPosition.chessBoard.put(4, new King(4, 1));
        basicPosition.chessBoard.put(5, new Bishop(5, 1));
        basicPosition.chessBoard.put(6, new Knight(6, 1));
        basicPosition.chessBoard.put(7, new Rook(7, 1));
        basicPosition.chessBoard.put(8, new Pawn(8, 1));
        basicPosition.chessBoard.put(9, new Pawn(9, 1));
        basicPosition.chessBoard.put(10, new Pawn(10, 1));
        basicPosition.chessBoard.put(11, new Pawn(11, 1));
        basicPosition.chessBoard.put(12, new Pawn(12, 1));
        basicPosition.chessBoard.put(13, new Pawn(13, 1));
        basicPosition.chessBoard.put(14, new Pawn(14, 1));
        basicPosition.chessBoard.put(15, new Pawn(15, 1));

        //White
        basicPosition.chessBoard.put(63, new Rook(63, 0));
        basicPosition.chessBoard.put(62, new Knight(62, 0));
        basicPosition.chessBoard.put(61, new Bishop(61, 0));
        basicPosition.chessBoard.put(60, new King(60, 0));
        basicPosition.chessBoard.put(59, new Queen(59, 0));
        basicPosition.chessBoard.put(58, new Bishop(58, 0));
        basicPosition.chessBoard.put(57, new Knight(57, 0));
        basicPosition.chessBoard.put(56, new Rook(56, 0));
        basicPosition.chessBoard.put(55, new Pawn(55, 0));
        basicPosition.chessBoard.put(54, new Pawn(54, 0));
        basicPosition.chessBoard.put(53, new Pawn(53, 0));
        basicPosition.chessBoard.put(52, new Pawn(52, 0));
        basicPosition.chessBoard.put(51, new Pawn(51, 0));
        basicPosition.chessBoard.put(50, new Pawn(50, 0));
        basicPosition.chessBoard.put(49, new Pawn(49, 0));
        basicPosition.chessBoard.put(48, new Pawn(48, 0));

        return basicPosition;
    }

}
