package battleships;


public class Destroyer extends Ships {

    /* Constructs a Destroyer with a default setting, indicating it is placed outside the board.
     * This constructor sets the ship to be rotated by default, with a length of 3, and initial coordinates outside the playing board.
     */
    public Destroyer() {
        super(true, 3, -1, -1); // True for rotated, length of 3, and initial position outside the board
    }
    
    /* Constructs a Destroyer with specified rotation and position.
     * rotated Indicates if the ship is rotated.
     * latitude The latitude (row) position of the ship on the board.
     * longitude The longitude (column) position of the ship on the board.
     */
    public Destroyer(boolean rotated, int latitude, int longitude) {
        super(rotated, 3, latitude, longitude); // Specifies rotation, length of 3, and given position
    }
}