package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.OffWorldException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

import static tp1.logic.gameobjects.UCMShip.allowedMoves;

public class MoveCommand extends Command {
	private Move move;

	public MoveCommand() {}
	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.move(move);
			return true;
		} catch (NotAllowedMoveException e) {
			throw new CommandExecuteException("Command Execution Error: ", e);
		} catch (OffWorldException e) {
			throw new CommandExecuteException("Command Execution Error: ", e);
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		if (commandWords.length != 2) throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		if (!matchCommandName(commandWords[0])) throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);

		Move move;
		try {
			switch (Move.valueOf(commandWords[1].toUpperCase())) {
				case LEFT -> move = Move.LEFT;
				case RIGHT -> move = Move.RIGHT;
				case RRIGHT -> move = Move.RRIGHT;
				case LLEFT -> move = Move.LLEFT;
				default -> {
					String availableMoves = allowedMoves("|");
					throw new IllegalArgumentException(Messages.DIRECTION_ERROR + availableMoves);
				}
			}
		} catch (IllegalArgumentException e) {
			throw new CommandParseException(Messages.DIRECTION_ERROR + commandWords[1] + "\n" + allowedMoves("|") + "\n");
		}
		return new MoveCommand(move);
	}


}