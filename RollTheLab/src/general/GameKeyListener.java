package general;

public class GameKeyListener {
	public static volatile boolean wPressed = false;
	public static boolean isWPressed() {
        synchronized (GameKeyListener.class) {
            return wPressed;
        }
    }
}
