package Colors;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class NewColors extends Canvas {

    static int gridType, dimension, size = 800, length;
    static String word, space = "";
    static Scanner scan = new Scanner(System.in);
    Random rnd = new Random();

    public static void main(String[] args) {
        System.out.println("Color: 0");
        System.out.println("Grey Scale: 1");
        System.out.println("Red Scale: 2");
        System.out.println("Green Scale: 3");
        System.out.println("Blue Scale: 4");
        System.out.println("Words: 5");
        System.out.print("Enter MovingGrid Type: ");
        gridType = Integer.parseInt(scan.nextLine());
        if(gridType >= 0 && gridType <= 4) {
            System.out.print("Enter Resolution: ");
            dimension = scan.nextInt();
        }
        if(gridType == 5) {
            System.out.print("Enter word: ");
            word = scan.nextLine();
            for(int i = 0; i < word.length() && i >= 0; i++){
                if(i >= 14){
                    space = word.substring(0, i);
                    dimension = 7 * space.length();
                    i = -2;
                    length = space.length();
                }
                else{
                    dimension = 7 * word.length();
                    length = word.length();
                }
            }
            size = 700;
        }

        JFrame frame = new JFrame("Better Colors");
        NewColors nc = new NewColors();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(nc);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        nc.repaint();
    }

    public NewColors() {
        setSize(size, size);
    }

    @Override
    public void update(Graphics g) {
        BinaryLetters b = new BinaryLetters();
        int xpos = 0, ypos = 0;
        int loop = 0;
        int color, color2;

        if(gridType >= 0 && gridType <= 4) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    switch (gridType) {
                        case 0:
                            g.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                            break;
                        case 1:
                            color = rnd.nextInt(256);
                            g.setColor(new Color(color, color, color));
                            break;
                        case 2:
                            while(loop == 0) {
                                color = rnd.nextInt(256);
                                color2 = rnd.nextInt(256);
                                if (color >= color2) {
                                    g.setColor(new Color(color, color2, color2));
                                    loop = 1;
                                }
                                else
                                    color2 = rnd.nextInt(256);
                            }
                            break;
                        case 3:
                            while(loop == 0) {
                                color = rnd.nextInt(256);
                                color2 = rnd.nextInt(256);
                                if (color >= color2) {
                                    g.setColor(new Color(color2, color, color2));
                                    loop = 1;
                                }
                                else
                                    color2 = rnd.nextInt(256);
                            }
                            break;
                        case 4:
                            while(loop == 0) {
                                color = rnd.nextInt(256);
                                color2 = rnd.nextInt(256);
                                if (color >= color2) {
                                    g.setColor(new Color(color2, color2, color));
                                    loop = 1;
                                }
                                else
                                    color2 = rnd.nextInt(256);
                            }
                            break;
                    }
                    g.fillRect(xpos, ypos, size / dimension, size / dimension);
                    xpos += size / dimension;
                    loop = 0;
                }
                xpos = 0;
                ypos += size / dimension;
            }
        }
        else if(gridType == 5) {
            int letterXpos = 0, letterYpos = 0, x = 0;
            String index;
            int[][] letterBinary;
            for(int i = 0; i < word.length(); i++){
                index = word.substring(i, i+1);
                letterBinary = b.letterConvert(index);
                for(int j = 0; j < letterBinary.length; j++){
                    for(int k = 0; k < letterBinary.length; k++){
                        if(letterBinary[j][k] == 1)
                            g.fillRect(letterXpos + (xpos / length), letterYpos + (ypos / length), size / dimension, size / dimension);
                        xpos += size / letterBinary.length;
                    }
                    xpos = 0;
                    ypos += size / letterBinary.length;
                }
                ypos = 0;
                x++;
                if(space.length() > 0 && x >= space.length()){
                    letterXpos = 0;
                    letterYpos += (size / dimension) * 9;
                    x = 0;
                }
                else
                    letterXpos += (size / dimension) * 7;
            }
        }
        else {
            System.out.println("Invalid Case");
            g.drawString("Sorry", (size/2)-5, size/2);
        }
    }

    @Override
    public void paint(Graphics gfx) {
        update(gfx);
    }
}