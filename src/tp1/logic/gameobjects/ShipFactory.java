package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

import java.util.Arrays;
import java.util.List;

public class ShipFactory {

    private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
            new RegularAlien(),
            new DestroyerAlien(),
            new ExplosiveAlien()
    );

    public static AlienShip spawnAlienShip(String input, Game game, Position pos, AlienManager am) {
        for (AlienShip ship : AVAILABLE_ALIEN_SHIPS) {
            if (ship.getSymbol().equals(input)) {
                return ship.copy(game, pos, am);
            }
        }
        return null;
    }

    public static boolean isValidShipType(String word) {
        return AVAILABLE_ALIEN_SHIPS.stream().anyMatch(ship -> ship.getSymbol().equals(word));
    }

}
