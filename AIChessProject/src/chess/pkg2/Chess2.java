package chess.pkg2;

import chess.pkg2.ChessPosition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chess2 implements Runnable {
    Table table;
    Thread thread;
    int blackLevel;
    int whiteLevel;

    /**
     * @param args the command line arguments
     */
     Chess2(int blackLevel, int whiteLevel) {
         this.blackLevel = blackLevel;
         this.whiteLevel = whiteLevel;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ChessPosition testChess;
        testChess = ChessPosition.createBasicChessPosition();
        
        table = new Table(0,blackLevel,whiteLevel);
    }

}
