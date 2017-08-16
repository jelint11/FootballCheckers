/**
 * Created with IntelliJ IDEA.
 * User: jelint11
 * Date: 27.4.13
 * Time: 10:30
 *
 */

import Parameters.Parameters.PIECE_COLOR;
import Parameters.Parameters.VERTICAL_DIRECTION;

import java.io.Serializable;

import static Parameters.Parameters.BLACK_SIGN;
import static Parameters.Parameters.WHITE_SIGN;

public class Piece implements Serializable {

    private boolean isKing;
    private PIECE_COLOR color;

    /**
     * Constructor
     * @param color
     */
    public Piece(PIECE_COLOR color) {
        this.color = color;
        isKing = false;
    }

    /**
     * Constructor make empty piece
     */
    public Piece() {
        this(PIECE_COLOR.NONE);
    }

    /**
     * Copy constructor
     * @param p
     */
    public Piece(Piece p) {
        isKing = p.isKing;
        color = p.color;
    }

    /**
     * @return true if piece is king, false otherwise
     */
    public boolean isPieceKing() {
        return isKing;
    }

    /**
     * set piece is king
     */
    public void makeKing() {
        isKing = true;
    }

    /**
     *  getter for piece color
     * @return color of piece
     */
    public PIECE_COLOR getColor() {
        return color;
    }

    /**
     *  Check whether this is an empty piece.
     * @return  true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (color == PIECE_COLOR.NONE);
    }

    /**
     * get vertical direction of piece movement
     * @return  vertical direction, where can piece move
     */
    public VERTICAL_DIRECTION getVerticalDirection() {

        VERTICAL_DIRECTION direction;

        if (isKing) {
            direction = VERTICAL_DIRECTION.BOTH;
        } else if (color == PIECE_COLOR.WHITE) {
            direction = VERTICAL_DIRECTION.UP;
        } else {
            direction = VERTICAL_DIRECTION.DOWN;
        }
        return direction;
    }

    @Override
    public String toString() {
        String piece = "";
        if (color == PIECE_COLOR.BLACK) {
            piece += BLACK_SIGN;
        } else if  (color == PIECE_COLOR.WHITE) {
            piece += WHITE_SIGN;
        } else {
            piece = " ";
        }
        return piece;
    }
}
