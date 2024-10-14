package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{

    public EnemyWeapon(Game game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public boolean performAttack(GameItem other){ //if the other thingy is on position, the other object receives the
        //attack, and then returns true otherwise its false.
        if (other.isOnPosition(this.pos)){
            other.receiveAttack(this);
            game.deleteObject(this);
            return true;
        }
        return false;
    }

    @Override // similar logic to ucmlaser and superlaser
    public boolean receiveAttack(UCMWeapon weapon) {
        if (this.pos.equals(weapon.pos)) {
            game.deleteObject(this);
            return true;
        }
        return false;
    }

}
