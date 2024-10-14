package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
    private Move dir;

    public AlienShip(Game game, Position pos, int life) {
        super(game, pos, life);
        dir = Move.LEFT;
    }

    public AlienShip() {
        super();
    }

    protected abstract AlienShip copy(Game game, Position pos, AlienManager am);

    private void move() {
        if (dir == Move.LEFT) {
            pos = pos.move(Move.LEFT);
        } else if (dir == Move.RIGHT) {
            pos = pos.move(Move.RIGHT);
        }
    }

    @Override
    public String toString() {
        return " " + this.getSymbol() + "[" + "0" + this.getLife() + "]";
    }

    @Override
    public boolean hasLanded() {
        return this.getPos().row == Game.DIM_Y - 1;
    }

    @Override
    public String getMessage() {
        return getSymbol();
    }

    @Override
    public boolean addCounter(){
        return true;
    }
}

