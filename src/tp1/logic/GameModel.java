package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.*;

public interface GameModel {
    boolean move(Move move) throws NotAllowedMoveException, OffWorldException;
    boolean shootLaser() throws LaserInFlightException;
    public boolean shootSuperLaser() throws LaserInFlightException, NotEnoughPointsException;
    boolean shockWave() throws NoShockWaveException;
    void reset() throws InitializationException;
    void reset(InitialConfiguration initialConfiguration) throws InitializationException, CommandExecuteException;
    boolean isFinished();
    void exit();

    void update();
}
