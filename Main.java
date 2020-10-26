import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import java.util.Arrays;

public class Main extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1015;
    public static final int HEIGHT = 722;
    public static final long FPS = 60;
    World worl;
    public BufferedImage background;
    public BufferedImage splashimage;
    public BufferedImage splashtext;
    public BufferedImage gameover;
    public BufferedImage playerone;
    public BufferedImage playertwo;
    public BufferedImage restart;
    public static BufferedImage sprites[] = new BufferedImage[272];
    // Player 1 Keys
    public static boolean wKey = false;
    public static boolean qKey = false;
    public static boolean sKey = false;
    public static boolean aKey = false;
    public static boolean dKey = false;
    public static boolean fKey = false;
    public static boolean onekey = false;
    public static boolean twokey = false;
    public static boolean threekey = false;
    public static boolean fourkey = false;
    // Player 2 Keys
    public static boolean kKey = false;
    public static boolean lKey = false;
    public static boolean colonKey = false;
    public static boolean jKey = false;
    public static boolean pKey = false;
    public static boolean oKey = false;
    public static boolean sevenkey = false;
    public static boolean eightkey = false;
    public static boolean ninekey = false;
    public static boolean zerokey = false;

    public static boolean vKey = false;

    public boolean gameDone = false; 
    public static boolean splash = true; 

    //main loop that runs the game
    //updates players, checks if the game is done, and repaints
    public void run(JFrame frame) {
        while (true) { 
            worl.updatePlayerStates(gameDone);
            worl.updatePlayers(gameDone); 
            for (int i = 0; i < 2; i++) { 
                if (worl.players[i].health <= 0) {
                    gameDone = true;
                }
            }
            repaint();
            try {
                Thread.sleep(1000 / FPS); 
            } catch (InterruptedException e) {
            }
        }

    }
    //initializes the main object
    public Main() {
        worl = new World(WIDTH, HEIGHT, 2); //Initializes world with two players
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
    }

    //buffers all images for the game
    public void bufferImages() { 
        try { 
            background = ImageIO.read(new File("Background.png"));
            splashimage = ImageIO.read(new File("splash.png"));
            splashtext = ImageIO.read(new File("splashtext.png"));
            gameover = ImageIO.read(new File("gameover.png"));
            playerone = ImageIO.read(new File("playerone.png"));
            playertwo = ImageIO.read(new File("playertwo.png"));
            restart = ImageIO.read(new File("restart.png"));

        } catch (IOException ex) {
            System.out.println("Could not find picture");
        }
        File spritesfilearray[] = new File("sprites").listFiles();
        Arrays.sort(spritesfilearray, (a, b) -> a.getName().compareTo(b.getName()));
        
        for (int i = 0; i < spritesfilearray.length; ++i) { 
            try {
                sprites[i] = ImageIO.read(spritesfilearray[i]);
            } catch (IOException ex) {
                System.out.println("No image at file index" + i);
            }
        }

    }
//initializes the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cat Fight");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main mainInstance = new Main();
        mainInstance.bufferImages(); 
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        mainInstance.run(frame);
    }
    //draws everything, and resets the game
    public void paintComponent(Graphics gOri) {
        Graphics2D g = (Graphics2D) gOri;
        //draws once the game is finished
        if (gameDone) {
            g.drawImage(background, 0, 0, this);
            g.drawImage(gameover, 0, 0, this);
            g.drawImage(restart,305,500,this);
            worl.drawPlayers(g,gameDone);
            int winner = 0;
            for (int i = 0; i < 2; i++) {
                if (worl.players[i].health > 0)
                    winner = i+1;
            }
            if(winner==1){
                g.drawImage(playerone, 0, 0, this);
            }
            if(winner==2){
                g.drawImage(playertwo, 0, 0, this);
            }
            

            if (vKey == true) {
                worl.players[0].setHealth(100);
                worl.players[1].setHealth(100);
                worl.players[0].positionX = ((-Main.WIDTH / 4) + worl.players[0].playerNum * (Main.WIDTH / 2)) - (worl.players[0].playerNum-1) * 90;
                worl.players[1].positionX = ((-Main.WIDTH / 4) + worl.players[1].playerNum * (Main.WIDTH / 2)) - (worl.players[1].playerNum-1) * 90;
                gameDone = false;
                splash = true;
                onekey = false;
                twokey = false;
                threekey = false;
                fourkey = false;
                sevenkey = false;
                eightkey = false;
                ninekey = false;
                zerokey = false;
                vKey = false;
            }

        }
        //draws during the game
        else if (!splash) { 
            g.drawImage(background, 0, 0, this);
            worl.drawPlayers(g,gameDone);
        } 
        //draws the character selection screen
        else {
            g.drawImage(splashimage, 0, 0, this);
            if (onekey) { 
                g.setPaint(Color.YELLOW);
                g.fillRect(7, 385, 110, 30);
            }
            if (twokey) {
                g.setPaint(Color.PINK);
                g.fillRect(117, 385, 130, 30);
            }
            if (threekey) {
                g.setPaint(Color.BLUE);
                g.fillRect(247, 385, 110, 30);
            }
            if (fourkey) {
                g.setPaint(Color.WHITE);
                g.fillRect(357, 385, 120, 30);
            }
            if (sevenkey) {
                g.setPaint(Color.YELLOW);
                g.fillRect(507, 385, 110, 30);
            }
            if (eightkey) {
                g.setPaint(Color.PINK);
                g.fillRect(617, 385, 130, 30);
            }
            if (ninekey) {
                g.setPaint(Color.BLUE);
                g.fillRect(743, 385, 110, 30);
            }
            if (zerokey) {
                g.setPaint(Color.WHITE);
                g.fillRect(853, 385, 130, 30);
            }

            g.drawImage(splashtext, 0, 0, this);
        }

    }
//takes in key presses
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        c=Character.toLowerCase(c);
////Splash Screen  
        if (c == 't' || c == 'T') { 
            if (!onekey && !twokey && !threekey && !fourkey){ ///Makes sure that user selects characters before they start game

            } else if (!sevenkey && !eightkey && !ninekey && !zerokey){

            } else
                splash = false;
        }
/////////////Player 1
        if (c == 'v' && gameDone) {
            vKey = true;
        }
        if (c == 'a' && !splash) { // Shield
            aKey = true;
        }

        if (c == 's' && !splash) { // Light Punch
            sKey = true;
        }

        if (c == 'd' && !splash) { // Move Left
            dKey = true;
        }

        if (c == 'f' && !splash) { // Move Right
            fKey = true;
        }

        if (c == 'q' && !splash) { // Grab
            qKey = true;
        }
        if (c == 'w' && !splash) { // Heavy Punch
            wKey = true;
        }
        if (c == '1' && splash) { //When player type is selected, sets that players stats. Also checks to make sure no other type has been selected so there isn't 
            onekey = true;
            twokey = false;
            threekey = false;
            fourkey = false;
            worl.players[0].setSpeed(3);
            worl.players[0].setColor(0);
            worl.players[0].setState(0);
            worl.players[0].setPower(10);
            worl.players[0].setHealth(100);
            worl.players[0].healthplace = 100;
        }
        if (c == '2' && splash) {
            twokey = true;
            onekey = false;
            threekey = false;
            fourkey = false;
            worl.players[0].setPower(15);
            worl.players[0].setColor(2);
            worl.players[0].setState(2);
            worl.players[0].setSpeed(0);
            worl.players[0].setHealth(100);
            worl.players[0].healthplace = 100;
        }
        if (c == '3' && splash) {
            threekey = true;
            onekey = false;
            twokey = false;
            fourkey = false;
            worl.players[0].setHealth(150);
            worl.players[0].healthplace=150;
            worl.players[0].setColor(4);
            worl.players[0].setState(4);
            worl.players[0].setSpeed(0);
            worl.players[0].setPower(10);
        }
        if (c == '4' && splash) {
            Random rand = new Random();
            fourkey = true;
            onekey = false;
            twokey = false;
            threekey = false;
            int healthRand = (int) rand.nextInt(70);
            int speedRand = (int) rand.nextInt(3);
            int powerRand = (int) rand.nextInt(7);
            worl.players[0].healthplace= (70+healthRand);
            worl.players[0].setHealth(70+healthRand);
            worl.players[0].setSpeed(speedRand);
            worl.players[0].setPower(7+powerRand);
            worl.players[0].setColor(6);
            worl.players[0].setState(6);

        }

        //////////////// Player 2//////////////////////////////////
        if (c == 'k' && !splash) {
            kKey = true;
        }
        if (c == 'l' && !splash) {
            lKey = true;
        }
        if (c == ';' && !splash) {
            colonKey = true;
        }
        if (c == 'j' && !splash) {
            jKey = true;
        }
        if (c == 'p' && !splash) {
            pKey = true;
        }
        if (c == 'o' && !splash) {
            oKey = true;
        }

        if (c == '7' && splash) { ////Selecting Characters through the same method as above except for player 2
            sevenkey = true;
            eightkey = false;
            ninekey = false;
            zerokey = false;
            worl.players[1].setSpeed(3);
            worl.players[1].setColor(1);
            worl.players[1].setState(1);
            worl.players[1].setPower(10);
            worl.players[1].setHealth(100);
            worl.players[1].healthplace = 100;
        }
        if (c == '8' && splash) {
            eightkey = true;
            sevenkey = false;
            ninekey = false;
            zerokey = false;
            worl.players[1].setPower(15);
            worl.players[1].setColor(3);
            worl.players[1].setState(3);
            worl.players[1].setSpeed(0);
            worl.players[1].setHealth(100);
            worl.players[1].healthplace = 100;
        }
        if (c == '9' && splash) {
            ninekey = true;
            sevenkey = false;
            eightkey = false;
            zerokey = false;
            worl.players[1].setHealth(150);
            worl.players[1].healthplace=150;
            worl.players[1].setColor(5);
            worl.players[1].setState(5);
            worl.players[1].setSpeed(0);
            worl.players[1].setPower(10);
        }
        if (c == '0' && splash) {
            Random rand = new Random();
            zerokey = true;
            sevenkey = false;
            eightkey = false;
            ninekey = false;
            int healthRand = (int) rand.nextInt(70);
            int speedRand = (int) rand.nextInt(3);
            int powerRand = (int) rand.nextInt(7);
            worl.players[1].setHealth(70+healthRand);
            worl.players[1].healthplace= (70+healthRand);
            worl.players[1].setSpeed(speedRand);
            worl.players[1].setPower(7+powerRand);
            worl.players[1].setColor(7);
            worl.players[1].setState(7);
        }
    }

    public void keyReleased(KeyEvent e) { //Sets key booleans false once they're no longer pressed
        char c = e.getKeyChar();
        c=Character.toLowerCase(c);
        if (c == 'a') {
            aKey = false;
        }
        if (c == 's') {
            sKey = false;
        }
        if (c == 'd') {
            dKey = false;
        }
        if (c == 'f') {
            fKey = false;
        }
        if (c == 'q') {
            qKey = false;
        }
        if (c == 'w') {
            wKey = false;
        }
        if (c == 'k') {
            kKey = false;
        }
        if (c == 'l') {
            lKey = false;
        }
        if (c == 'j') {
            jKey = false;
        }
        if (c == ';') {
            colonKey = false;
        }
        if (c == 'p') {
            pKey = false;
        }
        if (c == 'o') {
            oKey = false;
        }
    }
 
    public void keyTyped(KeyEvent e) { 
        e.getKeyChar();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
