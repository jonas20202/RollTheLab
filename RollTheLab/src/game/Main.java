package game;

import general.Vektor;

public class Main extends Thread{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.start();

	}
	@Override
	public void start(){
		super.start();
		GameFrame gameFrame = new GameFrame();
	}
}
