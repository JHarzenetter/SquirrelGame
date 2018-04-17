package Data;

public enum EntityType {
    BadPlant("BP") , GoodPlant("GP") , BadBeast("BB") , Goodbeast("GB") , MasterSquirrel("MA") , MiniSquirrel("MI") , Wall("W") , Air("");

    private final String wert;

    EntityType(String wert){
        this.wert = wert;
    }

    public String getWert(){return wert;}
}
