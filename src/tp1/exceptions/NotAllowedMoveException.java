package tp1.exceptions;

import tp1.view.Messages;

public class NotAllowedMoveException extends GameModelException {
    public NotAllowedMoveException() {
        super(Messages.MOVEMENT_ERROR);
}
}
