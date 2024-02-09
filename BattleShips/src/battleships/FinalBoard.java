package battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/* Represents the final game board UI for the Battleships game.
 */
public class FinalBoard extends JFrame {
    private User me;
    private User pc;
    private JLabel myname;

    /* Constructor sets up the game window and initializes the game boards for both players.
     *
     * me The human player.
     * pc The computer player.
     */
    public FinalBoard(User me, User pc) {
        this.me = me;
        this.pc = pc;

        // Window setup
        this.setTitle("BattleShip - Play Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1024, 400));
        this.setLayout(new BorderLayout());

        // Setting up the players' names and boards
        myname = new JLabel(me.getName() + "'s board" + "                           " + pc.getName() + "'s board", SwingConstants.CENTER);
        this.add(myname, BorderLayout.NORTH);

        JPanel middlePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        middlePanel.add(me.getMyboard());
        middlePanel.add(pc.getMyboard());
        this.add(middlePanel, BorderLayout.CENTER);

        // Adding a mouse listener to the PC's board for shot input
        pc.getMyboard().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = toLati(e.getX());
                int column = toLongit(e.getY());
                Shot shot = new Shot(column, row);
                ShotClick(shot);
            }
        });
    }

    /* Determines the winner and displays a message dialog.
     *
     * victor The user who won the game.
     */
    public void WinnerPane(User victor) {
        JOptionPane.showMessageDialog(this, victor.getName() + " is the winner!");
    }

    /* Handles the logic for when a shot is fired by either player.
     *
     * shot The shot fired by the user.
     */
    public void ShotClick(Shot shot) {
        // Process the shot for the PC
        pc.ShotBeDone(me, shot);
        if (pc.AllShipsDown()) {
            WinnerPane(me);
            return;
        }

        // PC's turn with a random shot
        Random rm = new Random();
        Shot pcShot = new Shot(rm.nextInt(10), rm.nextInt(10));
        while (me.getMyboard().ShotDone(pcShot)) {
            pcShot = new Shot(rm.nextInt(10), rm.nextInt(10));
        }

        // Process the PC's shot
        me.ShotBeDone(pc, pcShot);
        if (me.AllShipsDown()) {
            WinnerPane(pc);
        }
    }

    /* Converts the mouse x-coordinate to a row value.
     *
     * x The x-coordinate.
     * return The corresponding row.
     */
    private int toLati(int x) {
        return x / (pc.getMyboard().getWidth() / 10);
    }

    /* Converts the mouse y-coordinate to a column value.
     *
     * y The y-coordinate.
     * return The corresponding column.
     */
    private int toLongit(int y) {
        return y / (pc.getMyboard().getHeight() / 10);
    }
}