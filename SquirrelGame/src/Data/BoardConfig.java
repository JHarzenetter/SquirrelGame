package Data;

import java.io.*;
import java.util.Properties;

public class BoardConfig {

    private int length;
    private int height;
    private int remainingSteps;
    private String[] botNames;
    private int amountOfBots;
    private int amountOfHandOperated;
    private int amountOfBadBeast;
    private int amountOfGoodBeast;
    private int amountOfBadPlant;
    private int amountOfGoodPlant;
    private int amountOfWall;

    private File file;
    private Properties properties = new Properties();


    //21 + 40 + 40 + 49 + 49 = 199
    public BoardConfig() {
        file = new File("C:/Users/johan/IdeaProjects/SquirrelGame/BoardConfig.properties");
        if (file.exists()) {
            try {
                BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
                properties.load(reader);
                length = Integer.parseInt(properties.getProperty("length"));
                height = Integer.parseInt(properties.getProperty("height"));
                remainingSteps = Integer.parseInt(properties.getProperty("remainingSteps"));
                amountOfBots = Integer.parseInt(properties.getProperty("amountOfBots"));
                amountOfWall = Integer.parseInt(properties.getProperty("amountOfWall"));
                amountOfHandOperated = Integer.parseInt(properties.getProperty("amountOfHandOperated"));
                amountOfGoodBeast = Integer.parseInt(properties.getProperty("amountOfGoodBeast"));
                amountOfBadBeast = Integer.parseInt(properties.getProperty("amountOfBadBeast"));
                amountOfBadPlant = Integer.parseInt(properties.getProperty("amountOfBadPlant"));
                amountOfGoodPlant = Integer.parseInt(properties.getProperty("amountOfGoodPlant"));
                System.out.println(""+length+","+height+","+remainingSteps+","+amountOfBots+","+amountOfWall+","+amountOfHandOperated+","+amountOfGoodBeast+","+amountOfBadBeast+","+amountOfGoodPlant+","+amountOfBadPlant);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            length = 60;
            height = 40;
            remainingSteps = -1;
            amountOfBots = 0;
            amountOfHandOperated = 1;
            amountOfBadBeast = amountOfGoodBeast = amountOfBadPlant = amountOfGoodPlant = 4;
            amountOfWall = 5;
        }
    }

    public BoardConfig(String s) {
        file = new File("C:/Users/johan/IdeaProjects/SquirrelGame/BoardConfigBots.properties");
        if (file.exists()) {
            try {
                BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
                properties.load(reader);

                length = Integer.parseInt(properties.getProperty("length"));
                height = Integer.parseInt(properties.getProperty("height"));
                remainingSteps = Integer.parseInt(properties.getProperty("remainingSteps"));
                amountOfBots = Integer.parseInt(properties.getProperty("amountOfBots"));
                amountOfWall = Integer.parseInt(properties.getProperty("amountOfWall"));
                amountOfHandOperated = Integer.parseInt(properties.getProperty("amountOfHandOperated"));
                amountOfGoodBeast = Integer.parseInt(properties.getProperty("amountOfGoodBeast"));
                amountOfBadBeast = Integer.parseInt(properties.getProperty("amountOfBadBeast"));
                amountOfBadPlant = Integer.parseInt(properties.getProperty("amountOfBadPlant"));
                amountOfGoodPlant = Integer.parseInt(properties.getProperty("amountOfGoodPlant"));
                botNames = new String[]{"Factory", "Factory2", "Factory3"};
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            length = 60;
            height = 40;
            botNames = new String[]{"Factory", "Factory2", "Factory3"};
            remainingSteps = 100;
            amountOfBadBeast = amountOfGoodBeast = amountOfBadPlant = amountOfGoodPlant = 4;
            amountOfWall = 10;
            amountOfBots = botNames.length;
            amountOfHandOperated = 0;
        }
    }

    public BoardConfig(int bots, int humans) {
        length = 60;
        height = 40;
        amountOfBots = bots;
        amountOfHandOperated = humans;
        amountOfBadBeast = amountOfGoodBeast = amountOfBadPlant = amountOfGoodPlant = 4;
        amountOfWall = 5;
    }

    public XY getSize() {
        return new XY(length, height);
    }

    public int getAmountOfBots() {
        return amountOfBots;
    }

    public int getAmountOfHandOperated() {
        return amountOfHandOperated;
    }

    public int getAmountOfBadBeast() {
        return amountOfBadBeast;
    }

    public int getAmountOfGoodBeast() {
        return amountOfGoodBeast;
    }

    public int getAmountOfBadPlant() {
        return amountOfBadPlant;
    }

    public int getAmountOfGoodPlant() {
        return amountOfGoodPlant;
    }

    public int getAmountOfWall() {
        return amountOfWall;
    }

    public String[] getBotNames() {
        return botNames;
    }

    public long getRemainingSteps() {
        return remainingSteps;
    }

    public int getAmountOfEntities() {
        return ((length + height) * 2 - 4 + amountOfHandOperated + amountOfBots + amountOfBadBeast + amountOfGoodBeast + amountOfBadPlant + amountOfGoodPlant + amountOfWall);
    }

}