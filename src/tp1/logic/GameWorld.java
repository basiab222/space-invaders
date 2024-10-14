package tp1.logic;

import tp1.logic.gameobjects.GameObject;

public interface GameWorld {
    public void addObject(GameObject object);

    public GameObjectContainer getContainer();

    public void removeObject(GameObject object);

    public void update();

    public boolean shootChance();

    public boolean getUfoFrequency();
}
