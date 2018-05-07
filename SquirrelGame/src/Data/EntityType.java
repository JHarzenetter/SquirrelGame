package Data;

public enum EntityType {
    BadPlant("-") , GoodPlant("+") , BadBeast("B") , Goodbeast("G") , MasterSquirrel("M") , MiniSquirrel("S") , Wall("W") , Air(" ");

    private final String wert;

    EntityType(String wert){
        this.wert = wert;
    }

    public String getWert(){return wert;}
}
