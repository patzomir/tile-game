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
import java.io.*;
import java.net.*;
import java.rmi.server.*;
import java.rmi.*;
public class ServeOneHero extends UnicastRemoteObject implements interfaces.HeroInterface {
	private int moveX, moveY;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int x, y, angle;
	private String temp[], input;
	private Server serv;
	private int map[][];
	private boolean alive;
        String s;

	public ServeOneHero(Server serv, String s)  throws IOException {
            super();
            this.s = s;
            try {
               Naming.rebind(s, this);
            }
            catch(Exception e){e.printStackTrace();}
            x=40;
	    y=120;
	    System.out.println("\t\t\tnew remote connection");
	    map = new Map().getMap();
	    this.serv=serv;
	    alive=true;
	}
        public boolean checkWhetherAlive(){
            return alive;
        }
        public void shootArrow(){
            serv.AddArrowToDeque(new Arrow(x,y,angle, serv));
        }
        public void removeHero() throws RemoteException, NotBoundException {
            serv.RemoveFromDeque(this);
        }
        public void moveHero(int offsetX, int offsetY){
            moveX=MoveByX(offsetX*3);
            moveY=MoveByY(offsetY*3);
            this.SetAngle();
        }
	  public int GetX(){ return x; }
	  public int GetY(){ return y; }
	  public int GetAngle(){ return angle; }
	  public void WriteToOut(String str) { out.println(str); }
	  public int MoveByX(int offsetX){
		  if (map[y/40][(x+offsetX)/40] == 0 &&
				  map[(y+30)/40][(x+offsetX)/40] == 0 &&
				  map[y/40][(x+offsetX+30)/40] == 0 && 
				  map[(y+30)/40][(x+offsetX+30)/40] == 0){
			  x+=offsetX;
			  return offsetX; 
		  }
		  return 0;
	  }
	  public int MoveByY(int offsetY){
		  if (map[(y+offsetY)/40][(x)/40] == 0 &&
				  map[(y+30+offsetY)/40][(x)/40] == 0 &&
				  map[(y+offsetY)/40][(x+30)/40] == 0 && 
				  map[(y+30+offsetY)/40][(x+30)/40] == 0){
			  y+=offsetY;
		  return offsetY; 
	  }
	  return 0;
	  }
	  public void SetAngle(){
		  if (moveX == 0 && moveY < 0 )
			  angle=0;
		  else if (moveX > 0 && moveY < 0 )
			  angle=45;
		  else if (moveX > 0 && moveY == 0 )
			  angle=90;
		  else if (moveX > 0 && moveY > 0 )
			  angle=135;
		  else if (moveX == 0 && moveY > 0 )
			  angle=180;
		  else if (moveX < 0 && moveY > 0 )
			  angle=225;
		  else if (moveX < 0 && moveY == 0 )
			  angle=270;
		  else if (moveX < 0 && moveY < 0 )
			  angle=315;
	  }
	  public void kill(){
              try{
                    Naming.unbind(s);
              }catch(Exception e){e.printStackTrace();}
            alive = false;
	  }
}
