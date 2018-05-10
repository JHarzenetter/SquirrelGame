package Data;

public abstract class Game{
    protected State state;
    private long FPS;

    protected Game(State state){
        this.state = state;
        FPS = 100;
    }

    public void run() {
            render();
            processInput();
            update();
    }

    protected abstract void update();

    protected abstract void processInput();

    protected abstract void render();

}
