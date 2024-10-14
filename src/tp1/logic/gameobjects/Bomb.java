package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
    public Bomb(Game game, Position pos, int life) {
        super(game, pos, life);
    }
    @Override
    protected String getSymbol() {
        return Messages.BOMB_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 1;
    }

    @Override
    protected int getArmour() {
        return life;
    }

    @Override
    public void computerAction() {
        if (isValidPosition(this.pos) && this.isAlive()) {
            pos = pos.move(Move.DOWN);
        }
        else {
            life = 0;
        }
    }

    public boolean isValidPosition(Position position) {
        return position.row >= 0 && position.row < Game.DIM_Y;
    }

}