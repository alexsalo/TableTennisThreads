package edu.alexsalo.com;

public class TopRacket implements Runnable {
	private Ball ball;
	public int x = 0;
	public int y = 0;
	
	public TopRacket(Ball ball) {
		this.ball = ball;
		new Thread(this, "TopRacket").start();
	}
	@Override
	public void run() {
		while(true)			
			ball.bong(this);			
	}

}
