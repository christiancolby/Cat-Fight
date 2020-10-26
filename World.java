import java.awt.Graphics;

public class World {
    int height;
    int width;

    int numPlayers;
    public Player players[];

    //initializes the world and creates players
    public World(int initWidth, int initHeight, int initNumPlayers) {
        width = initWidth;
        height = initHeight;

        numPlayers = initNumPlayers;
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player();
        }
    }
    // called by the run method in main, draws the players
    public void drawPlayers(Graphics g, boolean gameDone) { 
        players[0].draw(g,gameDone);
        players[1].draw(g,gameDone);
        
    }
    //handles key presses, hecks to see if players are damaged and updates their position
    public void updatePlayers(boolean gameDone) { 
        Keys.updateKeys(this); 
        checkDamage();
        players[0].move(gameDone); 
        players[1].move(gameDone);
  
    }
    //updates player states
    public void updatePlayerStates(boolean gameDone){
        players[0].updateState(gameDone); 
        players[1].updateState(gameDone);
        

    }
    //checks to see if a player has hit another player, and deals damage accordingly
    public void checkDamage(){
        int distance = Math.abs(this.players[0].positionX - this.players[1].positionX);
        int p1_state= this.players[0].getState();
        int p2_state= this.players[1].getState();
        int p1_block=this.players[0].color+8;
        int p2_block=this.players[1].color+8;
        double heavy_mult= 2;
        int heavy_range=45;
        double light_mult=1;
        int light_range= 55;
        double kick_mult_high = 1;
        double kick_mult_low = 0.1;
        int kick_range = 60;
        double p1_damage=0;
        double p2_damage=0;
        
        if (p1_state >= 88 && p1_state <= 103) {
            if (distance <= light_range) {
                if (p2_state != p2_block && !(p2_state >= 16 && p2_state <= 31))
                    p2_damage= light_mult * this.players[0].power;
            }
        }
        else if (p1_state >= 136  && p1_state <= 151) {
            if (distance <= kick_range) {
                if (p2_state == p2_block && !(p2_state >= 16 && p2_state <= 31)) {
                    p2_damage= kick_mult_high * this.players[0].power;
                }
                else {
                    p2_damage = kick_mult_low * this.players[0].power;
                }
            }
        }
        else if (p1_state >= 192 && p1_state <= 215) {
            if (distance <= heavy_range) {
                if (p2_state != p2_block && !(p2_state >= 16 && p2_state <= 31))
                    p2_damage= heavy_mult * this.players[0].power;
            }
        }
        if (p2_state >= 88 && p2_state <= 103) {
            if (distance <= light_range) {
                if (p1_state != p1_block && !(p1_state >= 16 && p1_state <= 31))
                    p1_damage= light_mult * this.players[1].power;
            }
        }
        else if (p2_state >= 136 && p2_state <= 151) {
            if (distance <= kick_range) {
                if (p1_state == p1_block && !(p1_state >= 16 && p1_state <= 31))
                    p1_damage = kick_mult_high * this.players[1].power; 
                else
                    p1_damage = kick_mult_low * this.players[1].power;
            }
        }
        else if (p2_state >= 192 && p2_state <= 215) {
            if (distance <= heavy_range) {
                if (p1_state != p1_block && !(p1_state >= 16 && p1_state <= 31))
                    p1_damage = heavy_mult * this.players[1].power; 
            }
        }
        if (p1_damage!=0) {
            this.players[0].takeDamage(p1_damage);
        }
        if (p2_damage!=0) {
            this.players[1].takeDamage(p2_damage);
        }
        
            
        
    }
}