package battleships;


public class AircraftCarrier extends Ships {

    /* Constructs an Aircraft Carrier with a default setting, indicating it is placed outside the board.
     * This constructor sets the ship to be rotated by default, with a length of 5, and initial coordinates outside the playing board.
     */
    public AircraftCarrier() {
        super(true, 5, -1, -1); // True for rotated, length of 5, and initial position outside the board
    }
    
    /* Constructs an Aircraft Carrier with specified rotation and position.
     * rotated  Indicates if the ship is rotated.
     * latitude  The latitude (row) position of the ship on the board.
     * longitude  The longitude (column) position of the ship on the board.
     */
    public AircraftCarrier(boolean rotated, int latitude, int longitude) {
        super(rotated, 5, latitude, longitude); // Specifies rotation, length of 5, and given position
    }
}