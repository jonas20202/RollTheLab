package general;

public class GameKeyListener {
	public static volatile boolean isTurnRightPressed = false;
    public static volatile boolean isTurnLeftPressed = false;
	public static boolean isTurnRightPressed() {
        synchronized (GameKeyListener.class) {
            return isTurnRightPressed;
        }
    }
    public static boolean isTurnLeftPressed() {
        synchronized (GameKeyListener.class) {
            return isTurnLeftPressed;
        }
    }
}
