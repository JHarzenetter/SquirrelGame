package JUnit;

import Data.*;
import junit.framework.*;

public class JUnitTest extends TestCase {

    public JUnitTest(String name){
        super(name);
    }

    public void testXY(){
        XY xy = new XY(1,1);
        assertTrue(xy.equals(XY.RIGHT_DOWN));
        assertTrue(XY.ZERO_ZERO.equals(xy.minus(XY.RIGHT_DOWN)));
    }

    public void testCollisions(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        Wall w = new Wall(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1); //up
        BadBeast bb = new BadBeast(masterSquirrel.getPlace().getX()+1,masterSquirrel.getPlace().getY()); //right
        MasterSquirrel mas = new HandOperatedMasterSquirrel(masterSquirrel.getPlace().getX()-1,masterSquirrel.getPlace().getY()); //left
        GoodPlant gp = new GoodPlant(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()+1); //down
        GoodBeast gb = new GoodBeast(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()+2); //down


        b.addEntity(w);
        b.addEntity(bb);
        b.addEntity(mas);
        b.addEntity(gp);
        b.addEntity(gb);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();

        XY sad = masterSquirrel.getPlace();
        int energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(sad));
        assertTrue(masterSquirrel.getEnergy() -/*weil negativ Engery*/ w.getEnergy() == energysafe);

        masterSquirrel.setWait(0); // um stunned zu umgehen
        energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.RIGHT);
        assertTrue(masterSquirrel.getPlace().equals(sad));
        assertTrue(masterSquirrel.getEnergy() -/*weil negativ Engery*/ bb.getEnergy() == energysafe);

        energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel, XY.LEFT);
        assertTrue(masterSquirrel.getPlace().equals(sad));
        assertTrue(masterSquirrel.getEnergy() == energysafe);

        energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.DOWN);
        assertTrue(masterSquirrel.getPlace().equals(gp.getPlace()));
        assertTrue(masterSquirrel.getEnergy() == energysafe+gp.getEnergy());

        energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.DOWN);
        assertTrue(masterSquirrel.getPlace().equals(gb.getPlace()));
        assertTrue(masterSquirrel.getEnergy() == energysafe+gb.getEnergy());
    }

    public void testStunned(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        Wall w = new Wall(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();

        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getWait() >=0);
    }

    public void testVelocity(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        FlattenedBoard fb = b.flattened();

        XY sad = masterSquirrel.getPlace();

        fb.tryToMove(masterSquirrel,XY.DOWN);

        assertTrue(masterSquirrel.getPlace().getX() - sad.getX() <= 1 && masterSquirrel.getPlace().getY()-sad.getY() <=1);
    }

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(JUnitTest.class);
    }
}
