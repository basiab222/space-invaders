package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.LaserInFlightException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ShootCommand extends NoParamsCommand {
    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        try {
            game.shootLaser();
        } catch (LaserInFlightException e) {
            throw new CommandExecuteException("Command execute exception: ", e);
        }
        return true;
    }

    @Override
    protected String getName() {
        return Messages.COMMAND_SHOOT_NAME;
    }

    @Override
    protected boolean matchCommandName(String name) {
        return super.matchCommandName(name);
    }

    @Override
    public String helpText() {
        return super.helpText();
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        return super.parse(commandWords);
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_SHOOT_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_SHOOT_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_SHOOT_HELP;
    }

}