/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author Patzo
 */
import java.util.*;

public class Refresh {
	   Timer timer;
	   Layers panels;
	    public Refresh(int fps, Layers panels) {
	    	this.panels = panels;
	    	timer = new Timer();
	        timer.schedule(new RemindTask(),
	        		0,        //initial delay
	                1*fps);  //subsequent rate
	        
	    } 
	    class RemindTask extends TimerTask {	       
	        public void run() {
                    try{
	        	panels.SendOffsets();
                        panels.mapCoords();
                        panels.checkWhetherAlive();
                    }catch(Exception e){e.printStackTrace();}
	        	
	        }
	    }
}
