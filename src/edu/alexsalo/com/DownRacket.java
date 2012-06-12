package edu.alexsalo.com;

public class DownRacket implements Runnable {
	private Ball ball;
	public int x = 0;
	public int y = 0;
	public DownRacket(Ball ball) {
		this.ball = ball;
		new Thread(this, "DownRacket").start();
	}
	@Override
	public void run() {
		while(true)
			ball.bing(this);
	}

}
