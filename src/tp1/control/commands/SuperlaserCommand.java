package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class SuperlaserCommand extends NoParamsCommand{
    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        try {
            game.shootSuperLaser();
        } catch (LaserInFlightException | NotEnoughPointsException e) {
            throw new CommandExecuteException("Command execute exception: ", e);
        }
        return true;
    }
    @Override
    protected String getName() {
        return Messages.COMMAND_SUPERLASER_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SUPERLASER_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SUPERLASER_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SUPERLASER_HELP;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        return super.parse(commandWords);
    }

    @Override
    protected boolean matchCommandName(String name) {
        return super.matchCommandName(name);
    }
}
