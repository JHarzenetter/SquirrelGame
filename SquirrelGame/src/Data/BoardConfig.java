package Data;

public class BoardConfig{

    private int length;
    private int height;
    private long remainingSteps;
    private String[] botNames;
    private int amountOfBots;
    private int amountOfHandOperated;
    private int amountOfBadBeast;
    private int amountOfGoodBeast;
    private int amountOfBadPlant;
    private int amountOfGoodPlant;
    private int amountOfWall;

    //21 + 40 + 40 + 49 + 49 = 199
    public BoardConfig(){
        length = 60;
        height = 40;
        remainingSteps = -1;
        amountOfBots = 0;
        amountOfHandOperated = 1;
        amountOfBadBeast = amountOfGoodBeast = amountOfBadPlant = amountOfGoodPlant = 4;
        amountOfWall = 5;
    }

    public BoardConfig(String s){
        length = 60;
        height = 40;
        botNames = new String[]{"Factory","Factory2","Factory3"};
        remainingSteps = 100;
        amountOfBadBeast = amountOfGoodBeast = amountOfBadPlant = amountOfGoodPlant = 4;
        amountOfWall = 10;
        amountOfBots = botNames.length;
        amountOfHandOperated = 0;
    }

    public BoardConfig(int bots, int humans){
        length = 60;
        height = 40;
        amountOfBots = bots;
        amountOfHandOperated = humans;
        amountOfBadBeast = amountOfGoodBeast = amountOfBadPlant = amountOfGoodPlant = 4;
        amountOfWall = 5;
    }

    public XY getSize(){return new XY(length,height);}
    public int getAmountOfBots(){return amountOfBots;}
    public int getAmountOfHandOperated() {
        return amountOfHandOperated;
    }
    public int getAmountOfBadBeast(){return amountOfBadBeast;}
    public int getAmountOfGoodBeast(){return amountOfGoodBeast;}
    public int getAmountOfBadPlant(){return amountOfBadPlant;}
    public int getAmountOfGoodPlant(){return amountOfGoodPlant;}
    public int getAmountOfWall(){return amountOfWall;}
    public String[] getBotNames() {
        return botNames;
    }
    public long getRemainingSteps() {
        return remainingSteps;
    }
    public int getAmountOfEntities(){return ((length+height)*2-4+amountOfHandOperated+amountOfBots+amountOfBadBeast+amountOfGoodBeast+amountOfBadPlant+amountOfGoodPlant+amountOfWall);}

}