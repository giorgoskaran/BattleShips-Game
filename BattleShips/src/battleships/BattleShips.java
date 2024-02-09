package battleships;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

// BattleShips class is the entry point for the Battleship game application.
public class BattleShips {

    public static void main(String[] args) {
        // Initial demo frame setup (not visible in final application flow)
        JFrame firstFrame = new JFrame("Demo Frame");
        firstFrame.setSize(new Dimension(200, 250));
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setLayout(new GridLayout(5, 1, 10, 10));

        // Creating ship selectors for all ship types
        ShipsSelector carrier = new ShipsSelector(new AircraftCarrier());
        ShipsSelector battle = new ShipsSelector(new Battleship());
        ShipsSelector destroy = new ShipsSelector(new Destroyer());
        ShipsSelector marine = new ShipsSelector(new Submarine());
        ShipsSelector patrol = new ShipsSelector(new PatrolBoat());

        // Adding ship selectors to the frame
        firstFrame.add(carrier);
        firstFrame.add(battle);
        firstFrame.add(destroy);
        firstFrame.add(marine);
        firstFrame.add(patrol);

        // Making the first frame invisible as it's only for demo purposes
        firstFrame.setVisible(false);

        // Alias frame for additional demonstration (also not visible in final application flow)
        JFrame alias = new JFrame("Demo Frame 2");
        alias.setBounds(0, 0, 500, 500);
        alias.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoardsPanel myBoard = new BoardsPanel();
        alias.add(myBoard);
        alias.setVisible(false);

        // Prompting user for their name using a JOptionPane, which will be used for the player's details
        String name = (String) JOptionPane.showInputDialog(
                null,
                "Please insert your name and press OK",
                "BattleShip-Player's Details",
                JOptionPane.PLAIN_MESSAGE
        );

        // Exit if the user cancels the prompt
        if (name == null) {
            return;
        }

        // Creating a new User instance with the entered name and initializing the game's middle panel
        User player = new User(name, new BoardsPanel());
        MiddlePanel fr = new MiddlePanel(player);
        fr.setVisible(true);
    }
}