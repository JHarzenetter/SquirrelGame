package Data;

public enum EntityType {
    BadPlant("-") , GoodPlant("+") , BadBeast("B") , Goodbeast("R") , MasterSquirrel("M") , MiniSquirrel("s") , Wall("W") , Air(" ");

    private final String wert;

    EntityType(String wert){
        this.wert = wert;
    }

    public String getWert(){return wert;}
}
