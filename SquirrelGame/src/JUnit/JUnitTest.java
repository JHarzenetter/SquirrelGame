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

    public void testCollisionWall(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        Wall w = new Wall(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();

        XY sad = masterSquirrel.getPlace();
        int energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(sad));
        assertTrue(masterSquirrel.getEnergy() < energysafe);
    }

    public void testCollisionBB(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        BadBeast w = new BadBeast(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();

        XY sad = masterSquirrel.getPlace();
        int energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(sad));
        assertTrue(masterSquirrel.getEnergy() < energysafe);
    }

    public void testCollisionMaS(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        MasterSquirrel w = new HandOperatedMasterSquirrel(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();

        XY sad = masterSquirrel.getPlace();
        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(sad));
    }

    public void testCollisionGP(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        GoodPlant w = new GoodPlant(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();
        int energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(w.getPlace()));
        assertTrue(masterSquirrel.getEnergy() > energysafe);
    }

    public void testCollisionGB(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        GoodBeast w = new GoodBeast(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();
        int energysafe = masterSquirrel.getEnergy();

        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(w.getPlace()));
        assertTrue(masterSquirrel.getEnergy() > energysafe);
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
