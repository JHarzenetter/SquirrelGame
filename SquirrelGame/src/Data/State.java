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
        return new FlattenedBoard(b);
    }

    public Board getB(){return b;}

    public void update(){
        for (int i=0; i<b.getBoard().length-1;i++){
            b.getBoard()[i].nextStep(flattenedBoard());
        }
    }
}
