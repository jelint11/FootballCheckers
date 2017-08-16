import Parameters.Parameters.SQUARE_COLOR;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jelint11
 * Date: 27.4.13
 * Time: 0:57
 *
 */


public class Square implements Serializable {

    private SQUARE_COLOR color;
    private Piece content;

    /**
     * Constructor
     * @param color
     */
    public Square(SQUARE_COLOR color) {
        this.color = color;
        content = new Piece();

    }

    /**
     * get color of square
     * @return color
     */
    public SQUARE_COLOR getColor() {
        return color;
    }

    /**
     * get content of square
     * @return content
     */
    public Piece getContent() {
        return content;
    }

    /**
     * set content of square
     * @param p
     */
    public void setContent(Piece p) {
        content = new Piece(p);
    }

    /**
     * remove content from square
     */
    public void removeContent() {

        content = new Piece();
    }

    /**
     * test if square has piece
     * @return false if is empty else true
     */
    public boolean isOccupied() {
        return (!content.isEmpty());
    }


    @Override
    public String toString() {
        String square;
        if (color == SQUARE_COLOR.WHITE) {
            square = "[" + content.toString() + "]";
        } else {
            square = " " + content.toString() + " ";
        }
        return square;
    }
}
