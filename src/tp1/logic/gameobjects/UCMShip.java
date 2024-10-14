package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of the UCMShip that contains all the attributes and methods.
 */
public class UCMShip extends Ship{
    private UCMLaser laser;
    private SuperLaser superLaser;
    private boolean hasShockWave;
    private final boolean laserEnabled;

    private int points;

    public UCMShip(Game game, Position position, UCMLaser laser) {
        super(game, position, 3);
        this.dir = Move.NONE;
        this.laser = laser;
        this.laserEnabled = false;
        hasShockWave = false; //false in real case
        this.points = 0;
    }

    public static String allowedMoves(String separator) {
        String[] moves = {"left", "right", "lleft", "rright"};
        StringBuilder result = new StringBuilder("Allowed UCMShip moves: <");

        for (int i = 0; i < moves.length; i++) {
            result.append(moves[i]);
            if (i < moves.length - 1) {
                result.append(separator);
            }
        }

        result.append(">");
        return result.toString();
    }

    @Override
    protected String getSymbol() {
        if (!game.aliensWin())
            return Messages.UCMSHIP_SYMBOL;
        else
            return Messages.UCMSHIP_DEAD_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 0;
    }

    @Override
    protected int getArmour() {
        return 0;
    }

    public boolean move(Move move) {
        if (onBorderLeft() && (move.equals(Move.LEFT) || move.equals(Move.LLEFT))) {
            return false;
        } else if (onBorderRight() && (move.equals(Move.RIGHT) || move.equals(Move.RRIGHT))) {
            return false;
        } else if (almostOnBorderLeft() && move.equals(Move.LLEFT)) {
            return false;
        } else if (almostOnBorderRight() && move.equals(Move.RRIGHT)) {
            return false;
        } else {
            super.pos = pos.move(move);
            return true;
        }
    }

    private boolean almostOnBorderLeft(){
        return super.pos.col == 1;
    }

    private boolean almostOnBorderRight(){
        return super.pos.col == Game.DIM_X - 2;
    }


    private boolean onBorderLeft(){
        return super.pos.col == 0;
    }
    private boolean onBorderRight(){
        return super.pos.col == Game.DIM_X - 1;
    }

    public boolean shootLaser() {
        // Check if the ship can shoot a new laser
        if (laser == null || laser.getLife() == 0 || !laser.isValidPosition(laser.getPos())) {
            if (laser != null && laser.getLife() == 0) {
                game.getContainer().remove(laser); // Remove the existing laser with life == 0
            }
            laser = new UCMLaser(game, pos, 1);
            game.getContainer().add(laser); // Add the new laser to the container
            return true;
        }
        return false;
    }

    public boolean shootSuperLaser() {
        if (this.getPoints() < 5){
            return false;
        }
        else if(superLaser == null || superLaser.getLife() == 0) {
            if(superLaser != null && superLaser.getLife() == 0) {
                game.getContainer().remove(superLaser);
            }
            superLaser = new SuperLaser(game, pos, 1);
            game.getContainer().add(superLaser);
            this.setPoints(this.getPoints() - 5);
            return true;
        }
        return false;
    }


    @Override
    public boolean receiveAttack(EnemyWeapon weapon) {
        if(!this.pos.equals(weapon.pos)) return false;
        this.dealDamage(weapon);
        return true;
    }


    public boolean hasShockWave() {
        return hasShockWave;
    }

    public void setShockWave(boolean hasShockWave){ this.hasShockWave = hasShockWave; }

    public int getPoints() {
        //return points;
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public GameObject getLaser() {
        return laser;
    }

    public void useShockwave() {
        for (GameObject object : game.getContainer().getObjects()) {
            if (object.isAlive() && this.hasShockWave) {
                // Assuming that the shockwave affects all active objects, call performAttack on each one
                object.receiveShockwaveDamage(); // You may need to define shockwaveDamage
            }
        }
        this.hasShockWave = false;
    }


}