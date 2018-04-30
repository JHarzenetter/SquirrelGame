package Data;

public final class XY {

    private final int x;
    private final int y;

    public XY(int px , int py){
        x=px;
        y=py;
    }

    public boolean equals(XY xy){
        return (xy.getX() == x && xy.getY() == y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
