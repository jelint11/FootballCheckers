/**
 * Created with IntelliJ IDEA.
 * User: jelint11
 * Date: 26.4.13
 * Time: 22:30
 *
 */

import Parameters.Parameters;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Checkers {


    /**
     * prepare text areas for using
     * @param text
     * @return textArea
     */
    public static JTextArea makeText(String text){

        JTextArea textArea = new JTextArea(text);
        textArea.setColumns(40);
        textArea.setLineWrap( true );
        textArea.setWrapStyleWord( true );
        textArea.setEditable(false);
        textArea.setSize(textArea.getPreferredSize().width, 1);

        return textArea;
    }


    public static void main(String args[]) {



        final JFrame frame = new JFrame();
        frame.setTitle("Football Checkers");
        frame.setSize(750, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Board board = new Board();
        final UserInterface ui = new UserInterface();
        ui.setBoard(board);
        frame.add(ui);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);


        JMenu game = new JMenu("Game");
        menuBar.add(game);


        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board board = new Board();
                ui.setBoard(board);
                ui.setUi();
                ui.repaint();
            }
        });
        game.add(newGame);


        JMenuItem load = new JMenuItem("Load Game");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadOldGame();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Your saved game wasn't found", "Load game", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, "Your saved game wasn't found", "Load game", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void loadOldGame() throws FileNotFoundException, IOException, ClassNotFoundException{
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("save.txt"));
                UserInterface readUserInterface = (UserInterface)inputStream.readObject();
                ui.setUserInterface(readUserInterface);
                ui.repaint();
                inputStream.close();

            }
        });
        game.add(load);


        JMenuItem save = new JMenuItem("Save Game");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveOldGame();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Sorry, game wasn't save ","Save game", JOptionPane.ERROR_MESSAGE);

                } catch (ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, "Sorry, game wasn't save ","Save game", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void saveOldGame() throws FileNotFoundException, IOException, ClassNotFoundException{
                ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("save.txt"));
                save.writeObject(ui);
                save.reset();
                save.close();
            }
        });
        game.add(save);


        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        game.add(exit);


        JMenu help = new JMenu("Help");
        menuBar.add(help);


        JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, makeText(Parameters.CHECKERS_RULES), "Rules", JOptionPane.PLAIN_MESSAGE);
            }
        });
        help.add(rules);


        JMenuItem controls = new JMenuItem("Controls");
        controls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, makeText(Parameters.CONTROL_RULES), "Controls", JOptionPane.PLAIN_MESSAGE);
            }
        });
        help.add(controls);

        frame.setVisible(true);


    }



}

