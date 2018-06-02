package JUnit;

import Data.*;
import junit.framework.*;

public class JUnitTest extends TestCase {

    public JUnitTest(String name){
        super(name);
    }

    public void testXY(){
        XY xy = new XY(1,1);
        XY xy2 = new XY(2,2);
        assertTrue(xy2.equals(xy.plus(xy)));
        assertTrue(xy.equals(xy2.minus(xy)));
    }

    public void testCollisionWall(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        Wall w = new Wall(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();

        XY sad = masterSquirrel.getPlace();
        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(sad));
    }

    public void testCollisionBB(){
        Board b = new Board(new BoardConfig("test"));
        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(10,10);
        BadBeast w = new BadBeast(masterSquirrel.getPlace().getX(),masterSquirrel.getPlace().getY()-1);

        b.addEntity(w);
        b.addEntity(masterSquirrel);

        FlattenedBoard fb = b.flattened();

        XY sad = masterSquirrel.getPlace();
        fb.tryToMove(masterSquirrel,XY.UP);
        assertTrue(masterSquirrel.getPlace().equals(sad));
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
