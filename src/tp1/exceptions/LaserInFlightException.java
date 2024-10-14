package tp1.exceptions;

import tp1.view.Messages;

public class LaserInFlightException extends GameModelException {
    public LaserInFlightException(String message) {
        super(Messages.LASER_ALREADY_SHOT);
    }
}
