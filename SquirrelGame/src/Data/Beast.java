package Data;

public abstract class Beast extends Character{
    Beast(int pID, int pEnergy, int x, int y) {
        super(pID, pEnergy, x, y);
    }

   XY nearestSquirrel;

    public boolean isSquirrelNear(Entity e, FlattenedBoard f) {  //insert badBeast KI!
        int step = 1;

        while(step <= 6) {
            int tx = e.place.getX()-1;
            int ty = e.place.getY();

            //start 1 links neben entity
            //dann nach oben , dann nach recht , dann nach unten , dann nach links , dann nach oben bis startpunkt erreicht

            while (ty != e.place.getY() - step) {
                ty--;
                if(ty < f.getSize().getY() && tx <f.getSize().getX()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (tx != e.place.getX() + step) { // nach rechts
                tx++;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (ty != e.place.getY() + step) { // nach unten
                ty++;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (tx != e.place.getX() - step) { // nach links
                tx--;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (ty != e.place.getY()) { // nach oben
                ty--;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
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
