package Data;

public class State {

    private int highScore;
    private Board board;

    public State(Board b){
        this.board = b;
        highScore = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public FlattenedBoard flattenedBoard(){
        return board.flattened();
    }

    public void update(){
        board.update();
    }
}
