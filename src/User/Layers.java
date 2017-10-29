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
import interfaces.HeroInterface;
import interfaces.ServerInterface;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.rmi.*;
import Server.ServeOneHero;

import java.io.*;
public class Layers extends JLayeredPane implements KeyListener {
	boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed;
	static int x,y;
	static int j=2;
	private int offsetX, offsetY;
	Refresh send_data;
	private Deque<Hero> heroes;
	private Deque<Arrow> arrows;
        private ServerInterface remoteServer;
        private HeroInterface remoteHero;
        private boolean alive;

	public Layers(ServerInterface remoteServer, HeroInterface remoteHero){
		GameStart draw = new GameStart();
		draw.setBounds(0, 0, 640, 480);
        this.add(draw, new Integer(1));
        setFocusable(true);
        addKeyListener(this);
        this.alive = true;

        x=40; y=40;
        offsetX=0;
        offsetY=0;
        this.remoteServer=remoteServer;
        this.remoteHero=remoteHero;
        send_data=new Refresh(30, this);
        heroes = new ArrayDeque<Hero>();
        arrows = new ArrayDeque<Arrow>();
	}
	public void keyTyped(KeyEvent ke) {
    }
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_UP: isUpPressed = true; this.Listen(); break;
            case KeyEvent.VK_DOWN: isDownPressed = true; this.Listen(); break;
            case KeyEvent.VK_LEFT: isLeftPressed = true; this.Listen(); break;
            case KeyEvent.VK_RIGHT: isRightPressed = true; this.Listen(); break;
            case KeyEvent.VK_SPACE: isSpacePressed = true; this.Listen(); break;
        }
    }

    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()) {
	        case KeyEvent.VK_UP: isUpPressed = false; this.Listen(); break;
	        case KeyEvent.VK_DOWN: isDownPressed = false; this.Listen(); break;
	        case KeyEvent.VK_LEFT: isLeftPressed = false; this.Listen(); break;
	        case KeyEvent.VK_RIGHT: isRightPressed = false; this.Listen(); break;
        }
    }
	/*private String[] commands = {
            "UP",
            "DOWN",
            "LEFT",
            "RIGHT",
            "UP_LEFT"
        };*/
	/*private ActionListener panelAction = new ActionListener()
    {   
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            String command = (String) ae.getActionCommand();
            if (command.equals("UP"))
                offsetY -= 1;             
            else if (command.equals(commands[1]))
            	offsetY += 1;
            else if (command.equals(commands[2]))
            	offsetX -= 1;
            else if (command.equals(commands[3]))
            	offsetX += 1;
        }
    };*/
    public void Listen(){
    	/*for (int i = 0; i < commands.length; i++)       
            registerKeyboardAction(panelAction,
                            commands[i],
                            KeyStroke.getKeyStroke(commands[i]),
                            JComponent.WHEN_IN_FOCUSED_WINDOW);*/
    		if(isUpPressed && isLeftPressed){
    			offsetY -= 1; offsetX -= 1;
    		}else if (isDownPressed && isLeftPressed){
    			offsetY += 1; offsetX -= 1;
    		}else if(isUpPressed && isRightPressed){
    			offsetY -= 1; offsetX += 1;
    		}else if (isDownPressed && isRightPressed){
    			offsetY += 1; offsetX += 1;
    		}else if (isUpPressed)
    			offsetY -= 1;
    		else if (isDownPressed)
    			offsetY += 1;
    		else if (isLeftPressed)
    			offsetX -= 1;
    		else if (isRightPressed)
    			offsetX += 1;	
    }
    public void SendOffsets() throws RemoteException{
        remoteHero.moveHero(offsetX, offsetY);
    	if (isSpacePressed) remoteHero.shootArrow();
    	offsetX=0;
    	offsetY=0;
    	isSpacePressed=false;
    }
    public void PositionHeroes(String s){
    	int i=0;
    	String temp[] = s.split(";");
    	if (!temp[0].equals("ARROWS")){
	    	while (temp.length/3 != heroes.size())
	    	{
	    		if (temp.length/3 > heroes.size())
	    		{
	    			heroes.push(new Hero());      
	    			this.add(heroes.peekFirst(), new Integer(2));
	    		}else{ 
	    			this.remove(heroes.getFirst());
	    			this.repaint();
	    			heroes.pop();
	    		}
	    	}
	    	for (Hero hero : heroes)
	    	{
	    		try{
	    			hero.render(Integer.parseInt(temp[i]),Integer.parseInt(temp[i+1]),Integer.parseInt(temp[i+2]));
	    			i +=3;
	    		}catch(NumberFormatException e){}
	    	}
    	}else{
    		i+=1;
    		while ((temp.length-1)/3 != arrows.size())
	    	{
	    		if ((temp.length-1)/3 > arrows.size())
	    		{
	    			System.out.println(s);
	    			arrows.push(new Arrow());      
	    			this.add(arrows.peekFirst(), new Integer(2));
	    		}else{ 
	    			this.remove(arrows.getFirst());
	    			this.repaint();
	    			arrows.pop();
	    		}
	    	}
	    	for (Arrow arrow : arrows)
	    	{
	    		try{
	    			arrow.render(Integer.parseInt(temp[i]),Integer.parseInt(temp[i+1]),Integer.parseInt(temp[i+2]));
	    			i +=3;
	    		}catch(NumberFormatException e){}
	    	}
    		
    	}   		
    }
    public void mapCoords(){
        try{
            PositionHeroes(remoteServer.ReturnLayout());
            PositionHeroes(remoteServer.ReturnArrowLayout());
        }catch(Exception e){e.printStackTrace();}
    }
    public void checkWhetherAlive(){
        try{
            alive = remoteHero.checkWhetherAlive();
        }catch(Exception e){e.printStackTrace();}
            
    }
    public void ReceiveCoordinates(){
    		while(alive){
    		}
    }
}
