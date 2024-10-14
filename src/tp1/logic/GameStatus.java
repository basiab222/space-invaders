package tp1.logic;

public interface GameStatus {

	String positionToString(int x, int y);
	String infoToString();
	String stateToString();
	
	boolean playerWin();
	boolean aliensWin();

	int getCycle();
	int getRemainingAliens();

}
