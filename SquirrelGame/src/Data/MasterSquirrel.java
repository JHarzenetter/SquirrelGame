package Data;

import java.util.logging.Logger;

public abstract class MasterSquirrel extends Squirrel {

    private static Logger logger = Logger.getLogger("MasterSquirrelLogger");

    protected MasterSquirrel(int x, int y) {
        super(5 , 1000 , x,y);
    }

    public MiniSquirrel createMini(int energy){
        logger.info("MINI_SQUIRREL spawned");
        try{
            if(energy>=100 && energy<=this.getEnergy()){
                MiniSquirrel mini = new MiniSquirrel(energy, getPlace().getX(), getPlace().getY(), this);
                updateEnergy(-energy);
                return mini;
            } else {
                throw new NotEnoughEnergyExeption();
            }
        } catch (NotEnoughEnergyExeption e){
            return null;
        }
    }

    public MiniSquirrel createMini(XY direction , int energy){
        logger.info("MINI_SQUIRREL spawned");
        try{
            if(energy>=100 && energy<=this.getEnergy()){
                MiniSquirrel mini = new MiniSquirrel(energy, getPlace().getX() + direction.getX(), getPlace().getY() + direction.getY(), this);
                updateEnergy(-energy);
                return mini;
            } else {
                logger.warning("Not enough Energy!");
                throw new NotEnoughEnergyExeption();
            }
        } catch (NotEnoughEnergyExeption e){
            return null;
        }
    }

    public boolean checkEntitie(Entity e){
        if(e instanceof MiniSquirrel)
            if(((MiniSquirrel)e).getMaster() == this){
                return true;
            }
        return false;
    }


    public String toString(){
        return ("Type: MASTER_SQUIRREL " +super.toString());
    }
}