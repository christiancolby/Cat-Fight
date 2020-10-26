public class Keys {
    //changes player actions based on key presses
    public static void updateKeys(World worl) {
        //player 1 controls
        if (Main.sKey) {// Light Punch
            int state=worl.players[0].getState();
            int idle=worl.players[0].color;
            if (state < 16 || state > 255){
                worl.players[0].setTravel(0); //Sets travel to zero so they do not move during punch
                worl.players[0].setState(idle+64);
            }
        }
        if (!Main.sKey&&!Main.aKey&&!Main.dKey&&!Main.fKey&&!Main.wKey&&!Main.qKey) {// Neutral
            int idle=worl.players[0].color;
            if (worl.players[0].getState()==idle+8)
                worl.players[0].setState(idle);
            worl.players[0].setTravel(0);
        }
        if (Main.aKey) {// Block
            int state=worl.players[0].getState();
            int idle=worl.players[0].color;
            
            if (state < 16 || state > 255){
                worl.players[0].setTravel(0);//Sets travel to zero so they do not move during block
                worl.players[0].setState(idle+8);
            }
        }
        if (Main.dKey&&!Main.fKey) {// Move Left
            int state=worl.players[0].getState();
            if (state < 8 || state > 255){
                    worl.players[0].setTravel(-5-worl.players[0].speed);
            }
        }
        if (Main.fKey&&!Main.dKey) {// Move Right
            int distance = Math.abs(worl.players[0].positionX - worl.players[1].positionX);
            int state=worl.players[0].getState();
            if (state < 8 || state > 255){
                if (distance < 25)  //Checks if players are close to each other
                    worl.players[0].setTravel(0);//Sets travel to zero so players don't move through eachother
                else
                    worl.players[0].setTravel(5+worl.players[0].speed);
            }
            
                
        }
        if (Main.fKey&&Main.dKey){
            worl.players[0].setTravel(0);
        }
        if (Main.qKey) {//kick
            int state=worl.players[0].getState();
            int idle=worl.players[0].color;
            if (state < 16 || state > 255){
                worl.players[0].setTravel(0);//Sets travel to zero so they do not move during kick
                worl.players[0].setState(idle+104);
            }
        }
        if (Main.wKey) {//heavy punch
            int state=worl.players[0].getState();
            int idle=worl.players[0].color;
            if (state < 16 || state > 255){
                worl.players[0].setTravel(0);//Sets travel to zero so they do not move during punch
                worl.players[0].setState(idle+160);
            }
        }


        ///////////////////////////////////PLAYER 2
        
        if (Main.jKey&&!Main.kKey&&!Main.lKey&&!Main.colonKey&&!Main.pKey&&!Main.oKey) {// Move Left
            int distance = Math.abs(worl.players[0].positionX - worl.players[1].positionX);
            int state=worl.players[1].getState();
            if (state < 8 || state > 255){
                if (distance < 25) //Checks if players are close to each other
                    worl.players[1].setTravel(0);//Sets travel to zero so players don't move through eachother
                else
                    worl.players[1].setTravel(-5-worl.players[1].speed);
            }
        }
        if (Main.kKey&&!Main.jKey&&!Main.lKey&&!Main.colonKey&&!Main.pKey&&!Main.oKey) {// Move Right
            int state=worl.players[1].getState();
            if (state < 8 || state > 255){
                    worl.players[1].setTravel(5+worl.players[1].speed);
            }
        }
        if (Main.jKey&&Main.kKey){
            worl.players[1].setTravel(0);
        }
        if (!Main.kKey&&!Main.jKey&&!Main.lKey&&!Main.colonKey&&!Main.pKey&&!Main.oKey) {// neutral
            int idle=worl.players[1].color;
            if (worl.players[1].getState()==idle+8)
                worl.players[1].setState(idle);
            worl.players[1].setTravel(0);
        }
        if (Main.oKey) {// heavy Punch
            int state=worl.players[1].getState();
            int idle=worl.players[1].color;
            if (state < 16 || state > 255){
                worl.players[1].setTravel(0);//Sets travel to zero so they do not move during punch
                worl.players[1].setState(idle + 160);
            }
        }
        if (Main.lKey) {// Light Punch
            int state=worl.players[1].getState();
            int idle=worl.players[1].color;
            if (state < 16 || state > 255){
                worl.players[1].setTravel(0);//Sets travel to zero so they do not move during punch
                worl.players[1].setState(idle+64);
            }
        }
        if (Main.colonKey) {// Block
            int state=worl.players[1].getState();
            int idle=worl.players[1].color;
            if (state < 16 || state > 255){
                worl.players[1].setTravel(0);//Sets travel to zero so they do not move during block
                worl.players[1].setState(idle+8);
            }
        }
        if (Main.pKey){//kick
            int state=worl.players[1].getState();
            int idle=worl.players[1].color;
            if (state < 16 || state > 255){
                worl.players[1].setTravel(0);//Sets travel to zero so they do not move during kick
                worl.players[1].setState(idle+104);
            }
        }

    }
}