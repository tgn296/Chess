package chess.pkg2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Table extends JFrame implements Runnable {

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(500, 500);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);

    //chế độ chơi
    private final int MANCOMP = 1;//người chơi với máy
    private final int COMPCOMP = 0;//máy chơi với máy
    static int mode;
    static boolean endGame = false;
    
    public static int blacklevel;
    public static int whitelevel;
    public static StringBuffer moveInfor;
    private long timeStart ;

    static NavFrame nav;

    //undo và redo
    static ArrayList<ChessPosition> history;
    static int currentHistory;//vị trí lịch sử đang ở hiện tại

    static int preCoordinate;
    static int currentAlliance;
    static int[] preTilesChosen = new int[27];
    static int size = 0;

    private final Color lightTileColor = Color.decode("#e0e0e0");
    private final Color darkTileColor = Color.decode("#9d9d9d");

    static ChessPosition position;
    static ChessPosition formerPosition;
    static BoardPanel boardPanel;

    public Table(int mode, int blacklevel, int whitelevel) {
        this.setTitle("AI-CHESS");
        this.setLayout(new BorderLayout());
        this.setSize(OUTER_FRAME_DIMENSION);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.timeStart = System.currentTimeMillis();
        Table.position = ChessPosition.createBasicChessPosition();
        Table.formerPosition = ChessPosition.createBasicChessPosition();
        Table.boardPanel = new BoardPanel();
        this.add(Table.boardPanel, BorderLayout.CENTER);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(350, 100);
        Table.blacklevel = blacklevel;
        this.whitelevel = whitelevel;
        this.nav = new NavFrame();
        
//        NorthPanel np = new NorthPanel();
        this.add(new HorizontalPanel(),BorderLayout.NORTH);
        this.add(new HorizontalPanel(),BorderLayout.SOUTH);
//        WestPanel wp = new WestPanel();
        this.add(new VerticalPanel(),BorderLayout.WEST);
        this.add(new VerticalPanel(),BorderLayout.EAST);

        history = new ArrayList<>();//lịch sử
        currentHistory = 0;
        history.add(new ChessPosition(formerPosition));

        Table.currentAlliance = 1;//1 - đen , , 0 - trắng

        //*****Thêm chức năng đánh giữa 2 máy tính ********//
        Table.mode = mode;
        if (mode == COMPCOMP) {
            if (Table.currentAlliance == 0) {
                autoBlackPlay(position);
            } else {
                autoWhitePlay(position);
            }
        } else {
            setAlliance();
        }
        //***********************************************//
    }

    public void setAlliance() {
        Table.currentAlliance = 0;
    }

    //*****Thêm chức năng đánh giữa 2 máy tính ********//
    public void autoBlackPlay(ChessPosition currentPosition) {

        /**/
        ChessPosition src = new ChessPosition(currentPosition);

        currentPosition.endGameAnnoucement();
        currentPosition.fixCastleCondition();
        ChessPosition nextComputerPosition = null;
        long start = System.currentTimeMillis();
        switch (blacklevel) {
            case 2:
                nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                break;
            case 3:
                nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                break;
            case 4:
                nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                break;
            case 5:
                nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                break;
            case 6:
                nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                break;
        }
        long elapsedTime = System.currentTimeMillis() - start;
        currentPosition.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
        currentPosition.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
        currentPosition.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());

        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
        currentPosition.endGameAnnoucement();

        /**/
        //Vẽ lại bàn cờ
        for (int ii = 0; ii < 64; ++ii) {
            boardPanel.tileList.get(ii).removeAll();
            if (currentPosition.chessBoard.get(ii) != null) {

                
                    String s = "/images/" + currentPosition.chessBoard.get(ii).getAlliance() + currentPosition.chessBoard.get(ii).toString() + ".gif";
                    URL iconUrl1 = this.getClass().getResource(s);
                    Toolkit tk1 = this.getToolkit();
                    Image someimgicon = tk1.getImage(iconUrl1);
                    boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(someimgicon)));

                
            }
        }
        currentAlliance = Move.changeAlliance(currentAlliance);
        currentPosition.setCurrentAlliance(1 - currentAlliance);
        validate();
        repaint();
        boardPanel.setSize(600, 600);
        if (!Table.endGame) {
            autoWhitePlay(currentPosition);
        }
    }

    public void autoWhitePlay(ChessPosition currentPosition) {

        /**/
        ChessPosition src = new ChessPosition(currentPosition);

        currentPosition.endGameAnnoucement();
        currentPosition.fixCastleCondition();

        ChessPosition nextComputerPosition = null;
        long start = System.currentTimeMillis();
        switch (whitelevel) {
            case 2:
                nextComputerPosition = ChessAI.chooseBestWhiteNextPosition2(position);
                break;
            case 3:
                nextComputerPosition = ChessAI.chooseBestWhiteNextPosition3(position);
                break;
            case 4:
                nextComputerPosition = ChessAI.chooseBestWhiteNextPosition4(position);
                break;
            case 5:
                nextComputerPosition = ChessAI.chooseBestWhiteNextPosition5(position);
                break;
            case 6:
                nextComputerPosition = ChessAI.chooseBestWhiteNextPosition6(position);
                break;
        }
        long elapsedTime = System.currentTimeMillis() - start;
        currentPosition.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
        currentPosition.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
        currentPosition.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);

        currentPosition.endGameAnnoucement();

        /**/
        //Vẽ lại bàn cờ
        for (int ii = 0; ii < 64; ++ii) {
            boardPanel.tileList.get(ii).removeAll();
            if (currentPosition.chessBoard.get(ii) != null) {

                
                    String s = "/images/" + currentPosition.chessBoard.get(ii).getAlliance() + currentPosition.chessBoard.get(ii).toString() + ".gif";
                    URL iconUrl1 = this.getClass().getResource(s);
                    Toolkit tk1 = this.getToolkit();
                    Image someimgicon = tk1.getImage(iconUrl1);
                    boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(someimgicon)));

                
            }
        }
        currentAlliance = Move.changeAlliance(currentAlliance);
        currentPosition.setCurrentAlliance(1 - currentAlliance);
        validate();
        repaint();
        boardPanel.setSize(600, 600);
        if (!Table.endGame) {
            autoBlackPlay(currentPosition);
        }

    }
    //********************************//

    public void undoTable() {
        if (mode == 1 && currentHistory > 0) {
            currentAlliance = 0;
            position.createSimilarPosition(history.get(currentHistory - 1));
            currentHistory -= 1;
            for (int tileIndex = 0; tileIndex <= 63; ++tileIndex) {
                boardPanel.tileList.get(tileIndex).removeAll();
                if (position.chessBoard.get(tileIndex) != null) {
                    
                        String s = "/images/" + position.chessBoard.get(tileIndex).getAlliance() + position.chessBoard.get(tileIndex).toString() + ".gif";
                        URL iconUrl1 = this.getClass().getResource(s);
                        Toolkit tk1 = this.getToolkit();
                        Image imageComputer = tk1.getImage(iconUrl1);
                        boardPanel.tileList.get(tileIndex).add(new JLabel(new ImageIcon(imageComputer)));
                    
                }

            }
            boardPanel.setSize(500, 500);
        }
    }

    public void redoTable() {
        if (mode == 1 && (currentHistory < history.size() - 1)) {
            currentAlliance = 0;
            position.createSimilarPosition(history.get(currentHistory + 1));///**********///
            currentHistory += 1;
            for (int tileIndex = 0; tileIndex <= 63; ++tileIndex) {
                boardPanel.tileList.get(tileIndex).removeAll();
                if (position.chessBoard.get(tileIndex) != null) {
                    
                        String s = "/images/" + position.chessBoard.get(tileIndex).getAlliance() + position.chessBoard.get(tileIndex).toString() + ".gif";
                        URL iconUrl1 = this.getClass().getResource(s);
                        Toolkit tk1 = this.getToolkit();
                        Image imageComputer = tk1.getImage(iconUrl1);
                        boardPanel.tileList.get(tileIndex).add(new JLabel(new ImageIcon(imageComputer)));
                   
                }

            }
            boardPanel.setSize(500, 500);
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class BoardPanel extends JPanel {

        List<TilePanel> tileList;
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.decode("#2e3337"), 0);

        public BoardPanel() {
            super(new GridLayout(8, 8, 0, 0));
            this.tileList = new ArrayList<>();
            for (int i = 0; i < 64; ++i) {
                TilePanel tilePanel = new TilePanel(i, position, this);
                this.setBorder(border);
                this.tileList.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }
    }

    private class TilePanel extends JPanel {

        final int tileNumber;
        BoardPanel boardPanel;

        public TilePanel(final int tileNumber, final ChessPosition position, final BoardPanel boardPanel) {
            super(new GridLayout());
            this.boardPanel = boardPanel;
            this.tileNumber = tileNumber;

            setPreferredSize(TILE_PANEL_DIMENSION);
            if (((tileNumber / 8 == 0) || (tileNumber / 8 == 2) || (tileNumber / 8 == 4) || (tileNumber / 8 == 6)) && (tileNumber % 2 == 0)) {
                this.setBackground(lightTileColor);
            } else if (((tileNumber / 8 == 1) || (tileNumber / 8 == 3) || (tileNumber / 8 == 5) || (tileNumber / 8 == 7)) && (tileNumber % 2 == 1)) {
                this.setBackground(lightTileColor);
            } else {
                this.setBackground(darkTileColor);
            }

            Piece pieceAtPosition = position.chessBoard.get(tileNumber);

            if (pieceAtPosition != null) {

                String s = "/images/" + pieceAtPosition.getAlliance() + pieceAtPosition.toString() + ".gif";
                URL iconUrl1 = this.getClass().getResource(s);
                Toolkit tk1 = this.getToolkit();
                Image image = tk1.getImage(iconUrl1);
                add(new JLabel(new ImageIcon(image)));

            }

            this.addMouseListener(new MouseListener() {
                
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (Table.mode == MANCOMP) {
                        ChessPosition src = new ChessPosition(position);

                        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.GREEN, 3);
                        Piece pieceAtPosition = position.chessBoard.get(tileNumber);
                        ChessPosition nextComputerPosition = null;

                        if (pieceAtPosition != null) {

                            if ((pieceAtPosition.alliance == currentAlliance)) {
                                if (Table.size > 0) {
                                    for (int j = 0; j < Table.size; j++) {
                                        boardPanel.tileList.get(Table.preTilesChosen[j]).setBorder(null);
                                    }
                                    Table.size = 0;
                                }
                                

                                for (int i : pieceAtPosition.getPossibleNextPosition(position)) {
                                    if (i != 100) {
                                        boardPanel.tileList.get(i).setBorder(border);
                                        Table.preTilesChosen[Table.size++] = i;

                                        validate();
                                    }
                                }
                                preCoordinate = tileNumber;

                            } else {

                                for (int i = 0; i < Table.size; i++) {
                                    if (tileNumber == Table.preTilesChosen[i]) {
                                        boardPanel.tileList.get(tileNumber).removeAll();

                                        formerPosition.createSimilarPosition(position);
                                        position.movePiece(position.chessBoard.get(preCoordinate), preCoordinate, tileNumber);
//                                            System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                        long timeEnd = System.currentTimeMillis() - timeStart;
                                        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), timeEnd);

                                        position.endGameAnnoucement();

                                        String s = "/images/" + position.chessBoard.get(tileNumber).getAlliance() + position.chessBoard.get(tileNumber).toString() + ".gif";

                                        URL iconUrl1 = this.getClass().getResource(s);
                                        Toolkit tk1 = boardPanel.getToolkit();
                                        Image image = tk1.getImage(iconUrl1);
                                        boardPanel.tileList.get(tileNumber).add(new JLabel(new ImageIcon(image)));
                                        boardPanel.tileList.get(preCoordinate).removeAll();

                                        for (int j = 0; j < Table.size; j++) {
                                            boardPanel.tileList.get(Table.preTilesChosen[j]).setBorder(null);
                                        }
                                        Table.size = 0;
                                        position.setCurrentAlliance(1 - currentAlliance);

                                        currentAlliance = Move.changeAlliance(currentAlliance);
                                        position.fixCastleCondition();
                                        /**/
                                        long start = System.currentTimeMillis();
                                        switch (blacklevel) {
                                            case 2:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                                                break;
                                            case 3:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                                                break;
                                            case 4:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                                                break;
                                            case 5:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                                                break;
                                            case 6:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                                                break;
                                        }
                                        /**/
                                        long elapsedTime = System.currentTimeMillis() - start;

                                        position.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
                                        position.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
                                        position.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
                                        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
                                        position.endGameAnnoucement();
                                        for (int ii = 0; ii < 64; ++ii) {
                                            boardPanel.tileList.get(ii).removeAll();
                                            if (position.chessBoard.get(ii) != null) {
                                                
                                                    s = "/images/" + position.chessBoard.get(ii).getAlliance() + position.chessBoard.get(ii).toString() + ".gif";

                                                    iconUrl1 = this.getClass().getResource(s);
                                                    tk1 = boardPanel.getToolkit();
                                                    Image imageComputer = tk1.getImage(iconUrl1);
                                                    boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(imageComputer)));
                                                
                                            }
                                            validate();
                                            repaint();
                                            boardPanel.setSize(600, 600);
                                        }
                                        currentAlliance = Move.changeAlliance(currentAlliance);
                                        //**thêm trạng thái bàn cờ vào lịch sử 
                                        history.add(currentHistory + 1, new ChessPosition(nextComputerPosition));
                                        currentHistory = currentHistory + 1;

                                        boardPanel.setSize(600, 600);
                                        validate();
                                        repaint();
                                        timeStart = System.currentTimeMillis();
                                    }

                                }
                            }

                        } else if (position.chessBoard.get(preCoordinate) != null) {
                            if (!(position.chessBoard.get(preCoordinate).toString().equals("K"))) {
                                for (int i = 0; i < Table.size; i++) {
                                    if (tileNumber == Table.preTilesChosen[i]) {
                                        boardPanel.tileList.get(preCoordinate).removeAll();
                                        validate();
                                        formerPosition.createSimilarPosition(position);
                                        position.movePiece(position.chessBoard.get(preCoordinate), preCoordinate, tileNumber);
//                                        System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                        long timeEnd = System.currentTimeMillis() - timeStart;
                                        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), timeEnd);
                                        position.endGameAnnoucement();

                                        position.setCurrentAlliance(1 - currentAlliance);
                                        currentAlliance = Move.changeAlliance(currentAlliance);
                                        position.fixCastleCondition();
                                        /**/
                                        long start = System.currentTimeMillis();
                                        switch (blacklevel) {
                                            case 2:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                                                break;
                                            case 3:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                                                break;
                                            case 4:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                                                break;
                                            case 5:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                                                break;
                                            case 6:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                                                break;
                                        }
                                        /**/
                                        long elapsedTime = System.currentTimeMillis() - start;
                                        position.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
                                        position.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
                                        position.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
//                                        System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
                                        position.endGameAnnoucement();

                                        for (int ii = 0; ii < 64; ++ii) {
                                            boardPanel.tileList.get(ii).removeAll();
                                            if (position.chessBoard.get(ii) != null) {

                                                
                                                    String s = "/images/" + position.chessBoard.get(ii).getAlliance() + position.chessBoard.get(ii).toString() + ".gif";

                                                    URL iconUrl1 = this.getClass().getResource(s);
                                                    Toolkit tk1 = boardPanel.getToolkit();
                                                    Image imageComputer = tk1.getImage(iconUrl1);
                                                    boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(imageComputer)));

                                            }
                                        }
                                        currentAlliance = Move.changeAlliance(currentAlliance);

                                        //**thêm trạng thái bàn cờ vào lịch sử 
                                        history.add(currentHistory + 1, new ChessPosition(nextComputerPosition));
                                        currentHistory = currentHistory + 1;

                                        for (int k = 0; k < Table.size; k++) {
                                            boardPanel.tileList.get(Table.preTilesChosen[k]).setBorder(null);
                                        }
                                        Table.size = 0;
                                        validate();
                                    }
                                }
                                boardPanel.setSize(600, 600);
                                timeStart = System.currentTimeMillis();

                            } else if (tileNumber != 62 && tileNumber != 58 && tileNumber != 2 && tileNumber != 6 || (preCoordinate != 60 && preCoordinate != 4)) {
                                for (int i = 0; i < Table.size; i++) {
                                    if (tileNumber == Table.preTilesChosen[i]) {
                                        boardPanel.tileList.get(preCoordinate).removeAll();
                                        validate();
                                        formerPosition.createSimilarPosition(position);
                                        position.movePiece(position.chessBoard.get(preCoordinate), preCoordinate, tileNumber);
//                                        System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                        long timeEnd = System.currentTimeMillis() - timeStart;
                                        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), timeEnd);
                                        position.endGameAnnoucement();

                                        for (int k = 0; k < Table.size; k++) {
                                            boardPanel.tileList.get(Table.preTilesChosen[k]).setBorder(null);
                                        }
                                        Table.size = 0;
                                        position.setCurrentAlliance(1 - currentAlliance);
                                        currentAlliance = Move.changeAlliance(currentAlliance);
                                        position.fixCastleCondition();
                                        /**/
                                        long start = System.currentTimeMillis();
                                        switch (blacklevel) {
                                            case 2:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                                                break;
                                            case 3:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                                                break;
                                            case 4:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                                                break;
                                            case 5:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                                                break;
                                            case 6:
                                                nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                                                break;
                                        }
                                        /**/
                                        long elapsedTime = System.currentTimeMillis() - start;

                                        position.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
                                        position.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
                                        position.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
//                                        System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                        nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
                                        position.endGameAnnoucement();
                                        for (int ii = 0; ii < 64; ++ii) {
                                            boardPanel.tileList.get(ii).removeAll();
                                            if (position.chessBoard.get(ii) != null) {
                                                
                                                    String s = "/images/" + position.chessBoard.get(ii).getAlliance() + position.chessBoard.get(ii).toString() + ".gif";

                                                    URL iconUrl1 = this.getClass().getResource(s);
                                                    Toolkit tk1 = boardPanel.getToolkit();
                                                    Image imageComputer = tk1.getImage(iconUrl1);
                                                    boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(imageComputer)));

                                            }
                                        }

                                        currentAlliance = Move.changeAlliance(currentAlliance);

                                        //**thêm trạng thái bàn cờ vào lịch sử 
                                        history.add(currentHistory + 1, new ChessPosition(nextComputerPosition));
                                        currentHistory = currentHistory + 1;
                                        validate();
                                    }
                                }
                                boardPanel.setSize(600, 600);
                                timeStart = System.currentTimeMillis();

                            } else if (preCoordinate == 60 && position.chessBoard.get(preCoordinate).getAlliance() == 0 && tileNumber == 62 && boardPanel.tileList.get(62).getBorder() != null) {
                                for (int k = 0; k < Table.size; ++k) {
                                    boardPanel.tileList.get(Table.preTilesChosen[k]).setBorder(null);
                                }
                                size = 0;
                                formerPosition.createSimilarPosition(position);
                                position.movePiece(position.chessBoard.get(60), 60, 62);
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                long timeEnd = System.currentTimeMillis() - timeStart;
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), timeEnd);
                                position.fixCastleCondition();
                                position.setCurrentAlliance(1 - currentAlliance);
                                currentAlliance = Move.changeAlliance(currentAlliance);
                                /**/
                                long start = System.currentTimeMillis();
                                switch (blacklevel) {
                                    case 2:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                                        break;
                                    case 3:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                                        break;
                                    case 4:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                                        break;
                                    case 5:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                                        break;
                                    case 6:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                                        break;
                                }
                                /**/
                                long elapsedTime = System.currentTimeMillis() - start;

                                position.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
                                position.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
                                position.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
                                position.endGameAnnoucement();
                                for (int ii = 0; ii < 64; ++ii) {
                                    boardPanel.tileList.get(ii).removeAll();
                                    if (position.chessBoard.get(ii) != null) {
                                        
                                            String s = "/images/" + position.chessBoard.get(ii).getAlliance() + position.chessBoard.get(ii).toString() + ".gif";

                                            URL iconUrl1 = this.getClass().getResource(s);
                                            Toolkit tk1 = boardPanel.getToolkit();
                                            Image imageComputer = tk1.getImage(iconUrl1);
                                            boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(imageComputer)));

                                        
                                    }
                                }

                                currentAlliance = Move.changeAlliance(currentAlliance);

                                //**thêm trạng thái bàn cờ vào lịch sử 
                                history.add(currentHistory + 1, new ChessPosition(nextComputerPosition));
                                currentHistory = currentHistory + 1;
                                validate();
                                boardPanel.setSize(600, 600);
                                timeStart = System.currentTimeMillis();
                            } else if (preCoordinate == 60 && position.chessBoard.get(preCoordinate).getAlliance() == 0 && tileNumber == 58 && boardPanel.tileList.get(58).getBorder() != null) {
                                for (int k = 0; k < Table.size; ++k) {
                                    boardPanel.tileList.get(Table.preTilesChosen[k]).setBorder(null);
                                }
                                size = 0;
                                formerPosition.createSimilarPosition(position);
                                position.movePiece(position.chessBoard.get(60), 60, 58);
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                long timeEnd = System.currentTimeMillis() - timeStart;
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), timeEnd);
                                position.fixCastleCondition();
                                position.setCurrentAlliance(1 - currentAlliance);
                                currentAlliance = Move.changeAlliance(currentAlliance);
                                /**/
                                long start = System.currentTimeMillis();
                                switch (blacklevel) {
                                    case 2:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                                        break;
                                    case 3:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                                        break;
                                    case 4:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                                        break;
                                    case 5:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                                        break;
                                    case 6:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                                        break;
                                }
                                /**/
                                long elapsedTime = System.currentTimeMillis() - start;

                                position.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
                                position.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
                                position.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
                                position.endGameAnnoucement();
                                for (int ii = 0; ii < 64; ++ii) {
                                    boardPanel.tileList.get(ii).removeAll();
                                    if (position.chessBoard.get(ii) != null) {
                                       
                                            String s = "/images/" + position.chessBoard.get(ii).getAlliance() + position.chessBoard.get(ii).toString() + ".gif";

                                            URL iconUrl1 = this.getClass().getResource(s);
                                            Toolkit tk1 = boardPanel.getToolkit();
                                            Image imageComputer = tk1.getImage(iconUrl1);
                                            boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(imageComputer)));

                                    }
                                }

                                currentAlliance = Move.changeAlliance(currentAlliance);

                                //**thêm trạng thái bàn cờ vào lịch sử 
                                history.add(currentHistory + 1, new ChessPosition(nextComputerPosition));
                                currentHistory = currentHistory + 1;
                                boardPanel.setSize(600, 600);
                                timeStart = System.currentTimeMillis();
                            } else if (preCoordinate == 4 && position.chessBoard.get(preCoordinate).getAlliance() == 1 && tileNumber == 6 && boardPanel.tileList.get(6).getBorder() != null) {
                                for (int k = 0; k < Table.size; ++k) {
                                    boardPanel.tileList.get(Table.preTilesChosen[k]).setBorder(null);
                                }
                                size = 0;
                                formerPosition.createSimilarPosition(position);
                                position.movePiece(position.chessBoard.get(4), 4, 6);
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                long timeEnd = System.currentTimeMillis() - timeStart;
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), timeEnd);
                                position.fixCastleCondition();
                                position.setCurrentAlliance(1 - currentAlliance);
                                currentAlliance = Move.changeAlliance(currentAlliance);
                                /**/
                                long start = System.currentTimeMillis();
                                switch (blacklevel) {
                                    case 2:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                                        break;
                                    case 3:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                                        break;
                                    case 4:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                                        break;
                                    case 5:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                                        break;
                                    case 6:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                                        break;
                                }
                                long elapsedTime = System.currentTimeMillis() - start;
                                position.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
                                position.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
                                position.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
                                position.endGameAnnoucement();
                                for (int ii = 0; ii < 64; ++ii) {
                                    boardPanel.tileList.get(ii).removeAll();
                                    if (position.chessBoard.get(ii) != null) {
                                      
                                            String s = "/images/" + position.chessBoard.get(ii).getAlliance() + position.chessBoard.get(ii).toString() + ".gif";

                                            URL iconUrl1 = this.getClass().getResource(s);
                                            Toolkit tk1 = boardPanel.getToolkit();
                                            Image imageComputer = tk1.getImage(iconUrl1);
                                            boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(imageComputer)));

                                        
                                    }
                                }

                                currentAlliance = Move.changeAlliance(currentAlliance);

                                //**thêm trạng thái bàn cờ vào lịch sử 
                                history.add(currentHistory + 1, new ChessPosition(nextComputerPosition));
                                currentHistory = currentHistory + 1;
                                boardPanel.setSize(600, 600);
                                timeStart = System.currentTimeMillis();
                            } else if (position.chessBoard.get(preCoordinate).getAlliance() == 1 && tileNumber == 2 && boardPanel.tileList.get(2).getBorder() != null) {
                                for (int k = 0; k < Table.size; ++k) {
                                    boardPanel.tileList.get(Table.preTilesChosen[k]).setBorder(null);
                                }
                                size = 0;
                                formerPosition.createSimilarPosition(position);
                                position.movePiece(position.chessBoard.get(4), 4, 2);
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                long timeEnd = System.currentTimeMillis() - timeStart;
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), timeEnd);
                                position.fixCastleCondition();
                                position.setCurrentAlliance(1 - currentAlliance);
                                currentAlliance = Move.changeAlliance(currentAlliance);
                                /**/
                                long start = System.currentTimeMillis();
                                switch (blacklevel) {
                                    case 2:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition2(position);
                                        break;
                                    case 3:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition3(position);
                                        break;
                                    case 4:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition4(position);
                                        break;
                                    case 5:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition5(position);
                                        break;
                                    case 6:
                                        nextComputerPosition = ChessAI.chooseBestNextPosition6(position);
                                        break;
                                }
                                /**/
                                long elapsedTime = System.currentTimeMillis() - start;
                                position.chessBoard = ChessPosition.copyChessBoard(nextComputerPosition);
                                position.setPieceJustMoved(nextComputerPosition.getPieceJustMoved());
                                position.setNewPosition(nextComputerPosition.getPieceJustMovedNewPosition());
//                                System.out.println(position.getPieceJustMoved() + "\t" + position.getPieceJustMoved().piecePosition + "\t" + position.getPieceJustMovedNewPosition() + "\t" + position.getPieceJustMoved().getAlliance());
                                nav.showInfor2(position.getPieceJustMoved().toString(), position.getPieceJustMoved().piecePosition, position.getPieceJustMovedNewPosition(), position.getPieceJustMoved().getAlliance(), elapsedTime);
                                position.endGameAnnoucement();
                                for (int ii = 0; ii < 64; ++ii) {
                                    boardPanel.tileList.get(ii).removeAll();
                                    if (position.chessBoard.get(ii) != null) {
                                        
                                            String s = "/images/" + position.chessBoard.get(ii).getAlliance() + position.chessBoard.get(ii).toString() + ".gif";

                                            URL iconUrl1 = this.getClass().getResource(s);
                                            Toolkit tk1 = boardPanel.getToolkit();
                                            Image imageComputer = tk1.getImage(iconUrl1);
                                            boardPanel.tileList.get(ii).add(new JLabel(new ImageIcon(imageComputer)));

                                        
                                    }
                                }

                                currentAlliance = Move.changeAlliance(currentAlliance);

                                //**thêm trạng thái bàn cờ vào lịch sử 
                                history.add(currentHistory + 1, new ChessPosition(nextComputerPosition));
                                currentHistory = currentHistory + 1;
                                validate();
                                boardPanel.setSize(600, 600);
                                timeStart = System.currentTimeMillis();
                            }
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
    }
}
