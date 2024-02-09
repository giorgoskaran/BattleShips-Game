package battleships;
import javax.swing.JOptionPane;

/* Represents a player in the Battleships game with a name and a game board.
 */
public class User {
    private String name; // The name of the user
    private BoardsPanel myboard; // The user's game board

    /* Constructs a User with a name and a game board.
     * name The name of the user.
     * myboard The game board associated with the user.
     */
    public User(String name, BoardsPanel myboard) {
        this.name = name;
        this.myboard = myboard;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardsPanel getMyboard() {
        return myboard;
    }

    public void setMyboard(BoardsPanel myboard) {
        this.myboard = myboard;
    }

    /* Processes a shot made by this user against another user (PC).
     * It attempts to hit a ship on the board and displays a message if a ship is sunk.
     * pc The opponent user (typically the PC).
     * shot The shot being fired.
     */
    public void ShotBeDone(User pc, Shot shot) {
        Ships destroyed = myboard.PullShot(shot);
        if (destroyed != null && destroyed.AllShipsPanelsClicked()) {
            JOptionPane.showMessageDialog(null, pc.getName() + " sunk " + this.getName() + "'s ship!");
        }
    }

    /* Checks if all ships on this user's board have been sunk.
     * return true if all ships are sunk, false otherwise.
     */
    public boolean AllShipsDown() {
        for (Ships boat : myboard.getBoats()) {
            if (!boat.AllShipsPanelsClicked()) {
                return false;
            }
        }
        return true;
    }
}