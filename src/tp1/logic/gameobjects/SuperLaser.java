package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class SuperLaser extends UCMLaser {
    public SuperLaser(Game game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    protected String getSymbol() {
        return "ǁǁ";
    }

    @Override
    protected int getDamage() {
        return 2;
    }

}
