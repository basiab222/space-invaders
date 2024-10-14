package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends RegularAlien {
    public ExplosiveAlien(Game game, Position pos, AlienManager am) {
        super(game, pos, am);
        this.points = 12;
    }

    public ExplosiveAlien() {
    }

    @Override
    protected AlienShip copy(Game game, Position pos, AlienManager am) {
        return new ExplosiveAlien(game, pos, am);
    }

    @Override
    protected String getSymbol() {
        return Messages.EXPLOSIVE_ALIEN_SYMBOL;
    }

    public boolean isDead() {
        return this.getLife() == 0;
    }

    @Override
    public void kamikaze() {
        if (this.isDead()) {
            int range = 1; // Adjust the range as needed
            int centerX = this.getPos().col;
            int centerY = this.getPos().row;
                for (int x = centerX - range; x <= centerX + range; x++) {
                    for (int y = centerY - range; y <= centerY + range; y++) {
                        // Skip the explosive alien itself
                        if (x == centerX && y == centerY) {
                            continue;
                        }

                        Position currentPos = new Position(x, y);
                        for (GameObject go : game.getContainer().getObjects()) {
                            if (go.getPos().equals(currentPos)) {
                                go.setLife(go.getLife() - 1);
                            }
                        }
                    }
                }
        }

    }
}
