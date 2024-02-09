package battleships;


public class Submarine extends Ships {

	
    public Submarine() {
        super(true, 3, -1, -1); // True for rotated, length of 3, and initial position outside the board
    }
    
    /* Constructs a Submarine with specified rotation and position.
     * rotated Indicates if the ship is rotated.
     * latitude The latitude (row) position of the ship on the board.
     * longitude The longitude (column) position of the ship on the board.
     */
    public Submarine(boolean rotated, int latitude, int longitude) {
        super(rotated, 3, latitude, longitude); // Specifies rotation, length of 3, and given position
    }
}