package tp1.exceptions;

import tp1.view.Messages;

public class NotEnoughPointsException extends GameModelException  {
    public NotEnoughPointsException() {
        super(Messages.NOT_ENOUGH_POINTS_ERROR);
    }
}
