package battleships;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BoardsPanel extends JPanel {

    private ArrayList<Shot> grenades; // To store shots
    private ArrayList<Ships> boats; // To store ships
    private boolean ShipsVisible; // Determines if ships are visible on the board
    private JPanel[][] pane; // Grid of panels for the board

    // Constructor to initialize the board
    public BoardsPanel() {
        grenades = new ArrayList<>();
        boats = new ArrayList<>();
        ShipsVisible = true;
        pane = new JPanel[10][10];
        setLayout(new GridLayout(10, 10));

        // Initialize the board with blue panels and black borders
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                pane[i][j] = panel;
                panel.setBackground(Color.blue);
                this.add(panel);
            }
        }
        this.setPreferredSize(new Dimension(500, 500));
    }

    // Getters and setters
    public boolean isShipsVisible() {
        return ShipsVisible;
    }

    public void setShipsVisible(boolean ShipsVisible) {
        this.ShipsVisible = ShipsVisible;
    }

    public JPanel[][] getPane() {
        return pane;
    }

    public void setPane(JPanel[][] pane) {
        this.pane = pane;
    }

    public ArrayList<Shot> getGrenades() {
        return grenades;
    }

    public void setGrenades(ArrayList<Shot> grenades) {
        this.grenades = grenades;
    }

    public ArrayList<Ships> getBoats() {
        return boats;
    }

    public void setBoats(ArrayList<Ships> boats) {
        this.boats = boats;
    }

    // Returns the number of ships
    public int NumberOfShips() {
        return boats.size();
    }

    // Checks if a ship is in the ships ArrayList
    public Boolean ShipsInShipsArrayList(Ships boat) {
        return boats.contains(boat);
    }

    // Checks if a shot has already been made based on its coordinates
    public Boolean ShotDone(Shot shot) {
        return grenades.stream().anyMatch(s -> s.getLongi() == shot.getLongi() && s.getLat() == shot.getLat());
    }

    // Checks if a ship overlaps with another ship's location
    public Boolean ShipsOnShipsLocation(Ships boat) {
        return boats.stream().anyMatch(b -> b != boat && boat.ShipsCollidesShips(b));
    }

    // Checks if a ship collides with other ships or is out of bounds
    public Boolean ShipsCollidesOnBoard(Ships boat) {
        return ShipsOnShipsLocation(boat) || boat.ShipsOutOfBoard(10, 10);
    }

    // Checks if a shot destroys a ship
    public boolean ReturnShipsDestroyed(Shot shot) {
        return boats.stream().anyMatch(b -> b.ShotOnShipsPanel(shot));
    }

    // Method to color the board based on ships and shots , Default board coloring
    public void ColorOnBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pane[i][j].setBackground(Color.blue);
            }
        }

        // Coloring for ships
        if (ShipsVisible) {
            colorShips();
        }

        // Coloring for shots
        colorShots();
    }

    // Adds a ship to the ArrayList if it's not already present
    public void PutShips(Ships boat) {
        if (!ShipsInShipsArrayList(boat)) {
            boats.add(boat);
        }
        ColorOnBoard();
    }

    // Adds a shot to the ArrayList and checks if it hits a ship
    public Ships PullShot(Shot shot) {
        grenades.add(shot);
        ColorOnBoard();
        return boats.stream().filter(b -> shot.getLat() == b.getLatitude() && shot.getLongi() == b.getLongitude()).findFirst().orElse(null);
    }

    // Checks if there is a ship at the given position
    public Ships ShipsSamePositions(int lat, int longi) {
        return boats.stream().filter(b -> b.getLatitude() == lat && b.getLongitude() == longi).findFirst().orElse(null);
    }

    // Sets a ship as not selected if it is in the ArrayList
    public void ShipsAreClicked(Ships boat) {
        if (ShipsInShipsArrayList(boat)) {
            boat.setOn(false);
            ColorOnBoard();
        }
    }

    // Changes a ship's position if it is in the ArrayList
    public void ShipsChangePosition(Ships boat, int lat, int longi) {
        if (ShipsInShipsArrayList(boat)) {
            boat.setLongitude(longi);
            boat.setLatitude(lat);
            ColorOnBoard();
        }
    }

    // Toggles a ship's rotation status
    public void ShipsRotation(Ships boat) {
        if (ShipsInShipsArrayList(boat)) {
            boat.setRotated(!boat.isRotated());
            ColorOnBoard();
        }
    }

    // Randomly positions a ship without collisions
    public void RandomPositionWithoutCollides(Ships boat) {
        Random rm = new Random();
        while (ShipsCollidesOnBoard(boat)) {
            ShipsChangePosition(boat, rm.nextInt(10), rm.nextInt(10));
        }
    }

 // Initializes PC's ships with random positions
    public void PcShips() {
        Random rm = new Random();

        // Creating each ship with random orientations and positions, then putting them on the board
        AircraftCarrier first = new AircraftCarrier(rm.nextBoolean(), rm.nextInt(10), rm.nextInt(10));
        PutShips(first);
        RandomPositionWithoutCollides(first);
        
        Battleship second = new Battleship(rm.nextBoolean(), rm.nextInt(10), rm.nextInt(10));
        PutShips(second);
        RandomPositionWithoutCollides(second);
        
        Destroyer third = new Destroyer(rm.nextBoolean(), rm.nextInt(10), rm.nextInt(10));
        PutShips(third);
        RandomPositionWithoutCollides(third);
        
        Submarine fourth = new Submarine(rm.nextBoolean(), rm.nextInt(10), rm.nextInt(10));
        PutShips(fourth);
        RandomPositionWithoutCollides(fourth);
        
        PatrolBoat fifth = new PatrolBoat(rm.nextBoolean(), rm.nextInt(10), rm.nextInt(10));
        PutShips(fifth);
        RandomPositionWithoutCollides(fifth);
    }

 // Colors the panels based on ship positions
    private void colorShips() {
        if (!ShipsVisible) return; // If ships should not be visible, skip this method

        for (Ships boat : boats) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (boat.PanelBelongsShips(i, j)) { // Check if the panel belongs to the current ship
                        JPanel panel = pane[i][j];
                        if (boat.isOn()) { // If the ship is 'on' or active
                            if (ShipsCollidesOnBoard(boat)) {
                                panel.setBackground(Color.red); // Collision color
                            } else {
                                panel.setBackground(Color.green); // No collision color
                            }
                        } else {
                            panel.setBackground(Color.gray); // Ship is not active color
                        }
                    }
                }
            }
        }
    }

    // Colors the panels based on shot results
    private void colorShots() {
        for (Shot shot : grenades) {
            int i = shot.getLat();
            int j = shot.getLongi();
            JPanel tile = pane[i][j];
            
            if (ReturnShipsDestroyed(shot)) {
                tile.setBackground(Color.red); // Shot hit a ship
            } else {
                tile.setBackground(Color.white); // Shot missed
            }
        }
    }

    private void PutShipsAndRandomizePosition(Ships ship) {
        PutShips(ship);
        RandomPositionWithoutCollides(ship);
    }
}
