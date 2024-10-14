package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

    @Override
    public Command parse(String[] commandWords) throws CommandParseException { //cases for the possible no-param inputs
        if (matchCommandName(commandWords[0])) {
            if (commandWords.length == 1) {
                return switch (commandWords[0]) {
                    case "help", "h" -> new HelpCommand();
                    case "exit", "e" -> new ExitCommand();
                    case "none", "n", "" -> new NoneCommand();
                    case "reset", "r" -> new ResetCommand();
                    case "shoot", "s" -> new ShootCommand();
                    case "shockwave", "w" -> new ShockwaveCommand();
                    case "list", "l" -> new ListCommand();
                    case "superlaser", "sl" -> new SuperlaserCommand();
                    default -> null;
                };
            } else
                throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        } else
            throw new CommandParseException(Messages.UNKNOWN_COMMAND);
    }
}
