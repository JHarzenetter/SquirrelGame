package Data;

public abstract class Character extends Entity{

    Character(int pID, int pEnergy, int x, int y) {
        super(pID, pEnergy, x, y);
    }

    public void nextStep() {
        switch (place.getRandDirection()) {
            case 1: { // move up
                place = new XY(place.getX(), place.getY() - 1);
                break;
            }
            case 2: { // move up right
                place = new XY(place.getX() + 1, place.getY() - 1);
                break;
            }
            case 3: { // move right
                place = new XY(place.getX() + 1, place.getY());
                break;
            }
            case 4: { // move right down
                place = new XY(place.getX() + 1, place.getY() + 1);
                break;
            }
            case 5: { // move down
                place = new XY(place.getX(), place.getY() + 1);
                break;
            }
            case 6: { // move down left
                place = new XY(place.getX() - 1, place.getY() + 1);
                break;
            }
            case 7: { // move left
                place = new XY(place.getX() - 1, place.getY());
                break;
            }
            case 8: { // move up left
                place = new XY(place.getX() - 1, place.getY() - 1);
                break;
            }
        }
    }



}
