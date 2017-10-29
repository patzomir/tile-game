/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Patzo
 */
public class Arrow extends Thread {
	private int x,y, angle, speed;
	Server serv;
	public Arrow(int x, int y, int angle, Server serv){
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.serv=serv;
		speed=5;
		for (int i=0;i<(10*3)/speed;i++)
		{
			this.SetX();
			this.SetY();
		}
		start();
	}
	public void SetX(){
		switch (angle){
			case 0: break;
			case 45: x+=speed; break;
			case 90: x+=speed; break;
			case 135: x+=speed; break;
			case 180: break;
			case 225: x-=speed; break;
			case 270: x-=speed; break;
			case 315: x-=speed; break;
		}
	}
	public void SetY(){
		switch (angle){
			case 0: y-=speed; break;
			case 45: y-=speed; break;
			case 90: break;
			case 135: y+=speed; break;
			case 180: y+=speed; break;
			case 225: y+=speed; break;
			case 270: break;
			case 315: y-=speed; break;
		}
	}
	public int GetX(){ return x;}
	public int GetY(){ return y;}
	public int GetAngle(){ return angle;}
	public void run(){
		while (true){
			SetX();
			SetY();
			if (!serv.NoCollision(x, y))
				break;		
			try{
				sleep(30);
			} catch (InterruptedException e){}
			serv.ReleaseMovingArrow();
		}
		serv.ReleaseMovingArrow();
		serv.RemoveArrowFromDeque(this);
	}
}
