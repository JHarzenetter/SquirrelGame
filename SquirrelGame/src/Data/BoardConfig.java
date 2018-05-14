package Data;

public class BoardConfig{

    private int length = 30;
    private int height = 20;
    private int amountOfBadBeast = 4;
    private int amountOfGoodBeast = 4;
    private int amountOfBadPlant = 4;
    private int amountOfGoodPlant = 4;
    private int amountOfWall = 5;

    //21 + 40 +40 + 49 +49 = 199


    public int getHeight() {
        return height;
    }
    public int getLength() {
        return length;
    }
    public XY getSize(){return new XY(30,20);}
    public int getAmountOfBadBeast(){return amountOfBadBeast;}
    public int getAmountOfGoodBeast(){return amountOfGoodBeast;}
    public int getAmountOfBadPlant(){return amountOfBadPlant;}
    public int getAmountOfGoodPlant(){return amountOfGoodPlant;}
    public int getAmountOfWall(){return amountOfWall;}
    public int getAmountOfEntities(){return ((length+height)*2 -2+amountOfBadBeast+amountOfGoodBeast+amountOfBadPlant+amountOfGoodPlant+amountOfWall);}

}