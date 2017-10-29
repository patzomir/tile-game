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
import java.util.*;
import java.rmi.server.*;
import java.rmi.*;
public class Server extends UnicastRemoteObject implements interfaces.ServerInterface{
    private static Deque<ServeOneHero> heroes;
    private static Deque<Arrow> arrows;
    private int moving_arrows;
    private int hero_number;
    
    public Server(String name) throws RemoteException {
        super();
        hero_number = 0;
        heroes = new ArrayDeque<ServeOneHero>();
    	arrows = new ArrayDeque<Arrow>();
        try {
               Naming.rebind(name, this);
        }
        catch(Exception e){e.printStackTrace();}
    }

    public String createHero(){
        try{
            hero_number++;
            heroes.push(new ServeOneHero(this, "" + hero_number));
            return "" + hero_number;
        }catch(Exception e){e.printStackTrace();}
        return "";
    }
    synchronized public String ReturnLayout(){
    	String temp="";
    	for (ServeOneHero one : heroes)
    		temp+= one.GetX() + ";" + one.GetY() + ";" + one.GetAngle() + ";";
    	return temp;
    }
    synchronized public void RemoveFromDeque(ServeOneHero s){
    	s.kill();
    	heroes.remove(s);
    }
    synchronized public void AddArrowToDeque(Arrow a){
    	arrows.push(a);
    }
    synchronized public void RemoveArrowFromDeque(Arrow a){
    	arrows.remove(a);
    }
    synchronized public String ReturnArrowLayout(){
    	String temp="ARROWS;";
    	if (arrows.size()==0) return temp;
    	for (Arrow arrow : arrows)
    		temp+= arrow.GetX() + ";" + arrow.GetY() + ";" + arrow.GetAngle() + ";";
    	return temp;
    }
    synchronized boolean NoCollision(int x, int y){
    	while(moving_arrows>20){
    		try{wait();}
    		catch (InterruptedException e){}    		
    	}
    	moving_arrows++;
    	int map[][]=new Map().getMap();
    	if (map[(y+5)/40][(x+5)/40]==1 || map[(y+20)/40][(x+20)/40]==1 )
    		return false;
    	for (ServeOneHero hero: heroes)
    		if (!(hero.GetX() > x || (hero.GetX()+30) < x || hero.GetY() > y || hero.GetY()+30 < y ))
    		{
    			//hero.WriteToOut("DEAD");
    			RemoveFromDeque(hero);
    			return false;
    		}
    	return true;
    }
    synchronized void ReleaseMovingArrow(){
    	moving_arrows--;
    	notifyAll();
    }
}

