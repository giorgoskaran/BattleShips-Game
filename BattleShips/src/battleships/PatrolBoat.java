package battleships;


public class PatrolBoat extends Ships {

    
    public PatrolBoat() {
        super(true, 2, -1, -1); // True for rotated, length of 2, and initial position outside the board
    }
    
    /* Constructs a Patrol Boat with specified rotation and position.
     * rotated Indicates if the ship is rotated.
     * latitude The latitude (row) position of the ship on the board.
     * longitude The longitude (column) position of the ship on the board.
     */
    public PatrolBoat(boolean rotated, int latitude, int longitude) {
        super(rotated, 2, latitude, longitude); // Specifies rotation, length of 2, and given position
    }
}