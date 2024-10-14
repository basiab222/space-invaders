package tp1.exceptions;

import tp1.view.Messages;

public class NoShockWaveException extends GameModelException {
    public NoShockWaveException() {
        super(Messages.SHOCKWAVE_ERROR); //MESSAGES.SHOCKWAVE_ERROR
    }
}
