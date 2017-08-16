/**
 * Created with IntelliJ IDEA.
 * User: jelint11
 * Date: 22.4.13
 * Time: 0:05
 *
 */

import Parameters.Parameters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import static Parameters.Parameters.*;


public class UserInterface extends JPanel implements MouseListener, Serializable {

    public int mouseX, mouseY, newmouseX, newmouseY, x, y, nextPlayer;
    public boolean selected, wasJump, jump, move, isNextJump;
    private Board board;
    public Player player1, player2;
    public ArrayList<Move> markMoves, markJumps;

    /**
     * Constructor
     */
    public  UserInterface(){
        this.addMouseListener(this);
        selected = wasJump =  isNextJump = false;
        player1 = new Player(PIECE_COLOR.WHITE);
        player1.setName(1,new ImageIcon("profilSparta.jpg"));
        player2 = new Player(PIECE_COLOR.BLACK);
        player2.setName(2,new ImageIcon("profilSlavia.jpg"));
        nextPlayer=1;
        jump = move = true;
    }

    /**
     * Set board which is used
     * @param theBoard
     */
    public void setBoard(Board theBoard) {
        board = theBoard;
    }

    /**
     * Set values of userinterface after load game
     * @param ui
     */
    public void setUserInterface(UserInterface ui) {
        this.board=ui.board;
        this.player1=ui.player1;
        this.player2=ui.player2;
        this.selected=ui.selected;
        this.nextPlayer=ui.nextPlayer;
        this.wasJump=ui.wasJump;
        this.isNextJump=ui.isNextJump;

    }

    /**
     * Set userinterface after newgame
     */
    public void setUi (){
        selected = wasJump =  isNextJump = false;
        nextPlayer=1;
        jump = move = true;
    }


    @Override
    public void paint (Graphics g){

        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){

                if (board.getSquare(i, j).getColor() == SQUARE_COLOR.WHITE){
                    g.setColor(Color.white);
                    g.fillRect(i* squareSize,j* squareSize, squareSize, squareSize);
                }
                else {
                    g.setColor(Color.black);
                    g.fillRect(i* squareSize,j* squareSize, squareSize, squareSize);
                }

                if (board.getSquare(j, i).getContent().getColor() == PIECE_COLOR.BLACK){
                    g.drawImage(stoneImageSlavia,i* squareSize,j* squareSize,this);
                }
                if (board.getSquare(j, i).getContent().getColor() == PIECE_COLOR.WHITE){
                    g.drawImage(stoneImageSparta,i* squareSize,j* squareSize,this);
                }
                if (board.getSquare(j, i).getContent().getColor() == PIECE_COLOR.WHITE && board.getSquare(j, i).getContent().isPieceKing()){
                    g.drawImage(kingImageSparta,i*squareSize,j*squareSize,this);
                }
                if (board.getSquare(j, i).getContent().getColor() == PIECE_COLOR.BLACK && board.getSquare(j, i).getContent().isPieceKing()){
                    g.drawImage(kingImageSlavia,i*squareSize,j*squareSize,this);
                }
            }

        }


        if (!jump ){
            g.drawImage(ball,mouseY*squareSize,mouseX*squareSize,this);
            for (Move markJump : markJumps) {
                x = markJump.getTargetJumpRow();
                y = markJump.getTargetJumpColumn();
                g.drawImage(markedMove,y*squareSize,x*squareSize,this);
            }
        }
        else if (!move && board.getAllPossibleJumps(getPlayer().getColor()).isEmpty()){
            g.drawImage(ball,mouseY*squareSize,mouseX*squareSize,this);
            for (Move markMove : markMoves) {
                x = markMove.getTargetRow();
                y = markMove.getTargetColumn();
                g.drawImage(markedMove, y * squareSize, x * squareSize, this);
            }
        }
    }

    /**
     * get opponent player
     * @return player
     */
    public Player getOpponentPlayer(){
        if(nextPlayer==1 ){
            return player2;
        }
        else return player1;

    }

    /**
     * get current player
     * @return player
     */
    public Player getPlayer(){
        if(nextPlayer==1 ){
            return player1;
        }
        else return player2;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(!selected){
            mouseX = e.getY()/squareSize;
            mouseY = e.getX()/squareSize;
            System.out.println("from "+mouseX + "," + mouseY);
            mouseChoose();
        }

        else {
            newmouseX = e.getY()/squareSize;
            newmouseY = e.getX()/squareSize;
            jump=true;
            move=true;
            repaint();
            System.out.println("to "+newmouseX + "," + newmouseY);
            selected=false;
            if(nextPlayer == 1 && movePiece(player1)){

                testNextJump(player1,player2);
            }
            else if(nextPlayer == 2 && movePiece(player2)){

                testNextJump(player2,player1);
            }

        }

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

    /**
     * make a move if it is possble
     * @param p
     * @return true if move was succesfull else false
     */
    public boolean movePiece(Player p){

        ArrayList<Move> possibleMoves = board.getAllPossibleMoves(p.getColor());
        ArrayList<Move> possibleJumps = board.getAllPossibleJumps(p.getColor());
        if(possibleJumps.size()==0 && possibleMoves.size()==0){
            JOptionPane.showMessageDialog(this, "Player "+getOpponentPlayer().getName()+" win","End of game",JOptionPane.PLAIN_MESSAGE,new ImageIcon("pohar.jpg"));
        }

        VERTICAL_DIRECTION vDir = null;
        HORIZONTAL_DIRECTION hDir = null;
        vDir=getVerticalDirection(vDir);
        hDir=getHorizontalDirection(hDir);

        Move move = new Move(mouseX,mouseY,vDir,hDir);


        if(possibleMoves.contains(move) && !isNextJump && possibleJumps.size()==0){
           wasJump=false;

           board.makeAMove(move);
           repaint();
           return true;
        }

        else if (possibleJumps.contains(move)){
           isNextJump=false;
           board.makeAJump(move);
           wasJump=true;
           repaint();
           return true;
        }

        else{
           repaint();
           System.out.println("invalid move");
           return false;
        }

    }

   /**
     * get vertical direction of move which player wants to do
     * @param vDir
     * @return vertical direction
     */
   public VERTICAL_DIRECTION getVerticalDirection(VERTICAL_DIRECTION vDir) {

        if (mouseX - newmouseX <= -1){
            vDir=VERTICAL_DIRECTION.DOWN;
        }
        if (mouseX - newmouseX >= 1){
            vDir=VERTICAL_DIRECTION.UP;
        }
        return vDir;
   }

    /**
     * get horizontal direction of move which player wants to do
     * @param hDir
     * @return horizontal direction
     */
    public HORIZONTAL_DIRECTION getHorizontalDirection(HORIZONTAL_DIRECTION hDir) {

        if (mouseY - newmouseY >= 1){
            hDir=HORIZONTAL_DIRECTION.LEFT;
        }
        if (mouseY - newmouseY <= -1){
            hDir=HORIZONTAL_DIRECTION.RIGHT;
        }
        return hDir;
    }

    /**
     * if square is choosen, mark next possible  move or jump squares
     */
    public void mouseChoose(){

        selected=true;
        markMoves = board.getNextMove(mouseX, mouseY,getPlayer().getColor());
        if (markMoves.size()==0 ){
            move=true;
        }
        else if (jump) move=false;
        repaint();
        markJumps = board.getNextJump(mouseX,mouseY,getPlayer().getColor());
        if (markJumps.size()==0){
            jump=true;
        }
        else jump=false;
        repaint();
    }

    /**
     * test if is possible multiple jump
     * @param p1
     * @param p2
     */
    public void testNextJump(Player p1,Player p2){
        if (wasJump){

            ArrayList<Move> nextJumps = board.getNextJump(newmouseX,newmouseY,getPlayer().getColor());
            if(nextJumps.size()!=0){
                isNextJump=true;
                nextPlayer=p1.getNumber();
                return;
            }

        }
        nextPlayer=p2.getNumber();
        System.out.println("na tahu je hrac "+p2.getNumber());
    }

    @Override
    public String toString() {
        return "UserInterface{" +
                "board=" + board +
                '}';
    }
}