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
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Hero extends JPanel{
	int heroLayer;
	int x, y;
	int angle;
	Image hero;
	public Hero(){
		setOpaque(false);
		try {
			hero = ImageIO.read(new File("Resources\\NormalArcherIdle.png"));

		} catch (IOException e){ System.out.println("EXCEPTION");}
		angle =0;
	}
	/*public void paintComponent(Graphics g){
		super.paintComponent(g);
        g.drawImage(hero,0,0,40,40,null);
	}*/
	public void paintComponent(Graphics gg)
	{
	    Graphics2D g = (Graphics2D) gg;
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    AffineTransform matrix = g.getTransform(); // Backup
	    g.rotate(Math.toRadians(angle),20,20);
	    /* Begin */
	    g.drawImage(hero,0,0,40,40,null);
	    /* End */
	    g.setTransform(matrix); // Restore

	}
	public void render(int X, int Y, int angle){
		x=X;
		y=Y;
		this.setBounds(x, y, 40, 40);
		this.angle=angle;
		this.repaint();
	}
}
