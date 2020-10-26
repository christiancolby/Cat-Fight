import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Player extends JPanel {
    private static final long serialVersionUID = 1L;
    int health = 100;
    int healthplace= 100;
    double power = 10;
    int speed = 0;
    int positionX = 500;
    private int positionY = 380;
    int color;
    private int state;
    private static int counter = 0;
    int playerNum;
    public int travel = 0;
    int animation = 0;
    int idle_counter = 0;
    public Player() {
        this.state = 0;
        counter++;
        this.playerNum = counter;
        this.positionX = ((-Main.WIDTH / 4) + playerNum * (Main.WIDTH / 2)) - (playerNum-1) * 90;
    }
    //changes player position according to travel variable
    public void move(boolean gameDone) {
        //prevents moving off edge
        if (this.positionX+travel>Main.WIDTH-100) 
            this.positionX=Main.WIDTH-100;
        else if (this.positionX+travel<-40)
            this.positionX=-40;
        //moves the player during normal circumstances
        else if (!gameDone)
            this.positionX = this.positionX + this.travel;
        //creates slowmo effect once the game finishes
        else
            this.positionX = this.positionX + (this.travel/4);
    }
    //updates the players states
    //player states govern how the player is drawn, and what they are doing (punching, blocking, etc.)
    public void updateState(boolean gameDone){
        if (animation == 0) {
            if (this.state==this.color){
                if (this.travel!=0)
                    this.state=this.color+256;
                else
                    if (idle_counter  == 3)
                        this.state=this.color+264;
            }
            else if (this.state>=16) {
                if (this.state<=31) {
                    if (this.state+8>31 && !gameDone)
                        this.state=this.color;
                    else {
                        this.state += 8;
                    }
                }
                else if (this.state<=63){
                    if (this.state+8<=63)
                        this.state+=8;
                }
                else if (this.state <= 103){
                    if (this.state + 8 > 103)
                        this.state=this.color;
                    else
                        this.state += 8;
                }
                else if (this.state<=159){
                    if (this.state+8>159)
                        this.state=this.color;
                    else
                        this.state += 8;
                }
                else if (this.state<=255){
                    if (this.state + 8 > 255) 
                        this.state = this.color;
                    else
                        this.state += 8;
                    if (this.state>=184 && this.state<=199)
                        this.positionY-=7;
                    else if (this.state>=208 && this.state<=223)
                        this.positionY+=7;
                }
                else if (this.state<264)
                    this.state=this.color;
                else if (idle_counter==3)
                    this.state=this.color;     
            }
        }
        if (idle_counter==3)
            idle_counter=0;
        if (animation >= 4 && !gameDone) {
            animation = 0;
            idle_counter++;
        }
        //creates a slowmo effect once the game is done
        else if (animation==18 && gameDone){
            animation = 0;
            idle_counter++;
        }
        else 
            animation++;
            
    }
    //Draws each player and, if the game isn't done, their health bar
    public void draw(Graphics g, boolean gameover) { 
        int healthPos= Main.WIDTH / 2+4;
        if (!gameover){
            g.setColor(Color.WHITE);
            g.fillRect(healthPos-3,(Main.HEIGHT /8) -51,6,37);
            if (this.playerNum==1){
                g.fillRect(healthPos-this.healthplace*3-5,(Main.HEIGHT /8) -47, (this.healthplace*3)+2, 29);
                g.setColor(Color.RED);
                g.fillRect(healthPos-this.healthplace*3-3, (Main.HEIGHT / 8) - 45, (this.healthplace*3), 25);
                g.setColor(Color.GREEN);
                g.fillRect(healthPos-this.health*3-3, (Main.HEIGHT / 8) - 45, (this.health*3), 25);
            }
            else{
                g.fillRect(healthPos+3,(Main.HEIGHT /8) -47, (this.healthplace*3)+2, 29);
                g.setColor(Color.RED);
                g.fillRect(healthPos+3, (Main.HEIGHT / 8) - 45, (this.healthplace*3), 25);
                g.setColor(Color.GREEN);
                g.fillRect(healthPos+3, (Main.HEIGHT / 8) - 45, (this.health*3), 25);
            }
        }
        
        g.drawImage(Main.sprites[getState()], this.positionX, this.positionY, this);

    }

    public int getColor() {
        return this.color;
    }

    public int getState() {
        return this.state;
    }

    public void setTravel(int x) {
        this.travel=x;
    }

    public void setPosition(int x) {
        this.positionX = this.positionX + x;
    }
    //lowers players health and knocks them back when they take damage
    public void takeDamage(double num) {
       
        int damage = (int)Math.round(num);
        this.health -= damage;
        this.state=this.color+16;
        if (damage <= 5) {
            if (this.playerNum == 1)
                this.positionX -= 40;
            else 
                this.positionX += 40;
        }
        else if (this.playerNum == 1)
            this.positionX -= damage * 6;
        else
            this.positionX += damage * 6;
        
    }

    public void setState(int x) {
        this.state = x;
    }

    public void setSpeed(int x) {
        this.speed = x;
    }

    public void setHealth(int x) {
        this.health = x;
    }

    public void setPower(int x) {
        this.power = x;
    }

    public void setColor(int x) {
        this.color = x;
    }

    public int getHealth() {
        return this.health;
    }
}
