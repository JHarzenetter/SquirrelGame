package Data;

public class State {

    private int highScore;
    private Board b;

    public State(Board b){
        this.b = b;
        highScore = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public FlattenedBoard flattenedBoard(){
        return b.flattend();
    }

    public void update(){
        b.update();
    }
}
