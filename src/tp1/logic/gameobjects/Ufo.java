package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * This class manages the UFO. <br>
 * Contains the attributes and the movement of the UFO.
 */

public class Ufo extends EnemyShip {
	public Ufo(Game game, Position pos, int life) {
		super(game, pos, life);
		this.points = 25;
	}

	@Override
	protected String getSymbol() {
		return Messages.UFO_SYMBOL +"[" + "0" + life + "]";
	}

	@Override
	protected int getDamage() {
		return 0;
	}

	@Override
	protected int getArmour() {
		return 0;
	}

	public void onDelete() {

	}

	@Override
	public void computerAction() {
		if (!isValidPosition(getPos())){
			game.deleteObject(this);
		}
	}

	@Override
	public void automaticMove(){
		pos = pos.move(Move.LEFT);
	}

	public boolean isValidPosition(Position pos) {
		return pos.col >= 0 && pos.col < Game.DIM_X;
	}
	@Override
	public void giveShockwave(){
		game.getPlayer().setShockWave(true);
	}

}
