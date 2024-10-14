package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ExitCommand extends NoParamsCommand {
    @Override
    public boolean execute(GameModel game) {
        game.exit();
        return false;
    }

    @Override
    protected String getName() {
        return Messages.COMMAND_EXIT_NAME;
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
        return Messages.COMMAND_EXIT_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_EXIT_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_EXIT_HELP;
    }

}
