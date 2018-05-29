package Data;

public final class XY {

    private final int x;
    private final int y;

    public static final XY ZERO_ZERO = new XY(0, 0);
    public static final XY RIGHT = new XY(1, 0);
    public static final XY LEFT = new XY(-1, 0);
    public static final XY UP = new XY(0, -1);
    public static final XY DOWN = new XY(0, 1);
    public static final XY RIGHT_UP = new XY(1, -1);
    public static final XY RIGHT_DOWN = new XY(1, 1);
    public static final XY LEFT_UP = new XY(-1, -1);
    public static final XY LEFT_DOWN = new XY(-1, 1);

    public XY(int x , int y){
        this.x =x;
        this.y =y;
    }
    //TODO: implement methods
    public XY plus(XY xy){
        return new XY(x+xy.getX(),y+xy.getY());
    }

    public XY minus(XY xy){
        return new XY(x-xy.getX(),y-xy.getY());
    }

    public XY times(int factor){
        return null;
    }

    public double length(){
        return 0;
    }

    public double distanceFrom(XY xy){
        int dx = x - xy.getX();
        int dy = y - xy.getY();

        double sqrt = dx*dx+dy*dy;

        if (sqrt<0) {
            sqrt = sqrt * (-1);
        }

        return Math.sqrt(sqrt);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
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
