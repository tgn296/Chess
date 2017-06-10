package chess.pkg2;

import java.util.List;

public class ChessAI {

    //Chọn nước đi tốt nhất cho quân đen
    public static ChessPosition chooseBestNextPosition2(ChessPosition currentPosition) {

//******Cấp độ 2-nguyên gốc************// 
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);
        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int minThisLoop = 10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int max1stPoint = -10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                if (candidateSecondLoop.calculatePositionPoint() > minThisLoop) {
                    max1stPoint = candidateSecondLoop.calculatePositionPoint();
                    break;
                }
                if (candidateSecondLoop.calculatePositionPoint() > max1stPoint) {
                    max1stPoint = candidateSecondLoop.calculatePositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(max1stPoint);
            if (candidateFirstLoop.getPositionPoint() < minThisLoop) {
                minThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
        return nextPositionChosen;
        //******Cấp độ 2-nguyên gốc************// 
    }

    public static ChessPosition chooseBestNextPosition3(ChessPosition currentPosition) {

//******Cấp độ 3-nguyên gốc************// 
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);
        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int minThisLoop = 10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int max1stPoint = -10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int min2ndPoint = 10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    if (candidateThirdLoop.calculatePositionPoint() < max1stPoint) {
                        min2ndPoint = candidateThirdLoop.calculatePositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.calculatePositionPoint() < min2ndPoint) {
                        min2ndPoint = candidateThirdLoop.calculatePositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(min2ndPoint);
                if (candidateSecondLoop.getPositionPoint() > minThisLoop) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() > max1stPoint) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(max1stPoint);
            if (candidateFirstLoop.getPositionPoint() < minThisLoop) {
                minThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
        return nextPositionChosen;
        //******Cấp độ 3-nguyên gốc************// 
    }

    public static ChessPosition chooseBestNextPosition4(ChessPosition currentPosition) {

        //******Cấp độ 4-nguyên gốc************// 
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);
        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int minThisLoop = 10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {

//            System.out.println(candidateFirstLoop.getPieceJustMoved().toString());
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int max1stPoint = -10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int min2ndPoint = 10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    List<ChessPosition> positionsIn4thLoop = candidateThirdLoop.createAllNextPosition();
                    int max3rdPoint = -10000;
                    for (ChessPosition candidateFourthLoop : positionsIn4thLoop) {
                        if (candidateFourthLoop.calculatePositionPoint() > min2ndPoint) {//chú ý chỗ này nếu muốn thay đổi chiều sâu
                            max3rdPoint = candidateFourthLoop.calculatePositionPoint();
                            break;
                        }
                        if (candidateFourthLoop.calculatePositionPoint() > max3rdPoint) {
                            max3rdPoint = candidateFourthLoop.calculatePositionPoint();
                        }
                    }
                    candidateThirdLoop.setPositionPointBottomUp(max3rdPoint);
                    if (candidateThirdLoop.getPositionPoint() < max1stPoint) {
                        min2ndPoint = candidateThirdLoop.getPositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.getPositionPoint() < min2ndPoint) {
                        min2ndPoint = candidateThirdLoop.getPositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(min2ndPoint);
                if (candidateSecondLoop.getPositionPoint() > minThisLoop) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() > max1stPoint) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(max1stPoint);
            if (candidateFirstLoop.getPositionPoint() < minThisLoop) {
                minThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
//        System.out.println(nextPositionChosen.getPositionPoint());
        return nextPositionChosen;
        //******Cấp độ 4-nguyên gốc************// 
    }

    public static ChessPosition chooseBestNextPosition5(ChessPosition currentPosition) {

        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);
        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int minThisLoop = 10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int max1stPoint = -10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int min2ndPoint = 10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    List<ChessPosition> positionsIn4thLoop = candidateThirdLoop.createAllNextPosition();
                    int max3rdPoint = -10000;
                    for (ChessPosition candidateFourthLoop : positionsIn4thLoop) {
                        List<ChessPosition> positionsIn5thLoop = candidateFourthLoop.createAllNextPosition();
                        int min4thPoint = 10000;
                        for (ChessPosition candidateFifthLoop : positionsIn5thLoop) {

                            if (candidateFifthLoop.calculatePositionPoint() < max3rdPoint) {
                                min4thPoint = candidateFifthLoop.calculatePositionPoint();
                                break;
                            }
                            if (candidateFifthLoop.calculatePositionPoint() < min4thPoint) {
                                min4thPoint = candidateFifthLoop.calculatePositionPoint();
                            }
                        }
                        candidateFourthLoop.setPositionPointBottomUp(min4thPoint);
                        if (candidateFourthLoop.getPositionPoint() > min2ndPoint) {
                            max3rdPoint = candidateFourthLoop.getPositionPoint();
                            break;
                        }
                        if (candidateFourthLoop.getPositionPoint() > max3rdPoint) {
                            max3rdPoint = candidateFourthLoop.getPositionPoint();
                        }
                    }
                    candidateThirdLoop.setPositionPointBottomUp(max3rdPoint);
                    if (candidateThirdLoop.getPositionPoint() < max1stPoint) {
                        min2ndPoint = candidateThirdLoop.getPositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.getPositionPoint() < min2ndPoint) {
                        min2ndPoint = candidateThirdLoop.getPositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(min2ndPoint);
                if (candidateSecondLoop.getPositionPoint() > minThisLoop) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() > max1stPoint) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(max1stPoint);
            if (candidateFirstLoop.getPositionPoint() < minThisLoop) {
                minThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
        return nextPositionChosen;
    }

    public static ChessPosition chooseBestNextPosition6(ChessPosition currentPosition) {

        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);
        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int minThisLoop = 10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int max1stPoint = -10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int min2ndPoint = 10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    List<ChessPosition> positionsIn4thLoop = candidateThirdLoop.createAllNextPosition();
                    int max3rdPoint = -10000;
                    for (ChessPosition candidateFourthLoop : positionsIn4thLoop) {
                        List<ChessPosition> positionsIn5thLoop = candidateFourthLoop.createAllNextPosition();
                        int min4thPoint = 10000;
                        for (ChessPosition candidateFifthLoop : positionsIn5thLoop) {
                            List<ChessPosition> positionsIn6thLoop = candidateFifthLoop.createAllNextPosition();
                            int max5thPoint = -10000;
                            for (ChessPosition candidateSixthLoop : positionsIn6thLoop) {
                                if (candidateSixthLoop.calculatePositionPoint() > min4thPoint) {
                                    max5thPoint = candidateSixthLoop.calculatePositionPoint();
                                    break;
                                }
                                if (candidateSixthLoop.calculatePositionPoint() > max5thPoint) {
                                    max5thPoint = candidateSixthLoop.calculatePositionPoint();
                                }
                            }
                            candidateFifthLoop.setPositionPointBottomUp(max5thPoint);
                            if (candidateFifthLoop.getPositionPoint() < max3rdPoint) {
                                min4thPoint = candidateFifthLoop.getPositionPoint();
                                break;
                            }
                            if (candidateFifthLoop.getPositionPoint() < min4thPoint) {
                                min4thPoint = candidateFifthLoop.getPositionPoint();
                            }
                        }
                        candidateFourthLoop.setPositionPointBottomUp(min4thPoint);
                        if (candidateFourthLoop.getPositionPoint() > min2ndPoint) {
                            max3rdPoint = candidateFourthLoop.getPositionPoint();
                            break;
                        }
                        if (candidateFourthLoop.getPositionPoint() > max3rdPoint) {
                            max3rdPoint = candidateFourthLoop.getPositionPoint();
                        }
                    }
                    candidateThirdLoop.setPositionPointBottomUp(max3rdPoint);
                    if (candidateThirdLoop.getPositionPoint() < max1stPoint) {
                        min2ndPoint = candidateThirdLoop.getPositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.getPositionPoint() < min2ndPoint) {
                        min2ndPoint = candidateThirdLoop.getPositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(min2ndPoint);
                if (candidateSecondLoop.getPositionPoint() > minThisLoop) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() > max1stPoint) {
                    max1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(max1stPoint);
            if (candidateFirstLoop.getPositionPoint() < minThisLoop) {
                minThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
        return nextPositionChosen;
    }

    public static ChessPosition chooseBestWhiteNextPosition2(ChessPosition currentPosition) {
//*************************Cấp độ 2 - Đã sửa đổi***************//
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);

        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int maxThisLoop = -10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int min1stPoint = 10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                if (candidateSecondLoop.calculatePositionPoint() < maxThisLoop) {
                    min1stPoint = candidateSecondLoop.calculatePositionPoint();
                    break;
                }
                if (candidateSecondLoop.calculatePositionPoint() < min1stPoint) {
                    min1stPoint = candidateSecondLoop.calculatePositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(min1stPoint);
            if (candidateFirstLoop.getPositionPoint() > maxThisLoop) {
                maxThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
//        System.out.println(nextPositionChosen.getPositionPoint());
        return nextPositionChosen;
//**********************************Cấp độ 2 - Đã sửa đổi***************//
    }

    public static ChessPosition chooseBestWhiteNextPosition3(ChessPosition currentPosition) {
//*************************Cấp độ 3 - Đã sửa đổi***************//
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);

        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int maxThisLoop = -10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int min1stPoint = 10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int max2ndPoint = -10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    if (candidateThirdLoop.calculatePositionPoint() > min1stPoint) {
                        max2ndPoint = candidateThirdLoop.calculatePositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.calculatePositionPoint() > max2ndPoint) {
                        max2ndPoint = candidateThirdLoop.calculatePositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(max2ndPoint);
                if (candidateSecondLoop.getPositionPoint() < maxThisLoop) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() < min1stPoint) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(min1stPoint);
            if (candidateFirstLoop.getPositionPoint() > maxThisLoop) {
                maxThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
//        System.out.println(nextPositionChosen.getPositionPoint());
        return nextPositionChosen;
//**********************************Cấp độ 3 - Đã sửa đổi***************//
    }

    public static ChessPosition chooseBestWhiteNextPosition4(ChessPosition currentPosition) {
//*************************Cấp độ 4 - Đã sửa đổi***************//
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);

        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int maxThisLoop = -10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int min1stPoint = 10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int max2ndPoint = -10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    List<ChessPosition> positionsIn4thLoop = candidateThirdLoop.createAllNextPosition();
                    int min3rdPoint = 10000;
                    for (ChessPosition candidateFourthLoop : positionsIn4thLoop) {
                        if (candidateFourthLoop.calculatePositionPoint() < max2ndPoint) {
                            min3rdPoint = candidateFourthLoop.calculatePositionPoint();
                            break;
                        }
                        if (candidateFourthLoop.calculatePositionPoint() < min3rdPoint) {
                            min3rdPoint = candidateFourthLoop.calculatePositionPoint();
                        }
                    }
                    candidateThirdLoop.setPositionPointBottomUp(min3rdPoint);
                    if (candidateThirdLoop.getPositionPoint() > min1stPoint) {
                        max2ndPoint = candidateThirdLoop.getPositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.getPositionPoint() > max2ndPoint) {
                        max2ndPoint = candidateThirdLoop.getPositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(max2ndPoint);
                if (candidateSecondLoop.getPositionPoint() < maxThisLoop) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() < min1stPoint) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(min1stPoint);
            if (candidateFirstLoop.getPositionPoint() > maxThisLoop) {
                maxThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
//        System.out.println(nextPositionChosen.getPositionPoint());
        return nextPositionChosen;
//**********************************Cấp độ 4 - Đã sửa đổi***************//
    }

    public static ChessPosition chooseBestWhiteNextPosition5(ChessPosition currentPosition) {
//*************************Cấp độ 5 - Đã sửa đổi***************//
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);

        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int maxThisLoop = -10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int min1stPoint = 10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int max2ndPoint = -10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    List<ChessPosition> positionsIn4thLoop = candidateThirdLoop.createAllNextPosition();
                    int min3rdPoint = 10000;
                    for (ChessPosition candidateFourthLoop : positionsIn4thLoop) {
                        List<ChessPosition> positionsIn5thLoop = candidateFourthLoop.createAllNextPosition();
                        int max4rdPoint = -10000;
                        for (ChessPosition candidateFifthLoop : positionsIn5thLoop) {
                            if (candidateFifthLoop.calculatePositionPoint() > min3rdPoint) {
                                max4rdPoint = candidateFifthLoop.calculatePositionPoint();
                                break;
                            }
                            if (candidateFourthLoop.calculatePositionPoint() > max4rdPoint) {
                                max4rdPoint = candidateFourthLoop.calculatePositionPoint();
                            }

                        }
                        candidateFourthLoop.setPositionPointBottomUp(max4rdPoint);
                        if (candidateFourthLoop.getPositionPoint() < max2ndPoint) {
                            min3rdPoint = candidateFourthLoop.getPositionPoint();
                            break;
                        }
                        if (candidateFourthLoop.getPositionPoint() < min3rdPoint) {
                            min3rdPoint = candidateFourthLoop.getPositionPoint();
                        }
                    }
                    candidateThirdLoop.setPositionPointBottomUp(min3rdPoint);
                    if (candidateThirdLoop.getPositionPoint() > min1stPoint) {
                        max2ndPoint = candidateThirdLoop.getPositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.getPositionPoint() > max2ndPoint) {
                        max2ndPoint = candidateThirdLoop.getPositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(max2ndPoint);
                if (candidateSecondLoop.getPositionPoint() < maxThisLoop) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() < min1stPoint) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(min1stPoint);
            if (candidateFirstLoop.getPositionPoint() > maxThisLoop) {
                maxThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
//        System.out.println(nextPositionChosen.getPositionPoint());
        return nextPositionChosen;
//**********************************Cấp độ 5 - Đã sửa đổi***************//
    }

    public static ChessPosition chooseBestWhiteNextPosition6(ChessPosition currentPosition) {
//*************************Cấp độ 6 - Đã sửa đổi***************//
        ChessPosition nextPositionChosen;
        nextPositionChosen = new ChessPosition(currentPosition);

        List<ChessPosition> positionsIn1stLoop = currentPosition.createAllNextPosition();
        int maxThisLoop = -10000;
        for (ChessPosition candidateFirstLoop : positionsIn1stLoop) {
            List<ChessPosition> positionsIn2ndLoop = candidateFirstLoop.createAllNextPosition();
            int min1stPoint = 10000;
            for (ChessPosition candidateSecondLoop : positionsIn2ndLoop) {
                List<ChessPosition> positionsIn3rdLoop = candidateSecondLoop.createAllNextPosition();
                int max2ndPoint = -10000;
                for (ChessPosition candidateThirdLoop : positionsIn3rdLoop) {
                    List<ChessPosition> positionsIn4thLoop = candidateThirdLoop.createAllNextPosition();
                    int min3rdPoint = 10000;
                    for (ChessPosition candidateFourthLoop : positionsIn4thLoop) {
                        List<ChessPosition> positionsIn5thLoop = candidateFourthLoop.createAllNextPosition();
                        int max4rdPoint = -10000;
                        for (ChessPosition candidateFifthLoop : positionsIn5thLoop) {
                            List<ChessPosition> positionsIn6thLoop = candidateFifthLoop.createAllNextPosition();
                            int min5rdPoint = 10000;
                            for (ChessPosition candidateSixthLoop : positionsIn6thLoop) {
                                if (candidateSixthLoop.calculatePositionPoint() < max4rdPoint) {
                                    min5rdPoint = candidateSixthLoop.calculatePositionPoint();
                                    break;
                                }
                                if (candidateSixthLoop.calculatePositionPoint() < min5rdPoint) {
                                    min5rdPoint = candidateSixthLoop.calculatePositionPoint();
                                }

                            }
                            candidateFifthLoop.setPositionPointBottomUp(min5rdPoint);
                            if (candidateFifthLoop.getPositionPoint() > min3rdPoint) {
                                max4rdPoint = candidateFifthLoop.getPositionPoint();
                                break;
                            }
                            if (candidateFourthLoop.getPositionPoint() > max4rdPoint) {
                                max4rdPoint = candidateFourthLoop.getPositionPoint();
                            }

                        }
                        candidateFourthLoop.setPositionPointBottomUp(max4rdPoint);
                        if (candidateFourthLoop.getPositionPoint() < max2ndPoint) {
                            min3rdPoint = candidateFourthLoop.getPositionPoint();
                            break;
                        }
                        if (candidateFourthLoop.getPositionPoint() < min3rdPoint) {
                            min3rdPoint = candidateFourthLoop.getPositionPoint();
                        }
                    }
                    candidateThirdLoop.setPositionPointBottomUp(min3rdPoint);
                    if (candidateThirdLoop.getPositionPoint() > min1stPoint) {
                        max2ndPoint = candidateThirdLoop.getPositionPoint();
                        break;
                    }
                    if (candidateThirdLoop.getPositionPoint() > max2ndPoint) {
                        max2ndPoint = candidateThirdLoop.getPositionPoint();
                    }
                }
                candidateSecondLoop.setPositionPointBottomUp(max2ndPoint);
                if (candidateSecondLoop.getPositionPoint() < maxThisLoop) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                    break;
                }
                if (candidateSecondLoop.getPositionPoint() < min1stPoint) {
                    min1stPoint = candidateSecondLoop.getPositionPoint();
                }
            }
            candidateFirstLoop.setPositionPointBottomUp(min1stPoint);
            if (candidateFirstLoop.getPositionPoint() > maxThisLoop) {
                maxThisLoop = candidateFirstLoop.getPositionPoint();
                nextPositionChosen.chessBoard = ChessPosition.copyChessBoard(candidateFirstLoop);
                nextPositionChosen.setPieceJustMoved(candidateFirstLoop.getPieceJustMoved());
                nextPositionChosen.setNewPosition(candidateFirstLoop.getPieceJustMovedNewPosition());
            }
        }
//        System.out.println(nextPositionChosen.getPositionPoint());
        return nextPositionChosen;
//**********************************Cấp độ 6 - Đã sửa đổi***************//
    }
}
