package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ListCommand extends NoParamsCommand {

    @Override
    protected String getName() {
        return Messages.COMMAND_LIST_NAME;
    }

    @Override
    protected String getShortcut() {
        return Messages.COMMAND_LIST_SHORTCUT;
    }

    @Override
    protected String getDetails() {
        return Messages.COMMAND_LIST_DETAILS;
    }

    @Override
    protected String getHelp() {
        return Messages.COMMAND_LIST_HELP;
    }

    @Override
    public boolean execute(GameModel game) throws CommandExecuteException {
        System.out.printf((Messages.UCM_DESCRIPTION) + "%n",Messages.UCMSHIP_DESCRIPTION,1,3);
        System.out.printf((Messages.ALIEN_DESCRIPTION) + "%n",Messages.REGULAR_ALIEN_DESCRIPTION,5,0,2);
        System.out.printf((Messages.ALIEN_DESCRIPTION) + "%n",Messages.DESTROYER_ALIEN_DESCRIPTION,10,1,1);
        System.out.printf((Messages.ALIEN_DESCRIPTION) + "%n",Messages.UFO_DESCRIPTION,25,0,1);
        System.out.printf((Messages.ALIEN_DESCRIPTION) + "%n",Messages.EXPLOSIVE_ALIEN_DESCRIPTION,12,0,2);
        System.out.println("\n");
        return false;
    }
}
