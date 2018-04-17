package Data;

public class BoardConfig{

    private int length = 50;
    private int height = 40;
    private int amountOfBadBeast = 4;
    private int amountOfGoodBeast = 4;
    private int amountOfBadPlant = 4;
    private int amountOfGoodPlant = 4;
    private int amountOfWall = 5;

    //21 + 40 +40 + 49 +49 = 199

    public int getLength(){return length;}
    public int getHeight(){return height;}
    public int getAmountOfBadBeast(){return amountOfBadBeast;}
    public int getAmountOfGoodBeast(){return amountOfGoodBeast;}
    public int getAmountOfBadPlant(){return amountOfBadPlant;}
    public int getAmountOfGoodPlant(){return amountOfGoodPlant;}
    public int getAmountOfWall(){return amountOfWall;}
    public int getAmoutnOfEntiies(){return ((length+height)*2 + 19);}

}