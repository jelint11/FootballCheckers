/**
 * Created with IntelliJ IDEA.
 * User: jelint11
 * Date: 27.4.13
 * Time: 0:10
 *
 */

import Parameters.Parameters.*;

import java.io.Serializable;
import java.util.ArrayList;

import static Parameters.Parameters.*;

public class Board implements Serializable {

    private Square[][] square;

    /**
     * Constructor
     */
    public Board() {
        square = new Square[ROWS][COLUMNS];
        addSquares();
        addPieces();
    }

    /**
     *Make board with no pieces
     */
    public void addSquares() {
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLUMNS; j++) {
                if (i % 2 == j % 2) {

                    square[i][j] = new Square (SQUARE_COLOR.WHITE);
                } else {

                    square[i][j] = new Square (SQUARE_COLOR.BLACK);
                }
            }
        }
    }

    /**
     * Accesses to square of board
     * @param row
     * @param col
     * @return square of board
     */
    public Square getSquare(int row, int col) {
        return square[row][col];
    }

    /**
     * add pieces to board at initial positions
     */
    public void addPieces() {

        for (int i=0; i< DEPTH; i++) {
            for (int j=0; j<COLUMNS; j++) {
                if (square[i][j].getColor() == SQUARE_COLOR.BLACK) {
                    square[i][j].setContent(new Piece(PIECE_COLOR.BLACK));
                }
            }
        }


        for (int i=ROWS - DEPTH; i<ROWS; i++) {
            for (int j=0; j<COLUMNS; j++) {
                if (square[i][j].getColor() == SQUARE_COLOR.BLACK) {
                    square[i][j].setContent(new Piece(PIECE_COLOR.WHITE));
                }
            }
        }
    }

    /**
     * add piece to board
      * @param p
     * @param row
     * @param col
     */
    public void placePiece(Piece p, int row, int col) {
        square[row][col].setContent(p);
    }

    /**
     * remove piece from board
     * @param row
     * @param col
     */
    public void removePiece(int row, int col) {
        square[row][col].removeContent();
    }

    /**
     * remove jumped piece from board
     * @param row
     * @param col
     * @param nrow
     * @param ncol
     */
    public void removeJumpedPiece(int row, int col, int nrow, int ncol) {

        square[(row+nrow)/2][(col+ncol)/2].removeContent();
    }

    /**
     * make list of moves which are possible from source position
     * @param srow
     * @param scol
     * @param color
     * @return list of moves
     */
    public ArrayList<Move> getNextMove(int srow, int scol,PIECE_COLOR color){

        ArrayList<Move> nextMoves = new ArrayList<Move>();
        Piece currentPiece = square[srow][scol].getContent();
        if (currentPiece.getColor() == color) {
             VERTICAL_DIRECTION vDir = currentPiece.getVerticalDirection();
             if ((srow > 0)&& !square[srow][scol].getContent().isEmpty() &&((vDir == VERTICAL_DIRECTION.UP) ||(vDir == VERTICAL_DIRECTION.BOTH ))) {

                if ((scol > 0) && !square[srow-1][scol-1].isOccupied()){
                    nextMoves.add(new Move(srow, scol, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.LEFT));

                }

                 if ((scol < COLUMNS-1) && !square[srow-1][scol+1].isOccupied() ) {
                    nextMoves.add(new Move(srow, scol, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.RIGHT));

                 }
             }

             if ((srow < ROWS - 1)&& !square[srow][scol].getContent().isEmpty() && ((vDir == VERTICAL_DIRECTION.DOWN) || (vDir == VERTICAL_DIRECTION.BOTH ))) {

                if ((scol > 0) && !square[srow+1][scol-1].isOccupied() ){
                    nextMoves.add(new Move(srow, scol, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.LEFT));

                }

                if ((scol < COLUMNS-1) && !square[srow+1][scol+1].isOccupied()){
                    nextMoves.add(new Move(srow, scol, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.RIGHT));

                }
             }
        }
        return nextMoves;
    }

    /**
     * make list of jumps which are possible from source position
     * @param srow
     * @param scol
     * @param color
     * @return list of jumps
     */
    public ArrayList<Move> getNextJump(int srow, int scol,PIECE_COLOR color){

        ArrayList<Move> nextJumps = new ArrayList<Move>();
        Piece currentPiece = square[srow][scol].getContent();
        if (currentPiece.getColor() == color) {
            VERTICAL_DIRECTION vDir = currentPiece.getVerticalDirection();
            if ((srow > 1)&& !square[srow][scol].getContent().isEmpty() &&((vDir == VERTICAL_DIRECTION.UP) ||(vDir == VERTICAL_DIRECTION.BOTH ))) {

                if ((scol > 1) && !square[srow-2][scol-2].isOccupied()&& square[srow-1][scol-1].isOccupied()&&(currentPiece.getColor()!= square[srow-1][scol-1].getContent().getColor())){
                    nextJumps.add(new Move(srow, scol, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.LEFT));

                }

                if ((scol < COLUMNS-2) && !square[srow-2][scol+2].isOccupied() && square[srow-1][scol+1].isOccupied() && (currentPiece.getColor()!= square[srow-1][scol+1].getContent().getColor())) {
                    nextJumps.add(new Move(srow, scol, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.RIGHT));

                }
            }

            if ((srow < ROWS - 2) && !square[srow][scol].getContent().isEmpty() && ((vDir == VERTICAL_DIRECTION.DOWN) || (vDir == VERTICAL_DIRECTION.BOTH ))) {

                if ((scol > 1) && !square[srow+2][scol-2].isOccupied() && square[srow+1][scol-1].isOccupied() && (currentPiece.getColor()!= square[srow+1][scol-1].getContent().getColor())){
                    nextJumps.add(new Move(srow, scol, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.LEFT));

                }

                if ((scol < COLUMNS-2) && !square[srow+2][scol+2].isOccupied() && square[srow+1][scol+1].isOccupied() && (currentPiece.getColor()!= square[srow+1][scol+1].getContent().getColor())){
                    nextJumps.add(new Move(srow, scol, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.RIGHT));

                }
            }
        }
        return nextJumps;
    }

    /**
     * make list of all moves which are possible
     * @param color
     * @return list of moves
     */
    public ArrayList<Move> getAllPossibleJumps(PIECE_COLOR color) {

        ArrayList<Move> possibleJumps = new ArrayList<Move>();


        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLUMNS; j++) {

                Piece currentPiece = square[i][j].getContent();
                if (currentPiece.getColor() == color) {

                    VERTICAL_DIRECTION vDir = currentPiece.getVerticalDirection();

                    if ((i > 1) &&((vDir == VERTICAL_DIRECTION.UP) ||(vDir == VERTICAL_DIRECTION.BOTH ))) {

                        if ((j > 1) && !square[i-2][j-2].isOccupied()&& square[i-1][j-1].isOccupied()&&(currentPiece.getColor()!= square[i-1][j-1].getContent().getColor())){
                            possibleJumps.add(new Move(i, j, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.LEFT));

                        }

                        if ((j < COLUMNS-2) && !square[i-2][j+2].isOccupied() && square[i-1][j+1].isOccupied() && (currentPiece.getColor()!= square[i-1][j+1].getContent().getColor())) {
                            possibleJumps.add(new Move(i, j, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.RIGHT));

                        }
                    }

                    if ((i < ROWS - 2) && ((vDir == VERTICAL_DIRECTION.DOWN) || (vDir == VERTICAL_DIRECTION.BOTH ))) {

                        if ((j > 1) && !square[i+2][j-2].isOccupied() && square[i+1][j-1].isOccupied() && (currentPiece.getColor()!= square[i+1][j-1].getContent().getColor())){
                            possibleJumps.add(new Move(i, j, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.LEFT));

                        }

                        if ((j < COLUMNS-2) && !square[i+2][j+2].isOccupied() && square[i+1][j+1].isOccupied() && (currentPiece.getColor()!= square[i+1][j+1].getContent().getColor())){
                            possibleJumps.add(new Move(i, j, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.RIGHT));

                        }
                    }
                }

            }

        }
        return possibleJumps;
    }


    /**
     * make list of all jumps which are possible
     * @param color
     * @return list of jumps
     */
    public ArrayList<Move> getAllPossibleMoves(PIECE_COLOR color) {

        ArrayList<Move> possibleMoves = new ArrayList<Move>();

        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLUMNS; j++) {

                Piece currentPiece = square[i][j].getContent();
                if (currentPiece.getColor() == color) {

                    VERTICAL_DIRECTION vDir = currentPiece.getVerticalDirection();

                    if ((i > 0) &&((vDir == VERTICAL_DIRECTION.UP) ||(vDir == VERTICAL_DIRECTION.BOTH ))) {

                        if ((j > 0) && !square[i-1][j-1].isOccupied()){
                            possibleMoves.add(new Move(i, j, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.LEFT));

                        }

                        if ((j < COLUMNS-1) && !square[i-1][j+1].isOccupied()) {
                            possibleMoves.add(new Move(i, j, VERTICAL_DIRECTION.UP, HORIZONTAL_DIRECTION.RIGHT));
                        }
                    }

                    if ((i < ROWS - 1) && ((vDir == VERTICAL_DIRECTION.DOWN) || (vDir == VERTICAL_DIRECTION.BOTH))) {

                        if ((j > 0) && !square[i+1][j-1].isOccupied()){
                            possibleMoves.add(new Move(i, j, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.LEFT));
                        }

                        if ((j < COLUMNS-1) && !square[i+1][j+1].isOccupied()){
                            possibleMoves.add(new Move(i, j, VERTICAL_DIRECTION.DOWN, HORIZONTAL_DIRECTION.RIGHT));
                        }
                    }

                }
            }
        }
        return possibleMoves;
    }

    /**
     * do jump on the board
     * @param move
     */
    public void makeAJump(Move move) {

        int fromRow = move.getSourceRow();
        int fromCol = move.getSourceColumn();
        int toRow =  move.getTargetJumpRow();
        int toCol = move.getTargetJumpColumn();

        Piece pieceToMove = square[fromRow][fromCol].getContent();
        if (!pieceToMove.isPieceKing() && ((pieceToMove.getColor() == PIECE_COLOR.WHITE) && (toRow == 0)) ||((pieceToMove.getColor() == PIECE_COLOR.BLACK) && (toRow == ROWS-1))){
            pieceToMove.makeKing();
        }
        placePiece(pieceToMove, toRow, toCol);
        removePiece(fromRow, fromCol);
        removeJumpedPiece(fromRow,fromCol,toRow,toCol);

    }

    /**
     * do move on the board
     * @param move
     */
    public void makeAMove(Move move) {

        int fromRow = move.getSourceRow();
        int fromCol = move.getSourceColumn();
        int toRow =  move.getTargetRow();
        int toCol = move.getTargetColumn();

        Piece pieceToMove = square[fromRow][fromCol].getContent();
        if (!pieceToMove.isPieceKing() && ((pieceToMove.getColor() == PIECE_COLOR.WHITE) && (toRow == 0)) ||((pieceToMove.getColor() == PIECE_COLOR.BLACK) && (toRow == ROWS-1))){
            pieceToMove.makeKing();
        }
        placePiece(pieceToMove, toRow, toCol);
        removePiece(fromRow, fromCol);

    }



    @Override
    public String toString() {

        String board = "";


        for (int i = 0; i <ROWS; i++) {
            for (int j=0; j<COLUMNS; j++) {
               board += " " + square[i][j].toString();
               if (j % 7 == 0 && j != 0){
                   board+="\n";
               }
            }

        }
        return board;
    }





}
