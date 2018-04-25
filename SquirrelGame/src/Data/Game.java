package Data;

public abstract class Game{
    protected State state;

    protected Game(State state){
        this.state = state;
    }

    public void run(){
        while(true){
            render();
            processInput();
            update();
        }
    }

    protected abstract void update();

    protected abstract void processInput();

    protected abstract void render();

}
