package Data;

import java.util.Random;

public class XYsupport {
    private static XY[] directions = new XY[] {XY.DOWN, XY.UP, XY.LEFT, XY.RIGHT, XY.LEFT_DOWN, XY.LEFT_UP, XY.RIGHT_DOWN, XY.RIGHT_UP, XY.ZERO_ZERO};
    private static String[] names = new String[] {"DOWN", "UP", "LEFT", "RIGHT", "LEFT_DOWN", "LEFT_UP", "RIGHT_DOWN", "RIGHT_UP", "ZERO_ZERO"};

    public static XY valueOf(String s) {
        s=s.toUpperCase();
        for(int i=0; i<names.length;i++) {
            if(s.equals(names[i])) {
                return directions[i];
            }
        }
        return XY.ZERO_ZERO;
    }

    public static XY getRandCommand() {
        int r = new Random().nextInt(8);
        return directions[r];
    }
}
