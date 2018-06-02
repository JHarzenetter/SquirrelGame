package Data;

public enum EntityType {
    BAD_PLANT("-") , GOOD_PLANT("+") , BAD_BEAST("B") , GOOD_BEAST("G") , MASTER_SQUIRREL("M") , MINI_SQUIRREL("S") , WALL("W") , ZERO_ZERO(" ");

    private final String wert;

    EntityType(String wert){
        this.wert = wert;
    }

    public String getWert(){return wert;}
}
