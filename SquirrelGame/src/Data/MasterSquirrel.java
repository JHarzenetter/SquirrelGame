package Data;

import java.util.logging.Logger;

public abstract class MasterSquirrel extends Squirrel {

    private static Logger logger = Logger.getLogger("MasterSquirrelLogger");

    protected MasterSquirrel(int x, int y) {
        super(5 , 1000 , x,y);
    }

    /**
     * to Create an MiniSquirrel with a specific Energy
     * @param energy
     * @throws NotEnoughEnergyExeption occurs when energy is higher than MasterSquirrels Energy
     * @return
     */
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

    /**
     * to Create an MiniSquirrelBot with a specific Energy
     * @param direction
     * @param energy
     * @param context
     * @throws NotEnoughEnergyExeption occurs when energy is higher than MasterSquirrels Energy
     * @return
     */
    public MiniSquirrel createMiniBot(XY direction , int energy, EntityContext context){
        logger.info("MINI_SQUIRREL_BOT spawned");
        try{
            if(energy >= 100 && energy <= getEnergy()){
                MiniSquirrel mini = new MiniSquirrelBot(energy, getPlace().getX() + direction.getX(), getPlace().getY() + direction.getY(), this, context );
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

    /**
     * check, if the MiniSquirrel is mine or an enemy
     * @param e
     * @return
     */
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