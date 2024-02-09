package battleships;


public class Battleship extends Ships {

    /* Constructs a Battleship with a default setting, indicating it is placed outside the board.
     * This constructor sets the ship to be rotated by default, with a length of 4, and initial coordinates outside the playing board.
     */
    public Battleship() {
        super(true, 4, -1, -1); // True for rotated, length of 4, and initial position outside the board
    }
    
    /* Constructs a Battleship with specified rotation and position.
     * rotated Indicates if the ship is rotated.
     * latitude The latitude (row) position of the ship on the board.
     * longitude The longitude (column) position of the ship on the board.
     */
    public Battleship(boolean rotated, int latitude, int longitude) {
        super(rotated, 4, latitude, longitude); // Specifies rotation, length of 4, and given position
    }
}