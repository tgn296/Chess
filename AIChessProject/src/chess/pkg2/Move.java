/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.pkg2;

/**
 *
 * @author Kha
 */
public class Move {

    public static int changeAlliance(int currentAlliance) {
        if (currentAlliance == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
