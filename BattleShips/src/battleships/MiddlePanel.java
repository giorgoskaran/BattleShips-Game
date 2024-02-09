package battleships;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// MiddlePanel class handles the game setup interface for battleship placement before the game starts.
public class MiddlePanel extends JFrame implements MouseMotionListener, MouseListener {

    // Player instances for the user and computer
    private User player;
    private User pc;

    // UI components for ship selection and action buttons
    private ShipsSelector[] ShipsSelectorPanels;
    private Ships ShipsOn;
    private ShipsSelector ShipsPanelOn;
    private JButton play;
    private JButton rotate;

    // Constructor initializes the UI and sets up event listeners
    public MiddlePanel(final User player) {
        this.player = player;

        // Window setup
        setTitle("BattleShip-Ships' Placement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // Instruction label
        add(new JLabel("Please select ships from the left and place them on your board. Press start game when you are ready!"), BorderLayout.NORTH);

        // Panel for available ships
        JPanel availableShips = new JPanel(new GridLayout(5, 1, 10, 10));
        availableShips.setPreferredSize(new Dimension(250, 300));

        // Initialize ship selector panels
        ShipsSelectorPanels = new ShipsSelector[5];
        ShipsSelectorPanels[0] = new ShipsSelector(new AircraftCarrier());
        ShipsSelectorPanels[1] = new ShipsSelector(new Battleship());
        ShipsSelectorPanels[2] = new ShipsSelector(new Submarine());
        ShipsSelectorPanels[3] = new ShipsSelector(new Destroyer());
        ShipsSelectorPanels[4] = new ShipsSelector(new PatrolBoat());

        // Add ship selectors to the panel and set up mouse listeners
        for (int k = 0; k < 5; k++) {
            availableShips.add(ShipsSelectorPanels[k]);
            ShipsSelectorPanels[k].addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent arg0) {
                    // Ship selection logic
                    if (ShipsOn != null) return;

                    ShipsPanelOn = (ShipsSelector) arg0.getSource();
                    if (ShipsPanelOn.getBoat() == null) return;

                    ShipsOn = ShipsPanelOn.getBoat();
                    ShipsPanelOn.setShipsOn(true);
                    player.getMyboard().PutShips(ShipsOn);
                }
                public void mousePressed(MouseEvent arg0) {}
                public void mouseReleased(MouseEvent arg0) {}
                public void mouseEntered(MouseEvent arg0) {}
                public void mouseExited(MouseEvent arg0) {}
            });
        }

        // Add ship selection panel to the frame
        JPanel middlePane = new JPanel();
        middlePane.add(availableShips);
        add(middlePane, BorderLayout.CENTER);

        // Add the player's board to the frame
        add(player.getMyboard(), BorderLayout.EAST);

        // Buttons panel for actions
        JPanel buttonsPane = new JPanel(new BorderLayout());

        // Rotate button setup
        rotate = new JButton("Rotate ship");
        rotate.addActionListener(e -> {
            // Rotate the selected ship
            if (ShipsOn != null) {
                player.getMyboard().ShipsRotation(ShipsOn);
            }
        });
        buttonsPane.add(rotate, BorderLayout.WEST);

        // Start game button setup
        play = new JButton("Start game");
        play.addActionListener(e -> FinalBoardOpen()); // Proceed to the final game board
        play.setEnabled(false); // Initially disabled until all ships are placed
        buttonsPane.add(play, BorderLayout.EAST);

        // Add buttons panel to the frame
        add(buttonsPane, BorderLayout.SOUTH);

        // Add mouse listeners to the player's board for ship placement and movement
        player.getMyboard().addMouseListener(this);
        player.getMyboard().addMouseMotionListener(this);

        setVisible(true);
    }

    // Unused MouseMotionListener method
    public void mouseDragged(MouseEvent arg0) {}

    // Handles the movement of ships on the board
    public void mouseMoved(MouseEvent arg0) {
        int lati = toLati(arg0.getY());
        int longit = toLongit(arg0.getX());

        if (ShipsOn != null) {
            player.getMyboard().ShipsChangePosition(ShipsOn, lati, longit);
        }
    }

    // Handles ship placement and collision detection
    public void mouseClicked(MouseEvent arg0) {
        if (ShipsOn != null) {
            // Check for ship collisions and set game state accordingly
            if (!player.getMyboard().ShipsCollidesOnBoard(ShipsOn)) {
                ShipsPanelOn.setBoat(null);
                if (player.getMyboard().getBoats().size() == 5) play.setEnabled(true);
                player.getMyboard().ShipsAreClicked(ShipsOn);
                ShipsOn = null;
            }
        } else {
            int lati = toLati(arg0.getY());
            int longit = toLongit(arg0.getX());

            Ships boat = player.getMyboard().ShipsSamePositions(lati, longit);
            if (boat != null) {
                player.getMyboard().ShipsAreClicked(boat);
                ShipsOn = boat;
                play.setEnabled(false);
            }
        }
    }

    // Unused MouseListener methods
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}

    // Helper methods to convert mouse coordinates to board positions
    private int toLati(int lati) {
        return lati * 10 / player.getMyboard().getWidth();
    }

    private int toLongit(int longit) {
        return longit * 10 / player.getMyboard().getHeight();
    }

    // Finalizes game setup and transitions to the game phase
    private void FinalBoardOpen() {
        // Disable further ship placement
        player.getMyboard().removeMouseListener(this);
        player.getMyboard().removeMouseMotionListener(this);

        // Setup computer player and finalize the board setup
        pc = new User("computer", new BoardsPanel());
        pc.getMyboard().PcShips();
        pc.getMyboard().setShipsVisible(false);

        FinalBoard fr = new FinalBoard(player, pc);
        fr.setVisible(true);
        setVisible(false);
    }
}