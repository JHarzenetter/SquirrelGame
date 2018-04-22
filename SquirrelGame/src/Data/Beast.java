package Data;

import java.sql.SQLOutput;

public abstract class Beast extends Character{
    Beast(int pID, int pEnergy, int x, int y) {
        super(pID, pEnergy, x, y);
    }

   XY nearestSquirrel;

    public boolean isSquirrelNear(Entity e, FlattenedBoard f) {  //insert badBeast KI!
        int step = 1;
        while(step <= 6) {
            int tx = e.place.getX();
            int ty = e.place.getY()-1;

            //start 1 links neben entity
            // dann nach oben , dann nach recht , dann nach unten , dann nach links , dann nach oben bis startpunkt erreicht

            while (tx != e.place.getX() - step) {
                tx--;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (ty != e.place.getY() + step) { // nach rechts
                ty++;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (tx != e.place.getX() + step) { // nach unten
                tx++;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (ty != e.place.getY() - step) { // nach links
                ty--;
                if(tx < f.getSize().getX() && ty <f.getSize().getY()){
                    if (f.getFB()[tx][ty] instanceof Squirrel) {
                        nearestSquirrel = f.getFB()[tx][ty].place;
                        return true;
                    }
                }
            }
            while (tx != e.place.getX()) { // nach oben
                tx--;
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
