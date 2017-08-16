package Parameters;

import javax.swing.*;
import java.awt.*;

public class Parameters {

    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final int DEPTH = 4;
    public static final int squareSize=64;
    public static final char BLACK_SIGN = '*';
    public static final char WHITE_SIGN = '+';

    public static final Image stoneImageSlavia = new ImageIcon("slavia.png").getImage();
    public static final Image stoneImageSparta = new ImageIcon("sparta.png").getImage();
    public static final Image kingImageSlavia = new ImageIcon("kingslavia.png").getImage();
    public static final Image kingImageSparta = new ImageIcon("kingsparta.png").getImage();
    public static final Image markedMove = new ImageIcon("markedmove.png").getImage();
    public static final Image ball = new ImageIcon("ball.jpg").getImage();

    public static final String CHECKERS_RULES = "A piece which is not a king can move one square diagonally forward." +
            " A king can move one square diagonally forward or backward. A piece (piece or king) can only move to a vacant square." +
            " You capture an opponent's piece (piece or king) by jumping over it diagonally to" +
            " the adjacent vacant square beyond it. A king can jump diagonally, forward or backward. A piece which is not a king, can only jump" +
            " diagonally forward. You can make a multiple jump by jumping to empty square to empty square. You remove the jumped pieces from the board." +
            " You cannot jump your own piece. You cannot jump the same piece twice, in the same move. If you can jump, you must. And a multiple jump must be completed," +
            " you cannot stop part way through a multiple jump. If you have a choice of jumps, you can choose among them, regardless of" +
            " whether some of them are multiple, or not. When a piece reaches the last row it becomes a King." +
            " The players take turns moving. You can make only one move per turn. You must move. If you cannot move, you lose.";

    public static final String CONTROL_RULES = "For move choose piece by left mouse click. " +
            "Green labeled area shows places where you can move.Use left mouse button to target destination of your piece. ";

    public static enum SQUARE_COLOR {BLACK, WHITE}

    public static enum PIECE_COLOR {BLACK, WHITE, NONE}

    public static enum VERTICAL_DIRECTION {DOWN, UP, BOTH}

    public static enum HORIZONTAL_DIRECTION {LEFT, RIGHT}
}
