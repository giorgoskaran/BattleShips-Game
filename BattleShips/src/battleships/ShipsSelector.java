package battleships;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/* This class represents a visual selector for ships in the Battleships game.
 * It extends JPanel to display ships as a series of colored panels.
 */
public class ShipsSelector extends JPanel {

    private Ships boat; // The ship to be displayed by this selector.

    /* Constructor for ShipsSelector.
     * Initializes the panel with a specified ship, setting the background color and borders for each segment of the ship.
     * boat The ship to be displayed by this selector.
     */
    public ShipsSelector(Ships boat) {
        this.boat = boat;
        this.setPreferredSize(new Dimension(250, 50));
        this.setLayout(new GridLayout(1, 5));
        
        // Create panels to represent the length of the ship.
        for (int i = 0; i < boat.getLength(); i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.gray);
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(panel);
        }
        
        // Fill the remaining slots with empty panels if the ship's length is less than 5.
        for (int i = 0; i < 5 - boat.getLength(); i++) {
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(panel);
        }
    }

    // Getters and setters
    public Ships getBoat() {
        return boat;
    }

    public void setBoat(Ships boat) {
        this.boat = boat;
        ColorShips(); // Update the color of the ships based on the new boat object.
    }
    
    public boolean isShipsOn() {
        return this.boat.isOn();
    }

    public void setShipsOn(boolean on) {
        this.boat.setOn(on);
        ColorShips(); // Update the color of the ships to reflect their selection state.
    }
    
    /*Updates the color of the panels based on the ship's selection state.
     * If a ship is selected, its panels are colored yellow; otherwise, they are gray.
     */
    private void ColorShips() {
        // If no ship is set, clear all panel colors.
        if (boat == null) {
            for (int i = 0; i < 5; i++) {
                JPanel panel = (JPanel) this.getComponent(i);
                panel.setBackground(null);
            }
        } else if (isShipsOn()) {
            // Color the ship's panels yellow if selected.
            for (int i = 0; i < boat.getLength(); i++) {
                JPanel panel = (JPanel) this.getComponent(i);
                panel.setBackground(Color.yellow);
            }
        } else {
            // Keep the ship's panels gray if not selected.
            for (int i = 0; i < boat.getLength(); i++) {
                JPanel panel = (JPanel) this.getComponent(i);
                panel.setBackground(Color.gray);
            }
        }
    }
}