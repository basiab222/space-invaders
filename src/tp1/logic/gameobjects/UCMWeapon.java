package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
    public UCMWeapon(Game game, Position pos, int life) {
        super(game, pos, life);
    }
    @Override
    public boolean performAttack(GameItem other) {
        other.receiveAttack(this);
        return true;
    }

    @Override
    public boolean receiveAttack(EnemyWeapon weapon) {
        this.life -= weapon.getDamage();
        return true;
    }

}
