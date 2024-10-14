package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AlienManager {

    private final Game game;
    private Move dir;
    private boolean shouldDescend;
    private Ufo activeUfo;

    public AlienManager(Game game) {
        this.game = game;
        dir = Move.LEFT; //direction of the list goes to left by default
    }

    //Initialize all the regularAliens, all the destroyerAliens into a subContainer
    public GameObjectContainer initialize(InitialConfiguration initialConfiguration) throws InitializationException {
        GameObjectContainer container = new GameObjectContainer();
        initializeRegularAliens(container, initialConfiguration);
        initializeDestroyerAliens(container, initialConfiguration);

        return container;
    }

    private void initializeRegularAliens(GameObjectContainer container, InitialConfiguration initialConfiguration) throws InitializationException {
        Level level = this.game.getLevel();
        if (initialConfiguration == null || initialConfiguration == InitialConfiguration.NONE) {
            for (int row = 0, i = 0; row < level.numRowsRegularAliens; row++) { //logic for creating the dAliens
                for (int col = 0; col < level.getNumAliensPerRow(); col++, i++) { //position them so they are centered,
                    int centering = (Game.DIM_X / 2) - (level.getNumAliensPerRow() / 2);
                    container.add(new RegularAlien(
                            this.game,
                            new Position(col + centering, row + 1),
                            this
                    ));
                }
            }
        } else { // Case for initialconfigurations
            initializeFromConfiguration(container, initialConfiguration);
        }
    }

    private void initializeDestroyerAliens(GameObjectContainer container, InitialConfiguration initialConfiguration) throws InitializationException {
        Level level = this.game.getLevel();

        if (initialConfiguration == null || initialConfiguration == InitialConfiguration.NONE) { //if none initialconf
            for (int i = 0; i < level.numDestroyerAliens; i++) {
                int centering = (Game.DIM_X / 2) - (level.getNumAliensPerRow() / 2);
                int differential = centering + (level.getNumAliensPerRow() / level.numDestroyerAliens) - 1;

                container.add(new DestroyerAlien(
                        this.game,
                        new Position(i + differential, level.numRowsRegularAliens + 1),
                        this
                ));
            }
        } else { // Case for initialconfigurations not null or none
            initializeFromConfiguration(container, initialConfiguration);
        }
    }

    private void initializeFromConfiguration(GameObjectContainer container, InitialConfiguration initialConfiguration) throws InitializationException {

        for (String description : initialConfiguration.getShipDescription()) {
            String[] words = description.split(" ");

            // Check if there are three words in the description
            if (words.length != 3) {
                throw new InitializationException(Messages.INCORRECT_ENTRY.formatted(description));
            }

            boolean shipCondition = ShipFactory.isValidShipType(words[0]);
            boolean positionCondition = false;

            try {
                int row = Integer.parseInt(words[1]);
                int col = Integer.parseInt(words[2]);
                positionCondition = isValidPosition(row, col);
            } catch (NumberFormatException e) {
                throw new InitializationException(Messages.INVALID_POSITION.formatted(words[1], words[2]));
            }

            if (shipCondition) {
                if (positionCondition) {
                    container.add(ShipFactory.spawnAlienShip(words[0], game,
                            new Position(Integer.parseInt(words[1]), Integer.parseInt(words[2])), this));

                } else {
                    throw new InitializationException(Messages.INVALID_POSITION.formatted(words[1], words[2]));
                }
            } else {
                throw new InitializationException(Messages.UNKNOWN_SHIP.formatted(words[0]));
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < Game.DIM_X && col >= 0 && col < Game.DIM_Y;
    }

    public void checkOnBorder() { //check if any alien is on the border
        for (GameObject gameObject : game.getContainer().getObjects()) {
            if ((Objects.equals(gameObject.getMessage(), Messages.REGULAR_ALIEN_SYMBOL)
                    || Objects.equals(gameObject.getMessage(), Messages.DESTROYER_ALIEN_SYMBOL)) ||
                    Objects.equals(gameObject.getMessage(), Messages.EXPLOSIVE_ALIEN_SYMBOL)
                            && gameObject.isAlive()) {
                if (gameObject.isAlive()
                        && (gameObject.getPos().row + 1 == Game.DIM_Y || gameObject.getPos().row == 0
                        || gameObject.getPos().col + 1 == Game.DIM_X || gameObject.getPos().col == 0)) {

                    break; // No need to check once one alien reaches the border
                }
            }
        }
    }

    public boolean onBorder() { //check if any alien is on the border
        boolean onBorder = false;
        for (GameObject gameObject : game.getContainer().getObjects()) {
            if ((Objects.equals(gameObject.getMessage(), Messages.REGULAR_ALIEN_SYMBOL)
                    || Objects.equals(gameObject.getMessage(), Messages.DESTROYER_ALIEN_SYMBOL)) ||
                    Objects.equals(gameObject.getMessage(), Messages.EXPLOSIVE_ALIEN_SYMBOL)
                            && gameObject.isAlive()) {
                if (gameObject.getPos().col == 0 || gameObject.getPos().col == 8 || gameObject.getPos().row == 8) {
                    onBorder = true;
                }
            }
        }
        return onBorder;
    }

    public void moveAlienList() {
        int cycle = game.getCycle();
        int numCyclesToMoveOneCell = game.getLevel().getNumCyclesToMoveOneCell();

        tryShooting(); //try to shoot the bomb
        checkUfo(); //check if ufo should be created

        if (cycle % numCyclesToMoveOneCell == 0) {
            checkOnBorder(); //checkeverything is inside the border

            if (shouldDescend) {
                moveAllDown();
                shouldDescend = false; // flag it so that it doesnt keep moving down
            } else {
                for (GameObject gameObject : game.getContainer().getObjects()) {
                    if ((Objects.equals(gameObject.getMessage(), Messages.REGULAR_ALIEN_SYMBOL)
                            || Objects.equals(gameObject.getMessage(), Messages.DESTROYER_ALIEN_SYMBOL)) ||
                            Objects.equals(gameObject.getMessage(), Messages.EXPLOSIVE_ALIEN_SYMBOL)
                                    && gameObject.isAlive()) {
                        // Move in the current direction
                        gameObject.setPos(gameObject.getPos().move(dir));
                    }
                }

                // switch dir
                if (onBorder()) {
                    shouldDescend = true;
                    if (dir == Move.LEFT) {
                        dir = Move.RIGHT; // move to right after descending
                    } else {
                        dir = Move.LEFT;
                    }

                }
            }
        }
    }

    public void moveAllDown() { //Logic so that all aliens move down
        for (GameObject gameObject : game.getContainer().getObjects()) {
            if ((Objects.equals(gameObject.getMessage(), Messages.REGULAR_ALIEN_SYMBOL)
                    || Objects.equals(gameObject.getMessage(), Messages.DESTROYER_ALIEN_SYMBOL)) ||
                    Objects.equals(gameObject.getMessage(), Messages.EXPLOSIVE_ALIEN_SYMBOL)) {
                if (gameObject.isAlive()) {
                    gameObject.setPos(gameObject.getPos().move(Move.DOWN));
                }
            }
        }
    }
    // ALIEN BOMB GO BOOM LOGIC

    public void tryShooting() { //Logic so that the aliens can shoot, for each destroyerAlien
        List<DestroyerAlien> aliensToShoot = new ArrayList<>();
        for (GameObject gameObject : game.getContainer().getObjects()) {
            if ((Objects.equals(gameObject.getMessage(), Messages.DESTROYER_ALIEN_SYMBOL))) {
                DestroyerAlien alien = (DestroyerAlien) gameObject;
                if (game.shootChance()) {
                    aliensToShoot.add(alien);
                }
            }
        }
        for (DestroyerAlien alien : aliensToShoot) { //use some auxiliar aliens to shoot the bomb
            alien.shootBomb();
            //if the bomb is not in a valid position, it should be deleted, or if its dead.
            if (!alien.getBomb().isValidPosition(alien.getBomb().getPos()) && !alien.getBomb().isAlive()) {
                alien.setBomb(null);
            }
        }
        aliensToShoot.clear();
    }

    public void checkUfo() { //method to check if ufo should be created or not.
        //if it exits the bounds, it should be deleted and a will be tried to be created.
        if ((activeUfo == null || !activeUfo.isAlive())) {
            createUfo();
        } else if (!activeUfo.isValidPosition(activeUfo.getPos()) && game.getUfoFrequency()) {
            game.deleteObject(activeUfo);
            createUfo();
        }
    }

    private void createUfo() { //if the probability allows it, create a new ufo.
        if (game.getUfoFrequency()) {
            Ufo ufo = new Ufo(game, new Position(8, 0), 1);
            game.getContainer().add(ufo);
            activeUfo = ufo;
        }
    }

    public boolean landed() { //if any alien has landed, true is returned.
        for (GameObject objects : this.game.getContainer().getObjects()) {
            if (objects.hasLanded())
                return true;
        }
        return false;
    }


}