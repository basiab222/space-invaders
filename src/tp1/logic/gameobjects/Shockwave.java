package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Shockwave extends UCMWeapon {
    public Shockwave(Game game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    protected String getSymbol() {
        return Messages.SHOCKWAVE_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 1;
    }

    @Override
    protected int getArmour() {
        return 0;
    }

}
