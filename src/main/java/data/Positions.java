package data;

import java.util.ArrayList;

public class Positions {

    private ArrayList<SinglePosition> positionsList = new ArrayList<>();

    private Positions positions;



    public Positions() {
        positions = this;
    }

    public Positions getInstance() {
        return positions;
    }

    public ArrayList<SinglePosition> getPositionsList() {
        return positionsList;
    }
}
