package battleships;

/* Represents a shot in the game of Battleships.
 * This class stores the coordinates of a shot on the game board.
 */
public class Shot {

    private int lat; // The row coordinate of the shot.
    private int longi; // The column coordinate of the shot.

    /* Constructs a Shot with specified coordinates.
     * lat The latitude (row) coordinate of the shot.
     * longi The longitude (column) coordinate of the shot.
     */
    public Shot(int lat, int longi) {
        this.lat = lat;
        this.longi = longi;
    }

    
    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    
    public int getLongi() {
        return longi;
    }

    public void setLongi(int longi) {
        this.longi = longi;
    }
}