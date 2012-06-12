package edu.alexsalo.com;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 800;
	public static int HEIGTH = 600;
	public static String NAME = "TableTennis";
	private Ball ball;
	private TopRacket rup;
	private DownRacket rdo;
	private Image rupi;
	private Image rdoi;

	public void start() {
		new Thread(this, "GameThread").start();
	}

	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGTH));
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		game.start();		
	}
	
	public void init(){
		ball = new Ball(getBallImage("ball.png"));
		rup = new TopRacket(ball);
		rdo = new DownRacket(ball);
		ball.addRackets(rup, rdo);
		rupi = getBallImage("man.png");
		rdoi = getBallImage("man2.png");
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		ball.draw(g, ball.x, ball.y);
		g.drawImage(rupi, rup.x, 0, null);
		g.drawImage(rdoi, rdo.x, HEIGTH - 10, null);
		g.dispose();
		bs.show();

	}

	@Override
	public void run() {
		init();
		while (true)
			render();
	}

	public Image getBallImage(String path) {
		BufferedImage sourceImage = null;
		try {
			URL url = this.getClass().getClassLoader().getResource(path);
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
	}

}
