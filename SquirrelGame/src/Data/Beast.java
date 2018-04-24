package Data;

public abstract class Beast extends Character{
    Beast(int pID, int pEnergy, int x, int y) {
        super(pID, pEnergy, x, y);
    }

    private XY nearestSquirrel;

    public boolean isSquirrelNear(Entity e, FlattenedBoard f) {  //insert badBeast KI!
        int step = 1;

        while(step <= 6) {
            int tx = e.place.getX()-1;
            int ty = e.place.getY();

            while (ty != e.place.getY() - step) {
                ty--;
                if(ty < f.getSize().getY() && ty > 0 && tx < f.getSize().getX() && tx > 0){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (tx != e.place.getX() + step) { // nach rechts
                tx++;
                if(ty < f.getSize().getY() && ty > 0 && tx < f.getSize().getX() && tx > 0){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (ty != e.place.getY() + step) { // nach unten
                ty++;
                if(ty < f.getSize().getY() && ty > 0 && tx < f.getSize().getX() && tx > 0){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (tx != e.place.getX() - step) { // nach links
                tx--;
                if(ty < f.getSize().getY() && ty > 0 && tx < f.getSize().getX() && tx > 0){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (ty != e.place.getY()) { // nach oben
                ty--;
                if(ty < f.getSize().getY() && ty > 0 && tx < f.getSize().getX() && tx > 0){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            step++;
        }

        return false;
    }

    public XY getNearestSquirrel() {
        return nearestSquirrel;
    }
}
