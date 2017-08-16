/**
 * Created with IntelliJ IDEA.
 * User: jelint11
 * Date: 27.4.13
 * Time: 19:08
 *
 */


import Parameters.Parameters.PIECE_COLOR;

import javax.swing.*;
import java.io.Serializable;


public class Player implements Serializable {

    private PIECE_COLOR color;
    private String name;
    private int number;

    /**
     * Constructor
     * @param color
     */
    public Player(PIECE_COLOR color) {
        this.color = color;

    }

    /**
     * get color of player
     * @return color
     */
    public PIECE_COLOR getColor() {
        return color;
    }

    /**
     * Set name to player
     * @param player
     * @param icon
     * @return name
     */
    public String setName(int player,Icon icon){
        number = player;
        name = (String)JOptionPane.showInputDialog(null,"Player "+number+", insert your name","Creating player",JOptionPane.INFORMATION_MESSAGE,icon,null,"");
        return name;
    }

    /**
     * get number of player
     * @return number
     */
    public int getNumber(){
        return number;
    }

    /**
     * get name of player
     * @return name
     */
    public String getName(){
        return name;
    }


}
