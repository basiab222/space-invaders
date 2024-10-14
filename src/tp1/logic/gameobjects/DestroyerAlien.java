package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all the attributes and methods of the destroyer aliens. <br>
 * Also, this class extends the abstract class of Alien.
 */
public class DestroyerAlien extends AlienShip
{
    private Bomb bomb;
    private boolean cantShootBomb;

    AlienManager alienManager;
    public DestroyerAlien(Game game, Position pos, AlienManager alienManager) {
        super(game, pos, 1);
        this.bomb = null;
        this.cantShootBomb = false;
        this.points = 10;
        this.alienManager = alienManager;
    }

    public DestroyerAlien() {
        super();
    }

    @Override
    protected AlienShip copy(Game game, Position pos, AlienManager am) {
        return new DestroyerAlien(game, pos, am);
    }

    @Override
    protected String getSymbol() {
        return Messages.DESTROYER_ALIEN_SYMBOL;
    }
    @Override
    protected int getDamage() {
        return 0;
    }
    @Override
    protected int getArmour() {
        return 0;
    }

    public void shootBomb(){
        if (bomb != null) {
            return;
        }
        setCantShootBomb(true);
        Position position = new Position(this.pos.col, this.pos.row + 1);
        bomb = new Bomb(this.game, position, 1);
        game.getContainer().add(bomb);
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public void setCantShootBomb(boolean cantShootBomb) {
        this.cantShootBomb = cantShootBomb;
    }

}
