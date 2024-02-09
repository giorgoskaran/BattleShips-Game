package battleships;

import java.util.ArrayList;

public class Ships {

    private int length;
    private int latitude;
    private int longitude;
    private boolean on; // Indicates if the specific ship is selected during placement
    private boolean rotated; // Indicates if the selected ship has been rotated
    private ArrayList<Shot> shots; // Stores shots with their coordinates

    public Ships(boolean rotated, int length, int latitude, int longitude) {
        this.rotated = rotated;
        this.length = length;
        this.latitude = latitude;
        this.longitude = longitude;
        this.on = false;
        this.shots = new ArrayList<>();
    }

    // Getters and Setters
    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getLength() {
        return length;
    }

    public boolean isRotated() {
        return rotated;
    }

    public void setRotated(boolean rotated) {
        this.rotated = rotated;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }

    // Method to check if a given coordinate belongs to this ship
    public boolean PanelBelongsShips(int lat, int longi) {
        if (this.latitude == -1 || this.longitude == -1) { // outside board
            return false;
        }

        if (rotated) {
            for (int j = this.longitude; j < this.longitude + this.length; j++) { // check based on rows
                if (this.latitude == lat && j == longi) {
                    return true;
                }
            }
        } else {
            for (int i = this.latitude; i < this.latitude + this.length; i++) { // check based on columns
                if (this.longitude == longi && i == lat) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to check if a given shot's coordinates match any of the ship's panels
    public boolean ShotOnShipsPanel(Shot shot) {
        return PanelBelongsShips(shot.getLat(), shot.getLongi());
    }

    // Method to check if a shot with given coordinates has already been added to the ship
    public boolean ShipsPanelsClicked(int lat, int longi) {
        for (Shot shot : this.shots) {
            if (shot.getLat() == lat && shot.getLongi() == longi) {
                return true;
            }
        }
        return false;
    }

    // Method to determine if the ship is out of the board's boundaries
    public Boolean ShipsOutOfBoard(int LatLimit, int LongiLimit) {
        if (this.latitude == -1 || this.longitude == -1 || this.longitude >= LongiLimit || this.latitude >= LatLimit) {
            return true;
        }

        if (rotated) {
            for (int j = this.longitude; j < this.longitude + this.length; j++) {
                if (LongiLimit - j <= 0 || j >= LatLimit) {
                    return true;
                }
            }
        } else {
            for (int i = this.latitude; i < this.latitude + this.length; i++) {
                if (LatLimit - i <= 0 && i >= LongiLimit) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to check if all panels of the ship have been clicked (shot at)
    public Boolean AllShipsPanelsClicked() {
        return shots.size() == this.length;
    }

    // Method to check if this ship collides with another ship
    public Boolean ShipsCollidesShips(Ships ship) {
        if (this.latitude == -1 || this.longitude == -1) { // outside board
            return false;
        }

        if (rotated) { 
            for (int j = longitude; j < this.longitude + this.length; j++) {
                if (ship.PanelBelongsShips(this.latitude, j)) {
                    return true;
                }
            }
        } else {
            for (int i = this.latitude; i < this.latitude + this.length; i++) {
                if (ship.PanelBelongsShips(i, this.longitude)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to add a shot to the ship if it hits a panel of the ship
    public boolean ShotOnShip(Shot shot) {
        if (!ShotOnShipsPanel(shot)) {
            return false;
        }
        this.shots.add(shot);
        return true;
    }
}