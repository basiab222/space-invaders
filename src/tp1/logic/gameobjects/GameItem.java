package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
	
	
	boolean performAttack(GameItem other);
	
	boolean receiveAttack(EnemyWeapon weapon);
	boolean receiveAttack(UCMWeapon weapon);

	boolean isAlive();
	boolean isOnPosition(Position pos);

}
