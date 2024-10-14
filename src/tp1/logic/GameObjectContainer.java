package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMShip;

public class GameObjectContainer {
	private final List<GameObject> objects;
	private final List<GameObject> deadObjects;

	public GameObjectContainer() { //initialize both list of alive objects and dead objects to avoid nullerrors
		objects = new ArrayList<>();
		deadObjects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		deadObjects.add(object);
	}

	public void automaticMoves() { //automatic moves for all the objects
		for (GameObject o: objects) {
			o.automaticMove();
		}
	}

	public void computerActions() { //computer actions for all the objects
		for (GameObject objects: objects) {
			if (objects != null)
				objects.computerAction();
		}
	}

	//gives points for the aliens that are killed/dead
	public void givePoints(UCMShip player) {
		for (GameObject objects: objects) {
			if (objects != null){
				if (!objects.isAlive() && !objects.hasGivenPoints()) {
					player.setPoints(player.getPoints() + objects.getPoints());
					objects.setHasGivenPoints(true);
				}
			}

		}
	}

	//logic of the 2nd list of deadobjects, put the objects from the 1st into the deadobjects list
	public void deleteDeadObjects() {
		for (GameObject d: deadObjects) {
			if (d != null)
				this.objects.remove(d);
		}
		this.deadObjects.clear(); //clears the list every cycle
	}

	public void checkCollision(){ //checks all the collissions between objects (laser needs to be removeed still)
		for (int i=0; i < objects.size(); i++) {
			for (int j=0; j < objects.size(); j++) {
				if (i == j) continue;
				GameObject object1, object2;
				object1 = objects.get(i);
				object2 = objects.get(j);

				if (object1 != null && object2 != null){
					if (object1 == object2) continue;
					if (object1.isOnPosition(object2.getPos())) {
						object1.performAttack(object2);
						object1.giveShockwave();
					}
				}
			}
		}
	}

	//check for kamikaze explosion and deal dmg
	public void checkExplosion(){
		for (GameObject objects: objects) {
			objects.kamikaze();
		}
	}


	public List<GameObject> getObjects() {
		return objects;
	}


}
