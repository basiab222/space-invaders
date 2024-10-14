package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {
	protected Position pos;
	protected int life;
	protected Game game;
	protected Move dir;
	public int points;
	public String Message;

	private boolean hasGivenPoints;
	public GameObject(Game game, Position pos, int life) {
		this.pos = pos;
		this.game = game;
		this.life = life;
		this.hasGivenPoints = false;
	}

	public int getPoints() {
		return 0;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public GameObject() {
    }

    public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	@Override
	public boolean isAlive() {
		return this.life > 0;
	}

	public int getLife() {
		return this.life;
	}

	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();
	public void computerAction() {}

	@Override
	public boolean performAttack(GameItem other) {return false;}

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {return false;}

	@Override
	public String toString() {
		return this.getSymbol();
	}

	public void setLife(int life) {
		this.life = life;
	}

	@Override
	public boolean isOnPosition(Position pos) {
		return this.pos.equals(pos);
	}

	public boolean hasGivenPoints() {
		return hasGivenPoints;
	}

	public void setHasGivenPoints(boolean b) {
		this.hasGivenPoints = b;
	}

	public void automaticMove() {
	}

	public boolean hasLanded(){
		return false;
	}

	public String getMessage(){
		return Message;
	}

	public void receiveShockwaveDamage(){
	}

	public void kamikaze(){}

	public void giveShockwave(){}

	public boolean addCounter(){
		return false;
	}
}
