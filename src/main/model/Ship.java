package model;

public class Ship {
    private int width;
    private int sqHit;
    private int sqRemain;
    private boolean isAlive;
    private int posX;
    private int posY;


    //EFFECTS: create Ship with width w at coordinates x, y
    public Ship(int w, int x, int y) { // test to ensure sqHit+sqRemain always = width
        this.width = w;
        this.sqHit = 0;
        this.sqRemain = w;
        this.isAlive = true;
        this.posX = x;
        this.posY = y;

        //checkIsAlive();
    }

    public void checkIsAlive() { // this should be called in the turn system
        if (sqHit == this.width) {
            isAlive = false;
        }
    }
}
