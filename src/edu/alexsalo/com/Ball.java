package edu.alexsalo.com;

import java.awt.Graphics;
import java.awt.Image;
import java.lang.Math;

public class Ball {
	private Image image;
	private TopRacket rup;
	private DownRacket rdo;
	public int x = 0;
	public int y = 0;
	private byte direction = 1;
	private byte racketmove = 1;
	boolean valueSet = false;
	
	public Ball(Image image){
		this.image = image;
	}
	
	public void addRackets(TopRacket rup, DownRacket rdo){
		this.rdo = rdo;
		this.rup = rup;
	}
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
	
		if (!valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		valueSet = false;
		while (x <= Game.WIDTH-10) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x++;
			r.x = x;
			y += direction;
			if (y == Game.HEIGTH-30)
				direction = -1;
			if (y == 20)
				direction = 1;
			System.out.println("X,Y: " + x + ","+y);
		}
		
		notify();
	}*/
	synchronized void bing(DownRacket r){
		if (!valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		valueSet = false;
		while (y <= Game.HEIGTH-30) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y++;			
			x += direction;
			r.x = x;
			if (x == Game.WIDTH-10)
				direction = -1;
			if (x == 0)
				direction = 1;
			System.out.println("X,Y: " + x + ","+y);
		}
		
		notify();
	}
	synchronized void bong(TopRacket r){
		if (valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		valueSet = true;
		while (y >30) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y--;			
			x += direction;
			try{
			racketmove = (byte) ((x-r.x)/Math.abs(x-r.x));
			}catch (Exception e){}			
			r.x += racketmove;
			if (x == Game.WIDTH-10)
				direction = -1;
			if (x == 0)
				direction = 1;
			System.out.println("X,Y: " + x + ","+y);
		}
		notify();
	}
	
}
